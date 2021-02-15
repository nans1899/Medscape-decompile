package com.bea.xml.stream.filters;

import javax.xml.namespace.QName;
import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class NameFilter implements EventFilter, StreamFilter {
    private QName name;

    public NameFilter(QName qName) {
        this.name = qName;
    }

    public boolean accept(XMLEvent xMLEvent) {
        QName qName;
        if (!xMLEvent.isStartElement() && !xMLEvent.isEndElement()) {
            return false;
        }
        if (xMLEvent.isStartElement()) {
            qName = ((StartElement) xMLEvent).getName();
        } else {
            qName = ((EndElement) xMLEvent).getName();
        }
        if (this.name.equals(qName)) {
            return true;
        }
        return false;
    }

    public boolean accept(XMLStreamReader xMLStreamReader) {
        if (!xMLStreamReader.isStartElement() && !xMLStreamReader.isEndElement()) {
            return false;
        }
        if (this.name.equals(new QName(xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName()))) {
            return true;
        }
        return false;
    }
}
