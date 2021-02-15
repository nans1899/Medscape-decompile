package org.mozilla.javascript.ast;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class ReturnStatement extends AstNode {
    private AstNode returnValue;

    public ReturnStatement() {
        this.type = 4;
    }

    public ReturnStatement(int i) {
        super(i);
        this.type = 4;
    }

    public ReturnStatement(int i, int i2) {
        super(i, i2);
        this.type = 4;
    }

    public ReturnStatement(int i, int i2, AstNode astNode) {
        super(i, i2);
        this.type = 4;
        setReturnValue(astNode);
    }

    public AstNode getReturnValue() {
        return this.returnValue;
    }

    public void setReturnValue(AstNode astNode) {
        this.returnValue = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("return");
        if (this.returnValue != null) {
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(this.returnValue.toSource(0));
        }
        sb.append(";\n");
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        AstNode astNode;
        if (nodeVisitor.visit(this) && (astNode = this.returnValue) != null) {
            astNode.visit(nodeVisitor);
        }
    }
}
