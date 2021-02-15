package com.appboy.models;

import bo.app.bq;
import bo.app.bz;
import bo.app.cf;
import bo.app.ed;
import bo.app.fk;
import bo.app.fm;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.enums.inappmessage.TextAlign;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class InAppMessageImmersiveBase extends InAppMessageBase implements IInAppMessageImmersive {
    protected ImageStyle j;
    private String k;
    private int l;
    private int m;
    private List<MessageButton> n;
    private Integer o;
    private TextAlign p;
    private boolean q;
    private String r;

    protected InAppMessageImmersiveBase() {
        this.l = 0;
        this.m = 0;
        this.j = ImageStyle.TOP;
        this.o = null;
        this.p = TextAlign.CENTER;
        this.r = null;
    }

    public InAppMessageImmersiveBase(JSONObject jSONObject, bq bqVar) {
        this(jSONObject, bqVar, jSONObject.optString("header"), jSONObject.optInt("header_text_color"), jSONObject.optInt("close_btn_color"), (ImageStyle) ed.a(jSONObject, "image_style", ImageStyle.class, ImageStyle.TOP), (TextAlign) ed.a(jSONObject, "text_align_header", TextAlign.class, TextAlign.CENTER), (TextAlign) ed.a(jSONObject, "text_align_message", TextAlign.class, TextAlign.CENTER));
        if (jSONObject.optJSONArray("btns") != null) {
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("btns");
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(new MessageButton(optJSONArray.optJSONObject(i)));
            }
            setMessageButtons(arrayList);
        }
    }

    private InAppMessageImmersiveBase(JSONObject jSONObject, bq bqVar, String str, int i, int i2, ImageStyle imageStyle, TextAlign textAlign, TextAlign textAlign2) {
        super(jSONObject, bqVar);
        this.l = 0;
        this.m = 0;
        this.j = ImageStyle.TOP;
        this.o = null;
        this.p = TextAlign.CENTER;
        this.r = null;
        this.k = str;
        this.l = i;
        this.m = i2;
        if (jSONObject.has("frame_color")) {
            this.o = Integer.valueOf(jSONObject.optInt("frame_color"));
        }
        this.j = imageStyle;
        this.p = textAlign;
        this.f = textAlign2;
    }

    public String getHeader() {
        return this.k;
    }

    public int getHeaderTextColor() {
        return this.l;
    }

    public int getCloseButtonColor() {
        return this.m;
    }

    public List<MessageButton> getMessageButtons() {
        return this.n;
    }

    public Integer getFrameColor() {
        return this.o;
    }

    public ImageStyle getImageStyle() {
        return this.j;
    }

    public TextAlign getHeaderTextAlign() {
        return this.p;
    }

    public void setHeaderTextAlign(TextAlign textAlign) {
        this.p = textAlign;
    }

    public void setImageStyle(ImageStyle imageStyle) {
        this.j = imageStyle;
    }

    public void setFrameColor(Integer num) {
        this.o = num;
    }

    public void setMessageButtons(List<MessageButton> list) {
        this.n = list;
    }

    public void setHeader(String str) {
        this.k = str;
    }

    public void setHeaderTextColor(int i) {
        this.l = i;
    }

    public void setCloseButtonColor(int i) {
        this.m = i;
    }

    public boolean logButtonClick(MessageButton messageButton) {
        if (StringUtils.isNullOrBlank(this.b) && StringUtils.isNullOrBlank(this.c) && StringUtils.isNullOrBlank(this.d)) {
            AppboyLogger.d(a, "Campaign, trigger, and card Ids not found. Not logging button click.");
            return false;
        } else if (messageButton == null) {
            AppboyLogger.w(a, "Message button was null. Ignoring button click.");
            return false;
        } else if (this.q) {
            AppboyLogger.i(a, "Button click already logged for this message. Ignoring.");
            return false;
        } else if (this.i == null) {
            AppboyLogger.e(a, "Cannot log a button click because the AppboyManager is null.");
            return false;
        } else {
            try {
                cf a = cf.a(this.b, this.c, this.d, messageButton);
                this.r = cf.a(messageButton);
                this.i.a((bz) a);
                this.q = true;
                return true;
            } catch (JSONException e) {
                this.i.a((Throwable) e);
                return false;
            }
        }
    }

    public void onAfterClosed() {
        super.onAfterClosed();
        if (this.q && !StringUtils.isNullOrBlank(this.d) && !StringUtils.isNullOrBlank(this.r)) {
            this.i.a((fk) new fm(this.d, this.r));
        }
    }

    public JSONObject forJsonPut() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.putOpt("header", this.k);
            forJsonPut.put("header_text_color", this.l);
            forJsonPut.put("close_btn_color", this.m);
            forJsonPut.putOpt("image_style", this.j.toString());
            forJsonPut.putOpt("text_align_header", this.p.toString());
            if (this.o != null) {
                forJsonPut.put("frame_color", this.o.intValue());
            }
            if (this.n != null) {
                JSONArray jSONArray = new JSONArray();
                for (MessageButton forJsonPut2 : this.n) {
                    jSONArray.put(forJsonPut2.forJsonPut());
                }
                forJsonPut.put("btns", jSONArray);
            }
            return forJsonPut;
        } catch (JSONException unused) {
            return null;
        }
    }
}
