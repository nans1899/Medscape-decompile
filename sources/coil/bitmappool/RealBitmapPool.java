package coil.bitmappool;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.collection.ArraySet;
import androidx.collection.ArraySetKt;
import coil.bitmappool.strategy.BitmapPoolStrategy;
import coil.util.Bitmaps;
import coil.util.Logger;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.media.android.bidder.base.common.Constants;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0006\u0010\u0013\u001a\u00020\u0012J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J$\u0010\u0017\u001a\u00020\u00182\b\b\u0001\u0010\u0019\u001a\u00020\u00032\b\b\u0001\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J$\u0010\u001c\u001a\u00020\u00182\b\b\u0001\u0010\u0019\u001a\u00020\u00032\b\b\u0001\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u00182\b\b\u0001\u0010\u0019\u001a\u00020\u00032\b\b\u0001\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J&\u0010\u001e\u001a\u0004\u0018\u00010\u00182\b\b\u0001\u0010\u0019\u001a\u00020\u00032\b\b\u0001\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0006H\u0016J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0018H\u0002J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0018H\u0016J\u0010\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u0003H\u0016J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u0003H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcoil/bitmappool/RealBitmapPool;", "Lcoil/bitmappool/BitmapPool;", "maxSize", "", "allowedConfigs", "", "Landroid/graphics/Bitmap$Config;", "strategy", "Lcoil/bitmappool/strategy/BitmapPoolStrategy;", "logger", "Lcoil/util/Logger;", "(ILjava/util/Set;Lcoil/bitmappool/strategy/BitmapPoolStrategy;Lcoil/util/Logger;)V", "currentSize", "evictions", "hits", "misses", "puts", "clear", "", "clearMemory", "computeUnchecked", "", "dump", "get", "Landroid/graphics/Bitmap;", "width", "height", "config", "getDirty", "getDirtyOrNull", "getOrNull", "normalize", "bitmap", "put", "trimMemory", "level", "trimToSize", "size", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RealBitmapPool.kt */
public final class RealBitmapPool implements BitmapPool {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "RealBitmapPool";
    private final Set<Bitmap.Config> allowedConfigs;
    private int currentSize;
    private int evictions;
    private int hits;
    private final Logger logger;
    private final int maxSize;
    private int misses;
    private int puts;
    private final BitmapPoolStrategy strategy;

    public RealBitmapPool(int i, Set<? extends Bitmap.Config> set, BitmapPoolStrategy bitmapPoolStrategy, Logger logger2) {
        Intrinsics.checkParameterIsNotNull(set, "allowedConfigs");
        Intrinsics.checkParameterIsNotNull(bitmapPoolStrategy, "strategy");
        this.maxSize = i;
        this.allowedConfigs = set;
        this.strategy = bitmapPoolStrategy;
        this.logger = logger2;
        if (!(i >= 0)) {
            throw new IllegalArgumentException("maxSize must be >= 0.".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RealBitmapPool(int i, Set set, BitmapPoolStrategy bitmapPoolStrategy, Logger logger2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? Companion.getDefaultAllowedConfigs() : set, (i2 & 4) != 0 ? BitmapPoolStrategy.Companion.invoke() : bitmapPoolStrategy, (i2 & 8) != 0 ? null : logger2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcoil/bitmappool/RealBitmapPool$Companion;", "", "()V", "TAG", "", "getDefaultAllowedConfigs", "", "Landroid/graphics/Bitmap$Config;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: RealBitmapPool.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final Set<Bitmap.Config> getDefaultAllowedConfigs() {
            ArraySet arraySetOf = ArraySetKt.arraySetOf(Bitmap.Config.ALPHA_8, Bitmap.Config.RGB_565, Bitmap.Config.ARGB_4444, Bitmap.Config.ARGB_8888);
            if (Build.VERSION.SDK_INT >= 26) {
                arraySetOf.add(Bitmap.Config.RGBA_F16);
            }
            return arraySetOf;
        }
    }

    public synchronized void put(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        boolean z = true;
        if (!bitmap.isRecycled()) {
            int allocationByteCountCompat = Bitmaps.getAllocationByteCountCompat(bitmap);
            if (bitmap.isMutable() && allocationByteCountCompat <= this.maxSize) {
                if (this.allowedConfigs.contains(bitmap.getConfig())) {
                    this.strategy.put(bitmap);
                    this.puts++;
                    this.currentSize += allocationByteCountCompat;
                    Logger logger2 = this.logger;
                    if (logger2 != null && logger2.getLevel() <= 2) {
                        logger2.log(TAG, 2, "Put bitmap in pool=" + this.strategy.logBitmap(bitmap), (Throwable) null);
                    }
                    dump();
                    trimToSize(this.maxSize);
                    return;
                }
            }
            Logger logger3 = this.logger;
            if (logger3 != null && logger3.getLevel() <= 2) {
                StringBuilder sb = new StringBuilder();
                sb.append("Rejected bitmap from pool: bitmap: ");
                sb.append(this.strategy.logBitmap(bitmap));
                sb.append(", ");
                sb.append("is mutable: ");
                sb.append(bitmap.isMutable());
                sb.append(", ");
                sb.append("is greater than max size: ");
                if (allocationByteCountCompat <= this.maxSize) {
                    z = false;
                }
                sb.append(z);
                sb.append("is allowed config: ");
                sb.append(this.allowedConfigs.contains(bitmap.getConfig()));
                logger3.log(TAG, 2, sb.toString(), (Throwable) null);
            }
            bitmap.recycle();
            return;
        }
        throw new IllegalArgumentException("Cannot pool recycled bitmap!".toString());
    }

    public Bitmap get(int i, int i2, Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        Bitmap orNull = getOrNull(i, i2, config);
        if (orNull != null) {
            return orNull;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(width, height, config)");
        return createBitmap;
    }

    public Bitmap getOrNull(int i, int i2, Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        Bitmap dirtyOrNull = getDirtyOrNull(i, i2, config);
        if (dirtyOrNull == null) {
            return null;
        }
        dirtyOrNull.eraseColor(0);
        return dirtyOrNull;
    }

    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        Bitmap dirtyOrNull = getDirtyOrNull(i, i2, config);
        if (dirtyOrNull != null) {
            return dirtyOrNull;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(width, height, config)");
        return createBitmap;
    }

    public synchronized Bitmap getDirtyOrNull(int i, int i2, Bitmap.Config config) {
        Bitmap bitmap;
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        if (!Bitmaps.isHardware(config)) {
            bitmap = this.strategy.get(i, i2, config);
            if (bitmap == null) {
                Logger logger2 = this.logger;
                if (logger2 != null && logger2.getLevel() <= 2) {
                    logger2.log(TAG, 2, "Missing bitmap=" + this.strategy.logBitmap(i, i2, config), (Throwable) null);
                }
                this.misses++;
            } else {
                this.hits++;
                this.currentSize -= Bitmaps.getAllocationByteCountCompat(bitmap);
                normalize(bitmap);
            }
            Logger logger3 = this.logger;
            if (logger3 != null && logger3.getLevel() <= 2) {
                logger3.log(TAG, 2, "Get bitmap=" + this.strategy.logBitmap(i, i2, config), (Throwable) null);
            }
            dump();
        } else {
            throw new IllegalArgumentException("Cannot create a mutable hardware bitmap.".toString());
        }
        return bitmap;
    }

    public void clear() {
        clearMemory();
    }

    public final void clearMemory() {
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 2) {
            logger2.log(TAG, 2, "clearMemory", (Throwable) null);
        }
        trimToSize(-1);
    }

    public synchronized void trimMemory(int i) {
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 2) {
            logger2.log(TAG, 2, "trimMemory, level=" + i, (Throwable) null);
        }
        if (i >= 40) {
            clearMemory();
        } else if (10 <= i) {
            if (20 > i) {
                trimToSize(this.currentSize / 2);
            }
        }
    }

    private final void normalize(Bitmap bitmap) {
        bitmap.setDensity(0);
        bitmap.setHasAlpha(true);
        if (Build.VERSION.SDK_INT >= 19) {
            bitmap.setPremultiplied(true);
        }
    }

    private final synchronized void trimToSize(int i) {
        while (this.currentSize > i) {
            Bitmap removeLast = this.strategy.removeLast();
            if (removeLast == null) {
                Logger logger2 = this.logger;
                if (logger2 != null && logger2.getLevel() <= 5) {
                    logger2.log(TAG, 5, "Size mismatch, resetting.\n" + computeUnchecked(), (Throwable) null);
                }
                this.currentSize = 0;
                return;
            }
            this.currentSize -= Bitmaps.getAllocationByteCountCompat(removeLast);
            this.evictions++;
            Logger logger3 = this.logger;
            if (logger3 != null && logger3.getLevel() <= 2) {
                logger3.log(TAG, 2, "Evicting bitmap=" + this.strategy.logBitmap(removeLast), (Throwable) null);
            }
            dump();
            removeLast.recycle();
        }
    }

    private final void dump() {
        Logger logger2 = this.logger;
        if (logger2 != null && logger2.getLevel() <= 2) {
            logger2.log(TAG, 2, computeUnchecked(), (Throwable) null);
        }
    }

    private final String computeUnchecked() {
        return "Hits=" + this.hits + ", misses=" + this.misses + ", puts=" + this.puts + ", evictions=" + this.evictions + ", " + "currentSize=" + this.currentSize + ", maxSize=" + this.maxSize + ", strategy=" + this.strategy;
    }
}
