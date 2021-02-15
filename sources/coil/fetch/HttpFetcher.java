package coil.fetch;

import android.webkit.MimeTypeMap;
import coil.bitmappool.BitmapPool;
import coil.decode.Options;
import coil.fetch.Fetcher;
import coil.size.Size;
import coil.util.Extensions;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.ResponseBody;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000 \u0018*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0018B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J1\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u001a\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0011\u0010\u0016\u001a\u00020\u0013*\u00028\u0000H&¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0019"}, d2 = {"Lcoil/fetch/HttpFetcher;", "T", "", "Lcoil/fetch/Fetcher;", "callFactory", "Lokhttp3/Call$Factory;", "(Lokhttp3/Call$Factory;)V", "fetch", "Lcoil/fetch/FetchResult;", "pool", "Lcoil/bitmappool/BitmapPool;", "data", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "(Lcoil/bitmappool/BitmapPool;Ljava/lang/Object;Lcoil/size/Size;Lcoil/decode/Options;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMimeType", "", "Lokhttp3/HttpUrl;", "body", "Lokhttp3/ResponseBody;", "toHttpUrl", "(Ljava/lang/Object;)Lokhttp3/HttpUrl;", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: HttpFetcher.kt */
public abstract class HttpFetcher<T> implements Fetcher<T> {
    private static final CacheControl CACHE_CONTROL_FORCE_NETWORK_NO_CACHE = new CacheControl.Builder().noCache().noStore().build();
    private static final CacheControl CACHE_CONTROL_NO_NETWORK_NO_CACHE = new CacheControl.Builder().noCache().onlyIfCached().build();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String MIME_TYPE_TEXT_PLAIN = "text/plain";
    private final Call.Factory callFactory;

    public Object fetch(BitmapPool bitmapPool, T t, Size size, Options options, Continuation<? super FetchResult> continuation) {
        return fetch$suspendImpl(this, bitmapPool, t, size, options, continuation);
    }

    public abstract HttpUrl toHttpUrl(T t);

    public HttpFetcher(Call.Factory factory) {
        Intrinsics.checkParameterIsNotNull(factory, "callFactory");
        this.callFactory = factory;
    }

    public boolean handles(T t) {
        Intrinsics.checkParameterIsNotNull(t, "data");
        return Fetcher.DefaultImpls.handles(this, t);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcoil/fetch/HttpFetcher$Companion;", "", "()V", "CACHE_CONTROL_FORCE_NETWORK_NO_CACHE", "Lokhttp3/CacheControl;", "kotlin.jvm.PlatformType", "CACHE_CONTROL_NO_NETWORK_NO_CACHE", "MIME_TYPE_TEXT_PLAIN", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: HttpFetcher.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x013c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object fetch$suspendImpl(coil.fetch.HttpFetcher r9, coil.bitmappool.BitmapPool r10, java.lang.Object r11, coil.size.Size r12, coil.decode.Options r13, kotlin.coroutines.Continuation r14) {
        /*
            boolean r0 = r14 instanceof coil.fetch.HttpFetcher$fetch$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            coil.fetch.HttpFetcher$fetch$1 r0 = (coil.fetch.HttpFetcher$fetch$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            coil.fetch.HttpFetcher$fetch$1 r0 = new coil.fetch.HttpFetcher$fetch$1
            r0.<init>(r9, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0059
            if (r2 != r3) goto L_0x0051
            java.lang.Object r9 = r0.L$7
            okhttp3.Call r9 = (okhttp3.Call) r9
            boolean r9 = r0.Z$1
            boolean r9 = r0.Z$0
            java.lang.Object r9 = r0.L$6
            okhttp3.Request$Builder r9 = (okhttp3.Request.Builder) r9
            java.lang.Object r9 = r0.L$5
            okhttp3.HttpUrl r9 = (okhttp3.HttpUrl) r9
            java.lang.Object r10 = r0.L$4
            coil.decode.Options r10 = (coil.decode.Options) r10
            java.lang.Object r10 = r0.L$3
            coil.size.Size r10 = (coil.size.Size) r10
            java.lang.Object r10 = r0.L$2
            java.lang.Object r10 = r0.L$1
            coil.bitmappool.BitmapPool r10 = (coil.bitmappool.BitmapPool) r10
            java.lang.Object r10 = r0.L$0
            coil.fetch.HttpFetcher r10 = (coil.fetch.HttpFetcher) r10
            kotlin.ResultKt.throwOnFailure(r14)
            r8 = r14
            r14 = r9
            r9 = r10
            r10 = r8
            goto L_0x0102
        L_0x0051:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r14)
            okhttp3.HttpUrl r14 = r9.toHttpUrl(r11)
            okhttp3.Request$Builder r2 = new okhttp3.Request$Builder
            r2.<init>()
            okhttp3.Request$Builder r2 = r2.url((okhttp3.HttpUrl) r14)
            okhttp3.Headers r4 = r13.getHeaders()
            okhttp3.Request$Builder r2 = r2.headers(r4)
            coil.request.CachePolicy r4 = r13.getNetworkCachePolicy()
            boolean r4 = r4.getReadEnabled()
            coil.request.CachePolicy r5 = r13.getDiskCachePolicy()
            boolean r5 = r5.getReadEnabled()
            if (r4 != 0) goto L_0x008b
            if (r5 == 0) goto L_0x008b
            okhttp3.CacheControl r6 = okhttp3.CacheControl.FORCE_CACHE
            r2.cacheControl(r6)
            goto L_0x00ae
        L_0x008b:
            if (r4 == 0) goto L_0x00a5
            if (r5 != 0) goto L_0x00a5
            coil.request.CachePolicy r6 = r13.getDiskCachePolicy()
            boolean r6 = r6.getWriteEnabled()
            if (r6 == 0) goto L_0x009f
            okhttp3.CacheControl r6 = okhttp3.CacheControl.FORCE_NETWORK
            r2.cacheControl(r6)
            goto L_0x00ae
        L_0x009f:
            okhttp3.CacheControl r6 = CACHE_CONTROL_FORCE_NETWORK_NO_CACHE
            r2.cacheControl(r6)
            goto L_0x00ae
        L_0x00a5:
            if (r4 != 0) goto L_0x00ae
            if (r5 != 0) goto L_0x00ae
            okhttp3.CacheControl r6 = CACHE_CONTROL_NO_NETWORK_NO_CACHE
            r2.cacheControl(r6)
        L_0x00ae:
            okhttp3.Call$Factory r6 = r9.callFactory
            okhttp3.Request r7 = r2.build()
            okhttp3.Call r6 = r6.newCall(r7)
            java.lang.String r7 = "callFactory.newCall(request.build())"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r6, r7)
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r11
            r0.L$3 = r12
            r0.L$4 = r13
            r0.L$5 = r14
            r0.L$6 = r2
            r0.Z$0 = r4
            r0.Z$1 = r5
            r0.L$7 = r6
            r0.label = r3
            kotlinx.coroutines.CancellableContinuationImpl r10 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r0)
            r10.<init>(r11, r3)
            r10.initCancellability()
            r11 = r10
            kotlinx.coroutines.CancellableContinuation r11 = (kotlinx.coroutines.CancellableContinuation) r11
            coil.util.ContinuationCallback r12 = new coil.util.ContinuationCallback
            r12.<init>(r6, r11)
            r13 = r12
            okhttp3.Callback r13 = (okhttp3.Callback) r13
            r6.enqueue(r13)
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            r11.invokeOnCancellation(r12)
            java.lang.Object r10 = r10.getResult()
            java.lang.Object r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r10 != r11) goto L_0x00ff
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L_0x00ff:
            if (r10 != r1) goto L_0x0102
            return r1
        L_0x0102:
            okhttp3.Response r10 = (okhttp3.Response) r10
            boolean r11 = r10.isSuccessful()
            if (r11 == 0) goto L_0x013c
            okhttp3.ResponseBody r11 = r10.body()
            if (r11 == 0) goto L_0x012e
            coil.fetch.SourceResult r12 = new coil.fetch.SourceResult
            okio.BufferedSource r13 = r11.source()
            java.lang.String r0 = "body.source()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r13, r0)
            java.lang.String r9 = r9.getMimeType(r14, r11)
            okhttp3.Response r10 = r10.cacheResponse()
            if (r10 == 0) goto L_0x0128
            coil.decode.DataSource r10 = coil.decode.DataSource.DISK
            goto L_0x012a
        L_0x0128:
            coil.decode.DataSource r10 = coil.decode.DataSource.NETWORK
        L_0x012a:
            r12.<init>(r13, r9, r10)
            return r12
        L_0x012e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Null response body!"
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            throw r9
        L_0x013c:
            coil.network.HttpException r9 = new coil.network.HttpException
            r9.<init>(r10)
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.fetch.HttpFetcher.fetch$suspendImpl(coil.fetch.HttpFetcher, coil.bitmappool.BitmapPool, java.lang.Object, coil.size.Size, coil.decode.Options, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String getMimeType(HttpUrl httpUrl, ResponseBody responseBody) {
        MediaType contentType = responseBody.contentType();
        String mediaType = contentType != null ? contentType.toString() : null;
        if (mediaType != null && !StringsKt.startsWith$default(mediaType, MIME_TYPE_TEXT_PLAIN, false, 2, (Object) null)) {
            return mediaType;
        }
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        Intrinsics.checkExpressionValueIsNotNull(singleton, "MimeTypeMap.getSingleton()");
        String mimeTypeFromUrl = Extensions.getMimeTypeFromUrl(singleton, httpUrl.toString());
        return mimeTypeFromUrl != null ? mimeTypeFromUrl : mediaType;
    }
}
