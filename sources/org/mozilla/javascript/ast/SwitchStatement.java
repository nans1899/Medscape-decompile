package org.mozilla.javascript.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwitchStatement extends Jump {
    private static final List<SwitchCase> NO_CASES = Collections.unmodifiableList(new ArrayList());
    private List<SwitchCase> cases;
    private AstNode expression;
    private int lp = -1;
    private int rp = -1;

    public SwitchStatement() {
        this.type = 114;
    }

    public SwitchStatement(int i) {
        this.type = 114;
        this.position = i;
    }

    public SwitchStatement(int i, int i2) {
        this.type = 114;
        this.position = i;
        this.length = i2;
    }

    public AstNode getExpression() {
        return this.expression;
    }

    public void setExpression(AstNode astNode) {
        assertNotNull(astNode);
        this.expression = astNode;
        astNode.setParent(this);
    }

    public List<SwitchCase> getCases() {
        List<SwitchCase> list = this.cases;
        return list != null ? list : NO_CASES;
    }

    public void setCases(List<SwitchCase> list) {
        if (list == null) {
            this.cases = null;
            return;
        }
        List<SwitchCase> list2 = this.cases;
        if (list2 != null) {
            list2.clear();
        }
        for (SwitchCase addCase : list) {
            addCase(addCase);
        }
    }

    public void addCase(SwitchCase switchCase) {
        assertNotNull(switchCase);
        if (this.cases == null) {
            this.cases = new ArrayList();
        }
        this.cases.add(switchCase);
        switchCase.setParent(this);
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
        String makeIndent = makeIndent(i);
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent);
        sb.append("switch (");
        sb.append(this.expression.toSource(0));
        sb.append(") {\n");
        for (SwitchCase source : this.cases) {
            sb.append(source.toSource(i + 1));
        }
        sb.append(makeIndent);
        sb.append("}\n");
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.expression.visit(nodeVisitor);
            for (SwitchCase visit : getCases()) {
                visit.visit(nodeVisitor);
            }
        }
    }
}
