package org.liquidplayer.javascript;

public class JSFloat64Array extends JSTypedArray<Double> {
    public JSFloat64Array(JSContext jSContext, int i) {
        super(jSContext, i, "Float64Array", Double.class);
    }

    public JSFloat64Array(JSTypedArray jSTypedArray) {
        super(jSTypedArray, "Float64Array", Double.class);
    }

    public JSFloat64Array(JSContext jSContext, Object obj) {
        super(jSContext, obj, "Float64Array", Double.class);
    }

    public JSFloat64Array(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(jSArrayBuffer, i, i2, "Float64Array", Double.class);
    }

    public JSFloat64Array(JSArrayBuffer jSArrayBuffer, int i) {
        super(jSArrayBuffer, i, "Float64Array", Double.class);
    }

    public JSFloat64Array(JSArrayBuffer jSArrayBuffer) {
        super(jSArrayBuffer, "Float64Array", Double.class);
    }

    JSFloat64Array(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, Double.class);
    }

    public JSFloat64Array subarray(int i, int i2) {
        return (JSFloat64Array) super.subarray(i, i2);
    }

    public JSFloat64Array subarray(int i) {
        return (JSFloat64Array) super.subarray(i);
    }

    private JSFloat64Array(JSFloat64Array jSFloat64Array, int i, int i2) {
        super((JSTypedArray) jSFloat64Array, i, i2, Double.class);
    }

    public JSFloat64Array subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSFloat64Array(this, i, size() - i2);
        }
        throw new IndexOutOfBoundsException();
    }
}
