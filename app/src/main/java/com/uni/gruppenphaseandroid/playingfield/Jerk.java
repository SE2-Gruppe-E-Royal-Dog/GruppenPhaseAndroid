package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.manager.GameManager;

public class Jerk extends Figure {

    public Jerk(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
        typ = Typ.JERK;
    }

    public Jerk() {
    }

    /**
     * Degree has to be considered.
     * this figure - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    public boolean checkOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if (super.checkOvertaking() == true) {
            switch (figure2.getTyp()) {
                case JERK:
                    return true;
                case CITIZEN:
                case KNIGHT:
                case KING:
                    return false;
            }
        } return false;
    }

    /**
     * King can only be beaten by another king.
     * this figure - figure who moves
     * figure 2 - figure to be beaten
     * @return true if beating is possible
     */
    @Override
    public boolean checkBeaten() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if((super.checkBeaten() == true && figure2.getTyp() == Typ.KING) || super.checkBeaten() == false) {
            return false;
        } return true;
    }

    /**
     * Jerk is allowed to move up to 2 fields less than displayed on the card,
     * if he is moving into the goal area.
     * this gitfigure1 - figure who moves
     * @param fieldsToMove - fields to move
     * @return new Position of Jerk within Goal Area.
     */
    @Override
    protected Field setNewPosition ( int fieldsToMove){
        Card card = GameManager.getInstance().getSelectedCard();
        Field newPositionFigure1 = super.setNewPosition(fieldsToMove);

        if (getCurrentField() instanceof StartingField && ((StartingField) getCurrentField()).getColor() == getColor()) {
            GoalField goalfield = ((StartingField) getCurrentField()).getNextGoalField();
            if (fieldsToMove <= 6) {
                switch (fieldsToMove) {
                    case 6:
                        newPositionFigure1 = getCurrentField().getFieldAtDistance(fieldsToMove - 2, getColor());
                        break;
                    case 5:
                        newPositionFigure1 = getCurrentField().getFieldAtDistance(fieldsToMove - 1, getColor());
                        break;
                    default:
                        newPositionFigure1 = getCurrentField().getFieldAtDistance(fieldsToMove, getColor());
                }
            }
        } return newPositionFigure1;
    }
}
