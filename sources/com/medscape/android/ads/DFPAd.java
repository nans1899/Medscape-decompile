package com.medscape.android.ads;

import android.content.Context;
import android.util.Log;
import com.medscape.android.R;
import com.medscape.android.util.Util;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class DFPAd {
    private String activityId;
    private String bannerLabel;
    private String closeButtonText;
    private Context context;
    private String eaid;
    private String ecid;
    private String id;
    private boolean immediate;
    private boolean isEditorial;
    private boolean isFullScreenAd = false;
    private boolean isModified = false;
    private boolean isTracked = false;
    private String labelColor;
    private String navigationBarColor;
    private boolean navigationBarHidden;
    private String navigationBarTitle;
    private String orientationLock;
    private String parId;
    private boolean sendBI;
    private String slideType;
    private String tcid;
    private boolean toolbarHidden;
    private String url;

    public DFPAd(Context context2) {
        this.context = context2;
        this.bannerLabel = context2.getResources().getString(R.string.advertisement);
        setLabelColor("#000000");
    }

    public void setInfo(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            this.id = jSONObject.optString("eaid") + "," + jSONObject.optString("ecid");
            String optString = jSONObject.optString("bannerLabel");
            if (!optString.isEmpty()) {
                this.bannerLabel = optString;
            }
            String optString2 = jSONObject.optString("labelColor");
            if (!optString2.isEmpty()) {
                if (!optString2.startsWith("#")) {
                    optString2 = "#" + optString2;
                }
                setLabelColor(optString2);
            }
            boolean optBoolean = jSONObject.optBoolean("fullscreen");
            this.isFullScreenAd = optBoolean;
            this.isTracked = optBoolean;
            this.isModified = true;
        } catch (JSONException unused) {
            Log.e("App Event", "Didn't find the name value or JSON is invalid");
        }
    }

    public void setFullScreenAdInfo(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            this.url = jSONObject.optString("url");
            this.navigationBarColor = jSONObject.optString("navigationBarColor");
            this.toolbarHidden = jSONObject.optBoolean("toolbarHidden");
            this.navigationBarColor = jSONObject.optString("navigationBarColor");
            this.navigationBarTitle = jSONObject.optString("navigationBarTitle");
            this.navigationBarHidden = jSONObject.optBoolean("navigationBarHidden");
            this.closeButtonText = jSONObject.optString("closeButtonText");
            this.immediate = jSONObject.optBoolean("immediate");
            this.sendBI = jSONObject.optBoolean("sendBI");
        } catch (JSONException unused) {
            Log.e("App Event", "Didn't find the name value or JSON is invalid");
        }
    }

    public void setSlideshowInfo(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str.trim());
            this.url = jSONObject.optString("contentURL");
            this.activityId = jSONObject.optString("activityId");
            this.tcid = jSONObject.optString("tcid");
            this.parId = jSONObject.optString("parId");
            this.isEditorial = jSONObject.optBoolean("isEditorial");
            this.orientationLock = jSONObject.optString("orientationLock");
            this.slideType = jSONObject.optString("slideType");
        } catch (JSONException unused) {
            Log.e("App Event", "Didn't find the name value or JSON is invalid");
        }
    }

    public String getBannerLabel() {
        return this.bannerLabel;
    }

    public String getId() {
        return this.id;
    }

    public boolean isFullScreenAd() {
        return this.isFullScreenAd;
    }

    public void setFullScreenAd(boolean z) {
        this.isFullScreenAd = z;
    }

    public boolean isImmediate() {
        return this.immediate;
    }

    public void setImmediate(boolean z) {
        this.immediate = z;
    }

    public class Rendition {
        private String announceURL;
        private int chrome = -1;
        private String imageUrl;
        private boolean isAnnounced;
        private String mHighResImageUrl = "";
        private Map<String, Click> map;
        private String renditionId;
        private String targetURL;

        public Rendition(String str) {
            this.renditionId = str;
        }

        public String getTargetURL() {
            return this.targetURL;
        }

        public void setTargetURL(String str) {
            this.targetURL = str;
        }

        public String getRenditionId() {
            return this.renditionId;
        }

        public void setRenditionId(String str) {
            this.renditionId = str;
        }

        public int getChrome() {
            return this.chrome;
        }

        public void setChrome(int i) {
            this.chrome = i;
        }

        public void setMap(Map<String, Click> map2) {
            this.map = map2;
        }

        public Map<String, Click> getMap() {
            return this.map;
        }

        public String getAnnounceURL() {
            return this.announceURL;
        }

        public void setAnnounceURL(String str) {
            this.announceURL = str;
        }

        public void setImageUrl(String str) {
            this.imageUrl = str;
        }

        public String getImageUrl() {
            return this.imageUrl;
        }

        public void setHighResImageUrl(String str) {
            this.mHighResImageUrl = str;
        }

        public String getHightResImageUrl() {
            return this.mHighResImageUrl;
        }

        public boolean isAnnounced() {
            return this.isAnnounced;
        }

        public void setAnnounced(boolean z) {
            this.isAnnounced = z;
        }

        public class Click {
            private String clickUrl = "";
            private boolean isPopup = true;
            private boolean isnavbar = true;
            private boolean istoolbar = true;
            private String name;
            private String navBarButtonTitle = "Close";
            private String navBarColor = Util.AD_DEFAULT_NAV_BAR_COLOR;
            private String navBarTitle = Util.AD_DEFAULT_NAV_BAR_TITLE;
            private String targetUrl = "";

            public Click() {
            }

            public String getClickUrl() {
                return this.clickUrl;
            }

            public void setClickUrl(String str) {
                this.clickUrl = str;
            }

            public String getTargetUrl() {
                return this.targetUrl;
            }

            public void setTargetUrl(String str) {
                this.targetUrl = str;
            }

            public boolean isPopup() {
                return this.isPopup;
            }

            public void setPopup(boolean z) {
                this.isPopup = z;
            }

            public boolean isIstoolbar() {
                return this.istoolbar;
            }

            public void setIstoolbar(boolean z) {
                this.istoolbar = z;
            }

            public boolean isIsnavbar() {
                return this.isnavbar;
            }

            public void setIsnavbar(boolean z) {
                this.isnavbar = z;
            }

            public String getNavBarColor() {
                return this.navBarColor;
            }

            public void setNavBarColor(String str) {
                this.navBarColor = str;
            }

            public String getNavBarTitle() {
                return this.navBarTitle;
            }

            public void setNavBarTitle(String str) {
                this.navBarTitle = str;
            }

            public String getNavBarButtonTitle() {
                return this.navBarButtonTitle;
            }

            public void setNavBarButtonTitle(String str) {
                this.navBarButtonTitle = str;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getName() {
                return this.name;
            }
        }
    }

    public String getUrl() {
        return this.url;
    }

    public String getNavigationBarColor() {
        return this.navigationBarColor;
    }

    public boolean isToolbarHidden() {
        return this.toolbarHidden;
    }

    public boolean isNavigationBarHidden() {
        return this.navigationBarHidden;
    }

    public String getNavigationBarTitle() {
        return this.navigationBarTitle;
    }

    public String getCloseButtonText() {
        return this.closeButtonText;
    }

    public boolean isTracked() {
        return this.isTracked;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public String getTcid() {
        return this.tcid;
    }

    public String getParId() {
        return this.parId;
    }

    public boolean isEditorial() {
        return this.isEditorial;
    }

    public String getOrientationLock() {
        return this.orientationLock;
    }

    public String getSlideType() {
        return this.slideType;
    }

    public String getLabelColor() {
        return this.labelColor;
    }

    public void setLabelColor(String str) {
        this.labelColor = str;
    }

    public boolean isModified() {
        return this.isModified;
    }

    public void setModified(boolean z) {
        this.isModified = z;
    }

    public boolean isSendBI() {
        return this.sendBI;
    }
}
