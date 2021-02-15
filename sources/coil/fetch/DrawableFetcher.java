package coil.fetch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import coil.bitmappool.BitmapPool;
import coil.decode.DataSource;
import coil.decode.DrawableDecoderService;
import coil.decode.Options;
import coil.fetch.Fetcher;
import coil.size.Size;
import coil.util.Extensions;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J1\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lcoil/fetch/DrawableFetcher;", "Lcoil/fetch/Fetcher;", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "drawableDecoder", "Lcoil/decode/DrawableDecoderService;", "(Landroid/content/Context;Lcoil/decode/DrawableDecoderService;)V", "fetch", "Lcoil/fetch/FetchResult;", "pool", "Lcoil/bitmappool/BitmapPool;", "data", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "(Lcoil/bitmappool/BitmapPool;Landroid/graphics/drawable/Drawable;Lcoil/size/Size;Lcoil/decode/Options;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "key", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DrawableFetcher.kt */
public final class DrawableFetcher implements Fetcher<Drawable> {
    private final Context context;
    private final DrawableDecoderService drawableDecoder;

    public String key(Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "data");
        return null;
    }

    public DrawableFetcher(Context context2, DrawableDecoderService drawableDecoderService) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(drawableDecoderService, "drawableDecoder");
        this.context = context2;
        this.drawableDecoder = drawableDecoderService;
    }

    public boolean handles(Drawable drawable) {
        Intrinsics.checkParameterIsNotNull(drawable, "data");
        return Fetcher.DefaultImpls.handles(this, drawable);
    }

    public Object fetch(BitmapPool bitmapPool, Drawable drawable, Size size, Options options, Continuation<? super FetchResult> continuation) {
        boolean isVector = Extensions.isVector(drawable);
        if (isVector) {
            Bitmap convert = this.drawableDecoder.convert(drawable, options.getConfig(), size, options.getScale(), options.getAllowInexactSize());
            Resources resources = this.context.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
            drawable = new BitmapDrawable(resources, convert);
        }
        return new DrawableResult(drawable, isVector, DataSource.MEMORY);
    }
}
