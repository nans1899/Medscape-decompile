package org.dom4j.datatype;

import com.sun.msv.datatype.xsd.XSDatatype;
import java.util.HashMap;
import java.util.Map;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;

class NamedTypeResolver {
    protected Map complexTypeMap = new HashMap();
    protected DocumentFactory documentFactory;
    protected Map elementFactoryMap = new HashMap();
    protected Map simpleTypeMap = new HashMap();
    protected Map typedElementMap = new HashMap();

    NamedTypeResolver(DocumentFactory documentFactory2) {
        this.documentFactory = documentFactory2;
    }

    /* access modifiers changed from: package-private */
    public void registerComplexType(QName qName, DocumentFactory documentFactory2) {
        this.complexTypeMap.put(qName, documentFactory2);
    }

    /* access modifiers changed from: package-private */
    public void registerSimpleType(QName qName, XSDatatype xSDatatype) {
        this.simpleTypeMap.put(qName, xSDatatype);
    }

    /* access modifiers changed from: package-private */
    public void registerTypedElement(Element element, QName qName, DocumentFactory documentFactory2) {
        this.typedElementMap.put(element, qName);
        this.elementFactoryMap.put(element, documentFactory2);
    }

    /* access modifiers changed from: package-private */
    public void resolveElementTypes() {
        for (Element element : this.typedElementMap.keySet()) {
            QName qNameOfSchemaElement = getQNameOfSchemaElement(element);
            QName qName = (QName) this.typedElementMap.get(element);
            if (this.complexTypeMap.containsKey(qName)) {
                qNameOfSchemaElement.setDocumentFactory((DocumentFactory) this.complexTypeMap.get(qName));
            } else if (this.simpleTypeMap.containsKey(qName)) {
                XSDatatype xSDatatype = (XSDatatype) this.simpleTypeMap.get(qName);
                DocumentFactory documentFactory2 = (DocumentFactory) this.elementFactoryMap.get(element);
                if (documentFactory2 instanceof DatatypeElementFactory) {
                    ((DatatypeElementFactory) documentFactory2).setChildElementXSDatatype(qNameOfSchemaElement, xSDatatype);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resolveNamedTypes() {
        resolveElementTypes();
    }

    private QName getQNameOfSchemaElement(Element element) {
        return getQName(element.attributeValue("name"));
    }

    private QName getQName(String str) {
        return this.documentFactory.createQName(str);
    }
}
