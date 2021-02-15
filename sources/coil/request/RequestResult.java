package coil.request;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcoil/request/RequestResult;", "", "()V", "drawable", "Landroid/graphics/drawable/Drawable;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "Lcoil/request/SuccessResult;", "Lcoil/request/ErrorResult;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RequestResult.kt */
public abstract class RequestResult {
    public abstract Drawable getDrawable();

    private RequestResult() {
    }

    public /* synthetic */ RequestResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
