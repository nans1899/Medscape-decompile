package io.branch.indexing;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentDiscoveryManifest {
    public static final String CONTENT_DISCOVER_KEY = "cd";
    static final int DEF_MAX_DISCOVERY_REPEAT = 15;
    private static final String DISCOVERY_REPEAT_INTERVAL = "dri";
    static final int DRI_MINIMUM_THRESHOLD = 500;
    private static final String FILTERED_KEYS = "ck";
    static final String HASH_MODE_KEY = "h";
    private static final String MANIFEST_KEY = "m";
    public static final String MANIFEST_VERSION_KEY = "mv";
    private static final String MAX_DISCOVERY_REPEAT = "mdr";
    private static final String MAX_PACKET_SIZE_KEY = "mps";
    private static final String MAX_TEXT_LEN_KEY = "mtl";
    private static final String MAX_VIEW_HISTORY_LENGTH = "mhl";
    public static final String PACKAGE_NAME_KEY = "pn";
    private static final String PATH_KEY = "p";
    private static ContentDiscoveryManifest thisInstance_;
    private final String PREF_KEY = "BNC_CD_MANIFEST";
    private JSONObject cdManifestObject_;
    private JSONArray contentPaths_;
    private boolean isCDEnabled_ = false;
    private String manifestVersion_;
    private int maxPacketSize_ = 0;
    private int maxTextLen_ = 0;
    private int maxViewHistoryLength_ = 1;
    private SharedPreferences sharedPref;

    private ContentDiscoveryManifest(Context context) {
        this.sharedPref = context.getSharedPreferences("bnc_content_discovery_manifest_storage", 0);
        retrieve(context);
    }

    public static ContentDiscoveryManifest getInstance(Context context) {
        if (thisInstance_ == null) {
            thisInstance_ = new ContentDiscoveryManifest(context);
        }
        return thisInstance_;
    }

    private void persist() {
        this.sharedPref.edit().putString("BNC_CD_MANIFEST", this.cdManifestObject_.toString()).apply();
    }

    private void retrieve(Context context) {
        String string = this.sharedPref.getString("BNC_CD_MANIFEST", (String) null);
        if (string != null) {
            try {
                JSONObject jSONObject = new JSONObject(string);
                this.cdManifestObject_ = jSONObject;
                if (jSONObject.has(MANIFEST_VERSION_KEY)) {
                    this.manifestVersion_ = this.cdManifestObject_.getString(MANIFEST_VERSION_KEY);
                }
                if (this.cdManifestObject_.has(MANIFEST_KEY)) {
                    this.contentPaths_ = this.cdManifestObject_.getJSONArray(MANIFEST_KEY);
                }
            } catch (JSONException unused) {
                this.cdManifestObject_ = new JSONObject();
            }
        } else {
            this.cdManifestObject_ = new JSONObject();
        }
    }

    public void onBranchInitialised(JSONObject jSONObject) {
        int i;
        if (jSONObject.has(CONTENT_DISCOVER_KEY)) {
            this.isCDEnabled_ = true;
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(CONTENT_DISCOVER_KEY);
                if (jSONObject2.has(MANIFEST_VERSION_KEY)) {
                    this.manifestVersion_ = jSONObject2.getString(MANIFEST_VERSION_KEY);
                }
                if (jSONObject2.has(MAX_VIEW_HISTORY_LENGTH)) {
                    this.maxViewHistoryLength_ = jSONObject2.getInt(MAX_VIEW_HISTORY_LENGTH);
                }
                if (jSONObject2.has(MANIFEST_KEY)) {
                    this.contentPaths_ = jSONObject2.getJSONArray(MANIFEST_KEY);
                }
                if (jSONObject2.has(MAX_TEXT_LEN_KEY) && (i = jSONObject2.getInt(MAX_TEXT_LEN_KEY)) > 0) {
                    this.maxTextLen_ = i;
                }
                if (jSONObject2.has(MAX_PACKET_SIZE_KEY)) {
                    this.maxPacketSize_ = jSONObject2.getInt(MAX_PACKET_SIZE_KEY);
                }
                this.cdManifestObject_.put(MANIFEST_VERSION_KEY, this.manifestVersion_);
                this.cdManifestObject_.put(MANIFEST_KEY, this.contentPaths_);
                persist();
            } catch (JSONException unused) {
            }
        } else {
            this.isCDEnabled_ = false;
        }
    }

    /* access modifiers changed from: package-private */
    public CDPathProperties getCDPathProperties(Activity activity) {
        if (this.contentPaths_ == null) {
            return null;
        }
        String str = "/" + activity.getClass().getSimpleName();
        int i = 0;
        while (i < this.contentPaths_.length()) {
            try {
                JSONObject jSONObject = this.contentPaths_.getJSONObject(i);
                if (jSONObject.has("p") && jSONObject.getString("p").equals(str)) {
                    return new CDPathProperties(jSONObject);
                }
                i++;
            } catch (JSONException unused) {
                return null;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isCDEnabled() {
        return this.isCDEnabled_;
    }

    /* access modifiers changed from: package-private */
    public int getMaxTextLen() {
        return this.maxTextLen_;
    }

    /* access modifiers changed from: package-private */
    public int getMaxPacketSize() {
        return this.maxPacketSize_;
    }

    /* access modifiers changed from: package-private */
    public int getMaxViewHistorySize() {
        return this.maxViewHistoryLength_;
    }

    public String getManifestVersion() {
        if (TextUtils.isEmpty(this.manifestVersion_)) {
            return "-1";
        }
        return this.manifestVersion_;
    }

    class CDPathProperties {
        private int discoveryRepeatInterval_;
        private boolean isClearText_;
        private int maxDiscoveryRepeat_ = 15;
        final JSONObject pathInfo_;

        /* access modifiers changed from: package-private */
        public int getDiscoveryRepeatInterval() {
            return this.discoveryRepeatInterval_;
        }

        /* access modifiers changed from: package-private */
        public int getMaxDiscoveryRepeatNumber() {
            return this.maxDiscoveryRepeat_;
        }

        CDPathProperties(JSONObject jSONObject) {
            this.pathInfo_ = jSONObject;
            if (jSONObject.has(ContentDiscoveryManifest.HASH_MODE_KEY)) {
                try {
                    this.isClearText_ = !jSONObject.getBoolean(ContentDiscoveryManifest.HASH_MODE_KEY);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                if (jSONObject.has(ContentDiscoveryManifest.DISCOVERY_REPEAT_INTERVAL)) {
                    this.discoveryRepeatInterval_ = jSONObject.getInt(ContentDiscoveryManifest.DISCOVERY_REPEAT_INTERVAL);
                }
                if (jSONObject.has(ContentDiscoveryManifest.MAX_DISCOVERY_REPEAT)) {
                    this.maxDiscoveryRepeat_ = jSONObject.getInt(ContentDiscoveryManifest.MAX_DISCOVERY_REPEAT);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }

        /* access modifiers changed from: package-private */
        public JSONArray getFilteredElements() {
            if (this.pathInfo_.has(ContentDiscoveryManifest.FILTERED_KEYS)) {
                try {
                    return this.pathInfo_.getJSONArray(ContentDiscoveryManifest.FILTERED_KEYS);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean isClearTextRequested() {
            return this.isClearText_;
        }

        /* access modifiers changed from: package-private */
        public boolean isSkipContentDiscovery() {
            JSONArray filteredElements = getFilteredElements();
            return filteredElements != null && filteredElements.length() == 0;
        }
    }
}
