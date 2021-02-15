package bo.app;

import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import java.util.HashMap;
import java.util.Map;

public class d {
    public Map<String, String> a() {
        HashMap hashMap = new HashMap();
        hashMap.put(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate");
        hashMap.put("Content-Type", AbstractSpiCall.ACCEPT_JSON_VALUE);
        return hashMap;
    }
}
