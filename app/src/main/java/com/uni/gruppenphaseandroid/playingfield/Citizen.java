package com.uni.gruppenphaseandroid.playingfield;

import com.uni.gruppenphaseandroid.cards.Card;

public class Citizen extends Figure { // TODO: Sonderfeld offen

    public Citizen(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
        typ = Typ.CITIZEN;
    }

    public Citizen() {
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
            if (figure2.getTyp() != Typ.JERK || figure2.getTyp() != Typ.CITIZEN) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * If moving to a special field clockwise and taking a card,
     * citizen is allowed to jump to the next special field -
     * no matter of blocked starting fields or higher degree.
     * @param figure1 - figure who moves
     * @param fieldsToMove - fields to move
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(Figure figure1, int fieldsToMove) { // TODO: Sonderfeld einbauen für Karte ziehen? - bleibt offen


        return true;
    }
}
