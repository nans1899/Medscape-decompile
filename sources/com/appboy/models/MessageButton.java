package com.appboy.models;

import android.net.Uri;
import bo.app.ed;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageButton implements IPutIntoJson<JSONObject> {
    private static final String a = AppboyLogger.getAppboyLogTag(MessageButton.class);
    private JSONObject b;
    private int c;
    private ClickAction d;
    private Uri e;
    private String f;
    private int g;
    private int h;
    private boolean i;

    public MessageButton() {
        this.c = -1;
        this.d = ClickAction.NONE;
        this.g = 0;
        this.h = 0;
    }

    public MessageButton(JSONObject jSONObject) {
        this(jSONObject, jSONObject.optInt("id", -1), (ClickAction) ed.a(jSONObject, "click_action", ClickAction.class, ClickAction.NEWS_FEED), jSONObject.optString("uri"), jSONObject.optString("text"), jSONObject.optInt("bg_color"), jSONObject.optInt("text_color"), jSONObject.optBoolean("use_webview", false));
    }

    private MessageButton(JSONObject jSONObject, int i2, ClickAction clickAction, String str, String str2, int i3, int i4, boolean z) {
        this.c = -1;
        this.d = ClickAction.NONE;
        this.g = 0;
        this.h = 0;
        this.b = jSONObject;
        this.c = i2;
        this.d = clickAction;
        if (clickAction == ClickAction.URI && !StringUtils.isNullOrBlank(str)) {
            this.e = Uri.parse(str);
        }
        this.f = str2;
        this.g = i3;
        this.h = i4;
        this.i = z;
    }

    public int getId() {
        return this.c;
    }

    public ClickAction getClickAction() {
        return this.d;
    }

    public Uri getUri() {
        return this.e;
    }

    public String getText() {
        return this.f;
    }

    public int getBackgroundColor() {
        return this.g;
    }

    public int getTextColor() {
        return this.h;
    }

    public boolean getOpenUriInWebview() {
        return this.i;
    }

    public void setOpenUriInWebview(boolean z) {
        this.i = z;
    }

    public boolean setClickAction(ClickAction clickAction) {
        if (clickAction != ClickAction.URI) {
            this.d = clickAction;
            this.e = null;
            return true;
        }
        AppboyLogger.e(a, "A non-null URI is required in order to set the button ClickAction to URI.");
        return false;
    }

    public boolean setClickAction(ClickAction clickAction, Uri uri) {
        if (uri == null && clickAction == ClickAction.URI) {
            AppboyLogger.e(a, "A non-null URI is required in order to set the button ClickAction to URI.");
            return false;
        } else if (uri == null || clickAction != ClickAction.URI) {
            return setClickAction(clickAction);
        } else {
            this.d = clickAction;
            this.e = uri;
            return true;
        }
    }

    public void setBackgroundColor(int i2) {
        this.g = i2;
    }

    public void setTextColor(int i2) {
        this.h = i2;
    }

    public void setText(String str) {
        this.f = str;
    }

    public JSONObject forJsonPut() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", this.c);
            jSONObject.put("click_action", this.d.toString());
            if (this.e != null) {
                jSONObject.put("uri", this.e.toString());
            }
            jSONObject.putOpt("text", this.f);
            jSONObject.put("bg_color", this.g);
            jSONObject.put("text_color", this.h);
            jSONObject.put("use_webview", this.i);
            return jSONObject;
        } catch (JSONException unused) {
            return this.b;
        }
    }
}
