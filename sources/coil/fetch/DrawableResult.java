package coil.fetch;

import android.graphics.drawable.Drawable;
import coil.decode.DataSource;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\r¨\u0006\u0019"}, d2 = {"Lcoil/fetch/DrawableResult;", "Lcoil/fetch/FetchResult;", "drawable", "Landroid/graphics/drawable/Drawable;", "isSampled", "", "dataSource", "Lcoil/decode/DataSource;", "(Landroid/graphics/drawable/Drawable;ZLcoil/decode/DataSource;)V", "getDataSource", "()Lcoil/decode/DataSource;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: FetchResult.kt */
public final class DrawableResult extends FetchResult {
    private final DataSource dataSource;
    private final Drawable drawable;
    private final boolean isSampled;

    public static /* synthetic */ DrawableResult copy$default(DrawableResult drawableResult, Drawable drawable2, boolean z, DataSource dataSource2, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable2 = drawableResult.drawable;
        }
        if ((i & 2) != 0) {
            z = drawableResult.isSampled;
        }
        if ((i & 4) != 0) {
            dataSource2 = drawableResult.dataSource;
        }
        return drawableResult.copy(drawable2, z, dataSource2);
    }

    public final Drawable component1() {
        return this.drawable;
    }

    public final boolean component2() {
        return this.isSampled;
    }

    public final DataSource component3() {
        return this.dataSource;
    }

    public final DrawableResult copy(Drawable drawable2, boolean z, DataSource dataSource2) {
        Intrinsics.checkParameterIsNotNull(drawable2, "drawable");
        Intrinsics.checkParameterIsNotNull(dataSource2, "dataSource");
        return new DrawableResult(drawable2, z, dataSource2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DrawableResult)) {
            return false;
        }
        DrawableResult drawableResult = (DrawableResult) obj;
        return Intrinsics.areEqual((Object) this.drawable, (Object) drawableResult.drawable) && this.isSampled == drawableResult.isSampled && Intrinsics.areEqual((Object) this.dataSource, (Object) drawableResult.dataSource);
    }

    public int hashCode() {
        Drawable drawable2 = this.drawable;
        int i = 0;
        int hashCode = (drawable2 != null ? drawable2.hashCode() : 0) * 31;
        boolean z = this.isSampled;
        if (z) {
            z = true;
        }
        int i2 = (hashCode + (z ? 1 : 0)) * 31;
        DataSource dataSource2 = this.dataSource;
        if (dataSource2 != null) {
            i = dataSource2.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        return "DrawableResult(drawable=" + this.drawable + ", isSampled=" + this.isSampled + ", dataSource=" + this.dataSource + ")";
    }

    public final Drawable getDrawable() {
        return this.drawable;
    }

    public final boolean isSampled() {
        return this.isSampled;
    }

    public final DataSource getDataSource() {
        return this.dataSource;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DrawableResult(Drawable drawable2, boolean z, DataSource dataSource2) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(drawable2, "drawable");
        Intrinsics.checkParameterIsNotNull(dataSource2, "dataSource");
        this.drawable = drawable2;
        this.isSampled = z;
        this.dataSource = dataSource2;
    }
}
