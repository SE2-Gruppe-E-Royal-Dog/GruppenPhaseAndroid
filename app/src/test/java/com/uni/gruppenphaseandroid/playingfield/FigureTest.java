package com.uni.gruppenphaseandroid.playingfield;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FigureTest {
    /*
    @Test
    public void givenFigure_whenCheckingIfAnotherFigureCanOverTake_expectTrue() {
     var figure = new Figure();

     assertTrue(figure.checkOvertaking(new Figure()));
    }
    */

    @Test
    public void givenFigure_whenCheckingIfBeaten_expectTrue() {
        var field1 = new Field();
        var field2 = new Field();
        field2.setNextField(field1);

        var figure1 = new Figure(0, Color.BLACK, field1, Typ.KING, new FigureUIimpl());
        var figure2 = new Figure(1, Color.YELLOW, field2, Typ.CITIZEN, new FigureUIimpl());
        field2.setCurrentFigure(figure2);

        assertTrue(figure1.checkBeaten(figure2));
    }

    /*
    @Test
    public void givenFigure_whenCheckingIfMoving_expectTrue() {
        var field1 = new Field();
        var field2 = new Field();
        field2.setNextField(field1);

        var figure1 = new Figure(0, Color.BLACK, field1, Typ.KING, new FigureUIimpl());
        var figure2 = new Figure(1, Color.YELLOW, field2, Typ.CITIZEN, new FigureUIimpl());
        field2.setCurrentFigure(figure2);

        assertTrue(figure1.checkMoving(figure2, 2));
    }
    */

    @Test
    public void givenFigure_whenCheckingIfBeatenOnStartField_expectFalse() {
        var field1 = new Field();

        var field2 = new StartingField();
        field2.setColor(Color.BLACK);

        var field3 = new Field();

        field1.setNextField(field2);
        field2.setNextField(field3);

        var figure1 = new Figure(0, Color.BLACK, field2, Typ.KING, new FigureUIimpl());
        var figure2 = new Figure(1, Color.YELLOW, field1, Typ.CITIZEN, new FigureUIimpl());
        field2.setCurrentFigure(figure1);

        assertTrue(figure2.checkBeaten(figure1));
    }
}