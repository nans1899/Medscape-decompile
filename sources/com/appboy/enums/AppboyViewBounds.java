package com.appboy.enums;

public enum AppboyViewBounds {
    NOTIFICATION_EXPANDED_IMAGE(478, 256),
    NOTIFICATION_LARGE_ICON(64, 64),
    NOTIFICATION_ONE_IMAGE_STORY(256, 128),
    BASE_CARD_VIEW(512, 512),
    IN_APP_MESSAGE_MODAL(580, 580),
    IN_APP_MESSAGE_SLIDEUP(100, 100),
    NO_BOUNDS(0, 0);
    
    final int a;
    final int b;

    private AppboyViewBounds(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int getHeightDp() {
        return this.b;
    }

    public int getWidthDp() {
        return this.a;
    }
}
