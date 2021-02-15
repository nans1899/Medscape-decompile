package mnetinternal;

import android.text.TextUtils;
import java.util.List;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class cp implements cs {
    public List<BidResponse> a(List<BidResponse> list, String str, AdRequest adRequest, a aVar) {
        if (TextUtils.isEmpty(str)) {
            return list;
        }
        for (BidResponse creativeId : list) {
            creativeId.setCreativeId(str);
        }
        return list;
    }
}
