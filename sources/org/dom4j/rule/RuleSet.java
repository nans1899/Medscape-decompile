package org.dom4j.rule;

import java.util.ArrayList;
import java.util.Collections;
import org.dom4j.Node;

public class RuleSet {
    private Rule[] ruleArray;
    private ArrayList rules = new ArrayList();

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append(" [RuleSet: ");
        stringBuffer.append(this.rules);
        stringBuffer.append(" ]");
        return stringBuffer.toString();
    }

    public Rule getMatchingRule(Node node) {
        Rule[] ruleArray2 = getRuleArray();
        for (int length = ruleArray2.length - 1; length >= 0; length--) {
            Rule rule = ruleArray2[length];
            if (rule.matches(node)) {
                return rule;
            }
        }
        return null;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
        this.ruleArray = null;
    }

    public void removeRule(Rule rule) {
        this.rules.remove(rule);
        this.ruleArray = null;
    }

    public void addAll(RuleSet ruleSet) {
        this.rules.addAll(ruleSet.rules);
        this.ruleArray = null;
    }

    /* access modifiers changed from: protected */
    public Rule[] getRuleArray() {
        if (this.ruleArray == null) {
            Collections.sort(this.rules);
            Rule[] ruleArr = new Rule[this.rules.size()];
            this.ruleArray = ruleArr;
            this.rules.toArray(ruleArr);
        }
        return this.ruleArray;
    }
}
