package org.liquidplayer.javascript;

import com.google.android.gms.ads.AdError;

public class JSException extends RuntimeException {
    private static final long serialVersionUID = 1;
    private JSError error;

    public JSException(JSValue jSValue) {
        super(new JSError(jSValue).message());
        this.error = new JSError(jSValue);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JSException(JSContext jSContext, String str) {
        super(str == null ? "Error" : str);
        try {
            this.error = new JSError(jSContext, str == null ? "Error" : str);
        } catch (JSException unused) {
            this.error = null;
        }
    }

    public JSError getError() {
        return this.error;
    }

    public String stack() {
        JSError jSError = this.error;
        return jSError != null ? jSError.stack() : AdError.UNDEFINED_DOMAIN;
    }

    public String name() {
        JSError jSError = this.error;
        return jSError != null ? jSError.name() : "JSError";
    }

    public String toString() {
        JSError jSError = this.error;
        if (jSError != null) {
            try {
                return jSError.toString();
            } catch (JSException unused) {
            }
        }
        return "Unknown Error";
    }
}
