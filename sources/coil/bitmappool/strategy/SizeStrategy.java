package coil.bitmappool.strategy;

import android.graphics.Bitmap;
import coil.collection.GroupedLinkedMap;
import coil.util.Bitmaps;
import coil.util.Utils;
import com.dd.plist.ASCIIPropertyListParser;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.bytebuddy.pool.TypePool;
import net.media.android.bidder.base.common.Constants;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0001\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0006H\u0002J\u0010\u0010\r\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0002J&\u0010\u000e\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u000f\u001a\u00020\u00052\b\b\u0001\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J$\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u000f\u001a\u00020\u00052\b\b\u0001\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0018\u001a\u00020\u0014H\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcoil/bitmappool/strategy/SizeStrategy;", "Lcoil/bitmappool/strategy/BitmapPoolStrategy;", "()V", "groupedMap", "Lcoil/collection/GroupedLinkedMap;", "", "Landroid/graphics/Bitmap;", "sortedSizes", "Ljava/util/TreeMap;", "decrementBitmapOfSize", "", "size", "removed", "findBestSize", "get", "width", "height", "config", "Landroid/graphics/Bitmap$Config;", "logBitmap", "", "bitmap", "put", "removeLast", "toString", "Companion", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SizeStrategy.kt */
public final class SizeStrategy implements BitmapPoolStrategy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_SIZE_MULTIPLE = 8;
    private final GroupedLinkedMap<Integer, Bitmap> groupedMap = new GroupedLinkedMap<>();
    private final TreeMap<Integer, Integer> sortedSizes = new TreeMap<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\bR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcoil/bitmappool/strategy/SizeStrategy$Companion;", "", "()V", "MAX_SIZE_MULTIPLE", "", "getBitmapString", "", "size", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: SizeStrategy.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String getBitmapString(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            sb.append(i);
            sb.append(']');
            return sb.toString();
        }
    }

    public void put(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int allocationByteCountCompat = Bitmaps.getAllocationByteCountCompat(bitmap);
        this.groupedMap.set(Integer.valueOf(allocationByteCountCompat), bitmap);
        Integer num = this.sortedSizes.get(Integer.valueOf(allocationByteCountCompat));
        Map map = this.sortedSizes;
        Integer valueOf = Integer.valueOf(allocationByteCountCompat);
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        map.put(valueOf, Integer.valueOf(i));
    }

    public Bitmap get(int i, int i2, Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        int findBestSize = findBestSize(Utils.INSTANCE.calculateAllocationByteCount(i, i2, config));
        Bitmap bitmap = this.groupedMap.get(Integer.valueOf(findBestSize));
        if (bitmap != null) {
            decrementBitmapOfSize(findBestSize, bitmap);
            bitmap.reconfigure(i, i2, config);
        }
        return bitmap;
    }

    private final int findBestSize(int i) {
        Integer ceilingKey = this.sortedSizes.ceilingKey(Integer.valueOf(i));
        return (ceilingKey == null || ceilingKey.intValue() > i * 8) ? i : ceilingKey.intValue();
    }

    public Bitmap removeLast() {
        Bitmap removeLast = this.groupedMap.removeLast();
        if (removeLast != null) {
            decrementBitmapOfSize(Bitmaps.getAllocationByteCountCompat(removeLast), removeLast);
        }
        return removeLast;
    }

    private final void decrementBitmapOfSize(int i, Bitmap bitmap) {
        Integer num = this.sortedSizes.get(Integer.valueOf(i));
        if (num != null) {
            Intrinsics.checkExpressionValueIsNotNull(num, "sortedSizes[size] ?: run…, this: $this\")\n        }");
            int intValue = num.intValue();
            if (intValue == 1) {
                this.sortedSizes.remove(Integer.valueOf(i));
            } else {
                this.sortedSizes.put(Integer.valueOf(i), Integer.valueOf(intValue - 1));
            }
        } else {
            SizeStrategy sizeStrategy = this;
            throw new NullPointerException("Tried to decrement empty size, size: " + i + ", " + "removed: " + sizeStrategy.logBitmap(bitmap) + ", this: " + sizeStrategy);
        }
    }

    public String logBitmap(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int allocationByteCountCompat = Bitmaps.getAllocationByteCountCompat(bitmap);
        StringBuilder sb = new StringBuilder();
        sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        sb.append(allocationByteCountCompat);
        sb.append(']');
        return sb.toString();
    }

    public String logBitmap(int i, int i2, Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        int calculateAllocationByteCount = Utils.INSTANCE.calculateAllocationByteCount(i, i2, config);
        StringBuilder sb = new StringBuilder();
        sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        sb.append(calculateAllocationByteCount);
        sb.append(']');
        return sb.toString();
    }

    public String toString() {
        return "SizeStrategy: groupedMap=" + this.groupedMap + ", sortedSizes=(" + this.sortedSizes + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }
}
