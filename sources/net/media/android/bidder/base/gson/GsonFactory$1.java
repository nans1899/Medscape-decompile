package net.media.android.bidder.base.gson;

import com.mnet.gson.k;
import com.mnet.gson.q;
import com.mnet.gson.r;
import com.mnet.gson.s;
import java.lang.reflect.Type;

class GsonFactory$1 implements s<Double> {
    GsonFactory$1() {
    }

    public k a(Double d, Type type, r rVar) {
        if (d.doubleValue() == ((double) d.longValue())) {
            return new q((Number) Long.valueOf(d.longValue()));
        }
        return new q((Number) d);
    }
}
