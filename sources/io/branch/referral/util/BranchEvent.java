package io.branch.referral.util;

import android.content.Context;
import io.branch.indexing.BranchUniversalObject;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import io.branch.referral.ServerRequest;
import io.branch.referral.ServerResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BranchEvent {
    public static final String ADD_TO_CART = "Add to Cart";
    public static final String ADD_TO_WISH_LIST = "Add to Wishlist";
    public static final String CANONICAL_ID_LIST = "$canonical_identifier_list";
    public static final String PURCHASED = "Purchased";
    public static final String PURCHASE_STARTED = "Purchase Started";
    public static final String SHARE_COMPLETED = "Share Completed";
    public static final String SHARE_STARTED = "Share Started";
    public static final String VIEW = "View";
    /* access modifiers changed from: private */
    public final List<BranchUniversalObject> buoList;
    /* access modifiers changed from: private */
    public final JSONObject customProperties;
    /* access modifiers changed from: private */
    public final String eventName;
    private final boolean isStandardEvent;
    /* access modifiers changed from: private */
    public final JSONObject standardProperties;

    public BranchEvent(BRANCH_STANDARD_EVENT branch_standard_event) {
        this(branch_standard_event.getName(), true);
    }

    public BranchEvent(String str) {
        this(str, false);
    }

    private BranchEvent(String str, boolean z) {
        this.standardProperties = new JSONObject();
        this.customProperties = new JSONObject();
        this.eventName = str;
        BRANCH_STANDARD_EVENT[] values = BRANCH_STANDARD_EVENT.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            } else if (str.equals(values[i].getName())) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        this.isStandardEvent = z;
        this.buoList = new ArrayList();
    }

    public BranchEvent setTransactionID(String str) {
        return addStandardProperty(Defines.Jsonkey.TransactionID.getKey(), str);
    }

    public BranchEvent setCurrency(CurrencyType currencyType) {
        return addStandardProperty(Defines.Jsonkey.Currency.getKey(), currencyType.toString());
    }

    public BranchEvent setRevenue(double d) {
        return addStandardProperty(Defines.Jsonkey.Revenue.getKey(), Double.valueOf(d));
    }

    public BranchEvent setShipping(double d) {
        return addStandardProperty(Defines.Jsonkey.Shipping.getKey(), Double.valueOf(d));
    }

    public BranchEvent setTax(double d) {
        return addStandardProperty(Defines.Jsonkey.Tax.getKey(), Double.valueOf(d));
    }

    public BranchEvent setCoupon(String str) {
        return addStandardProperty(Defines.Jsonkey.Coupon.getKey(), str);
    }

    public BranchEvent setAffiliation(String str) {
        return addStandardProperty(Defines.Jsonkey.Affiliation.getKey(), str);
    }

    public BranchEvent setDescription(String str) {
        return addStandardProperty(Defines.Jsonkey.Description.getKey(), str);
    }

    public BranchEvent setSearchQuery(String str) {
        return addStandardProperty(Defines.Jsonkey.SearchQuery.getKey(), str);
    }

    private BranchEvent addStandardProperty(String str, Object obj) {
        if (obj != null) {
            try {
                this.standardProperties.put(str, obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            this.standardProperties.remove(str);
        }
        return this;
    }

    public BranchEvent addCustomDataProperty(String str, String str2) {
        try {
            this.customProperties.put(str, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public BranchEvent addContentItems(BranchUniversalObject... branchUniversalObjectArr) {
        Collections.addAll(this.buoList, branchUniversalObjectArr);
        return this;
    }

    public BranchEvent addContentItems(List<BranchUniversalObject> list) {
        this.buoList.addAll(list);
        return this;
    }

    public boolean logEvent(Context context) {
        String path = (this.isStandardEvent ? Defines.RequestPath.TrackStandardEvent : Defines.RequestPath.TrackCustomEvent).getPath();
        if (Branch.getInstance() == null) {
            return false;
        }
        Branch.getInstance().handleNewRequest(new ServerRequestLogEvent(context, path));
        return true;
    }

    private class ServerRequestLogEvent extends ServerRequest {
        public void clearCallbacks() {
        }

        public boolean handleErrors(Context context) {
            return false;
        }

        public void handleFailure(int i, String str) {
        }

        public boolean isGAdsParamsRequired() {
            return true;
        }

        public boolean isGetRequest() {
            return false;
        }

        public void onRequestSucceeded(ServerResponse serverResponse, Branch branch) {
        }

        public boolean shouldRetryOnFail() {
            return true;
        }

        /* access modifiers changed from: protected */
        public boolean shouldUpdateLimitFacebookTracking() {
            return true;
        }

        ServerRequestLogEvent(Context context, String str) {
            super(context, str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Defines.Jsonkey.Name.getKey(), BranchEvent.this.eventName);
                if (BranchEvent.this.customProperties.length() > 0) {
                    jSONObject.put(Defines.Jsonkey.CustomData.getKey(), BranchEvent.this.customProperties);
                }
                if (BranchEvent.this.standardProperties.length() > 0) {
                    jSONObject.put(Defines.Jsonkey.EventData.getKey(), BranchEvent.this.standardProperties);
                }
                if (BranchEvent.this.buoList.size() > 0) {
                    JSONArray jSONArray = new JSONArray();
                    jSONObject.put(Defines.Jsonkey.ContentItems.getKey(), jSONArray);
                    for (BranchUniversalObject convertToJson : BranchEvent.this.buoList) {
                        jSONArray.put(convertToJson.convertToJson());
                    }
                }
                setPost(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            updateEnvironment(context, jSONObject);
        }

        public ServerRequest.BRANCH_API_VERSION getBranchRemoteAPIVersion() {
            return ServerRequest.BRANCH_API_VERSION.V2;
        }
    }
}
