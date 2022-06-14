package com.uni.gruppenphaseandroid;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.uni.gruppenphaseandroid.cheating.Cheater;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.FigureManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheaterTest {
    Cheater cheater;
    Cheater cheater2;
    int playerID;
    int currentRound;


    @Before
    public void setUp() {
        Cheater.emptyCheaters();

        playerID = 0;
        currentRound = GameManager.getInstance().getRoundIndex();
        cheater = new Cheater(playerID,currentRound);

        playerID = 1;
        currentRound = GameManager.getInstance().getRoundIndex();
        cheater2 = new Cheater(playerID,currentRound);
    }


    @Test
    public void checkSetUp(){
        assertEquals(0, Cheater.getCheaters().size());
    }

    @Test
    public void cheatingAllowedForThisClientTrue() {
        GameManager.getInstance().setHasCheated(false);
        assertTrue(cheater.cheatingAllowedForThisClient());
    }

    @Test
    public void cheatingAllowedForThisClientFalse() {
        GameManager.getInstance().setHasCheated(true);
        assertFalse(cheater.cheatingAllowedForThisClient());
        GameManager.getInstance().setHasCheated(false);
    }

    @Test
    public void noteCheater(){
        Cheater.noteCheating(cheater);
        assertEquals(1, Cheater.getCheaters().size());
    }

    @Test
    public void addNewCheater(){
        playerID = 2;
        currentRound = GameManager.getInstance().getRoundIndex();
        Cheater cheater3 = new Cheater(playerID,currentRound);

    }

    @Test
    public void addNewCheaterToList(){
        playerID = 0;
        currentRound = GameManager.getInstance().getRoundIndex();
        cheater2 = new Cheater(playerID,currentRound);

        assertEquals(0,Cheater.getCheaters().size());
        cheater2.cheating(cheater2);
        assertEquals(1,Cheater.getCheaters().size());
    }

    @Test
    public void checkCheatingAllowed(){
        playerID = 0;
        currentRound = GameManager.getInstance().getRoundIndex();
        cheater2 = new Cheater(playerID,currentRound);

        GameManager.getInstance().setHasCheated(true);
        assertFalse(cheater.cheatingAllowed(Integer.toString(cheater.getPlayerID())));
    }

    @Test
    public void cheatLastCheat(){
        playerID = 2;
        currentRound = 3;
        Cheater cheater3 = new Cheater(playerID,currentRound);
        cheater3.cheating(cheater3);
        assertEquals(3, cheater3.getLastCheat(cheater3.getPlayerID()));
    }

    @Test
    public void makeAccusationTest(){
        //TODO Testable..how ;-;
        /*cheater.cheating(cheater);
        cheater2.cheating(cheater2);

        GameManager gm = mock(GameManager.class);
        FigureManager fm = mock((FigureManager.class));

        Cheater.makeAccusation(playerID, currentRound, 3);

        verify(gm, times(1)).punishPlayer(GameManager.getInstance().getCurrentTurnPlayerNumber());

    */

    }
}
