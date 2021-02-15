package coil;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import coil.request.CachePolicy;
import coil.size.Precision;
import coil.transition.Transition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0012¢\u0006\u0002\u0010\u0015J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0012HÆ\u0003J\t\u0010+\u001a\u00020\u0012HÆ\u0003J\t\u0010,\u001a\u00020\u0012HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0007HÆ\u0003J\t\u0010/\u001a\u00020\tHÆ\u0003J\t\u00100\u001a\u00020\u000bHÆ\u0003J\t\u00101\u001a\u00020\u000bHÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0012HÆ\u0001J\u0013\u00106\u001a\u00020\u000b2\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00108\u001a\u000209HÖ\u0001J\t\u0010:\u001a\u00020;HÖ\u0001R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0011\u0010\u0014\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006<"}, d2 = {"Lcoil/DefaultRequestOptions;", "", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "transition", "Lcoil/transition/Transition;", "precision", "Lcoil/size/Precision;", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "allowHardware", "", "allowRgb565", "placeholder", "Landroid/graphics/drawable/Drawable;", "error", "fallback", "memoryCachePolicy", "Lcoil/request/CachePolicy;", "diskCachePolicy", "networkCachePolicy", "(Lkotlinx/coroutines/CoroutineDispatcher;Lcoil/transition/Transition;Lcoil/size/Precision;Landroid/graphics/Bitmap$Config;ZZLandroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Landroid/graphics/drawable/Drawable;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;)V", "getAllowHardware", "()Z", "getAllowRgb565", "getBitmapConfig", "()Landroid/graphics/Bitmap$Config;", "getDiskCachePolicy", "()Lcoil/request/CachePolicy;", "getDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "getError", "()Landroid/graphics/drawable/Drawable;", "getFallback", "getMemoryCachePolicy", "getNetworkCachePolicy", "getPlaceholder", "getPrecision", "()Lcoil/size/Precision;", "getTransition", "()Lcoil/transition/Transition;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DefaultRequestOptions.kt */
public final class DefaultRequestOptions {
    private final boolean allowHardware;
    private final boolean allowRgb565;
    private final Bitmap.Config bitmapConfig;
    private final CachePolicy diskCachePolicy;
    private final CoroutineDispatcher dispatcher;
    private final Drawable error;
    private final Drawable fallback;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Drawable placeholder;
    private final Precision precision;
    private final Transition transition;

    public DefaultRequestOptions() {
        this((CoroutineDispatcher) null, (Transition) null, (Precision) null, (Bitmap.Config) null, false, false, (Drawable) null, (Drawable) null, (Drawable) null, (CachePolicy) null, (CachePolicy) null, (CachePolicy) null, 4095, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ DefaultRequestOptions copy$default(DefaultRequestOptions defaultRequestOptions, CoroutineDispatcher coroutineDispatcher, Transition transition2, Precision precision2, Bitmap.Config config, boolean z, boolean z2, Drawable drawable, Drawable drawable2, Drawable drawable3, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, int i, Object obj) {
        DefaultRequestOptions defaultRequestOptions2 = defaultRequestOptions;
        int i2 = i;
        return defaultRequestOptions.copy((i2 & 1) != 0 ? defaultRequestOptions2.dispatcher : coroutineDispatcher, (i2 & 2) != 0 ? defaultRequestOptions2.transition : transition2, (i2 & 4) != 0 ? defaultRequestOptions2.precision : precision2, (i2 & 8) != 0 ? defaultRequestOptions2.bitmapConfig : config, (i2 & 16) != 0 ? defaultRequestOptions2.allowHardware : z, (i2 & 32) != 0 ? defaultRequestOptions2.allowRgb565 : z2, (i2 & 64) != 0 ? defaultRequestOptions2.placeholder : drawable, (i2 & 128) != 0 ? defaultRequestOptions2.error : drawable2, (i2 & 256) != 0 ? defaultRequestOptions2.fallback : drawable3, (i2 & 512) != 0 ? defaultRequestOptions2.memoryCachePolicy : cachePolicy, (i2 & 1024) != 0 ? defaultRequestOptions2.diskCachePolicy : cachePolicy2, (i2 & 2048) != 0 ? defaultRequestOptions2.networkCachePolicy : cachePolicy3);
    }

    public final CoroutineDispatcher component1() {
        return this.dispatcher;
    }

    public final CachePolicy component10() {
        return this.memoryCachePolicy;
    }

    public final CachePolicy component11() {
        return this.diskCachePolicy;
    }

    public final CachePolicy component12() {
        return this.networkCachePolicy;
    }

    public final Transition component2() {
        return this.transition;
    }

    public final Precision component3() {
        return this.precision;
    }

    public final Bitmap.Config component4() {
        return this.bitmapConfig;
    }

    public final boolean component5() {
        return this.allowHardware;
    }

    public final boolean component6() {
        return this.allowRgb565;
    }

    public final Drawable component7() {
        return this.placeholder;
    }

    public final Drawable component8() {
        return this.error;
    }

    public final Drawable component9() {
        return this.fallback;
    }

    public final DefaultRequestOptions copy(CoroutineDispatcher coroutineDispatcher, Transition transition2, Precision precision2, Bitmap.Config config, boolean z, boolean z2, Drawable drawable, Drawable drawable2, Drawable drawable3, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "dispatcher");
        Transition transition3 = transition2;
        Intrinsics.checkParameterIsNotNull(transition3, "transition");
        Precision precision3 = precision2;
        Intrinsics.checkParameterIsNotNull(precision3, "precision");
        Bitmap.Config config2 = config;
        Intrinsics.checkParameterIsNotNull(config2, "bitmapConfig");
        CachePolicy cachePolicy4 = cachePolicy;
        Intrinsics.checkParameterIsNotNull(cachePolicy4, "memoryCachePolicy");
        CachePolicy cachePolicy5 = cachePolicy2;
        Intrinsics.checkParameterIsNotNull(cachePolicy5, "diskCachePolicy");
        CachePolicy cachePolicy6 = cachePolicy3;
        Intrinsics.checkParameterIsNotNull(cachePolicy6, "networkCachePolicy");
        return new DefaultRequestOptions(coroutineDispatcher, transition3, precision3, config2, z, z2, drawable, drawable2, drawable3, cachePolicy4, cachePolicy5, cachePolicy6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DefaultRequestOptions)) {
            return false;
        }
        DefaultRequestOptions defaultRequestOptions = (DefaultRequestOptions) obj;
        return Intrinsics.areEqual((Object) this.dispatcher, (Object) defaultRequestOptions.dispatcher) && Intrinsics.areEqual((Object) this.transition, (Object) defaultRequestOptions.transition) && Intrinsics.areEqual((Object) this.precision, (Object) defaultRequestOptions.precision) && Intrinsics.areEqual((Object) this.bitmapConfig, (Object) defaultRequestOptions.bitmapConfig) && this.allowHardware == defaultRequestOptions.allowHardware && this.allowRgb565 == defaultRequestOptions.allowRgb565 && Intrinsics.areEqual((Object) this.placeholder, (Object) defaultRequestOptions.placeholder) && Intrinsics.areEqual((Object) this.error, (Object) defaultRequestOptions.error) && Intrinsics.areEqual((Object) this.fallback, (Object) defaultRequestOptions.fallback) && Intrinsics.areEqual((Object) this.memoryCachePolicy, (Object) defaultRequestOptions.memoryCachePolicy) && Intrinsics.areEqual((Object) this.diskCachePolicy, (Object) defaultRequestOptions.diskCachePolicy) && Intrinsics.areEqual((Object) this.networkCachePolicy, (Object) defaultRequestOptions.networkCachePolicy);
    }

    public int hashCode() {
        CoroutineDispatcher coroutineDispatcher = this.dispatcher;
        int i = 0;
        int hashCode = (coroutineDispatcher != null ? coroutineDispatcher.hashCode() : 0) * 31;
        Transition transition2 = this.transition;
        int hashCode2 = (hashCode + (transition2 != null ? transition2.hashCode() : 0)) * 31;
        Precision precision2 = this.precision;
        int hashCode3 = (hashCode2 + (precision2 != null ? precision2.hashCode() : 0)) * 31;
        Bitmap.Config config = this.bitmapConfig;
        int hashCode4 = (hashCode3 + (config != null ? config.hashCode() : 0)) * 31;
        boolean z = this.allowHardware;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i2 = (hashCode4 + (z ? 1 : 0)) * 31;
        boolean z3 = this.allowRgb565;
        if (!z3) {
            z2 = z3;
        }
        int i3 = (i2 + (z2 ? 1 : 0)) * 31;
        Drawable drawable = this.placeholder;
        int hashCode5 = (i3 + (drawable != null ? drawable.hashCode() : 0)) * 31;
        Drawable drawable2 = this.error;
        int hashCode6 = (hashCode5 + (drawable2 != null ? drawable2.hashCode() : 0)) * 31;
        Drawable drawable3 = this.fallback;
        int hashCode7 = (hashCode6 + (drawable3 != null ? drawable3.hashCode() : 0)) * 31;
        CachePolicy cachePolicy = this.memoryCachePolicy;
        int hashCode8 = (hashCode7 + (cachePolicy != null ? cachePolicy.hashCode() : 0)) * 31;
        CachePolicy cachePolicy2 = this.diskCachePolicy;
        int hashCode9 = (hashCode8 + (cachePolicy2 != null ? cachePolicy2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy3 = this.networkCachePolicy;
        if (cachePolicy3 != null) {
            i = cachePolicy3.hashCode();
        }
        return hashCode9 + i;
    }

    public String toString() {
        return "DefaultRequestOptions(dispatcher=" + this.dispatcher + ", transition=" + this.transition + ", precision=" + this.precision + ", bitmapConfig=" + this.bitmapConfig + ", allowHardware=" + this.allowHardware + ", allowRgb565=" + this.allowRgb565 + ", placeholder=" + this.placeholder + ", error=" + this.error + ", fallback=" + this.fallback + ", memoryCachePolicy=" + this.memoryCachePolicy + ", diskCachePolicy=" + this.diskCachePolicy + ", networkCachePolicy=" + this.networkCachePolicy + ")";
    }

    public DefaultRequestOptions(CoroutineDispatcher coroutineDispatcher, Transition transition2, Precision precision2, Bitmap.Config config, boolean z, boolean z2, Drawable drawable, Drawable drawable2, Drawable drawable3, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        Intrinsics.checkParameterIsNotNull(coroutineDispatcher, "dispatcher");
        Intrinsics.checkParameterIsNotNull(transition2, "transition");
        Intrinsics.checkParameterIsNotNull(precision2, "precision");
        Intrinsics.checkParameterIsNotNull(config, "bitmapConfig");
        Intrinsics.checkParameterIsNotNull(cachePolicy, "memoryCachePolicy");
        Intrinsics.checkParameterIsNotNull(cachePolicy2, "diskCachePolicy");
        Intrinsics.checkParameterIsNotNull(cachePolicy3, "networkCachePolicy");
        this.dispatcher = coroutineDispatcher;
        this.transition = transition2;
        this.precision = precision2;
        this.bitmapConfig = config;
        this.allowHardware = z;
        this.allowRgb565 = z2;
        this.placeholder = drawable;
        this.error = drawable2;
        this.fallback = drawable3;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ DefaultRequestOptions(kotlinx.coroutines.CoroutineDispatcher r13, coil.transition.Transition r14, coil.size.Precision r15, android.graphics.Bitmap.Config r16, boolean r17, boolean r18, android.graphics.drawable.Drawable r19, android.graphics.drawable.Drawable r20, android.graphics.drawable.Drawable r21, coil.request.CachePolicy r22, coil.request.CachePolicy r23, coil.request.CachePolicy r24, int r25, kotlin.jvm.internal.DefaultConstructorMarker r26) {
        /*
            r12 = this;
            r0 = r25
            r1 = r0 & 1
            if (r1 == 0) goto L_0x000b
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            goto L_0x000c
        L_0x000b:
            r1 = r13
        L_0x000c:
            r2 = r0 & 2
            if (r2 == 0) goto L_0x0013
            coil.transition.Transition r2 = coil.transition.Transition.NONE
            goto L_0x0014
        L_0x0013:
            r2 = r14
        L_0x0014:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x001b
            coil.size.Precision r3 = coil.size.Precision.AUTOMATIC
            goto L_0x001c
        L_0x001b:
            r3 = r15
        L_0x001c:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x0027
            coil.util.Utils r4 = coil.util.Utils.INSTANCE
            android.graphics.Bitmap$Config r4 = r4.getDefaultBitmapConfig()
            goto L_0x0029
        L_0x0027:
            r4 = r16
        L_0x0029:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x002f
            r5 = 1
            goto L_0x0031
        L_0x002f:
            r5 = r17
        L_0x0031:
            r6 = r0 & 32
            if (r6 == 0) goto L_0x0037
            r6 = 0
            goto L_0x0039
        L_0x0037:
            r6 = r18
        L_0x0039:
            r7 = r0 & 64
            r8 = 0
            if (r7 == 0) goto L_0x0042
            r7 = r8
            android.graphics.drawable.Drawable r7 = (android.graphics.drawable.Drawable) r7
            goto L_0x0044
        L_0x0042:
            r7 = r19
        L_0x0044:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x004c
            r9 = r8
            android.graphics.drawable.Drawable r9 = (android.graphics.drawable.Drawable) r9
            goto L_0x004e
        L_0x004c:
            r9 = r20
        L_0x004e:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x0055
            android.graphics.drawable.Drawable r8 = (android.graphics.drawable.Drawable) r8
            goto L_0x0057
        L_0x0055:
            r8 = r21
        L_0x0057:
            r10 = r0 & 512(0x200, float:7.175E-43)
            if (r10 == 0) goto L_0x005e
            coil.request.CachePolicy r10 = coil.request.CachePolicy.ENABLED
            goto L_0x0060
        L_0x005e:
            r10 = r22
        L_0x0060:
            r11 = r0 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x0067
            coil.request.CachePolicy r11 = coil.request.CachePolicy.ENABLED
            goto L_0x0069
        L_0x0067:
            r11 = r23
        L_0x0069:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x0070
            coil.request.CachePolicy r0 = coil.request.CachePolicy.ENABLED
            goto L_0x0072
        L_0x0070:
            r0 = r24
        L_0x0072:
            r13 = r12
            r14 = r1
            r15 = r2
            r16 = r3
            r17 = r4
            r18 = r5
            r19 = r6
            r20 = r7
            r21 = r9
            r22 = r8
            r23 = r10
            r24 = r11
            r25 = r0
            r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.DefaultRequestOptions.<init>(kotlinx.coroutines.CoroutineDispatcher, coil.transition.Transition, coil.size.Precision, android.graphics.Bitmap$Config, boolean, boolean, android.graphics.drawable.Drawable, android.graphics.drawable.Drawable, android.graphics.drawable.Drawable, coil.request.CachePolicy, coil.request.CachePolicy, coil.request.CachePolicy, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final CoroutineDispatcher getDispatcher() {
        return this.dispatcher;
    }

    public final Transition getTransition() {
        return this.transition;
    }

    public final Precision getPrecision() {
        return this.precision;
    }

    public final Bitmap.Config getBitmapConfig() {
        return this.bitmapConfig;
    }

    public final boolean getAllowHardware() {
        return this.allowHardware;
    }

    public final boolean getAllowRgb565() {
        return this.allowRgb565;
    }

    public final Drawable getPlaceholder() {
        return this.placeholder;
    }

    public final Drawable getError() {
        return this.error;
    }

    public final Drawable getFallback() {
        return this.fallback;
    }

    public final CachePolicy getMemoryCachePolicy() {
        return this.memoryCachePolicy;
    }

    public final CachePolicy getDiskCachePolicy() {
        return this.diskCachePolicy;
    }

    public final CachePolicy getNetworkCachePolicy() {
        return this.networkCachePolicy;
    }
}
