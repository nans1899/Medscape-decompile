package org.mozilla.javascript;

import com.dd.plist.ASCIIPropertyListParser;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.bytebuddy.pool.TypePool;
import org.mozilla.classfile.ClassFileWriter;
import org.mozilla.javascript.ObjToIntMap;

public final class JavaAdapter implements IdFunctionCall {
    private static final Object FTAG = "JavaAdapter";
    private static final int Id_JavaAdapter = 1;

    static class JavaAdapterSignature {
        Class<?>[] interfaces;
        ObjToIntMap names;
        Class<?> superClass;

        JavaAdapterSignature(Class<?> cls, Class<?>[] clsArr, ObjToIntMap objToIntMap) {
            this.superClass = cls;
            this.interfaces = clsArr;
            this.names = objToIntMap;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof JavaAdapterSignature)) {
                return false;
            }
            JavaAdapterSignature javaAdapterSignature = (JavaAdapterSignature) obj;
            if (this.superClass != javaAdapterSignature.superClass) {
                return false;
            }
            Class<?>[] clsArr = this.interfaces;
            Class<?>[] clsArr2 = javaAdapterSignature.interfaces;
            if (clsArr != clsArr2) {
                if (clsArr.length == clsArr2.length) {
                    int i = 0;
                    while (true) {
                        Class<?>[] clsArr3 = this.interfaces;
                        if (i >= clsArr3.length) {
                            break;
                        } else if (clsArr3[i] != javaAdapterSignature.interfaces[i]) {
                            return false;
                        } else {
                            i++;
                        }
                    }
                } else {
                    return false;
                }
            }
            if (this.names.size() != javaAdapterSignature.names.size()) {
                return false;
            }
            ObjToIntMap.Iterator iterator = new ObjToIntMap.Iterator(this.names);
            iterator.start();
            while (!iterator.done()) {
                int value = iterator.getValue();
                if (value != javaAdapterSignature.names.get((String) iterator.getKey(), value + 1)) {
                    return false;
                }
                iterator.next();
            }
            return true;
        }

        public int hashCode() {
            return (this.superClass.hashCode() + Arrays.hashCode(this.interfaces)) ^ this.names.size();
        }
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        IdFunctionObject idFunctionObject = new IdFunctionObject(new JavaAdapter(), FTAG, 1, "JavaAdapter", 1, scriptable);
        idFunctionObject.markAsConstructor((Scriptable) null);
        if (z) {
            idFunctionObject.sealObject();
        }
        idFunctionObject.exportAsScopeProperty();
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (idFunctionObject.hasTag(FTAG) && idFunctionObject.methodId() == 1) {
            return js_createAdapter(context, scriptable, objArr);
        }
        throw idFunctionObject.unknown();
    }

    public static Object convertResult(Object obj, Class<?> cls) {
        if (obj != Undefined.instance || cls == ScriptRuntime.ObjectClass || cls == ScriptRuntime.StringClass) {
            return Context.jsToJava(obj, cls);
        }
        return null;
    }

    public static Scriptable createAdapterWrapper(Scriptable scriptable, Object obj) {
        NativeJavaObject nativeJavaObject = new NativeJavaObject(ScriptableObject.getTopLevelScope(scriptable), obj, (Class<?>) null, true);
        nativeJavaObject.setPrototype(scriptable);
        return nativeJavaObject;
    }

    public static Object getAdapterSelf(Class<?> cls, Object obj) throws NoSuchFieldException, IllegalAccessException {
        return cls.getDeclaredField(JSONAPISpecConstants.SELF).get(obj);
    }

    static Object js_createAdapter(Context context, Scriptable scriptable, Object[] objArr) {
        Object obj;
        int length = objArr.length;
        if (length != 0) {
            int i = 0;
            while (i < length - 1) {
                Object obj2 = objArr[i];
                if (obj2 instanceof NativeObject) {
                    break;
                } else if (obj2 instanceof NativeJavaClass) {
                    i++;
                } else {
                    throw ScriptRuntime.typeError2("msg.not.java.class.arg", String.valueOf(i), ScriptRuntime.toString(obj2));
                }
            }
            Class<?> cls = null;
            Class[] clsArr = new Class[i];
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Class<?> classObject = objArr[i3].getClassObject();
                if (classObject.isInterface()) {
                    clsArr[i2] = classObject;
                    i2++;
                } else if (cls == null) {
                    cls = classObject;
                } else {
                    throw ScriptRuntime.typeError2("msg.only.one.super", cls.getName(), classObject.getName());
                }
            }
            if (cls == null) {
                cls = ScriptRuntime.ObjectClass;
            }
            Class[] clsArr2 = new Class[i2];
            System.arraycopy(clsArr, 0, clsArr2, 0, i2);
            Scriptable ensureScriptable = ScriptableObject.ensureScriptable(objArr[i]);
            Class<?> adapterClass = getAdapterClass(scriptable, cls, clsArr2, ensureScriptable);
            int i4 = (length - i) - 1;
            if (i4 > 0) {
                try {
                    Object[] objArr2 = new Object[(i4 + 2)];
                    objArr2[0] = ensureScriptable;
                    objArr2[1] = context.getFactory();
                    System.arraycopy(objArr, i + 1, objArr2, 2, i4);
                    NativeJavaMethod nativeJavaMethod = new NativeJavaClass(scriptable, adapterClass, true).members.ctors;
                    int findCachedFunction = nativeJavaMethod.findCachedFunction(context, objArr2);
                    if (findCachedFunction >= 0) {
                        obj = NativeJavaClass.constructInternal(objArr2, nativeJavaMethod.methods[findCachedFunction]);
                    } else {
                        throw Context.reportRuntimeError2("msg.no.java.ctor", adapterClass.getName(), NativeJavaMethod.scriptSignature(objArr));
                    }
                } catch (Exception e) {
                    throw Context.throwAsScriptRuntimeEx(e);
                }
            } else {
                Class[] clsArr3 = {ScriptRuntime.ScriptableClass, ScriptRuntime.ContextFactoryClass};
                obj = adapterClass.getConstructor(clsArr3).newInstance(new Object[]{ensureScriptable, context.getFactory()});
            }
            Object adapterSelf = getAdapterSelf(adapterClass, obj);
            if (adapterSelf instanceof Wrapper) {
                Object unwrap = ((Wrapper) adapterSelf).unwrap();
                if (unwrap instanceof Scriptable) {
                    if (unwrap instanceof ScriptableObject) {
                        ScriptRuntime.setObjectProtoAndParent((ScriptableObject) unwrap, scriptable);
                    }
                    return unwrap;
                }
            }
            return adapterSelf;
        }
        throw ScriptRuntime.typeError0("msg.adapter.zero.args");
    }

    public static void writeAdapterObject(Object obj, ObjectOutputStream objectOutputStream) throws IOException {
        Class<?> cls = obj.getClass();
        objectOutputStream.writeObject(cls.getSuperclass().getName());
        Class[] interfaces = cls.getInterfaces();
        String[] strArr = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            strArr[i] = interfaces[i].getName();
        }
        objectOutputStream.writeObject(strArr);
        try {
            objectOutputStream.writeObject(cls.getField("delegee").get(obj));
        } catch (IllegalAccessException | NoSuchFieldException unused) {
            throw new IOException();
        }
    }

    public static Object readAdapterObject(Scriptable scriptable, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Context currentContext = Context.getCurrentContext();
        ContextFactory factory = currentContext != null ? currentContext.getFactory() : null;
        Class<?> cls = Class.forName((String) objectInputStream.readObject());
        String[] strArr = (String[]) objectInputStream.readObject();
        Class[] clsArr = new Class[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            clsArr[i] = Class.forName(strArr[i]);
        }
        Scriptable scriptable2 = (Scriptable) objectInputStream.readObject();
        try {
            return getAdapterClass(scriptable, cls, clsArr, scriptable2).getConstructor(new Class[]{ScriptRuntime.ContextFactoryClass, ScriptRuntime.ScriptableClass, ScriptRuntime.ScriptableClass}).newInstance(new Object[]{factory, scriptable2, scriptable});
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
            throw new ClassNotFoundException("adapter");
        }
    }

    private static ObjToIntMap getObjectFunctionNames(Scriptable scriptable) {
        Object[] propertyIds = ScriptableObject.getPropertyIds(scriptable);
        ObjToIntMap objToIntMap = new ObjToIntMap(propertyIds.length);
        for (int i = 0; i != propertyIds.length; i++) {
            if (propertyIds[i] instanceof String) {
                String str = (String) propertyIds[i];
                Object property = ScriptableObject.getProperty(scriptable, str);
                if (property instanceof Function) {
                    int int32 = ScriptRuntime.toInt32(ScriptableObject.getProperty((Scriptable) (Function) property, Name.LENGTH));
                    if (int32 < 0) {
                        int32 = 0;
                    }
                    objToIntMap.put(str, int32);
                }
            }
        }
        return objToIntMap;
    }

    private static Class<?> getAdapterClass(Scriptable scriptable, Class<?> cls, Class<?>[] clsArr, Scriptable scriptable2) {
        ClassCache classCache = ClassCache.get(scriptable);
        Map<JavaAdapterSignature, Class<?>> interfaceAdapterCacheMap = classCache.getInterfaceAdapterCacheMap();
        ObjToIntMap objectFunctionNames = getObjectFunctionNames(scriptable2);
        JavaAdapterSignature javaAdapterSignature = new JavaAdapterSignature(cls, clsArr, objectFunctionNames);
        Class<?> cls2 = interfaceAdapterCacheMap.get(javaAdapterSignature);
        if (cls2 == null) {
            String str = "adapter" + classCache.newClassSerialNumber();
            cls2 = loadAdapterClass(str, createAdapterCode(objectFunctionNames, str, cls, clsArr, (String) null));
            if (classCache.isCachingEnabled()) {
                interfaceAdapterCacheMap.put(javaAdapterSignature, cls2);
            }
        }
        return cls2;
    }

    public static byte[] createAdapterCode(ObjToIntMap objToIntMap, String str, Class<?> cls, Class<?>[] clsArr, String str2) {
        int i;
        Method[] methodArr;
        int i2;
        Method[] methodArr2;
        ObjToIntMap objToIntMap2 = objToIntMap;
        String str3 = str;
        Class<?>[] clsArr2 = clsArr;
        String str4 = str2;
        ClassFileWriter classFileWriter = new ClassFileWriter(str3, cls.getName(), "<adapter>");
        classFileWriter.addField("factory", "Lorg/mozilla/javascript/ContextFactory;", 17);
        classFileWriter.addField("delegee", "Lorg/mozilla/javascript/Scriptable;", 17);
        classFileWriter.addField(JSONAPISpecConstants.SELF, "Lorg/mozilla/javascript/Scriptable;", 17);
        if (clsArr2 == null) {
            i = 0;
        } else {
            i = clsArr2.length;
        }
        for (int i3 = 0; i3 < i; i3++) {
            if (clsArr2[i3] != null) {
                classFileWriter.addInterface(clsArr2[i3].getName());
            }
        }
        String replace = cls.getName().replace('.', '/');
        for (Constructor generateCtor : cls.getConstructors()) {
            generateCtor(classFileWriter, str3, replace, generateCtor);
        }
        generateSerialCtor(classFileWriter, str3, replace);
        if (str4 != null) {
            generateEmptyCtor(classFileWriter, str3, replace, str4);
        }
        ObjToIntMap objToIntMap3 = new ObjToIntMap();
        ObjToIntMap objToIntMap4 = new ObjToIntMap();
        for (int i4 = 0; i4 < i; i4++) {
            Method[] methods = clsArr2[i4].getMethods();
            int i5 = 0;
            while (i5 < methods.length) {
                Method method = methods[i5];
                int modifiers = method.getModifiers();
                if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers)) {
                    String name = method.getName();
                    Class[] parameterTypes = method.getParameterTypes();
                    if (!objToIntMap2.has(name)) {
                        try {
                            cls.getMethod(name, parameterTypes);
                        } catch (NoSuchMethodException unused) {
                        }
                    } else {
                        Class<?> cls2 = cls;
                        String str5 = name + getMethodSignature(method, parameterTypes);
                        if (!objToIntMap3.has(str5)) {
                            Class[] clsArr3 = parameterTypes;
                            String str6 = name;
                            String str7 = name;
                            Class[] clsArr4 = clsArr3;
                            i2 = i5;
                            methodArr2 = methods;
                            generateMethod(classFileWriter, str, str6, clsArr4, method.getReturnType(), true);
                            objToIntMap3.put(str5, 0);
                            objToIntMap4.put(str7, 0);
                            i5 = i2 + 1;
                            methods = methodArr2;
                        }
                    }
                }
                i2 = i5;
                methodArr2 = methods;
                i5 = i2 + 1;
                methods = methodArr2;
            }
        }
        int i6 = 0;
        for (Method[] overridableMethods = getOverridableMethods(cls); i6 < overridableMethods.length; overridableMethods = methodArr) {
            Method method2 = overridableMethods[i6];
            boolean isAbstract = Modifier.isAbstract(method2.getModifiers());
            String name2 = method2.getName();
            if (isAbstract || objToIntMap2.has(name2)) {
                Class[] parameterTypes2 = method2.getParameterTypes();
                String methodSignature = getMethodSignature(method2, parameterTypes2);
                String str8 = name2 + methodSignature;
                if (!objToIntMap3.has(str8)) {
                    String str9 = methodSignature;
                    Class[] clsArr5 = parameterTypes2;
                    methodArr = overridableMethods;
                    String str10 = name2;
                    generateMethod(classFileWriter, str, name2, parameterTypes2, method2.getReturnType(), true);
                    objToIntMap3.put(str8, 0);
                    objToIntMap4.put(str10, 0);
                    if (!isAbstract) {
                        generateSuper(classFileWriter, str, replace, str10, str9, clsArr5, method2.getReturnType());
                        i6++;
                        String str11 = str;
                    }
                    i6++;
                    String str112 = str;
                }
            }
            methodArr = overridableMethods;
            i6++;
            String str1122 = str;
        }
        ObjToIntMap.Iterator iterator = new ObjToIntMap.Iterator(objToIntMap2);
        iterator.start();
        while (!iterator.done()) {
            String str12 = (String) iterator.getKey();
            if (!objToIntMap4.has(str12)) {
                int value = iterator.getValue();
                Class[] clsArr6 = new Class[value];
                for (int i7 = 0; i7 < value; i7++) {
                    clsArr6[i7] = ScriptRuntime.ObjectClass;
                }
                generateMethod(classFileWriter, str, str12, clsArr6, ScriptRuntime.ObjectClass, false);
            }
            iterator.next();
        }
        return classFileWriter.toByteArray();
    }

    static Method[] getOverridableMethods(Class<?> cls) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            appendOverridableMethods(cls2, arrayList, hashSet);
        }
        for (Class<? super Object> cls3 = cls; cls3 != null; cls3 = cls3.getSuperclass()) {
            for (Class appendOverridableMethods : cls3.getInterfaces()) {
                appendOverridableMethods(appendOverridableMethods, arrayList, hashSet);
            }
        }
        return (Method[]) arrayList.toArray(new Method[arrayList.size()]);
    }

    private static void appendOverridableMethods(Class<?> cls, ArrayList<Method> arrayList, HashSet<String> hashSet) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            String str = declaredMethods[i].getName() + getMethodSignature(declaredMethods[i], declaredMethods[i].getParameterTypes());
            if (!hashSet.contains(str)) {
                int modifiers = declaredMethods[i].getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    if (Modifier.isFinal(modifiers)) {
                        hashSet.add(str);
                    } else if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers)) {
                        arrayList.add(declaredMethods[i]);
                        hashSet.add(str);
                    }
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.security.CodeSource] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Class<?> loadAdapterClass(java.lang.String r4, byte[] r5) {
        /*
            java.lang.Class r0 = org.mozilla.javascript.SecurityController.getStaticSecurityDomainClass()
            java.lang.Class<java.security.CodeSource> r1 = java.security.CodeSource.class
            r2 = 0
            if (r0 == r1) goto L_0x0010
            java.lang.Class<java.security.ProtectionDomain> r1 = java.security.ProtectionDomain.class
            if (r0 != r1) goto L_0x000e
            goto L_0x0010
        L_0x000e:
            r1 = r2
            goto L_0x0028
        L_0x0010:
            java.security.ProtectionDomain r1 = org.mozilla.javascript.SecurityUtilities.getScriptProtectionDomain()
            if (r1 != 0) goto L_0x001c
            java.lang.Class<org.mozilla.javascript.JavaAdapter> r1 = org.mozilla.javascript.JavaAdapter.class
            java.security.ProtectionDomain r1 = r1.getProtectionDomain()
        L_0x001c:
            java.lang.Class<java.security.CodeSource> r3 = java.security.CodeSource.class
            if (r0 != r3) goto L_0x0028
            if (r1 != 0) goto L_0x0023
            goto L_0x000e
        L_0x0023:
            java.security.CodeSource r0 = r1.getCodeSource()
            r1 = r0
        L_0x0028:
            org.mozilla.javascript.GeneratedClassLoader r0 = org.mozilla.javascript.SecurityController.createLoader(r2, r1)
            java.lang.Class r4 = r0.defineClass(r4, r5)
            r0.linkClass(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.JavaAdapter.loadAdapterClass(java.lang.String, byte[]):java.lang.Class");
    }

    public static Function getFunction(Scriptable scriptable, String str) {
        Object property = ScriptableObject.getProperty(scriptable, str);
        if (property == Scriptable.NOT_FOUND) {
            return null;
        }
        if (property instanceof Function) {
            return (Function) property;
        }
        throw ScriptRuntime.notFunctionError(property, str);
    }

    public static Object callMethod(ContextFactory contextFactory, Scriptable scriptable, Function function, Object[] objArr, long j) {
        if (function == null) {
            return Undefined.instance;
        }
        if (contextFactory == null) {
            contextFactory = ContextFactory.getGlobal();
        }
        final Scriptable parentScope = function.getParentScope();
        if (j == 0) {
            return Context.call(contextFactory, function, parentScope, scriptable, objArr);
        }
        Context currentContext = Context.getCurrentContext();
        if (currentContext != null) {
            return doCall(currentContext, parentScope, scriptable, function, objArr, j);
        }
        final Scriptable scriptable2 = scriptable;
        final Function function2 = function;
        final Object[] objArr2 = objArr;
        final long j2 = j;
        return contextFactory.call(new ContextAction() {
            public Object run(Context context) {
                return JavaAdapter.doCall(context, parentScope, scriptable2, function2, objArr2, j2);
            }
        });
    }

    /* access modifiers changed from: private */
    public static Object doCall(Context context, Scriptable scriptable, Scriptable scriptable2, Function function, Object[] objArr, long j) {
        for (int i = 0; i != objArr.length; i++) {
            if (0 != (((long) (1 << i)) & j)) {
                Object obj = objArr[i];
                if (!(obj instanceof Scriptable)) {
                    objArr[i] = context.getWrapFactory().wrap(context, scriptable, obj, (Class<?>) null);
                }
            }
        }
        return function.call(context, scriptable, scriptable2, objArr);
    }

    public static Scriptable runScript(final Script script) {
        return (Scriptable) ContextFactory.getGlobal().call(new ContextAction() {
            public Object run(Context context) {
                ScriptableObject global = ScriptRuntime.getGlobal(context);
                script.exec(context, global);
                return global;
            }
        });
    }

    private static void generateCtor(ClassFileWriter classFileWriter, String str, String str2, Constructor<?> constructor) {
        Class[] parameterTypes = constructor.getParameterTypes();
        short s = 3;
        if (parameterTypes.length == 0) {
            classFileWriter.startMethod(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ContextFactory;)V", 1);
            classFileWriter.add(42);
            classFileWriter.addInvoke(183, str2, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        } else {
            StringBuilder sb = new StringBuilder("(Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/ContextFactory;");
            int length = sb.length();
            for (Class appendTypeString : parameterTypes) {
                appendTypeString(sb, appendTypeString);
            }
            sb.append(")V");
            classFileWriter.startMethod(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, sb.toString(), 1);
            classFileWriter.add(42);
            for (Class generatePushParam : parameterTypes) {
                s = (short) (s + generatePushParam(classFileWriter, s, generatePushParam));
            }
            sb.delete(1, length);
            classFileWriter.addInvoke(183, str2, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, sb.toString());
        }
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(44);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "createAdapterWrapper", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(181, str, JSONAPISpecConstants.SELF, "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod(s);
    }

    private static void generateSerialCtor(ClassFileWriter classFileWriter, String str, String str2) {
        classFileWriter.startMethod(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "(Lorg/mozilla/javascript/ContextFactory;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Scriptable;)V", 1);
        classFileWriter.add(42);
        classFileWriter.addInvoke(183, str2, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(44);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(45);
        classFileWriter.add(181, str, JSONAPISpecConstants.SELF, "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod(4);
    }

    private static void generateEmptyCtor(ClassFileWriter classFileWriter, String str, String str2, String str3) {
        classFileWriter.startMethod(MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V", 1);
        classFileWriter.add(42);
        classFileWriter.addInvoke(183, str2, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        classFileWriter.add(42);
        classFileWriter.add(1);
        classFileWriter.add(181, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(187, str3);
        classFileWriter.add(89);
        classFileWriter.addInvoke(183, str3, MethodDescription.CONSTRUCTOR_INTERNAL_NAME, "()V");
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "runScript", "(Lorg/mozilla/javascript/Script;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(76);
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(181, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(43);
        classFileWriter.add(42);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "createAdapterWrapper", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/Object;)Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(181, str, JSONAPISpecConstants.SELF, "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(177);
        classFileWriter.stopMethod(2);
    }

    static void generatePushWrappedArgs(ClassFileWriter classFileWriter, Class<?>[] clsArr, int i) {
        classFileWriter.addPush(i);
        classFileWriter.add(189, TypeProxy.SilentConstruction.Appender.JAVA_LANG_OBJECT_INTERNAL_NAME);
        int i2 = 1;
        for (int i3 = 0; i3 != clsArr.length; i3++) {
            classFileWriter.add(89);
            classFileWriter.addPush(i3);
            i2 += generateWrapArg(classFileWriter, i2, clsArr[i3]);
            classFileWriter.add(83);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0068, code lost:
        if (r9 != 's') goto L_0x0090;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int generateWrapArg(org.mozilla.classfile.ClassFileWriter r7, int r8, java.lang.Class<?> r9) {
        /*
            boolean r0 = r9.isPrimitive()
            r1 = 1
            if (r0 != 0) goto L_0x000e
            r9 = 25
            r7.add((int) r9, (int) r8)
            goto L_0x0095
        L_0x000e:
            java.lang.Class r0 = java.lang.Boolean.TYPE
            java.lang.String r2 = "<init>"
            r3 = 183(0xb7, float:2.56E-43)
            r4 = 89
            r5 = 187(0xbb, float:2.62E-43)
            r6 = 21
            if (r9 != r0) goto L_0x002d
            java.lang.String r9 = "java/lang/Boolean"
            r7.add((int) r5, (java.lang.String) r9)
            r7.add(r4)
            r7.add((int) r6, (int) r8)
            java.lang.String r8 = "(Z)V"
            r7.addInvoke(r3, r9, r2, r8)
            goto L_0x0095
        L_0x002d:
            java.lang.Class r0 = java.lang.Character.TYPE
            if (r9 != r0) goto L_0x0040
            r7.add((int) r6, (int) r8)
            r8 = 184(0xb8, float:2.58E-43)
            java.lang.String r9 = "java/lang/String"
            java.lang.String r0 = "valueOf"
            java.lang.String r2 = "(C)Ljava/lang/String;"
            r7.addInvoke(r8, r9, r0, r2)
            goto L_0x0095
        L_0x0040:
            java.lang.String r0 = "java/lang/Double"
            r7.add((int) r5, (java.lang.String) r0)
            r7.add(r4)
            java.lang.String r9 = r9.getName()
            r4 = 0
            char r9 = r9.charAt(r4)
            r4 = 98
            r5 = 2
            if (r9 == r4) goto L_0x0088
            r4 = 100
            if (r9 == r4) goto L_0x0081
            r4 = 102(0x66, float:1.43E-43)
            if (r9 == r4) goto L_0x0076
            r4 = 105(0x69, float:1.47E-43)
            if (r9 == r4) goto L_0x0088
            r4 = 108(0x6c, float:1.51E-43)
            if (r9 == r4) goto L_0x006b
            r4 = 115(0x73, float:1.61E-43)
            if (r9 == r4) goto L_0x0088
            goto L_0x0090
        L_0x006b:
            r9 = 22
            r7.add((int) r9, (int) r8)
            r8 = 138(0x8a, float:1.93E-43)
            r7.add(r8)
            goto L_0x0086
        L_0x0076:
            r9 = 23
            r7.add((int) r9, (int) r8)
            r8 = 141(0x8d, float:1.98E-43)
            r7.add(r8)
            goto L_0x0090
        L_0x0081:
            r9 = 24
            r7.add((int) r9, (int) r8)
        L_0x0086:
            r1 = 2
            goto L_0x0090
        L_0x0088:
            r7.add((int) r6, (int) r8)
            r8 = 135(0x87, float:1.89E-43)
            r7.add(r8)
        L_0x0090:
            java.lang.String r8 = "(D)V"
            r7.addInvoke(r3, r0, r2, r8)
        L_0x0095:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.JavaAdapter.generateWrapArg(org.mozilla.classfile.ClassFileWriter, int, java.lang.Class):int");
    }

    static void generateReturnResult(ClassFileWriter classFileWriter, Class<?> cls, boolean z) {
        if (cls == Void.TYPE) {
            classFileWriter.add(87);
            classFileWriter.add(177);
        } else if (cls == Boolean.TYPE) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toBoolean", "(Ljava/lang/Object;)Z");
            classFileWriter.add(172);
        } else if (cls == Character.TYPE) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toString", "(Ljava/lang/Object;)Ljava/lang/String;");
            classFileWriter.add(3);
            classFileWriter.addInvoke(182, "java/lang/String", "charAt", "(I)C");
            classFileWriter.add(172);
        } else if (cls.isPrimitive()) {
            classFileWriter.addInvoke(184, "org/mozilla/javascript/Context", "toNumber", "(Ljava/lang/Object;)D");
            char charAt = cls.getName().charAt(0);
            if (charAt != 'b') {
                if (charAt == 'd') {
                    classFileWriter.add(175);
                    return;
                } else if (charAt == 'f') {
                    classFileWriter.add(144);
                    classFileWriter.add(174);
                    return;
                } else if (charAt != 'i') {
                    if (charAt == 'l') {
                        classFileWriter.add(143);
                        classFileWriter.add(173);
                        return;
                    } else if (charAt != 's') {
                        throw new RuntimeException("Unexpected return type " + cls.toString());
                    }
                }
            }
            classFileWriter.add(142);
            classFileWriter.add(172);
        } else {
            String name = cls.getName();
            if (z) {
                classFileWriter.addLoadConstant(name);
                classFileWriter.addInvoke(184, TypeProxy.SilentConstruction.Appender.JAVA_LANG_CLASS_INTERNAL_NAME, "forName", "(Ljava/lang/String;)Ljava/lang/Class;");
                classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "convertResult", "(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;");
            }
            classFileWriter.add(192, name);
            classFileWriter.add(176);
        }
    }

    private static void generateMethod(ClassFileWriter classFileWriter, String str, String str2, Class<?>[] clsArr, Class<?> cls, boolean z) {
        StringBuilder sb = new StringBuilder();
        int appendMethodSignature = appendMethodSignature(clsArr, cls, sb);
        classFileWriter.startMethod(str2, sb.toString(), 1);
        classFileWriter.add(42);
        classFileWriter.add(180, str, "factory", "Lorg/mozilla/javascript/ContextFactory;");
        classFileWriter.add(42);
        classFileWriter.add(180, str, JSONAPISpecConstants.SELF, "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.add(42);
        classFileWriter.add(180, str, "delegee", "Lorg/mozilla/javascript/Scriptable;");
        classFileWriter.addPush(str2);
        classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "getFunction", "(Lorg/mozilla/javascript/Scriptable;Ljava/lang/String;)Lorg/mozilla/javascript/Function;");
        generatePushWrappedArgs(classFileWriter, clsArr, clsArr.length);
        if (clsArr.length <= 64) {
            long j = 0;
            for (int i = 0; i != clsArr.length; i++) {
                if (!clsArr[i].isPrimitive()) {
                    j |= (long) (1 << i);
                }
            }
            classFileWriter.addPush(j);
            classFileWriter.addInvoke(184, "org/mozilla/javascript/JavaAdapter", "callMethod", "(Lorg/mozilla/javascript/ContextFactory;Lorg/mozilla/javascript/Scriptable;Lorg/mozilla/javascript/Function;[Ljava/lang/Object;J)Ljava/lang/Object;");
            generateReturnResult(classFileWriter, cls, z);
            classFileWriter.stopMethod((short) appendMethodSignature);
            return;
        }
        throw Context.reportRuntimeError0("JavaAdapter can not subclass methods with more then 64 arguments.");
    }

    private static int generatePushParam(ClassFileWriter classFileWriter, int i, Class<?> cls) {
        if (!cls.isPrimitive()) {
            classFileWriter.addALoad(i);
            return 1;
        }
        char charAt = cls.getName().charAt(0);
        if (charAt != 'f') {
            if (charAt != 'i') {
                if (charAt == 'l') {
                    classFileWriter.addLLoad(i);
                    return 2;
                } else if (!(charAt == 's' || charAt == 'z')) {
                    switch (charAt) {
                        case 'b':
                        case 'c':
                            break;
                        case 'd':
                            classFileWriter.addDLoad(i);
                            return 2;
                        default:
                            throw Kit.codeBug();
                    }
                }
            }
            classFileWriter.addILoad(i);
            return 1;
        }
        classFileWriter.addFLoad(i);
        return 1;
    }

    private static void generatePopResult(ClassFileWriter classFileWriter, Class<?> cls) {
        if (cls.isPrimitive()) {
            char charAt = cls.getName().charAt(0);
            if (charAt != 'f') {
                if (charAt != 'i') {
                    if (charAt == 'l') {
                        classFileWriter.add(173);
                        return;
                    } else if (!(charAt == 's' || charAt == 'z')) {
                        switch (charAt) {
                            case 'b':
                            case 'c':
                                break;
                            case 'd':
                                classFileWriter.add(175);
                                return;
                            default:
                                return;
                        }
                    }
                }
                classFileWriter.add(172);
                return;
            }
            classFileWriter.add(174);
            return;
        }
        classFileWriter.add(176);
    }

    private static void generateSuper(ClassFileWriter classFileWriter, String str, String str2, String str3, String str4, Class<?>[] clsArr, Class<?> cls) {
        classFileWriter.startMethod("super$" + str3, str4, 1);
        classFileWriter.add(25, 0);
        int i = 1;
        for (Class<?> generatePushParam : clsArr) {
            i += generatePushParam(classFileWriter, i, generatePushParam);
        }
        classFileWriter.addInvoke(183, str2, str3, str4);
        if (!cls.equals(Void.TYPE)) {
            generatePopResult(classFileWriter, cls);
        } else {
            classFileWriter.add(177);
        }
        classFileWriter.stopMethod((short) (i + 1));
    }

    private static String getMethodSignature(Method method, Class<?>[] clsArr) {
        StringBuilder sb = new StringBuilder();
        appendMethodSignature(clsArr, method.getReturnType(), sb);
        return sb.toString();
    }

    static int appendMethodSignature(Class<?>[] clsArr, Class<?> cls, StringBuilder sb) {
        sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        int length = clsArr.length + 1;
        for (Class<?> cls2 : clsArr) {
            appendTypeString(sb, cls2);
            if (cls2 == Long.TYPE || cls2 == Double.TYPE) {
                length++;
            }
        }
        sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        appendTypeString(sb, cls);
        return length;
    }

    private static StringBuilder appendTypeString(StringBuilder sb, Class<?> cls) {
        char c;
        while (cls.isArray()) {
            sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            cls = cls.getComponentType();
        }
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                c = ASCIIPropertyListParser.DATE_APPLE_END_TOKEN;
            } else if (cls == Long.TYPE) {
                c = 'J';
            } else {
                c = Character.toUpperCase(cls.getName().charAt(0));
            }
            sb.append(c);
        } else {
            sb.append('L');
            sb.append(cls.getName().replace('.', '/'));
            sb.append(';');
        }
        return sb;
    }

    static int[] getArgsToConvert(Class<?>[] clsArr) {
        int i = 0;
        for (int i2 = 0; i2 != clsArr.length; i2++) {
            if (!clsArr[i2].isPrimitive()) {
                i++;
            }
        }
        if (i == 0) {
            return null;
        }
        int[] iArr = new int[i];
        int i3 = 0;
        for (int i4 = 0; i4 != clsArr.length; i4++) {
            if (!clsArr[i4].isPrimitive()) {
                iArr[i3] = i4;
                i3++;
            }
        }
        return iArr;
    }
}
