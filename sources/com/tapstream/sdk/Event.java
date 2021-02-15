package com.tapstream.sdk;

import com.adobe.mobile.TargetLocationRequest;
import com.tapstream.sdk.http.FormPostBody;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Event {
    public static final String PURCHASE_CURRENCY = "purchase-currency";
    public static final String PURCHASE_PRICE = "purchase-price";
    public static final String PURCHASE_PRODUCT_ID = "purchase-product-id";
    public static final String PURCHASE_QUANTITY = "purchase-quantity";
    public static final String PURCHASE_TRANSACTION_ID = "purchase-transaction-id";
    public static final String RECEIPT_BODY = "receipt-body";
    private final Params customParams;
    private final boolean isTransaction;
    private String name;
    private final boolean oneTimeOnly;
    private final Params params;
    private String productSku;

    public static class Params {
        private final Map<String, String> params = new HashMap();

        public void put(String str, long j) {
            put(str, Long.toString(j));
        }

        public void put(String str, int i) {
            put(str, Integer.toString(i));
        }

        public void put(String str, String str2) {
            put(str, str2, true);
        }

        public void putWithoutTruncation(String str, String str2) {
            put(str, str2, false);
        }

        private void put(String str, String str2, boolean z) {
            if (str != null && str2 != null) {
                if (str.length() > 255) {
                    Logging.log(5, "key exceeds 255 characters, this field will not be included in the post (key=%s)", str);
                } else if (!z || str2.length() <= 255) {
                    this.params.put(str, str2);
                } else {
                    Logging.log(5, "value exceeds 255 characters, this field will not be included in the post (value=%s)", str2);
                }
            }
        }

        public Map<String, String> toMap() {
            return this.params;
        }
    }

    public Event(String str, boolean z) {
        this.params = new Params();
        this.customParams = new Params();
        this.name = str;
        this.oneTimeOnly = z;
        this.isTransaction = false;
    }

    public Event(String str, String str2, int i) {
        this.params = new Params();
        this.customParams = new Params();
        this.name = "";
        this.oneTimeOnly = false;
        this.isTransaction = true;
        this.productSku = str2;
        this.params.put(PURCHASE_TRANSACTION_ID, str);
        this.params.put(PURCHASE_PRODUCT_ID, str2);
        this.params.put(PURCHASE_QUANTITY, i);
    }

    public Event(String str, String str2, int i, int i2, String str3) {
        this.params = new Params();
        this.customParams = new Params();
        this.name = "";
        this.oneTimeOnly = false;
        this.isTransaction = true;
        this.productSku = str2;
        this.params.put(PURCHASE_TRANSACTION_ID, str);
        this.params.put(PURCHASE_PRODUCT_ID, str2);
        this.params.put(PURCHASE_QUANTITY, i);
        this.params.put(PURCHASE_PRICE, i2);
        this.params.put(PURCHASE_CURRENCY, str3);
    }

    public Event(String str, String str2, String str3) throws JSONException {
        this.params = new Params();
        this.customParams = new Params();
        this.name = "";
        this.oneTimeOnly = false;
        this.isTransaction = true;
        JSONObject jSONObject = new JSONObject(str2);
        JSONObject jSONObject2 = new JSONObject(str);
        this.productSku = jSONObject2.getString("productId");
        String string = jSONObject2.getString(TargetLocationRequest.TARGET_PARAMETER_ORDER_ID);
        if (!jSONObject.has("price_currency_code") || !jSONObject.has("price_amount_micros")) {
            this.params.put(PURCHASE_TRANSACTION_ID, string);
            this.params.put(PURCHASE_PRODUCT_ID, this.productSku);
            this.params.put(PURCHASE_QUANTITY, 1);
        } else {
            String string2 = jSONObject.getString("price_currency_code");
            this.params.put(PURCHASE_TRANSACTION_ID, string);
            this.params.put(PURCHASE_PRODUCT_ID, this.productSku);
            this.params.put(PURCHASE_QUANTITY, 1);
            this.params.put(PURCHASE_PRICE, (int) Math.round(((double) jSONObject.getInt("price_amount_micros")) / 10000.0d));
            this.params.put(PURCHASE_CURRENCY, string2);
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("purchase_data", str);
        jSONObject3.put("signature", str3);
        this.params.putWithoutTruncation(RECEIPT_BODY, jSONObject3.toString());
    }

    public void setCustomParameter(String str, Object obj) {
        this.customParams.put(str, obj.toString());
    }

    public String getName() {
        return this.name;
    }

    public boolean isOneTimeOnly() {
        return this.oneTimeOnly;
    }

    /* access modifiers changed from: package-private */
    public void setNameForPurchase(String str) {
        this.name = String.format(Locale.US, "android-%s-purchase-%s", new Object[]{str, this.productSku});
    }

    /* access modifiers changed from: package-private */
    public void prepare(String str) {
        if (this.isTransaction) {
            setNameForPurchase(str);
        }
    }

    /* access modifiers changed from: package-private */
    public FormPostBody buildPostBody(Params params2, Params params3) {
        FormPostBody formPostBody = new FormPostBody();
        formPostBody.add(params2.toMap());
        formPostBody.add(this.params.toMap());
        if (params3 != null) {
            for (Map.Entry next : params3.toMap().entrySet()) {
                formPostBody.add("custom-" + ((String) next.getKey()), (String) next.getValue());
            }
        }
        Params params4 = this.customParams;
        if (params4 != null) {
            for (Map.Entry next2 : params4.toMap().entrySet()) {
                formPostBody.add("custom-" + ((String) next2.getKey()), (String) next2.getValue());
            }
        }
        formPostBody.add("created-ms", Long.toString(System.currentTimeMillis()));
        return formPostBody;
    }

    /* access modifiers changed from: package-private */
    public Params getParams() {
        return this.params;
    }

    /* access modifiers changed from: package-private */
    public Params getCustomParams() {
        return this.customParams;
    }

    /* access modifiers changed from: package-private */
    public boolean isTransaction() {
        return this.isTransaction;
    }

    /* access modifiers changed from: package-private */
    public String getProductSku() {
        return this.productSku;
    }
}
