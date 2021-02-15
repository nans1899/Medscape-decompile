package bo.app;

import com.appboy.support.AppboyLogger;
import java.util.List;
import org.json.JSONArray;

public abstract class fb implements eq {
    private static final String b = AppboyLogger.getAppboyLogTag(fb.class);
    protected List<eq> a;

    protected fb(List<eq> list) {
        this.a = list;
    }

    /* renamed from: a */
    public JSONArray forJsonPut() {
        JSONArray jSONArray = new JSONArray();
        try {
            for (eq forJsonPut : this.a) {
                jSONArray.put(forJsonPut.forJsonPut());
            }
        } catch (Exception e) {
            AppboyLogger.e(b, "Caught exception creating Json.", e);
        }
        return jSONArray;
    }
}
