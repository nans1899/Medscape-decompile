package com.google.firebase.inappmessaging.internal;

import com.google.protobuf.AbstractMessageLite;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
final /* synthetic */ class ProtoStorageClient$$Lambda$1 implements Callable {
    private final ProtoStorageClient arg$1;
    private final AbstractMessageLite arg$2;

    private ProtoStorageClient$$Lambda$1(ProtoStorageClient protoStorageClient, AbstractMessageLite abstractMessageLite) {
        this.arg$1 = protoStorageClient;
        this.arg$2 = abstractMessageLite;
    }

    public static Callable lambdaFactory$(ProtoStorageClient protoStorageClient, AbstractMessageLite abstractMessageLite) {
        return new ProtoStorageClient$$Lambda$1(protoStorageClient, abstractMessageLite);
    }

    public Object call() {
        return ProtoStorageClient.lambda$write$0(this.arg$1, this.arg$2);
    }
}
