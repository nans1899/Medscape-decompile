package io.branch.referral.network;

import android.content.Context;
import io.branch.referral.Branch;
import io.branch.referral.BranchError;
import io.branch.referral.Defines;
import io.branch.referral.PrefHelper;
import io.branch.referral.ServerResponse;
import net.bytebuddy.description.type.TypeDescription;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BranchRemoteInterface {
    public static final String RETRY_NUMBER = "retryNumber";

    public abstract BranchResponse doRestfulGet(String str) throws BranchRemoteException;

    public abstract BranchResponse doRestfulPost(String str, JSONObject jSONObject) throws BranchRemoteException;

    public final ServerResponse make_restful_get(String str, JSONObject jSONObject, String str2, String str3) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!addCommonParams(jSONObject, str3)) {
            return new ServerResponse(str2, BranchError.ERR_BRANCH_KEY_INVALID);
        }
        String str4 = str + convertJSONtoString(jSONObject);
        long currentTimeMillis = System.currentTimeMillis();
        PrefHelper.Debug("BranchSDK", "getting " + str4);
        try {
            BranchResponse doRestfulGet = doRestfulGet(str4);
            ServerResponse processEntityForJSON = processEntityForJSON(doRestfulGet.responseData, doRestfulGet.responseCode, str2);
            if (Branch.getInstance() != null) {
                Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines.Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            return processEntityForJSON;
        } catch (BranchRemoteException e) {
            if (e.branchErrorCode == -111) {
                ServerResponse serverResponse = new ServerResponse(str2, BranchError.ERR_BRANCH_REQ_TIMED_OUT);
                if (Branch.getInstance() != null) {
                    Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines.Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
                }
                return serverResponse;
            }
            ServerResponse serverResponse2 = new ServerResponse(str2, BranchError.ERR_BRANCH_NO_CONNECTIVITY);
            if (Branch.getInstance() != null) {
                Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines.Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            return serverResponse2;
        } catch (Throwable th) {
            if (Branch.getInstance() != null) {
                Branch.getInstance().addExtraInstrumentationData(str2 + "-" + Defines.Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            throw th;
        }
    }

    public final ServerResponse make_restful_post(JSONObject jSONObject, String str, String str2, String str3) {
        long currentTimeMillis = System.currentTimeMillis();
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (!addCommonParams(jSONObject, str3)) {
            return new ServerResponse(str2, BranchError.ERR_BRANCH_KEY_INVALID);
        }
        PrefHelper.Debug("BranchSDK", "posting to " + str);
        PrefHelper.Debug("BranchSDK", "Post value = " + jSONObject.toString());
        try {
            BranchResponse doRestfulPost = doRestfulPost(str, jSONObject);
            ServerResponse processEntityForJSON = processEntityForJSON(doRestfulPost.responseData, doRestfulPost.responseCode, str2);
            if (Branch.getInstance() != null) {
                Branch instance = Branch.getInstance();
                instance.addExtraInstrumentationData(str2 + "-" + Defines.Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            return processEntityForJSON;
        } catch (BranchRemoteException e) {
            if (e.branchErrorCode == -111) {
                ServerResponse serverResponse = new ServerResponse(str2, BranchError.ERR_BRANCH_REQ_TIMED_OUT);
                if (Branch.getInstance() != null) {
                    Branch instance2 = Branch.getInstance();
                    instance2.addExtraInstrumentationData(str2 + "-" + Defines.Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
                }
                return serverResponse;
            }
            ServerResponse serverResponse2 = new ServerResponse(str2, BranchError.ERR_BRANCH_NO_CONNECTIVITY);
            if (Branch.getInstance() != null) {
                Branch instance3 = Branch.getInstance();
                instance3.addExtraInstrumentationData(str2 + "-" + Defines.Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            return serverResponse2;
        } catch (Throwable th) {
            if (Branch.getInstance() != null) {
                Branch instance4 = Branch.getInstance();
                instance4.addExtraInstrumentationData(str2 + "-" + Defines.Jsonkey.Branch_Round_Trip_Time.getKey(), String.valueOf((int) (System.currentTimeMillis() - currentTimeMillis)));
            }
            throw th;
        }
    }

    public static final BranchRemoteInterface getDefaultBranchRemoteInterface(Context context) {
        return new BranchRemoteInterfaceUrlConnection(context);
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0026 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private io.branch.referral.ServerResponse processEntityForJSON(java.lang.String r3, int r4, java.lang.String r5) {
        /*
            r2 = this;
            io.branch.referral.ServerResponse r0 = new io.branch.referral.ServerResponse
            r0.<init>(r5, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "returned "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            java.lang.String r5 = "BranchSDK"
            io.branch.referral.PrefHelper.Debug(r5, r4)
            if (r3 == 0) goto L_0x0050
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0026 }
            r4.<init>(r3)     // Catch:{ JSONException -> 0x0026 }
            r0.setPost(r4)     // Catch:{ JSONException -> 0x0026 }
            goto L_0x0050
        L_0x0026:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch:{ JSONException -> 0x002f }
            r4.<init>(r3)     // Catch:{ JSONException -> 0x002f }
            r0.setPost(r4)     // Catch:{ JSONException -> 0x002f }
            goto L_0x0050
        L_0x002f:
            r3 = move-exception
            java.lang.Class r4 = r2.getClass()
            java.lang.String r4 = r4.getSimpleName()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r1 = "JSON exception: "
            r5.append(r1)
            java.lang.String r3 = r3.getMessage()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            io.branch.referral.PrefHelper.Debug(r4, r3)
        L_0x0050:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.network.BranchRemoteInterface.processEntityForJSON(java.lang.String, int, java.lang.String):io.branch.referral.ServerResponse");
    }

    private boolean addCommonParams(JSONObject jSONObject, String str) {
        try {
            if (!jSONObject.has(Defines.Jsonkey.UserData.getKey())) {
                jSONObject.put(Defines.Jsonkey.SDK.getKey(), "android2.19.5");
            }
            if (str.equals("bnc_no_value")) {
                return false;
            }
            jSONObject.put(Defines.Jsonkey.BranchKey.getKey(), str);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }

    private String convertJSONtoString(JSONObject jSONObject) {
        JSONArray names;
        StringBuilder sb = new StringBuilder();
        if (!(jSONObject == null || (names = jSONObject.names()) == null)) {
            int length = names.length();
            boolean z = true;
            int i = 0;
            while (i < length) {
                try {
                    String string = names.getString(i);
                    if (z) {
                        sb.append(TypeDescription.Generic.OfWildcardType.SYMBOL);
                        z = false;
                    } else {
                        sb.append("&");
                    }
                    String string2 = jSONObject.getString(string);
                    sb.append(string);
                    sb.append("=");
                    sb.append(string2);
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return sb.toString();
    }

    public static class BranchResponse {
        /* access modifiers changed from: private */
        public final int responseCode;
        /* access modifiers changed from: private */
        public final String responseData;

        public BranchResponse(String str, int i) {
            this.responseData = str;
            this.responseCode = i;
        }
    }

    public static class BranchRemoteException extends Exception {
        /* access modifiers changed from: private */
        public int branchErrorCode = BranchError.ERR_BRANCH_NO_CONNECTIVITY;

        public BranchRemoteException(int i) {
            this.branchErrorCode = i;
        }
    }
}
