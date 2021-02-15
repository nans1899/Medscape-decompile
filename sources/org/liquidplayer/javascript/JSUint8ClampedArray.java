package org.liquidplayer.javascript;

public class JSUint8ClampedArray extends JSTypedArray<Byte> {
    public JSUint8ClampedArray(JSContext jSContext, int i) {
        super(jSContext, i, "Uint8ClampedArray", Byte.class);
    }

    public JSUint8ClampedArray(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Uint8ClampedArray", Byte.class);
    }

    public JSUint8ClampedArray(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Uint8ClampedArray", Byte.class);
    }

    public JSUint8ClampedArray(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Uint8ClampedArray", Byte.class);
    }

    public JSUint8ClampedArray(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Uint8ClampedArray", Byte.class);
    }

    public JSUint8ClampedArray(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Uint8ClampedArray", Byte.class);
    }

    JSUint8ClampedArray(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Byte.class);
    }

    public JSUint8ClampedArray subarray(int i, int i2) {
        return (JSUint8ClampedArray) super.subarray(i, i2);
    }

    public JSUint8ClampedArray subarray(int i) {
        return (JSUint8ClampedArray) super.subarray(i);
    }

    private JSUint8ClampedArray(JSUint8ClampedArray jSUint8ClampedArray, int i, int i2) {
        super((JSTypedArray) jSUint8ClampedArray, i, i2, Byte.class);
    }

    public JSUint8ClampedArray subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSUint8ClampedArray(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
