package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzqe implements Handler.Callback, Choreographer.FrameCallback {
    private static final zzqe zzbmt = new zzqe();
    private final Handler handler;
    public volatile long zzbms;
    private final HandlerThread zzbmu;
    private Choreographer zzbmv;
    private int zzbmw;

    public static zzqe zzjp() {
        return zzbmt;
    }

    private zzqe() {
        HandlerThread handlerThread = new HandlerThread("ChoreographerOwner:Handler");
        this.zzbmu = handlerThread;
        handlerThread.start();
        Handler handler2 = new Handler(this.zzbmu.getLooper(), this);
        this.handler = handler2;
        handler2.sendEmptyMessage(0);
    }

    public final void zzjq() {
        this.handler.sendEmptyMessage(1);
    }

    public final void zzjr() {
        this.handler.sendEmptyMessage(2);
    }

    public final void doFrame(long j) {
        this.zzbms = j;
        this.zzbmv.postFrameCallbackDelayed(this, 500);
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            this.zzbmv = Choreographer.getInstance();
            return true;
        } else if (i == 1) {
            int i2 = this.zzbmw + 1;
            this.zzbmw = i2;
            if (i2 == 1) {
                this.zzbmv.postFrameCallback(this);
            }
            return true;
        } else if (i != 2) {
            return false;
        } else {
            int i3 = this.zzbmw - 1;
            this.zzbmw = i3;
            if (i3 == 0) {
                this.zzbmv.removeFrameCallback(this);
                this.zzbms = 0;
            }
            return true;
        }
    }
}
