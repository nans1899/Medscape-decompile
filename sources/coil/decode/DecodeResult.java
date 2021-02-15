package coil.decode;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t¨\u0006\u0013"}, d2 = {"Lcoil/decode/DecodeResult;", "", "drawable", "Landroid/graphics/drawable/Drawable;", "isSampled", "", "(Landroid/graphics/drawable/Drawable;Z)V", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DecodeResult.kt */
public final class DecodeResult {
    private final Drawable drawable;
    private final boolean isSampled;

    public static /* synthetic */ DecodeResult copy$default(DecodeResult decodeResult, Drawable drawable2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable2 = decodeResult.drawable;
        }
        if ((i & 2) != 0) {
            z = decodeResult.isSampled;
        }
        return decodeResult.copy(drawable2, z);
    }

    public final Drawable component1() {
        return this.drawable;
    }

    public final boolean component2() {
        return this.isSampled;
    }

    public final DecodeResult copy(Drawable drawable2, boolean z) {
        Intrinsics.checkParameterIsNotNull(drawable2, "drawable");
        return new DecodeResult(drawable2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DecodeResult)) {
            return false;
        }
        DecodeResult decodeResult = (DecodeResult) obj;
        return Intrinsics.areEqual((Object) this.drawable, (Object) decodeResult.drawable) && this.isSampled == decodeResult.isSampled;
    }

    public int hashCode() {
        Drawable drawable2 = this.drawable;
        int hashCode = (drawable2 != null ? drawable2.hashCode() : 0) * 31;
        boolean z = this.isSampled;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "DecodeResult(drawable=" + this.drawable + ", isSampled=" + this.isSampled + ")";
    }

    public DecodeResult(Drawable drawable2, boolean z) {
        Intrinsics.checkParameterIsNotNull(drawable2, "drawable");
        this.drawable = drawable2;
        this.isSampled = z;
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final boolean isSampled() {
        return this.isSampled;
    }
}
