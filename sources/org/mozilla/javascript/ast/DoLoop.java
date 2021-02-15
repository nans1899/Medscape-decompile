package org.mozilla.javascript.ast;

public class DoLoop extends Loop {
    private AstNode condition;
    private int whilePosition = -1;

    public DoLoop() {
        this.type = 118;
    }

    public DoLoop(int i) {
        super(i);
        this.type = 118;
    }

    public DoLoop(int i, int i2) {
        super(i, i2);
        this.type = 118;
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode astNode) {
        assertNotNull(astNode);
        this.condition = astNode;
        astNode.setParent(this);
    }

    public int getWhilePosition() {
        return this.whilePosition;
    }

    public void setWhilePosition(int i) {
        this.whilePosition = i;
    }

    public String toSource(int i) {
        return makeIndent(i) + "do " + this.body.toSource(i).trim() + " while (" + this.condition.toSource(0) + ");\n";
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.body.visit(nodeVisitor);
            this.condition.visit(nodeVisitor);
        }
    }
}
