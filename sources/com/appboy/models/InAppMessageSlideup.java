package com.appboy.models;

import bo.app.bq;
import bo.app.ed;
import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.MessageType;
import com.appboy.enums.inappmessage.SlideFrom;
import com.appboy.enums.inappmessage.TextAlign;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppMessageSlideup extends InAppMessageBase {
    private SlideFrom j;
    private int k;

    public InAppMessageSlideup() {
        this.j = SlideFrom.BOTTOM;
        this.f = TextAlign.START;
    }

    public InAppMessageSlideup(JSONObject jSONObject, bq bqVar) {
        this(jSONObject, bqVar, (SlideFrom) ed.a(jSONObject, "slide_from", SlideFrom.class, SlideFrom.BOTTOM), jSONObject.optInt("close_btn_color"));
    }

    private InAppMessageSlideup(JSONObject jSONObject, bq bqVar, SlideFrom slideFrom, int i) {
        super(jSONObject, bqVar);
        this.j = SlideFrom.BOTTOM;
        this.j = slideFrom;
        if (slideFrom == null) {
            this.j = SlideFrom.BOTTOM;
        }
        this.k = i;
        this.e = (CropType) ed.a(jSONObject, "crop_type", CropType.class, CropType.FIT_CENTER);
        this.f = (TextAlign) ed.a(jSONObject, "text_align_message", TextAlign.class, TextAlign.START);
    }

    public SlideFrom getSlideFrom() {
        return this.j;
    }

    public int getChevronColor() {
        return this.k;
    }

    public void setChevronColor(int i) {
        this.k = i;
    }

    public void setSlideFrom(SlideFrom slideFrom) {
        this.j = slideFrom;
    }

    public JSONObject forJsonPut() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.putOpt("slide_from", this.j.toString());
            forJsonPut.put("close_btn_color", this.k);
            forJsonPut.put("type", MessageType.SLIDEUP.name());
            return forJsonPut;
        } catch (JSONException unused) {
            return null;
        }
    }
}
