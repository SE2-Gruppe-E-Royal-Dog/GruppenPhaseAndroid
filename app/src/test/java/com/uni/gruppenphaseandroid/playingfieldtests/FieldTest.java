package com.uni.gruppenphaseandroid.playingfieldtests;

import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FieldTest {

    Field field1;
    Field field2;
    Field field3;
    Field field4;

    @Before
    public void setUp(){
        field1 = new Field();
        field2 = new Field();
        field3 = new Field();
        field4 = new Field();

        field1.setNextField(field2);
        field2.setPreviousField(field1);
        field2.setNextField(field3);
        field3.setPreviousField(field2);
        field3.setNextField(field4);
        field4.setPreviousField(field3);
    }

    @After
   public void tearDown(){
        field1 = null;
        field2 = null;
        field3 = null;
        field4 = null;
    }


    @Test
    public void getFieldAtDistanceTestPositive(){
        Field destinationField = field1.getFieldAtDistance(3, Color.GREEN);
        Assert.assertEquals(field4, destinationField);
    }

    @Test
    public void getFieldAtDistanceTestNegative(){
        Field destinationField = field4.getFieldAtDistance(-2, Color.BLUE);
        Assert.assertEquals(field2, destinationField);
    }

    @Test
    public void getFieldAtDistanceWith0(){
        Field destinationField = field1.getFieldAtDistance(0, Color.RED);
        Assert.assertEquals(field1, destinationField);
    }


}
