package org.liquidplayer.javascript;

import com.fasterxml.jackson.core.JsonFactory;

public class JSON extends JSValue {
    private JSON(JSContext jSContext, String str) {
        this.context = jSContext;
        this.valueRef = this.context.ctxRef().makeFromJSONString(str);
    }

    public static String stringify(JSValue jSValue) {
        return jSValue.getContext().property(JsonFactory.FORMAT_NAME_JSON).toObject().property("stringify").toFunction().call((JSObject) null, jSValue).toString();
    }

    public static String stringify(JSContext jSContext, Object obj) {
        return jSContext.property(JsonFactory.FORMAT_NAME_JSON).toObject().property("stringify").toFunction().call((JSObject) null, obj).toString();
    }

    public static JSValue parse(JSContext jSContext, String str) {
        return new JSON(jSContext, str);
    }
}
