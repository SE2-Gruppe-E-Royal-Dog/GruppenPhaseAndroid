package com.uni.gruppenphaseandroid.playingfield;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class FieldUIimpl extends FieldUI{

    public ImageView getFieldImageView() {
        return fieldImageView;
    }

    private ImageView fieldImageView;
    private View view;

    public FieldUIimpl(ImageView fieldImageView){
        this.fieldImageView = fieldImageView;
    }

    public FieldUIimpl(View view){
        this.view = view;
    }

    public FieldUIimpl(){};



    @Override
    public void changeAppearance(int resourceID) {
        fieldImageView.setImageResource(resourceID);
    }

    @Override
    public void registerUIobject(String tag) {
        fieldImageView = view.findViewWithTag(tag);
    }


    @Override
    public void switchFieldUI(FieldUI fieldUI) {
        Drawable helpDrawable = ((FieldUIimpl)fieldUI).getImageResource();
        ((FieldUIimpl)fieldUI).getFieldImageView().setImageDrawable(getImageResource());
        fieldImageView.setImageDrawable(helpDrawable);
    }

    public Drawable getImageResource(){
        return fieldImageView.getDrawable();
    }
}
