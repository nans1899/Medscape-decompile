package org.mozilla.javascript.ast;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import org.mozilla.javascript.Token;

public class UnaryExpression extends AstNode {
    private boolean isPostfix;
    private AstNode operand;

    public UnaryExpression() {
    }

    public UnaryExpression(int i) {
        super(i);
    }

    public UnaryExpression(int i, int i2) {
        super(i, i2);
    }

    public UnaryExpression(int i, int i2, AstNode astNode) {
        this(i, i2, astNode, false);
    }

    public UnaryExpression(int i, int i2, AstNode astNode, boolean z) {
        int i3;
        assertNotNull(astNode);
        int position = z ? astNode.getPosition() : i2;
        if (z) {
            i3 = i2 + 2;
        } else {
            i3 = astNode.getPosition() + astNode.getLength();
        }
        setBounds(position, i3);
        setOperator(i);
        setOperand(astNode);
        this.isPostfix = z;
    }

    public int getOperator() {
        return this.type;
    }

    public void setOperator(int i) {
        if (Token.isValidToken(i)) {
            setType(i);
            return;
        }
        throw new IllegalArgumentException("Invalid token: " + i);
    }

    public AstNode getOperand() {
        return this.operand;
    }

    public void setOperand(AstNode astNode) {
        assertNotNull(astNode);
        this.operand = astNode;
        astNode.setParent(this);
    }

    public boolean isPostfix() {
        return this.isPostfix;
    }

    public boolean isPrefix() {
        return !this.isPostfix;
    }

    public void setIsPostfix(boolean z) {
        this.isPostfix = z;
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        int type = getType();
        if (!this.isPostfix) {
            sb.append(operatorToString(type));
            if (type == 32 || type == 31 || type == 126) {
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
        }
        sb.append(this.operand.toSource());
        if (this.isPostfix) {
            sb.append(operatorToString(type));
        }
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.operand.visit(nodeVisitor);
        }
    }
}
