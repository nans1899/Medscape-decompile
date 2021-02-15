package net.media.android.bidder.base.gson.adapter;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import java.util.HashMap;
import mnetinternal.g;
import net.media.android.bidder.base.models.StagFactory;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.Publisher;

public final class Stag {

    public static class Factory implements w {
        private final HashMap<String, Integer> a = new HashMap<>(2);
        private final w[] b = new w[2];

        private static <T> String a(Class<T> cls) {
            String name = cls.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf == -1) {
                return null;
            }
            return name.substring(0, lastIndexOf);
        }

        private static w a(int i) {
            if (i == 0) {
                return new StagFactory();
            }
            if (i != 1) {
                return null;
            }
            return new net.media.android.bidder.base.models.internal.StagFactory();
        }

        private w b(int i) {
            w wVar = this.b[i];
            if (wVar != null) {
                return wVar;
            }
            w a2 = a(i);
            this.b[i] = a2;
            return a2;
        }

        private w a(Class<?> cls, String str, int i) {
            String a2 = a(cls);
            this.a.put(a2, Integer.valueOf(i));
            if (str.equals(a2)) {
                return b(i);
            }
            return null;
        }

        private synchronized w a(String str) {
            Integer num = this.a.get(str);
            if (num != null) {
                return b(num.intValue());
            }
            int size = this.a.size();
            if (size == 0) {
                w a2 = a(a.class, str, 0);
                if (a2 != null) {
                    return a2;
                }
            } else if (size != 1) {
                return null;
            }
            w a3 = a(Publisher.class, str, 1);
            if (a3 != null) {
                return a3;
            }
            return null;
        }

        public <T> v<T> create(e eVar, g<T> gVar) {
            w a2;
            String a3 = a(gVar.a());
            if (a3 == null || (a2 = a(a3)) == null) {
                return null;
            }
            return a2.create(eVar, gVar);
        }
    }
}
