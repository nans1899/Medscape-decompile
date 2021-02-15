package bo.app;

import org.json.JSONArray;
import org.json.JSONObject;

public class cm {
    private final long a;
    private final long b;
    private final boolean c;
    private final JSONArray d;

    public cm(JSONObject jSONObject) {
        this.a = jSONObject.optLong("last_card_updated_at", -1);
        this.b = jSONObject.optLong("last_full_sync_at", -1);
        this.c = jSONObject.optBoolean("full_sync", false);
        this.d = jSONObject.optJSONArray("cards");
    }

    public cm(String str) {
        this.c = false;
        this.a = -1;
        this.b = -1;
        this.d = new JSONArray().put(new JSONObject(str));
    }

    public long a() {
        return this.b;
    }

    public long b() {
        return this.a;
    }

    public boolean c() {
        return this.c;
    }

    public JSONArray d() {
        return this.d;
    }
}
