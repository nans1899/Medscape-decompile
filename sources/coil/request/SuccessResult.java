package coil.request;

import android.graphics.drawable.Drawable;
import coil.decode.DataSource;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/request/SuccessResult;", "Lcoil/request/RequestResult;", "drawable", "Landroid/graphics/drawable/Drawable;", "source", "Lcoil/decode/DataSource;", "(Landroid/graphics/drawable/Drawable;Lcoil/decode/DataSource;)V", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "getSource", "()Lcoil/decode/DataSource;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestResult.kt */
public final class SuccessResult extends RequestResult {
    private final Drawable drawable;
    private final DataSource source;

    public static /* synthetic */ SuccessResult copy$default(SuccessResult successResult, Drawable drawable2, DataSource dataSource, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable2 = successResult.getDrawable();
        }
        if ((i & 2) != 0) {
            dataSource = successResult.source;
        }
        return successResult.copy(drawable2, dataSource);
    }

    public final Drawable component1() {
        return getDrawable();
    }

    public final DataSource component2() {
        return this.source;
    }

    public final SuccessResult copy(Drawable drawable2, DataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(drawable2, "drawable");
        Intrinsics.checkParameterIsNotNull(dataSource, "source");
        return new SuccessResult(drawable2, dataSource);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuccessResult)) {
            return false;
        }
        SuccessResult successResult = (SuccessResult) obj;
        return Intrinsics.areEqual((Object) getDrawable(), (Object) successResult.getDrawable()) && Intrinsics.areEqual((Object) this.source, (Object) successResult.source);
    }

    public int hashCode() {
        Drawable drawable2 = getDrawable();
        int i = 0;
        int hashCode = (drawable2 != null ? drawable2.hashCode() : 0) * 31;
        DataSource dataSource = this.source;
        if (dataSource != null) {
            i = dataSource.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "SuccessResult(drawable=" + getDrawable() + ", source=" + this.source + ")";
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public final DataSource getSource() {
        return this.source;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SuccessResult(Drawable drawable2, DataSource dataSource) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(drawable2, "drawable");
        Intrinsics.checkParameterIsNotNull(dataSource, "source");
        this.drawable = drawable2;
        this.source = dataSource;
    }
}
