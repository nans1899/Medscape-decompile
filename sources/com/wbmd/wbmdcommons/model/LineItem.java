package com.wbmd.wbmdcommons.model;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

public class LineItem {
    public CrossLink crossLink;
    public boolean isAd;
    public boolean isHeader;
    public boolean isListItem;
    public boolean isSubsection;
    private PublisherAdView mPublisherAdView;
    public int sectionFirstPosition;
    public CharSequence text;

    public LineItem(CrossLink crossLink2, CharSequence charSequence, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        this.crossLink = crossLink2;
        this.isHeader = z;
        this.isSubsection = z2;
        this.isListItem = z3;
        this.text = charSequence;
        this.sectionFirstPosition = i;
        this.isAd = z4;
    }

    public void setPublisherAdView(PublisherAdView publisherAdView) {
        this.mPublisherAdView = publisherAdView;
    }

    public PublisherAdView getPublisherAdView() {
        return this.mPublisherAdView;
    }
}
