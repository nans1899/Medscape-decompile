package com.appboy.events;

import com.appboy.models.outgoing.Feedback;
import com.appboy.models.response.ResponseError;

public final class SubmitFeedbackFailed {
    private final Feedback a;
    private final ResponseError b;

    public SubmitFeedbackFailed(Feedback feedback, ResponseError responseError) {
        this.a = feedback;
        this.b = responseError;
    }

    public Feedback getFeedback() {
        return this.a;
    }

    public ResponseError getError() {
        return this.b;
    }
}
