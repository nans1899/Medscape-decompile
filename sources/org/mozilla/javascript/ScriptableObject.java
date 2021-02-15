package org.mozilla.javascript;

import com.google.android.gms.ads.AdError;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.slideshow.SlideshowPageFragment;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.asm.Advice;
import org.mozilla.javascript.TopLevel;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSGetter;
import org.mozilla.javascript.annotations.JSSetter;
import org.mozilla.javascript.annotations.JSStaticFunction;
import org.mozilla.javascript.debug.DebuggableObject;

public abstract class ScriptableObject implements Scriptable, Serializable, DebuggableObject, ConstProperties {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int CONST = 13;
    public static final int DONTENUM = 2;
    public static final int EMPTY = 0;
    private static final int INITIAL_SLOT_SIZE = 4;
    public static final int PERMANENT = 4;
    public static final int READONLY = 1;
    private static final int SLOT_CONVERT_ACCESSOR_TO_DATA = 5;
    private static final int SLOT_MODIFY = 2;
    private static final int SLOT_MODIFY_CONST = 3;
    private static final int SLOT_MODIFY_GETTER_SETTER = 4;
    private static final int SLOT_QUERY = 1;
    public static final int UNINITIALIZED_CONST = 8;
    static final long serialVersionUID = 2829861078851942586L;
    private volatile Map<Object, Object> associatedValues;
    private int count;
    private transient Slot firstAdded;
    private boolean isExtensible = true;
    private transient Slot lastAdded;
    private Scriptable parentScopeObject;
    private Scriptable prototypeObject;
    private transient Slot[] slots;

    private static int getSlotIndex(int i, int i2) {
        return (i - 1) & i2;
    }

    public boolean avoidObjectDetection() {
        return false;
    }

    public abstract String getClassName();

    private static class Slot implements Serializable {
        private static final long serialVersionUID = -6090581677123995491L;
        /* access modifiers changed from: private */
        public volatile short attributes;
        int indexOrHash;
        String name;
        transient Slot next;
        volatile transient Slot orderedNext;
        volatile Object value;
        volatile transient boolean wasDeleted;

        Slot(String str, int i, int i2) {
            this.name = str;
            this.indexOrHash = i;
            this.attributes = (short) i2;
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            String str = this.name;
            if (str != null) {
                this.indexOrHash = str.hashCode();
            }
        }

        /* access modifiers changed from: package-private */
        public boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
            if ((this.attributes & 1) != 0) {
                return true;
            }
            if (scriptable != scriptable2) {
                return false;
            }
            this.value = obj;
            return true;
        }

        /* access modifiers changed from: package-private */
        public Object getValue(Scriptable scriptable) {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public int getAttributes() {
            return this.attributes;
        }

        /* access modifiers changed from: package-private */
        public synchronized void setAttributes(int i) {
            ScriptableObject.checkValidAttributes(i);
            this.attributes = (short) i;
        }

        /* access modifiers changed from: package-private */
        public void markDeleted() {
            this.wasDeleted = true;
            this.value = null;
            this.name = null;
        }

        /* access modifiers changed from: package-private */
        public ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
            return ScriptableObject.buildDataDescriptor(scriptable, this.value, this.attributes);
        }
    }

    protected static ScriptableObject buildDataDescriptor(Scriptable scriptable, Object obj, int i) {
        NativeObject nativeObject = new NativeObject();
        ScriptRuntime.setBuiltinProtoAndParent(nativeObject, scriptable, TopLevel.Builtins.Object);
        nativeObject.defineProperty("value", obj, 0);
        boolean z = true;
        nativeObject.defineProperty("writable", (Object) Boolean.valueOf((i & 1) == 0), 0);
        nativeObject.defineProperty("enumerable", (Object) Boolean.valueOf((i & 2) == 0), 0);
        if ((i & 4) != 0) {
            z = false;
        }
        nativeObject.defineProperty("configurable", (Object) Boolean.valueOf(z), 0);
        return nativeObject;
    }

    private static final class GetterSlot extends Slot {
        static final long serialVersionUID = -4900574849788797588L;
        Object getter;
        Object setter;

        GetterSlot(String str, int i, int i2) {
            super(str, i, i2);
        }

        /* access modifiers changed from: package-private */
        public ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
            int attributes = getAttributes();
            NativeObject nativeObject = new NativeObject();
            ScriptRuntime.setBuiltinProtoAndParent(nativeObject, scriptable, TopLevel.Builtins.Object);
            boolean z = true;
            nativeObject.defineProperty("enumerable", (Object) Boolean.valueOf((attributes & 2) == 0), 0);
            if ((attributes & 4) != 0) {
                z = false;
            }
            nativeObject.defineProperty("configurable", (Object) Boolean.valueOf(z), 0);
            Object obj = this.getter;
            if (obj != null) {
                nativeObject.defineProperty("get", obj, 0);
            }
            Object obj2 = this.setter;
            if (obj2 != null) {
                nativeObject.defineProperty("set", obj2, 0);
            }
            return nativeObject;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: org.mozilla.javascript.Scriptable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: org.mozilla.javascript.Scriptable} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean setValue(java.lang.Object r6, org.mozilla.javascript.Scriptable r7, org.mozilla.javascript.Scriptable r8) {
            /*
                r5 = this;
                java.lang.Object r0 = r5.setter
                r1 = 1
                if (r0 != 0) goto L_0x0024
                java.lang.Object r0 = r5.getter
                if (r0 == 0) goto L_0x001f
                org.mozilla.javascript.Context r6 = org.mozilla.javascript.Context.getContext()
                r7 = 11
                boolean r6 = r6.hasFeature(r7)
                if (r6 != 0) goto L_0x0016
                return r1
            L_0x0016:
                java.lang.String r6 = r5.name
                java.lang.String r7 = "msg.set.prop.no.setter"
                org.mozilla.javascript.EcmaError r6 = org.mozilla.javascript.ScriptRuntime.typeError1(r7, r6)
                throw r6
            L_0x001f:
                boolean r6 = super.setValue(r6, r7, r8)
                return r6
            L_0x0024:
                org.mozilla.javascript.Context r7 = org.mozilla.javascript.Context.getContext()
                java.lang.Object r0 = r5.setter
                boolean r2 = r0 instanceof org.mozilla.javascript.MemberBox
                r3 = 0
                if (r2 == 0) goto L_0x0057
                org.mozilla.javascript.MemberBox r0 = (org.mozilla.javascript.MemberBox) r0
                java.lang.Class<?>[] r2 = r0.argTypes
                int r4 = r2.length
                int r4 = r4 - r1
                r2 = r2[r4]
                int r2 = org.mozilla.javascript.FunctionObject.getTypeTag(r2)
                java.lang.Object r6 = org.mozilla.javascript.FunctionObject.convertArg((org.mozilla.javascript.Context) r7, (org.mozilla.javascript.Scriptable) r8, (java.lang.Object) r6, (int) r2)
                java.lang.Object r7 = r0.delegateTo
                if (r7 != 0) goto L_0x0048
                java.lang.Object[] r7 = new java.lang.Object[r1]
                r7[r3] = r6
                goto L_0x0053
            L_0x0048:
                java.lang.Object r7 = r0.delegateTo
                r2 = 2
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r2[r3] = r8
                r2[r1] = r6
                r8 = r7
                r7 = r2
            L_0x0053:
                r0.invoke(r8, r7)
                goto L_0x0068
            L_0x0057:
                boolean r2 = r0 instanceof org.mozilla.javascript.Function
                if (r2 == 0) goto L_0x0068
                org.mozilla.javascript.Function r0 = (org.mozilla.javascript.Function) r0
                org.mozilla.javascript.Scriptable r2 = r0.getParentScope()
                java.lang.Object[] r4 = new java.lang.Object[r1]
                r4[r3] = r6
                r0.call(r7, r2, r8, r4)
            L_0x0068:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptableObject.GetterSlot.setValue(java.lang.Object, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable):boolean");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: org.mozilla.javascript.Scriptable} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: org.mozilla.javascript.Scriptable} */
        /* access modifiers changed from: package-private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getValue(org.mozilla.javascript.Scriptable r5) {
            /*
                r4 = this;
                java.lang.Object r0 = r4.getter
                if (r0 == 0) goto L_0x0035
                boolean r1 = r0 instanceof org.mozilla.javascript.MemberBox
                if (r1 == 0) goto L_0x0020
                org.mozilla.javascript.MemberBox r0 = (org.mozilla.javascript.MemberBox) r0
                java.lang.Object r1 = r0.delegateTo
                if (r1 != 0) goto L_0x0011
                java.lang.Object[] r1 = org.mozilla.javascript.ScriptRuntime.emptyArgs
                goto L_0x001b
            L_0x0011:
                java.lang.Object r1 = r0.delegateTo
                r2 = 1
                java.lang.Object[] r2 = new java.lang.Object[r2]
                r3 = 0
                r2[r3] = r5
                r5 = r1
                r1 = r2
            L_0x001b:
                java.lang.Object r5 = r0.invoke(r5, r1)
                return r5
            L_0x0020:
                boolean r1 = r0 instanceof org.mozilla.javascript.Function
                if (r1 == 0) goto L_0x0035
                org.mozilla.javascript.Function r0 = (org.mozilla.javascript.Function) r0
                org.mozilla.javascript.Context r1 = org.mozilla.javascript.Context.getContext()
                org.mozilla.javascript.Scriptable r2 = r0.getParentScope()
                java.lang.Object[] r3 = org.mozilla.javascript.ScriptRuntime.emptyArgs
                java.lang.Object r5 = r0.call(r1, r2, r5, r3)
                return r5
            L_0x0035:
                java.lang.Object r5 = r4.value
                boolean r0 = r5 instanceof org.mozilla.javascript.LazilyLoadedCtor
                if (r0 == 0) goto L_0x004f
                org.mozilla.javascript.LazilyLoadedCtor r5 = (org.mozilla.javascript.LazilyLoadedCtor) r5
                r5.init()     // Catch:{ all -> 0x0047 }
                java.lang.Object r5 = r5.getValue()
                r4.value = r5
                goto L_0x004f
            L_0x0047:
                r0 = move-exception
                java.lang.Object r5 = r5.getValue()
                r4.value = r5
                throw r0
            L_0x004f:
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptableObject.GetterSlot.getValue(org.mozilla.javascript.Scriptable):java.lang.Object");
        }

        /* access modifiers changed from: package-private */
        public void markDeleted() {
            super.markDeleted();
            this.getter = null;
            this.setter = null;
        }
    }

    private static class RelinkedSlot extends Slot {
        final Slot slot;

        RelinkedSlot(Slot slot2) {
            super(slot2.name, slot2.indexOrHash, slot2.attributes);
            this.slot = ScriptableObject.unwrapSlot(slot2);
        }

        /* access modifiers changed from: package-private */
        public boolean setValue(Object obj, Scriptable scriptable, Scriptable scriptable2) {
            return this.slot.setValue(obj, scriptable, scriptable2);
        }

        /* access modifiers changed from: package-private */
        public Object getValue(Scriptable scriptable) {
            return this.slot.getValue(scriptable);
        }

        /* access modifiers changed from: package-private */
        public ScriptableObject getPropertyDescriptor(Context context, Scriptable scriptable) {
            return this.slot.getPropertyDescriptor(context, scriptable);
        }

        /* access modifiers changed from: package-private */
        public int getAttributes() {
            return this.slot.getAttributes();
        }

        /* access modifiers changed from: package-private */
        public void setAttributes(int i) {
            this.slot.setAttributes(i);
        }

        /* access modifiers changed from: package-private */
        public void markDeleted() {
            super.markDeleted();
            this.slot.markDeleted();
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(this.slot);
        }
    }

    static void checkValidAttributes(int i) {
        if ((i & -16) != 0) {
            throw new IllegalArgumentException(String.valueOf(i));
        }
    }

    public ScriptableObject() {
    }

    public ScriptableObject(Scriptable scriptable, Scriptable scriptable2) {
        if (scriptable != null) {
            this.parentScopeObject = scriptable;
            this.prototypeObject = scriptable2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public String getTypeOf() {
        return avoidObjectDetection() ? AdError.UNDEFINED_DOMAIN : SlideshowPageFragment.ARG_OBJECT;
    }

    public boolean has(String str, Scriptable scriptable) {
        return getSlot(str, 0, 1) != null;
    }

    public boolean has(int i, Scriptable scriptable) {
        return getSlot((String) null, i, 1) != null;
    }

    public Object get(String str, Scriptable scriptable) {
        Slot slot = getSlot(str, 0, 1);
        if (slot == null) {
            return Scriptable.NOT_FOUND;
        }
        return slot.getValue(scriptable);
    }

    public Object get(int i, Scriptable scriptable) {
        Slot slot = getSlot((String) null, i, 1);
        if (slot == null) {
            return Scriptable.NOT_FOUND;
        }
        return slot.getValue(scriptable);
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        if (!putImpl(str, 0, scriptable, obj)) {
            if (scriptable != this) {
                scriptable.put(str, scriptable, obj);
                return;
            }
            throw Kit.codeBug();
        }
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        if (!putImpl((String) null, i, scriptable, obj)) {
            if (scriptable != this) {
                scriptable.put(i, scriptable, obj);
                return;
            }
            throw Kit.codeBug();
        }
    }

    public void delete(String str) {
        checkNotSealed(str, 0);
        removeSlot(str, 0);
    }

    public void delete(int i) {
        checkNotSealed((String) null, i);
        removeSlot((String) null, i);
    }

    public void putConst(String str, Scriptable scriptable, Object obj) {
        if (!putConstImpl(str, 0, scriptable, obj, 1)) {
            if (scriptable == this) {
                throw Kit.codeBug();
            } else if (scriptable instanceof ConstProperties) {
                ((ConstProperties) scriptable).putConst(str, scriptable, obj);
            } else {
                scriptable.put(str, scriptable, obj);
            }
        }
    }

    public void defineConst(String str, Scriptable scriptable) {
        if (!putConstImpl(str, 0, scriptable, Undefined.instance, 8)) {
            if (scriptable == this) {
                throw Kit.codeBug();
            } else if (scriptable instanceof ConstProperties) {
                ((ConstProperties) scriptable).defineConst(str, scriptable);
            }
        }
    }

    public boolean isConst(String str) {
        Slot slot = getSlot(str, 0, 1);
        if (slot != null && (slot.getAttributes() & 5) == 5) {
            return true;
        }
        return false;
    }

    public final int getAttributes(String str, Scriptable scriptable) {
        return getAttributes(str);
    }

    public final int getAttributes(int i, Scriptable scriptable) {
        return getAttributes(i);
    }

    public final void setAttributes(String str, Scriptable scriptable, int i) {
        setAttributes(str, i);
    }

    public void setAttributes(int i, Scriptable scriptable, int i2) {
        setAttributes(i, i2);
    }

    public int getAttributes(String str) {
        return findAttributeSlot(str, 0, 1).getAttributes();
    }

    public int getAttributes(int i) {
        return findAttributeSlot((String) null, i, 1).getAttributes();
    }

    public void setAttributes(String str, int i) {
        checkNotSealed(str, 0);
        findAttributeSlot(str, 0, 2).setAttributes(i);
    }

    public void setAttributes(int i, int i2) {
        checkNotSealed((String) null, i);
        findAttributeSlot((String) null, i, 2).setAttributes(i2);
    }

    public void setGetterOrSetter(String str, int i, Callable callable, boolean z) {
        setGetterOrSetter(str, i, callable, z, false);
    }

    private void setGetterOrSetter(String str, int i, Callable callable, boolean z, boolean z2) {
        GetterSlot getterSlot;
        if (str == null || i == 0) {
            if (!z2) {
                checkNotSealed(str, i);
            }
            if (isExtensible()) {
                getterSlot = (GetterSlot) getSlot(str, i, 4);
            } else {
                Slot unwrapSlot = unwrapSlot(getSlot(str, i, 1));
                if (unwrapSlot instanceof GetterSlot) {
                    getterSlot = (GetterSlot) unwrapSlot;
                } else {
                    return;
                }
            }
            if (z2 || (getterSlot.getAttributes() & 1) == 0) {
                if (z) {
                    getterSlot.setter = callable;
                } else {
                    getterSlot.getter = callable;
                }
                getterSlot.value = Undefined.instance;
                return;
            }
            throw Context.reportRuntimeError1("msg.modify.readonly", str);
        }
        throw new IllegalArgumentException(str);
    }

    public Object getGetterOrSetter(String str, int i, boolean z) {
        if (str == null || i == 0) {
            Slot unwrapSlot = unwrapSlot(getSlot(str, i, 1));
            if (unwrapSlot == null) {
                return null;
            }
            if (!(unwrapSlot instanceof GetterSlot)) {
                return Undefined.instance;
            }
            GetterSlot getterSlot = (GetterSlot) unwrapSlot;
            Object obj = z ? getterSlot.setter : getterSlot.getter;
            return obj != null ? obj : Undefined.instance;
        }
        throw new IllegalArgumentException(str);
    }

    /* access modifiers changed from: protected */
    public boolean isGetterOrSetter(String str, int i, boolean z) {
        Slot unwrapSlot = unwrapSlot(getSlot(str, i, 1));
        if (!(unwrapSlot instanceof GetterSlot)) {
            return false;
        }
        if (z && ((GetterSlot) unwrapSlot).setter != null) {
            return true;
        }
        if (z || ((GetterSlot) unwrapSlot).getter == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addLazilyInitializedValue(String str, int i, LazilyLoadedCtor lazilyLoadedCtor, int i2) {
        if (str == null || i == 0) {
            checkNotSealed(str, i);
            GetterSlot getterSlot = (GetterSlot) getSlot(str, i, 4);
            getterSlot.setAttributes(i2);
            getterSlot.getter = null;
            getterSlot.setter = null;
            getterSlot.value = lazilyLoadedCtor;
            return;
        }
        throw new IllegalArgumentException(str);
    }

    public Scriptable getPrototype() {
        return this.prototypeObject;
    }

    public void setPrototype(Scriptable scriptable) {
        this.prototypeObject = scriptable;
    }

    public Scriptable getParentScope() {
        return this.parentScopeObject;
    }

    public void setParentScope(Scriptable scriptable) {
        this.parentScopeObject = scriptable;
    }

    public Object[] getIds() {
        return getIds(false);
    }

    public Object[] getAllIds() {
        return getIds(true);
    }

    public Object getDefaultValue(Class<?> cls) {
        return getDefaultValue(this, cls);
    }

    public static Object getDefaultValue(Scriptable scriptable, Class<?> cls) {
        String str;
        Object[] objArr;
        Object call;
        Context context = null;
        int i = 0;
        while (true) {
            String str2 = AdError.UNDEFINED_DOMAIN;
            if (i < 2) {
                boolean z = cls != ScriptRuntime.StringClass ? i == 1 : i == 0;
                if (z) {
                    objArr = ScriptRuntime.emptyArgs;
                    str = "toString";
                } else {
                    Object[] objArr2 = new Object[1];
                    if (cls != null) {
                        if (cls == ScriptRuntime.StringClass) {
                            str2 = "string";
                        } else if (cls == ScriptRuntime.ScriptableClass) {
                            str2 = SlideshowPageFragment.ARG_OBJECT;
                        } else if (cls == ScriptRuntime.FunctionClass) {
                            str2 = "function";
                        } else if (cls == ScriptRuntime.BooleanClass || cls == Boolean.TYPE) {
                            str2 = "boolean";
                        } else if (cls == ScriptRuntime.NumberClass || cls == ScriptRuntime.ByteClass || cls == Byte.TYPE || cls == ScriptRuntime.ShortClass || cls == Short.TYPE || cls == ScriptRuntime.IntegerClass || cls == Integer.TYPE || cls == ScriptRuntime.FloatClass || cls == Float.TYPE || cls == ScriptRuntime.DoubleClass || cls == Double.TYPE) {
                            str2 = "number";
                        } else {
                            throw Context.reportRuntimeError1("msg.invalid.type", cls.toString());
                        }
                    }
                    objArr2[0] = str2;
                    Object[] objArr3 = objArr2;
                    str = "valueOf";
                    objArr = objArr3;
                }
                Object property = getProperty(scriptable, str);
                if (property instanceof Function) {
                    Function function = (Function) property;
                    if (context == null) {
                        context = Context.getContext();
                    }
                    call = function.call(context, function.getParentScope(), scriptable, objArr);
                    if (call == null) {
                        continue;
                    } else if (!(!(call instanceof Scriptable) || cls == ScriptRuntime.ScriptableClass || cls == ScriptRuntime.FunctionClass)) {
                        if (z && (call instanceof Wrapper)) {
                            Object unwrap = ((Wrapper) call).unwrap();
                            if (unwrap instanceof String) {
                                return unwrap;
                            }
                        }
                    }
                }
                i++;
            } else {
                if (cls != null) {
                    str2 = cls.getName();
                }
                throw ScriptRuntime.typeError1("msg.default.value", str2);
            }
        }
        return call;
    }

    public boolean hasInstance(Scriptable scriptable) {
        return ScriptRuntime.jsDelegatesTo(scriptable, this);
    }

    /* access modifiers changed from: protected */
    public Object equivalentValues(Object obj) {
        return this == obj ? Boolean.TRUE : Scriptable.NOT_FOUND;
    }

    public static <T extends Scriptable> void defineClass(Scriptable scriptable, Class<T> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        defineClass(scriptable, cls, false, false);
    }

    public static <T extends Scriptable> void defineClass(Scriptable scriptable, Class<T> cls, boolean z) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        defineClass(scriptable, cls, z, false);
    }

    public static <T extends Scriptable> String defineClass(Scriptable scriptable, Class<T> cls, boolean z, boolean z2) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        BaseFunction buildClassCtor = buildClassCtor(scriptable, cls, z, z2);
        if (buildClassCtor == null) {
            return null;
        }
        String className = buildClassCtor.getClassPrototype().getClassName();
        defineProperty(scriptable, className, buildClassCtor, 2);
        return className;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: org.mozilla.javascript.FunctionObject} */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0207, code lost:
        if (r4 == null) goto L_0x0194;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x0219  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x021e  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x02d4  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x02ac A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0145  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T extends org.mozilla.javascript.Scriptable> org.mozilla.javascript.BaseFunction buildClassCtor(org.mozilla.javascript.Scriptable r24, java.lang.Class<T> r25, boolean r26, boolean r27) throws java.lang.IllegalAccessException, java.lang.InstantiationException, java.lang.reflect.InvocationTargetException {
        /*
            r0 = r24
            r1 = r26
            r2 = r27
            java.lang.reflect.Method[] r3 = org.mozilla.javascript.FunctionObject.getMethodList(r25)
            r4 = 0
            r5 = 0
        L_0x000c:
            int r6 = r3.length
            r7 = 3
            r8 = 2
            r9 = 0
            r10 = 1
            if (r5 >= r6) goto L_0x007a
            r6 = r3[r5]
            java.lang.String r11 = r6.getName()
            java.lang.String r12 = "init"
            boolean r11 = r11.equals(r12)
            if (r11 != 0) goto L_0x0022
            goto L_0x0077
        L_0x0022:
            java.lang.Class[] r11 = r6.getParameterTypes()
            int r12 = r11.length
            if (r12 != r7) goto L_0x005c
            r12 = r11[r4]
            java.lang.Class<?> r13 = org.mozilla.javascript.ScriptRuntime.ContextClass
            if (r12 != r13) goto L_0x005c
            r12 = r11[r10]
            java.lang.Class<org.mozilla.javascript.Scriptable> r13 = org.mozilla.javascript.ScriptRuntime.ScriptableClass
            if (r12 != r13) goto L_0x005c
            r12 = r11[r8]
            java.lang.Class r13 = java.lang.Boolean.TYPE
            if (r12 != r13) goto L_0x005c
            int r12 = r6.getModifiers()
            boolean r12 = java.lang.reflect.Modifier.isStatic(r12)
            if (r12 == 0) goto L_0x005c
            java.lang.Object[] r2 = new java.lang.Object[r7]
            org.mozilla.javascript.Context r3 = org.mozilla.javascript.Context.getContext()
            r2[r4] = r3
            r2[r10] = r0
            if (r1 == 0) goto L_0x0054
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            goto L_0x0056
        L_0x0054:
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
        L_0x0056:
            r2[r8] = r0
            r6.invoke(r9, r2)
            return r9
        L_0x005c:
            int r7 = r11.length
            if (r7 != r10) goto L_0x0077
            r7 = r11[r4]
            java.lang.Class<org.mozilla.javascript.Scriptable> r8 = org.mozilla.javascript.ScriptRuntime.ScriptableClass
            if (r7 != r8) goto L_0x0077
            int r7 = r6.getModifiers()
            boolean r7 = java.lang.reflect.Modifier.isStatic(r7)
            if (r7 == 0) goto L_0x0077
            java.lang.Object[] r1 = new java.lang.Object[r10]
            r1[r4] = r0
            r6.invoke(r9, r1)
            return r9
        L_0x0077:
            int r5 = r5 + 1
            goto L_0x000c
        L_0x007a:
            java.lang.reflect.Constructor[] r5 = r25.getConstructors()
            r6 = 0
        L_0x007f:
            int r11 = r5.length
            if (r6 >= r11) goto L_0x0091
            r11 = r5[r6]
            java.lang.Class[] r11 = r11.getParameterTypes()
            int r11 = r11.length
            if (r11 != 0) goto L_0x008e
            r6 = r5[r6]
            goto L_0x0092
        L_0x008e:
            int r6 = r6 + 1
            goto L_0x007f
        L_0x0091:
            r6 = r9
        L_0x0092:
            if (r6 == 0) goto L_0x02df
            java.lang.Object[] r11 = org.mozilla.javascript.ScriptRuntime.emptyArgs
            java.lang.Object r6 = r6.newInstance(r11)
            org.mozilla.javascript.Scriptable r6 = (org.mozilla.javascript.Scriptable) r6
            java.lang.String r11 = r6.getClassName()
            org.mozilla.javascript.Scriptable r12 = getTopLevelScope(r24)
            java.lang.Object r12 = getProperty((org.mozilla.javascript.Scriptable) r12, (java.lang.String) r11)
            boolean r13 = r12 instanceof org.mozilla.javascript.BaseFunction
            if (r13 == 0) goto L_0x00c1
            org.mozilla.javascript.BaseFunction r12 = (org.mozilla.javascript.BaseFunction) r12
            java.lang.Object r13 = r12.getPrototypeProperty()
            if (r13 == 0) goto L_0x00c1
            java.lang.Class r13 = r13.getClass()
            r14 = r25
            boolean r13 = r14.equals(r13)
            if (r13 == 0) goto L_0x00c3
            return r12
        L_0x00c1:
            r14 = r25
        L_0x00c3:
            if (r2 == 0) goto L_0x00ea
            java.lang.Class r12 = r25.getSuperclass()
            java.lang.Class<org.mozilla.javascript.Scriptable> r13 = org.mozilla.javascript.ScriptRuntime.ScriptableClass
            boolean r13 = r13.isAssignableFrom(r12)
            if (r13 == 0) goto L_0x00ea
            int r13 = r12.getModifiers()
            boolean r13 = java.lang.reflect.Modifier.isAbstract(r13)
            if (r13 != 0) goto L_0x00ea
            java.lang.Class r12 = extendsScriptable(r12)
            java.lang.String r2 = defineClass(r0, r12, r1, r2)
            if (r2 == 0) goto L_0x00ea
            org.mozilla.javascript.Scriptable r2 = getClassPrototype(r0, r2)
            goto L_0x00eb
        L_0x00ea:
            r2 = r9
        L_0x00eb:
            if (r2 != 0) goto L_0x00f1
            org.mozilla.javascript.Scriptable r2 = getObjectPrototype(r24)
        L_0x00f1:
            r6.setPrototype(r2)
            java.lang.Class<org.mozilla.javascript.annotations.JSConstructor> r2 = org.mozilla.javascript.annotations.JSConstructor.class
            java.lang.reflect.Member r2 = findAnnotatedMember(r3, r2)
            if (r2 != 0) goto L_0x0102
            java.lang.Class<org.mozilla.javascript.annotations.JSConstructor> r2 = org.mozilla.javascript.annotations.JSConstructor.class
            java.lang.reflect.Member r2 = findAnnotatedMember(r5, r2)
        L_0x0102:
            java.lang.String r12 = "jsConstructor"
            if (r2 != 0) goto L_0x010a
            java.lang.reflect.Method r2 = org.mozilla.javascript.FunctionObject.findSingleMethod(r3, r12)
        L_0x010a:
            if (r2 != 0) goto L_0x013a
            int r13 = r5.length
            if (r13 != r10) goto L_0x0112
            r2 = r5[r4]
            goto L_0x012c
        L_0x0112:
            int r13 = r5.length
            if (r13 != r8) goto L_0x012c
            r13 = r5[r4]
            java.lang.Class[] r13 = r13.getParameterTypes()
            int r13 = r13.length
            if (r13 != 0) goto L_0x0121
            r2 = r5[r10]
            goto L_0x012c
        L_0x0121:
            r13 = r5[r10]
            java.lang.Class[] r13 = r13.getParameterTypes()
            int r13 = r13.length
            if (r13 != 0) goto L_0x012c
            r2 = r5[r4]
        L_0x012c:
            if (r2 == 0) goto L_0x012f
            goto L_0x013a
        L_0x012f:
            java.lang.String r0 = r25.getName()
            java.lang.String r1 = "msg.ctor.multiple.parms"
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError1(r1, r0)
            throw r0
        L_0x013a:
            org.mozilla.javascript.FunctionObject r5 = new org.mozilla.javascript.FunctionObject
            r5.<init>(r11, r2, r0)
            boolean r11 = r5.isVarArgsMethod()
            if (r11 != 0) goto L_0x02d4
            r5.initAsConstructor(r0, r6)
            java.util.HashSet r11 = new java.util.HashSet
            r11.<init>()
            java.util.HashSet r13 = new java.util.HashSet
            r13.<init>()
            int r14 = r3.length
            r15 = 0
        L_0x0154:
            if (r15 >= r14) goto L_0x02b3
            r8 = r3[r15]
            if (r8 != r2) goto L_0x015d
            r25 = r11
            goto L_0x0192
        L_0x015d:
            java.lang.String r10 = r8.getName()
            java.lang.String r4 = "finishInit"
            boolean r4 = r10.equals(r4)
            if (r4 == 0) goto L_0x019a
            java.lang.Class[] r4 = r8.getParameterTypes()
            r25 = r11
            int r11 = r4.length
            if (r11 != r7) goto L_0x019c
            r11 = 0
            r7 = r4[r11]
            java.lang.Class<org.mozilla.javascript.Scriptable> r11 = org.mozilla.javascript.ScriptRuntime.ScriptableClass
            if (r7 != r11) goto L_0x019c
            r7 = 1
            r11 = r4[r7]
            java.lang.Class<org.mozilla.javascript.FunctionObject> r7 = org.mozilla.javascript.FunctionObject.class
            if (r11 != r7) goto L_0x019c
            r7 = 2
            r4 = r4[r7]
            java.lang.Class<org.mozilla.javascript.Scriptable> r7 = org.mozilla.javascript.ScriptRuntime.ScriptableClass
            if (r4 != r7) goto L_0x019c
            int r4 = r8.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 == 0) goto L_0x019c
            r9 = r8
        L_0x0192:
            r27 = r12
        L_0x0194:
            r22 = r13
            r23 = r14
            goto L_0x028d
        L_0x019a:
            r25 = r11
        L_0x019c:
            r4 = 36
            int r4 = r10.indexOf(r4)
            r7 = -1
            if (r4 == r7) goto L_0x01a6
        L_0x01a5:
            goto L_0x0192
        L_0x01a6:
            boolean r4 = r10.equals(r12)
            if (r4 == 0) goto L_0x01ad
            goto L_0x01a5
        L_0x01ad:
            java.lang.Class<org.mozilla.javascript.annotations.JSFunction> r4 = org.mozilla.javascript.annotations.JSFunction.class
            boolean r4 = r8.isAnnotationPresent(r4)
            if (r4 == 0) goto L_0x01bc
            java.lang.Class<org.mozilla.javascript.annotations.JSFunction> r4 = org.mozilla.javascript.annotations.JSFunction.class
            java.lang.annotation.Annotation r4 = r8.getAnnotation(r4)
            goto L_0x01e4
        L_0x01bc:
            java.lang.Class<org.mozilla.javascript.annotations.JSStaticFunction> r4 = org.mozilla.javascript.annotations.JSStaticFunction.class
            boolean r4 = r8.isAnnotationPresent(r4)
            if (r4 == 0) goto L_0x01cb
            java.lang.Class<org.mozilla.javascript.annotations.JSStaticFunction> r4 = org.mozilla.javascript.annotations.JSStaticFunction.class
            java.lang.annotation.Annotation r4 = r8.getAnnotation(r4)
            goto L_0x01e4
        L_0x01cb:
            java.lang.Class<org.mozilla.javascript.annotations.JSGetter> r4 = org.mozilla.javascript.annotations.JSGetter.class
            boolean r4 = r8.isAnnotationPresent(r4)
            if (r4 == 0) goto L_0x01da
            java.lang.Class<org.mozilla.javascript.annotations.JSGetter> r4 = org.mozilla.javascript.annotations.JSGetter.class
            java.lang.annotation.Annotation r4 = r8.getAnnotation(r4)
            goto L_0x01e4
        L_0x01da:
            java.lang.Class<org.mozilla.javascript.annotations.JSSetter> r4 = org.mozilla.javascript.annotations.JSSetter.class
            boolean r4 = r8.isAnnotationPresent(r4)
            if (r4 == 0) goto L_0x01e3
            goto L_0x01a5
        L_0x01e3:
            r4 = 0
        L_0x01e4:
            java.lang.String r7 = "jsFunction_"
            java.lang.String r11 = "jsGet_"
            r27 = r12
            java.lang.String r12 = "jsStaticFunction_"
            if (r4 != 0) goto L_0x020a
            boolean r16 = r10.startsWith(r7)
            if (r16 == 0) goto L_0x01f7
        L_0x01f4:
            r22 = r13
            goto L_0x020d
        L_0x01f7:
            boolean r7 = r10.startsWith(r12)
            if (r7 == 0) goto L_0x01ff
            r7 = r12
            goto L_0x01f4
        L_0x01ff:
            boolean r7 = r10.startsWith(r11)
            if (r7 == 0) goto L_0x0207
            r7 = r11
            goto L_0x01f4
        L_0x0207:
            if (r4 != 0) goto L_0x020a
            goto L_0x0194
        L_0x020a:
            r22 = r13
            r7 = 0
        L_0x020d:
            boolean r13 = r4 instanceof org.mozilla.javascript.annotations.JSStaticFunction
            if (r13 != 0) goto L_0x0216
            if (r7 != r12) goto L_0x0214
            goto L_0x0216
        L_0x0214:
            r12 = 0
            goto L_0x0217
        L_0x0216:
            r12 = 1
        L_0x0217:
            if (r12 == 0) goto L_0x021e
            r13 = r25
            r23 = r14
            goto L_0x0222
        L_0x021e:
            r23 = r14
            r13 = r22
        L_0x0222:
            java.lang.String r14 = getPropertyName(r10, r7, r4)
            boolean r16 = r13.contains(r14)
            if (r16 != 0) goto L_0x02ac
            r13.add(r14)
            boolean r4 = r4 instanceof org.mozilla.javascript.annotations.JSGetter
            if (r4 != 0) goto L_0x026f
            if (r7 != r11) goto L_0x0236
            goto L_0x026f
        L_0x0236:
            if (r12 == 0) goto L_0x024a
            int r4 = r8.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 == 0) goto L_0x0243
            goto L_0x024a
        L_0x0243:
            java.lang.String r0 = "jsStaticFunction must be used with static method."
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError(r0)
            throw r0
        L_0x024a:
            org.mozilla.javascript.FunctionObject r4 = new org.mozilla.javascript.FunctionObject
            r4.<init>(r14, r8, r6)
            boolean r7 = r4.isVarArgsConstructor()
            if (r7 != 0) goto L_0x0264
            if (r12 == 0) goto L_0x0259
            r7 = r5
            goto L_0x025a
        L_0x0259:
            r7 = r6
        L_0x025a:
            r8 = 2
            defineProperty(r7, r14, r4, r8)
            if (r1 == 0) goto L_0x028d
            r4.sealObject()
            goto L_0x028d
        L_0x0264:
            java.lang.String r0 = r2.getName()
            java.lang.String r1 = "msg.varargs.fun"
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError1(r1, r0)
            throw r0
        L_0x026f:
            boolean r4 = r6 instanceof org.mozilla.javascript.ScriptableObject
            if (r4 == 0) goto L_0x029d
            java.lang.String r4 = "jsSet_"
            java.lang.reflect.Method r20 = findSetterMethod(r3, r14, r4)
            if (r20 == 0) goto L_0x027d
            r11 = 0
            goto L_0x027e
        L_0x027d:
            r11 = 1
        L_0x027e:
            r21 = r11 | 6
            r16 = r6
            org.mozilla.javascript.ScriptableObject r16 = (org.mozilla.javascript.ScriptableObject) r16
            r18 = 0
            r17 = r14
            r19 = r8
            r16.defineProperty(r17, r18, r19, r20, r21)
        L_0x028d:
            int r15 = r15 + 1
            r11 = r25
            r12 = r27
            r13 = r22
            r14 = r23
            r4 = 0
            r7 = 3
            r8 = 2
            r10 = 1
            goto L_0x0154
        L_0x029d:
            java.lang.Class r0 = r6.getClass()
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "msg.extend.scriptable"
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError2(r1, r0, r14)
            throw r0
        L_0x02ac:
            java.lang.String r0 = "duplicate.defineClass.name"
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError2(r0, r10, r14)
            throw r0
        L_0x02b3:
            if (r9 == 0) goto L_0x02c5
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r0
            r0 = 1
            r2[r0] = r5
            r0 = 2
            r2[r0] = r6
            r0 = 0
            r9.invoke(r0, r2)
        L_0x02c5:
            if (r1 == 0) goto L_0x02d3
            r5.sealObject()
            boolean r0 = r6 instanceof org.mozilla.javascript.ScriptableObject
            if (r0 == 0) goto L_0x02d3
            org.mozilla.javascript.ScriptableObject r6 = (org.mozilla.javascript.ScriptableObject) r6
            r6.sealObject()
        L_0x02d3:
            return r5
        L_0x02d4:
            java.lang.String r0 = r2.getName()
            java.lang.String r1 = "msg.varargs.ctor"
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError1(r1, r0)
            throw r0
        L_0x02df:
            r14 = r25
            java.lang.String r0 = r25.getName()
            java.lang.String r1 = "msg.zero.arg.ctor"
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError1(r1, r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptableObject.buildClassCtor(org.mozilla.javascript.Scriptable, java.lang.Class, boolean, boolean):org.mozilla.javascript.BaseFunction");
    }

    private static Member findAnnotatedMember(AccessibleObject[] accessibleObjectArr, Class<? extends Annotation> cls) {
        for (AccessibleObject accessibleObject : accessibleObjectArr) {
            if (accessibleObject.isAnnotationPresent(cls)) {
                return (Member) accessibleObject;
            }
        }
        return null;
    }

    private static Method findSetterMethod(Method[] methodArr, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("set");
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.substring(1));
        String sb2 = sb.toString();
        for (Method method : methodArr) {
            JSSetter jSSetter = (JSSetter) method.getAnnotation(JSSetter.class);
            if (jSSetter != null && (str.equals(jSSetter.value()) || ("".equals(jSSetter.value()) && sb2.equals(method.getName())))) {
                return method;
            }
        }
        String str3 = str2 + str;
        for (Method method2 : methodArr) {
            if (str3.equals(method2.getName())) {
                return method2;
            }
        }
        return null;
    }

    private static String getPropertyName(String str, String str2, Annotation annotation) {
        if (str2 != null) {
            return str.substring(str2.length());
        }
        String str3 = null;
        if (annotation instanceof JSGetter) {
            str3 = ((JSGetter) annotation).value();
            if ((str3 == null || str3.length() == 0) && str.length() > 3 && str.startsWith("get")) {
                str3 = str.substring(3);
                if (Character.isUpperCase(str3.charAt(0))) {
                    if (str3.length() == 1) {
                        str3 = str3.toLowerCase();
                    } else if (!Character.isUpperCase(str3.charAt(1))) {
                        str3 = Character.toLowerCase(str3.charAt(0)) + str3.substring(1);
                    }
                }
            }
        } else if (annotation instanceof JSFunction) {
            str3 = ((JSFunction) annotation).value();
        } else if (annotation instanceof JSStaticFunction) {
            str3 = ((JSStaticFunction) annotation).value();
        }
        return (str3 == null || str3.length() == 0) ? str : str3;
    }

    private static <T extends Scriptable> Class<T> extendsScriptable(Class<?> cls) {
        if (ScriptRuntime.ScriptableClass.isAssignableFrom(cls)) {
            return cls;
        }
        return null;
    }

    public void defineProperty(String str, Object obj, int i) {
        checkNotSealed(str, 0);
        put(str, (Scriptable) this, obj);
        setAttributes(str, i);
    }

    public static void defineProperty(Scriptable scriptable, String str, Object obj, int i) {
        if (!(scriptable instanceof ScriptableObject)) {
            scriptable.put(str, scriptable, obj);
        } else {
            ((ScriptableObject) scriptable).defineProperty(str, obj, i);
        }
    }

    public static void defineConstProperty(Scriptable scriptable, String str) {
        if (scriptable instanceof ConstProperties) {
            ((ConstProperties) scriptable).defineConst(str, scriptable);
        } else {
            defineProperty(scriptable, str, Undefined.instance, 13);
        }
    }

    public void defineProperty(String str, Class<?> cls, int i) {
        int length = str.length();
        if (length != 0) {
            char[] cArr = new char[(length + 3)];
            str.getChars(0, length, cArr, 3);
            cArr[3] = Character.toUpperCase(cArr[3]);
            cArr[0] = 'g';
            cArr[1] = 'e';
            cArr[2] = Advice.OffsetMapping.ForOrigin.Renderer.ForTypeName.SYMBOL;
            String str2 = new String(cArr);
            cArr[0] = Advice.OffsetMapping.ForOrigin.Renderer.ForJavaSignature.SYMBOL;
            String str3 = new String(cArr);
            Method[] methodList = FunctionObject.getMethodList(cls);
            Method findSingleMethod = FunctionObject.findSingleMethod(methodList, str2);
            Method findSingleMethod2 = FunctionObject.findSingleMethod(methodList, str3);
            if (findSingleMethod2 == null) {
                i |= 1;
            }
            int i2 = i;
            if (findSingleMethod2 == null) {
                findSingleMethod2 = null;
            }
            defineProperty(str, (Object) null, findSingleMethod, findSingleMethod2, i2);
            return;
        }
        throw new IllegalArgumentException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: org.mozilla.javascript.MemberBox} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.String} */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        if (r5 == org.mozilla.javascript.ScriptRuntime.ScriptableObjectClass) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003f, code lost:
        if (r4 != false) goto L_0x002f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void defineProperty(java.lang.String r9, java.lang.Object r10, java.lang.reflect.Method r11, java.lang.reflect.Method r12, int r13) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            r2 = 1
            if (r11 == 0) goto L_0x004d
            org.mozilla.javascript.MemberBox r3 = new org.mozilla.javascript.MemberBox
            r3.<init>((java.lang.reflect.Method) r11)
            int r4 = r11.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 != 0) goto L_0x001c
            if (r10 == 0) goto L_0x0018
            r4 = 1
            goto L_0x0019
        L_0x0018:
            r4 = 0
        L_0x0019:
            r3.delegateTo = r10
            goto L_0x0021
        L_0x001c:
            java.lang.Class r4 = java.lang.Void.TYPE
            r3.delegateTo = r4
            r4 = 1
        L_0x0021:
            java.lang.Class[] r5 = r11.getParameterTypes()
            int r6 = r5.length
            java.lang.String r7 = "msg.bad.getter.parms"
            if (r6 != 0) goto L_0x0031
            if (r4 == 0) goto L_0x002f
            java.lang.String r7 = "msg.obj.getter.parms"
            goto L_0x0041
        L_0x002f:
            r7 = r0
            goto L_0x0041
        L_0x0031:
            int r6 = r5.length
            if (r6 != r2) goto L_0x0041
            r5 = r5[r1]
            java.lang.Class<org.mozilla.javascript.Scriptable> r6 = org.mozilla.javascript.ScriptRuntime.ScriptableClass
            if (r5 == r6) goto L_0x003f
            java.lang.Class<?> r6 = org.mozilla.javascript.ScriptRuntime.ScriptableObjectClass
            if (r5 == r6) goto L_0x003f
            goto L_0x0041
        L_0x003f:
            if (r4 != 0) goto L_0x002f
        L_0x0041:
            if (r7 != 0) goto L_0x0044
            goto L_0x004e
        L_0x0044:
            java.lang.String r9 = r11.toString()
            org.mozilla.javascript.EvaluatorException r9 = org.mozilla.javascript.Context.reportRuntimeError1(r7, r9)
            throw r9
        L_0x004d:
            r3 = r0
        L_0x004e:
            if (r12 == 0) goto L_0x00b1
            java.lang.Class r11 = r12.getReturnType()
            java.lang.Class r4 = java.lang.Void.TYPE
            if (r11 != r4) goto L_0x00a6
            org.mozilla.javascript.MemberBox r11 = new org.mozilla.javascript.MemberBox
            r11.<init>((java.lang.reflect.Method) r12)
            int r4 = r12.getModifiers()
            boolean r4 = java.lang.reflect.Modifier.isStatic(r4)
            if (r4 != 0) goto L_0x006f
            if (r10 == 0) goto L_0x006b
            r4 = 1
            goto L_0x006c
        L_0x006b:
            r4 = 0
        L_0x006c:
            r11.delegateTo = r10
            goto L_0x0074
        L_0x006f:
            java.lang.Class r10 = java.lang.Void.TYPE
            r11.delegateTo = r10
            r4 = 1
        L_0x0074:
            java.lang.Class[] r10 = r12.getParameterTypes()
            int r5 = r10.length
            if (r5 != r2) goto L_0x0080
            if (r4 == 0) goto L_0x0099
            java.lang.String r0 = "msg.setter2.expected"
            goto L_0x0099
        L_0x0080:
            int r2 = r10.length
            r5 = 2
            if (r2 != r5) goto L_0x0097
            r10 = r10[r1]
            java.lang.Class<org.mozilla.javascript.Scriptable> r2 = org.mozilla.javascript.ScriptRuntime.ScriptableClass
            if (r10 == r2) goto L_0x0091
            java.lang.Class<?> r2 = org.mozilla.javascript.ScriptRuntime.ScriptableObjectClass
            if (r10 == r2) goto L_0x0091
            java.lang.String r10 = "msg.setter2.parms"
            goto L_0x0095
        L_0x0091:
            if (r4 != 0) goto L_0x0099
            java.lang.String r10 = "msg.setter1.parms"
        L_0x0095:
            r0 = r10
            goto L_0x0099
        L_0x0097:
            java.lang.String r0 = "msg.setter.parms"
        L_0x0099:
            if (r0 != 0) goto L_0x009d
            r0 = r11
            goto L_0x00b1
        L_0x009d:
            java.lang.String r9 = r12.toString()
            org.mozilla.javascript.EvaluatorException r9 = org.mozilla.javascript.Context.reportRuntimeError1(r0, r9)
            throw r9
        L_0x00a6:
            java.lang.String r9 = r12.toString()
            java.lang.String r10 = "msg.setter.return"
            org.mozilla.javascript.EvaluatorException r9 = org.mozilla.javascript.Context.reportRuntimeError1(r10, r9)
            throw r9
        L_0x00b1:
            r10 = 4
            org.mozilla.javascript.ScriptableObject$Slot r9 = r8.getSlot((java.lang.String) r9, (int) r1, (int) r10)
            org.mozilla.javascript.ScriptableObject$GetterSlot r9 = (org.mozilla.javascript.ScriptableObject.GetterSlot) r9
            r9.setAttributes(r13)
            r9.getter = r3
            r9.setter = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptableObject.defineProperty(java.lang.String, java.lang.Object, java.lang.reflect.Method, java.lang.reflect.Method, int):void");
    }

    public void defineOwnProperties(Context context, ScriptableObject scriptableObject) {
        Object[] ids = scriptableObject.getIds();
        for (Object obj : ids) {
            checkPropertyDefinition(ensureScriptableObject(scriptableObject.get(obj)));
        }
        for (Object obj2 : ids) {
            defineOwnProperty(context, obj2, (ScriptableObject) scriptableObject.get(obj2));
        }
    }

    public void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject) {
        checkPropertyDefinition(scriptableObject);
        defineOwnProperty(context, obj, scriptableObject, true);
    }

    /* access modifiers changed from: protected */
    public void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        int i;
        ScriptableObject scriptableObject2;
        boolean z2 = true;
        Slot slot = getSlot(context, obj, 1);
        if (slot != null) {
            z2 = false;
        }
        if (z) {
            if (slot == null) {
                scriptableObject2 = null;
            } else {
                scriptableObject2 = slot.getPropertyDescriptor(context, this);
            }
            checkPropertyChange(ScriptRuntime.toString(obj), scriptableObject2, scriptableObject);
        }
        boolean isAccessorDescriptor = isAccessorDescriptor(scriptableObject);
        if (slot == null) {
            slot = getSlot(context, obj, isAccessorDescriptor ? 4 : 2);
            i = applyDescriptorToAttributeBitset(7, scriptableObject);
        } else {
            i = applyDescriptorToAttributeBitset(slot.getAttributes(), scriptableObject);
        }
        Slot unwrapSlot = unwrapSlot(slot);
        if (isAccessorDescriptor) {
            if (!(unwrapSlot instanceof GetterSlot)) {
                unwrapSlot = getSlot(context, obj, 4);
            }
            GetterSlot getterSlot = (GetterSlot) unwrapSlot;
            Object property = getProperty((Scriptable) scriptableObject, "get");
            if (property != NOT_FOUND) {
                getterSlot.getter = property;
            }
            Object property2 = getProperty((Scriptable) scriptableObject, "set");
            if (property2 != NOT_FOUND) {
                getterSlot.setter = property2;
            }
            getterSlot.value = Undefined.instance;
            getterSlot.setAttributes(i);
            return;
        }
        if ((unwrapSlot instanceof GetterSlot) && isDataDescriptor(scriptableObject)) {
            unwrapSlot = getSlot(context, obj, 5);
        }
        Object property3 = getProperty((Scriptable) scriptableObject, "value");
        if (property3 != NOT_FOUND) {
            unwrapSlot.value = property3;
        } else if (z2) {
            unwrapSlot.value = Undefined.instance;
        }
        unwrapSlot.setAttributes(i);
    }

    /* access modifiers changed from: protected */
    public void checkPropertyDefinition(ScriptableObject scriptableObject) {
        Object property = getProperty((Scriptable) scriptableObject, "get");
        if (property == NOT_FOUND || property == Undefined.instance || (property instanceof Callable)) {
            Object property2 = getProperty((Scriptable) scriptableObject, "set");
            if (property2 != NOT_FOUND && property2 != Undefined.instance && !(property2 instanceof Callable)) {
                throw ScriptRuntime.notFunctionError(property2);
            } else if (isDataDescriptor(scriptableObject) && isAccessorDescriptor(scriptableObject)) {
                throw ScriptRuntime.typeError0("msg.both.data.and.accessor.desc");
            }
        } else {
            throw ScriptRuntime.notFunctionError(property);
        }
    }

    /* access modifiers changed from: protected */
    public void checkPropertyChange(String str, ScriptableObject scriptableObject, ScriptableObject scriptableObject2) {
        if (scriptableObject == null) {
            if (!isExtensible()) {
                throw ScriptRuntime.typeError0("msg.not.extensible");
            }
        } else if (!isFalse(scriptableObject.get("configurable", (Scriptable) scriptableObject))) {
        } else {
            if (isTrue(getProperty((Scriptable) scriptableObject2, "configurable"))) {
                throw ScriptRuntime.typeError1("msg.change.configurable.false.to.true", str);
            } else if (isTrue(scriptableObject.get("enumerable", (Scriptable) scriptableObject)) == isTrue(getProperty((Scriptable) scriptableObject2, "enumerable"))) {
                boolean isDataDescriptor = isDataDescriptor(scriptableObject2);
                boolean isAccessorDescriptor = isAccessorDescriptor(scriptableObject2);
                if (!isDataDescriptor && !isAccessorDescriptor) {
                    return;
                }
                if (!isDataDescriptor || !isDataDescriptor(scriptableObject)) {
                    if (!isAccessorDescriptor || !isAccessorDescriptor(scriptableObject)) {
                        if (isDataDescriptor(scriptableObject)) {
                            throw ScriptRuntime.typeError1("msg.change.property.data.to.accessor.with.configurable.false", str);
                        }
                        throw ScriptRuntime.typeError1("msg.change.property.accessor.to.data.with.configurable.false", str);
                    } else if (!sameValue(getProperty((Scriptable) scriptableObject2, "set"), scriptableObject.get("set", (Scriptable) scriptableObject))) {
                        throw ScriptRuntime.typeError1("msg.change.setter.with.configurable.false", str);
                    } else if (!sameValue(getProperty((Scriptable) scriptableObject2, "get"), scriptableObject.get("get", (Scriptable) scriptableObject))) {
                        throw ScriptRuntime.typeError1("msg.change.getter.with.configurable.false", str);
                    }
                } else if (!isFalse(scriptableObject.get("writable", (Scriptable) scriptableObject))) {
                } else {
                    if (isTrue(getProperty((Scriptable) scriptableObject2, "writable"))) {
                        throw ScriptRuntime.typeError1("msg.change.writable.false.to.true.with.configurable.false", str);
                    } else if (!sameValue(getProperty((Scriptable) scriptableObject2, "value"), scriptableObject.get("value", (Scriptable) scriptableObject))) {
                        throw ScriptRuntime.typeError1("msg.change.value.with.writable.false", str);
                    }
                }
            } else {
                throw ScriptRuntime.typeError1("msg.change.enumerable.with.configurable.false", str);
            }
        }
    }

    protected static boolean isTrue(Object obj) {
        return obj != NOT_FOUND && ScriptRuntime.toBoolean(obj);
    }

    protected static boolean isFalse(Object obj) {
        return !isTrue(obj);
    }

    /* access modifiers changed from: protected */
    public boolean sameValue(Object obj, Object obj2) {
        if (obj == NOT_FOUND) {
            return true;
        }
        if (obj2 == NOT_FOUND) {
            obj2 = Undefined.instance;
        }
        if ((obj2 instanceof Number) && (obj instanceof Number)) {
            double doubleValue = ((Number) obj2).doubleValue();
            double doubleValue2 = ((Number) obj).doubleValue();
            if (Double.isNaN(doubleValue) && Double.isNaN(doubleValue2)) {
                return true;
            }
            if (doubleValue == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && Double.doubleToLongBits(doubleValue) != Double.doubleToLongBits(doubleValue2)) {
                return false;
            }
        }
        return ScriptRuntime.shallowEq(obj2, obj);
    }

    /* access modifiers changed from: protected */
    public int applyDescriptorToAttributeBitset(int i, ScriptableObject scriptableObject) {
        Object property = getProperty((Scriptable) scriptableObject, "enumerable");
        if (property != NOT_FOUND) {
            i = ScriptRuntime.toBoolean(property) ? i & -3 : i | 2;
        }
        Object property2 = getProperty((Scriptable) scriptableObject, "writable");
        if (property2 != NOT_FOUND) {
            i = ScriptRuntime.toBoolean(property2) ? i & -2 : i | 1;
        }
        Object property3 = getProperty((Scriptable) scriptableObject, "configurable");
        if (property3 != NOT_FOUND) {
            return ScriptRuntime.toBoolean(property3) ? i & -5 : i | 4;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public boolean isDataDescriptor(ScriptableObject scriptableObject) {
        return hasProperty((Scriptable) scriptableObject, "value") || hasProperty((Scriptable) scriptableObject, "writable");
    }

    /* access modifiers changed from: protected */
    public boolean isAccessorDescriptor(ScriptableObject scriptableObject) {
        return hasProperty((Scriptable) scriptableObject, "get") || hasProperty((Scriptable) scriptableObject, "set");
    }

    /* access modifiers changed from: protected */
    public boolean isGenericDescriptor(ScriptableObject scriptableObject) {
        return !isDataDescriptor(scriptableObject) && !isAccessorDescriptor(scriptableObject);
    }

    protected static Scriptable ensureScriptable(Object obj) {
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(obj));
    }

    protected static ScriptableObject ensureScriptableObject(Object obj) {
        if (obj instanceof ScriptableObject) {
            return (ScriptableObject) obj;
        }
        throw ScriptRuntime.typeError1("msg.arg.not.object", ScriptRuntime.typeof(obj));
    }

    public void defineFunctionProperties(String[] strArr, Class<?> cls, int i) {
        Method[] methodList = FunctionObject.getMethodList(cls);
        int i2 = 0;
        while (i2 < strArr.length) {
            String str = strArr[i2];
            Method findSingleMethod = FunctionObject.findSingleMethod(methodList, str);
            if (findSingleMethod != null) {
                defineProperty(str, (Object) new FunctionObject(str, findSingleMethod, this), i);
                i2++;
            } else {
                throw Context.reportRuntimeError2("msg.method.not.found", str, cls.getName());
            }
        }
    }

    public static Scriptable getObjectPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), TopLevel.Builtins.Object);
    }

    public static Scriptable getFunctionPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), TopLevel.Builtins.Function);
    }

    public static Scriptable getArrayPrototype(Scriptable scriptable) {
        return TopLevel.getBuiltinPrototype(getTopLevelScope(scriptable), TopLevel.Builtins.Array);
    }

    public static Scriptable getClassPrototype(Scriptable scriptable, String str) {
        Object obj;
        Object property = getProperty(getTopLevelScope(scriptable), str);
        if (property instanceof BaseFunction) {
            obj = ((BaseFunction) property).getPrototypeProperty();
        } else {
            if (property instanceof Scriptable) {
                Scriptable scriptable2 = (Scriptable) property;
                obj = scriptable2.get("prototype", scriptable2);
            }
            return null;
        }
        if (obj instanceof Scriptable) {
            return (Scriptable) obj;
        }
        return null;
    }

    public static Scriptable getTopLevelScope(Scriptable scriptable) {
        while (true) {
            Scriptable parentScope = scriptable.getParentScope();
            if (parentScope == null) {
                return scriptable;
            }
            scriptable = parentScope;
        }
    }

    public boolean isExtensible() {
        return this.isExtensible;
    }

    public void preventExtensions() {
        this.isExtensible = false;
    }

    /* JADX INFO: finally extract failed */
    public synchronized void sealObject() {
        if (this.count >= 0) {
            for (Slot slot = this.firstAdded; slot != null; slot = slot.orderedNext) {
                Object obj = slot.value;
                if (obj instanceof LazilyLoadedCtor) {
                    LazilyLoadedCtor lazilyLoadedCtor = (LazilyLoadedCtor) obj;
                    try {
                        lazilyLoadedCtor.init();
                        slot.value = lazilyLoadedCtor.getValue();
                    } catch (Throwable th) {
                        slot.value = lazilyLoadedCtor.getValue();
                        throw th;
                    }
                }
            }
            this.count = ~this.count;
        }
    }

    public final boolean isSealed() {
        return this.count < 0;
    }

    private void checkNotSealed(String str, int i) {
        if (isSealed()) {
            if (str == null) {
                str = Integer.toString(i);
            }
            throw Context.reportRuntimeError1("msg.modify.sealed", str);
        }
    }

    public static Object getProperty(Scriptable scriptable, String str) {
        Object obj;
        Scriptable scriptable2 = scriptable;
        do {
            obj = scriptable2.get(str, scriptable);
            if (obj != Scriptable.NOT_FOUND || (scriptable2 = scriptable2.getPrototype()) == null) {
                return obj;
            }
            obj = scriptable2.get(str, scriptable);
            break;
        } while ((scriptable2 = scriptable2.getPrototype()) == null);
        return obj;
    }

    public static <T> T getTypedProperty(Scriptable scriptable, int i, Class<T> cls) {
        Object property = getProperty(scriptable, i);
        if (property == Scriptable.NOT_FOUND) {
            property = null;
        }
        return cls.cast(Context.jsToJava(property, cls));
    }

    public static Object getProperty(Scriptable scriptable, int i) {
        Object obj;
        Scriptable scriptable2 = scriptable;
        do {
            obj = scriptable2.get(i, scriptable);
            if (obj != Scriptable.NOT_FOUND || (scriptable2 = scriptable2.getPrototype()) == null) {
                return obj;
            }
            obj = scriptable2.get(i, scriptable);
            break;
        } while ((scriptable2 = scriptable2.getPrototype()) == null);
        return obj;
    }

    public static <T> T getTypedProperty(Scriptable scriptable, String str, Class<T> cls) {
        Object property = getProperty(scriptable, str);
        if (property == Scriptable.NOT_FOUND) {
            property = null;
        }
        return cls.cast(Context.jsToJava(property, cls));
    }

    public static boolean hasProperty(Scriptable scriptable, String str) {
        return getBase(scriptable, str) != null;
    }

    public static void redefineProperty(Scriptable scriptable, String str, boolean z) {
        Scriptable base = getBase(scriptable, str);
        if (base != null) {
            if ((base instanceof ConstProperties) && ((ConstProperties) base).isConst(str)) {
                throw Context.reportRuntimeError1("msg.const.redecl", str);
            } else if (z) {
                throw Context.reportRuntimeError1("msg.var.redecl", str);
            }
        }
    }

    public static boolean hasProperty(Scriptable scriptable, int i) {
        return getBase(scriptable, i) != null;
    }

    public static void putProperty(Scriptable scriptable, String str, Object obj) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            base = scriptable;
        }
        base.put(str, scriptable, obj);
    }

    public static void putConstProperty(Scriptable scriptable, String str, Object obj) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            base = scriptable;
        }
        if (base instanceof ConstProperties) {
            ((ConstProperties) base).putConst(str, scriptable, obj);
        }
    }

    public static void putProperty(Scriptable scriptable, int i, Object obj) {
        Scriptable base = getBase(scriptable, i);
        if (base == null) {
            base = scriptable;
        }
        base.put(i, scriptable, obj);
    }

    public static boolean deleteProperty(Scriptable scriptable, String str) {
        Scriptable base = getBase(scriptable, str);
        if (base == null) {
            return true;
        }
        base.delete(str);
        return !base.has(str, scriptable);
    }

    public static boolean deleteProperty(Scriptable scriptable, int i) {
        Scriptable base = getBase(scriptable, i);
        if (base == null) {
            return true;
        }
        base.delete(i);
        return !base.has(i, scriptable);
    }

    public static Object[] getPropertyIds(Scriptable scriptable) {
        if (scriptable == null) {
            return ScriptRuntime.emptyArgs;
        }
        Object[] ids = scriptable.getIds();
        ObjToIntMap objToIntMap = null;
        while (true) {
            scriptable = scriptable.getPrototype();
            if (scriptable == null) {
                break;
            }
            Object[] ids2 = scriptable.getIds();
            if (ids2.length != 0) {
                if (objToIntMap == null) {
                    if (ids.length == 0) {
                        ids = ids2;
                    } else {
                        objToIntMap = new ObjToIntMap(ids.length + ids2.length);
                        for (int i = 0; i != ids.length; i++) {
                            objToIntMap.intern(ids[i]);
                        }
                        ids = null;
                    }
                }
                for (int i2 = 0; i2 != ids2.length; i2++) {
                    objToIntMap.intern(ids2[i2]);
                }
            }
        }
        if (objToIntMap != null) {
            return objToIntMap.getKeys();
        }
        return ids;
    }

    public static Object callMethod(Scriptable scriptable, String str, Object[] objArr) {
        return callMethod((Context) null, scriptable, str, objArr);
    }

    public static Object callMethod(Context context, Scriptable scriptable, String str, Object[] objArr) {
        Object property = getProperty(scriptable, str);
        if (property instanceof Function) {
            Function function = (Function) property;
            Scriptable topLevelScope = getTopLevelScope(scriptable);
            if (context != null) {
                return function.call(context, topLevelScope, scriptable, objArr);
            }
            return Context.call((ContextFactory) null, function, topLevelScope, scriptable, objArr);
        }
        throw ScriptRuntime.notFunctionError(scriptable, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000b, LOOP_START, MTH_ENTER_BLOCK, PHI: r1 
      PHI: (r1v1 org.mozilla.javascript.Scriptable) = (r1v0 org.mozilla.javascript.Scriptable), (r1v3 org.mozilla.javascript.Scriptable) binds: [B:0:0x0000, B:3:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.mozilla.javascript.Scriptable getBase(org.mozilla.javascript.Scriptable r1, java.lang.String r2) {
        /*
        L_0x0000:
            boolean r0 = r1.has((java.lang.String) r2, (org.mozilla.javascript.Scriptable) r1)
            if (r0 == 0) goto L_0x0007
            goto L_0x000d
        L_0x0007:
            org.mozilla.javascript.Scriptable r1 = r1.getPrototype()
            if (r1 != 0) goto L_0x0000
        L_0x000d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptableObject.getBase(org.mozilla.javascript.Scriptable, java.lang.String):org.mozilla.javascript.Scriptable");
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP:0: B:0:0x0000->B:3:0x000b, LOOP_START, MTH_ENTER_BLOCK, PHI: r1 
      PHI: (r1v1 org.mozilla.javascript.Scriptable) = (r1v0 org.mozilla.javascript.Scriptable), (r1v3 org.mozilla.javascript.Scriptable) binds: [B:0:0x0000, B:3:0x000b] A[DONT_GENERATE, DONT_INLINE]] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.mozilla.javascript.Scriptable getBase(org.mozilla.javascript.Scriptable r1, int r2) {
        /*
        L_0x0000:
            boolean r0 = r1.has((int) r2, (org.mozilla.javascript.Scriptable) r1)
            if (r0 == 0) goto L_0x0007
            goto L_0x000d
        L_0x0007:
            org.mozilla.javascript.Scriptable r1 = r1.getPrototype()
            if (r1 != 0) goto L_0x0000
        L_0x000d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptableObject.getBase(org.mozilla.javascript.Scriptable, int):org.mozilla.javascript.Scriptable");
    }

    public final Object getAssociatedValue(Object obj) {
        Map<Object, Object> map = this.associatedValues;
        if (map == null) {
            return null;
        }
        return map.get(obj);
    }

    public static Object getTopScopeValue(Scriptable scriptable, Object obj) {
        Object associatedValue;
        Scriptable topLevelScope = getTopLevelScope(scriptable);
        do {
            if ((topLevelScope instanceof ScriptableObject) && (associatedValue = ((ScriptableObject) topLevelScope).getAssociatedValue(obj)) != null) {
                return associatedValue;
            }
            topLevelScope = topLevelScope.getPrototype();
        } while (topLevelScope != null);
        return null;
    }

    public final synchronized Object associateValue(Object obj, Object obj2) {
        Map map;
        if (obj2 != null) {
            map = this.associatedValues;
            if (map == null) {
                map = new HashMap();
                this.associatedValues = map;
            }
        } else {
            throw new IllegalArgumentException();
        }
        return Kit.initHash(map, obj, obj2);
    }

    private boolean putImpl(String str, int i, Scriptable scriptable, Object obj) {
        Slot slot;
        if (this != scriptable) {
            slot = getSlot(str, i, 1);
            if (slot == null) {
                return false;
            }
        } else if (!this.isExtensible) {
            slot = getSlot(str, i, 1);
            if (slot == null) {
                return true;
            }
        } else {
            if (this.count < 0) {
                checkNotSealed(str, i);
            }
            slot = getSlot(str, i, 2);
        }
        return slot.setValue(obj, this, scriptable);
    }

    private boolean putConstImpl(String str, int i, Scriptable scriptable, Object obj, int i2) {
        Slot slot;
        if (this != scriptable) {
            slot = getSlot(str, i, 1);
            if (slot == null) {
                return false;
            }
        } else if (!isExtensible()) {
            slot = getSlot(str, i, 1);
            if (slot == null) {
                return true;
            }
        } else {
            checkNotSealed(str, i);
            Slot unwrapSlot = unwrapSlot(getSlot(str, i, 3));
            int attributes = unwrapSlot.getAttributes();
            if ((attributes & 1) != 0) {
                if ((attributes & 8) != 0) {
                    unwrapSlot.value = obj;
                    if (i2 != 8) {
                        unwrapSlot.setAttributes(attributes & -9);
                    }
                }
                return true;
            }
            throw Context.reportRuntimeError1("msg.var.redecl", str);
        }
        return slot.setValue(obj, this, scriptable);
    }

    private Slot findAttributeSlot(String str, int i, int i2) {
        Slot slot = getSlot(str, i, i2);
        if (slot != null) {
            return slot;
        }
        if (str == null) {
            str = Integer.toString(i);
        }
        throw Context.reportRuntimeError1("msg.prop.not.found", str);
    }

    /* access modifiers changed from: private */
    public static Slot unwrapSlot(Slot slot) {
        return slot instanceof RelinkedSlot ? ((RelinkedSlot) slot).slot : slot;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004f, code lost:
        if (r0 != null) goto L_0x0051;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.mozilla.javascript.ScriptableObject.Slot getSlot(java.lang.String r5, int r6, int r7) {
        /*
            r4 = this;
            org.mozilla.javascript.ScriptableObject$Slot[] r0 = r4.slots
            r1 = 1
            if (r0 != 0) goto L_0x0009
            if (r7 != r1) goto L_0x0009
            r5 = 0
            return r5
        L_0x0009:
            if (r5 == 0) goto L_0x000f
            int r6 = r5.hashCode()
        L_0x000f:
            if (r0 == 0) goto L_0x0052
            int r2 = r0.length
            int r2 = getSlotIndex(r2, r6)
            r0 = r0[r2]
        L_0x0018:
            if (r0 == 0) goto L_0x002e
            java.lang.String r2 = r0.name
            int r3 = r0.indexOrHash
            if (r6 != r3) goto L_0x002b
            if (r2 == r5) goto L_0x002e
            if (r5 == 0) goto L_0x002b
            boolean r2 = r5.equals(r2)
            if (r2 == 0) goto L_0x002b
            goto L_0x002e
        L_0x002b:
            org.mozilla.javascript.ScriptableObject$Slot r0 = r0.next
            goto L_0x0018
        L_0x002e:
            if (r7 == r1) goto L_0x0051
            r1 = 2
            if (r7 == r1) goto L_0x004f
            r1 = 3
            if (r7 == r1) goto L_0x004f
            r1 = 4
            if (r7 == r1) goto L_0x0046
            r1 = 5
            if (r7 == r1) goto L_0x003d
            goto L_0x0052
        L_0x003d:
            org.mozilla.javascript.ScriptableObject$Slot r0 = unwrapSlot(r0)
            boolean r1 = r0 instanceof org.mozilla.javascript.ScriptableObject.GetterSlot
            if (r1 != 0) goto L_0x0052
            return r0
        L_0x0046:
            org.mozilla.javascript.ScriptableObject$Slot r0 = unwrapSlot(r0)
            boolean r1 = r0 instanceof org.mozilla.javascript.ScriptableObject.GetterSlot
            if (r1 == 0) goto L_0x0052
            return r0
        L_0x004f:
            if (r0 == 0) goto L_0x0052
        L_0x0051:
            return r0
        L_0x0052:
            org.mozilla.javascript.ScriptableObject$Slot r5 = r4.createSlot(r5, r6, r7)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ScriptableObject.getSlot(java.lang.String, int, int):org.mozilla.javascript.ScriptableObject$Slot");
    }

    private synchronized Slot createSlot(String str, int i, int i2) {
        int i3;
        Slot slot;
        Slot[] slotArr = this.slots;
        if (this.count == 0) {
            slotArr = new Slot[4];
            this.slots = slotArr;
            i3 = getSlotIndex(4, i);
        } else {
            i3 = getSlotIndex(slotArr.length, i);
            Slot slot2 = slotArr[i3];
            Slot slot3 = slot2;
            while (true) {
                if (slot2 == null) {
                    break;
                }
                if (slot2.indexOrHash == i) {
                    if (slot2.name != str) {
                        if (str != null && str.equals(slot2.name)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                slot3 = slot2;
                slot2 = slot2.next;
            }
            if (slot2 != null) {
                Slot unwrapSlot = unwrapSlot(slot2);
                if (i2 == 4 && !(unwrapSlot instanceof GetterSlot)) {
                    slot = new GetterSlot(str, i, unwrapSlot.getAttributes());
                } else if (i2 == 5 && (unwrapSlot instanceof GetterSlot)) {
                    slot = new Slot(str, i, unwrapSlot.getAttributes());
                } else if (i2 == 3) {
                    return null;
                } else {
                    return unwrapSlot;
                }
                slot.value = unwrapSlot.value;
                slot.next = slot2.next;
                if (this.lastAdded != null) {
                    this.lastAdded.orderedNext = slot;
                }
                if (this.firstAdded == null) {
                    this.firstAdded = slot;
                }
                this.lastAdded = slot;
                if (slot3 == slot2) {
                    slotArr[i3] = slot;
                } else {
                    slot3.next = slot;
                }
                slot2.markDeleted();
                return slot;
            } else if ((this.count + 1) * 4 > slotArr.length * 3) {
                int length = slotArr.length * 2;
                Slot[] slotArr2 = new Slot[length];
                copyTable(this.slots, slotArr2, this.count);
                this.slots = slotArr2;
                Slot[] slotArr3 = slotArr2;
                i3 = getSlotIndex(length, i);
                slotArr = slotArr3;
            }
        }
        Slot getterSlot = i2 == 4 ? new GetterSlot(str, i, 0) : new Slot(str, i, 0);
        if (i2 == 3) {
            getterSlot.setAttributes(13);
        }
        this.count++;
        if (this.lastAdded != null) {
            this.lastAdded.orderedNext = getterSlot;
        }
        if (this.firstAdded == null) {
            this.firstAdded = getterSlot;
        }
        this.lastAdded = getterSlot;
        addKnownAbsentSlot(slotArr, getterSlot, i3);
        return getterSlot;
    }

    private synchronized void removeSlot(String str, int i) {
        Slot slot;
        if (str != null) {
            try {
                i = str.hashCode();
            } catch (Throwable th) {
                throw th;
            }
        }
        Slot[] slotArr = this.slots;
        if (this.count != 0) {
            int slotIndex = getSlotIndex(slotArr.length, i);
            Slot slot2 = slotArr[slotIndex];
            Slot slot3 = slot2;
            while (true) {
                if (slot2 == null) {
                    break;
                }
                if (slot2.indexOrHash == i) {
                    if (slot2.name != str) {
                        if (str != null && str.equals(slot2.name)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                slot3 = slot2;
                slot2 = slot2.next;
            }
            if (slot2 != null && (slot2.getAttributes() & 4) == 0) {
                this.count--;
                if (slot3 == slot2) {
                    slotArr[slotIndex] = slot2.next;
                } else {
                    slot3.next = slot2.next;
                }
                Slot unwrapSlot = unwrapSlot(slot2);
                if (unwrapSlot == this.firstAdded) {
                    slot = null;
                    this.firstAdded = unwrapSlot.orderedNext;
                } else {
                    slot = this.firstAdded;
                    while (slot.orderedNext != unwrapSlot) {
                        slot = slot.orderedNext;
                    }
                    slot.orderedNext = unwrapSlot.orderedNext;
                }
                if (unwrapSlot == this.lastAdded) {
                    this.lastAdded = slot;
                }
                slot2.markDeleted();
            }
        }
    }

    private static void copyTable(Slot[] slotArr, Slot[] slotArr2, int i) {
        if (i != 0) {
            int length = slotArr2.length;
            int length2 = slotArr.length;
            while (true) {
                length2--;
                Slot slot = slotArr[length2];
                while (true) {
                    if (slot != null) {
                        addKnownAbsentSlot(slotArr2, slot.next == null ? slot : new RelinkedSlot(slot), getSlotIndex(length, slot.indexOrHash));
                        slot = slot.next;
                        i--;
                        if (i == 0) {
                            return;
                        }
                    }
                }
            }
        } else {
            throw Kit.codeBug();
        }
    }

    private static void addKnownAbsentSlot(Slot[] slotArr, Slot slot, int i) {
        if (slotArr[i] == null) {
            slotArr[i] = slot;
            return;
        }
        Slot slot2 = slotArr[i];
        Slot slot3 = slot2.next;
        while (true) {
            Slot slot4 = slot3;
            Slot slot5 = slot2;
            slot2 = slot4;
            if (slot2 != null) {
                slot3 = slot2.next;
            } else {
                slot5.next = slot;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Object[] getIds(boolean z) {
        Slot slot;
        Slot[] slotArr = this.slots;
        Object[] objArr = ScriptRuntime.emptyArgs;
        if (slotArr == null) {
            return objArr;
        }
        Slot slot2 = this.firstAdded;
        while (slot != null && slot.wasDeleted) {
            slot2 = slot.orderedNext;
        }
        int i = 0;
        while (slot != null) {
            if (z || (slot.getAttributes() & 2) == 0) {
                if (i == 0) {
                    objArr = new Object[slotArr.length];
                }
                int i2 = i + 1;
                objArr[i] = slot.name != null ? slot.name : Integer.valueOf(slot.indexOrHash);
                i = i2;
            }
            slot = slot.orderedNext;
            while (slot != null && slot.wasDeleted) {
                slot = slot.orderedNext;
            }
        }
        if (i == objArr.length) {
            return objArr;
        }
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    private synchronized void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int i = this.count;
        if (i < 0) {
            i = ~i;
        }
        if (i == 0) {
            objectOutputStream.writeInt(0);
        } else {
            objectOutputStream.writeInt(this.slots.length);
            Slot slot = this.firstAdded;
            while (slot != null && slot.wasDeleted) {
                slot = slot.orderedNext;
            }
            this.firstAdded = slot;
            while (slot != null) {
                objectOutputStream.writeObject(slot);
                Slot slot2 = slot.orderedNext;
                while (slot2 != null && slot2.wasDeleted) {
                    slot2 = slot2.orderedNext;
                }
                slot.orderedNext = slot2;
                slot = slot2;
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt != 0) {
            if (((readInt - 1) & readInt) != 0) {
                if (readInt <= 1073741824) {
                    int i = 4;
                    while (i < readInt) {
                        i <<= 1;
                    }
                    readInt = i;
                } else {
                    throw new RuntimeException("Property table overflow");
                }
            }
            this.slots = new Slot[readInt];
            int i2 = this.count;
            if (i2 < 0) {
                i2 = ~i2;
            }
            Slot slot = null;
            for (int i3 = 0; i3 != i2; i3++) {
                Slot slot2 = (Slot) objectInputStream.readObject();
                this.lastAdded = slot2;
                if (i3 == 0) {
                    this.firstAdded = slot2;
                } else {
                    slot.orderedNext = slot2;
                }
                addKnownAbsentSlot(this.slots, this.lastAdded, getSlotIndex(readInt, this.lastAdded.indexOrHash));
                slot = this.lastAdded;
            }
        }
    }

    /* access modifiers changed from: protected */
    public ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        Slot slot = getSlot(context, obj, 1);
        if (slot == null) {
            return null;
        }
        Scriptable parentScope = getParentScope();
        if (parentScope == null) {
            parentScope = this;
        }
        return slot.getPropertyDescriptor(context, parentScope);
    }

    /* access modifiers changed from: protected */
    public Slot getSlot(Context context, Object obj, int i) {
        String stringIdOrIndex = ScriptRuntime.toStringIdOrIndex(context, obj);
        if (stringIdOrIndex == null) {
            return getSlot((String) null, ScriptRuntime.lastIndexResult(context), i);
        }
        return getSlot(stringIdOrIndex, 0, i);
    }

    public int size() {
        int i = this.count;
        return i < 0 ? ~i : i;
    }

    public boolean isEmpty() {
        int i = this.count;
        return i == 0 || i == -1;
    }

    public Object get(Object obj) {
        Object obj2;
        if (obj instanceof String) {
            obj2 = get((String) obj, (Scriptable) this);
        } else {
            obj2 = obj instanceof Number ? get(((Number) obj).intValue(), (Scriptable) this) : null;
        }
        if (obj2 == Scriptable.NOT_FOUND || obj2 == Undefined.instance) {
            return null;
        }
        return obj2 instanceof Wrapper ? ((Wrapper) obj2).unwrap() : obj2;
    }
}
