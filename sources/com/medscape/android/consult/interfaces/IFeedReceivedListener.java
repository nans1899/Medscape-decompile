package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.util.MedscapeException;

public interface IFeedReceivedListener {
    void onFailedToReceiveFeed(MedscapeException medscapeException, int i, String str);

    void onFeedReceived(ConsultFeed consultFeed, int i, String str);
}
