package net.media.android.bidder.base.models.internal;

import java.util.List;
import mnetinternal.c;

public final class PrefetchResponse {
    @c(a = "prefetch_urls")
    private List<String> mUrls;

    public List<String> getUrls() {
        return this.mUrls;
    }
}
