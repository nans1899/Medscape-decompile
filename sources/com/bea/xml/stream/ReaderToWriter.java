package com.bea.xml.stream;

import java.io.FileReader;
import java.io.OutputStream;
import java.io.Reader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class ReaderToWriter {
    private XMLStreamWriter writer;

    public ReaderToWriter() {
    }

    public ReaderToWriter(XMLStreamWriter xMLStreamWriter) {
        this.writer = xMLStreamWriter;
    }

    public void setStreamWriter(XMLStreamWriter xMLStreamWriter) {
        this.writer = xMLStreamWriter;
    }

    public void write(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        System.out.println("wrote event");
        switch (xMLStreamReader.getEventType()) {
            case 1:
                String prefix = xMLStreamReader.getPrefix();
                if (xMLStreamReader.getNamespaceURI() == null) {
                    this.writer.writeStartElement(xMLStreamReader.getLocalName());
                } else if (prefix != null) {
                    this.writer.writeStartElement(xMLStreamReader.getPrefix(), xMLStreamReader.getLocalName(), xMLStreamReader.getNamespaceURI());
                } else {
                    this.writer.writeStartElement(xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName());
                }
                for (int i = 0; i < xMLStreamReader.getNamespaceCount(); i++) {
                    this.writer.writeNamespace(xMLStreamReader.getNamespacePrefix(i), xMLStreamReader.getNamespaceURI(i));
                }
                return;
            case 2:
                this.writer.writeEndElement();
                return;
            case 3:
                this.writer.writeProcessingInstruction(xMLStreamReader.getPITarget(), xMLStreamReader.getPIData());
                return;
            case 4:
            case 6:
                this.writer.writeCharacters(xMLStreamReader.getTextCharacters(), xMLStreamReader.getTextStart(), xMLStreamReader.getTextLength());
                return;
            case 5:
                this.writer.writeComment(xMLStreamReader.getText());
                return;
            case 7:
                String characterEncodingScheme = xMLStreamReader.getCharacterEncodingScheme();
                String version = xMLStreamReader.getVersion();
                if (characterEncodingScheme != null && version != null) {
                    this.writer.writeStartDocument(characterEncodingScheme, version);
                    return;
                } else if (version != null) {
                    this.writer.writeStartDocument(xMLStreamReader.getVersion());
                    return;
                } else {
                    return;
                }
            case 8:
                this.writer.writeEndDocument();
                return;
            case 9:
                this.writer.writeEntityRef(xMLStreamReader.getLocalName());
                return;
            case 11:
                this.writer.writeDTD(xMLStreamReader.getText());
                return;
            case 12:
                this.writer.writeCData(xMLStreamReader.getText());
                return;
            default:
                return;
        }
    }

    public XMLStreamWriter writeAll(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        while (xMLStreamReader.hasNext()) {
            write(xMLStreamReader);
            xMLStreamReader.next();
        }
        this.writer.flush();
        return this.writer;
    }

    public static void main(String[] strArr) throws Exception {
        XMLInputFactory newInstance = XMLInputFactory.newInstance();
        XMLOutputFactory newInstance2 = XMLOutputFactory.newInstance();
        XMLStreamReader createXMLStreamReader = newInstance.createXMLStreamReader((Reader) new FileReader(strArr[0]));
        XMLStreamWriter createXMLStreamWriter = newInstance2.createXMLStreamWriter((OutputStream) System.out);
        ReaderToWriter readerToWriter = new ReaderToWriter(createXMLStreamWriter);
        while (createXMLStreamReader.hasNext()) {
            readerToWriter.write(createXMLStreamReader);
            createXMLStreamReader.next();
        }
        createXMLStreamWriter.flush();
    }
}
