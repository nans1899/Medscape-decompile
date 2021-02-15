package org.mozilla.javascript.ast;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

public class VariableDeclaration extends AstNode {
    private boolean isStatement;
    private List<VariableInitializer> variables = new ArrayList();

    public VariableDeclaration() {
        this.type = 122;
    }

    public VariableDeclaration(int i) {
        super(i);
        this.type = 122;
    }

    public VariableDeclaration(int i, int i2) {
        super(i, i2);
        this.type = 122;
    }

    public List<VariableInitializer> getVariables() {
        return this.variables;
    }

    public void setVariables(List<VariableInitializer> list) {
        assertNotNull(list);
        this.variables.clear();
        for (VariableInitializer addVariable : list) {
            addVariable(addVariable);
        }
    }

    public void addVariable(VariableInitializer variableInitializer) {
        assertNotNull(variableInitializer);
        this.variables.add(variableInitializer);
        variableInitializer.setParent(this);
    }

    public Node setType(int i) {
        if (i == 122 || i == 154 || i == 153) {
            return super.setType(i);
        }
        throw new IllegalArgumentException("invalid decl type: " + i);
    }

    public boolean isVar() {
        return this.type == 122;
    }

    public boolean isConst() {
        return this.type == 154;
    }

    public boolean isLet() {
        return this.type == 153;
    }

    public boolean isStatement() {
        return this.isStatement;
    }

    public void setIsStatement(boolean z) {
        this.isStatement = z;
    }

    private String declTypeName() {
        return Token.typeToName(this.type).toLowerCase();
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append(declTypeName());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        printList(this.variables, sb);
        if (isStatement()) {
            sb.append(";\n");
        }
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            for (VariableInitializer visit : this.variables) {
                visit.visit(nodeVisitor);
            }
        }
    }
}
