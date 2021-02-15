package mnetinternal;

import android.content.SharedPreferences;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.common.Constants;

public final class cb {
    private static volatile cb a;
    private SharedPreferences b = MNet.getContext().getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, 0);

    private cb() {
    }

    public static cb a() {
        cb cbVar = a;
        if (cbVar == null) {
            synchronized (cb.class) {
                cbVar = a;
                if (cbVar == null) {
                    cbVar = new cb();
                    a = cbVar;
                }
            }
        }
        return cbVar;
    }

    public void a(String str, String str2) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public void a(String str, boolean z) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public void a(String str, int i) {
        SharedPreferences.Editor edit = this.b.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public int b(String str, int i) {
        return this.b.getInt(str, i);
    }

    public String a(String str) {
        return this.b.getString(str, (String) null);
    }

    public boolean b(String str) {
        return this.b.getBoolean(str, false);
    }
}
