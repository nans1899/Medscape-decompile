package org.liquidplayer.javascript;

public class JSUint16Array extends JSTypedArray<Short> {
    public JSUint16Array(JSContext jSContext, int i) {
        super(jSContext, i, "Uint16Array", Short.class);
    }

    public JSUint16Array(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Uint16Array", Short.class);
    }

    public JSUint16Array(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Uint16Array", Short.class);
    }

    public JSUint16Array(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Uint16Array", Short.class);
    }

    public JSUint16Array(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Uint16Array", Short.class);
    }

    public JSUint16Array(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Uint16Array", Short.class);
    }

    JSUint16Array(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Short.class);
    }

    public JSUint16Array subarray(int i, int i2) {
        return (JSUint16Array) super.subarray(i, i2);
    }

    public JSUint16Array subarray(int i) {
        return (JSUint16Array) super.subarray(i);
    }

    private JSUint16Array(JSUint16Array jSUint16Array, int i, int i2) {
        super((JSTypedArray) jSUint16Array, i, i2, Short.class);
    }

    public JSUint16Array subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSUint16Array(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
