package net.media.android.bidder.base.models;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.g;
import net.media.android.bidder.base.models.MNetUser;

public final class StagFactory implements w {
    public <T> v<T> create(e eVar, g<T> gVar) {
        Class<? super T> a = gVar.a();
        if (a == a.class) {
            return new b(eVar);
        }
        if (a == MNetUser.class) {
            return new MNetUser.TypeAdapter(eVar);
        }
        return null;
    }
}
