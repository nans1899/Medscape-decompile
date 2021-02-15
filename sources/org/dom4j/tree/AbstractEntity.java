package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Visitor;

public abstract class AbstractEntity extends AbstractNode implements Entity {
    public short getNodeType() {
        return 5;
    }

    public String getPath(Element element) {
        Element parent = getParent();
        if (parent == null || parent == element) {
            return "text()";
        }
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(parent.getPath(element)));
        stringBuffer.append("/text()");
        return stringBuffer.toString();
    }

    public String getUniquePath(Element element) {
        Element parent = getParent();
        if (parent == null || parent == element) {
            return "text()";
        }
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(parent.getUniquePath(element)));
        stringBuffer.append("/text()");
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append(" [Entity: &");
        stringBuffer.append(getName());
        stringBuffer.append(";]");
        return stringBuffer.toString();
    }

    public String getStringValue() {
        StringBuffer stringBuffer = new StringBuffer("&");
        stringBuffer.append(getName());
        stringBuffer.append(";");
        return stringBuffer.toString();
    }

    public String asXML() {
        StringBuffer stringBuffer = new StringBuffer("&");
        stringBuffer.append(getName());
        stringBuffer.append(";");
        return stringBuffer.toString();
    }

    public void write(Writer writer) throws IOException {
        writer.write("&");
        writer.write(getName());
        writer.write(";");
    }

    public void accept(Visitor visitor) {
        visitor.visit((Entity) this);
    }
}
