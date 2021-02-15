package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.util.MedscapeException;

public interface ICommunityUserReceivedListener {
    void onCurrentUserReceived(ConsultUser consultUser);

    void onFailedToReceiveCurrentUser(MedscapeException medscapeException);
}
