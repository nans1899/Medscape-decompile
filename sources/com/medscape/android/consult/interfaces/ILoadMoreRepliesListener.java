package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultComment;

public interface ILoadMoreRepliesListener {
    void onLoadMoreRepliesForComment(ConsultComment consultComment, int i);
}
