package org.dom4j.tree;

import org.dom4j.CDATA;
import org.dom4j.Element;
import org.dom4j.Node;

public class FlyweightCDATA extends AbstractCDATA implements CDATA {
    protected String text;

    public FlyweightCDATA(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    /* access modifiers changed from: protected */
    public Node createXPathResult(Element element) {
        return new DefaultCDATA(element, getText());
    }
}
