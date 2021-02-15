package org.mozilla.javascript;

final class NativeMath extends IdScriptableObject {
    private static final int Id_E = 20;
    private static final int Id_LN10 = 22;
    private static final int Id_LN2 = 23;
    private static final int Id_LOG10E = 25;
    private static final int Id_LOG2E = 24;
    private static final int Id_PI = 21;
    private static final int Id_SQRT1_2 = 26;
    private static final int Id_SQRT2 = 27;
    private static final int Id_abs = 2;
    private static final int Id_acos = 3;
    private static final int Id_asin = 4;
    private static final int Id_atan = 5;
    private static final int Id_atan2 = 6;
    private static final int Id_ceil = 7;
    private static final int Id_cos = 8;
    private static final int Id_exp = 9;
    private static final int Id_floor = 10;
    private static final int Id_log = 11;
    private static final int Id_max = 12;
    private static final int Id_min = 13;
    private static final int Id_pow = 14;
    private static final int Id_random = 15;
    private static final int Id_round = 16;
    private static final int Id_sin = 17;
    private static final int Id_sqrt = 18;
    private static final int Id_tan = 19;
    private static final int Id_toSource = 1;
    private static final int LAST_METHOD_ID = 19;
    private static final Object MATH_TAG = "Math";
    private static final int MAX_ID = 27;
    static final long serialVersionUID = -8838847185801131569L;

    public String getClassName() {
        return "Math";
    }

    static void init(Scriptable scriptable, boolean z) {
        NativeMath nativeMath = new NativeMath();
        nativeMath.activatePrototypeMap(27);
        nativeMath.setPrototype(getObjectPrototype(scriptable));
        nativeMath.setParentScope(scriptable);
        if (z) {
            nativeMath.sealObject();
        }
        ScriptableObject.defineProperty(scriptable, "Math", nativeMath, 2);
    }

    private NativeMath() {
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        r2 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004d, code lost:
        r0 = r1;
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004f, code lost:
        initPrototypeMethod(MATH_TAG, r4, r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initPrototypeId(int r4) {
        /*
            r3 = this;
            r0 = 19
            if (r4 > r0) goto L_0x0055
            r0 = 0
            r1 = 2
            r2 = 1
            switch(r4) {
                case 1: goto L_0x004b;
                case 2: goto L_0x0048;
                case 3: goto L_0x0045;
                case 4: goto L_0x0042;
                case 5: goto L_0x003f;
                case 6: goto L_0x003b;
                case 7: goto L_0x0038;
                case 8: goto L_0x0035;
                case 9: goto L_0x0032;
                case 10: goto L_0x002f;
                case 11: goto L_0x002c;
                case 12: goto L_0x0029;
                case 13: goto L_0x0026;
                case 14: goto L_0x0023;
                case 15: goto L_0x0020;
                case 16: goto L_0x001d;
                case 17: goto L_0x001a;
                case 18: goto L_0x0017;
                case 19: goto L_0x0014;
                default: goto L_0x000a;
            }
        L_0x000a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0.<init>(r4)
            throw r0
        L_0x0014:
            java.lang.String r0 = "tan"
            goto L_0x004f
        L_0x0017:
            java.lang.String r0 = "sqrt"
            goto L_0x004f
        L_0x001a:
            java.lang.String r0 = "sin"
            goto L_0x004f
        L_0x001d:
            java.lang.String r0 = "round"
            goto L_0x004f
        L_0x0020:
            java.lang.String r1 = "random"
            goto L_0x004d
        L_0x0023:
            java.lang.String r0 = "pow"
            goto L_0x003d
        L_0x0026:
            java.lang.String r0 = "min"
            goto L_0x003d
        L_0x0029:
            java.lang.String r0 = "max"
            goto L_0x003d
        L_0x002c:
            java.lang.String r0 = "log"
            goto L_0x004f
        L_0x002f:
            java.lang.String r0 = "floor"
            goto L_0x004f
        L_0x0032:
            java.lang.String r0 = "exp"
            goto L_0x004f
        L_0x0035:
            java.lang.String r0 = "cos"
            goto L_0x004f
        L_0x0038:
            java.lang.String r0 = "ceil"
            goto L_0x004f
        L_0x003b:
            java.lang.String r0 = "atan2"
        L_0x003d:
            r2 = 2
            goto L_0x004f
        L_0x003f:
            java.lang.String r0 = "atan"
            goto L_0x004f
        L_0x0042:
            java.lang.String r0 = "asin"
            goto L_0x004f
        L_0x0045:
            java.lang.String r0 = "acos"
            goto L_0x004f
        L_0x0048:
            java.lang.String r0 = "abs"
            goto L_0x004f
        L_0x004b:
            java.lang.String r1 = "toSource"
        L_0x004d:
            r0 = r1
            r2 = 0
        L_0x004f:
            java.lang.Object r1 = MATH_TAG
            r3.initPrototypeMethod(r1, r4, r0, r2)
            goto L_0x00a9
        L_0x0055:
            switch(r4) {
                case 20: goto L_0x009a;
                case 21: goto L_0x0092;
                case 22: goto L_0x008a;
                case 23: goto L_0x0082;
                case 24: goto L_0x007a;
                case 25: goto L_0x0072;
                case 26: goto L_0x006a;
                case 27: goto L_0x0062;
                default: goto L_0x0058;
            }
        L_0x0058:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0.<init>(r4)
            throw r0
        L_0x0062:
            r0 = 4609047870845172685(0x3ff6a09e667f3bcd, double:1.4142135623730951)
            java.lang.String r2 = "SQRT2"
            goto L_0x00a1
        L_0x006a:
            r0 = 4604544271217802189(0x3fe6a09e667f3bcd, double:0.7071067811865476)
            java.lang.String r2 = "SQRT1_2"
            goto L_0x00a1
        L_0x0072:
            r0 = 4601495173785380110(0x3fdbcb7b1526e50e, double:0.4342944819032518)
            java.lang.String r2 = "LOG10E"
            goto L_0x00a1
        L_0x007a:
            r0 = 4609176140021203710(0x3ff71547652b82fe, double:1.4426950408889634)
            java.lang.String r2 = "LOG2E"
            goto L_0x00a1
        L_0x0082:
            r0 = 4604418534313441775(0x3fe62e42fefa39ef, double:0.6931471805599453)
            java.lang.String r2 = "LN2"
            goto L_0x00a1
        L_0x008a:
            r0 = 4612367379483415830(0x40026bb1bbb55516, double:2.302585092994046)
            java.lang.String r2 = "LN10"
            goto L_0x00a1
        L_0x0092:
            r0 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            java.lang.String r2 = "PI"
            goto L_0x00a1
        L_0x009a:
            r0 = 4613303445314885481(0x4005bf0a8b145769, double:2.718281828459045)
            java.lang.String r2 = "E"
        L_0x00a1:
            java.lang.Number r0 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r0)
            r1 = 7
            r3.initPrototypeValue(r4, r2, r0, r1)
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeMath.initPrototypeId(int):void");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0076, code lost:
        if (r10 != com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0078, code lost:
        r10 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x014e, code lost:
        return org.mozilla.javascript.ScriptRuntime.wrapNumber(r10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object execIdCall(org.mozilla.javascript.IdFunctionObject r8, org.mozilla.javascript.Context r9, org.mozilla.javascript.Scriptable r10, org.mozilla.javascript.Scriptable r11, java.lang.Object[] r12) {
        /*
            r7 = this;
            java.lang.Object r0 = MATH_TAG
            boolean r0 = r8.hasTag(r0)
            if (r0 != 0) goto L_0x000d
            java.lang.Object r8 = super.execIdCall(r8, r9, r10, r11, r12)
            return r8
        L_0x000d:
            int r8 = r8.methodId()
            r9 = 1
            r10 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r0 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            r2 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r4 = 0
            r6 = 0
            switch(r8) {
                case 1: goto L_0x014f;
                case 2: goto L_0x0139;
                case 3: goto L_0x0116;
                case 4: goto L_0x0116;
                case 5: goto L_0x010d;
                case 6: goto L_0x0100;
                case 7: goto L_0x00f7;
                case 8: goto L_0x00e4;
                case 9: goto L_0x00cf;
                case 10: goto L_0x00c5;
                case 11: goto L_0x00b6;
                case 12: goto L_0x008f;
                case 13: goto L_0x008f;
                case 14: goto L_0x0081;
                case 15: goto L_0x007b;
                case 16: goto L_0x0051;
                case 17: goto L_0x003c;
                case 18: goto L_0x0032;
                case 19: goto L_0x0028;
                default: goto L_0x001e;
            }
        L_0x001e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r8 = java.lang.String.valueOf(r8)
            r9.<init>(r8)
            throw r9
        L_0x0028:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            double r10 = java.lang.Math.tan(r8)
            goto L_0x014a
        L_0x0032:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            double r10 = java.lang.Math.sqrt(r8)
            goto L_0x014a
        L_0x003c:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            int r12 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r12 == 0) goto L_0x014a
            int r12 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r12 != 0) goto L_0x004a
            goto L_0x014a
        L_0x004a:
            double r8 = java.lang.Math.sin(r8)
        L_0x004e:
            r10 = r8
            goto L_0x014a
        L_0x0051:
            double r10 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            int r8 = (r10 > r10 ? 1 : (r10 == r10 ? 0 : -1))
            if (r8 != 0) goto L_0x014a
            int r8 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r8 == 0) goto L_0x014a
            int r8 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r8 == 0) goto L_0x014a
            long r8 = java.lang.Math.round(r10)
            r0 = 0
            int r12 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r12 == 0) goto L_0x006d
            double r8 = (double) r8
            goto L_0x004e
        L_0x006d:
            int r8 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r8 >= 0) goto L_0x0074
            double r8 = org.mozilla.javascript.ScriptRuntime.negativeZero
            goto L_0x004e
        L_0x0074:
            int r8 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x014a
        L_0x0078:
            r10 = r4
            goto L_0x014a
        L_0x007b:
            double r10 = java.lang.Math.random()
            goto L_0x014a
        L_0x0081:
            double r10 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r9)
            double r10 = r7.js_pow(r10, r8)
            goto L_0x014a
        L_0x008f:
            r9 = 12
            if (r8 != r9) goto L_0x0094
            goto L_0x0095
        L_0x0094:
            r0 = r2
        L_0x0095:
            int r10 = r12.length
            if (r6 == r10) goto L_0x00b3
            r10 = r12[r6]
            double r10 = org.mozilla.javascript.ScriptRuntime.toNumber((java.lang.Object) r10)
            int r2 = (r10 > r10 ? 1 : (r10 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x00a4
            goto L_0x014a
        L_0x00a4:
            if (r8 != r9) goto L_0x00ab
            double r10 = java.lang.Math.max(r0, r10)
            goto L_0x00af
        L_0x00ab:
            double r10 = java.lang.Math.min(r0, r10)
        L_0x00af:
            r0 = r10
            int r6 = r6 + 1
            goto L_0x0095
        L_0x00b3:
            r10 = r0
            goto L_0x014a
        L_0x00b6:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            int r12 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r12 >= 0) goto L_0x00c0
            goto L_0x014a
        L_0x00c0:
            double r8 = java.lang.Math.log(r8)
            goto L_0x004e
        L_0x00c5:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            double r10 = java.lang.Math.floor(r8)
            goto L_0x014a
        L_0x00cf:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            int r10 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r10 != 0) goto L_0x00d9
            goto L_0x004e
        L_0x00d9:
            int r10 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r10 != 0) goto L_0x00de
            goto L_0x0078
        L_0x00de:
            double r8 = java.lang.Math.exp(r8)
            goto L_0x004e
        L_0x00e4:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            int r12 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r12 == 0) goto L_0x014a
            int r12 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r12 != 0) goto L_0x00f1
            goto L_0x014a
        L_0x00f1:
            double r8 = java.lang.Math.cos(r8)
            goto L_0x004e
        L_0x00f7:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            double r10 = java.lang.Math.ceil(r8)
            goto L_0x014a
        L_0x0100:
            double r10 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r9)
            double r10 = java.lang.Math.atan2(r10, r8)
            goto L_0x014a
        L_0x010d:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            double r10 = java.lang.Math.atan(r8)
            goto L_0x014a
        L_0x0116:
            double r0 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            int r9 = (r0 > r0 ? 1 : (r0 == r0 ? 0 : -1))
            if (r9 != 0) goto L_0x014a
            r2 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r9 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r9 > 0) goto L_0x014a
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r9 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r9 > 0) goto L_0x014a
            r9 = 3
            if (r8 != r9) goto L_0x0133
            double r8 = java.lang.Math.acos(r0)
            goto L_0x004e
        L_0x0133:
            double r8 = java.lang.Math.asin(r0)
            goto L_0x004e
        L_0x0139:
            double r8 = org.mozilla.javascript.ScriptRuntime.toNumber(r12, r6)
            int r10 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x0143
            goto L_0x0078
        L_0x0143:
            int r10 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r10 >= 0) goto L_0x004e
            double r8 = -r8
            goto L_0x004e
        L_0x014a:
            java.lang.Number r8 = org.mozilla.javascript.ScriptRuntime.wrapNumber(r10)
            return r8
        L_0x014f:
            java.lang.String r8 = "Math"
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeMath.execIdCall(org.mozilla.javascript.IdFunctionObject, org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0062, code lost:
        if (r23 < 1.0d) goto L_0x007e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007b, code lost:
        if (r23 < 1.0d) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0085, code lost:
        if (r8 > 0) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x009f, code lost:
        if (r8 > 0) goto L_0x0065;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double js_pow(double r23, double r25) {
        /*
            r22 = this;
            r0 = r25
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r4 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r6 = 0
            int r8 = (r0 > r0 ? 1 : (r0 == r0 ? 0 : -1))
            if (r8 == 0) goto L_0x0010
            r17 = r0
            goto L_0x00a4
        L_0x0010:
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 != 0) goto L_0x0018
            r17 = r2
            goto L_0x00a4
        L_0x0018:
            r9 = -9223372036854775808
            r11 = 0
            r13 = 1
            r15 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            int r17 = (r23 > r6 ? 1 : (r23 == r6 ? 0 : -1))
            if (r17 != 0) goto L_0x0045
            double r2 = r2 / r23
            int r17 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r17 <= 0) goto L_0x002e
            if (r8 <= 0) goto L_0x0065
            r4 = r6
            goto L_0x0065
        L_0x002e:
            long r2 = (long) r0
            double r6 = (double) r2
            int r19 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r19 != 0) goto L_0x0040
            long r0 = r2 & r13
            int r2 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r2 == 0) goto L_0x0040
            if (r8 <= 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r9 = r15
        L_0x003e:
            r4 = r9
            goto L_0x0065
        L_0x0040:
            if (r8 <= 0) goto L_0x0065
            r4 = 0
            goto L_0x0065
        L_0x0045:
            double r6 = java.lang.Math.pow(r23, r25)
            int r19 = (r6 > r6 ? 1 : (r6 == r6 ? 0 : -1))
            if (r19 == 0) goto L_0x00a2
            r19 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r21 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r21 != 0) goto L_0x0068
            int r0 = (r23 > r19 ? 1 : (r23 == r19 ? 0 : -1))
            if (r0 < 0) goto L_0x0065
            int r0 = (r2 > r23 ? 1 : (r2 == r23 ? 0 : -1))
            if (r0 >= 0) goto L_0x005c
            goto L_0x0065
        L_0x005c:
            int r0 = (r19 > r23 ? 1 : (r19 == r23 ? 0 : -1))
            if (r0 >= 0) goto L_0x00a2
            int r0 = (r23 > r2 ? 1 : (r23 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x00a2
            goto L_0x007e
        L_0x0065:
            r17 = r4
            goto L_0x00a4
        L_0x0068:
            int r21 = (r0 > r15 ? 1 : (r0 == r15 ? 0 : -1))
            if (r21 != 0) goto L_0x0081
            int r0 = (r23 > r19 ? 1 : (r23 == r19 ? 0 : -1))
            if (r0 < 0) goto L_0x007e
            int r0 = (r2 > r23 ? 1 : (r2 == r23 ? 0 : -1))
            if (r0 >= 0) goto L_0x0075
            goto L_0x007e
        L_0x0075:
            int r0 = (r19 > r23 ? 1 : (r19 == r23 ? 0 : -1))
            if (r0 >= 0) goto L_0x00a2
            int r0 = (r23 > r2 ? 1 : (r23 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x00a2
            goto L_0x0065
        L_0x007e:
            r17 = 0
            goto L_0x00a4
        L_0x0081:
            int r2 = (r23 > r4 ? 1 : (r23 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0088
            if (r8 <= 0) goto L_0x007e
            goto L_0x0065
        L_0x0088:
            int r2 = (r23 > r15 ? 1 : (r23 == r15 ? 0 : -1))
            if (r2 != 0) goto L_0x00a2
            long r2 = (long) r0
            double r6 = (double) r2
            int r19 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r19 != 0) goto L_0x009f
            long r0 = r2 & r13
            int r2 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r2 == 0) goto L_0x009f
            if (r8 <= 0) goto L_0x009b
            goto L_0x009c
        L_0x009b:
            r15 = r9
        L_0x009c:
            r17 = r15
            goto L_0x00a4
        L_0x009f:
            if (r8 <= 0) goto L_0x007e
            goto L_0x0065
        L_0x00a2:
            r17 = r6
        L_0x00a4:
            return r17
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeMath.js_pow(double, double):double");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r13) {
        /*
            r12 = this;
            int r0 = r13.length()
            r1 = 114(0x72, float:1.6E-43)
            r2 = 78
            r3 = 116(0x74, float:1.63E-43)
            r4 = 101(0x65, float:1.42E-43)
            r5 = 99
            r6 = 76
            r7 = 115(0x73, float:1.61E-43)
            r8 = 97
            r9 = 0
            r10 = 2
            r11 = 1
            switch(r0) {
                case 1: goto L_0x0177;
                case 2: goto L_0x0164;
                case 3: goto L_0x009f;
                case 4: goto L_0x006b;
                case 5: goto L_0x003a;
                case 6: goto L_0x0026;
                case 7: goto L_0x0020;
                case 8: goto L_0x001c;
                default: goto L_0x001a;
            }
        L_0x001a:
            goto L_0x0182
        L_0x001c:
            java.lang.String r0 = "toSource"
            goto L_0x0184
        L_0x0020:
            r11 = 26
            java.lang.String r0 = "SQRT1_2"
            goto L_0x0184
        L_0x0026:
            char r0 = r13.charAt(r9)
            if (r0 != r6) goto L_0x0032
            r11 = 25
            java.lang.String r0 = "LOG10E"
            goto L_0x0184
        L_0x0032:
            if (r0 != r1) goto L_0x0182
            r11 = 15
            java.lang.String r0 = "random"
            goto L_0x0184
        L_0x003a:
            char r0 = r13.charAt(r9)
            if (r0 == r6) goto L_0x0065
            r2 = 83
            if (r0 == r2) goto L_0x005f
            if (r0 == r8) goto L_0x005a
            r2 = 102(0x66, float:1.43E-43)
            if (r0 == r2) goto L_0x0054
            if (r0 == r1) goto L_0x004e
            goto L_0x0182
        L_0x004e:
            r11 = 16
            java.lang.String r0 = "round"
            goto L_0x0184
        L_0x0054:
            r11 = 10
            java.lang.String r0 = "floor"
            goto L_0x0184
        L_0x005a:
            r11 = 6
            java.lang.String r0 = "atan2"
            goto L_0x0184
        L_0x005f:
            r11 = 27
            java.lang.String r0 = "SQRT2"
            goto L_0x0184
        L_0x0065:
            r11 = 24
            java.lang.String r0 = "LOG2E"
            goto L_0x0184
        L_0x006b:
            char r0 = r13.charAt(r11)
            if (r0 == r2) goto L_0x0099
            if (r0 == r5) goto L_0x0094
            if (r0 == r4) goto L_0x008f
            r1 = 113(0x71, float:1.58E-43)
            if (r0 == r1) goto L_0x0089
            if (r0 == r7) goto L_0x0084
            if (r0 == r3) goto L_0x007f
            goto L_0x0182
        L_0x007f:
            r11 = 5
            java.lang.String r0 = "atan"
            goto L_0x0184
        L_0x0084:
            r11 = 4
            java.lang.String r0 = "asin"
            goto L_0x0184
        L_0x0089:
            r11 = 18
            java.lang.String r0 = "sqrt"
            goto L_0x0184
        L_0x008f:
            r11 = 7
            java.lang.String r0 = "ceil"
            goto L_0x0184
        L_0x0094:
            r11 = 3
            java.lang.String r0 = "acos"
            goto L_0x0184
        L_0x0099:
            r11 = 22
            java.lang.String r0 = "LN10"
            goto L_0x0184
        L_0x009f:
            char r0 = r13.charAt(r9)
            if (r0 == r6) goto L_0x0153
            if (r0 == r8) goto L_0x0143
            r1 = 111(0x6f, float:1.56E-43)
            if (r0 == r5) goto L_0x0134
            r2 = 120(0x78, float:1.68E-43)
            r5 = 112(0x70, float:1.57E-43)
            if (r0 == r4) goto L_0x0125
            if (r0 == r5) goto L_0x0113
            r4 = 108(0x6c, float:1.51E-43)
            if (r0 == r4) goto L_0x0101
            r1 = 109(0x6d, float:1.53E-43)
            r4 = 105(0x69, float:1.47E-43)
            r5 = 110(0x6e, float:1.54E-43)
            if (r0 == r1) goto L_0x00e5
            if (r0 == r7) goto L_0x00d5
            if (r0 == r3) goto L_0x00c5
            goto L_0x0182
        L_0x00c5:
            char r0 = r13.charAt(r10)
            if (r0 != r5) goto L_0x0182
            char r0 = r13.charAt(r11)
            if (r0 != r8) goto L_0x0182
            r9 = 19
            goto L_0x0190
        L_0x00d5:
            char r0 = r13.charAt(r10)
            if (r0 != r5) goto L_0x0182
            char r0 = r13.charAt(r11)
            if (r0 != r4) goto L_0x0182
            r9 = 17
            goto L_0x0190
        L_0x00e5:
            char r0 = r13.charAt(r10)
            if (r0 != r5) goto L_0x00f5
            char r0 = r13.charAt(r11)
            if (r0 != r4) goto L_0x0182
            r9 = 13
            goto L_0x0190
        L_0x00f5:
            if (r0 != r2) goto L_0x0182
            char r0 = r13.charAt(r11)
            if (r0 != r8) goto L_0x0182
            r9 = 12
            goto L_0x0190
        L_0x0101:
            char r0 = r13.charAt(r10)
            r2 = 103(0x67, float:1.44E-43)
            if (r0 != r2) goto L_0x0182
            char r0 = r13.charAt(r11)
            if (r0 != r1) goto L_0x0182
            r9 = 11
            goto L_0x0190
        L_0x0113:
            char r0 = r13.charAt(r10)
            r2 = 119(0x77, float:1.67E-43)
            if (r0 != r2) goto L_0x0182
            char r0 = r13.charAt(r11)
            if (r0 != r1) goto L_0x0182
            r9 = 14
            goto L_0x0190
        L_0x0125:
            char r0 = r13.charAt(r10)
            if (r0 != r5) goto L_0x0182
            char r0 = r13.charAt(r11)
            if (r0 != r2) goto L_0x0182
            r9 = 9
            goto L_0x0190
        L_0x0134:
            char r0 = r13.charAt(r10)
            if (r0 != r7) goto L_0x0182
            char r0 = r13.charAt(r11)
            if (r0 != r1) goto L_0x0182
            r9 = 8
            goto L_0x0190
        L_0x0143:
            char r0 = r13.charAt(r10)
            if (r0 != r7) goto L_0x0182
            char r0 = r13.charAt(r11)
            r1 = 98
            if (r0 != r1) goto L_0x0182
            r9 = 2
            goto L_0x0190
        L_0x0153:
            char r0 = r13.charAt(r10)
            r1 = 50
            if (r0 != r1) goto L_0x0182
            char r0 = r13.charAt(r11)
            if (r0 != r2) goto L_0x0182
            r9 = 23
            goto L_0x0190
        L_0x0164:
            char r0 = r13.charAt(r9)
            r1 = 80
            if (r0 != r1) goto L_0x0182
            char r0 = r13.charAt(r11)
            r1 = 73
            if (r0 != r1) goto L_0x0182
            r9 = 21
            goto L_0x0190
        L_0x0177:
            char r0 = r13.charAt(r9)
            r1 = 69
            if (r0 != r1) goto L_0x0182
            r9 = 20
            goto L_0x0190
        L_0x0182:
            r0 = 0
            r11 = 0
        L_0x0184:
            if (r0 == 0) goto L_0x018f
            if (r0 == r13) goto L_0x018f
            boolean r13 = r0.equals(r13)
            if (r13 != 0) goto L_0x018f
            goto L_0x0190
        L_0x018f:
            r9 = r11
        L_0x0190:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeMath.findPrototypeId(java.lang.String):int");
    }
}
