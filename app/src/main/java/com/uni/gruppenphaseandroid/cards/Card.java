package com.uni.gruppenphaseandroid.cards;

import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Figure;
import com.uni.gruppenphaseandroid.playingfield.PlayingField;

public class Card {
    private static final String INVALID_COMBINATION_MSG = "Invalid Combination of values";
    private Cardtype cardtype;

    public Card(Cardtype cardtype) {
        this.cardtype = cardtype;
    }

    public Cardtype getCardtype() {
        return cardtype;
    }

    private void playNonEffectCard(Figure myFigure) {
        PlayingField playingField = GameManager.getInstance().getPlayingField();
        switch (getCardtype()){
            case MAGNET:
                playingField.moveToNextFigure(myFigure);
                return;
            case TWO:
            case THREE:
            case FIVE:
            case SIX:
            case EIGTH:
            case NINE:
            case TEN:
            case TWELVE:
                playingField.move(myFigure, getCardtype().getValue());
                break;
            default:throw new IllegalArgumentException(INVALID_COMBINATION_MSG);
        }
    }

    private void playEffectCard(Figure myFigure, int effect) {
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
                break;
            default:throw new IllegalArgumentException(INVALID_COMBINATION_MSG);

        }
    }

    public void playCard(Figure myFigure, int effect, Figure targetFigure) {
        if(myFigure==null){
            throw new IllegalArgumentException("myFigure cannot be null");
        }

        if(effect==-1 && targetFigure==null){
            playNonEffectCard(myFigure);
        }else if(effect>=0 && effect<=13 && targetFigure==null){
            playEffectCard(myFigure, effect);
        }else if(effect==-1 && targetFigure!=null){
            //playSwitchCard
            if(getCardtype()==Cardtype.SWITCH) {
                GameManager.getInstance().getPlayingField().switchPositions(myFigure, targetFigure);
            }else{
                throw new IllegalArgumentException(INVALID_COMBINATION_MSG);
            }
        }else{
            throw new IllegalArgumentException(INVALID_COMBINATION_MSG);
        }
    }
}
