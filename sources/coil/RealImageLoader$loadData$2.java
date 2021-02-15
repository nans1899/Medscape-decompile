package coil;

import coil.fetch.DrawableResult;
import coil.fetch.Fetcher;
import coil.request.Request;
import coil.size.Scale;
import coil.size.Size;
import coil.size.SizeResolver;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "Lcoil/fetch/DrawableResult;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "coil.RealImageLoader$loadData$2", f = "RealImageLoader.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {327, 349, 528}, m = "invokeSuspend", n = {"$this$withContext", "options", "$this$withContext", "options", "fetchResult", "decoder", "$this$withContext", "options", "fetchResult", "baseResult", "this_$iv", "request$iv", "size$iv", "eventListener$iv", "$this$run$iv", "transformations$iv", "$this$foldIndices$iv$iv", "accumulator$iv$iv", "i$iv$iv", "baseBitmap$iv", "transformation$iv", "bitmap$iv"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$9", "L$10", "L$11", "L$12", "I$0", "L$13", "L$14", "L$15"})
/* compiled from: RealImageLoader.kt */
public final class RealImageLoader$loadData$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DrawableResult>, Object> {
    final /* synthetic */ EventListener $eventListener;
    final /* synthetic */ Fetcher $fetcher;
    final /* synthetic */ Object $mappedData;
    final /* synthetic */ Request $request;
    final /* synthetic */ Scale $scale;
    final /* synthetic */ Size $size;
    final /* synthetic */ SizeResolver $sizeResolver;
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$11;
    Object L$12;
    Object L$13;
    Object L$14;
    Object L$15;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ RealImageLoader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RealImageLoader$loadData$2(RealImageLoader realImageLoader, Request request, SizeResolver sizeResolver, Size size, Scale scale, EventListener eventListener, Fetcher fetcher, Object obj, Continuation continuation) {
        super(2, continuation);
        this.this$0 = realImageLoader;
        this.$request = request;
        this.$sizeResolver = sizeResolver;
        this.$size = size;
        this.$scale = scale;
        this.$eventListener = eventListener;
        this.$fetcher = fetcher;
        this.$mappedData = obj;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkParameterIsNotNull(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        RealImageLoader$loadData$2 realImageLoader$loadData$2 = new RealImageLoader$loadData$2(this.this$0, this.$request, this.$sizeResolver, this.$size, this.$scale, this.$eventListener, this.$fetcher, this.$mappedData, continuation);
        realImageLoader$loadData$2.p$ = (CoroutineScope) obj;
        return realImageLoader$loadData$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((RealImageLoader$loadData$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0132 A[Catch:{ Exception -> 0x01b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0138 A[Catch:{ Exception -> 0x01b4 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x018f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x02cc  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0378  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x037a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r26) {
        /*
            r25 = this;
            r7 = r25
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r9 = 3
            r10 = 2
            r12 = 1
            if (r1 == 0) goto L_0x00a4
            if (r1 == r12) goto L_0x0094
            if (r1 == r10) goto L_0x0079
            if (r1 != r9) goto L_0x0071
            java.lang.Object r1 = r7.L$15
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            java.lang.Object r1 = r7.L$14
            coil.transform.Transformation r1 = (coil.transform.Transformation) r1
            java.lang.Object r1 = r7.L$13
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            int r2 = r7.I$1
            int r3 = r7.I$0
            java.lang.Object r4 = r7.L$12
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            java.lang.Object r4 = r7.L$11
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r5 = r7.L$10
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r6 = r7.L$9
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            java.lang.Object r8 = r7.L$8
            coil.RealImageLoader$loadData$2 r8 = (coil.RealImageLoader$loadData$2) r8
            java.lang.Object r10 = r7.L$7
            coil.EventListener r10 = (coil.EventListener) r10
            java.lang.Object r13 = r7.L$6
            coil.size.Size r13 = (coil.size.Size) r13
            java.lang.Object r14 = r7.L$5
            coil.request.Request r14 = (coil.request.Request) r14
            java.lang.Object r15 = r7.L$4
            coil.RealImageLoader r15 = (coil.RealImageLoader) r15
            java.lang.Object r9 = r7.L$3
            coil.fetch.DrawableResult r9 = (coil.fetch.DrawableResult) r9
            java.lang.Object r11 = r7.L$2
            coil.fetch.FetchResult r11 = (coil.fetch.FetchResult) r11
            java.lang.Object r12 = r7.L$1
            coil.decode.Options r12 = (coil.decode.Options) r12
            r17 = r1
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r26)
            r21 = r1
            r1 = r26
            r23 = r17
            r17 = r0
            r0 = r7
            r7 = r14
            r14 = r12
            r12 = r10
            r10 = r23
            r24 = r13
            r13 = r11
            r11 = r24
            goto L_0x032c
        L_0x0071:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0079:
            java.lang.Object r1 = r7.L$3
            coil.decode.Decoder r1 = (coil.decode.Decoder) r1
            java.lang.Object r2 = r7.L$2
            coil.fetch.FetchResult r2 = (coil.fetch.FetchResult) r2
            java.lang.Object r3 = r7.L$1
            coil.decode.Options r3 = (coil.decode.Options) r3
            java.lang.Object r4 = r7.L$0
            kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
            kotlin.ResultKt.throwOnFailure(r26)     // Catch:{ Exception -> 0x0091 }
            r13 = r1
            r1 = r26
            goto L_0x0193
        L_0x0091:
            r0 = move-exception
            goto L_0x01b6
        L_0x0094:
            java.lang.Object r1 = r7.L$1
            coil.decode.Options r1 = (coil.decode.Options) r1
            java.lang.Object r2 = r7.L$0
            kotlinx.coroutines.CoroutineScope r2 = (kotlinx.coroutines.CoroutineScope) r2
            kotlin.ResultKt.throwOnFailure(r26)
            r11 = r1
            r9 = r2
            r1 = r26
            goto L_0x00f3
        L_0x00a4:
            kotlin.ResultKt.throwOnFailure(r26)
            kotlinx.coroutines.CoroutineScope r9 = r7.p$
            coil.RealImageLoader r1 = r7.this$0
            coil.memory.RequestService r17 = r1.requestService
            coil.request.Request r1 = r7.$request
            coil.size.SizeResolver r2 = r7.$sizeResolver
            coil.size.Size r3 = r7.$size
            coil.size.Scale r4 = r7.$scale
            coil.RealImageLoader r5 = r7.this$0
            coil.util.SystemCallbacks r5 = r5.systemCallbacks
            boolean r22 = r5.isOnline()
            r18 = r1
            r19 = r2
            r20 = r3
            r21 = r4
            coil.decode.Options r11 = r17.options(r18, r19, r20, r21, r22)
            coil.EventListener r1 = r7.$eventListener
            coil.request.Request r2 = r7.$request
            coil.fetch.Fetcher r3 = r7.$fetcher
            r1.fetchStart(r2, r3, r11)
            coil.fetch.Fetcher r1 = r7.$fetcher
            coil.RealImageLoader r2 = r7.this$0
            coil.bitmappool.BitmapPool r2 = r2.bitmapPool
            java.lang.Object r3 = r7.$mappedData
            coil.size.Size r4 = r7.$size
            r7.L$0 = r9
            r7.L$1 = r11
            r5 = 1
            r7.label = r5
            r5 = r11
            r6 = r25
            java.lang.Object r1 = r1.fetch(r2, r3, r4, r5, r6)
            if (r1 != r0) goto L_0x00f3
            return r0
        L_0x00f3:
            r12 = r1
            coil.fetch.FetchResult r12 = (coil.fetch.FetchResult) r12
            coil.EventListener r1 = r7.$eventListener
            coil.request.Request r2 = r7.$request
            coil.fetch.Fetcher r3 = r7.$fetcher
            r1.fetchEnd(r2, r3, r11)
            boolean r1 = r12 instanceof coil.fetch.SourceResult
            if (r1 == 0) goto L_0x01c4
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r9)     // Catch:{ Exception -> 0x01b4 }
            coil.request.Request r1 = r7.$request     // Catch:{ Exception -> 0x01b4 }
            boolean r1 = r1 instanceof coil.request.LoadRequest     // Catch:{ Exception -> 0x01b4 }
            if (r1 == 0) goto L_0x012f
            coil.request.Request r1 = r7.$request     // Catch:{ Exception -> 0x01b4 }
            coil.target.Target r1 = r1.getTarget()     // Catch:{ Exception -> 0x01b4 }
            if (r1 != 0) goto L_0x012f
            coil.request.Request r1 = r7.$request     // Catch:{ Exception -> 0x01b4 }
            coil.request.CachePolicy r1 = r1.getMemoryCachePolicy()     // Catch:{ Exception -> 0x01b4 }
            if (r1 == 0) goto L_0x011d
            goto L_0x0127
        L_0x011d:
            coil.RealImageLoader r1 = r7.this$0     // Catch:{ Exception -> 0x01b4 }
            coil.DefaultRequestOptions r1 = r1.getDefaults()     // Catch:{ Exception -> 0x01b4 }
            coil.request.CachePolicy r1 = r1.getMemoryCachePolicy()     // Catch:{ Exception -> 0x01b4 }
        L_0x0127:
            boolean r1 = r1.getWriteEnabled()     // Catch:{ Exception -> 0x01b4 }
            if (r1 != 0) goto L_0x012f
            r5 = 1
            goto L_0x0130
        L_0x012f:
            r5 = 0
        L_0x0130:
            if (r5 == 0) goto L_0x0138
            coil.decode.EmptyDecoder r1 = coil.decode.EmptyDecoder.INSTANCE     // Catch:{ Exception -> 0x01b4 }
            coil.decode.Decoder r1 = (coil.decode.Decoder) r1     // Catch:{ Exception -> 0x01b4 }
        L_0x0136:
            r13 = r1
            goto L_0x0165
        L_0x0138:
            coil.request.Request r1 = r7.$request     // Catch:{ Exception -> 0x01b4 }
            coil.decode.Decoder r1 = r1.getDecoder()     // Catch:{ Exception -> 0x01b4 }
            if (r1 == 0) goto L_0x0141
            goto L_0x0136
        L_0x0141:
            coil.RealImageLoader r1 = r7.this$0     // Catch:{ Exception -> 0x01b4 }
            coil.ComponentRegistry r1 = r1.registry     // Catch:{ Exception -> 0x01b4 }
            coil.request.Request r2 = r7.$request     // Catch:{ Exception -> 0x01b4 }
            java.lang.Object r2 = r2.getData()     // Catch:{ Exception -> 0x01b4 }
            if (r2 != 0) goto L_0x0152
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ Exception -> 0x01b4 }
        L_0x0152:
            r3 = r12
            coil.fetch.SourceResult r3 = (coil.fetch.SourceResult) r3     // Catch:{ Exception -> 0x01b4 }
            okio.BufferedSource r3 = r3.getSource()     // Catch:{ Exception -> 0x01b4 }
            r4 = r12
            coil.fetch.SourceResult r4 = (coil.fetch.SourceResult) r4     // Catch:{ Exception -> 0x01b4 }
            java.lang.String r4 = r4.getMimeType()     // Catch:{ Exception -> 0x01b4 }
            coil.decode.Decoder r1 = r1.requireDecoder(r2, r3, r4)     // Catch:{ Exception -> 0x01b4 }
            goto L_0x0136
        L_0x0165:
            coil.EventListener r1 = r7.$eventListener     // Catch:{ Exception -> 0x01b4 }
            coil.request.Request r2 = r7.$request     // Catch:{ Exception -> 0x01b4 }
            r1.decodeStart(r2, r13, r11)     // Catch:{ Exception -> 0x01b4 }
            coil.RealImageLoader r1 = r7.this$0     // Catch:{ Exception -> 0x01b4 }
            coil.bitmappool.BitmapPool r2 = r1.bitmapPool     // Catch:{ Exception -> 0x01b4 }
            r1 = r12
            coil.fetch.SourceResult r1 = (coil.fetch.SourceResult) r1     // Catch:{ Exception -> 0x01b4 }
            okio.BufferedSource r3 = r1.getSource()     // Catch:{ Exception -> 0x01b4 }
            coil.size.Size r4 = r7.$size     // Catch:{ Exception -> 0x01b4 }
            r7.L$0 = r9     // Catch:{ Exception -> 0x01b4 }
            r7.L$1 = r11     // Catch:{ Exception -> 0x01b4 }
            r7.L$2 = r12     // Catch:{ Exception -> 0x01b4 }
            r7.L$3 = r13     // Catch:{ Exception -> 0x01b4 }
            r7.label = r10     // Catch:{ Exception -> 0x01b4 }
            r1 = r13
            r5 = r11
            r6 = r25
            java.lang.Object r1 = r1.decode(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x01b4 }
            if (r1 != r0) goto L_0x0190
            return r0
        L_0x0190:
            r4 = r9
            r3 = r11
            r2 = r12
        L_0x0193:
            coil.decode.DecodeResult r1 = (coil.decode.DecodeResult) r1     // Catch:{ Exception -> 0x0091 }
            coil.EventListener r5 = r7.$eventListener     // Catch:{ Exception -> 0x0091 }
            coil.request.Request r6 = r7.$request     // Catch:{ Exception -> 0x0091 }
            r5.decodeEnd(r6, r13, r3)     // Catch:{ Exception -> 0x0091 }
            coil.fetch.DrawableResult r5 = new coil.fetch.DrawableResult
            android.graphics.drawable.Drawable r6 = r1.getDrawable()
            boolean r1 = r1.isSampled()
            r9 = r2
            coil.fetch.SourceResult r9 = (coil.fetch.SourceResult) r9
            coil.decode.DataSource r9 = r9.getDataSource()
            r5.<init>(r6, r1, r9)
            r12 = r2
            r11 = r3
            r9 = r4
            goto L_0x01cb
        L_0x01b4:
            r0 = move-exception
            r2 = r12
        L_0x01b6:
            coil.fetch.SourceResult r2 = (coil.fetch.SourceResult) r2
            okio.BufferedSource r1 = r2.getSource()
            java.io.Closeable r1 = (java.io.Closeable) r1
            coil.util.Extensions.closeQuietly(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x01c4:
            boolean r1 = r12 instanceof coil.fetch.DrawableResult
            if (r1 == 0) goto L_0x0389
            r5 = r12
            coil.fetch.DrawableResult r5 = (coil.fetch.DrawableResult) r5
        L_0x01cb:
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r9)
            coil.RealImageLoader r1 = r7.this$0
            coil.request.Request r2 = r7.$request
            coil.size.Size r3 = r7.$size
            coil.EventListener r4 = r7.$eventListener
            java.util.List r6 = r2.getTransformations()
            boolean r10 = r6.isEmpty()
            if (r10 == 0) goto L_0x01e2
            goto L_0x0370
        L_0x01e2:
            r4.transformStart(r2)
            android.graphics.drawable.Drawable r10 = r5.getDrawable()
            boolean r10 = r10 instanceof android.graphics.drawable.BitmapDrawable
            r13 = 4
            java.lang.String r14 = "RealImageLoader"
            if (r10 == 0) goto L_0x0257
            android.graphics.drawable.Drawable r10 = r5.getDrawable()
            android.graphics.drawable.BitmapDrawable r10 = (android.graphics.drawable.BitmapDrawable) r10
            android.graphics.Bitmap r10 = r10.getBitmap()
            android.graphics.Bitmap$Config[] r15 = coil.memory.RequestService.VALID_TRANSFORMATION_CONFIGS
            java.lang.String r8 = "resultBitmap"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r10, r8)
            android.graphics.Bitmap$Config r8 = coil.util.Bitmaps.getSafeConfig(r10)
            boolean r8 = kotlin.collections.ArraysKt.contains((T[]) r15, r8)
            if (r8 == 0) goto L_0x020e
        L_0x020b:
            r15 = 0
            goto L_0x02b0
        L_0x020e:
            coil.util.Logger r8 = r1.getLogger$coil_base_release()
            if (r8 == 0) goto L_0x023c
            int r15 = r8.getLevel()
            if (r15 > r13) goto L_0x023c
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            java.lang.String r13 = "Converting bitmap with config "
            r15.append(r13)
            android.graphics.Bitmap$Config r10 = coil.util.Bitmaps.getSafeConfig(r10)
            r15.append(r10)
            java.lang.String r10 = " to apply transformations: "
            r15.append(r10)
            r15.append(r6)
            java.lang.String r10 = r15.toString()
            r13 = 4
            r15 = 0
            r8.log(r14, r13, r10, r15)
        L_0x023c:
            coil.decode.DrawableDecoderService r17 = r1.drawableDecoder
            android.graphics.drawable.Drawable r18 = r5.getDrawable()
            android.graphics.Bitmap$Config r19 = r11.getConfig()
            coil.size.Scale r21 = r11.getScale()
            boolean r22 = r11.getAllowInexactSize()
            r20 = r3
            android.graphics.Bitmap r10 = r17.convert(r18, r19, r20, r21, r22)
            goto L_0x020b
        L_0x0257:
            coil.util.Logger r8 = r1.getLogger$coil_base_release()
            if (r8 == 0) goto L_0x0295
            int r10 = r8.getLevel()
            r13 = 4
            if (r10 > r13) goto L_0x0295
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r13 = "Converting drawable of type "
            r10.append(r13)
            android.graphics.drawable.Drawable r13 = r5.getDrawable()
            java.lang.Class r13 = r13.getClass()
            java.lang.String r13 = r13.getCanonicalName()
            r10.append(r13)
            r13 = 32
            r10.append(r13)
            java.lang.String r13 = "to apply transformations: "
            r10.append(r13)
            r10.append(r6)
            java.lang.String r10 = r10.toString()
            r13 = 4
            r15 = 0
            r8.log(r14, r13, r10, r15)
            goto L_0x0296
        L_0x0295:
            r15 = 0
        L_0x0296:
            coil.decode.DrawableDecoderService r17 = r1.drawableDecoder
            android.graphics.drawable.Drawable r18 = r5.getDrawable()
            android.graphics.Bitmap$Config r19 = r11.getConfig()
            coil.size.Scale r21 = r11.getScale()
            boolean r22 = r11.getAllowInexactSize()
            r20 = r3
            android.graphics.Bitmap r10 = r17.convert(r18, r19, r20, r21, r22)
        L_0x02b0:
            r8 = r6
            java.util.Collection r8 = (java.util.Collection) r8
            int r8 = r8.size()
            r17 = r0
            r26 = r1
            r15 = r3
            r0 = r7
            r3 = r0
            r1 = r9
            r14 = r11
            r13 = r12
            r7 = r2
            r12 = r4
            r4 = r6
            r2 = r8
            r11 = r10
            r8 = 0
            r9 = r5
            r5 = r4
            r6 = r1
        L_0x02ca:
            if (r8 >= r2) goto L_0x0341
            java.lang.Object r18 = r4.get(r8)
            r19 = r10
            r10 = r18
            coil.transform.Transformation r10 = (coil.transform.Transformation) r10
            r18 = r10
            coil.bitmappool.BitmapPool r10 = r26.bitmapPool
            r20 = r10
            java.lang.String r10 = "bitmap"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r10)
            r0.L$0 = r1
            r0.L$1 = r14
            r0.L$2 = r13
            r0.L$3 = r9
            r10 = r26
            r0.L$4 = r10
            r0.L$5 = r7
            r0.L$6 = r15
            r0.L$7 = r12
            r0.L$8 = r3
            r0.L$9 = r6
            r0.L$10 = r5
            r0.L$11 = r4
            r0.L$12 = r11
            r0.I$0 = r8
            r0.I$1 = r2
            r21 = r1
            r1 = r19
            r0.L$13 = r1
            r1 = r18
            r0.L$14 = r1
            r0.L$15 = r11
            r18 = r2
            r2 = 3
            r0.label = r2
            r2 = r20
            java.lang.Object r1 = r1.transform(r2, r11, r15, r3)
            r2 = r17
            if (r1 != r2) goto L_0x031f
            return r2
        L_0x031f:
            r17 = r2
            r11 = r15
            r2 = r18
            r15 = r10
            r10 = r19
            r23 = r8
            r8 = r3
            r3 = r23
        L_0x032c:
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r6)
            r16 = 1
            int r3 = r3 + 1
            r26 = r15
            r15 = r11
            r11 = r1
            r1 = r21
            r23 = r8
            r8 = r3
            r3 = r23
            goto L_0x02ca
        L_0x0341:
            r10 = r26
            java.lang.String r0 = "transformedBitmap"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r0)
            android.content.Context r0 = r10.context
            android.content.res.Resources r0 = r0.getResources()
            java.lang.String r1 = "context.resources"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable
            r1.<init>(r0, r11)
            r18 = r1
            android.graphics.drawable.Drawable r18 = (android.graphics.drawable.Drawable) r18
            r19 = 0
            r20 = 0
            r21 = 6
            r22 = 0
            r17 = r9
            coil.fetch.DrawableResult r5 = coil.fetch.DrawableResult.copy$default(r17, r18, r19, r20, r21, r22)
            r12.transformEnd(r7)
        L_0x0370:
            android.graphics.drawable.Drawable r0 = r5.getDrawable()
            boolean r1 = r0 instanceof android.graphics.drawable.BitmapDrawable
            if (r1 != 0) goto L_0x037a
            r11 = 0
            goto L_0x037b
        L_0x037a:
            r11 = r0
        L_0x037b:
            android.graphics.drawable.BitmapDrawable r11 = (android.graphics.drawable.BitmapDrawable) r11
            if (r11 == 0) goto L_0x0388
            android.graphics.Bitmap r0 = r11.getBitmap()
            if (r0 == 0) goto L_0x0388
            r0.prepareToDraw()
        L_0x0388:
            return r5
        L_0x0389:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader$loadData$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
