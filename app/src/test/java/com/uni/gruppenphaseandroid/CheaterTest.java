package com.uni.gruppenphaseandroid;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import com.uni.gruppenphaseandroid.cheating.Cheater;

import org.junit.Before;
import org.junit.Test;

public class CheaterTest {
    Cheater cheater;
    Cheater cheater2;
    String playerID;
    int currentRound;


    @Before
    public void emptyList(){
        Cheater.emptyCheaters();
    }


    @Test
    public void cheatingAllowed(){
    }


    @Test
    public void noteCheater1() {
        playerID = "3";
        currentRound = 3;
        cheater = new Cheater(playerID, currentRound);
        if (cheater.cheatingAllowed(playerID)) {
            Cheater.noteCheating(cheater);
        }

        playerID = "3";
        currentRound = 8;
        cheater = new Cheater(playerID, currentRound);
        if (cheater.cheatingAllowed(playerID)) {
            Cheater.noteCheating(cheater);
        }

        assertTrue(Cheater.getCheaters().size() == 1);
    }

    @Test
    public void noteCheater2(){

        playerID = "3";
        currentRound = 2;
        cheater = new Cheater(playerID, currentRound);
        if (cheater.cheatingAllowed(playerID)){
            Cheater.noteCheating(cheater);
        }

        //should not add a cheater to the list
        playerID = "3";
        currentRound = 4;
        cheater = new Cheater(playerID, currentRound);
        if (cheater.cheatingAllowed(playerID)){
            Cheater.noteCheating(cheater);
        }


        cheater = new Cheater(playerID, currentRound);
        if (cheater.cheatingAllowed(playerID)){
            Cheater.noteCheating(cheater);
        }

        assertTrue(Cheater.getCheaters().size() == 1);
    }

  @Test
    public void isCheatingAllowed1 (){
        currentRound = 10;
        playerID = "3";

        cheater = new Cheater(playerID, currentRound);
        cheater.setRoundIndex(currentRound);

boolean hija = cheater.cheatingAllowed(playerID);
        assertTrue(cheater.cheatingAllowed(playerID));

    }

    @Test
    public void isCheatingAllowed2 (){
        int previousRound = 2;
        currentRound = 8;
        playerID = "3";

        cheater = new Cheater(playerID, previousRound);
        cheater.setRoundIndex(previousRound);
        assertTrue(cheater.cheatingAllowed(playerID));


        cheater2 = new Cheater(playerID,currentRound);
        cheater2.setRoundIndex(currentRound);


        assertTrue(cheater2.cheatingAllowed(playerID));


    }

    //TODO add Tests for cheating not allowed


}
