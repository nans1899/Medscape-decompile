package org.mozilla.javascript;

import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.appevents.AppEventsConstants;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.google.android.gms.ads.AdError;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.slideshow.SlideshowPageFragment;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.mozilla.javascript.NativeIterator;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.v8dtoa.FastDtoa;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLObject;
import org.objenesis.strategy.PlatformDescription;

public class ScriptRuntime {
    public static final Class<?> BooleanClass = Kit.classOrNull("java.lang.Boolean");
    public static final Class<?> ByteClass = Kit.classOrNull("java.lang.Byte");
    public static final Class<?> CharacterClass = Kit.classOrNull("java.lang.Character");
    public static final Class<?> ClassClass = Kit.classOrNull("java.lang.Class");
    public static final Class<?> ContextClass = Kit.classOrNull("org.mozilla.javascript.Context");
    public static final Class<?> ContextFactoryClass = Kit.classOrNull("org.mozilla.javascript.ContextFactory");
    private static final String DEFAULT_NS_TAG = "__default_namespace__";
    public static final Class<?> DateClass = Kit.classOrNull("java.util.Date");
    public static final Class<?> DoubleClass = Kit.classOrNull("java.lang.Double");
    public static final int ENUMERATE_ARRAY = 2;
    public static final int ENUMERATE_ARRAY_NO_ITERATOR = 5;
    public static final int ENUMERATE_KEYS = 0;
    public static final int ENUMERATE_KEYS_NO_ITERATOR = 3;
    public static final int ENUMERATE_VALUES = 1;
    public static final int ENUMERATE_VALUES_NO_ITERATOR = 4;
    public static final Class<?> FloatClass = Kit.classOrNull("java.lang.Float");
    public static final Class<?> FunctionClass = Kit.classOrNull("org.mozilla.javascript.Function");
    public static final Class<?> IntegerClass = Kit.classOrNull("java.lang.Integer");
    private static final Object LIBRARY_SCOPE_KEY = "LIBRARY_SCOPE";
    public static final Class<?> LongClass = Kit.classOrNull("java.lang.Long");
    public static final double NaN = Double.longBitsToDouble(9221120237041090560L);
    public static final Double NaNobj = new Double(NaN);
    public static final Class<?> NumberClass = Kit.classOrNull("java.lang.Number");
    public static final Class<?> ObjectClass = Kit.classOrNull("java.lang.Object");
    public static Locale ROOT_LOCALE = new Locale("");
    public static final Class<Scriptable> ScriptableClass = Scriptable.class;
    public static final Class<?> ScriptableObjectClass = Kit.classOrNull("org.mozilla.javascript.ScriptableObject");
    public static final Class<?> ShortClass = Kit.classOrNull("java.lang.Short");
    public static final Class<?> StringClass = Kit.classOrNull("java.lang.String");
    private static BaseFunction THROW_TYPE_ERROR;
    public static final Object[] emptyArgs = new Object[0];
    public static final String[] emptyStrings = new String[0];
    public static MessageProvider messageProvider = new DefaultMessageProvider();
    public static final double negativeZero = Double.longBitsToDouble(Long.MIN_VALUE);

    public interface MessageProvider {
        String getMessage(String str, Object[] objArr);
    }

    public static boolean isJSLineTerminator(int i) {
        if ((57296 & i) != 0) {
            return false;
        }
        return i == 10 || i == 13 || i == 8232 || i == 8233;
    }

    protected ScriptRuntime() {
    }

    public static BaseFunction typeErrorThrower() {
        if (THROW_TYPE_ERROR == null) {
            AnonymousClass1 r0 = new BaseFunction() {
                static final long serialVersionUID = -5891740962154902286L;

                public int getLength() {
                    return 0;
                }

                public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
                    throw ScriptRuntime.typeError0("msg.op.not.allowed");
                }
            };
            r0.preventExtensions();
            THROW_TYPE_ERROR = r0;
        }
        return THROW_TYPE_ERROR;
    }

    static class NoSuchMethodShim implements Callable {
        String methodName;
        Callable noSuchMethodMethod;

        NoSuchMethodShim(Callable callable, String str) {
            this.noSuchMethodMethod = callable;
            this.methodName = str;
        }

        public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
            return this.noSuchMethodMethod.call(context, scriptable, scriptable2, new Object[]{this.methodName, ScriptRuntime.newArrayLiteral(objArr, (int[]) null, context, scriptable)});
        }
    }

    public static boolean isRhinoRuntimeType(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls != Character.TYPE) {
                return true;
            }
            return false;
        } else if (cls == StringClass || cls == BooleanClass || NumberClass.isAssignableFrom(cls) || ScriptableClass.isAssignableFrom(cls)) {
            return true;
        } else {
            return false;
        }
    }

    public static ScriptableObject initStandardObjects(Context context, ScriptableObject scriptableObject, boolean z) {
        if (scriptableObject == null) {
            scriptableObject = new NativeObject();
        }
        scriptableObject.associateValue(LIBRARY_SCOPE_KEY, scriptableObject);
        new ClassCache().associate(scriptableObject);
        BaseFunction.init(scriptableObject, z);
        NativeObject.init(scriptableObject, z);
        Scriptable objectPrototype = ScriptableObject.getObjectPrototype(scriptableObject);
        ScriptableObject.getClassPrototype(scriptableObject, "Function").setPrototype(objectPrototype);
        if (scriptableObject.getPrototype() == null) {
            scriptableObject.setPrototype(objectPrototype);
        }
        NativeError.init(scriptableObject, z);
        NativeGlobal.init(context, scriptableObject, z);
        NativeArray.init(scriptableObject, z);
        if (context.getOptimizationLevel() > 0) {
            NativeArray.setMaximumInitialCapacity(200000);
        }
        NativeString.init(scriptableObject, z);
        NativeBoolean.init(scriptableObject, z);
        NativeNumber.init(scriptableObject, z);
        NativeDate.init(scriptableObject, z);
        NativeMath.init(scriptableObject, z);
        NativeJSON.init(scriptableObject, z);
        NativeWith.init(scriptableObject, z);
        NativeCall.init(scriptableObject, z);
        NativeScript.init(scriptableObject, z);
        NativeIterator.init(scriptableObject, z);
        boolean z2 = context.hasFeature(6) && context.getE4xImplementationFactory() != null;
        ScriptableObject scriptableObject2 = scriptableObject;
        boolean z3 = z;
        new LazilyLoadedCtor(scriptableObject2, "RegExp", "org.mozilla.javascript.regexp.NativeRegExp", z3, true);
        new LazilyLoadedCtor(scriptableObject2, "Packages", "org.mozilla.javascript.NativeJavaTopPackage", z3, true);
        new LazilyLoadedCtor(scriptableObject2, "getClass", "org.mozilla.javascript.NativeJavaTopPackage", z3, true);
        new LazilyLoadedCtor(scriptableObject2, "JavaAdapter", "org.mozilla.javascript.JavaAdapter", z3, true);
        new LazilyLoadedCtor(scriptableObject2, "JavaImporter", "org.mozilla.javascript.ImporterTopLevel", z3, true);
        new LazilyLoadedCtor(scriptableObject2, "Continuation", "org.mozilla.javascript.NativeContinuation", z3, true);
        for (String lazilyLoadedCtor : getTopPackageNames()) {
            new LazilyLoadedCtor(scriptableObject, lazilyLoadedCtor, "org.mozilla.javascript.NativeJavaTopPackage", z, true);
        }
        if (z2) {
            ScriptableObject scriptableObject3 = scriptableObject;
            String implementationClassName = context.getE4xImplementationFactory().getImplementationClassName();
            boolean z4 = z;
            new LazilyLoadedCtor(scriptableObject3, "XML", implementationClassName, z4, true);
            new LazilyLoadedCtor(scriptableObject3, "XMLList", implementationClassName, z4, true);
            new LazilyLoadedCtor(scriptableObject3, "Namespace", implementationClassName, z4, true);
            new LazilyLoadedCtor(scriptableObject3, "QName", implementationClassName, z4, true);
        }
        if (scriptableObject instanceof TopLevel) {
            ((TopLevel) scriptableObject).cacheBuiltins();
        }
        return scriptableObject;
    }

    static String[] getTopPackageNames() {
        return PlatformDescription.DALVIK.equals(System.getProperty("java.vm.name")) ? new String[]{"java", "javax", "org", "com", "edu", "net", "android"} : new String[]{"java", "javax", "org", "com", "edu", "net"};
    }

    public static ScriptableObject getLibraryScopeOrNull(Scriptable scriptable) {
        return (ScriptableObject) ScriptableObject.getTopScopeValue(scriptable, LIBRARY_SCOPE_KEY);
    }

    public static boolean isJSWhitespaceOrLineTerminator(int i) {
        return isStrWhiteSpaceChar(i) || isJSLineTerminator(i);
    }

    static boolean isStrWhiteSpaceChar(int i) {
        if (i == 32 || i == 160 || i == 65279 || i == 8232 || i == 8233) {
            return true;
        }
        switch (i) {
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return true;
            default:
                return Character.getType(i) == 12;
        }
    }

    public static Boolean wrapBoolean(boolean z) {
        return z ? Boolean.TRUE : Boolean.FALSE;
    }

    public static Integer wrapInt(int i) {
        return Integer.valueOf(i);
    }

    public static Number wrapNumber(double d) {
        if (d != d) {
            return NaNobj;
        }
        return new Double(d);
    }

    public static boolean toBoolean(Object obj) {
        while (!(obj instanceof Boolean)) {
            if (obj == null || obj == Undefined.instance) {
                return false;
            }
            if (obj instanceof CharSequence) {
                if (((CharSequence) obj).length() != 0) {
                    return true;
                }
                return false;
            } else if (obj instanceof Number) {
                double doubleValue = ((Number) obj).doubleValue();
                if (doubleValue != doubleValue || doubleValue == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    return false;
                }
                return true;
            } else if (!(obj instanceof Scriptable)) {
                warnAboutNonJSObject(obj);
                return true;
            } else if ((obj instanceof ScriptableObject) && ((ScriptableObject) obj).avoidObjectDetection()) {
                return false;
            } else {
                if (Context.getContext().isVersionECMA1()) {
                    return true;
                }
                obj = ((Scriptable) obj).getDefaultValue(BooleanClass);
                if (obj instanceof Scriptable) {
                    throw errorWithClassName("msg.primitive.expected", obj);
                }
            }
        }
        return ((Boolean) obj).booleanValue();
    }

    public static double toNumber(Object obj) {
        while (!(obj instanceof Number)) {
            if (obj == null) {
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }
            if (obj == Undefined.instance) {
                return NaN;
            }
            if (obj instanceof String) {
                return toNumber((String) obj);
            }
            if (obj instanceof CharSequence) {
                return toNumber(obj.toString());
            }
            if (obj instanceof Boolean) {
                if (((Boolean) obj).booleanValue()) {
                    return 1.0d;
                }
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            } else if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(NumberClass);
                if (obj instanceof Scriptable) {
                    throw errorWithClassName("msg.primitive.expected", obj);
                }
            } else {
                warnAboutNonJSObject(obj);
                return NaN;
            }
        }
        return ((Number) obj).doubleValue();
    }

    public static double toNumber(Object[] objArr, int i) {
        return i < objArr.length ? toNumber(objArr[i]) : NaN;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x009c, code lost:
        if (r11 != false) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a1, code lost:
        if ((r11 & r13) != false) goto L_0x00a3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static double stringToNumber(java.lang.String r24, int r25, int r26) {
        /*
            r0 = r24
            r1 = r25
            r2 = r26
            int r3 = r24.length()
            r4 = 57
            r5 = 10
            r6 = 1
            if (r2 >= r5) goto L_0x0016
            int r7 = r2 + 48
            int r7 = r7 - r6
            char r7 = (char) r7
            goto L_0x0018
        L_0x0016:
            r7 = 57
        L_0x0018:
            r8 = 65
            r9 = 97
            if (r2 <= r5) goto L_0x0027
            int r10 = r2 + 97
            int r10 = r10 - r5
            char r10 = (char) r10
            int r11 = r2 + 65
            int r11 = r11 - r5
            char r11 = (char) r11
            goto L_0x002b
        L_0x0027:
            r10 = 97
            r11 = 65
        L_0x002b:
            r14 = r1
            r15 = 0
        L_0x002e:
            r12 = 48
            if (r14 >= r3) goto L_0x0058
            char r13 = r0.charAt(r14)
            if (r12 > r13) goto L_0x003d
            if (r13 > r7) goto L_0x003d
            int r13 = r13 + -48
            goto L_0x004c
        L_0x003d:
            if (r9 > r13) goto L_0x0045
            if (r13 >= r10) goto L_0x0045
            int r13 = r13 + -97
        L_0x0043:
            int r13 = r13 + r5
            goto L_0x004c
        L_0x0045:
            if (r8 > r13) goto L_0x0058
            if (r13 >= r11) goto L_0x0058
            int r13 = r13 + -65
            goto L_0x0043
        L_0x004c:
            double r8 = (double) r2
            double r15 = r15 * r8
            double r8 = (double) r13
            double r15 = r15 + r8
            int r14 = r14 + 1
            r8 = 65
            r9 = 97
            goto L_0x002e
        L_0x0058:
            if (r1 != r14) goto L_0x005d
            double r0 = NaN
            return r0
        L_0x005d:
            r7 = 4845873199050653696(0x4340000000000000, double:9.007199254740992E15)
            int r3 = (r15 > r7 ? 1 : (r15 == r7 ? 0 : -1))
            if (r3 < 0) goto L_0x0102
            if (r2 != r5) goto L_0x0071
            java.lang.String r0 = r0.substring(r1, r14)     // Catch:{ NumberFormatException -> 0x006e }
            double r0 = java.lang.Double.parseDouble(r0)     // Catch:{ NumberFormatException -> 0x006e }
            return r0
        L_0x006e:
            double r0 = NaN
            return r0
        L_0x0071:
            r3 = 2
            r5 = 4
            if (r2 == r3) goto L_0x0083
            if (r2 == r5) goto L_0x0083
            r7 = 8
            if (r2 == r7) goto L_0x0083
            r7 = 16
            if (r2 == r7) goto L_0x0083
            r7 = 32
            if (r2 != r7) goto L_0x0102
        L_0x0083:
            r7 = 53
            r9 = 1
            r10 = 0
            r11 = 0
            r13 = 0
            r17 = 0
            r18 = 0
        L_0x008d:
            r8 = 3
            r20 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r9 != r6) goto L_0x00cb
            if (r1 != r14) goto L_0x00ad
            if (r10 == 0) goto L_0x00a9
            if (r10 == r8) goto L_0x009f
            if (r10 == r5) goto L_0x009c
            goto L_0x0102
        L_0x009c:
            if (r11 == 0) goto L_0x00a5
            goto L_0x00a3
        L_0x009f:
            r0 = r11 & r13
            if (r0 == 0) goto L_0x00a5
        L_0x00a3:
            double r15 = r15 + r20
        L_0x00a5:
            double r12 = r15 * r18
            goto L_0x0103
        L_0x00a9:
            r12 = 0
            goto L_0x0103
        L_0x00ad:
            int r9 = r1 + 1
            char r1 = r0.charAt(r1)
            if (r12 > r1) goto L_0x00ba
            if (r1 > r4) goto L_0x00ba
            int r1 = r1 + -48
            goto L_0x00c7
        L_0x00ba:
            r4 = 97
            if (r4 > r1) goto L_0x00c5
            r4 = 122(0x7a, float:1.71E-43)
            if (r1 > r4) goto L_0x00c5
            int r1 = r1 + -87
            goto L_0x00c7
        L_0x00c5:
            int r1 = r1 + -55
        L_0x00c7:
            r17 = r1
            r1 = r9
            r9 = r2
        L_0x00cb:
            int r9 = r9 >> r6
            r4 = r17 & r9
            if (r4 == 0) goto L_0x00d2
            r4 = 1
            goto L_0x00d3
        L_0x00d2:
            r4 = 0
        L_0x00d3:
            r22 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r10 == 0) goto L_0x00f8
            if (r10 == r6) goto L_0x00eb
            if (r10 == r3) goto L_0x00e6
            if (r10 == r8) goto L_0x00e0
            if (r10 == r5) goto L_0x00e3
            goto L_0x00ff
        L_0x00e0:
            if (r4 == 0) goto L_0x00e3
            r10 = 4
        L_0x00e3:
            double r18 = r18 * r22
            goto L_0x00ff
        L_0x00e6:
            r11 = r4
            r18 = r22
            r10 = 3
            goto L_0x00ff
        L_0x00eb:
            double r15 = r15 * r22
            if (r4 == 0) goto L_0x00f1
            double r15 = r15 + r20
        L_0x00f1:
            int r7 = r7 + -1
            if (r7 != 0) goto L_0x00ff
            r13 = r4
            r10 = 2
            goto L_0x00ff
        L_0x00f8:
            if (r4 == 0) goto L_0x00ff
            int r7 = r7 + -1
            r15 = r20
            r10 = 1
        L_0x00ff:
            r4 = 57
            goto L_0x008d
        L_0x0102:
            r12 = r15
        L_0x0103:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptRuntime.stringToNumber(java.lang.String, int, int):double");
    }

    public static double toNumber(String str) {
        char charAt;
        int i;
        char charAt2;
        char charAt3;
        int length = str.length();
        int i2 = 0;
        while (i2 != length) {
            char charAt4 = str.charAt(i2);
            if (!isStrWhiteSpaceChar(charAt4)) {
                if (charAt4 == '0') {
                    int i3 = i2 + 2;
                    if (i3 < length && ((charAt3 = str.charAt(i2 + 1)) == 'x' || charAt3 == 'X')) {
                        return stringToNumber(str, i3, 16);
                    }
                } else if ((charAt4 == '+' || charAt4 == '-') && (i = i2 + 3) < length && str.charAt(i2 + 1) == '0' && ((charAt2 = str.charAt(i2 + 2)) == 'x' || charAt2 == 'X')) {
                    double stringToNumber = stringToNumber(str, i, 16);
                    return charAt4 == '-' ? -stringToNumber : stringToNumber;
                }
                while (true) {
                    length--;
                    charAt = str.charAt(length);
                    if (!isStrWhiteSpaceChar(charAt)) {
                        break;
                    }
                }
                if (charAt == 'y') {
                    if (charAt4 == '+' || charAt4 == '-') {
                        i2++;
                    }
                    if (i2 + 7 != length || !str.regionMatches(i2, "Infinity", 0, 8)) {
                        return NaN;
                    }
                    return charAt4 == '-' ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                }
                String substring = str.substring(i2, length + 1);
                for (int length2 = substring.length() - 1; length2 >= 0; length2--) {
                    char charAt5 = substring.charAt(length2);
                    if (('0' > charAt5 || charAt5 > '9') && charAt5 != '.' && charAt5 != 'e' && charAt5 != 'E' && charAt5 != '+' && charAt5 != '-') {
                        return NaN;
                    }
                }
                try {
                    return Double.parseDouble(substring);
                } catch (NumberFormatException unused) {
                    return NaN;
                }
            } else {
                i2++;
            }
        }
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static Object[] padArguments(Object[] objArr, int i) {
        if (i < objArr.length) {
            return objArr;
        }
        Object[] objArr2 = new Object[i];
        int i2 = 0;
        while (i2 < objArr.length) {
            objArr2[i2] = objArr[i2];
            i2++;
        }
        while (i2 < i) {
            objArr2[i2] = Undefined.instance;
            i2++;
        }
        return objArr2;
    }

    public static String escapeString(String str) {
        return escapeString(str, '\"');
    }

    public static String escapeString(String str, char c) {
        int i;
        if (!(c == '\"' || c == '\'')) {
            Kit.codeBug();
        }
        StringBuffer stringBuffer = null;
        int length = str.length();
        for (int i2 = 0; i2 != length; i2++) {
            char charAt = str.charAt(i2);
            int i3 = 32;
            if (' ' > charAt || charAt > '~' || charAt == c || charAt == '\\') {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(length + 3);
                    stringBuffer.append(str);
                    stringBuffer.setLength(i2);
                }
                if (charAt != ' ') {
                    if (charAt != '\\') {
                        switch (charAt) {
                            case 8:
                                i3 = 98;
                                break;
                            case 9:
                                i3 = 116;
                                break;
                            case 10:
                                i3 = 110;
                                break;
                            case 11:
                                i3 = 118;
                                break;
                            case 12:
                                i3 = 102;
                                break;
                            case 13:
                                i3 = 114;
                                break;
                            default:
                                i3 = -1;
                                break;
                        }
                    } else {
                        i3 = 92;
                    }
                }
                if (i3 >= 0) {
                    stringBuffer.append('\\');
                    stringBuffer.append((char) i3);
                } else if (charAt == c) {
                    stringBuffer.append('\\');
                    stringBuffer.append(c);
                } else {
                    if (charAt < 256) {
                        stringBuffer.append("\\x");
                        i = 2;
                    } else {
                        stringBuffer.append("\\u");
                        i = 4;
                    }
                    for (int i4 = (i - 1) * 4; i4 >= 0; i4 -= 4) {
                        int i5 = (charAt >> i4) & 15;
                        stringBuffer.append((char) (i5 < 10 ? i5 + 48 : i5 + 87));
                    }
                }
            } else if (stringBuffer != null) {
                stringBuffer.append((char) charAt);
            }
        }
        return stringBuffer == null ? str : stringBuffer.toString();
    }

    static boolean isValidIdentifierName(String str) {
        int length = str.length();
        if (length == 0 || !Character.isJavaIdentifierStart(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i != length; i++) {
            if (!Character.isJavaIdentifierPart(str.charAt(i))) {
                return false;
            }
        }
        return !TokenStream.isKeyword(str);
    }

    public static CharSequence toCharSequence(Object obj) {
        if (obj instanceof NativeString) {
            return ((NativeString) obj).toCharSequence();
        }
        return obj instanceof CharSequence ? (CharSequence) obj : toString(obj);
    }

    public static String toString(Object obj) {
        while (obj != null) {
            if (obj == Undefined.instance) {
                return AdError.UNDEFINED_DOMAIN;
            }
            if (obj instanceof String) {
                return (String) obj;
            }
            if (obj instanceof CharSequence) {
                return obj.toString();
            }
            if (obj instanceof Number) {
                return numberToString(((Number) obj).doubleValue(), 10);
            }
            if (!(obj instanceof Scriptable)) {
                return obj.toString();
            }
            obj = ((Scriptable) obj).getDefaultValue(StringClass);
            if (obj instanceof Scriptable) {
                throw errorWithClassName("msg.primitive.expected", obj);
            }
        }
        return "null";
    }

    static String defaultObjectToString(Scriptable scriptable) {
        return "[object " + scriptable.getClassName() + ']';
    }

    public static String toString(Object[] objArr, int i) {
        return i < objArr.length ? toString(objArr[i]) : AdError.UNDEFINED_DOMAIN;
    }

    public static String toString(double d) {
        return numberToString(d, 10);
    }

    public static String numberToString(double d, int i) {
        if (d != d) {
            return "NaN";
        }
        if (d == Double.POSITIVE_INFINITY) {
            return "Infinity";
        }
        if (d == Double.NEGATIVE_INFINITY) {
            return "-Infinity";
        }
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        if (i < 2 || i > 36) {
            throw Context.reportRuntimeError1("msg.bad.radix", Integer.toString(i));
        } else if (i != 10) {
            return DToA.JS_dtobasestr(i, d);
        } else {
            String numberToString = FastDtoa.numberToString(d);
            if (numberToString != null) {
                return numberToString;
            }
            StringBuilder sb = new StringBuilder();
            DToA.JS_dtostr(sb, 0, 0, d);
            return sb.toString();
        }
    }

    static String uneval(Context context, Scriptable scriptable, Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj == Undefined.instance) {
            return AdError.UNDEFINED_DOMAIN;
        }
        if (obj instanceof CharSequence) {
            String escapeString = escapeString(obj.toString());
            StringBuffer stringBuffer = new StringBuffer(escapeString.length() + 2);
            stringBuffer.append('\"');
            stringBuffer.append(escapeString);
            stringBuffer.append('\"');
            return stringBuffer.toString();
        } else if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || 1.0d / doubleValue >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return toString(doubleValue);
            }
            return "-0";
        } else if (obj instanceof Boolean) {
            return toString(obj);
        } else {
            if (obj instanceof Scriptable) {
                Scriptable scriptable2 = (Scriptable) obj;
                if (ScriptableObject.hasProperty(scriptable2, "toSource")) {
                    Object property = ScriptableObject.getProperty(scriptable2, "toSource");
                    if (property instanceof Function) {
                        return toString(((Function) property).call(context, scriptable, scriptable2, emptyArgs));
                    }
                }
                return toString(obj);
            }
            warnAboutNonJSObject(obj);
            return obj.toString();
        }
    }

    static String defaultObjectToSource(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        boolean z;
        boolean z2;
        Object obj;
        if (context.iterating == null) {
            context.iterating = new ObjToIntMap(31);
            z = true;
            z2 = false;
        } else {
            z2 = context.iterating.has(scriptable2);
            z = false;
        }
        StringBuffer stringBuffer = new StringBuffer(128);
        if (z) {
            stringBuffer.append("(");
        }
        stringBuffer.append(ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN);
        if (!z2) {
            try {
                context.iterating.intern(scriptable2);
                Object[] ids = scriptable2.getIds();
                for (int i = 0; i < ids.length; i++) {
                    Object obj2 = ids[i];
                    if (obj2 instanceof Integer) {
                        int intValue = ((Integer) obj2).intValue();
                        obj = scriptable2.get(intValue, scriptable2);
                        if (obj == Scriptable.NOT_FOUND) {
                        } else {
                            if (i > 0) {
                                stringBuffer.append(", ");
                            }
                            stringBuffer.append(intValue);
                        }
                    } else {
                        String str = (String) obj2;
                        obj = scriptable2.get(str, scriptable2);
                        if (obj == Scriptable.NOT_FOUND) {
                        } else {
                            if (i > 0) {
                                stringBuffer.append(", ");
                            }
                            if (isValidIdentifierName(str)) {
                                stringBuffer.append(str);
                            } else {
                                stringBuffer.append('\'');
                                stringBuffer.append(escapeString(str, '\''));
                                stringBuffer.append('\'');
                            }
                        }
                    }
                    stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
                    stringBuffer.append(uneval(context, scriptable, obj));
                }
            } catch (Throwable th) {
                if (z) {
                    context.iterating = null;
                }
                throw th;
            }
        }
        if (z) {
            context.iterating = null;
        }
        stringBuffer.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
        if (z) {
            stringBuffer.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        }
        return stringBuffer.toString();
    }

    public static Scriptable toObject(Scriptable scriptable, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        return toObject(Context.getContext(), scriptable, obj);
    }

    public static Scriptable toObjectOrNull(Context context, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj == null || obj == Undefined.instance) {
            return null;
        }
        return toObject(context, getTopCallScope(context), obj);
    }

    public static Scriptable toObjectOrNull(Context context, Object obj, Scriptable scriptable) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj == null || obj == Undefined.instance) {
            return null;
        }
        return toObject(context, scriptable, obj);
    }

    public static Scriptable toObject(Scriptable scriptable, Object obj, Class<?> cls) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        return toObject(Context.getContext(), scriptable, obj);
    }

    public static Scriptable toObject(Context context, Scriptable scriptable, Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        if (obj instanceof CharSequence) {
            NativeString nativeString = new NativeString((CharSequence) obj);
            setBuiltinProtoAndParent(nativeString, scriptable, TopLevel.Builtins.String);
            return nativeString;
        } else if (obj instanceof Number) {
            NativeNumber nativeNumber = new NativeNumber(((Number) obj).doubleValue());
            setBuiltinProtoAndParent(nativeNumber, scriptable, TopLevel.Builtins.Number);
            return nativeNumber;
        } else if (obj instanceof Boolean) {
            NativeBoolean nativeBoolean = new NativeBoolean(((Boolean) obj).booleanValue());
            setBuiltinProtoAndParent(nativeBoolean, scriptable, TopLevel.Builtins.Boolean);
            return nativeBoolean;
        } else if (obj == null) {
            throw typeError0("msg.null.to.object");
        } else if (obj != Undefined.instance) {
            Object wrap = context.getWrapFactory().wrap(context, scriptable, obj, (Class<?>) null);
            if (wrap instanceof Scriptable) {
                return (Scriptable) wrap;
            }
            throw errorWithClassName("msg.invalid.type", obj);
        } else {
            throw typeError0("msg.undef.to.object");
        }
    }

    public static Scriptable toObject(Context context, Scriptable scriptable, Object obj, Class<?> cls) {
        return toObject(context, scriptable, obj);
    }

    public static Object call(Context context, Object obj, Object obj2, Object[] objArr, Scriptable scriptable) {
        if (obj instanceof Function) {
            Function function = (Function) obj;
            Scriptable objectOrNull = toObjectOrNull(context, obj2);
            if (objectOrNull != null) {
                return function.call(context, scriptable, objectOrNull, objArr);
            }
            throw undefCallError(objectOrNull, "function");
        }
        throw notFunctionError(toString(obj));
    }

    public static Scriptable newObject(Context context, Scriptable scriptable, String str, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        Function existingCtor = getExistingCtor(context, topLevelScope, str);
        if (objArr == null) {
            objArr = emptyArgs;
        }
        return existingCtor.construct(context, topLevelScope, objArr);
    }

    public static Scriptable newBuiltinObject(Context context, Scriptable scriptable, TopLevel.Builtins builtins, Object[] objArr) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        Function builtinCtor = TopLevel.getBuiltinCtor(context, topLevelScope, builtins);
        if (objArr == null) {
            objArr = emptyArgs;
        }
        return builtinCtor.construct(context, topLevelScope, objArr);
    }

    public static double toInteger(Object obj) {
        return toInteger(toNumber(obj));
    }

    public static double toInteger(double d) {
        if (d != d) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        int i = (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0 : -1));
        if (i == 0 || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY) {
            return d;
        }
        if (i > 0) {
            return Math.floor(d);
        }
        return Math.ceil(d);
    }

    public static double toInteger(Object[] objArr, int i) {
        return i < objArr.length ? toInteger(objArr[i]) : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static int toInt32(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return toInt32(toNumber(obj));
    }

    public static int toInt32(Object[] objArr, int i) {
        if (i < objArr.length) {
            return toInt32(objArr[i]);
        }
        return 0;
    }

    public static int toInt32(double d) {
        int i = (int) d;
        if (((double) i) == d) {
            return i;
        }
        if (d != d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY) {
            return 0;
        }
        return (int) ((long) Math.IEEEremainder(d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.floor(d) : Math.ceil(d), 4.294967296E9d));
    }

    public static long toUint32(double d) {
        long j = (long) d;
        if (((double) j) == d) {
            return j & 4294967295L;
        }
        if (d != d || d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY) {
            return 0;
        }
        return ((long) Math.IEEEremainder(d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.floor(d) : Math.ceil(d), 4.294967296E9d)) & 4294967295L;
    }

    public static long toUint32(Object obj) {
        return toUint32(toNumber(obj));
    }

    public static char toUint16(Object obj) {
        double number = toNumber(obj);
        int i = (int) number;
        if (((double) i) == number) {
            return (char) i;
        }
        if (number != number || number == Double.POSITIVE_INFINITY || number == Double.NEGATIVE_INFINITY) {
            return 0;
        }
        return (char) ((int) Math.IEEEremainder(number >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Math.floor(number) : Math.ceil(number), (double) 65536));
    }

    public static Object setDefaultNamespace(Object obj, Context context) {
        Scriptable scriptable = context.currentActivationCall;
        if (scriptable == null) {
            scriptable = getTopCallScope(context);
        }
        Object defaultXmlNamespace = currentXMLLib(context).toDefaultXmlNamespace(context, obj);
        if (!scriptable.has(DEFAULT_NS_TAG, scriptable)) {
            ScriptableObject.defineProperty(scriptable, DEFAULT_NS_TAG, defaultXmlNamespace, 6);
        } else {
            scriptable.put(DEFAULT_NS_TAG, scriptable, defaultXmlNamespace);
        }
        return Undefined.instance;
    }

    public static Object searchDefaultNamespace(Context context) {
        Scriptable scriptable = context.currentActivationCall;
        if (scriptable == null) {
            scriptable = getTopCallScope(context);
        }
        while (true) {
            Scriptable parentScope = scriptable.getParentScope();
            if (parentScope == null) {
                Object property = ScriptableObject.getProperty(scriptable, DEFAULT_NS_TAG);
                if (property == Scriptable.NOT_FOUND) {
                    return null;
                }
                return property;
            }
            Object obj = scriptable.get(DEFAULT_NS_TAG, scriptable);
            if (obj != Scriptable.NOT_FOUND) {
                return obj;
            }
            scriptable = parentScope;
        }
    }

    public static Object getTopLevelProp(Scriptable scriptable, String str) {
        return ScriptableObject.getProperty(ScriptableObject.getTopLevelScope(scriptable), str);
    }

    static Function getExistingCtor(Context context, Scriptable scriptable, String str) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property instanceof Function) {
            return (Function) property;
        }
        if (property == Scriptable.NOT_FOUND) {
            throw Context.reportRuntimeError1("msg.ctor.not.found", str);
        }
        throw Context.reportRuntimeError1("msg.not.ctor", str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005a, code lost:
        if (r4 <= (r5 ? 8 : 7)) goto L_0x005c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long indexFromString(java.lang.String r12) {
        /*
            int r0 = r12.length()
            r1 = -1
            if (r0 <= 0) goto L_0x0068
            r3 = 0
            char r4 = r12.charAt(r3)
            r5 = 45
            r6 = 48
            r7 = 1
            if (r4 != r5) goto L_0x0020
            if (r0 <= r7) goto L_0x0020
            char r4 = r12.charAt(r7)
            if (r4 != r6) goto L_0x001d
            return r1
        L_0x001d:
            r5 = 1
            r8 = 1
            goto L_0x0022
        L_0x0020:
            r5 = 0
            r8 = 0
        L_0x0022:
            int r4 = r4 + -48
            if (r4 < 0) goto L_0x0068
            r9 = 9
            if (r4 > r9) goto L_0x0068
            if (r5 == 0) goto L_0x002f
            r10 = 11
            goto L_0x0031
        L_0x002f:
            r10 = 10
        L_0x0031:
            if (r0 > r10) goto L_0x0068
            int r10 = -r4
            int r8 = r8 + r7
            if (r10 == 0) goto L_0x004b
        L_0x0037:
            if (r8 == r0) goto L_0x004b
            char r4 = r12.charAt(r8)
            int r4 = r4 - r6
            if (r4 < 0) goto L_0x004b
            if (r4 > r9) goto L_0x004b
            int r3 = r10 * 10
            int r3 = r3 - r4
            int r8 = r8 + 1
            r11 = r10
            r10 = r3
            r3 = r11
            goto L_0x0037
        L_0x004b:
            if (r8 != r0) goto L_0x0068
            r12 = -214748364(0xfffffffff3333334, float:-1.4197688E31)
            if (r3 > r12) goto L_0x005c
            if (r3 != r12) goto L_0x0068
            if (r5 == 0) goto L_0x0059
            r12 = 8
            goto L_0x005a
        L_0x0059:
            r12 = 7
        L_0x005a:
            if (r4 > r12) goto L_0x0068
        L_0x005c:
            r0 = 4294967295(0xffffffff, double:2.1219957905E-314)
            if (r5 == 0) goto L_0x0064
            goto L_0x0065
        L_0x0064:
            int r10 = -r10
        L_0x0065:
            long r2 = (long) r10
            long r0 = r0 & r2
            return r0
        L_0x0068:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptRuntime.indexFromString(java.lang.String):long");
    }

    public static long testUint32String(String str) {
        int length = str.length();
        if (1 <= length && length <= 10) {
            int charAt = str.charAt(0) - '0';
            if (charAt == 0) {
                if (length == 1) {
                    return 0;
                }
                return -1;
            } else if (1 <= charAt && charAt <= 9) {
                long j = (long) charAt;
                for (int i = 1; i != length; i++) {
                    int charAt2 = str.charAt(i) - '0';
                    if (charAt2 < 0 || charAt2 > 9) {
                        return -1;
                    }
                    j = (j * 10) + ((long) charAt2);
                }
                if ((j >>> 32) == 0) {
                    return j;
                }
            }
        }
        return -1;
    }

    static Object getIndexObject(String str) {
        long indexFromString = indexFromString(str);
        return indexFromString >= 0 ? Integer.valueOf((int) indexFromString) : str;
    }

    static Object getIndexObject(double d) {
        int i = (int) d;
        if (((double) i) == d) {
            return Integer.valueOf(i);
        }
        return toString(d);
    }

    static String toStringIdOrIndex(Context context, Object obj) {
        String str;
        if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            int i = (int) doubleValue;
            if (((double) i) != doubleValue) {
                return toString(obj);
            }
            storeIndexResult(context, i);
            return null;
        }
        if (obj instanceof String) {
            str = (String) obj;
        } else {
            str = toString(obj);
        }
        long indexFromString = indexFromString(str);
        if (indexFromString < 0) {
            return str;
        }
        storeIndexResult(context, (int) indexFromString);
        return null;
    }

    public static Object getObjectElem(Object obj, Object obj2, Context context) {
        return getObjectElem(obj, obj2, context, getTopCallScope(context));
    }

    public static Object getObjectElem(Object obj, Object obj2, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull != null) {
            return getObjectElem(objectOrNull, obj2, context);
        }
        throw undefReadError(obj, obj2);
    }

    public static Object getObjectElem(Scriptable scriptable, Object obj, Context context) {
        Object obj2;
        if (scriptable instanceof XMLObject) {
            obj2 = ((XMLObject) scriptable).get(context, obj);
        } else {
            String stringIdOrIndex = toStringIdOrIndex(context, obj);
            if (stringIdOrIndex == null) {
                obj2 = ScriptableObject.getProperty(scriptable, lastIndexResult(context));
            } else {
                obj2 = ScriptableObject.getProperty(scriptable, stringIdOrIndex);
            }
        }
        return obj2 == Scriptable.NOT_FOUND ? Undefined.instance : obj2;
    }

    public static Object getObjectProp(Object obj, String str, Context context) {
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            return getObjectProp(objectOrNull, str, context);
        }
        throw undefReadError(obj, str);
    }

    public static Object getObjectProp(Object obj, String str, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj, scriptable);
        if (objectOrNull != null) {
            return getObjectProp(objectOrNull, str, context);
        }
        throw undefReadError(obj, str);
    }

    public static Object getObjectProp(Scriptable scriptable, String str, Context context) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property != Scriptable.NOT_FOUND) {
            return property;
        }
        if (context.hasFeature(11)) {
            Context.reportWarning(getMessage1("msg.ref.undefined.prop", str));
        }
        return Undefined.instance;
    }

    public static Object getObjectPropNoWarn(Object obj, String str, Context context) {
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            Object property = ScriptableObject.getProperty(objectOrNull, str);
            return property == Scriptable.NOT_FOUND ? Undefined.instance : property;
        }
        throw undefReadError(obj, str);
    }

    public static Object getObjectIndex(Object obj, double d, Context context) {
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            int i = (int) d;
            if (((double) i) == d) {
                return getObjectIndex(objectOrNull, i, context);
            }
            return getObjectProp(objectOrNull, toString(d), context);
        }
        throw undefReadError(obj, toString(d));
    }

    public static Object getObjectIndex(Scriptable scriptable, int i, Context context) {
        Object property = ScriptableObject.getProperty(scriptable, i);
        return property == Scriptable.NOT_FOUND ? Undefined.instance : property;
    }

    public static Object setObjectElem(Object obj, Object obj2, Object obj3, Context context) {
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            return setObjectElem(objectOrNull, obj2, obj3, context);
        }
        throw undefWriteError(obj, obj2, obj3);
    }

    public static Object setObjectElem(Scriptable scriptable, Object obj, Object obj2, Context context) {
        if (scriptable instanceof XMLObject) {
            ((XMLObject) scriptable).put(context, obj, obj2);
        } else {
            String stringIdOrIndex = toStringIdOrIndex(context, obj);
            if (stringIdOrIndex == null) {
                ScriptableObject.putProperty(scriptable, lastIndexResult(context), obj2);
            } else {
                ScriptableObject.putProperty(scriptable, stringIdOrIndex, obj2);
            }
        }
        return obj2;
    }

    public static Object setObjectProp(Object obj, String str, Object obj2, Context context) {
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            return setObjectProp(objectOrNull, str, obj2, context);
        }
        throw undefWriteError(obj, str, obj2);
    }

    public static Object setObjectProp(Scriptable scriptable, String str, Object obj, Context context) {
        ScriptableObject.putProperty(scriptable, str, obj);
        return obj;
    }

    public static Object setObjectIndex(Object obj, double d, Object obj2, Context context) {
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            int i = (int) d;
            if (((double) i) == d) {
                return setObjectIndex(objectOrNull, i, obj2, context);
            }
            return setObjectProp(objectOrNull, toString(d), obj2, context);
        }
        throw undefWriteError(obj, String.valueOf(d), obj2);
    }

    public static Object setObjectIndex(Scriptable scriptable, int i, Object obj, Context context) {
        ScriptableObject.putProperty(scriptable, i, obj);
        return obj;
    }

    public static boolean deleteObjectElem(Scriptable scriptable, Object obj, Context context) {
        String stringIdOrIndex = toStringIdOrIndex(context, obj);
        if (stringIdOrIndex == null) {
            int lastIndexResult = lastIndexResult(context);
            scriptable.delete(lastIndexResult);
            return !scriptable.has(lastIndexResult, scriptable);
        }
        scriptable.delete(stringIdOrIndex);
        return !scriptable.has(stringIdOrIndex, scriptable);
    }

    public static boolean hasObjectElem(Scriptable scriptable, Object obj, Context context) {
        String stringIdOrIndex = toStringIdOrIndex(context, obj);
        if (stringIdOrIndex == null) {
            return ScriptableObject.hasProperty(scriptable, lastIndexResult(context));
        }
        return ScriptableObject.hasProperty(scriptable, stringIdOrIndex);
    }

    public static Object refGet(Ref ref, Context context) {
        return ref.get(context);
    }

    public static Object refSet(Ref ref, Object obj, Context context) {
        return ref.set(context, obj);
    }

    public static Object refDel(Ref ref, Context context) {
        return wrapBoolean(ref.delete(context));
    }

    static boolean isSpecialProperty(String str) {
        return str.equals("__proto__") || str.equals("__parent__");
    }

    public static Ref specialRef(Object obj, String str, Context context) {
        return SpecialRef.createSpecial(context, obj, str);
    }

    public static Object delete(Object obj, Object obj2, Context context) {
        return delete(obj, obj2, context, false);
    }

    public static Object delete(Object obj, Object obj2, Context context, boolean z) {
        String str;
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            return wrapBoolean(deleteObjectElem(objectOrNull, obj2, context));
        }
        if (z) {
            return Boolean.TRUE;
        }
        if (obj2 == null) {
            str = "null";
        } else {
            str = obj2.toString();
        }
        throw typeError2("msg.undef.prop.delete", toString(obj), str);
    }

    public static Object name(Context context, Scriptable scriptable, String str) {
        Scriptable parentScope = scriptable.getParentScope();
        if (parentScope != null) {
            return nameOrFunction(context, scriptable, parentScope, str, false);
        }
        Object obj = topScopeName(context, scriptable, str);
        if (obj != Scriptable.NOT_FOUND) {
            return obj;
        }
        throw notFoundError(scriptable, str);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: org.mozilla.javascript.xml.XMLObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v6, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: org.mozilla.javascript.xml.XMLObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: org.mozilla.javascript.xml.XMLObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: org.mozilla.javascript.xml.XMLObject} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: org.mozilla.javascript.Scriptable} */
    /* JADX WARNING: type inference failed for: r1v2, types: [org.mozilla.javascript.Scriptable] */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0046, code lost:
        r6 = r2;
        r1 = r1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0075 A[LOOP:0: B:1:0x0002->B:39:0x0075, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x004e A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object nameOrFunction(org.mozilla.javascript.Context r5, org.mozilla.javascript.Scriptable r6, org.mozilla.javascript.Scriptable r7, java.lang.String r8, boolean r9) {
        /*
            r0 = 0
            r1 = r6
        L_0x0002:
            boolean r2 = r1 instanceof org.mozilla.javascript.NativeWith
            if (r2 == 0) goto L_0x0028
            org.mozilla.javascript.Scriptable r1 = r1.getPrototype()
            boolean r2 = r1 instanceof org.mozilla.javascript.xml.XMLObject
            if (r2 == 0) goto L_0x001f
            org.mozilla.javascript.xml.XMLObject r1 = (org.mozilla.javascript.xml.XMLObject) r1
            boolean r2 = r1.has(r8, r1)
            if (r2 == 0) goto L_0x001b
            java.lang.Object r6 = r1.get(r8, r1)
            goto L_0x0065
        L_0x001b:
            if (r0 != 0) goto L_0x0048
            r0 = r1
            goto L_0x0048
        L_0x001f:
            java.lang.Object r2 = org.mozilla.javascript.ScriptableObject.getProperty((org.mozilla.javascript.Scriptable) r1, (java.lang.String) r8)
            java.lang.Object r3 = org.mozilla.javascript.Scriptable.NOT_FOUND
            if (r2 == r3) goto L_0x0048
            goto L_0x0046
        L_0x0028:
            boolean r2 = r1 instanceof org.mozilla.javascript.NativeCall
            if (r2 == 0) goto L_0x003e
            java.lang.Object r1 = r1.get((java.lang.String) r8, (org.mozilla.javascript.Scriptable) r1)
            java.lang.Object r2 = org.mozilla.javascript.Scriptable.NOT_FOUND
            if (r1 == r2) goto L_0x0048
            if (r9 == 0) goto L_0x003a
            org.mozilla.javascript.Scriptable r6 = org.mozilla.javascript.ScriptableObject.getTopLevelScope(r7)
        L_0x003a:
            r4 = r1
            r1 = r6
            r6 = r4
            goto L_0x0065
        L_0x003e:
            java.lang.Object r2 = org.mozilla.javascript.ScriptableObject.getProperty((org.mozilla.javascript.Scriptable) r1, (java.lang.String) r8)
            java.lang.Object r3 = org.mozilla.javascript.Scriptable.NOT_FOUND
            if (r2 == r3) goto L_0x0048
        L_0x0046:
            r6 = r2
            goto L_0x0065
        L_0x0048:
            org.mozilla.javascript.Scriptable r1 = r7.getParentScope()
            if (r1 != 0) goto L_0x0075
            java.lang.Object r6 = topScopeName(r5, r7, r8)
            java.lang.Object r1 = org.mozilla.javascript.Scriptable.NOT_FOUND
            if (r6 != r1) goto L_0x0064
            if (r0 == 0) goto L_0x005f
            if (r9 != 0) goto L_0x005f
            java.lang.Object r6 = r0.get(r8, r0)
            goto L_0x0064
        L_0x005f:
            java.lang.RuntimeException r5 = notFoundError(r7, r8)
            throw r5
        L_0x0064:
            r1 = r7
        L_0x0065:
            if (r9 == 0) goto L_0x0074
            boolean r7 = r6 instanceof org.mozilla.javascript.Callable
            if (r7 == 0) goto L_0x006f
            storeScriptable(r5, r1)
            goto L_0x0074
        L_0x006f:
            java.lang.RuntimeException r5 = notFunctionError(r6, r8)
            throw r5
        L_0x0074:
            return r6
        L_0x0075:
            r4 = r1
            r1 = r7
            r7 = r4
            goto L_0x0002
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptRuntime.nameOrFunction(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.String, boolean):java.lang.Object");
    }

    private static Object topScopeName(Context context, Scriptable scriptable, String str) {
        if (context.useDynamicScope) {
            scriptable = checkDynamicScope(context.topCallScope, scriptable);
        }
        return ScriptableObject.getProperty(scriptable, str);
    }

    public static Scriptable bind(Context context, Scriptable scriptable, String str) {
        Scriptable parentScope = scriptable.getParentScope();
        XMLObject xMLObject = null;
        if (parentScope != null) {
            XMLObject xMLObject2 = null;
            while (true) {
                if (scriptable instanceof NativeWith) {
                    Scriptable prototype = scriptable.getPrototype();
                    if (prototype instanceof XMLObject) {
                        XMLObject xMLObject3 = (XMLObject) prototype;
                        if (xMLObject3.has(context, str)) {
                            return xMLObject3;
                        }
                        if (xMLObject2 == null) {
                            xMLObject2 = xMLObject3;
                        }
                    } else if (ScriptableObject.hasProperty(prototype, str)) {
                        return prototype;
                    }
                    Scriptable parentScope2 = parentScope.getParentScope();
                    if (parentScope2 == null) {
                        break;
                    }
                    Scriptable scriptable2 = parentScope;
                    parentScope = parentScope2;
                    scriptable = scriptable2;
                } else {
                    while (!ScriptableObject.hasProperty(scriptable, str)) {
                        Scriptable parentScope3 = parentScope.getParentScope();
                        if (parentScope3 != null) {
                            Scriptable scriptable3 = parentScope;
                            parentScope = parentScope3;
                            scriptable = scriptable3;
                        }
                    }
                    return scriptable;
                }
            }
            scriptable = parentScope;
            xMLObject = xMLObject2;
        }
        if (context.useDynamicScope) {
            scriptable = checkDynamicScope(context.topCallScope, scriptable);
        }
        return ScriptableObject.hasProperty(scriptable, str) ? scriptable : xMLObject;
    }

    public static Object setName(Scriptable scriptable, Object obj, Context context, Scriptable scriptable2, String str) {
        if (scriptable != null) {
            ScriptableObject.putProperty(scriptable, str, obj);
        } else {
            if (context.hasFeature(11) || context.hasFeature(8)) {
                Context.reportWarning(getMessage1("msg.assn.create.strict", str));
            }
            Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable2);
            if (context.useDynamicScope) {
                topLevelScope = checkDynamicScope(context.topCallScope, topLevelScope);
            }
            topLevelScope.put(str, topLevelScope, obj);
        }
        return obj;
    }

    public static Object strictSetName(Scriptable scriptable, Object obj, Context context, Scriptable scriptable2, String str) {
        if (scriptable != null) {
            ScriptableObject.putProperty(scriptable, str, obj);
            return obj;
        }
        throw constructError("ReferenceError", "Assignment to undefined \"" + str + "\" in strict mode");
    }

    public static Object setConst(Scriptable scriptable, Object obj, Context context, String str) {
        if (scriptable instanceof XMLObject) {
            scriptable.put(str, scriptable, obj);
        } else {
            ScriptableObject.putConstProperty(scriptable, str, obj);
        }
        return obj;
    }

    private static class IdEnumeration implements Serializable {
        private static final long serialVersionUID = 1;
        Object currentId;
        boolean enumNumbers;
        int enumType;
        Object[] ids;
        int index;
        Scriptable iterator;
        Scriptable obj;
        ObjToIntMap used;

        private IdEnumeration() {
        }
    }

    public static Scriptable toIterator(Context context, Scriptable scriptable, Scriptable scriptable2, boolean z) {
        if (!ScriptableObject.hasProperty(scriptable2, NativeIterator.ITERATOR_PROPERTY_NAME)) {
            return null;
        }
        Object property = ScriptableObject.getProperty(scriptable2, NativeIterator.ITERATOR_PROPERTY_NAME);
        if (property instanceof Callable) {
            Callable callable = (Callable) property;
            Object[] objArr = new Object[1];
            objArr[0] = z ? Boolean.TRUE : Boolean.FALSE;
            Object call = callable.call(context, scriptable, scriptable2, objArr);
            if (call instanceof Scriptable) {
                return (Scriptable) call;
            }
            throw typeError0("msg.iterator.primitive");
        }
        throw typeError0("msg.invalid.iterator");
    }

    public static Object enumInit(Object obj, Context context, boolean z) {
        return enumInit(obj, context, z ? 1 : 0);
    }

    public static Object enumInit(Object obj, Context context, int i) {
        IdEnumeration idEnumeration = new IdEnumeration();
        idEnumeration.obj = toObjectOrNull(context, obj);
        if (idEnumeration.obj == null) {
            return idEnumeration;
        }
        idEnumeration.enumType = i;
        idEnumeration.iterator = null;
        if (!(i == 3 || i == 4 || i == 5)) {
            idEnumeration.iterator = toIterator(context, idEnumeration.obj.getParentScope(), idEnumeration.obj, i == 0);
        }
        if (idEnumeration.iterator == null) {
            enumChangeObject(idEnumeration);
        }
        return idEnumeration;
    }

    public static void setEnumNumbers(Object obj, boolean z) {
        ((IdEnumeration) obj).enumNumbers = z;
    }

    public static Boolean enumNext(Object obj) {
        IdEnumeration idEnumeration = (IdEnumeration) obj;
        if (idEnumeration.iterator != null) {
            Object property = ScriptableObject.getProperty(idEnumeration.iterator, JSONAPISpecConstants.NEXT);
            if (!(property instanceof Callable)) {
                return Boolean.FALSE;
            }
            try {
                idEnumeration.currentId = ((Callable) property).call(Context.getContext(), idEnumeration.iterator.getParentScope(), idEnumeration.iterator, emptyArgs);
                return Boolean.TRUE;
            } catch (JavaScriptException e) {
                if (e.getValue() instanceof NativeIterator.StopIteration) {
                    return Boolean.FALSE;
                }
                throw e;
            }
        } else {
            while (idEnumeration.obj != null) {
                if (idEnumeration.index == idEnumeration.ids.length) {
                    idEnumeration.obj = idEnumeration.obj.getPrototype();
                    enumChangeObject(idEnumeration);
                } else {
                    Object[] objArr = idEnumeration.ids;
                    int i = idEnumeration.index;
                    idEnumeration.index = i + 1;
                    Object obj2 = objArr[i];
                    if (idEnumeration.used == null || !idEnumeration.used.has(obj2)) {
                        if (obj2 instanceof String) {
                            String str = (String) obj2;
                            if (idEnumeration.obj.has(str, idEnumeration.obj)) {
                                idEnumeration.currentId = str;
                            }
                        } else {
                            int intValue = ((Number) obj2).intValue();
                            if (idEnumeration.obj.has(intValue, idEnumeration.obj)) {
                                idEnumeration.currentId = idEnumeration.enumNumbers ? Integer.valueOf(intValue) : String.valueOf(intValue);
                            }
                        }
                        return Boolean.TRUE;
                    }
                }
            }
            return Boolean.FALSE;
        }
    }

    public static Object enumId(Object obj, Context context) {
        IdEnumeration idEnumeration = (IdEnumeration) obj;
        if (idEnumeration.iterator != null) {
            return idEnumeration.currentId;
        }
        int i = idEnumeration.enumType;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                throw Kit.codeBug();
                            }
                        }
                    }
                }
                return context.newArray(ScriptableObject.getTopLevelScope(idEnumeration.obj), new Object[]{idEnumeration.currentId, enumValue(obj, context)});
            }
            return enumValue(obj, context);
        }
        return idEnumeration.currentId;
    }

    public static Object enumValue(Object obj, Context context) {
        IdEnumeration idEnumeration = (IdEnumeration) obj;
        String stringIdOrIndex = toStringIdOrIndex(context, idEnumeration.currentId);
        if (stringIdOrIndex != null) {
            return idEnumeration.obj.get(stringIdOrIndex, idEnumeration.obj);
        }
        return idEnumeration.obj.get(lastIndexResult(context), idEnumeration.obj);
    }

    private static void enumChangeObject(IdEnumeration idEnumeration) {
        Object[] objArr = null;
        while (idEnumeration.obj != null) {
            objArr = idEnumeration.obj.getIds();
            if (objArr.length != 0) {
                break;
            }
            idEnumeration.obj = idEnumeration.obj.getPrototype();
        }
        if (!(idEnumeration.obj == null || idEnumeration.ids == null)) {
            Object[] objArr2 = idEnumeration.ids;
            int length = objArr2.length;
            if (idEnumeration.used == null) {
                idEnumeration.used = new ObjToIntMap(length);
            }
            for (int i = 0; i != length; i++) {
                idEnumeration.used.intern(objArr2[i]);
            }
        }
        idEnumeration.ids = objArr;
        idEnumeration.index = 0;
    }

    public static Callable getNameFunctionAndThis(String str, Context context, Scriptable scriptable) {
        Scriptable parentScope = scriptable.getParentScope();
        if (parentScope != null) {
            return (Callable) nameOrFunction(context, scriptable, parentScope, str, true);
        }
        Object obj = topScopeName(context, scriptable, str);
        if (obj instanceof Callable) {
            storeScriptable(context, scriptable);
            return (Callable) obj;
        } else if (obj == Scriptable.NOT_FOUND) {
            throw notFoundError(scriptable, str);
        } else {
            throw notFunctionError(obj, str);
        }
    }

    public static Callable getElemFunctionAndThis(Object obj, Object obj2, Context context) {
        String stringIdOrIndex = toStringIdOrIndex(context, obj2);
        if (stringIdOrIndex != null) {
            return getPropFunctionAndThis(obj, stringIdOrIndex, context);
        }
        int lastIndexResult = lastIndexResult(context);
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            Object property = ScriptableObject.getProperty(objectOrNull, lastIndexResult);
            if (property instanceof Callable) {
                storeScriptable(context, objectOrNull);
                return (Callable) property;
            }
            throw notFunctionError(property, obj2);
        }
        throw undefCallError(obj, String.valueOf(lastIndexResult));
    }

    public static Callable getPropFunctionAndThis(Object obj, String str, Context context) {
        return getPropFunctionAndThisHelper(obj, str, context, toObjectOrNull(context, obj));
    }

    public static Callable getPropFunctionAndThis(Object obj, String str, Context context, Scriptable scriptable) {
        return getPropFunctionAndThisHelper(obj, str, context, toObjectOrNull(context, obj, scriptable));
    }

    private static Callable getPropFunctionAndThisHelper(Object obj, String str, Context context, Scriptable scriptable) {
        if (scriptable != null) {
            Object property = ScriptableObject.getProperty(scriptable, str);
            if (!(property instanceof Callable)) {
                Object property2 = ScriptableObject.getProperty(scriptable, "__noSuchMethod__");
                if (property2 instanceof Callable) {
                    property = new NoSuchMethodShim((Callable) property2, str);
                }
            }
            if (property instanceof Callable) {
                storeScriptable(context, scriptable);
                return (Callable) property;
            }
            throw notFunctionError(scriptable, property, str);
        }
        throw undefCallError(obj, str);
    }

    public static Callable getValueFunctionAndThis(Object obj, Context context) {
        if (obj instanceof Callable) {
            Callable callable = (Callable) obj;
            Scriptable scriptable = null;
            if (callable instanceof Scriptable) {
                scriptable = ((Scriptable) callable).getParentScope();
            }
            if (scriptable == null) {
                if (context.topCallScope != null) {
                    scriptable = context.topCallScope;
                } else {
                    throw new IllegalStateException();
                }
            }
            if (scriptable.getParentScope() != null && !(scriptable instanceof NativeWith) && (scriptable instanceof NativeCall)) {
                scriptable = ScriptableObject.getTopLevelScope(scriptable);
            }
            storeScriptable(context, scriptable);
            return callable;
        }
        throw notFunctionError(obj);
    }

    public static Ref callRef(Callable callable, Scriptable scriptable, Object[] objArr, Context context) {
        if (callable instanceof RefCallable) {
            RefCallable refCallable = (RefCallable) callable;
            Ref refCall = refCallable.refCall(context, scriptable, objArr);
            if (refCall != null) {
                return refCall;
            }
            throw new IllegalStateException(refCallable.getClass().getName() + ".refCall() returned null");
        }
        throw constructError("ReferenceError", getMessage1("msg.no.ref.from.function", toString((Object) callable)));
    }

    public static Scriptable newObject(Object obj, Context context, Scriptable scriptable, Object[] objArr) {
        if (obj instanceof Function) {
            return ((Function) obj).construct(context, scriptable, objArr);
        }
        throw notFunctionError(obj);
    }

    public static Object callSpecial(Context context, Callable callable, Scriptable scriptable, Object[] objArr, Scriptable scriptable2, Scriptable scriptable3, int i, String str, int i2) {
        if (i == 1) {
            if (scriptable.getParentScope() == null && NativeGlobal.isEvalFunction(callable)) {
                return evalSpecial(context, scriptable2, scriptable3, objArr, str, i2);
            }
        } else if (i != 2) {
            throw Kit.codeBug();
        } else if (NativeWith.isWithFunction(callable)) {
            throw Context.reportRuntimeError1("msg.only.from.new", "With");
        }
        return callable.call(context, scriptable2, scriptable, objArr);
    }

    public static Object newSpecial(Context context, Object obj, Object[] objArr, Scriptable scriptable, int i) {
        if (i == 1) {
            if (NativeGlobal.isEvalFunction(obj)) {
                throw typeError1("msg.not.ctor", "eval");
            }
        } else if (i != 2) {
            throw Kit.codeBug();
        } else if (NativeWith.isWithFunction(obj)) {
            return NativeWith.newWithSpecial(context, scriptable, objArr);
        }
        return newObject(obj, context, scriptable, objArr);
    }

    public static Object applyOrCall(boolean z, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object[] objArr2;
        int length = objArr.length;
        Callable callable = getCallable(scriptable2);
        Scriptable objectOrNull = length != 0 ? toObjectOrNull(context, objArr[0]) : null;
        if (objectOrNull == null) {
            objectOrNull = getTopCallScope(context);
        }
        if (z) {
            objArr2 = length <= 1 ? emptyArgs : getApplyArguments(context, objArr[1]);
        } else if (length <= 1) {
            objArr2 = emptyArgs;
        } else {
            int i = length - 1;
            objArr2 = new Object[i];
            System.arraycopy(objArr, 1, objArr2, 0, i);
        }
        return callable.call(context, scriptable, objectOrNull, objArr2);
    }

    static Object[] getApplyArguments(Context context, Object obj) {
        if (obj == null || obj == Undefined.instance) {
            return emptyArgs;
        }
        if ((obj instanceof NativeArray) || (obj instanceof Arguments)) {
            return context.getElements((Scriptable) obj);
        }
        throw typeError0("msg.arg.isnt.array");
    }

    static Callable getCallable(Scriptable scriptable) {
        if (scriptable instanceof Callable) {
            return (Callable) scriptable;
        }
        Object defaultValue = scriptable.getDefaultValue(FunctionClass);
        if (defaultValue instanceof Callable) {
            return (Callable) defaultValue;
        }
        throw notFunctionError(defaultValue, scriptable);
    }

    public static Object evalSpecial(Context context, Scriptable scriptable, Object obj, Object[] objArr, String str, int i) {
        if (objArr.length < 1) {
            return Undefined.instance;
        }
        Object obj2 = objArr[0];
        if (obj2 instanceof CharSequence) {
            if (str == null) {
                int[] iArr = new int[1];
                String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
                if (sourcePositionFromStack != null) {
                    i = iArr[0];
                    str = sourcePositionFromStack;
                } else {
                    str = "";
                }
            }
            String makeUrlForGeneratedScript = makeUrlForGeneratedScript(true, str, i);
            ErrorReporter forEval = DefaultErrorReporter.forEval(context.getErrorReporter());
            Evaluator createInterpreter = Context.createInterpreter();
            if (createInterpreter != null) {
                Script compileString = context.compileString(obj2.toString(), createInterpreter, forEval, makeUrlForGeneratedScript, 1, (Object) null);
                createInterpreter.setEvalScriptFlag(compileString);
                return ((Callable) compileString).call(context, scriptable, (Scriptable) obj, emptyArgs);
            }
            throw new JavaScriptException("Interpreter not present", str, i);
        } else if (context.hasFeature(11) || context.hasFeature(9)) {
            throw Context.reportRuntimeError0("msg.eval.nonstring.strict");
        } else {
            Context.reportWarning(getMessage0("msg.eval.nonstring"));
            return obj2;
        }
    }

    public static String typeof(Object obj) {
        if (obj == null) {
            return SlideshowPageFragment.ARG_OBJECT;
        }
        if (obj == Undefined.instance) {
            return AdError.UNDEFINED_DOMAIN;
        }
        if (obj instanceof ScriptableObject) {
            return ((ScriptableObject) obj).getTypeOf();
        }
        if (obj instanceof Scriptable) {
            if (obj instanceof Callable) {
                return "function";
            }
            return SlideshowPageFragment.ARG_OBJECT;
        } else if (obj instanceof CharSequence) {
            return "string";
        } else {
            if (obj instanceof Number) {
                return "number";
            }
            if (obj instanceof Boolean) {
                return "boolean";
            }
            throw errorWithClassName("msg.invalid.type", obj);
        }
    }

    public static String typeofName(Scriptable scriptable, String str) {
        Context context = Context.getContext();
        Scriptable bind = bind(context, scriptable, str);
        if (bind == null) {
            return AdError.UNDEFINED_DOMAIN;
        }
        return typeof(getObjectProp(bind, str, context));
    }

    public static Object add(Object obj, Object obj2, Context context) {
        Object addValues;
        Object addValues2;
        if ((obj instanceof Number) && (obj2 instanceof Number)) {
            return wrapNumber(((Number) obj).doubleValue() + ((Number) obj2).doubleValue());
        }
        if ((obj instanceof XMLObject) && (addValues2 = ((XMLObject) obj).addValues(context, true, obj2)) != Scriptable.NOT_FOUND) {
            return addValues2;
        }
        if ((obj2 instanceof XMLObject) && (addValues = ((XMLObject) obj2).addValues(context, false, obj)) != Scriptable.NOT_FOUND) {
            return addValues;
        }
        if (obj instanceof Scriptable) {
            obj = ((Scriptable) obj).getDefaultValue((Class<?>) null);
        }
        if (obj2 instanceof Scriptable) {
            obj2 = ((Scriptable) obj2).getDefaultValue((Class<?>) null);
        }
        if ((obj instanceof CharSequence) || (obj2 instanceof CharSequence)) {
            return new ConsString(toCharSequence(obj), toCharSequence(obj2));
        }
        if (!(obj instanceof Number) || !(obj2 instanceof Number)) {
            return wrapNumber(toNumber(obj) + toNumber(obj2));
        }
        return wrapNumber(((Number) obj).doubleValue() + ((Number) obj2).doubleValue());
    }

    public static CharSequence add(CharSequence charSequence, Object obj) {
        return new ConsString(charSequence, toCharSequence(obj));
    }

    public static CharSequence add(Object obj, CharSequence charSequence) {
        return new ConsString(toCharSequence(obj), charSequence);
    }

    public static Object nameIncrDecr(Scriptable scriptable, String str, int i) {
        return nameIncrDecr(scriptable, str, Context.getContext(), i);
    }

    public static Object nameIncrDecr(Scriptable scriptable, String str, Context context, int i) {
        do {
            if (context.useDynamicScope && scriptable.getParentScope() == null) {
                scriptable = checkDynamicScope(context.topCallScope, scriptable);
            }
            Scriptable scriptable2 = scriptable;
            do {
                if ((scriptable2 instanceof NativeWith) && (scriptable2.getPrototype() instanceof XMLObject)) {
                    break;
                }
                Object obj = scriptable2.get(str, scriptable);
                if (obj != Scriptable.NOT_FOUND) {
                    return doScriptableIncrDecr(scriptable2, str, scriptable, obj, i);
                }
                scriptable2 = scriptable2.getPrototype();
            } while (scriptable2 != null);
            scriptable = scriptable.getParentScope();
        } while (scriptable != null);
        throw notFoundError(scriptable, str);
    }

    public static Object propIncrDecr(Object obj, String str, Context context, int i) {
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull != null) {
            Scriptable scriptable = objectOrNull;
            do {
                Object obj2 = scriptable.get(str, objectOrNull);
                if (obj2 != Scriptable.NOT_FOUND) {
                    return doScriptableIncrDecr(scriptable, str, objectOrNull, obj2, i);
                }
                scriptable = scriptable.getPrototype();
            } while (scriptable != null);
            objectOrNull.put(str, objectOrNull, (Object) NaNobj);
            return NaNobj;
        }
        throw undefReadError(obj, str);
    }

    private static Object doScriptableIncrDecr(Scriptable scriptable, String str, Scriptable scriptable2, Object obj, int i) {
        double d;
        boolean z = (i & 2) != 0;
        if (obj instanceof Number) {
            d = ((Number) obj).doubleValue();
        } else {
            d = toNumber(obj);
            if (z) {
                obj = wrapNumber(d);
            }
        }
        Number wrapNumber = wrapNumber((i & 1) == 0 ? d + 1.0d : d - 1.0d);
        scriptable.put(str, scriptable2, (Object) wrapNumber);
        return z ? obj : wrapNumber;
    }

    public static Object elemIncrDecr(Object obj, Object obj2, Context context, int i) {
        double d;
        Object objectElem = getObjectElem(obj, obj2, context);
        boolean z = (i & 2) != 0;
        if (objectElem instanceof Number) {
            d = ((Number) objectElem).doubleValue();
        } else {
            d = toNumber(objectElem);
            if (z) {
                objectElem = wrapNumber(d);
            }
        }
        Number wrapNumber = wrapNumber((i & 1) == 0 ? d + 1.0d : d - 1.0d);
        setObjectElem(obj, obj2, (Object) wrapNumber, context);
        return z ? objectElem : wrapNumber;
    }

    public static Object refIncrDecr(Ref ref, Context context, int i) {
        double d;
        Object obj = ref.get(context);
        boolean z = (i & 2) != 0;
        if (obj instanceof Number) {
            d = ((Number) obj).doubleValue();
        } else {
            d = toNumber(obj);
            if (z) {
                obj = wrapNumber(d);
            }
        }
        Number wrapNumber = wrapNumber((i & 1) == 0 ? d + 1.0d : d - 1.0d);
        ref.set(context, wrapNumber);
        return z ? obj : wrapNumber;
    }

    public static Object toPrimitive(Object obj) {
        return toPrimitive(obj, (Class<?>) null);
    }

    public static Object toPrimitive(Object obj, Class<?> cls) {
        if (!(obj instanceof Scriptable)) {
            return obj;
        }
        Object defaultValue = ((Scriptable) obj).getDefaultValue(cls);
        if (!(defaultValue instanceof Scriptable)) {
            return defaultValue;
        }
        throw typeError0("msg.bad.default.value");
    }

    public static boolean eq(Object obj, Object obj2) {
        Object equivalentValues;
        Object equivalentValues2;
        Object equivalentValues3;
        Object equivalentValues4;
        Object equivalentValues5;
        if (obj == null || obj == Undefined.instance) {
            if (obj2 == null || obj2 == Undefined.instance) {
                return true;
            }
            if (!(obj2 instanceof ScriptableObject) || (equivalentValues = ((ScriptableObject) obj2).equivalentValues(obj)) == Scriptable.NOT_FOUND) {
                return false;
            }
            return ((Boolean) equivalentValues).booleanValue();
        } else if (obj instanceof Number) {
            return eqNumber(((Number) obj).doubleValue(), obj2);
        } else {
            if (obj == obj2) {
                return true;
            }
            if (obj instanceof CharSequence) {
                return eqString((CharSequence) obj, obj2);
            }
            double d = 1.0d;
            if (obj instanceof Boolean) {
                boolean booleanValue = ((Boolean) obj).booleanValue();
                if (obj2 instanceof Boolean) {
                    if (booleanValue == ((Boolean) obj2).booleanValue()) {
                        return true;
                    }
                    return false;
                } else if ((obj2 instanceof ScriptableObject) && (equivalentValues5 = ((ScriptableObject) obj2).equivalentValues(obj)) != Scriptable.NOT_FOUND) {
                    return ((Boolean) equivalentValues5).booleanValue();
                } else {
                    if (!booleanValue) {
                        d = 0.0d;
                    }
                    return eqNumber(d, obj2);
                }
            } else if (!(obj instanceof Scriptable)) {
                warnAboutNonJSObject(obj);
                if (obj == obj2) {
                    return true;
                }
                return false;
            } else if (obj2 instanceof Scriptable) {
                if ((obj instanceof ScriptableObject) && (equivalentValues4 = ((ScriptableObject) obj).equivalentValues(obj2)) != Scriptable.NOT_FOUND) {
                    return ((Boolean) equivalentValues4).booleanValue();
                }
                if ((obj2 instanceof ScriptableObject) && (equivalentValues3 = ((ScriptableObject) obj2).equivalentValues(obj)) != Scriptable.NOT_FOUND) {
                    return ((Boolean) equivalentValues3).booleanValue();
                }
                if (!(obj instanceof Wrapper) || !(obj2 instanceof Wrapper)) {
                    return false;
                }
                Object unwrap = ((Wrapper) obj).unwrap();
                Object unwrap2 = ((Wrapper) obj2).unwrap();
                if (unwrap == unwrap2) {
                    return true;
                }
                if (!isPrimitive(unwrap) || !isPrimitive(unwrap2) || !eq(unwrap, unwrap2)) {
                    return false;
                }
                return true;
            } else if (obj2 instanceof Boolean) {
                if ((obj instanceof ScriptableObject) && (equivalentValues2 = ((ScriptableObject) obj).equivalentValues(obj2)) != Scriptable.NOT_FOUND) {
                    return ((Boolean) equivalentValues2).booleanValue();
                }
                if (!((Boolean) obj2).booleanValue()) {
                    d = 0.0d;
                }
                return eqNumber(d, obj);
            } else if (obj2 instanceof Number) {
                return eqNumber(((Number) obj2).doubleValue(), obj);
            } else {
                if (obj2 instanceof CharSequence) {
                    return eqString((CharSequence) obj2, obj);
                }
                return false;
            }
        }
    }

    public static boolean isPrimitive(Object obj) {
        return obj == null || obj == Undefined.instance || (obj instanceof Number) || (obj instanceof String) || (obj instanceof Boolean);
    }

    static boolean eqNumber(double d, Object obj) {
        Object equivalentValues;
        while (true) {
            if (obj != null && obj != Undefined.instance) {
                if (!(obj instanceof Number)) {
                    if (!(obj instanceof CharSequence)) {
                        if (!(obj instanceof Boolean)) {
                            if (!(obj instanceof Scriptable)) {
                                warnAboutNonJSObject(obj);
                                break;
                            } else if ((obj instanceof ScriptableObject) && (equivalentValues = ((ScriptableObject) obj).equivalentValues(wrapNumber(d))) != Scriptable.NOT_FOUND) {
                                return ((Boolean) equivalentValues).booleanValue();
                            } else {
                                obj = toPrimitive(obj);
                            }
                        } else {
                            if (d == (((Boolean) obj).booleanValue() ? 1.0d : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
                                return true;
                            }
                            return false;
                        }
                    } else if (d == toNumber(obj)) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (d == ((Number) obj).doubleValue()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                break;
            }
        }
        return false;
    }

    private static boolean eqString(CharSequence charSequence, Object obj) {
        Object equivalentValues;
        while (true) {
            if (obj != null && obj != Undefined.instance) {
                if (!(obj instanceof CharSequence)) {
                    if (!(obj instanceof Number)) {
                        if (!(obj instanceof Boolean)) {
                            if (!(obj instanceof Scriptable)) {
                                warnAboutNonJSObject(obj);
                                break;
                            } else if ((obj instanceof ScriptableObject) && (equivalentValues = ((ScriptableObject) obj).equivalentValues(charSequence.toString())) != Scriptable.NOT_FOUND) {
                                return ((Boolean) equivalentValues).booleanValue();
                            } else {
                                obj = toPrimitive(obj);
                            }
                        } else {
                            if (toNumber(charSequence.toString()) == (((Boolean) obj).booleanValue() ? 1.0d : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
                                return true;
                            }
                            return false;
                        }
                    } else if (toNumber(charSequence.toString()) == ((Number) obj).doubleValue()) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    CharSequence charSequence2 = (CharSequence) obj;
                    if (charSequence.length() != charSequence2.length() || !charSequence.toString().equals(charSequence2.toString())) {
                        return false;
                    }
                    return true;
                }
            } else {
                break;
            }
        }
        return false;
    }

    public static boolean shallowEq(Object obj, Object obj2) {
        if (obj == obj2) {
            if (!(obj instanceof Number)) {
                return true;
            }
            double doubleValue = ((Number) obj).doubleValue();
            if (doubleValue == doubleValue) {
                return true;
            }
            return false;
        } else if (obj == null || obj == Undefined.instance) {
            return false;
        } else {
            if (obj instanceof Number) {
                if (!(obj2 instanceof Number) || ((Number) obj).doubleValue() != ((Number) obj2).doubleValue()) {
                    return false;
                }
                return true;
            } else if (obj instanceof CharSequence) {
                if (obj2 instanceof CharSequence) {
                    return obj.toString().equals(obj2.toString());
                }
            } else if (obj instanceof Boolean) {
                if (obj2 instanceof Boolean) {
                    return obj.equals(obj2);
                }
            } else if (!(obj instanceof Scriptable)) {
                warnAboutNonJSObject(obj);
                if (obj == obj2) {
                    return true;
                }
                return false;
            } else if (!(obj instanceof Wrapper) || !(obj2 instanceof Wrapper) || ((Wrapper) obj).unwrap() != ((Wrapper) obj2).unwrap()) {
                return false;
            } else {
                return true;
            }
            return false;
        }
    }

    public static boolean instanceOf(Object obj, Object obj2, Context context) {
        if (!(obj2 instanceof Scriptable)) {
            throw typeError0("msg.instanceof.not.object");
        } else if (!(obj instanceof Scriptable)) {
            return false;
        } else {
            return ((Scriptable) obj2).hasInstance((Scriptable) obj);
        }
    }

    public static boolean jsDelegatesTo(Scriptable scriptable, Scriptable scriptable2) {
        for (Scriptable prototype = scriptable.getPrototype(); prototype != null; prototype = prototype.getPrototype()) {
            if (prototype.equals(scriptable2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean in(Object obj, Object obj2, Context context) {
        if (obj2 instanceof Scriptable) {
            return hasObjectElem((Scriptable) obj2, obj, context);
        }
        throw typeError0("msg.in.not.object");
    }

    public static boolean cmp_LT(Object obj, Object obj2) {
        double d;
        double d2;
        if (!(obj instanceof Number) || !(obj2 instanceof Number)) {
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(NumberClass);
            }
            if (obj2 instanceof Scriptable) {
                obj2 = ((Scriptable) obj2).getDefaultValue(NumberClass);
            }
            if (!(obj instanceof CharSequence) || !(obj2 instanceof CharSequence)) {
                d2 = toNumber(obj);
                d = toNumber(obj2);
            } else if (obj.toString().compareTo(obj2.toString()) < 0) {
                return true;
            } else {
                return false;
            }
        } else {
            d2 = ((Number) obj).doubleValue();
            d = ((Number) obj2).doubleValue();
        }
        if (d2 < d) {
            return true;
        }
        return false;
    }

    public static boolean cmp_LE(Object obj, Object obj2) {
        double d;
        double d2;
        if (!(obj instanceof Number) || !(obj2 instanceof Number)) {
            if (obj instanceof Scriptable) {
                obj = ((Scriptable) obj).getDefaultValue(NumberClass);
            }
            if (obj2 instanceof Scriptable) {
                obj2 = ((Scriptable) obj2).getDefaultValue(NumberClass);
            }
            if (!(obj instanceof CharSequence) || !(obj2 instanceof CharSequence)) {
                d2 = toNumber(obj);
                d = toNumber(obj2);
            } else if (obj.toString().compareTo(obj2.toString()) <= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            d2 = ((Number) obj).doubleValue();
            d = ((Number) obj2).doubleValue();
        }
        if (d2 <= d) {
            return true;
        }
        return false;
    }

    public static ScriptableObject getGlobal(Context context) {
        Class<?> classOrNull = Kit.classOrNull("org.mozilla.javascript.tools.shell.Global");
        if (classOrNull != null) {
            try {
                return (ScriptableObject) classOrNull.getConstructor(new Class[]{ContextClass}).newInstance(new Object[]{context});
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
        return new ImporterTopLevel(context);
    }

    public static boolean hasTopCall(Context context) {
        return context.topCallScope != null;
    }

    public static Scriptable getTopCallScope(Context context) {
        Scriptable scriptable = context.topCallScope;
        if (scriptable != null) {
            return scriptable;
        }
        throw new IllegalStateException();
    }

    public static Object doTopCall(Callable callable, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (scriptable == null) {
            throw new IllegalArgumentException();
        } else if (context.topCallScope == null) {
            context.topCallScope = ScriptableObject.getTopLevelScope(scriptable);
            context.useDynamicScope = context.hasFeature(7);
            try {
                Object doTopCall = context.getFactory().doTopCall(callable, context, scriptable, scriptable2, objArr);
                context.topCallScope = null;
                context.cachedXMLLib = null;
                if (context.currentActivationCall == null) {
                    return doTopCall;
                }
                throw new IllegalStateException();
            } catch (Throwable th) {
                context.topCallScope = null;
                context.cachedXMLLib = null;
                if (context.currentActivationCall != null) {
                    throw new IllegalStateException();
                }
                throw th;
            }
        } else {
            throw new IllegalStateException();
        }
    }

    static Scriptable checkDynamicScope(Scriptable scriptable, Scriptable scriptable2) {
        if (scriptable == scriptable2) {
            return scriptable;
        }
        Scriptable scriptable3 = scriptable;
        do {
            scriptable3 = scriptable3.getPrototype();
            if (scriptable3 == scriptable2) {
                return scriptable;
            }
        } while (scriptable3 != null);
        return scriptable2;
    }

    public static void addInstructionCount(Context context, int i) {
        context.instructionCount += i;
        if (context.instructionCount > context.instructionThreshold) {
            context.observeInstructionCount(context.instructionCount);
            context.instructionCount = 0;
        }
    }

    public static void initScript(NativeFunction nativeFunction, Scriptable scriptable, Context context, Scriptable scriptable2, boolean z) {
        if (context.topCallScope != null) {
            int paramAndVarCount = nativeFunction.getParamAndVarCount();
            if (paramAndVarCount != 0) {
                Scriptable scriptable3 = scriptable2;
                while (scriptable3 instanceof NativeWith) {
                    scriptable3 = scriptable3.getParentScope();
                }
                while (true) {
                    int i = paramAndVarCount - 1;
                    if (paramAndVarCount != 0) {
                        String paramOrVarName = nativeFunction.getParamOrVarName(i);
                        boolean paramOrVarConst = nativeFunction.getParamOrVarConst(i);
                        if (ScriptableObject.hasProperty(scriptable2, paramOrVarName)) {
                            ScriptableObject.redefineProperty(scriptable2, paramOrVarName, paramOrVarConst);
                        } else if (z) {
                            scriptable3.put(paramOrVarName, scriptable3, Undefined.instance);
                        } else if (paramOrVarConst) {
                            ScriptableObject.defineConstProperty(scriptable3, paramOrVarName);
                        } else {
                            ScriptableObject.defineProperty(scriptable3, paramOrVarName, Undefined.instance, 4);
                        }
                        paramAndVarCount = i;
                    } else {
                        return;
                    }
                }
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public static Scriptable createFunctionActivation(NativeFunction nativeFunction, Scriptable scriptable, Object[] objArr) {
        return new NativeCall(nativeFunction, scriptable, objArr);
    }

    public static void enterActivationFunction(Context context, Scriptable scriptable) {
        if (context.topCallScope != null) {
            NativeCall nativeCall = (NativeCall) scriptable;
            nativeCall.parentActivationCall = context.currentActivationCall;
            context.currentActivationCall = nativeCall;
            return;
        }
        throw new IllegalStateException();
    }

    public static void exitActivationFunction(Context context) {
        NativeCall nativeCall = context.currentActivationCall;
        context.currentActivationCall = nativeCall.parentActivationCall;
        nativeCall.parentActivationCall = null;
    }

    static NativeCall findFunctionActivation(Context context, Function function) {
        for (NativeCall nativeCall = context.currentActivationCall; nativeCall != null; nativeCall = nativeCall.parentActivationCall) {
            if (nativeCall.function == function) {
                return nativeCall;
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: org.mozilla.javascript.EvaluatorException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: org.mozilla.javascript.EcmaError} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v24, resolved type: org.mozilla.javascript.EvaluatorException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v25, resolved type: org.mozilla.javascript.EvaluatorException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v28, resolved type: org.mozilla.javascript.EvaluatorException} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.mozilla.javascript.Scriptable newCatchScope(java.lang.Throwable r11, org.mozilla.javascript.Scriptable r12, java.lang.String r13, org.mozilla.javascript.Context r14, org.mozilla.javascript.Scriptable r15) {
        /*
            boolean r0 = r11 instanceof org.mozilla.javascript.JavaScriptException
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x000f
            r12 = r11
            org.mozilla.javascript.JavaScriptException r12 = (org.mozilla.javascript.JavaScriptException) r12
            java.lang.Object r12 = r12.getValue()
            goto L_0x00e4
        L_0x000f:
            if (r12 == 0) goto L_0x001f
            org.mozilla.javascript.NativeObject r12 = (org.mozilla.javascript.NativeObject) r12
            java.lang.Object r12 = r12.getAssociatedValue(r11)
            if (r12 != 0) goto L_0x001c
            org.mozilla.javascript.Kit.codeBug()
        L_0x001c:
            r1 = 1
            goto L_0x00e4
        L_0x001f:
            boolean r12 = r11 instanceof org.mozilla.javascript.EcmaError
            java.lang.String r0 = "JavaException"
            r3 = 0
            if (r12 == 0) goto L_0x0033
            r12 = r11
            org.mozilla.javascript.EcmaError r12 = (org.mozilla.javascript.EcmaError) r12
            java.lang.String r0 = r12.getName()
            java.lang.String r4 = r12.getErrorMessage()
        L_0x0031:
            r5 = r3
            goto L_0x0082
        L_0x0033:
            boolean r12 = r11 instanceof org.mozilla.javascript.WrappedException
            if (r12 == 0) goto L_0x0062
            r12 = r11
            org.mozilla.javascript.WrappedException r12 = (org.mozilla.javascript.WrappedException) r12
            java.lang.Throwable r4 = r12.getWrappedException()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.Class r6 = r4.getClass()
            java.lang.String r6 = r6.getName()
            r5.append(r6)
            java.lang.String r6 = ": "
            r5.append(r6)
            java.lang.String r6 = r4.getMessage()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r10 = r5
            r5 = r4
            r4 = r10
            goto L_0x0082
        L_0x0062:
            boolean r12 = r11 instanceof org.mozilla.javascript.EvaluatorException
            if (r12 == 0) goto L_0x0070
            r12 = r11
            org.mozilla.javascript.EvaluatorException r12 = (org.mozilla.javascript.EvaluatorException) r12
            java.lang.String r4 = r12.getMessage()
            java.lang.String r0 = "InternalError"
            goto L_0x0031
        L_0x0070:
            r12 = 13
            boolean r12 = r14.hasFeature(r12)
            if (r12 == 0) goto L_0x0103
            org.mozilla.javascript.WrappedException r12 = new org.mozilla.javascript.WrappedException
            r12.<init>(r11)
            java.lang.String r4 = r11.toString()
            goto L_0x0031
        L_0x0082:
            java.lang.String r6 = r12.sourceName()
            if (r6 != 0) goto L_0x008a
            java.lang.String r6 = ""
        L_0x008a:
            int r7 = r12.lineNumber()
            r8 = 2
            if (r7 <= 0) goto L_0x009f
            r9 = 3
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r9[r1] = r4
            r9[r2] = r6
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
            r9[r8] = r1
            goto L_0x00a5
        L_0x009f:
            java.lang.Object[] r9 = new java.lang.Object[r8]
            r9[r1] = r4
            r9[r2] = r6
        L_0x00a5:
            org.mozilla.javascript.Scriptable r1 = r14.newObject(r15, r0, r9)
            java.lang.String r4 = "name"
            org.mozilla.javascript.ScriptableObject.putProperty((org.mozilla.javascript.Scriptable) r1, (java.lang.String) r4, (java.lang.Object) r0)
            boolean r0 = r1 instanceof org.mozilla.javascript.NativeError
            if (r0 == 0) goto L_0x00b8
            r0 = r1
            org.mozilla.javascript.NativeError r0 = (org.mozilla.javascript.NativeError) r0
            r0.setStackProvider(r12)
        L_0x00b8:
            r0 = 5
            if (r5 == 0) goto L_0x00ce
            boolean r4 = isVisible(r14, r5)
            if (r4 == 0) goto L_0x00ce
            org.mozilla.javascript.WrapFactory r4 = r14.getWrapFactory()
            java.lang.Object r4 = r4.wrap(r14, r15, r5, r3)
            java.lang.String r5 = "javaException"
            org.mozilla.javascript.ScriptableObject.defineProperty(r1, r5, r4, r0)
        L_0x00ce:
            boolean r4 = isVisible(r14, r12)
            if (r4 == 0) goto L_0x00e1
            org.mozilla.javascript.WrapFactory r4 = r14.getWrapFactory()
            java.lang.Object r12 = r4.wrap(r14, r15, r12, r3)
            java.lang.String r3 = "rhinoException"
            org.mozilla.javascript.ScriptableObject.defineProperty(r1, r3, r12, r0)
        L_0x00e1:
            r12 = r1
            goto L_0x001c
        L_0x00e4:
            org.mozilla.javascript.NativeObject r0 = new org.mozilla.javascript.NativeObject
            r0.<init>()
            r2 = 4
            r0.defineProperty((java.lang.String) r13, (java.lang.Object) r12, (int) r2)
            boolean r13 = isVisible(r14, r11)
            if (r13 == 0) goto L_0x00fd
            java.lang.Object r13 = org.mozilla.javascript.Context.javaToJS(r11, r15)
            r14 = 6
            java.lang.String r15 = "__exception__"
            r0.defineProperty((java.lang.String) r15, (java.lang.Object) r13, (int) r14)
        L_0x00fd:
            if (r1 == 0) goto L_0x0102
            r0.associateValue(r11, r12)
        L_0x0102:
            return r0
        L_0x0103:
            java.lang.RuntimeException r11 = org.mozilla.javascript.Kit.codeBug()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptRuntime.newCatchScope(java.lang.Throwable, org.mozilla.javascript.Scriptable, java.lang.String, org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable):org.mozilla.javascript.Scriptable");
    }

    private static boolean isVisible(Context context, Object obj) {
        ClassShutter classShutter = context.getClassShutter();
        return classShutter == null || classShutter.visibleToScripts(obj.getClass().getName());
    }

    public static Scriptable enterWith(Object obj, Context context, Scriptable scriptable) {
        Scriptable objectOrNull = toObjectOrNull(context, obj);
        if (objectOrNull == null) {
            throw typeError1("msg.undef.with", toString(obj));
        } else if (objectOrNull instanceof XMLObject) {
            return ((XMLObject) objectOrNull).enterWith(scriptable);
        } else {
            return new NativeWith(scriptable, objectOrNull);
        }
    }

    public static Scriptable leaveWith(Scriptable scriptable) {
        return ((NativeWith) scriptable).getParentScope();
    }

    public static Scriptable enterDotQuery(Object obj, Scriptable scriptable) {
        if (obj instanceof XMLObject) {
            return ((XMLObject) obj).enterDotQuery(scriptable);
        }
        throw notXmlError(obj);
    }

    public static Object updateDotQuery(boolean z, Scriptable scriptable) {
        return ((NativeWith) scriptable).updateDotQuery(z);
    }

    public static Scriptable leaveDotQuery(Scriptable scriptable) {
        return ((NativeWith) scriptable).getParentScope();
    }

    public static void setFunctionProtoAndParent(BaseFunction baseFunction, Scriptable scriptable) {
        baseFunction.setParentScope(scriptable);
        baseFunction.setPrototype(ScriptableObject.getFunctionPrototype(scriptable));
    }

    public static void setObjectProtoAndParent(ScriptableObject scriptableObject, Scriptable scriptable) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        scriptableObject.setParentScope(topLevelScope);
        scriptableObject.setPrototype(ScriptableObject.getClassPrototype(topLevelScope, scriptableObject.getClassName()));
    }

    public static void setBuiltinProtoAndParent(ScriptableObject scriptableObject, Scriptable scriptable, TopLevel.Builtins builtins) {
        Scriptable topLevelScope = ScriptableObject.getTopLevelScope(scriptable);
        scriptableObject.setParentScope(topLevelScope);
        scriptableObject.setPrototype(TopLevel.getBuiltinPrototype(topLevelScope, builtins));
    }

    public static void initFunction(Context context, Scriptable scriptable, NativeFunction nativeFunction, int i, boolean z) {
        if (i == 1) {
            String functionName = nativeFunction.getFunctionName();
            if (functionName != null && functionName.length() != 0) {
                if (!z) {
                    ScriptableObject.defineProperty(scriptable, functionName, nativeFunction, 4);
                } else {
                    scriptable.put(functionName, scriptable, (Object) nativeFunction);
                }
            }
        } else if (i == 3) {
            String functionName2 = nativeFunction.getFunctionName();
            if (functionName2 != null && functionName2.length() != 0) {
                while (scriptable instanceof NativeWith) {
                    scriptable = scriptable.getParentScope();
                }
                scriptable.put(functionName2, scriptable, (Object) nativeFunction);
            }
        } else {
            throw Kit.codeBug();
        }
    }

    public static Scriptable newArrayLiteral(Object[] objArr, int[] iArr, Context context, Scriptable scriptable) {
        int length = objArr.length;
        int i = 0;
        int length2 = iArr != null ? iArr.length : 0;
        int i2 = length + length2;
        if (i2 <= 1 || length2 * 2 >= i2) {
            Scriptable newArray = context.newArray(scriptable, i2);
            int i3 = 0;
            int i4 = 0;
            while (i != i2) {
                if (i3 == length2 || iArr[i3] != i) {
                    ScriptableObject.putProperty(newArray, i, objArr[i4]);
                    i4++;
                } else {
                    i3++;
                }
                i++;
            }
            return newArray;
        }
        if (length2 != 0) {
            Object[] objArr2 = new Object[i2];
            int i5 = 0;
            int i6 = 0;
            while (i != i2) {
                if (i5 == length2 || iArr[i5] != i) {
                    objArr2[i] = objArr[i6];
                    i6++;
                } else {
                    objArr2[i] = Scriptable.NOT_FOUND;
                    i5++;
                }
                i++;
            }
            objArr = objArr2;
        }
        return context.newArray(scriptable, objArr);
    }

    public static Scriptable newObjectLiteral(Object[] objArr, Object[] objArr2, Context context, Scriptable scriptable) {
        return newObjectLiteral(objArr, objArr2, (int[]) null, context, scriptable);
    }

    public static Scriptable newObjectLiteral(Object[] objArr, Object[] objArr2, int[] iArr, Context context, Scriptable scriptable) {
        int i;
        Scriptable newObject = context.newObject(scriptable);
        int length = objArr.length;
        for (int i2 = 0; i2 != length; i2++) {
            String str = objArr[i2];
            if (iArr == null) {
                i = 0;
            } else {
                i = iArr[i2];
            }
            Callable callable = objArr2[i2];
            if (!(str instanceof String)) {
                newObject.put(((Integer) str).intValue(), newObject, (Object) callable);
            } else if (i == 0) {
                String str2 = str;
                if (isSpecialProperty(str2)) {
                    specialRef(newObject, str2, context).set(context, callable);
                } else {
                    newObject.put(str2, newObject, (Object) callable);
                }
            } else {
                ScriptableObject scriptableObject = (ScriptableObject) newObject;
                Callable callable2 = callable;
                boolean z = true;
                if (i != 1) {
                    z = false;
                }
                scriptableObject.setGetterOrSetter(str, 0, callable2, z);
            }
        }
        return newObject;
    }

    public static boolean isArrayObject(Object obj) {
        return (obj instanceof NativeArray) || (obj instanceof Arguments);
    }

    public static Object[] getArrayElements(Scriptable scriptable) {
        long lengthProperty = NativeArray.getLengthProperty(Context.getContext(), scriptable);
        if (lengthProperty <= 2147483647L) {
            int i = (int) lengthProperty;
            if (i == 0) {
                return emptyArgs;
            }
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                Object property = ScriptableObject.getProperty(scriptable, i2);
                if (property == Scriptable.NOT_FOUND) {
                    property = Undefined.instance;
                }
                objArr[i2] = property;
            }
            return objArr;
        }
        throw new IllegalArgumentException();
    }

    static void checkDeprecated(Context context, String str) {
        int languageVersion = context.getLanguageVersion();
        if (languageVersion >= 140 || languageVersion == 0) {
            String message1 = getMessage1("msg.deprec.ctor", str);
            if (languageVersion == 0) {
                Context.reportWarning(message1);
                return;
            }
            throw Context.reportRuntimeError(message1);
        }
    }

    public static String getMessage0(String str) {
        return getMessage(str, (Object[]) null);
    }

    public static String getMessage1(String str, Object obj) {
        return getMessage(str, new Object[]{obj});
    }

    public static String getMessage2(String str, Object obj, Object obj2) {
        return getMessage(str, new Object[]{obj, obj2});
    }

    public static String getMessage3(String str, Object obj, Object obj2, Object obj3) {
        return getMessage(str, new Object[]{obj, obj2, obj3});
    }

    public static String getMessage4(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return getMessage(str, new Object[]{obj, obj2, obj3, obj4});
    }

    public static String getMessage(String str, Object[] objArr) {
        return messageProvider.getMessage(str, objArr);
    }

    private static class DefaultMessageProvider implements MessageProvider {
        private DefaultMessageProvider() {
        }

        public String getMessage(String str, Object[] objArr) {
            Context currentContext = Context.getCurrentContext();
            try {
                return new MessageFormat(ResourceBundle.getBundle("org.mozilla.javascript.resources.Messages", currentContext != null ? currentContext.getLocale() : Locale.getDefault()).getString(str)).format(objArr);
            } catch (MissingResourceException unused) {
                throw new RuntimeException("no message resource found for message property " + str);
            }
        }
    }

    public static EcmaError constructError(String str, String str2) {
        int[] iArr = new int[1];
        return constructError(str, str2, Context.getSourcePositionFromStack(iArr), iArr[0], (String) null, 0);
    }

    public static EcmaError constructError(String str, String str2, int i) {
        int[] iArr = new int[1];
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        if (iArr[0] != 0) {
            iArr[0] = iArr[0] + i;
        }
        return constructError(str, str2, sourcePositionFromStack, iArr[0], (String) null, 0);
    }

    public static EcmaError constructError(String str, String str2, String str3, int i, String str4, int i2) {
        return new EcmaError(str, str2, str3, i, str4, i2);
    }

    public static EcmaError typeError(String str) {
        return constructError("TypeError", str);
    }

    public static EcmaError typeError0(String str) {
        return typeError(getMessage0(str));
    }

    public static EcmaError typeError1(String str, String str2) {
        return typeError(getMessage1(str, str2));
    }

    public static EcmaError typeError2(String str, String str2, String str3) {
        return typeError(getMessage2(str, str2, str3));
    }

    public static EcmaError typeError3(String str, String str2, String str3, String str4) {
        return typeError(getMessage3(str, str2, str3, str4));
    }

    public static RuntimeException undefReadError(Object obj, Object obj2) {
        return typeError2("msg.undef.prop.read", toString(obj), obj2 == null ? "null" : obj2.toString());
    }

    public static RuntimeException undefCallError(Object obj, Object obj2) {
        return typeError2("msg.undef.method.call", toString(obj), obj2 == null ? "null" : obj2.toString());
    }

    public static RuntimeException undefWriteError(Object obj, Object obj2, Object obj3) {
        return typeError3("msg.undef.prop.write", toString(obj), obj2 == null ? "null" : obj2.toString(), obj3 instanceof Scriptable ? obj3.toString() : toString(obj3));
    }

    public static RuntimeException notFoundError(Scriptable scriptable, String str) {
        throw constructError("ReferenceError", getMessage1("msg.is.not.defined", str));
    }

    public static RuntimeException notFunctionError(Object obj) {
        return notFunctionError(obj, obj);
    }

    public static RuntimeException notFunctionError(Object obj, Object obj2) {
        String obj3 = obj2 == null ? "null" : obj2.toString();
        if (obj == Scriptable.NOT_FOUND) {
            return typeError1("msg.function.not.found", obj3);
        }
        return typeError2("msg.isnt.function", obj3, typeof(obj));
    }

    public static RuntimeException notFunctionError(Object obj, Object obj2, String str) {
        int indexOf;
        String scriptRuntime = toString(obj);
        if ((obj instanceof NativeFunction) && (indexOf = scriptRuntime.indexOf(123)) > -1) {
            scriptRuntime = scriptRuntime.substring(0, indexOf + 1) + "...}";
        }
        if (obj2 == Scriptable.NOT_FOUND) {
            return typeError2("msg.function.not.found.in", str, scriptRuntime);
        }
        return typeError3("msg.isnt.function.in", str, scriptRuntime, typeof(obj2));
    }

    private static RuntimeException notXmlError(Object obj) {
        throw typeError1("msg.isnt.xml.object", toString(obj));
    }

    private static void warnAboutNonJSObject(Object obj) {
        String str = "RHINO USAGE WARNING: Missed Context.javaToJS() conversion:\nRhino runtime detected object " + obj + " of class " + obj.getClass().getName() + " where it expected String, Number, Boolean or Scriptable instance. Please check your code for missing Context.javaToJS() call.";
        Context.reportWarning(str);
        System.err.println(str);
    }

    public static RegExpProxy getRegExpProxy(Context context) {
        return context.getRegExpProxy();
    }

    public static void setRegExpProxy(Context context, RegExpProxy regExpProxy) {
        if (regExpProxy != null) {
            context.regExpProxy = regExpProxy;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static RegExpProxy checkRegExpProxy(Context context) {
        RegExpProxy regExpProxy = getRegExpProxy(context);
        if (regExpProxy != null) {
            return regExpProxy;
        }
        throw Context.reportRuntimeError0("msg.no.regexp");
    }

    public static Scriptable wrapRegExp(Context context, Scriptable scriptable, Object obj) {
        return context.getRegExpProxy().wrapRegExp(context, scriptable, obj);
    }

    private static XMLLib currentXMLLib(Context context) {
        if (context.topCallScope != null) {
            XMLLib xMLLib = context.cachedXMLLib;
            if (xMLLib == null) {
                xMLLib = XMLLib.extractFromScope(context.topCallScope);
                if (xMLLib != null) {
                    context.cachedXMLLib = xMLLib;
                } else {
                    throw new IllegalStateException();
                }
            }
            return xMLLib;
        }
        throw new IllegalStateException();
    }

    public static String escapeAttributeValue(Object obj, Context context) {
        return currentXMLLib(context).escapeAttributeValue(obj);
    }

    public static String escapeTextValue(Object obj, Context context) {
        return currentXMLLib(context).escapeTextValue(obj);
    }

    public static Ref memberRef(Object obj, Object obj2, Context context, int i) {
        if (obj instanceof XMLObject) {
            return ((XMLObject) obj).memberRef(context, obj2, i);
        }
        throw notXmlError(obj);
    }

    public static Ref memberRef(Object obj, Object obj2, Object obj3, Context context, int i) {
        if (obj instanceof XMLObject) {
            return ((XMLObject) obj).memberRef(context, obj2, obj3, i);
        }
        throw notXmlError(obj);
    }

    public static Ref nameRef(Object obj, Context context, Scriptable scriptable, int i) {
        return currentXMLLib(context).nameRef(context, obj, scriptable, i);
    }

    public static Ref nameRef(Object obj, Object obj2, Context context, Scriptable scriptable, int i) {
        return currentXMLLib(context).nameRef(context, obj, obj2, scriptable, i);
    }

    private static void storeIndexResult(Context context, int i) {
        context.scratchIndex = i;
    }

    static int lastIndexResult(Context context) {
        return context.scratchIndex;
    }

    public static void storeUint32Result(Context context, long j) {
        if ((j >>> 32) == 0) {
            context.scratchUint32 = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static long lastUint32Result(Context context) {
        long j = context.scratchUint32;
        if ((j >>> 32) == 0) {
            return j;
        }
        throw new IllegalStateException();
    }

    private static void storeScriptable(Context context, Scriptable scriptable) {
        if (context.scratchScriptable == null) {
            context.scratchScriptable = scriptable;
            return;
        }
        throw new IllegalStateException();
    }

    public static Scriptable lastStoredScriptable(Context context) {
        Scriptable scriptable = context.scratchScriptable;
        context.scratchScriptable = null;
        return scriptable;
    }

    static String makeUrlForGeneratedScript(boolean z, String str, int i) {
        if (z) {
            return str + '#' + i + "(eval)";
        }
        return str + '#' + i + "(Function)";
    }

    static boolean isGeneratedScript(String str) {
        return str.indexOf("(eval)") >= 0 || str.indexOf("(Function)") >= 0;
    }

    private static RuntimeException errorWithClassName(String str, Object obj) {
        return Context.reportRuntimeError1(str, obj.getClass().getName());
    }

    public static JavaScriptException throwError(Context context, Scriptable scriptable, String str) {
        int[] iArr = {0};
        String sourcePositionFromStack = Context.getSourcePositionFromStack(iArr);
        return new JavaScriptException(newBuiltinObject(context, scriptable, TopLevel.Builtins.Error, new Object[]{str, sourcePositionFromStack, Integer.valueOf(iArr[0])}), sourcePositionFromStack, iArr[0]);
    }
}
