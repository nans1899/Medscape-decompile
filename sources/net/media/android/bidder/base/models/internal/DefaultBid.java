package net.media.android.bidder.base.models.internal;

import mnetinternal.c;

public final class DefaultBid {
    @c(a = "m_bid")
    private double mBid;
    @c(a = "bid_response")
    private BidResponse mBidResponse;
    @c(a = "bidder_id")
    private String mBidderId;
    @c(a = "crid")
    private String mCreativeId;
    @c(a = "requrl_re")
    private String mRequestUrlRegex;

    public void setBid(double d) {
        this.mBid = d;
    }

    public void setBidderId(String str) {
        this.mBidderId = str;
    }

    public void setRequestUrlRegex(String str) {
        this.mRequestUrlRegex = str;
    }

    public void setCreativeId(String str) {
        this.mCreativeId = str;
    }

    public void setBidResponse(BidResponse bidResponse) {
        this.mBidResponse = bidResponse;
    }

    public double getBid() {
        return this.mBid;
    }

    public String getBidderId() {
        return this.mBidderId;
    }

    public String getRequestUrlRegex() {
        return this.mRequestUrlRegex;
    }

    public String getCreativeId() {
        return this.mCreativeId;
    }

    public BidResponse getBidResponse() {
        return this.mBidResponse;
    }
}
