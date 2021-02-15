package com.appboy.models;

import bo.app.bq;
import bo.app.ed;
import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.ImageStyle;
import com.appboy.enums.inappmessage.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppMessageModal extends InAppMessageImmersiveBase {
    public InAppMessageModal() {
    }

    public InAppMessageModal(JSONObject jSONObject, bq bqVar) {
        super(jSONObject, bqVar);
        if (this.j.equals(ImageStyle.GRAPHIC)) {
            this.e = (CropType) ed.a(jSONObject, "crop_type", CropType.class, CropType.CENTER_CROP);
        } else {
            this.e = (CropType) ed.a(jSONObject, "crop_type", CropType.class, CropType.FIT_CENTER);
        }
    }

    public JSONObject forJsonPut() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.put("type", MessageType.MODAL.name());
            return forJsonPut;
        } catch (JSONException unused) {
            return null;
        }
    }
}
