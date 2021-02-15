package org.mozilla.javascript.xmlimpl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.IdFunctionObject;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.NativeWith;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.XmlNode;

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
    private static final int Id_elements = 13;
    private static final int Id_hasComplexContent = 18;
    private static final int Id_hasOwnProperty = 17;
    private static final int Id_hasSimpleContent = 19;
    private static final int Id_inScopeNamespaces = 14;
    private static final int Id_insertChildAfter = 15;
    private static final int Id_insertChildBefore = 16;
    private static final int Id_length = 20;
    private static final int Id_localName = 21;
    private static final int Id_name = 22;
    private static final int Id_namespace = 23;
    private static final int Id_namespaceDeclarations = 24;
    private static final int Id_nodeKind = 25;
    private static final int Id_normalize = 26;
    private static final int Id_parent = 27;
    private static final int Id_prependChild = 28;
    private static final int Id_processingInstructions = 29;
    private static final int Id_propertyIsEnumerable = 30;
    private static final int Id_removeNamespace = 31;
    private static final int Id_replace = 32;
    private static final int Id_setChildren = 33;
    private static final int Id_setLocalName = 34;
    private static final int Id_setName = 35;
    private static final int Id_setNamespace = 36;
    private static final int Id_text = 37;
    private static final int Id_toSource = 39;
    private static final int Id_toString = 38;
    private static final int Id_toXMLString = 40;
    private static final int Id_valueOf = 41;
    private static final int MAX_PROTOTYPE_ID = 41;
    private static final Object XMLOBJECT_TAG = "XMLObject";
    private XMLLibImpl lib;
    private boolean prototypeFlag;

    /* access modifiers changed from: package-private */
    public abstract void addMatches(XMLList xMLList, XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract XMLList child(int i);

    /* access modifiers changed from: package-private */
    public abstract XMLList child(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract XMLList children();

    /* access modifiers changed from: package-private */
    public abstract XMLList comments();

    /* access modifiers changed from: package-private */
    public abstract boolean contains(Object obj);

    /* access modifiers changed from: package-private */
    public abstract XMLObjectImpl copy();

    /* access modifiers changed from: package-private */
    public abstract void deleteXMLProperty(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract XMLList elements(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract boolean equivalentXml(Object obj);

    /* access modifiers changed from: package-private */
    public abstract XML getXML();

    /* access modifiers changed from: package-private */
    public abstract Object getXMLProperty(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract boolean hasComplexContent();

    /* access modifiers changed from: package-private */
    public abstract boolean hasOwnProperty(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract boolean hasSimpleContent();

    /* access modifiers changed from: package-private */
    public abstract boolean hasXMLProperty(XMLName xMLName);

    /* access modifiers changed from: protected */
    public abstract Object jsConstructor(Context context, boolean z, Object[] objArr);

    /* access modifiers changed from: package-private */
    public abstract int length();

    /* access modifiers changed from: package-private */
    public abstract void normalize();

    /* access modifiers changed from: package-private */
    public abstract Object parent();

    /* access modifiers changed from: package-private */
    public abstract XMLList processingInstructions(XMLName xMLName);

    /* access modifiers changed from: package-private */
    public abstract boolean propertyIsEnumerable(Object obj);

    /* access modifiers changed from: package-private */
    public abstract void putXMLProperty(XMLName xMLName, Object obj);

    /* access modifiers changed from: package-private */
    public abstract XMLList text();

    /* access modifiers changed from: package-private */
    public abstract String toSource(int i);

    public abstract String toString();

    /* access modifiers changed from: package-private */
    public abstract String toXMLString();

    /* access modifiers changed from: package-private */
    public abstract Object valueOf();

    protected XMLObjectImpl(XMLLibImpl xMLLibImpl, Scriptable scriptable, XMLObject xMLObject) {
        initialize(xMLLibImpl, scriptable, xMLObject);
    }

    /* access modifiers changed from: package-private */
    public final void initialize(XMLLibImpl xMLLibImpl, Scriptable scriptable, XMLObject xMLObject) {
        setParentScope(scriptable);
        setPrototype(xMLObject);
        this.prototypeFlag = xMLObject == null;
        this.lib = xMLLibImpl;
    }

    /* access modifiers changed from: package-private */
    public final boolean isPrototype() {
        return this.prototypeFlag;
    }

    /* access modifiers changed from: package-private */
    public XMLLibImpl getLib() {
        return this.lib;
    }

    /* access modifiers changed from: package-private */
    public final XML newXML(XmlNode xmlNode) {
        return this.lib.newXML(xmlNode);
    }

    /* access modifiers changed from: package-private */
    public XML xmlFromNode(XmlNode xmlNode) {
        if (xmlNode.getXml() == null) {
            xmlNode.setXml(newXML(xmlNode));
        }
        return xmlNode.getXml();
    }

    /* access modifiers changed from: package-private */
    public final XMLList newXMLList() {
        return this.lib.newXMLList();
    }

    /* access modifiers changed from: package-private */
    public final XMLList newXMLListFrom(Object obj) {
        return this.lib.newXMLListFrom(obj);
    }

    /* access modifiers changed from: package-private */
    public final XmlProcessor getProcessor() {
        return this.lib.getProcessor();
    }

    /* access modifiers changed from: package-private */
    public final QName newQName(String str, String str2, String str3) {
        return this.lib.newQName(str, str2, str3);
    }

    /* access modifiers changed from: package-private */
    public final QName newQName(XmlNode.QName qName) {
        return this.lib.newQName(qName);
    }

    /* access modifiers changed from: package-private */
    public final Namespace createNamespace(XmlNode.Namespace namespace) {
        if (namespace == null) {
            return null;
        }
        return this.lib.createNamespaces(new XmlNode.Namespace[]{namespace})[0];
    }

    /* access modifiers changed from: package-private */
    public final Namespace[] createNamespaces(XmlNode.Namespace[] namespaceArr) {
        return this.lib.createNamespaces(namespaceArr);
    }

    public final Scriptable getPrototype() {
        return super.getPrototype();
    }

    public final void setPrototype(Scriptable scriptable) {
        super.setPrototype(scriptable);
    }

    public final Scriptable getParentScope() {
        return super.getParentScope();
    }

    public final void setParentScope(Scriptable scriptable) {
        super.setParentScope(scriptable);
    }

    public final Object getDefaultValue(Class<?> cls) {
        return toString();
    }

    public final boolean hasInstance(Scriptable scriptable) {
        return super.hasInstance(scriptable);
    }

    private XMLList getMatches(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        addMatches(newXMLList, xMLName);
        return newXMLList;
    }

    /* access modifiers changed from: protected */
    public final Object equivalentValues(Object obj) {
        return equivalentXml(obj) ? Boolean.TRUE : Boolean.FALSE;
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
        if (isPrototype()) {
            return super.get(i, (Scriptable) this);
        }
        Scriptable prototype = getPrototype();
        if (prototype instanceof XMLObject) {
            return ((XMLObject) prototype).getFunctionProperty(context, i);
        }
        return NOT_FOUND;
    }

    public Object getFunctionProperty(Context context, String str) {
        if (isPrototype()) {
            return super.get(str, this);
        }
        Scriptable prototype = getPrototype();
        if (prototype instanceof XMLObject) {
            return ((XMLObject) prototype).getFunctionProperty(context, str);
        }
        return NOT_FOUND;
    }

    public Ref memberRef(Context context, Object obj, int i) {
        boolean z = true;
        boolean z2 = (i & 2) != 0;
        if ((i & 4) == 0) {
            z = false;
        }
        if (z2 || z) {
            XMLName create = XMLName.create(this.lib.toNodeQName(context, obj, z2), z2, z);
            create.initXMLObject(this);
            return create;
        }
        throw Kit.codeBug();
    }

    public Ref memberRef(Context context, Object obj, Object obj2, int i) {
        boolean z = true;
        boolean z2 = (i & 2) != 0;
        if ((i & 4) == 0) {
            z = false;
        }
        XMLName create = XMLName.create(this.lib.toNodeQName(context, obj, obj2), z2, z);
        create.initXMLObject(this);
        return create;
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
        exportAsJSClass(41, getParentScope(), z);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int findPrototypeId(java.lang.String r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = r17.length()
            r3 = 112(0x70, float:1.57E-43)
            r4 = 110(0x6e, float:1.54E-43)
            r5 = 109(0x6d, float:1.53E-43)
            r6 = 100
            r7 = 105(0x69, float:1.47E-43)
            r8 = 114(0x72, float:1.6E-43)
            r9 = 3
            r10 = 115(0x73, float:1.61E-43)
            r11 = 97
            r12 = 116(0x74, float:1.63E-43)
            r13 = 101(0x65, float:1.42E-43)
            r14 = 2
            r15 = 99
            r2 = 0
            switch(r1) {
                case 4: goto L_0x0180;
                case 5: goto L_0x017c;
                case 6: goto L_0x0168;
                case 7: goto L_0x014d;
                case 8: goto L_0x0106;
                case 9: goto L_0x00e1;
                case 10: goto L_0x00cf;
                case 11: goto L_0x00a3;
                case 12: goto L_0x0076;
                case 13: goto L_0x0022;
                case 14: goto L_0x0070;
                case 15: goto L_0x006a;
                case 16: goto L_0x0054;
                case 17: goto L_0x0036;
                case 18: goto L_0x0022;
                case 19: goto L_0x0022;
                case 20: goto L_0x0030;
                case 21: goto L_0x002a;
                case 22: goto L_0x0024;
                default: goto L_0x0022;
            }
        L_0x0022:
            goto L_0x0199
        L_0x0024:
            r1 = 29
            java.lang.String r3 = "processingInstructions"
            goto L_0x019b
        L_0x002a:
            r1 = 24
            java.lang.String r3 = "namespaceDeclarations"
            goto L_0x019b
        L_0x0030:
            r1 = 30
            java.lang.String r3 = "propertyIsEnumerable"
            goto L_0x019b
        L_0x0036:
            char r1 = r0.charAt(r9)
            r3 = 67
            if (r1 != r3) goto L_0x0044
            r1 = 18
            java.lang.String r3 = "hasComplexContent"
            goto L_0x019b
        L_0x0044:
            if (r1 != r15) goto L_0x004c
            r1 = 14
            java.lang.String r3 = "inScopeNamespaces"
            goto L_0x019b
        L_0x004c:
            if (r1 != r13) goto L_0x0199
            r1 = 16
            java.lang.String r3 = "insertChildBefore"
            goto L_0x019b
        L_0x0054:
            char r1 = r0.charAt(r2)
            r3 = 104(0x68, float:1.46E-43)
            if (r1 != r3) goto L_0x0062
            r1 = 19
            java.lang.String r3 = "hasSimpleContent"
            goto L_0x019b
        L_0x0062:
            if (r1 != r7) goto L_0x0199
            r1 = 15
            java.lang.String r3 = "insertChildAfter"
            goto L_0x019b
        L_0x006a:
            r1 = 31
            java.lang.String r3 = "removeNamespace"
            goto L_0x019b
        L_0x0070:
            r1 = 17
            java.lang.String r3 = "hasOwnProperty"
            goto L_0x019b
        L_0x0076:
            char r1 = r0.charAt(r2)
            if (r1 != r11) goto L_0x0081
            java.lang.String r3 = "addNamespace"
            r1 = 2
            goto L_0x019b
        L_0x0081:
            if (r1 != r3) goto L_0x0089
            r1 = 28
            java.lang.String r3 = "prependChild"
            goto L_0x019b
        L_0x0089:
            if (r1 != r10) goto L_0x0199
            char r1 = r0.charAt(r9)
            r3 = 76
            if (r1 != r3) goto L_0x0099
            r1 = 34
            java.lang.String r3 = "setLocalName"
            goto L_0x019b
        L_0x0099:
            r3 = 78
            if (r1 != r3) goto L_0x0199
            r1 = 36
            java.lang.String r3 = "setNamespace"
            goto L_0x019b
        L_0x00a3:
            char r1 = r0.charAt(r2)
            if (r1 == r11) goto L_0x00ca
            if (r1 == r15) goto L_0x00c5
            if (r1 == r6) goto L_0x00bf
            if (r1 == r10) goto L_0x00b9
            if (r1 == r12) goto L_0x00b3
            goto L_0x0199
        L_0x00b3:
            r1 = 40
            java.lang.String r3 = "toXMLString"
            goto L_0x019b
        L_0x00b9:
            r1 = 33
            java.lang.String r3 = "setChildren"
            goto L_0x019b
        L_0x00bf:
            r1 = 12
            java.lang.String r3 = "descendants"
            goto L_0x019b
        L_0x00c5:
            r1 = 1
            java.lang.String r3 = "constructor"
            goto L_0x019b
        L_0x00ca:
            java.lang.String r3 = "appendChild"
            r1 = 3
            goto L_0x019b
        L_0x00cf:
            char r1 = r0.charAt(r2)
            if (r1 != r11) goto L_0x00da
            r1 = 5
            java.lang.String r3 = "attributes"
            goto L_0x019b
        L_0x00da:
            if (r1 != r15) goto L_0x0199
            java.lang.String r3 = "childIndex"
            r1 = 7
            goto L_0x019b
        L_0x00e1:
            char r1 = r0.charAt(r14)
            if (r1 == r15) goto L_0x0100
            if (r1 == r5) goto L_0x00fa
            if (r1 == r8) goto L_0x00f4
            if (r1 == r12) goto L_0x00ef
            goto L_0x0199
        L_0x00ef:
            r1 = 4
            java.lang.String r3 = "attribute"
            goto L_0x019b
        L_0x00f4:
            r1 = 26
            java.lang.String r3 = "normalize"
            goto L_0x019b
        L_0x00fa:
            r1 = 23
            java.lang.String r3 = "namespace"
            goto L_0x019b
        L_0x0100:
            r1 = 21
            java.lang.String r3 = "localName"
            goto L_0x019b
        L_0x0106:
            char r1 = r0.charAt(r14)
            r3 = 83
            if (r1 == r3) goto L_0x0138
            if (r1 == r7) goto L_0x0132
            if (r1 == r6) goto L_0x012c
            if (r1 == r13) goto L_0x0126
            if (r1 == r5) goto L_0x0120
            if (r1 == r4) goto L_0x011a
            goto L_0x0199
        L_0x011a:
            r1 = 10
            java.lang.String r3 = "contains"
            goto L_0x019b
        L_0x0120:
            r1 = 9
            java.lang.String r3 = "comments"
            goto L_0x019b
        L_0x0126:
            r1 = 13
            java.lang.String r3 = "elements"
            goto L_0x019b
        L_0x012c:
            r1 = 25
            java.lang.String r3 = "nodeKind"
            goto L_0x019b
        L_0x0132:
            r1 = 8
            java.lang.String r3 = "children"
            goto L_0x019b
        L_0x0138:
            r1 = 7
            char r1 = r0.charAt(r1)
            if (r1 != r13) goto L_0x0144
            r1 = 39
            java.lang.String r3 = "toSource"
            goto L_0x019b
        L_0x0144:
            r3 = 103(0x67, float:1.44E-43)
            if (r1 != r3) goto L_0x0199
            r1 = 38
            java.lang.String r3 = "toString"
            goto L_0x019b
        L_0x014d:
            char r1 = r0.charAt(r2)
            if (r1 != r8) goto L_0x0158
            r1 = 32
            java.lang.String r3 = "replace"
            goto L_0x019b
        L_0x0158:
            if (r1 != r10) goto L_0x015f
            r1 = 35
            java.lang.String r3 = "setName"
            goto L_0x019b
        L_0x015f:
            r3 = 118(0x76, float:1.65E-43)
            if (r1 != r3) goto L_0x0199
            r1 = 41
            java.lang.String r3 = "valueOf"
            goto L_0x019b
        L_0x0168:
            char r1 = r0.charAt(r2)
            r4 = 108(0x6c, float:1.51E-43)
            if (r1 != r4) goto L_0x0175
            r1 = 20
            java.lang.String r3 = "length"
            goto L_0x019b
        L_0x0175:
            if (r1 != r3) goto L_0x0199
            r1 = 27
            java.lang.String r3 = "parent"
            goto L_0x019b
        L_0x017c:
            r1 = 6
            java.lang.String r3 = "child"
            goto L_0x019b
        L_0x0180:
            char r1 = r0.charAt(r2)
            if (r1 != r15) goto L_0x018b
            r1 = 11
            java.lang.String r3 = "copy"
            goto L_0x019b
        L_0x018b:
            if (r1 != r4) goto L_0x0192
            r1 = 22
            java.lang.String r3 = "name"
            goto L_0x019b
        L_0x0192:
            if (r1 != r12) goto L_0x0199
            r1 = 37
            java.lang.String r3 = "text"
            goto L_0x019b
        L_0x0199:
            r3 = 0
            r1 = 0
        L_0x019b:
            if (r3 == 0) goto L_0x01a6
            if (r3 == r0) goto L_0x01a6
            boolean r0 = r3.equals(r0)
            if (r0 != 0) goto L_0x01a6
            goto L_0x01a7
        L_0x01a6:
            r2 = r1
        L_0x01a7:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLObjectImpl.findPrototypeId(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006d, code lost:
        r0 = r1;
        r2 = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x008d, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0097, code lost:
        initPrototypeMethod(XMLOBJECT_TAG, r5, r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009c, code lost:
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
                case 1: goto L_0x009d;
                case 2: goto L_0x0095;
                case 3: goto L_0x0092;
                case 4: goto L_0x008f;
                case 5: goto L_0x008b;
                case 6: goto L_0x0088;
                case 7: goto L_0x0085;
                case 8: goto L_0x0082;
                case 9: goto L_0x007f;
                case 10: goto L_0x007c;
                case 11: goto L_0x0079;
                case 12: goto L_0x0076;
                case 13: goto L_0x0073;
                case 14: goto L_0x0070;
                case 15: goto L_0x006b;
                case 16: goto L_0x0068;
                case 17: goto L_0x0065;
                case 18: goto L_0x0062;
                case 19: goto L_0x005f;
                case 20: goto L_0x005c;
                case 21: goto L_0x0059;
                case 22: goto L_0x0056;
                case 23: goto L_0x0053;
                case 24: goto L_0x0050;
                case 25: goto L_0x004d;
                case 26: goto L_0x004a;
                case 27: goto L_0x0047;
                case 28: goto L_0x0043;
                case 29: goto L_0x003f;
                case 30: goto L_0x003b;
                case 31: goto L_0x0037;
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
            java.lang.String r0 = "valueOf"
            goto L_0x008d
        L_0x0014:
            java.lang.String r0 = "toXMLString"
            goto L_0x0097
        L_0x0018:
            java.lang.String r0 = "toSource"
            goto L_0x0097
        L_0x001c:
            java.lang.String r0 = "toString"
            goto L_0x008d
        L_0x0020:
            java.lang.String r0 = "text"
            goto L_0x008d
        L_0x0024:
            java.lang.String r0 = "setNamespace"
            goto L_0x0097
        L_0x0028:
            java.lang.String r0 = "setName"
            goto L_0x0097
        L_0x002c:
            java.lang.String r0 = "setLocalName"
            goto L_0x0097
        L_0x0030:
            java.lang.String r0 = "setChildren"
            goto L_0x0097
        L_0x0034:
            java.lang.String r1 = "replace"
            goto L_0x006d
        L_0x0037:
            java.lang.String r0 = "removeNamespace"
            goto L_0x0097
        L_0x003b:
            java.lang.String r0 = "propertyIsEnumerable"
            goto L_0x0097
        L_0x003f:
            java.lang.String r0 = "processingInstructions"
            goto L_0x0097
        L_0x0043:
            java.lang.String r0 = "prependChild"
            goto L_0x0097
        L_0x0047:
            java.lang.String r0 = "parent"
            goto L_0x008d
        L_0x004a:
            java.lang.String r0 = "normalize"
            goto L_0x008d
        L_0x004d:
            java.lang.String r0 = "nodeKind"
            goto L_0x008d
        L_0x0050:
            java.lang.String r0 = "namespaceDeclarations"
            goto L_0x008d
        L_0x0053:
            java.lang.String r0 = "namespace"
            goto L_0x0097
        L_0x0056:
            java.lang.String r0 = "name"
            goto L_0x008d
        L_0x0059:
            java.lang.String r0 = "localName"
            goto L_0x008d
        L_0x005c:
            java.lang.String r0 = "length"
            goto L_0x008d
        L_0x005f:
            java.lang.String r0 = "hasSimpleContent"
            goto L_0x008d
        L_0x0062:
            java.lang.String r0 = "hasComplexContent"
            goto L_0x008d
        L_0x0065:
            java.lang.String r0 = "hasOwnProperty"
            goto L_0x0097
        L_0x0068:
            java.lang.String r1 = "insertChildBefore"
            goto L_0x006d
        L_0x006b:
            java.lang.String r1 = "insertChildAfter"
        L_0x006d:
            r0 = r1
            r2 = 2
            goto L_0x0097
        L_0x0070:
            java.lang.String r0 = "inScopeNamespaces"
            goto L_0x008d
        L_0x0073:
            java.lang.String r0 = "elements"
            goto L_0x0097
        L_0x0076:
            java.lang.String r0 = "descendants"
            goto L_0x0097
        L_0x0079:
            java.lang.String r0 = "copy"
            goto L_0x008d
        L_0x007c:
            java.lang.String r0 = "contains"
            goto L_0x0097
        L_0x007f:
            java.lang.String r0 = "comments"
            goto L_0x008d
        L_0x0082:
            java.lang.String r0 = "children"
            goto L_0x008d
        L_0x0085:
            java.lang.String r0 = "childIndex"
            goto L_0x008d
        L_0x0088:
            java.lang.String r0 = "child"
            goto L_0x0097
        L_0x008b:
            java.lang.String r0 = "attributes"
        L_0x008d:
            r2 = 0
            goto L_0x0097
        L_0x008f:
            java.lang.String r0 = "attribute"
            goto L_0x0097
        L_0x0092:
            java.lang.String r0 = "appendChild"
            goto L_0x0097
        L_0x0095:
            java.lang.String r0 = "addNamespace"
        L_0x0097:
            java.lang.Object r1 = XMLOBJECT_TAG
            r4.initPrototypeMethod(r1, r5, r0, r2)
            return
        L_0x009d:
            boolean r0 = r4 instanceof org.mozilla.javascript.xmlimpl.XML
            if (r0 == 0) goto L_0x00ac
            org.mozilla.javascript.xmlimpl.XMLCtor r0 = new org.mozilla.javascript.xmlimpl.XMLCtor
            r1 = r4
            org.mozilla.javascript.xmlimpl.XML r1 = (org.mozilla.javascript.xmlimpl.XML) r1
            java.lang.Object r3 = XMLOBJECT_TAG
            r0.<init>(r1, r3, r5, r2)
            goto L_0x00b3
        L_0x00ac:
            org.mozilla.javascript.IdFunctionObject r0 = new org.mozilla.javascript.IdFunctionObject
            java.lang.Object r1 = XMLOBJECT_TAG
            r0.<init>(r4, r1, r5, r2)
        L_0x00b3:
            r4.initPrototypeConstructor(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLObjectImpl.initPrototypeId(int):void");
    }

    private Object[] toObjectArray(Object[] objArr) {
        int length = objArr.length;
        Object[] objArr2 = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr2[i] = objArr[i];
        }
        return objArr2;
    }

    private void xmlMethodNotFound(Object obj, String str) {
        throw ScriptRuntime.notFunctionError(obj, str);
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        String str;
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
            XML xml = xMLObjectImpl.getXML();
            String str2 = null;
            switch (methodId) {
                case 2:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "addNamespace");
                    }
                    return xml.addNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                case 3:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "appendChild");
                    }
                    return xml.appendChild(arg(objArr, 0));
                case 4:
                    return xMLObjectImpl.getMatches(XMLName.create(this.lib.toNodeQName(context, arg(objArr, 0), true), true, false));
                case 5:
                    return xMLObjectImpl.getMatches(XMLName.create(XmlNode.QName.create((XmlNode.Namespace) null, (String) null), true, false));
                case 6:
                    XMLName xMLNameOrIndex = this.lib.toXMLNameOrIndex(context, arg(objArr, 0));
                    if (xMLNameOrIndex == null) {
                        return xMLObjectImpl.child((int) ScriptRuntime.lastUint32Result(context));
                    }
                    return xMLObjectImpl.child(xMLNameOrIndex);
                case 7:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "childIndex");
                    }
                    return ScriptRuntime.wrapInt(xml.childIndex());
                case 8:
                    return xMLObjectImpl.children();
                case 9:
                    return xMLObjectImpl.comments();
                case 10:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.contains(arg(objArr, 0)));
                case 11:
                    return xMLObjectImpl.copy();
                case 12:
                    return xMLObjectImpl.getMatches(XMLName.create(objArr.length == 0 ? XmlNode.QName.create((XmlNode.Namespace) null, (String) null) : this.lib.toNodeQName(context, objArr[0], false), false, true));
                case 13:
                    return xMLObjectImpl.elements(objArr.length == 0 ? XMLName.formStar() : this.lib.toXMLName(context, objArr[0]));
                case 14:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "inScopeNamespaces");
                    }
                    return context.newArray(scriptable, toObjectArray(xml.inScopeNamespaces()));
                case 15:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "insertChildAfter");
                    }
                    Object arg = arg(objArr, 0);
                    if (arg == null || (arg instanceof XML)) {
                        return xml.insertChildAfter((XML) arg, arg(objArr, 1));
                    }
                    return Undefined.instance;
                case 16:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "insertChildBefore");
                    }
                    Object arg2 = arg(objArr, 0);
                    if (arg2 == null || (arg2 instanceof XML)) {
                        return xml.insertChildBefore((XML) arg2, arg(objArr, 1));
                    }
                    return Undefined.instance;
                case 17:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasOwnProperty(this.lib.toXMLName(context, arg(objArr, 0))));
                case 18:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasComplexContent());
                case 19:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.hasSimpleContent());
                case 20:
                    return ScriptRuntime.wrapInt(xMLObjectImpl.length());
                case 21:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "localName");
                    }
                    return xml.localName();
                case 22:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "name");
                    }
                    return xml.name();
                case 23:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "namespace");
                    }
                    if (objArr.length > 0) {
                        str2 = ScriptRuntime.toString(objArr[0]);
                    }
                    Namespace namespace = xml.namespace(str2);
                    if (namespace == null) {
                        return Undefined.instance;
                    }
                    return namespace;
                case 24:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "namespaceDeclarations");
                    }
                    return context.newArray(scriptable, toObjectArray(xml.namespaceDeclarations()));
                case 25:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "nodeKind");
                    }
                    return xml.nodeKind();
                case 26:
                    xMLObjectImpl.normalize();
                    return Undefined.instance;
                case 27:
                    return xMLObjectImpl.parent();
                case 28:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "prependChild");
                    }
                    return xml.prependChild(arg(objArr, 0));
                case 29:
                    return xMLObjectImpl.processingInstructions(objArr.length > 0 ? this.lib.toXMLName(context, objArr[0]) : XMLName.formStar());
                case 30:
                    return ScriptRuntime.wrapBoolean(xMLObjectImpl.propertyIsEnumerable(arg(objArr, 0)));
                case 31:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "removeNamespace");
                    }
                    return xml.removeNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                case 32:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "replace");
                    }
                    XMLName xMLNameOrIndex2 = this.lib.toXMLNameOrIndex(context, arg(objArr, 0));
                    Object arg3 = arg(objArr, 1);
                    if (xMLNameOrIndex2 == null) {
                        return xml.replace((int) ScriptRuntime.lastUint32Result(context), arg3);
                    }
                    return xml.replace(xMLNameOrIndex2, arg3);
                case 33:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "setChildren");
                    }
                    return xml.setChildren(arg(objArr, 0));
                case 34:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "setLocalName");
                    }
                    Object arg4 = arg(objArr, 0);
                    if (arg4 instanceof QName) {
                        str = ((QName) arg4).localName();
                    } else {
                        str = ScriptRuntime.toString(arg4);
                    }
                    xml.setLocalName(str);
                    return Undefined.instance;
                case 35:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "setName");
                    }
                    xml.setName(this.lib.constructQName(context, objArr.length != 0 ? objArr[0] : Undefined.instance));
                    return Undefined.instance;
                case 36:
                    if (xml == null) {
                        xmlMethodNotFound(xMLObjectImpl, "setNamespace");
                    }
                    xml.setNamespace(this.lib.castToNamespace(context, arg(objArr, 0)));
                    return Undefined.instance;
                case 37:
                    return xMLObjectImpl.text();
                case 38:
                    return xMLObjectImpl.toString();
                case 39:
                    return xMLObjectImpl.toSource(ScriptRuntime.toInt32(objArr, 0));
                case 40:
                    return xMLObjectImpl.toXMLString();
                case 41:
                    return xMLObjectImpl.valueOf();
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

    /* access modifiers changed from: package-private */
    public final XML newTextElementXML(XmlNode xmlNode, XmlNode.QName qName, String str) {
        return this.lib.newTextElementXML(xmlNode, qName, str);
    }

    /* access modifiers changed from: package-private */
    public final XML newXMLFromJs(Object obj) {
        return this.lib.newXMLFromJs(obj);
    }

    /* access modifiers changed from: package-private */
    public final XML ecmaToXml(Object obj) {
        return this.lib.ecmaToXml(obj);
    }

    /* access modifiers changed from: package-private */
    public final String ecmaEscapeAttributeValue(String str) {
        String escapeAttributeValue = this.lib.escapeAttributeValue(str);
        return escapeAttributeValue.substring(1, escapeAttributeValue.length() - 1);
    }

    /* access modifiers changed from: package-private */
    public final XML createEmptyXML() {
        return newXML(XmlNode.createEmpty(getProcessor()));
    }
}
