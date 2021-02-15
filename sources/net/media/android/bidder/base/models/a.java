package net.media.android.bidder.base.models;

import java.util.Map;
import mnetinternal.c;
import net.media.android.bidder.base.models.internal.AdDetails;
import net.media.android.bidder.base.models.internal.ServerDelays;

public final class a {
    @c(a = "ads")
    protected Map<String, AdDetails> a;
    @c(a = "ext")
    protected Map<String, Object> b;
    @c(a = "td")
    protected ServerDelays c;

    protected a() {
    }

    public Map<String, AdDetails> a() {
        Map<String, AdDetails> map = this.a;
        if (map != null && map.containsKey("ext")) {
            this.a.remove("ext");
        }
        return this.a;
    }

    public AdDetails a(String str) {
        Map<String, AdDetails> map = this.a;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public Map<String, Object> b() {
        return this.b;
    }

    public boolean c() {
        Map<String, Object> map = this.b;
        return map != null && map.containsKey("cc") && ((Boolean) this.b.get("cc")).booleanValue();
    }

    public String d() {
        Map<String, Object> map = this.b;
        if (map == null || !map.containsKey("prediction_id")) {
            return null;
        }
        return (String) this.b.get("prediction_id");
    }

    public ServerDelays e() {
        return this.c;
    }
}
