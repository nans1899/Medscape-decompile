package org.dom4j.rule;

import org.dom4j.Node;

public class Rule implements Comparable {
    private Action action;
    private int appearenceCount;
    private int importPrecedence;
    private String mode;
    private Pattern pattern;
    private double priority;

    public Rule() {
        this.priority = 0.5d;
    }

    public Rule(Pattern pattern2) {
        this.pattern = pattern2;
        this.priority = pattern2.getPriority();
    }

    public Rule(Pattern pattern2, Action action2) {
        this(pattern2);
        this.action = action2;
    }

    public Rule(Rule rule, Pattern pattern2) {
        this.mode = rule.mode;
        this.importPrecedence = rule.importPrecedence;
        this.priority = rule.priority;
        this.appearenceCount = rule.appearenceCount;
        this.action = rule.action;
        this.pattern = pattern2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Rule) || compareTo((Rule) obj) != 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.importPrecedence + this.appearenceCount;
    }

    public int compareTo(Object obj) {
        if (obj instanceof Rule) {
            return compareTo((Rule) obj);
        }
        return getClass().getName().compareTo(obj.getClass().getName());
    }

    public int compareTo(Rule rule) {
        int i = this.importPrecedence - rule.importPrecedence;
        if (i != 0) {
            return i;
        }
        int round = (int) Math.round(this.priority - rule.priority);
        return round == 0 ? this.appearenceCount - rule.appearenceCount : round;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append("[ pattern: ");
        stringBuffer.append(getPattern());
        stringBuffer.append(" action: ");
        stringBuffer.append(getAction());
        stringBuffer.append(" ]");
        return stringBuffer.toString();
    }

    public final boolean matches(Node node) {
        return this.pattern.matches(node);
    }

    public Rule[] getUnionRules() {
        Pattern[] unionPatterns = this.pattern.getUnionPatterns();
        if (unionPatterns == null) {
            return null;
        }
        int length = unionPatterns.length;
        Rule[] ruleArr = new Rule[length];
        for (int i = 0; i < length; i++) {
            ruleArr[i] = new Rule(this, unionPatterns[i]);
        }
        return ruleArr;
    }

    public final short getMatchType() {
        return this.pattern.getMatchType();
    }

    public final String getMatchesNodeName() {
        return this.pattern.getMatchesNodeName();
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public int getImportPrecedence() {
        return this.importPrecedence;
    }

    public void setImportPrecedence(int i) {
        this.importPrecedence = i;
    }

    public double getPriority() {
        return this.priority;
    }

    public void setPriority(double d) {
        this.priority = d;
    }

    public int getAppearenceCount() {
        return this.appearenceCount;
    }

    public void setAppearenceCount(int i) {
        this.appearenceCount = i;
    }

    public Pattern getPattern() {
        return this.pattern;
    }

    public void setPattern(Pattern pattern2) {
        this.pattern = pattern2;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action2) {
        this.action = action2;
    }
}
