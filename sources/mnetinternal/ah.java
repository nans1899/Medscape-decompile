package mnetinternal;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.BidResponse;
import net.media.android.bidder.base.models.internal.Config;
import net.media.android.bidder.base.models.internal.LoggerResponse;
import net.media.android.bidder.base.models.internal.PrefetchResponse;

final class ah implements ae {
    private static ah a;
    private Map<Type, ap> b = new HashMap();

    private ah() {
    }

    public static ah a() {
        if (a == null) {
            a = new ah();
        }
        return a;
    }

    private ap a(Type type) {
        ap apVar = this.b.get(type);
        if (apVar != null) {
            return apVar;
        }
        String canonicalName = ((Class) type).getCanonicalName();
        if (canonicalName.contentEquals(String.class.getCanonicalName())) {
            apVar = new ay();
        }
        if (canonicalName.contentEquals(a.class.getCanonicalName())) {
            apVar = new ao();
        }
        if (canonicalName.contentEquals(aj.class.getCanonicalName())) {
            apVar = new au();
        }
        if (canonicalName.contentEquals(LoggerResponse.class.getCanonicalName())) {
            apVar = new aw();
        }
        if (canonicalName.contentEquals(Config.class.getCanonicalName())) {
            apVar = new at();
        }
        if (canonicalName.contentEquals(BidResponse.class.getCanonicalName())) {
            apVar = new aq();
        }
        if (canonicalName.contentEquals(BidResponse[].class.getCanonicalName())) {
            apVar = new ar();
        }
        if (canonicalName.contentEquals(PrefetchResponse.class.getCanonicalName())) {
            apVar = new ax();
        }
        if (canonicalName.contentEquals(byte[].class.getCanonicalName())) {
            apVar = new as();
        }
        if (canonicalName.contentEquals(InputStream.class.getCanonicalName())) {
            apVar = new av();
        }
        if (apVar == null) {
            Logger.debug("##HttpConverterFactory##", "No converter found for type: " + type);
        }
        this.b.put(type, apVar);
        return apVar;
    }

    public Object a(InputStream inputStream, Type type) {
        return a(type).c(inputStream);
    }
}
