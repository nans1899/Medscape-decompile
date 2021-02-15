package org.liquidplayer.javascript;

public class JSUint8Array extends JSTypedArray<Byte> {
    public JSUint8Array(JSContext jSContext, int i) {
        super(jSContext, i, "Uint8Array", Byte.class);
    }

    public JSUint8Array(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Uint8Array", Byte.class);
    }

    public JSUint8Array(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Uint8Array", Byte.class);
    }

    public JSUint8Array(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Uint8Array", Byte.class);
    }

    public JSUint8Array(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Uint8Array", Byte.class);
    }

    public JSUint8Array(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Uint8Array", Byte.class);
    }

    JSUint8Array(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Byte.class);
    }

    public JSUint8Array subarray(int i, int i2) {
        return (JSUint8Array) super.subarray(i, i2);
    }

    public JSUint8Array subarray(int i) {
        return (JSUint8Array) super.subarray(i);
    }

    private JSUint8Array(JSUint8Array jSUint8Array, int i, int i2) {
        super((JSTypedArray) jSUint8Array, i, i2, Byte.class);
    }

    public JSUint8Array subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSUint8Array(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
