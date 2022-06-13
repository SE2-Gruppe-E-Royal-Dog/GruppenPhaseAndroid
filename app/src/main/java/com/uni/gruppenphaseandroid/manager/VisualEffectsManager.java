package com.uni.gruppenphaseandroid.manager;

public abstract class VisualEffectsManager {

    protected abstract void setStackImage();

    protected abstract void setInitialStackImage();

    protected abstract void showIllegalMoveMessage();

    protected abstract void showWinningScreen();

    protected abstract void showCanNotAcccusePlayerMessage();

    protected abstract void setCardHolderUI ();

    protected abstract void showNoPossibleMoveMessage();

    protected abstract void showNextTurnMessage(String turnPlayerName, String colorName);
}
