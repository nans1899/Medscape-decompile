package org.liquidplayer.javascript;

public class JSFloat32Array extends JSTypedArray<Float> {
    public JSFloat32Array(JSContext jSContext, int i) {
        super(jSContext, i, "Float32Array", Float.class);
    }

    public JSFloat32Array(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Float32Array", Float.class);
    }

    public JSFloat32Array(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Float32Array", Float.class);
    }

    public JSFloat32Array(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Float32Array", Float.class);
    }

    public JSFloat32Array(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Float32Array", Float.class);
    }

    public JSFloat32Array(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Float32Array", Float.class);
    }

    JSFloat32Array(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Float.class);
    }

    public JSFloat32Array subarray(int i, int i2) {
        return (JSFloat32Array) super.subarray(i, i2);
    }

    public JSFloat32Array subarray(int i) {
        return (JSFloat32Array) super.subarray(i);
    }

    private JSFloat32Array(JSFloat32Array jSFloat32Array, int i, int i2) {
        super((JSTypedArray) jSFloat32Array, i, i2, Float.class);
    }

    public JSFloat32Array subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSFloat32Array(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
