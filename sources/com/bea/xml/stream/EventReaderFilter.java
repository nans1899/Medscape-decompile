package com.bea.xml.stream;

import com.bea.xml.stream.filters.TypeFilter;
import java.io.FileReader;
import java.io.Reader;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class EventReaderFilter implements XMLEventReader {
    private EventFilter filter;
    private XMLEventReader parent;

    public EventReaderFilter(XMLEventReader xMLEventReader) throws XMLStreamException {
        this.parent = xMLEventReader;
    }

    public EventReaderFilter(XMLEventReader xMLEventReader, EventFilter eventFilter) throws XMLStreamException {
        this.parent = xMLEventReader;
        this.filter = eventFilter;
    }

    public void setFilter(EventFilter eventFilter) {
        this.filter = eventFilter;
    }

    public Object next() {
        try {
            return nextEvent();
        } catch (XMLStreamException unused) {
            return null;
        }
    }

    public XMLEvent nextEvent() throws XMLStreamException {
        if (hasNext()) {
            return this.parent.nextEvent();
        }
        return null;
    }

    public String getElementText() throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        if (nextEvent().isStartElement()) {
            while (hasNext()) {
                XMLEvent peek = peek();
                if (!peek.isStartElement()) {
                    if (peek.isCharacters()) {
                        stringBuffer.append(((Characters) peek).getData());
                    }
                    if (peek.isEndElement()) {
                        return stringBuffer.toString();
                    }
                    nextEvent();
                } else {
                    throw new XMLStreamException("Unexpected Element start");
                }
            }
            throw new XMLStreamException("Unexpected end of Document");
        }
        throw new XMLStreamException("Precondition for readText is nextEvent().getTypeEventType() == START_ELEMENT");
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0006  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.xml.stream.events.XMLEvent nextTag() throws javax.xml.stream.XMLStreamException {
        /*
            r2 = this;
        L_0x0000:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x002f
            javax.xml.stream.events.XMLEvent r0 = r2.nextEvent()
            boolean r1 = r0.isCharacters()
            if (r1 == 0) goto L_0x0022
            r1 = r0
            javax.xml.stream.events.Characters r1 = (javax.xml.stream.events.Characters) r1
            boolean r1 = r1.isWhiteSpace()
            if (r1 == 0) goto L_0x001a
            goto L_0x0022
        L_0x001a:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            java.lang.String r1 = "Unexpected text"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0022:
            boolean r1 = r0.isStartElement()
            if (r1 != 0) goto L_0x002e
            boolean r1 = r0.isEndElement()
            if (r1 == 0) goto L_0x0000
        L_0x002e:
            return r0
        L_0x002f:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            java.lang.String r1 = "Unexpected end of Document"
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.EventReaderFilter.nextTag():javax.xml.stream.events.XMLEvent");
    }

    public boolean hasNext() {
        while (this.parent.hasNext()) {
            try {
                if (this.filter.accept(this.parent.peek())) {
                    return true;
                }
                this.parent.nextEvent();
            } catch (XMLStreamException unused) {
            }
        }
        return false;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public XMLEvent peek() throws XMLStreamException {
        if (hasNext()) {
            return this.parent.peek();
        }
        return null;
    }

    public void close() throws XMLStreamException {
        this.parent.close();
    }

    public Object getProperty(String str) {
        return this.parent.getProperty(str);
    }

    public static void main(String[] strArr) throws Exception {
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.bea.xml.stream.EventFactory");
        XMLInputFactory newInstance = XMLInputFactory.newInstance();
        TypeFilter typeFilter = new TypeFilter();
        typeFilter.addType(1);
        typeFilter.addType(2);
        XMLEventReader createFilteredReader = newInstance.createFilteredReader(newInstance.createXMLEventReader((Reader) new FileReader(strArr[0])), (EventFilter) typeFilter);
        while (createFilteredReader.hasNext()) {
            System.out.println(createFilteredReader.nextEvent());
        }
    }
}
