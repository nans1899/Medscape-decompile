package org.dom4j.io;

import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.dom4j.Attribute;
import org.dom4j.CharacterData;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;

public class STAXEventReader {
    private DocumentFactory factory;
    private XMLInputFactory inputFactory;

    public STAXEventReader() {
        this.inputFactory = XMLInputFactory.newInstance();
        this.factory = DocumentFactory.getInstance();
    }

    public STAXEventReader(DocumentFactory documentFactory) {
        this.inputFactory = XMLInputFactory.newInstance();
        if (documentFactory != null) {
            this.factory = documentFactory;
        } else {
            this.factory = DocumentFactory.getInstance();
        }
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        if (documentFactory != null) {
            this.factory = documentFactory;
        } else {
            this.factory = DocumentFactory.getInstance();
        }
    }

    public Document readDocument(InputStream inputStream) throws XMLStreamException {
        return readDocument(inputStream, (String) null);
    }

    public Document readDocument(Reader reader) throws XMLStreamException {
        return readDocument(reader, (String) null);
    }

    public Document readDocument(InputStream inputStream, String str) throws XMLStreamException {
        XMLEventReader createXMLEventReader = this.inputFactory.createXMLEventReader(str, inputStream);
        try {
            return readDocument(createXMLEventReader);
        } finally {
            createXMLEventReader.close();
        }
    }

    public Document readDocument(Reader reader, String str) throws XMLStreamException {
        XMLEventReader createXMLEventReader = this.inputFactory.createXMLEventReader(str, reader);
        try {
            return readDocument(createXMLEventReader);
        } finally {
            createXMLEventReader.close();
        }
    }

    public Node readNode(XMLEventReader xMLEventReader) throws XMLStreamException {
        XMLEvent peek = xMLEventReader.peek();
        if (peek.isStartElement()) {
            return readElement(xMLEventReader);
        }
        if (peek.isCharacters()) {
            return readCharacters(xMLEventReader);
        }
        if (peek.isStartDocument()) {
            return readDocument(xMLEventReader);
        }
        if (peek.isProcessingInstruction()) {
            return readProcessingInstruction(xMLEventReader);
        }
        if (peek.isEntityReference()) {
            return readEntityReference(xMLEventReader);
        }
        if (peek.isAttribute()) {
            return readAttribute(xMLEventReader);
        }
        if (peek.isNamespace()) {
            return readNamespace(xMLEventReader);
        }
        StringBuffer stringBuffer = new StringBuffer("Unsupported event: ");
        stringBuffer.append(peek);
        throw new XMLStreamException(stringBuffer.toString());
    }

    public Document readDocument(XMLEventReader xMLEventReader) throws XMLStreamException {
        Document document = null;
        while (xMLEventReader.hasNext()) {
            int eventType = xMLEventReader.peek().getEventType();
            if (!(eventType == 4 || eventType == 6)) {
                if (eventType == 7) {
                    StartDocument startDocument = (StartDocument) xMLEventReader.nextEvent();
                    if (document != null) {
                        throw new XMLStreamException("Unexpected StartDocument event", startDocument.getLocation());
                    } else if (startDocument.encodingSet()) {
                        document = this.factory.createDocument(startDocument.getCharacterEncodingScheme());
                    } else {
                        document = this.factory.createDocument();
                    }
                } else if (eventType != 8) {
                    if (document == null) {
                        document = this.factory.createDocument();
                    }
                    document.add(readNode(xMLEventReader));
                }
            }
            xMLEventReader.nextEvent();
        }
        return document;
    }

    public Element readElement(XMLEventReader xMLEventReader) throws XMLStreamException {
        XMLEvent peek = xMLEventReader.peek();
        if (peek.isStartElement()) {
            StartElement asStartElement = xMLEventReader.nextEvent().asStartElement();
            Element createElement = createElement(asStartElement);
            while (xMLEventReader.hasNext()) {
                if (xMLEventReader.peek().isEndElement()) {
                    EndElement asEndElement = xMLEventReader.nextEvent().asEndElement();
                    if (asEndElement.getName().equals(asStartElement.getName())) {
                        return createElement;
                    }
                    StringBuffer stringBuffer = new StringBuffer("Expected ");
                    stringBuffer.append(asStartElement.getName());
                    stringBuffer.append(" end-tag, but found");
                    stringBuffer.append(asEndElement.getName());
                    throw new XMLStreamException(stringBuffer.toString());
                }
                createElement.add(readNode(xMLEventReader));
            }
            throw new XMLStreamException("Unexpected end of stream while reading element content");
        }
        StringBuffer stringBuffer2 = new StringBuffer("Expected Element event, found: ");
        stringBuffer2.append(peek);
        throw new XMLStreamException(stringBuffer2.toString());
    }

    public Attribute readAttribute(XMLEventReader xMLEventReader) throws XMLStreamException {
        XMLEvent peek = xMLEventReader.peek();
        if (peek.isAttribute()) {
            return createAttribute((Element) null, (javax.xml.stream.events.Attribute) xMLEventReader.nextEvent());
        }
        StringBuffer stringBuffer = new StringBuffer("Expected Attribute event, found: ");
        stringBuffer.append(peek);
        throw new XMLStreamException(stringBuffer.toString());
    }

    public Namespace readNamespace(XMLEventReader xMLEventReader) throws XMLStreamException {
        XMLEvent peek = xMLEventReader.peek();
        if (peek.isNamespace()) {
            return createNamespace((javax.xml.stream.events.Namespace) xMLEventReader.nextEvent());
        }
        StringBuffer stringBuffer = new StringBuffer("Expected Namespace event, found: ");
        stringBuffer.append(peek);
        throw new XMLStreamException(stringBuffer.toString());
    }

    public CharacterData readCharacters(XMLEventReader xMLEventReader) throws XMLStreamException {
        XMLEvent peek = xMLEventReader.peek();
        if (peek.isCharacters()) {
            return createCharacterData(xMLEventReader.nextEvent().asCharacters());
        }
        StringBuffer stringBuffer = new StringBuffer("Expected Characters event, found: ");
        stringBuffer.append(peek);
        throw new XMLStreamException(stringBuffer.toString());
    }

    public Comment readComment(XMLEventReader xMLEventReader) throws XMLStreamException {
        XMLEvent peek = xMLEventReader.peek();
        if (peek instanceof javax.xml.stream.events.Comment) {
            return createComment((javax.xml.stream.events.Comment) xMLEventReader.nextEvent());
        }
        StringBuffer stringBuffer = new StringBuffer("Expected Comment event, found: ");
        stringBuffer.append(peek);
        throw new XMLStreamException(stringBuffer.toString());
    }

    public Entity readEntityReference(XMLEventReader xMLEventReader) throws XMLStreamException {
        XMLEvent peek = xMLEventReader.peek();
        if (peek.isEntityReference()) {
            return createEntity((EntityReference) xMLEventReader.nextEvent());
        }
        StringBuffer stringBuffer = new StringBuffer("Expected EntityRef event, found: ");
        stringBuffer.append(peek);
        throw new XMLStreamException(stringBuffer.toString());
    }

    public ProcessingInstruction readProcessingInstruction(XMLEventReader xMLEventReader) throws XMLStreamException {
        XMLEvent peek = xMLEventReader.peek();
        if (peek.isProcessingInstruction()) {
            return createProcessingInstruction((javax.xml.stream.events.ProcessingInstruction) xMLEventReader.nextEvent());
        }
        StringBuffer stringBuffer = new StringBuffer("Expected PI event, found: ");
        stringBuffer.append(peek);
        throw new XMLStreamException(stringBuffer.toString());
    }

    public Element createElement(StartElement startElement) {
        Element createElement = this.factory.createElement(createQName(startElement.getName()));
        Iterator attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            javax.xml.stream.events.Attribute attribute = (javax.xml.stream.events.Attribute) attributes.next();
            createElement.addAttribute(createQName(attribute.getName()), attribute.getValue());
        }
        Iterator namespaces = startElement.getNamespaces();
        while (namespaces.hasNext()) {
            javax.xml.stream.events.Namespace namespace = (javax.xml.stream.events.Namespace) namespaces.next();
            createElement.addNamespace(namespace.getPrefix(), namespace.getNamespaceURI());
        }
        return createElement;
    }

    public Attribute createAttribute(Element element, javax.xml.stream.events.Attribute attribute) {
        return this.factory.createAttribute(element, createQName(attribute.getName()), attribute.getValue());
    }

    public Namespace createNamespace(javax.xml.stream.events.Namespace namespace) {
        return this.factory.createNamespace(namespace.getPrefix(), namespace.getNamespaceURI());
    }

    public CharacterData createCharacterData(Characters characters) {
        String data = characters.getData();
        if (characters.isCData()) {
            return this.factory.createCDATA(data);
        }
        return this.factory.createText(data);
    }

    public Comment createComment(javax.xml.stream.events.Comment comment) {
        return this.factory.createComment(comment.getText());
    }

    public Entity createEntity(EntityReference entityReference) {
        return this.factory.createEntity(entityReference.getName(), entityReference.getDeclaration().getReplacementText());
    }

    public ProcessingInstruction createProcessingInstruction(javax.xml.stream.events.ProcessingInstruction processingInstruction) {
        return this.factory.createProcessingInstruction(processingInstruction.getTarget(), processingInstruction.getData());
    }

    public QName createQName(javax.xml.namespace.QName qName) {
        return this.factory.createQName(qName.getLocalPart(), qName.getPrefix(), qName.getNamespaceURI());
    }
}
