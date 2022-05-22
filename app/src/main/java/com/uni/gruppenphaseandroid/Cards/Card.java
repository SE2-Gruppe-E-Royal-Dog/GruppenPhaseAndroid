package com.uni.gruppenphaseandroid.Cards;

import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Field;
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
            default:throw new IllegalArgumentException("Invalid Combination of values");
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
            default:throw new IllegalArgumentException("Invalid Combination of values");

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
        }else if(effect==-1){
            //playSwitchCard
            if(getCardtype()==Cardtype.SWITCH) {
                GameManager.getInstance().getPlayingField().switchPositions(myFigure, targetFigure);
            }else{
                throw new IllegalArgumentException("Invalid Combination of values");
            }
        }else{
            throw new IllegalArgumentException("Invalid Combination of values");
        }
    }


    public boolean checkIfCardIsPlayable(Figure myFigure, int effect, Figure targetFigure){
        if(myFigure==null){
            throw new IllegalArgumentException("myFigure cannot be null");
        }

        if(effect==-1 && targetFigure==null){
            return checkNonEffectCard(myFigure);
        }else if(effect>=0 && effect<=13 && targetFigure==null){
            return checkEffectCard(myFigure, effect);
        }else if(effect==-1){
            //playSwitchCard
            if(getCardtype()==Cardtype.SWITCH) {
                return checkSwitchCardFigure(myFigure) && checkSwitchCardFigure(targetFigure);
            }else{
                throw new IllegalArgumentException("Invalid Combination of values");
            }
        }else{
            throw new IllegalArgumentException("Invalid Combination of values");
        }
    }

    private boolean checkNonEffectCard(Figure figure){
        switch (getCardtype()){
            case MAGNET:
                return checkMagnet(figure);
            case TWO:
            case THREE:
            case FIVE:
            case SIX:
            case EIGTH:
            case NINE:
            case TEN:
            case TWELVE:
                return figure.checkMoving(figure, getCardtype().getValue());
            default:throw new IllegalArgumentException("Invalid Combination of values");
        }
    }

    private boolean checkMagnet(Figure figure){
        //TODO: check if there is another figure on the field
        Field current = figure.getCurrentField();
        Field field = current;

        while (field.getNextField().getCurrentFigure()==null) {
            field = field.getNextField();
        }
        if(field!=current.getPreviousField()){
            return true;
        }
        return false;
    }

    private boolean checkEffectCard(Figure figure, int effect){
        switch (getCardtype()) {
            case FOUR_PLUSMINUS:
                if (effect == 1) return figure.checkMoving(figure, 4);
                else return figure.checkMoving(figure, -4);
            case ONETOSEVEN:
                return figure.checkMoving(figure, effect);
            case ONEORELEVEN_START:
                //TODO: check for starting field
                if (effect == 0) return figure.isOnStartingfield();
                else if (effect == 1) return figure.checkMoving(figure, 1);
                else return figure.checkMoving(figure, 11);
            case THIRTEEN_START:
                //TODO: check for starting field
                if (effect == 0) return figure.isOnStartingfield();
                else figure.checkMoving(figure, 13);

                break;
            default:throw new IllegalArgumentException("Invalid Combination of values");

        }
        return true;
    }

    private boolean checkSwitchCardFigure(Figure figure){
        //TODO: check if switch is possible for figure 1
        if(figure.isOnStartingfield() || figure.isOnGoalfield()){
            return false;
        }
        return true;
    }
}
