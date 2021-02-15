package org.mozilla.javascript.ast;

public class EmptyStatement extends AstNode {
    public EmptyStatement() {
        this.type = 128;
    }

    public EmptyStatement(int i) {
        super(i);
        this.type = 128;
    }

    public EmptyStatement(int i, int i2) {
        super(i, i2);
        this.type = 128;
    }

    public String toSource(int i) {
        return makeIndent(i) + ";\n";
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
