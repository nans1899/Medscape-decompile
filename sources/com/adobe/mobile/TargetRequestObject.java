package com.adobe.mobile;

import com.adobe.mobile.Target;
import java.util.Map;

public class TargetRequestObject extends TargetRequest {
    private Target.TargetCallback<String> callback;
    private String defaultContent;

    public /* bridge */ /* synthetic */ String getMboxName() {
        return super.getMboxName();
    }

    public /* bridge */ /* synthetic */ Map getMboxParameters() {
        return super.getMboxParameters();
    }

    public /* bridge */ /* synthetic */ Map getOrderParameters() {
        return super.getOrderParameters();
    }

    public /* bridge */ /* synthetic */ Map getProductParameters() {
        return super.getProductParameters();
    }

    protected TargetRequestObject(String str, String str2, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3, Target.TargetCallback<String> targetCallback) {
        super(str, map, map2, map3);
        this.defaultContent = str2;
        this.callback = targetCallback;
    }

    public String getDefaultContent() {
        return this.defaultContent;
    }

    public Target.TargetCallback<String> getCallback() {
        return this.callback;
    }
}
