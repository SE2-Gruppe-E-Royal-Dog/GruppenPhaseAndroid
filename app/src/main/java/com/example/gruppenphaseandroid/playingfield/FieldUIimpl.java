package com.example.gruppenphaseandroid.playingfield;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.gruppenphaseandroid.R;

public class FieldUIimpl extends FieldUI{

    private ImageView fieldImageView;

    public FieldUIimpl(ImageView fieldImageView){
        this.fieldImageView = fieldImageView;
    }

    @Override
    public void changeAppearance(int resourceID) {
        fieldImageView.setImageResource(resourceID);
    }
}
