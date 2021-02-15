package com.bea.xml.stream;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;

public class XMLOutputFactoryBase extends XMLOutputFactory {
    ConfigurationContextBase config = new ConfigurationContextBase();

    public XMLStreamWriter createXMLStreamWriter(Writer writer) throws XMLStreamException {
        XMLWriterBase xMLWriterBase = new XMLWriterBase(writer);
        xMLWriterBase.setConfigurationContext(this.config);
        return xMLWriterBase;
    }

    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream) throws XMLStreamException {
        return createXMLStreamWriter((Writer) new BufferedWriter(new OutputStreamWriter(outputStream), 500));
    }

    public XMLStreamWriter createXMLStreamWriter(OutputStream outputStream, String str) throws XMLStreamException {
        try {
            return createXMLStreamWriter((Writer) new BufferedWriter(new OutputStreamWriter(outputStream, str), 500));
        } catch (UnsupportedEncodingException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unsupported encoding ");
            stringBuffer.append(str);
            throw new XMLStreamException(stringBuffer.toString(), (Throwable) e);
        }
    }

    public XMLEventWriter createXMLEventWriter(OutputStream outputStream) throws XMLStreamException {
        return new XMLEventWriterBase(createXMLStreamWriter(outputStream));
    }

    public XMLEventWriter createXMLEventWriter(Writer writer) throws XMLStreamException {
        return new XMLEventWriterBase(createXMLStreamWriter(writer));
    }

    public XMLEventWriter createXMLEventWriter(OutputStream outputStream, String str) throws XMLStreamException {
        return new XMLEventWriterBase(createXMLStreamWriter(outputStream, str));
    }

    public void setProperty(String str, Object obj) {
        this.config.setProperty(str, obj);
    }

    public Object getProperty(String str) {
        return this.config.getProperty(str);
    }

    public boolean isPrefixDefaulting() {
        return this.config.isPrefixDefaulting();
    }

    public void setPrefixDefaulting(boolean z) {
        this.config.setPrefixDefaulting(z);
    }

    public boolean isPropertySupported(String str) {
        return this.config.isPropertySupported(str);
    }

    public XMLStreamWriter createXMLStreamWriter(Result result) throws XMLStreamException {
        throw new UnsupportedOperationException();
    }

    public XMLEventWriter createXMLEventWriter(Result result) throws XMLStreamException {
        throw new UnsupportedOperationException();
    }
}
