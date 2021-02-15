package org.dom4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.util.XMLEventConsumer;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;

public class STAXEventWriter {
    private XMLEventConsumer consumer;
    /* access modifiers changed from: private */
    public XMLEventFactory factory;
    private XMLOutputFactory outputFactory;

    public STAXEventWriter() {
        this.factory = XMLEventFactory.newInstance();
        this.outputFactory = XMLOutputFactory.newInstance();
    }

    public STAXEventWriter(File file) throws XMLStreamException, IOException {
        this.factory = XMLEventFactory.newInstance();
        XMLOutputFactory newInstance = XMLOutputFactory.newInstance();
        this.outputFactory = newInstance;
        this.consumer = newInstance.createXMLEventWriter((Writer) new FileWriter(file));
    }

    public STAXEventWriter(Writer writer) throws XMLStreamException {
        this.factory = XMLEventFactory.newInstance();
        XMLOutputFactory newInstance = XMLOutputFactory.newInstance();
        this.outputFactory = newInstance;
        this.consumer = newInstance.createXMLEventWriter(writer);
    }

    public STAXEventWriter(OutputStream outputStream) throws XMLStreamException {
        this.factory = XMLEventFactory.newInstance();
        XMLOutputFactory newInstance = XMLOutputFactory.newInstance();
        this.outputFactory = newInstance;
        this.consumer = newInstance.createXMLEventWriter(outputStream);
    }

    public STAXEventWriter(XMLEventConsumer xMLEventConsumer) {
        this.factory = XMLEventFactory.newInstance();
        this.outputFactory = XMLOutputFactory.newInstance();
        this.consumer = xMLEventConsumer;
    }

    public XMLEventConsumer getConsumer() {
        return this.consumer;
    }

    public void setConsumer(XMLEventConsumer xMLEventConsumer) {
        this.consumer = xMLEventConsumer;
    }

    public XMLEventFactory getEventFactory() {
        return this.factory;
    }

    public void setEventFactory(XMLEventFactory xMLEventFactory) {
        this.factory = xMLEventFactory;
    }

    public void writeNode(Node node) throws XMLStreamException {
        switch (node.getNodeType()) {
            case 1:
                writeElement((Element) node);
                return;
            case 2:
                writeAttribute((Attribute) node);
                return;
            case 3:
                writeText((Text) node);
                return;
            case 4:
                writeCDATA((CDATA) node);
                return;
            case 5:
                writeEntity((Entity) node);
                return;
            case 7:
                writeProcessingInstruction((ProcessingInstruction) node);
                return;
            case 8:
                writeComment((Comment) node);
                return;
            case 9:
                writeDocument((Document) node);
                return;
            case 10:
                writeDocumentType((DocumentType) node);
                return;
            case 13:
                writeNamespace((Namespace) node);
                return;
            default:
                StringBuffer stringBuffer = new StringBuffer("Unsupported DOM4J Node: ");
                stringBuffer.append(node);
                throw new XMLStreamException(stringBuffer.toString());
        }
    }

    public void writeChildNodes(Branch branch) throws XMLStreamException {
        int nodeCount = branch.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            writeNode(branch.node(i));
        }
    }

    public void writeElement(Element element) throws XMLStreamException {
        this.consumer.add(createStartElement(element));
        writeChildNodes(element);
        this.consumer.add(createEndElement(element));
    }

    public StartElement createStartElement(Element element) {
        return this.factory.createStartElement(createQName(element.getQName()), (Iterator) new AttributeIterator(element.attributeIterator()), (Iterator) new NamespaceIterator(element.declaredNamespaces().iterator()));
    }

    public EndElement createEndElement(Element element) {
        return this.factory.createEndElement(createQName(element.getQName()), new NamespaceIterator(element.declaredNamespaces().iterator()));
    }

    public void writeAttribute(Attribute attribute) throws XMLStreamException {
        this.consumer.add(createAttribute(attribute));
    }

    public javax.xml.stream.events.Attribute createAttribute(Attribute attribute) {
        return this.factory.createAttribute(createQName(attribute.getQName()), attribute.getValue());
    }

    public void writeNamespace(Namespace namespace) throws XMLStreamException {
        this.consumer.add(createNamespace(namespace));
    }

    public javax.xml.stream.events.Namespace createNamespace(Namespace namespace) {
        return this.factory.createNamespace(namespace.getPrefix(), namespace.getURI());
    }

    public void writeText(Text text) throws XMLStreamException {
        this.consumer.add(createCharacters(text));
    }

    public Characters createCharacters(Text text) {
        return this.factory.createCharacters(text.getText());
    }

    public void writeCDATA(CDATA cdata) throws XMLStreamException {
        this.consumer.add(createCharacters(cdata));
    }

    public Characters createCharacters(CDATA cdata) {
        return this.factory.createCData(cdata.getText());
    }

    public void writeComment(Comment comment) throws XMLStreamException {
        this.consumer.add(createComment(comment));
    }

    public javax.xml.stream.events.Comment createComment(Comment comment) {
        return this.factory.createComment(comment.getText());
    }

    public void writeProcessingInstruction(ProcessingInstruction processingInstruction) throws XMLStreamException {
        this.consumer.add(createProcessingInstruction(processingInstruction));
    }

    public javax.xml.stream.events.ProcessingInstruction createProcessingInstruction(ProcessingInstruction processingInstruction) {
        return this.factory.createProcessingInstruction(processingInstruction.getTarget(), processingInstruction.getText());
    }

    public void writeEntity(Entity entity) throws XMLStreamException {
        this.consumer.add(createEntityReference(entity));
    }

    private EntityReference createEntityReference(Entity entity) {
        return this.factory.createEntityReference(entity.getName(), (EntityDeclaration) null);
    }

    public void writeDocumentType(DocumentType documentType) throws XMLStreamException {
        this.consumer.add(createDTD(documentType));
    }

    public DTD createDTD(DocumentType documentType) {
        StringWriter stringWriter = new StringWriter();
        try {
            documentType.write(stringWriter);
            return this.factory.createDTD(stringWriter.toString());
        } catch (IOException e) {
            throw new RuntimeException("Error writing DTD", e);
        }
    }

    public void writeDocument(Document document) throws XMLStreamException {
        this.consumer.add(createStartDocument(document));
        writeChildNodes(document);
        this.consumer.add(createEndDocument(document));
    }

    public StartDocument createStartDocument(Document document) {
        String xMLEncoding = document.getXMLEncoding();
        if (xMLEncoding != null) {
            return this.factory.createStartDocument(xMLEncoding);
        }
        return this.factory.createStartDocument();
    }

    public EndDocument createEndDocument(Document document) {
        return this.factory.createEndDocument();
    }

    public QName createQName(org.dom4j.QName qName) {
        return new QName(qName.getNamespaceURI(), qName.getName(), qName.getNamespacePrefix());
    }

    private class AttributeIterator implements Iterator {
        private Iterator iter;

        public AttributeIterator(Iterator it) {
            this.iter = it;
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public Object next() {
            Attribute attribute = (Attribute) this.iter.next();
            return STAXEventWriter.this.factory.createAttribute(STAXEventWriter.this.createQName(attribute.getQName()), attribute.getValue());
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class NamespaceIterator implements Iterator {
        private Iterator iter;

        public NamespaceIterator(Iterator it) {
            this.iter = it;
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public Object next() {
            Namespace namespace = (Namespace) this.iter.next();
            return STAXEventWriter.this.factory.createNamespace(namespace.getPrefix(), namespace.getURI());
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
