package coil;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"coil/Coil$setImageLoader$1", "Lcoil/ImageLoaderFactory;", "newImageLoader", "Lcoil/ImageLoader;", "coil-singleton_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Coil.kt */
public final class Coil$setImageLoader$1 implements ImageLoaderFactory {
    final /* synthetic */ ImageLoader $loader;

    Coil$setImageLoader$1(ImageLoader imageLoader) {
        this.$loader = imageLoader;
    }

    public ImageLoader newImageLoader() {
        return this.$loader;
    }
}
