package com.wbmd.adlibrary.fragments;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.wbmd.adlibrary.R;
import com.wbmd.adlibrary.cache.LocationMemCache;
import com.wbmd.adlibrary.callbacks.IAdStateListener;
import com.wbmd.adlibrary.callbacks.IGetLocationCallback;
import com.wbmd.adlibrary.constants.AdTypes;
import com.wbmd.adlibrary.model.AdRequest;
import com.wbmd.adlibrary.utilities.AdRequestToPublisherAdRequestConverter;
import com.wbmd.adlibrary.utilities.AdTypeViewResourceMapper;

public class AdFragment extends Fragment {
    private AdRequest mAdRequest;
    /* access modifiers changed from: private */
    public IAdStateListener mCallback;
    private boolean mIsAdLoading;
    private PublisherAdRequest mPublisherAdRequest;
    private PublisherAdView mPublisherAdView;
    /* access modifiers changed from: private */
    public View mRootView;

    public static AdFragment newInstance(AdRequest adRequest, IAdStateListener iAdStateListener) {
        AdFragment adFragment = new AdFragment();
        adFragment.mAdRequest = adRequest;
        adFragment.mCallback = iAdStateListener;
        return adFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AdRequest adRequest = this.mAdRequest;
        if (adRequest != null) {
            if (adRequest.getAdType().equals(AdTypes.BANNER_INLINE)) {
                this.mRootView = layoutInflater.inflate(R.layout.inline_banner_ad, viewGroup, false);
            } else if (this.mAdRequest.getAdType().equals(AdTypes.BRANDSEEKER)) {
                this.mRootView = layoutInflater.inflate(R.layout.fullscreen_brandseeker_ad, viewGroup, false);
            } else {
                this.mRootView = layoutInflater.inflate(R.layout.fragment_banner_ad, viewGroup, false);
            }
            LocationMemCache.getInstance().fetchLocation(getContext(), new IGetLocationCallback() {
                public void onGetLocation(Location location) {
                    AdFragment.this.setupAdView(location);
                }

                public void onGetLocationError(Exception exc) {
                    AdFragment.this.setupAdView();
                }
            });
        }
        return this.mRootView;
    }

    /* access modifiers changed from: private */
    public void setupAdView() {
        setupAdView((Location) null);
    }

    /* access modifiers changed from: private */
    public void setupAdView(Location location) {
        this.mPublisherAdView = (PublisherAdView) this.mRootView.findViewById(getAdLayoutResourceId());
        this.mPublisherAdRequest = new AdRequestToPublisherAdRequestConverter(this.mAdRequest).convert(location);
        if (!this.mPublisherAdView.isLoading() && !this.mIsAdLoading) {
            makeAdLoadRequest();
        }
    }

    public void onResume() {
        super.onResume();
        PublisherAdView publisherAdView = this.mPublisherAdView;
        if (publisherAdView != null) {
            publisherAdView.resume();
        }
    }

    private void makeAdLoadRequest() {
        Log.d("____ad____", "in makeLoadRequest");
        PublisherAdRequest publisherAdRequest = this.mPublisherAdRequest;
        if (publisherAdRequest != null) {
            this.mPublisherAdView.loadAd(publisherAdRequest);
            this.mPublisherAdView.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }

                public void onAdFailedToLoad(int i) {
                    AdFragment.this.mCallback.onAdFailedToLoad();
                    super.onAdFailedToLoad(i);
                    Log.d("____ad____", "in onAdFailedToLoad");
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                }

                public void onAdOpened() {
                    super.onAdOpened();
                }

                public void onAdLoaded() {
                    AdFragment.this.mRootView.setVisibility(0);
                    AdFragment.this.mCallback.onAdLoaded();
                    super.onAdLoaded();
                    Log.d("____ad____", "in onAdLoaded");
                }
            });
        }
    }

    public void destroyAd() {
        PublisherAdView publisherAdView = this.mPublisherAdView;
        if (publisherAdView != null) {
            publisherAdView.destroy();
        }
    }

    private int getAdLayoutResourceId() {
        AdTypeViewResourceMapper adTypeViewResourceMapper = new AdTypeViewResourceMapper(getContext());
        if (this.mAdRequest.getAdType() != null) {
            return adTypeViewResourceMapper.getResourceId(this.mAdRequest.getAdType());
        }
        return R.id.publisherAdView;
    }

    public void pauseAd() {
        PublisherAdView publisherAdView = this.mPublisherAdView;
        if (publisherAdView != null) {
            publisherAdView.pause();
        }
    }
}
