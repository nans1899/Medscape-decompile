package org.xmlpull.v1.wrapper.classic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.xmlpull.v1.XmlSerializer;

public class XmlSerializerDelegate implements XmlSerializer {
    protected XmlSerializer xs;

    public XmlSerializerDelegate(XmlSerializer xmlSerializer) {
        this.xs = xmlSerializer;
    }

    public String getName() {
        return this.xs.getName();
    }

    public void setPrefix(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.setPrefix(str, str2);
    }

    public void setOutput(OutputStream outputStream, String str) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.setOutput(outputStream, str);
    }

    public void endDocument() throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.endDocument();
    }

    public void comment(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.comment(str);
    }

    public int getDepth() {
        return this.xs.getDepth();
    }

    public void setProperty(String str, Object obj) throws IllegalArgumentException, IllegalStateException {
        this.xs.setProperty(str, obj);
    }

    public void cdsect(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.cdsect(str);
    }

    public void setFeature(String str, boolean z) throws IllegalArgumentException, IllegalStateException {
        this.xs.setFeature(str, z);
    }

    public void entityRef(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.entityRef(str);
    }

    public void processingInstruction(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.processingInstruction(str);
    }

    public void setOutput(Writer writer) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.setOutput(writer);
    }

    public void docdecl(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.docdecl(str);
    }

    public void flush() throws IOException {
        this.xs.flush();
    }

    public Object getProperty(String str) {
        return this.xs.getProperty(str);
    }

    public XmlSerializer startTag(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        return this.xs.startTag(str, str2);
    }

    public void ignorableWhitespace(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.ignorableWhitespace(str);
    }

    public XmlSerializer text(String str) throws IOException, IllegalArgumentException, IllegalStateException {
        return this.xs.text(str);
    }

    public boolean getFeature(String str) {
        return this.xs.getFeature(str);
    }

    public XmlSerializer attribute(String str, String str2, String str3) throws IOException, IllegalArgumentException, IllegalStateException {
        return this.xs.attribute(str, str2, str3);
    }

    public void startDocument(String str, Boolean bool) throws IOException, IllegalArgumentException, IllegalStateException {
        this.xs.startDocument(str, bool);
    }

    public String getPrefix(String str, boolean z) throws IllegalArgumentException {
        return this.xs.getPrefix(str, z);
    }

    public String getNamespace() {
        return this.xs.getNamespace();
    }

    public XmlSerializer endTag(String str, String str2) throws IOException, IllegalArgumentException, IllegalStateException {
        return this.xs.endTag(str, str2);
    }

    public XmlSerializer text(char[] cArr, int i, int i2) throws IOException, IllegalArgumentException, IllegalStateException {
        return this.xs.text(cArr, i, i2);
    }
}
