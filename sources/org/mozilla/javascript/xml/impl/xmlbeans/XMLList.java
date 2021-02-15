package org.mozilla.javascript.xml.impl.xmlbeans;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.ads.AdError;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Vector;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.xml.XMLObject;
import org.mozilla.javascript.xml.impl.xmlbeans.XML;

class XMLList extends XMLObjectImpl implements Function {
    static final long serialVersionUID = -4543618751670781135L;
    private AnnotationList _annos;
    private XMLObjectImpl targetObject;
    private QName targetProperty;

    public String getClassName() {
        return "XMLList";
    }

    /* access modifiers changed from: package-private */
    public Object valueOf() {
        return this;
    }

    static class AnnotationList {
        private Vector v = new Vector();

        AnnotationList() {
        }

        /* access modifiers changed from: package-private */
        public void add(XML.XScriptAnnotation xScriptAnnotation) {
            this.v.add(xScriptAnnotation);
        }

        /* access modifiers changed from: package-private */
        public XML.XScriptAnnotation item(int i) {
            return (XML.XScriptAnnotation) this.v.get(i);
        }

        /* access modifiers changed from: package-private */
        public void remove(int i) {
            this.v.remove(i);
        }

        /* access modifiers changed from: package-private */
        public int length() {
            return this.v.size();
        }
    }

    XMLList(XMLLibImpl xMLLibImpl) {
        super(xMLLibImpl, xMLLibImpl.xmlListPrototype);
        this.targetObject = null;
        this.targetProperty = null;
        this._annos = new AnnotationList();
    }

    XMLList(XMLLibImpl xMLLibImpl, Object obj) {
        super(xMLLibImpl, xMLLibImpl.xmlListPrototype);
        this.targetObject = null;
        this.targetProperty = null;
        if (obj != null && !(obj instanceof Undefined)) {
            if (obj instanceof XML) {
                AnnotationList annotationList = new AnnotationList();
                this._annos = annotationList;
                annotationList.add(((XML) obj).getAnnotation());
                return;
            }
            int i = 0;
            if (obj instanceof XMLList) {
                XMLList xMLList = (XMLList) obj;
                this._annos = new AnnotationList();
                while (i < xMLList._annos.length()) {
                    this._annos.add(xMLList._annos.item(i));
                    i++;
                }
                return;
            }
            String trim = ScriptRuntime.toString(obj).trim();
            if (!trim.startsWith("<>")) {
                trim = "<>" + trim + "</>";
            }
            String str = "<fragment>" + trim.substring(2);
            if (str.endsWith("</>")) {
                XMLList children = XML.createFromJS(xMLLibImpl, str.substring(0, str.length() - 3) + "</fragment>").children();
                this._annos = new AnnotationList();
                while (i < children._annos.length()) {
                    this._annos.add(((XML) children.item(i).copy()).getAnnotation());
                    i++;
                }
                return;
            }
            throw ScriptRuntime.typeError("XML with anonymous tag missing end anonymous tag");
        }
    }

    /* access modifiers changed from: package-private */
    public void setTargets(XMLObjectImpl xMLObjectImpl, QName qName) {
        this.targetObject = xMLObjectImpl;
        this.targetProperty = qName;
    }

    /* access modifiers changed from: package-private */
    public XML getXmlFromAnnotation(int i) {
        if (i < 0 || i >= length()) {
            return null;
        }
        return XML.getFromAnnotation(this.lib, this._annos.item(i));
    }

    private void internalRemoveFromList(int i) {
        this._annos.remove(i);
    }

    /* access modifiers changed from: package-private */
    public void replace(int i, XML xml) {
        if (i < length()) {
            AnnotationList annotationList = new AnnotationList();
            for (int i2 = 0; i2 < i; i2++) {
                annotationList.add(this._annos.item(i2));
            }
            annotationList.add(xml.getAnnotation());
            while (true) {
                i++;
                if (i < length()) {
                    annotationList.add(this._annos.item(i));
                } else {
                    this._annos = annotationList;
                    return;
                }
            }
        }
    }

    private void insert(int i, XML xml) {
        if (i < length()) {
            AnnotationList annotationList = new AnnotationList();
            for (int i2 = 0; i2 < i; i2++) {
                annotationList.add(this._annos.item(i2));
            }
            annotationList.add(xml.getAnnotation());
            while (i < length()) {
                annotationList.add(this._annos.item(i));
                i++;
            }
            this._annos = annotationList;
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
        QName qName;
        if (obj == null) {
            obj = "null";
        } else if (obj instanceof Undefined) {
            obj = AdError.UNDEFINED_DOMAIN;
        }
        if (length() > 1) {
            throw ScriptRuntime.typeError("Assignment to lists with more that one item is not supported");
        } else if (length() == 0) {
            if (this.targetObject == null || (qName = this.targetProperty) == null || qName.getLocalPart().equals("*")) {
                throw ScriptRuntime.typeError("Assignment to empty XMLList without targets not supported");
            }
            addToList(XML.createTextElement(this.lib, this.targetProperty, ""));
            if (xMLName.isAttributeName()) {
                setAttribute(xMLName, obj);
            } else {
                item(0).putXMLProperty(xMLName, obj);
                replace(0, item(0));
            }
            this.targetObject.putXMLProperty(XMLName.formProperty(this.targetProperty.getNamespaceURI(), this.targetProperty.getLocalPart()), this);
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
            obj2 = XML.createFromJS(this.lib, obj.toString());
        } else {
            obj2 = XML.createTextElement(this.lib, this.targetProperty, obj.toString());
        }
        if (i < length()) {
            obj3 = item(i).parent();
        } else {
            obj3 = parent();
        }
        if (obj3 instanceof XML) {
            XML xml = (XML) obj3;
            if (i < length()) {
                XML xmlFromAnnotation = getXmlFromAnnotation(i);
                if (obj2 instanceof XML) {
                    xmlFromAnnotation.replaceAll((XML) obj2);
                    replace(i, xmlFromAnnotation);
                } else if (obj2 instanceof XMLList) {
                    XMLList xMLList = (XMLList) obj2;
                    if (xMLList.length() > 0) {
                        int childIndex = xmlFromAnnotation.childIndex();
                        xmlFromAnnotation.replaceAll(xMLList.item(0));
                        replace(i, xMLList.item(0));
                        for (int i2 = 1; i2 < xMLList.length(); i2++) {
                            xml.insertChildAfter(xml.getXmlChild((long) childIndex), xMLList.item(i2));
                            childIndex++;
                            insert(i + i2, xMLList.item(i2));
                        }
                    }
                }
            } else {
                xml.appendChild(obj2);
                addToList(xml.getXmlChild((long) i));
            }
        } else if (i < length()) {
            XML fromAnnotation = XML.getFromAnnotation(this.lib, this._annos.item(i));
            if (obj2 instanceof XML) {
                fromAnnotation.replaceAll((XML) obj2);
                replace(i, fromAnnotation);
            } else if (obj2 instanceof XMLList) {
                XMLList xMLList2 = (XMLList) obj2;
                if (xMLList2.length() > 0) {
                    fromAnnotation.replaceAll(xMLList2.item(0));
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

    /* access modifiers changed from: package-private */
    public void deleteXMLProperty(XMLName xMLName) {
        for (int i = 0; i < length(); i++) {
            XML xmlFromAnnotation = getXmlFromAnnotation(i);
            if (xmlFromAnnotation.tokenType() == XmlCursor.TokenType.START) {
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
        if (this.prototypeFlag) {
            return new Object[0];
        }
        int length = length();
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            objArr[i] = new Integer(i);
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
        return this._annos != null ? getXmlFromAnnotation(i) : XML.createEmptyXML(this.lib);
    }

    private void setAttribute(XMLName xMLName, Object obj) {
        for (int i = 0; i < length(); i++) {
            getXmlFromAnnotation(i).setAttribute(xMLName, obj);
        }
    }

    /* access modifiers changed from: package-private */
    public void addToList(Object obj) {
        if (!(obj instanceof Undefined)) {
            if (obj instanceof XMLList) {
                XMLList xMLList = (XMLList) obj;
                for (int i = 0; i < xMLList.length(); i++) {
                    this._annos.add(xMLList.item(i).getAnnotation());
                }
            } else if (obj instanceof XML) {
                this._annos.add(((XML) obj).getAnnotation());
            } else if (obj instanceof XML.XScriptAnnotation) {
                this._annos.add((XML.XScriptAnnotation) obj);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public XML addNamespace(Namespace namespace) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).addNamespace(namespace);
        }
        throw ScriptRuntime.typeError("The addNamespace method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XML appendChild(Object obj) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).appendChild(obj);
        }
        throw ScriptRuntime.typeError("The appendChild method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XMLList attribute(XMLName xMLName) {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).attribute(xMLName));
        }
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList attributes() {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).attributes());
        }
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList child(long j) {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).child(j));
        }
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList child(XMLName xMLName) {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).child(xMLName));
        }
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public int childIndex() {
        if (length() == 1) {
            return getXmlFromAnnotation(0).childIndex();
        }
        throw ScriptRuntime.typeError("The childIndex method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XMLList children() {
        Vector vector = new Vector();
        for (int i = 0; i < length(); i++) {
            XML xmlFromAnnotation = getXmlFromAnnotation(i);
            if (xmlFromAnnotation != null) {
                XMLList children = xmlFromAnnotation.children();
                if (children instanceof XMLList) {
                    XMLList xMLList = children;
                    int length = xMLList.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        vector.addElement(xMLList.item(i2));
                    }
                }
            }
        }
        XMLList xMLList2 = new XMLList(this.lib);
        int size = vector.size();
        for (int i3 = 0; i3 < size; i3++) {
            xMLList2.addToList(vector.get(i3));
        }
        return xMLList2;
    }

    /* access modifiers changed from: package-private */
    public XMLList comments() {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).comments());
        }
        return xMLList;
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
    public Object copy() {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).copy());
        }
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public XMLList descendants(XMLName xMLName) {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).descendants(xMLName));
        }
        return xMLList;
    }

    /* access modifiers changed from: package-private */
    public Object[] inScopeNamespaces() {
        if (length() == 1) {
            return getXmlFromAnnotation(0).inScopeNamespaces();
        }
        throw ScriptRuntime.typeError("The inScopeNamespaces method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XML insertChildAfter(Object obj, Object obj2) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).insertChildAfter(obj, obj2);
        }
        throw ScriptRuntime.typeError("The insertChildAfter method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XML insertChildBefore(Object obj, Object obj2) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).insertChildAfter(obj, obj2);
        }
        throw ScriptRuntime.typeError("The insertChildBefore method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public boolean hasOwnProperty(XMLName xMLName) {
        if (this.prototypeFlag) {
            if (findPrototypeId(xMLName.localName()) != 0) {
                return true;
            }
        } else if (getPropertyList(xMLName).length() > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasComplexContent() {
        int length = length();
        if (length != 0) {
            if (length == 1) {
                return getXmlFromAnnotation(0).hasComplexContent();
            }
            for (int i = 0; i < length; i++) {
                if (getXmlFromAnnotation(i).tokenType() == XmlCursor.TokenType.START) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasSimpleContent() {
        int length = length();
        if (length != 0) {
            if (length == 1) {
                return getXmlFromAnnotation(0).hasSimpleContent();
            }
            for (int i = 0; i < length; i++) {
                if (getXmlFromAnnotation(i).tokenType() == XmlCursor.TokenType.START) {
                    return false;
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public int length() {
        AnnotationList annotationList = this._annos;
        if (annotationList != null) {
            return annotationList.length();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public String localName() {
        if (length() == 1) {
            return name().localName();
        }
        throw ScriptRuntime.typeError("The localName method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public QName name() {
        if (length() == 1) {
            return getXmlFromAnnotation(0).name();
        }
        throw ScriptRuntime.typeError("The name method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public Object namespace(String str) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).namespace(str);
        }
        throw ScriptRuntime.typeError("The namespace method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public Object[] namespaceDeclarations() {
        if (length() == 1) {
            return getXmlFromAnnotation(0).namespaceDeclarations();
        }
        throw ScriptRuntime.typeError("The namespaceDeclarations method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public Object nodeKind() {
        if (length() == 1) {
            return getXmlFromAnnotation(0).nodeKind();
        }
        throw ScriptRuntime.typeError("The nodeKind method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public void normalize() {
        for (int i = 0; i < length(); i++) {
            getXmlFromAnnotation(i).normalize();
        }
    }

    /* access modifiers changed from: package-private */
    public Object parent() {
        XMLObjectImpl xMLObjectImpl;
        Object obj = Undefined.instance;
        if (length() == 0 && (xMLObjectImpl = this.targetObject) != null && (xMLObjectImpl instanceof XML)) {
            return xMLObjectImpl;
        }
        for (int i = 0; i < length(); i++) {
            Object parent = getXmlFromAnnotation(i).parent();
            if (i == 0) {
                obj = parent;
            } else if (obj != parent) {
                return Undefined.instance;
            }
        }
        return obj;
    }

    /* access modifiers changed from: package-private */
    public XML prependChild(Object obj) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).prependChild(obj);
        }
        throw ScriptRuntime.typeError("The prependChild method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public Object processingInstructions(XMLName xMLName) {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).processingInstructions(xMLName));
        }
        return xMLList;
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
    public XML removeNamespace(Namespace namespace) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).removeNamespace(namespace);
        }
        throw ScriptRuntime.typeError("The removeNamespace method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XML replace(long j, Object obj) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).replace(j, obj);
        }
        throw ScriptRuntime.typeError("The replace method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XML replace(XMLName xMLName, Object obj) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).replace(xMLName, obj);
        }
        throw ScriptRuntime.typeError("The replace method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XML setChildren(Object obj) {
        if (length() == 1) {
            return getXmlFromAnnotation(0).setChildren(obj);
        }
        throw ScriptRuntime.typeError("The setChildren method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public void setLocalName(String str) {
        if (length() == 1) {
            getXmlFromAnnotation(0).setLocalName(str);
            return;
        }
        throw ScriptRuntime.typeError("The setLocalName method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public void setName(QName qName) {
        if (length() == 1) {
            getXmlFromAnnotation(0).setName(qName);
            return;
        }
        throw ScriptRuntime.typeError("The setName method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public void setNamespace(Namespace namespace) {
        if (length() == 1) {
            getXmlFromAnnotation(0).setNamespace(namespace);
            return;
        }
        throw ScriptRuntime.typeError("The setNamespace method works only on lists containing one item");
    }

    /* access modifiers changed from: package-private */
    public XMLList text() {
        XMLList xMLList = new XMLList(this.lib);
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).text());
        }
        return xMLList;
    }

    public String toString() {
        if (!hasSimpleContent()) {
            return toXMLString(0);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length(); i++) {
            stringBuffer.append(getXmlFromAnnotation(i).toString());
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String toSource(int i) {
        return "<>" + toXMLString(0) + "</>";
    }

    /* access modifiers changed from: package-private */
    public String toXMLString(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < length(); i2++) {
            if (i2 > 0) {
                stringBuffer.append(10);
            }
            stringBuffer.append(getXmlFromAnnotation(i2).toXMLString(i));
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
        XMLList xMLList = new XMLList(this.lib);
        xMLList.setTargets(this, (xMLName.isDescendants() || xMLName.isAttributeName()) ? null : new QName(xMLName.uri(), xMLName.localName()));
        for (int i = 0; i < length(); i++) {
            xMLList.addToList(getXmlFromAnnotation(i).getPropertyList(xMLName));
        }
        return xMLList;
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
            return new XMLList(this.lib);
        }
        Object obj = objArr[0];
        if (z || !(obj instanceof XMLList)) {
            return new XMLList(this.lib, obj);
        }
        return obj;
    }

    /* access modifiers changed from: package-private */
    public XmlObject getXmlObject() {
        if (length() == 1) {
            return getXmlFromAnnotation(0).getXmlObject();
        }
        throw ScriptRuntime.typeError("getXmlObject method works only on lists containing one item");
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
            javax.xml.namespace.QName r0 = r8.targetProperty
            if (r0 == 0) goto L_0x005f
            java.lang.String r0 = r0.getLocalPart()
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
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.xml.impl.xmlbeans.XMLList.call(org.mozilla.javascript.Context, org.mozilla.javascript.Scriptable, org.mozilla.javascript.Scriptable, java.lang.Object[]):java.lang.Object");
    }

    public Scriptable construct(Context context, Scriptable scriptable, Object[] objArr) {
        throw ScriptRuntime.typeError1("msg.not.ctor", "XMLList");
    }
}
