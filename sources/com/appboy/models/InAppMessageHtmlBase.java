package com.appboy.models;

import bo.app.bq;
import bo.app.bz;
import bo.app.cf;
import bo.app.fk;
import bo.app.fm;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class InAppMessageHtmlBase extends InAppMessageBase implements IInAppMessageHtml {
    private String j;
    private String k;
    private boolean l = false;
    private String m = null;

    protected InAppMessageHtmlBase() {
        this.g = true;
    }

    public InAppMessageHtmlBase(JSONObject jSONObject, bq bqVar) {
        super(jSONObject, bqVar);
        if (!StringUtils.isNullOrBlank(jSONObject.optString("zipped_assets_url"))) {
            this.j = jSONObject.optString("zipped_assets_url");
        }
        this.g = jSONObject.optBoolean("use_webview", true);
    }

    public String getLocalAssetsDirectoryUrl() {
        return this.k;
    }

    public String getAssetsZipRemoteUrl() {
        return this.j;
    }

    public String getRemoteAssetPathForPrefetch() {
        return getAssetsZipRemoteUrl();
    }

    public void setLocalAssetsDirectoryUrl(String str) {
        this.k = str;
    }

    public void setAssetsZipRemoteUrl(String str) {
        this.j = str;
    }

    public void setLocalAssetPathForPrefetch(String str) {
        setLocalAssetsDirectoryUrl(str);
    }

    public boolean logButtonClick(String str) {
        if (StringUtils.isNullOrEmpty(this.b) && StringUtils.isNullOrEmpty(this.c) && StringUtils.isNullOrEmpty(this.d)) {
            AppboyLogger.d(a, "Campaign, card, and trigger Ids not found. Not logging html in-app message click.");
            return false;
        } else if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.i(a, "Button Id was null or blank for this html in-app message. Ignoring.");
            return false;
        } else if (this.l) {
            AppboyLogger.i(a, "Button click already logged for this html in-app message. Ignoring.");
            return false;
        } else if (this.i == null) {
            AppboyLogger.e(a, "Cannot log an html in-app message button click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.i.a((bz) cf.a(this.b, this.c, this.d, str));
                this.m = str;
                this.l = true;
                return true;
            } catch (JSONException e) {
                this.i.a((Throwable) e);
                return false;
            }
        }
    }

    public void onAfterClosed() {
        super.onAfterClosed();
        if (this.l && !StringUtils.isNullOrBlank(this.d) && !StringUtils.isNullOrBlank(this.m)) {
            this.i.a((fk) new fm(this.d, this.m));
        }
    }

    public JSONObject forJsonPut() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.putOpt("zipped_assets_url", this.j);
            return forJsonPut;
        } catch (JSONException unused) {
            return null;
        }
    }
}
