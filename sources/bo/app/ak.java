package bo.app;

import com.appboy.models.IInAppMessage;
import org.json.JSONObject;

public final class ak {
    private final IInAppMessage a;
    private final String b;
    private final ek c;

    public ak(ek ekVar, IInAppMessage iInAppMessage, String str) {
        this.b = str;
        if (iInAppMessage != null) {
            this.a = iInAppMessage;
            this.c = ekVar;
            return;
        }
        throw null;
    }

    public ek a() {
        return this.c;
    }

    public IInAppMessage b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String toString() {
        return ed.a((JSONObject) this.a.forJsonPut()) + "\nTriggered Action Id: " + this.c.b() + "\nUser Id: " + this.b;
    }
}
