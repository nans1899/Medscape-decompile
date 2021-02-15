package com.webmd.webmdrx.models;

public class RxPricingData {
    private Price[] Prices;
    private String Quantity;
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(String str) {
        this.Quantity = str;
    }

    public Price[] getPrices() {
        return this.Prices;
    }

    public void setPrices(Price[] priceArr) {
        this.Prices = priceArr;
    }
}
