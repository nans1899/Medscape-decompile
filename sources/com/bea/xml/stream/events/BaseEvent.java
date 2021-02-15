package com.bea.xml.stream.events;

import com.bea.xml.stream.util.ElementTypeNames;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public abstract class BaseEvent implements XMLEvent, Location {
    private int characterOffset = 0;
    private int column = -1;
    private int eventType = -1;
    private int line = -1;
    private String locationURI;

    /* access modifiers changed from: protected */
    public abstract void doWriteAsEncodedUnicode(Writer writer) throws IOException, XMLStreamException;

    public Location getLocation() {
        return this;
    }

    public String getPublicId() {
        return null;
    }

    public QName getSchemaType() {
        return null;
    }

    public String getSourceName() {
        return null;
    }

    public String getSystemId() {
        return null;
    }

    public void recycle() {
    }

    public BaseEvent() {
    }

    public BaseEvent(int i) {
        this.eventType = i;
    }

    public int getEventType() {
        return this.eventType;
    }

    /* access modifiers changed from: protected */
    public void setEventType(int i) {
        this.eventType = i;
    }

    public String getTypeAsString() {
        return ElementTypeNames.getEventTypeString(this.eventType);
    }

    public boolean isStartElement() {
        return this.eventType == 1;
    }

    public boolean isEndElement() {
        return this.eventType == 2;
    }

    public boolean isEntityReference() {
        return this.eventType == 9;
    }

    public boolean isProcessingInstruction() {
        return this.eventType == 3;
    }

    public boolean isCharacters() {
        return this.eventType == 4;
    }

    public boolean isStartDocument() {
        return this.eventType == 7;
    }

    public boolean isEndDocument() {
        return this.eventType == 8;
    }

    public boolean isAttribute() {
        return this.eventType == 10;
    }

    public boolean isNamespace() {
        return this.eventType == 13;
    }

    public int getLineNumber() {
        return this.line;
    }

    public void setLineNumber(int i) {
        this.line = i;
    }

    public int getColumnNumber() {
        return this.column;
    }

    public void setColumnNumber(int i) {
        this.column = i;
    }

    public int getCharacterOffset() {
        return this.characterOffset;
    }

    public void setCharacterOffset(int i) {
        this.characterOffset = i;
    }

    public String getLocationURI() {
        return this.locationURI;
    }

    public void setLocationURI(String str) {
        this.locationURI = str;
    }

    public StartElement asStartElement() {
        return (StartElement) this;
    }

    public EndElement asEndElement() {
        return (EndElement) this;
    }

    public Characters asCharacters() {
        return (Characters) this;
    }

    public final void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            doWriteAsEncodedUnicode(writer);
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter(64);
        try {
            writeAsEncodedUnicode(stringWriter);
        } catch (XMLStreamException e) {
            stringWriter.write("[ERROR: ");
            stringWriter.write(e.toString());
            stringWriter.write("]");
        }
        return stringWriter.toString();
    }
}
