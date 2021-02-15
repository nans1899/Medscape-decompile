package com.appboy.models;

import bo.app.bq;
import bo.app.ed;
import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppMessageFull extends InAppMessageImmersiveBase {
    public InAppMessageFull() {
        this.e = CropType.CENTER_CROP;
    }

    public InAppMessageFull(JSONObject jSONObject, bq bqVar) {
        super(jSONObject, bqVar);
        this.e = (CropType) ed.a(jSONObject, "crop_type", CropType.class, CropType.CENTER_CROP);
    }

    public JSONObject forJsonPut() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.put("type", MessageType.FULL.name());
            return forJsonPut;
        } catch (JSONException unused) {
            return null;
        }
    }
}
