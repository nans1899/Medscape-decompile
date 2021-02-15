package org.dom4j.jaxb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import javax.xml.bind.Element;
import javax.xml.bind.JAXBException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

public class JAXBWriter extends JAXBSupport {
    private OutputFormat outputFormat;
    private XMLWriter xmlWriter;

    public JAXBWriter(String str) {
        super(str);
        this.outputFormat = new OutputFormat();
    }

    public JAXBWriter(String str, OutputFormat outputFormat2) {
        super(str);
        this.outputFormat = outputFormat2;
    }

    public JAXBWriter(String str, ClassLoader classLoader) {
        super(str, classLoader);
    }

    public JAXBWriter(String str, ClassLoader classLoader, OutputFormat outputFormat2) {
        super(str, classLoader);
        this.outputFormat = outputFormat2;
    }

    public OutputFormat getOutputFormat() {
        return this.outputFormat;
    }

    public void setOutput(File file) throws IOException {
        getWriter().setOutputStream(new FileOutputStream(file));
    }

    public void setOutput(OutputStream outputStream) throws IOException {
        getWriter().setOutputStream(outputStream);
    }

    public void setOutput(Writer writer) throws IOException {
        getWriter().setWriter(writer);
    }

    public void startDocument() throws IOException, SAXException {
        getWriter().startDocument();
    }

    public void endDocument() throws IOException, SAXException {
        getWriter().endDocument();
    }

    public void write(Element element) throws IOException, JAXBException {
        getWriter().write(marshal(element));
    }

    public void writeClose(Element element) throws IOException, JAXBException {
        getWriter().writeClose(marshal(element));
    }

    public void writeOpen(Element element) throws IOException, JAXBException {
        getWriter().writeOpen(marshal(element));
    }

    public void writeElement(org.dom4j.Element element) throws IOException {
        getWriter().write(element);
    }

    public void writeCloseElement(org.dom4j.Element element) throws IOException {
        getWriter().writeClose(element);
    }

    public void writeOpenElement(org.dom4j.Element element) throws IOException {
        getWriter().writeOpen(element);
    }

    private XMLWriter getWriter() throws IOException {
        if (this.xmlWriter == null) {
            if (this.outputFormat != null) {
                this.xmlWriter = new XMLWriter(this.outputFormat);
            } else {
                this.xmlWriter = new XMLWriter();
            }
        }
        return this.xmlWriter;
    }
}
