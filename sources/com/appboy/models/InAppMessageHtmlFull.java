package com.appboy.models;

import bo.app.bq;
import com.appboy.enums.inappmessage.MessageType;
import org.json.JSONException;
import org.json.JSONObject;

public class InAppMessageHtmlFull extends InAppMessageHtmlBase {
    public InAppMessageHtmlFull() {
    }

    public InAppMessageHtmlFull(JSONObject jSONObject, bq bqVar) {
        super(jSONObject, bqVar);
    }

    public JSONObject forJsonPut() {
        if (this.h != null) {
            return this.h;
        }
        try {
            JSONObject forJsonPut = super.forJsonPut();
            forJsonPut.put("type", MessageType.HTML_FULL.name());
            return forJsonPut;
        } catch (JSONException unused) {
            return null;
        }
    }
}
