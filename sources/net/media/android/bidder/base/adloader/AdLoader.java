package net.media.android.bidder.base.adloader;

import net.media.android.bidder.base.models.AdRequest;

public interface AdLoader {
    void loadAd(AdRequest adRequest, AdLoaderListener adLoaderListener);

    void prefetchAd(AdRequest adRequest, a aVar);
}
