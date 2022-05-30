package com.uni.gruppenphaseandroid.playingfield;

public class Knight extends Figure {

    public Knight(int id, Color color, Field currentField, Typ typ, FigureUI figureUI) {
        super(id, color, currentField, typ, figureUI);
    }

    public Knight() {
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

        if(super.checkOvertaking(figure1)) {
            return figure2.getTyp() == Typ.JERK && figure2.getTyp() == Typ.CITIZEN && figure2.getTyp() == Typ.KNIGHT;
        } else {
            return false;
        }
    }
}
