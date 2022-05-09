package com.uni.gruppenphaseandroid.playingfieldtests;

import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.GoalField;
import com.uni.gruppenphaseandroid.playingfield.StartingAreaField;
import com.uni.gruppenphaseandroid.playingfield.StartingField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StartingFieldTest {

    Field field1;
    StartingField field2;
    Field field3;
    Field field4;

    GoalField goalField;
    StartingAreaField startingAreaField;

    @Before
    public void setUp() {
        field1 = new Field();
        field2 = new StartingField();
        field3 = new Field();
        field4 = new Field();
        goalField = new GoalField();
        startingAreaField = new StartingAreaField();

        field1.setNextField(field2);
        field2.setPreviousField(field1);
        field2.setNextField(field3);
        field3.setPreviousField(field2);
        field3.setNextField(field4);
        field4.setPreviousField(field3);

        field2.setNextGoalField(goalField);
        field2.setPreviousStartingArea(startingAreaField);
        field2.setColor(Color.GREEN);
    }

    @After
    public void tearDown() {
        field1 = null;
        field2 = null;
        field3 = null;
        field4 = null;

        startingAreaField = null;
        goalField = null;
    }

    @Test
    public void testGoIntoGoalArea() {
        Field destinationField = field1.getFieldAtDistance(2, Color.GREEN);
        Assert.assertEquals(goalField, destinationField);
    }

    @Test
    public void testNonMatchingColor() {
        Field destinationField = field1.getFieldAtDistance(2, Color.BLUE);
        Assert.assertEquals(field3, destinationField);
    }

    @Test
    public void testGoTooFarForGoalField() {
        Field destinationField = field1.getFieldAtDistance(3, Color.GREEN);
        Assert.assertEquals(field4, destinationField);
    }

    @Test
    public void testGoBackwardsPastStart() {
        Field destinationField = field3.getFieldAtDistance(-2, Color.GREEN);
        Assert.assertEquals(field1, destinationField);
    }

    @Test
    public void testBeginAtStartingField() {
        Field destinationField = field2.getFieldAtDistance(1, Color.GREEN);
        Assert.assertEquals(field3, destinationField);
    }

    @Test
    public void test0Distance() {
        Field destinationField = field2.getFieldAtDistance(0, Color.GREEN);
        Assert.assertEquals(field2, destinationField);
    }
}
