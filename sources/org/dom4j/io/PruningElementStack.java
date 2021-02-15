package org.dom4j.io;

import org.dom4j.Element;
import org.dom4j.ElementHandler;

class PruningElementStack extends ElementStack {
    private ElementHandler elementHandler;
    private int matchingElementIndex;
    private String[] path;

    public PruningElementStack(String[] strArr, ElementHandler elementHandler2) {
        this.path = strArr;
        this.elementHandler = elementHandler2;
        checkPath();
    }

    public PruningElementStack(String[] strArr, ElementHandler elementHandler2, int i) {
        super(i);
        this.path = strArr;
        this.elementHandler = elementHandler2;
        checkPath();
    }

    public Element popElement() {
        Element popElement = super.popElement();
        if (this.lastElementIndex == this.matchingElementIndex && this.lastElementIndex >= 0 && validElement(popElement, this.lastElementIndex + 1)) {
            int i = 0;
            Element element = null;
            Element element2 = null;
            while (true) {
                if (i > this.lastElementIndex) {
                    element = element2;
                    break;
                }
                element2 = this.stack[i];
                if (!validElement(element2, i)) {
                    break;
                }
                i++;
            }
            if (element != null) {
                pathMatches(element, popElement);
            }
        }
        return popElement;
    }

    /* access modifiers changed from: protected */
    public void pathMatches(Element element, Element element2) {
        this.elementHandler.onEnd(this);
        element.remove(element2);
    }

    /* access modifiers changed from: protected */
    public boolean validElement(Element element, int i) {
        String str = this.path[i];
        String name = element.getName();
        if (str == name) {
            return true;
        }
        if (str == null || name == null) {
            return false;
        }
        return str.equals(name);
    }

    private void checkPath() {
        String[] strArr = this.path;
        if (strArr.length >= 2) {
            this.matchingElementIndex = strArr.length - 2;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("Invalid path of length: ");
        stringBuffer.append(this.path.length);
        stringBuffer.append(" it must be greater than 2");
        throw new RuntimeException(stringBuffer.toString());
    }
}
