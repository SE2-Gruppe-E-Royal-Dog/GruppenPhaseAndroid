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

        FigureUIimpl figureUIimpl = new FigureUIimpl();
        Jerk jerk = new Jerk(figureList.size()+1, color, playingField.getRightStartingAreaField(color), Typ.JERK, figureUIimpl);
        figureUIimpl.createFigureUI(relativeLayout.getRootView(),"fig" + figureList.size()+1, relativeLayout, createRightDrawable(color, Typ.JERK));
        figureList.add(jerk);
        figureUIimpl.moveFigureToPosition(jerk.getCurrentField().getFieldUIobject());

        figureUIimpl = new FigureUIimpl();
        Citizen citizen = new Citizen(figureList.size()+1, color, playingField.getRightStartingAreaField(color), Typ.CITIZEN, figureUIimpl);
        figureUIimpl.createFigureUI(relativeLayout.getRootView(),"fig" + figureList.size()+1, relativeLayout, createRightDrawable(color, Typ.CITIZEN));
        figureList.add(citizen);
        figureUIimpl.moveFigureToPosition(citizen.getCurrentField().getFieldUIobject());

        figureUIimpl = new FigureUIimpl();
        Knight knight = new Knight(figureList.size()+1, color, playingField.getRightStartingAreaField(color), Typ.KNIGHT, figureUIimpl);
        figureUIimpl.createFigureUI(relativeLayout.getRootView(),"fig" + figureList.size()+1, relativeLayout, createRightDrawable(color, Typ.KNIGHT));
        figureList.add(knight);
        figureUIimpl.moveFigureToPosition(knight.getCurrentField().getFieldUIobject());

        figureUIimpl = new FigureUIimpl();
        King king = new King(figureList.size()+1, color, playingField.getRightStartingAreaField(color), Typ.KING, figureUIimpl);
        figureUIimpl.createFigureUI(relativeLayout.getRootView(),"fig" + figureList.size()+1, relativeLayout, createRightDrawable(color, Typ.KING));
        figureList.add(king);
        figureUIimpl.moveFigureToPosition(king.getCurrentField().getFieldUIobject());
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
    public void moveFigureTest(PlayingField playingField){
        try {
            playingField.move(figureList.get(0),1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void moveFigureTest_2(PlayingField playingField){
        try{
            playingField.move(figureList.get(2), 3);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
