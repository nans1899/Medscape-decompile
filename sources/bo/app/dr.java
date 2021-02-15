package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.json.JSONException;

public class dr implements dl {
    private static final String a = AppboyLogger.getAppboyLogTag(dr.class);
    private boolean b = false;
    private final SharedPreferences c;

    public dr(Context context, String str, String str2) {
        this.c = context.getSharedPreferences("com.appboy.storage.appboy_event_storage" + StringUtils.getCacheFileSuffix(context, str, str2), 0);
    }

    public void a(bz bzVar) {
        if (this.b) {
            String str = a;
            AppboyLogger.w(str, "Storage provider is closed. Not adding event: " + bzVar);
            return;
        }
        SharedPreferences.Editor edit = this.c.edit();
        edit.putString(bzVar.d(), bzVar.e());
        edit.apply();
    }

    public Collection<bz> a() {
        if (this.b) {
            AppboyLogger.w(a, "Storage provider is closed. Not getting all events.");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.c.getAll().entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            try {
                arrayList.add(cf.g(str2, str));
            } catch (JSONException unused) {
                String str3 = a;
                AppboyLogger.e(str3, "Could not create AppboyEvent from [serialized event string=" + str2 + ", unique identifier=" + str + "] ... Deleting!");
                a(str);
            }
        }
        return arrayList;
    }

    public void b(bz bzVar) {
        if (this.b) {
            String str = a;
            AppboyLogger.w(str, "Storage provider is closed. Not deleting event: " + bzVar);
            return;
        }
        String str2 = a;
        AppboyLogger.d(str2, "Deleting event from storage with uid " + bzVar.d(), false);
        a(bzVar.d());
    }

    public void b() {
        AppboyLogger.w(a, "Setting this provider to closed.");
        this.b = true;
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        SharedPreferences.Editor edit = this.c.edit();
        edit.remove(str);
        edit.apply();
    }
}
