package com.appboy.events;

import bo.app.ed;
import com.appboy.models.IInAppMessage;
import org.json.JSONObject;

public final class InAppMessageEvent {
    private final IInAppMessage a;
    private final String b;

    public InAppMessageEvent(IInAppMessage iInAppMessage, String str) {
        this.b = str;
        if (iInAppMessage != null) {
            this.a = iInAppMessage;
            return;
        }
        throw null;
    }

    public IInAppMessage getInAppMessage() {
        return this.a;
    }

    public String getUserId() {
        return this.b;
    }

    public String toString() {
        return ed.a((JSONObject) this.a.forJsonPut());
    }
}
