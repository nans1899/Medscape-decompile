package com.bea.xml.stream;

import com.bea.xml.stream.util.CircularQueue;
import com.bea.xml.stream.util.ElementTypeNames;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.NoSuchElementException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.stream.util.XMLEventConsumer;

public class XMLEventReaderBase implements XMLEventReader, XMLEventConsumer {
    protected XMLEventAllocator allocator;
    private ConfigurationContextBase configurationContext;
    private CircularQueue elementQ;
    private boolean open;
    private boolean reachedEOF;
    protected XMLStreamReader reader;

    public XMLEventReaderBase(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this(xMLStreamReader, new XMLEventAllocatorBase());
    }

    public XMLEventReaderBase(XMLStreamReader xMLStreamReader, XMLEventAllocator xMLEventAllocator) throws XMLStreamException {
        this.elementQ = new CircularQueue();
        this.open = true;
        this.reachedEOF = false;
        if (xMLStreamReader == null) {
            throw new IllegalArgumentException("XMLStreamReader may not be null");
        } else if (xMLEventAllocator != null) {
            this.reader = xMLStreamReader;
            this.open = true;
            this.allocator = xMLEventAllocator;
            if (xMLStreamReader.getEventType() == 7) {
                XMLEvent allocate = this.allocator.allocate(xMLStreamReader);
                xMLStreamReader.next();
                add(allocate);
            }
        } else {
            throw new IllegalArgumentException("XMLEventAllocator may not be null");
        }
    }

    public void setAllocator(XMLEventAllocator xMLEventAllocator) {
        if (xMLEventAllocator != null) {
            this.allocator = xMLEventAllocator;
            return;
        }
        throw new IllegalArgumentException("XMLEvent Allocator may not be null");
    }

    public String getElementText() throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        XMLEvent nextEvent = nextEvent();
        if (nextEvent.isStartElement()) {
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
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Precondition for readText is nextEvent().getTypeEventType() == START_ELEMENT (got ");
        stringBuffer2.append(nextEvent.getEventType());
        stringBuffer2.append(")");
        throw new XMLStreamException(stringBuffer2.toString());
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
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.XMLEventReaderBase.nextTag():javax.xml.stream.events.XMLEvent");
    }

    public Object next() {
        try {
            return nextEvent();
        } catch (XMLStreamException unused) {
            return null;
        }
    }

    public XMLEvent nextEvent() throws XMLStreamException {
        if (!needsMore() || parseSome()) {
            return get();
        }
        throw new NoSuchElementException("Attempt to call nextEvent() on a stream with no more elements");
    }

    public boolean hasNext() {
        if (!this.open) {
            return false;
        }
        if (!this.elementQ.isEmpty()) {
            return true;
        }
        try {
            if (this.reader.hasNext()) {
                return true;
            }
            this.open = false;
            return false;
        } catch (XMLStreamException unused) {
        }
    }

    public XMLEvent peek() throws XMLStreamException {
        if (!this.elementQ.isEmpty()) {
            return (XMLEvent) this.elementQ.peek();
        }
        if (parseSome()) {
            return (XMLEvent) this.elementQ.peek();
        }
        return null;
    }

    public void add(XMLEvent xMLEvent) throws XMLStreamException {
        this.elementQ.add(xMLEvent);
    }

    /* access modifiers changed from: protected */
    public boolean needsMore() {
        return this.elementQ.isEmpty();
    }

    /* access modifiers changed from: protected */
    public XMLEvent get() throws XMLStreamException {
        return (XMLEvent) this.elementQ.remove();
    }

    /* access modifiers changed from: protected */
    public boolean isOpen() {
        return !this.reachedEOF;
    }

    /* access modifiers changed from: protected */
    public void internal_close() {
        this.reachedEOF = true;
    }

    public void close() throws XMLStreamException {
        internal_close();
    }

    /* access modifiers changed from: protected */
    public boolean parseSome() throws XMLStreamException {
        if (this.reachedEOF) {
            return false;
        }
        this.allocator.allocate(this.reader, this);
        if (this.reader.hasNext()) {
            this.reader.next();
        }
        if (this.reader.getEventType() == 8) {
            this.allocator.allocate(this.reader, this);
            this.reachedEOF = true;
        }
        return !needsMore();
    }

    public void setConfigurationContext(ConfigurationContextBase configurationContextBase) {
        this.configurationContext = configurationContextBase;
    }

    public Object getProperty(String str) {
        return this.configurationContext.getProperty(str);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] strArr) throws Exception {
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.bea.xml.stream.EventFactory");
        XMLEventReader createXMLEventReader = XMLInputFactory.newInstance().createXMLEventReader((Reader) new FileReader(strArr[0]));
        while (createXMLEventReader.hasNext()) {
            XMLEvent nextEvent = createXMLEventReader.nextEvent();
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            stringBuffer.append(ElementTypeNames.getEventTypeString(nextEvent.getEventType()));
            stringBuffer.append("][");
            stringBuffer.append(nextEvent);
            stringBuffer.append("]");
            printStream.println(stringBuffer.toString());
        }
    }
}
