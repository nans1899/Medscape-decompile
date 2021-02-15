package org.liquidplayer.javascript;

public class JSUint32Array extends JSTypedArray<Long> {
    public JSUint32Array(JSContext jSContext, int i) {
        super(jSContext, i, "Uint32Array", Long.class);
    }

    public JSUint32Array(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Uint32Array", Long.class);
    }

    public JSUint32Array(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Uint32Array", Long.class);
    }

    public JSUint32Array(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Uint32Array", Long.class);
    }

    public JSUint32Array(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Uint32Array", Long.class);
    }

    public JSUint32Array(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Uint32Array", Long.class);
    }

    JSUint32Array(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Long.class);
    }

    public JSUint32Array subarray(int i, int i2) {
        return (JSUint32Array) super.subarray(i, i2);
    }

    public JSUint32Array subarray(int i) {
        return (JSUint32Array) super.subarray(i);
    }

    private JSUint32Array(JSUint32Array jSUint32Array, int i, int i2) {
        super((JSTypedArray) jSUint32Array, i, i2, Long.class);
    }

    public JSUint32Array subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSUint32Array(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
