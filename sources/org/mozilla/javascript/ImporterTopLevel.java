package org.mozilla.javascript;

public class ImporterTopLevel extends TopLevel {
    private static final Object IMPORTER_TAG = "Importer";
    private static final int Id_constructor = 1;
    private static final int Id_importClass = 2;
    private static final int Id_importPackage = 3;
    private static final int MAX_PROTOTYPE_ID = 3;
    static final long serialVersionUID = -9095380847465315412L;
    private ObjArray importedPackages;
    private boolean topScopeFlag;

    public ImporterTopLevel() {
        this.importedPackages = new ObjArray();
    }

    public ImporterTopLevel(Context context) {
        this(context, false);
    }

    public ImporterTopLevel(Context context, boolean z) {
        this.importedPackages = new ObjArray();
        initStandardObjects(context, z);
    }

    public String getClassName() {
        return this.topScopeFlag ? "global" : "JavaImporter";
    }

    public static void init(Context context, Scriptable scriptable, boolean z) {
        new ImporterTopLevel().exportAsJSClass(3, scriptable, z);
    }

    public void initStandardObjects(Context context, boolean z) {
        context.initStandardObjects(this, z);
        this.topScopeFlag = true;
        IdFunctionObject exportAsJSClass = exportAsJSClass(3, this, false);
        if (z) {
            exportAsJSClass.sealObject();
        }
        delete("constructor");
    }

    public boolean has(String str, Scriptable scriptable) {
        return super.has(str, scriptable) || getPackageProperty(str, scriptable) != NOT_FOUND;
    }

    public Object get(String str, Scriptable scriptable) {
        Object obj = super.get(str, scriptable);
        if (obj != NOT_FOUND) {
            return obj;
        }
        return getPackageProperty(str, scriptable);
    }

    private Object getPackageProperty(String str, Scriptable scriptable) {
        Object[] array;
        Object obj = NOT_FOUND;
        synchronized (this.importedPackages) {
            array = this.importedPackages.toArray();
        }
        for (Object obj2 : array) {
            Object pkgProperty = ((NativeJavaPackage) obj2).getPkgProperty(str, scriptable, false);
            if (pkgProperty != null && !(pkgProperty instanceof NativeJavaPackage)) {
                if (obj == NOT_FOUND) {
                    obj = pkgProperty;
                } else {
                    throw Context.reportRuntimeError2("msg.ambig.import", obj.toString(), pkgProperty.toString());
                }
            }
        }
        return obj;
    }

    public void importPackage(Context context, Scriptable scriptable, Object[] objArr, Function function) {
        js_importPackage(objArr);
    }

    private Object js_construct(Scriptable scriptable, Object[] objArr) {
        ImporterTopLevel importerTopLevel = new ImporterTopLevel();
        for (int i = 0; i != objArr.length; i++) {
            NativeJavaClass nativeJavaClass = objArr[i];
            if (nativeJavaClass instanceof NativeJavaClass) {
                importerTopLevel.importClass(nativeJavaClass);
            } else if (nativeJavaClass instanceof NativeJavaPackage) {
                importerTopLevel.importPackage((NativeJavaPackage) nativeJavaClass);
            } else {
                throw Context.reportRuntimeError1("msg.not.class.not.pkg", Context.toString(nativeJavaClass));
            }
        }
        importerTopLevel.setParentScope(scriptable);
        importerTopLevel.setPrototype(this);
        return importerTopLevel;
    }

    private Object js_importClass(Object[] objArr) {
        int i = 0;
        while (i != objArr.length) {
            NativeJavaClass nativeJavaClass = objArr[i];
            if (nativeJavaClass instanceof NativeJavaClass) {
                importClass(nativeJavaClass);
                i++;
            } else {
                throw Context.reportRuntimeError1("msg.not.class", Context.toString(nativeJavaClass));
            }
        }
        return Undefined.instance;
    }

    private Object js_importPackage(Object[] objArr) {
        int i = 0;
        while (i != objArr.length) {
            NativeJavaPackage nativeJavaPackage = objArr[i];
            if (nativeJavaPackage instanceof NativeJavaPackage) {
                importPackage(nativeJavaPackage);
                i++;
            } else {
                throw Context.reportRuntimeError1("msg.not.pkg", Context.toString(nativeJavaPackage));
            }
        }
        return Undefined.instance;
    }

    private void importPackage(NativeJavaPackage nativeJavaPackage) {
        if (nativeJavaPackage != null) {
            synchronized (this.importedPackages) {
                int i = 0;
                while (i != this.importedPackages.size()) {
                    if (!nativeJavaPackage.equals(this.importedPackages.get(i))) {
                        i++;
                    } else {
                        return;
                    }
                }
                this.importedPackages.add(nativeJavaPackage);
            }
        }
    }

    private void importClass(NativeJavaClass nativeJavaClass) {
        String name = nativeJavaClass.getClassObject().getName();
        String substring = name.substring(name.lastIndexOf(46) + 1);
        Object obj = get(substring, this);
        if (obj == NOT_FOUND || obj == nativeJavaClass) {
            put(substring, this, nativeJavaClass);
            return;
        }
        throw Context.reportRuntimeError1("msg.prop.defined", substring);
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        int i2 = 1;
        if (i == 1) {
            i2 = 0;
            str = "constructor";
        } else if (i == 2) {
            str = "importClass";
        } else if (i == 3) {
            str = "importPackage";
        } else {
            throw new IllegalArgumentException(String.valueOf(i));
        }
        initPrototypeMethod(IMPORTER_TAG, i, str, i2);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        if (!idFunctionObject.hasTag(IMPORTER_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            return js_construct(scriptable, objArr);
        }
        if (methodId == 2) {
            return realThis(scriptable2, idFunctionObject).js_importClass(objArr);
        }
        if (methodId == 3) {
            return realThis(scriptable2, idFunctionObject).js_importPackage(objArr);
        }
        throw new IllegalArgumentException(String.valueOf(methodId));
    }

    private ImporterTopLevel realThis(Scriptable scriptable, IdFunctionObject idFunctionObject) {
        if (this.topScopeFlag) {
            return this;
        }
        if (scriptable instanceof ImporterTopLevel) {
            return (ImporterTopLevel) scriptable;
        }
        throw incompatibleCallError(idFunctionObject);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0029 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.length()
            r1 = 0
            r2 = 11
            if (r0 != r2) goto L_0x001d
            char r0 = r4.charAt(r1)
            r2 = 99
            if (r0 != r2) goto L_0x0015
            r0 = 1
            java.lang.String r2 = "constructor"
            goto L_0x0027
        L_0x0015:
            r2 = 105(0x69, float:1.47E-43)
            if (r0 != r2) goto L_0x0025
            r0 = 2
            java.lang.String r2 = "importClass"
            goto L_0x0027
        L_0x001d:
            r2 = 13
            if (r0 != r2) goto L_0x0025
            r0 = 3
            java.lang.String r2 = "importPackage"
            goto L_0x0027
        L_0x0025:
            r2 = 0
            r0 = 0
        L_0x0027:
            if (r2 == 0) goto L_0x0032
            if (r2 == r4) goto L_0x0032
            boolean r4 = r2.equals(r4)
            if (r4 != 0) goto L_0x0032
            goto L_0x0033
        L_0x0032:
            r1 = r0
        L_0x0033:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.ImporterTopLevel.findPrototypeId(java.lang.String):int");
    }
}
