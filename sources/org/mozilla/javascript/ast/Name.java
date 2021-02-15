package org.mozilla.javascript.ast;

public class Name extends AstNode {
    private String identifier;
    private Scope scope;

    public Name() {
        this.type = 39;
    }

    public Name(int i) {
        super(i);
        this.type = 39;
    }

    public Name(int i, int i2) {
        super(i, i2);
        this.type = 39;
    }

    public Name(int i, int i2, String str) {
        super(i, i2);
        this.type = 39;
        setIdentifier(str);
    }

    public Name(int i, String str) {
        super(i);
        this.type = 39;
        setIdentifier(str);
        setLength(str.length());
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        assertNotNull(str);
        this.identifier = str;
        setLength(str.length());
    }

    public void setScope(Scope scope2) {
        this.scope = scope2;
    }

    public Scope getScope() {
        return this.scope;
    }

    public Scope getDefiningScope() {
        Scope enclosingScope = getEnclosingScope();
        String identifier2 = getIdentifier();
        if (enclosingScope == null) {
            return null;
        }
        return enclosingScope.getDefiningScope(identifier2);
    }

    public boolean isLocalName() {
        Scope definingScope = getDefiningScope();
        return (definingScope == null || definingScope.getParentScope() == null) ? false : true;
    }

    public int length() {
        String str = this.identifier;
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        String str = this.identifier;
        if (str == null) {
            str = "<null>";
        }
        sb.append(str);
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        nodeVisitor.visit(this);
    }
}
