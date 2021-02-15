package org.liquidplayer.javascript;

public class JSInt8Array extends JSTypedArray<Byte> {
    public JSInt8Array(JSContext jSContext, int i) {
        super(jSContext, i, "Int8Array", Byte.class);
    }

    public JSInt8Array(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Int8Array", Byte.class);
    }

    public JSInt8Array(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Int8Array", Byte.class);
    }

    public JSInt8Array(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Int8Array", Byte.class);
    }

    public JSInt8Array(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Int8Array", Byte.class);
    }

    public JSInt8Array(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Int8Array", Byte.class);
    }

    JSInt8Array(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Byte.class);
    }

    public JSInt8Array subarray(int i, int i2) {
        return (JSInt8Array) super.subarray(i, i2);
    }

    public JSInt8Array subarray(int i) {
        return (JSInt8Array) super.subarray(i);
    }

    private JSInt8Array(JSInt8Array jSInt8Array, int i, int i2) {
        super((JSTypedArray) jSInt8Array, i, i2, Byte.class);
    }

    public JSInt8Array subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSInt8Array(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
