package com.uni.gruppenphaseandroid.Cards;

import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class Card {
    private Cardtype cardtype;

    public Card(Cardtype cardtype) {
        this.cardtype = cardtype;
    }

    public Cardtype getCardtype() {
        return cardtype;
    }

    private void playNumCard(Figure myFigure) throws Exception {
            GameManager.getInstance().getPlayingField().move(myFigure, getCardtype().getValue());
    }

    private void playEffectCards(Figure myFigure, int effect) throws Exception {
        PlayingField playingField = GameManager.getInstance().getPlayingField();
        switch (getCardtype()) {
            case FOUR_PLUSMINUS:
                if (effect == 1) playingField.move(myFigure, 4);
                else playingField.move(myFigure, -4);
                return;
            case ONETOSEVEN:
                playingField.move(myFigure, effect);
                return;
            case ONEORELEVEN_START:
                if (effect == 0) playingField.moveToStart(myFigure);
                else if (effect == 1) playingField.move(myFigure, 1);
                else playingField.move(myFigure, 11);
                return;
            case THIRTEEN_START:
                if (effect == 0) playingField.moveToStart(myFigure);
                else playingField.move(myFigure, 13);
        }
    }

    private void playSpecialCards(Figure myFigure, Figure targetFigure) throws Exception {
        PlayingField playingField = GameManager.getInstance().getPlayingField();
        switch (getCardtype()) {
            case MAGNET:
                playingField.move(myFigure, targetFigure.getCurrentField().getFieldID() - myFigure.getCurrentField().getFieldID() - 1);
                return;
            case SWITCH:
                playingField.switchPositions(myFigure, targetFigure);
        }
    }

    public void playCard(Figure myFigure, int effect, Figure targetFigure) throws Exception {
        if(myFigure==null){
            throw new IllegalArgumentException("myFigure cannot be null");
        }

        if(effect==-1 && targetFigure==null){
            playNumCard(myFigure);
        }else if(effect>=0 && effect<=13 && targetFigure==null){
            playEffectCards(myFigure, effect);
        }else if(effect==0){
            playSpecialCards(myFigure, targetFigure);
        }else{
            throw new IllegalArgumentException("Invalid Combination of values");
        }
    }
}
