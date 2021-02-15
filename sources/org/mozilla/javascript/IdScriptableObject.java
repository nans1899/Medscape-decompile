package org.mozilla.javascript;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class IdScriptableObject extends ScriptableObject implements IdFunctionCall {
    private transient PrototypeValues prototypeValues;

    protected static int instanceIdInfo(int i, int i2) {
        return (i << 16) | i2;
    }

    /* access modifiers changed from: protected */
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
    }

    /* access modifiers changed from: protected */
    public int findInstanceIdInfo(String str) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getMaxInstanceId() {
        return 0;
    }

    private static final class PrototypeValues implements Serializable {
        private static final int NAME_SLOT = 1;
        private static final int SLOT_SPAN = 2;
        static final long serialVersionUID = 3038645279153854371L;
        private short[] attributeArray;
        private IdFunctionObject constructor;
        private short constructorAttrs;
        int constructorId;
        private int maxId;
        private IdScriptableObject obj;
        private Object[] valueArray;

        PrototypeValues(IdScriptableObject idScriptableObject, int i) {
            if (idScriptableObject == null) {
                throw new IllegalArgumentException();
            } else if (i >= 1) {
                this.obj = idScriptableObject;
                this.maxId = i;
            } else {
                throw new IllegalArgumentException();
            }
        }

        /* access modifiers changed from: package-private */
        public final int getMaxId() {
            return this.maxId;
        }

        /* access modifiers changed from: package-private */
        public final void initValue(int i, String str, Object obj2, int i2) {
            if (1 > i || i > this.maxId) {
                throw new IllegalArgumentException();
            } else if (str == null) {
                throw new IllegalArgumentException();
            } else if (obj2 != Scriptable.NOT_FOUND) {
                ScriptableObject.checkValidAttributes(i2);
                if (this.obj.findPrototypeId(str) != i) {
                    throw new IllegalArgumentException(str);
                } else if (i != this.constructorId) {
                    initSlot(i, str, obj2, i2);
                } else if (obj2 instanceof IdFunctionObject) {
                    this.constructor = (IdFunctionObject) obj2;
                    this.constructorAttrs = (short) i2;
                } else {
                    throw new IllegalArgumentException("consructor should be initialized with IdFunctionObject");
                }
            } else {
                throw new IllegalArgumentException();
            }
        }

        private void initSlot(int i, String str, Object obj2, int i2) {
            Object[] objArr = this.valueArray;
            if (objArr != null) {
                if (obj2 == null) {
                    obj2 = UniqueTag.NULL_VALUE;
                }
                int i3 = i - 1;
                int i4 = i3 * 2;
                synchronized (this) {
                    if (objArr[i4] == null) {
                        objArr[i4] = obj2;
                        objArr[i4 + 1] = str;
                        this.attributeArray[i3] = (short) i2;
                    } else if (!str.equals(objArr[i4 + 1])) {
                        throw new IllegalStateException();
                    }
                }
                return;
            }
            throw new IllegalStateException();
        }

        /* access modifiers changed from: package-private */
        public final IdFunctionObject createPrecachedConstructor() {
            if (this.constructorId == 0) {
                int findPrototypeId = this.obj.findPrototypeId("constructor");
                this.constructorId = findPrototypeId;
                if (findPrototypeId != 0) {
                    this.obj.initPrototypeId(findPrototypeId);
                    IdFunctionObject idFunctionObject = this.constructor;
                    if (idFunctionObject != null) {
                        idFunctionObject.initFunction(this.obj.getClassName(), ScriptableObject.getTopLevelScope(this.obj));
                        this.constructor.markAsConstructor(this.obj);
                        return this.constructor;
                    }
                    throw new IllegalStateException(this.obj.getClass().getName() + ".initPrototypeId() did not " + "initialize id=" + this.constructorId);
                }
                throw new IllegalStateException("No id for constructor property");
            }
            throw new IllegalStateException();
        }

        /* access modifiers changed from: package-private */
        public final int findId(String str) {
            return this.obj.findPrototypeId(str);
        }

        /* access modifiers changed from: package-private */
        public final boolean has(int i) {
            Object obj2;
            Object[] objArr = this.valueArray;
            if (objArr == null || (obj2 = objArr[(i - 1) * 2]) == null || obj2 != Scriptable.NOT_FOUND) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public final Object get(int i) {
            Object ensureId = ensureId(i);
            if (ensureId == UniqueTag.NULL_VALUE) {
                return null;
            }
            return ensureId;
        }

        /* access modifiers changed from: package-private */
        public final void set(int i, Scriptable scriptable, Object obj2) {
            if (obj2 != Scriptable.NOT_FOUND) {
                ensureId(i);
                int i2 = i - 1;
                if ((this.attributeArray[i2] & 1) != 0) {
                    return;
                }
                if (scriptable == this.obj) {
                    if (obj2 == null) {
                        obj2 = UniqueTag.NULL_VALUE;
                    }
                    int i3 = i2 * 2;
                    synchronized (this) {
                        this.valueArray[i3] = obj2;
                    }
                    return;
                }
                scriptable.put((String) this.valueArray[(i2 * 2) + 1], scriptable, obj2);
                return;
            }
            throw new IllegalArgumentException();
        }

        /* access modifiers changed from: package-private */
        public final void delete(int i) {
            ensureId(i);
            int i2 = i - 1;
            if ((this.attributeArray[i2] & 4) == 0) {
                int i3 = i2 * 2;
                synchronized (this) {
                    this.valueArray[i3] = Scriptable.NOT_FOUND;
                    this.attributeArray[i2] = 0;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final int getAttributes(int i) {
            ensureId(i);
            return this.attributeArray[i - 1];
        }

        /* access modifiers changed from: package-private */
        public final void setAttributes(int i, int i2) {
            ScriptableObject.checkValidAttributes(i2);
            ensureId(i);
            synchronized (this) {
                this.attributeArray[i - 1] = (short) i2;
            }
        }

        /* access modifiers changed from: package-private */
        public final Object[] getNames(boolean z, Object[] objArr) {
            Object[] objArr2 = null;
            int i = 0;
            for (int i2 = 1; i2 <= this.maxId; i2++) {
                Object ensureId = ensureId(i2);
                if ((z || (this.attributeArray[i2 - 1] & 2) == 0) && ensureId != Scriptable.NOT_FOUND) {
                    String str = (String) this.valueArray[((i2 - 1) * 2) + 1];
                    if (objArr2 == null) {
                        objArr2 = new Object[this.maxId];
                    }
                    objArr2[i] = str;
                    i++;
                }
            }
            if (i == 0) {
                return objArr;
            }
            if (objArr != null && objArr.length != 0) {
                int length = objArr.length;
                Object[] objArr3 = new Object[(length + i)];
                System.arraycopy(objArr, 0, objArr3, 0, length);
                System.arraycopy(objArr2, 0, objArr3, length, i);
                return objArr3;
            } else if (i == objArr2.length) {
                return objArr2;
            } else {
                Object[] objArr4 = new Object[i];
                System.arraycopy(objArr2, 0, objArr4, 0, i);
                return objArr4;
            }
        }

        private Object ensureId(int i) {
            Object[] objArr = this.valueArray;
            if (objArr == null) {
                synchronized (this) {
                    objArr = this.valueArray;
                    if (objArr == null) {
                        objArr = new Object[(this.maxId * 2)];
                        this.valueArray = objArr;
                        this.attributeArray = new short[this.maxId];
                    }
                }
            }
            int i2 = (i - 1) * 2;
            Object obj2 = objArr[i2];
            if (obj2 == null) {
                int i3 = this.constructorId;
                if (i == i3) {
                    initSlot(i3, "constructor", this.constructor, this.constructorAttrs);
                    this.constructor = null;
                } else {
                    this.obj.initPrototypeId(i);
                }
                obj2 = objArr[i2];
                if (obj2 == null) {
                    throw new IllegalStateException(this.obj.getClass().getName() + ".initPrototypeId(int id) " + "did not initialize id=" + i);
                }
            }
            return obj2;
        }
    }

    public IdScriptableObject() {
    }

    public IdScriptableObject(Scriptable scriptable, Scriptable scriptable2) {
        super(scriptable, scriptable2);
    }

    /* access modifiers changed from: protected */
    public final Object defaultGet(String str) {
        return super.get(str, (Scriptable) this);
    }

    /* access modifiers changed from: protected */
    public final void defaultPut(String str, Object obj) {
        super.put(str, (Scriptable) this, obj);
    }

    public boolean has(String str, Scriptable scriptable) {
        int findId;
        int findInstanceIdInfo = findInstanceIdInfo(str);
        if (findInstanceIdInfo == 0) {
            PrototypeValues prototypeValues2 = this.prototypeValues;
            if (prototypeValues2 == null || (findId = prototypeValues2.findId(str)) == 0) {
                return super.has(str, scriptable);
            }
            return this.prototypeValues.has(findId);
        } else if (((findInstanceIdInfo >>> 16) & 4) != 0) {
            return true;
        } else {
            if (NOT_FOUND != getInstanceIdValue(65535 & findInstanceIdInfo)) {
                return true;
            }
            return false;
        }
    }

    public Object get(String str, Scriptable scriptable) {
        int findId;
        Object obj;
        Object instanceIdValue;
        Object obj2 = super.get(str, scriptable);
        if (obj2 != NOT_FOUND) {
            return obj2;
        }
        int findInstanceIdInfo = findInstanceIdInfo(str);
        if (findInstanceIdInfo != 0 && (instanceIdValue = getInstanceIdValue(findInstanceIdInfo & 65535)) != NOT_FOUND) {
            return instanceIdValue;
        }
        PrototypeValues prototypeValues2 = this.prototypeValues;
        if (prototypeValues2 == null || (findId = prototypeValues2.findId(str)) == 0 || (obj = this.prototypeValues.get(findId)) == NOT_FOUND) {
            return NOT_FOUND;
        }
        return obj;
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        int findId;
        int findInstanceIdInfo = findInstanceIdInfo(str);
        if (findInstanceIdInfo == 0) {
            PrototypeValues prototypeValues2 = this.prototypeValues;
            if (prototypeValues2 == null || (findId = prototypeValues2.findId(str)) == 0) {
                super.put(str, scriptable, obj);
            } else if (scriptable != this || !isSealed()) {
                this.prototypeValues.set(findId, scriptable, obj);
            } else {
                throw Context.reportRuntimeError1("msg.modify.sealed", str);
            }
        } else if (scriptable == this && isSealed()) {
            throw Context.reportRuntimeError1("msg.modify.sealed", str);
        } else if (((findInstanceIdInfo >>> 16) & 1) != 0) {
        } else {
            if (scriptable == this) {
                setInstanceIdValue(65535 & findInstanceIdInfo, obj);
            } else {
                scriptable.put(str, scriptable, obj);
            }
        }
    }

    public void delete(String str) {
        int findId;
        int findInstanceIdInfo = findInstanceIdInfo(str);
        if (findInstanceIdInfo == 0 || isSealed()) {
            PrototypeValues prototypeValues2 = this.prototypeValues;
            if (prototypeValues2 == null || (findId = prototypeValues2.findId(str)) == 0) {
                super.delete(str);
            } else if (!isSealed()) {
                this.prototypeValues.delete(findId);
            }
        } else if (((findInstanceIdInfo >>> 16) & 4) == 0) {
            setInstanceIdValue(65535 & findInstanceIdInfo, NOT_FOUND);
        }
    }

    public int getAttributes(String str) {
        int findId;
        int findInstanceIdInfo = findInstanceIdInfo(str);
        if (findInstanceIdInfo != 0) {
            return findInstanceIdInfo >>> 16;
        }
        PrototypeValues prototypeValues2 = this.prototypeValues;
        if (prototypeValues2 == null || (findId = prototypeValues2.findId(str)) == 0) {
            return super.getAttributes(str);
        }
        return this.prototypeValues.getAttributes(findId);
    }

    public void setAttributes(String str, int i) {
        int findId;
        ScriptableObject.checkValidAttributes(i);
        int findInstanceIdInfo = findInstanceIdInfo(str);
        if (findInstanceIdInfo != 0) {
            int i2 = 65535 & findInstanceIdInfo;
            if (i != (findInstanceIdInfo >>> 16)) {
                setInstanceIdAttributes(i2, i);
                return;
            }
            return;
        }
        PrototypeValues prototypeValues2 = this.prototypeValues;
        if (prototypeValues2 == null || (findId = prototypeValues2.findId(str)) == 0) {
            super.setAttributes(str, i);
        } else {
            this.prototypeValues.setAttributes(findId, i);
        }
    }

    /* access modifiers changed from: package-private */
    public Object[] getIds(boolean z) {
        Object[] ids = super.getIds(z);
        PrototypeValues prototypeValues2 = this.prototypeValues;
        if (prototypeValues2 != null) {
            ids = prototypeValues2.getNames(z, ids);
        }
        int maxInstanceId = getMaxInstanceId();
        if (maxInstanceId == 0) {
            return ids;
        }
        Object[] objArr = null;
        int i = 0;
        while (maxInstanceId != 0) {
            String instanceIdName = getInstanceIdName(maxInstanceId);
            int findInstanceIdInfo = findInstanceIdInfo(instanceIdName);
            if (findInstanceIdInfo != 0) {
                int i2 = findInstanceIdInfo >>> 16;
                if (!((i2 & 4) == 0 && NOT_FOUND == getInstanceIdValue(maxInstanceId)) && (z || (i2 & 2) == 0)) {
                    if (i == 0) {
                        objArr = new Object[maxInstanceId];
                    }
                    objArr[i] = instanceIdName;
                    i++;
                }
            }
            maxInstanceId--;
        }
        if (i == 0) {
            return ids;
        }
        if (ids.length == 0 && objArr.length == i) {
            return objArr;
        }
        Object[] objArr2 = new Object[(ids.length + i)];
        System.arraycopy(ids, 0, objArr2, 0, ids.length);
        System.arraycopy(objArr, 0, objArr2, ids.length, i);
        return objArr2;
    }

    /* access modifiers changed from: protected */
    public String getInstanceIdName(int i) {
        throw new IllegalArgumentException(String.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        throw new IllegalStateException(String.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void setInstanceIdValue(int i, Object obj) {
        throw new IllegalStateException(String.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void setInstanceIdAttributes(int i, int i2) {
        throw ScriptRuntime.constructError("InternalError", "Changing attributes not supported for " + getClassName() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getInstanceIdName(i) + " property");
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        throw idFunctionObject.unknown();
    }

    public final IdFunctionObject exportAsJSClass(int i, Scriptable scriptable, boolean z) {
        if (!(scriptable == this || scriptable == null)) {
            setParentScope(scriptable);
            setPrototype(getObjectPrototype(scriptable));
        }
        activatePrototypeMap(i);
        IdFunctionObject createPrecachedConstructor = this.prototypeValues.createPrecachedConstructor();
        if (z) {
            sealObject();
        }
        fillConstructorProperties(createPrecachedConstructor);
        if (z) {
            createPrecachedConstructor.sealObject();
        }
        createPrecachedConstructor.exportAsScopeProperty();
        return createPrecachedConstructor;
    }

    public final boolean hasPrototypeMap() {
        return this.prototypeValues != null;
    }

    public final void activatePrototypeMap(int i) {
        PrototypeValues prototypeValues2 = new PrototypeValues(this, i);
        synchronized (this) {
            if (this.prototypeValues == null) {
                this.prototypeValues = prototypeValues2;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public final void initPrototypeMethod(Object obj, int i, String str, int i2) {
        this.prototypeValues.initValue(i, str, newIdFunction(obj, i, str, i2, ScriptableObject.getTopLevelScope(this)), 2);
    }

    public final void initPrototypeConstructor(IdFunctionObject idFunctionObject) {
        int i = this.prototypeValues.constructorId;
        if (i == 0) {
            throw new IllegalStateException();
        } else if (idFunctionObject.methodId() == i) {
            if (isSealed()) {
                idFunctionObject.sealObject();
            }
            this.prototypeValues.initValue(i, "constructor", idFunctionObject, 2);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public final void initPrototypeValue(int i, String str, Object obj, int i2) {
        this.prototypeValues.initValue(i, str, obj, i2);
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        throw new IllegalStateException(String.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public int findPrototypeId(String str) {
        throw new IllegalStateException(str);
    }

    /* access modifiers changed from: protected */
    public void addIdFunctionProperty(Scriptable scriptable, Object obj, int i, String str, int i2) {
        newIdFunction(obj, i, str, i2, ScriptableObject.getTopLevelScope(scriptable)).addAsProperty(scriptable);
    }

    protected static EcmaError incompatibleCallError(IdFunctionObject idFunctionObject) {
        throw ScriptRuntime.typeError1("msg.incompat.call", idFunctionObject.getFunctionName());
    }

    private IdFunctionObject newIdFunction(Object obj, int i, String str, int i2, Scriptable scriptable) {
        IdFunctionObject idFunctionObject = new IdFunctionObject(this, obj, i, str, i2, scriptable);
        if (isSealed()) {
            idFunctionObject.sealObject();
        }
        return idFunctionObject;
    }

    public void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject) {
        int findId;
        if (obj instanceof String) {
            String str = (String) obj;
            int findInstanceIdInfo = findInstanceIdInfo(str);
            if (findInstanceIdInfo != 0) {
                int i = 65535 & findInstanceIdInfo;
                if (isAccessorDescriptor(scriptableObject)) {
                    delete(i);
                } else {
                    checkPropertyDefinition(scriptableObject);
                    checkPropertyChange(str, getOwnPropertyDescriptor(context, obj), scriptableObject);
                    int i2 = findInstanceIdInfo >>> 16;
                    Object property = getProperty((Scriptable) scriptableObject, "value");
                    if (property != NOT_FOUND && (i2 & 1) == 0 && !sameValue(property, getInstanceIdValue(i))) {
                        setInstanceIdValue(i, property);
                    }
                    setAttributes(str, applyDescriptorToAttributeBitset(i2, scriptableObject));
                    return;
                }
            }
            PrototypeValues prototypeValues2 = this.prototypeValues;
            if (!(prototypeValues2 == null || (findId = prototypeValues2.findId(str)) == 0)) {
                if (isAccessorDescriptor(scriptableObject)) {
                    this.prototypeValues.delete(findId);
                } else {
                    checkPropertyDefinition(scriptableObject);
                    checkPropertyChange(str, getOwnPropertyDescriptor(context, obj), scriptableObject);
                    int attributes = this.prototypeValues.getAttributes(findId);
                    Object property2 = getProperty((Scriptable) scriptableObject, "value");
                    if (property2 != NOT_FOUND && (attributes & 1) == 0 && !sameValue(property2, this.prototypeValues.get(findId))) {
                        this.prototypeValues.set(findId, this, property2);
                    }
                    this.prototypeValues.setAttributes(findId, applyDescriptorToAttributeBitset(attributes, scriptableObject));
                    return;
                }
            }
        }
        super.defineOwnProperty(context, obj, scriptableObject);
    }

    /* access modifiers changed from: protected */
    public ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        ScriptableObject ownPropertyDescriptor = super.getOwnPropertyDescriptor(context, obj);
        return (ownPropertyDescriptor != null || !(obj instanceof String)) ? ownPropertyDescriptor : getBuiltInDescriptor((String) obj);
    }

    private ScriptableObject getBuiltInDescriptor(String str) {
        int findId;
        Scriptable parentScope = getParentScope();
        if (parentScope == null) {
            parentScope = this;
        }
        int findInstanceIdInfo = findInstanceIdInfo(str);
        if (findInstanceIdInfo != 0) {
            return buildDataDescriptor(parentScope, getInstanceIdValue(65535 & findInstanceIdInfo), findInstanceIdInfo >>> 16);
        }
        PrototypeValues prototypeValues2 = this.prototypeValues;
        if (prototypeValues2 == null || (findId = prototypeValues2.findId(str)) == 0) {
            return null;
        }
        return buildDataDescriptor(parentScope, this.prototypeValues.get(findId), this.prototypeValues.getAttributes(findId));
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt != 0) {
            activatePrototypeMap(readInt);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        PrototypeValues prototypeValues2 = this.prototypeValues;
        objectOutputStream.writeInt(prototypeValues2 != null ? prototypeValues2.getMaxId() : 0);
    }
}
