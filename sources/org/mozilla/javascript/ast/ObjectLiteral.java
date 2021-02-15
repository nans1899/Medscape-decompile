package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ObjectLiteral extends AstNode implements DestructuringForm {
    private static final List<ObjectProperty> NO_ELEMS = Collections.unmodifiableList(new ArrayList());
    private List<ObjectProperty> elements;
    boolean isDestructuring;

    public ObjectLiteral() {
        this.type = 66;
    }

    public ObjectLiteral(int i) {
        super(i);
        this.type = 66;
    }

    public ObjectLiteral(int i, int i2) {
        super(i, i2);
        this.type = 66;
    }

    public List<ObjectProperty> getElements() {
        List<ObjectProperty> list = this.elements;
        return list != null ? list : NO_ELEMS;
    }

    public void setElements(List<ObjectProperty> list) {
        if (list == null) {
            this.elements = null;
            return;
        }
        List<ObjectProperty> list2 = this.elements;
        if (list2 != null) {
            list2.clear();
        }
        for (ObjectProperty addElement : list) {
            addElement(addElement);
        }
    }

    public void addElement(ObjectProperty objectProperty) {
        assertNotNull(objectProperty);
        if (this.elements == null) {
            this.elements = new ArrayList();
        }
        this.elements.add(objectProperty);
        objectProperty.setParent(this);
    }

    public void setIsDestructuring(boolean z) {
        this.isDestructuring = z;
    }

    public boolean isDestructuring() {
        return this.isDestructuring;
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("{");
        List<ObjectProperty> list = this.elements;
        if (list != null) {
            printList(list, sb);
        }
        sb.append("}");
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            for (ObjectProperty visit : getElements()) {
                visit.visit(nodeVisitor);
            }
        }
    }
}
