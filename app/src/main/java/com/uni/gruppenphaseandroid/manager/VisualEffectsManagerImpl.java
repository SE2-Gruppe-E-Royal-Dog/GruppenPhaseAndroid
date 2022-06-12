package com.uni.gruppenphaseandroid.manager;

import android.widget.ImageButton;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.cards.CardUI;

public class VisualEffectsManagerImpl extends VisualEffectsManager{

    ImageView stackImage;
    ImageButton cardHolder;
    //other attributes...

    public VisualEffectsManagerImpl(ImageView stackImage, ImageButton cardHolder) {
        this.stackImage = stackImage;
        this.cardHolder = cardHolder;
    }

    @Override
    protected void setInitialStackImage() {
        stackImage.setImageResource(R.drawable.ic_card_ablagestapel);
    }

    @Override
    protected void setStackImage() {
        LastTurn lastTurn = GameManager.getInstance().getLastTurn();
        if(lastTurn.getCardtype()==null){
            throw new IllegalArgumentException("No Cardtype has been set");
        }

        int imageId = CardUI.getInstance().cardtypeToId(lastTurn.getCardtype());
        stackImage.setImageResource(imageId);
    }

    @Override
    protected void showIllegalMoveMessage() {

    }

    @Override
    protected void showWinningScreen() {

    }

    @Override
    protected void showCanNotAcccusePlayerMessage() {

    }

    @Override
    protected void setCardHolderUI() {
        cardHolder.setImageResource(R.drawable.ic_card_cardholder);
    }
}
