package com.bea.xml.stream.samples;

import com.bea.xml.stream.XMLEventAllocatorBase;
import com.bea.xml.stream.util.ElementTypeNames;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Iterator;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import org.apache.commons.io.IOUtils;

public class Parse {
    private static String filename;

    private static void printUsage() {
        System.out.println("usage: java com.bea.xml.stream.samples.Parse <xmlfile>");
    }

    public static void main(String[] strArr) throws Exception {
        try {
            filename = strArr[0];
        } catch (ArrayIndexOutOfBoundsException unused) {
            printUsage();
            System.exit(0);
        }
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        XMLInputFactory newInstance = XMLInputFactory.newInstance();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("FACTORY: ");
        stringBuffer.append(newInstance);
        printStream.println(stringBuffer.toString());
        XMLStreamReader createXMLStreamReader = newInstance.createXMLStreamReader((Reader) new FileReader(filename));
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("READER:  ");
        stringBuffer2.append(createXMLStreamReader);
        stringBuffer2.append(IOUtils.LINE_SEPARATOR_UNIX);
        printStream2.println(stringBuffer2.toString());
        int eventType = createXMLStreamReader.getEventType();
        System.out.println("PARSER STATE BEFORE FIRST next(): ");
        printEventType(eventType);
        printName(createXMLStreamReader);
        printValue(createXMLStreamReader);
        System.out.println("-----------------------------");
        while (createXMLStreamReader.hasNext()) {
            printEventType(createXMLStreamReader.next());
            printName(createXMLStreamReader);
            printValue(createXMLStreamReader);
            if (createXMLStreamReader.isStartElement()) {
                printAttributes(createXMLStreamReader);
                printNamespaces(createXMLStreamReader);
            }
            System.out.println("-----------------------------");
        }
    }

    public static final String getEventTypeString(int i) {
        return ElementTypeNames.getEventTypeString(i);
    }

    private static void printEventType(int i) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("EVENT TYPE(");
        stringBuffer.append(i);
        stringBuffer.append("):");
        printStream.print(stringBuffer.toString());
        System.out.println(getEventTypeString(i));
    }

    private static void printName(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.hasName()) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("HAS NAME: ");
            stringBuffer.append(xMLStreamReader.getLocalName());
            printStream.println(stringBuffer.toString());
            return;
        }
        System.out.println("HAS NO NAME");
    }

    private static void printValue(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.hasText()) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("HAS VALUE: ");
            stringBuffer.append(xMLStreamReader.getText());
            printStream.println(stringBuffer.toString());
            return;
        }
        System.out.println("HAS NO VALUE");
    }

    private static void printAttributes(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.getAttributeCount() > 0) {
            System.out.println("\nHAS ATTRIBUTES: ");
            Iterator attributes = XMLEventAllocatorBase.getAttributes(xMLStreamReader);
            while (attributes.hasNext()) {
                System.out.println("");
                printAttribute((Attribute) attributes.next());
            }
            return;
        }
        System.out.println("HAS NO ATTRIBUTES");
    }

    private static void printAttribute(Attribute attribute) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PREFIX: ");
        stringBuffer.append(attribute.getName().getPrefix());
        printStream.println(stringBuffer.toString());
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("NAMESP: ");
        stringBuffer2.append(attribute.getName().getNamespaceURI());
        printStream2.println(stringBuffer2.toString());
        PrintStream printStream3 = System.out;
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("NAME:   ");
        stringBuffer3.append(attribute.getName().getLocalPart());
        printStream3.println(stringBuffer3.toString());
        PrintStream printStream4 = System.out;
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("VALUE:  ");
        stringBuffer4.append(attribute.getValue());
        printStream4.println(stringBuffer4.toString());
        PrintStream printStream5 = System.out;
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append("TYPE:   ");
        stringBuffer5.append(attribute.getDTDType());
        printStream5.println(stringBuffer5.toString());
    }

    private static void printNamespaces(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.getNamespaceCount() > 0) {
            System.out.println("\nHAS NAMESPACES: ");
            Iterator namespaces = XMLEventAllocatorBase.getNamespaces(xMLStreamReader);
            while (namespaces.hasNext()) {
                System.out.println("");
                printNamespace((Namespace) namespaces.next());
            }
            return;
        }
        System.out.println("HAS NO NAMESPACES");
    }

    private static void printNamespace(Namespace namespace) {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PREFIX: ");
        stringBuffer.append(namespace.getName().getPrefix());
        printStream.println(stringBuffer.toString());
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("NAMESP: ");
        stringBuffer2.append(namespace.getName().getNamespaceURI());
        printStream2.println(stringBuffer2.toString());
        PrintStream printStream3 = System.out;
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("NAME:   ");
        stringBuffer3.append(namespace.getName().getLocalPart());
        printStream3.println(stringBuffer3.toString());
        PrintStream printStream4 = System.out;
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("VALUE:  ");
        stringBuffer4.append(namespace.getValue());
        printStream4.println(stringBuffer4.toString());
        PrintStream printStream5 = System.out;
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append("TYPE:   ");
        stringBuffer5.append(namespace.getDTDType());
        printStream5.println(stringBuffer5.toString());
    }
}
