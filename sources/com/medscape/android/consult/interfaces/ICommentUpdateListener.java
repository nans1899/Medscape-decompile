package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.util.MedscapeException;

public interface ICommentUpdateListener {
    void onCommentUpdateFailed(MedscapeException medscapeException);

    void onCommentUpdated(ConsultComment consultComment);
}
