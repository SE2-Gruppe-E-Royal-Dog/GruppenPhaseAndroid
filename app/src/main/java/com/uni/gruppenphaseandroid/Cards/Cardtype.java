package com.uni.gruppenphaseandroid.Cards;

public enum Cardtype {
    TWO(2),
    THREE(3),
    FOUR_PLUSMINUS(-1),
    FIVE(5),
    SIX(6),
    ONETOSEVEN(-1),
    EIGTH(8),
    NINE(9),
    TEN(10),
    ONEORELEVEN_START(-1),
    TWELVE(12),
    THIRTEEN_START(13),
    EQUAL(-1),
    MAGNET(-1),
    SWITCH(-1);

    private int value;

    Cardtype(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
