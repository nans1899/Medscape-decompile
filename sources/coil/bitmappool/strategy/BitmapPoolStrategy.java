package coil.bitmappool.strategy;

import android.graphics.Bitmap;
import android.os.Build;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b`\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ&\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H&J$\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u0003H&J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0003H&¨\u0006\u0010"}, d2 = {"Lcoil/bitmappool/strategy/BitmapPoolStrategy;", "", "get", "Landroid/graphics/Bitmap;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "logBitmap", "", "bitmap", "put", "", "removeLast", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BitmapPoolStrategy.kt */
public interface BitmapPoolStrategy {
    public static final Companion Companion = Companion.$$INSTANCE;

    Bitmap get(int i, int i2, Bitmap.Config config);

    String logBitmap(int i, int i2, Bitmap.Config config);

    String logBitmap(Bitmap bitmap);

    void put(Bitmap bitmap);

    Bitmap removeLast();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\t\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, d2 = {"Lcoil/bitmappool/strategy/BitmapPoolStrategy$Companion;", "", "()V", "invoke", "Lcoil/bitmappool/strategy/BitmapPoolStrategy;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BitmapPoolStrategy.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final BitmapPoolStrategy invoke() {
            if (Build.VERSION.SDK_INT >= 23) {
                return new SizeStrategy();
            }
            if (Build.VERSION.SDK_INT >= 19) {
                return new SizeConfigStrategy();
            }
            return new AttributeStrategy();
        }
    }
}
