package org.mozilla.javascript.ast;

import org.mozilla.javascript.Token;

public class Comment extends AstNode {
    private Token.CommentType commentType;
    private String value;

    public Comment(int i, int i2, Token.CommentType commentType2, String str) {
        super(i, i2);
        this.type = 161;
        this.commentType = commentType2;
        this.value = str;
    }

    public Token.CommentType getCommentType() {
        return this.commentType;
    }

    public void setCommentType(Token.CommentType commentType2) {
        this.commentType = commentType2;
    }

    public String getValue() {
        return this.value;
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder(getLength() + 10);
        sb.append(makeIndent(i));
        sb.append(this.value);
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
