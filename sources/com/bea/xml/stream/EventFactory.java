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
import java.util.Iterator;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.Comment;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityDeclaration;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;

public class EventFactory extends XMLEventFactory {
    private Location location;

    public static String checkPrefix(String str) {
        return str == null ? "" : str;
    }

    public void setLocation(Location location2) {
        this.location = location2;
    }

    public Attribute createAttribute(QName qName, String str) {
        return new AttributeBase(qName, str);
    }

    public Attribute createAttribute(String str, String str2) {
        return new AttributeBase("", str, str2);
    }

    public Attribute createAttribute(String str, String str2, String str3, String str4) {
        return new AttributeBase(str, str2, str3, str4, "CDATA");
    }

    public Namespace createNamespace(String str) {
        return new NamespaceBase(str);
    }

    public Namespace createNamespace(String str, String str2) {
        if (str != null) {
            return new NamespaceBase(str, str2);
        }
        throw new NullPointerException("The prefix of a namespace may not be set to null");
    }

    public StartElement createStartElement(QName qName, Iterator it, Iterator it2) {
        StartElementEvent startElementEvent = new StartElementEvent(qName);
        while (it != null && it.hasNext()) {
            startElementEvent.addAttribute((Attribute) it.next());
        }
        while (it2 != null && it2.hasNext()) {
            startElementEvent.addNamespace((Namespace) it2.next());
        }
        return startElementEvent;
    }

    public StartElement createStartElement(String str, String str2, String str3) {
        return new StartElementEvent(new QName(str2, str3, str));
    }

    public StartElement createStartElement(String str, String str2, String str3, Iterator it, Iterator it2) {
        StartElementEvent startElementEvent = new StartElementEvent(new QName(str2, str3, checkPrefix(str)));
        while (it != null && it.hasNext()) {
            startElementEvent.addAttribute((Attribute) it.next());
        }
        while (it2 != null && it2.hasNext()) {
            startElementEvent.addNamespace((Namespace) it2.next());
        }
        return startElementEvent;
    }

    public StartElement createStartElement(String str, String str2, String str3, Iterator it, Iterator it2, NamespaceContext namespaceContext) {
        StartElementEvent startElementEvent = new StartElementEvent(new QName(str2, str3, checkPrefix(str)));
        while (it != null && it.hasNext()) {
            startElementEvent.addAttribute((Attribute) it.next());
        }
        while (it2 != null && it2.hasNext()) {
            startElementEvent.addNamespace((Namespace) it2.next());
        }
        startElementEvent.setNamespaceContext(namespaceContext);
        return startElementEvent;
    }

    public EndElement createEndElement(QName qName, Iterator it) {
        EndElementEvent endElementEvent = new EndElementEvent(qName);
        while (it != null && it.hasNext()) {
            endElementEvent.addNamespace((Namespace) it.next());
        }
        return endElementEvent;
    }

    public EndElement createEndElement(String str, String str2, String str3) {
        return new EndElementEvent(new QName(str2, str3, checkPrefix(str)));
    }

    public EndElement createEndElement(String str, String str2, String str3, Iterator it) {
        EndElementEvent endElementEvent = new EndElementEvent(new QName(str2, str3, checkPrefix(str)));
        while (it.hasNext()) {
            endElementEvent.addNamespace((Namespace) it.next());
        }
        return endElementEvent;
    }

    public Characters createCharacters(String str) {
        return new CharactersEvent(str);
    }

    public Characters createCData(String str) {
        return new CharactersEvent(str, true);
    }

    public StartDocument createStartDocument() {
        return new StartDocumentEvent();
    }

    public StartDocument createStartDocument(String str, String str2, boolean z) {
        StartDocumentEvent startDocumentEvent = new StartDocumentEvent();
        startDocumentEvent.setEncoding(str);
        startDocumentEvent.setVersion(str2);
        startDocumentEvent.setStandalone(z);
        return startDocumentEvent;
    }

    public StartDocument createStartDocument(String str, String str2) {
        StartDocumentEvent startDocumentEvent = new StartDocumentEvent();
        startDocumentEvent.setEncoding(str);
        startDocumentEvent.setVersion(str2);
        return startDocumentEvent;
    }

    public StartDocument createStartDocument(String str) {
        StartDocumentEvent startDocumentEvent = new StartDocumentEvent();
        startDocumentEvent.setEncoding(str);
        return startDocumentEvent;
    }

    public EndDocument createEndDocument() {
        return new EndDocumentEvent();
    }

    public EntityReference createEntityReference(String str, EntityDeclaration entityDeclaration) {
        return new EntityReferenceEvent(str, entityDeclaration);
    }

    public Characters createSpace(String str) {
        CharactersEvent charactersEvent = new CharactersEvent(str);
        charactersEvent.setSpace(true);
        return charactersEvent;
    }

    public Characters createIgnorableSpace(String str) {
        CharactersEvent charactersEvent = new CharactersEvent(str);
        charactersEvent.setSpace(true);
        charactersEvent.setIgnorable(true);
        return charactersEvent;
    }

    public Comment createComment(String str) {
        return new CommentEvent(str);
    }

    public ProcessingInstruction createProcessingInstruction(String str, String str2) {
        return new ProcessingInstructionEvent(str, str2);
    }

    public DTD createDTD(String str) {
        return new DTDEvent(str);
    }
}
