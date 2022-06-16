package com.uni.gruppenphaseandroid.playingfieldtests;

import com.uni.gruppenphaseandroid.cards.Card;
import com.uni.gruppenphaseandroid.cards.Cardtype;
import com.uni.gruppenphaseandroid.manager.GameManager;
import com.uni.gruppenphaseandroid.playingfield.Citizen;
import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.GoalField;
import com.uni.gruppenphaseandroid.playingfield.Jerk;
import com.uni.gruppenphaseandroid.playingfield.King;
import com.uni.gruppenphaseandroid.playingfield.Knight;
import com.uni.gruppenphaseandroid.playingfield.StartingAreaField;
import com.uni.gruppenphaseandroid.playingfield.StartingField;
import com.uni.gruppenphaseandroid.playingfield.Typ;

import org.junit.Assert;
import org.junit.Test;

public class PlayFieldTestMove extends FigureTest {

    @Override
    public void setUp(){
        super.setUp();
        Card greencard = new Card(Cardtype.ONEORELEVEN_START);
        GameManager.getInstance().setSelectedCard(greencard);
    }

    /**
     * Test auf alle Figuren von Green ohne GreenCard - Normales Feld
     */
    @Test
    public void checkMoveJerkRed4FieldsGivePosition() { // OK
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,4);
        Assert.assertEquals(field5, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatsJerkGreenGivePositionJerkRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertEquals(field2, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatsJerkGreenGivePositionJerkGreen() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkRedOvertakingJerkGreenGivePositionJerkRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,2);
        Assert.assertEquals(field3, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatsJerkGreenGivePositionCitizenRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertEquals(field2, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatsJerkGreenGivePositionJerkGreen() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenRedOvertakingJerkGreenGivePositionCitizenRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,2);
        Assert.assertEquals(field3, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatsJerkGreenGivePositionKnightRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertEquals(field2, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatsJerkGreenGivePositionJerkGreen() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightRedOvertakingJerkGreenGivePositionKnightRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,2);
        Assert.assertEquals(field3, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatenJerkGreenPositionKingRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertEquals(field2, kingRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatsJerkGreenGivePositionJerkGreen() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingRedOvertakingJerkGreenGivePositionKingRed() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field2, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,2);
        Assert.assertEquals(field3, kingRed.getCurrentField());
    }


    @Test
    public void checkMoveJerkBlueBeatsCitizenGreenGivePositionJerkBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertEquals(field2, jerkBlue.getCurrentField());
    }

    @Test
    public void checkMoveJerkBlueBeatsCitizenGreenGivePositionCitizenGreen() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        playingField.move(jerkBlue,1);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkBlueOvertakingCitizenGreenGivePositionJerkBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(2));
    }


    @Test
    public void checkMoveCitizenBlueBeatsCitizenGreenGivePositionCitizenBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertEquals(field2, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveCitizenBlueBeatsCitizenGreenGivePositionCitizenGreen() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,1);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenBlueOvertakingCitizenGreenGivePositionCitizenBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        playingField.move(citizenBlue,2);
        Assert.assertEquals(field3, citizenBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatsCitizenGreenGivePositionKnightBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertEquals(field2, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKnightBlueBeatsCitizenGreenGivePositionCitizenGreen() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,1);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightBlueOvertakingCitizenGreenGivePositionKnightBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,2);
        Assert.assertEquals(field3, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatsCitizenGreenGivePositionKingBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertEquals(field2, kingBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatsCitizenGreenGivePositionCitizenGreen() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingBlueOvertakingCitizenGreenGivePositionKingBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field2, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,2);
        Assert.assertEquals(field3, kingBlue.getCurrentField());
    }


    @Test
    public void checkMoveJerkRedBeatsKnightGreenGivePositionJerkRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertEquals(field2, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatsKnightGreenGivePositionKnightGreen() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveJerkRedOvertakingKnightGreenGivePositionJerkRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field1, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(2));
    }


    @Test
    public void checkMoveCitizenRedBeatsKnightGreenGivePositionCitizenRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertEquals(field2, citizenRed.getCurrentField());
    }

    @Test
    public void checkMoveCitizenRedBeatsKnightGreenGivePositionKnightGreen() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        playingField.move(citizenRed,1);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveCitizenRedOvertakingKnightGreenGivePositionCitizenRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field1, Typ.CITIZEN, figureUI2);
        Assert.assertNull(citizenRed.setNewPosition(2));
    }


    @Test
    public void checkMoveKnightRedBeatsKnightGreenGivePositionKnightRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertEquals(field2, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKnightRedBeatsKnightGreenGivePositionKnightGreen() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,1);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKnightRedOvertakingKnightGreenGivePositionKnightRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field1, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,2);
        Assert.assertEquals(field3, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatsKnightGreenGivePositionKingRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertEquals(field2, kingRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatsKnightGreenGivePositionKnightGreen() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,1);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingRedOvertakingKnightGreenGivePositionKingRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field2, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field1, Typ.KING, figureUI4);
        playingField.move(kingRed,2);
        Assert.assertEquals(field3, kingRed.getCurrentField());
    }


    @Test
    public void checkMoveJerkBlueBeatsKingGreenGivePositionJerkBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveJerkBlueOvertakingKingGreenGivePositionJerkBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field1, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(2));
    }

    @Test
    public void checkMoveCitizenBlueBeatsKingGreenGivePositionCitizenBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveCitizenBlueOvertakingKingGreenGivePositionCitizenBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field1, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(2));
    }

    @Test
    public void checkMoveKnightBlueBeatsKingGreenGivePositionKnightBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertNull(knightBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveKnightBlueOvertakingKingGreenGivePositionKnightBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field1, Typ.KNIGHT, figureUI7);
        Assert.assertNull(knightBlue.setNewPosition(2));
    }


    @Test
    public void checkMoveKingBlueBeatsKingGreenGivePositionKingBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertEquals(field2, kingBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatsKingGreenGivePositionKingGreen() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,1);
        Assert.assertTrue(kingGreen.getCurrentField() instanceof StartingAreaField);
    }

    @Test
    public void checkMoveKingBlueOvertakingKingGreenGivePositionKingBlue() { // OK
        kingGreen = new King(12, Color.GREEN, field2, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field1, Typ.KING, figureUI8);
        playingField.move(kingBlue,2);
        Assert.assertEquals(field3, kingBlue.getCurrentField());
    }


    /**
     * Test auf alle Figuren von Green ohne GreenCard - Startfeld
     */
    @Test
    public void checkMoveJerkRedToStartingFieldGivePosition() { // OK
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        playingField.move(jerkRed,1);
        Assert.assertEquals(field13, jerkRed.getCurrentField());
    }

    @Test
    public void checkMoveJerkRedBeatsJerkGreenOnStartingFieldGivePositionJerkRedNull() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(1));
    }

    @Test
    public void checkMoveJerkRedOvertakingJerkGreenOnStartingFieldGivePositionJerkRedNull() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(2));
    }


    @Test
    public void checkMoveCitizenRedBeatsJerkGreenOnStartingFieldGivePositionCitizenRedNull() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertNull(citizenRed.setNewPosition(1));
    }

    @Test
    public void checkMoveCitizenRedOvertakingJerkGreenOnStartingFieldGivePositionCitizenRedNull() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertNull(citizenRed.setNewPosition(2));
    }


    @Test
    public void checkMoveKnightRedBeatsJerkGreenOnStartingFieldGivePositionKnightRedNull() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertNull(knightRed.setNewPosition(1));
    }

    @Test
    public void checkMoveKnightRedOvertakingJerkGreenOnStartingFieldGivePositionKnightRedNull() { // OK ???
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,2);
        Assert.assertEquals(field14, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatsJerkGreenOnStartingFieldGivePositionKingRedNull() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertNull(kingRed.setNewPosition(1));
    }

    @Test
    public void checkMoveKingRedOvertakingJerkGreenOnStartingFieldGivePositionKingRedNull() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field13, Typ.JERK, figureUI9);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertNull(kingRed.setNewPosition(2));
    }


    @Test
    public void checkMoveJerkBlueBeatsCitizenGreenOnStartingFieldGivePositionJerkBlueNull() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveJerkBlueOvertakingCitizenGreenOnStartingFieldGivePositionJerkBlueNull() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(2));
    }


    @Test
    public void checkMoveCitizenBlueBeatsCitizenGreenOnStartingFieldGivePositionCitizenBlueNull() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveCitizenBlueOvertakingCitizenGreenOnStartingFieldGivePositionCitizenBlueNull() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(2));
    }


    @Test
    public void checkMoveKnightBlueBeatsCitizenGreenOnStartingFieldGivePositionKnightBlueNull() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertNull(knightBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveKnightBlueOvertakingCitizenGreenOnStartingFieldGivePositionKnightBlue() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        playingField.move(knightBlue,2);
        Assert.assertEquals(field14, knightBlue.getCurrentField());
    }

    @Test
    public void checkMoveKingBlueBeatsCitizenGreenOnStartingFieldGivePositionKingBlueNull() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertNull(kingBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveKingBlueOvertakingCitizenGreenOnStartingFieldGivePositionKingBlueNull() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field13, Typ.CITIZEN, figureUI10);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertNull(kingBlue.setNewPosition(2));
    }


    @Test
    public void checkMoveJerkRedBeatsKnightGreenOnStartingFieldGivePositionJerkRedNull() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(1));
    }

    @Test
    public void checkMoveJerkRedOvertakingKnightGreenOnStartingFieldGivePositionJerkRedNull() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        jerkRed = new Jerk(1, Color.RED, field12, Typ.JERK, figureUI1);
        Assert.assertNull(jerkRed.setNewPosition(2));
    }


    @Test
    public void checkMoveCitizenRedBeatsKnightGreenOnStartingFieldGivePositionCitizenRedNull() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertNull(citizenRed.setNewPosition(1));
    }

    @Test
    public void checkMoveCitizenRedOvertakingKnightGreenOnStartingFieldGivePositionCitizenRedNull() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        citizenRed = new Citizen(2, Color.RED, field12, Typ.CITIZEN, figureUI2);
        Assert.assertNull(citizenRed.setNewPosition(2));
    }


    @Test
    public void checkMoveKnightRedBeatsKnightGreenOnStartingFieldGivePositionKnightRedNull() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        Assert.assertNull(knightRed.setNewPosition(1));
    }

    @Test
    public void checkMoveKnightRedOvertakingKnightGreenOnStartingFieldGivePositionKnightRed() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        knightRed = new Knight(3, Color.RED, field12, Typ.KNIGHT, figureUI3);
        playingField.move(knightRed,2);
        Assert.assertEquals(field14, knightRed.getCurrentField());
    }

    @Test
    public void checkMoveKingRedBeatsKnightGreenOnStartingFieldGivePositionKingRedNull() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertNull(kingRed.setNewPosition(1));
    }

    @Test
    public void checkMoveKingRedOvertakingKnightGreenOnStartingFieldGivePositionKingRedNull() { // OK
        knightGreen = new Knight(11, Color.GREEN, field13, Typ.KNIGHT, figureUI11);
        kingRed = new King(4, Color.RED, field12, Typ.KING, figureUI4);
        Assert.assertNull(kingRed.setNewPosition(2));
    }


    @Test
    public void checkMoveJerkBlueBeatsKingGreenOnStartingFieldGivePositionJerkBlueNull() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveJerkBlueOvertakingKingGreenOnStartingFieldGivePositionJerkBlueNull() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        jerkBlue = new Jerk(5, Color.BLUE, field12, Typ.JERK, figureUI5);
        Assert.assertNull(jerkBlue.setNewPosition(2));
    }

    @Test
    public void checkMoveCitizenBlueBeatsKingGreenOnStartingFieldGivePositionCitizenBlueNull() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveCitizenBlueOvertakingKingGreenOnStartingFieldGivePositionCitizenBlueNull() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        citizenBlue = new Citizen(6, Color.BLUE, field12, Typ.CITIZEN, figureUI6);
        Assert.assertNull(citizenBlue.setNewPosition(2));
    }

    @Test
    public void checkMoveKnightBlueBeatsKingGreenOnStartingFieldGivePositionKnightBlueNull() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertNull(knightBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveKnightBlueOvertakingKingGreenOnStartingFieldGivePositionKnightBlueNull() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        knightBlue = new Knight(7, Color.BLUE, field12, Typ.KNIGHT, figureUI7);
        Assert.assertNull(knightBlue.setNewPosition(2));
    }


    @Test
    public void checkMoveKingBlueBeatsKingGreenOnStartingFieldGivePositionKingBlueNull() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertNull(kingBlue.setNewPosition(1));
    }

    @Test
    public void checkMoveKingBlueOvertakingKingGreenOnStartingFieldGivePositionKingBlueNull() { // OK
        kingGreen = new King(12, Color.GREEN, field13, Typ.KING, figureUI12);
        kingBlue = new King(8, Color.BLUE, field12, Typ.KING, figureUI8);
        Assert.assertNull(kingBlue.setNewPosition(2));
    }

    /**
     * Zieleinfahrt grüne Figuren
     * Jerk kann bis zu 2 Punkte verfallen lassen
     */
    @Test
    public void checkMoveGreenJerkIntoGoalFields2True() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        playingField.move(jerkGreen, 2);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenJerkIntoGoalFields5True() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        playingField.move(jerkGreen, 5);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof GoalField);
    }

    /*@Test
    public void checkMoveGreenJerkIntoGoalFields6True() { // TODO: NOK - Punkte verfallen lassen
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        playingField.move(jerkGreen, 6);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenJerkIntoGoalFields7True() { // NOK
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        playingField.move(jerkGreen, 7);
        Assert.assertTrue(jerkGreen.getCurrentField() instanceof GoalField);
    }

     */

    @Test
    public void checkMoveGreenJerkIntoGoalFields8False() { // OK
        jerkGreen = new Jerk(9, Color.GREEN, field12, Typ.JERK, figureUI9);
        playingField.move(jerkGreen, 8);
        Assert.assertFalse(jerkGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenCitizenIntoGoalFields3True() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        playingField.move(citizenGreen, 3);
        Assert.assertTrue(citizenGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenCitizenIntoGoalFields10False() { // OK
        citizenGreen = new Citizen(10, Color.GREEN, field12, Typ.CITIZEN, figureUI10);
        playingField.move(citizenGreen, 10);
        Assert.assertFalse(citizenGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenKnightIntoGoalFields4True() { // OK
        knightGreen = new Knight(11, Color.GREEN, field12, Typ.KNIGHT, figureUI11);
        playingField.move(knightGreen, 4);
        Assert.assertTrue(knightGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenKnightIntoGoalFields6False() { // OK
        knightGreen = new Knight(11, Color.GREEN, field12, Typ.KNIGHT, figureUI11);
        playingField.move(knightGreen, 6);
        Assert.assertFalse(knightGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenKingIntoGoalFields3True() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        playingField.move(kingGreen, 3);
        Assert.assertTrue(kingGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenKingIntoGoalFields6False() { // OK
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        playingField.move(kingGreen, 6);
        Assert.assertFalse(kingGreen.getCurrentField() instanceof GoalField);
    }

    @Test
    public void checkMoveGreenKingIntoGoalFieldsViaStartingFieldFalse() { // TODO: OK - soltle so sein?
        kingGreen = new King(12, Color.GREEN, field12, Typ.KING, figureUI12);
        playingField.move(kingGreen, 1);
        Assert.assertTrue(kingGreen.getCurrentField() instanceof StartingField);
        playingField.move(kingGreen, 1);
        Assert.assertEquals(field14,kingGreen.getCurrentField());
    }
}
