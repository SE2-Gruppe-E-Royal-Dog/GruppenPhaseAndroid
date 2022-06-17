package com.uni.gruppenphaseandroid.cards;

import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.GameManager;

import java.util.LinkedList;
import java.util.List;

public class CardUI  {


    private static CardUI cardUI;
    LinkedList<Integer> imageCardList;



    public static CardUI getInstance() {
        if (cardUI == null) {
            cardUI = new CardUI();
        }
        return cardUI;
    }


    public CardUI() {
    }


    public void addCardToHand() {
        imageCardList = new LinkedList<>();
        List<Card> cards = GameManager.getInstance().getCardManager().getMyHandCards();

       for (Card c : cards){
           imageCardList.add(findImageView(c));
        }
    }

    public int findImageView (Card card) {

            switch (card.getCardtype()) {
                case TWO:
                    return R.drawable.ic_card_2;
                case THREE:
                    return R.drawable.ic_card_3;
                case FIVE:
                    return R.drawable.ic_card_5;
                case SIX:
                    return R.drawable.ic_card_6;
                case EIGTH:
                    return R.drawable.ic_card_8;
                case NINE:
                    return R.drawable.ic_card_9;
                case TEN:
                    return R.drawable.ic_card_10;
                case TWELVE:
                    return R.drawable.ic_card_12;
                case EQUAL:
                    return R.drawable.ic_card_copy;
                case FOUR_PLUSMINUS:
                    return R.drawable.ic_card_4;
                case ONETOSEVEN:
                    return R.drawable.ic_card_7;
                case ONEORELEVEN_START:
                    return R.drawable.ic_card_11;
                case THIRTEEN_START:
                    return R.drawable.ic_card_13;
                case MAGNET:
                    return R.drawable.ic_card_magnet;
                case SWITCH:
                    return R.drawable.ic_card_switch;

            }
            return 0;
    }

    public Cardtype idToCardType (int card) {
        switch (card) {
            case R.drawable.ic_card_2:
                return Cardtype.TWO;
            case R.drawable.ic_card_3:
                return Cardtype.THREE;
            case R.drawable.ic_card_5:
                return Cardtype.FIVE;
            case R.drawable.ic_card_6:
                return Cardtype.SIX;
            case R.drawable.ic_card_8:
                return Cardtype.EIGTH;
            case R.drawable.ic_card_9:
                return Cardtype.NINE;
            case R.drawable.ic_card_10:
                return Cardtype.TEN;
            case R.drawable.ic_card_12:
                return Cardtype.TWELVE;
            case R.drawable.ic_card_copy:
                return Cardtype.EQUAL;
            case R.drawable.ic_card_4:
                return Cardtype.FOUR_PLUSMINUS;
            case R.drawable.ic_card_7:
                return Cardtype.ONETOSEVEN;
            case R.drawable.ic_card_11:
                return Cardtype.ONEORELEVEN_START;
            case R.drawable.ic_card_13:
                return Cardtype.THIRTEEN_START;
            case R.drawable.ic_card_magnet:
                return Cardtype.MAGNET;
            case R.drawable.ic_card_switch:
                return Cardtype.SWITCH;
        }
        return null;
    }


    public LinkedList<Integer> cardsForRecyclerView (){
        addCardToHand();
        return imageCardList;
    }

    public int cardtypeToId(Cardtype cardtype){
        switch (cardtype) {
            case TWO:
                return R.drawable.ic_card_2;
            case THREE:
                return R.drawable.ic_card_3;
            case FIVE:
                return R.drawable.ic_card_5;
            case SIX:
                return R.drawable.ic_card_6;
            case EIGTH:
                return R.drawable.ic_card_8;
            case NINE:
                return R.drawable.ic_card_9;
            case TEN:
                return R.drawable.ic_card_10;
            case TWELVE:
                return R.drawable.ic_card_12;
            case EQUAL:
                return R.drawable.ic_card_copy;
            case FOUR_PLUSMINUS:
                return R.drawable.ic_card_4;
            case ONETOSEVEN:
                return R.drawable.ic_card_7;
            case ONEORELEVEN_START:
                return R.drawable.ic_card_11;
            case THIRTEEN_START:
                return R.drawable.ic_card_13;
            case MAGNET:
                return R.drawable.ic_card_magnet;
            case SWITCH:
                return R.drawable.ic_card_switch;
            default:
                return -1;
        }
    }



}


