package org.liquidplayer.javascript;

public class JSDataView extends JSObjectWrapper {
    public /* bridge */ /* synthetic */ JSObject getJSObject() {
        return super.getJSObject();
    }

    public JSDataView(JSArrayBuffer jSArrayBuffer) {
        super(new JSFunction(jSArrayBuffer.getJSObject().getContext(), "_DataView", new String[]{"buffer"}, "return new DataView(buffer);", (String) null, 0).call((JSObject) null, jSArrayBuffer).toObject());
    }

    public JSDataView(JSArrayBuffer jSArrayBuffer, int i) {
        super(new JSFunction(jSArrayBuffer.getJSObject().getContext(), "_DataView1", new String[]{"buffer", "byteOffset"}, "return new DataView(buffer,byteOffset);", (String) null, 0).call((JSObject) null, jSArrayBuffer, Integer.valueOf(i)).toObject());
    }

    public JSDataView(JSArrayBuffer jSArrayBuffer, int i, int i2) {
        super(new JSFunction(jSArrayBuffer.getJSObject().getContext(), "_DataView2", new String[]{"buffer", "byteOffset", "byteLength"}, "return new DataView(buffer,byteOffset,byteLength);", (String) null, 0).call((JSObject) null, jSArrayBuffer, Integer.valueOf(i), Integer.valueOf(i2)).toObject());
    }

    public JSDataView(JSObject jSObject) {
        super(jSObject);
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

    public Float getFloat32(int i, boolean z) {
        return Float.valueOf(property("getFloat32").toFunction().call(this, Integer.valueOf(i), Boolean.valueOf(z)).toNumber().floatValue());
    }

    public Float getFloat32(int i) {
        return Float.valueOf(property("getFloat32").toFunction().call(this, Integer.valueOf(i)).toNumber().floatValue());
    }

    public void setFloat32(int i, Float f, boolean z) {
        property("setFloat32").toFunction().call(this, Integer.valueOf(i), f, Boolean.valueOf(z));
    }

    public void setFloat32(int i, Float f) {
        property("setFloat32").toFunction().call(this, Integer.valueOf(i), f);
    }

    public Double getFloat64(int i, boolean z) {
        return property("getFloat64").toFunction().call(this, Integer.valueOf(i), Boolean.valueOf(z)).toNumber();
    }

    public Double getFloat64(int i) {
        return property("getFloat64").toFunction().call(this, Integer.valueOf(i)).toNumber();
    }

    public void setFloat64(int i, Double d, boolean z) {
        property("setFloat64").toFunction().call(this, Integer.valueOf(i), d, Boolean.valueOf(z));
    }

    public void setFloat64(int i, Double d) {
        property("setFloat64").toFunction().call(this, Integer.valueOf(i), d);
    }

    public Integer getInt32(int i, boolean z) {
        return Integer.valueOf(property("getInt32").toFunction().call(this, Integer.valueOf(i), Boolean.valueOf(z)).toNumber().intValue());
    }

    public Integer getInt32(int i) {
        return Integer.valueOf(property("getInt32").toFunction().call(this, Integer.valueOf(i)).toNumber().intValue());
    }

    public void setInt32(int i, Integer num, boolean z) {
        property("setInt32").toFunction().call(this, Integer.valueOf(i), num, Boolean.valueOf(z));
    }

    public void setInt32(int i, Integer num) {
        property("setInt32").toFunction().call(this, Integer.valueOf(i), num);
    }

    public Long getUint32(int i, boolean z) {
        return Long.valueOf(property("getUint32").toFunction().call(this, Integer.valueOf(i), Boolean.valueOf(z)).toNumber().longValue());
    }

    public Long getUint32(int i) {
        return Long.valueOf(property("getUint32").toFunction().call(this, Integer.valueOf(i)).toNumber().longValue());
    }

    public void setUint32(int i, Long l, boolean z) {
        property("setUint32").toFunction().call(this, Integer.valueOf(i), l, Boolean.valueOf(z));
    }

    public void setUint32(int i, Long l) {
        property("setUint32").toFunction().call(this, Integer.valueOf(i), l);
    }

    public Short getInt16(int i, boolean z) {
        return Short.valueOf(property("getInt16").toFunction().call(this, Integer.valueOf(i), Boolean.valueOf(z)).toNumber().shortValue());
    }

    public Short getInt16(int i) {
        return Short.valueOf(property("getInt16").toFunction().call(this, Integer.valueOf(i)).toNumber().shortValue());
    }

    public void setInt16(int i, Short sh, boolean z) {
        property("setInt16").toFunction().call(this, Integer.valueOf(i), sh, Boolean.valueOf(z));
    }

    public void setInt16(int i, Short sh) {
        property("setInt16").toFunction().call(this, Integer.valueOf(i), sh);
    }

    public Short getUint16(int i, boolean z) {
        return Short.valueOf(property("getUint16").toFunction().call(this, Integer.valueOf(i), Boolean.valueOf(z)).toNumber().shortValue());
    }

    public Short getUint16(int i) {
        return Short.valueOf(property("getUint16").toFunction().call(this, Integer.valueOf(i)).toNumber().shortValue());
    }

    public void setUint16(int i, Short sh, boolean z) {
        property("setUint16").toFunction().call(this, Integer.valueOf(i), sh, Boolean.valueOf(z));
    }

    public void setUint16(int i, Short sh) {
        property("setUint16").toFunction().call(this, Integer.valueOf(i), sh);
    }

    public Byte getInt8(int i) {
        return Byte.valueOf(property("getInt8").toFunction().call(this, Integer.valueOf(i)).toNumber().byteValue());
    }

    public void setInt8(int i, Byte b) {
        property("setInt8").toFunction().call(this, Integer.valueOf(i), b);
    }

    public Byte getUint8(int i) {
        return Byte.valueOf(property("getUint8").toFunction().call(this, Integer.valueOf(i)).toNumber().byteValue());
    }

    public void setUint8(int i, Byte b) {
        property("setUint8").toFunction().call(this, Integer.valueOf(i), b);
    }
}
