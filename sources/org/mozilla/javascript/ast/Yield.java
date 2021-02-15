package org.mozilla.javascript.ast;

public class Yield extends AstNode {
    private AstNode value;

    public Yield() {
        this.type = 72;
    }

    public Yield(int i) {
        super(i);
        this.type = 72;
    }

    public Yield(int i, int i2) {
        super(i, i2);
        this.type = 72;
    }

    public Yield(int i, int i2, AstNode astNode) {
        super(i, i2);
        this.type = 72;
        setValue(astNode);
    }

    public AstNode getValue() {
        return this.value;
    }

    public void setValue(AstNode astNode) {
        this.value = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public String toSource(int i) {
        if (this.value == null) {
            return "yield";
        }
        return "yield " + this.value.toSource(0);
    }

    public void visit(NodeVisitor nodeVisitor) {
        AstNode astNode;
        if (nodeVisitor.visit(this) && (astNode = this.value) != null) {
            astNode.visit(nodeVisitor);
        }
    }
}
