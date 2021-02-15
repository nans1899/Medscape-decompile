package org.mockito.internal.stubbing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;

class DoAnswerStyleStubbing implements Serializable {
    private final List<Answer<?>> answers = new ArrayList();
    private Strictness stubbingStrictness;

    DoAnswerStyleStubbing() {
    }

    /* access modifiers changed from: package-private */
    public void setAnswers(List<Answer<?>> list, Strictness strictness) {
        this.stubbingStrictness = strictness;
        this.answers.addAll(list);
    }

    /* access modifiers changed from: package-private */
    public boolean isSet() {
        return this.answers.isEmpty();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.answers.clear();
        this.stubbingStrictness = null;
    }

    /* access modifiers changed from: package-private */
    public List<Answer<?>> getAnswers() {
        return this.answers;
    }

    /* access modifiers changed from: package-private */
    public Strictness getStubbingStrictness() {
        return this.stubbingStrictness;
    }
}
