package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultComment;

public interface ICommentReplySelectedListener {
    void onCommentReplySelected(ConsultComment consultComment, ConsultComment consultComment2, int i);
}
