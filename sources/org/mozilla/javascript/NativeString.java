package org.mozilla.javascript;

import com.appboy.Constants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.text.Collator;

final class NativeString extends IdScriptableObject {
    private static final int ConstructorId_charAt = -5;
    private static final int ConstructorId_charCodeAt = -6;
    private static final int ConstructorId_concat = -14;
    private static final int ConstructorId_equalsIgnoreCase = -30;
    private static final int ConstructorId_fromCharCode = -1;
    private static final int ConstructorId_indexOf = -7;
    private static final int ConstructorId_lastIndexOf = -8;
    private static final int ConstructorId_localeCompare = -34;
    private static final int ConstructorId_match = -31;
    private static final int ConstructorId_replace = -33;
    private static final int ConstructorId_search = -32;
    private static final int ConstructorId_slice = -15;
    private static final int ConstructorId_split = -9;
    private static final int ConstructorId_substr = -13;
    private static final int ConstructorId_substring = -10;
    private static final int ConstructorId_toLocaleLowerCase = -35;
    private static final int ConstructorId_toLowerCase = -11;
    private static final int ConstructorId_toUpperCase = -12;
    private static final int Id_anchor = 28;
    private static final int Id_big = 21;
    private static final int Id_blink = 22;
    private static final int Id_bold = 16;
    private static final int Id_charAt = 5;
    private static final int Id_charCodeAt = 6;
    private static final int Id_concat = 14;
    private static final int Id_constructor = 1;
    private static final int Id_equals = 29;
    private static final int Id_equalsIgnoreCase = 30;
    private static final int Id_fixed = 18;
    private static final int Id_fontcolor = 26;
    private static final int Id_fontsize = 25;
    private static final int Id_indexOf = 7;
    private static final int Id_italics = 17;
    private static final int Id_lastIndexOf = 8;
    private static final int Id_length = 1;
    private static final int Id_link = 27;
    private static final int Id_localeCompare = 34;
    private static final int Id_match = 31;
    private static final int Id_replace = 33;
    private static final int Id_search = 32;
    private static final int Id_slice = 15;
    private static final int Id_small = 20;
    private static final int Id_split = 9;
    private static final int Id_strike = 19;
    private static final int Id_sub = 24;
    private static final int Id_substr = 13;
    private static final int Id_substring = 10;
    private static final int Id_sup = 23;
    private static final int Id_toLocaleLowerCase = 35;
    private static final int Id_toLocaleUpperCase = 36;
    private static final int Id_toLowerCase = 11;
    private static final int Id_toSource = 3;
    private static final int Id_toString = 2;
    private static final int Id_toUpperCase = 12;
    private static final int Id_trim = 37;
    private static final int Id_valueOf = 4;
    private static final int MAX_INSTANCE_ID = 1;
    private static final int MAX_PROTOTYPE_ID = 37;
    private static final Object STRING_TAG = "String";
    static final long serialVersionUID = 920268368584188687L;
    private CharSequence string;

    public String getClassName() {
        return "String";
    }

    /* access modifiers changed from: protected */
    public int getMaxInstanceId() {
        return 1;
    }

    static void init(Scriptable scriptable, boolean z) {
        new NativeString("").exportAsJSClass(37, scriptable, z);
    }

    NativeString(CharSequence charSequence) {
        this.string = charSequence;
    }

    /* access modifiers changed from: protected */
    public int findInstanceIdInfo(String str) {
        if (str.equals(Name.LENGTH)) {
            return instanceIdInfo(7, 1);
        }
        return super.findInstanceIdInfo(str);
    }

    /* access modifiers changed from: protected */
    public String getInstanceIdName(int i) {
        return i == 1 ? Name.LENGTH : super.getInstanceIdName(i);
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        if (i == 1) {
            return ScriptRuntime.wrapInt(this.string.length());
        }
        return super.getInstanceIdValue(i);
    }

    /* access modifiers changed from: protected */
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        IdFunctionObject idFunctionObject2 = idFunctionObject;
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, -1, "fromCharCode", 1);
        IdFunctionObject idFunctionObject3 = idFunctionObject;
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, -5, "charAt", 2);
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, -6, "charCodeAt", 2);
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, -7, "indexOf", 2);
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, -8, "lastIndexOf", 2);
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, -9, "split", 3);
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, -10, "substring", 3);
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, -11, "toLowerCase", 1);
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, -12, "toUpperCase", 1);
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, -13, "substr", 3);
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, -14, "concat", 2);
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, ConstructorId_slice, "slice", 3);
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, ConstructorId_equalsIgnoreCase, "equalsIgnoreCase", 2);
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, ConstructorId_match, "match", 2);
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, ConstructorId_search, "search", 2);
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, ConstructorId_replace, "replace", 2);
        addIdFunctionProperty(idFunctionObject2, STRING_TAG, ConstructorId_localeCompare, "localeCompare", 2);
        addIdFunctionProperty(idFunctionObject3, STRING_TAG, ConstructorId_toLocaleLowerCase, "toLocaleLowerCase", 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0072, code lost:
        r0 = r1;
        r2 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008c, code lost:
        r2 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x008d, code lost:
        initPrototypeMethod(STRING_TAG, r4, r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0092, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initPrototypeId(int r4) {
        /*
            r3 = this;
            r0 = 2
            r1 = 1
            r2 = 0
            switch(r4) {
                case 1: goto L_0x008a;
                case 2: goto L_0x0087;
                case 3: goto L_0x0084;
                case 4: goto L_0x0081;
                case 5: goto L_0x007e;
                case 6: goto L_0x007b;
                case 7: goto L_0x0078;
                case 8: goto L_0x0075;
                case 9: goto L_0x0070;
                case 10: goto L_0x006d;
                case 11: goto L_0x006a;
                case 12: goto L_0x0067;
                case 13: goto L_0x0064;
                case 14: goto L_0x0061;
                case 15: goto L_0x005e;
                case 16: goto L_0x005b;
                case 17: goto L_0x0058;
                case 18: goto L_0x0055;
                case 19: goto L_0x0052;
                case 20: goto L_0x004f;
                case 21: goto L_0x004c;
                case 22: goto L_0x0049;
                case 23: goto L_0x0046;
                case 24: goto L_0x0043;
                case 25: goto L_0x0040;
                case 26: goto L_0x003c;
                case 27: goto L_0x0038;
                case 28: goto L_0x0034;
                case 29: goto L_0x0030;
                case 30: goto L_0x002c;
                case 31: goto L_0x0028;
                case 32: goto L_0x0024;
                case 33: goto L_0x0020;
                case 34: goto L_0x001c;
                case 35: goto L_0x0018;
                case 36: goto L_0x0014;
                case 37: goto L_0x0010;
                default: goto L_0x0006;
            }
        L_0x0006:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0.<init>(r4)
            throw r0
        L_0x0010:
            java.lang.String r0 = "trim"
            goto L_0x008d
        L_0x0014:
            java.lang.String r0 = "toLocaleUpperCase"
            goto L_0x008d
        L_0x0018:
            java.lang.String r0 = "toLocaleLowerCase"
            goto L_0x008d
        L_0x001c:
            java.lang.String r0 = "localeCompare"
            goto L_0x008c
        L_0x0020:
            java.lang.String r0 = "replace"
            goto L_0x008c
        L_0x0024:
            java.lang.String r0 = "search"
            goto L_0x008c
        L_0x0028:
            java.lang.String r0 = "match"
            goto L_0x008c
        L_0x002c:
            java.lang.String r0 = "equalsIgnoreCase"
            goto L_0x008c
        L_0x0030:
            java.lang.String r0 = "equals"
            goto L_0x008c
        L_0x0034:
            java.lang.String r0 = "anchor"
            goto L_0x008d
        L_0x0038:
            java.lang.String r0 = "link"
            goto L_0x008d
        L_0x003c:
            java.lang.String r0 = "fontcolor"
            goto L_0x008d
        L_0x0040:
            java.lang.String r0 = "fontsize"
            goto L_0x008d
        L_0x0043:
            java.lang.String r0 = "sub"
            goto L_0x008d
        L_0x0046:
            java.lang.String r0 = "sup"
            goto L_0x008d
        L_0x0049:
            java.lang.String r0 = "blink"
            goto L_0x008d
        L_0x004c:
            java.lang.String r0 = "big"
            goto L_0x008d
        L_0x004f:
            java.lang.String r0 = "small"
            goto L_0x008d
        L_0x0052:
            java.lang.String r0 = "strike"
            goto L_0x008d
        L_0x0055:
            java.lang.String r0 = "fixed"
            goto L_0x008d
        L_0x0058:
            java.lang.String r0 = "italics"
            goto L_0x008d
        L_0x005b:
            java.lang.String r0 = "bold"
            goto L_0x008d
        L_0x005e:
            java.lang.String r1 = "slice"
            goto L_0x0072
        L_0x0061:
            java.lang.String r0 = "concat"
            goto L_0x008c
        L_0x0064:
            java.lang.String r1 = "substr"
            goto L_0x0072
        L_0x0067:
            java.lang.String r0 = "toUpperCase"
            goto L_0x008d
        L_0x006a:
            java.lang.String r0 = "toLowerCase"
            goto L_0x008d
        L_0x006d:
            java.lang.String r1 = "substring"
            goto L_0x0072
        L_0x0070:
            java.lang.String r1 = "split"
        L_0x0072:
            r0 = r1
            r2 = 2
            goto L_0x008d
        L_0x0075:
            java.lang.String r0 = "lastIndexOf"
            goto L_0x008c
        L_0x0078:
            java.lang.String r0 = "indexOf"
            goto L_0x008c
        L_0x007b:
            java.lang.String r0 = "charCodeAt"
            goto L_0x008c
        L_0x007e:
            java.lang.String r0 = "charAt"
            goto L_0x008c
        L_0x0081:
            java.lang.String r0 = "valueOf"
            goto L_0x008d
        L_0x0084:
            java.lang.String r0 = "toSource"
            goto L_0x008d
        L_0x0087:
            java.lang.String r0 = "toString"
            goto L_0x008d
        L_0x008a:
            java.lang.String r0 = "constructor"
        L_0x008c:
            r2 = 1
        L_0x008d:
            java.lang.Object r1 = STRING_TAG
            r3.initPrototypeMethod(r1, r4, r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeString.initPrototypeId(int):void");
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        boolean z;
        if (!idFunctionObject.hasTag(STRING_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        Scriptable scriptable3 = scriptable2;
        Object[] objArr2 = objArr;
        while (true) {
            CharSequence charSequence = "";
            int i = 0;
            if (methodId != -1) {
                switch (methodId) {
                    case ConstructorId_toLocaleLowerCase /*-35*/:
                    case ConstructorId_localeCompare /*-34*/:
                    case ConstructorId_replace /*-33*/:
                    case ConstructorId_search /*-32*/:
                    case ConstructorId_match /*-31*/:
                    case ConstructorId_equalsIgnoreCase /*-30*/:
                        break;
                    default:
                        switch (methodId) {
                            case ConstructorId_slice /*-15*/:
                            case -14:
                            case -13:
                            case -12:
                            case -11:
                            case -10:
                            case -9:
                            case -8:
                            case -7:
                            case -6:
                            case -5:
                                break;
                            default:
                                switch (methodId) {
                                    case 1:
                                        if (objArr2.length >= 1) {
                                            charSequence = ScriptRuntime.toCharSequence(objArr2[0]);
                                        }
                                        if (scriptable3 == null) {
                                            return new NativeString(charSequence);
                                        }
                                        return charSequence instanceof String ? charSequence : charSequence.toString();
                                    case 2:
                                    case 4:
                                        CharSequence charSequence2 = realThis(scriptable3, idFunctionObject).string;
                                        return charSequence2 instanceof String ? charSequence2 : charSequence2.toString();
                                    case 3:
                                        CharSequence charSequence3 = realThis(scriptable3, idFunctionObject).string;
                                        return "(new String(\"" + ScriptRuntime.escapeString(charSequence3.toString()) + "\"))";
                                    case 5:
                                    case 6:
                                        CharSequence charSequence4 = ScriptRuntime.toCharSequence(scriptable3);
                                        double integer = ScriptRuntime.toInteger(objArr2, 0);
                                        if (integer >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && integer < ((double) charSequence4.length())) {
                                            char charAt = charSequence4.charAt((int) integer);
                                            if (methodId == 5) {
                                                return String.valueOf(charAt);
                                            }
                                            return ScriptRuntime.wrapInt(charAt);
                                        } else if (methodId == 5) {
                                            return charSequence;
                                        } else {
                                            return ScriptRuntime.NaNobj;
                                        }
                                    case 7:
                                        return ScriptRuntime.wrapInt(js_indexOf(ScriptRuntime.toString((Object) scriptable3), objArr2));
                                    case 8:
                                        return ScriptRuntime.wrapInt(js_lastIndexOf(ScriptRuntime.toString((Object) scriptable3), objArr2));
                                    case 9:
                                        return ScriptRuntime.checkRegExpProxy(context).js_split(context, scriptable, ScriptRuntime.toString((Object) scriptable3), objArr2);
                                    case 10:
                                        return js_substring(context, ScriptRuntime.toCharSequence(scriptable3), objArr2);
                                    case 11:
                                        return ScriptRuntime.toString((Object) scriptable3).toLowerCase(ScriptRuntime.ROOT_LOCALE);
                                    case 12:
                                        return ScriptRuntime.toString((Object) scriptable3).toUpperCase(ScriptRuntime.ROOT_LOCALE);
                                    case 13:
                                        return js_substr(ScriptRuntime.toCharSequence(scriptable3), objArr2);
                                    case 14:
                                        return js_concat(ScriptRuntime.toString((Object) scriptable3), objArr2);
                                    case 15:
                                        return js_slice(ScriptRuntime.toCharSequence(scriptable3), objArr2);
                                    case 16:
                                        return tagify(scriptable3, "b", (String) null, (Object[]) null);
                                    case 17:
                                        return tagify(scriptable3, "i", (String) null, (Object[]) null);
                                    case 18:
                                        return tagify(scriptable3, "tt", (String) null, (Object[]) null);
                                    case 19:
                                        return tagify(scriptable3, "strike", (String) null, (Object[]) null);
                                    case 20:
                                        return tagify(scriptable3, "small", (String) null, (Object[]) null);
                                    case 21:
                                        return tagify(scriptable3, "big", (String) null, (Object[]) null);
                                    case 22:
                                        return tagify(scriptable3, "blink", (String) null, (Object[]) null);
                                    case 23:
                                        return tagify(scriptable3, "sup", (String) null, (Object[]) null);
                                    case 24:
                                        return tagify(scriptable3, "sub", (String) null, (Object[]) null);
                                    case 25:
                                        return tagify(scriptable3, "font", "size", objArr2);
                                    case 26:
                                        return tagify(scriptable3, "font", "color", objArr2);
                                    case 27:
                                        return tagify(scriptable3, Constants.APPBOY_PUSH_CONTENT_KEY, "href", objArr2);
                                    case 28:
                                        return tagify(scriptable3, Constants.APPBOY_PUSH_CONTENT_KEY, "name", objArr2);
                                    case 29:
                                    case 30:
                                        String scriptRuntime = ScriptRuntime.toString((Object) scriptable3);
                                        String scriptRuntime2 = ScriptRuntime.toString(objArr2, 0);
                                        if (methodId == 29) {
                                            z = scriptRuntime.equals(scriptRuntime2);
                                        } else {
                                            z = scriptRuntime.equalsIgnoreCase(scriptRuntime2);
                                        }
                                        return ScriptRuntime.wrapBoolean(z);
                                    case 31:
                                    case 32:
                                    case 33:
                                        return ScriptRuntime.checkRegExpProxy(context).action(context, scriptable, scriptable3, objArr2, methodId == 31 ? 1 : methodId == 32 ? 3 : 2);
                                    case 34:
                                        Collator instance = Collator.getInstance(context.getLocale());
                                        instance.setStrength(3);
                                        instance.setDecomposition(1);
                                        return ScriptRuntime.wrapNumber((double) instance.compare(ScriptRuntime.toString((Object) scriptable3), ScriptRuntime.toString(objArr2, 0)));
                                    case 35:
                                        return ScriptRuntime.toString((Object) scriptable3).toLowerCase(context.getLocale());
                                    case 36:
                                        return ScriptRuntime.toString((Object) scriptable3).toUpperCase(context.getLocale());
                                    case 37:
                                        String scriptRuntime3 = ScriptRuntime.toString((Object) scriptable3);
                                        char[] charArray = scriptRuntime3.toCharArray();
                                        while (i < charArray.length && ScriptRuntime.isJSWhitespaceOrLineTerminator(charArray[i])) {
                                            i++;
                                        }
                                        int length = charArray.length;
                                        while (length > i && ScriptRuntime.isJSWhitespaceOrLineTerminator(charArray[length - 1])) {
                                            length--;
                                        }
                                        return scriptRuntime3.substring(i, length);
                                    default:
                                        throw new IllegalArgumentException(String.valueOf(methodId));
                                }
                        }
                }
                if (objArr2.length > 0) {
                    Scriptable object = ScriptRuntime.toObject(scriptable, ScriptRuntime.toCharSequence(objArr2[0]));
                    int length2 = objArr2.length - 1;
                    Object[] objArr3 = new Object[length2];
                    while (i < length2) {
                        int i2 = i + 1;
                        objArr3[i] = objArr2[i2];
                        i = i2;
                    }
                    scriptable3 = object;
                    objArr2 = objArr3;
                } else {
                    scriptable3 = ScriptRuntime.toObject(scriptable, ScriptRuntime.toCharSequence(scriptable3));
                }
                methodId = -methodId;
            } else {
                int length3 = objArr2.length;
                if (length3 < 1) {
                    return charSequence;
                }
                StringBuffer stringBuffer = new StringBuffer(length3);
                while (i != length3) {
                    stringBuffer.append(ScriptRuntime.toUint16(objArr2[i]));
                    i++;
                }
                return stringBuffer.toString();
            }
        }
    }

    private static NativeString realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (scriptable instanceof NativeString) {
            return (NativeString) scriptable;
        }
        throw incompatibleCallError(idFunctionObject);
    }

    private static String tagify(Object obj, String str, String str2, Object[] objArr) {
        String scriptRuntime = ScriptRuntime.toString(obj);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('<');
        stringBuffer.append(str);
        if (str2 != null) {
            stringBuffer.append(' ');
            stringBuffer.append(str2);
            stringBuffer.append("=\"");
            stringBuffer.append(ScriptRuntime.toString(objArr, 0));
            stringBuffer.append('\"');
        }
        stringBuffer.append('>');
        stringBuffer.append(scriptRuntime);
        stringBuffer.append("</");
        stringBuffer.append(str);
        stringBuffer.append('>');
        return stringBuffer.toString();
    }

    public CharSequence toCharSequence() {
        return this.string;
    }

    public String toString() {
        CharSequence charSequence = this.string;
        return charSequence instanceof String ? (String) charSequence : charSequence.toString();
    }

    public Object get(int i, Scriptable scriptable) {
        if (i < 0 || i >= this.string.length()) {
            return super.get(i, scriptable);
        }
        return String.valueOf(this.string.charAt(i));
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        if (i < 0 || i >= this.string.length()) {
            super.put(i, scriptable, obj);
        }
    }

    private static int js_indexOf(String str, Object[] objArr) {
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        double integer = ScriptRuntime.toInteger(objArr, 1);
        if (integer > ((double) str.length())) {
            return -1;
        }
        if (integer < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            integer = 0.0d;
        }
        return str.indexOf(scriptRuntime, (int) integer);
    }

    private static int js_lastIndexOf(String str, Object[] objArr) {
        String scriptRuntime = ScriptRuntime.toString(objArr, 0);
        double number = ScriptRuntime.toNumber(objArr, 1);
        if (number != number || number > ((double) str.length())) {
            number = (double) str.length();
        } else if (number < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            number = 0.0d;
        }
        return str.lastIndexOf(scriptRuntime, (int) number);
    }

    private static CharSequence js_substring(Context context, CharSequence charSequence, Object[] objArr) {
        int length = charSequence.length();
        double integer = ScriptRuntime.toInteger(objArr, 0);
        double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (integer < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            integer = 0.0d;
        } else {
            double d2 = (double) length;
            if (integer > d2) {
                integer = d2;
            }
        }
        if (objArr.length <= 1 || objArr[1] == Undefined.instance) {
            d = (double) length;
        } else {
            double integer2 = ScriptRuntime.toInteger(objArr[1]);
            if (integer2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d = (double) length;
                if (integer2 <= d) {
                    d = integer2;
                }
            }
            if (d < integer) {
                if (context.getLanguageVersion() != 120) {
                    double d3 = integer;
                    integer = d;
                    d = d3;
                } else {
                    d = integer;
                }
            }
        }
        return charSequence.subSequence((int) integer, (int) d);
    }

    /* access modifiers changed from: package-private */
    public int getLength() {
        return this.string.length();
    }

    private static CharSequence js_substr(CharSequence charSequence, Object[] objArr) {
        double d;
        if (objArr.length < 1) {
            return charSequence;
        }
        double integer = ScriptRuntime.toInteger(objArr[0]);
        int length = charSequence.length();
        double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (integer < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            integer += (double) length;
            if (integer < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                integer = 0.0d;
            }
        } else {
            double d3 = (double) length;
            if (integer > d3) {
                integer = d3;
            }
        }
        if (objArr.length == 1) {
            d = (double) length;
        } else {
            double integer2 = ScriptRuntime.toInteger(objArr[1]);
            if (integer2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                d2 = integer2;
            }
            double d4 = d2 + integer;
            d = (double) length;
            if (d4 <= d) {
                d = d4;
            }
        }
        return charSequence.subSequence((int) integer, (int) d);
    }

    private static String js_concat(String str, Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return str;
        }
        if (length == 1) {
            return str.concat(ScriptRuntime.toString(objArr[0]));
        }
        int length2 = str.length();
        String[] strArr = new String[length];
        for (int i = 0; i != length; i++) {
            String scriptRuntime = ScriptRuntime.toString(objArr[i]);
            strArr[i] = scriptRuntime;
            length2 += scriptRuntime.length();
        }
        StringBuffer stringBuffer = new StringBuffer(length2);
        stringBuffer.append(str);
        for (int i2 = 0; i2 != length; i2++) {
            stringBuffer.append(strArr[i2]);
        }
        return stringBuffer.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        if (r5 < com.google.firebase.remoteconfig.FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
        if (r5 > r3) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.CharSequence js_slice(java.lang.CharSequence r9, java.lang.Object[] r10) {
        /*
            int r0 = r10.length
            if (r0 == 0) goto L_0x004d
            r0 = 0
            r0 = r10[r0]
            double r0 = org.mozilla.javascript.ScriptRuntime.toInteger((java.lang.Object) r0)
            int r2 = r9.length()
            r3 = 0
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x001c
            double r5 = (double) r2
            double r0 = r0 + r5
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0022
            r0 = r3
            goto L_0x0022
        L_0x001c:
            double r5 = (double) r2
            int r7 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0022
            r0 = r5
        L_0x0022:
            int r5 = r10.length
            r6 = 1
            if (r5 != r6) goto L_0x0028
            double r2 = (double) r2
            goto L_0x0047
        L_0x0028:
            r10 = r10[r6]
            double r5 = org.mozilla.javascript.ScriptRuntime.toInteger((java.lang.Object) r10)
            int r10 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r10 >= 0) goto L_0x0039
            double r7 = (double) r2
            double r5 = r5 + r7
            int r10 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r10 >= 0) goto L_0x003f
            goto L_0x0040
        L_0x0039:
            double r3 = (double) r2
            int r10 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r10 <= 0) goto L_0x003f
            goto L_0x0040
        L_0x003f:
            r3 = r5
        L_0x0040:
            int r10 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r10 >= 0) goto L_0x0046
            r2 = r0
            goto L_0x0047
        L_0x0046:
            r2 = r3
        L_0x0047:
            int r10 = (int) r0
            int r0 = (int) r2
            java.lang.CharSequence r9 = r9.subSequence(r10, r0)
        L_0x004d:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeString.js_slice(java.lang.CharSequence, java.lang.Object[]):java.lang.CharSequence");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = r17.length()
            r2 = 8
            r3 = 85
            r4 = 76
            r5 = 108(0x6c, float:1.51E-43)
            r6 = 104(0x68, float:1.46E-43)
            r7 = 110(0x6e, float:1.54E-43)
            r8 = 101(0x65, float:1.42E-43)
            r9 = 4
            r11 = 2
            r12 = 117(0x75, float:1.64E-43)
            r13 = 116(0x74, float:1.63E-43)
            r14 = 115(0x73, float:1.61E-43)
            r15 = 1
            r10 = 0
            switch(r1) {
                case 3: goto L_0x0158;
                case 4: goto L_0x013d;
                case 5: goto L_0x0104;
                case 6: goto L_0x00c3;
                case 7: goto L_0x009d;
                case 8: goto L_0x0081;
                case 9: goto L_0x006b;
                case 10: goto L_0x0066;
                case 11: goto L_0x0043;
                case 12: goto L_0x0021;
                case 13: goto L_0x003d;
                case 14: goto L_0x0021;
                case 15: goto L_0x0021;
                case 16: goto L_0x0037;
                case 17: goto L_0x0023;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0199
        L_0x0023:
            char r1 = r0.charAt(r2)
            if (r1 != r4) goto L_0x002f
            r2 = 35
            java.lang.String r1 = "toLocaleLowerCase"
            goto L_0x019b
        L_0x002f:
            if (r1 != r3) goto L_0x0199
            r2 = 36
            java.lang.String r1 = "toLocaleUpperCase"
            goto L_0x019b
        L_0x0037:
            r2 = 30
            java.lang.String r1 = "equalsIgnoreCase"
            goto L_0x019b
        L_0x003d:
            r2 = 34
            java.lang.String r1 = "localeCompare"
            goto L_0x019b
        L_0x0043:
            char r1 = r0.charAt(r11)
            if (r1 == r4) goto L_0x0060
            if (r1 == r3) goto L_0x005a
            if (r1 == r7) goto L_0x0055
            if (r1 == r14) goto L_0x0051
            goto L_0x0199
        L_0x0051:
            java.lang.String r1 = "lastIndexOf"
            goto L_0x019b
        L_0x0055:
            java.lang.String r1 = "constructor"
            r2 = 1
            goto L_0x019b
        L_0x005a:
            r2 = 12
            java.lang.String r1 = "toUpperCase"
            goto L_0x019b
        L_0x0060:
            r2 = 11
            java.lang.String r1 = "toLowerCase"
            goto L_0x019b
        L_0x0066:
            r2 = 6
            java.lang.String r1 = "charCodeAt"
            goto L_0x019b
        L_0x006b:
            char r1 = r0.charAt(r10)
            r2 = 102(0x66, float:1.43E-43)
            if (r1 != r2) goto L_0x0079
            r2 = 26
            java.lang.String r1 = "fontcolor"
            goto L_0x019b
        L_0x0079:
            if (r1 != r14) goto L_0x0199
            r2 = 10
            java.lang.String r1 = "substring"
            goto L_0x019b
        L_0x0081:
            char r1 = r0.charAt(r9)
            r2 = 114(0x72, float:1.6E-43)
            if (r1 != r2) goto L_0x008e
            java.lang.String r1 = "toString"
            r2 = 2
            goto L_0x019b
        L_0x008e:
            if (r1 != r14) goto L_0x0096
            r2 = 25
            java.lang.String r1 = "fontsize"
            goto L_0x019b
        L_0x0096:
            if (r1 != r12) goto L_0x0199
            r2 = 3
            java.lang.String r1 = "toSource"
            goto L_0x019b
        L_0x009d:
            char r1 = r0.charAt(r15)
            r2 = 97
            if (r1 == r2) goto L_0x00be
            if (r1 == r8) goto L_0x00b8
            if (r1 == r7) goto L_0x00b3
            if (r1 == r13) goto L_0x00ad
            goto L_0x0199
        L_0x00ad:
            r2 = 17
            java.lang.String r1 = "italics"
            goto L_0x019b
        L_0x00b3:
            r2 = 7
            java.lang.String r1 = "indexOf"
            goto L_0x019b
        L_0x00b8:
            r2 = 33
            java.lang.String r1 = "replace"
            goto L_0x019b
        L_0x00be:
            java.lang.String r1 = "valueOf"
            r2 = 4
            goto L_0x019b
        L_0x00c3:
            char r1 = r0.charAt(r15)
            if (r1 == r8) goto L_0x00fe
            if (r1 == r6) goto L_0x00f9
            r2 = 113(0x71, float:1.58E-43)
            if (r1 == r2) goto L_0x00f3
            if (r1 == r7) goto L_0x00ed
            r2 = 111(0x6f, float:1.56E-43)
            if (r1 == r2) goto L_0x00e7
            if (r1 == r13) goto L_0x00e1
            if (r1 == r12) goto L_0x00db
            goto L_0x0199
        L_0x00db:
            r2 = 13
            java.lang.String r1 = "substr"
            goto L_0x019b
        L_0x00e1:
            r2 = 19
            java.lang.String r1 = "strike"
            goto L_0x019b
        L_0x00e7:
            r2 = 14
            java.lang.String r1 = "concat"
            goto L_0x019b
        L_0x00ed:
            r2 = 28
            java.lang.String r1 = "anchor"
            goto L_0x019b
        L_0x00f3:
            r2 = 29
            java.lang.String r1 = "equals"
            goto L_0x019b
        L_0x00f9:
            r2 = 5
            java.lang.String r1 = "charAt"
            goto L_0x019b
        L_0x00fe:
            r2 = 32
            java.lang.String r1 = "search"
            goto L_0x019b
        L_0x0104:
            char r1 = r0.charAt(r9)
            r2 = 100
            if (r1 == r2) goto L_0x0138
            if (r1 == r8) goto L_0x0132
            if (r1 == r6) goto L_0x012c
            if (r1 == r13) goto L_0x0126
            r2 = 107(0x6b, float:1.5E-43)
            if (r1 == r2) goto L_0x0120
            if (r1 == r5) goto L_0x011a
            goto L_0x0199
        L_0x011a:
            r2 = 20
            java.lang.String r1 = "small"
            goto L_0x019b
        L_0x0120:
            r2 = 22
            java.lang.String r1 = "blink"
            goto L_0x019b
        L_0x0126:
            r2 = 9
            java.lang.String r1 = "split"
            goto L_0x019b
        L_0x012c:
            r2 = 31
            java.lang.String r1 = "match"
            goto L_0x019b
        L_0x0132:
            r2 = 15
            java.lang.String r1 = "slice"
            goto L_0x019b
        L_0x0138:
            r2 = 18
            java.lang.String r1 = "fixed"
            goto L_0x019b
        L_0x013d:
            char r1 = r0.charAt(r10)
            r2 = 98
            if (r1 != r2) goto L_0x014a
            r2 = 16
            java.lang.String r1 = "bold"
            goto L_0x019b
        L_0x014a:
            if (r1 != r5) goto L_0x0151
            r2 = 27
            java.lang.String r1 = "link"
            goto L_0x019b
        L_0x0151:
            if (r1 != r13) goto L_0x0199
            r2 = 37
            java.lang.String r1 = "trim"
            goto L_0x019b
        L_0x0158:
            char r1 = r0.charAt(r11)
            r2 = 98
            if (r1 != r2) goto L_0x016f
            char r1 = r0.charAt(r10)
            if (r1 != r14) goto L_0x0199
            char r1 = r0.charAt(r15)
            if (r1 != r12) goto L_0x0199
            r10 = 24
            goto L_0x01a7
        L_0x016f:
            r2 = 103(0x67, float:1.44E-43)
            if (r1 != r2) goto L_0x0186
            char r1 = r0.charAt(r10)
            r2 = 98
            if (r1 != r2) goto L_0x0199
            char r1 = r0.charAt(r15)
            r2 = 105(0x69, float:1.47E-43)
            if (r1 != r2) goto L_0x0199
            r10 = 21
            goto L_0x01a7
        L_0x0186:
            r2 = 112(0x70, float:1.57E-43)
            if (r1 != r2) goto L_0x0199
            char r1 = r0.charAt(r10)
            if (r1 != r14) goto L_0x0199
            char r1 = r0.charAt(r15)
            if (r1 != r12) goto L_0x0199
            r10 = 23
            goto L_0x01a7
        L_0x0199:
            r1 = 0
            r2 = 0
        L_0x019b:
            if (r1 == 0) goto L_0x01a6
            if (r1 == r0) goto L_0x01a6
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x01a6
            goto L_0x01a7
        L_0x01a6:
            r10 = r2
        L_0x01a7:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeString.findPrototypeId(java.lang.String):int");
    }
}
