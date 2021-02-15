package org.liquidplayer.javascript;

public class JSInt16Array extends JSTypedArray<Short> {
    public JSInt16Array(JSContext jSContext, int i) {
        super(jSContext, i, "Int16Array", Short.class);
    }

    public JSInt16Array(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Int16Array", Short.class);
    }

    public JSInt16Array(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Int16Array", Short.class);
    }

    public JSInt16Array(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Int16Array", Short.class);
    }

    public JSInt16Array(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Int16Array", Short.class);
    }

    public JSInt16Array(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Int16Array", Short.class);
    }

    JSInt16Array(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Short.class);
    }

    public JSInt16Array subarray(int i, int i2) {
        return (JSInt16Array) super.subarray(i, i2);
    }

    public JSInt16Array subarray(int i) {
        return (JSInt16Array) super.subarray(i);
    }

    private JSInt16Array(JSInt16Array jSInt16Array, int i, int i2) {
        super((JSTypedArray) jSInt16Array, i, i2, Short.class);
    }

    public JSInt16Array subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSInt16Array(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
