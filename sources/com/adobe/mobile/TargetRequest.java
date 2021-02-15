package com.adobe.mobile;

import java.util.HashMap;
import java.util.Map;

class TargetRequest {
    private String mboxName;
    private Map<String, Object> mboxParameters;
    private Map<String, Object> orderParameters;
    private Map<String, Object> productParameters;

    protected TargetRequest(String str, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        this.mboxName = str;
        if (map != null) {
            this.mboxParameters = new HashMap(map);
        }
        if (map2 != null) {
            this.orderParameters = new HashMap(map2);
        }
        if (map3 != null) {
            this.productParameters = new HashMap(map3);
        }
    }

    public String getMboxName() {
        return this.mboxName;
    }

    public Map<String, Object> getMboxParameters() {
        return this.mboxParameters;
    }

    public Map<String, Object> getOrderParameters() {
        return this.orderParameters;
    }

    public Map<String, Object> getProductParameters() {
        return this.productParameters;
    }
}
