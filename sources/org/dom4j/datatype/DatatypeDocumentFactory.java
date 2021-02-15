package org.dom4j.datatype;

import java.io.PrintStream;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class DatatypeDocumentFactory extends DocumentFactory {
    private static final boolean DO_INTERN_QNAME = false;
    private static final Namespace XSI_NAMESPACE;
    private static final QName XSI_NO_SCHEMA_LOCATION = QName.get("noNamespaceSchemaLocation", XSI_NAMESPACE);
    private static final QName XSI_SCHEMA_LOCATION;
    protected static transient DatatypeDocumentFactory singleton = new DatatypeDocumentFactory();
    private boolean autoLoadSchema = true;
    private SchemaParser schemaBuilder = new SchemaParser(this);
    private SAXReader xmlSchemaReader = new SAXReader();

    static {
        Namespace namespace = Namespace.get("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        XSI_NAMESPACE = namespace;
        XSI_SCHEMA_LOCATION = QName.get("schemaLocation", namespace);
    }

    public static DocumentFactory getInstance() {
        return singleton;
    }

    public void loadSchema(Document document) {
        this.schemaBuilder.build(document);
    }

    public void loadSchema(Document document, Namespace namespace) {
        this.schemaBuilder.build(document, namespace);
    }

    public DatatypeElementFactory getElementFactory(QName qName) {
        DocumentFactory documentFactory = qName.getDocumentFactory();
        if (documentFactory instanceof DatatypeElementFactory) {
            return (DatatypeElementFactory) documentFactory;
        }
        return null;
    }

    public Attribute createAttribute(Element element, QName qName, String str) {
        Document document = null;
        if (this.autoLoadSchema && qName.equals(XSI_NO_SCHEMA_LOCATION)) {
            if (element != null) {
                document = element.getDocument();
            }
            loadSchema(document, str);
        } else if (this.autoLoadSchema && qName.equals(XSI_SCHEMA_LOCATION)) {
            if (element != null) {
                document = element.getDocument();
            }
            loadSchema(document, str.substring(str.indexOf(32) + 1), element.getNamespaceForURI(str.substring(0, str.indexOf(32))));
        }
        return super.createAttribute(element, qName, str);
    }

    /* access modifiers changed from: protected */
    public void loadSchema(Document document, String str) {
        try {
            EntityResolver entityResolver = document.getEntityResolver();
            if (entityResolver != null) {
                InputSource resolveEntity = entityResolver.resolveEntity((String) null, str);
                if (entityResolver != null) {
                    loadSchema(this.xmlSchemaReader.read(resolveEntity));
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer("Could not resolve the URI: ");
                stringBuffer.append(str);
                throw new InvalidSchemaException(stringBuffer.toString());
            }
            StringBuffer stringBuffer2 = new StringBuffer("No EntityResolver available for resolving URI: ");
            stringBuffer2.append(str);
            throw new InvalidSchemaException(stringBuffer2.toString());
        } catch (Exception e) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer3 = new StringBuffer("Failed to load schema: ");
            stringBuffer3.append(str);
            printStream.println(stringBuffer3.toString());
            PrintStream printStream2 = System.out;
            StringBuffer stringBuffer4 = new StringBuffer("Caught: ");
            stringBuffer4.append(e);
            printStream2.println(stringBuffer4.toString());
            e.printStackTrace();
            StringBuffer stringBuffer5 = new StringBuffer("Failed to load schema: ");
            stringBuffer5.append(str);
            throw new InvalidSchemaException(stringBuffer5.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void loadSchema(Document document, String str, Namespace namespace) {
        try {
            EntityResolver entityResolver = document.getEntityResolver();
            if (entityResolver != null) {
                InputSource resolveEntity = entityResolver.resolveEntity((String) null, str);
                if (entityResolver != null) {
                    loadSchema(this.xmlSchemaReader.read(resolveEntity), namespace);
                    return;
                }
                StringBuffer stringBuffer = new StringBuffer("Could not resolve the URI: ");
                stringBuffer.append(str);
                throw new InvalidSchemaException(stringBuffer.toString());
            }
            StringBuffer stringBuffer2 = new StringBuffer("No EntityResolver available for resolving URI: ");
            stringBuffer2.append(str);
            throw new InvalidSchemaException(stringBuffer2.toString());
        } catch (Exception e) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer3 = new StringBuffer("Failed to load schema: ");
            stringBuffer3.append(str);
            printStream.println(stringBuffer3.toString());
            PrintStream printStream2 = System.out;
            StringBuffer stringBuffer4 = new StringBuffer("Caught: ");
            stringBuffer4.append(e);
            printStream2.println(stringBuffer4.toString());
            e.printStackTrace();
            StringBuffer stringBuffer5 = new StringBuffer("Failed to load schema: ");
            stringBuffer5.append(str);
            throw new InvalidSchemaException(stringBuffer5.toString());
        }
    }
}
