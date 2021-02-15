package org.mozilla.javascript.ast;

public class ExpressionStatement extends AstNode {
    private AstNode expr;

    public void setHasResult() {
        this.type = 134;
    }

    public ExpressionStatement() {
        this.type = 133;
    }

    public ExpressionStatement(AstNode astNode, boolean z) {
        this(astNode);
        if (z) {
            setHasResult();
        }
    }

    public ExpressionStatement(AstNode astNode) {
        this(astNode.getPosition(), astNode.getLength(), astNode);
    }

    public ExpressionStatement(int i, int i2) {
        super(i, i2);
        this.type = 133;
    }

    public ExpressionStatement(int i, int i2, AstNode astNode) {
        super(i, i2);
        this.type = 133;
        setExpression(astNode);
    }

    public AstNode getExpression() {
        return this.expr;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expr = astNode;
        astNode.setParent(this);
        setLineno(astNode.getLineno());
    }

    public boolean hasSideEffects() {
        return this.type == 134 || this.expr.hasSideEffects();
    }

    public String toSource(int i) {
        return this.expr.toSource(i) + ";\n";
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expr.visit(nodeVisitor);
        }
    }
}
