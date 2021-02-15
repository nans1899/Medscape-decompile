package com.bea.xml.stream;

import com.bea.xml.stream.util.NamespaceContextImpl;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;

public class XMLStreamPlayer implements XMLStreamReader {
    NamespaceContextImpl context = new NamespaceContextImpl();
    EventScanner scanner;
    EventState state;

    public void close() throws XMLStreamException {
    }

    public String getAttributeType(int i) {
        return "CDATA";
    }

    public String getCharacterEncodingScheme() {
        return null;
    }

    public Location getLocation() {
        return null;
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        return null;
    }

    public int getTextStart() {
        return 0;
    }

    public String getVersion() {
        return "1.0";
    }

    public boolean isAttributeSpecified(int i) {
        return false;
    }

    public boolean isStandalone() {
        return true;
    }

    public boolean isWhiteSpace() {
        return false;
    }

    public void require(int i, String str, String str2) throws XMLStreamException {
    }

    public boolean standaloneSet() {
        return false;
    }

    public XMLStreamReader subReader() throws XMLStreamException {
        return null;
    }

    public XMLStreamPlayer() {
    }

    public XMLStreamPlayer(InputStream inputStream) {
        try {
            this.scanner = new EventScanner(new InputStreamReader(inputStream));
            next();
            if (getEventType() == 7) {
                this.scanner = new EventScanner(new InputStreamReader(inputStream, getCharacterEncodingScheme()));
            }
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to instantiate the XMLStreamPlayer");
            stringBuffer.append(e.getMessage());
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public XMLStreamPlayer(Reader reader) {
        try {
            this.scanner = new EventScanner(reader);
            next();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int next() throws XMLStreamException {
        try {
            if (!this.scanner.hasNext()) {
                this.state = null;
                return -1;
            }
            this.state = this.scanner.readElement();
            if (isStartElement()) {
                this.context.openScope();
                for (int i = 0; i < getNamespaceCount(); i++) {
                    this.context.bindNamespace(getNamespacePrefix(i), getNamespaceURI(i));
                }
            } else if (isEndElement() && this.context.getDepth() > 0) {
                this.context.closeScope();
            }
            return this.state.getType();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            throw new XMLStreamException(e.getMessage(), (Throwable) e);
        }
    }

    public String getElementText() throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        if (getEventType() == 1) {
            while (next() != 8) {
                if (!isStartElement()) {
                    if (isCharacters()) {
                        stringBuffer.append(getText());
                    }
                    if (isEndElement()) {
                        return stringBuffer.toString();
                    }
                } else {
                    throw new XMLStreamException("Unexpected Element start");
                }
            }
            throw new XMLStreamException("Unexpected end of Document");
        }
        throw new XMLStreamException("Precondition for readText is getEventType() == START_ELEMENT");
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0008  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int nextTag() throws javax.xml.stream.XMLStreamException {
        /*
            r2 = this;
        L_0x0000:
            int r0 = r2.next()
            r1 = 8
            if (r0 == r1) goto L_0x002e
            boolean r0 = r2.isCharacters()
            if (r0 == 0) goto L_0x001d
            boolean r0 = r2.isWhiteSpace()
            if (r0 == 0) goto L_0x0015
            goto L_0x001d
        L_0x0015:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            java.lang.String r1 = "Unexpected text"
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x001d:
            boolean r0 = r2.isStartElement()
            if (r0 != 0) goto L_0x0029
            boolean r0 = r2.isEndElement()
            if (r0 == 0) goto L_0x0000
        L_0x0029:
            int r0 = r2.getEventType()
            return r0
        L_0x002e:
            javax.xml.stream.XMLStreamException r0 = new javax.xml.stream.XMLStreamException
            java.lang.String r1 = "Unexpected end of Document"
            r0.<init>((java.lang.String) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.XMLStreamPlayer.nextTag():int");
    }

    public boolean hasNext() throws XMLStreamException {
        try {
            return (this.state == null || this.state.getType() == 8) ? false : true;
        } catch (Exception e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    public String getNamespaceURI(String str) {
        return this.context.getNamespaceURI(str);
    }

    private Attribute getAttributeInternal(int i) {
        return (Attribute) this.state.getAttributes().get(i);
    }

    private Attribute getNamespaceInternal(int i) {
        return (Attribute) this.state.getNamespaces().get(i);
    }

    public boolean isStartElement() {
        return (getEventType() & 1) != 0;
    }

    public boolean isEndElement() {
        return (getEventType() & 2) != 0;
    }

    public boolean isCharacters() {
        return (getEventType() & 4) != 0;
    }

    public String getAttributeValue(String str, String str2) {
        for (int i = 0; i < getAttributeCount(); i++) {
            Attribute attributeInternal = getAttributeInternal(i);
            if (str2.equals(attributeInternal.getName().getLocalPart())) {
                if (str == null) {
                    return attributeInternal.getValue();
                }
                if (str.equals(attributeInternal.getName().getNamespaceURI())) {
                    return attributeInternal.getValue();
                }
            }
        }
        return null;
    }

    public int getAttributeCount() {
        if (isStartElement()) {
            return this.state.getAttributes().size();
        }
        return 0;
    }

    public QName getAttributeName(int i) {
        return new QName(getAttributeNamespace(i), getAttributeLocalName(i), getAttributePrefix(i));
    }

    public String getAttributeNamespace(int i) {
        Attribute attributeInternal = getAttributeInternal(i);
        if (attributeInternal == null) {
            return null;
        }
        return attributeInternal.getName().getNamespaceURI();
    }

    public String getAttributeLocalName(int i) {
        Attribute attributeInternal = getAttributeInternal(i);
        if (attributeInternal == null) {
            return null;
        }
        return attributeInternal.getName().getLocalPart();
    }

    public String getAttributePrefix(int i) {
        Attribute attributeInternal = getAttributeInternal(i);
        if (attributeInternal == null) {
            return null;
        }
        return attributeInternal.getName().getPrefix();
    }

    public String getAttributeValue(int i) {
        Attribute attributeInternal = getAttributeInternal(i);
        if (attributeInternal == null) {
            return null;
        }
        return attributeInternal.getValue();
    }

    public int getNamespaceCount() {
        if (isStartElement()) {
            return this.state.getNamespaces().size();
        }
        return 0;
    }

    public String getNamespacePrefix(int i) {
        Attribute namespaceInternal = getNamespaceInternal(i);
        if (namespaceInternal == null) {
            return null;
        }
        return namespaceInternal.getName().getLocalPart();
    }

    public String getNamespaceURI(int i) {
        Attribute namespaceInternal = getNamespaceInternal(i);
        if (namespaceInternal == null) {
            return null;
        }
        return namespaceInternal.getValue();
    }

    public NamespaceContext getNamespaceContext() {
        return this.context;
    }

    public int getEventType() {
        EventState eventState = this.state;
        if (eventState == null) {
            return 8;
        }
        return eventState.getType();
    }

    public String getText() {
        return this.state.getData();
    }

    public Reader getTextStream() {
        throw new UnsupportedOperationException();
    }

    public char[] getTextCharacters() {
        return this.state.getData().toCharArray();
    }

    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        throw new UnsupportedOperationException();
    }

    public int getTextLength() {
        return this.state.getData().length();
    }

    public String getEncoding() {
        return this.state.getData();
    }

    public boolean hasText() {
        return (getEventType() & 15) != 0;
    }

    public QName getName() {
        return new QName(getNamespaceURI(), getLocalName(), getPrefix());
    }

    public String getLocalName() {
        return this.state.getLocalName();
    }

    public boolean hasName() {
        return (getEventType() & 11) != 0;
    }

    public String getNamespaceURI() {
        return this.state.getNamespaceURI();
    }

    public String getPrefix() {
        return this.state.getPrefix();
    }

    public String getPITarget() {
        return this.state.getData();
    }

    public String getPIData() {
        return this.state.getExtraData();
    }

    public boolean endDocumentIsPresent() {
        return this.scanner.endDocumentIsPresent();
    }

    public static void main(String[] strArr) throws Exception {
        XMLStreamPlayer xMLStreamPlayer = new XMLStreamPlayer((Reader) new FileReader(strArr[0]));
        XMLStreamWriter createXMLStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter((OutputStream) System.out);
        ReaderToWriter readerToWriter = new ReaderToWriter(createXMLStreamWriter);
        while (xMLStreamPlayer.hasNext()) {
            readerToWriter.write(xMLStreamPlayer);
            xMLStreamPlayer.next();
        }
        createXMLStreamWriter.flush();
    }
}
