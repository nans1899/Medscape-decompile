package coil.memory;

import android.graphics.Bitmap;
import coil.request.Parameters;
import coil.size.Size;
import coil.transform.Transformation;
import coil.util.Logger;
import com.dd.plist.ASCIIPropertyListParser;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b`\u0018\u0000 \u00132\u00020\u0001:\u0003\u0013\u0014\u0015J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\t\u001a\u00020\nH&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\nH&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\nH&¨\u0006\u0016"}, d2 = {"Lcoil/memory/MemoryCache;", "", "clearMemory", "", "get", "Lcoil/memory/MemoryCache$Value;", "key", "Lcoil/memory/MemoryCache$Key;", "invalidate", "maxSize", "", "set", "bitmap", "Landroid/graphics/Bitmap;", "isSampled", "", "size", "trimMemory", "level", "Companion", "Key", "Value", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MemoryCache.kt */
public interface MemoryCache {
    public static final Companion Companion = Companion.$$INSTANCE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\b¨\u0006\t"}, d2 = {"Lcoil/memory/MemoryCache$Value;", "", "bitmap", "Landroid/graphics/Bitmap;", "getBitmap", "()Landroid/graphics/Bitmap;", "isSampled", "", "()Z", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MemoryCache.kt */
    public interface Value {
        Bitmap getBitmap();

        boolean isSampled();
    }

    void clearMemory();

    Value get(Key key);

    void invalidate(Key key);

    int maxSize();

    void set(Key key, Bitmap bitmap, boolean z);

    int size();

    void trimMemory(int i);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¨\u0006\r"}, d2 = {"Lcoil/memory/MemoryCache$Companion;", "", "()V", "invoke", "Lcoil/memory/MemoryCache;", "weakMemoryCache", "Lcoil/memory/WeakMemoryCache;", "referenceCounter", "Lcoil/memory/BitmapReferenceCounter;", "maxSize", "", "logger", "Lcoil/util/Logger;", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MemoryCache.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final MemoryCache invoke(WeakMemoryCache weakMemoryCache, BitmapReferenceCounter bitmapReferenceCounter, int i, Logger logger) {
            Intrinsics.checkParameterIsNotNull(weakMemoryCache, "weakMemoryCache");
            Intrinsics.checkParameterIsNotNull(bitmapReferenceCounter, "referenceCounter");
            if (i > 0) {
                return new RealMemoryCache(weakMemoryCache, bitmapReferenceCounter, i, logger);
            }
            if (weakMemoryCache instanceof RealWeakMemoryCache) {
                return new ForwardingMemoryCache(weakMemoryCache);
            }
            return EmptyMemoryCache.INSTANCE;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B/\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001e"}, d2 = {"Lcoil/memory/MemoryCache$Key;", "", "baseKey", "", "parameters", "Lcoil/request/Parameters;", "(Ljava/lang/String;Lcoil/request/Parameters;)V", "transformations", "", "Lcoil/transform/Transformation;", "size", "Lcoil/size/Size;", "(Ljava/lang/String;Ljava/util/List;Lcoil/size/Size;Lcoil/request/Parameters;)V", "getBaseKey", "()Ljava/lang/String;", "parameterKeys", "", "getParameterKeys", "()Ljava/util/Map;", "getSize", "()Lcoil/size/Size;", "transformationKeys", "getTransformationKeys", "()Ljava/util/List;", "equals", "", "other", "hashCode", "", "toString", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MemoryCache.kt */
    public static final class Key {
        private final String baseKey;
        private final Map<String, String> parameterKeys;
        private final Size size;
        private final List<String> transformationKeys;

        public final String getBaseKey() {
            return this.baseKey;
        }

        public final List<String> getTransformationKeys() {
            return this.transformationKeys;
        }

        public final Size getSize() {
            return this.size;
        }

        public final Map<String, String> getParameterKeys() {
            return this.parameterKeys;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Key(String str, Parameters parameters, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? Parameters.EMPTY : parameters);
        }

        public Key(String str, Parameters parameters) {
            Intrinsics.checkParameterIsNotNull(str, "baseKey");
            Intrinsics.checkParameterIsNotNull(parameters, "parameters");
            this.baseKey = str;
            this.transformationKeys = CollectionsKt.emptyList();
            this.size = null;
            this.parameterKeys = parameters.cacheKeys();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Key(String str, List list, Size size2, Parameters parameters, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (List<? extends Transformation>) list, size2, (i & 8) != 0 ? Parameters.EMPTY : parameters);
        }

        public Key(String str, List<? extends Transformation> list, Size size2, Parameters parameters) {
            Intrinsics.checkParameterIsNotNull(str, "baseKey");
            Intrinsics.checkParameterIsNotNull(list, "transformations");
            Intrinsics.checkParameterIsNotNull(size2, "size");
            Intrinsics.checkParameterIsNotNull(parameters, "parameters");
            this.baseKey = str;
            if (list.isEmpty()) {
                this.transformationKeys = CollectionsKt.emptyList();
                this.size = null;
            } else {
                ArrayList arrayList = new ArrayList(list.size());
                int size3 = list.size();
                for (int i = 0; i < size3; i++) {
                    arrayList.add(((Transformation) list.get(i)).key());
                }
                this.transformationKeys = arrayList;
                this.size = size2;
            }
            this.parameterKeys = parameters.cacheKeys();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Key) {
                Key key = (Key) obj;
                if (!Intrinsics.areEqual((Object) this.baseKey, (Object) key.baseKey) || !Intrinsics.areEqual((Object) this.transformationKeys, (Object) key.transformationKeys) || !Intrinsics.areEqual((Object) this.size, (Object) key.size) || !Intrinsics.areEqual((Object) this.parameterKeys, (Object) key.parameterKeys)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode = ((this.baseKey.hashCode() * 31) + this.transformationKeys.hashCode()) * 31;
            Size size2 = this.size;
            return ((hashCode + (size2 != null ? size2.hashCode() : 0)) * 31) + this.parameterKeys.hashCode();
        }

        public String toString() {
            return "MemoryCache.Key(baseKey='" + this.baseKey + "', transformationKeys=" + this.transformationKeys + ", size=" + this.size + ", parameterKeys=" + this.parameterKeys + ASCIIPropertyListParser.ARRAY_END_TOKEN;
        }
    }
}
