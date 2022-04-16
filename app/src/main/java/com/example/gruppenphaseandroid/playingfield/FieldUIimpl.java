package com.example.gruppenphaseandroid.playingfield;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.example.gruppenphaseandroid.R;

public class FieldUIimpl extends FieldUI{

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
}
