package coil.request;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcoil/request/ErrorResult;", "Lcoil/request/RequestResult;", "drawable", "Landroid/graphics/drawable/Drawable;", "throwable", "", "(Landroid/graphics/drawable/Drawable;Ljava/lang/Throwable;)V", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "getThrowable", "()Ljava/lang/Throwable;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestResult.kt */
public final class ErrorResult extends RequestResult {
    private final Drawable drawable;
    private final Throwable throwable;

    public static /* synthetic */ ErrorResult copy$default(ErrorResult errorResult, Drawable drawable2, Throwable th, int i, Object obj) {
        if ((i & 1) != 0) {
            drawable2 = errorResult.getDrawable();
        }
        if ((i & 2) != 0) {
            th = errorResult.throwable;
        }
        return errorResult.copy(drawable2, th);
    }

    public final Drawable component1() {
        return getDrawable();
    }

    public final Throwable component2() {
        return this.throwable;
    }

    public final ErrorResult copy(Drawable drawable2, Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "throwable");
        return new ErrorResult(drawable2, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ErrorResult)) {
            return false;
        }
        ErrorResult errorResult = (ErrorResult) obj;
        return Intrinsics.areEqual((Object) getDrawable(), (Object) errorResult.getDrawable()) && Intrinsics.areEqual((Object) this.throwable, (Object) errorResult.throwable);
    }

    public int hashCode() {
        Drawable drawable2 = getDrawable();
        int i = 0;
        int hashCode = (drawable2 != null ? drawable2.hashCode() : 0) * 31;
        Throwable th = this.throwable;
        if (th != null) {
            i = th.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "ErrorResult(drawable=" + getDrawable() + ", throwable=" + this.throwable + ")";
    }

    public Drawable getDrawable() {
        return this.drawable;
    }

    public final Throwable getThrowable() {
        return this.throwable;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ErrorResult(Drawable drawable2, Throwable th) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(th, "throwable");
        this.drawable = drawable2;
        this.throwable = th;
    }
}
