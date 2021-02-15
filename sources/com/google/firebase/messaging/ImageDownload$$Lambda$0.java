package com.google.firebase.messaging;

import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-messaging@@20.3.0 */
final /* synthetic */ class ImageDownload$$Lambda$0 implements Callable {
    private final ImageDownload arg$1;

    ImageDownload$$Lambda$0(ImageDownload imageDownload) {
        this.arg$1 = imageDownload;
    }

    public final Object call() {
        return this.arg$1.blockingDownload();
    }
}
