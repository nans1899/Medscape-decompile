package net.media.android.bidder.base.gson.adapter;

import com.mnet.gson.v;
import mnetinternal.h;
import mnetinternal.i;
import mnetinternal.j;

public final class a extends v<String> {
    /* renamed from: a */
    public String read(h hVar) {
        if (hVar.f() != i.NULL) {
            return hVar.h();
        }
        hVar.j();
        return "";
    }

    /* renamed from: a */
    public void write(j jVar, String str) {
        if (str == null) {
            jVar.b("");
        } else {
            jVar.b(str);
        }
    }
}
