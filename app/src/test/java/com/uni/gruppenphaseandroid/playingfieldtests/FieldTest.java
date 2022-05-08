package com.uni.gruppenphaseandroid.playingfieldtests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.uni.gruppenphaseandroid.playingfield.Color;
import com.uni.gruppenphaseandroid.playingfield.Field;
import com.uni.gruppenphaseandroid.playingfield.FieldUI;
import com.uni.gruppenphaseandroid.playingfield.FieldUIimpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class FieldTest {

    Field field1;
    Field field2;
    Field field3;
    Field field4;

    Field field5;
    Field field6;
    Field field7;

    FieldUIimpl field2UI;
    FieldUIimpl field6UI;



    @Before
    public void setUp(){

        field2UI = mock(FieldUIimpl.class);
        field6UI = mock(FieldUIimpl.class);


        field1 = new Field();
        field2 = new Field();
        field3 = new Field();
        field4 = new Field();
        field5 = new Field();
        field6 = new Field();
        field7 = new Field();


        field1.setNextField(field2);
        field2.setPreviousField(field1);
        field2.setNextField(field3);
        field3.setPreviousField(field2);
        field3.setNextField(field4);
        field4.setPreviousField(field3);
        field4.setNextField(field5);
        field5.setPreviousField(field4);
        field5.setNextField(field6);
        field6.setPreviousField(field5);
        field6.setNextField(field7);
        field7.setPreviousField(field6);


        field2.setFieldUIobject(field2UI);
        field6.setFieldUIobject(field6UI);
    }

    @After
   public void tearDown(){
        field1 = null;
        field2 = null;
        field3 = null;
        field4 = null;
        field5 = null;
        field6 = null;
        field7 = null;
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

    @Test
    public void switchField(){
        field2.setFieldID(10);

        field6.setFieldID(17);

        Field field3 = field2.getNextField();
        Field field5 = field6.getPreviousField();

        field2.switchField(field6);

        Assert.assertEquals(field2, field5.getNextField());
        Assert.assertEquals(field6, field3.getPreviousField());


        Assert.assertEquals(17, field2.getFieldID());
        Assert.assertEquals(10, field6.getFieldID());

        verify(field2UI,times(1)).switchFieldUI(field6UI);

        int fieldstraversed = 0;
        Field currentField = field1;
        while(currentField != null ){
            fieldstraversed++;
            currentField = currentField.getNextField();
        }
        Assert.assertEquals(7, fieldstraversed);

        fieldstraversed = 0;
        currentField = field7;
        while(currentField != null ){
            fieldstraversed++;
            currentField = currentField.getPreviousField();
        }
        Assert.assertEquals(7, fieldstraversed);
    }


}
