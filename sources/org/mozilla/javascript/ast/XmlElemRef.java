package org.mozilla.javascript.ast;

public class XmlElemRef extends XmlRef {
    private AstNode indexExpr;
    private int lb = -1;
    private int rb = -1;

    public XmlElemRef() {
        this.type = 77;
    }

    public XmlElemRef(int i) {
        super(i);
        this.type = 77;
    }

    public XmlElemRef(int i, int i2) {
        super(i, i2);
        this.type = 77;
    }

    public AstNode getExpression() {
        return this.indexExpr;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.indexExpr = astNode;
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

    public void setBrackets(int i, int i2) {
        this.lb = i;
        this.rb = i2;
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        if (isAttributeAccess()) {
            sb.append("@");
        }
        if (this.namespace != null) {
            sb.append(this.namespace.toSource(0));
            sb.append("::");
        }
        sb.append("[");
        sb.append(this.indexExpr.toSource(0));
        sb.append("]");
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            if (this.namespace != null) {
                this.namespace.visit(nodeVisitor);
            }
            this.indexExpr.visit(nodeVisitor);
        }
    }
}
