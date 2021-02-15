package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class bg implements bs {
    final SharedPreferences a;

    public bg(Context context) {
        this.a = context.getSharedPreferences("com.appboy.device", 0);
    }

    public String a() {
        String str = null;
        String string = this.a.getString("device_id", (String) null);
        if (!c()) {
            str = string;
        }
        if (str == null) {
            String uuid = UUID.randomUUID().toString();
            a(uuid);
            return uuid;
        }
        if (!this.a.contains("persistent_device_id")) {
            a(str);
        }
        return str;
    }

    private void a(String str) {
        SharedPreferences.Editor edit = this.a.edit();
        edit.putString("device_id", str);
        edit.putString("persistent_device_id", b());
        edit.apply();
    }

    private boolean c() {
        if (!this.a.contains("persistent_device_id")) {
            return false;
        }
        return !b().equals(this.a.getString("persistent_device_id", ""));
    }

    static String b() {
        return String.valueOf(722989291);
    }
}
