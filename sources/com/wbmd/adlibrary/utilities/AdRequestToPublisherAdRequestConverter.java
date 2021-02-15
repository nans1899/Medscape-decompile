package com.wbmd.adlibrary.utilities;

import android.location.Location;
import android.util.Log;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.wbmd.adlibrary.model.AdRequest;
import java.util.Map;

public class AdRequestToPublisherAdRequestConverter {
    private AdRequest mAdRequest;
    private Map<String, String> mCustomAdParamaters;

    public AdRequestToPublisherAdRequestConverter(AdRequest adRequest) {
        this.mAdRequest = adRequest;
        this.mCustomAdParamaters = adRequest.getCustomAdParamaters();
    }

    public PublisherAdRequest convert(Location location) {
        PublisherAdRequest.Builder builder = new PublisherAdRequest.Builder();
        if (this.mAdRequest.getContentUrl() != null) {
            builder.setContentUrl(this.mAdRequest.getContentUrl());
        }
        if (this.mAdRequest.getExclusionCategories() != null) {
            for (String addCategoryExclusion : this.mAdRequest.getExclusionCategories()) {
                builder.addCategoryExclusion(addCategoryExclusion);
            }
        }
        if (location != null) {
            builder.setLocation(location);
        }
        Log.d("____ad____", "Brandseeker custom Parameters: ...............");
        for (String next : this.mCustomAdParamaters.keySet()) {
            String str = this.mCustomAdParamaters.get(next);
            if (str != null) {
                builder.addCustomTargeting(next, this.mCustomAdParamaters.get(next));
                Log.d("____ad____", next + " - " + str);
            }
        }
        return builder.build();
    }
}
