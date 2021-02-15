package mnetinternal;

import java.util.List;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class cn implements cs {
    public List<BidResponse> a(List<BidResponse> list, String str, AdRequest adRequest, a aVar) {
        if (adRequest == null) {
            return list;
        }
        for (BidResponse keywords : list) {
            keywords.setKeywords(adRequest.getKeywords());
        }
        return list;
    }
}
