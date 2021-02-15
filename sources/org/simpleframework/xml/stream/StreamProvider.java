package org.simpleframework.xml.stream;

import java.io.InputStream;
import java.io.Reader;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

class StreamProvider implements Provider {
    private final XMLInputFactory factory = XMLInputFactory.newInstance();

    public EventReader provide(InputStream inputStream) throws Exception {
        return provide(this.factory.createXMLEventReader(inputStream));
    }

    public EventReader provide(Reader reader) throws Exception {
        return provide(this.factory.createXMLEventReader(reader));
    }

    private EventReader provide(XMLEventReader xMLEventReader) throws Exception {
        return new StreamReader(xMLEventReader);
    }
}
