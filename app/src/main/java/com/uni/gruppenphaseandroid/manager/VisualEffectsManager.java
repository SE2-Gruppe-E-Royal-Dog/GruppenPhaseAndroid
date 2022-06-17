package com.uni.gruppenphaseandroid.manager;

import com.uni.gruppenphaseandroid.cards.Card;

public abstract class VisualEffectsManager {

    protected abstract void setStackImage();

    protected abstract void setInitialStackImage();

    protected abstract void showIllegalMoveMessage();

    protected abstract void showWinningScreen();

    protected abstract void showCanNotAcccusePlayerMessage();

    protected abstract void setCardHolderUI ();

    protected abstract void showNoPossibleMoveMessage();

    protected abstract void showNextTurnMessage(String turnPlayerName);

    public abstract void setStackImageAfterMyMove (Card card);
}
