package net.media.android.bidder.base.gson;

import com.mnet.gson.e;
import com.mnet.gson.f;
import java.util.Map;
import mnetinternal.g;
import net.media.android.bidder.base.gson.adapter.EventJsonSerializer;
import net.media.android.bidder.base.gson.adapter.LoggerJsonSerializer;
import net.media.android.bidder.base.gson.adapter.MapSerializer;
import net.media.android.bidder.base.gson.adapter.Stag;
import net.media.android.bidder.base.models.internal.Event;
import net.media.android.bidder.base.models.internal.Logger;

public final class a {
    private static e a;

    public static void a() {
        if (a == null) {
            a = new f().a(Event.class, new EventJsonSerializer()).a(Logger.class, new LoggerJsonSerializer()).a(new g<Map<String, Object>>() {
            }.b(), new MapSerializer()).a(new Stag.Factory()).a(Double.class, new GsonFactory$1()).a(new GsonAdapterFactory()).a().b();
        }
    }

    public static e b() {
        if (a == null) {
            a();
        }
        return a;
    }
}
