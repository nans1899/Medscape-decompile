package bo.app;

import com.appboy.support.AppboyLogger;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class dv {
    private static final String a = AppboyLogger.getAppboyLogTag(dv.class);

    public static <TargetEnum extends Enum<TargetEnum>> TargetEnum a(String str, Class<TargetEnum> cls) {
        return Enum.valueOf(cls, str);
    }

    public static Set<String> a(EnumSet enumSet) {
        HashSet hashSet = new HashSet();
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().toString());
        }
        return hashSet;
    }

    public static <TargetEnum extends Enum<TargetEnum>> EnumSet<TargetEnum> a(Class<TargetEnum> cls, Set<String> set) {
        EnumSet<TargetEnum> noneOf = EnumSet.noneOf(cls);
        for (String next : set) {
            try {
                noneOf.add(a(next.toUpperCase(Locale.US), cls));
            } catch (Exception unused) {
                String str = a;
                AppboyLogger.e(str, "Failed to create valid device key enum from string: " + next);
            }
        }
        return noneOf;
    }
}
