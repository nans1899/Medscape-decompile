package com.bea.xml.stream;

import com.bea.xml.stream.util.ElementTypeNames;
import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import net.bytebuddy.pool.TypePool;

public class EventScanner {
    protected char currentChar;
    protected int currentLine = 0;
    private boolean readEndDocument = false;
    protected Reader reader;

    public EventScanner() {
    }

    public EventScanner(Reader reader2) throws IOException {
        setReader(reader2);
    }

    public void setReader(Reader reader2) throws IOException {
        this.reader = reader2;
        read();
        skipSpace();
    }

    /* access modifiers changed from: protected */
    public String readString(char c) throws IOException, XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        while (getChar() != c) {
            if (getChar() == '[' && c == ']') {
                read();
                stringBuffer.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
                if (getChar() != ']') {
                    stringBuffer.append(readString(']'));
                }
                stringBuffer.append(']');
                read(']');
            } else {
                stringBuffer.append(getChar());
                read();
            }
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public char getChar() {
        return this.currentChar;
    }

    /* access modifiers changed from: protected */
    public void skipSpace() throws IOException {
        while (true) {
            boolean z = true;
            boolean z2 = (this.currentChar == ' ') | (this.currentChar == 10) | (this.currentChar == 9);
            if (this.currentChar != 13) {
                z = false;
            }
            if (z2 || z) {
                read();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public char read() throws IOException {
        char read = (char) this.reader.read();
        this.currentChar = read;
        if (read == 10) {
            this.currentLine++;
        }
        return this.currentChar;
    }

    /* access modifiers changed from: protected */
    public char read(char c) throws XMLStreamException, IOException {
        if (this.currentChar == c) {
            return read();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unexpected character '");
        stringBuffer.append(this.currentChar);
        stringBuffer.append("' , expected '");
        stringBuffer.append(c);
        stringBuffer.append("' at line ");
        stringBuffer.append(this.currentLine);
        throw new XMLStreamException(stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public void read(String str) throws XMLStreamException, IOException {
        for (int i = 0; i < str.length(); i++) {
            read(str.charAt(i));
        }
    }

    /* access modifiers changed from: protected */
    public int readType() throws XMLStreamException, IOException {
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        int eventType = ElementTypeNames.getEventType(readString(']'));
        read(']');
        return eventType;
    }

    public EventState readStartElement() throws XMLStreamException, IOException {
        EventState eventState = new EventState(1);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        eventState.setName(readName());
        if (getChar() == '[') {
            for (Object next : readAttributes()) {
                if (next instanceof Namespace) {
                    eventState.addNamespace(next);
                } else {
                    eventState.addAttribute(next);
                }
            }
        }
        read(']');
        return eventState;
    }

    public EventState readEndElement() throws XMLStreamException, IOException {
        EventState eventState = new EventState(2);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        eventState.setName(readName());
        read(']');
        return eventState;
    }

    public EventState readProcessingInstruction() throws XMLStreamException, IOException {
        String str;
        EventState eventState = new EventState(3);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        String readString = readString(']');
        read(']');
        if (getChar() == ',') {
            read(",[");
            str = readString(']');
            read(']');
        } else {
            str = null;
        }
        eventState.setData(readString);
        eventState.setExtraData(str);
        return eventState;
    }

    public EventState readCharacterData() throws XMLStreamException, IOException {
        EventState eventState = new EventState(4);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        eventState.setData(readString(']'));
        read(']');
        return eventState;
    }

    public EventState readCDATA() throws XMLStreamException, IOException {
        EventState eventState = new EventState(12);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        readString(']');
        read(']');
        return eventState;
    }

    public EventState readStartDocument() throws XMLStreamException, IOException {
        EventState eventState = new EventState(7);
        if (getChar() != ';') {
            read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            String readString = readString(']');
            read(']');
            read((char) ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            String readString2 = readString(']');
            read(']');
            read(']');
            eventState.setData(readString);
            eventState.setExtraData(readString2);
        }
        return eventState;
    }

    public EventState readDTD() throws XMLStreamException, IOException {
        EventState eventState = new EventState(11);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        String readString = readString(']');
        read(']');
        eventState.setData(readString);
        return eventState;
    }

    public EventState readEndDocument() throws XMLStreamException {
        return new EventState(8);
    }

    public EventState readComment() throws XMLStreamException, IOException {
        EventState eventState = new EventState(5);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        eventState.setData(readString(']'));
        read(']');
        return eventState;
    }

    public String getPrefix(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            return null;
        }
        return str.substring(0, indexOf);
    }

    public String getName(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            return str;
        }
        return str.substring(indexOf + 1);
    }

    public QName readName() throws XMLStreamException, IOException {
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        QName readName = readName(']');
        read(']');
        return readName;
    }

    public QName readName(char c) throws XMLStreamException, IOException {
        String str;
        String str2 = "";
        if (getChar() == '\'') {
            read('\'');
            str = readString('\'');
            read('\'');
            read((char) ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        } else {
            str = str2;
        }
        String readString = readString(c);
        String prefix = getPrefix(readString);
        if (prefix != null) {
            str2 = prefix;
        }
        return new QName(str, getName(readString), str2);
    }

    public List readAttributes() throws XMLStreamException, IOException {
        ArrayList arrayList = new ArrayList();
        while (getChar() == '[') {
            arrayList.add(readAttribute());
        }
        return arrayList;
    }

    public Attribute readAttribute() throws XMLStreamException, IOException {
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        String readString = readString(']');
        read(']');
        QName readName = readName();
        read("=[");
        String readString2 = readString(']');
        read(']');
        read(']');
        if (readString.equals("ATTRIBUTE")) {
            return new AttributeBase(readName, readString2);
        }
        if (readString.equals(MessengerShareContentUtility.PREVIEW_DEFAULT)) {
            return new NamespaceBase(readString2);
        }
        if (readString.equals("NAMESPACE")) {
            return new NamespaceBase(readName.getLocalPart(), readString2);
        }
        throw new XMLStreamException("Parser Error expected (ATTRIBUTE||DEFAULT|NAMESPACE");
    }

    public EventState readEntityReference() throws XMLStreamException, IOException {
        EventState eventState = new EventState(9);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        eventState.setData(readString(']'));
        read(']');
        return eventState;
    }

    public EventState readSpace() throws XMLStreamException, IOException {
        EventState eventState = new EventState(6);
        read((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        String readString = readString(']');
        read(']');
        eventState.setData(readString);
        return eventState;
    }

    public EventState readElement() throws XMLStreamException, IOException {
        EventState eventState;
        int readType = readType();
        switch (readType) {
            case 1:
                eventState = readStartElement();
                break;
            case 2:
                eventState = readEndElement();
                break;
            case 3:
                eventState = readProcessingInstruction();
                break;
            case 4:
                eventState = readCharacterData();
                break;
            case 5:
                eventState = readComment();
                break;
            case 6:
                eventState = readSpace();
                break;
            case 7:
                eventState = readStartDocument();
                break;
            case 8:
                this.readEndDocument = true;
                eventState = readEndDocument();
                break;
            case 9:
                eventState = readEntityReference();
                break;
            case 11:
                eventState = readDTD();
                break;
            case 12:
                eventState = readCDATA();
                break;
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Attempt to read unknown element [");
                stringBuffer.append(readType);
                stringBuffer.append("]");
                throw new XMLStreamException(stringBuffer.toString());
        }
        read(';');
        skipSpace();
        return eventState;
    }

    public boolean endDocumentIsPresent() {
        return this.readEndDocument;
    }

    public boolean hasNext() throws IOException {
        return this.reader.ready() && !this.readEndDocument;
    }

    public static void main(String[] strArr) throws Exception {
        EventScanner eventScanner = new EventScanner(new FileReader(strArr[0]));
        while (eventScanner.hasNext()) {
            System.out.println(eventScanner.readElement());
        }
    }
}
