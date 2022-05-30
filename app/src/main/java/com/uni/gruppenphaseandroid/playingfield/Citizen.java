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
     * this figure - figure who moves
     * figure 2 - figure to be overtaken
     * @return true if overtaking possible
     */
    @Override
    public boolean checkOvertaking() {
        Field newPosition = getCurrentField().getNextField();
        Figure figure2 = newPosition.getCurrentFigure();

        if(super.checkOvertaking() == true) {
            switch (figure2.getTyp()) {
                case JERK:
                case CITIZEN:
                    return true;
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

        if((super.checkBeaten() == true && figure2.getTyp() == Typ.KING) || super.checkBeaten() == false) { // TODO: Noch offen
            return false;
        } return true;
    }

    /**
     * If moving to a special field clockwise and taking a card,
     * citizen is allowed to jump to the next special field -
     * no matter of blocked starting fields or higher degree.
     * this figure - figure who moves
     * @param fieldsToMove - fields to move
     * @return true if moving possible
     */
    @Override
    public boolean checkMoving(int fieldsToMove) { // TODO: Sonderfeld einbauen für Karte ziehen? - bleibt offen


        return true;
    }
}
