package bo.app;

import com.appboy.models.outgoing.AppboyProperties;
import com.google.firebase.analytics.FirebaseAnalytics;

public class fo extends fs {
    private String a;

    public String b() {
        return FirebaseAnalytics.Event.PURCHASE;
    }

    public fo(String str, AppboyProperties appboyProperties, bz bzVar) {
        super(appboyProperties, bzVar);
        this.a = str;
    }

    public String a() {
        return this.a;
    }
}
