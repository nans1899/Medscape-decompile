package org.dom4j.io;

import com.wbmd.wbmddrugscommons.constants.Constants;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import junit.framework.AssertionFailedError;
import junit.textui.TestRunner;
import org.dom4j.AbstractTestCase;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.dtd.AttributeDecl;
import org.dom4j.dtd.ElementDecl;
import org.dom4j.dtd.ExternalEntityDecl;
import org.dom4j.dtd.InternalEntityDecl;
import org.dom4j.tree.DefaultDocumentType;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DTDTest extends AbstractTestCase {
    private static final String DTD_FILE = "xml/dtd/sample.dtd";
    protected static final String DTD_PUBLICID = "-//dom4j//DTD sample";
    protected static final String DTD_SYSTEM_ID = "sample.dtd";
    private static final String XML_EXTERNAL_FILE = "xml/dtd/external.xml";
    private static final String XML_INTERNAL_FILE = "xml/dtd/internal.xml";
    private static final String XML_MIXED = "xml/dtd/mixed.xml";
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.io.DTDTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testInternalDTDSubset() throws Exception {
        DefaultDocumentType defaultDocumentType = new DefaultDocumentType();
        defaultDocumentType.setElementName("greeting");
        defaultDocumentType.setInternalDeclarations(getInternalDeclarations());
        try {
            assertSameDocumentType(defaultDocumentType, readDocument(XML_INTERNAL_FILE, true, false).getDocType());
        } catch (AssertionFailedError e) {
            throw e;
        } catch (Throwable th) {
            StringBuffer stringBuffer = new StringBuffer("Not expecting: ");
            stringBuffer.append(th);
            fail(stringBuffer.toString());
        }
    }

    public void testExternalDTDSubset() {
        DefaultDocumentType defaultDocumentType = new DefaultDocumentType("another-greeting", (String) null, DTD_SYSTEM_ID);
        defaultDocumentType.setExternalDeclarations(getExternalDeclarations());
        try {
            assertSameDocumentType(defaultDocumentType, readDocument(XML_EXTERNAL_FILE, false, true).getDocType());
        } catch (AssertionFailedError e) {
            throw e;
        } catch (Throwable th) {
            StringBuffer stringBuffer = new StringBuffer("Not expecting: ");
            stringBuffer.append(th);
            fail(stringBuffer.toString());
        }
    }

    public void testMixedDTDSubset() {
        DefaultDocumentType defaultDocumentType = new DefaultDocumentType("another-greeting", (String) null, DTD_SYSTEM_ID);
        defaultDocumentType.setInternalDeclarations(getInternalDeclarations());
        defaultDocumentType.setExternalDeclarations(getExternalDeclarations());
        try {
            assertSameDocumentType(defaultDocumentType, readDocument(XML_MIXED, true, true).getDocType());
        } catch (AssertionFailedError e) {
            throw e;
        } catch (Throwable th) {
            StringBuffer stringBuffer = new StringBuffer("Not expecting: ");
            stringBuffer.append(th);
            fail(stringBuffer.toString());
        }
    }

    /* access modifiers changed from: protected */
    public List getInternalDeclarations() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ElementDecl("greeting", "(#PCDATA)"));
        arrayList.add(new AttributeDecl("greeting", "foo", Constants.WBMDTugStringID, "#IMPLIED", (String) null));
        arrayList.add(new InternalEntityDecl("%boolean", "( true | false )"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List getExternalDeclarations() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ElementDecl("another-greeting", "(#PCDATA)"));
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void assertSameDocumentType(DocumentType documentType, DocumentType documentType2) {
        if (documentType != null) {
            if (documentType2 == null) {
                fail("Expecting DOCTYPE");
            }
            StringBuffer stringBuffer = new StringBuffer("Expected DocumentType:\n");
            stringBuffer.append(documentType.toString());
            log(stringBuffer.toString());
            StringBuffer stringBuffer2 = new StringBuffer("Actual DocumentType:\n");
            stringBuffer2.append(documentType2.toString());
            log(stringBuffer2.toString());
            assertSameDTDSubset("Internal", documentType.getInternalDeclarations(), documentType2.getInternalDeclarations());
            assertSameDTDSubset("External", documentType.getExternalDeclarations(), documentType2.getExternalDeclarations());
        } else if (documentType2 != null) {
            fail("Not expecting DOCTYPE.");
        }
    }

    /* access modifiers changed from: protected */
    public void assertSameDTDSubset(String str, List list, List list2) {
        if (list != null) {
            if (list2 == null) {
                StringBuffer stringBuffer = new StringBuffer("Expecting ");
                stringBuffer.append(str);
                stringBuffer.append(" DTD subset.");
                fail(stringBuffer.toString());
            }
            StringBuffer stringBuffer2 = new StringBuffer(String.valueOf(str));
            stringBuffer2.append(" DTD subset has correct #of declarations");
            stringBuffer2.append(": expected=[");
            stringBuffer2.append(list.toString());
            stringBuffer2.append("]");
            stringBuffer2.append(", actual=[");
            stringBuffer2.append(list2.toString());
            stringBuffer2.append("]");
            assertEquals(stringBuffer2.toString(), list.size(), list2.size());
            Iterator it = list2.iterator();
            for (Object next : list) {
                Object next2 = it.next();
                StringBuffer stringBuffer3 = new StringBuffer(String.valueOf(str));
                stringBuffer3.append(" DTD subset: Same type of declaration");
                assertEquals(stringBuffer3.toString(), next.getClass().getName(), next2.getClass().getName());
                if (next instanceof AttributeDecl) {
                    assertSameDecl((AttributeDecl) next, (AttributeDecl) next2);
                } else if (next instanceof ElementDecl) {
                    assertSameDecl((ElementDecl) next, (ElementDecl) next2);
                } else if (next instanceof InternalEntityDecl) {
                    assertSameDecl((InternalEntityDecl) next, (InternalEntityDecl) next2);
                } else if (next instanceof ExternalEntityDecl) {
                    assertSameDecl((ExternalEntityDecl) next, (ExternalEntityDecl) next2);
                } else {
                    StringBuffer stringBuffer4 = new StringBuffer("Unexpected declaration type: ");
                    stringBuffer4.append(next.getClass());
                    throw new AssertionError(stringBuffer4.toString());
                }
            }
        } else if (list2 != null) {
            StringBuffer stringBuffer5 = new StringBuffer("Not expecting ");
            stringBuffer5.append(str);
            stringBuffer5.append(" DTD subset.");
            fail(stringBuffer5.toString());
        }
    }

    public void assertSameDecl(AttributeDecl attributeDecl, AttributeDecl attributeDecl2) {
        assertEquals("attributeName is correct", attributeDecl.getAttributeName(), attributeDecl2.getAttributeName());
        assertEquals("elementName is correct", attributeDecl.getElementName(), attributeDecl2.getElementName());
        assertEquals("type is correct", attributeDecl.getType(), attributeDecl2.getType());
        assertEquals("value is not correct", attributeDecl.getValue(), attributeDecl2.getValue());
        assertEquals("valueDefault is correct", attributeDecl.getValueDefault(), attributeDecl2.getValueDefault());
        assertEquals("toString() is correct", attributeDecl.toString(), attributeDecl2.toString());
    }

    /* access modifiers changed from: protected */
    public void assertSameDecl(ElementDecl elementDecl, ElementDecl elementDecl2) {
        assertEquals("name is correct", elementDecl.getName(), elementDecl2.getName());
        assertEquals("model is not correct", elementDecl.getModel(), elementDecl2.getModel());
        assertEquals("toString() is correct", elementDecl.toString(), elementDecl2.toString());
    }

    /* access modifiers changed from: protected */
    public void assertSameDecl(InternalEntityDecl internalEntityDecl, InternalEntityDecl internalEntityDecl2) {
        assertEquals("name is correct", internalEntityDecl.getName(), internalEntityDecl2.getName());
        assertEquals("value is not correct", internalEntityDecl.getValue(), internalEntityDecl2.getValue());
        assertEquals("toString() is correct", internalEntityDecl.toString(), internalEntityDecl2.toString());
    }

    /* access modifiers changed from: protected */
    public void assertSameDecl(ExternalEntityDecl externalEntityDecl, ExternalEntityDecl externalEntityDecl2) {
        assertEquals("name is correct", externalEntityDecl.getName(), externalEntityDecl2.getName());
        assertEquals("publicID is correct", externalEntityDecl.getPublicID(), externalEntityDecl2.getPublicID());
        assertEquals("systemID is correct", externalEntityDecl.getSystemID(), externalEntityDecl2.getSystemID());
        assertEquals("toString() is correct", externalEntityDecl.toString(), externalEntityDecl2.toString());
    }

    /* access modifiers changed from: protected */
    public Document readDocument(String str, boolean z, boolean z2) throws Exception {
        SAXReader sAXReader = new SAXReader();
        sAXReader.setIncludeInternalDTDDeclarations(z);
        sAXReader.setIncludeExternalDTDDeclarations(z2);
        sAXReader.setEntityResolver(new MyEntityResolver(DTD_FILE, DTD_PUBLICID, DTD_SYSTEM_ID));
        return getDocument(str, sAXReader);
    }

    protected static class MyEntityResolver implements EntityResolver {
        private String pubId;
        private String resourceName;
        private String sysId;

        public MyEntityResolver(String str, String str2, String str3) {
            this.resourceName = str;
            this.sysId = str3;
        }

        public InputSource resolveEntity(String str, String str2) throws SAXException, IOException {
            String str3 = this.pubId;
            if (str3 != null && str3.equals(str)) {
                return new InputSource(getInputStream(this.resourceName));
            }
            if (this.sysId.equals(str2)) {
                return new InputSource(getInputStream(this.resourceName));
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public InputStream getInputStream(String str) throws IOException {
            return new FileInputStream(str);
        }
    }
}
