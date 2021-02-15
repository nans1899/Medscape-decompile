package com.google.firebase.inappmessaging.internal;

import android.app.Application;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.Parser;
import io.reactivex.Completable;
import io.reactivex.Maybe;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class ProtoStorageClient {
    private final Application application;
    private final String fileName;

    public ProtoStorageClient(Application application2, String str) {
        this.application = application2;
        this.fileName = str;
    }

    public Completable write(AbstractMessageLite abstractMessageLite) {
        return Completable.fromCallable(ProtoStorageClient$$Lambda$1.lambdaFactory$(this, abstractMessageLite));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001c, code lost:
        if (r0 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        $closeResource(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object lambda$write$0(com.google.firebase.inappmessaging.internal.ProtoStorageClient r3, com.google.protobuf.AbstractMessageLite r4) throws java.lang.Exception {
        /*
            monitor-enter(r3)
            android.app.Application r0 = r3.application     // Catch:{ all -> 0x0022 }
            java.lang.String r1 = r3.fileName     // Catch:{ all -> 0x0022 }
            r2 = 0
            java.io.FileOutputStream r0 = r0.openFileOutput(r1, r2)     // Catch:{ all -> 0x0022 }
            r1 = 0
            byte[] r2 = r4.toByteArray()     // Catch:{ all -> 0x0019 }
            r0.write(r2)     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0017
            $closeResource(r1, r0)     // Catch:{ all -> 0x0022 }
        L_0x0017:
            monitor-exit(r3)     // Catch:{ all -> 0x0022 }
            return r4
        L_0x0019:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x001b }
        L_0x001b:
            r1 = move-exception
            if (r0 == 0) goto L_0x0021
            $closeResource(r4, r0)     // Catch:{ all -> 0x0022 }
        L_0x0021:
            throw r1     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0022 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.internal.ProtoStorageClient.lambda$write$0(com.google.firebase.inappmessaging.internal.ProtoStorageClient, com.google.protobuf.AbstractMessageLite):java.lang.Object");
    }

    public <T extends AbstractMessageLite> Maybe<T> read(Parser<T> parser) {
        return Maybe.fromCallable(ProtoStorageClient$$Lambda$2.lambdaFactory$(this, parser));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0019, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001a, code lost:
        if (r1 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        $closeResource(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x001f, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ com.google.protobuf.AbstractMessageLite lambda$read$1(com.google.firebase.inappmessaging.internal.ProtoStorageClient r3, com.google.protobuf.Parser r4) throws java.lang.Exception {
        /*
            monitor-enter(r3)
            r0 = 0
            android.app.Application r1 = r3.application     // Catch:{ InvalidProtocolBufferException -> 0x0024, FileNotFoundException -> 0x0022 }
            java.lang.String r2 = r3.fileName     // Catch:{ InvalidProtocolBufferException -> 0x0024, FileNotFoundException -> 0x0022 }
            java.io.FileInputStream r1 = r1.openFileInput(r2)     // Catch:{ InvalidProtocolBufferException -> 0x0024, FileNotFoundException -> 0x0022 }
            java.lang.Object r4 = r4.parseFrom((java.io.InputStream) r1)     // Catch:{ all -> 0x0017 }
            com.google.protobuf.AbstractMessageLite r4 = (com.google.protobuf.AbstractMessageLite) r4     // Catch:{ all -> 0x0017 }
            if (r1 == 0) goto L_0x0015
            $closeResource(r0, r1)     // Catch:{ InvalidProtocolBufferException -> 0x0024, FileNotFoundException -> 0x0022 }
        L_0x0015:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            return r4
        L_0x0017:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r2 = move-exception
            if (r1 == 0) goto L_0x001f
            $closeResource(r4, r1)     // Catch:{ InvalidProtocolBufferException -> 0x0024, FileNotFoundException -> 0x0022 }
        L_0x001f:
            throw r2     // Catch:{ InvalidProtocolBufferException -> 0x0024, FileNotFoundException -> 0x0022 }
        L_0x0020:
            r4 = move-exception
            goto L_0x003f
        L_0x0022:
            r4 = move-exception
            goto L_0x0025
        L_0x0024:
            r4 = move-exception
        L_0x0025:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0020 }
            r1.<init>()     // Catch:{ all -> 0x0020 }
            java.lang.String r2 = "Recoverable exception while reading cache: "
            r1.append(r2)     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0020 }
            r1.append(r4)     // Catch:{ all -> 0x0020 }
            java.lang.String r4 = r1.toString()     // Catch:{ all -> 0x0020 }
            com.google.firebase.inappmessaging.internal.Logging.logi(r4)     // Catch:{ all -> 0x0020 }
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            return r0
        L_0x003f:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.internal.ProtoStorageClient.lambda$read$1(com.google.firebase.inappmessaging.internal.ProtoStorageClient, com.google.protobuf.Parser):com.google.protobuf.AbstractMessageLite");
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable unused) {
            }
        } else {
            autoCloseable.close();
        }
    }
}
