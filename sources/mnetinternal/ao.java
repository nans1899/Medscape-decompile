package mnetinternal;

import com.mnet.gson.k;
import com.mnet.gson.n;
import java.io.InputStream;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.a;

public final class ao extends ap<a> {
    /* renamed from: a */
    public a b(InputStream inputStream) {
        n d = d(inputStream);
        if (d == null) {
            return null;
        }
        if (d.a("code") && d.b("code").g() == 918 && !cb.a().b("__mn__config_expired")) {
            Logger.debug("##AdResponseConverter##", "config has expired!, stopping all calls");
            net.media.android.bidder.base.configs.a.a().c();
            cb.a().a("__mn__config_expired", true);
            return null;
        } else if (d.a("data") && !d.b("data").l() && d.b("data").j()) {
            return (a) net.media.android.bidder.base.gson.a.b().a((k) d.b("data").m(), a.class);
        } else {
            return null;
        }
    }
}
