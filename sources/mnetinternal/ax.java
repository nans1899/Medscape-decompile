package mnetinternal;

import com.mnet.gson.k;
import com.mnet.gson.n;
import java.io.InputStream;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.models.internal.PrefetchResponse;

public final class ax extends ap<PrefetchResponse> {
    /* renamed from: a */
    public PrefetchResponse b(InputStream inputStream) {
        n d = d(inputStream);
        if (d.a("data") && !d.b("data").l() && d.b("data").j()) {
            return (PrefetchResponse) a.b().a((k) d.b("data").m(), PrefetchResponse.class);
        }
        return null;
    }
}
