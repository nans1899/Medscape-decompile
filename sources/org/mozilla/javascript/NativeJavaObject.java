package org.mozilla.javascript;

import com.google.android.gms.ads.AdError;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import org.mozilla.javascript.TopLevel;

public class NativeJavaObject implements Scriptable, Wrapper, Serializable {
    private static final Object COERCED_INTERFACE_KEY = "Coerced Interface";
    static final byte CONVERSION_NONE = 99;
    static final byte CONVERSION_NONTRIVIAL = 0;
    static final byte CONVERSION_TRIVIAL = 1;
    private static final int JSTYPE_BOOLEAN = 2;
    private static final int JSTYPE_JAVA_ARRAY = 7;
    private static final int JSTYPE_JAVA_CLASS = 5;
    private static final int JSTYPE_JAVA_OBJECT = 6;
    private static final int JSTYPE_NULL = 1;
    private static final int JSTYPE_NUMBER = 3;
    private static final int JSTYPE_OBJECT = 8;
    private static final int JSTYPE_STRING = 4;
    private static final int JSTYPE_UNDEFINED = 0;
    private static Method adapter_readAdapterObject = null;
    private static Method adapter_writeAdapterObject = null;
    static final long serialVersionUID = -6948590651130498591L;
    private transient Map<String, FieldAndMethods> fieldAndMethods;
    protected transient boolean isAdapter;
    protected transient Object javaObject;
    protected transient JavaMembers members;
    protected Scriptable parent;
    protected Scriptable prototype;
    protected transient Class<?> staticType;

    public void delete(int i) {
    }

    public void delete(String str) {
    }

    public String getClassName() {
        return "JavaObject";
    }

    public boolean has(int i, Scriptable scriptable) {
        return false;
    }

    public boolean hasInstance(Scriptable scriptable) {
        return false;
    }

    public NativeJavaObject() {
    }

    public NativeJavaObject(Scriptable scriptable, Object obj, Class<?> cls) {
        this(scriptable, obj, cls, false);
    }

    public NativeJavaObject(Scriptable scriptable, Object obj, Class<?> cls, boolean z) {
        this.parent = scriptable;
        this.javaObject = obj;
        this.staticType = cls;
        this.isAdapter = z;
        initMembers();
    }

    /* access modifiers changed from: protected */
    public void initMembers() {
        Class<?> cls;
        Object obj = this.javaObject;
        if (obj != null) {
            cls = obj.getClass();
        } else {
            cls = this.staticType;
        }
        JavaMembers lookupClass = JavaMembers.lookupClass(this.parent, cls, this.staticType, this.isAdapter);
        this.members = lookupClass;
        this.fieldAndMethods = lookupClass.getFieldAndMethodsObjects(this, this.javaObject, false);
    }

    public boolean has(String str, Scriptable scriptable) {
        return this.members.has(str, false);
    }

    public Object get(String str, Scriptable scriptable) {
        FieldAndMethods fieldAndMethods2;
        Map<String, FieldAndMethods> map = this.fieldAndMethods;
        if (map == null || (fieldAndMethods2 = map.get(str)) == null) {
            return this.members.get(this, str, this.javaObject, false);
        }
        return fieldAndMethods2;
    }

    public Object get(int i, Scriptable scriptable) {
        throw this.members.reportMemberNotFound(Integer.toString(i));
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        if (this.prototype == null || this.members.has(str, false)) {
            this.members.put(this, str, this.javaObject, obj, false);
            return;
        }
        Scriptable scriptable2 = this.prototype;
        scriptable2.put(str, scriptable2, obj);
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        throw this.members.reportMemberNotFound(Integer.toString(i));
    }

    public Scriptable getPrototype() {
        if (this.prototype != null || !(this.javaObject instanceof String)) {
            return this.prototype;
        }
        return TopLevel.getBuiltinPrototype(ScriptableObject.getTopLevelScope(this.parent), TopLevel.Builtins.String);
    }

    public void setPrototype(Scriptable scriptable) {
        this.prototype = scriptable;
    }

    public Scriptable getParentScope() {
        return this.parent;
    }

    public void setParentScope(Scriptable scriptable) {
        this.parent = scriptable;
    }

    public Object[] getIds() {
        return this.members.getIds(false);
    }

    public static Object wrap(Scriptable scriptable, Object obj, Class<?> cls) {
        Context context = Context.getContext();
        return context.getWrapFactory().wrap(context, scriptable, obj, cls);
    }

    public Object unwrap() {
        return this.javaObject;
    }

    public Object getDefaultValue(Class<?> cls) {
        String str;
        if (cls == null && (this.javaObject instanceof Boolean)) {
            cls = ScriptRuntime.BooleanClass;
        }
        if (cls == null || cls == ScriptRuntime.StringClass) {
            return this.javaObject.toString();
        }
        if (cls == ScriptRuntime.BooleanClass) {
            str = "booleanValue";
        } else if (cls == ScriptRuntime.NumberClass) {
            str = "doubleValue";
        } else {
            throw Context.reportRuntimeError0("msg.default.value");
        }
        Object obj = get(str, (Scriptable) this);
        if (obj instanceof Function) {
            Function function = (Function) obj;
            return function.call(Context.getContext(), function.getParentScope(), this, ScriptRuntime.emptyArgs);
        }
        if (cls == ScriptRuntime.NumberClass) {
            Object obj2 = this.javaObject;
            if (obj2 instanceof Boolean) {
                return ScriptRuntime.wrapNumber(((Boolean) obj2).booleanValue() ? 1.0d : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
            }
        }
        return this.javaObject.toString();
    }

    public static boolean canConvert(Object obj, Class<?> cls) {
        return getConversionWeight(obj, cls) < 99;
    }

    static int getConversionWeight(Object obj, Class<?> cls) {
        int jSTypeCode = getJSTypeCode(obj);
        switch (jSTypeCode) {
            case 0:
                if (cls == ScriptRuntime.StringClass || cls == ScriptRuntime.ObjectClass) {
                    return 1;
                }
                break;
            case 1:
                if (!cls.isPrimitive()) {
                    return 1;
                }
                break;
            case 2:
                if (cls == Boolean.TYPE) {
                    return 1;
                }
                if (cls == ScriptRuntime.BooleanClass) {
                    return 2;
                }
                if (cls == ScriptRuntime.ObjectClass) {
                    return 3;
                }
                if (cls == ScriptRuntime.StringClass) {
                    return 4;
                }
                break;
            case 3:
                if (cls.isPrimitive()) {
                    if (cls == Double.TYPE) {
                        return 1;
                    }
                    if (cls != Boolean.TYPE) {
                        return getSizeRank(cls) + 1;
                    }
                } else if (cls == ScriptRuntime.StringClass) {
                    return 9;
                } else {
                    if (cls == ScriptRuntime.ObjectClass) {
                        return 10;
                    }
                    if (ScriptRuntime.NumberClass.isAssignableFrom(cls)) {
                        return 2;
                    }
                }
                break;
            case 4:
                if (cls == ScriptRuntime.StringClass) {
                    return 1;
                }
                if (cls.isInstance(obj)) {
                    return 2;
                }
                if (cls.isPrimitive()) {
                    if (cls == Character.TYPE) {
                        return 3;
                    }
                    if (cls != Boolean.TYPE) {
                        return 4;
                    }
                }
                break;
            case 5:
                if (cls == ScriptRuntime.ClassClass) {
                    return 1;
                }
                if (cls == ScriptRuntime.ObjectClass) {
                    return 3;
                }
                if (cls == ScriptRuntime.StringClass) {
                    return 4;
                }
                break;
            case 6:
            case 7:
                if (obj instanceof Wrapper) {
                    obj = ((Wrapper) obj).unwrap();
                }
                if (cls.isInstance(obj)) {
                    return 0;
                }
                if (cls == ScriptRuntime.StringClass) {
                    return 2;
                }
                if (!cls.isPrimitive() || cls == Boolean.TYPE || jSTypeCode == 7) {
                    return 99;
                }
                return getSizeRank(cls) + 2;
            case 8:
                if (cls != ScriptRuntime.ObjectClass && cls.isInstance(obj)) {
                    return 1;
                }
                if (cls.isArray()) {
                    if (obj instanceof NativeArray) {
                        return 2;
                    }
                } else if (cls == ScriptRuntime.ObjectClass) {
                    return 3;
                } else {
                    if (cls == ScriptRuntime.StringClass) {
                        return 4;
                    }
                    if (cls == ScriptRuntime.DateClass) {
                        if (obj instanceof NativeDate) {
                            return 1;
                        }
                    } else if (cls.isInterface()) {
                        if ((obj instanceof NativeObject) || (obj instanceof NativeFunction)) {
                            return 1;
                        }
                        return 12;
                    } else if (cls.isPrimitive() && cls != Boolean.TYPE) {
                        return getSizeRank(cls) + 4;
                    }
                }
                break;
        }
        return 99;
    }

    static int getSizeRank(Class<?> cls) {
        if (cls == Double.TYPE) {
            return 1;
        }
        if (cls == Float.TYPE) {
            return 2;
        }
        if (cls == Long.TYPE) {
            return 3;
        }
        if (cls == Integer.TYPE) {
            return 4;
        }
        if (cls == Short.TYPE) {
            return 5;
        }
        if (cls == Character.TYPE) {
            return 6;
        }
        if (cls == Byte.TYPE) {
            return 7;
        }
        return cls == Boolean.TYPE ? 99 : 8;
    }

    private static int getJSTypeCode(Object obj) {
        if (obj == null) {
            return 1;
        }
        if (obj == Undefined.instance) {
            return 0;
        }
        if (obj instanceof CharSequence) {
            return 4;
        }
        if (obj instanceof Number) {
            return 3;
        }
        if (obj instanceof Boolean) {
            return 2;
        }
        if (obj instanceof Scriptable) {
            if (obj instanceof NativeJavaClass) {
                return 5;
            }
            if (obj instanceof NativeJavaArray) {
                return 7;
            }
            if (obj instanceof Wrapper) {
                return 6;
            }
            return 8;
        } else if (obj instanceof Class) {
            return 5;
        } else {
            return obj.getClass().isArray() ? 7 : 6;
        }
    }

    public static Object coerceType(Class<?> cls, Object obj) {
        return coerceTypeImpl(cls, obj);
    }

    static Object coerceTypeImpl(Class<?> cls, Object obj) {
        if (obj != null && obj.getClass() == cls) {
            return obj;
        }
        switch (getJSTypeCode(obj)) {
            case 0:
                if (cls != ScriptRuntime.StringClass && cls != ScriptRuntime.ObjectClass) {
                    reportConversionError(AdError.UNDEFINED_DOMAIN, cls);
                    break;
                } else {
                    return AdError.UNDEFINED_DOMAIN;
                }
                break;
            case 1:
                if (!cls.isPrimitive()) {
                    return null;
                }
                reportConversionError(obj, cls);
                return null;
            case 2:
                if (cls != Boolean.TYPE && cls != ScriptRuntime.BooleanClass && cls != ScriptRuntime.ObjectClass) {
                    if (cls != ScriptRuntime.StringClass) {
                        reportConversionError(obj, cls);
                        break;
                    } else {
                        return obj.toString();
                    }
                } else {
                    return obj;
                }
            case 3:
                if (cls == ScriptRuntime.StringClass) {
                    return ScriptRuntime.toString(obj);
                }
                if (cls == ScriptRuntime.ObjectClass) {
                    return coerceToNumber(Double.TYPE, obj);
                }
                if ((!cls.isPrimitive() || cls == Boolean.TYPE) && !ScriptRuntime.NumberClass.isAssignableFrom(cls)) {
                    reportConversionError(obj, cls);
                    break;
                } else {
                    return coerceToNumber(cls, obj);
                }
                break;
            case 4:
                if (cls == ScriptRuntime.StringClass || cls.isInstance(obj)) {
                    return obj.toString();
                }
                if (cls == Character.TYPE || cls == ScriptRuntime.CharacterClass) {
                    CharSequence charSequence = (CharSequence) obj;
                    if (charSequence.length() == 1) {
                        return Character.valueOf(charSequence.charAt(0));
                    }
                    return coerceToNumber(cls, obj);
                } else if ((!cls.isPrimitive() || cls == Boolean.TYPE) && !ScriptRuntime.NumberClass.isAssignableFrom(cls)) {
                    reportConversionError(obj, cls);
                    break;
                } else {
                    return coerceToNumber(cls, obj);
                }
                break;
            case 5:
                if (obj instanceof Wrapper) {
                    obj = ((Wrapper) obj).unwrap();
                }
                if (cls != ScriptRuntime.ClassClass && cls != ScriptRuntime.ObjectClass) {
                    if (cls != ScriptRuntime.StringClass) {
                        reportConversionError(obj, cls);
                        break;
                    } else {
                        return obj.toString();
                    }
                } else {
                    return obj;
                }
                break;
            case 6:
            case 7:
                if (obj instanceof Wrapper) {
                    obj = ((Wrapper) obj).unwrap();
                }
                if (!cls.isPrimitive()) {
                    if (cls != ScriptRuntime.StringClass) {
                        if (!cls.isInstance(obj)) {
                            reportConversionError(obj, cls);
                            break;
                        } else {
                            return obj;
                        }
                    } else {
                        return obj.toString();
                    }
                } else {
                    if (cls == Boolean.TYPE) {
                        reportConversionError(obj, cls);
                    }
                    return coerceToNumber(cls, obj);
                }
            case 8:
                if (cls == ScriptRuntime.StringClass) {
                    return ScriptRuntime.toString(obj);
                }
                if (cls.isPrimitive()) {
                    if (cls == Boolean.TYPE) {
                        reportConversionError(obj, cls);
                    }
                    return coerceToNumber(cls, obj);
                } else if (cls.isInstance(obj)) {
                    return obj;
                } else {
                    if (cls == ScriptRuntime.DateClass && (obj instanceof NativeDate)) {
                        return new Date((long) ((NativeDate) obj).getJSTimeValue());
                    }
                    if (cls.isArray() && (obj instanceof NativeArray)) {
                        NativeArray nativeArray = (NativeArray) obj;
                        long length = nativeArray.getLength();
                        Class<?> componentType = cls.getComponentType();
                        Object newInstance = Array.newInstance(componentType, (int) length);
                        for (int i = 0; ((long) i) < length; i++) {
                            try {
                                Array.set(newInstance, i, coerceTypeImpl(componentType, nativeArray.get(i, nativeArray)));
                            } catch (EvaluatorException unused) {
                                reportConversionError(obj, cls);
                            }
                        }
                        return newInstance;
                    } else if (obj instanceof Wrapper) {
                        obj = ((Wrapper) obj).unwrap();
                        if (!cls.isInstance(obj)) {
                            reportConversionError(obj, cls);
                            break;
                        } else {
                            return obj;
                        }
                    } else if (!cls.isInterface() || (!(obj instanceof NativeObject) && !(obj instanceof NativeFunction))) {
                        reportConversionError(obj, cls);
                        break;
                    } else {
                        return createInterfaceAdapter(cls, (ScriptableObject) obj);
                    }
                }
                break;
        }
        return obj;
    }

    protected static Object createInterfaceAdapter(Class<?> cls, ScriptableObject scriptableObject) {
        Object makeHashKeyFromPair = Kit.makeHashKeyFromPair(COERCED_INTERFACE_KEY, cls);
        Object associatedValue = scriptableObject.getAssociatedValue(makeHashKeyFromPair);
        if (associatedValue != null) {
            return associatedValue;
        }
        return scriptableObject.associateValue(makeHashKeyFromPair, InterfaceAdapter.create(Context.getContext(), cls, scriptableObject));
    }

    private static Object coerceToNumber(Class<?> cls, Object obj) {
        Class<?> cls2 = obj.getClass();
        if (cls == Character.TYPE || cls == ScriptRuntime.CharacterClass) {
            if (cls2 == ScriptRuntime.CharacterClass) {
                return obj;
            }
            return Character.valueOf((char) ((int) toInteger(obj, ScriptRuntime.CharacterClass, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 65535.0d)));
        } else if (cls == ScriptRuntime.ObjectClass || cls == ScriptRuntime.DoubleClass || cls == Double.TYPE) {
            return cls2 == ScriptRuntime.DoubleClass ? obj : new Double(toDouble(obj));
        } else {
            if (cls == ScriptRuntime.FloatClass || cls == Float.TYPE) {
                if (cls2 == ScriptRuntime.FloatClass) {
                    return obj;
                }
                double d = toDouble(obj);
                if (!Double.isInfinite(d) && !Double.isNaN(d)) {
                    double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    int i = (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0 : -1));
                    if (i != 0) {
                        double abs = Math.abs(d);
                        if (abs < 1.401298464324817E-45d) {
                            if (i <= 0) {
                                d2 = -0.0d;
                            }
                            return new Float(d2);
                        } else if (abs <= 3.4028234663852886E38d) {
                            return new Float((float) d);
                        } else {
                            return new Float(i > 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY);
                        }
                    }
                }
                return new Float((float) d);
            } else if (cls == ScriptRuntime.IntegerClass || cls == Integer.TYPE) {
                if (cls2 == ScriptRuntime.IntegerClass) {
                    return obj;
                }
                return Integer.valueOf((int) toInteger(obj, ScriptRuntime.IntegerClass, -2.147483648E9d, 2.147483647E9d));
            } else if (cls == ScriptRuntime.LongClass || cls == Long.TYPE) {
                if (cls2 == ScriptRuntime.LongClass) {
                    return obj;
                }
                return Long.valueOf(toInteger(obj, ScriptRuntime.LongClass, Double.longBitsToDouble(-4332462841530417152L), Double.longBitsToDouble(4890909195324358655L)));
            } else if (cls == ScriptRuntime.ShortClass || cls == Short.TYPE) {
                if (cls2 == ScriptRuntime.ShortClass) {
                    return obj;
                }
                return Short.valueOf((short) ((int) toInteger(obj, ScriptRuntime.ShortClass, -32768.0d, 32767.0d)));
            } else if (cls != ScriptRuntime.ByteClass && cls != Byte.TYPE) {
                return new Double(toDouble(obj));
            } else {
                if (cls2 == ScriptRuntime.ByteClass) {
                    return obj;
                }
                return Byte.valueOf((byte) ((int) toInteger(obj, ScriptRuntime.ByteClass, -128.0d, 127.0d)));
            }
        }
    }

    private static double toDouble(Object obj) {
        Method method;
        if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        }
        if (obj instanceof String) {
            return ScriptRuntime.toNumber((String) obj);
        }
        if (!(obj instanceof Scriptable)) {
            try {
                method = obj.getClass().getMethod("doubleValue", (Class[]) null);
            } catch (NoSuchMethodException | SecurityException unused) {
                method = null;
            }
            if (method != null) {
                try {
                    return ((Number) method.invoke(obj, (Object[]) null)).doubleValue();
                } catch (IllegalAccessException unused2) {
                    reportConversionError(obj, Double.TYPE);
                } catch (InvocationTargetException unused3) {
                    reportConversionError(obj, Double.TYPE);
                }
            }
            return ScriptRuntime.toNumber(obj.toString());
        } else if (obj instanceof Wrapper) {
            return toDouble(((Wrapper) obj).unwrap());
        } else {
            return ScriptRuntime.toNumber(obj);
        }
    }

    private static long toInteger(Object obj, Class<?> cls, double d, double d2) {
        double d3;
        double d4 = toDouble(obj);
        if (Double.isInfinite(d4) || Double.isNaN(d4)) {
            reportConversionError(ScriptRuntime.toString(obj), cls);
        }
        if (d4 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            d3 = Math.floor(d4);
        } else {
            d3 = Math.ceil(d4);
        }
        if (d3 < d || d3 > d2) {
            reportConversionError(ScriptRuntime.toString(obj), cls);
        }
        return (long) d3;
    }

    static void reportConversionError(Object obj, Class<?> cls) {
        throw Context.reportRuntimeError2("msg.conversion.not.allowed", String.valueOf(obj), JavaMembers.javaSignature(cls));
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeBoolean(this.isAdapter);
        if (this.isAdapter) {
            Method method = adapter_writeAdapterObject;
            if (method != null) {
                try {
                    method.invoke((Object) null, new Object[]{this.javaObject, objectOutputStream});
                } catch (Exception unused) {
                    throw new IOException();
                }
            } else {
                throw new IOException();
            }
        } else {
            objectOutputStream.writeObject(this.javaObject);
        }
        Class<?> cls = this.staticType;
        if (cls != null) {
            objectOutputStream.writeObject(cls.getClass().getName());
        } else {
            objectOutputStream.writeObject((Object) null);
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        boolean readBoolean = objectInputStream.readBoolean();
        this.isAdapter = readBoolean;
        if (readBoolean) {
            Method method = adapter_readAdapterObject;
            if (method != null) {
                try {
                    this.javaObject = method.invoke((Object) null, new Object[]{this, objectInputStream});
                } catch (Exception unused) {
                    throw new IOException();
                }
            } else {
                throw new ClassNotFoundException();
            }
        } else {
            this.javaObject = objectInputStream.readObject();
        }
        String str = (String) objectInputStream.readObject();
        if (str != null) {
            this.staticType = Class.forName(str);
        } else {
            this.staticType = null;
        }
        initMembers();
    }

    static {
        Class[] clsArr = new Class[2];
        Class<?> classOrNull = Kit.classOrNull("org.mozilla.javascript.JavaAdapter");
        if (classOrNull != null) {
            try {
                clsArr[0] = ScriptRuntime.ObjectClass;
                clsArr[1] = Kit.classOrNull("java.io.ObjectOutputStream");
                adapter_writeAdapterObject = classOrNull.getMethod("writeAdapterObject", clsArr);
                clsArr[0] = ScriptRuntime.ScriptableClass;
                clsArr[1] = Kit.classOrNull("java.io.ObjectInputStream");
                adapter_readAdapterObject = classOrNull.getMethod("readAdapterObject", clsArr);
            } catch (NoSuchMethodException unused) {
                adapter_writeAdapterObject = null;
                adapter_readAdapterObject = null;
            }
        }
    }
}
