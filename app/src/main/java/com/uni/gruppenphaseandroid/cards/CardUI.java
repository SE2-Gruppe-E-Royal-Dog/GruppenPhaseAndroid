package com.uni.gruppenphaseandroid.cards;

import com.uni.gruppenphaseandroid.R;
import com.uni.gruppenphaseandroid.manager.GameManager;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CardUI  {

    LinkedList<Integer> imageCardList;
    Map<Integer, Cardtype> cardtypeMap;



    public CardUI(){
        cardtypeMap = new HashMap<>();
        setUpMap();
    }

    public void setUpMap(){
        cardtypeMap.put(R.drawable.ic_card_2, Cardtype.TWO);
        cardtypeMap.put(R.drawable.ic_card_3, Cardtype.THREE);
        cardtypeMap.put(R.drawable.ic_card_4, Cardtype.FOUR_PLUSMINUS);
        cardtypeMap.put(R.drawable.ic_card_5, Cardtype.FIVE);
        cardtypeMap.put(R.drawable.ic_card_6, Cardtype.SIX);
        cardtypeMap.put(R.drawable.ic_card_7, Cardtype.ONETOSEVEN);
        cardtypeMap.put(R.drawable.ic_card_8, Cardtype.EIGTH);
        cardtypeMap.put(R.drawable.ic_card_9, Cardtype.NINE);
        cardtypeMap.put(R.drawable.ic_card_10, Cardtype.TEN);
        cardtypeMap.put(R.drawable.ic_card_11, Cardtype.ONEORELEVEN_START);
        cardtypeMap.put(R.drawable.ic_card_12, Cardtype.TWELVE);
        cardtypeMap.put(R.drawable.ic_card_13, Cardtype.THIRTEEN_START);
        cardtypeMap.put(R.drawable.ic_card_switch, Cardtype.SWITCH);
        cardtypeMap.put(R.drawable.ic_card_copy, Cardtype.EQUAL);
        cardtypeMap.put(R.drawable.ic_card_magnet, Cardtype.MAGNET);
    }

    public void addCardToHand() {
        imageCardList = new LinkedList<>();
        List<Card> cards = GameManager.getInstance().getCardManager().getMyHandCards();

       for (Card c : cards){
           imageCardList.add(findImageView(c));
        }
    }

    public int findImageView (Card card) {
            for (Map.Entry<Integer, Cardtype> entry : cardtypeMap.entrySet()){
                if (entry.getValue().equals(card.getCardtype())){
                    return entry.getKey();
                }
            }
            return 0;
    }

    public Cardtype idToCardType (int card) {
        return cardtypeMap.get(card);
    }


    public LinkedList<Integer> cardsForRecyclerView (){
        addCardToHand();
        return imageCardList;
    }

    public Map<Integer, Cardtype> getCardtypeMap(){
        return cardtypeMap;
    }
}


