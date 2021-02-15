package com.tapstream.sdk;

public class ActivityEventSource {
    protected ActivityListener listener = null;

    public interface ActivityListener {
        void onOpen();
    }

    public void setListener(ActivityListener activityListener) {
        this.listener = activityListener;
    }
}
