package com.medscape.android.contentviewer;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

public class InlineAdLineItem extends LineItem {
    PublisherAdView adView;

    public InlineAdLineItem(CrossLink crossLink, CharSequence charSequence, int i, boolean z, boolean z2, boolean z3) {
        super(crossLink, charSequence, i, z, z2, z3);
    }

    public PublisherAdView getAdView() {
        return this.adView;
    }

    public void setAdView(PublisherAdView publisherAdView) {
        this.adView = publisherAdView;
    }
}
