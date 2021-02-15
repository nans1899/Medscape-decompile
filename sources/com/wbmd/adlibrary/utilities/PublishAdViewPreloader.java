package com.wbmd.adlibrary.utilities;

import android.content.Context;
import android.location.Location;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.wbmd.adlibrary.R;
import com.wbmd.adlibrary.audience.Audience;
import com.wbmd.adlibrary.audience.AudienceInfoResponse;
import com.wbmd.adlibrary.model.AdRequest;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import org.jetbrains.anko.DimensionsKt;

public class PublishAdViewPreloader {
    public static PublisherAdView preLoadInlineAd(Context context, Location location, AdRequest.AdRequestBuilder adRequestBuilder, String str, AdSize... adSizeArr) {
        PublisherAdView publisherAdView = new PublisherAdView(context);
        if (adSizeArr == null || (adSizeArr != null && adSizeArr.length == 0)) {
            publisherAdView.setAdSizes(AdSize.BANNER, new AdSize(300, 50), new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), new AdSize(DimensionsKt.XHDPI, PullToRefreshBase.SMOOTH_SCROLL_LONG_DURATION_MS), AdSize.MEDIUM_RECTANGLE);
        } else {
            publisherAdView.setAdSizes(adSizeArr);
        }
        if (StringExtensions.isNullOrEmpty(str)) {
            str = context.getResources().getString(R.string.banner_ad_unit_id);
        }
        publisherAdView.setAdUnitId(str);
        publisherAdView.loadAd(new AdRequestToPublisherAdRequestConverter(adRequestBuilder.build()).convert(location));
        return publisherAdView;
    }

    public static PublisherAdView preLoadBrandseekerAd(Context context, Location location, AdRequest.AdRequestBuilder adRequestBuilder) {
        PublisherAdView publisherAdView = new PublisherAdView(context);
        publisherAdView.setAdSizes(new AdSize(DimensionsKt.XHDPI, PullToRefreshBase.SMOOTH_SCROLL_LONG_DURATION_MS), new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), AdSize.MEDIUM_RECTANGLE);
        publisherAdView.setAdUnitId(context.getResources().getString(R.string.banner_ad_unit_id));
        publisherAdView.loadAd(new AdRequestToPublisherAdRequestConverter(adRequestBuilder.build()).convert(location));
        return publisherAdView;
    }

    private static String getLotameAttributesCSV(AudienceInfoResponse audienceInfoResponse) {
        if (audienceInfoResponse == null || audienceInfoResponse.getProfile() == null || audienceInfoResponse.getProfile().getAudiences() == null || audienceInfoResponse.getProfile().getAudiences().getAudience() == null || audienceInfoResponse.getProfile().getAudiences().getAudience().size() <= 0) {
            return null;
        }
        int size = audienceInfoResponse.getProfile().getAudiences().getAudience().size();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (Audience abbr : audienceInfoResponse.getProfile().getAudiences().getAudience()) {
            sb.append(abbr.getAbbr());
            i++;
            if (i < size) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
