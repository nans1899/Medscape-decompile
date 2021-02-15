package mnetinternal;

import java.util.List;
import net.media.android.bidder.base.models.AdRequest;
import net.media.android.bidder.base.models.a;
import net.media.android.bidder.base.models.internal.BidResponse;
import net.media.android.bidder.base.models.internal.HostAppContext;

public final class co implements cs {
    public List<BidResponse> a(List<BidResponse> list, String str, AdRequest adRequest, a aVar) {
        HostAppContext hostAppContext;
        if (!(adRequest == null || (hostAppContext = adRequest.getHostAppContext()) == null || aVar == null)) {
            for (BidResponse contextLink : list) {
                contextLink.setContextLink(hostAppContext.getCrawlerLink());
            }
        }
        return list;
    }
}
