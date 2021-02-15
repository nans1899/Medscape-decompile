package org.liquidplayer.javascript;

import com.google.common.net.HttpHeaders;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class JSDate extends JSObject {
    public JSDate(JSContext jSContext) {
        this.context = jSContext;
        this.valueRef = this.context.ctxRef().makeDate(new long[0]);
        addJSExports();
        this.context.persistObject(this);
    }

    public JSDate(JSContext jSContext, Date date) {
        this.context = jSContext;
        this.valueRef = this.context.ctxRef().makeDate(new long[]{date.getTime()});
        addJSExports();
        this.context.persistObject(this);
    }

    public JSDate(JSContext jSContext, Long l) {
        this.context = jSContext;
        this.valueRef = this.context.ctxRef().makeDate(new long[]{l.longValue()});
        addJSExports();
        this.context.persistObject(this);
    }

    public JSDate(JSContext jSContext, Integer... numArr) {
        this.context = jSContext;
        Calendar instance = Calendar.getInstance();
        int[] iArr = {1, 2, 5, 11, 12, 13, 14};
        for (int i = 0; i < 7; i++) {
            if (i < numArr.length) {
                instance.set(iArr[i], numArr[i].intValue());
            } else {
                instance.set(iArr[i], 0);
            }
        }
        this.valueRef = this.context.ctxRef().makeDate(new long[]{instance.getTime().getTime()});
        this.context.persistObject(this);
    }

    public static Long now(JSContext jSContext) {
        return Long.valueOf(jSContext.property(HttpHeaders.DATE).toObject().property("now").toFunction().call().toNumber().longValue());
    }

    public static Long parse(JSContext jSContext, String str) {
        return Long.valueOf(jSContext.property(HttpHeaders.DATE).toObject().property("parse").toFunction().call((JSObject) null, str).toNumber().longValue());
    }

    public static Long UTC(JSContext jSContext, Integer... numArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 7; i++) {
            if (i < numArr.length) {
                arrayList.add(numArr[i]);
            } else {
                arrayList.add(0);
            }
        }
        return Long.valueOf(jSContext.property(HttpHeaders.DATE).toObject().property("UTC").toFunction().apply((JSObject) null, arrayList.toArray()).toNumber().longValue());
    }

    public Integer getDate() {
        return Integer.valueOf(property("getDate").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getDay() {
        return Integer.valueOf(property("getDay").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getFullYear() {
        return Integer.valueOf(property("getFullYear").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getHours() {
        return Integer.valueOf(property("getHours").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getMilliseconds() {
        return Integer.valueOf(property("getMilliseconds").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getMinutes() {
        return Integer.valueOf(property("getMinutes").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getMonth() {
        return Integer.valueOf(property("getMonth").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getSeconds() {
        return Integer.valueOf(property("getSeconds").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Long getTime() {
        return Long.valueOf(property("getTime").toFunction().call(this, new Object[0]).toNumber().longValue());
    }

    public Integer getTimezoneOffset() {
        return Integer.valueOf(property("getTimezoneOffset").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getUTCDate() {
        return Integer.valueOf(property("getUTCDate").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getUTCDay() {
        return Integer.valueOf(property("getUTCDay").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getUTCFullYear() {
        return Integer.valueOf(property("getUTCFullYear").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getUTCHours() {
        return Integer.valueOf(property("getUTCHours").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getUTCMilliseconds() {
        return Integer.valueOf(property("getUTCMilliseconds").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getUTCMinutes() {
        return Integer.valueOf(property("getUTCMinutes").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getUTCMonth() {
        return Integer.valueOf(property("getUTCMonth").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public Integer getUTCSeconds() {
        return Integer.valueOf(property("getUTCSeconds").toFunction().call(this, new Object[0]).toNumber().intValue());
    }

    public void setDate(Integer num) {
        property("setDate").toFunction().call(this, num);
    }

    public void setFullYear(Integer num) {
        property("setFullYear").toFunction().call(this, num);
    }

    public void setHours(Integer num) {
        property("setHours").toFunction().call(this, num);
    }

    public void setMilliseconds(Integer num) {
        property("setMilliseconds").toFunction().call(this, num);
    }

    public void setMinutes(Integer num) {
        property("setMinutes").toFunction().call(this, num);
    }

    public void setMonth(Integer num) {
        property("setMonth").toFunction().call(this, num);
    }

    public void setSeconds(Integer num) {
        property("setSeconds").toFunction().call(this, num);
    }

    public void setTime(Long l) {
        property("setTime").toFunction().call(this, l);
    }

    public void setUTCDate(Integer num) {
        property("setUTCDate").toFunction().call(this, num);
    }

    public void setUTCFullYear(Integer num) {
        property("setUTCFullYear").toFunction().call(this, num);
    }

    public void setUTCHours(Integer num) {
        property("setUTCHours").toFunction().call(this, num);
    }

    public void setUTCMilliseconds(Integer num) {
        property("setUTCMilliseconds").toFunction().call(this, num);
    }

    public void setUTCMinutes(Integer num) {
        property("setUTCMinutes").toFunction().call(this, num);
    }

    public void setUTCMonth(Integer num) {
        property("setUTCMonth").toFunction().call(this, num);
    }

    public void setUTCSeconds(Integer num) {
        property("setUTCSeconds").toFunction().call(this, num);
    }

    public String toDateString() {
        return property("toDateString").toFunction().call(this, new Object[0]).toString();
    }

    public String toISOString() {
        return property("toISOString").toFunction().call(this, new Object[0]).toString();
    }

    public String toJSON() {
        return property("toJSON").toFunction().call(this, new Object[0]).toString();
    }

    public String toTimeString() {
        return property("toTimeString").toFunction().call(this, new Object[0]).toString();
    }

    public String toUTCString() {
        return property("toUTCString").toFunction().call(this, new Object[0]).toString();
    }
}
