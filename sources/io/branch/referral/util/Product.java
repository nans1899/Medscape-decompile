package io.branch.referral.util;

import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    private String brand;
    private ProductCategory category;
    private String name;
    private Double price;
    private Integer quantity;
    private String sku;
    private String variant;

    public String getSku() {
        return this.sku;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double d) {
        this.price = d;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer num) {
        this.quantity = num;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getVariant() {
        return this.variant;
    }

    public void setVariant(String str) {
        this.variant = str;
    }

    public ProductCategory getCategory() {
        return this.category;
    }

    public void setCategory(ProductCategory productCategory) {
        this.category = productCategory;
    }

    public Product() {
    }

    public Product(String str, String str2, Double d, Integer num, String str3, String str4, ProductCategory productCategory) {
        this.sku = str;
        this.name = str2;
        this.price = d;
        this.quantity = num;
        this.brand = str3;
        this.variant = str4;
        this.category = productCategory;
    }

    public JSONObject getProductJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sku", this.sku);
            jSONObject.put("name", this.name);
            jSONObject.put(FirebaseAnalytics.Param.PRICE, this.price);
            jSONObject.put(FirebaseAnalytics.Param.QUANTITY, this.quantity);
            jSONObject.put("brand", this.brand);
            jSONObject.put("variant", this.variant);
            jSONObject.put("category", this.category);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
