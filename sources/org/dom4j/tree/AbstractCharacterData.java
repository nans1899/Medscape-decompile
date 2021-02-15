package org.dom4j.tree;

import org.dom4j.CharacterData;
import org.dom4j.Element;

public abstract class AbstractCharacterData extends AbstractNode implements CharacterData {
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

    public void appendText(String str) {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(getText()));
        stringBuffer.append(str);
        setText(stringBuffer.toString());
    }
}
