package org.dom4j.datatype;

import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import junit.textui.TestRunner;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Datatype2Test extends AbstractDataTypeTestCase {
    public static final int DATE = 31;
    public static final int MONTH = 10;
    public static final int YEAR = 2001;
    static /* synthetic */ Class class$0;

    public static void main(String[] strArr) {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.dom4j.datatype.Datatype2Test");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        TestRunner.run(cls);
    }

    public void testSchema() throws Exception {
        validateDocumentWithSchema(getSchema());
    }

    public void testSchemaWithNamedComplexType() throws Exception {
        validateDocumentWithSchema(getSchemaWithNamedComplexType());
    }

    public void testSchemaWithReference() throws Exception {
        validateDocumentWithSchema(getSchemaWithReference());
    }

    public void testSchemaWithNamedSimpleType() throws Exception {
        validateDocumentWithSchema(getSchemaWithNamedSimpleType());
    }

    private void validateDocumentWithSchema(Document document) throws Exception {
        Element rootElement = getSource(document).getRootElement();
        validateLongAttribute(rootElement);
        validateFloatElement(rootElement);
        validateDateElement(rootElement);
    }

    private void validateLongAttribute(Element element) throws Exception {
        Object data = element.attribute("longAttribute").getData();
        validateData("testLongAttribute", data, new Long(123));
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("retrieved attribute ");
        stringBuffer.append(data);
        printStream.println(stringBuffer.toString());
    }

    private void validateFloatElement(Element element) throws Exception {
        Object data = element.element("floatElement").getData();
        validateData("testFloatElement", data, new Float(1.23d));
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("retrieved element:");
        stringBuffer.append(data);
        printStream.println(stringBuffer.toString());
    }

    private void validateDateElement(Element element) throws Exception {
        Object data = element.element("dateElement").getData();
        Calendar date = getDate();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer("retrieved element:");
        stringBuffer.append(data);
        printStream.println(stringBuffer.toString());
        assertTrue(data instanceof Calendar);
        Calendar calendar = (Calendar) data;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyyZ");
        simpleDateFormat.setTimeZone(calendar.getTimeZone());
        String format = simpleDateFormat.format(calendar.getTime());
        simpleDateFormat.setTimeZone(date.getTimeZone());
        assertEquals("testDateElement", simpleDateFormat.format(date.getTime()), format);
    }

    private void validateData(String str, Object obj, Object obj2) throws Exception {
        Class<?> cls = obj.getClass();
        Class<?> cls2 = obj2.getClass();
        if (!cls2.equals(cls)) {
            StringBuffer stringBuffer = new StringBuffer("class mismatch in ");
            stringBuffer.append(str);
            stringBuffer.append(":expected ");
            stringBuffer.append(cls2);
            stringBuffer.append(", retrieved ");
            stringBuffer.append(cls);
            throw new Exception(stringBuffer.toString());
        } else if (!obj2.equals(obj)) {
            StringBuffer stringBuffer2 = new StringBuffer("value mismatch in ");
            stringBuffer2.append(str);
            stringBuffer2.append(":expected ");
            stringBuffer2.append(obj2);
            stringBuffer2.append(", retrieved ");
            stringBuffer2.append(obj);
            throw new Exception(stringBuffer2.toString());
        }
    }

    private Document getSource(Document document) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version='1.0' ?>");
        stringBuffer.append("<test xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'");
        stringBuffer.append("        xsi:noNamespaceSchemaLocation='long.xsd'");
        stringBuffer.append("        longAttribute='123' >");
        stringBuffer.append("    <floatElement>1.23</floatElement>");
        StringBuffer stringBuffer2 = new StringBuffer("    <dateElement>");
        stringBuffer2.append(getDateString());
        stringBuffer2.append("</dateElement>");
        stringBuffer.append(stringBuffer2.toString());
        stringBuffer.append("</test>");
        StringReader stringReader = new StringReader(stringBuffer.toString());
        DatatypeDocumentFactory datatypeDocumentFactory = new DatatypeDocumentFactory();
        datatypeDocumentFactory.loadSchema(document);
        return new SAXReader((DocumentFactory) datatypeDocumentFactory).read((Reader) stringReader);
    }

    private Document getSchema() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        stringBuffer.append("<xsd:schema xmlns:xsd='http://www.w3.org/2001/XMLSchema'>");
        stringBuffer.append(" <xsd:element name='test'>");
        stringBuffer.append("  <xsd:complexType>");
        stringBuffer.append("   <xsd:sequence>");
        stringBuffer.append("    <xsd:element name='floatElement' type='xsd:float' />");
        stringBuffer.append("    <xsd:element name='dateElement' type='xsd:date' />");
        stringBuffer.append("   </xsd:sequence>");
        stringBuffer.append("   <xsd:attribute name='longAttribute' type='xsd:long' />");
        stringBuffer.append("  </xsd:complexType>");
        stringBuffer.append(" </xsd:element>");
        stringBuffer.append("</xsd:schema>");
        return new SAXReader().read((Reader) new StringReader(stringBuffer.toString()));
    }

    private Document getSchemaWithNamedComplexType() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        stringBuffer.append("<xsd:schema xmlns:xsd='http://www.w3.org/2001/XMLSchema'>");
        stringBuffer.append(" <xsd:element name='test' type='TimePeriodType' />");
        stringBuffer.append(" <xsd:complexType name='TimePeriodType'>");
        stringBuffer.append("  <xsd:sequence>");
        stringBuffer.append("   <xsd:element name='floatElement' type='xsd:float' />");
        stringBuffer.append("   <xsd:element name='dateElement' type='xsd:date' />");
        stringBuffer.append("  </xsd:sequence>");
        stringBuffer.append("  <xsd:attribute name='longAttribute' type='xsd:long' />");
        stringBuffer.append(" </xsd:complexType>");
        stringBuffer.append("</xsd:schema>");
        return new SAXReader().read((Reader) new StringReader(stringBuffer.toString()));
    }

    private Document getSchemaWithReference() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        stringBuffer.append("<xsd:schema xmlns:xsd='http://www.w3.org/2001/XMLSchema'>");
        stringBuffer.append(" <xsd:element name='test' type='TimePeriodType' />");
        stringBuffer.append(" <xsd:complexType name='TimePeriodType'>");
        stringBuffer.append("  <xsd:sequence>");
        stringBuffer.append("   <xsd:element name='floatElement' type='xsd:float' />");
        stringBuffer.append("   <xsd:element ref='dateElement' />");
        stringBuffer.append("  </xsd:sequence>");
        stringBuffer.append("  <xsd:attribute name='longAttribute' type='xsd:long' />");
        stringBuffer.append(" </xsd:complexType>");
        stringBuffer.append(" <xsd:element name='dateElement' type='xsd:date' />");
        stringBuffer.append("</xsd:schema>");
        return new SAXReader().read((Reader) new StringReader(stringBuffer.toString()));
    }

    private Document getSchemaWithNamedSimpleType() throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<?xml version='1.0' encoding='UTF-8'?>");
        stringBuffer.append("<xsd:schema xmlns:xsd='http://www.w3.org/2001/XMLSchema'>");
        stringBuffer.append(" <xsd:element name='test'>");
        stringBuffer.append("  <xsd:complexType>");
        stringBuffer.append("   <xsd:sequence>");
        stringBuffer.append("    <xsd:element name='floatElement' type='xsd:float' />");
        stringBuffer.append("    <xsd:element name='dateElement' type='dateType' />");
        stringBuffer.append("   </xsd:sequence>");
        stringBuffer.append("   <xsd:attribute name='longAttribute' type='xsd:long' />");
        stringBuffer.append("  </xsd:complexType>");
        stringBuffer.append(" </xsd:element>");
        stringBuffer.append(" <xsd:simpleType name='dateType'>");
        stringBuffer.append("  <xsd:restriction base='xsd:date'>");
        stringBuffer.append("  </xsd:restriction>");
        stringBuffer.append(" </xsd:simpleType>");
        stringBuffer.append("</xsd:schema>");
        return new SAXReader().read((Reader) new StringReader(stringBuffer.toString()));
    }

    private static String getDateString() {
        String num = Integer.toString(2001);
        String num2 = Integer.toString(10);
        String num3 = Integer.toString(31);
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(num));
        stringBuffer.append("-");
        stringBuffer.append(num2);
        stringBuffer.append("-");
        stringBuffer.append(num3);
        stringBuffer.append("Z");
        return stringBuffer.toString();
    }

    private static Calendar getDate() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.clear();
        gregorianCalendar.set(1, 2001);
        gregorianCalendar.set(2, 9);
        gregorianCalendar.set(5, 31);
        gregorianCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.setTimeZone(new SimpleTimeZone(0, "XSD 'Z' timezone"));
        return gregorianCalendar;
    }
}
