package org.mozilla.javascript;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.common.net.HttpHeaders;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import okhttp3.internal.http2.Http2Connection;

final class NativeDate extends IdScriptableObject {
    private static final int ConstructorId_UTC = -1;
    private static final int ConstructorId_now = -3;
    private static final int ConstructorId_parse = -2;
    private static final Object DATE_TAG = HttpHeaders.DATE;
    private static final double HalfTimeDomain = 8.64E15d;
    private static final double HoursPerDay = 24.0d;
    private static final int Id_constructor = 1;
    private static final int Id_getDate = 17;
    private static final int Id_getDay = 19;
    private static final int Id_getFullYear = 13;
    private static final int Id_getHours = 21;
    private static final int Id_getMilliseconds = 27;
    private static final int Id_getMinutes = 23;
    private static final int Id_getMonth = 15;
    private static final int Id_getSeconds = 25;
    private static final int Id_getTime = 11;
    private static final int Id_getTimezoneOffset = 29;
    private static final int Id_getUTCDate = 18;
    private static final int Id_getUTCDay = 20;
    private static final int Id_getUTCFullYear = 14;
    private static final int Id_getUTCHours = 22;
    private static final int Id_getUTCMilliseconds = 28;
    private static final int Id_getUTCMinutes = 24;
    private static final int Id_getUTCMonth = 16;
    private static final int Id_getUTCSeconds = 26;
    private static final int Id_getYear = 12;
    private static final int Id_setDate = 39;
    private static final int Id_setFullYear = 43;
    private static final int Id_setHours = 37;
    private static final int Id_setMilliseconds = 31;
    private static final int Id_setMinutes = 35;
    private static final int Id_setMonth = 41;
    private static final int Id_setSeconds = 33;
    private static final int Id_setTime = 30;
    private static final int Id_setUTCDate = 40;
    private static final int Id_setUTCFullYear = 44;
    private static final int Id_setUTCHours = 38;
    private static final int Id_setUTCMilliseconds = 32;
    private static final int Id_setUTCMinutes = 36;
    private static final int Id_setUTCMonth = 42;
    private static final int Id_setUTCSeconds = 34;
    private static final int Id_setYear = 45;
    private static final int Id_toDateString = 4;
    private static final int Id_toGMTString = 8;
    private static final int Id_toISOString = 46;
    private static final int Id_toJSON = 47;
    private static final int Id_toLocaleDateString = 7;
    private static final int Id_toLocaleString = 5;
    private static final int Id_toLocaleTimeString = 6;
    private static final int Id_toSource = 9;
    private static final int Id_toString = 2;
    private static final int Id_toTimeString = 3;
    private static final int Id_toUTCString = 8;
    private static final int Id_valueOf = 10;
    private static double LocalTZA = 0.0d;
    private static final int MAXARGS = 7;
    private static final int MAX_PROTOTYPE_ID = 47;
    private static final double MinutesPerDay = 1440.0d;
    private static final double MinutesPerHour = 60.0d;
    private static final double SecondsPerDay = 86400.0d;
    private static final double SecondsPerHour = 3600.0d;
    private static final double SecondsPerMinute = 60.0d;
    private static final DateFormat isoFormat;
    private static final String js_NaN_date_str = "Invalid Date";
    private static DateFormat localeDateFormatter = null;
    private static DateFormat localeDateTimeFormatter = null;
    private static DateFormat localeTimeFormatter = null;
    private static final double msPerDay = 8.64E7d;
    private static final double msPerHour = 3600000.0d;
    private static final double msPerMinute = 60000.0d;
    private static final double msPerSecond = 1000.0d;
    static final long serialVersionUID = -8307438915861678966L;
    private static TimeZone thisTimeZone;
    private static DateFormat timeZoneFormatter;
    private double date;

    private static double MakeDate(double d, double d2) {
        return (d * msPerDay) + d2;
    }

    private static double MakeTime(double d, double d2, double d3, double d4) {
        return (((((d * 60.0d) + d2) * 60.0d) + d3) * msPerSecond) + d4;
    }

    private static double TimeWithinDay(double d) {
        double d2 = d % msPerDay;
        return d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? d2 + msPerDay : d2;
    }

    private static int msFromTime(double d) {
        double d2 = d % msPerSecond;
        if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d2 += msPerSecond;
        }
        return (int) d2;
    }

    public String getClassName() {
        return HttpHeaders.DATE;
    }

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        isoFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "UTC"));
        isoFormat.setLenient(false);
    }

    static void init(Scriptable scriptable, boolean z) {
        NativeDate nativeDate = new NativeDate();
        nativeDate.date = ScriptRuntime.NaN;
        nativeDate.exportAsJSClass(47, scriptable, z);
    }

    private NativeDate() {
        if (thisTimeZone == null) {
            TimeZone timeZone = TimeZone.getDefault();
            thisTimeZone = timeZone;
            LocalTZA = (double) timeZone.getRawOffset();
        }
    }

    public Object getDefaultValue(Class<?> cls) {
        if (cls == null) {
            cls = ScriptRuntime.StringClass;
        }
        return super.getDefaultValue(cls);
    }

    /* access modifiers changed from: package-private */
    public double getJSTimeValue() {
        return this.date;
    }

    /* access modifiers changed from: protected */
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        IdFunctionObject idFunctionObject2 = idFunctionObject;
        addIdFunctionProperty(idFunctionObject2, DATE_TAG, -3, "now", 0);
        addIdFunctionProperty(idFunctionObject, DATE_TAG, -2, "parse", 1);
        addIdFunctionProperty(idFunctionObject2, DATE_TAG, -1, "UTC", 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r0 = r1;
        r4 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        r4 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        r4 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b0, code lost:
        r4 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b1, code lost:
        initPrototypeMethod(DATE_TAG, r6, r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initPrototypeId(int r6) {
        /*
            r5 = this;
            r0 = 4
            r1 = 3
            r2 = 2
            r3 = 1
            r4 = 0
            switch(r6) {
                case 1: goto L_0x00ae;
                case 2: goto L_0x00ab;
                case 3: goto L_0x00a8;
                case 4: goto L_0x00a5;
                case 5: goto L_0x00a2;
                case 6: goto L_0x009f;
                case 7: goto L_0x009c;
                case 8: goto L_0x0099;
                case 9: goto L_0x0096;
                case 10: goto L_0x0093;
                case 11: goto L_0x0090;
                case 12: goto L_0x008d;
                case 13: goto L_0x008a;
                case 14: goto L_0x0087;
                case 15: goto L_0x0084;
                case 16: goto L_0x0081;
                case 17: goto L_0x007e;
                case 18: goto L_0x007b;
                case 19: goto L_0x0078;
                case 20: goto L_0x0075;
                case 21: goto L_0x0072;
                case 22: goto L_0x006f;
                case 23: goto L_0x006c;
                case 24: goto L_0x0069;
                case 25: goto L_0x0066;
                case 26: goto L_0x0063;
                case 27: goto L_0x005f;
                case 28: goto L_0x005b;
                case 29: goto L_0x0057;
                case 30: goto L_0x0053;
                case 31: goto L_0x004f;
                case 32: goto L_0x004b;
                case 33: goto L_0x0046;
                case 34: goto L_0x0043;
                case 35: goto L_0x003e;
                case 36: goto L_0x003b;
                case 37: goto L_0x0035;
                case 38: goto L_0x0032;
                case 39: goto L_0x002e;
                case 40: goto L_0x002a;
                case 41: goto L_0x0027;
                case 42: goto L_0x0024;
                case 43: goto L_0x0021;
                case 44: goto L_0x001e;
                case 45: goto L_0x001a;
                case 46: goto L_0x0016;
                case 47: goto L_0x0012;
                default: goto L_0x0008;
            }
        L_0x0008:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r0.<init>(r6)
            throw r0
        L_0x0012:
            java.lang.String r0 = "toJSON"
            goto L_0x00b0
        L_0x0016:
            java.lang.String r0 = "toISOString"
            goto L_0x00b1
        L_0x001a:
            java.lang.String r0 = "setYear"
            goto L_0x00b0
        L_0x001e:
            java.lang.String r0 = "setUTCFullYear"
            goto L_0x0040
        L_0x0021:
            java.lang.String r0 = "setFullYear"
            goto L_0x0040
        L_0x0024:
            java.lang.String r0 = "setUTCMonth"
            goto L_0x0048
        L_0x0027:
            java.lang.String r0 = "setMonth"
            goto L_0x0048
        L_0x002a:
            java.lang.String r0 = "setUTCDate"
            goto L_0x00b0
        L_0x002e:
            java.lang.String r0 = "setDate"
            goto L_0x00b0
        L_0x0032:
            java.lang.String r1 = "setUTCHours"
            goto L_0x0037
        L_0x0035:
            java.lang.String r1 = "setHours"
        L_0x0037:
            r0 = r1
            r4 = 4
            goto L_0x00b1
        L_0x003b:
            java.lang.String r0 = "setUTCMinutes"
            goto L_0x0040
        L_0x003e:
            java.lang.String r0 = "setMinutes"
        L_0x0040:
            r4 = 3
            goto L_0x00b1
        L_0x0043:
            java.lang.String r0 = "setUTCSeconds"
            goto L_0x0048
        L_0x0046:
            java.lang.String r0 = "setSeconds"
        L_0x0048:
            r4 = 2
            goto L_0x00b1
        L_0x004b:
            java.lang.String r0 = "setUTCMilliseconds"
            goto L_0x00b0
        L_0x004f:
            java.lang.String r0 = "setMilliseconds"
            goto L_0x00b0
        L_0x0053:
            java.lang.String r0 = "setTime"
            goto L_0x00b0
        L_0x0057:
            java.lang.String r0 = "getTimezoneOffset"
            goto L_0x00b1
        L_0x005b:
            java.lang.String r0 = "getUTCMilliseconds"
            goto L_0x00b1
        L_0x005f:
            java.lang.String r0 = "getMilliseconds"
            goto L_0x00b1
        L_0x0063:
            java.lang.String r0 = "getUTCSeconds"
            goto L_0x00b1
        L_0x0066:
            java.lang.String r0 = "getSeconds"
            goto L_0x00b1
        L_0x0069:
            java.lang.String r0 = "getUTCMinutes"
            goto L_0x00b1
        L_0x006c:
            java.lang.String r0 = "getMinutes"
            goto L_0x00b1
        L_0x006f:
            java.lang.String r0 = "getUTCHours"
            goto L_0x00b1
        L_0x0072:
            java.lang.String r0 = "getHours"
            goto L_0x00b1
        L_0x0075:
            java.lang.String r0 = "getUTCDay"
            goto L_0x00b1
        L_0x0078:
            java.lang.String r0 = "getDay"
            goto L_0x00b1
        L_0x007b:
            java.lang.String r0 = "getUTCDate"
            goto L_0x00b1
        L_0x007e:
            java.lang.String r0 = "getDate"
            goto L_0x00b1
        L_0x0081:
            java.lang.String r0 = "getUTCMonth"
            goto L_0x00b1
        L_0x0084:
            java.lang.String r0 = "getMonth"
            goto L_0x00b1
        L_0x0087:
            java.lang.String r0 = "getUTCFullYear"
            goto L_0x00b1
        L_0x008a:
            java.lang.String r0 = "getFullYear"
            goto L_0x00b1
        L_0x008d:
            java.lang.String r0 = "getYear"
            goto L_0x00b1
        L_0x0090:
            java.lang.String r0 = "getTime"
            goto L_0x00b1
        L_0x0093:
            java.lang.String r0 = "valueOf"
            goto L_0x00b1
        L_0x0096:
            java.lang.String r0 = "toSource"
            goto L_0x00b1
        L_0x0099:
            java.lang.String r0 = "toUTCString"
            goto L_0x00b1
        L_0x009c:
            java.lang.String r0 = "toLocaleDateString"
            goto L_0x00b1
        L_0x009f:
            java.lang.String r0 = "toLocaleTimeString"
            goto L_0x00b1
        L_0x00a2:
            java.lang.String r0 = "toLocaleString"
            goto L_0x00b1
        L_0x00a5:
            java.lang.String r0 = "toDateString"
            goto L_0x00b1
        L_0x00a8:
            java.lang.String r0 = "toTimeString"
            goto L_0x00b1
        L_0x00ab:
            java.lang.String r0 = "toString"
            goto L_0x00b1
        L_0x00ae:
            java.lang.String r0 = "constructor"
        L_0x00b0:
            r4 = 1
        L_0x00b1:
            java.lang.Object r1 = DATE_TAG
            r5.initPrototypeMethod(r1, r6, r0, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeDate.initPrototypeId(int):void");
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        double d;
        double d2;
        if (!idFunctionObject.hasTag(DATE_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == -3) {
            return ScriptRuntime.wrapNumber(now());
        }
        if (methodId == -2) {
            return ScriptRuntime.wrapNumber(date_parseString(ScriptRuntime.toString(objArr, 0)));
        }
        if (methodId == -1) {
            return ScriptRuntime.wrapNumber(jsStaticFunction_UTC(objArr));
        }
        if (methodId != 1) {
            if (methodId != 47) {
                if (scriptable2 instanceof NativeDate) {
                    NativeDate nativeDate = (NativeDate) scriptable2;
                    double d3 = nativeDate.date;
                    switch (methodId) {
                        case 2:
                        case 3:
                        case 4:
                            if (d3 == d3) {
                                return date_format(d3, methodId);
                            }
                            return js_NaN_date_str;
                        case 5:
                        case 6:
                        case 7:
                            if (d3 == d3) {
                                return toLocale_helper(d3, methodId);
                            }
                            return js_NaN_date_str;
                        case 8:
                            if (d3 == d3) {
                                return js_toUTCString(d3);
                            }
                            return js_NaN_date_str;
                        case 9:
                            return "(new Date(" + ScriptRuntime.toString(d3) + "))";
                        case 10:
                        case 11:
                            return ScriptRuntime.wrapNumber(d3);
                        case 12:
                        case 13:
                        case 14:
                            if (d3 == d3) {
                                if (methodId != 14) {
                                    d3 = LocalTime(d3);
                                }
                                d3 = (double) YearFromTime(d3);
                                if (methodId == 12 && (!context.hasFeature(1) || (1900.0d <= d3 && d3 < 2000.0d))) {
                                    d3 -= 1900.0d;
                                }
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 15:
                        case 16:
                            if (d3 == d3) {
                                if (methodId == 15) {
                                    d3 = LocalTime(d3);
                                }
                                d3 = (double) MonthFromTime(d3);
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 17:
                        case 18:
                            if (d3 == d3) {
                                if (methodId == 17) {
                                    d3 = LocalTime(d3);
                                }
                                d3 = (double) DateFromTime(d3);
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 19:
                        case 20:
                            if (d3 == d3) {
                                if (methodId == 19) {
                                    d3 = LocalTime(d3);
                                }
                                d3 = (double) WeekDay(d3);
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 21:
                        case 22:
                            if (d3 == d3) {
                                if (methodId == 21) {
                                    d3 = LocalTime(d3);
                                }
                                d3 = (double) HourFromTime(d3);
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 23:
                        case 24:
                            if (d3 == d3) {
                                if (methodId == 23) {
                                    d3 = LocalTime(d3);
                                }
                                d3 = (double) MinFromTime(d3);
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 25:
                        case 26:
                            if (d3 == d3) {
                                if (methodId == 25) {
                                    d3 = LocalTime(d3);
                                }
                                d3 = (double) SecFromTime(d3);
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 27:
                        case 28:
                            if (d3 == d3) {
                                if (methodId == 27) {
                                    d3 = LocalTime(d3);
                                }
                                d3 = (double) msFromTime(d3);
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 29:
                            if (d3 == d3) {
                                d3 = (d3 - LocalTime(d3)) / msPerMinute;
                            }
                            return ScriptRuntime.wrapNumber(d3);
                        case 30:
                            double TimeClip = TimeClip(ScriptRuntime.toNumber(objArr, 0));
                            nativeDate.date = TimeClip;
                            return ScriptRuntime.wrapNumber(TimeClip);
                        case 31:
                        case 32:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                            double makeTime = makeTime(d3, objArr, methodId);
                            nativeDate.date = makeTime;
                            return ScriptRuntime.wrapNumber(makeTime);
                        case 39:
                        case 40:
                        case 41:
                        case 42:
                        case 43:
                        case 44:
                            double makeDate = makeDate(d3, objArr, methodId);
                            nativeDate.date = makeDate;
                            return ScriptRuntime.wrapNumber(makeDate);
                        case 45:
                            double number = ScriptRuntime.toNumber(objArr, 0);
                            if (number != number || Double.isInfinite(number)) {
                                d = ScriptRuntime.NaN;
                            } else {
                                if (d3 != d3) {
                                    d2 = 0.0d;
                                } else {
                                    d2 = LocalTime(d3);
                                }
                                if (number >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && number <= 99.0d) {
                                    number += 1900.0d;
                                }
                                d = TimeClip(internalUTC(MakeDate(MakeDay(number, (double) MonthFromTime(d2), (double) DateFromTime(d2)), TimeWithinDay(d2))));
                            }
                            nativeDate.date = d;
                            return ScriptRuntime.wrapNumber(d);
                        case 46:
                            return nativeDate.toISOString();
                        default:
                            throw new IllegalArgumentException(String.valueOf(methodId));
                    }
                } else {
                    throw incompatibleCallError(idFunctionObject);
                }
            } else if (scriptable2 instanceof NativeDate) {
                return ((NativeDate) scriptable2).toISOString();
            } else {
                Scriptable object = ScriptRuntime.toObject(context, scriptable, (Object) scriptable2);
                Object primitive = ScriptRuntime.toPrimitive(object, ScriptRuntime.NumberClass);
                if (primitive instanceof Number) {
                    double doubleValue = ((Number) primitive).doubleValue();
                    if (doubleValue != doubleValue || Double.isInfinite(doubleValue)) {
                        return null;
                    }
                }
                Object obj = object.get("toISOString", object);
                if (obj == NOT_FOUND) {
                    throw ScriptRuntime.typeError2("msg.function.not.found.in", "toISOString", ScriptRuntime.toString((Object) object));
                } else if (obj instanceof Callable) {
                    Object call = ((Callable) obj).call(context, scriptable, object, ScriptRuntime.emptyArgs);
                    if (ScriptRuntime.isPrimitive(call)) {
                        return call;
                    }
                    throw ScriptRuntime.typeError1("msg.toisostring.must.return.primitive", ScriptRuntime.toString(call));
                } else {
                    throw ScriptRuntime.typeError3("msg.isnt.function.in", "toISOString", ScriptRuntime.toString((Object) object), ScriptRuntime.toString(obj));
                }
            }
        } else if (scriptable2 != null) {
            return date_format(now(), 2);
        } else {
            return jsConstructor(objArr);
        }
    }

    private String toISOString() {
        String format;
        double d = this.date;
        if (d == d) {
            synchronized (isoFormat) {
                format = isoFormat.format(new Date((long) this.date));
            }
            return format;
        }
        throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage0("msg.invalid.date"));
    }

    private static double Day(double d) {
        return Math.floor(d / msPerDay);
    }

    private static boolean IsLeapYear(int i) {
        return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    private static double DayFromYear(double d) {
        return ((((d - 1970.0d) * 365.0d) + Math.floor((d - 1969.0d) / 4.0d)) - Math.floor((d - 1901.0d) / 100.0d)) + Math.floor((d - 1601.0d) / 400.0d);
    }

    private static double TimeFromYear(double d) {
        return DayFromYear(d) * msPerDay;
    }

    private static int YearFromTime(double d) {
        double d2 = d / msPerDay;
        int floor = ((int) Math.floor(d2 / 366.0d)) + 1970;
        int floor2 = ((int) Math.floor(d2 / 365.0d)) + 1970;
        if (floor2 >= floor) {
            int i = floor;
            floor = floor2;
            floor2 = i;
        }
        while (floor > floor2) {
            int i2 = (floor + floor2) / 2;
            if (TimeFromYear((double) i2) > d) {
                floor = i2 - 1;
            } else {
                floor2 = i2 + 1;
                if (TimeFromYear((double) floor2) > d) {
                    return i2;
                }
            }
        }
        return floor2;
    }

    private static double DayFromMonth(int i, int i2) {
        int i3;
        int i4;
        int i5 = i * 30;
        if (i >= 7) {
            i4 = i / 2;
        } else if (i >= 2) {
            i4 = (i - 1) / 2;
        } else {
            i3 = i5 + i;
            if (i >= 2 && IsLeapYear(i2)) {
                i3++;
            }
            return (double) i3;
        }
        i3 = i5 + (i4 - 1);
        i3++;
        return (double) i3;
    }

    private static int MonthFromTime(double d) {
        int i;
        int YearFromTime = YearFromTime(d);
        int Day = ((int) (Day(d) - DayFromYear((double) YearFromTime))) - 59;
        if (Day < 0) {
            return Day < -28 ? 0 : 1;
        }
        if (IsLeapYear(YearFromTime)) {
            if (Day == 0) {
                return 1;
            }
            Day--;
        }
        int i2 = Day / 30;
        switch (i2) {
            case 0:
                return 2;
            case 1:
                i = 31;
                break;
            case 2:
                i = 61;
                break;
            case 3:
                i = 92;
                break;
            case 4:
                i = 122;
                break;
            case 5:
                i = 153;
                break;
            case 6:
                i = 184;
                break;
            case 7:
                i = 214;
                break;
            case 8:
                i = 245;
                break;
            case 9:
                i = 275;
                break;
            case 10:
                return 11;
            default:
                throw Kit.codeBug();
        }
        return Day >= i ? i2 + 2 : i2 + 1;
    }

    private static int DateFromTime(double d) {
        int YearFromTime = YearFromTime(d);
        int Day = ((int) (Day(d) - DayFromYear((double) YearFromTime))) - 59;
        int i = 31;
        if (Day < 0) {
            if (Day < -28) {
                Day += 31;
            }
            return Day + 28 + 1;
        }
        if (IsLeapYear(YearFromTime)) {
            if (Day == 0) {
                return 29;
            }
            Day--;
        }
        int i2 = 30;
        switch (Day / 30) {
            case 0:
                return Day + 1;
            case 1:
                break;
            case 2:
                i = 61;
                break;
            case 3:
                i = 92;
                break;
            case 4:
                i = 122;
                break;
            case 5:
                i = 153;
                break;
            case 6:
                i = 184;
                break;
            case 7:
                i = 214;
                break;
            case 8:
                i = 245;
                break;
            case 9:
                i = 275;
                break;
            case 10:
                return (Day - 275) + 1;
            default:
                throw Kit.codeBug();
        }
        i2 = 31;
        int i3 = Day - i;
        if (i3 < 0) {
            i3 += i2;
        }
        return i3 + 1;
    }

    private static int WeekDay(double d) {
        double Day = (Day(d) + 4.0d) % 7.0d;
        if (Day < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            Day += 7.0d;
        }
        return (int) Day;
    }

    private static double now() {
        return (double) System.currentTimeMillis();
    }

    private static double DaylightSavingTA(double d) {
        if (d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d = MakeDate(MakeDay((double) EquivalentYear(YearFromTime(d)), (double) MonthFromTime(d), (double) DateFromTime(d)), TimeWithinDay(d));
        }
        if (thisTimeZone.inDaylightTime(new Date((long) d))) {
            return msPerHour;
        }
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private static int EquivalentYear(int i) {
        int DayFromYear = (((int) DayFromYear((double) i)) + 4) % 7;
        if (DayFromYear < 0) {
            DayFromYear += 7;
        }
        if (IsLeapYear(i)) {
            switch (DayFromYear) {
                case 0:
                    return 1984;
                case 1:
                    return 1996;
                case 2:
                    return 1980;
                case 3:
                    return 1992;
                case 4:
                    return 1976;
                case 5:
                    return 1988;
                case 6:
                    return 1972;
            }
        } else {
            switch (DayFromYear) {
                case 0:
                    return 1978;
                case 1:
                    return 1973;
                case 2:
                    return 1985;
                case 3:
                    return 1986;
                case 4:
                    return 1981;
                case 5:
                    return 1971;
                case 6:
                    return 1977;
            }
        }
        throw Kit.codeBug();
    }

    private static double LocalTime(double d) {
        return LocalTZA + d + DaylightSavingTA(d);
    }

    private static double internalUTC(double d) {
        double d2 = LocalTZA;
        return (d - d2) - DaylightSavingTA(d - d2);
    }

    private static int HourFromTime(double d) {
        double floor = Math.floor(d / msPerHour) % HoursPerDay;
        if (floor < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            floor += HoursPerDay;
        }
        return (int) floor;
    }

    private static int MinFromTime(double d) {
        double floor = Math.floor(d / msPerMinute) % 60.0d;
        if (floor < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            floor += 60.0d;
        }
        return (int) floor;
    }

    private static int SecFromTime(double d) {
        double floor = Math.floor(d / msPerSecond) % 60.0d;
        if (floor < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            floor += 60.0d;
        }
        return (int) floor;
    }

    private static double MakeDay(double d, double d2, double d3) {
        double floor = d + Math.floor(d2 / 12.0d);
        double d4 = d2 % 12.0d;
        if (d4 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d4 += 12.0d;
        }
        return ((Math.floor(TimeFromYear(floor) / msPerDay) + DayFromMonth((int) d4, (int) floor)) + d3) - 1.0d;
    }

    private static double TimeClip(double d) {
        if (d != d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY || Math.abs(d) > HalfTimeDomain) {
            return ScriptRuntime.NaN;
        }
        if (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return Math.floor(d + FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }
        return Math.ceil(d + FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    private static double date_msecFromDate(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
        return MakeDate(MakeDay(d, d2, d3), MakeTime(d4, d5, d6, d7));
    }

    private static double date_msecFromArgs(Object[] objArr) {
        Object[] objArr2 = objArr;
        double[] dArr = new double[7];
        for (int i = 0; i < 7; i++) {
            if (i < objArr2.length) {
                double number = ScriptRuntime.toNumber(objArr2[i]);
                if (number != number || Double.isInfinite(number)) {
                    return ScriptRuntime.NaN;
                }
                dArr[i] = ScriptRuntime.toInteger(objArr2[i]);
            } else if (i == 2) {
                dArr[i] = 1.0d;
            } else {
                dArr[i] = 0.0d;
            }
        }
        if (dArr[0] >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && dArr[0] <= 99.0d) {
            dArr[0] = dArr[0] + 1900.0d;
        }
        return date_msecFromDate(dArr[0], dArr[1], dArr[2], dArr[3], dArr[4], dArr[5], dArr[6]);
    }

    private static double jsStaticFunction_UTC(Object[] objArr) {
        return TimeClip(date_msecFromArgs(objArr));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:174:0x01d5, code lost:
        r17 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x020a, code lost:
        if (r6 <= '9') goto L_0x020c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x0125 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0160 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double date_parseString(java.lang.String r34) {
        /*
            r7 = r34
            r8 = 24
            int r0 = r34.length()     // Catch:{ ParseException -> 0x001d }
            if (r0 != r8) goto L_0x001d
            java.text.DateFormat r1 = isoFormat     // Catch:{ ParseException -> 0x001d }
            monitor-enter(r1)     // Catch:{ ParseException -> 0x001d }
            java.text.DateFormat r0 = isoFormat     // Catch:{ all -> 0x001a }
            java.util.Date r0 = r0.parse(r7)     // Catch:{ all -> 0x001a }
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            long r0 = r0.getTime()     // Catch:{ ParseException -> 0x001d }
            double r0 = (double) r0
            return r0
        L_0x001a:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r0     // Catch:{ ParseException -> 0x001d }
        L_0x001d:
            int r0 = r34.length()
            r1 = -1
            r1 = 0
            r5 = 0
            r6 = -1
            r12 = -1
            r13 = -1
            r14 = -1
            r15 = -1
            r16 = -1
            r17 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r19 = 0
        L_0x002f:
            if (r1 >= r0) goto L_0x0216
            char r2 = r7.charAt(r1)
            int r1 = r1 + 1
            r3 = 57
            r4 = 32
            r11 = 48
            r9 = 45
            if (r2 <= r4) goto L_0x01f9
            r10 = 44
            if (r2 == r10) goto L_0x01f9
            if (r2 != r9) goto L_0x0049
            goto L_0x01f9
        L_0x0049:
            r8 = 40
            r21 = 1
            if (r2 != r8) goto L_0x0066
        L_0x004f:
            if (r1 >= r0) goto L_0x0212
            char r2 = r7.charAt(r1)
            int r1 = r1 + 1
            if (r2 != r8) goto L_0x005c
            int r21 = r21 + 1
            goto L_0x004f
        L_0x005c:
            r3 = 41
            if (r2 != r3) goto L_0x004f
            int r21 = r21 + -1
            if (r21 > 0) goto L_0x004f
            goto L_0x0212
        L_0x0066:
            r4 = 43
            r10 = 47
            r24 = 0
            if (r11 > r2) goto L_0x0130
            if (r2 > r3) goto L_0x0130
            int r26 = r2 + -48
            r8 = r26
        L_0x0074:
            if (r1 >= r0) goto L_0x0085
            char r2 = r7.charAt(r1)
            if (r11 > r2) goto L_0x0085
            if (r2 > r3) goto L_0x0085
            int r8 = r8 * 10
            int r8 = r8 + r2
            int r8 = r8 - r11
            int r1 = r1 + 1
            goto L_0x0074
        L_0x0085:
            r3 = 60
            if (r5 == r4) goto L_0x010a
            if (r5 != r9) goto L_0x008d
            goto L_0x010a
        L_0x008d:
            r4 = 70
            if (r8 >= r4) goto L_0x00ed
            if (r5 != r10) goto L_0x009b
            if (r13 < 0) goto L_0x009b
            if (r14 < 0) goto L_0x009b
            if (r12 >= 0) goto L_0x009b
            goto L_0x00ed
        L_0x009b:
            r4 = 58
            if (r2 != r4) goto L_0x00ac
            if (r6 >= 0) goto L_0x00a6
            r6 = r8
        L_0x00a2:
            r11 = 24
            goto L_0x012d
        L_0x00a6:
            if (r16 >= 0) goto L_0x00a9
            goto L_0x00dd
        L_0x00a9:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x00ac:
            if (r2 != r10) goto L_0x00ba
            if (r13 >= 0) goto L_0x00b4
            int r8 = r8 + -1
            r13 = r8
            goto L_0x00a2
        L_0x00b4:
            if (r14 >= 0) goto L_0x00b7
            goto L_0x00e8
        L_0x00b7:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x00ba:
            if (r1 >= r0) goto L_0x00c9
            r4 = 44
            if (r2 == r4) goto L_0x00c9
            r4 = 32
            if (r2 <= r4) goto L_0x00c9
            if (r2 == r9) goto L_0x00c9
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x00c9:
            if (r19 == 0) goto L_0x00d9
            if (r8 >= r3) goto L_0x00d9
            int r2 = (r17 > r24 ? 1 : (r17 == r24 ? 0 : -1))
            if (r2 >= 0) goto L_0x00d5
            double r2 = (double) r8
            double r17 = r17 - r2
            goto L_0x00a2
        L_0x00d5:
            double r2 = (double) r8
            double r17 = r17 + r2
            goto L_0x00a2
        L_0x00d9:
            if (r6 < 0) goto L_0x00e0
            if (r16 >= 0) goto L_0x00e0
        L_0x00dd:
            r16 = r8
            goto L_0x00a2
        L_0x00e0:
            if (r16 < 0) goto L_0x00e6
            if (r15 >= 0) goto L_0x00e6
            r15 = r8
            goto L_0x00a2
        L_0x00e6:
            if (r14 >= 0) goto L_0x00ea
        L_0x00e8:
            r14 = r8
            goto L_0x00a2
        L_0x00ea:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x00ed:
            if (r12 < 0) goto L_0x00f2
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x00f2:
            r3 = 32
            if (r2 <= r3) goto L_0x0102
            r3 = 44
            if (r2 == r3) goto L_0x0102
            if (r2 == r10) goto L_0x0102
            if (r1 < r0) goto L_0x00ff
            goto L_0x0102
        L_0x00ff:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x0102:
            r2 = 100
            if (r8 >= r2) goto L_0x0108
            int r8 = r8 + 1900
        L_0x0108:
            r12 = r8
            goto L_0x00a2
        L_0x010a:
            r11 = 24
            if (r8 >= r11) goto L_0x0111
            int r8 = r8 * 60
            goto L_0x0118
        L_0x0111:
            int r2 = r8 % 100
            int r8 = r8 / 100
            int r8 = r8 * 60
            int r8 = r8 + r2
        L_0x0118:
            if (r5 != r4) goto L_0x011b
            int r8 = -r8
        L_0x011b:
            int r2 = (r17 > r24 ? 1 : (r17 == r24 ? 0 : -1))
            if (r2 == 0) goto L_0x0128
            r2 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r4 = (r17 > r2 ? 1 : (r17 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0128
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x0128:
            double r2 = (double) r8
            r17 = r2
            r19 = 1
        L_0x012d:
            r5 = 0
            goto L_0x0212
        L_0x0130:
            r11 = 24
            if (r2 == r10) goto L_0x01f5
            r3 = 58
            if (r2 == r3) goto L_0x01f5
            if (r2 == r4) goto L_0x01f5
            if (r2 != r9) goto L_0x013e
            goto L_0x01f5
        L_0x013e:
            int r8 = r1 + -1
            r9 = r1
        L_0x0141:
            if (r9 >= r0) goto L_0x015b
            char r1 = r7.charAt(r9)
            r2 = 65
            if (r2 > r1) goto L_0x014f
            r2 = 90
            if (r1 <= r2) goto L_0x0158
        L_0x014f:
            r2 = 97
            if (r2 > r1) goto L_0x015b
            r2 = 122(0x7a, float:1.71E-43)
            if (r1 <= r2) goto L_0x0158
            goto L_0x015b
        L_0x0158:
            int r9 = r9 + 1
            goto L_0x0141
        L_0x015b:
            int r10 = r9 - r8
            r4 = 2
            if (r10 >= r4) goto L_0x0163
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x0163:
            java.lang.String r3 = "am;pm;monday;tuesday;wednesday;thursday;friday;saturday;sunday;january;february;march;april;may;june;july;august;september;october;november;december;gmt;ut;utc;est;edt;cst;cdt;mst;mdt;pst;pdt;"
            r1 = 0
            r2 = 0
        L_0x0167:
            r4 = 59
            int r21 = r3.indexOf(r4, r2)
            if (r21 >= 0) goto L_0x0172
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x0172:
            r4 = 1
            r11 = r1
            r1 = r3
            r23 = r2
            r2 = r4
            r26 = r3
            r3 = r23
            r4 = r34
            r20 = r5
            r5 = r8
            r23 = r9
            r9 = r6
            r6 = r10
            boolean r1 = r1.regionMatches(r2, r3, r4, r5, r6)
            if (r1 == 0) goto L_0x01e5
            r1 = 4646096334330265600(0x407a400000000000, double:420.0)
            r3 = 4645040803167600640(0x4076800000000000, double:360.0)
            r5 = 4643985272004935680(0x4072c00000000000, double:300.0)
            r8 = 12
            r10 = 2
            if (r11 >= r10) goto L_0x01b2
            if (r9 > r8) goto L_0x01af
            if (r9 >= 0) goto L_0x01a4
            goto L_0x01af
        L_0x01a4:
            if (r11 != 0) goto L_0x01aa
            if (r9 != r8) goto L_0x01df
            r6 = 0
            goto L_0x01e0
        L_0x01aa:
            if (r9 == r8) goto L_0x01df
            int r6 = r9 + 12
            goto L_0x01e0
        L_0x01af:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x01b2:
            int r10 = r11 + -2
            r11 = 7
            if (r10 >= r11) goto L_0x01b8
            goto L_0x01df
        L_0x01b8:
            int r10 = r10 + -7
            if (r10 >= r8) goto L_0x01c4
            if (r13 >= 0) goto L_0x01c1
            r6 = r9
            r13 = r10
            goto L_0x01e0
        L_0x01c1:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x01c4:
            int r10 = r10 + -12
            switch(r10) {
                case 0: goto L_0x01db;
                case 1: goto L_0x01db;
                case 2: goto L_0x01db;
                case 3: goto L_0x01d8;
                case 4: goto L_0x01d3;
                case 5: goto L_0x01d0;
                case 6: goto L_0x01d8;
                case 7: goto L_0x01d5;
                case 8: goto L_0x01d0;
                case 9: goto L_0x01cd;
                case 10: goto L_0x01d5;
                default: goto L_0x01c9;
            }
        L_0x01c9:
            org.mozilla.javascript.Kit.codeBug()
            goto L_0x01df
        L_0x01cd:
            r1 = 4647151865492930560(0x407e000000000000, double:480.0)
            goto L_0x01d5
        L_0x01d0:
            r17 = r3
            goto L_0x01df
        L_0x01d3:
            r1 = 4642648265865560064(0x406e000000000000, double:240.0)
        L_0x01d5:
            r17 = r1
            goto L_0x01df
        L_0x01d8:
            r17 = r5
            goto L_0x01df
        L_0x01db:
            r6 = r9
            r17 = r24
            goto L_0x01e0
        L_0x01df:
            r6 = r9
        L_0x01e0:
            r5 = r20
            r1 = r23
            goto L_0x0212
        L_0x01e5:
            r3 = 2
            int r2 = r21 + 1
            int r1 = r11 + 1
            r6 = r9
            r5 = r20
            r9 = r23
            r3 = r26
            r11 = 24
            goto L_0x0167
        L_0x01f5:
            r9 = r6
            r4 = 24
            goto L_0x020c
        L_0x01f9:
            r20 = r5
            r9 = r6
            r4 = 24
            r5 = 45
            if (r1 >= r0) goto L_0x020f
            char r6 = r7.charAt(r1)
            if (r2 != r5) goto L_0x020f
            if (r11 > r6) goto L_0x020f
            if (r6 > r3) goto L_0x020f
        L_0x020c:
            r5 = r2
            r6 = r9
            goto L_0x0212
        L_0x020f:
            r6 = r9
            r5 = r20
        L_0x0212:
            r8 = 24
            goto L_0x002f
        L_0x0216:
            r9 = r6
            if (r12 < 0) goto L_0x0259
            if (r13 < 0) goto L_0x0259
            if (r14 >= 0) goto L_0x021e
            goto L_0x0259
        L_0x021e:
            if (r15 >= 0) goto L_0x0221
            r15 = 0
        L_0x0221:
            if (r16 >= 0) goto L_0x0225
            r0 = 0
            goto L_0x0227
        L_0x0225:
            r0 = r16
        L_0x0227:
            if (r9 >= 0) goto L_0x022b
            r11 = 0
            goto L_0x022c
        L_0x022b:
            r11 = r9
        L_0x022c:
            double r1 = (double) r12
            double r3 = (double) r13
            double r5 = (double) r14
            double r7 = (double) r11
            double r9 = (double) r0
            double r11 = (double) r15
            r32 = 0
            r20 = r1
            r22 = r3
            r24 = r5
            r26 = r7
            r28 = r9
            r30 = r11
            double r0 = date_msecFromDate(r20, r22, r24, r26, r28, r30, r32)
            r2 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r4 = (r17 > r2 ? 1 : (r17 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x024f
            double r0 = internalUTC(r0)
            return r0
        L_0x024f:
            r2 = 4678479150791524352(0x40ed4c0000000000, double:60000.0)
            double r17 = r17 * r2
            double r0 = r0 + r17
            return r0
        L_0x0259:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeDate.date_parseString(java.lang.String):double");
    }

    private static String date_format(double d, int i) {
        StringBuffer stringBuffer = new StringBuffer(60);
        double LocalTime = LocalTime(d);
        if (i != 3) {
            appendWeekDayName(stringBuffer, WeekDay(LocalTime));
            stringBuffer.append(' ');
            appendMonthName(stringBuffer, MonthFromTime(LocalTime));
            stringBuffer.append(' ');
            append0PaddedUint(stringBuffer, DateFromTime(LocalTime), 2);
            stringBuffer.append(' ');
            int YearFromTime = YearFromTime(LocalTime);
            if (YearFromTime < 0) {
                stringBuffer.append('-');
                YearFromTime = -YearFromTime;
            }
            append0PaddedUint(stringBuffer, YearFromTime, 4);
            if (i != 4) {
                stringBuffer.append(' ');
            }
        }
        if (i != 4) {
            append0PaddedUint(stringBuffer, HourFromTime(LocalTime), 2);
            stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
            append0PaddedUint(stringBuffer, MinFromTime(LocalTime), 2);
            stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
            append0PaddedUint(stringBuffer, SecFromTime(LocalTime), 2);
            int floor = (int) Math.floor((LocalTZA + DaylightSavingTA(d)) / msPerMinute);
            int i2 = ((floor / 60) * 100) + (floor % 60);
            if (i2 > 0) {
                stringBuffer.append(" GMT+");
            } else {
                stringBuffer.append(" GMT-");
                i2 = -i2;
            }
            append0PaddedUint(stringBuffer, i2, 4);
            if (timeZoneFormatter == null) {
                timeZoneFormatter = new SimpleDateFormat("zzz");
            }
            if (d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d = MakeDate(MakeDay((double) EquivalentYear(YearFromTime(LocalTime)), (double) MonthFromTime(d), (double) DateFromTime(d)), TimeWithinDay(d));
            }
            stringBuffer.append(" (");
            Date date2 = new Date((long) d);
            synchronized (timeZoneFormatter) {
                stringBuffer.append(timeZoneFormatter.format(date2));
            }
            stringBuffer.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        }
        return stringBuffer.toString();
    }

    private static Object jsConstructor(Object[] objArr) {
        double d;
        NativeDate nativeDate = new NativeDate();
        if (objArr.length == 0) {
            nativeDate.date = now();
            return nativeDate;
        } else if (objArr.length == 1) {
            Object obj = objArr[0];
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue((Class<?>) null);
            }
            if (obj instanceof CharSequence) {
                d = date_parseString(obj.toString());
            } else {
                d = ScriptRuntime.toNumber(obj);
            }
            nativeDate.date = TimeClip(d);
            return nativeDate;
        } else {
            double date_msecFromArgs = date_msecFromArgs(objArr);
            if (!Double.isNaN(date_msecFromArgs) && !Double.isInfinite(date_msecFromArgs)) {
                date_msecFromArgs = TimeClip(internalUTC(date_msecFromArgs));
            }
            nativeDate.date = date_msecFromArgs;
            return nativeDate;
        }
    }

    private static String toLocale_helper(double d, int i) {
        DateFormat dateFormat;
        String format;
        if (i == 5) {
            if (localeDateTimeFormatter == null) {
                localeDateTimeFormatter = DateFormat.getDateTimeInstance(1, 1);
            }
            dateFormat = localeDateTimeFormatter;
        } else if (i == 6) {
            if (localeTimeFormatter == null) {
                localeTimeFormatter = DateFormat.getTimeInstance(1);
            }
            dateFormat = localeTimeFormatter;
        } else if (i == 7) {
            if (localeDateFormatter == null) {
                localeDateFormatter = DateFormat.getDateInstance(1);
            }
            dateFormat = localeDateFormatter;
        } else {
            throw new AssertionError();
        }
        synchronized (dateFormat) {
            format = dateFormat.format(new Date((long) d));
        }
        return format;
    }

    private static String js_toUTCString(double d) {
        StringBuffer stringBuffer = new StringBuffer(60);
        appendWeekDayName(stringBuffer, WeekDay(d));
        stringBuffer.append(", ");
        append0PaddedUint(stringBuffer, DateFromTime(d), 2);
        stringBuffer.append(' ');
        appendMonthName(stringBuffer, MonthFromTime(d));
        stringBuffer.append(' ');
        int YearFromTime = YearFromTime(d);
        if (YearFromTime < 0) {
            stringBuffer.append('-');
            YearFromTime = -YearFromTime;
        }
        append0PaddedUint(stringBuffer, YearFromTime, 4);
        stringBuffer.append(' ');
        append0PaddedUint(stringBuffer, HourFromTime(d), 2);
        stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        append0PaddedUint(stringBuffer, MinFromTime(d), 2);
        stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        append0PaddedUint(stringBuffer, SecFromTime(d), 2);
        stringBuffer.append(" GMT");
        return stringBuffer.toString();
    }

    private static void append0PaddedUint(StringBuffer stringBuffer, int i, int i2) {
        if (i < 0) {
            Kit.codeBug();
        }
        int i3 = i2 - 1;
        int i4 = Http2Connection.DEGRADED_PONG_TIMEOUT_NS;
        if (i < 10) {
            i4 = 1;
        } else if (i < 1000000000) {
            i4 = 1;
            while (true) {
                int i5 = i4 * 10;
                if (i < i5) {
                    break;
                }
                i3--;
                i4 = i5;
            }
        } else {
            i3 -= 9;
        }
        while (i3 > 0) {
            stringBuffer.append('0');
            i3--;
        }
        while (i4 != 1) {
            stringBuffer.append((char) ((i / i4) + 48));
            i %= i4;
            i4 /= 10;
        }
        stringBuffer.append((char) (i + 48));
    }

    private static void appendMonthName(StringBuffer stringBuffer, int i) {
        int i2 = i * 3;
        for (int i3 = 0; i3 != 3; i3++) {
            stringBuffer.append("JanFebMarAprMayJunJulAugSepOctNovDec".charAt(i2 + i3));
        }
    }

    private static void appendWeekDayName(StringBuffer stringBuffer, int i) {
        int i2 = i * 3;
        for (int i3 = 0; i3 != 3; i3++) {
            stringBuffer.append("SunMonTueWedThuFriSat".charAt(i2 + i3));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001d, code lost:
        r7 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        r8 = new double[4];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0027, code lost:
        if (r21 == r21) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        return r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        if (r0.length != 0) goto L_0x0031;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002d, code lost:
        r0 = org.mozilla.javascript.ScriptRuntime.padArguments(r0, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0031, code lost:
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0033, code lost:
        if (r9 >= r0.length) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0035, code lost:
        if (r9 >= r7) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0037, code lost:
        r8[r9] = org.mozilla.javascript.ScriptRuntime.toNumber(r0[r9]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0045, code lost:
        if (r8[r9] != r8[r9]) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x004d, code lost:
        if (java.lang.Double.isInfinite(r8[r9]) == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0050, code lost:
        r8[r9] = org.mozilla.javascript.ScriptRuntime.toInteger(r8[r9]);
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005d, code lost:
        return org.mozilla.javascript.ScriptRuntime.NaN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005e, code lost:
        if (r6 == false) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0060, code lost:
        r9 = LocalTime(r21);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0065, code lost:
        r9 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        r0 = r0.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0068, code lost:
        if (r7 < 4) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006a, code lost:
        if (r0 <= 0) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006c, code lost:
        r13 = r8[0];
        r4 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0071, code lost:
        r13 = (double) HourFromTime(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0077, code lost:
        if (r7 < 3) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0079, code lost:
        if (r4 >= r0) goto L_0x0082;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007b, code lost:
        r15 = r8[r4];
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0082, code lost:
        r15 = (double) MinFromTime(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0088, code lost:
        if (r7 < 2) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008a, code lost:
        if (r4 >= r0) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008c, code lost:
        r2 = r8[r4];
        r4 = r4 + 1;
        r17 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0094, code lost:
        r17 = (double) SecFromTime(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009b, code lost:
        if (r7 < 1) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x009d, code lost:
        if (r4 >= r0) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009f, code lost:
        r0 = r8[r4];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a2, code lost:
        r0 = (double) msFromTime(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a7, code lost:
        r0 = MakeDate(Day(r9), MakeTime(r13, r15, r17, r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b5, code lost:
        if (r6 == false) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b7, code lost:
        r0 = internalUTC(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00bf, code lost:
        return TimeClip(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r7 = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        r7 = 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double makeTime(double r21, java.lang.Object[] r23, int r24) {
        /*
            r0 = r23
            r1 = 3
            r2 = 2
            r3 = 4
            r4 = 0
            r5 = 1
            switch(r24) {
                case 31: goto L_0x0021;
                case 32: goto L_0x001f;
                case 33: goto L_0x001c;
                case 34: goto L_0x001a;
                case 35: goto L_0x0017;
                case 36: goto L_0x0015;
                case 37: goto L_0x0012;
                case 38: goto L_0x0010;
                default: goto L_0x000a;
            }
        L_0x000a:
            org.mozilla.javascript.Kit.codeBug()
            r6 = 1
            r7 = 0
            goto L_0x0023
        L_0x0010:
            r6 = 0
            goto L_0x0013
        L_0x0012:
            r6 = 1
        L_0x0013:
            r7 = 4
            goto L_0x0023
        L_0x0015:
            r6 = 0
            goto L_0x0018
        L_0x0017:
            r6 = 1
        L_0x0018:
            r7 = 3
            goto L_0x0023
        L_0x001a:
            r6 = 0
            goto L_0x001d
        L_0x001c:
            r6 = 1
        L_0x001d:
            r7 = 2
            goto L_0x0023
        L_0x001f:
            r6 = 0
            goto L_0x0022
        L_0x0021:
            r6 = 1
        L_0x0022:
            r7 = 1
        L_0x0023:
            double[] r8 = new double[r3]
            int r9 = (r21 > r21 ? 1 : (r21 == r21 ? 0 : -1))
            if (r9 == 0) goto L_0x002a
            return r21
        L_0x002a:
            int r9 = r0.length
            if (r9 != 0) goto L_0x0031
            java.lang.Object[] r0 = org.mozilla.javascript.ScriptRuntime.padArguments(r0, r5)
        L_0x0031:
            r9 = 0
        L_0x0032:
            int r10 = r0.length
            if (r9 >= r10) goto L_0x005e
            if (r9 >= r7) goto L_0x005e
            r10 = r0[r9]
            double r10 = org.mozilla.javascript.ScriptRuntime.toNumber((java.lang.Object) r10)
            r8[r9] = r10
            r10 = r8[r9]
            r12 = r8[r9]
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 != 0) goto L_0x005b
            r10 = r8[r9]
            boolean r10 = java.lang.Double.isInfinite(r10)
            if (r10 == 0) goto L_0x0050
            goto L_0x005b
        L_0x0050:
            r10 = r8[r9]
            double r10 = org.mozilla.javascript.ScriptRuntime.toInteger((double) r10)
            r8[r9] = r10
            int r9 = r9 + 1
            goto L_0x0032
        L_0x005b:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x005e:
            if (r6 == 0) goto L_0x0065
            double r9 = LocalTime(r21)
            goto L_0x0067
        L_0x0065:
            r9 = r21
        L_0x0067:
            int r0 = r0.length
            if (r7 < r3) goto L_0x0071
            if (r0 <= 0) goto L_0x0071
            r3 = r8[r4]
            r13 = r3
            r4 = 1
            goto L_0x0077
        L_0x0071:
            int r3 = HourFromTime(r9)
            double r11 = (double) r3
            r13 = r11
        L_0x0077:
            if (r7 < r1) goto L_0x0082
            if (r4 >= r0) goto L_0x0082
            int r1 = r4 + 1
            r3 = r8[r4]
            r15 = r3
            r4 = r1
            goto L_0x0088
        L_0x0082:
            int r1 = MinFromTime(r9)
            double r11 = (double) r1
            r15 = r11
        L_0x0088:
            if (r7 < r2) goto L_0x0094
            if (r4 >= r0) goto L_0x0094
            int r1 = r4 + 1
            r2 = r8[r4]
            r4 = r1
            r17 = r2
            goto L_0x009b
        L_0x0094:
            int r1 = SecFromTime(r9)
            double r1 = (double) r1
            r17 = r1
        L_0x009b:
            if (r7 < r5) goto L_0x00a2
            if (r4 >= r0) goto L_0x00a2
            r0 = r8[r4]
            goto L_0x00a7
        L_0x00a2:
            int r0 = msFromTime(r9)
            double r0 = (double) r0
        L_0x00a7:
            r19 = r0
            double r0 = MakeTime(r13, r15, r17, r19)
            double r2 = Day(r9)
            double r0 = MakeDate(r2, r0)
            if (r6 == 0) goto L_0x00bb
            double r0 = internalUTC(r0)
        L_0x00bb:
            double r0 = TimeClip(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeDate.makeTime(double, java.lang.Object[], int):double");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        r6 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        r7 = new double[3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        if (r0.length != 0) goto L_0x0026;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0022, code lost:
        r0 = org.mozilla.javascript.ScriptRuntime.padArguments(r0, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0028, code lost:
        if (r8 >= r0.length) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002a, code lost:
        if (r8 >= r6) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002c, code lost:
        r7[r8] = org.mozilla.javascript.ScriptRuntime.toNumber(r0[r8]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        if (r7[r8] != r7[r8]) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0042, code lost:
        if (java.lang.Double.isInfinite(r7[r8]) == false) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0045, code lost:
        r7[r8] = org.mozilla.javascript.ScriptRuntime.toInteger(r7[r8]);
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0052, code lost:
        return org.mozilla.javascript.ScriptRuntime.NaN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        if (r18 == r18) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        if (r0.length >= 3) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005c, code lost:
        return org.mozilla.javascript.ScriptRuntime.NaN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005d, code lost:
        r8 = com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0060, code lost:
        if (r5 == false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0062, code lost:
        r8 = LocalTime(r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0067, code lost:
        r8 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0069, code lost:
        r0 = r0.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006a, code lost:
        if (r6 < 3) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006c, code lost:
        if (r0 <= 0) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x006e, code lost:
        r12 = r7[0];
        r3 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0073, code lost:
        r12 = (double) YearFromTime(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0079, code lost:
        if (r6 < 2) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x007b, code lost:
        if (r3 >= r0) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007d, code lost:
        r14 = r7[r3];
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0084, code lost:
        r14 = (double) MonthFromTime(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x008a, code lost:
        if (r6 < 1) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x008c, code lost:
        if (r3 >= r0) goto L_0x0091;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x008e, code lost:
        r0 = r7[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0091, code lost:
        r0 = (double) DateFromTime(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0096, code lost:
        r0 = MakeDate(MakeDay(r12, r14, r0), TimeWithinDay(r8));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a4, code lost:
        if (r5 == false) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a6, code lost:
        r0 = internalUTC(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ae, code lost:
        return TimeClip(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        r6 = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r6 = 2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double makeDate(double r18, java.lang.Object[] r20, int r21) {
        /*
            r0 = r20
            r1 = 2
            r2 = 3
            r3 = 0
            r4 = 1
            switch(r21) {
                case 39: goto L_0x001b;
                case 40: goto L_0x0019;
                case 41: goto L_0x0016;
                case 42: goto L_0x0014;
                case 43: goto L_0x0011;
                case 44: goto L_0x000f;
                default: goto L_0x0009;
            }
        L_0x0009:
            org.mozilla.javascript.Kit.codeBug()
            r5 = 1
            r6 = 0
            goto L_0x001d
        L_0x000f:
            r5 = 0
            goto L_0x0012
        L_0x0011:
            r5 = 1
        L_0x0012:
            r6 = 3
            goto L_0x001d
        L_0x0014:
            r5 = 0
            goto L_0x0017
        L_0x0016:
            r5 = 1
        L_0x0017:
            r6 = 2
            goto L_0x001d
        L_0x0019:
            r5 = 0
            goto L_0x001c
        L_0x001b:
            r5 = 1
        L_0x001c:
            r6 = 1
        L_0x001d:
            double[] r7 = new double[r2]
            int r8 = r0.length
            if (r8 != 0) goto L_0x0026
            java.lang.Object[] r0 = org.mozilla.javascript.ScriptRuntime.padArguments(r0, r4)
        L_0x0026:
            r8 = 0
        L_0x0027:
            int r9 = r0.length
            if (r8 >= r9) goto L_0x0053
            if (r8 >= r6) goto L_0x0053
            r9 = r0[r8]
            double r9 = org.mozilla.javascript.ScriptRuntime.toNumber((java.lang.Object) r9)
            r7[r8] = r9
            r9 = r7[r8]
            r11 = r7[r8]
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 != 0) goto L_0x0050
            r9 = r7[r8]
            boolean r9 = java.lang.Double.isInfinite(r9)
            if (r9 == 0) goto L_0x0045
            goto L_0x0050
        L_0x0045:
            r9 = r7[r8]
            double r9 = org.mozilla.javascript.ScriptRuntime.toInteger((double) r9)
            r7[r8] = r9
            int r8 = r8 + 1
            goto L_0x0027
        L_0x0050:
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x0053:
            int r8 = (r18 > r18 ? 1 : (r18 == r18 ? 0 : -1))
            if (r8 == 0) goto L_0x0060
            int r8 = r0.length
            if (r8 >= r2) goto L_0x005d
            double r0 = org.mozilla.javascript.ScriptRuntime.NaN
            return r0
        L_0x005d:
            r8 = 0
            goto L_0x0069
        L_0x0060:
            if (r5 == 0) goto L_0x0067
            double r8 = LocalTime(r18)
            goto L_0x0069
        L_0x0067:
            r8 = r18
        L_0x0069:
            int r0 = r0.length
            if (r6 < r2) goto L_0x0073
            if (r0 <= 0) goto L_0x0073
            r2 = r7[r3]
            r12 = r2
            r3 = 1
            goto L_0x0079
        L_0x0073:
            int r2 = YearFromTime(r8)
            double r10 = (double) r2
            r12 = r10
        L_0x0079:
            if (r6 < r1) goto L_0x0084
            if (r3 >= r0) goto L_0x0084
            int r1 = r3 + 1
            r2 = r7[r3]
            r14 = r2
            r3 = r1
            goto L_0x008a
        L_0x0084:
            int r1 = MonthFromTime(r8)
            double r1 = (double) r1
            r14 = r1
        L_0x008a:
            if (r6 < r4) goto L_0x0091
            if (r3 >= r0) goto L_0x0091
            r0 = r7[r3]
            goto L_0x0096
        L_0x0091:
            int r0 = DateFromTime(r8)
            double r0 = (double) r0
        L_0x0096:
            r16 = r0
            double r0 = MakeDay(r12, r14, r16)
            double r2 = TimeWithinDay(r8)
            double r0 = MakeDate(r0, r2)
            if (r5 == 0) goto L_0x00aa
            double r0 = internalUTC(r0)
        L_0x00aa:
            double r0 = TimeClip(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeDate.makeDate(double, java.lang.Object[], int):double");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r15) {
        /*
            r14 = this;
            int r0 = r15.length()
            r1 = 2
            r2 = 6
            r3 = 8
            r4 = 83
            r5 = 9
            r6 = 84
            r7 = 68
            r8 = 77
            r9 = 3
            r10 = 116(0x74, float:1.63E-43)
            r11 = 115(0x73, float:1.61E-43)
            r12 = 103(0x67, float:1.44E-43)
            r13 = 0
            switch(r0) {
                case 6: goto L_0x0207;
                case 7: goto L_0x01bb;
                case 8: goto L_0x0177;
                case 9: goto L_0x0171;
                case 10: goto L_0x0129;
                case 11: goto L_0x00be;
                case 12: goto L_0x00ac;
                case 13: goto L_0x007c;
                case 14: goto L_0x0061;
                case 15: goto L_0x004d;
                case 16: goto L_0x001d;
                case 17: goto L_0x0047;
                case 18: goto L_0x001f;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x0219
        L_0x001f:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x002b
            r1 = 28
            java.lang.String r0 = "getUTCMilliseconds"
            goto L_0x021b
        L_0x002b:
            if (r0 != r11) goto L_0x0033
            r1 = 32
            java.lang.String r0 = "setUTCMilliseconds"
            goto L_0x021b
        L_0x0033:
            if (r0 != r10) goto L_0x0219
            char r0 = r15.charAt(r3)
            if (r0 != r7) goto L_0x0040
            r1 = 7
            java.lang.String r0 = "toLocaleDateString"
            goto L_0x021b
        L_0x0040:
            if (r0 != r6) goto L_0x0219
            java.lang.String r0 = "toLocaleTimeString"
            r1 = 6
            goto L_0x021b
        L_0x0047:
            r1 = 29
            java.lang.String r0 = "getTimezoneOffset"
            goto L_0x021b
        L_0x004d:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x0059
            r1 = 27
            java.lang.String r0 = "getMilliseconds"
            goto L_0x021b
        L_0x0059:
            if (r0 != r11) goto L_0x0219
            r1 = 31
            java.lang.String r0 = "setMilliseconds"
            goto L_0x021b
        L_0x0061:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x006d
            r1 = 14
            java.lang.String r0 = "getUTCFullYear"
            goto L_0x021b
        L_0x006d:
            if (r0 != r11) goto L_0x0075
            r1 = 44
            java.lang.String r0 = "setUTCFullYear"
            goto L_0x021b
        L_0x0075:
            if (r0 != r10) goto L_0x0219
            r1 = 5
            java.lang.String r0 = "toLocaleString"
            goto L_0x021b
        L_0x007c:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x0096
            char r0 = r15.charAt(r2)
            if (r0 != r8) goto L_0x008e
            r1 = 24
            java.lang.String r0 = "getUTCMinutes"
            goto L_0x021b
        L_0x008e:
            if (r0 != r4) goto L_0x0219
            r1 = 26
            java.lang.String r0 = "getUTCSeconds"
            goto L_0x021b
        L_0x0096:
            if (r0 != r11) goto L_0x0219
            char r0 = r15.charAt(r2)
            if (r0 != r8) goto L_0x00a4
            r1 = 36
            java.lang.String r0 = "setUTCMinutes"
            goto L_0x021b
        L_0x00a4:
            if (r0 != r4) goto L_0x0219
            r1 = 34
            java.lang.String r0 = "setUTCSeconds"
            goto L_0x021b
        L_0x00ac:
            char r0 = r15.charAt(r1)
            if (r0 != r7) goto L_0x00b7
            r1 = 4
            java.lang.String r0 = "toDateString"
            goto L_0x021b
        L_0x00b7:
            if (r0 != r6) goto L_0x0219
            java.lang.String r0 = "toTimeString"
            r1 = 3
            goto L_0x021b
        L_0x00be:
            char r0 = r15.charAt(r9)
            r1 = 70
            if (r0 == r1) goto L_0x0115
            if (r0 == r8) goto L_0x010f
            if (r0 == r11) goto L_0x010a
            switch(r0) {
                case 83: goto L_0x0104;
                case 84: goto L_0x0101;
                case 85: goto L_0x00cf;
                default: goto L_0x00cd;
            }
        L_0x00cd:
            goto L_0x0219
        L_0x00cf:
            char r0 = r15.charAt(r13)
            r1 = 114(0x72, float:1.6E-43)
            if (r0 != r12) goto L_0x00eb
            char r0 = r15.charAt(r5)
            if (r0 != r1) goto L_0x00e3
            r1 = 22
            java.lang.String r0 = "getUTCHours"
            goto L_0x021b
        L_0x00e3:
            if (r0 != r10) goto L_0x0219
            r1 = 16
            java.lang.String r0 = "getUTCMonth"
            goto L_0x021b
        L_0x00eb:
            if (r0 != r11) goto L_0x0219
            char r0 = r15.charAt(r5)
            if (r0 != r1) goto L_0x00f9
            r1 = 38
            java.lang.String r0 = "setUTCHours"
            goto L_0x021b
        L_0x00f9:
            if (r0 != r10) goto L_0x0219
            r1 = 42
            java.lang.String r0 = "setUTCMonth"
            goto L_0x021b
        L_0x0101:
            java.lang.String r0 = "toUTCString"
            goto L_0x0111
        L_0x0104:
            r1 = 46
            java.lang.String r0 = "toISOString"
            goto L_0x021b
        L_0x010a:
            r1 = 1
            java.lang.String r0 = "constructor"
            goto L_0x021b
        L_0x010f:
            java.lang.String r0 = "toGMTString"
        L_0x0111:
            r1 = 8
            goto L_0x021b
        L_0x0115:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x0121
            r1 = 13
            java.lang.String r0 = "getFullYear"
            goto L_0x021b
        L_0x0121:
            if (r0 != r11) goto L_0x0219
            r1 = 43
            java.lang.String r0 = "setFullYear"
            goto L_0x021b
        L_0x0129:
            char r0 = r15.charAt(r9)
            if (r0 != r8) goto L_0x0143
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x013b
            r1 = 23
            java.lang.String r0 = "getMinutes"
            goto L_0x021b
        L_0x013b:
            if (r0 != r11) goto L_0x0219
            r1 = 35
            java.lang.String r0 = "setMinutes"
            goto L_0x021b
        L_0x0143:
            if (r0 != r4) goto L_0x0159
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x0151
            r1 = 25
            java.lang.String r0 = "getSeconds"
            goto L_0x021b
        L_0x0151:
            if (r0 != r11) goto L_0x0219
            r1 = 33
            java.lang.String r0 = "setSeconds"
            goto L_0x021b
        L_0x0159:
            r1 = 85
            if (r0 != r1) goto L_0x0219
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x0169
            r1 = 18
            java.lang.String r0 = "getUTCDate"
            goto L_0x021b
        L_0x0169:
            if (r0 != r11) goto L_0x0219
            r1 = 40
            java.lang.String r0 = "setUTCDate"
            goto L_0x021b
        L_0x0171:
            r1 = 20
            java.lang.String r0 = "getUTCDay"
            goto L_0x021b
        L_0x0177:
            char r0 = r15.charAt(r9)
            r2 = 72
            if (r0 == r2) goto L_0x01a7
            if (r0 == r8) goto L_0x0193
            r2 = 111(0x6f, float:1.56E-43)
            if (r0 == r2) goto L_0x018d
            if (r0 == r10) goto L_0x0189
            goto L_0x0219
        L_0x0189:
            java.lang.String r0 = "toString"
            goto L_0x021b
        L_0x018d:
            java.lang.String r0 = "toSource"
            r1 = 9
            goto L_0x021b
        L_0x0193:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x019f
            r1 = 15
            java.lang.String r0 = "getMonth"
            goto L_0x021b
        L_0x019f:
            if (r0 != r11) goto L_0x0219
            r1 = 41
            java.lang.String r0 = "setMonth"
            goto L_0x021b
        L_0x01a7:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x01b3
            r1 = 21
            java.lang.String r0 = "getHours"
            goto L_0x021b
        L_0x01b3:
            if (r0 != r11) goto L_0x0219
            r1 = 37
            java.lang.String r0 = "setHours"
            goto L_0x021b
        L_0x01bb:
            char r0 = r15.charAt(r9)
            if (r0 == r7) goto L_0x01f5
            if (r0 == r6) goto L_0x01e3
            r1 = 89
            if (r0 == r1) goto L_0x01d1
            r1 = 117(0x75, float:1.64E-43)
            if (r0 == r1) goto L_0x01cc
            goto L_0x0219
        L_0x01cc:
            r1 = 10
            java.lang.String r0 = "valueOf"
            goto L_0x021b
        L_0x01d1:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x01dc
            r1 = 12
            java.lang.String r0 = "getYear"
            goto L_0x021b
        L_0x01dc:
            if (r0 != r11) goto L_0x0219
            r1 = 45
            java.lang.String r0 = "setYear"
            goto L_0x021b
        L_0x01e3:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x01ee
            r1 = 11
            java.lang.String r0 = "getTime"
            goto L_0x021b
        L_0x01ee:
            if (r0 != r11) goto L_0x0219
            r1 = 30
            java.lang.String r0 = "setTime"
            goto L_0x021b
        L_0x01f5:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x0200
            r1 = 17
            java.lang.String r0 = "getDate"
            goto L_0x021b
        L_0x0200:
            if (r0 != r11) goto L_0x0219
            r1 = 39
            java.lang.String r0 = "setDate"
            goto L_0x021b
        L_0x0207:
            char r0 = r15.charAt(r13)
            if (r0 != r12) goto L_0x0212
            r1 = 19
            java.lang.String r0 = "getDay"
            goto L_0x021b
        L_0x0212:
            if (r0 != r10) goto L_0x0219
            r1 = 47
            java.lang.String r0 = "toJSON"
            goto L_0x021b
        L_0x0219:
            r0 = 0
            r1 = 0
        L_0x021b:
            if (r0 == 0) goto L_0x0226
            if (r0 == r15) goto L_0x0226
            boolean r15 = r0.equals(r15)
            if (r15 != 0) goto L_0x0226
            goto L_0x0227
        L_0x0226:
            r13 = r1
        L_0x0227:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeDate.findPrototypeId(java.lang.String):int");
    }
}
