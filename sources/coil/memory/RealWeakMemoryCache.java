package coil.memory;

import android.graphics.Bitmap;
import coil.memory.MemoryCache;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 %2\u00020\u0001:\u0003%&'B\u0005¢\u0006\u0002\u0010\u0002J\r\u0010\u0014\u001a\u00020\u0015H\u0001¢\u0006\u0002\b\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u0005H\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u0005H\u0016J(\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000eH\u0016J\u0010\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\u000eH\u0016RX\u0010\u0003\u001a>\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\u0004j\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b`\t8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u00020\u000e8\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000f\u0010\u0002\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006("}, d2 = {"Lcoil/memory/RealWeakMemoryCache;", "Lcoil/memory/WeakMemoryCache;", "()V", "cache", "Ljava/util/HashMap;", "Lcoil/memory/MemoryCache$Key;", "Ljava/util/ArrayList;", "Lcoil/memory/RealWeakMemoryCache$WeakValue;", "Lkotlin/collections/ArrayList;", "Lkotlin/collections/HashMap;", "cache$annotations", "getCache$coil_base_release", "()Ljava/util/HashMap;", "operationsSinceCleanUp", "", "operationsSinceCleanUp$annotations", "getOperationsSinceCleanUp$coil_base_release", "()I", "setOperationsSinceCleanUp$coil_base_release", "(I)V", "cleanUp", "", "cleanUp$coil_base_release", "cleanUpIfNecessary", "clearMemory", "get", "Lcoil/memory/MemoryCache$Value;", "key", "invalidate", "bitmap", "Landroid/graphics/Bitmap;", "set", "isSampled", "", "size", "trimMemory", "level", "Companion", "StrongValue", "WeakValue", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: WeakMemoryCache.kt */
public final class RealWeakMemoryCache implements WeakMemoryCache {
    private static final int CLEAN_UP_INTERVAL = 10;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HashMap<MemoryCache.Key, ArrayList<WeakValue>> cache = new HashMap<>();
    private int operationsSinceCleanUp;

    public static /* synthetic */ void cache$annotations() {
    }

    public static /* synthetic */ void operationsSinceCleanUp$annotations() {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcoil/memory/RealWeakMemoryCache$Companion;", "", "()V", "CLEAN_UP_INTERVAL", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: WeakMemoryCache.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final HashMap<MemoryCache.Key, ArrayList<WeakValue>> getCache$coil_base_release() {
        return this.cache;
    }

    public final int getOperationsSinceCleanUp$coil_base_release() {
        return this.operationsSinceCleanUp;
    }

    public final void setOperationsSinceCleanUp$coil_base_release(int i) {
        this.operationsSinceCleanUp = i;
    }

    public MemoryCache.Value get(MemoryCache.Key key) {
        StrongValue strongValue;
        Intrinsics.checkParameterIsNotNull(key, "key");
        ArrayList arrayList = this.cache.get(key);
        StrongValue strongValue2 = null;
        if (arrayList == null) {
            return null;
        }
        Intrinsics.checkExpressionValueIsNotNull(arrayList, "cache[key] ?: return null");
        List list = arrayList;
        int i = 0;
        int size = list.size();
        while (true) {
            if (i >= size) {
                break;
            }
            WeakValue weakValue = (WeakValue) list.get(i);
            Bitmap bitmap = (Bitmap) weakValue.getReference().get();
            if (bitmap != null) {
                Intrinsics.checkExpressionValueIsNotNull(bitmap, "bitmap");
                strongValue = new StrongValue(bitmap, weakValue.isSampled());
            } else {
                strongValue = null;
            }
            if (strongValue != null) {
                strongValue2 = strongValue;
                break;
            }
            i++;
        }
        cleanUpIfNecessary();
        return strongValue2;
    }

    public void set(MemoryCache.Key key, Bitmap bitmap, boolean z, int i) {
        ArrayList arrayList;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        ArrayList arrayList2 = this.cache.get(key);
        if (arrayList2 != null) {
            arrayList = arrayList2;
        } else {
            arrayList = new ArrayList();
        }
        RealWeakMemoryCache realWeakMemoryCache = this;
        WeakValue weakValue = new WeakValue(System.identityHashCode(bitmap), new WeakReference(bitmap), z, i);
        int i2 = 0;
        Collection collection = arrayList;
        int size = collection.size();
        while (true) {
            if (i2 >= size) {
                collection.add(weakValue);
                break;
            } else if (i >= ((WeakValue) arrayList.get(i2)).getSize()) {
                arrayList.add(i2, weakValue);
                break;
            } else {
                i2++;
            }
        }
        if (arrayList2 == null) {
            this.cache.put(key, arrayList);
        }
        cleanUpIfNecessary();
    }

    public void invalidate(MemoryCache.Key key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        MemoryCache.Value value = get(key);
        if (value != null) {
            invalidate(value.getBitmap());
        }
    }

    public void clearMemory() {
        this.cache.clear();
    }

    public void trimMemory(int i) {
        if (i >= 10 && i != 20) {
            cleanUp$coil_base_release();
        }
    }

    private final void cleanUpIfNecessary() {
        int i = this.operationsSinceCleanUp;
        this.operationsSinceCleanUp = i + 1;
        if (i >= 10) {
            cleanUp$coil_base_release();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0032, code lost:
        r2 = r2.getReference();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void cleanUp$coil_base_release() {
        /*
            r10 = this;
            r0 = 0
            r10.operationsSinceCleanUp = r0
            java.util.HashMap<coil.memory.MemoryCache$Key, java.util.ArrayList<coil.memory.RealWeakMemoryCache$WeakValue>> r1 = r10.cache
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x000d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x008b
            java.lang.Object r2 = r1.next()
            java.lang.String r3 = "iterator.next()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            r3 = r2
            java.util.Collection r3 = (java.util.Collection) r3
            int r3 = r3.size()
            r4 = 1
            if (r3 > r4) goto L_0x0046
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r2 = kotlin.collections.CollectionsKt.firstOrNull(r2)
            coil.memory.RealWeakMemoryCache$WeakValue r2 = (coil.memory.RealWeakMemoryCache.WeakValue) r2
            if (r2 == 0) goto L_0x003f
            java.lang.ref.WeakReference r2 = r2.getReference()
            if (r2 == 0) goto L_0x003f
            java.lang.Object r2 = r2.get()
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            goto L_0x0040
        L_0x003f:
            r2 = 0
        L_0x0040:
            if (r2 != 0) goto L_0x000d
            r1.remove()
            goto L_0x000d
        L_0x0046:
            int r3 = android.os.Build.VERSION.SDK_INT
            r5 = 24
            if (r3 < r5) goto L_0x0054
            coil.memory.RealWeakMemoryCache$cleanUp$1 r3 = coil.memory.RealWeakMemoryCache$cleanUp$1.INSTANCE
            java.util.function.Predicate r3 = (java.util.function.Predicate) r3
            r2.removeIf(r3)
            goto L_0x0081
        L_0x0054:
            r3 = r2
            java.util.List r3 = (java.util.List) r3
            r5 = r3
            java.util.Collection r5 = (java.util.Collection) r5
            int r5 = r5.size()
            r6 = 0
            r7 = 0
        L_0x0060:
            if (r6 >= r5) goto L_0x0081
            int r8 = r6 - r7
            java.lang.Object r9 = r3.get(r8)
            coil.memory.RealWeakMemoryCache$WeakValue r9 = (coil.memory.RealWeakMemoryCache.WeakValue) r9
            java.lang.ref.WeakReference r9 = r9.getReference()
            java.lang.Object r9 = r9.get()
            if (r9 != 0) goto L_0x0076
            r9 = 1
            goto L_0x0077
        L_0x0076:
            r9 = 0
        L_0x0077:
            if (r9 == 0) goto L_0x007e
            r3.remove(r8)
            int r7 = r7 + 1
        L_0x007e:
            int r6 = r6 + 1
            goto L_0x0060
        L_0x0081:
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x000d
            r1.remove()
            goto L_0x000d
        L_0x008b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.memory.RealWeakMemoryCache.cleanUp$coil_base_release():void");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u0011"}, d2 = {"Lcoil/memory/RealWeakMemoryCache$WeakValue;", "", "identityHashCode", "", "reference", "Ljava/lang/ref/WeakReference;", "Landroid/graphics/Bitmap;", "isSampled", "", "size", "(ILjava/lang/ref/WeakReference;ZI)V", "getIdentityHashCode", "()I", "()Z", "getReference", "()Ljava/lang/ref/WeakReference;", "getSize", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: WeakMemoryCache.kt */
    public static final class WeakValue {
        private final int identityHashCode;
        private final boolean isSampled;
        private final WeakReference<Bitmap> reference;
        private final int size;

        public WeakValue(int i, WeakReference<Bitmap> weakReference, boolean z, int i2) {
            Intrinsics.checkParameterIsNotNull(weakReference, "reference");
            this.identityHashCode = i;
            this.reference = weakReference;
            this.isSampled = z;
            this.size = i2;
        }

        public final int getIdentityHashCode() {
            return this.identityHashCode;
        }

        public final WeakReference<Bitmap> getReference() {
            return this.reference;
        }

        public final boolean isSampled() {
            return this.isSampled;
        }

        public final int getSize() {
            return this.size;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\t¨\u0006\n"}, d2 = {"Lcoil/memory/RealWeakMemoryCache$StrongValue;", "Lcoil/memory/MemoryCache$Value;", "bitmap", "Landroid/graphics/Bitmap;", "isSampled", "", "(Landroid/graphics/Bitmap;Z)V", "getBitmap", "()Landroid/graphics/Bitmap;", "()Z", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: WeakMemoryCache.kt */
    private static final class StrongValue implements MemoryCache.Value {
        private final Bitmap bitmap;
        private final boolean isSampled;

        public StrongValue(Bitmap bitmap2, boolean z) {
            Intrinsics.checkParameterIsNotNull(bitmap2, "bitmap");
            this.bitmap = bitmap2;
            this.isSampled = z;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public boolean isSampled() {
            return this.isSampled;
        }
    }

    public void invalidate(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int identityHashCode = System.identityHashCode(bitmap);
        Collection<ArrayList<WeakValue>> values = this.cache.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "cache.values");
        Iterator it = values.iterator();
        loop0:
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ArrayList arrayList = (ArrayList) it.next();
            int i = 0;
            Intrinsics.checkExpressionValueIsNotNull(arrayList, "values");
            int size = arrayList.size();
            while (true) {
                if (i < size) {
                    if (((WeakValue) arrayList.get(i)).getIdentityHashCode() == identityHashCode) {
                        arrayList.remove(i);
                        break loop0;
                    }
                    i++;
                }
            }
        }
        cleanUpIfNecessary();
    }
}
