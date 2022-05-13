package com.uni.gruppenphaseandroid.playingfield;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.view.View;
import android.widget.RelativeLayout;

import com.uni.gruppenphaseandroid.R;

import java.util.ArrayList;

public class FigureManager {

    private ArrayList<Figure> figureList;

    public FigureManager() {
        this.figureList = new ArrayList<>();
    }

    public void createFigureSetOfColor(Color color, PlayingField playingField, RelativeLayout relativeLayout){

        createSingleFigure(color, playingField, relativeLayout, Typ.JERK);
        createSingleFigure(color, playingField, relativeLayout, Typ.CITIZEN);
        createSingleFigure(color, playingField, relativeLayout, Typ.KNIGHT);
        createSingleFigure(color, playingField, relativeLayout, Typ.KING);
    }

    public void createSingleFigure(Color color, PlayingField playingField, RelativeLayout relativeLayout, Typ typ){
        FigureUIimpl figureUIimpl = new FigureUIimpl();
        Figure figure = null;
        switch (typ){
            case JERK: figure = new Jerk(figureList.size()+1, color, playingField.getRightStartingAreaField(color), typ, figureUIimpl);
            break;
            case CITIZEN: figure = new Citizen(figureList.size()+1, color, playingField.getRightStartingAreaField(color), typ, figureUIimpl);
            break;
            case KNIGHT: figure = new Knight(figureList.size()+1, color, playingField.getRightStartingAreaField(color), typ, figureUIimpl);
            break;
            case KING: figure = new King(figureList.size()+1, color, playingField.getRightStartingAreaField(color), typ, figureUIimpl);
        }
        figureUIimpl.createFigureUI(relativeLayout.getRootView(),"fig" + figureList.size()+1, relativeLayout, createRightDrawable(color, typ));
        figureList.add(figure);
        figureUIimpl.moveFigureToPosition(figure.getCurrentField().getFieldUIobject());
    }

    private int createRightDrawable(Color color, Typ typ){

        if(color == Color.BLUE){
            switch (typ){
                case JERK: return R.drawable.ic_bluejester;
                case CITIZEN: return R.drawable.ic_bluecitizen;
                case KNIGHT: return R.drawable.ic_blueknight;
                case KING: return R.drawable.ic_blueking;
            }
        }else if(color==Color.GREEN){
            switch (typ){
                case JERK: return R.drawable.ic_greenjester;
                case CITIZEN: return R.drawable.ic_greencitizen;
                case KNIGHT: return R.drawable.ic_greenknight;
                case KING: return R.drawable.ic_greenking;
            }
        }else if(color == Color.RED){
            switch (typ){
                case JERK: return R.drawable.ic_redjester;
                case CITIZEN: return R.drawable.ic_redcitizen;
                case KNIGHT: return R.drawable.ic_redknight;
                case KING: return R.drawable.ic_redking;
            }
        }else {
            switch (typ){
                case JERK: return R.drawable.ic_yellowjester;
                case CITIZEN: return R.drawable.ic_yellowcitizen;
                case KNIGHT: return R.drawable.ic_yellowknight;
                case KING: return R.drawable.ic_yellowking;
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

}
