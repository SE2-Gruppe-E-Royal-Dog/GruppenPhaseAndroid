package com.uni.gruppenphaseandroid.playingfield;

public interface FieldUI {
    void changeAppearance(int resourceID);
    void registerUIobject(String tag);
    int getMarginTop();
    int getMarginLeft();
    void switchFieldUI(FieldUI fieldUI);
    void turnIntoWormhole();
    void turnIntoRegularField();
}
