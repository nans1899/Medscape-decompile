package mnetinternal;

import com.mnet.gson.h;
import com.mnet.gson.n;
import java.io.InputStream;
import net.media.android.bidder.base.gson.a;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class ar extends ap<BidResponse[]> {
    /* renamed from: a */
    public BidResponse[] b(InputStream inputStream) {
        n d = d(inputStream);
        int a = d.b("data").m().b("ads").n().a();
        BidResponse[] bidResponseArr = new BidResponse[a];
        h n = d.b("data").m().b("ads").n();
        for (int i = 0; i < a; i++) {
            bidResponseArr[i] = (BidResponse) a.b().a(n.a(i), BidResponse.class);
        }
        return bidResponseArr;
    }
}
