package com.uni.gruppenphaseandroid.playingfield;

public abstract class FigureUI {

    public FigureUI() {
    }

    public abstract void moveFigureToPosition(FieldUI targetFieldUI);

    public abstract void setButtonClickBehaviour(Figure figure);
}
