package mnetinternal;

import android.view.View;
import com.dd.plist.ASCIIPropertyListParser;
import java.util.HashMap;
import java.util.Map;

public final class bm {
    private int a = -1;
    private String b;
    private String c;
    private String d;
    private String e;
    private transient bh f;
    private transient View g;
    private int h = -1;
    private int i = -1;
    private bm j;
    private int k = -1;
    private Map<String, Object> l = new HashMap();

    public bm(View view, bh bhVar) {
        this.g = view;
        this.f = bhVar;
        this.b = ca.a(view, this.a);
        this.c = view.getClass().getName();
        this.d = ca.c(view);
        this.e = ca.b(view);
    }

    public void a(Map<String, Object> map) {
        this.l = map;
    }

    public void a(int i2) {
        this.a = i2;
        this.b = ca.a(this.g, i2);
    }

    public void a(bm bmVar) {
        this.j = bmVar;
    }

    public int a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public View c() {
        return this.g;
    }

    public int d() {
        return this.i;
    }

    public int e() {
        return this.h;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004d, code lost:
        if (mnetinternal.br.a(r9.g) == false) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject f() {
        /*
            r9 = this;
            java.lang.String r0 = "view_rect_normalized"
            java.lang.String r1 = "view_type"
            java.lang.String r2 = "view_class"
            java.lang.String r3 = "resource_name"
            java.lang.String r4 = "view_id"
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            int r6 = r9.a     // Catch:{ JSONException -> 0x0099 }
            r5.put(r4, r6)     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r6 = r9.b     // Catch:{ JSONException -> 0x0099 }
            r5.put(r3, r6)     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r6 = r9.c     // Catch:{ JSONException -> 0x0099 }
            r5.put(r2, r6)     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r6 = r9.d     // Catch:{ JSONException -> 0x0099 }
            r5.put(r1, r6)     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r6 = r9.e     // Catch:{ JSONException -> 0x0099 }
            r5.put(r0, r6)     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r6 = "properties"
            java.util.Map<java.lang.String, java.lang.Object> r7 = r9.l     // Catch:{ JSONException -> 0x0099 }
            r5.put(r6, r7)     // Catch:{ JSONException -> 0x0099 }
            android.view.View r6 = r9.g     // Catch:{ JSONException -> 0x0099 }
            boolean r6 = r6 instanceof android.widget.AdapterView     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r7 = "child_offset"
            if (r6 != 0) goto L_0x004f
            android.view.View r6 = r9.g     // Catch:{ JSONException -> 0x0099 }
            boolean r6 = mnetinternal.bv.a(r6)     // Catch:{ JSONException -> 0x0099 }
            if (r6 != 0) goto L_0x004f
            android.view.View r6 = r9.g     // Catch:{ JSONException -> 0x0099 }
            boolean r6 = mnetinternal.bt.a(r6)     // Catch:{ JSONException -> 0x0099 }
            if (r6 != 0) goto L_0x004f
            android.view.View r6 = r9.g     // Catch:{ JSONException -> 0x0099 }
            boolean r6 = mnetinternal.br.a(r6)     // Catch:{ JSONException -> 0x0099 }
            if (r6 == 0) goto L_0x0062
        L_0x004f:
            java.lang.String r6 = "start_offset"
            int r8 = r9.i     // Catch:{ JSONException -> 0x0099 }
            r5.put(r6, r8)     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r6 = "page_count"
            int r8 = r9.h     // Catch:{ JSONException -> 0x0099 }
            r5.put(r6, r8)     // Catch:{ JSONException -> 0x0099 }
            int r6 = r9.k     // Catch:{ JSONException -> 0x0099 }
            r5.put(r7, r6)     // Catch:{ JSONException -> 0x0099 }
        L_0x0062:
            mnetinternal.bm r6 = r9.j     // Catch:{ JSONException -> 0x0099 }
            if (r6 == 0) goto L_0x00a3
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0099 }
            r6.<init>()     // Catch:{ JSONException -> 0x0099 }
            mnetinternal.bm r8 = r9.j     // Catch:{ JSONException -> 0x0099 }
            int r8 = r8.a     // Catch:{ JSONException -> 0x0099 }
            r6.put(r4, r8)     // Catch:{ JSONException -> 0x0099 }
            mnetinternal.bm r4 = r9.j     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r4 = r4.b     // Catch:{ JSONException -> 0x0099 }
            r6.put(r3, r4)     // Catch:{ JSONException -> 0x0099 }
            mnetinternal.bm r3 = r9.j     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r3 = r3.c     // Catch:{ JSONException -> 0x0099 }
            r6.put(r2, r3)     // Catch:{ JSONException -> 0x0099 }
            mnetinternal.bm r2 = r9.j     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r2 = r2.d     // Catch:{ JSONException -> 0x0099 }
            r6.put(r1, r2)     // Catch:{ JSONException -> 0x0099 }
            mnetinternal.bm r1 = r9.j     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r1 = r1.e     // Catch:{ JSONException -> 0x0099 }
            r6.put(r0, r1)     // Catch:{ JSONException -> 0x0099 }
            int r0 = r9.k     // Catch:{ JSONException -> 0x0099 }
            r6.put(r7, r0)     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r0 = "parent"
            r5.put(r0, r6)     // Catch:{ JSONException -> 0x0099 }
            goto L_0x00a3
        L_0x0099:
            r0 = move-exception
            java.lang.String r1 = r0.getMessage()
            java.lang.String r2 = "##ViewInfo"
            net.media.android.bidder.base.logging.Logger.error(r2, r1, r0)
        L_0x00a3:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: mnetinternal.bm.f():org.json.JSONObject");
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bm bmVar = (bm) obj;
        if (bmVar.b.equals(this.b) && bmVar.e.equals(this.e) && bmVar.c.equals(this.c) && bmVar.d.equals(this.d) && bmVar.a == this.a) {
            return true;
        }
        return false;
    }

    public void b(int i2) {
        this.h = i2;
    }

    public void c(int i2) {
        this.i = i2;
    }

    public String toString() {
        return "ViewInfo{mViewId=" + this.a + ", mShadowActivity=" + this.f.a().getLocalClassName() + ", mResourceName='" + this.b + '\'' + ", mViewClass='" + this.c + '\'' + ", mViewType='" + this.d + '\'' + ", mViewRectNormalized='" + this.e + '\'' + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
