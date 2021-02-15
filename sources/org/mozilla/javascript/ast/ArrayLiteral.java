package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayLiteral extends AstNode implements DestructuringForm {
    private static final List<AstNode> NO_ELEMS = Collections.unmodifiableList(new ArrayList());
    private int destructuringLength;
    private List<AstNode> elements;
    private boolean isDestructuring;
    private int skipCount;

    public ArrayLiteral() {
        this.type = 65;
    }

    public ArrayLiteral(int i) {
        super(i);
        this.type = 65;
    }

    public ArrayLiteral(int i, int i2) {
        super(i, i2);
        this.type = 65;
    }

    public List<AstNode> getElements() {
        List<AstNode> list = this.elements;
        return list != null ? list : NO_ELEMS;
    }

    public void setElements(List<AstNode> list) {
        if (list == null) {
            this.elements = null;
            return;
        }
        List<AstNode> list2 = this.elements;
        if (list2 != null) {
            list2.clear();
        }
        for (AstNode addElement : list) {
            addElement(addElement);
        }
    }

    public void addElement(AstNode astNode) {
        assertNotNull(astNode);
        if (this.elements == null) {
            this.elements = new ArrayList();
        }
        this.elements.add(astNode);
        astNode.setParent(this);
    }

    public int getSize() {
        List<AstNode> list = this.elements;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public AstNode getElement(int i) {
        List<AstNode> list = this.elements;
        if (list != null) {
            return list.get(i);
        }
        throw new IndexOutOfBoundsException("no elements");
    }

    public int getDestructuringLength() {
        return this.destructuringLength;
    }

    public void setDestructuringLength(int i) {
        this.destructuringLength = i;
    }

    public int getSkipCount() {
        return this.skipCount;
    }

    public void setSkipCount(int i) {
        this.skipCount = i;
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
        sb.append("[");
        List<AstNode> list = this.elements;
        if (list != null) {
            printList(list, sb);
        }
        sb.append("]");
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            for (AstNode visit : getElements()) {
                visit.visit(nodeVisitor);
            }
        }
    }
}
