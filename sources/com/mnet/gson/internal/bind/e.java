package com.mnet.gson.internal.bind;

import com.mnet.gson.internal.f;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.util.ArrayList;
import mnetinternal.h;
import mnetinternal.j;

public final class e extends v<Object> {
    public static final w a = new ObjectTypeAdapter$1();
    private final com.mnet.gson.e b;

    e(com.mnet.gson.e eVar) {
        this.b = eVar;
    }

    /* renamed from: com.mnet.gson.internal.bind.e$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                mnetinternal.i[] r0 = mnetinternal.i.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                mnetinternal.i r1 = mnetinternal.i.BEGIN_ARRAY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                mnetinternal.i r1 = mnetinternal.i.BEGIN_OBJECT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                mnetinternal.i r1 = mnetinternal.i.STRING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                mnetinternal.i r1 = mnetinternal.i.NUMBER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x003e }
                mnetinternal.i r1 = mnetinternal.i.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0049 }
                mnetinternal.i r1 = mnetinternal.i.NULL     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mnet.gson.internal.bind.e.AnonymousClass1.<clinit>():void");
        }
    }

    public Object read(h hVar) {
        switch (AnonymousClass1.a[hVar.f().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                hVar.a();
                while (hVar.e()) {
                    arrayList.add(read(hVar));
                }
                hVar.b();
                return arrayList;
            case 2:
                f fVar = new f();
                hVar.c();
                while (hVar.e()) {
                    fVar.put(hVar.g(), read(hVar));
                }
                hVar.d();
                return fVar;
            case 3:
                return hVar.h();
            case 4:
                return Double.valueOf(hVar.k());
            case 5:
                return Boolean.valueOf(hVar.i());
            case 6:
                hVar.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void write(j jVar, Object obj) {
        if (obj == null) {
            jVar.f();
            return;
        }
        v<?> a2 = this.b.a(obj.getClass());
        if (a2 instanceof e) {
            jVar.d();
            jVar.e();
            return;
        }
        a2.write(jVar, obj);
    }
}
