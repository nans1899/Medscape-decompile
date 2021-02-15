package coil.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import coil.Coil;
import coil.ImageLoader;
import coil.request.LoadRequest;
import coil.request.LoadRequestBuilder;
import coil.request.RequestDisposable;
import coil.util.CoilUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a<\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\b\u001a<\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\b\u001a<\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\b\u001a<\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\b\u001a<\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\b\u0001\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\b\u001a<\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\u0010\u000f\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\b\u001a<\u0010\u0003\u001a\u00020\u0004*\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\b\u001a<\u0010\u0018\u001a\u00020\u0004*\u00020\u00022\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\n¢\u0006\u0002\b\fH\b¨\u0006\u001b"}, d2 = {"clear", "", "Landroid/widget/ImageView;", "load", "Lcoil/request/RequestDisposable;", "bitmap", "Landroid/graphics/Bitmap;", "imageLoader", "Lcoil/ImageLoader;", "builder", "Lkotlin/Function1;", "Lcoil/request/LoadRequestBuilder;", "Lkotlin/ExtensionFunctionType;", "drawable", "Landroid/graphics/drawable/Drawable;", "uri", "Landroid/net/Uri;", "file", "Ljava/io/File;", "drawableResId", "", "", "url", "Lokhttp3/HttpUrl;", "loadAny", "data", "", "coil-singleton_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: ImageViews.kt */
public final class ImageViews {
    public static /* synthetic */ RequestDisposable load$default(ImageView imageView, String str, ImageLoader imageLoader, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            Context context = imageView.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            imageLoader = Coil.imageLoader(context);
        }
        if ((i & 4) != 0) {
            function1 = ImageViews$load$1.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context2 = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context2).data(str)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static /* synthetic */ RequestDisposable load$default(ImageView imageView, HttpUrl httpUrl, ImageLoader imageLoader, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            Context context = imageView.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            imageLoader = Coil.imageLoader(context);
        }
        if ((i & 4) != 0) {
            function1 = ImageViews$load$2.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context2 = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context2).data(httpUrl)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static /* synthetic */ RequestDisposable load$default(ImageView imageView, Uri uri, ImageLoader imageLoader, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            Context context = imageView.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            imageLoader = Coil.imageLoader(context);
        }
        if ((i & 4) != 0) {
            function1 = ImageViews$load$3.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context2 = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context2).data(uri)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static /* synthetic */ RequestDisposable load$default(ImageView imageView, File file, ImageLoader imageLoader, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            Context context = imageView.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            imageLoader = Coil.imageLoader(context);
        }
        if ((i & 4) != 0) {
            function1 = ImageViews$load$4.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context2 = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context2).data(file)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static /* synthetic */ RequestDisposable load$default(ImageView imageView, int i, ImageLoader imageLoader, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            Context context = imageView.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            imageLoader = Coil.imageLoader(context);
        }
        if ((i2 & 4) != 0) {
            function1 = ImageViews$load$5.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        Integer valueOf = Integer.valueOf(i);
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context2 = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context2).data(valueOf)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static final /* synthetic */ RequestDisposable load(ImageView imageView, int i, ImageLoader imageLoader, Function1<? super LoadRequestBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        Integer valueOf = Integer.valueOf(i);
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context).data(valueOf)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static /* synthetic */ RequestDisposable load$default(ImageView imageView, Drawable drawable, ImageLoader imageLoader, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            Context context = imageView.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            imageLoader = Coil.imageLoader(context);
        }
        if ((i & 4) != 0) {
            function1 = ImageViews$load$6.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context2 = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context2).data(drawable)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static /* synthetic */ RequestDisposable load$default(ImageView imageView, Bitmap bitmap, ImageLoader imageLoader, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            Context context = imageView.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            imageLoader = Coil.imageLoader(context);
        }
        if ((i & 4) != 0) {
            function1 = ImageViews$load$7.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context2 = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context2).data(bitmap)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static /* synthetic */ RequestDisposable loadAny$default(ImageView imageView, Object obj, ImageLoader imageLoader, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            Context context = imageView.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            imageLoader = Coil.imageLoader(context);
        }
        if ((i & 4) != 0) {
            function1 = ImageViews$loadAny$1.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(imageView, "$this$loadAny");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context2 = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context2).data(obj)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static final /* synthetic */ RequestDisposable loadAny(ImageView imageView, Object obj, ImageLoader imageLoader, Function1<? super LoadRequestBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$loadAny");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context).data(obj)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static final void clear(ImageView imageView) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$clear");
        CoilUtils.clear(imageView);
    }

    public static final /* synthetic */ RequestDisposable load(ImageView imageView, String str, ImageLoader imageLoader, Function1<? super LoadRequestBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context).data(str)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static final /* synthetic */ RequestDisposable load(ImageView imageView, HttpUrl httpUrl, ImageLoader imageLoader, Function1<? super LoadRequestBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context).data(httpUrl)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static final /* synthetic */ RequestDisposable load(ImageView imageView, Uri uri, ImageLoader imageLoader, Function1<? super LoadRequestBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context).data(uri)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static final /* synthetic */ RequestDisposable load(ImageView imageView, File file, ImageLoader imageLoader, Function1<? super LoadRequestBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context).data(file)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static final /* synthetic */ RequestDisposable load(ImageView imageView, Drawable drawable, ImageLoader imageLoader, Function1<? super LoadRequestBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context).data(drawable)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }

    public static final /* synthetic */ RequestDisposable load(ImageView imageView, Bitmap bitmap, ImageLoader imageLoader, Function1<? super LoadRequestBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(imageView, "$this$load");
        Intrinsics.checkParameterIsNotNull(imageLoader, "imageLoader");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        LoadRequest.Companion companion = LoadRequest.Companion;
        Context context = imageView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LoadRequestBuilder target = ((LoadRequestBuilder) new LoadRequestBuilder(context).data(bitmap)).target(imageView);
        function1.invoke(target);
        return imageLoader.execute(target.build());
    }
}
