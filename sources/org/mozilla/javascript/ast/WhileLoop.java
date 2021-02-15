package org.mozilla.javascript.ast;

import org.apache.commons.io.IOUtils;

public class WhileLoop extends Loop {
    private AstNode condition;

    public WhileLoop() {
        this.type = 117;
    }

    public WhileLoop(int i) {
        super(i);
        this.type = 117;
    }

    public WhileLoop(int i, int i2) {
        super(i, i2);
        this.type = 117;
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode astNode) {
        assertNotNull(astNode);
        this.condition = astNode;
        astNode.setParent(this);
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("while (");
        sb.append(this.condition.toSource(0));
        sb.append(") ");
        if (this.body instanceof Block) {
            sb.append(this.body.toSource(i).trim());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        } else {
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            sb.append(this.body.toSource(i + 1));
        }
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.condition.visit(nodeVisitor);
            this.body.visit(nodeVisitor);
        }
    }
}
