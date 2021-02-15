package org.mozilla.javascript;

final class Arguments extends IdScriptableObject {
    private static final String FTAG = "Arguments";
    private static final int Id_callee = 1;
    private static final int Id_caller = 3;
    private static final int Id_constructor = 4;
    private static final int Id_length = 2;
    private static final int MAX_INSTANCE_ID = 4;
    static final long serialVersionUID = 4275508002492040609L;
    private NativeCall activation;
    private Object[] args;
    private Object calleeObj;
    private Object callerObj;
    private Object constructor;
    private Object lengthObj;

    public String getClassName() {
        return FTAG;
    }

    /* access modifiers changed from: protected */
    public String getInstanceIdName(int i) {
        if (i == 1) {
            return "callee";
        }
        if (i == 2) {
            return Name.LENGTH;
        }
        if (i == 3) {
            return "caller";
        }
        if (i != 4) {
            return null;
        }
        return "constructor";
    }

    /* access modifiers changed from: protected */
    public int getMaxInstanceId() {
        return 4;
    }

    public Arguments(NativeCall nativeCall) {
        this.activation = nativeCall;
        Scriptable parentScope = nativeCall.getParentScope();
        setParentScope(parentScope);
        setPrototype(ScriptableObject.getObjectPrototype(parentScope));
        Object[] objArr = nativeCall.originalArgs;
        this.args = objArr;
        this.lengthObj = Integer.valueOf(objArr.length);
        NativeFunction nativeFunction = nativeCall.function;
        this.calleeObj = nativeFunction;
        this.constructor = getProperty(getTopLevelScope(parentScope), "Object");
        int languageVersion = nativeFunction.getLanguageVersion();
        if (languageVersion > 130 || languageVersion == 0) {
            this.callerObj = NOT_FOUND;
        } else {
            this.callerObj = null;
        }
    }

    private Object arg(int i) {
        if (i >= 0) {
            Object[] objArr = this.args;
            if (objArr.length > i) {
                return objArr[i];
            }
        }
        return NOT_FOUND;
    }

    private void putIntoActivation(int i, Object obj) {
        String paramOrVarName = this.activation.function.getParamOrVarName(i);
        NativeCall nativeCall = this.activation;
        nativeCall.put(paramOrVarName, nativeCall, obj);
    }

    private Object getFromActivation(int i) {
        String paramOrVarName = this.activation.function.getParamOrVarName(i);
        NativeCall nativeCall = this.activation;
        return nativeCall.get(paramOrVarName, nativeCall);
    }

    private void replaceArg(int i, Object obj) {
        if (sharedWithActivation(i)) {
            putIntoActivation(i, obj);
        }
        synchronized (this) {
            if (this.args == this.activation.originalArgs) {
                this.args = (Object[]) this.args.clone();
            }
            this.args[i] = obj;
        }
    }

    private void removeArg(int i) {
        synchronized (this) {
            if (this.args[i] != NOT_FOUND) {
                if (this.args == this.activation.originalArgs) {
                    this.args = (Object[]) this.args.clone();
                }
                this.args[i] = NOT_FOUND;
            }
        }
    }

    public boolean has(int i, Scriptable scriptable) {
        if (arg(i) != NOT_FOUND) {
            return true;
        }
        return super.has(i, scriptable);
    }

    public Object get(int i, Scriptable scriptable) {
        Object arg = arg(i);
        if (arg == NOT_FOUND) {
            return super.get(i, scriptable);
        }
        return sharedWithActivation(i) ? getFromActivation(i) : arg;
    }

    private boolean sharedWithActivation(int i) {
        NativeFunction nativeFunction = this.activation.function;
        int paramCount = nativeFunction.getParamCount();
        if (i >= paramCount) {
            return false;
        }
        if (i < paramCount - 1) {
            String paramOrVarName = nativeFunction.getParamOrVarName(i);
            for (int i2 = i + 1; i2 < paramCount; i2++) {
                if (paramOrVarName.equals(nativeFunction.getParamOrVarName(i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        if (arg(i) == NOT_FOUND) {
            super.put(i, scriptable, obj);
        } else {
            replaceArg(i, obj);
        }
    }

    public void delete(int i) {
        if (i >= 0 && i < this.args.length) {
            removeArg(i);
        }
        super.delete(i);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findInstanceIdInfo(java.lang.String r8) {
        /*
            r7 = this;
            int r0 = r8.length()
            r1 = 3
            r2 = 1
            r3 = 4
            r4 = 0
            r5 = 2
            r6 = 6
            if (r0 != r6) goto L_0x0029
            r0 = 5
            char r0 = r8.charAt(r0)
            r6 = 101(0x65, float:1.42E-43)
            if (r0 != r6) goto L_0x0019
            java.lang.String r0 = "callee"
            r6 = 1
            goto L_0x0033
        L_0x0019:
            r6 = 104(0x68, float:1.46E-43)
            if (r0 != r6) goto L_0x0021
            java.lang.String r0 = "length"
            r6 = 2
            goto L_0x0033
        L_0x0021:
            r6 = 114(0x72, float:1.6E-43)
            if (r0 != r6) goto L_0x0031
            java.lang.String r0 = "caller"
            r6 = 3
            goto L_0x0033
        L_0x0029:
            r6 = 11
            if (r0 != r6) goto L_0x0031
            java.lang.String r0 = "constructor"
            r6 = 4
            goto L_0x0033
        L_0x0031:
            r0 = 0
            r6 = 0
        L_0x0033:
            if (r0 == 0) goto L_0x003e
            if (r0 == r8) goto L_0x003e
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            r4 = r6
        L_0x003f:
            if (r4 != 0) goto L_0x0046
            int r8 = super.findInstanceIdInfo(r8)
            return r8
        L_0x0046:
            if (r4 == r2) goto L_0x0055
            if (r4 == r5) goto L_0x0055
            if (r4 == r1) goto L_0x0055
            if (r4 != r3) goto L_0x004f
            goto L_0x0055
        L_0x004f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            r8.<init>()
            throw r8
        L_0x0055:
            int r8 = instanceIdInfo(r5, r4)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.Arguments.findInstanceIdInfo(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    public Object getInstanceIdValue(int i) {
        NativeCall nativeCall;
        if (i == 1) {
            return this.calleeObj;
        }
        if (i == 2) {
            return this.lengthObj;
        }
        if (i == 3) {
            Object obj = this.callerObj;
            if (obj == UniqueTag.NULL_VALUE) {
                return null;
            }
            if (obj != null || (nativeCall = this.activation.parentActivationCall) == null) {
                return obj;
            }
            return nativeCall.get("arguments", nativeCall);
        } else if (i != 4) {
            return super.getInstanceIdValue(i);
        } else {
            return this.constructor;
        }
    }

    /* access modifiers changed from: protected */
    public void setInstanceIdValue(int i, Object obj) {
        if (i == 1) {
            this.calleeObj = obj;
        } else if (i == 2) {
            this.lengthObj = obj;
        } else if (i == 3) {
            if (obj == null) {
                obj = UniqueTag.NULL_VALUE;
            }
            this.callerObj = obj;
        } else if (i != 4) {
            super.setInstanceIdValue(i, obj);
        } else {
            this.constructor = obj;
        }
    }

    /* access modifiers changed from: package-private */
    public Object[] getIds(boolean z) {
        int intValue;
        Object[] ids = super.getIds(z);
        Object[] objArr = this.args;
        if (objArr.length == 0) {
            return ids;
        }
        int length = objArr.length;
        boolean[] zArr = new boolean[length];
        int length2 = objArr.length;
        for (int i = 0; i != ids.length; i++) {
            Object obj = ids[i];
            if ((obj instanceof Integer) && (intValue = ((Integer) obj).intValue()) >= 0 && intValue < this.args.length && !zArr[intValue]) {
                zArr[intValue] = true;
                length2--;
            }
        }
        if (!z) {
            for (int i2 = 0; i2 < length; i2++) {
                if (!zArr[i2] && super.has(i2, (Scriptable) this)) {
                    zArr[i2] = true;
                    length2--;
                }
            }
        }
        if (length2 == 0) {
            return ids;
        }
        Object[] objArr2 = new Object[(ids.length + length2)];
        System.arraycopy(ids, 0, objArr2, length2, ids.length);
        int i3 = 0;
        for (int i4 = 0; i4 != this.args.length; i4++) {
            if (!zArr[i4]) {
                objArr2[i3] = Integer.valueOf(i4);
                i3++;
            }
        }
        if (i3 != length2) {
            Kit.codeBug();
        }
        return objArr2;
    }

    /* access modifiers changed from: protected */
    public ScriptableObject getOwnPropertyDescriptor(Context context, Object obj) {
        double number = ScriptRuntime.toNumber(obj);
        int i = (int) number;
        if (number != ((double) i)) {
            return super.getOwnPropertyDescriptor(context, obj);
        }
        Object arg = arg(i);
        if (arg == NOT_FOUND) {
            return super.getOwnPropertyDescriptor(context, obj);
        }
        if (sharedWithActivation(i)) {
            arg = getFromActivation(i);
        }
        if (super.has(i, (Scriptable) this)) {
            ScriptableObject ownPropertyDescriptor = super.getOwnPropertyDescriptor(context, obj);
            ownPropertyDescriptor.put("value", (Scriptable) ownPropertyDescriptor, arg);
            return ownPropertyDescriptor;
        }
        Scriptable parentScope = getParentScope();
        if (parentScope == null) {
            parentScope = this;
        }
        return buildDataDescriptor(parentScope, arg, 0);
    }

    /* access modifiers changed from: protected */
    public void defineOwnProperty(Context context, Object obj, ScriptableObject scriptableObject, boolean z) {
        super.defineOwnProperty(context, obj, scriptableObject, z);
        double number = ScriptRuntime.toNumber(obj);
        int i = (int) number;
        if (number != ((double) i) || arg(i) == NOT_FOUND) {
            return;
        }
        if (isAccessorDescriptor(scriptableObject)) {
            removeArg(i);
            return;
        }
        Object property = getProperty((Scriptable) scriptableObject, "value");
        if (property != NOT_FOUND) {
            replaceArg(i, property);
            if (isFalse(getProperty((Scriptable) scriptableObject, "writable"))) {
                removeArg(i);
            }
        }
    }
}
