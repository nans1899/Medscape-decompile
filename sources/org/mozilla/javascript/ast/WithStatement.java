package org.mozilla.javascript.ast;

public class WithStatement extends AstNode {
    private AstNode expression;
    private int lp = -1;
    private int rp = -1;
    private AstNode statement;

    public WithStatement() {
        this.type = 123;
    }

    public WithStatement(int i) {
        super(i);
        this.type = 123;
    }

    public WithStatement(int i, int i2) {
        super(i, i2);
        this.type = 123;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    public AstNode getStatement() {
        return this.statement;
    }

    public void setStatement(AstNode astNode) {
        assertNotNull(astNode);
        this.statement = astNode;
        astNode.setParent(this);
    }

    public int getLp() {
        return this.lp;
    }

    public void setLp(int i) {
        this.lp = i;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int i) {
        this.rp = i;
    }

    public void setParens(int i, int i2) {
        this.lp = i;
        this.rp = i2;
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("with (");
        sb.append(this.expression.toSource(0));
        sb.append(") ");
        sb.append(this.statement.toSource(i + 1));
        if (!(this.statement instanceof Block)) {
            sb.append(";\n");
        }
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
            this.statement.visit(nodeVisitor);
        }
    }
}
