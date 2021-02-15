package bo.app;

import com.appboy.models.IPutIntoJson;

public class ce implements IPutIntoJson<String> {
    private final String a;

    public ce(String str) {
        this.a = str;
    }

    public String toString() {
        return this.a;
    }

    /* renamed from: a */
    public String forJsonPut() {
        return this.a;
    }
}
