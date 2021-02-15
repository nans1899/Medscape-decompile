package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.Visitor;

public abstract class AbstractComment extends AbstractCharacterData implements Comment {
    public short getNodeType() {
        return 8;
    }

    public String getPath(Element element) {
        Element parent = getParent();
        if (parent == null || parent == element) {
            return "comment()";
        }
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(parent.getPath(element)));
        stringBuffer.append("/comment()");
        return stringBuffer.toString();
    }

    public String getUniquePath(Element element) {
        Element parent = getParent();
        if (parent == null || parent == element) {
            return "comment()";
        }
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(parent.getUniquePath(element)));
        stringBuffer.append("/comment()");
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append(" [Comment: \"");
        stringBuffer.append(getText());
        stringBuffer.append("\"]");
        return stringBuffer.toString();
    }

    public String asXML() {
        StringBuffer stringBuffer = new StringBuffer("<!--");
        stringBuffer.append(getText());
        stringBuffer.append("-->");
        return stringBuffer.toString();
    }

    public void write(Writer writer) throws IOException {
        writer.write("<!--");
        writer.write(getText());
        writer.write("-->");
    }

    public void accept(Visitor visitor) {
        visitor.visit((Comment) this);
    }
}
