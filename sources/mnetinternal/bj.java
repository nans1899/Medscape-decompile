package mnetinternal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.Scopes;
import net.media.android.bidder.base.logging.Logger;

public final class bj {
    private String a;
    private String b;
    private String c;
    private String d;

    public bj(Intent intent) {
        this.a = intent.getDataString();
        this.b = intent.getType();
        this.d = intent.getAction();
        this.c = a(intent.getExtras());
    }

    private String a(Bundle bundle) {
        String str;
        if (bundle == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : bundle.keySet()) {
            if (!a(str2)) {
                sb.append(str2);
                sb.append("=");
                Object obj = bundle.get(str2);
                if (obj instanceof Parcelable) {
                    try {
                        if (obj.getClass().getDeclaredMethod("toString", new Class[0]) != null) {
                            str = String.valueOf(bundle.get(str2));
                        }
                    } catch (Exception unused) {
                    }
                    str = "null";
                } else {
                    str = String.valueOf(bundle.get(str2));
                }
                sb.append(str);
                sb.append(";");
            }
        }
        if (sb.length() == 0) {
            return "null";
        }
        String sb2 = sb.toString();
        return sb2.substring(0, sb2.length() - 1);
    }

    private boolean a(String str) {
        return ((str.hashCode() == -309425751 && str.equals(Scopes.PROFILE)) ? (char) 0 : 65535) == 0;
    }

    private String c() {
        StringBuilder sb = new StringBuilder();
        sb.append("data:");
        String str = this.a;
        if (str == null) {
            str = "null";
        }
        sb.append(str);
        sb.append(",");
        sb.append("action:");
        sb.append(this.d);
        sb.append(",");
        sb.append("type:");
        sb.append(this.b);
        sb.append(",");
        sb.append("extras:");
        sb.append(this.c);
        Logger.debug("##IntentModel", "Intent : " + sb.toString());
        return sb.toString();
    }

    public String a() {
        return bx.a(c());
    }

    public String b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        bj bjVar = (bj) obj;
        String str = this.a;
        if (str != null && !str.equals(bjVar.a)) {
            return false;
        }
        String str2 = this.d;
        if (str2 != null && !str2.equals(bjVar.d)) {
            return false;
        }
        String str3 = this.c;
        if (str3 != null && !str3.equals(bjVar.c)) {
            return false;
        }
        String str4 = this.b;
        if (str4 == null || str4.equals(bjVar.b)) {
            return true;
        }
        return false;
    }
}
