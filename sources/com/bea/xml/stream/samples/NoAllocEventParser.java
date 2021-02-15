package com.bea.xml.stream.samples;

import com.bea.xml.stream.StaticAllocator;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

public class NoAllocEventParser {
    private static String filename;

    private static void printUsage() {
        System.out.println("usage: java com.bea.xml.stream.samples.EventParse <xmlfile>");
    }

    public static void main(String[] strArr) throws Exception {
        try {
            filename = strArr[0];
        } catch (ArrayIndexOutOfBoundsException unused) {
            printUsage();
            System.exit(0);
        }
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.bea.xml.stream.EventFactory");
        XMLInputFactory newInstance = XMLInputFactory.newInstance();
        newInstance.setEventAllocator(new StaticAllocator());
        XMLEventReader createXMLEventReader = newInstance.createXMLEventReader((Reader) new FileReader(filename));
        while (createXMLEventReader.hasNext()) {
            XMLEvent nextEvent = createXMLEventReader.nextEvent();
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("ID:");
            stringBuffer.append(nextEvent.hashCode());
            stringBuffer.append("[");
            stringBuffer.append(nextEvent);
            stringBuffer.append("]");
            printStream.println(stringBuffer.toString());
        }
    }
}
