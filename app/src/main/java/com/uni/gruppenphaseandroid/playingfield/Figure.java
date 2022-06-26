package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;

public class Figure {
    private int id;
    private Color color;
    private Field currentField;
    protected Typ typ;
    private FigureUI figureUI;

    public Figure(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        this.id = id;
        this.color = color;
        this.currentField = currentField;
        currentField.setCurrentFigure(this);
        this.typ = typ;
        this.figureUI = figureUI;
    }

    public Figure() {

    }

    /**
     * A figure cannot be overtaken by another one (no matter which color),
     * if its current position is the own starting field.
     * Exception: Knight is allowed to overtake but has to consider degree.
     * this figure - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    public boolean isOvertaking() {
        Field newPosition = currentField.getNextField(); // always check next field
        Figure figure2 = newPosition.getCurrentFigure();

        if (typ == Typ.KNIGHT) { // is always allowed to overtake within degree (not king)
            return true;
        } else {
            if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == figure2.getColor()) { // overtaking not allowed if figure2 is on own starting field
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * Green Card (4 +/- and 10) cancel overtaking rules.
     * @return true if overtaking possible
     */
    public boolean isGreenCard() {
        Card card = GameManager.getInstance().getSelectedCard();
        return (card.getCardtype() == Cardtype.FOUR_PLUSMINUS || card.getCardtype() == Cardtype.TEN);
    }

    public boolean isOvertakingPossible() {
        if (isGreenCard()) {
            return true;
        } else {
            return isOvertaking();
        }
    }

    /**
     *  A figure cannot be beaten by another one (no matter which color),
     *  if its current position is the own starting field or the own goal area.
     *  Additionally a figure can beat every other figure on its own starting field,
     *  no matter if coming from Starting Area or coming for the goal area.
     *  Figure can not beat a figure of its own color on its own Starting Field.
     * this figure - figure who moves
     * figure2 - figure to be beaten
     * @return true if beating is possible
     */
    public boolean isBeaten() {
        Field newPosition = currentField.getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == color && ((StartingField) newPosition).getColor() != figure2.getColor()) { // beating allowed if figure 2 is on foreign starting field
            return true;
        } else if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == figure2.getColor() || newPosition instanceof GoalField) { // it is not allowed to beat figure2 on its own starting field; or within goal area
            return false;
        } return true;
    }

    public boolean isBeatingKingOnNormalField(Figure figure2, Field newPosition) {
        if(figure2.getTyp() == Typ.KING && !(newPosition instanceof StartingField)) { // beating King is only allowed on foreign staring field from a figure of that color => used in Sub classes
            return true;
        } return false;
    }

    /**
     * A figure cannot be changed with another one (no matter which color),
     * if its current position is the own starting field or own goal area.
     * Exception: Jerk is allowed to move up to 2 fields less than displayed on the card,
     * if he is moving into the goal area.
     * Exception: Card 4+/- is a green card - when moving -4: the figure on that field can always be beaten.
     * Moving into Goal Area starting/pausing from/on the Starting Field is not possible!
     * this figure - figure who moves
     * @param fieldsToMove - number of fields to move
     * @return true if moving is possible
     */
    public boolean isMoving(int fieldsToMove) {
        if(fieldsToMove == -4){ // card with -4 will always be true => beating always allowed
            return true;
        }

        Field originField = currentField;
        if (currentField.getNextField() == null) { //check again, in case entire loop will be skipped if fieldsToMove == 1
            return false;
        }

        for (int i = 0; i < fieldsToMove - 1; i++) {
            if (currentField.getNextField().getCurrentFigure() != null && !isOvertakingPossible()) { // check if figure1 is allowed to overtake figure2
                setCurrentField(originField);//reset to avoid weird behaviour
                return false;
            }

            if (currentField instanceof StartingField && ((StartingField) currentField).getColor() == color && currentField != originField) { // check possibility for moving into goalarea
                GoalField goalfield = ((StartingField) currentField).getNextGoalField();
                if (isGoalFieldPossible(fieldsToMove-i)) {
                    setCurrentField(goalfield);
                    continue;
                }
            }
            setCurrentField(currentField.getFieldAtDistance(1, color)); // checking each field within "fieldsToMove"
            if (currentField.getNextField() == null) {//case we reached last goal
                setCurrentField(originField);//reset to avoid weird behaviour
                return (typ == typ.JERK && fieldsToMove-i-1 <= 2); //if we are jerk, possibly return true => jerk is allowed to move up to two fields less
            }
        }

        Field newPosition = originField.getFieldAtDistance(fieldsToMove, color); // check final destination field
        if (newPosition.getCurrentFigure() != null) {
            boolean beatingPossible = isBeaten(); // check if figure2 can be beaten
            setCurrentField(originField);//reset to avoid weird behaviour
            return beatingPossible;
        }
        setCurrentField(originField);//reset to avoid weird behaviour
        return true;
    }

    public boolean isGoalFieldPossible(int fieldsToMove) {
        if (typ == Typ.JERK && fieldsToMove <= 6 || typ != Typ.JERK && fieldsToMove <= 4) { // jerk is allowed to move up to two fields less
            return true;
        }
        return false;
    }

    /**
     * Sets new Position of Figure.
     * this figure - figure who moves
     * checkMoving is called via GameManager
     * @param fieldsToMove - number of fields to move
     * @return new Position Field
     */
    public Field setNewPosition(int fieldsToMove) {
        if (isMoving(fieldsToMove)) { // for testing included
            Field newPositionFigure1 = currentField.getFieldAtDistance(fieldsToMove, color);
            return newPositionFigure1; // actual setting of figure to final destination
        } else {
            return null;
        }
    }

    public boolean isOnStartingAreaField(){
        if(getCurrentField().getClass().equals(StartingAreaField.class)){
            return true;
        }
        return false;
    }

    public boolean isOnGoalField(){
        if(getCurrentField().getClass().equals(GoalField.class)){
            return true;
        }
        return false;
    }

    public boolean isOnNormalField(){
        if(isOnStartingAreaField() || isOnGoalField()){
            return false;
        }
        return true;
    }

    public boolean isAnotherFigureOnPlayingField(){
        Field current = this.getCurrentField();
        Field field = current;

        while (field.getNextField().getCurrentFigure()==null) {
            field = field.getNextField(); // checks each field within playfield and stops when he found a figure
        }
        if(field!=current.getPreviousField()){ // found figure, because loop wasn't finished
            return true;
        }
        return false;
    }


    // Getter and Setter:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public FigureUI getFigureUI() {
        return figureUI;
    }

    public void setFigureUI(FigureUI figureUI) {
        this.figureUI = figureUI;
    }
}
