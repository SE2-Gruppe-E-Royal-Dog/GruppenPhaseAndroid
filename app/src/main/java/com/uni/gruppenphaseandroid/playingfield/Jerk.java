package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.Cards.Card;

public class Jerk extends Figure {

    public Jerk(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
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
    public boolean checkOvertaking(Figure figure1) {
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
     * @param card which is played
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(Figure figure1, Card card) { // TODO: Button implementieren für wieviele Punkte verfallen lassen => offen
        if(super.checkMoving(figure1, card) == true &&) { // noch offen
            return true;
        } else {
            return false;
        }

    }
}
