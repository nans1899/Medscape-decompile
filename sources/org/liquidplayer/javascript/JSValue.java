package org.liquidplayer.javascript;

import com.appboy.Constants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSValue {
    protected JSContext context = null;
    protected JNIJSValue valueRef;

    protected JSValue() {
    }

    public JSValue(JSContext jSContext) {
        this.context = jSContext;
        this.valueRef = jSContext.ctxRef().makeUndefined();
    }

    public JSValue(JSContext jSContext, Object obj) {
        Class<JSValue> cls = JSValue.class;
        this.context = jSContext;
        if (obj == null) {
            this.valueRef = jSContext.ctxRef().makeNull();
        } else if (obj instanceof JSValue) {
            this.valueRef = ((JSValue) obj).valueRef();
        } else if ((obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            this.valueRef = JSON.parse(this.context, obj.toString()).valueRef();
        } else if (obj instanceof Map) {
            this.valueRef = new JSObjectPropertiesMap(jSContext, (Map) obj, Object.class).getJSObject().valueRef();
        } else if (obj instanceof List) {
            this.valueRef = new JSArray(jSContext, (Collection) (List) obj, cls).valueRef();
        } else if (obj.getClass().isArray()) {
            this.valueRef = new JSArray(this.context, (Object[]) obj, cls).valueRef();
        } else if (obj instanceof Boolean) {
            this.valueRef = this.context.ctxRef().makeBoolean(((Boolean) obj).booleanValue());
        } else if (obj instanceof Double) {
            this.valueRef = this.context.ctxRef().makeNumber(((Double) obj).doubleValue());
        } else if (obj instanceof Float) {
            this.valueRef = this.context.ctxRef().makeNumber(Double.valueOf(obj.toString()).doubleValue());
        } else if (obj instanceof Integer) {
            this.valueRef = this.context.ctxRef().makeNumber(((Integer) obj).doubleValue());
        } else if (obj instanceof Long) {
            this.valueRef = this.context.ctxRef().makeNumber(((Long) obj).doubleValue());
        } else if (obj instanceof Byte) {
            this.valueRef = this.context.ctxRef().makeNumber(((Byte) obj).doubleValue());
        } else if (obj instanceof Short) {
            this.valueRef = this.context.ctxRef().makeNumber(((Short) obj).doubleValue());
        } else if (obj instanceof String) {
            this.valueRef = this.context.ctxRef().makeString((String) obj);
        } else {
            this.valueRef = this.context.ctxRef().makeUndefined();
        }
    }

    JSValue(JNIJSValue jNIJSValue, JSContext jSContext) {
        this.context = jSContext;
        if (jNIJSValue != null) {
            this.valueRef = jNIJSValue;
        } else {
            this.valueRef = jSContext.ctxRef().makeUndefined();
        }
    }

    public Boolean isUndefined() {
        return Boolean.valueOf(valueRef().isUndefined());
    }

    public Boolean isNull() {
        return Boolean.valueOf(valueRef().isNull());
    }

    public Boolean isBoolean() {
        return Boolean.valueOf(valueRef().isBoolean());
    }

    public Boolean isNumber() {
        return Boolean.valueOf(valueRef().isNumber());
    }

    public Boolean isString() {
        return Boolean.valueOf(valueRef().isString());
    }

    public Boolean isArray() {
        return Boolean.valueOf(valueRef().isArray());
    }

    public Boolean isDate() {
        return Boolean.valueOf(valueRef().isDate());
    }

    public Boolean isTypedArray() {
        return Boolean.valueOf(valueRef().isTypedArray());
    }

    public Boolean isInt8Array() {
        return Boolean.valueOf(valueRef().isInt8Array());
    }

    public Boolean isInt16Array() {
        return Boolean.valueOf(valueRef().isInt16Array());
    }

    public Boolean isInt32Array() {
        return Boolean.valueOf(valueRef().isInt32Array());
    }

    public Boolean isUint8ClampedArray() {
        return Boolean.valueOf(valueRef().isUint8ClampedArray());
    }

    public Boolean isUint8Array() {
        return Boolean.valueOf(valueRef().isUint8Array());
    }

    public Boolean isUint16Array() {
        return Boolean.valueOf(valueRef().isUint16Array());
    }

    public Boolean isUint32Array() {
        return Boolean.valueOf(valueRef().isUint32Array());
    }

    public Boolean isFloat32Array() {
        return Boolean.valueOf(valueRef().isFloat32Array());
    }

    public Boolean isFloat64Array() {
        return Boolean.valueOf(valueRef().isFloat64Array());
    }

    public Boolean isObject() {
        return Boolean.valueOf(valueRef().isObject());
    }

    public Boolean isInstanceOfConstructor(JSObject jSObject) {
        try {
            return new JSFunction(this.context, "_instanceOf", new String[]{Constants.APPBOY_PUSH_CONTENT_KEY, "b"}, "return (a instanceof b);", (String) null, 0).call((JSObject) null, this, jSObject).toBoolean();
        } catch (JSException unused) {
            return false;
        }
    }

    public boolean equals(Object obj) {
        return isEqual(obj);
    }

    public boolean isEqual(Object obj) {
        JSValue jSValue;
        if (obj == this) {
            return true;
        }
        if (obj instanceof JSValue) {
            jSValue = (JSValue) obj;
        } else {
            jSValue = new JSValue(this.context, obj);
        }
        try {
            return valueRef().isEqual(jSValue.valueRef);
        } catch (JNIJSException unused) {
            return false;
        }
    }

    public boolean isStrictEqual(Object obj) {
        JSValue jSValue;
        if (obj == this) {
            return true;
        }
        if (obj instanceof JSValue) {
            jSValue = (JSValue) obj;
        } else {
            jSValue = new JSValue(this.context, obj);
        }
        return valueRef().isStrictEqual(jSValue.valueRef);
    }

    public Boolean toBoolean() {
        return Boolean.valueOf(valueRef().toBoolean());
    }

    public Double toNumber() {
        try {
            return Double.valueOf(valueRef().toNumber());
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
    }

    public String toString() {
        try {
            return valueRef().toStringCopy();
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return "";
        }
    }

    public JSObject toObject() {
        if (this instanceof JSObject) {
            return (JSObject) this;
        }
        try {
            return this.context.getObjectFromRef(valueRef().toObject());
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return new JSObject(this.context);
        }
    }

    public JSFunction toFunction() {
        if (isObject().booleanValue()) {
            JSObject object = toObject();
            if (object instanceof JSFunction) {
                return (JSFunction) object;
            }
        }
        this.context.throwJSException(new JSException(this.context, "JSObject not a function"));
        return null;
    }

    public JSBaseArray toJSArray() {
        if (isObject().booleanValue()) {
            JSObject object = toObject();
            if (object instanceof JSBaseArray) {
                return (JSBaseArray) object;
            }
        }
        this.context.throwJSException(new JSException(this.context, "JSObject not an array"));
        return null;
    }

    public String toJSON() {
        try {
            JSValue jSValue = new JSValue(valueRef().createJSONString(), this.context);
            if (jSValue.isUndefined().booleanValue()) {
                return null;
            }
            return jSValue.toString();
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Object toJavaObject(Class cls) {
        if (cls == Object.class) {
            return this;
        }
        if (cls == JSONObject.class) {
            try {
                return new JSONObject(toJSON());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } else if (cls == JSONArray.class) {
            return new JSONArray(toJSON());
        } else {
            if (cls == Map.class) {
                return new JSObjectPropertiesMap(toObject(), Object.class);
            }
            if (cls == List.class) {
                return toJSArray();
            }
            if (cls == String.class) {
                return toString();
            }
            if (cls == Double.class || cls == Double.TYPE) {
                return toNumber();
            }
            if (cls == Float.class || cls == Float.TYPE) {
                return Float.valueOf(toNumber().floatValue());
            }
            if (cls == Integer.class || cls == Integer.TYPE) {
                return Integer.valueOf(toNumber().intValue());
            }
            if (cls == Long.class || cls == Long.TYPE) {
                return Long.valueOf(toNumber().longValue());
            }
            if (cls == Byte.class || cls == Byte.TYPE) {
                return Byte.valueOf(toNumber().byteValue());
            }
            if (cls == Short.class || cls == Short.TYPE) {
                return Short.valueOf(toNumber().shortValue());
            }
            if (cls == Boolean.class || cls == Boolean.TYPE) {
                return toBoolean();
            }
            if (cls.isArray()) {
                return toJSArray().toArray((Class) cls.getComponentType());
            }
            if (JSArray.class.isAssignableFrom(cls)) {
                return cls.cast(toJSArray());
            }
            if (JSObject.class.isAssignableFrom(cls)) {
                return cls.cast(toObject());
            }
            if (JSValue.class.isAssignableFrom(cls)) {
                return cls.cast(this);
            }
            return null;
        }
    }

    public int hashCode() {
        if (isBoolean().booleanValue()) {
            return toBoolean().hashCode();
        }
        if (isNumber().booleanValue()) {
            return toNumber().hashCode();
        }
        if (isString().booleanValue()) {
            return toString().hashCode();
        }
        if (isUndefined().booleanValue() || isNull().booleanValue()) {
            return 0;
        }
        return super.hashCode();
    }

    public JSContext getContext() {
        return this.context;
    }

    /* access modifiers changed from: package-private */
    public JNIJSValue valueRef() {
        return this.valueRef;
    }

    public long valueHash() {
        return valueRef().reference;
    }
}
