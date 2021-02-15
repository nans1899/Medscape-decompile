package coil.memory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Lifecycle;
import coil.DefaultRequestOptions;
import coil.decode.Options;
import coil.lifecycle.GlobalLifecycle;
import coil.lifecycle.LifecycleCoroutineDispatcher;
import coil.request.CachePolicy;
import coil.request.ErrorResult;
import coil.request.GetRequest;
import coil.request.LoadRequest;
import coil.request.NullRequestDataException;
import coil.request.Parameters;
import coil.request.Request;
import coil.size.DisplaySizeResolver;
import coil.size.Precision;
import coil.size.Scale;
import coil.size.Size;
import coil.size.SizeResolver;
import coil.size.ViewSizeResolver;
import coil.target.Target;
import coil.target.ViewTarget;
import coil.util.Bitmaps;
import coil.util.Contexts;
import coil.util.Extensions;
import coil.util.Logger;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Headers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 '2\u00020\u0001:\u0002'(B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\nJ\u0016\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0003J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\fH\u0007J0\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\nH\u0007J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u0004\u0018\u00010%*\u00020&H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcoil/memory/RequestService;", "", "defaults", "Lcoil/DefaultRequestOptions;", "logger", "Lcoil/util/Logger;", "(Lcoil/DefaultRequestOptions;Lcoil/util/Logger;)V", "hardwareBitmapService", "Lcoil/memory/HardwareBitmapService;", "allowInexactSize", "", "request", "Lcoil/request/Request;", "sizeResolver", "Lcoil/size/SizeResolver;", "errorResult", "Lcoil/request/ErrorResult;", "throwable", "", "allowFake", "isConfigValidForHardware", "requestedConfig", "Landroid/graphics/Bitmap$Config;", "isConfigValidForHardwareAllocation", "size", "Lcoil/size/Size;", "isConfigValidForTransformations", "lifecycleInfo", "Lcoil/memory/RequestService$LifecycleInfo;", "options", "Lcoil/decode/Options;", "scale", "Lcoil/size/Scale;", "isOnline", "context", "Landroid/content/Context;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "Lcoil/request/LoadRequest;", "Companion", "LifecycleInfo", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestService.kt */
public final class RequestService {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final ErrorResult FAKE_ERROR_RESULT = new ErrorResult((Drawable) null, new Exception());
    public static final Bitmap.Config[] VALID_TRANSFORMATION_CONFIGS = (Build.VERSION.SDK_INT >= 26 ? new Bitmap.Config[]{Bitmap.Config.ARGB_8888, Bitmap.Config.RGBA_F16} : new Bitmap.Config[]{Bitmap.Config.ARGB_8888});
    private final DefaultRequestOptions defaults;
    private final HardwareBitmapService hardwareBitmapService = HardwareBitmapService.Companion.invoke();
    private final Logger logger;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Precision.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Precision.EXACT.ordinal()] = 1;
            $EnumSwitchMapping$0[Precision.INEXACT.ordinal()] = 2;
            $EnumSwitchMapping$0[Precision.AUTOMATIC.ordinal()] = 3;
        }
    }

    public RequestService(DefaultRequestOptions defaultRequestOptions, Logger logger2) {
        Intrinsics.checkParameterIsNotNull(defaultRequestOptions, RemoteConfigComponent.DEFAULTS_FILE_NAME);
        this.defaults = defaultRequestOptions;
        this.logger = logger2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0000X\u0004¢\u0006\u0004\n\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcoil/memory/RequestService$Companion;", "", "()V", "FAKE_ERROR_RESULT", "Lcoil/request/ErrorResult;", "VALID_TRANSFORMATION_CONFIGS", "", "Landroid/graphics/Bitmap$Config;", "[Landroid/graphics/Bitmap$Config;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: RequestService.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final ErrorResult errorResult(Request request, Throwable th, boolean z) {
        Drawable drawable;
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(th, "throwable");
        if ((request instanceof GetRequest) && z) {
            return FAKE_ERROR_RESULT;
        }
        if (th instanceof NullRequestDataException) {
            drawable = (!(request instanceof LoadRequest) || request.getFallbackDrawable$coil_base_release() == null) ? this.defaults.getFallback() : request.getFallback();
        } else {
            drawable = (!(request instanceof LoadRequest) || request.getErrorDrawable$coil_base_release() == null) ? this.defaults.getError() : request.getError();
        }
        return new ErrorResult(drawable, th);
    }

    public final LifecycleInfo lifecycleInfo(Request request) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        if (request instanceof GetRequest) {
            return LifecycleInfo.Companion.getGLOBAL();
        }
        if (request instanceof LoadRequest) {
            Lifecycle lifecycle = getLifecycle((LoadRequest) request);
            if (lifecycle != null) {
                return new LifecycleInfo(lifecycle, LifecycleCoroutineDispatcher.Companion.createUnlessStarted(Dispatchers.getMain().getImmediate(), lifecycle));
            }
            return LifecycleInfo.Companion.getGLOBAL();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final SizeResolver sizeResolver(Request request, Context context) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(context, "context");
        SizeResolver sizeResolver = request.getSizeResolver();
        Target target = request.getTarget();
        if (sizeResolver != null) {
            return sizeResolver;
        }
        if (target instanceof ViewTarget) {
            return ViewSizeResolver.Companion.create$default(ViewSizeResolver.Companion, ((ViewTarget) target).getView(), false, 2, (Object) null);
        }
        return new DisplaySizeResolver(context);
    }

    public final Scale scale(Request request, SizeResolver sizeResolver) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(sizeResolver, "sizeResolver");
        Scale scale = request.getScale();
        if (scale != null) {
            return scale;
        }
        if (sizeResolver instanceof ViewSizeResolver) {
            View view = ((ViewSizeResolver) sizeResolver).getView();
            if (view instanceof ImageView) {
                return Extensions.getScale((ImageView) view);
            }
        }
        Target target = request.getTarget();
        if (target instanceof ViewTarget) {
            View view2 = ((ViewTarget) target).getView();
            if (view2 instanceof ImageView) {
                return Extensions.getScale((ImageView) view2);
            }
        }
        return Scale.FILL;
    }

    public final boolean allowInexactSize(Request request, SizeResolver sizeResolver) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(sizeResolver, "sizeResolver");
        Precision precision = request.getPrecision();
        if (precision == null) {
            precision = this.defaults.getPrecision();
        }
        int i = WhenMappings.$EnumSwitchMapping$0[precision.ordinal()];
        if (i == 1) {
            return false;
        }
        if (i == 2) {
            return true;
        }
        if (i == 3) {
            Target target = request.getTarget();
            if (target instanceof ViewTarget) {
                ViewTarget viewTarget = (ViewTarget) target;
                if ((viewTarget.getView() instanceof ImageView) && (sizeResolver instanceof ViewSizeResolver) && ((ViewSizeResolver) sizeResolver).getView() == viewTarget.getView()) {
                    return true;
                }
            }
            return request.getSizeResolver() == null && (sizeResolver instanceof DisplaySizeResolver);
        }
        throw new NoWhenBranchMatchedException();
    }

    public final Options options(Request request, SizeResolver sizeResolver, Size size, Scale scale, boolean z) {
        Bitmap.Config config;
        CachePolicy cachePolicy;
        Request request2 = request;
        Size size2 = size;
        Intrinsics.checkParameterIsNotNull(request2, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(sizeResolver, "sizeResolver");
        Intrinsics.checkParameterIsNotNull(size2, "size");
        Intrinsics.checkParameterIsNotNull(scale, "scale");
        if (isConfigValidForTransformations(request) && isConfigValidForHardwareAllocation(request2, size2)) {
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            Bitmap.Config bitmapConfig = request.getBitmapConfig();
            config = bitmapConfig != null ? bitmapConfig : defaultRequestOptions.getBitmapConfig();
        } else {
            config = Bitmap.Config.ARGB_8888;
        }
        CachePolicy networkCachePolicy = z ? request.getNetworkCachePolicy() : CachePolicy.DISABLED;
        Boolean allowRgb565 = request.getAllowRgb565();
        boolean z2 = (allowRgb565 != null ? allowRgb565.booleanValue() : this.defaults.getAllowRgb565()) && request.getTransformations().isEmpty() && config != Bitmap.Config.ALPHA_8;
        ColorSpace colorSpace = request.getColorSpace();
        boolean allowInexactSize = allowInexactSize(request, sizeResolver);
        Headers headers = request.getHeaders();
        Parameters parameters = request.getParameters();
        CachePolicy memoryCachePolicy = request.getMemoryCachePolicy();
        if (memoryCachePolicy == null) {
            memoryCachePolicy = this.defaults.getMemoryCachePolicy();
        }
        CachePolicy cachePolicy2 = memoryCachePolicy;
        CachePolicy diskCachePolicy = request.getDiskCachePolicy();
        if (diskCachePolicy == null) {
            diskCachePolicy = this.defaults.getDiskCachePolicy();
        }
        CachePolicy cachePolicy3 = diskCachePolicy;
        if (networkCachePolicy != null) {
            cachePolicy = networkCachePolicy;
        } else {
            cachePolicy = this.defaults.getNetworkCachePolicy();
        }
        return new Options(config, colorSpace, scale, allowInexactSize, z2, headers, parameters, cachePolicy2, cachePolicy3, cachePolicy);
    }

    public final boolean isConfigValidForHardware(Request request, Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(request, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        Intrinsics.checkParameterIsNotNull(config, "requestedConfig");
        if (!Bitmaps.isHardware(config)) {
            return true;
        }
        Boolean allowHardware = request.getAllowHardware();
        if (!(allowHardware != null ? allowHardware.booleanValue() : this.defaults.getAllowHardware())) {
            return false;
        }
        Target target = request.getTarget();
        if (target instanceof ViewTarget) {
            View view = ((ViewTarget) target).getView();
            if (ViewCompat.isAttachedToWindow(view) && !view.isHardwareAccelerated()) {
                return false;
            }
        }
        return true;
    }

    private final boolean isConfigValidForHardwareAllocation(Request request, Size size) {
        DefaultRequestOptions defaultRequestOptions = this.defaults;
        Bitmap.Config bitmapConfig = request.getBitmapConfig();
        if (bitmapConfig == null) {
            bitmapConfig = defaultRequestOptions.getBitmapConfig();
        }
        return isConfigValidForHardware(request, bitmapConfig) && this.hardwareBitmapService.allowHardware(size, this.logger);
    }

    private final boolean isConfigValidForTransformations(Request request) {
        if (!request.getTransformations().isEmpty()) {
            Bitmap.Config[] configArr = VALID_TRANSFORMATION_CONFIGS;
            DefaultRequestOptions defaultRequestOptions = this.defaults;
            Bitmap.Config bitmapConfig = request.getBitmapConfig();
            if (bitmapConfig == null) {
                bitmapConfig = defaultRequestOptions.getBitmapConfig();
            }
            return ArraysKt.contains((T[]) configArr, bitmapConfig);
        }
    }

    private final Lifecycle getLifecycle(LoadRequest loadRequest) {
        if (loadRequest.getLifecycle() != null) {
            return loadRequest.getLifecycle();
        }
        if (!(loadRequest.getTarget() instanceof ViewTarget)) {
            return Contexts.getLifecycle(loadRequest.getContext());
        }
        Context context = ((ViewTarget) loadRequest.getTarget()).getView().getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "target.view.context");
        return Contexts.getLifecycle(context);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/memory/RequestService$LifecycleInfo;", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Landroidx/lifecycle/Lifecycle;Lkotlinx/coroutines/CoroutineDispatcher;)V", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "getMainDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: RequestService.kt */
    public static final class LifecycleInfo {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        /* access modifiers changed from: private */
        public static final LifecycleInfo GLOBAL = new LifecycleInfo(GlobalLifecycle.INSTANCE, Dispatchers.getMain().getImmediate());
        private final Lifecycle lifecycle;
        private final CoroutineDispatcher mainDispatcher;

        public static /* synthetic */ LifecycleInfo copy$default(LifecycleInfo lifecycleInfo, Lifecycle lifecycle2, CoroutineDispatcher coroutineDispatcher, int i, Object obj) {
            if ((i & 1) != 0) {
                lifecycle2 = lifecycleInfo.lifecycle;
            }
            if ((i & 2) != 0) {
                coroutineDispatcher = lifecycleInfo.mainDispatcher;
            }
            return lifecycleInfo.copy(lifecycle2, coroutineDispatcher);
        }

        public final Lifecycle component1() {
            return this.lifecycle;
        }

        public final CoroutineDispatcher component2() {
            return this.mainDispatcher;
        }

        public final LifecycleInfo copy(Lifecycle lifecycle2, CoroutineDispatcher coroutineDispatcher) {
            Intrinsics.checkParameterIsNotNull(lifecycle2, "lifecycle");
            Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "mainDispatcher");
            return new LifecycleInfo(lifecycle2, coroutineDispatcher);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LifecycleInfo)) {
                return false;
            }
            LifecycleInfo lifecycleInfo = (LifecycleInfo) obj;
            return Intrinsics.areEqual((Object) this.lifecycle, (Object) lifecycleInfo.lifecycle) && Intrinsics.areEqual((Object) this.mainDispatcher, (Object) lifecycleInfo.mainDispatcher);
        }

        public int hashCode() {
            Lifecycle lifecycle2 = this.lifecycle;
            int i = 0;
            int hashCode = (lifecycle2 != null ? lifecycle2.hashCode() : 0) * 31;
            CoroutineDispatcher coroutineDispatcher = this.mainDispatcher;
            if (coroutineDispatcher != null) {
                i = coroutineDispatcher.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return "LifecycleInfo(lifecycle=" + this.lifecycle + ", mainDispatcher=" + this.mainDispatcher + ")";
        }

        public LifecycleInfo(Lifecycle lifecycle2, CoroutineDispatcher coroutineDispatcher) {
            Intrinsics.checkParameterIsNotNull(lifecycle2, "lifecycle");
            Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "mainDispatcher");
            this.lifecycle = lifecycle2;
            this.mainDispatcher = coroutineDispatcher;
        }

        public final Lifecycle getLifecycle() {
            return this.lifecycle;
        }

        public final CoroutineDispatcher getMainDispatcher() {
            return this.mainDispatcher;
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcoil/memory/RequestService$LifecycleInfo$Companion;", "", "()V", "GLOBAL", "Lcoil/memory/RequestService$LifecycleInfo;", "getGLOBAL", "()Lcoil/memory/RequestService$LifecycleInfo;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
        /* compiled from: RequestService.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final LifecycleInfo getGLOBAL() {
                return LifecycleInfo.GLOBAL;
            }
        }
    }
}
