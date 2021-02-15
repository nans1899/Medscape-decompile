package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.support.StringUtils;

public class l {
    private final SharedPreferences a;

    public l(Context context) {
        this.a = context.getSharedPreferences("com.appboy.offline.storagemap", 0);
    }

    public String a() {
        return this.a.getString("last_user", "");
    }

    public void a(String str) {
        StringUtils.checkNotNullOrEmpty(str);
        SharedPreferences.Editor edit = this.a.edit();
        edit.putString("last_user", str);
        edit.apply();
    }

    public void b(String str) {
        StringUtils.checkNotNullOrEmpty(str);
        SharedPreferences.Editor edit = this.a.edit();
        edit.putString("default_user", str);
        edit.putString("last_user", str);
        edit.apply();
    }
}
