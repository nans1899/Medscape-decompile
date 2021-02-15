package org.liquidplayer.javascript;

public class JSInt32Array extends JSTypedArray<Integer> {
    public JSInt32Array(JSContext jSContext, int i) {
        super(jSContext, i, "Int32Array", Integer.class);
    }

    public JSInt32Array(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Int32Array", Integer.class);
    }

    public JSInt32Array(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Int32Array", Integer.class);
    }

    public JSInt32Array(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Int32Array", Integer.class);
    }

    public JSInt32Array(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Int32Array", Integer.class);
    }

    public JSInt32Array(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Int32Array", Integer.class);
    }

    JSInt32Array(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Integer.class);
    }

    public JSInt32Array subarray(int i, int i2) {
        return (JSInt32Array) super.subarray(i, i2);
    }

    public JSInt32Array subarray(int i) {
        return (JSInt32Array) super.subarray(i);
    }

    private JSInt32Array(JSInt32Array jSInt32Array, int i, int i2) {
        super((JSTypedArray) jSInt32Array, i, i2, Integer.class);
    }

    public JSInt32Array subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSInt32Array(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
