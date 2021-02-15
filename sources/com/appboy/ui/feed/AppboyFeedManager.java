package com.appboy.ui.feed;

import com.appboy.ui.feed.listeners.AppboyDefaultFeedClickActionListener;
import com.appboy.ui.feed.listeners.IFeedClickActionListener;

public class AppboyFeedManager {
    private static volatile AppboyFeedManager sInstance;
    private IFeedClickActionListener mCustomFeedClickActionListener;
    private IFeedClickActionListener mDefaultFeedClickActionListener = new AppboyDefaultFeedClickActionListener();

    public static AppboyFeedManager getInstance() {
        if (sInstance == null) {
            synchronized (AppboyFeedManager.class) {
                if (sInstance == null) {
                    sInstance = new AppboyFeedManager();
                }
            }
        }
        return sInstance;
    }

    public void setFeedCardClickActionListener(IFeedClickActionListener iFeedClickActionListener) {
        this.mCustomFeedClickActionListener = iFeedClickActionListener;
    }

    public IFeedClickActionListener getFeedCardClickActionListener() {
        IFeedClickActionListener iFeedClickActionListener = this.mCustomFeedClickActionListener;
        return iFeedClickActionListener != null ? iFeedClickActionListener : this.mDefaultFeedClickActionListener;
    }
}
