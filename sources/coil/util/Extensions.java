package coil.util;

import android.app.ActivityManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.StatFs;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import coil.base.R;
import coil.decode.DataSource;
import coil.memory.MemoryCache;
import coil.memory.ViewTargetRequestManager;
import coil.request.Parameters;
import coil.size.Scale;
import java.io.Closeable;
import java.util.List;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Æ\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\b\u0010;\u001a\u00020$H\u0000\u001a\u0016\u0010<\u001a\u00020=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020=0?H\u0000\u001a,\u0010@\u001a\u0004\u0018\u0001HA\"\u0004\b\u0000\u0010A2\u0006\u0010B\u001a\u00020$2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002HA0?H\b¢\u0006\u0002\u0010D\u001a\u0015\u0010E\u001a\u00020F*\u00020GHHø\u0001\u0000¢\u0006\u0002\u0010H\u001a\f\u0010I\u001a\u00020J*\u00020KH\u0000\u001a\u0018\u0010L\u001a\u0004\u0018\u00010\u0012*\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u0012H\u0000\u001a\u0018\u0010O\u001a\u0004\u0018\u00010P*\u00020Q2\b\u0010R\u001a\u0004\u0018\u00010SH\u0000\u001a\u000e\u0010T\u001a\u00020U*\u0004\u0018\u00010UH\u0000\u001a\u0016\u0010T\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u0004\u0018\u00010\u0005H\u0000\u001a&\u0010V\u001a\u00020J*\u00020Q2\b\u0010R\u001a\u0004\u0018\u00010S2\u0006\u0010W\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020$H\u0000\u001a1\u0010Y\u001a\u0002HA\"\u0004\b\u0000\u0010A*\u00020 2\u0017\u0010Z\u001a\u0013\u0012\u0004\u0012\u0002HA\u0012\u0004\u0012\u00020J0[¢\u0006\u0002\b\\H\b¢\u0006\u0002\u0010]\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u0007\u001a\u00020\b*\u00020\t8À\u0002X\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r\"\u001f\u0010\u000e\u001a\u00020\b*\u00020\t8À\u0002X\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u0010\u0010\r\"\u0018\u0010\u0011\u001a\u00020\u0012*\u00020\u00138@X\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\"\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u0012*\u00020\u00178@X\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019\"\u0018\u0010\u001a\u001a\u00020\u001b*\u00020\u001c8@X\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\"\u0019\u0010\u001f\u001a\u00020\u001b*\u00020 8À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u0019\u0010#\u001a\u00020$*\u00020%8À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b#\u0010&\"\u0019\u0010'\u001a\u00020$*\u00020(8À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b'\u0010)\"\u0018\u0010*\u001a\u00020$*\u00020\u001c8@X\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0018\u0010,\u001a\u00020\u001b*\u00020-8@X\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/\"\u0018\u00100\u001a\u000201*\u00020%8@X\u0004¢\u0006\u0006\u001a\u0004\b2\u00103\"\u0018\u00104\u001a\u000205*\u0002068@X\u0004¢\u0006\u0006\u001a\u0004\b7\u00108\"\u0018\u00109\u001a\u00020\u001b*\u00020\u001c8@X\u0004¢\u0006\u0006\u001a\u0004\b:\u0010\u001e\u0002\u0004\n\u0002\b\u0019¨\u0006^"}, d2 = {"EMPTY_DRAWABLE", "Landroid/graphics/drawable/ColorDrawable;", "getEMPTY_DRAWABLE", "()Landroid/graphics/drawable/ColorDrawable;", "EMPTY_HEADERS", "Lokhttp3/Headers;", "kotlin.jvm.PlatformType", "blockCountCompat", "", "Landroid/os/StatFs;", "blockCountCompat$annotations", "(Landroid/os/StatFs;)V", "getBlockCountCompat", "(Landroid/os/StatFs;)J", "blockSizeCompat", "blockSizeCompat$annotations", "getBlockSizeCompat", "emoji", "", "Lcoil/decode/DataSource;", "getEmoji", "(Lcoil/decode/DataSource;)Ljava/lang/String;", "firstPathSegment", "Landroid/net/Uri;", "getFirstPathSegment", "(Landroid/net/Uri;)Ljava/lang/String;", "height", "", "Landroid/graphics/drawable/Drawable;", "getHeight", "(Landroid/graphics/drawable/Drawable;)I", "identityHashCode", "", "getIdentityHashCode", "(Ljava/lang/Object;)I", "isAttachedToWindowCompat", "", "Landroid/view/View;", "(Landroid/view/View;)Z", "isLowRamDeviceCompat", "Landroid/app/ActivityManager;", "(Landroid/app/ActivityManager;)Z", "isVector", "(Landroid/graphics/drawable/Drawable;)Z", "nightMode", "Landroid/content/res/Configuration;", "getNightMode", "(Landroid/content/res/Configuration;)I", "requestManager", "Lcoil/memory/ViewTargetRequestManager;", "getRequestManager", "(Landroid/view/View;)Lcoil/memory/ViewTargetRequestManager;", "scale", "Lcoil/size/Scale;", "Landroid/widget/ImageView;", "getScale", "(Landroid/widget/ImageView;)Lcoil/size/Scale;", "width", "getWidth", "isMainThread", "lazyCallFactory", "Lokhttp3/Call$Factory;", "initializer", "Lkotlin/Function0;", "takeIf", "T", "take", "factory", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "await", "Lokhttp3/Response;", "Lokhttp3/Call;", "(Lokhttp3/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "closeQuietly", "", "Ljava/io/Closeable;", "getMimeTypeFromUrl", "Landroid/webkit/MimeTypeMap;", "url", "getValue", "Lcoil/memory/MemoryCache$Value;", "Lcoil/memory/MemoryCache;", "key", "Lcoil/memory/MemoryCache$Key;", "orEmpty", "Lcoil/request/Parameters;", "putValue", "value", "isSampled", "self", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* renamed from: coil.util.-Extensions  reason: invalid class name */
/* compiled from: Extensions.kt */
public final class Extensions {
    private static final ColorDrawable EMPTY_DRAWABLE = new ColorDrawable(0);
    private static final Headers EMPTY_HEADERS = new Headers.Builder().build();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* renamed from: coil.util.-Extensions$WhenMappings */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DataSource.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[DataSource.MEMORY_CACHE.ordinal()] = 1;
            $EnumSwitchMapping$0[DataSource.MEMORY.ordinal()] = 2;
            $EnumSwitchMapping$0[DataSource.DISK.ordinal()] = 3;
            $EnumSwitchMapping$0[DataSource.NETWORK.ordinal()] = 4;
            int[] iArr2 = new int[ImageView.ScaleType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[ImageView.ScaleType.FIT_START.ordinal()] = 1;
            $EnumSwitchMapping$1[ImageView.ScaleType.FIT_CENTER.ordinal()] = 2;
            $EnumSwitchMapping$1[ImageView.ScaleType.FIT_END.ordinal()] = 3;
            $EnumSwitchMapping$1[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 4;
        }
    }

    public static /* synthetic */ void blockCountCompat$annotations(StatFs statFs) {
    }

    public static /* synthetic */ void blockSizeCompat$annotations(StatFs statFs) {
    }

    public static final boolean isLowRamDeviceCompat(ActivityManager activityManager) {
        Intrinsics.checkParameterIsNotNull(activityManager, "$this$isLowRamDeviceCompat");
        return Build.VERSION.SDK_INT < 19 || activityManager.isLowRamDevice();
    }

    public static final long getBlockCountCompat(StatFs statFs) {
        Intrinsics.checkParameterIsNotNull(statFs, "$this$blockCountCompat");
        return Build.VERSION.SDK_INT > 18 ? statFs.getBlockCountLong() : (long) statFs.getBlockCount();
    }

    public static final long getBlockSizeCompat(StatFs statFs) {
        Intrinsics.checkParameterIsNotNull(statFs, "$this$blockSizeCompat");
        return Build.VERSION.SDK_INT > 18 ? statFs.getBlockSizeLong() : (long) statFs.getBlockSize();
    }

    public static final MemoryCache.Value getValue(MemoryCache memoryCache, MemoryCache.Key key) {
        Intrinsics.checkParameterIsNotNull(memoryCache, "$this$getValue");
        if (key != null) {
            return memoryCache.get(key);
        }
        return null;
    }

    public static final void putValue(MemoryCache memoryCache, MemoryCache.Key key, Drawable drawable, boolean z) {
        Intrinsics.checkParameterIsNotNull(memoryCache, "$this$putValue");
        Intrinsics.checkParameterIsNotNull(drawable, "value");
        if (key != null) {
            Bitmap bitmap = null;
            if (!(drawable instanceof BitmapDrawable)) {
                drawable = null;
            }
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable != null) {
                bitmap = bitmapDrawable.getBitmap();
            }
            if (bitmap != null) {
                memoryCache.set(key, bitmap, z);
            }
        }
    }

    public static final <T> T takeIf(boolean z, Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "factory");
        if (z) {
            return function0.invoke();
        }
        return null;
    }

    public static final ViewTargetRequestManager getRequestManager(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$requestManager");
        Object tag = view.getTag(R.id.coil_request_manager);
        if (!(tag instanceof ViewTargetRequestManager)) {
            tag = null;
        }
        ViewTargetRequestManager viewTargetRequestManager = (ViewTargetRequestManager) tag;
        if (viewTargetRequestManager != null) {
            return viewTargetRequestManager;
        }
        ViewTargetRequestManager viewTargetRequestManager2 = new ViewTargetRequestManager();
        view.addOnAttachStateChangeListener(viewTargetRequestManager2);
        view.setTag(R.id.coil_request_manager, viewTargetRequestManager2);
        return viewTargetRequestManager2;
    }

    public static final boolean isAttachedToWindowCompat(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$this$isAttachedToWindowCompat");
        return ViewCompat.isAttachedToWindow(view);
    }

    public static final String getEmoji(DataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(dataSource, "$this$emoji");
        int i = WhenMappings.$EnumSwitchMapping$0[dataSource.ordinal()];
        if (i == 1 || i == 2) {
            return Emoji.BRAIN;
        }
        if (i == 3) {
            return Emoji.FLOPPY;
        }
        if (i == 4) {
            return Emoji.CLOUD;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final int getWidth(Drawable drawable) {
        Bitmap bitmap;
        Intrinsics.checkParameterIsNotNull(drawable, "$this$width");
        BitmapDrawable bitmapDrawable = (BitmapDrawable) (!(drawable instanceof BitmapDrawable) ? null : drawable);
        return (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null) ? drawable.getIntrinsicWidth() : bitmap.getWidth();
    }

    public static final int getHeight(Drawable drawable) {
        Bitmap bitmap;
        Intrinsics.checkParameterIsNotNull(drawable, "$this$height");
        BitmapDrawable bitmapDrawable = (BitmapDrawable) (!(drawable instanceof BitmapDrawable) ? null : drawable);
        return (bitmapDrawable == null || (bitmap = bitmapDrawable.getBitmap()) == null) ? drawable.getIntrinsicHeight() : bitmap.getHeight();
    }

    public static final boolean isVector(Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "$this$isVector");
        return (drawable instanceof VectorDrawableCompat) || (Build.VERSION.SDK_INT > 21 && (drawable instanceof VectorDrawable));
    }

    public static final void closeQuietly(Closeable closeable) {
        Intrinsics.checkParameterIsNotNull(closeable, "$this$closeQuietly");
        try {
            closeable.close();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception unused) {
        }
    }

    public static final Scale getScale(ImageView imageView) {
        int i;
        Intrinsics.checkParameterIsNotNull(imageView, "$this$scale");
        ImageView.ScaleType scaleType = imageView.getScaleType();
        if (scaleType != null && ((i = WhenMappings.$EnumSwitchMapping$1[scaleType.ordinal()]) == 1 || i == 2 || i == 3 || i == 4)) {
            return Scale.FIT;
        }
        return Scale.FILL;
    }

    public static final <T> T self(Object obj, Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$self");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        function1.invoke(obj);
        return obj;
    }

    public static final Call.Factory lazyCallFactory(Function0<? extends Call.Factory> function0) {
        Intrinsics.checkParameterIsNotNull(function0, "initializer");
        return new Extensions$lazyCallFactory$1(LazyKt.lazy(function0));
    }

    public static final String getMimeTypeFromUrl(MimeTypeMap mimeTypeMap, String str) {
        Intrinsics.checkParameterIsNotNull(mimeTypeMap, "$this$getMimeTypeFromUrl");
        CharSequence charSequence = str;
        if (charSequence == null || StringsKt.isBlank(charSequence)) {
            return null;
        }
        return mimeTypeMap.getMimeTypeFromExtension(StringsKt.substringAfterLast(StringsKt.substringAfterLast$default(StringsKt.substringBeforeLast$default(StringsKt.substringBeforeLast$default(str, '#', (String) null, 2, (Object) null), '?', (String) null, 2, (Object) null), '/', (String) null, 2, (Object) null), '.', ""));
    }

    public static final String getFirstPathSegment(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "$this$firstPathSegment");
        List<String> pathSegments = uri.getPathSegments();
        Intrinsics.checkExpressionValueIsNotNull(pathSegments, "pathSegments");
        return (String) CollectionsKt.firstOrNull(pathSegments);
    }

    public static final int getNightMode(Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "$this$nightMode");
        return configuration.uiMode & 48;
    }

    public static final ColorDrawable getEMPTY_DRAWABLE() {
        return EMPTY_DRAWABLE;
    }

    public static final Headers orEmpty(Headers headers) {
        return headers != null ? headers : EMPTY_HEADERS;
    }

    public static final Parameters orEmpty(Parameters parameters) {
        return parameters != null ? parameters : Parameters.EMPTY;
    }

    public static final boolean isMainThread() {
        return Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper());
    }

    public static final int getIdentityHashCode(Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "$this$identityHashCode");
        return System.identityHashCode(obj);
    }

    private static final Object await$$forInline(Call call, Continuation continuation) {
        InlineMarker.mark(0);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ContinuationCallback continuationCallback = new ContinuationCallback(call, cancellableContinuation);
        call.enqueue(continuationCallback);
        cancellableContinuation.invokeOnCancellation(continuationCallback);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }

    public static final Object await(Call call, Continuation<? super Response> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ContinuationCallback continuationCallback = new ContinuationCallback(call, cancellableContinuation);
        call.enqueue(continuationCallback);
        cancellableContinuation.invokeOnCancellation(continuationCallback);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
