package coil.decode;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import coil.request.CachePolicy;
import coil.request.Parameters;
import coil.size.Scale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.media.android.bidder.base.common.Constants;
import okhttp3.Headers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0010¢\u0006\u0002\u0010\u0013J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0010HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0007HÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\fHÆ\u0003J\t\u0010,\u001a\u00020\u000eHÆ\u0003J\t\u0010-\u001a\u00020\u0010HÆ\u0003J\t\u0010.\u001a\u00020\u0010HÆ\u0003Jo\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u0010HÆ\u0001J\u0013\u00100\u001a\u00020\t2\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\t\u00104\u001a\u000205HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00066"}, d2 = {"Lcoil/decode/Options;", "", "config", "Landroid/graphics/Bitmap$Config;", "colorSpace", "Landroid/graphics/ColorSpace;", "scale", "Lcoil/size/Scale;", "allowInexactSize", "", "allowRgb565", "headers", "Lokhttp3/Headers;", "parameters", "Lcoil/request/Parameters;", "memoryCachePolicy", "Lcoil/request/CachePolicy;", "diskCachePolicy", "networkCachePolicy", "(Landroid/graphics/Bitmap$Config;Landroid/graphics/ColorSpace;Lcoil/size/Scale;ZZLokhttp3/Headers;Lcoil/request/Parameters;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;Lcoil/request/CachePolicy;)V", "getAllowInexactSize", "()Z", "getAllowRgb565", "getColorSpace", "()Landroid/graphics/ColorSpace;", "getConfig", "()Landroid/graphics/Bitmap$Config;", "getDiskCachePolicy", "()Lcoil/request/CachePolicy;", "getHeaders", "()Lokhttp3/Headers;", "getMemoryCachePolicy", "getNetworkCachePolicy", "getParameters", "()Lcoil/request/Parameters;", "getScale", "()Lcoil/size/Scale;", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Options.kt */
public final class Options {
    private final boolean allowInexactSize;
    private final boolean allowRgb565;
    private final ColorSpace colorSpace;
    private final Bitmap.Config config;
    private final CachePolicy diskCachePolicy;
    private final Headers headers;
    private final CachePolicy memoryCachePolicy;
    private final CachePolicy networkCachePolicy;
    private final Parameters parameters;
    private final Scale scale;

    public static /* synthetic */ Options copy$default(Options options, Bitmap.Config config2, ColorSpace colorSpace2, Scale scale2, boolean z, boolean z2, Headers headers2, Parameters parameters2, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3, int i, Object obj) {
        Options options2 = options;
        int i2 = i;
        return options.copy((i2 & 1) != 0 ? options2.config : config2, (i2 & 2) != 0 ? options2.colorSpace : colorSpace2, (i2 & 4) != 0 ? options2.scale : scale2, (i2 & 8) != 0 ? options2.allowInexactSize : z, (i2 & 16) != 0 ? options2.allowRgb565 : z2, (i2 & 32) != 0 ? options2.headers : headers2, (i2 & 64) != 0 ? options2.parameters : parameters2, (i2 & 128) != 0 ? options2.memoryCachePolicy : cachePolicy, (i2 & 256) != 0 ? options2.diskCachePolicy : cachePolicy2, (i2 & 512) != 0 ? options2.networkCachePolicy : cachePolicy3);
    }

    public final Bitmap.Config component1() {
        return this.config;
    }

    public final CachePolicy component10() {
        return this.networkCachePolicy;
    }

    public final ColorSpace component2() {
        return this.colorSpace;
    }

    public final Scale component3() {
        return this.scale;
    }

    public final boolean component4() {
        return this.allowInexactSize;
    }

    public final boolean component5() {
        return this.allowRgb565;
    }

    public final Headers component6() {
        return this.headers;
    }

    public final Parameters component7() {
        return this.parameters;
    }

    public final CachePolicy component8() {
        return this.memoryCachePolicy;
    }

    public final CachePolicy component9() {
        return this.diskCachePolicy;
    }

    public final Options copy(Bitmap.Config config2, ColorSpace colorSpace2, Scale scale2, boolean z, boolean z2, Headers headers2, Parameters parameters2, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        Intrinsics.checkParameterIsNotNull(config2, Constants.CONFIG_FILE_NAME);
        Intrinsics.checkParameterIsNotNull(scale2, "scale");
        Headers headers3 = headers2;
        Intrinsics.checkParameterIsNotNull(headers3, "headers");
        Parameters parameters3 = parameters2;
        Intrinsics.checkParameterIsNotNull(parameters3, "parameters");
        CachePolicy cachePolicy4 = cachePolicy;
        Intrinsics.checkParameterIsNotNull(cachePolicy4, "memoryCachePolicy");
        CachePolicy cachePolicy5 = cachePolicy2;
        Intrinsics.checkParameterIsNotNull(cachePolicy5, "diskCachePolicy");
        CachePolicy cachePolicy6 = cachePolicy3;
        Intrinsics.checkParameterIsNotNull(cachePolicy6, "networkCachePolicy");
        return new Options(config2, colorSpace2, scale2, z, z2, headers3, parameters3, cachePolicy4, cachePolicy5, cachePolicy6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Options)) {
            return false;
        }
        Options options = (Options) obj;
        return Intrinsics.areEqual((Object) this.config, (Object) options.config) && Intrinsics.areEqual((Object) this.colorSpace, (Object) options.colorSpace) && Intrinsics.areEqual((Object) this.scale, (Object) options.scale) && this.allowInexactSize == options.allowInexactSize && this.allowRgb565 == options.allowRgb565 && Intrinsics.areEqual((Object) this.headers, (Object) options.headers) && Intrinsics.areEqual((Object) this.parameters, (Object) options.parameters) && Intrinsics.areEqual((Object) this.memoryCachePolicy, (Object) options.memoryCachePolicy) && Intrinsics.areEqual((Object) this.diskCachePolicy, (Object) options.diskCachePolicy) && Intrinsics.areEqual((Object) this.networkCachePolicy, (Object) options.networkCachePolicy);
    }

    public int hashCode() {
        Bitmap.Config config2 = this.config;
        int i = 0;
        int hashCode = (config2 != null ? config2.hashCode() : 0) * 31;
        ColorSpace colorSpace2 = this.colorSpace;
        int hashCode2 = (hashCode + (colorSpace2 != null ? colorSpace2.hashCode() : 0)) * 31;
        Scale scale2 = this.scale;
        int hashCode3 = (hashCode2 + (scale2 != null ? scale2.hashCode() : 0)) * 31;
        boolean z = this.allowInexactSize;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i2 = (hashCode3 + (z ? 1 : 0)) * 31;
        boolean z3 = this.allowRgb565;
        if (!z3) {
            z2 = z3;
        }
        int i3 = (i2 + (z2 ? 1 : 0)) * 31;
        Headers headers2 = this.headers;
        int hashCode4 = (i3 + (headers2 != null ? headers2.hashCode() : 0)) * 31;
        Parameters parameters2 = this.parameters;
        int hashCode5 = (hashCode4 + (parameters2 != null ? parameters2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy = this.memoryCachePolicy;
        int hashCode6 = (hashCode5 + (cachePolicy != null ? cachePolicy.hashCode() : 0)) * 31;
        CachePolicy cachePolicy2 = this.diskCachePolicy;
        int hashCode7 = (hashCode6 + (cachePolicy2 != null ? cachePolicy2.hashCode() : 0)) * 31;
        CachePolicy cachePolicy3 = this.networkCachePolicy;
        if (cachePolicy3 != null) {
            i = cachePolicy3.hashCode();
        }
        return hashCode7 + i;
    }

    public String toString() {
        return "Options(config=" + this.config + ", colorSpace=" + this.colorSpace + ", scale=" + this.scale + ", allowInexactSize=" + this.allowInexactSize + ", allowRgb565=" + this.allowRgb565 + ", headers=" + this.headers + ", parameters=" + this.parameters + ", memoryCachePolicy=" + this.memoryCachePolicy + ", diskCachePolicy=" + this.diskCachePolicy + ", networkCachePolicy=" + this.networkCachePolicy + ")";
    }

    public Options(Bitmap.Config config2, ColorSpace colorSpace2, Scale scale2, boolean z, boolean z2, Headers headers2, Parameters parameters2, CachePolicy cachePolicy, CachePolicy cachePolicy2, CachePolicy cachePolicy3) {
        Intrinsics.checkParameterIsNotNull(config2, Constants.CONFIG_FILE_NAME);
        Intrinsics.checkParameterIsNotNull(scale2, "scale");
        Intrinsics.checkParameterIsNotNull(headers2, "headers");
        Intrinsics.checkParameterIsNotNull(parameters2, "parameters");
        Intrinsics.checkParameterIsNotNull(cachePolicy, "memoryCachePolicy");
        Intrinsics.checkParameterIsNotNull(cachePolicy2, "diskCachePolicy");
        Intrinsics.checkParameterIsNotNull(cachePolicy3, "networkCachePolicy");
        this.config = config2;
        this.colorSpace = colorSpace2;
        this.scale = scale2;
        this.allowInexactSize = z;
        this.allowRgb565 = z2;
        this.headers = headers2;
        this.parameters = parameters2;
        this.memoryCachePolicy = cachePolicy;
        this.diskCachePolicy = cachePolicy2;
        this.networkCachePolicy = cachePolicy3;
    }

    public final Bitmap.Config getConfig() {
        return this.config;
    }

    public final ColorSpace getColorSpace() {
        return this.colorSpace;
    }

    public final Scale getScale() {
        return this.scale;
    }

    public final boolean getAllowInexactSize() {
        return this.allowInexactSize;
    }

    public final boolean getAllowRgb565() {
        return this.allowRgb565;
    }

    public final Headers getHeaders() {
        return this.headers;
    }

    public final Parameters getParameters() {
        return this.parameters;
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
