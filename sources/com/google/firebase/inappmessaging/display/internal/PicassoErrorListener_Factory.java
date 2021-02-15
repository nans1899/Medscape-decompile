package com.google.firebase.inappmessaging.display.internal;

import dagger.internal.Factory;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public final class PicassoErrorListener_Factory implements Factory<PicassoErrorListener> {
    private static final PicassoErrorListener_Factory INSTANCE = new PicassoErrorListener_Factory();

    public PicassoErrorListener get() {
        return new PicassoErrorListener();
    }

    public static PicassoErrorListener_Factory create() {
        return INSTANCE;
    }

    public static PicassoErrorListener newInstance() {
        return new PicassoErrorListener();
    }
}
