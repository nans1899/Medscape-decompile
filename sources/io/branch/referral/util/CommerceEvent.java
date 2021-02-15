package io.branch.referral.util;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CommerceEvent {
    private String affiliation;
    private String coupon;
    private CurrencyType currencyType;
    private List<Product> products;
    private Double revenue;
    private Double shipping;
    private Double tax;
    private String transactionID;

    public Double getRevenue() {
        return this.revenue;
    }

    public void setRevenue(Double d) {
        this.revenue = d;
    }

    public CurrencyType getCurrencyType() {
        return this.currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType2) {
        this.currencyType = currencyType2;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(String str) {
        this.transactionID = str;
    }

    public Double getShipping() {
        return this.shipping;
    }

    public void setShipping(Double d) {
        this.shipping = d;
    }

    public Double getTax() {
        return this.tax;
    }

    public void setTax(Double d) {
        this.tax = d;
    }

    public String getCoupon() {
        return this.coupon;
    }

    public void setCoupon(String str) {
        this.coupon = str;
    }

    public String getAffiliation() {
        return this.affiliation;
    }

    public void setAffiliation(String str) {
        this.affiliation = str;
    }

    public void setProducts(List<Product> list) {
        this.products = list;
    }

    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new ArrayList();
        }
        this.products.add(product);
    }

    public CommerceEvent() {
    }

    public CommerceEvent(Double d, CurrencyType currencyType2, String str, Double d2, Double d3, String str2, String str3, List<Product> list) {
        this.revenue = d;
        this.currencyType = currencyType2;
        this.transactionID = str;
        this.shipping = d2;
        this.tax = d3;
        this.coupon = str2;
        this.affiliation = str3;
        this.products = list;
    }

    public CommerceEvent(Double d, CurrencyType currencyType2, String str, Double d2, Double d3, String str2, String str3, Product product) {
        this.revenue = d;
        this.currencyType = currencyType2;
        this.transactionID = str;
        this.shipping = d2;
        this.tax = d3;
        this.coupon = str2;
        this.affiliation = str3;
        ArrayList arrayList = new ArrayList();
        this.products = arrayList;
        arrayList.add(product);
    }

    public JSONObject getCommerceJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("revenue", this.revenue);
            jSONObject.put(FirebaseAnalytics.Param.CURRENCY, this.currencyType);
            jSONObject.put(FirebaseAnalytics.Param.TRANSACTION_ID, this.transactionID);
            jSONObject.put(FirebaseAnalytics.Param.SHIPPING, this.shipping);
            jSONObject.put(FirebaseAnalytics.Param.TAX, this.tax);
            jSONObject.put(FirebaseAnalytics.Param.COUPON, this.coupon);
            jSONObject.put(FirebaseAnalytics.Param.AFFILIATION, this.affiliation);
            if (getProducts() != null) {
                jSONObject.put("products", getProducts());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public List<JSONObject> getProducts() {
        if (this.products == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Product productJSONObject : this.products) {
            arrayList.add(productJSONObject.getProductJSONObject());
        }
        return arrayList;
    }
}
