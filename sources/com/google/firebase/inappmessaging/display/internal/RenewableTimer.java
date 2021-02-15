package com.google.firebase.inappmessaging.display.internal;

import android.os.CountDownTimer;
import javax.inject.Inject;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class RenewableTimer {
    private CountDownTimer mCountDownTimer;

    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    public interface Callback {
        void onFinish();
    }

    @Inject
    RenewableTimer() {
    }

    public void start(Callback callback, long j, long j2) {
        final Callback callback2 = callback;
        this.mCountDownTimer = new CountDownTimer(j, j2) {
            public void onTick(long j) {
            }

            public void onFinish() {
                callback2.onFinish();
            }
        }.start();
    }

    public void cancel() {
        CountDownTimer countDownTimer = this.mCountDownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.mCountDownTimer = null;
        }
    }
}
