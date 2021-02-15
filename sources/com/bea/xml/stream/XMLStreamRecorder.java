package com.bea.xml.stream;

import com.bea.xml.stream.util.ElementTypeNames;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import net.bytebuddy.pool.TypePool;
import okhttp3.HttpUrl;

public class XMLStreamRecorder extends XMLWriterBase {
    public XMLStreamRecorder() {
    }

    public XMLStreamRecorder(Writer writer) {
        super(writer);
    }

    /* access modifiers changed from: protected */
    public String writeName(String str, String str2, String str3) throws XMLStreamException {
        if (!"".equals(str2)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("['");
            stringBuffer.append(str2);
            stringBuffer.append("':");
            write(stringBuffer.toString());
        } else {
            write("[");
        }
        String writeName = super.writeName(str, str2, str3);
        write(']');
        return writeName;
    }

    /* access modifiers changed from: protected */
    public void writeType(int i) throws XMLStreamException {
        closeStartElement();
        write((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
        write(ElementTypeNames.getEventTypeString(i));
        write(']');
    }

    /* access modifiers changed from: protected */
    public void openStartTag() throws XMLStreamException {
        write((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
    }

    /* access modifiers changed from: protected */
    public void closeStartTag() throws XMLStreamException {
        write("];\n");
    }

    /* access modifiers changed from: protected */
    public void openEndTag() throws XMLStreamException {
        write((char) TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
    }

    /* access modifiers changed from: protected */
    public void closeEndTag() throws XMLStreamException {
        write(']');
    }

    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        write("[[ATTRIBUTE]");
        writeName("", str, str2);
        write("=");
        writeCharactersInternal(str3.toCharArray(), 0, str3.length(), true);
        write("]");
    }

    public void writeNamespace(String str, String str2) throws XMLStreamException {
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before a namespace");
        } else if (str == null || "".equals(str) || XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
            writeDefaultNamespace(str2);
        } else {
            write("[[NAMESPACE][");
            write("xmlns:");
            write(str);
            write("]=[");
            write(str2);
            write("]");
            setPrefix(str, str2);
            write(']');
        }
    }

    public void writeDefaultNamespace(String str) throws XMLStreamException {
        write("[[DEFAULT][");
        if (isOpen()) {
            write("xmlns]");
            write("=[");
            write(str);
            write("]");
            setPrefix("", str);
            write(']');
            return;
        }
        throw new XMLStreamException("A start element must be written before the default namespace");
    }

    public void writeComment(String str) throws XMLStreamException {
        closeStartElement();
        write("[");
        if (str != null) {
            write(str);
        }
        write("]");
    }

    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        closeStartElement();
        write("[");
        if (str != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[");
            stringBuffer.append(str);
            stringBuffer.append("]");
            write(stringBuffer.toString());
        }
        if (str2 != null) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(",[");
            stringBuffer2.append(str2);
            stringBuffer2.append("]");
            write(stringBuffer2.toString());
        }
        write("]");
    }

    public void writeDTD(String str) throws XMLStreamException {
        write("[");
        super.write(str);
        write("]");
    }

    public void writeCData(String str) throws XMLStreamException {
        write("[");
        if (str != null) {
            write(str);
        }
        write("]");
    }

    public void writeEntityRef(String str) throws XMLStreamException {
        write("[");
        super.writeEntityRef(str);
        write("]");
    }

    public void writeStartDocument() throws XMLStreamException {
        write("[[1.0],[utf-8]]");
    }

    public void writeStartDocument(String str) throws XMLStreamException {
        write("[[");
        write(str);
        write("],[utf-8]]");
    }

    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        write("[[");
        write(str2);
        write("],[");
        write(str);
        write("]]");
    }

    /* access modifiers changed from: protected */
    public void writeCharactersInternal(char[] cArr, int i, int i2, boolean z) throws XMLStreamException {
        if (i2 == 0) {
            write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
            return;
        }
        write("[");
        write(cArr, i, i2);
        write("]");
    }

    public void write(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        writeType(xMLStreamReader.getEventType());
        super.write(xMLStreamReader);
        if (!isOpen()) {
            write(";\n");
        }
    }

    public static void main(String[] strArr) throws Exception {
        XMLInputFactory newInstance = XMLInputFactory.newInstance();
        XMLOutputFactory.newInstance();
        XMLStreamReader createXMLStreamReader = newInstance.createXMLStreamReader((Reader) new FileReader(strArr[0]));
        XMLStreamRecorder xMLStreamRecorder = new XMLStreamRecorder(new OutputStreamWriter(new FileOutputStream("out.stream")));
        while (createXMLStreamReader.hasNext()) {
            xMLStreamRecorder.write(createXMLStreamReader);
            createXMLStreamReader.next();
        }
        xMLStreamRecorder.write(createXMLStreamReader);
        xMLStreamRecorder.flush();
    }
}
