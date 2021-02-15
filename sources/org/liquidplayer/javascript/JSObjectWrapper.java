package org.liquidplayer.javascript;

abstract class JSObjectWrapper extends JSObject {
    private final JSObject mJSObject;

    JSObjectWrapper(JSObject jSObject) {
        this.mJSObject = jSObject;
        this.context = jSObject.getContext();
        this.valueRef = jSObject.valueRef();
    }

    public JSObject getJSObject() {
        return this.mJSObject;
    }
}
