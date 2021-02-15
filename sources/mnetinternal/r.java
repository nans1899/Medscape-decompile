package mnetinternal;

import java.util.List;
import java.util.Map;
import net.media.android.bidder.base.models.AdSize;
import net.media.android.bidder.base.models.internal.BidResponse;

public interface r {
    List<BidResponse> a(String str);

    List<BidResponse> a(String str, List<AdSize> list, String str2);

    Map<String, Map<String, Integer>> a();

    void a(String str, List<BidResponse> list);

    Map<String, Integer> b(String str);
}
