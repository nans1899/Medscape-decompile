package org.mozilla.javascript.ast;

public class ObjectProperty extends InfixExpression {
    public void setNodeType(int i) {
        if (i == 103 || i == 151 || i == 152) {
            setType(i);
            return;
        }
        throw new IllegalArgumentException("invalid node type: " + i);
    }

    public ObjectProperty() {
        this.type = 103;
    }

    public ObjectProperty(int i) {
        super(i);
        this.type = 103;
    }

    public ObjectProperty(int i, int i2) {
        super(i, i2);
        this.type = 103;
    }

    public void setIsGetter() {
        this.type = 151;
    }

    public boolean isGetter() {
        return this.type == 151;
    }

    public void setIsSetter() {
        this.type = 152;
    }

    public boolean isSetter() {
        return this.type == 152;
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        if (isGetter()) {
            sb.append("get ");
        } else if (isSetter()) {
            sb.append("set ");
        }
        sb.append(this.left.toSource(0));
        if (this.type == 103) {
            sb.append(": ");
        }
        sb.append(this.right.toSource(0));
        return sb.toString();
    }
}
