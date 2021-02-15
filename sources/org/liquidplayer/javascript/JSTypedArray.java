package org.liquidplayer.javascript;

import com.google.firebase.analytics.FirebaseAnalytics;

public abstract class JSTypedArray<T> extends JSBaseArray<T> {
    protected JSTypedArray(JSContext jSContext, int i, String str, Class<T> cls) {
        super(jSContext, cls);
        this.valueRef = new JSFunction(this.context, "_" + str, new String[]{Name.LENGTH}, "return new " + str + "(length);", (String) null, 0).call((JSObject) null, Integer.valueOf(i)).valueRef();
        addJSExports();
        this.context.persistObject(this);
    }

    protected JSTypedArray(JSTypedArray jSTypedArray, String str, Class<T> cls) {
        super(jSTypedArray.context, cls);
        JSContext jSContext = this.context;
        this.valueRef = new JSFunction(jSContext, "_" + str, new String[]{"tarr"}, "return new " + str + "(tarr);", (String) null, 0).call((JSObject) null, jSTypedArray).valueRef();
        addJSExports();
        this.context.persistObject(this);
    }

    protected JSTypedArray(JSContext jSContext, Object obj, String str, Class<T> cls) {
        super(jSContext, cls);
        this.context = jSContext;
        JSContext jSContext2 = this.context;
        this.valueRef = new JSFunction(jSContext2, "_" + str, new String[]{"obj"}, "return new " + str + "(obj);", (String) null, 0).call((JSObject) null, obj).valueRef();
        addJSExports();
        this.context.persistObject(this);
    }

    protected JSTypedArray(JSArrayBuffer jSArrayBuffer, int i, int i2, String str, Class<T> cls) {
        super(jSArrayBuffer.getJSObject().getContext(), cls);
        JSContext jSContext = this.context;
        this.valueRef = new JSFunction(jSContext, "_" + str, new String[]{"buffer,byteOffset,length"}, "return new " + str + "(buffer,byteOffset,length);", (String) null, 0).call((JSObject) null, jSArrayBuffer.getJSObject(), Integer.valueOf(i), Integer.valueOf(i2)).valueRef();
        addJSExports();
        this.context.persistObject(this);
    }

    protected JSTypedArray(JSArrayBuffer jSArrayBuffer, int i, String str, Class<T> cls) {
        super(jSArrayBuffer.getJSObject().getContext(), cls);
        JSContext jSContext = this.context;
        this.valueRef = new JSFunction(jSContext, "_" + str, new String[]{"buffer,byteOffset"}, "return new " + str + "(buffer,byteOffset);", (String) null, 0).call((JSObject) null, jSArrayBuffer.getJSObject(), Integer.valueOf(i)).valueRef();
        addJSExports();
        this.context.persistObject(this);
    }

    protected JSTypedArray(JSArrayBuffer jSArrayBuffer, String str, Class<T> cls) {
        super(jSArrayBuffer.getJSObject().getContext(), cls);
        JSContext jSContext = this.context;
        this.valueRef = new JSFunction(jSContext, "_" + str, new String[]{"buffer"}, "return new " + str + "(buffer);", (String) null, 0).call((JSObject) null, jSArrayBuffer.getJSObject()).valueRef();
        addJSExports();
        this.context.persistObject(this);
    }

    protected JSTypedArray(JNIJSObject jNIJSObject, JSContext jSContext, Class<T> cls) {
        super(jNIJSObject, jSContext, cls);
    }

    protected JSTypedArray(JSTypedArray jSTypedArray, int i, int i2, Class<T> cls) {
        super(jSTypedArray, i, i2, cls);
    }

    public static JSTypedArray from(JSObject jSObject) {
        if (jSObject.isInt8Array().booleanValue()) {
            return new JSInt8Array(jSObject.toObject().JNI(), jSObject.getContext());
        }
        if (jSObject.isUint8Array().booleanValue()) {
            return new JSUint8Array(jSObject.toObject().JNI(), jSObject.getContext());
        }
        if (jSObject.isUint8ClampedArray().booleanValue()) {
            return new JSUint8ClampedArray(jSObject.toObject().JNI(), jSObject.getContext());
        }
        if (jSObject.isInt16Array().booleanValue()) {
            return new JSInt16Array(jSObject.toObject().JNI(), jSObject.getContext());
        }
        if (jSObject.isUint16Array().booleanValue()) {
            return new JSUint16Array(jSObject.toObject().JNI(), jSObject.getContext());
        }
        if (jSObject.isInt32Array().booleanValue()) {
            return new JSInt32Array(jSObject.toObject().JNI(), jSObject.getContext());
        }
        if (jSObject.isUint32Array().booleanValue()) {
            return new JSUint32Array(jSObject.toObject().JNI(), jSObject.getContext());
        }
        if (jSObject.isFloat32Array().booleanValue()) {
            return new JSFloat32Array(jSObject.toObject().JNI(), jSObject.getContext());
        }
        if (jSObject.isFloat64Array().booleanValue()) {
            return new JSFloat64Array(jSObject.toObject().JNI(), jSObject.getContext());
        }
        throw new JSException(jSObject.getContext(), "Object not a typed array");
    }

    public JSArrayBuffer buffer() {
        return new JSArrayBuffer(property("buffer").toObject());
    }

    public int byteLength() {
        return property("byteLength").toNumber().intValue();
    }

    public int byteOffset() {
        return property("byteOffset").toNumber().intValue();
    }

    /* access modifiers changed from: protected */
    public JSValue arrayElement(int i) {
        return new JSFunction(this.context, "_getElement", new String[]{"thiz", FirebaseAnalytics.Param.INDEX}, "return thiz[index]", (String) null, 0).call((JSObject) null, this, Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void arrayElement(int i, T t) {
        new JSFunction(this.context, "_setElement", new String[]{"thiz", FirebaseAnalytics.Param.INDEX, "value"}, "thiz[index] = value", (String) null, 0).call((JSObject) null, this, Integer.valueOf(i), t);
    }

    public boolean add(T t) throws JSException {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public JSTypedArray<T> subarray(int i, int i2) {
        return (JSTypedArray) property("subarray").toFunction().call(this, Integer.valueOf(i), Integer.valueOf(i2)).toObject().toJSArray();
    }

    /* access modifiers changed from: protected */
    public JSTypedArray<T> subarray(int i) {
        return (JSTypedArray) property("subarray").toFunction().call(this, Integer.valueOf(i)).toObject().toJSArray();
    }
}
