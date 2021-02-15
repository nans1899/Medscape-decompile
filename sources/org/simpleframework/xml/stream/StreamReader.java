package org.simpleframework.xml.stream;

import java.util.Iterator;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

class StreamReader implements EventReader {
    private EventNode peek;
    private XMLEventReader reader;

    public StreamReader(XMLEventReader xMLEventReader) {
        this.reader = xMLEventReader;
    }

    public EventNode peek() throws Exception {
        if (this.peek == null) {
            this.peek = next();
        }
        return this.peek;
    }

    public EventNode next() throws Exception {
        EventNode eventNode = this.peek;
        if (eventNode == null) {
            return read();
        }
        this.peek = null;
        return eventNode;
    }

    private EventNode read() throws Exception {
        XMLEvent nextEvent = this.reader.nextEvent();
        if (nextEvent.isEndDocument()) {
            return null;
        }
        if (nextEvent.isStartElement()) {
            return start(nextEvent);
        }
        if (nextEvent.isCharacters()) {
            return text(nextEvent);
        }
        if (nextEvent.isEndElement()) {
            return end();
        }
        return read();
    }

    private Start start(XMLEvent xMLEvent) {
        Start start = new Start(xMLEvent);
        return start.isEmpty() ? build(start) : start;
    }

    private Start build(Start start) {
        Iterator<Attribute> attributes = start.getAttributes();
        while (attributes.hasNext()) {
            Entry attribute = attribute(attributes.next());
            if (!attribute.isReserved()) {
                start.add(attribute);
            }
        }
        return start;
    }

    private Entry attribute(Attribute attribute) {
        return new Entry(attribute);
    }

    private Text text(XMLEvent xMLEvent) {
        return new Text(xMLEvent);
    }

    private End end() {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final Attribute entry;

        public boolean isReserved() {
            return false;
        }

        public Entry(Attribute attribute) {
            this.entry = attribute;
        }

        public String getName() {
            return this.entry.getName().getLocalPart();
        }

        public String getPrefix() {
            return this.entry.getName().getPrefix();
        }

        public String getReference() {
            return this.entry.getName().getNamespaceURI();
        }

        public String getValue() {
            return this.entry.getValue();
        }

        public Object getSource() {
            return this.entry;
        }
    }

    private static class Start extends EventElement {
        private final StartElement element;
        private final Location location;

        public Start(XMLEvent xMLEvent) {
            this.element = xMLEvent.asStartElement();
            this.location = xMLEvent.getLocation();
        }

        public int getLine() {
            return this.location.getLineNumber();
        }

        public String getName() {
            return this.element.getName().getLocalPart();
        }

        public String getPrefix() {
            return this.element.getName().getPrefix();
        }

        public String getReference() {
            return this.element.getName().getNamespaceURI();
        }

        public Iterator<Attribute> getAttributes() {
            return this.element.getAttributes();
        }

        public Object getSource() {
            return this.element;
        }
    }

    private static class Text extends EventToken {
        private final Characters text;

        public boolean isText() {
            return true;
        }

        public Text(XMLEvent xMLEvent) {
            this.text = xMLEvent.asCharacters();
        }

        public String getValue() {
            return this.text.getData();
        }

        public Object getSource() {
            return this.text;
        }
    }

    private static class End extends EventToken {
        public boolean isEnd() {
            return true;
        }

        private End() {
        }
    }
}
