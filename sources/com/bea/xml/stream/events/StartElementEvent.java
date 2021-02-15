package com.bea.xml.stream.events;

import com.bea.xml.stream.util.EmptyIterator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StartElementEvent extends NamedEvent implements StartElement {
    private List attributes;
    private NamespaceContext context;
    private List namespaces;

    public StartElementEvent() {
    }

    public StartElementEvent(QName qName) {
        super(qName);
        init();
    }

    public void reset() {
        List list = this.attributes;
        if (list != null) {
            list.clear();
        }
        List list2 = this.namespaces;
        if (list2 != null) {
            list2.clear();
        }
        if (this.context != null) {
            this.context = null;
        }
    }

    public StartElementEvent(StartElement startElement) {
        super(startElement.getName());
        init();
        setName(startElement.getName());
        Iterator attributes2 = startElement.getAttributes();
        while (attributes2.hasNext()) {
            addAttribute((Attribute) attributes2.next());
        }
        startElement.getNamespaces();
        Iterator namespaces2 = startElement.getNamespaces();
        while (namespaces2.hasNext()) {
            addNamespace((Namespace) namespaces2.next());
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(1);
    }

    public Iterator getAttributes() {
        List list = this.attributes;
        if (list == null) {
            return EmptyIterator.emptyIterator;
        }
        return list.iterator();
    }

    public Iterator getNamespaces() {
        List list = this.namespaces;
        if (list == null) {
            return EmptyIterator.emptyIterator;
        }
        return list.iterator();
    }

    public Attribute getAttributeByName(QName qName) {
        if (qName == null) {
            return null;
        }
        Iterator attributes2 = getAttributes();
        while (attributes2.hasNext()) {
            Attribute attribute = (Attribute) attributes2.next();
            if (attribute.getName().equals(qName)) {
                return attribute;
            }
        }
        return null;
    }

    public void setAttributes(List list) {
        this.attributes = list;
    }

    public void addAttribute(Attribute attribute) {
        if (this.attributes == null) {
            this.attributes = new ArrayList();
        }
        this.attributes.add(attribute);
    }

    public void addNamespace(Namespace namespace) {
        if (this.namespaces == null) {
            this.namespaces = new ArrayList();
        }
        this.namespaces.add(namespace);
    }

    public String getNamespaceURI(String str) {
        NamespaceContext namespaceContext = this.context;
        if (namespaceContext == null) {
            return null;
        }
        return namespaceContext.getNamespaceURI(str);
    }

    public void setNamespaceContext(NamespaceContext namespaceContext) {
        this.context = namespaceContext;
    }

    public NamespaceContext getNamespaceContext() {
        return this.context;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(HtmlObject.HtmlMarkUp.OPEN_BRACKER);
        stringBuffer.append(nameAsString());
        String stringBuffer2 = stringBuffer.toString();
        Iterator attributes2 = getAttributes();
        while (attributes2.hasNext()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(stringBuffer2);
            stringBuffer3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer3.append(attributes2.next().toString());
            stringBuffer2 = stringBuffer3.toString();
        }
        Iterator namespaces2 = getNamespaces();
        while (namespaces2.hasNext()) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append(stringBuffer2);
            stringBuffer4.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer4.append(namespaces2.next().toString());
            stringBuffer2 = stringBuffer4.toString();
        }
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append(stringBuffer2);
        stringBuffer5.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        return stringBuffer5.toString();
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException, XMLStreamException {
        writer.write(60);
        QName name = getName();
        String prefix = name.getPrefix();
        if (prefix != null && prefix.length() > 0) {
            writer.write(prefix);
            writer.write(58);
        }
        writer.write(name.getLocalPart());
        Iterator namespaces2 = getNamespaces();
        while (namespaces2.hasNext()) {
            writer.write(32);
            ((XMLEvent) namespaces2.next()).writeAsEncodedUnicode(writer);
        }
        Iterator attributes2 = getAttributes();
        while (attributes2.hasNext()) {
            writer.write(32);
            ((XMLEvent) attributes2.next()).writeAsEncodedUnicode(writer);
        }
        writer.write(62);
    }
}
