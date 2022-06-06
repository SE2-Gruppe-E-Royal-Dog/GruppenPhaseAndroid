package com.uni.gruppenphaseandroid.manager;

import android.widget.ImageView;

public class VisualEffectsManagerImpl extends VisualEffectsManager{

    ImageView stackImage;
    //other attributes...

    public VisualEffectsManagerImpl(ImageView stackImage) {
        this.stackImage = stackImage;
    }

    @Override
    protected void setInitialStackImage() {

    }

    @Override
    protected void setStackImage() {

    }

    @Override
    protected void showIllegalMoveMessage() {

    }

    @Override
    protected void showWinningScreen() {

    }
}
