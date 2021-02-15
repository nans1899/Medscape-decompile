package org.mozilla.javascript.ast;

import org.apache.commons.io.IOUtils;

public class ForLoop extends Loop {
    private AstNode condition;
    private AstNode increment;
    private AstNode initializer;

    public ForLoop() {
        this.type = 119;
    }

    public ForLoop(int i) {
        super(i);
        this.type = 119;
    }

    public ForLoop(int i, int i2) {
        super(i, i2);
        this.type = 119;
    }

    public AstNode getInitializer() {
        return this.initializer;
    }

    public void setInitializer(AstNode astNode) {
        assertNotNull(astNode);
        this.initializer = astNode;
        astNode.setParent(this);
    }

    public AstNode getCondition() {
        return this.condition;
    }

    public void setCondition(AstNode astNode) {
        assertNotNull(astNode);
        this.condition = astNode;
        astNode.setParent(this);
    }

    public AstNode getIncrement() {
        return this.increment;
    }

    public void setIncrement(AstNode astNode) {
        assertNotNull(astNode);
        this.increment = astNode;
        astNode.setParent(this);
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("for (");
        sb.append(this.initializer.toSource(0));
        sb.append("; ");
        sb.append(this.condition.toSource(0));
        sb.append("; ");
        sb.append(this.increment.toSource(0));
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
            this.initializer.visit(nodeVisitor);
            this.condition.visit(nodeVisitor);
            this.increment.visit(nodeVisitor);
            this.body.visit(nodeVisitor);
        }
    }
}
