package org.mozilla.javascript;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class NativeObject extends IdScriptableObject implements Map {
    private static final int ConstructorId_create = -9;
    private static final int ConstructorId_defineProperties = -8;
    private static final int ConstructorId_defineProperty = -5;
    private static final int ConstructorId_freeze = -13;
    private static final int ConstructorId_getOwnPropertyDescriptor = -4;
    private static final int ConstructorId_getOwnPropertyNames = -3;
    private static final int ConstructorId_getPrototypeOf = -1;
    private static final int ConstructorId_isExtensible = -6;
    private static final int ConstructorId_isFrozen = -11;
    private static final int ConstructorId_isSealed = -10;
    private static final int ConstructorId_keys = -2;
    private static final int ConstructorId_preventExtensions = -7;
    private static final int ConstructorId_seal = -12;
    private static final int Id___defineGetter__ = 9;
    private static final int Id___defineSetter__ = 10;
    private static final int Id___lookupGetter__ = 11;
    private static final int Id___lookupSetter__ = 12;
    private static final int Id_constructor = 1;
    private static final int Id_hasOwnProperty = 5;
    private static final int Id_isPrototypeOf = 7;
    private static final int Id_propertyIsEnumerable = 6;
    private static final int Id_toLocaleString = 3;
    private static final int Id_toSource = 8;
    private static final int Id_toString = 2;
    private static final int Id_valueOf = 4;
    private static final int MAX_PROTOTYPE_ID = 12;
    private static final Object OBJECT_TAG = "Object";
    static final long serialVersionUID = -6345305608474346996L;

    public String getClassName() {
        return "Object";
    }

    static void init(Scriptable scriptable, boolean z) {
        new NativeObject().exportAsJSClass(12, scriptable, z);
    }

    public String toString() {
        return ScriptRuntime.defaultObjectToString(this);
    }

    /* access modifiers changed from: protected */
    public void fillConstructorProperties(IdFunctionObject idFunctionObject) {
        IdFunctionObject idFunctionObject2 = idFunctionObject;
        addIdFunctionProperty(idFunctionObject2, OBJECT_TAG, -1, "getPrototypeOf", 1);
        IdFunctionObject idFunctionObject3 = idFunctionObject;
        addIdFunctionProperty(idFunctionObject3, OBJECT_TAG, -2, "keys", 1);
        addIdFunctionProperty(idFunctionObject2, OBJECT_TAG, -3, "getOwnPropertyNames", 1);
        addIdFunctionProperty(idFunctionObject3, OBJECT_TAG, -4, "getOwnPropertyDescriptor", 2);
        addIdFunctionProperty(idFunctionObject2, OBJECT_TAG, -5, "defineProperty", 3);
        addIdFunctionProperty(idFunctionObject3, OBJECT_TAG, -6, "isExtensible", 1);
        addIdFunctionProperty(idFunctionObject2, OBJECT_TAG, -7, "preventExtensions", 1);
        addIdFunctionProperty(idFunctionObject3, OBJECT_TAG, -8, "defineProperties", 2);
        addIdFunctionProperty(idFunctionObject2, OBJECT_TAG, -9, "create", 2);
        addIdFunctionProperty(idFunctionObject3, OBJECT_TAG, -10, "isSealed", 1);
        addIdFunctionProperty(idFunctionObject2, OBJECT_TAG, -11, "isFrozen", 1);
        addIdFunctionProperty(idFunctionObject3, OBJECT_TAG, -12, "seal", 1);
        addIdFunctionProperty(idFunctionObject2, OBJECT_TAG, -13, "freeze", 1);
        super.fillConstructorProperties(idFunctionObject);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0036, code lost:
        initPrototypeMethod(OBJECT_TAG, r4, r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001b, code lost:
        r0 = r1;
        r2 = 2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initPrototypeId(int r4) {
        /*
            r3 = this;
            r0 = 2
            r1 = 0
            r2 = 1
            switch(r4) {
                case 1: goto L_0x0034;
                case 2: goto L_0x0030;
                case 3: goto L_0x002d;
                case 4: goto L_0x002a;
                case 5: goto L_0x0027;
                case 6: goto L_0x0024;
                case 7: goto L_0x0021;
                case 8: goto L_0x001e;
                case 9: goto L_0x0019;
                case 10: goto L_0x0016;
                case 11: goto L_0x0013;
                case 12: goto L_0x0010;
                default: goto L_0x0006;
            }
        L_0x0006:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0.<init>(r4)
            throw r0
        L_0x0010:
            java.lang.String r0 = "__lookupSetter__"
            goto L_0x0036
        L_0x0013:
            java.lang.String r0 = "__lookupGetter__"
            goto L_0x0036
        L_0x0016:
            java.lang.String r1 = "__defineSetter__"
            goto L_0x001b
        L_0x0019:
            java.lang.String r1 = "__defineGetter__"
        L_0x001b:
            r0 = r1
            r2 = 2
            goto L_0x0036
        L_0x001e:
            java.lang.String r0 = "toSource"
            goto L_0x0032
        L_0x0021:
            java.lang.String r0 = "isPrototypeOf"
            goto L_0x0036
        L_0x0024:
            java.lang.String r0 = "propertyIsEnumerable"
            goto L_0x0036
        L_0x0027:
            java.lang.String r0 = "hasOwnProperty"
            goto L_0x0036
        L_0x002a:
            java.lang.String r0 = "valueOf"
            goto L_0x0032
        L_0x002d:
            java.lang.String r0 = "toLocaleString"
            goto L_0x0032
        L_0x0030:
            java.lang.String r0 = "toString"
        L_0x0032:
            r2 = 0
            goto L_0x0036
        L_0x0034:
            java.lang.String r0 = "constructor"
        L_0x0036:
            java.lang.Object r1 = OBJECT_TAG
            r3.initPrototypeMethod(r1, r4, r0, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeObject.initPrototypeId(int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v12, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v1, types: [int] */
    /* JADX WARNING: type inference failed for: r5v3, types: [int] */
    /* JADX WARNING: type inference failed for: r5v5, types: [int] */
    /* JADX WARNING: type inference failed for: r5v7, types: [int] */
    /* JADX WARNING: type inference failed for: r5v13 */
    /* JADX WARNING: type inference failed for: r5v14 */
    /* JADX WARNING: type inference failed for: r5v15 */
    /* JADX WARNING: type inference failed for: r5v16 */
    /* JADX WARNING: type inference failed for: r5v17 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: type inference failed for: r5v19 */
    /* JADX WARNING: type inference failed for: r5v20 */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x00fc, code lost:
        if ((((org.mozilla.javascript.ScriptableObject) r11).getAttributes(r8) & 2) == 0) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0110, code lost:
        if ((((org.mozilla.javascript.ScriptableObject) r11).getAttributes(r8) & 2) == 0) goto L_0x0114;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object execIdCall(org.mozilla.javascript.IdFunctionObject r8, org.mozilla.javascript.Context r9, org.mozilla.javascript.Scriptable r10, org.mozilla.javascript.Scriptable r11, java.lang.Object[] r12) {
        /*
            r7 = this;
            java.lang.Object r0 = OBJECT_TAG
            boolean r0 = r8.hasTag(r0)
            if (r0 != 0) goto L_0x000d
            java.lang.Object r8 = super.execIdCall(r8, r9, r10, r11, r12)
            return r8
        L_0x000d:
            int r0 = r8.methodId()
            java.lang.String r1 = "writable"
            java.lang.String r2 = "configurable"
            r3 = 2
            r4 = 1
            r5 = 0
            switch(r0) {
                case -13: goto L_0x0361;
                case -12: goto L_0x032c;
                case -11: goto L_0x02e0;
                case -10: goto L_0x02a9;
                case -9: goto L_0x026f;
                case -8: goto L_0x024b;
                case -7: goto L_0x023b;
                case -6: goto L_0x0226;
                case -5: goto L_0x0201;
                case -4: goto L_0x01e0;
                case -3: goto L_0x01bd;
                case -2: goto L_0x019a;
                case -1: goto L_0x0189;
                case 0: goto L_0x001b;
                case 1: goto L_0x0167;
                case 2: goto L_0x013b;
                case 3: goto L_0x013b;
                case 4: goto L_0x013a;
                case 5: goto L_0x011c;
                case 6: goto L_0x00db;
                case 7: goto L_0x00bf;
                case 8: goto L_0x00ba;
                case 9: goto L_0x0061;
                case 10: goto L_0x0061;
                case 11: goto L_0x0025;
                case 12: goto L_0x0025;
                default: goto L_0x001b;
            }
        L_0x001b:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = java.lang.String.valueOf(r0)
            r8.<init>(r9)
            throw r8
        L_0x0025:
            int r8 = r12.length
            if (r8 < r4) goto L_0x005e
            boolean r8 = r11 instanceof org.mozilla.javascript.ScriptableObject
            if (r8 != 0) goto L_0x002d
            goto L_0x005e
        L_0x002d:
            org.mozilla.javascript.ScriptableObject r11 = (org.mozilla.javascript.ScriptableObject) r11
            r8 = r12[r5]
            java.lang.String r8 = org.mozilla.javascript.ScriptRuntime.toStringIdOrIndex(r9, r8)
            if (r8 == 0) goto L_0x0039
            r9 = 0
            goto L_0x003d
        L_0x0039:
            int r9 = org.mozilla.javascript.ScriptRuntime.lastIndexResult(r9)
        L_0x003d:
            r10 = 12
            if (r0 != r10) goto L_0x0042
            goto L_0x0043
        L_0x0042:
            r4 = 0
        L_0x0043:
            java.lang.Object r10 = r11.getGetterOrSetter(r8, r9, r4)
            if (r10 == 0) goto L_0x004a
            goto L_0x0058
        L_0x004a:
            org.mozilla.javascript.Scriptable r11 = r11.getPrototype()
            if (r11 != 0) goto L_0x0051
            goto L_0x0058
        L_0x0051:
            boolean r12 = r11 instanceof org.mozilla.javascript.ScriptableObject
            if (r12 == 0) goto L_0x0058
            org.mozilla.javascript.ScriptableObject r11 = (org.mozilla.javascript.ScriptableObject) r11
            goto L_0x0043
        L_0x0058:
            if (r10 == 0) goto L_0x005b
            return r10
        L_0x005b:
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            return r8
        L_0x005e:
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            return r8
        L_0x0061:
            int r8 = r12.length
            if (r8 < r3) goto L_0x00ad
            r8 = r12[r4]
            boolean r8 = r8 instanceof org.mozilla.javascript.Callable
            if (r8 != 0) goto L_0x006b
            goto L_0x00ad
        L_0x006b:
            boolean r8 = r11 instanceof org.mozilla.javascript.ScriptableObject
            if (r8 == 0) goto L_0x0098
            org.mozilla.javascript.ScriptableObject r11 = (org.mozilla.javascript.ScriptableObject) r11
            r8 = r12[r5]
            java.lang.String r8 = org.mozilla.javascript.ScriptRuntime.toStringIdOrIndex(r9, r8)
            if (r8 == 0) goto L_0x007b
            r9 = 0
            goto L_0x007f
        L_0x007b:
            int r9 = org.mozilla.javascript.ScriptRuntime.lastIndexResult(r9)
        L_0x007f:
            r10 = r12[r4]
            org.mozilla.javascript.Callable r10 = (org.mozilla.javascript.Callable) r10
            r12 = 10
            if (r0 != r12) goto L_0x0088
            goto L_0x0089
        L_0x0088:
            r4 = 0
        L_0x0089:
            r11.setGetterOrSetter(r8, r9, r10, r4)
            boolean r8 = r11 instanceof org.mozilla.javascript.NativeArray
            if (r8 == 0) goto L_0x0095
            org.mozilla.javascript.NativeArray r11 = (org.mozilla.javascript.NativeArray) r11
            r11.setDenseOnly(r5)
        L_0x0095:
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            return r8
        L_0x0098:
            java.lang.Class r8 = r11.getClass()
            java.lang.String r8 = r8.getName()
            r9 = r12[r5]
            java.lang.String r9 = java.lang.String.valueOf(r9)
            java.lang.String r10 = "msg.extend.scriptable"
            org.mozilla.javascript.EvaluatorException r8 = org.mozilla.javascript.Context.reportRuntimeError2(r10, r8, r9)
            throw r8
        L_0x00ad:
            int r8 = r12.length
            if (r8 < r3) goto L_0x00b3
            r8 = r12[r4]
            goto L_0x00b5
        L_0x00b3:
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
        L_0x00b5:
            java.lang.RuntimeException r8 = org.mozilla.javascript.ScriptRuntime.notFunctionError(r8)
            throw r8
        L_0x00ba:
            java.lang.String r8 = org.mozilla.javascript.ScriptRuntime.defaultObjectToSource(r9, r10, r11, r12)
            return r8
        L_0x00bf:
            int r8 = r12.length
            if (r8 == 0) goto L_0x00d5
            r8 = r12[r5]
            boolean r8 = r8 instanceof org.mozilla.javascript.Scriptable
            if (r8 == 0) goto L_0x00d5
            r8 = r12[r5]
            org.mozilla.javascript.Scriptable r8 = (org.mozilla.javascript.Scriptable) r8
        L_0x00cc:
            org.mozilla.javascript.Scriptable r8 = r8.getPrototype()
            if (r8 != r11) goto L_0x00d3
            goto L_0x00d6
        L_0x00d3:
            if (r8 != 0) goto L_0x00cc
        L_0x00d5:
            r4 = 0
        L_0x00d6:
            java.lang.Boolean r8 = org.mozilla.javascript.ScriptRuntime.wrapBoolean(r4)
            return r8
        L_0x00db:
            int r8 = r12.length
            if (r8 != 0) goto L_0x00df
            goto L_0x0117
        L_0x00df:
            r8 = r12[r5]
            java.lang.String r8 = org.mozilla.javascript.ScriptRuntime.toStringIdOrIndex(r9, r8)
            if (r8 != 0) goto L_0x00ff
            int r8 = org.mozilla.javascript.ScriptRuntime.lastIndexResult(r9)
            boolean r9 = r11.has((int) r8, (org.mozilla.javascript.Scriptable) r11)
            if (r9 == 0) goto L_0x0116
            boolean r10 = r11 instanceof org.mozilla.javascript.ScriptableObject
            if (r10 == 0) goto L_0x0116
            org.mozilla.javascript.ScriptableObject r11 = (org.mozilla.javascript.ScriptableObject) r11
            int r8 = r11.getAttributes((int) r8)
            r8 = r8 & r3
            if (r8 != 0) goto L_0x0113
            goto L_0x0114
        L_0x00ff:
            boolean r9 = r11.has((java.lang.String) r8, (org.mozilla.javascript.Scriptable) r11)
            if (r9 == 0) goto L_0x0116
            boolean r10 = r11 instanceof org.mozilla.javascript.ScriptableObject
            if (r10 == 0) goto L_0x0116
            org.mozilla.javascript.ScriptableObject r11 = (org.mozilla.javascript.ScriptableObject) r11
            int r8 = r11.getAttributes((java.lang.String) r8)
            r8 = r8 & r3
            if (r8 != 0) goto L_0x0113
            goto L_0x0114
        L_0x0113:
            r4 = 0
        L_0x0114:
            r5 = r4
            goto L_0x0117
        L_0x0116:
            r5 = r9
        L_0x0117:
            java.lang.Boolean r8 = org.mozilla.javascript.ScriptRuntime.wrapBoolean(r5)
            return r8
        L_0x011c:
            int r8 = r12.length
            if (r8 != 0) goto L_0x0120
            goto L_0x0135
        L_0x0120:
            r8 = r12[r5]
            java.lang.String r8 = org.mozilla.javascript.ScriptRuntime.toStringIdOrIndex(r9, r8)
            if (r8 != 0) goto L_0x0131
            int r8 = org.mozilla.javascript.ScriptRuntime.lastIndexResult(r9)
            boolean r5 = r11.has((int) r8, (org.mozilla.javascript.Scriptable) r11)
            goto L_0x0135
        L_0x0131:
            boolean r5 = r11.has((java.lang.String) r8, (org.mozilla.javascript.Scriptable) r11)
        L_0x0135:
            java.lang.Boolean r8 = org.mozilla.javascript.ScriptRuntime.wrapBoolean(r5)
            return r8
        L_0x013a:
            return r11
        L_0x013b:
            r8 = 4
            boolean r8 = r9.hasFeature(r8)
            if (r8 == 0) goto L_0x0162
            java.lang.String r8 = org.mozilla.javascript.ScriptRuntime.defaultObjectToSource(r9, r10, r11, r12)
            int r9 = r8.length()
            if (r9 == 0) goto L_0x0161
            char r10 = r8.charAt(r5)
            r11 = 40
            if (r10 != r11) goto L_0x0161
            int r9 = r9 - r4
            char r10 = r8.charAt(r9)
            r11 = 41
            if (r10 != r11) goto L_0x0161
            java.lang.String r8 = r8.substring(r4, r9)
        L_0x0161:
            return r8
        L_0x0162:
            java.lang.String r8 = org.mozilla.javascript.ScriptRuntime.defaultObjectToString(r11)
            return r8
        L_0x0167:
            if (r11 == 0) goto L_0x016e
            org.mozilla.javascript.Scriptable r8 = r8.construct(r9, r10, r12)
            return r8
        L_0x016e:
            int r8 = r12.length
            if (r8 == 0) goto L_0x0183
            r8 = r12[r5]
            if (r8 == 0) goto L_0x0183
            r8 = r12[r5]
            java.lang.Object r11 = org.mozilla.javascript.Undefined.instance
            if (r8 != r11) goto L_0x017c
            goto L_0x0183
        L_0x017c:
            r8 = r12[r5]
            org.mozilla.javascript.Scriptable r8 = org.mozilla.javascript.ScriptRuntime.toObject((org.mozilla.javascript.Context) r9, (org.mozilla.javascript.Scriptable) r10, (java.lang.Object) r8)
            return r8
        L_0x0183:
            org.mozilla.javascript.NativeObject r8 = new org.mozilla.javascript.NativeObject
            r8.<init>()
            return r8
        L_0x0189:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x018f
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x0191
        L_0x018f:
            r8 = r12[r5]
        L_0x0191:
            org.mozilla.javascript.Scriptable r8 = ensureScriptable(r8)
            org.mozilla.javascript.Scriptable r8 = r8.getPrototype()
            return r8
        L_0x019a:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x01a0
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x01a2
        L_0x01a0:
            r8 = r12[r5]
        L_0x01a2:
            org.mozilla.javascript.Scriptable r8 = ensureScriptable(r8)
            java.lang.Object[] r8 = r8.getIds()
        L_0x01aa:
            int r11 = r8.length
            if (r5 >= r11) goto L_0x01b8
            r11 = r8[r5]
            java.lang.String r11 = org.mozilla.javascript.ScriptRuntime.toString((java.lang.Object) r11)
            r8[r5] = r11
            int r5 = r5 + 1
            goto L_0x01aa
        L_0x01b8:
            org.mozilla.javascript.Scriptable r8 = r9.newArray((org.mozilla.javascript.Scriptable) r10, (java.lang.Object[]) r8)
            return r8
        L_0x01bd:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x01c3
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x01c5
        L_0x01c3:
            r8 = r12[r5]
        L_0x01c5:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            java.lang.Object[] r8 = r8.getAllIds()
        L_0x01cd:
            int r11 = r8.length
            if (r5 >= r11) goto L_0x01db
            r11 = r8[r5]
            java.lang.String r11 = org.mozilla.javascript.ScriptRuntime.toString((java.lang.Object) r11)
            r8[r5] = r11
            int r5 = r5 + 1
            goto L_0x01cd
        L_0x01db:
            org.mozilla.javascript.Scriptable r8 = r9.newArray((org.mozilla.javascript.Scriptable) r10, (java.lang.Object[]) r8)
            return r8
        L_0x01e0:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x01e6
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x01e8
        L_0x01e6:
            r8 = r12[r5]
        L_0x01e8:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            int r10 = r12.length
            if (r10 >= r3) goto L_0x01f2
            java.lang.Object r10 = org.mozilla.javascript.Undefined.instance
            goto L_0x01f4
        L_0x01f2:
            r10 = r12[r4]
        L_0x01f4:
            java.lang.String r10 = org.mozilla.javascript.ScriptRuntime.toString((java.lang.Object) r10)
            org.mozilla.javascript.ScriptableObject r8 = r8.getOwnPropertyDescriptor(r9, r10)
            if (r8 != 0) goto L_0x0200
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
        L_0x0200:
            return r8
        L_0x0201:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x0207
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x0209
        L_0x0207:
            r8 = r12[r5]
        L_0x0209:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            int r10 = r12.length
            if (r10 >= r3) goto L_0x0213
            java.lang.Object r10 = org.mozilla.javascript.Undefined.instance
            goto L_0x0215
        L_0x0213:
            r10 = r12[r4]
        L_0x0215:
            int r11 = r12.length
            r0 = 3
            if (r11 >= r0) goto L_0x021c
            java.lang.Object r11 = org.mozilla.javascript.Undefined.instance
            goto L_0x021e
        L_0x021c:
            r11 = r12[r3]
        L_0x021e:
            org.mozilla.javascript.ScriptableObject r11 = ensureScriptableObject(r11)
            r8.defineOwnProperty(r9, r10, r11)
            return r8
        L_0x0226:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x022c
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x022e
        L_0x022c:
            r8 = r12[r5]
        L_0x022e:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            boolean r8 = r8.isExtensible()
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            return r8
        L_0x023b:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x0241
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x0243
        L_0x0241:
            r8 = r12[r5]
        L_0x0243:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            r8.preventExtensions()
            return r8
        L_0x024b:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x0251
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x0253
        L_0x0251:
            r8 = r12[r5]
        L_0x0253:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            int r10 = r12.length
            if (r10 >= r3) goto L_0x025d
            java.lang.Object r10 = org.mozilla.javascript.Undefined.instance
            goto L_0x025f
        L_0x025d:
            r10 = r12[r4]
        L_0x025f:
            org.mozilla.javascript.Scriptable r11 = r7.getParentScope()
            org.mozilla.javascript.Scriptable r10 = org.mozilla.javascript.Context.toObject(r10, r11)
            org.mozilla.javascript.ScriptableObject r10 = ensureScriptableObject(r10)
            r8.defineOwnProperties(r9, r10)
            return r8
        L_0x026f:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x0275
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x0277
        L_0x0275:
            r8 = r12[r5]
        L_0x0277:
            if (r8 != 0) goto L_0x027b
            r8 = 0
            goto L_0x027f
        L_0x027b:
            org.mozilla.javascript.Scriptable r8 = ensureScriptable(r8)
        L_0x027f:
            org.mozilla.javascript.NativeObject r10 = new org.mozilla.javascript.NativeObject
            r10.<init>()
            org.mozilla.javascript.Scriptable r11 = r7.getParentScope()
            r10.setParentScope(r11)
            r10.setPrototype(r8)
            int r8 = r12.length
            if (r8 <= r4) goto L_0x02a8
            r8 = r12[r4]
            java.lang.Object r11 = org.mozilla.javascript.Undefined.instance
            if (r8 == r11) goto L_0x02a8
            r8 = r12[r4]
            org.mozilla.javascript.Scriptable r11 = r7.getParentScope()
            org.mozilla.javascript.Scriptable r8 = org.mozilla.javascript.Context.toObject(r8, r11)
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            r10.defineOwnProperties(r9, r8)
        L_0x02a8:
            return r10
        L_0x02a9:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x02af
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x02b1
        L_0x02af:
            r8 = r12[r5]
        L_0x02b1:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            boolean r10 = r8.isExtensible()
            if (r10 == 0) goto L_0x02be
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            return r8
        L_0x02be:
            java.lang.Object[] r10 = r8.getAllIds()
            int r11 = r10.length
        L_0x02c3:
            if (r5 >= r11) goto L_0x02dd
            r12 = r10[r5]
            org.mozilla.javascript.ScriptableObject r12 = r8.getOwnPropertyDescriptor(r9, r12)
            java.lang.Object r12 = r12.get(r2)
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            boolean r12 = r0.equals(r12)
            if (r12 == 0) goto L_0x02da
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            return r8
        L_0x02da:
            int r5 = r5 + 1
            goto L_0x02c3
        L_0x02dd:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            return r8
        L_0x02e0:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x02e6
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x02e8
        L_0x02e6:
            r8 = r12[r5]
        L_0x02e8:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            boolean r10 = r8.isExtensible()
            if (r10 == 0) goto L_0x02f5
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            return r8
        L_0x02f5:
            java.lang.Object[] r10 = r8.getAllIds()
            int r11 = r10.length
        L_0x02fa:
            if (r5 >= r11) goto L_0x0329
            r12 = r10[r5]
            org.mozilla.javascript.ScriptableObject r12 = r8.getOwnPropertyDescriptor(r9, r12)
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            java.lang.Object r3 = r12.get(r2)
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x0311
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            return r8
        L_0x0311:
            boolean r0 = r7.isDataDescriptor(r12)
            if (r0 == 0) goto L_0x0326
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            java.lang.Object r12 = r12.get(r1)
            boolean r12 = r0.equals(r12)
            if (r12 == 0) goto L_0x0326
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            return r8
        L_0x0326:
            int r5 = r5 + 1
            goto L_0x02fa
        L_0x0329:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            return r8
        L_0x032c:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x0332
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x0334
        L_0x0332:
            r8 = r12[r5]
        L_0x0334:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            java.lang.Object[] r10 = r8.getAllIds()
            int r11 = r10.length
            r12 = 0
        L_0x033e:
            if (r12 >= r11) goto L_0x035d
            r0 = r10[r12]
            org.mozilla.javascript.ScriptableObject r1 = r8.getOwnPropertyDescriptor(r9, r0)
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            java.lang.Object r4 = r1.get(r2)
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x035a
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            r1.put((java.lang.String) r2, (org.mozilla.javascript.Scriptable) r1, (java.lang.Object) r3)
            r8.defineOwnProperty(r9, r0, r1, r5)
        L_0x035a:
            int r12 = r12 + 1
            goto L_0x033e
        L_0x035d:
            r8.preventExtensions()
            return r8
        L_0x0361:
            int r8 = r12.length
            if (r8 >= r4) goto L_0x0367
            java.lang.Object r8 = org.mozilla.javascript.Undefined.instance
            goto L_0x0369
        L_0x0367:
            r8 = r12[r5]
        L_0x0369:
            org.mozilla.javascript.ScriptableObject r8 = ensureScriptableObject(r8)
            java.lang.Object[] r10 = r8.getAllIds()
            int r11 = r10.length
            r12 = 0
        L_0x0373:
            if (r12 >= r11) goto L_0x03a9
            r0 = r10[r12]
            org.mozilla.javascript.ScriptableObject r3 = r8.getOwnPropertyDescriptor(r9, r0)
            boolean r4 = r7.isDataDescriptor(r3)
            if (r4 == 0) goto L_0x0392
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            java.lang.Object r6 = r3.get(r1)
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0392
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            r3.put((java.lang.String) r1, (org.mozilla.javascript.Scriptable) r3, (java.lang.Object) r4)
        L_0x0392:
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            java.lang.Object r6 = r3.get(r2)
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x03a3
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            r3.put((java.lang.String) r2, (org.mozilla.javascript.Scriptable) r3, (java.lang.Object) r4)
        L_0x03a3:
            r8.defineOwnProperty(r9, r0, r3, r5)
            int r12 = r12 + 1
            goto L_0x0373
        L_0x03a9:
            r8.preventExtensions()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeObject.execIdCall(org.mozilla.javascript.IdFunctionObject, org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    public boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return has((String) obj, this);
        }
        if (obj instanceof Number) {
            return has(((Number) obj).intValue(), (Scriptable) this);
        }
        return false;
    }

    public boolean containsValue(Object obj) {
        for (Object next : values()) {
            if (obj == next) {
                return true;
            }
            if (obj != null && obj.equals(next)) {
                return true;
            }
        }
        return false;
    }

    public Object remove(Object obj) {
        Object obj2 = get(obj);
        if (obj instanceof String) {
            delete((String) obj);
        } else if (obj instanceof Number) {
            delete(((Number) obj).intValue());
        }
        return obj2;
    }

    public Set<Object> keySet() {
        return new KeySet();
    }

    public Collection<Object> values() {
        return new ValueCollection();
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return new EntrySet();
    }

    public Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    public void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    class EntrySet extends AbstractSet<Map.Entry<Object, Object>> {
        EntrySet() {
        }

        public Iterator<Map.Entry<Object, Object>> iterator() {
            return new Iterator<Map.Entry<Object, Object>>() {
                Object[] ids = NativeObject.this.getIds();
                int index = 0;
                Object key = null;

                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                public Map.Entry<Object, Object> next() {
                    Object[] objArr = this.ids;
                    int i = this.index;
                    this.index = i + 1;
                    final Object obj = objArr[i];
                    this.key = obj;
                    final Object obj2 = NativeObject.this.get(this.key);
                    return new Map.Entry<Object, Object>() {
                        public Object getKey() {
                            return obj;
                        }

                        public Object getValue() {
                            return obj2;
                        }

                        public Object setValue(Object obj) {
                            throw new UnsupportedOperationException();
                        }

                        /* JADX WARNING: Removed duplicated region for block: B:14:0x002e A[ORIG_RETURN, RETURN, SYNTHETIC] */
                        /* Code decompiled incorrectly, please refer to instructions dump. */
                        public boolean equals(java.lang.Object r4) {
                            /*
                                r3 = this;
                                boolean r0 = r4 instanceof java.util.Map.Entry
                                r1 = 0
                                if (r0 != 0) goto L_0x0006
                                return r1
                            L_0x0006:
                                java.util.Map$Entry r4 = (java.util.Map.Entry) r4
                                java.lang.Object r0 = r0
                                if (r0 != 0) goto L_0x0013
                                java.lang.Object r0 = r4.getKey()
                                if (r0 != 0) goto L_0x002f
                                goto L_0x001d
                            L_0x0013:
                                java.lang.Object r2 = r4.getKey()
                                boolean r0 = r0.equals(r2)
                                if (r0 == 0) goto L_0x002f
                            L_0x001d:
                                java.lang.Object r0 = r1
                                java.lang.Object r4 = r4.getValue()
                                if (r0 != 0) goto L_0x0028
                                if (r4 != 0) goto L_0x002f
                                goto L_0x002e
                            L_0x0028:
                                boolean r4 = r0.equals(r4)
                                if (r4 == 0) goto L_0x002f
                            L_0x002e:
                                r1 = 1
                            L_0x002f:
                                return r1
                            */
                            throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeObject.EntrySet.AnonymousClass1.AnonymousClass1.equals(java.lang.Object):boolean");
                        }

                        public int hashCode() {
                            Object obj = obj;
                            int i = 0;
                            int hashCode = obj == null ? 0 : obj.hashCode();
                            Object obj2 = obj2;
                            if (obj2 != null) {
                                i = obj2.hashCode();
                            }
                            return hashCode ^ i;
                        }

                        public String toString() {
                            return obj + "=" + obj2;
                        }
                    };
                }

                public void remove() {
                    if (this.key != null) {
                        NativeObject.this.remove(this.key);
                        this.key = null;
                        return;
                    }
                    throw new IllegalStateException();
                }
            };
        }

        public int size() {
            return NativeObject.this.size();
        }
    }

    class KeySet extends AbstractSet<Object> {
        KeySet() {
        }

        public boolean contains(Object obj) {
            return NativeObject.this.containsKey(obj);
        }

        public Iterator<Object> iterator() {
            return new Iterator<Object>() {
                Object[] ids = NativeObject.this.getIds();
                int index = 0;
                Object key;

                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                public Object next() {
                    try {
                        Object[] objArr = this.ids;
                        int i = this.index;
                        this.index = i + 1;
                        Object obj = objArr[i];
                        this.key = obj;
                        return obj;
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        this.key = null;
                        throw new NoSuchElementException();
                    }
                }

                public void remove() {
                    if (this.key != null) {
                        NativeObject.this.remove(this.key);
                        this.key = null;
                        return;
                    }
                    throw new IllegalStateException();
                }
            };
        }

        public int size() {
            return NativeObject.this.size();
        }
    }

    class ValueCollection extends AbstractCollection<Object> {
        ValueCollection() {
        }

        public Iterator<Object> iterator() {
            return new Iterator<Object>() {
                Object[] ids = NativeObject.this.getIds();
                int index = 0;
                Object key;

                public boolean hasNext() {
                    return this.index < this.ids.length;
                }

                public Object next() {
                    NativeObject nativeObject = NativeObject.this;
                    Object[] objArr = this.ids;
                    int i = this.index;
                    this.index = i + 1;
                    Object obj = objArr[i];
                    this.key = obj;
                    return nativeObject.get(obj);
                }

                public void remove() {
                    if (this.key != null) {
                        NativeObject.this.remove(this.key);
                        this.key = null;
                        return;
                    }
                    throw new IllegalStateException();
                }
            };
        }

        public int size() {
            return NativeObject.this.size();
        }
    }

    /* access modifiers changed from: protected */
    public int findPrototypeId(String str) {
        String str2;
        int length = str.length();
        int i = 2;
        if (length != 7) {
            if (length == 8) {
                char charAt = str.charAt(3);
                if (charAt == 'o') {
                    str2 = "toSource";
                    i = 8;
                } else if (charAt == 't') {
                    str2 = "toString";
                }
            } else if (length == 11) {
                i = 1;
                str2 = "constructor";
            } else if (length == 16) {
                char charAt2 = str.charAt(2);
                if (charAt2 == 'd') {
                    char charAt3 = str.charAt(8);
                    if (charAt3 == 'G') {
                        i = 9;
                        str2 = "__defineGetter__";
                    } else if (charAt3 == 'S') {
                        i = 10;
                        str2 = "__defineSetter__";
                    }
                } else if (charAt2 == 'l') {
                    char charAt4 = str.charAt(8);
                    if (charAt4 == 'G') {
                        str2 = "__lookupGetter__";
                        i = 11;
                    } else if (charAt4 == 'S') {
                        i = 12;
                        str2 = "__lookupSetter__";
                    }
                }
            } else if (length == 20) {
                i = 6;
                str2 = "propertyIsEnumerable";
            } else if (length == 13) {
                str2 = "isPrototypeOf";
                i = 7;
            } else if (length == 14) {
                char charAt5 = str.charAt(0);
                if (charAt5 == 'h') {
                    i = 5;
                    str2 = "hasOwnProperty";
                } else if (charAt5 == 't') {
                    str2 = "toLocaleString";
                    i = 3;
                }
            }
            str2 = null;
            i = 0;
        } else {
            i = 4;
            str2 = "valueOf";
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
