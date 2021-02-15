package net.media.android.bidder.base.adloader;

import java.util.List;
import net.media.android.bidder.base.error.MNetError;
import net.media.android.bidder.base.models.internal.BidResponse;

public interface a {
    void a(String str, List<BidResponse> list);

    void a(MNetError mNetError);
}
