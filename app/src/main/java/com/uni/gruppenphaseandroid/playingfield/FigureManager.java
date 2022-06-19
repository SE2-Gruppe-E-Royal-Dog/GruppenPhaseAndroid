package com.uni.gruppenphaseandroid.playingfield;

import android.widget.RelativeLayout;

import com.uni.gruppenphaseandroid.R;

import java.util.ArrayList;
import java.util.Random;

public class FigureManager {

    private ArrayList<Figure> figureList;

    public FigureManager() {
        this.figureList = new ArrayList<>();
    }

    public void createFigureSetOfColor(Color color, PlayingField playingField) {

        createFigureObjects(color, playingField);

        for(Figure figure : figureList){
            if(figure.getColor()==color){
                setUpSingleFigureUI(figure, playingField.getView().findViewById(R.id.playingFieldRelativeLayout));
            }
        }
    }

    public void createFigureObjects(Color color, PlayingField playingField){
        figureList.add(createSingleFigure(color, playingField, Typ.JERK));
        figureList.add(createSingleFigure(color, playingField, Typ.CITIZEN));
        figureList.add(createSingleFigure(color, playingField, Typ.KNIGHT));
        figureList.add(createSingleFigure(color, playingField, Typ.KING));
    }

    private Figure createSingleFigure(Color color, PlayingField playingField, Typ typ){

        Figure figure = null;
        switch (typ){
            case JERK: figure = new Jerk(figureList.size()+1, color, playingField.getRightStartingAreaField(color), typ, null);
            break;
            case CITIZEN: figure = new Citizen(figureList.size()+1, color, playingField.getRightStartingAreaField(color), typ, null);
            break;
            case KNIGHT: figure = new Knight(figureList.size()+1, color, playingField.getRightStartingAreaField(color), typ, null);
            break;
            case KING: figure = new King(figureList.size()+1, color, playingField.getRightStartingAreaField(color), typ, null);
        }
        return figure;
    }

    private void setUpSingleFigureUI(Figure figure, RelativeLayout relativeLayout){
        FigureUIimpl figureUIimpl = new FigureUIimpl();
        figureUIimpl.createFigureUI(relativeLayout.getRootView(),"fig" + figureList.size()+1, relativeLayout, createRightDrawable(figure.getColor(), figure.getTyp()));
        figureUIimpl.moveFigureToPosition(figure.getCurrentField().getFieldUIobject());
        figureUIimpl.setButtonClickBehaviour(figure);
        figure.setFigureUI(figureUIimpl);
    }

    public int createRightDrawable(Color color, Typ typ) {

        if(color == Color.BLUE){
            switch (typ){
                case JERK: return R.drawable.ic_bluejestercat;
                case CITIZEN: return R.drawable.ic_bluecitizencat;
                case KNIGHT: return R.drawable.ic_blueknightcat;
                case KING: return R.drawable.ic_bluekingcat;
            }
        }else if(color==Color.GREEN){
            switch (typ){
                case JERK: return R.drawable.ic_greenjestercat;
                case CITIZEN: return R.drawable.ic_greencitizencat;
                case KNIGHT: return R.drawable.ic_greenknightcat;
                case KING: return R.drawable.ic_greenkingcat;
            }
        }else if(color == Color.RED){
            switch (typ){
                case JERK: return R.drawable.ic_redjestercat;
                case CITIZEN: return R.drawable.ic_redcitizencat;
                case KNIGHT: return R.drawable.ic_redknightcat;
                case KING: return R.drawable.ic_redkingcat;
            }
        }else if(color == Color.YELLOW){
            switch (typ){
                case JERK: return R.drawable.ic_yellowjestercat;
                case CITIZEN: return R.drawable.ic_yellowcitizencat;
                case KNIGHT: return R.drawable.ic_yellowknightcat;
                case KING: return R.drawable.ic_yellowkingcat;
            }
        }
        return 0;
    }

    public Figure getFigureWithID(int id) throws IllegalArgumentException{
        for (Figure figure: figureList) {
            if(figure.getId() == id){
                return figure;
            }
        }
        throw new IllegalArgumentException();
    }

    public ArrayList<Figure> getFiguresOfColour(Color color){
        ArrayList<Figure> figureSet = new ArrayList<>();
        for (Figure figure: figureList){
            if(figure.getColor() == color){
                figureSet.add(figure);
            }
        }
        return figureSet;
    }


    public boolean isWinner(Color color) {
        boolean winner = true;
        for (Figure figure: figureList) {
            if (figure.getColor() == color && !(figure.getCurrentField() instanceof GoalField)) {
                winner = false;
            }
        } return winner;
    }

    public boolean checkIfPlayerHasFigureOnBoard(Color color){
        boolean hasFigureOnBoard = false;
        for (Figure figure:figureList){
            if(figure.getColor() == color){
                if (!(figure.getCurrentField() instanceof StartingAreaField)){
                    hasFigureOnBoard = true;
                }
            }

        }
        return hasFigureOnBoard;

    }

    public int getRandomFigureIdOfPlayerOnBoard(Color color){
        Random random = new Random();
        int max = 3;
        int min = 0;
        ArrayList <Figure> figuresOfColour = getFiguresOfColour(color);
        while (true){
            int randomIndex = random.nextInt(max - min) + min;
            Figure figure = figuresOfColour.get(randomIndex);
            if (!(figure.getCurrentField() instanceof StartingAreaField)){
                return figure.getId();
            }
        }
    }

    public int getNumberOfTotalFigures(){
        return figureList.size();
    }

}
