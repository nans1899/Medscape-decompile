package mnetinternal;

import java.io.InputStream;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class aq extends ap<BidResponse> {
    /* renamed from: a */
    public BidResponse b(InputStream inputStream) {
        return (BidResponse) a.b().a(d(inputStream).b("data").m().b("ad"), BidResponse.class);
    }
}
