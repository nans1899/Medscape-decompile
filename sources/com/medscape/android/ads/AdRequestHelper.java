package com.medscape.android.ads;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.medscape.android.R;
import com.medscape.android.ads.bidding.AdBidder;
import java.util.HashMap;

public class AdRequestHelper {
    public static DFPAdAction getSharethroughADAction(Context context, PublisherAdView publisherAdView, OnAdListener onAdListener) {
        DFPAdAction dFPAdAction = new DFPAdAction(context, publisherAdView, true, onAdListener);
        dFPAdAction.isSharethrough = true;
        return dFPAdAction;
    }

    public static PublisherAdView createShareThroughAdView(Context context) {
        if (context == null) {
            return null;
        }
        PublisherAdView publisherAdView = new PublisherAdView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        publisherAdView.setLayoutParams(layoutParams);
        publisherAdView.setAdSizes(DFPAdAction.ADSIZE_320x80, DFPAdAction.ADSIZE_320x95, DFPAdAction.ADSIZE_1x3);
        publisherAdView.setAdUnitId(DFPReferenceAdListener.AD_UNIT_ID);
        return publisherAdView;
    }

    public static PublisherAdView createPopUpAd(Context context) {
        if (context == null) {
            return null;
        }
        PublisherAdView publisherAdView = new PublisherAdView(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        publisherAdView.setLayoutParams(layoutParams);
        publisherAdView.setAdSizes(DFPAdAction.ADSIZE_335x452, DFPAdAction.ADSIZE_1x3);
        publisherAdView.setAdUnitId(DFPReferenceAdListener.AD_UNIT_ID);
        return publisherAdView;
    }

    public void makeDrugMonographInlineAdRequest(Context context, HashMap<String, String> hashMap, AdBidder adBidder, INativeDFPAdLoadListener iNativeDFPAdLoadListener) {
        if (context != null) {
            NativeAdAction nativeAdAction = new NativeAdAction(context, DFPReferenceAdListener.AD_UNIT_ID, (View) null);
            nativeAdAction.prepADWithCombinedRequests(iNativeDFPAdLoadListener, new AdSize[]{DFPAdAction.ADSIZE_1x3, DFPAdAction.ADSIZE_3x1, DFPAdAction.ADSIZE_300x250, DFPAdAction.ADSIZE_300x400});
            nativeAdAction.isInlineAd = true;
            hashMap.put("pos", context.getResources().getString(R.string.inline_ad_pos));
            adBidder.makeADCallWithBidding(context, hashMap, nativeAdAction);
        }
    }

    public void makeSponsoredPostADCall(Context context, HashMap<String, String> hashMap, OnAdListener onAdListener) {
        if (context != null) {
            PublisherAdView publisherAdView = new PublisherAdView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.gravity = 17;
            publisherAdView.setLayoutParams(layoutParams);
            publisherAdView.setAdSizes(DFPAdAction.ADSIZE_2x8);
            publisherAdView.setAdUnitId(DFPConsultAdListener.AD_UNIT_ID);
            publisherAdView.setVisibility(8);
            DFPAdAction dFPAdAction = new DFPAdAction(context, publisherAdView, true, onAdListener);
            hashMap.put("pos", context.getResources().getString(R.string.sponsored_consult_ad_pos));
            dFPAdAction.makeADRequestWithoutBidding(hashMap);
        }
    }
}
