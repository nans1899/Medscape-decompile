package coil.decode;

import coil.util.Extensions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Lcoil/decode/EmptyDecoder;", "Lcoil/decode/Decoder;", "()V", "result", "Lcoil/decode/DecodeResult;", "sink", "Lokio/Sink;", "decode", "pool", "Lcoil/bitmappool/BitmapPool;", "source", "Lokio/BufferedSource;", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "(Lcoil/bitmappool/BitmapPool;Lokio/BufferedSource;Lcoil/size/Size;Lcoil/decode/Options;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handles", "", "mimeType", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: EmptyDecoder.kt */
public final class EmptyDecoder implements Decoder {
    public static final EmptyDecoder INSTANCE = new EmptyDecoder();
    private static final DecodeResult result = new DecodeResult(Extensions.getEMPTY_DRAWABLE(), false);
    private static final Sink sink = Okio.blackhole();

    public boolean handles(BufferedSource bufferedSource, String str) {
        Intrinsics.checkParameterIsNotNull(bufferedSource, "source");
        return false;
    }

    private EmptyDecoder() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object decode(coil.bitmappool.BitmapPool r1, okio.BufferedSource r2, coil.size.Size r3, coil.decode.Options r4, kotlin.coroutines.Continuation<? super coil.decode.DecodeResult> r5) {
        /*
            r0 = this;
            java.io.Closeable r2 = (java.io.Closeable) r2
            r1 = 0
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r3 = r2
            okio.BufferedSource r3 = (okio.BufferedSource) r3     // Catch:{ all -> 0x0017 }
            okio.Sink r4 = sink     // Catch:{ all -> 0x0017 }
            long r3 = r3.readAll(r4)     // Catch:{ all -> 0x0017 }
            kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)     // Catch:{ all -> 0x0017 }
            kotlin.io.CloseableKt.closeFinally(r2, r1)
            coil.decode.DecodeResult r1 = result
            return r1
        L_0x0017:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0019 }
        L_0x0019:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r1)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.decode.EmptyDecoder.decode(coil.bitmappool.BitmapPool, okio.BufferedSource, coil.size.Size, coil.decode.Options, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
