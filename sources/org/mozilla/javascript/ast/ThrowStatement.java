package org.mozilla.javascript.ast;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class ThrowStatement extends AstNode {
    private AstNode expression;

    public ThrowStatement() {
        this.type = 50;
    }

    public ThrowStatement(int i) {
        super(i);
        this.type = 50;
    }

    public ThrowStatement(int i, int i2) {
        super(i, i2);
        this.type = 50;
    }

    public ThrowStatement(AstNode astNode) {
        this.type = 50;
        setExpression(astNode);
    }

    public ThrowStatement(int i, AstNode astNode) {
        super(i, astNode.getLength());
        this.type = 50;
        setExpression(astNode);
    }

    public ThrowStatement(int i, int i2, AstNode astNode) {
        super(i, i2);
        this.type = 50;
        setExpression(astNode);
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    public String toSource(int i) {
        return makeIndent(i) + "throw" + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.expression.toSource(0) + ";\n";
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
        }
    }
}
