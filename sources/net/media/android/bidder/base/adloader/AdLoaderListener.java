package net.media.android.bidder.base.adloader;

import net.media.android.bidder.base.error.MNetError;
import net.media.android.bidder.base.models.internal.AdResponse;

public interface AdLoaderListener {
    void onError(MNetError mNetError);

    void onSuccess(AdResponse adResponse);
}
