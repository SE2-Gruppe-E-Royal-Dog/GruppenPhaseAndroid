package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.Cards.Card;
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
     * @param figure1 - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    protected boolean checkOvertaking(Figure figure1) {
        Field newPosition = figure1.getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if(super.checkOvertaking(figure1) == true) {
            if (figure2.getTyp() != Typ.JERK) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Jerk is allowed to move up to 2 fields less than displayed on the card,
     * if he is moving into the goal area.
     * @param figure1 - figure who moves
     * @param fieldsToMove - fields to move
     * @return new Position of Jerk within Goal Area.
     */
    @Override
    protected Field setNewPosition(Figure figure1, int fieldsToMove) {
        Card card = GameManager.getInstance().getSelectedCard();
        Field newPositionFigure1 = super.setNewPosition(figure1, fieldsToMove);

        if (figure1.getCurrentField() instanceof StartingField && ((StartingField) figure1.getCurrentField()).getColor() == figure1.getColor()) {
            GoalField goalfield = ((StartingField) figure1.getCurrentField()).getNextGoalField();
            if (fieldsToMove <= 6) {
                switch (fieldsToMove) {
                    case 6:
                        newPositionFigure1 = figure1.getCurrentField().getFieldAtDistance(fieldsToMove - 2, figure1.getColor());
                        break;
                    case 5:
                        newPositionFigure1 = figure1.getCurrentField().getFieldAtDistance(fieldsToMove - 1, figure1.getColor());
                        break;
                    default:
                        newPositionFigure1 = figure1.getCurrentField().getFieldAtDistance(fieldsToMove, figure1.getColor());
                }
            }
        }    return newPositionFigure1;

    }
}
