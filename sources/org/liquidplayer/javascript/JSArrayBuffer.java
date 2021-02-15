package org.liquidplayer.javascript;

public class JSArrayBuffer extends JSObjectWrapper {
    public /* bridge */ /* synthetic */ JSObject getJSObject() {
        return super.getJSObject();
    }

    public JSArrayBuffer(JSContext jSContext, int i) {
        super(new JSFunction(jSContext, "_ArrayBuffer", new String[]{Name.LENGTH}, "return new ArrayBuffer(length);", (String) null, 0).call((JSObject) null, Integer.valueOf(i)).toObject());
    }

    public JSArrayBuffer(JSObject jSObject) {
        super(jSObject);
    }

    public int byteLength() {
        return property("byteLength").toNumber().intValue();
    }

    public static boolean isView(JSValue jSValue) {
        return jSValue.getContext().property("ArrayBuffer").toObject().property("isView").toFunction().call((JSObject) null, jSValue).toBoolean().booleanValue();
    }

    public JSArrayBuffer slice(int i, int i2) {
        return new JSArrayBuffer(property("slice").toFunction().call(this, Integer.valueOf(i), Integer.valueOf(i2)).toObject());
    }

    public JSArrayBuffer slice(int i) {
        return new JSArrayBuffer(property("slice").toFunction().call(this, Integer.valueOf(i)).toObject());
    }
}
