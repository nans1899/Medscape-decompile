package org.mozilla.javascript.ast;

public class ElementGet extends AstNode {
    private AstNode element;
    private int lb = -1;
    private int rb = -1;
    private AstNode target;

    public ElementGet() {
        this.type = 36;
    }

    public ElementGet(int i) {
        super(i);
        this.type = 36;
    }

    public ElementGet(int i, int i2) {
        super(i, i2);
        this.type = 36;
    }

    public ElementGet(AstNode astNode, AstNode astNode2) {
        this.type = 36;
        setTarget(astNode);
        setElement(astNode2);
    }

    public AstNode getTarget() {
        return this.target;
    }

    public void setTarget(AstNode astNode) {
        assertNotNull(astNode);
        this.target = astNode;
        astNode.setParent(this);
    }

    public AstNode getElement() {
        return this.element;
    }

    public void setElement(AstNode astNode) {
        assertNotNull(astNode);
        this.element = astNode;
        astNode.setParent(this);
    }

    public int getLb() {
        return this.lb;
    }

    public void setLb(int i) {
        this.lb = i;
    }

    public int getRb() {
        return this.rb;
    }

    public void setRb(int i) {
        this.rb = i;
    }

    public void setParens(int i, int i2) {
        this.lb = i;
        this.rb = i2;
    }

    public String toSource(int i) {
        return makeIndent(i) + this.target.toSource(0) + "[" + this.element.toSource(0) + "]";
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.target.visit(nodeVisitor);
            this.element.visit(nodeVisitor);
        }
    }
}
