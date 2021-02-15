package mnetinternal;

import java.util.List;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class cm implements cs {
    public List<BidResponse> a(List<BidResponse> list, String str, AdRequest adRequest, a aVar) {
        if (adRequest == null) {
            return list;
        }
        for (BidResponse adCycleId : list) {
            adCycleId.setAdCycleId(adRequest.getAdCycleId());
        }
        return list;
    }
}
