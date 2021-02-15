package org.dom4j.tree;

import org.dom4j.Element;
import org.dom4j.Node;

public class FlyweightEntity extends AbstractEntity {
    protected String name;
    protected String text;

    protected FlyweightEntity() {
    }

    public FlyweightEntity(String str) {
        this.name = str;
    }

    public FlyweightEntity(String str, String str2) {
        this.name = str;
        this.text = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        if (this.text != null) {
            this.text = str;
            return;
        }
        throw new UnsupportedOperationException("This Entity is read-only. It cannot be modified");
    }

    /* access modifiers changed from: protected */
    public Node createXPathResult(Element element) {
        return new DefaultEntity(element, getName(), getText());
    }
}
