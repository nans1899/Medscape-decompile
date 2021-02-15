package org.dom4j.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import org.dom4j.Document;
import org.xml.sax.InputSource;

class DocumentInputSource extends InputSource {
    private Document document;

    public DocumentInputSource() {
    }

    public DocumentInputSource(Document document2) {
        this.document = document2;
        setSystemId(document2.getName());
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document2) {
        this.document = document2;
        setSystemId(document2.getName());
    }

    public void setCharacterStream(Reader reader) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public Reader getCharacterStream() {
        try {
            StringWriter stringWriter = new StringWriter();
            XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter);
            xMLWriter.write(this.document);
            xMLWriter.flush();
            return new StringReader(stringWriter.toString());
        } catch (IOException e) {
            return new Reader() {
                public void close() throws IOException {
                }

                public int read(char[] cArr, int i, int i2) throws IOException {
                    throw e;
                }
            };
        }
    }
}
