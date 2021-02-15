package com.appboy.events;

import com.appboy.models.outgoing.Feedback;

public final class SubmitFeedbackSucceeded {
    private final Feedback a;

    public SubmitFeedbackSucceeded(Feedback feedback) {
        this.a = feedback;
    }

    public Feedback getFeedback() {
        return this.a;
    }
}
