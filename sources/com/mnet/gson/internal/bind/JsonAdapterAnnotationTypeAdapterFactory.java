package com.mnet.gson.internal.bind;

import com.mnet.gson.e;
import com.mnet.gson.internal.c;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.b;
import mnetinternal.g;

public final class JsonAdapterAnnotationTypeAdapterFactory implements w {
    private final c a;

    public JsonAdapterAnnotationTypeAdapterFactory(c cVar) {
        this.a = cVar;
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        b bVar = (b) gVar.a().getAnnotation(b.class);
        if (bVar == null) {
            return null;
        }
        return a(this.a, eVar, gVar, bVar);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: com.mnet.gson.v<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: com.mnet.gson.v} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.mnet.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: com.mnet.gson.internal.bind.TreeTypeAdapter} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: com.mnet.gson.internal.bind.TreeTypeAdapter} */
    /* JADX WARNING: type inference failed for: r9v3, types: [com.mnet.gson.v<?>, com.mnet.gson.v] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.mnet.gson.v<?> a(com.mnet.gson.internal.c r9, com.mnet.gson.e r10, mnetinternal.g<?> r11, mnetinternal.b r12) {
        /*
            r8 = this;
            java.lang.Class r0 = r12.a()
            mnetinternal.g r0 = mnetinternal.g.b(r0)
            com.mnet.gson.internal.g r9 = r9.a(r0)
            java.lang.Object r9 = r9.a()
            boolean r0 = r9 instanceof com.mnet.gson.v
            if (r0 == 0) goto L_0x0017
            com.mnet.gson.v r9 = (com.mnet.gson.v) r9
            goto L_0x004d
        L_0x0017:
            boolean r0 = r9 instanceof com.mnet.gson.w
            if (r0 == 0) goto L_0x0022
            com.mnet.gson.w r9 = (com.mnet.gson.w) r9
            com.mnet.gson.v r9 = r9.create(r10, r11)
            goto L_0x004d
        L_0x0022:
            boolean r0 = r9 instanceof com.mnet.gson.s
            if (r0 != 0) goto L_0x0033
            boolean r1 = r9 instanceof com.mnet.gson.j
            if (r1 == 0) goto L_0x002b
            goto L_0x0033
        L_0x002b:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "@JsonAdapter value must be TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer reference."
            r9.<init>(r10)
            throw r9
        L_0x0033:
            r1 = 0
            if (r0 == 0) goto L_0x003b
            r0 = r9
            com.mnet.gson.s r0 = (com.mnet.gson.s) r0
            r3 = r0
            goto L_0x003c
        L_0x003b:
            r3 = r1
        L_0x003c:
            boolean r0 = r9 instanceof com.mnet.gson.j
            if (r0 == 0) goto L_0x0043
            r1 = r9
            com.mnet.gson.j r1 = (com.mnet.gson.j) r1
        L_0x0043:
            r4 = r1
            com.mnet.gson.internal.bind.TreeTypeAdapter r9 = new com.mnet.gson.internal.bind.TreeTypeAdapter
            r7 = 0
            r2 = r9
            r5 = r10
            r6 = r11
            r2.<init>(r3, r4, r5, r6, r7)
        L_0x004d:
            if (r9 == 0) goto L_0x0059
            boolean r10 = r12.b()
            if (r10 == 0) goto L_0x0059
            com.mnet.gson.v r9 = r9.nullSafe()
        L_0x0059:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mnet.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory.a(com.mnet.gson.internal.c, com.mnet.gson.e, mnetinternal.g, mnetinternal.b):com.mnet.gson.v");
    }
}
