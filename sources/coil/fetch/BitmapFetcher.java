package coil.fetch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import coil.bitmappool.BitmapPool;
import coil.decode.DataSource;
import coil.decode.Options;
import coil.fetch.Fetcher;
import coil.size.Size;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J1\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lcoil/fetch/BitmapFetcher;", "Lcoil/fetch/Fetcher;", "Landroid/graphics/Bitmap;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "fetch", "Lcoil/fetch/FetchResult;", "pool", "Lcoil/bitmappool/BitmapPool;", "data", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "(Lcoil/bitmappool/BitmapPool;Landroid/graphics/Bitmap;Lcoil/size/Size;Lcoil/decode/Options;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "key", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BitmapFetcher.kt */
public final class BitmapFetcher implements Fetcher<Bitmap> {
    private final Context context;

    public String key(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "data");
        return null;
    }

    public BitmapFetcher(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public boolean handles(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "data");
        return Fetcher.DefaultImpls.handles(this, bitmap);
    }

    public Object fetch(BitmapPool bitmapPool, Bitmap bitmap, Size size, Options options, Continuation<? super FetchResult> continuation) {
        Resources resources = this.context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        return new DrawableResult(new BitmapDrawable(resources, bitmap), false, DataSource.MEMORY);
    }
}
