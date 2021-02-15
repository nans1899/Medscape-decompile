package net.media.android.bidder.base.bidder;

import java.util.Map;
import net.media.android.bidder.base.error.MNetError;

public interface AdProcessListener {
    void onCompleted(Map<String, Object> map);

    void onError(MNetError mNetError);
}
