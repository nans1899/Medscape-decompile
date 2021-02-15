package bo.app;

import android.net.Uri;
import java.util.Map;
import net.bytebuddy.description.type.TypeDescription;

public abstract class cz implements cw {
    public final Uri a;
    private Map<String, String> b;

    protected cz(Uri uri, Map<String, String> map) {
        this.b = map;
        this.a = Uri.parse(uri + j());
    }

    public Uri a() {
        return this.a;
    }

    public String j() {
        Map<String, String> map = this.b;
        if (map == null || map.size() == 0) {
            return "";
        }
        String str = TypeDescription.Generic.OfWildcardType.SYMBOL;
        for (String next : this.b.keySet()) {
            str = str + next + "=" + this.b.get(next) + "&";
        }
        return str.substring(0, str.length() - 1);
    }
}
