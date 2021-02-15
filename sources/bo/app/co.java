package bo.app;

import android.net.Uri;
import com.appboy.Appboy;
import com.appboy.enums.SdkFlavor;
import com.appboy.models.response.ResponseError;
import com.appboy.support.AppboyLogger;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.medscape.android.cache.Cache;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class co extends cz implements cb, cv {
    private static final String b = AppboyLogger.getAppboyLogTag(co.class);
    private Long c;
    private String d;
    private String e;
    private String f;
    private ch g;
    private String h;
    private SdkFlavor i;
    private ck j;
    private cj k;
    private bx l;

    protected co(Uri uri, Map<String, String> map) {
        super(uri, map);
    }

    public Uri a() {
        return Appboy.getAppboyApiEndpoint(this.a);
    }

    public void a(String str) {
        this.d = str;
    }

    public ch c() {
        return this.g;
    }

    public void a(ch chVar) {
        this.g = chVar;
    }

    public void a(long j2) {
        this.c = Long.valueOf(j2);
    }

    public void b(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.f = str;
    }

    public void a(SdkFlavor sdkFlavor) {
        this.i = sdkFlavor;
    }

    public void d(String str) {
        this.h = str;
    }

    public ck d() {
        return this.j;
    }

    public void a(ck ckVar) {
        this.j = ckVar;
    }

    public void a(cj cjVar) {
        this.k = cjVar;
    }

    public cj e() {
        return this.k;
    }

    public void a(bx bxVar) {
        this.l = bxVar;
    }

    public bx f() {
        return this.l;
    }

    public void a(ad adVar, ResponseError responseError) {
        String str = b;
        AppboyLogger.e(str, "Error occurred while executing Braze request: " + responseError.getMessage());
    }

    public JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.h != null) {
                jSONObject.put("app_version", this.h);
            }
            if (this.d != null) {
                jSONObject.put("device_id", this.d);
            }
            if (this.c != null) {
                jSONObject.put(Cache.Caches.TIME, this.c);
            }
            if (this.e != null) {
                jSONObject.put("api_key", this.e);
            }
            if (this.f != null) {
                jSONObject.put("sdk_version", this.f);
            }
            if (this.g != null && !this.g.b()) {
                jSONObject.put("device", this.g.forJsonPut());
            }
            if (this.j != null && !this.j.b()) {
                jSONObject.put(JSONAPISpecConstants.ATTRIBUTES, this.j.forJsonPut());
            }
            if (this.l != null && !this.l.b()) {
                jSONObject.put(OmnitureConstants.CHANNEL, ed.a(this.l.a()));
            }
            if (this.i != null) {
                jSONObject.put("sdk_flavor", this.i.forJsonPut());
            }
            return jSONObject;
        } catch (JSONException e2) {
            AppboyLogger.w(b, "Experienced JSONException while retrieving parameters. Returning null.", e2);
            return null;
        }
    }

    public boolean h() {
        return b();
    }

    public boolean b() {
        ArrayList<cb> arrayList = new ArrayList<>();
        arrayList.add(this.g);
        arrayList.add(this.j);
        arrayList.add(this.l);
        for (cb cbVar : arrayList) {
            if (cbVar != null && !cbVar.b()) {
                return false;
            }
        }
        return true;
    }

    public void a(ad adVar) {
        ck ckVar = this.j;
        if (ckVar != null) {
            adVar.a(new am(ckVar), am.class);
        }
        ch chVar = this.g;
        if (chVar != null) {
            adVar.a(new ai(chVar), ai.class);
        }
    }

    public void a(Map<String, String> map) {
        map.put("X-Braze-Api-Key", this.e);
    }
}
