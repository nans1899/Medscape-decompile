package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.JaxenException;

public class UnionPattern extends Pattern {
    private Pattern lhs;
    private String matchesNodeName = null;
    private short nodeType = 0;
    private Pattern rhs;

    public UnionPattern() {
    }

    public UnionPattern(Pattern pattern, Pattern pattern2) {
        this.lhs = pattern;
        this.rhs = pattern2;
        init();
    }

    public Pattern getLHS() {
        return this.lhs;
    }

    public void setLHS(Pattern pattern) {
        this.lhs = pattern;
        init();
    }

    public Pattern getRHS() {
        return this.rhs;
    }

    public void setRHS(Pattern pattern) {
        this.rhs = pattern;
        init();
    }

    public boolean matches(Object obj, Context context) throws JaxenException {
        return this.lhs.matches(obj, context) || this.rhs.matches(obj, context);
    }

    public Pattern[] getUnionPatterns() {
        return new Pattern[]{this.lhs, this.rhs};
    }

    public short getMatchType() {
        return this.nodeType;
    }

    public String getMatchesNodeName() {
        return this.matchesNodeName;
    }

    public Pattern simplify() {
        this.lhs = this.lhs.simplify();
        this.rhs = this.rhs.simplify();
        init();
        return this;
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.lhs.getText());
        stringBuffer.append(" | ");
        stringBuffer.append(this.rhs.getText());
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append("[ lhs: ");
        stringBuffer.append(this.lhs);
        stringBuffer.append(" rhs: ");
        stringBuffer.append(this.rhs);
        stringBuffer.append(" ]");
        return stringBuffer.toString();
    }

    private void init() {
        short matchType = this.lhs.getMatchType();
        if (matchType != this.rhs.getMatchType()) {
            matchType = 0;
        }
        this.nodeType = matchType;
        String matchesNodeName2 = this.lhs.getMatchesNodeName();
        String matchesNodeName3 = this.rhs.getMatchesNodeName();
        this.matchesNodeName = null;
        if (matchesNodeName2 != null && matchesNodeName3 != null && matchesNodeName2.equals(matchesNodeName3)) {
            this.matchesNodeName = matchesNodeName2;
        }
    }
}
