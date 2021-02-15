package coil;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import coil.EventListener;
import coil.bitmappool.BitmapPool;
import coil.decode.BitmapFactoryDecoder;
import coil.decode.DataSource;
import coil.decode.Decoder;
import coil.decode.DrawableDecoderService;
import coil.decode.Options;
import coil.fetch.AssetUriFetcher;
import coil.fetch.BitmapFetcher;
import coil.fetch.ContentUriFetcher;
import coil.fetch.DrawableFetcher;
import coil.fetch.DrawableResult;
import coil.fetch.Fetcher;
import coil.fetch.FileFetcher;
import coil.fetch.HttpUriFetcher;
import coil.fetch.HttpUrlFetcher;
import coil.fetch.ResourceUriFetcher;
import coil.map.FileUriMapper;
import coil.map.ResourceIntMapper;
import coil.map.ResourceUriMapper;
import coil.map.StringMapper;
import coil.memory.BitmapReferenceCounter;
import coil.memory.DelegateService;
import coil.memory.MemoryCache;
import coil.memory.MemoryCacheService;
import coil.memory.RequestService;
import coil.memory.TargetDelegate;
import coil.memory.WeakMemoryCache;
import coil.request.BaseTargetRequestDisposable;
import coil.request.LoadRequest;
import coil.request.Parameters;
import coil.request.Request;
import coil.request.RequestDisposable;
import coil.request.SuccessResult;
import coil.request.ViewTargetRequestDisposable;
import coil.size.Scale;
import coil.size.Size;
import coil.size.SizeResolver;
import coil.target.ViewTarget;
import coil.transform.Transformation;
import coil.util.Bitmaps;
import coil.util.Extensions;
import coil.util.Logger;
import coil.util.SystemCallbacks;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.io.File;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import okhttp3.Call;
import okhttp3.HttpUrl;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\b\u0000\u0018\u0000 e2\u00020\u0001:\u0002efBW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0002\u0010\u0016JC\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206HHø\u0001\u0000¢\u0006\u0004\b7\u00108J\b\u00109\u001a\u00020:H\u0016JS\u0010;\u001a\u0004\u0018\u00010<\"\b\b\u0000\u0010=*\u00020>2\f\u0010?\u001a\b\u0012\u0004\u0012\u0002H=0@2\u0006\u0010A\u001a\u0002H=2\u0006\u0010B\u001a\u00020C2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020F0E2\u0006\u0010G\u001a\u00020HHHø\u0001\u0000¢\u0006\u0004\bI\u0010JJ\u0019\u0010K\u001a\u00020L2\u0006\u0010/\u001a\u00020MH@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0010\u0010K\u001a\u00020O2\u0006\u0010/\u001a\u00020PH\u0016J\u0019\u0010Q\u001a\u00020R2\u0006\u0010/\u001a\u000200H@ø\u0001\u0000¢\u0006\u0002\u0010SJ\u0010\u0010T\u001a\u00020:2\u0006\u0010U\u001a\u00020VH\u0016JO\u0010W\u001a\u00020,2\u0006\u0010X\u001a\u00020>2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020>0@2\u0006\u0010/\u001a\u0002002\u0006\u0010Y\u001a\u00020Z2\u0006\u00101\u001a\u0002022\u0006\u0010[\u001a\u00020\\2\u0006\u00105\u001a\u000206HHø\u0001\u0000¢\u0006\u0002\u0010]J#\u0010^\u001a\u00020>2\u0006\u0010A\u001a\u00020>2\u0006\u0010G\u001a\u00020HHHø\u0001\u0000¢\u0006\u0004\b_\u0010`J\u000e\u0010a\u001a\u00020:2\u0006\u0010b\u001a\u00020cJ\b\u0010d\u001a\u00020:H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006g"}, d2 = {"Lcoil/RealImageLoader;", "Lcoil/ImageLoader;", "context", "Landroid/content/Context;", "defaults", "Lcoil/DefaultRequestOptions;", "bitmapPool", "Lcoil/bitmappool/BitmapPool;", "referenceCounter", "Lcoil/memory/BitmapReferenceCounter;", "memoryCache", "Lcoil/memory/MemoryCache;", "weakMemoryCache", "Lcoil/memory/WeakMemoryCache;", "callFactory", "Lokhttp3/Call$Factory;", "eventListenerFactory", "Lcoil/EventListener$Factory;", "registry", "Lcoil/ComponentRegistry;", "logger", "Lcoil/util/Logger;", "(Landroid/content/Context;Lcoil/DefaultRequestOptions;Lcoil/bitmappool/BitmapPool;Lcoil/memory/BitmapReferenceCounter;Lcoil/memory/MemoryCache;Lcoil/memory/WeakMemoryCache;Lokhttp3/Call$Factory;Lcoil/EventListener$Factory;Lcoil/ComponentRegistry;Lcoil/util/Logger;)V", "getDefaults", "()Lcoil/DefaultRequestOptions;", "delegateService", "Lcoil/memory/DelegateService;", "drawableDecoder", "Lcoil/decode/DrawableDecoderService;", "exceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "isShutdown", "", "loaderScope", "Lkotlinx/coroutines/CoroutineScope;", "getLogger$coil_base_release", "()Lcoil/util/Logger;", "memoryCacheService", "Lcoil/memory/MemoryCacheService;", "requestService", "Lcoil/memory/RequestService;", "systemCallbacks", "Lcoil/util/SystemCallbacks;", "applyTransformations", "Lcoil/fetch/DrawableResult;", "scope", "result", "request", "Lcoil/request/Request;", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "eventListener", "Lcoil/EventListener;", "applyTransformations$coil_base_release", "(Lkotlinx/coroutines/CoroutineScope;Lcoil/fetch/DrawableResult;Lcoil/request/Request;Lcoil/size/Size;Lcoil/decode/Options;Lcoil/EventListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearMemory", "", "computeCacheKey", "Lcoil/memory/MemoryCache$Key;", "T", "", "fetcher", "Lcoil/fetch/Fetcher;", "data", "parameters", "Lcoil/request/Parameters;", "transformations", "", "Lcoil/transform/Transformation;", "lazySizeResolver", "Lcoil/RealImageLoader$LazySizeResolver;", "computeCacheKey$coil_base_release", "(Lcoil/fetch/Fetcher;Ljava/lang/Object;Lcoil/request/Parameters;Ljava/util/List;Lcoil/RealImageLoader$LazySizeResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "execute", "Lcoil/request/RequestResult;", "Lcoil/request/GetRequest;", "(Lcoil/request/GetRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcoil/request/RequestDisposable;", "Lcoil/request/LoadRequest;", "executeInternal", "Lcoil/request/SuccessResult;", "(Lcoil/request/Request;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invalidate", "key", "", "loadData", "mappedData", "sizeResolver", "Lcoil/size/SizeResolver;", "scale", "Lcoil/size/Scale;", "(Ljava/lang/Object;Lcoil/fetch/Fetcher;Lcoil/request/Request;Lcoil/size/SizeResolver;Lcoil/size/Size;Lcoil/size/Scale;Lcoil/EventListener;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mapData", "mapData$coil_base_release", "(Ljava/lang/Object;Lcoil/RealImageLoader$LazySizeResolver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onTrimMemory", "level", "", "shutdown", "Companion", "LazySizeResolver", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RealImageLoader.kt */
public final class RealImageLoader implements ImageLoader {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "RealImageLoader";
    /* access modifiers changed from: private */
    public final BitmapPool bitmapPool;
    /* access modifiers changed from: private */
    public final Context context;
    private final DefaultRequestOptions defaults;
    /* access modifiers changed from: private */
    public final DelegateService delegateService = new DelegateService(this, this.referenceCounter, this.logger);
    /* access modifiers changed from: private */
    public final DrawableDecoderService drawableDecoder = new DrawableDecoderService(this.bitmapPool);
    /* access modifiers changed from: private */
    public final EventListener.Factory eventListenerFactory;
    private final CoroutineExceptionHandler exceptionHandler = new RealImageLoader$$special$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, this);
    /* access modifiers changed from: private */
    public boolean isShutdown;
    /* access modifiers changed from: private */
    public final CoroutineScope loaderScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()));
    private final Logger logger;
    /* access modifiers changed from: private */
    public final MemoryCache memoryCache;
    /* access modifiers changed from: private */
    public final MemoryCacheService memoryCacheService = new MemoryCacheService(this.requestService, this.logger);
    /* access modifiers changed from: private */
    public final BitmapReferenceCounter referenceCounter;
    /* access modifiers changed from: private */
    public final ComponentRegistry registry;
    /* access modifiers changed from: private */
    public final RequestService requestService = new RequestService(getDefaults(), this.logger);
    /* access modifiers changed from: private */
    public final SystemCallbacks systemCallbacks = new SystemCallbacks(this, this.context);
    private final WeakMemoryCache weakMemoryCache;

    public RealImageLoader(Context context2, DefaultRequestOptions defaultRequestOptions, BitmapPool bitmapPool2, BitmapReferenceCounter bitmapReferenceCounter, MemoryCache memoryCache2, WeakMemoryCache weakMemoryCache2, Call.Factory factory, EventListener.Factory factory2, ComponentRegistry componentRegistry, Logger logger2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(defaultRequestOptions, RemoteConfigComponent.DEFAULTS_FILE_NAME);
        Intrinsics.checkParameterIsNotNull(bitmapPool2, "bitmapPool");
        Intrinsics.checkParameterIsNotNull(bitmapReferenceCounter, "referenceCounter");
        Intrinsics.checkParameterIsNotNull(memoryCache2, "memoryCache");
        Intrinsics.checkParameterIsNotNull(weakMemoryCache2, "weakMemoryCache");
        Intrinsics.checkParameterIsNotNull(factory, "callFactory");
        Intrinsics.checkParameterIsNotNull(factory2, "eventListenerFactory");
        Intrinsics.checkParameterIsNotNull(componentRegistry, "registry");
        this.context = context2;
        this.defaults = defaultRequestOptions;
        this.bitmapPool = bitmapPool2;
        this.referenceCounter = bitmapReferenceCounter;
        this.memoryCache = memoryCache2;
        this.weakMemoryCache = weakMemoryCache2;
        this.eventListenerFactory = factory2;
        this.logger = logger2;
        Class<Uri> cls = Uri.class;
        Class<Uri> cls2 = Uri.class;
        Class<Integer> cls3 = Integer.class;
        Class<Uri> cls4 = Uri.class;
        Class<HttpUrl> cls5 = HttpUrl.class;
        Class<File> cls6 = File.class;
        Class<Uri> cls7 = Uri.class;
        Class<Uri> cls8 = Uri.class;
        Class<Uri> cls9 = Uri.class;
        Class<Drawable> cls10 = Drawable.class;
        Class<Bitmap> cls11 = Bitmap.class;
        this.registry = componentRegistry.newBuilder().add(String.class, new StringMapper()).add(cls, new FileUriMapper()).add(cls2, new ResourceUriMapper(this.context)).add(cls3, new ResourceIntMapper(this.context)).add(cls4, new HttpUriFetcher(factory)).add(cls5, new HttpUrlFetcher(factory)).add(cls6, new FileFetcher()).add(cls7, new AssetUriFetcher(this.context)).add(cls8, new ContentUriFetcher(this.context)).add(cls9, new ResourceUriFetcher(this.context, this.drawableDecoder)).add(cls10, new DrawableFetcher(this.context, this.drawableDecoder)).add(cls11, new BitmapFetcher(this.context)).add((Decoder) new BitmapFactoryDecoder(this.context)).build();
    }

    public DefaultRequestOptions getDefaults() {
        return this.defaults;
    }

    public final Logger getLogger$coil_base_release() {
        return this.logger;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"Lcoil/RealImageLoader$Companion;", "", "()V", "TAG", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: RealImageLoader.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public RequestDisposable execute(LoadRequest loadRequest) {
        Intrinsics.checkParameterIsNotNull(loadRequest, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Job launch$default = BuildersKt__Builders_commonKt.launch$default(this.loaderScope, this.exceptionHandler, (CoroutineStart) null, new RealImageLoader$execute$job$1(this, loadRequest, (Continuation) null), 2, (Object) null);
        if (loadRequest.getTarget() instanceof ViewTarget) {
            return new ViewTargetRequestDisposable(Extensions.getRequestManager(((ViewTarget) loadRequest.getTarget()).getView()).setCurrentRequestJob(launch$default), (ViewTarget) loadRequest.getTarget());
        }
        return new BaseTargetRequestDisposable(launch$default);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        r6 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0054, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0062, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0065, code lost:
        throw r5;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:10:0x002e, B:16:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0062 A[ExcHandler: CancellationException (r5v1 'e' java.util.concurrent.CancellationException A[CUSTOM_DECLARE]), Splitter:B:10:0x002e] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object execute(coil.request.GetRequest r5, kotlin.coroutines.Continuation<? super coil.request.RequestResult> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof coil.RealImageLoader$execute$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            coil.RealImageLoader$execute$1 r0 = (coil.RealImageLoader$execute$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            coil.RealImageLoader$execute$1 r0 = new coil.RealImageLoader$execute$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r5 = r0.L$1
            coil.request.GetRequest r5 = (coil.request.GetRequest) r5
            java.lang.Object r0 = r0.L$0
            coil.RealImageLoader r0 = (coil.RealImageLoader) r0
            kotlin.ResultKt.throwOnFailure(r6)     // Catch:{ CancellationException -> 0x0062, all -> 0x0032 }
            goto L_0x0050
        L_0x0032:
            r6 = move-exception
            goto L_0x0055
        L_0x0034:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r6)
            r6 = r5
            coil.request.Request r6 = (coil.request.Request) r6     // Catch:{ CancellationException -> 0x0062, all -> 0x0053 }
            r0.L$0 = r4     // Catch:{ CancellationException -> 0x0062, all -> 0x0053 }
            r0.L$1 = r5     // Catch:{ CancellationException -> 0x0062, all -> 0x0053 }
            r0.label = r3     // Catch:{ CancellationException -> 0x0062, all -> 0x0053 }
            java.lang.Object r6 = r4.executeInternal(r6, r0)     // Catch:{ CancellationException -> 0x0062, all -> 0x0053 }
            if (r6 != r1) goto L_0x004f
            return r1
        L_0x004f:
            r0 = r4
        L_0x0050:
            coil.request.RequestResult r6 = (coil.request.RequestResult) r6     // Catch:{ CancellationException -> 0x0062, all -> 0x0032 }
            goto L_0x0061
        L_0x0053:
            r6 = move-exception
            r0 = r4
        L_0x0055:
            coil.memory.RequestService r0 = r0.requestService
            coil.request.Request r5 = (coil.request.Request) r5
            r1 = 0
            coil.request.ErrorResult r5 = r0.errorResult(r5, r6, r1)
            r6 = r5
            coil.request.RequestResult r6 = (coil.request.RequestResult) r6
        L_0x0061:
            return r6
        L_0x0062:
            r5 = move-exception
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.execute(coil.request.GetRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object executeInternal(Request request, Continuation<? super SuccessResult> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new RealImageLoader$executeInternal$2(this, request, (Continuation) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object mapData$coil_base_release(java.lang.Object r19, coil.RealImageLoader.LazySizeResolver r20, kotlin.coroutines.Continuation<java.lang.Object> r21) {
        /*
            r18 = this;
            r0 = r21
            boolean r1 = r0 instanceof coil.RealImageLoader$mapData$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            coil.RealImageLoader$mapData$1 r1 = (coil.RealImageLoader$mapData$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r18
            goto L_0x001f
        L_0x0018:
            coil.RealImageLoader$mapData$1 r1 = new coil.RealImageLoader$mapData$1
            r2 = r18
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r6 = 1
            if (r4 == 0) goto L_0x006d
            if (r4 != r6) goto L_0x0065
            java.lang.Object r4 = r1.L$12
            android.graphics.drawable.BitmapDrawable r4 = (android.graphics.drawable.BitmapDrawable) r4
            java.lang.Object r4 = r1.L$11
            kotlin.jvm.internal.Ref$ObjectRef r4 = (kotlin.jvm.internal.Ref.ObjectRef) r4
            java.lang.Object r7 = r1.L$10
            coil.map.MeasuredMapper r7 = (coil.map.MeasuredMapper) r7
            java.lang.Object r8 = r1.L$9
            java.lang.Object r9 = r1.L$8
            coil.RealImageLoader$LazySizeResolver r9 = (coil.RealImageLoader.LazySizeResolver) r9
            java.lang.Object r10 = r1.L$7
            coil.map.MeasuredMapper r10 = (coil.map.MeasuredMapper) r10
            java.lang.Object r10 = r1.L$6
            java.lang.Class r10 = (java.lang.Class) r10
            java.lang.Object r10 = r1.L$5
            kotlin.Pair r10 = (kotlin.Pair) r10
            int r10 = r1.I$1
            int r11 = r1.I$0
            java.lang.Object r12 = r1.L$4
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r13 = r1.L$3
            kotlin.jvm.internal.Ref$ObjectRef r13 = (kotlin.jvm.internal.Ref.ObjectRef) r13
            java.lang.Object r14 = r1.L$2
            coil.RealImageLoader$LazySizeResolver r14 = (coil.RealImageLoader.LazySizeResolver) r14
            java.lang.Object r15 = r1.L$1
            java.lang.Object r5 = r1.L$0
            coil.RealImageLoader r5 = (coil.RealImageLoader) r5
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0101
        L_0x0065:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x006d:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r4 = r19
            r0.element = r4
            coil.ComponentRegistry r5 = r18.registry
            java.util.List r5 = r5.getMeasuredMappers$coil_base_release()
            r7 = r5
            java.util.Collection r7 = (java.util.Collection) r7
            int r7 = r7.size()
            r12 = r5
            r10 = r7
            r11 = 0
            r5 = r0
            r7 = r2
            r0 = r20
        L_0x008f:
            if (r11 >= r10) goto L_0x0127
            java.lang.Object r8 = r12.get(r11)
            kotlin.Pair r8 = (kotlin.Pair) r8
            java.lang.Object r9 = r8.component1()
            java.lang.Class r9 = (java.lang.Class) r9
            java.lang.Object r13 = r8.component2()
            coil.map.MeasuredMapper r13 = (coil.map.MeasuredMapper) r13
            T r14 = r5.element
            java.lang.Class r14 = r14.getClass()
            boolean r14 = r9.isAssignableFrom(r14)
            if (r14 == 0) goto L_0x0123
            if (r13 == 0) goto L_0x011b
            T r14 = r5.element
            boolean r14 = r13.handles(r14)
            if (r14 == 0) goto L_0x0123
            T r14 = r5.element
            r15 = 0
            android.graphics.drawable.BitmapDrawable r15 = (android.graphics.drawable.BitmapDrawable) r15
            coil.size.Size r16 = r0.size
            if (r16 == 0) goto L_0x00c9
            r6 = r13
            r8 = r16
            r13 = r5
            goto L_0x0113
        L_0x00c9:
            r0.beforeResolveSize(r15)
            coil.size.SizeResolver r6 = r0.sizeResolver
            r1.L$0 = r7
            r1.L$1 = r4
            r1.L$2 = r0
            r1.L$3 = r5
            r1.L$4 = r12
            r1.I$0 = r11
            r1.I$1 = r10
            r1.L$5 = r8
            r1.L$6 = r9
            r1.L$7 = r13
            r1.L$8 = r0
            r1.L$9 = r14
            r1.L$10 = r13
            r1.L$11 = r5
            r1.L$12 = r15
            r8 = 1
            r1.label = r8
            java.lang.Object r6 = r6.size(r1)
            if (r6 != r3) goto L_0x00f8
            return r3
        L_0x00f8:
            r9 = r0
            r15 = r4
            r4 = r5
            r8 = r14
            r14 = r9
            r0 = r6
            r5 = r7
            r7 = r13
            r13 = r4
        L_0x0101:
            coil.size.Size r0 = (coil.size.Size) r0
            r9.size = r0
            r9.afterResolveSize(r0)
            r6 = r7
            r7 = r5
            r5 = r4
            r4 = r15
            r17 = r8
            r8 = r0
            r0 = r14
            r14 = r17
        L_0x0113:
            java.lang.Object r6 = r6.map(r14, r8)
            r5.element = r6
            r5 = r13
            goto L_0x0123
        L_0x011b:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type coil.map.MeasuredMapper<kotlin.Any, *>"
            r0.<init>(r1)
            throw r0
        L_0x0123:
            r6 = 1
            int r11 = r11 + r6
            goto L_0x008f
        L_0x0127:
            coil.ComponentRegistry r0 = r7.registry
            java.util.List r0 = r0.getMappers$coil_base_release()
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            r3 = 0
        L_0x0137:
            if (r3 >= r1) goto L_0x0175
            java.lang.Object r4 = r0.get(r3)
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r6 = r4.component1()
            java.lang.Class r6 = (java.lang.Class) r6
            java.lang.Object r4 = r4.component2()
            coil.map.Mapper r4 = (coil.map.Mapper) r4
            T r7 = r5.element
            java.lang.Class r7 = r7.getClass()
            boolean r6 = r6.isAssignableFrom(r7)
            if (r6 == 0) goto L_0x0172
            if (r4 == 0) goto L_0x016a
            T r6 = r5.element
            boolean r6 = r4.handles(r6)
            if (r6 == 0) goto L_0x0172
            T r6 = r5.element
            java.lang.Object r4 = r4.map(r6)
            r5.element = r4
            goto L_0x0172
        L_0x016a:
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type coil.map.Mapper<kotlin.Any, *>"
            r0.<init>(r1)
            throw r0
        L_0x0172:
            int r3 = r3 + 1
            goto L_0x0137
        L_0x0175:
            T r0 = r5.element
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.mapData$coil_base_release(java.lang.Object, coil.RealImageLoader$LazySizeResolver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: coil.size.Size} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object mapData$coil_base_release$$forInline(java.lang.Object r8, coil.RealImageLoader.LazySizeResolver r9, kotlin.coroutines.Continuation r10) {
        /*
            r7 = this;
            coil.ComponentRegistry r0 = r7.registry
            java.util.List r0 = r0.getMeasuredMappers$coil_base_release()
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            r2 = 0
            r3 = 0
        L_0x0011:
            if (r3 >= r1) goto L_0x006c
            java.lang.Object r4 = r0.get(r3)
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r5 = r4.component1()
            java.lang.Class r5 = (java.lang.Class) r5
            java.lang.Object r4 = r4.component2()
            coil.map.MeasuredMapper r4 = (coil.map.MeasuredMapper) r4
            java.lang.Class r6 = r8.getClass()
            boolean r5 = r5.isAssignableFrom(r6)
            if (r5 == 0) goto L_0x0069
            if (r4 == 0) goto L_0x0061
            boolean r5 = r4.handles(r8)
            if (r5 == 0) goto L_0x0069
            r5 = 0
            android.graphics.drawable.BitmapDrawable r5 = (android.graphics.drawable.BitmapDrawable) r5
            coil.size.Size r6 = r9.size
            if (r6 == 0) goto L_0x0041
            goto L_0x005c
        L_0x0041:
            r9.beforeResolveSize(r5)
            coil.size.SizeResolver r5 = r9.sizeResolver
            kotlin.jvm.internal.InlineMarker.mark((int) r2)
            java.lang.Object r5 = r5.size(r10)
            r6 = 1
            kotlin.jvm.internal.InlineMarker.mark((int) r6)
            r6 = r5
            coil.size.Size r6 = (coil.size.Size) r6
            r9.size = r6
            r9.afterResolveSize(r6)
        L_0x005c:
            java.lang.Object r8 = r4.map(r8, r6)
            goto L_0x0069
        L_0x0061:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            java.lang.String r9 = "null cannot be cast to non-null type coil.map.MeasuredMapper<kotlin.Any, *>"
            r8.<init>(r9)
            throw r8
        L_0x0069:
            int r3 = r3 + 1
            goto L_0x0011
        L_0x006c:
            coil.ComponentRegistry r9 = r7.registry
            java.util.List r9 = r9.getMappers$coil_base_release()
            r10 = r9
            java.util.Collection r10 = (java.util.Collection) r10
            int r10 = r10.size()
        L_0x007b:
            if (r2 >= r10) goto L_0x00b1
            java.lang.Object r0 = r9.get(r2)
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r1 = r0.component1()
            java.lang.Class r1 = (java.lang.Class) r1
            java.lang.Object r0 = r0.component2()
            coil.map.Mapper r0 = (coil.map.Mapper) r0
            java.lang.Class r3 = r8.getClass()
            boolean r1 = r1.isAssignableFrom(r3)
            if (r1 == 0) goto L_0x00ae
            if (r0 == 0) goto L_0x00a6
            boolean r1 = r0.handles(r8)
            if (r1 == 0) goto L_0x00ae
            java.lang.Object r8 = r0.map(r8)
            goto L_0x00ae
        L_0x00a6:
            kotlin.TypeCastException r8 = new kotlin.TypeCastException
            java.lang.String r9 = "null cannot be cast to non-null type coil.map.Mapper<kotlin.Any, *>"
            r8.<init>(r9)
            throw r8
        L_0x00ae:
            int r2 = r2 + 1
            goto L_0x007b
        L_0x00b1:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.mapData$coil_base_release$$forInline(java.lang.Object, coil.RealImageLoader$LazySizeResolver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: coil.RealImageLoader$LazySizeResolver} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> java.lang.Object computeCacheKey$coil_base_release(coil.fetch.Fetcher<T> r6, T r7, coil.request.Parameters r8, java.util.List<? extends coil.transform.Transformation> r9, coil.RealImageLoader.LazySizeResolver r10, kotlin.coroutines.Continuation<? super coil.memory.MemoryCache.Key> r11) {
        /*
            r5 = this;
            boolean r0 = r11 instanceof coil.RealImageLoader$computeCacheKey$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            coil.RealImageLoader$computeCacheKey$1 r0 = (coil.RealImageLoader$computeCacheKey$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            coil.RealImageLoader$computeCacheKey$1 r0 = new coil.RealImageLoader$computeCacheKey$1
            r0.<init>(r5, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x005e
            if (r2 != r3) goto L_0x0056
            java.lang.Object r6 = r0.L$10
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r7 = r0.L$9
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r0.L$8
            android.graphics.drawable.BitmapDrawable r8 = (android.graphics.drawable.BitmapDrawable) r8
            java.lang.Object r8 = r0.L$7
            r10 = r8
            coil.RealImageLoader$LazySizeResolver r10 = (coil.RealImageLoader.LazySizeResolver) r10
            java.lang.Object r8 = r0.L$6
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r0.L$5
            coil.RealImageLoader$LazySizeResolver r8 = (coil.RealImageLoader.LazySizeResolver) r8
            java.lang.Object r8 = r0.L$4
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r8 = r0.L$3
            coil.request.Parameters r8 = (coil.request.Parameters) r8
            java.lang.Object r9 = r0.L$2
            java.lang.Object r9 = r0.L$1
            coil.fetch.Fetcher r9 = (coil.fetch.Fetcher) r9
            java.lang.Object r9 = r0.L$0
            coil.RealImageLoader r9 = (coil.RealImageLoader) r9
            kotlin.ResultKt.throwOnFailure(r11)
            r9 = r6
            goto L_0x00a5
        L_0x0056:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x005e:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.String r11 = r6.key(r7)
            r2 = 0
            if (r11 == 0) goto L_0x00b5
            boolean r4 = r9.isEmpty()
            if (r4 == 0) goto L_0x0074
            coil.memory.MemoryCache$Key r6 = new coil.memory.MemoryCache$Key
            r6.<init>(r11, r8)
            goto L_0x00b4
        L_0x0074:
            android.graphics.drawable.BitmapDrawable r2 = (android.graphics.drawable.BitmapDrawable) r2
            coil.size.Size r4 = r10.size
            if (r4 == 0) goto L_0x007d
            goto L_0x00af
        L_0x007d:
            r10.beforeResolveSize(r2)
            coil.size.SizeResolver r4 = r10.sizeResolver
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r8
            r0.L$4 = r9
            r0.L$5 = r10
            r0.L$6 = r11
            r0.L$7 = r10
            r0.L$8 = r2
            r0.L$9 = r11
            r0.L$10 = r9
            r0.label = r3
            java.lang.Object r6 = r4.size(r0)
            if (r6 != r1) goto L_0x00a3
            return r1
        L_0x00a3:
            r7 = r11
            r11 = r6
        L_0x00a5:
            r4 = r11
            coil.size.Size r4 = (coil.size.Size) r4
            r10.size = r4
            r10.afterResolveSize(r4)
            r11 = r7
        L_0x00af:
            coil.memory.MemoryCache$Key r6 = new coil.memory.MemoryCache$Key
            r6.<init>((java.lang.String) r11, (java.util.List<? extends coil.transform.Transformation>) r9, (coil.size.Size) r4, (coil.request.Parameters) r8)
        L_0x00b4:
            return r6
        L_0x00b5:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.computeCacheKey$coil_base_release(coil.fetch.Fetcher, java.lang.Object, coil.request.Parameters, java.util.List, coil.RealImageLoader$LazySizeResolver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: coil.size.Size} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object computeCacheKey$coil_base_release$$forInline(coil.fetch.Fetcher r2, java.lang.Object r3, coil.request.Parameters r4, java.util.List r5, coil.RealImageLoader.LazySizeResolver r6, kotlin.coroutines.Continuation r7) {
        /*
            r1 = this;
            java.lang.String r2 = r2.key(r3)
            r3 = 0
            if (r2 == 0) goto L_0x003d
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L_0x0013
            coil.memory.MemoryCache$Key r3 = new coil.memory.MemoryCache$Key
            r3.<init>(r2, r4)
            goto L_0x003d
        L_0x0013:
            android.graphics.drawable.BitmapDrawable r3 = (android.graphics.drawable.BitmapDrawable) r3
            coil.size.Size r0 = r6.size
            if (r0 == 0) goto L_0x001c
            goto L_0x0038
        L_0x001c:
            r6.beforeResolveSize(r3)
            coil.size.SizeResolver r3 = r6.sizeResolver
            r0 = 0
            kotlin.jvm.internal.InlineMarker.mark((int) r0)
            java.lang.Object r3 = r3.size(r7)
            r7 = 1
            kotlin.jvm.internal.InlineMarker.mark((int) r7)
            r0 = r3
            coil.size.Size r0 = (coil.size.Size) r0
            r6.size = r0
            r6.afterResolveSize(r0)
        L_0x0038:
            coil.memory.MemoryCache$Key r3 = new coil.memory.MemoryCache$Key
            r3.<init>((java.lang.String) r2, (java.util.List<? extends coil.transform.Transformation>) r5, (coil.size.Size) r0, (coil.request.Parameters) r4)
        L_0x003d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.computeCacheKey$coil_base_release$$forInline(coil.fetch.Fetcher, java.lang.Object, coil.request.Parameters, java.util.List, coil.RealImageLoader$LazySizeResolver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final /* synthetic */ Object loadData(Object obj, Fetcher<Object> fetcher, Request request, SizeResolver sizeResolver, Size size, Scale scale, EventListener eventListener, Continuation<? super DrawableResult> continuation) {
        CoroutineDispatcher dispatcher = request.getDispatcher();
        if (dispatcher == null) {
            dispatcher = getDefaults().getDispatcher();
        }
        InlineMarker.mark(0);
        Object withContext = BuildersKt.withContext(dispatcher, new RealImageLoader$loadData$2(this, request, sizeResolver, size, scale, eventListener, fetcher, obj, (Continuation) null), continuation);
        InlineMarker.mark(1);
        return withContext;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object applyTransformations$coil_base_release(kotlinx.coroutines.CoroutineScope r20, coil.fetch.DrawableResult r21, coil.request.Request r22, coil.size.Size r23, coil.decode.Options r24, coil.EventListener r25, kotlin.coroutines.Continuation<? super coil.fetch.DrawableResult> r26) {
        /*
            r19 = this;
            r0 = r26
            boolean r1 = r0 instanceof coil.RealImageLoader$applyTransformations$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            coil.RealImageLoader$applyTransformations$1 r1 = (coil.RealImageLoader$applyTransformations$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r19
            goto L_0x001f
        L_0x0018:
            coil.RealImageLoader$applyTransformations$1 r1 = new coil.RealImageLoader$applyTransformations$1
            r2 = r19
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 1
            if (r4 == 0) goto L_0x008a
            if (r4 != r5) goto L_0x0082
            java.lang.Object r4 = r1.L$13
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            java.lang.Object r4 = r1.L$12
            coil.transform.Transformation r4 = (coil.transform.Transformation) r4
            java.lang.Object r4 = r1.L$11
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            int r6 = r1.I$1
            int r7 = r1.I$0
            java.lang.Object r8 = r1.L$10
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            java.lang.Object r8 = r1.L$9
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r9 = r1.L$8
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r10 = r1.L$7
            kotlinx.coroutines.CoroutineScope r10 = (kotlinx.coroutines.CoroutineScope) r10
            java.lang.Object r11 = r1.L$6
            coil.EventListener r11 = (coil.EventListener) r11
            java.lang.Object r12 = r1.L$5
            coil.decode.Options r12 = (coil.decode.Options) r12
            java.lang.Object r13 = r1.L$4
            coil.size.Size r13 = (coil.size.Size) r13
            java.lang.Object r14 = r1.L$3
            coil.request.Request r14 = (coil.request.Request) r14
            java.lang.Object r15 = r1.L$2
            coil.fetch.DrawableResult r15 = (coil.fetch.DrawableResult) r15
            java.lang.Object r5 = r1.L$1
            kotlinx.coroutines.CoroutineScope r5 = (kotlinx.coroutines.CoroutineScope) r5
            java.lang.Object r2 = r1.L$0
            coil.RealImageLoader r2 = (coil.RealImageLoader) r2
            kotlin.ResultKt.throwOnFailure(r0)
            r16 = r5
            r5 = r12
            r12 = r6
            r6 = r11
            r11 = r4
            r4 = r13
            r17 = r8
            r8 = r1
            r1 = r10
            r10 = r3
            r3 = r14
            r14 = r17
            r18 = r9
            r9 = r2
            r2 = r15
            r15 = r18
            goto L_0x01d3
        L_0x0082:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x008a:
            kotlin.ResultKt.throwOnFailure(r0)
            java.util.List r0 = r22.getTransformations()
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L_0x009b
            r0 = r21
            goto L_0x0215
        L_0x009b:
            r2 = r22
            r4 = r25
            r4.transformStart(r2)
            android.graphics.drawable.Drawable r5 = r21.getDrawable()
            boolean r5 = r5 instanceof android.graphics.drawable.BitmapDrawable
            r6 = 0
            r7 = 4
            java.lang.String r8 = "RealImageLoader"
            if (r5 == 0) goto L_0x0112
            android.graphics.drawable.Drawable r5 = r21.getDrawable()
            android.graphics.drawable.BitmapDrawable r5 = (android.graphics.drawable.BitmapDrawable) r5
            android.graphics.Bitmap r5 = r5.getBitmap()
            android.graphics.Bitmap$Config[] r9 = coil.memory.RequestService.VALID_TRANSFORMATION_CONFIGS
            java.lang.String r10 = "resultBitmap"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r10)
            android.graphics.Bitmap$Config r10 = coil.util.Bitmaps.getSafeConfig(r5)
            boolean r9 = kotlin.collections.ArraysKt.contains((T[]) r9, r10)
            if (r9 == 0) goto L_0x00cb
            goto L_0x0166
        L_0x00cb:
            coil.util.Logger r9 = r19.getLogger$coil_base_release()
            if (r9 == 0) goto L_0x00f7
            int r10 = r9.getLevel()
            if (r10 > r7) goto L_0x00f7
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Converting bitmap with config "
            r10.append(r11)
            android.graphics.Bitmap$Config r5 = coil.util.Bitmaps.getSafeConfig(r5)
            r10.append(r5)
            java.lang.String r5 = " to apply transformations: "
            r10.append(r5)
            r10.append(r0)
            java.lang.String r5 = r10.toString()
            r9.log(r8, r7, r5, r6)
        L_0x00f7:
            coil.decode.DrawableDecoderService r10 = r19.drawableDecoder
            android.graphics.drawable.Drawable r11 = r21.getDrawable()
            android.graphics.Bitmap$Config r12 = r24.getConfig()
            coil.size.Scale r14 = r24.getScale()
            boolean r15 = r24.getAllowInexactSize()
            r13 = r23
            android.graphics.Bitmap r5 = r10.convert(r11, r12, r13, r14, r15)
            goto L_0x0166
        L_0x0112:
            coil.util.Logger r5 = r19.getLogger$coil_base_release()
            if (r5 == 0) goto L_0x014c
            int r9 = r5.getLevel()
            if (r9 > r7) goto L_0x014c
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Converting drawable of type "
            r9.append(r10)
            android.graphics.drawable.Drawable r10 = r21.getDrawable()
            java.lang.Class r10 = r10.getClass()
            java.lang.String r10 = r10.getCanonicalName()
            r9.append(r10)
            r10 = 32
            r9.append(r10)
            java.lang.String r10 = "to apply transformations: "
            r9.append(r10)
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            r5.log(r8, r7, r9, r6)
        L_0x014c:
            coil.decode.DrawableDecoderService r10 = r19.drawableDecoder
            android.graphics.drawable.Drawable r11 = r21.getDrawable()
            android.graphics.Bitmap$Config r12 = r24.getConfig()
            coil.size.Scale r14 = r24.getScale()
            boolean r15 = r24.getAllowInexactSize()
            r13 = r23
            android.graphics.Bitmap r5 = r10.convert(r11, r12, r13, r14, r15)
        L_0x0166:
            r6 = 0
            r7 = r0
            java.util.Collection r7 = (java.util.Collection) r7
            int r7 = r7.size()
            r8 = r19
            r14 = r0
            r15 = r14
            r9 = r3
            r6 = r4
            r10 = r5
            r11 = r10
            r12 = r7
            r13 = 0
            r0 = r20
            r4 = r23
            r5 = r24
            r7 = r1
            r3 = r2
            r1 = r0
            r2 = r21
        L_0x0183:
            if (r13 >= r12) goto L_0x01e3
            java.lang.Object r16 = r14.get(r13)
            r20 = r9
            r9 = r16
            coil.transform.Transformation r9 = (coil.transform.Transformation) r9
            r21 = r9
            coil.bitmappool.BitmapPool r9 = r8.bitmapPool
            r22 = r9
            java.lang.String r9 = "bitmap"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r9)
            r7.L$0 = r8
            r7.L$1 = r0
            r7.L$2 = r2
            r7.L$3 = r3
            r7.L$4 = r4
            r7.L$5 = r5
            r7.L$6 = r6
            r7.L$7 = r1
            r7.L$8 = r15
            r7.L$9 = r14
            r7.L$10 = r11
            r7.I$0 = r13
            r7.I$1 = r12
            r7.L$11 = r10
            r9 = r21
            r7.L$12 = r9
            r7.L$13 = r11
            r16 = r0
            r0 = 1
            r7.label = r0
            r0 = r22
            java.lang.Object r0 = r9.transform(r0, r11, r4, r7)
            r9 = r20
            if (r0 != r9) goto L_0x01ce
            return r9
        L_0x01ce:
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r13
        L_0x01d3:
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r1)
            r13 = 1
            int r7 = r7 + r13
            r13 = r7
            r7 = r8
            r8 = r9
            r9 = r10
            r10 = r11
            r11 = r0
            r0 = r16
            goto L_0x0183
        L_0x01e3:
            java.lang.String r0 = "transformedBitmap"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r0)
            android.content.Context r0 = r8.context
            android.content.res.Resources r0 = r0.getResources()
            java.lang.String r1 = "context.resources"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable
            r1.<init>(r0, r11)
            r0 = r1
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0
            r1 = 0
            r4 = 0
            r5 = 6
            r7 = 0
            r20 = r2
            r21 = r0
            r22 = r1
            r23 = r4
            r24 = r5
            r25 = r7
            coil.fetch.DrawableResult r0 = coil.fetch.DrawableResult.copy$default(r20, r21, r22, r23, r24, r25)
            r6.transformEnd(r3)
        L_0x0215:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.applyTransformations$coil_base_release(kotlinx.coroutines.CoroutineScope, coil.fetch.DrawableResult, coil.request.Request, coil.size.Size, coil.decode.Options, coil.EventListener, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final Object applyTransformations$coil_base_release$$forInline(CoroutineScope coroutineScope, DrawableResult drawableResult, Request request, Size size, Options options, EventListener eventListener, Continuation continuation) {
        Bitmap bitmap;
        Request request2 = request;
        EventListener eventListener2 = eventListener;
        List<Transformation> transformations = request.getTransformations();
        if (transformations.isEmpty()) {
            return drawableResult;
        }
        eventListener2.transformStart(request2);
        if (drawableResult.getDrawable() instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawableResult.getDrawable()).getBitmap();
            Bitmap.Config[] configArr = RequestService.VALID_TRANSFORMATION_CONFIGS;
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "resultBitmap");
            if (!ArraysKt.contains((T[]) configArr, Bitmaps.getSafeConfig(bitmap))) {
                Logger logger$coil_base_release = getLogger$coil_base_release();
                if (logger$coil_base_release != null && logger$coil_base_release.getLevel() <= 4) {
                    logger$coil_base_release.log(TAG, 4, "Converting bitmap with config " + Bitmaps.getSafeConfig(bitmap) + " to apply transformations: " + transformations, (Throwable) null);
                }
                bitmap = this.drawableDecoder.convert(drawableResult.getDrawable(), options.getConfig(), size, options.getScale(), options.getAllowInexactSize());
            }
        } else {
            Logger logger$coil_base_release2 = getLogger$coil_base_release();
            if (logger$coil_base_release2 != null && logger$coil_base_release2.getLevel() <= 4) {
                logger$coil_base_release2.log(TAG, 4, "Converting drawable of type " + drawableResult.getDrawable().getClass().getCanonicalName() + ' ' + "to apply transformations: " + transformations, (Throwable) null);
            }
            bitmap = this.drawableDecoder.convert(drawableResult.getDrawable(), options.getConfig(), size, options.getScale(), options.getAllowInexactSize());
        }
        int size2 = transformations.size();
        for (int i = 0; i < size2; i++) {
            BitmapPool access$getBitmapPool$p = this.bitmapPool;
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
            InlineMarker.mark(0);
            Object transform = transformations.get(i).transform(access$getBitmapPool$p, bitmap, size, continuation);
            InlineMarker.mark(1);
            bitmap = (Bitmap) transform;
            CoroutineScopeKt.ensureActive(coroutineScope);
        }
        Intrinsics.checkExpressionValueIsNotNull(bitmap, "transformedBitmap");
        Resources resources = this.context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        DrawableResult copy$default = DrawableResult.copy$default(drawableResult, new BitmapDrawable(resources, bitmap), false, (DataSource) null, 6, (Object) null);
        eventListener2.transformEnd(request2);
        return copy$default;
    }

    public void invalidate(String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        MemoryCache.Key key = new MemoryCache.Key(str, (Parameters) null, 2, (DefaultConstructorMarker) null);
        this.memoryCache.invalidate(key);
        this.weakMemoryCache.invalidate(key);
    }

    public final void onTrimMemory(int i) {
        this.memoryCache.trimMemory(i);
        this.weakMemoryCache.trimMemory(i);
        this.bitmapPool.trimMemory(i);
    }

    public void clearMemory() {
        this.memoryCache.clearMemory();
        this.weakMemoryCache.clearMemory();
        this.bitmapPool.clear();
    }

    public void shutdown() {
        if (!this.isShutdown) {
            this.isShutdown = true;
            CoroutineScopeKt.cancel$default(this.loaderScope, (CancellationException) null, 1, (Object) null);
            this.systemCallbacks.shutdown();
            clearMemory();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0013\u001a\u00020\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u001d\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015HHø\u0001\u0000¢\u0006\u0002\u0010\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lcoil/RealImageLoader$LazySizeResolver;", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "sizeResolver", "Lcoil/size/SizeResolver;", "targetDelegate", "Lcoil/memory/TargetDelegate;", "request", "Lcoil/request/Request;", "defaults", "Lcoil/DefaultRequestOptions;", "eventListener", "Lcoil/EventListener;", "(Lkotlinx/coroutines/CoroutineScope;Lcoil/size/SizeResolver;Lcoil/memory/TargetDelegate;Lcoil/request/Request;Lcoil/DefaultRequestOptions;Lcoil/EventListener;)V", "size", "Lcoil/size/Size;", "afterResolveSize", "", "beforeResolveSize", "cached", "Landroid/graphics/drawable/BitmapDrawable;", "(Landroid/graphics/drawable/BitmapDrawable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: RealImageLoader.kt */
    public static final class LazySizeResolver {
        private final DefaultRequestOptions defaults;
        private final EventListener eventListener;
        private final Request request;
        private final CoroutineScope scope;
        /* access modifiers changed from: private */
        public Size size;
        /* access modifiers changed from: private */
        public final SizeResolver sizeResolver;
        private final TargetDelegate targetDelegate;

        public LazySizeResolver(CoroutineScope coroutineScope, SizeResolver sizeResolver2, TargetDelegate targetDelegate2, Request request2, DefaultRequestOptions defaultRequestOptions, EventListener eventListener2) {
            Intrinsics.checkParameterIsNotNull(coroutineScope, "scope");
            Intrinsics.checkParameterIsNotNull(sizeResolver2, "sizeResolver");
            Intrinsics.checkParameterIsNotNull(targetDelegate2, "targetDelegate");
            Intrinsics.checkParameterIsNotNull(request2, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
            Intrinsics.checkParameterIsNotNull(defaultRequestOptions, RemoteConfigComponent.DEFAULTS_FILE_NAME);
            Intrinsics.checkParameterIsNotNull(eventListener2, "eventListener");
            this.scope = coroutineScope;
            this.sizeResolver = sizeResolver2;
            this.targetDelegate = targetDelegate2;
            this.request = request2;
            this.defaults = defaultRequestOptions;
            this.eventListener = eventListener2;
        }

        public static /* synthetic */ Object size$default(LazySizeResolver lazySizeResolver, BitmapDrawable bitmapDrawable, Continuation continuation, int i, Object obj) {
            if ((i & 1) != 0) {
                bitmapDrawable = null;
            }
            Size access$getSize$p = lazySizeResolver.size;
            if (access$getSize$p != null) {
                return access$getSize$p;
            }
            lazySizeResolver.beforeResolveSize(bitmapDrawable);
            SizeResolver access$getSizeResolver$p = lazySizeResolver.sizeResolver;
            InlineMarker.mark(0);
            Object size2 = access$getSizeResolver$p.size(continuation);
            InlineMarker.mark(1);
            Size size3 = (Size) size2;
            lazySizeResolver.size = size3;
            lazySizeResolver.afterResolveSize(size3);
            return size3;
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object size(android.graphics.drawable.BitmapDrawable r5, kotlin.coroutines.Continuation<? super coil.size.Size> r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof coil.RealImageLoader$LazySizeResolver$size$1
                if (r0 == 0) goto L_0x0014
                r0 = r6
                coil.RealImageLoader$LazySizeResolver$size$1 r0 = (coil.RealImageLoader$LazySizeResolver$size$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r6 = r0.label
                int r6 = r6 - r2
                r0.label = r6
                goto L_0x0019
            L_0x0014:
                coil.RealImageLoader$LazySizeResolver$size$1 r0 = new coil.RealImageLoader$LazySizeResolver$size$1
                r0.<init>(r4, r6)
            L_0x0019:
                java.lang.Object r6 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x003a
                if (r2 != r3) goto L_0x0032
                java.lang.Object r5 = r0.L$1
                android.graphics.drawable.BitmapDrawable r5 = (android.graphics.drawable.BitmapDrawable) r5
                java.lang.Object r5 = r0.L$0
                coil.RealImageLoader$LazySizeResolver r5 = (coil.RealImageLoader.LazySizeResolver) r5
                kotlin.ResultKt.throwOnFailure(r6)
                goto L_0x0059
            L_0x0032:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L_0x003a:
                kotlin.ResultKt.throwOnFailure(r6)
                coil.size.Size r6 = r4.size
                if (r6 == 0) goto L_0x0044
                return r6
            L_0x0044:
                r4.beforeResolveSize(r5)
                coil.size.SizeResolver r6 = r4.sizeResolver
                r0.L$0 = r4
                r0.L$1 = r5
                r0.label = r3
                java.lang.Object r6 = r6.size(r0)
                if (r6 != r1) goto L_0x0058
                return r1
            L_0x0058:
                r5 = r4
            L_0x0059:
                coil.size.Size r6 = (coil.size.Size) r6
                r5.size = r6
                r5.afterResolveSize(r6)
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: coil.RealImageLoader.LazySizeResolver.size(android.graphics.drawable.BitmapDrawable, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private final Object size$$forInline(BitmapDrawable bitmapDrawable, Continuation continuation) {
            Size access$getSize$p = this.size;
            if (access$getSize$p != null) {
                return access$getSize$p;
            }
            beforeResolveSize(bitmapDrawable);
            SizeResolver access$getSizeResolver$p = this.sizeResolver;
            InlineMarker.mark(0);
            Object size2 = access$getSizeResolver$p.size(continuation);
            InlineMarker.mark(1);
            Size size3 = (Size) size2;
            this.size = size3;
            afterResolveSize(size3);
            return size3;
        }

        /* access modifiers changed from: private */
        public final void beforeResolveSize(BitmapDrawable bitmapDrawable) {
            Drawable drawable;
            TargetDelegate targetDelegate2 = this.targetDelegate;
            if (bitmapDrawable != null) {
                drawable = bitmapDrawable;
            } else {
                Request request2 = this.request;
                drawable = (!(request2 instanceof LoadRequest) || ((LoadRequest) request2).getPlaceholderDrawable$coil_base_release() == null) ? this.defaults.getPlaceholder() : request2.getPlaceholder();
            }
            targetDelegate2.start(bitmapDrawable, drawable);
            this.eventListener.onStart(this.request);
            Request.Listener listener = this.request.getListener();
            if (listener != null) {
                listener.onStart(this.request);
            }
            this.eventListener.resolveSizeStart(this.request);
        }

        /* access modifiers changed from: private */
        public final void afterResolveSize(Size size2) {
            this.eventListener.resolveSizeEnd(this.request, size2);
            CoroutineScopeKt.ensureActive(this.scope);
        }
    }
}
