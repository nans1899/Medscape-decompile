package org.mozilla.javascript;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

final class NativeNumber extends IdScriptableObject {
    private static final int Id_constructor = 1;
    private static final int Id_toExponential = 7;
    private static final int Id_toFixed = 6;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toPrecision = 8;
    private static final int Id_toSource = 4;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 5;
    private static final int MAX_PRECISION = 100;
    private static final int MAX_PROTOTYPE_ID = 8;
    private static final Object NUMBER_TAG = "Number";
    static final long serialVersionUID = 3504516769741512101L;
    private double doubleValue;

    public String getClassName() {
        return "Number";
    }

    static void init(Scriptable scriptable, boolean z) {
        new NativeNumber(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE).exportAsJSClass(8, scriptable, z);
    }

    NativeNumber(double d) {
        this.doubleValue = d;
    }

    /* access modifiers changed from: protected */
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        idFunctionObject.defineProperty("NaN", (Object) ScriptRuntime.NaNobj, 7);
        idFunctionObject.defineProperty("POSITIVE_INFINITY", (Object) ScriptRuntime.wrapNumber(Double.POSITIVE_INFINITY), 7);
        idFunctionObject.defineProperty("NEGATIVE_INFINITY", (Object) ScriptRuntime.wrapNumber(Double.NEGATIVE_INFINITY), 7);
        idFunctionObject.defineProperty("MAX_VALUE", (Object) ScriptRuntime.wrapNumber(Double.MAX_VALUE), 7);
        idFunctionObject.defineProperty("MIN_VALUE", (Object) ScriptRuntime.wrapNumber(Double.MIN_VALUE), 7);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        String str2;
        int i2 = 1;
        switch (i) {
            case 1:
                str = "constructor";
                break;
            case 2:
                str = "toString";
                break;
            case 3:
                str = "toLocaleString";
                break;
            case 4:
                str2 = "toSource";
                break;
            case 5:
                str2 = "valueOf";
                break;
            case 6:
                str = "toFixed";
                break;
            case 7:
                str = "toExponential";
                break;
            case 8:
                str = "toPrecision";
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(i));
        }
        str = str2;
        i2 = 0;
        initPrototypeMethod(NUMBER_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(NUMBER_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (methodId == 1) {
            if (objArr.length >= 1) {
                d = ScriptRuntime.toNumber(objArr[0]);
            }
            if (scriptable2 == null) {
                return new NativeNumber(d);
            }
            return ScriptRuntime.wrapNumber(d);
        } else if (scriptable2 instanceof NativeNumber) {
            double d2 = ((NativeNumber) scriptable2).doubleValue;
            int i = 10;
            switch (methodId) {
                case 2:
                case 3:
                    if (!(objArr.length == 0 || objArr[0] == Undefined.instance)) {
                        i = ScriptRuntime.toInt32(objArr[0]);
                    }
                    return ScriptRuntime.numberToString(d2, i);
                case 4:
                    return "(new Number(" + ScriptRuntime.toString(d2) + "))";
                case 5:
                    return ScriptRuntime.wrapNumber(d2);
                case 6:
                    return num_to(d2, objArr, 2, 2, -20, 0);
                case 7:
                    if (Double.isNaN(d2)) {
                        return "NaN";
                    }
                    if (Double.isInfinite(d2)) {
                        return d2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? "Infinity" : "-Infinity";
                    }
                    return num_to(d2, objArr, 1, 3, 0, 1);
                case 8:
                    if (objArr.length == 0 || objArr[0] == Undefined.instance) {
                        return ScriptRuntime.numberToString(d2, 10);
                    }
                    if (Double.isNaN(d2)) {
                        return "NaN";
                    }
                    if (Double.isInfinite(d2)) {
                        return d2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? "Infinity" : "-Infinity";
                    }
                    return num_to(d2, objArr, 0, 4, 1, 0);
                default:
                    throw new IllegalArgumentException(String.valueOf(methodId));
            }
        } else {
            throw incompatibleCallError(idFunctionObject);
        }
    }

    public String toString() {
        return ScriptRuntime.numberToString(this.doubleValue, 10);
    }

    private static String num_to(double d, Object[] objArr, int i, int i2, int i3, int i4) {
        int i5 = 0;
        if (objArr.length != 0) {
            int int32 = ScriptRuntime.toInt32(objArr[0]);
            if (int32 < i3 || int32 > 100) {
                throw ScriptRuntime.constructError("RangeError", ScriptRuntime.getMessage1("msg.bad.precision", ScriptRuntime.toString(objArr[0])));
            }
            i5 = int32;
            i = i2;
        }
        StringBuilder sb = new StringBuilder();
        DToA.JS_dtostr(sb, i, i5 + i4, d);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005e A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r8) {
        /*
            r7 = this;
            int r0 = r8.length()
            r1 = 3
            r2 = 8
            r3 = 7
            r4 = 116(0x74, float:1.63E-43)
            r5 = 0
            if (r0 == r3) goto L_0x0048
            if (r0 == r2) goto L_0x0036
            r6 = 11
            if (r0 == r6) goto L_0x0023
            r2 = 13
            if (r0 == r2) goto L_0x001f
            r2 = 14
            if (r0 == r2) goto L_0x001c
            goto L_0x005a
        L_0x001c:
            java.lang.String r0 = "toLocaleString"
            goto L_0x005c
        L_0x001f:
            java.lang.String r0 = "toExponential"
            r1 = 7
            goto L_0x005c
        L_0x0023:
            char r0 = r8.charAt(r5)
            r1 = 99
            if (r0 != r1) goto L_0x002f
            r1 = 1
            java.lang.String r0 = "constructor"
            goto L_0x005c
        L_0x002f:
            if (r0 != r4) goto L_0x005a
            java.lang.String r0 = "toPrecision"
            r1 = 8
            goto L_0x005c
        L_0x0036:
            char r0 = r8.charAt(r1)
            r1 = 111(0x6f, float:1.56E-43)
            if (r0 != r1) goto L_0x0042
            r1 = 4
            java.lang.String r0 = "toSource"
            goto L_0x005c
        L_0x0042:
            if (r0 != r4) goto L_0x005a
            r1 = 2
            java.lang.String r0 = "toString"
            goto L_0x005c
        L_0x0048:
            char r0 = r8.charAt(r5)
            if (r0 != r4) goto L_0x0052
            r1 = 6
            java.lang.String r0 = "toFixed"
            goto L_0x005c
        L_0x0052:
            r1 = 118(0x76, float:1.65E-43)
            if (r0 != r1) goto L_0x005a
            r1 = 5
            java.lang.String r0 = "valueOf"
            goto L_0x005c
        L_0x005a:
            r0 = 0
            r1 = 0
        L_0x005c:
            if (r0 == 0) goto L_0x0067
            if (r0 == r8) goto L_0x0067
            boolean r8 = r0.equals(r8)
            if (r8 != 0) goto L_0x0067
            goto L_0x0068
        L_0x0067:
            r5 = r1
        L_0x0068:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeNumber.findPrototypeId(java.lang.String):int");
    }
}
