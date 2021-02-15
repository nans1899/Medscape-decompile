package com.bea.xml.stream.filters;

import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class TypeFilter implements EventFilter, StreamFilter {
    protected boolean[] types = new boolean[20];

    public void addType(int i) {
        this.types[i] = true;
    }

    public boolean accept(XMLEvent xMLEvent) {
        return this.types[xMLEvent.getEventType()];
    }

    public boolean accept(XMLStreamReader xMLStreamReader) {
        return this.types[xMLStreamReader.getEventType()];
    }
}
