package com.bea.xml.stream;

import com.appboy.Constants;
import com.bea.xml.stream.util.NamespaceContextImpl;
import com.bea.xml.stream.util.Stack;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XMLWriterBase extends ReaderToWriter implements XMLStreamWriter {
    protected static final String DEFAULTNS = "";
    private ConfigurationContextBase config;
    protected NamespaceContextImpl context = new NamespaceContextImpl();
    private int defaultPrefixCount = 0;
    private CharsetEncoder encoder;
    private boolean isEmpty = false;
    private boolean isPrefixDefaulting;
    private Stack localNameStack = new Stack();
    private HashSet needToWrite;
    private Stack prefixStack = new Stack();
    private HashSet setNeedsWritingNs = new HashSet();
    private boolean startElementOpened = false;
    private Stack uriStack = new Stack();
    private Writer writer;

    public XMLWriterBase() {
    }

    public XMLWriterBase(Writer writer2) {
        this.writer = writer2;
        setWriter(writer2);
    }

    public void setWriter(Writer writer2) {
        this.writer = writer2;
        setStreamWriter(this);
        if (writer2 instanceof OutputStreamWriter) {
            this.encoder = Charset.forName(((OutputStreamWriter) writer2).getEncoding()).newEncoder();
        } else {
            this.encoder = null;
        }
    }

    public void setConfigurationContext(ConfigurationContextBase configurationContextBase) {
        this.config = configurationContextBase;
        this.isPrefixDefaulting = configurationContextBase.isPrefixDefaulting();
    }

    /* access modifiers changed from: protected */
    public void write(String str) throws XMLStreamException {
        try {
            this.writer.write(str);
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public void write(char c) throws XMLStreamException {
        try {
            this.writer.write(c);
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public void write(char[] cArr) throws XMLStreamException {
        try {
            this.writer.write(cArr);
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public void write(char[] cArr, int i, int i2) throws XMLStreamException {
        try {
            this.writer.write(cArr, i, i2);
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    public void writeCharactersInternal(char[] cArr, int i, int i2, boolean z) throws XMLStreamException {
        CharsetEncoder charsetEncoder;
        if (i2 != 0) {
            int i3 = 0;
            while (i3 < i2) {
                char c = cArr[i3 + i];
                if (c == '\"') {
                    if (z) {
                        break;
                    }
                } else if (c != '&' && c != '<' && c != '>') {
                    if (c >= ' ') {
                        if (c > 127 && (charsetEncoder = this.encoder) != null && !charsetEncoder.canEncode(c)) {
                            break;
                        }
                    } else if (!z) {
                        if (!(c == 9 || c == 10)) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
                i3++;
            }
            if (i3 < i2) {
                slowWriteCharacters(cArr, i, i2, z);
            } else {
                write(cArr, i, i2);
            }
        }
    }

    private void slowWriteCharacters(char[] cArr, int i, int i2, boolean z) throws XMLStreamException {
        CharsetEncoder charsetEncoder;
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i3 + i];
            if (c != '\"') {
                if (c == '&') {
                    write("&amp;");
                } else if (c == '<') {
                    write("&lt;");
                } else if (c == '>') {
                    write("&gt;");
                } else if (c < ' ') {
                    if (z || !(c == 9 || c == 10)) {
                        write("&#");
                        write(Integer.toString(c));
                        write(';');
                    }
                } else if (c > 127 && (charsetEncoder = this.encoder) != null && !charsetEncoder.canEncode(c)) {
                    write("&#");
                    write(Integer.toString(c));
                    write(';');
                }
            } else if (z) {
                write("&quot;");
            }
            write(c);
        }
    }

    /* access modifiers changed from: protected */
    public void closeStartElement() throws XMLStreamException {
        if (this.startElementOpened) {
            closeStartTag();
            this.startElementOpened = false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isOpen() {
        return this.startElementOpened;
    }

    /* access modifiers changed from: protected */
    public void closeStartTag() throws XMLStreamException {
        flushNamespace();
        clearNeedsWritingNs();
        if (this.isEmpty) {
            write("/>");
            this.isEmpty = false;
            return;
        }
        write(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
    }

    private void openStartElement() throws XMLStreamException {
        if (this.startElementOpened) {
            closeStartTag();
        } else {
            this.startElementOpened = true;
        }
    }

    /* access modifiers changed from: protected */
    public String writeName(String str, String str2, String str3) throws XMLStreamException {
        if (!"".equals(str2)) {
            str = getPrefixInternal(str2);
        }
        if (!"".equals(str)) {
            write(str);
            write(":");
        }
        write(str3);
        return str;
    }

    private String getPrefixInternal(String str) {
        String prefix = this.context.getPrefix(str);
        return prefix == null ? "" : prefix;
    }

    /* access modifiers changed from: protected */
    public String getURIInternal(String str) {
        String namespaceURI = this.context.getNamespaceURI(str);
        return namespaceURI == null ? "" : namespaceURI;
    }

    /* access modifiers changed from: protected */
    public void openStartTag() throws XMLStreamException {
        write(HtmlObject.HtmlMarkUp.OPEN_BRACKER);
    }

    private boolean needToWrite(String str) {
        if (this.needToWrite == null) {
            this.needToWrite = new HashSet();
        }
        boolean contains = this.needToWrite.contains(str);
        this.needToWrite.add(str);
        return contains;
    }

    private void prepareNamespace(String str) throws XMLStreamException {
        if (this.isPrefixDefaulting && !"".equals(str) && getPrefix(str) == null) {
            this.defaultPrefixCount++;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("ns");
            stringBuffer.append(this.defaultPrefixCount);
            setPrefix(stringBuffer.toString(), str);
        }
    }

    private void removeNamespace(String str) {
        HashSet hashSet;
        if (this.isPrefixDefaulting && (hashSet = this.needToWrite) != null) {
            hashSet.remove(str);
        }
    }

    private void flushNamespace() throws XMLStreamException {
        HashSet hashSet;
        if (this.isPrefixDefaulting && (hashSet = this.needToWrite) != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                String prefix = this.context.getPrefix(str);
                if (prefix != null) {
                    writeNamespace(prefix, str);
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Unable to default prefix with uri:");
                    stringBuffer.append(str);
                    throw new XMLStreamException(stringBuffer.toString());
                }
            }
            this.needToWrite.clear();
        }
    }

    /* access modifiers changed from: protected */
    public void writeStartElementInternal(String str, String str2) throws XMLStreamException {
        if (str == null) {
            throw new IllegalArgumentException("The namespace URI may not be null");
        } else if (str2 != null) {
            openStartElement();
            openStartTag();
            prepareNamespace(str);
            this.prefixStack.push(writeName("", str, str2));
            this.localNameStack.push(str2);
            this.uriStack.push(str);
        } else {
            throw new IllegalArgumentException("The local name  may not be null");
        }
    }

    public void writeStartElement(String str, String str2) throws XMLStreamException {
        this.context.openScope();
        writeStartElementInternal(str, str2);
    }

    public void writeStartElement(String str, String str2, String str3) throws XMLStreamException {
        if (str3 == null) {
            throw new IllegalArgumentException("The namespace URI may not be null");
        } else if (str2 == null) {
            throw new IllegalArgumentException("The local name may not be null");
        } else if (str != null) {
            this.context.openScope();
            prepareNamespace(str3);
            this.context.bindNamespace(str, str3);
            writeStartElementInternal(str3, str2);
        } else {
            throw new IllegalArgumentException("The prefix may not be null");
        }
    }

    public void writeStartElement(String str) throws XMLStreamException {
        this.context.openScope();
        writeStartElement("", str);
    }

    public void writeEmptyElement(String str, String str2) throws XMLStreamException {
        openStartElement();
        prepareNamespace(str);
        this.isEmpty = true;
        write(HtmlObject.HtmlMarkUp.OPEN_BRACKER);
        writeName("", str, str2);
    }

    public void writeEmptyElement(String str, String str2, String str3) throws XMLStreamException {
        openStartElement();
        prepareNamespace(str3);
        this.isEmpty = true;
        write(HtmlObject.HtmlMarkUp.OPEN_BRACKER);
        write(str);
        write(":");
        write(str2);
    }

    public void writeEmptyElement(String str) throws XMLStreamException {
        writeEmptyElement("", str);
    }

    /* access modifiers changed from: protected */
    public void openEndTag() throws XMLStreamException {
        write("</");
    }

    /* access modifiers changed from: protected */
    public void closeEndTag() throws XMLStreamException {
        write(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
    }

    public void writeEndElement() throws XMLStreamException {
        if (isOpen()) {
            closeStartElement();
        }
        this.uriStack.pop();
        openEndTag();
        writeName((String) this.prefixStack.pop(), "", (String) this.localNameStack.pop());
        closeEndTag();
        this.context.closeScope();
    }

    public void writeRaw(String str) throws XMLStreamException {
        closeStartElement();
        write(str);
    }

    public void close() throws XMLStreamException {
        flush();
    }

    public void flush() throws XMLStreamException {
        try {
            this.writer.flush();
        } catch (IOException e) {
            throw new XMLStreamException((Throwable) e);
        }
    }

    public void writeEndDocument() throws XMLStreamException {
        while (!this.localNameStack.isEmpty()) {
            writeEndElement();
        }
    }

    public void writeAttribute(String str, String str2) throws XMLStreamException {
        writeAttribute("", str, str2);
    }

    public void writeAttribute(String str, String str2, String str3) throws XMLStreamException {
        if (isOpen()) {
            prepareNamespace(str);
            write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            writeName("", str, str2);
            write("=\"");
            writeCharactersInternal(str3.toCharArray(), 0, str3.length(), true);
            write("\"");
            return;
        }
        throw new XMLStreamException("A start element must be written before an attribute");
    }

    public void writeAttribute(String str, String str2, String str3, String str4) throws XMLStreamException {
        if (isOpen()) {
            prepareNamespace(str2);
            this.context.bindNamespace(str, str2);
            write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            writeName(str, str2, str3);
            write("=\"");
            writeCharactersInternal(str4.toCharArray(), 0, str4.length(), true);
            write("\"");
            return;
        }
        throw new XMLStreamException("A start element must be written before an attribute");
    }

    public void writeNamespace(String str, String str2) throws XMLStreamException {
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before a namespace");
        } else if (str == null || "".equals(str) || XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
            writeDefaultNamespace(str2);
        } else if (needsWritingNs(str)) {
            write(" xmlns:");
            write(str);
            write("=\"");
            write(str2);
            write("\"");
            setPrefix(str, str2);
        }
    }

    private void clearNeedsWritingNs() {
        this.setNeedsWritingNs.clear();
    }

    private boolean needsWritingNs(String str) {
        boolean z = !this.setNeedsWritingNs.contains(str);
        if (z) {
            this.setNeedsWritingNs.add(str);
        }
        return z;
    }

    public void writeDefaultNamespace(String str) throws XMLStreamException {
        if (!isOpen()) {
            throw new XMLStreamException("A start element must be written before the default namespace");
        } else if (needsWritingNs("")) {
            write(" xmlns");
            write("=\"");
            write(str);
            write("\"");
            setPrefix("", str);
        }
    }

    public void writeComment(String str) throws XMLStreamException {
        closeStartElement();
        write("<!--");
        if (str != null) {
            write(str);
        }
        write("-->");
    }

    public void writeProcessingInstruction(String str) throws XMLStreamException {
        closeStartElement();
        writeProcessingInstruction(str, (String) null);
    }

    public void writeProcessingInstruction(String str, String str2) throws XMLStreamException {
        closeStartElement();
        write("<?");
        if (str != null) {
            write(str);
        }
        if (str2 != null) {
            write(' ');
            write(str2);
        }
        write("?>");
    }

    public void writeDTD(String str) throws XMLStreamException {
        write(str);
    }

    public void writeCData(String str) throws XMLStreamException {
        closeStartElement();
        write("<![CDATA[");
        if (str != null) {
            write(str);
        }
        write("]]>");
    }

    public void writeEntityRef(String str) throws XMLStreamException {
        closeStartElement();
        write("&");
        write(str);
        write(";");
    }

    public void writeStartDocument() throws XMLStreamException {
        write("<?xml version='1.0' encoding='utf-8'?>");
    }

    public void writeStartDocument(String str) throws XMLStreamException {
        write("<?xml version='");
        write(str);
        write("'?>");
    }

    public void writeStartDocument(String str, String str2) throws XMLStreamException {
        write("<?xml version='");
        write(str2);
        write("' encoding='");
        write(str);
        write("'?>");
    }

    public void writeCharacters(String str) throws XMLStreamException {
        closeStartElement();
        writeCharactersInternal(str.toCharArray(), 0, str.length(), false);
    }

    public void writeCharacters(char[] cArr, int i, int i2) throws XMLStreamException {
        closeStartElement();
        writeCharactersInternal(cArr, i, i2, false);
    }

    public String getPrefix(String str) throws XMLStreamException {
        return this.context.getPrefix(str);
    }

    public void setPrefix(String str, String str2) throws XMLStreamException {
        needToWrite(str2);
        this.context.bindNamespace(str, str2);
    }

    public void setDefaultNamespace(String str) throws XMLStreamException {
        needToWrite(str);
        this.context.bindDefaultNameSpace(str);
    }

    public void setNamespaceContext(NamespaceContext namespaceContext) throws XMLStreamException {
        if (namespaceContext != null) {
            this.context = new NamespaceContextImpl(namespaceContext);
            return;
        }
        throw new NullPointerException("The namespace  context may not be null.");
    }

    public NamespaceContext getNamespaceContext() {
        return this.context;
    }

    public Object getProperty(String str) throws IllegalArgumentException {
        return this.config.getProperty(str);
    }

    public static void main(String[] strArr) throws Exception {
        XMLOutputFactory newInstance = XMLOutputFactoryBase.newInstance();
        newInstance.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, new Boolean(true));
        XMLStreamWriter createXMLStreamWriter = newInstance.createXMLStreamWriter((Writer) new OutputStreamWriter(new FileOutputStream("tmp"), "us-ascii"));
        createXMLStreamWriter.writeStartDocument();
        createXMLStreamWriter.setPrefix("c", "http://c");
        createXMLStreamWriter.setDefaultNamespace("http://d");
        createXMLStreamWriter.writeStartElement("http://c", Constants.APPBOY_PUSH_CONTENT_KEY);
        createXMLStreamWriter.writeAttribute("b", "blah");
        createXMLStreamWriter.writeEmptyElement("http://c", Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE);
        createXMLStreamWriter.writeEmptyElement("http://d", "e");
        createXMLStreamWriter.writeEmptyElement("http://e", "f");
        createXMLStreamWriter.writeEmptyElement("http://f", "g");
        createXMLStreamWriter.writeAttribute("http://c", "chris", "fry");
        createXMLStreamWriter.writeCharacters("foo bar foo");
        createXMLStreamWriter.writeCharacters("bad char coming[");
        createXMLStreamWriter.writeCharacters("$");
        createXMLStreamWriter.writeCharacters("]");
        createXMLStreamWriter.writeEndElement();
        createXMLStreamWriter.flush();
    }
}
