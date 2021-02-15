package com.bea.xml.stream;

import com.bea.xml.stream.events.CharactersEvent;
import com.bea.xml.stream.events.CommentEvent;
import com.bea.xml.stream.events.DTDEvent;
import com.bea.xml.stream.events.EndDocumentEvent;
import com.bea.xml.stream.events.EndElementEvent;
import com.bea.xml.stream.events.EntityReferenceEvent;
import com.bea.xml.stream.events.ProcessingInstructionEvent;
import com.bea.xml.stream.events.StartDocumentEvent;
import com.bea.xml.stream.events.StartElementEvent;
import com.bea.xml.stream.util.ElementTypeNames;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.stream.util.XMLEventConsumer;

public class StaticAllocator implements XMLEventAllocator {
    public static final String FEATURE_STAX_ENTITIES = "javax.xml.stream.entities";
    public static final String FEATURE_STAX_NOTATIONS = "javax.xml.stream.notations";
    CharactersEvent cData = new CharactersEvent("", true);
    CharactersEvent characters = new CharactersEvent();
    CommentEvent comment = new CommentEvent();
    DTDEvent dtd = new DTDEvent();
    EndDocumentEvent endDoc = new EndDocumentEvent();
    EndElementEvent endElement = new EndElementEvent();
    EntityReferenceEvent entity = new EntityReferenceEvent();
    ProcessingInstructionEvent pi = new ProcessingInstructionEvent();
    CharactersEvent space = new CharactersEvent();
    StartDocumentEvent startDoc = new StartDocumentEvent();
    StartElementEvent startElement = new StartElementEvent();

    public String toString() {
        return "Static Allocator";
    }

    public XMLEventAllocator newInstance() {
        return new StaticAllocator();
    }

    public StartElement allocateStartElement(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.startElement.reset();
        this.startElement.setName(new QName(xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName(), EventFactory.checkPrefix(xMLStreamReader.getPrefix())));
        Iterator attributes = XMLEventAllocatorBase.getAttributes(xMLStreamReader);
        while (attributes.hasNext()) {
            this.startElement.addAttribute((Attribute) attributes.next());
        }
        Iterator namespaces = XMLEventAllocatorBase.getNamespaces(xMLStreamReader);
        while (namespaces.hasNext()) {
            this.startElement.addAttribute((Namespace) namespaces.next());
        }
        return this.startElement;
    }

    public EndElement allocateEndElement(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.endElement.reset();
        this.endElement.setName(new QName(xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName(), EventFactory.checkPrefix(xMLStreamReader.getPrefix())));
        Iterator namespaces = XMLEventAllocatorBase.getNamespaces(xMLStreamReader);
        while (namespaces.hasNext()) {
            this.endElement.addNamespace((Namespace) namespaces.next());
        }
        return this.endElement;
    }

    public Characters allocateCharacters(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.characters.setData(xMLStreamReader.getText());
        return this.characters;
    }

    public Characters allocateCData(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.cData.setData(xMLStreamReader.getText());
        return this.cData;
    }

    public Characters allocateSpace(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.space.setSpace(true);
        this.space.setData(xMLStreamReader.getText());
        return this.space;
    }

    public EntityReference allocateEntityReference(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.entity.setName(xMLStreamReader.getLocalName());
        this.entity.setReplacementText(xMLStreamReader.getText());
        return this.entity;
    }

    public ProcessingInstruction allocatePI(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.pi.setTarget(xMLStreamReader.getPITarget());
        this.pi.setData(xMLStreamReader.getPIData());
        return this.pi;
    }

    public Comment allocateComment(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.comment.setData(xMLStreamReader.getText());
        return this.comment;
    }

    public StartDocument allocateStartDocument(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        allocateXMLDeclaration(xMLStreamReader);
        return this.startDoc;
    }

    public EndDocument allocateEndDocument(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        return this.endDoc;
    }

    public DTD allocateDTD(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.dtd.setDTD(xMLStreamReader.getText());
        return this.dtd;
    }

    public StartDocument allocateXMLDeclaration(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this.startDoc.clear();
        String characterEncodingScheme = xMLStreamReader.getCharacterEncodingScheme();
        String version = xMLStreamReader.getVersion();
        boolean isStandalone = xMLStreamReader.isStandalone();
        if (characterEncodingScheme != null && version != null && !isStandalone) {
            this.startDoc.setEncoding(characterEncodingScheme);
            this.startDoc.setVersion(version);
            this.startDoc.setStandalone(isStandalone);
            return this.startDoc;
        } else if (version == null || characterEncodingScheme == null) {
            if (characterEncodingScheme != null) {
                this.startDoc.setEncoding(characterEncodingScheme);
            }
            return this.startDoc;
        } else {
            this.startDoc.setEncoding(characterEncodingScheme);
            this.startDoc.setVersion(version);
            return this.startDoc;
        }
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
                stringBuffer.append(ElementTypeNames.getEventTypeString(xMLStreamReader.getEventType()));
                stringBuffer.append("]");
                throw new XMLStreamException(stringBuffer.toString());
        }
    }

    public void allocate(XMLStreamReader xMLStreamReader, XMLEventConsumer xMLEventConsumer) throws XMLStreamException {
        xMLEventConsumer.add(allocate(xMLStreamReader));
    }
}
