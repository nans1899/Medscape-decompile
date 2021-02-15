package org.mozilla.javascript.ast;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.io.IOUtils;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.ast.AstNode;

public class AstRoot extends ScriptNode {
    private SortedSet<Comment> comments;
    private boolean inStrictMode;

    public AstRoot() {
        this.type = 136;
    }

    public AstRoot(int i) {
        super(i);
        this.type = 136;
    }

    public SortedSet<Comment> getComments() {
        return this.comments;
    }

    public void setComments(SortedSet<Comment> sortedSet) {
        if (sortedSet == null) {
            this.comments = null;
            return;
        }
        SortedSet<Comment> sortedSet2 = this.comments;
        if (sortedSet2 != null) {
            sortedSet2.clear();
        }
        for (Comment addComment : sortedSet) {
            addComment(addComment);
        }
    }

    public void addComment(Comment comment) {
        assertNotNull(comment);
        if (this.comments == null) {
            this.comments = new TreeSet(new AstNode.PositionComparator());
        }
        this.comments.add(comment);
        comment.setParent(this);
    }

    public void setInStrictMode(boolean z) {
        this.inStrictMode = z;
    }

    public boolean isInStrictMode() {
        return this.inStrictMode;
    }

    public void visitComments(NodeVisitor nodeVisitor) {
        SortedSet<Comment> sortedSet = this.comments;
        if (sortedSet != null) {
            for (Comment visit : sortedSet) {
                nodeVisitor.visit(visit);
            }
        }
    }

    public void visitAll(NodeVisitor nodeVisitor) {
        visit(nodeVisitor);
        visitComments(nodeVisitor);
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        Iterator<Node> it = iterator();
        while (it.hasNext()) {
            sb.append(((AstNode) it.next()).toSource(i));
        }
        return sb.toString();
    }

    public String debugPrint() {
        AstNode.DebugPrintVisitor debugPrintVisitor = new AstNode.DebugPrintVisitor(new StringBuilder(1000));
        visitAll(debugPrintVisitor);
        return debugPrintVisitor.toString();
    }

    public void checkParentLinks() {
        visit(new NodeVisitor() {
            public boolean visit(AstNode astNode) {
                if (astNode.getType() == 136 || astNode.getParent() != null) {
                    return true;
                }
                throw new IllegalStateException("No parent for node: " + astNode + IOUtils.LINE_SEPARATOR_UNIX + astNode.toSource(0));
            }
        });
    }
}
