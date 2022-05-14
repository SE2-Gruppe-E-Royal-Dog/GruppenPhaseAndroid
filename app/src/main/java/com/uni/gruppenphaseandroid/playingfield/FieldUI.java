package com.uni.gruppenphaseandroid.playingfield;

public abstract class FieldUI {

    public abstract void changeAppearance(int resourceID);

    public abstract void registerUIobject(String tag);

    public abstract int getMarginTop();

    public abstract int getMarginLeft();

    public abstract void switchFieldUI(FieldUI fieldUI);
    public abstract void turnIntoWormhole();
    public abstract void turnIntoRegularField();
}
