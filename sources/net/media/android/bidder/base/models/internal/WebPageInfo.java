package net.media.android.bidder.base.models.internal;

import mnetinternal.c;

public final class WebPageInfo {
    @c(a = "title")
    private String mTitle;
    @c(a = "url")
    private String mUrl;
    @c(a = "visits")
    private int mVisits;

    public WebPageInfo(String str, String str2, int i) {
        this.mTitle = str;
        this.mUrl = str2;
        this.mVisits = i;
    }
}
