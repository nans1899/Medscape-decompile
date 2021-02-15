package org.mockito.internal.util;

import org.mockito.internal.exceptions.Reporter;

public class Timer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long durationMillis;
    private long startTime = -1;

    public Timer(long j) {
        validateInput(j);
        this.durationMillis = j;
    }

    public boolean isCounting() {
        return System.currentTimeMillis() - this.startTime <= this.durationMillis;
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    private void validateInput(long j) {
        if (j < 0) {
            throw Reporter.cannotCreateTimerWithNegativeDurationTime(j);
        }
    }

    public long duration() {
        return this.durationMillis;
    }
}
