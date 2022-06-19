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
        Field newPosition = currentField.getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (typ == Typ.KNIGHT) {
            return true;
        } else {
            if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == figure2.getColor()) {
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
        if (card.getCardtype() == Cardtype.FOUR_PLUSMINUS || card.getCardtype() == Cardtype.TEN) {
            return true;
        } else {
            return false;
        }
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

        if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == color && ((StartingField) newPosition).getColor() != figure2.getColor()) {
            return true;
        } else if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == figure2.getColor() || newPosition instanceof GoalField) {
            return false;
        } return true;
    }

    /**
     * A figure cannot be changed with another one (no matter which color),
     * if its current position is the own starting field or own goal area.
     * Exception: Jerk is allowed to move up to 2 fields less than displayed on the card,
     * if he is moving into the goal area.
     * Moving into Goal Area starting/pausing from/on the Starting Field isnot possible!
     * this figure - figure who moves
     * @param fieldsToMove - number of fields to move
     * @return true if moving is possible
     */
    public boolean isMoving(int fieldsToMove) {
        if(fieldsToMove == -4){//-4 is a green card, it will always work, no need to rewrite the logic for backwards-checking
            return true;
        }
        Field originField = currentField;

        if (currentField.getNextField() == null) { //check again, in case entire loop will be skipped if fieldsToMove == 1
            return false;
        }
        for (int i = 0; i < fieldsToMove - 1; i++) {
            if (currentField.getNextField() == null) {
                return false;
            }
            if (currentField.getNextField().getCurrentFigure() != null && !isOvertakingPossible()) { // check if figure1 is allowed to overtake figure2
                return false;
            }

            if (currentField instanceof StartingField && ((StartingField) currentField).getColor() == color) {
                GoalField goalfield = ((StartingField) currentField).getNextGoalField();
                if (isGoalFieldPossible(fieldsToMove)) {
                    setCurrentField(goalfield);
                    continue;
                }
            }
            setCurrentField(currentField.getFieldAtDistance(1, color));
        }


        Field newPosition = originField.getFieldAtDistance(fieldsToMove, color);
        if (newPosition.getCurrentFigure() != null) {
            return isBeaten(); // check if figure2 can be beaten
        }
        setCurrentField(originField);
        return true;
    }

    public boolean isGoalFieldPossible(int fieldsToMove) {
        if (typ == Typ.JERK && fieldsToMove <= 6 || typ != Typ.JERK && fieldsToMove <= 4) {
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
            return newPositionFigure1;
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
            field = field.getNextField();
        }
        if(field!=current.getPreviousField()){
            return true;
        }
        return false;
    }


    // Getter and Setter:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id++;
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
