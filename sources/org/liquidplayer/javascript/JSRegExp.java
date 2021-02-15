package org.liquidplayer.javascript;

import com.google.firebase.analytics.FirebaseAnalytics;

public class JSRegExp extends JSObject {
    public JSRegExp(JSContext jSContext, String str, String str2) {
        this.context = jSContext;
        try {
            this.valueRef = this.context.ctxRef().makeRegExp(str, str2);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            this.valueRef = this.context.ctxRef().make();
        }
        addJSExports();
    }

    public JSRegExp(JSContext jSContext, String str) {
        this(jSContext, str, "");
    }

    public class ExecResult extends JSArray<String> {
        ExecResult(JSObject jSObject) {
            super((JNIJSObject) jSObject.valueRef(), jSObject.getContext(), String.class);
        }

        public Integer index() {
            return Integer.valueOf(property(FirebaseAnalytics.Param.INDEX).toNumber().intValue());
        }

        public String input() {
            return property("input").toString();
        }
    }

    public ExecResult exec(String str) {
        JSValue call = property("exec").toFunction().call(this, str);
        if (call.isNull().booleanValue()) {
            return null;
        }
        return new ExecResult(call.toObject());
    }

    public Integer lastIndex() {
        return Integer.valueOf(property("lastIndex").toNumber().intValue());
    }

    public Boolean ignoreCase() {
        return property("ignoreCase").toBoolean();
    }

    public Boolean global() {
        return property("global").toBoolean();
    }

    public Boolean multiline() {
        return property("multiline").toBoolean();
    }

    public String source() {
        return property("source").toString();
    }

    public Boolean test(String str) {
        return property("test").toFunction().call(this, str).toBoolean();
    }
}
