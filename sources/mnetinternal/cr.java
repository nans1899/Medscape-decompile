package mnetinternal;

import java.util.List;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.BidResponse;

public final class cr implements cs {
    public List<BidResponse> a(List<BidResponse> list, String str, AdRequest adRequest, a aVar) {
        if (aVar == null) {
            return list;
        }
        for (BidResponse predictionId : list) {
            predictionId.setPredictionId(aVar.d());
        }
        return list;
    }
}