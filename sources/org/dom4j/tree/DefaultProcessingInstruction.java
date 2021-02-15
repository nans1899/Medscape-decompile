package org.dom4j.tree;

import java.util.Map;
import org.dom4j.Element;

public class DefaultProcessingInstruction extends FlyweightProcessingInstruction {
    private Element parent;

    public boolean isReadOnly() {
        return false;
    }

    public boolean supportsParent() {
        return true;
    }

    public DefaultProcessingInstruction(String str, Map map) {
        super(str, map);
    }

    public DefaultProcessingInstruction(String str, String str2) {
        super(str, str2);
    }

    public DefaultProcessingInstruction(Element element, String str, String str2) {
        super(str, str2);
        this.parent = element;
    }

    public void setTarget(String str) {
        this.target = str;
    }

    public void setText(String str) {
        this.text = str;
        this.values = parseValues(str);
    }

    public void setValues(Map map) {
        this.values = map;
        this.text = toString(map);
    }

    public void setValue(String str, String str2) {
        this.values.put(str, str2);
    }

    public Element getParent() {
        return this.parent;
    }

    public void setParent(Element element) {
        this.parent = element;
    }
}
