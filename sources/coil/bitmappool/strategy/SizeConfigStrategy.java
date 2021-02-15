package coil.bitmappool.strategy;

import android.graphics.Bitmap;
import android.os.Build;
import coil.collection.GroupedLinkedMap;
import coil.util.Bitmaps;
import coil.util.Utils;
import com.dd.plist.ASCIIPropertyListParser;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.bytebuddy.pool.TypePool;
import net.media.android.bidder.base.common.Constants;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0001\u0018\u0000 !2\u00020\u0001:\u0002!\"B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0018\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\tH\u0002J&\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010\u0014\u001a\u00020\u000b2\b\b\u0001\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u001b\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00172\u0006\u0010\u0018\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\tH\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J$\u0010\u001b\u001a\u00020\u001c2\b\b\u0001\u0010\u0014\u001a\u00020\u000b2\b\b\u0001\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\tH\u0016J\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010 \u001a\u00020\u001cH\u0016R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000RF\u0010\u0007\u001a:\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n0\bj\u001c\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\n`\fX\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcoil/bitmappool/strategy/SizeConfigStrategy;", "Lcoil/bitmappool/strategy/BitmapPoolStrategy;", "()V", "groupedMap", "Lcoil/collection/GroupedLinkedMap;", "Lcoil/bitmappool/strategy/SizeConfigStrategy$Key;", "Landroid/graphics/Bitmap;", "sortedSizes", "Ljava/util/HashMap;", "Landroid/graphics/Bitmap$Config;", "Ljava/util/NavigableMap;", "", "Lkotlin/collections/HashMap;", "decrementBitmapOfSize", "", "size", "removed", "findBestKey", "config", "get", "width", "height", "getInConfigs", "", "requested", "(Landroid/graphics/Bitmap$Config;)[Landroid/graphics/Bitmap$Config;", "getSizesForConfig", "logBitmap", "", "bitmap", "put", "removeLast", "toString", "Companion", "Key", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SizeConfigStrategy.kt */
public final class SizeConfigStrategy implements BitmapPoolStrategy {
    private static final Bitmap.Config[] ALPHA_8_IN_CONFIGS = {Bitmap.Config.ALPHA_8};
    private static final Bitmap.Config[] ARGB_4444_IN_CONFIGS = {Bitmap.Config.ARGB_4444};
    private static final Bitmap.Config[] ARGB_8888_IN_CONFIGS;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_SIZE_MULTIPLE = 8;
    private static final Bitmap.Config[] RGBA_F16_IN_CONFIGS;
    private static final Bitmap.Config[] RGB_565_IN_CONFIGS = {Bitmap.Config.RGB_565};
    private final GroupedLinkedMap<Key, Bitmap> groupedMap = new GroupedLinkedMap<>();
    private final HashMap<Bitmap.Config, NavigableMap<Integer, Integer>> sortedSizes = new HashMap<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0005H\bR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcoil/bitmappool/strategy/SizeConfigStrategy$Companion;", "", "()V", "ALPHA_8_IN_CONFIGS", "", "Landroid/graphics/Bitmap$Config;", "[Landroid/graphics/Bitmap$Config;", "ARGB_4444_IN_CONFIGS", "ARGB_8888_IN_CONFIGS", "MAX_SIZE_MULTIPLE", "", "RGBA_F16_IN_CONFIGS", "RGB_565_IN_CONFIGS", "getBitmapString", "", "size", "config", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: SizeConfigStrategy.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final String getBitmapString(int i, Bitmap.Config config) {
            return TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH + i + "](" + config + ASCIIPropertyListParser.ARRAY_END_TOKEN;
        }
    }

    static {
        Bitmap.Config[] configArr = Build.VERSION.SDK_INT >= 26 ? new Bitmap.Config[]{Bitmap.Config.ARGB_8888, Bitmap.Config.RGBA_F16} : new Bitmap.Config[]{Bitmap.Config.ARGB_8888};
        ARGB_8888_IN_CONFIGS = configArr;
        RGBA_F16_IN_CONFIGS = configArr;
    }

    public void put(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int allocationByteCountCompat = Bitmaps.getAllocationByteCountCompat(bitmap);
        Bitmap.Config config = bitmap.getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "bitmap.config");
        Key key = new Key(allocationByteCountCompat, config);
        this.groupedMap.set(key, bitmap);
        NavigableMap<Integer, Integer> sizesForConfig = getSizesForConfig(bitmap.getConfig());
        Integer num = (Integer) sizesForConfig.get(Integer.valueOf(key.getSize()));
        Map map = sizesForConfig;
        Integer valueOf = Integer.valueOf(key.getSize());
        int i = 1;
        if (num != null) {
            i = 1 + num.intValue();
        }
        map.put(valueOf, Integer.valueOf(i));
    }

    public Bitmap get(int i, int i2, Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        Key findBestKey = findBestKey(Utils.INSTANCE.calculateAllocationByteCount(i, i2, config), config);
        Bitmap bitmap = this.groupedMap.get(findBestKey);
        if (bitmap != null) {
            decrementBitmapOfSize(findBestKey.getSize(), bitmap);
            bitmap.reconfigure(i, i2, config);
        }
        return bitmap;
    }

    private final Key findBestKey(int i, Bitmap.Config config) {
        for (Bitmap.Config config2 : getInConfigs(config)) {
            Integer ceilingKey = getSizesForConfig(config2).ceilingKey(Integer.valueOf(i));
            if (ceilingKey != null && ceilingKey.intValue() <= i * 8) {
                return new Key(ceilingKey.intValue(), config2);
            }
        }
        return new Key(i, config);
    }

    public Bitmap removeLast() {
        Bitmap removeLast = this.groupedMap.removeLast();
        if (removeLast != null) {
            decrementBitmapOfSize(Bitmaps.getAllocationByteCountCompat(removeLast), removeLast);
        }
        return removeLast;
    }

    private final void decrementBitmapOfSize(int i, Bitmap bitmap) {
        NavigableMap<Integer, Integer> sizesForConfig = getSizesForConfig(bitmap.getConfig());
        Object obj = sizesForConfig.get(Integer.valueOf(i));
        if (obj != null) {
            int intValue = ((Number) obj).intValue();
            if (intValue == 1) {
                sizesForConfig.remove(Integer.valueOf(i));
            } else {
                sizesForConfig.put(Integer.valueOf(i), Integer.valueOf(intValue - 1));
            }
        } else {
            throw new IllegalStateException(("Tried to decrement empty size, size: " + i + ", removed: " + logBitmap(bitmap) + ", this: " + this).toString());
        }
    }

    private final NavigableMap<Integer, Integer> getSizesForConfig(Bitmap.Config config) {
        Map map = this.sortedSizes;
        Object obj = map.get(config);
        if (obj == null) {
            obj = new TreeMap();
            map.put(config, obj);
        }
        return (NavigableMap) obj;
    }

    private final Bitmap.Config[] getInConfigs(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && Bitmap.Config.RGBA_F16 == config) {
            return RGBA_F16_IN_CONFIGS;
        }
        if (config == Bitmap.Config.ARGB_8888) {
            return ARGB_8888_IN_CONFIGS;
        }
        if (config == Bitmap.Config.RGB_565) {
            return RGB_565_IN_CONFIGS;
        }
        if (config == Bitmap.Config.ARGB_4444) {
            return ARGB_4444_IN_CONFIGS;
        }
        if (config == Bitmap.Config.ALPHA_8) {
            return ALPHA_8_IN_CONFIGS;
        }
        return new Bitmap.Config[]{config};
    }

    public String logBitmap(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int allocationByteCountCompat = Bitmaps.getAllocationByteCountCompat(bitmap);
        Bitmap.Config config = bitmap.getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "bitmap.config");
        return TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH + allocationByteCountCompat + "](" + config + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }

    public String logBitmap(int i, int i2, Bitmap.Config config) {
        Intrinsics.checkParameterIsNotNull(config, Constants.CONFIG_FILE_NAME);
        int calculateAllocationByteCount = Utils.INSTANCE.calculateAllocationByteCount(i, i2, config);
        return TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH + calculateAllocationByteCount + "](" + config + ASCIIPropertyListParser.ARRAY_END_TOKEN;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SizeConfigStrategy: groupedMap=");
        sb.append(this.groupedMap);
        sb.append(", sortedSizes=(");
        for (Map.Entry entry : this.sortedSizes.entrySet()) {
            sb.append((Bitmap.Config) entry.getKey());
            sb.append("[");
            sb.append((NavigableMap) entry.getValue());
            sb.append("], ");
        }
        if (!this.sortedSizes.isEmpty()) {
            sb.replace(sb.length() - 2, sb.length(), "");
        }
        sb.append(")");
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcoil/bitmappool/strategy/SizeConfigStrategy$Key;", "", "size", "", "config", "Landroid/graphics/Bitmap$Config;", "(ILandroid/graphics/Bitmap$Config;)V", "getConfig", "()Landroid/graphics/Bitmap$Config;", "getSize", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: SizeConfigStrategy.kt */
    private static final class Key {
        private final Bitmap.Config config;
        private final int size;

        public static /* synthetic */ Key copy$default(Key key, int i, Bitmap.Config config2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = key.size;
            }
            if ((i2 & 2) != 0) {
                config2 = key.config;
            }
            return key.copy(i, config2);
        }

        public final int component1() {
            return this.size;
        }

        public final Bitmap.Config component2() {
            return this.config;
        }

        public final Key copy(int i, Bitmap.Config config2) {
            Intrinsics.checkParameterIsNotNull(config2, Constants.CONFIG_FILE_NAME);
            return new Key(i, config2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            return this.size == key.size && Intrinsics.areEqual((Object) this.config, (Object) key.config);
        }

        public int hashCode() {
            int i = this.size * 31;
            Bitmap.Config config2 = this.config;
            return i + (config2 != null ? config2.hashCode() : 0);
        }

        public Key(int i, Bitmap.Config config2) {
            Intrinsics.checkParameterIsNotNull(config2, Constants.CONFIG_FILE_NAME);
            this.size = i;
            this.config = config2;
        }

        public final int getSize() {
            return this.size;
        }

        public final Bitmap.Config getConfig() {
            return this.config;
        }

        public String toString() {
            Companion companion = SizeConfigStrategy.Companion;
            int i = this.size;
            Bitmap.Config config2 = this.config;
            return TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH + i + "](" + config2 + ASCIIPropertyListParser.ARRAY_END_TOKEN;
        }
    }
}
