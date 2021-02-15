package org.liquidplayer.javascript;

import com.facebook.share.internal.ShareConstants;

public class JSError extends JSObject {
    public JSError(JSContext jSContext, String str) {
        this.context = jSContext;
        this.valueRef = this.context.ctxRef().makeError(str);
        addJSExports();
        this.context.persistObject(this);
    }

    public JSError(JSContext jSContext) {
        this(jSContext, "Error");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    JSError(org.liquidplayer.javascript.JSValue r3) {
        /*
            r2 = this;
            java.lang.Boolean r0 = r3.isObject()
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0015
            org.liquidplayer.javascript.JSObject r0 = r3.toObject()
            org.liquidplayer.javascript.JNIJSValue r0 = r0.valueRef()
            org.liquidplayer.javascript.JNIJSObject r0 = (org.liquidplayer.javascript.JNIJSObject) r0
            goto L_0x0024
        L_0x0015:
            org.liquidplayer.javascript.JSError r0 = new org.liquidplayer.javascript.JSError
            org.liquidplayer.javascript.JSContext r1 = r3.getContext()
            r0.<init>((org.liquidplayer.javascript.JSContext) r1)
            org.liquidplayer.javascript.JNIJSValue r0 = r0.valueRef()
            org.liquidplayer.javascript.JNIJSObject r0 = (org.liquidplayer.javascript.JNIJSObject) r0
        L_0x0024:
            org.liquidplayer.javascript.JSContext r3 = r3.getContext()
            r2.<init>((org.liquidplayer.javascript.JNIJSObject) r0, (org.liquidplayer.javascript.JSContext) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.liquidplayer.javascript.JSError.<init>(org.liquidplayer.javascript.JSValue):void");
    }

    public String stack() {
        return property("stack").toString();
    }

    public String message() {
        return property(ShareConstants.WEB_DIALOG_PARAM_MESSAGE).toString();
    }

    public String name() {
        return property("name").toString();
    }
}
