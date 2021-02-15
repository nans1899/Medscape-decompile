package org.dom4j;

import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import junit.textui.TestRunner;
import org.dom4j.io.SAXReader;

public class SerializeTest extends AbstractTestCase {
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.SerializeTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testSerializePeriodicTable() throws Exception {
        testSerialize("/xml/periodic_table.xml");
    }

    public void testSerializeMuchAdo() throws Exception {
        testSerialize("/xml/much_ado.xml");
    }

    public void testSerializeTestSchema() throws Exception {
        testSerialize("/xml/test/schema/personal.xsd");
    }

    public void testSerializeXPath() throws Exception {
        HashMap hashMap = new HashMap();
        hashMap.put("SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
        hashMap.put("m", "urn:xmethodsBabelFish");
        DocumentFactory documentFactory = new DocumentFactory();
        documentFactory.setXPathNamespaceURIs(hashMap);
        SAXReader sAXReader = new SAXReader();
        sAXReader.setDocumentFactory(documentFactory);
        Document document = getDocument("/xml/soap.xml", sAXReader);
        boolean z = true;
        assertTrue("Found valid element", document.selectSingleNode("/SOAP-ENV:Envelope/SOAP-ENV:Body/m:BabelFish") != null);
        XPath createXPath = documentFactory.createXPath("/SOAP-ENV:Envelope/SOAP-ENV:Body/m:BabelFish");
        assertTrue("Found valid element", createXPath.selectSingleNode(document) != null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(createXPath);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        objectInputStream.close();
        if (((XPath) objectInputStream.readObject()).selectSingleNode(document) == null) {
            z = false;
        }
        assertTrue("Found valid element", z);
    }

    /* access modifiers changed from: protected */
    public void testSerialize(String str) throws Exception {
        Document document = getDocument(str);
        String asXML = document.asXML();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(document);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Document document2 = (Document) objectInputStream.readObject();
        objectInputStream.close();
        assertEquals("Documents text are equal", asXML, document2.asXML());
        assertTrue("Read back document after serialization", document2 != null && (document2 instanceof Document));
        assertDocumentsEqual(document, document2);
        document2.getRootElement().addElement(AppSettingsData.STATUS_NEW);
    }
}
