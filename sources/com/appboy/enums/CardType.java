package com.appboy.enums;

public enum CardType {
    BANNER,
    CAPTIONED_IMAGE,
    DEFAULT,
    SHORT_NEWS,
    TEXT_ANNOUNCEMENT,
    CONTROL;

    public int getValue() {
        return ordinal();
    }

    public static CardType fromValue(int i) {
        return values()[i];
    }
}
