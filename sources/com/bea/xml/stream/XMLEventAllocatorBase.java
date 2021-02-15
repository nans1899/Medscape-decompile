package com.bea.xml.stream;

import com.bea.xml.stream.events.DTDEvent;
import com.bea.xml.stream.events.EntityDeclarationEvent;
import com.bea.xml.stream.util.ElementTypeNames;
import com.bea.xml.stream.util.EmptyIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.stream.util.XMLEventConsumer;

public class XMLEventAllocatorBase implements XMLEventAllocator {
    XMLEventFactory factory = XMLEventFactory.newInstance();

    public String toString() {
        return "NonStaticAllocator";
    }

    public XMLEventAllocator newInstance() {
        return new XMLEventAllocatorBase();
    }

    public static Iterator getAttributes(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.getAttributeCount() == 0) {
            return EmptyIterator.emptyIterator;
        }
        int attributeCount = xMLStreamReader.getAttributeCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < attributeCount; i++) {
            arrayList.add(new AttributeBase(xMLStreamReader.getAttributePrefix(i), xMLStreamReader.getAttributeNamespace(i), xMLStreamReader.getAttributeLocalName(i), xMLStreamReader.getAttributeValue(i), xMLStreamReader.getAttributeType(i)));
        }
        return arrayList.iterator();
    }

    public static Iterator getNamespaces(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.getNamespaceCount() == 0) {
            return EmptyIterator.emptyIterator;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < xMLStreamReader.getNamespaceCount(); i++) {
            String namespacePrefix = xMLStreamReader.getNamespacePrefix(i);
            if (namespacePrefix == null || namespacePrefix.equals("")) {
                arrayList.add(new NamespaceBase(xMLStreamReader.getNamespaceURI(i)));
            } else {
                arrayList.add(new NamespaceBase(namespacePrefix, xMLStreamReader.getNamespaceURI(i)));
            }
        }
        return arrayList.iterator();
    }

    public StartElement allocateStartElement(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String prefix = xMLStreamReader.getPrefix();
        String namespaceURI = xMLStreamReader.getNamespaceURI();
        return this.factory.createStartElement(prefix == null ? "" : prefix, namespaceURI == null ? "" : namespaceURI, xMLStreamReader.getLocalName(), getAttributes(xMLStreamReader), getNamespaces(xMLStreamReader));
    }

    public EndElement allocateEndElement(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String prefix = xMLStreamReader.getPrefix();
        String namespaceURI = xMLStreamReader.getNamespaceURI();
        if (prefix == null) {
            prefix = "";
        }
        if (namespaceURI == null) {
            namespaceURI = "";
        }
        return this.factory.createEndElement(prefix, namespaceURI, xMLStreamReader.getLocalName(), getNamespaces(xMLStreamReader));
    }

    public Characters allocateCharacters(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String str = new String(xMLStreamReader.getTextCharacters(), xMLStreamReader.getTextStart(), xMLStreamReader.getTextLength());
        if (xMLStreamReader.isWhiteSpace()) {
            return this.factory.createSpace(str);
        }
        return this.factory.createCharacters(str);
    }

    public Characters allocateCData(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return this.factory.createCData(xMLStreamReader.getText());
    }

    public Characters allocateSpace(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return this.factory.createSpace(xMLStreamReader.getText());
    }

    public EntityReference allocateEntityReference(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String localName = xMLStreamReader.getLocalName();
        boolean z = xMLStreamReader instanceof MXParser;
        return this.factory.createEntityReference(localName, new EntityDeclarationEvent(localName, xMLStreamReader.getText()));
    }

    public ProcessingInstruction allocatePI(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return this.factory.createProcessingInstruction(xMLStreamReader.getPITarget(), xMLStreamReader.getPIData());
    }

    public Comment allocateComment(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return this.factory.createComment(xMLStreamReader.getText());
    }

    public StartDocument allocateStartDocument(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return allocateXMLDeclaration(xMLStreamReader);
    }

    public EndDocument allocateEndDocument(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return this.factory.createEndDocument();
    }

    public DTD allocateDTD(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        if (!(xMLStreamReader instanceof MXParser)) {
            return this.factory.createDTD(xMLStreamReader.getText());
        }
        MXParser mXParser = (MXParser) xMLStreamReader;
        DTDEvent dTDEvent = new DTDEvent(xMLStreamReader.getText());
        dTDEvent.setNotations((List) mXParser.getProperty("javax.xml.stream.notations"));
        dTDEvent.setEntities((List) mXParser.getProperty("javax.xml.stream.entities"));
        return dTDEvent;
    }

    public StartDocument allocateXMLDeclaration(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        String characterEncodingScheme = xMLStreamReader.getCharacterEncodingScheme();
        String version = xMLStreamReader.getVersion();
        boolean isStandalone = xMLStreamReader.isStandalone();
        if (characterEncodingScheme != null && version != null && !isStandalone) {
            return this.factory.createStartDocument(characterEncodingScheme, version, isStandalone);
        }
        if (version != null && characterEncodingScheme != null) {
            return this.factory.createStartDocument(characterEncodingScheme, version);
        }
        if (characterEncodingScheme != null) {
            return this.factory.createStartDocument(characterEncodingScheme);
        }
        return this.factory.createStartDocument();
    }

    public XMLEvent allocate(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        switch (xMLStreamReader.getEventType()) {
            case 1:
                return allocateStartElement(xMLStreamReader);
            case 2:
                return allocateEndElement(xMLStreamReader);
            case 3:
                return allocatePI(xMLStreamReader);
            case 4:
                return allocateCharacters(xMLStreamReader);
            case 5:
                return allocateComment(xMLStreamReader);
            case 6:
                return allocateCharacters(xMLStreamReader);
            case 7:
                return allocateStartDocument(xMLStreamReader);
            case 8:
                return allocateEndDocument(xMLStreamReader);
            case 9:
                return allocateEntityReference(xMLStreamReader);
            case 11:
                return allocateDTD(xMLStreamReader);
            case 12:
                return allocateCData(xMLStreamReader);
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unable to allocate event[");
                stringBuffer.append(xMLStreamReader.getEventType());
                stringBuffer.append(" , ");
                stringBuffer.append(ElementTypeNames.getEventTypeString(xMLStreamReader.getEventType()));
                stringBuffer.append("]");
                throw new XMLStreamException(stringBuffer.toString());
        }
    }

    public void allocate(XMLStreamReader xMLStreamReader, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        xMLEventConsumer.add(allocate(xMLStreamReader));
    }
}
