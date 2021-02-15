package com.appboy.ui.contentcards;

import com.appboy.ui.contentcards.listeners.AppboyContentCardsActionListener;
import com.appboy.ui.contentcards.listeners.IContentCardsActionListener;

public class AppboyContentCardsManager {
    private static volatile AppboyContentCardsManager sInstance;
    private IContentCardsActionListener mCustomContentCardsActionListener;
    private IContentCardsActionListener mDefaultContentCardsActionListener = new AppboyContentCardsActionListener();

    public static AppboyContentCardsManager getInstance() {
        if (sInstance == null) {
            synchronized (AppboyContentCardsManager.class) {
                if (sInstance == null) {
                    sInstance = new AppboyContentCardsManager();
                }
            }
        }
        return sInstance;
    }

    public void setContentCardsActionListener(IContentCardsActionListener iContentCardsActionListener) {
        this.mCustomContentCardsActionListener = iContentCardsActionListener;
    }

    public IContentCardsActionListener getContentCardsActionListener() {
        IContentCardsActionListener iContentCardsActionListener = this.mCustomContentCardsActionListener;
        return iContentCardsActionListener != null ? iContentCardsActionListener : this.mDefaultContentCardsActionListener;
    }
}
