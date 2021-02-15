package net.media.android.bidder.base.gson;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.g;
import net.media.android.bidder.base.gson.adapter.a;

final class GsonAdapterFactory implements w {
    GsonAdapterFactory() {
    }

    public <T> v<T> create(e eVar, g<T> gVar) {
        if (gVar.a() != String.class) {
            return null;
        }
        return new a();
    }
}
