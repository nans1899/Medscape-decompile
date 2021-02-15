package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Text;
import org.dom4j.Visitor;

public abstract class AbstractText extends AbstractCharacterData implements Text {
    public short getNodeType() {
        return 3;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append(" [Text: \"");
        stringBuffer.append(getText());
        stringBuffer.append("\"]");
        return stringBuffer.toString();
    }

    public String asXML() {
        return getText();
    }

    public void write(Writer writer) throws IOException {
        writer.write(getText());
    }

    public void accept(Visitor visitor) {
        visitor.visit((Text) this);
    }
}
