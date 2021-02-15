package org.mozilla.javascript.xmlimpl;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.ads.AdError;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xmlimpl.XmlNode;

class XMLList extends XMLObjectImpl implements Function {
    static final long serialVersionUID = -4543618751670781135L;
    private XmlNode.InternalList _annos = new XmlNode.InternalList();
    private XMLObjectImpl targetObject = null;
    private XmlNode.QName targetProperty = null;

    public String getClassName() {
        return "XMLList";
    }

    /* access modifiers changed from: package-private */
    public Object valueOf() {
        return this;
    }

    XMLList(XMLLibImpl xMLLibImpl, Scriptable scriptable, XMLObject xMLObject) {
        super(xMLLibImpl, scriptable, xMLObject);
    }

    /* access modifiers changed from: package-private */
    public XmlNode.InternalList getNodeList() {
        return this._annos;
    }

    /* access modifiers changed from: package-private */
    public void setTargets(XMLObjectImpl xMLObjectImpl, XmlNode.QName qName) {
        this.targetObject = xMLObjectImpl;
        this.targetProperty = qName;
    }

    private XML getXmlFromAnnotation(int i) {
        return getXML(this._annos, i);
    }

    /* access modifiers changed from: package-private */
    public XML getXML() {
        if (length() == 1) {
            return getXmlFromAnnotation(0);
        }
        return null;
    }

    private void internalRemoveFromList(int i) {
        this._annos.remove(i);
    }

    /* access modifiers changed from: package-private */
    public void replace(int i, XML xml) {
        if (i < length()) {
            XmlNode.InternalList internalList = new XmlNode.InternalList();
            internalList.add(this._annos, 0, i);
            internalList.add(xml);
            internalList.add(this._annos, i + 1, length());
            this._annos = internalList;
        }
    }

    private void insert(int i, XML xml) {
        if (i < length()) {
            XmlNode.InternalList internalList = new XmlNode.InternalList();
            internalList.add(this._annos, 0, i);
            internalList.add(xml);
            internalList.add(this._annos, i, length());
            this._annos = internalList;
        }
    }

    public Object get(int i, Scriptable scriptable) {
        if (i < 0 || i >= length()) {
            return Scriptable.NOT_FOUND;
        }
        return getXmlFromAnnotation(i);
    }

    /* access modifiers changed from: package-private */
    public boolean hasXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName).length() > 0;
    }

    public boolean has(int i, Scriptable scriptable) {
        return i >= 0 && i < length();
    }

    /* access modifiers changed from: package-private */
    public void putXMLProperty(XMLName xMLName, Object obj) {
        XmlNode.QName qName;
        if (obj == null) {
            obj = "null";
        } else if (obj instanceof Undefined) {
            obj = AdError.UNDEFINED_DOMAIN;
        }
        if (length() > 1) {
            throw ScriptRuntime.typeError("Assignment to lists with more than one item is not supported");
        } else if (length() == 0) {
            if (this.targetObject == null || (qName = this.targetProperty) == null || qName.getLocalName() == null || this.targetProperty.getLocalName().length() <= 0) {
                throw ScriptRuntime.typeError("Assignment to empty XMLList without targets not supported");
            }
            addToList(newTextElementXML((XmlNode) null, this.targetProperty, (String) null));
            if (xMLName.isAttributeName()) {
                setAttribute(xMLName, obj);
            } else {
                item(0).putXMLProperty(xMLName, obj);
                replace(0, item(0));
            }
            this.targetObject.putXMLProperty(XMLName.formProperty(this.targetProperty.getNamespace().getUri(), this.targetProperty.getLocalName()), this);
            replace(0, this.targetObject.getXML().getLastXmlChild());
        } else if (xMLName.isAttributeName()) {
            setAttribute(xMLName, obj);
        } else {
            item(0).putXMLProperty(xMLName, obj);
            replace(0, item(0));
        }
    }

    /* access modifiers changed from: package-private */
    public Object getXMLProperty(XMLName xMLName) {
        return getPropertyList(xMLName);
    }

    private void replaceNode(XML xml, XML xml2) {
        xml.replaceWith(xml2);
    }

    public void put(int i, Scriptable scriptable, Object obj) {
        Object obj2;
        Object obj3;
        Object obj4 = Undefined.instance;
        if (obj == null) {
            obj = "null";
        } else if (obj instanceof Undefined) {
            obj = AdError.UNDEFINED_DOMAIN;
        }
        if (obj instanceof XMLObject) {
            obj2 = (XMLObject) obj;
        } else if (this.targetProperty == null) {
            obj2 = newXMLFromJs(obj.toString());
        } else {
            Object item = item(i);
            if (item == null) {
                XML item2 = item(0);
                item = item2 == null ? newTextElementXML((XmlNode) null, this.targetProperty, (String) null) : item2.copy();
            }
            ((XML) item).setChildren(obj);
            obj2 = item;
        }
        if (i < length()) {
            obj3 = item(i).parent();
        } else if (length() == 0) {
            XMLObjectImpl xMLObjectImpl = this.targetObject;
            obj3 = xMLObjectImpl != null ? xMLObjectImpl.getXML() : parent();
        } else {
            obj3 = parent();
        }
        if (obj3 instanceof XML) {
            XML xml = (XML) obj3;
            if (i < length()) {
                XML xmlFromAnnotation = getXmlFromAnnotation(i);
                if (obj2 instanceof XML) {
                    replaceNode(xmlFromAnnotation, (XML) obj2);
                    replace(i, xmlFromAnnotation);
                } else if (obj2 instanceof XMLList) {
                    XMLList xMLList = (XMLList) obj2;
                    if (xMLList.length() > 0) {
                        int childIndex = xmlFromAnnotation.childIndex();
                        replaceNode(xmlFromAnnotation, xMLList.item(0));
                        replace(i, xMLList.item(0));
                        for (int i2 = 1; i2 < xMLList.length(); i2++) {
                            xml.insertChildAfter(xml.getXmlChild(childIndex), xMLList.item(i2));
                            childIndex++;
                            insert(i + i2, xMLList.item(i2));
                        }
                    }
                }
            } else {
                xml.appendChild(obj2);
                addToList(xml.getLastXmlChild());
            }
        } else if (i < length()) {
            XML xml2 = getXML(this._annos, i);
            if (obj2 instanceof XML) {
                replaceNode(xml2, (XML) obj2);
                replace(i, xml2);
            } else if (obj2 instanceof XMLList) {
                XMLList xMLList2 = (XMLList) obj2;
                if (xMLList2.length() > 0) {
                    replaceNode(xml2, xMLList2.item(0));
                    replace(i, xMLList2.item(0));
                    for (int i3 = 1; i3 < xMLList2.length(); i3++) {
                        insert(i + i3, xMLList2.item(i3));
                    }
                }
            }
        } else {
            addToList(obj2);
        }
    }

    private XML getXML(XmlNode.InternalList internalList, int i) {
        if (i < 0 || i >= length()) {
            return null;
        }
        return xmlFromNode(internalList.item(i));
    }

    /* access modifiers changed from: package-private */
    public void deleteXMLProperty(XMLName xMLName) {
        for (int i = 0; i < length(); i++) {
            XML xmlFromAnnotation = getXmlFromAnnotation(i);
            if (xmlFromAnnotation.isElement()) {
                xmlFromAnnotation.deleteXMLProperty(xMLName);
            }
        }
    }

    public void delete(int i) {
        if (i >= 0 && i < length()) {
            getXmlFromAnnotation(i).remove();
            internalRemoveFromList(i);
        }
    }

    public Object[] getIds() {
        if (isPrototype()) {
            return new Object[0];
        }
        int length = length();
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = Integer.valueOf(i);
        }
        return objArr;
    }

    public Object[] getIdsForDebug() {
        return getIds();
    }

    /* access modifiers changed from: package-private */
    public void remove() {
        for (int length = length() - 1; length >= 0; length--) {
            XML xmlFromAnnotation = getXmlFromAnnotation(length);
            if (xmlFromAnnotation != null) {
                xmlFromAnnotation.remove();
                internalRemoveFromList(length);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public XML item(int i) {
        return this._annos != null ? getXmlFromAnnotation(i) : createEmptyXML();
    }

    private void setAttribute(XMLName xMLName, Object obj) {
        for (int i = 0; i < length(); i++) {
            getXmlFromAnnotation(i).setAttribute(xMLName, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void addToList(Object obj) {
        this._annos.addToList(obj);
    }

    /* access modifiers changed from: package-private */
    public XMLList child(int i) {
        XMLList newXMLList = newXMLList();
        for (int i2 = 0; i2 < length(); i2++) {
            newXMLList.addToList(getXmlFromAnnotation(i2).child(i));
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList child(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).child(xMLName));
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public void addMatches(XMLList xMLList, XMLName xMLName) {
        for (int i = 0; i < length(); i++) {
            getXmlFromAnnotation(i).addMatches(xMLList, xMLName);
        }
    }

    /* access modifiers changed from: package-private */
    public XMLList children() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < length(); i++) {
            XML xmlFromAnnotation = getXmlFromAnnotation(i);
            if (xmlFromAnnotation != null) {
                XMLList children = xmlFromAnnotation.children();
                int length = children.length();
                for (int i2 = 0; i2 < length; i2++) {
                    arrayList.add(children.item(i2));
                }
            }
        }
        XMLList newXMLList = newXMLList();
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            newXMLList.addToList(arrayList.get(i3));
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList comments() {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).comments());
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList elements(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).elements(xMLName));
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public boolean contains(Object obj) {
        for (int i = 0; i < length(); i++) {
            if (getXmlFromAnnotation(i).equivalentXml(obj)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public XMLObjectImpl copy() {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).copy());
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public boolean hasOwnProperty(XMLName xMLName) {
        if (isPrototype()) {
            if (findPrototypeId(xMLName.localName()) != 0) {
                return true;
            }
            return false;
        } else if (getPropertyList(xMLName).length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasComplexContent() {
        int length = length();
        if (length != 0) {
            if (length == 1) {
                return getXmlFromAnnotation(0).hasComplexContent();
            }
            for (int i = 0; i < length; i++) {
                if (getXmlFromAnnotation(i).isElement()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasSimpleContent() {
        if (length() == 0) {
            return true;
        }
        if (length() == 1) {
            return getXmlFromAnnotation(0).hasSimpleContent();
        }
        for (int i = 0; i < length(); i++) {
            if (getXmlFromAnnotation(i).isElement()) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int length() {
        XmlNode.InternalList internalList = this._annos;
        if (internalList != null) {
            return internalList.length();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void normalize() {
        for (int i = 0; i < length(); i++) {
            getXmlFromAnnotation(i).normalize();
        }
    }

    /* access modifiers changed from: package-private */
    public Object parent() {
        if (length() == 0) {
            return Undefined.instance;
        }
        XML xml = null;
        for (int i = 0; i < length(); i++) {
            Object parent = getXmlFromAnnotation(i).parent();
            if (!(parent instanceof XML)) {
                return Undefined.instance;
            }
            XML xml2 = (XML) parent;
            if (i == 0) {
                xml = xml2;
            } else if (!xml.is(xml2)) {
                return Undefined.instance;
            }
        }
        return xml;
    }

    /* access modifiers changed from: package-private */
    public XMLList processingInstructions(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).processingInstructions(xMLName));
        }
        return newXMLList;
    }

    /* access modifiers changed from: package-private */
    public boolean propertyIsEnumerable(Object obj) {
        long j;
        if (obj instanceof Integer) {
            j = (long) ((Integer) obj).intValue();
        } else if (obj instanceof Number) {
            double doubleValue = ((Number) obj).doubleValue();
            long j2 = (long) doubleValue;
            if (((double) j2) != doubleValue) {
                return false;
            }
            if (j2 == 0 && 1.0d / doubleValue < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                return false;
            }
            j = j2;
        } else {
            j = ScriptRuntime.testUint32String(ScriptRuntime.toString(obj));
        }
        if (0 > j || j >= ((long) length())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public XMLList text() {
        XMLList newXMLList = newXMLList();
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).text());
        }
        return newXMLList;
    }

    public String toString() {
        if (!hasSimpleContent()) {
            return toXMLString();
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length(); i++) {
            XML xmlFromAnnotation = getXmlFromAnnotation(i);
            if (!xmlFromAnnotation.isComment() && !xmlFromAnnotation.isProcessingInstruction()) {
                stringBuffer.append(xmlFromAnnotation.toString());
            }
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String toSource(int i) {
        return toXMLString();
    }

    /* access modifiers changed from: package-private */
    public String toXMLString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length(); i++) {
            if (getProcessor().isPrettyPrinting() && i != 0) {
                stringBuffer.append(10);
            }
            stringBuffer.append(getXmlFromAnnotation(i).toXMLString());
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean equivalentXml(Object obj) {
        if ((obj instanceof Undefined) && length() == 0) {
            return true;
        }
        if (length() == 1) {
            return getXmlFromAnnotation(0).equivalentXml(obj);
        }
        if (obj instanceof XMLList) {
            XMLList xMLList = (XMLList) obj;
            if (xMLList.length() == length()) {
                int i = 0;
                while (i < length()) {
                    if (getXmlFromAnnotation(i).equivalentXml(xMLList.getXmlFromAnnotation(i))) {
                        i++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private XMLList getPropertyList(XMLName xMLName) {
        XMLList newXMLList = newXMLList();
        newXMLList.setTargets(this, (xMLName.isDescendants() || xMLName.isAttributeName()) ? null : xMLName.toQname());
        for (int i = 0; i < length(); i++) {
            newXMLList.addToList(getXmlFromAnnotation(i).getPropertyList(xMLName));
        }
        return newXMLList;
    }

    private Object applyOrCall(boolean z, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        String str = z ? "apply" : NotificationCompat.CATEGORY_CALL;
        if ((scriptable2 instanceof XMLList) && ((XMLList) scriptable2).targetProperty != null) {
            return ScriptRuntime.applyOrCall(z, context, scriptable, scriptable2, objArr);
        }
        throw ScriptRuntime.typeError1("msg.isnt.function", str);
    }

    /* access modifiers changed from: protected */
    public Object jsConstructor(Context context, boolean z, Object[] objArr) {
        if (objArr.length == 0) {
            return newXMLList();
        }
        Object obj = objArr[0];
        if (z || !(obj instanceof XMLList)) {
            return newXMLListFrom(obj);
        }
        return obj;
    }

    public Scriptable getExtraMethodSource(Context context) {
        if (length() == 1) {
            return getXmlFromAnnotation(0);
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        r11 = (org.mozilla.javascript.xml.XMLObject) r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object call(org.mozilla.javascript.Context r9, org.mozilla.javascript.Scriptable r10, org.mozilla.javascript.Scriptable r11, java.lang.Object[] r12) {
        /*
            r8 = this;
            org.mozilla.javascript.xmlimpl.XmlNode$QName r0 = r8.targetProperty
            if (r0 == 0) goto L_0x005f
            java.lang.String r0 = r0.getLocalName()
            java.lang.String r1 = "apply"
            boolean r3 = r0.equals(r1)
            if (r3 != 0) goto L_0x0055
            java.lang.String r1 = "call"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0019
            goto L_0x0055
        L_0x0019:
            boolean r1 = r11 instanceof org.mozilla.javascript.xml.XMLObject
            if (r1 == 0) goto L_0x004e
            r1 = 0
        L_0x001e:
            r2 = r1
        L_0x001f:
            r1 = r11
        L_0x0020:
            boolean r3 = r11 instanceof org.mozilla.javascript.xml.XMLObject
            if (r3 == 0) goto L_0x003e
            org.mozilla.javascript.xml.XMLObject r11 = (org.mozilla.javascript.xml.XMLObject) r11
            java.lang.Object r2 = r11.getFunctionProperty((org.mozilla.javascript.Context) r9, (java.lang.String) r0)
            java.lang.Object r3 = org.mozilla.javascript.Scriptable.NOT_FOUND
            if (r2 == r3) goto L_0x002f
            goto L_0x003e
        L_0x002f:
            org.mozilla.javascript.Scriptable r11 = r11.getExtraMethodSource(r9)
            if (r11 == 0) goto L_0x0020
            boolean r1 = r11 instanceof org.mozilla.javascript.xml.XMLObject
            if (r1 != 0) goto L_0x001f
            java.lang.Object r1 = org.mozilla.javascript.ScriptableObject.getProperty((org.mozilla.javascript.Scriptable) r11, (java.lang.String) r0)
            goto L_0x001e
        L_0x003e:
            boolean r11 = r2 instanceof org.mozilla.javascript.Callable
            if (r11 == 0) goto L_0x0049
            org.mozilla.javascript.Callable r2 = (org.mozilla.javascript.Callable) r2
            java.lang.Object r9 = r2.call(r9, r10, r1, r12)
            return r9
        L_0x0049:
            java.lang.RuntimeException r9 = org.mozilla.javascript.ScriptRuntime.notFunctionError(r1, r2, r0)
            throw r9
        L_0x004e:
            java.lang.String r9 = "msg.incompat.call"
            org.mozilla.javascript.EcmaError r9 = org.mozilla.javascript.ScriptRuntime.typeError1(r9, r0)
            throw r9
        L_0x0055:
            r2 = r8
            r4 = r9
            r5 = r10
            r6 = r11
            r7 = r12
            java.lang.Object r9 = r2.applyOrCall(r3, r4, r5, r6, r7)
            return r9
        L_0x005f:
            java.lang.RuntimeException r9 = org.mozilla.javascript.ScriptRuntime.notFunctionError(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xmlimpl.XMLList.call(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.typeError1("msg.not.ctor", "XMLList");
    }
}
