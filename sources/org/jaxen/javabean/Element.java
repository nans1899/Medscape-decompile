package org.jaxen.javabean;

public class Element {
    private String name;
    private Object object;
    private Element parent;

    public Element(Element element, String str, Object obj) {
        this.parent = element;
        this.name = str;
        this.object = obj;
    }

    public Element getParent() {
        return this.parent;
    }

    public String getName() {
        return this.name;
    }

    public Object getObject() {
        return this.object;
    }
}
