package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.InitialConsultData;
import com.medscape.android.util.MedscapeException;

public interface IInitialConsultInfoReceivedListener {
    void onFailedToReceiveInitialConsultInfo(MedscapeException medscapeException);

    void onInitialConsultInfoReceived(InitialConsultData initialConsultData);
}
