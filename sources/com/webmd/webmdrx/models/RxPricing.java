package com.webmd.webmdrx.models;

public class RxPricing {
    private RxPricingData data;
    private RxPricingMeta meta;

    public RxPricingMeta getMeta() {
        return this.meta;
    }

    public void setMeta(RxPricingMeta rxPricingMeta) {
        this.meta = rxPricingMeta;
    }

    public RxPricingData getData() {
        return this.data;
    }

    public void setData(RxPricingData rxPricingData) {
        this.data = rxPricingData;
    }
}
