package coil.bitmappool;

import android.graphics.Bitmap;
import coil.bitmappool.strategy.BitmapPoolStrategy;
import coil.util.Logger;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012J\b\u0010\u0002\u001a\u00020\u0003H&J$\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J$\u0010\u000b\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J&\u0010\f\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J&\u0010\r\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0005H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0007H&¨\u0006\u0013"}, d2 = {"Lcoil/bitmappool/BitmapPool;", "", "clear", "", "get", "Landroid/graphics/Bitmap;", "width", "", "height", "config", "Landroid/graphics/Bitmap$Config;", "getDirty", "getDirtyOrNull", "getOrNull", "put", "bitmap", "trimMemory", "level", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BitmapPool.kt */
public interface BitmapPool {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* renamed from: coil.bitmappool.BitmapPool$-CC  reason: invalid class name */
    /* compiled from: BitmapPool.kt */
    public final /* synthetic */ class CC {
        @JvmStatic
        public static BitmapPool create(int i, Logger logger) {
            return BitmapPool.Companion.create(i, logger);
        }
    }

    void clear();

    Bitmap get(int i, int i2, Bitmap.Config config);

    Bitmap getDirty(int i, int i2, Bitmap.Config config);

    Bitmap getDirtyOrNull(int i, int i2, Bitmap.Config config);

    Bitmap getOrNull(int i, int i2, Bitmap.Config config);

    void put(Bitmap bitmap);

    void trimMemory(int i);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Lcoil/bitmappool/BitmapPool$Companion;", "", "()V", "invoke", "Lcoil/bitmappool/BitmapPool;", "maxSize", "", "logger", "Lcoil/util/Logger;", "create", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: BitmapPool.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public static /* synthetic */ BitmapPool create$default(Companion companion, int i, Logger logger, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                logger = null;
            }
            return companion.create(i, logger);
        }

        @JvmStatic
        public final BitmapPool create(int i, Logger logger) {
            return new RealBitmapPool(i, (Set) null, (BitmapPoolStrategy) null, logger, 6, (DefaultConstructorMarker) null);
        }
    }
}
