package com.wbmd.adlibrary.viewholders;

import android.content.Context;
import android.location.Location;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.wbmd.adlibrary.R;
import com.wbmd.adlibrary.cache.LocationMemCache;
import com.wbmd.adlibrary.callbacks.IGetLocationCallback;
import com.wbmd.adlibrary.model.AdRequest;
import com.wbmd.adlibrary.utilities.AdRequestToPublisherAdRequestConverter;

public class AdViewHolder extends RecyclerView.ViewHolder {
    /* access modifiers changed from: private */
    public AdRequest mAdRequest;
    /* access modifiers changed from: private */
    public PublisherAdRequest mPublisherAdRequest;
    private PublisherAdView mPublisherAdView;
    /* access modifiers changed from: private */
    public View mRootView;

    public AdViewHolder(View view, Context context, AdRequest adRequest) {
        super(view);
        this.mRootView = view;
        this.mAdRequest = adRequest;
        this.mPublisherAdView = (PublisherAdView) view.findViewById(R.id.publisherAdView);
        LocationMemCache.getInstance().fetchLocation(context, new IGetLocationCallback() {
            public void onGetLocation(Location location) {
                AdViewHolder adViewHolder = AdViewHolder.this;
                PublisherAdRequest unused = adViewHolder.mPublisherAdRequest = new AdRequestToPublisherAdRequestConverter(adViewHolder.mAdRequest).convert(location);
            }

            public void onGetLocationError(Exception exc) {
                AdViewHolder adViewHolder = AdViewHolder.this;
                PublisherAdRequest unused = adViewHolder.mPublisherAdRequest = new AdRequestToPublisherAdRequestConverter(adViewHolder.mAdRequest).convert((Location) null);
            }
        });
    }

    public void makeAdLoadRequest() {
        PublisherAdRequest publisherAdRequest = this.mPublisherAdRequest;
        if (publisherAdRequest != null) {
            this.mPublisherAdView.loadAd(publisherAdRequest);
            this.mPublisherAdView.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    AdViewHolder.this.mRootView.setVisibility(0);
                    super.onAdLoaded();
                }
            });
        }
    }
}
