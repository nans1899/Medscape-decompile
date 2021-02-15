package org.dom4j.tree;

import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.Node;

public class FlyweightComment extends AbstractComment implements Comment {
    protected String text;

    public FlyweightComment(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    /* access modifiers changed from: protected */
    public Node createXPathResult(Element element) {
        return new DefaultComment(element, getText());
    }
}
