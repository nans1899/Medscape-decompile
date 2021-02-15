package org.dom4j.datatype;

import com.sun.msv.datatype.DatabindableDatatype;
import com.sun.msv.datatype.SerializationContext;
import com.sun.msv.datatype.xsd.XSDatatype;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.tree.AbstractAttribute;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;

public class DatatypeAttribute extends AbstractAttribute implements SerializationContext, ValidationContext {
    private Object data;
    private XSDatatype datatype;
    private Element parent;
    private QName qname;
    private String text;

    public String getBaseUri() {
        return null;
    }

    public boolean isNotation(String str) {
        return false;
    }

    public boolean isReadOnly() {
        return false;
    }

    public boolean isUnparsedEntity(String str) {
        return true;
    }

    public boolean supportsParent() {
        return true;
    }

    public DatatypeAttribute(QName qName, XSDatatype xSDatatype) {
        this.qname = qName;
        this.datatype = xSDatatype;
    }

    public DatatypeAttribute(QName qName, XSDatatype xSDatatype, String str) {
        this.qname = qName;
        this.datatype = xSDatatype;
        this.text = str;
        this.data = convertToValue(str);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(getClass().getName()));
        stringBuffer.append(hashCode());
        stringBuffer.append(" [Attribute: name ");
        stringBuffer.append(getQualifiedName());
        stringBuffer.append(" value \"");
        stringBuffer.append(getValue());
        stringBuffer.append("\" data: ");
        stringBuffer.append(getData());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public XSDatatype getXSDatatype() {
        return this.datatype;
    }

    public String getNamespacePrefix(String str) {
        Namespace namespaceForURI;
        Element parent2 = getParent();
        if (parent2 == null || (namespaceForURI = parent2.getNamespaceForURI(str)) == null) {
            return null;
        }
        return namespaceForURI.getPrefix();
    }

    public String resolveNamespacePrefix(String str) {
        Namespace namespaceForPrefix;
        if (str.equals(getNamespacePrefix())) {
            return getNamespaceURI();
        }
        Element parent2 = getParent();
        if (parent2 == null || (namespaceForPrefix = parent2.getNamespaceForPrefix(str)) == null) {
            return null;
        }
        return namespaceForPrefix.getURI();
    }

    public QName getQName() {
        return this.qname;
    }

    public String getValue() {
        return this.text;
    }

    public void setValue(String str) {
        validate(str);
        this.text = str;
        this.data = convertToValue(str);
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object obj) {
        String convertToLexicalValue = this.datatype.convertToLexicalValue(obj, this);
        validate(convertToLexicalValue);
        this.text = convertToLexicalValue;
        this.data = obj;
    }

    public Element getParent() {
        return this.parent;
    }

    public void setParent(Element element) {
        this.parent = element;
    }

    /* access modifiers changed from: protected */
    public void validate(String str) throws IllegalArgumentException {
        try {
            this.datatype.checkValid(str, this);
        } catch (DatatypeException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public Object convertToValue(String str) {
        XSDatatype xSDatatype = this.datatype;
        if (xSDatatype instanceof DatabindableDatatype) {
            return xSDatatype.createJavaObject(str, this);
        }
        return xSDatatype.createValue(str, this);
    }
}
