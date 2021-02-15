package mnetinternal;

import com.mnet.gson.k;
import java.io.InputStream;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.models.internal.Config;

public final class at extends ap<Config> {
    /* renamed from: a */
    public Config b(InputStream inputStream) {
        return (Config) a.b().a((k) d(inputStream), Config.class);
    }
}
