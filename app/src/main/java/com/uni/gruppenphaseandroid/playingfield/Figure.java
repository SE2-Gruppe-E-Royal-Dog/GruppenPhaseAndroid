package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.Cards.Card;
import com.uni.gruppenphaseandroid.Cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;

public class Figure {
    private int id;
    private Color color;
    private Field currentField;
    private Typ typ;
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
     * @param figure1 - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    protected boolean checkOvertaking(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (figure1.getTyp() == Typ.KNIGHT) {
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
    private boolean checkGreenCard(Figure figure1) {
        Card card = GameManager.getInstance().getSelectedCard();
        if (card.getCardtype() == Cardtype.FOUR_PLUSMINUS || card.getCardtype() == Cardtype.TEN) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkOvertakingPossible(Figure figure1) {
        if (checkGreenCard(figure1)) {
            return true;
        } else {
            return figure1.checkOvertaking(figure1);
        }
    }

    /**
     *  A figure cannot be beaten by another one (no matter which color),
     *  if its current position is the own starting field or the own goal area.
     * @param figure1 - figure who moves
     * figure2 - figure to be beaten
     * @return true if beating is possible
     */
    protected boolean checkBeaten(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (newPosition instanceof StartingField && ((StartingField) newPosition).getColor() == figure2.getColor() || newPosition instanceof GoalField) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * A figure cannot be changed with another one (no matter which color),
     * if its current position is the own starting field or own goal area.
     * Exception: Jerk is allowed to move up to 2 fields less than displayed on the card,
     * if he is moving into the goal area.
     * @param figure1 - figure who moves
     * @param fieldsToMove - number of fields to move
     * @return true if moving is possible
     */
    public boolean checkMoving(Figure figure1, int fieldsToMove) {
        Field originField = figure1.getCurrentField();

        for (int i = 0; i < fieldsToMove - 1; i++) {
            if (figure1.getCurrentField().getNextField().getCurrentFigure() != null && !checkOvertakingPossible(figure1)) { // check if figure1 is allowed to overtake figure2
                return false;
            }

            if (figure1.getCurrentField() instanceof StartingField && ((StartingField) figure1.getCurrentField()).getColor() == figure1.getColor()) {
                GoalField goalfield = ((StartingField) figure1.getCurrentField()).getNextGoalField();
                if (typ == Typ.JERK && fieldsToMove <= 6 || typ != Typ.JERK && fieldsToMove <= 4) {
                    figure1.setCurrentField(goalfield);
                    continue;
                }
            }
            figure1.setCurrentField(figure1.getCurrentField().getFieldAtDistance(1, figure1.getColor()));
        }
        figure1.setCurrentField(originField);

        Field newPosition = figure1.getCurrentField().getFieldAtDistance(fieldsToMove, figure1.getColor());
        if (newPosition.getCurrentFigure() != null) {
            return checkBeaten(figure1); // check if figure2 can be beaten
        }
        return true;
    }

    /**
     * Sets new Position of Figure.
     * @param figure1 - figure who moves
     * @param fieldsToMove - number of fields to move
     * @return new Position Field
     */
    protected Field setNewPosition(Figure figure1, int fieldsToMove) {
        if (checkMoving(figure1, fieldsToMove)) { // check if moving possible
            Field newPositionFigure1 = figure1.getCurrentField().getFieldAtDistance(fieldsToMove, figure1.getColor());
            return newPositionFigure1;
        } else {
            return null;
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

    public boolean checkisOnNormalField(){
        if(isOnStartingAreaField() || isOnGoalField()){
            return false;
        }
        return true;
    }

    public boolean checkIfAnotherFigureOnPlayingfield(){
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
