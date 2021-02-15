package org.mozilla.javascript.ast;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TryStatement extends AstNode {
    private static final List<CatchClause> NO_CATCHES = Collections.unmodifiableList(new ArrayList());
    private List<CatchClause> catchClauses;
    private AstNode finallyBlock;
    private int finallyPosition = -1;
    private AstNode tryBlock;

    public TryStatement() {
        this.type = 81;
    }

    public TryStatement(int i) {
        super(i);
        this.type = 81;
    }

    public TryStatement(int i, int i2) {
        super(i, i2);
        this.type = 81;
    }

    public AstNode getTryBlock() {
        return this.tryBlock;
    }

    public void setTryBlock(AstNode astNode) {
        assertNotNull(astNode);
        this.tryBlock = astNode;
        astNode.setParent(this);
    }

    public List<CatchClause> getCatchClauses() {
        List<CatchClause> list = this.catchClauses;
        return list != null ? list : NO_CATCHES;
    }

    public void setCatchClauses(List<CatchClause> list) {
        if (list == null) {
            this.catchClauses = null;
            return;
        }
        List<CatchClause> list2 = this.catchClauses;
        if (list2 != null) {
            list2.clear();
        }
        for (CatchClause addCatchClause : list) {
            addCatchClause(addCatchClause);
        }
    }

    public void addCatchClause(CatchClause catchClause) {
        assertNotNull(catchClause);
        if (this.catchClauses == null) {
            this.catchClauses = new ArrayList();
        }
        this.catchClauses.add(catchClause);
        catchClause.setParent(this);
    }

    public AstNode getFinallyBlock() {
        return this.finallyBlock;
    }

    public void setFinallyBlock(AstNode astNode) {
        this.finallyBlock = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public int getFinallyPosition() {
        return this.finallyPosition;
    }

    public void setFinallyPosition(int i) {
        this.finallyPosition = i;
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        sb.append(makeIndent(i));
        sb.append("try ");
        sb.append(this.tryBlock.toSource(i).trim());
        for (CatchClause source : getCatchClauses()) {
            sb.append(source.toSource(i));
        }
        if (this.finallyBlock != null) {
            sb.append(" finally ");
            sb.append(this.finallyBlock.toSource(i));
        }
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            this.tryBlock.visit(nodeVisitor);
            for (CatchClause visit : getCatchClauses()) {
                visit.visit(nodeVisitor);
            }
            AstNode astNode = this.finallyBlock;
            if (astNode != null) {
                astNode.visit(nodeVisitor);
            }
        }
    }
}
