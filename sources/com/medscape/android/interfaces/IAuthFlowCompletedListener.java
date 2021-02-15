package com.medscape.android.interfaces;

import com.medscape.android.util.MedscapeException;

public interface IAuthFlowCompletedListener {
    void onFailure(MedscapeException medscapeException);

    void onSuccess();
}
