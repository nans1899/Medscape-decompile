package org.mozilla.javascript.regexp;

import kotlin.text.Typography;
import org.mozilla.javascript.BaseFunction;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.Undefined;

class NativeRegExpCtor extends BaseFunction {
    private static final int DOLLAR_ID_BASE = 12;
    private static final int Id_AMPERSAND = 6;
    private static final int Id_BACK_QUOTE = 10;
    private static final int Id_DOLLAR_1 = 13;
    private static final int Id_DOLLAR_2 = 14;
    private static final int Id_DOLLAR_3 = 15;
    private static final int Id_DOLLAR_4 = 16;
    private static final int Id_DOLLAR_5 = 17;
    private static final int Id_DOLLAR_6 = 18;
    private static final int Id_DOLLAR_7 = 19;
    private static final int Id_DOLLAR_8 = 20;
    private static final int Id_DOLLAR_9 = 21;
    private static final int Id_PLUS = 8;
    private static final int Id_QUOTE = 12;
    private static final int Id_STAR = 2;
    private static final int Id_UNDERSCORE = 4;
    private static final int Id_input = 3;
    private static final int Id_lastMatch = 5;
    private static final int Id_lastParen = 7;
    private static final int Id_leftContext = 9;
    private static final int Id_multiline = 1;
    private static final int Id_rightContext = 11;
    private static final int MAX_INSTANCE_ID = 21;
    static final long serialVersionUID = -5733330028285400526L;

    public String getFunctionName() {
        return "RegExp";
    }

    NativeRegExpCtor() {
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (objArr.length <= 0 || !(objArr[0] instanceof NativeRegExp) || (objArr.length != 1 && objArr[1] != Undefined.instance)) {
            return construct(context, scriptable, objArr);
        }
        return objArr[0];
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        NativeRegExp nativeRegExp = new NativeRegExp();
        nativeRegExp.compile(context, scriptable, objArr);
        ScriptRuntime.setBuiltinProtoAndParent(nativeRegExp, scriptable, TopLevel.Builtins.RegExp);
        return nativeRegExp;
    }

    private static RegExpImpl getImpl() {
        return (RegExpImpl) ScriptRuntime.getRegExpProxy(Context.getCurrentContext());
    }

    /* access modifiers changed from: protected */
    public int getMaxInstanceId() {
        return super.getMaxInstanceId() + 21;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00ed, code lost:
        if (r11.charAt(0) == '$') goto L_0x0105;
     */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x010c A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findInstanceIdInfo(java.lang.String r11) {
        /*
            r10 = this;
            int r0 = r11.length()
            r1 = 3
            r2 = 12
            r3 = 11
            r4 = 9
            r5 = 1
            r6 = 5
            r7 = 2
            r8 = 4
            r9 = 0
            if (r0 == r7) goto L_0x004e
            if (r0 == r6) goto L_0x0049
            if (r0 == r4) goto L_0x0028
            if (r0 == r3) goto L_0x0022
            if (r0 == r2) goto L_0x001c
            goto L_0x00f8
        L_0x001c:
            java.lang.String r0 = "rightContext"
            r2 = 11
            goto L_0x00fa
        L_0x0022:
            java.lang.String r0 = "leftContext"
            r2 = 9
            goto L_0x00fa
        L_0x0028:
            char r0 = r11.charAt(r8)
            r2 = 77
            if (r0 != r2) goto L_0x0035
            java.lang.String r0 = "lastMatch"
            r2 = 5
            goto L_0x00fa
        L_0x0035:
            r2 = 80
            if (r0 != r2) goto L_0x0040
            r0 = 7
            java.lang.String r2 = "lastParen"
            r0 = r2
            r2 = 7
            goto L_0x00fa
        L_0x0040:
            r2 = 105(0x69, float:1.47E-43)
            if (r0 != r2) goto L_0x00f8
            java.lang.String r0 = "multiline"
            r2 = 1
            goto L_0x00fa
        L_0x0049:
            java.lang.String r0 = "input"
            r2 = 3
            goto L_0x00fa
        L_0x004e:
            char r0 = r11.charAt(r5)
            r3 = 38
            r4 = 36
            if (r0 == r3) goto L_0x00f0
            r3 = 39
            if (r0 == r3) goto L_0x00e9
            r2 = 42
            if (r0 == r2) goto L_0x00e1
            r2 = 43
            if (r0 == r2) goto L_0x00d8
            r2 = 95
            if (r0 == r2) goto L_0x00d0
            r2 = 96
            if (r0 == r2) goto L_0x00c7
            switch(r0) {
                case 49: goto L_0x00be;
                case 50: goto L_0x00b5;
                case 51: goto L_0x00ac;
                case 52: goto L_0x00a3;
                case 53: goto L_0x0099;
                case 54: goto L_0x008f;
                case 55: goto L_0x0085;
                case 56: goto L_0x007b;
                case 57: goto L_0x0071;
                default: goto L_0x006f;
            }
        L_0x006f:
            goto L_0x00f8
        L_0x0071:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 21
            goto L_0x0105
        L_0x007b:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 20
            goto L_0x0105
        L_0x0085:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 19
            goto L_0x0105
        L_0x008f:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 18
            goto L_0x0105
        L_0x0099:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 17
            goto L_0x0105
        L_0x00a3:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 16
            goto L_0x0105
        L_0x00ac:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 15
            goto L_0x0105
        L_0x00b5:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 14
            goto L_0x0105
        L_0x00be:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 13
            goto L_0x0105
        L_0x00c7:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 10
            goto L_0x0105
        L_0x00d0:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 4
            goto L_0x0105
        L_0x00d8:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 8
            goto L_0x0105
        L_0x00e1:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 2
            goto L_0x0105
        L_0x00e9:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            goto L_0x0105
        L_0x00f0:
            char r0 = r11.charAt(r9)
            if (r0 != r4) goto L_0x00f8
            r2 = 6
            goto L_0x0105
        L_0x00f8:
            r0 = 0
            r2 = 0
        L_0x00fa:
            if (r0 == 0) goto L_0x0105
            if (r0 == r11) goto L_0x0105
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0105
            r2 = 0
        L_0x0105:
            if (r2 != 0) goto L_0x010c
            int r11 = super.findInstanceIdInfo(r11)
            return r11
        L_0x010c:
            if (r2 == r5) goto L_0x0115
            if (r2 == r7) goto L_0x0115
            if (r2 == r1) goto L_0x0115
            if (r2 == r8) goto L_0x0115
            goto L_0x0116
        L_0x0115:
            r6 = 4
        L_0x0116:
            int r11 = super.getMaxInstanceId()
            int r11 = r11 + r2
            int r11 = instanceIdInfo(r6, r11)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.regexp.NativeRegExpCtor.findInstanceIdInfo(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    public String getInstanceIdName(int i) {
        int maxInstanceId = i - super.getMaxInstanceId();
        if (1 > maxInstanceId || maxInstanceId > 21) {
            return super.getInstanceIdName(i);
        }
        switch (maxInstanceId) {
            case 1:
                return "multiline";
            case 2:
                return "$*";
            case 3:
                return "input";
            case 4:
                return "$_";
            case 5:
                return "lastMatch";
            case 6:
                return "$&";
            case 7:
                return "lastParen";
            case 8:
                return "$+";
            case 9:
                return "leftContext";
            case 10:
                return "$`";
            case 11:
                return "rightContext";
            case 12:
                return "$'";
            default:
                return new String(new char[]{Typography.dollar, (char) (((maxInstanceId - 12) - 1) + 49)});
        }
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        Object obj;
        int maxInstanceId = i - super.getMaxInstanceId();
        if (1 > maxInstanceId || maxInstanceId > 21) {
            return super.getInstanceIdValue(i);
        }
        RegExpImpl impl = getImpl();
        switch (maxInstanceId) {
            case 1:
            case 2:
                return ScriptRuntime.wrapBoolean(impl.multiline);
            case 3:
            case 4:
                obj = impl.input;
                break;
            case 5:
            case 6:
                obj = impl.lastMatch;
                break;
            case 7:
            case 8:
                obj = impl.lastParen;
                break;
            case 9:
            case 10:
                obj = impl.leftContext;
                break;
            case 11:
            case 12:
                obj = impl.rightContext;
                break;
            default:
                obj = impl.getParenSubString((maxInstanceId - 12) - 1);
                break;
        }
        return obj == null ? "" : obj.toString();
    }

    /* access modifiers changed from: protected */
    public void setInstanceIdValue(int i, Object obj) {
        int maxInstanceId = i - super.getMaxInstanceId();
        switch (maxInstanceId) {
            case 1:
            case 2:
                getImpl().multiline = ScriptRuntime.toBoolean(obj);
                return;
            case 3:
            case 4:
                getImpl().input = ScriptRuntime.toString(obj);
                return;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                return;
            default:
                int i2 = (maxInstanceId - 12) - 1;
                if (i2 < 0 || i2 > 8) {
                    super.setInstanceIdValue(i, obj);
                    return;
                }
                return;
        }
    }
}
