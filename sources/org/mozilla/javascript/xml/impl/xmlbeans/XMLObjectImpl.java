package org.mozilla.javascript.xml.impl.xmlbeans;

import org.apache.xmlbeans.XmlObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLLib;
import org.mozilla.javascript.xml.XMLObject;

abstract class XMLObjectImpl extends XMLObject {
    private static final int Id_addNamespace = 2;
    private static final int Id_appendChild = 3;
    private static final int Id_attribute = 4;
    private static final int Id_attributes = 5;
    private static final int Id_child = 6;
    private static final int Id_childIndex = 7;
    private static final int Id_children = 8;
    private static final int Id_comments = 9;
    private static final int Id_constructor = 1;
    private static final int Id_contains = 10;
    private static final int Id_copy = 11;
    private static final int Id_descendants = 12;
    private static final int Id_getXmlObject = 41;
    private static final int Id_hasComplexContent = 17;
    private static final int Id_hasOwnProperty = 16;
    private static final int Id_hasSimpleContent = 18;
    private static final int Id_inScopeNamespaces = 13;
    private static final int Id_insertChildAfter = 14;
    private static final int Id_insertChildBefore = 15;
    private static final int Id_length = 19;
    private static final int Id_localName = 20;
    private static final int Id_name = 21;
    private static final int Id_namespace = 22;
    private static final int Id_namespaceDeclarations = 23;
    private static final int Id_nodeKind = 24;
    private static final int Id_normalize = 25;
    private static final int Id_parent = 26;
    private static final int Id_prependChild = 27;
    private static final int Id_processingInstructions = 28;
    private static final int Id_propertyIsEnumerable = 29;
    private static final int Id_removeNamespace = 30;
    private static final int Id_replace = 31;
    private static final int Id_setChildren = 32;
    private static final int Id_setLocalName = 33;
    private static final int Id_setName = 34;
    private static final int Id_setNamespace = 35;
    private static final int Id_text = 36;
    private static final int Id_toSource = 38;
    private static final int Id_toString = 37;
    private static final int Id_toXMLString = 39;
    private static final int Id_valueOf = 40;
    private static final int MAX_PROTOTYPE_ID = 41;
    private static final Object XMLOBJECT_TAG = "XMLObject";
    protected final XMLLibImpl lib;
    protected boolean prototypeFlag;

    /* access modifiers changed from: package-private */
    public abstract XML addNamespace(Namespace namespace);

    /* access modifiers changed from: package-private */
    public abstract XML appendChild(Object obj);

    /* access modifiers changed from: package-private */
    public abstract XMLList attribute(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract XMLList attributes();

    /* access modifiers changed from: package-private */
    public abstract XMLList child(long j);

    /* access modifiers changed from: package-private */
    public abstract XMLList child(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract int childIndex();

    /* access modifiers changed from: package-private */
    public abstract XMLList children();

    /* access modifiers changed from: package-private */
    public abstract XMLList comments();

    /* access modifiers changed from: package-private */
    public abstract boolean contains(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object copy();

    /* access modifiers changed from: package-private */
    public abstract void deleteXMLProperty(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract XMLList descendants(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract boolean equivalentXml(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object getXMLProperty(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract XmlObject getXmlObject();

    /* access modifiers changed from: package-private */
    public abstract boolean hasComplexContent();

    /* access modifiers changed from: package-private */
    public abstract boolean hasOwnProperty(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract boolean hasSimpleContent();

    /* access modifiers changed from: package-private */
    public abstract boolean hasXMLProperty(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract Object[] inScopeNamespaces();

    /* access modifiers changed from: package-private */
    public abstract XML insertChildAfter(Object obj, Object obj2);

    /* access modifiers changed from: package-private */
    public abstract XML insertChildBefore(Object obj, Object obj2);

    /* access modifiers changed from: protected */
    public abstract Object jsConstructor(Context context, boolean z, Object[] objArr);

    /* access modifiers changed from: package-private */
    public abstract int length();

    /* access modifiers changed from: package-private */
    public abstract String localName();

    /* access modifiers changed from: package-private */
    public abstract QName name();

    /* access modifiers changed from: package-private */
    public abstract Object namespace(String str);

    /* access modifiers changed from: package-private */
    public abstract Object[] namespaceDeclarations();

    /* access modifiers changed from: package-private */
    public abstract Object nodeKind();

    /* access modifiers changed from: package-private */
    public abstract void normalize();

    /* access modifiers changed from: package-private */
    public abstract Object parent();

    /* access modifiers changed from: package-private */
    public abstract XML prependChild(Object obj);

    /* access modifiers changed from: package-private */
    public abstract Object processingInstructions(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract boolean propertyIsEnumerable(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void putXMLProperty(XMLName xMLName, Object obj);

    /* access modifiers changed from: package-private */
    public abstract XML removeNamespace(Namespace namespace);

    /* access modifiers changed from: package-private */
    public abstract XML replace(long j, Object obj);

    /* access modifiers changed from: package-private */
    public abstract XML replace(XMLName xMLName, Object obj);

    /* access modifiers changed from: package-private */
    public abstract XML setChildren(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void setLocalName(String str);

    /* access modifiers changed from: package-private */
    public abstract void setName(QName qName);

    /* access modifiers changed from: package-private */
    public abstract void setNamespace(Namespace namespace);

    /* access modifiers changed from: package-private */
    public abstract XMLList text();

    /* access modifiers changed from: package-private */
    public abstract String toSource(int i);

    public abstract String toString();

    /* access modifiers changed from: package-private */
    public abstract String toXMLString(int i);

    /* access modifiers changed from: package-private */
    public abstract Object valueOf();

    protected XMLObjectImpl(XMLLibImpl xMLLibImpl, XMLObject xMLObject) {
        super(xMLLibImpl.globalScope(), xMLObject);
        this.lib = xMLLibImpl;
    }

    public final Object getDefaultValue(Class cls) {
        return toString();
    }

    /* access modifiers changed from: protected */
    public final Object equivalentValues(Object obj) {
        return equivalentXml(obj) ? Boolean.TRUE : Boolean.FALSE;
    }

    public final XMLLib lib() {
        return this.lib;
    }

    public final boolean has(Context context, Object obj) {
        if (context == null) {
            context = Context.getCurrentContext();
        }
        XMLName xMLNameOrIndex = this.lib.toXMLNameOrIndex(context, obj);
        if (xMLNameOrIndex == null) {
            return has((int) ScriptRuntime.lastUint32Result(context), (Scriptable) this);
        }
        return hasXMLProperty(xMLNameOrIndex);
    }

    public boolean has(String str, Scriptable scriptable) {
        return hasXMLProperty(this.lib.toXMLNameFromString(Context.getCurrentContext(), str));
    }

    public final Object get(Context context, Object obj) {
        if (context == null) {
            context = Context.getCurrentContext();
        }
        XMLName xMLNameOrIndex = this.lib.toXMLNameOrIndex(context, obj);
        if (xMLNameOrIndex != null) {
            return getXMLProperty(xMLNameOrIndex);
        }
        Object obj2 = get((int) ScriptRuntime.lastUint32Result(context), (Scriptable) this);
        return obj2 == Scriptable.NOT_FOUND ? Undefined.instance : obj2;
    }

    public Object get(String str, Scriptable scriptable) {
        return getXMLProperty(this.lib.toXMLNameFromString(Context.getCurrentContext(), str));
    }

    public final void put(Context context, Object obj, Object obj2) {
        if (context == null) {
            context = Context.getCurrentContext();
        }
        XMLName xMLNameOrIndex = this.lib.toXMLNameOrIndex(context, obj);
        if (xMLNameOrIndex == null) {
            put((int) ScriptRuntime.lastUint32Result(context), (Scriptable) this, obj2);
        } else {
            putXMLProperty(xMLNameOrIndex, obj2);
        }
    }

    public void put(String str, Scriptable scriptable, Object obj) {
        putXMLProperty(this.lib.toXMLNameFromString(Context.getCurrentContext(), str), obj);
    }

    public final boolean delete(Context context, Object obj) {
        if (context == null) {
            context = Context.getCurrentContext();
        }
        XMLName xMLNameOrIndex = this.lib.toXMLNameOrIndex(context, obj);
        if (xMLNameOrIndex == null) {
            delete((int) ScriptRuntime.lastUint32Result(context));
            return true;
        }
        deleteXMLProperty(xMLNameOrIndex);
        return true;
    }

    public void delete(String str) {
        deleteXMLProperty(this.lib.toXMLNameFromString(Context.getCurrentContext(), str));
    }

    public Object getFunctionProperty(Context context, int i) {
        if (this.prototypeFlag) {
            return super.get(i, (Scriptable) this);
        }
        Scriptable prototype = getPrototype();
        if (prototype instanceof XMLObject) {
            return ((XMLObject) prototype).getFunctionProperty(context, i);
        }
        return NOT_FOUND;
    }

    public Object getFunctionProperty(Context context, String str) {
        if (this.prototypeFlag) {
            return super.get(str, this);
        }
        Scriptable prototype = getPrototype();
        if (prototype instanceof XMLObject) {
            return ((XMLObject) prototype).getFunctionProperty(context, str);
        }
        return NOT_FOUND;
    }

    public Ref memberRef(Context context, Object obj, int i) {
        XMLName xMLName;
        if ((i & 2) != 0) {
            xMLName = this.lib.toAttributeName(context, obj);
        } else if ((i & 4) != 0) {
            xMLName = this.lib.toXMLName(context, obj);
        } else {
            throw Kit.codeBug();
        }
        if ((i & 4) != 0) {
            xMLName.setIsDescendants();
        }
        xMLName.initXMLObject(this);
        return xMLName;
    }

    public Ref memberRef(Context context, Object obj, Object obj2, int i) {
        XMLName qualifiedName = this.lib.toQualifiedName(context, obj, obj2);
        if ((i & 2) != 0 && !qualifiedName.isAttributeName()) {
            qualifiedName.setAttributeName();
        }
        if ((i & 4) != 0) {
            qualifiedName.setIsDescendants();
        }
        qualifiedName.initXMLObject(this);
        return qualifiedName;
    }

    public NativeWith enterWith(Scriptable scriptable) {
        return new XMLWithScope(this.lib, scriptable, this);
    }

    public NativeWith enterDotQuery(Scriptable scriptable) {
        XMLWithScope xMLWithScope = new XMLWithScope(this.lib, scriptable, this);
        xMLWithScope.initAsDotQuery();
        return xMLWithScope;
    }

    public final Object addValues(Context context, boolean z, Object obj) {
        XMLObject xMLObject;
        XMLObject xMLObject2;
        if (obj instanceof XMLObject) {
            if (z) {
                xMLObject2 = (XMLObject) obj;
                xMLObject = this;
            } else {
                xMLObject = (XMLObject) obj;
                xMLObject2 = this;
            }
            return this.lib.addXMLObjects(context, xMLObject, xMLObject2);
        } else if (obj == Undefined.instance) {
            return ScriptRuntime.toString((Object) this);
        } else {
            return super.addValues(context, z, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public final void exportAsJSClass(boolean z) {
        this.prototypeFlag = true;
        exportAsJSClass(41, this.lib.globalScope(), z);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r14) {
        /*
            r13 = this;
            int r0 = r14.length()
            r1 = 112(0x70, float:1.57E-43)
            r2 = 2
            r3 = 101(0x65, float:1.42E-43)
            r4 = 100
            r5 = 4
            r6 = 3
            r7 = 115(0x73, float:1.61E-43)
            r8 = 116(0x74, float:1.63E-43)
            r9 = 114(0x72, float:1.6E-43)
            r10 = 97
            r11 = 99
            r12 = 0
            switch(r0) {
                case 4: goto L_0x0178;
                case 5: goto L_0x0174;
                case 6: goto L_0x0160;
                case 7: goto L_0x0145;
                case 8: goto L_0x010e;
                case 9: goto L_0x00e7;
                case 10: goto L_0x00d5;
                case 11: goto L_0x00a9;
                case 12: goto L_0x0071;
                case 13: goto L_0x001b;
                case 14: goto L_0x006b;
                case 15: goto L_0x0065;
                case 16: goto L_0x004d;
                case 17: goto L_0x002f;
                case 18: goto L_0x001b;
                case 19: goto L_0x001b;
                case 20: goto L_0x0029;
                case 21: goto L_0x0023;
                case 22: goto L_0x001d;
                default: goto L_0x001b;
            }
        L_0x001b:
            goto L_0x0193
        L_0x001d:
            r2 = 28
            java.lang.String r0 = "processingInstructions"
            goto L_0x0195
        L_0x0023:
            r2 = 23
            java.lang.String r0 = "namespaceDeclarations"
            goto L_0x0195
        L_0x0029:
            r2 = 29
            java.lang.String r0 = "propertyIsEnumerable"
            goto L_0x0195
        L_0x002f:
            char r0 = r14.charAt(r6)
            r1 = 67
            if (r0 != r1) goto L_0x003d
            r2 = 17
            java.lang.String r0 = "hasComplexContent"
            goto L_0x0195
        L_0x003d:
            if (r0 != r11) goto L_0x0045
            r2 = 13
            java.lang.String r0 = "inScopeNamespaces"
            goto L_0x0195
        L_0x0045:
            if (r0 != r3) goto L_0x0193
            r2 = 15
            java.lang.String r0 = "insertChildBefore"
            goto L_0x0195
        L_0x004d:
            char r0 = r14.charAt(r12)
            r1 = 104(0x68, float:1.46E-43)
            if (r0 != r1) goto L_0x005b
            r2 = 18
            java.lang.String r0 = "hasSimpleContent"
            goto L_0x0195
        L_0x005b:
            r1 = 105(0x69, float:1.47E-43)
            if (r0 != r1) goto L_0x0193
            r2 = 14
            java.lang.String r0 = "insertChildAfter"
            goto L_0x0195
        L_0x0065:
            r2 = 30
            java.lang.String r0 = "removeNamespace"
            goto L_0x0195
        L_0x006b:
            r2 = 16
            java.lang.String r0 = "hasOwnProperty"
            goto L_0x0195
        L_0x0071:
            char r0 = r14.charAt(r12)
            if (r0 == r10) goto L_0x00a5
            r2 = 103(0x67, float:1.44E-43)
            if (r0 == r2) goto L_0x009f
            if (r0 == r1) goto L_0x0099
            if (r0 == r7) goto L_0x0081
            goto L_0x0193
        L_0x0081:
            char r0 = r14.charAt(r6)
            r1 = 76
            if (r0 != r1) goto L_0x008f
            r2 = 33
            java.lang.String r0 = "setLocalName"
            goto L_0x0195
        L_0x008f:
            r1 = 78
            if (r0 != r1) goto L_0x0193
            r2 = 35
            java.lang.String r0 = "setNamespace"
            goto L_0x0195
        L_0x0099:
            r2 = 27
            java.lang.String r0 = "prependChild"
            goto L_0x0195
        L_0x009f:
            r2 = 41
            java.lang.String r0 = "getXmlObject"
            goto L_0x0195
        L_0x00a5:
            java.lang.String r0 = "addNamespace"
            goto L_0x0195
        L_0x00a9:
            char r0 = r14.charAt(r12)
            if (r0 == r10) goto L_0x00d0
            if (r0 == r11) goto L_0x00cb
            if (r0 == r4) goto L_0x00c5
            if (r0 == r7) goto L_0x00bf
            if (r0 == r8) goto L_0x00b9
            goto L_0x0193
        L_0x00b9:
            r2 = 39
            java.lang.String r0 = "toXMLString"
            goto L_0x0195
        L_0x00bf:
            r2 = 32
            java.lang.String r0 = "setChildren"
            goto L_0x0195
        L_0x00c5:
            r2 = 12
            java.lang.String r0 = "descendants"
            goto L_0x0195
        L_0x00cb:
            r2 = 1
            java.lang.String r0 = "constructor"
            goto L_0x0195
        L_0x00d0:
            java.lang.String r0 = "appendChild"
            r2 = 3
            goto L_0x0195
        L_0x00d5:
            char r0 = r14.charAt(r12)
            if (r0 != r10) goto L_0x00e0
            r2 = 5
            java.lang.String r0 = "attributes"
            goto L_0x0195
        L_0x00e0:
            if (r0 != r11) goto L_0x0193
            r2 = 7
            java.lang.String r0 = "childIndex"
            goto L_0x0195
        L_0x00e7:
            char r0 = r14.charAt(r2)
            if (r0 == r11) goto L_0x0108
            r1 = 109(0x6d, float:1.53E-43)
            if (r0 == r1) goto L_0x0102
            if (r0 == r9) goto L_0x00fc
            if (r0 == r8) goto L_0x00f7
            goto L_0x0193
        L_0x00f7:
            java.lang.String r0 = "attribute"
            r2 = 4
            goto L_0x0195
        L_0x00fc:
            r2 = 25
            java.lang.String r0 = "normalize"
            goto L_0x0195
        L_0x0102:
            r2 = 22
            java.lang.String r0 = "namespace"
            goto L_0x0195
        L_0x0108:
            r2 = 20
            java.lang.String r0 = "localName"
            goto L_0x0195
        L_0x010e:
            char r0 = r14.charAt(r5)
            r1 = 75
            if (r0 == r1) goto L_0x0140
            if (r0 == r10) goto L_0x013b
            if (r0 == r9) goto L_0x0136
            r1 = 117(0x75, float:1.64E-43)
            if (r0 == r1) goto L_0x0130
            if (r0 == r4) goto L_0x012a
            if (r0 == r3) goto L_0x0124
            goto L_0x0193
        L_0x0124:
            r2 = 9
            java.lang.String r0 = "comments"
            goto L_0x0195
        L_0x012a:
            r2 = 8
            java.lang.String r0 = "children"
            goto L_0x0195
        L_0x0130:
            r2 = 38
            java.lang.String r0 = "toSource"
            goto L_0x0195
        L_0x0136:
            r2 = 37
            java.lang.String r0 = "toString"
            goto L_0x0195
        L_0x013b:
            r2 = 10
            java.lang.String r0 = "contains"
            goto L_0x0195
        L_0x0140:
            r2 = 24
            java.lang.String r0 = "nodeKind"
            goto L_0x0195
        L_0x0145:
            char r0 = r14.charAt(r12)
            if (r0 != r9) goto L_0x0150
            r2 = 31
            java.lang.String r0 = "replace"
            goto L_0x0195
        L_0x0150:
            if (r0 != r7) goto L_0x0157
            r2 = 34
            java.lang.String r0 = "setName"
            goto L_0x0195
        L_0x0157:
            r1 = 118(0x76, float:1.65E-43)
            if (r0 != r1) goto L_0x0193
            r2 = 40
            java.lang.String r0 = "valueOf"
            goto L_0x0195
        L_0x0160:
            char r0 = r14.charAt(r12)
            r2 = 108(0x6c, float:1.51E-43)
            if (r0 != r2) goto L_0x016d
            r2 = 19
            java.lang.String r0 = "length"
            goto L_0x0195
        L_0x016d:
            if (r0 != r1) goto L_0x0193
            r2 = 26
            java.lang.String r0 = "parent"
            goto L_0x0195
        L_0x0174:
            r2 = 6
            java.lang.String r0 = "child"
            goto L_0x0195
        L_0x0178:
            char r0 = r14.charAt(r12)
            if (r0 != r11) goto L_0x0183
            r2 = 11
            java.lang.String r0 = "copy"
            goto L_0x0195
        L_0x0183:
            r1 = 110(0x6e, float:1.54E-43)
            if (r0 != r1) goto L_0x018c
            r2 = 21
            java.lang.String r0 = "name"
            goto L_0x0195
        L_0x018c:
            if (r0 != r8) goto L_0x0193
            r2 = 36
            java.lang.String r0 = "text"
            goto L_0x0195
        L_0x0193:
            r0 = 0
            r2 = 0
        L_0x0195:
            if (r0 == 0) goto L_0x01a0
            if (r0 == r14) goto L_0x01a0
            boolean r14 = r0.equals(r14)
            if (r14 != 0) goto L_0x01a0
            goto L_0x01a1
        L_0x01a0:
            r12 = r2
        L_0x01a1:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XMLObjectImpl.findPrototypeId(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0071, code lost:
        r0 = r1;
        r1 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0097, code lost:
        r1 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0098, code lost:
        initPrototypeMethod(XMLOBJECT_TAG, r5, r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initPrototypeId(int r5) {
        /*
            r4 = this;
            r0 = 2
            r1 = 0
            r2 = 1
            switch(r5) {
                case 1: goto L_0x009e;
                case 2: goto L_0x0095;
                case 3: goto L_0x0092;
                case 4: goto L_0x008f;
                case 5: goto L_0x008c;
                case 6: goto L_0x0089;
                case 7: goto L_0x0086;
                case 8: goto L_0x0083;
                case 9: goto L_0x0080;
                case 10: goto L_0x007d;
                case 11: goto L_0x007a;
                case 12: goto L_0x0077;
                case 13: goto L_0x0074;
                case 14: goto L_0x006f;
                case 15: goto L_0x006c;
                case 16: goto L_0x0069;
                case 17: goto L_0x0066;
                case 18: goto L_0x0063;
                case 19: goto L_0x0060;
                case 20: goto L_0x005d;
                case 21: goto L_0x005a;
                case 22: goto L_0x0057;
                case 23: goto L_0x0054;
                case 24: goto L_0x0051;
                case 25: goto L_0x004e;
                case 26: goto L_0x004b;
                case 27: goto L_0x0047;
                case 28: goto L_0x0043;
                case 29: goto L_0x003f;
                case 30: goto L_0x003b;
                case 31: goto L_0x0038;
                case 32: goto L_0x0034;
                case 33: goto L_0x0030;
                case 34: goto L_0x002c;
                case 35: goto L_0x0028;
                case 36: goto L_0x0024;
                case 37: goto L_0x0020;
                case 38: goto L_0x001c;
                case 39: goto L_0x0018;
                case 40: goto L_0x0014;
                case 41: goto L_0x0010;
                default: goto L_0x0006;
            }
        L_0x0006:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r5 = java.lang.String.valueOf(r5)
            r0.<init>(r5)
            throw r0
        L_0x0010:
            java.lang.String r0 = "getXmlObject"
            goto L_0x0098
        L_0x0014:
            java.lang.String r0 = "valueOf"
            goto L_0x0098
        L_0x0018:
            java.lang.String r0 = "toXMLString"
            goto L_0x0097
        L_0x001c:
            java.lang.String r0 = "toSource"
            goto L_0x0097
        L_0x0020:
            java.lang.String r0 = "toString"
            goto L_0x0098
        L_0x0024:
            java.lang.String r0 = "text"
            goto L_0x0098
        L_0x0028:
            java.lang.String r0 = "setNamespace"
            goto L_0x0097
        L_0x002c:
            java.lang.String r0 = "setName"
            goto L_0x0097
        L_0x0030:
            java.lang.String r0 = "setLocalName"
            goto L_0x0097
        L_0x0034:
            java.lang.String r0 = "setChildren"
            goto L_0x0097
        L_0x0038:
            java.lang.String r1 = "replace"
            goto L_0x0071
        L_0x003b:
            java.lang.String r0 = "removeNamespace"
            goto L_0x0097
        L_0x003f:
            java.lang.String r0 = "propertyIsEnumerable"
            goto L_0x0097
        L_0x0043:
            java.lang.String r0 = "processingInstructions"
            goto L_0x0097
        L_0x0047:
            java.lang.String r0 = "prependChild"
            goto L_0x0097
        L_0x004b:
            java.lang.String r0 = "parent"
            goto L_0x0098
        L_0x004e:
            java.lang.String r0 = "normalize"
            goto L_0x0098
        L_0x0051:
            java.lang.String r0 = "nodeKind"
            goto L_0x0098
        L_0x0054:
            java.lang.String r0 = "namespaceDeclarations"
            goto L_0x0098
        L_0x0057:
            java.lang.String r0 = "namespace"
            goto L_0x0097
        L_0x005a:
            java.lang.String r0 = "name"
            goto L_0x0098
        L_0x005d:
            java.lang.String r0 = "localName"
            goto L_0x0098
        L_0x0060:
            java.lang.String r0 = "length"
            goto L_0x0098
        L_0x0063:
            java.lang.String r0 = "hasSimpleContent"
            goto L_0x0098
        L_0x0066:
            java.lang.String r0 = "hasComplexContent"
            goto L_0x0098
        L_0x0069:
            java.lang.String r0 = "hasOwnProperty"
            goto L_0x0097
        L_0x006c:
            java.lang.String r1 = "insertChildBefore"
            goto L_0x0071
        L_0x006f:
            java.lang.String r1 = "insertChildAfter"
        L_0x0071:
            r0 = r1
            r1 = 2
            goto L_0x0098
        L_0x0074:
            java.lang.String r0 = "inScopeNamespaces"
            goto L_0x0098
        L_0x0077:
            java.lang.String r0 = "descendants"
            goto L_0x0097
        L_0x007a:
            java.lang.String r0 = "copy"
            goto L_0x0098
        L_0x007d:
            java.lang.String r0 = "contains"
            goto L_0x0097
        L_0x0080:
            java.lang.String r0 = "comments"
            goto L_0x0098
        L_0x0083:
            java.lang.String r0 = "children"
            goto L_0x0098
        L_0x0086:
            java.lang.String r0 = "childIndex"
            goto L_0x0098
        L_0x0089:
            java.lang.String r0 = "child"
            goto L_0x0097
        L_0x008c:
            java.lang.String r0 = "attributes"
            goto L_0x0098
        L_0x008f:
            java.lang.String r0 = "attribute"
            goto L_0x0097
        L_0x0092:
            java.lang.String r0 = "appendChild"
            goto L_0x0097
        L_0x0095:
            java.lang.String r0 = "addNamespace"
        L_0x0097:
            r1 = 1
        L_0x0098:
            java.lang.Object r2 = XMLOBJECT_TAG
            r4.initPrototypeMethod(r2, r5, r0, r1)
            return
        L_0x009e:
            boolean r0 = r4 instanceof org.mozilla.javascript.xml.impl.xmlbeans.XML
            if (r0 == 0) goto L_0x00ad
            org.mozilla.javascript.xml.impl.xmlbeans.XMLCtor r0 = new org.mozilla.javascript.xml.impl.xmlbeans.XMLCtor
            r1 = r4
            org.mozilla.javascript.xml.impl.xmlbeans.XML r1 = (org.mozilla.javascript.xml.impl.xmlbeans.XML) r1
            java.lang.Object r3 = XMLOBJECT_TAG
            r0.<init>(r1, r3, r5, r2)
            goto L_0x00b4
        L_0x00ad:
            org.mozilla.javascript.IdFunctionObject r0 = new org.mozilla.javascript.IdFunctionObject
            java.lang.Object r1 = XMLOBJECT_TAG
            r0.<init>(r4, r1, r5, r2)
        L_0x00b4:
            r4.initPrototypeConstructor(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XMLObjectImpl.initPrototypeId(int):void");
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        String str;
        QName qName;
        if (!idFunctionObject.hasTag(XMLOBJECT_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        boolean z = true;
        if (methodId == 1) {
            if (scriptable2 != null) {
                z = false;
            }
            return jsConstructor(context, z, objArr);
        } else if (scriptable2 instanceof XMLObjectImpl) {
            XMLObjectImpl xMLObjectImpl = (XMLObjectImpl) scriptable2;
            switch (methodId) {
                case 2:
                    return xMLObjectImpl.addNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                case 3:
                    return xMLObjectImpl.appendChild(arg(objArr, 0));
                case 4:
                    return xMLObjectImpl.attribute(this.lib.toAttributeName(context, arg(objArr, 0)));
                case 5:
                    return xMLObjectImpl.attributes();
                case 6:
                    XMLName xMLNameOrIndex = this.lib.toXMLNameOrIndex(context, arg(objArr, 0));
                    if (xMLNameOrIndex == null) {
                        return xMLObjectImpl.child(ScriptRuntime.lastUint32Result(context));
                    }
                    return xMLObjectImpl.child(xMLNameOrIndex);
                case 7:
                    return ScriptRuntime.wrapInt(xMLObjectImpl.childIndex());
                case 8:
                    return xMLObjectImpl.children();
                case 9:
                    return xMLObjectImpl.comments();
                case 10:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.contains(arg(objArr, 0)));
                case 11:
                    return xMLObjectImpl.copy();
                case 12:
                    return xMLObjectImpl.descendants(objArr.length == 0 ? XMLName.formStar() : this.lib.toXMLName(context, objArr[0]));
                case 13:
                    return context.newArray(scriptable, xMLObjectImpl.inScopeNamespaces());
                case 14:
                    return xMLObjectImpl.insertChildAfter(arg(objArr, 0), arg(objArr, 1));
                case 15:
                    return xMLObjectImpl.insertChildBefore(arg(objArr, 0), arg(objArr, 1));
                case 16:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasOwnProperty(this.lib.toXMLName(context, arg(objArr, 0))));
                case 17:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasComplexContent());
                case 18:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasSimpleContent());
                case 19:
                    return ScriptRuntime.wrapInt(xMLObjectImpl.length());
                case 20:
                    return xMLObjectImpl.localName();
                case 21:
                    return xMLObjectImpl.name();
                case 22:
                    return xMLObjectImpl.namespace(objArr.length > 0 ? ScriptRuntime.toString(objArr[0]) : null);
                case 23:
                    return context.newArray(scriptable, xMLObjectImpl.namespaceDeclarations());
                case 24:
                    return xMLObjectImpl.nodeKind();
                case 25:
                    xMLObjectImpl.normalize();
                    return Undefined.instance;
                case 26:
                    return xMLObjectImpl.parent();
                case 27:
                    return xMLObjectImpl.prependChild(arg(objArr, 0));
                case 28:
                    return xMLObjectImpl.processingInstructions(objArr.length > 0 ? this.lib.toXMLName(context, objArr[0]) : XMLName.formStar());
                case 29:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.propertyIsEnumerable(arg(objArr, 0)));
                case 30:
                    return xMLObjectImpl.removeNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                case 31:
                    XMLName xMLNameOrIndex2 = this.lib.toXMLNameOrIndex(context, arg(objArr, 0));
                    Object arg = arg(objArr, 1);
                    if (xMLNameOrIndex2 == null) {
                        return xMLObjectImpl.replace(ScriptRuntime.lastUint32Result(context), arg);
                    }
                    return xMLObjectImpl.replace(xMLNameOrIndex2, arg);
                case 32:
                    return xMLObjectImpl.setChildren(arg(objArr, 0));
                case 33:
                    Object arg2 = arg(objArr, 0);
                    if (arg2 instanceof QName) {
                        str = ((QName) arg2).localName();
                    } else {
                        str = ScriptRuntime.toString(arg2);
                    }
                    xMLObjectImpl.setLocalName(str);
                    return Undefined.instance;
                case 34:
                    Object obj = objArr.length != 0 ? objArr[0] : Undefined.instance;
                    if (obj instanceof QName) {
                        QName qName2 = (QName) obj;
                        if (qName2.uri() == null) {
                            qName = this.lib.constructQNameFromString(context, qName2.localName());
                        } else {
                            qName = this.lib.constructQName(context, qName2);
                        }
                    } else {
                        qName = this.lib.constructQName(context, obj);
                    }
                    xMLObjectImpl.setName(qName);
                    return Undefined.instance;
                case 35:
                    xMLObjectImpl.setNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                    return Undefined.instance;
                case 36:
                    return xMLObjectImpl.text();
                case 37:
                    return xMLObjectImpl.toString();
                case 38:
                    return xMLObjectImpl.toSource(ScriptRuntime.toInt32(objArr, 0));
                case 39:
                    return xMLObjectImpl.toXMLString(ScriptRuntime.toInt32(objArr, 0));
                case 40:
                    return xMLObjectImpl.valueOf();
                case 41:
                    return Context.javaToJS(xMLObjectImpl.getXmlObject(), scriptable);
                default:
                    throw new IllegalArgumentException(String.valueOf(methodId));
            }
        } else {
            throw incompatibleCallError(idFunctionObject);
        }
    }

    private static Object arg(Object[] objArr, int i) {
        return i < objArr.length ? objArr[i] : Undefined.instance;
    }
}
