package org.dom4j;

import com.facebook.share.internal.ShareConstants;
import com.wbmd.wbmddrugscommons.constants.Constants;
import junit.textui.TestRunner;

public class ElementByIDTest extends AbstractTestCase {
    protected static final String INPUT_XML_FILE = "xml/test/elementByID.xml";
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.ElementByIDTest");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testElementByID() throws Exception {
        Document document = getDocument(INPUT_XML_FILE);
        StringBuffer stringBuffer = new StringBuffer("//*[@ID='");
        stringBuffer.append(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        stringBuffer.append("']");
        Element element = (Element) document.selectSingleNode(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer("Found element by ID: ");
        stringBuffer2.append(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        boolean z = true;
        assertTrue(stringBuffer2.toString(), element != null);
        assertEquals("ID is equal", ShareConstants.WEB_DIALOG_PARAM_MESSAGE, element.attributeValue(Constants.WBMDTugStringID));
        Element elementByID = document.elementByID(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        StringBuffer stringBuffer3 = new StringBuffer("Found element by ID: ");
        stringBuffer3.append(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        assertTrue(stringBuffer3.toString(), elementByID != null);
        assertEquals("ID is equal", ShareConstants.WEB_DIALOG_PARAM_MESSAGE, elementByID.attributeValue(Constants.WBMDTugStringID));
        StringBuffer stringBuffer4 = new StringBuffer("Found element: ");
        stringBuffer4.append(elementByID.getText());
        log(stringBuffer4.toString());
        if (document.elementByID("DoesNotExist") != null) {
            z = false;
        }
        assertTrue("Found no element", z);
    }
}
