package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.util.MedscapeException;

public interface IPostUploadedListener {
    void onPostFailed(MedscapeException medscapeException);

    void onPostSentToServer(ConsultPost consultPost);
}
