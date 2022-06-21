package com.uni.gruppenphaseandroid.playingfield;


import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.uni.gruppenphaseandroid.R;

public class FieldUIimpl implements FieldUI {

    public ImageView getFieldImageView() {
        return fieldImageView;
    }

    private ImageView fieldImageView;
    private View view;

    public FieldUIimpl(ImageView fieldImageView) {
        this.fieldImageView = fieldImageView;
    }

    public FieldUIimpl(View view) {
        this.view = view;
    }

    public FieldUIimpl() {
    }

    @Override
    public int getMarginTop() {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) fieldImageView.getLayoutParams();
        return layoutParams.topMargin;
    }

    public int getMarginLeft() {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) fieldImageView.getLayoutParams();
        return layoutParams.leftMargin;
    }

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
        Drawable helpDrawable = ((FieldUIimpl) fieldUI).getImageResource();
        ((FieldUIimpl) fieldUI).getFieldImageView().setImageDrawable(getImageResource());
        fieldImageView.setImageDrawable(helpDrawable);
    }

    @Override
    public void turnIntoWormhole() {
        changeAppearance(R.drawable.ic_wormhole);
    }

    @Override
    public void turnIntoRegularField() {
        changeAppearance(R.drawable.ic_regularfield);
    }

    public Drawable getImageResource() {
        return fieldImageView.getDrawable();
    }
}
