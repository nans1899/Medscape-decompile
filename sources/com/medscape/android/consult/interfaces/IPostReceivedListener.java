package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.util.MedscapeException;

public interface IPostReceivedListener {
    void onPostReceived(ConsultPost consultPost);

    void onPostRequestFailed(MedscapeException medscapeException);
}
