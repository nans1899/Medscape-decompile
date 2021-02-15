package org.dom4j.rule;

import java.util.HashMap;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

public class Mode {
    private Map attributeNameRuleSets;
    private Map elementNameRuleSets;
    private RuleSet[] ruleSets = new RuleSet[14];

    public void fireRule(Node node) throws Exception {
        Rule matchingRule;
        Action action;
        if (node != null && (matchingRule = getMatchingRule(node)) != null && (action = matchingRule.getAction()) != null) {
            action.run(node);
        }
    }

    public void applyTemplates(Element element) throws Exception {
        int attributeCount = element.attributeCount();
        for (int i = 0; i < attributeCount; i++) {
            fireRule(element.attribute(i));
        }
        int nodeCount = element.nodeCount();
        for (int i2 = 0; i2 < nodeCount; i2++) {
            fireRule(element.node(i2));
        }
    }

    public void applyTemplates(Document document) throws Exception {
        int nodeCount = document.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            fireRule(document.node(i));
        }
    }

    public void addRule(Rule rule) {
        short matchType = rule.getMatchType();
        String matchesNodeName = rule.getMatchesNodeName();
        if (matchesNodeName != null) {
            if (matchType == 1) {
                this.elementNameRuleSets = addToNameMap(this.elementNameRuleSets, matchesNodeName, rule);
            } else if (matchType == 2) {
                this.attributeNameRuleSets = addToNameMap(this.attributeNameRuleSets, matchesNodeName, rule);
            }
        }
        if (matchType >= 14) {
            matchType = 0;
        }
        if (matchType == 0) {
            int length = this.ruleSets.length;
            for (int i = 1; i < length; i++) {
                RuleSet ruleSet = this.ruleSets[i];
                if (ruleSet != null) {
                    ruleSet.addRule(rule);
                }
            }
        }
        getRuleSet(matchType).addRule(rule);
    }

    public void removeRule(Rule rule) {
        short matchType = rule.getMatchType();
        String matchesNodeName = rule.getMatchesNodeName();
        if (matchesNodeName != null) {
            if (matchType == 1) {
                removeFromNameMap(this.elementNameRuleSets, matchesNodeName, rule);
            } else if (matchType == 2) {
                removeFromNameMap(this.attributeNameRuleSets, matchesNodeName, rule);
            }
        }
        if (matchType >= 14) {
            matchType = 0;
        }
        getRuleSet(matchType).removeRule(rule);
        if (matchType != 0) {
            getRuleSet(0).removeRule(rule);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0054, code lost:
        r0 = r4.ruleSets[0];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.dom4j.rule.Rule getMatchingRule(org.dom4j.Node r5) {
        /*
            r4 = this;
            short r0 = r5.getNodeType()
            r1 = 1
            if (r0 != r1) goto L_0x0020
            java.util.Map r1 = r4.elementNameRuleSets
            if (r1 == 0) goto L_0x003c
            java.lang.String r1 = r5.getName()
            java.util.Map r2 = r4.elementNameRuleSets
            java.lang.Object r1 = r2.get(r1)
            org.dom4j.rule.RuleSet r1 = (org.dom4j.rule.RuleSet) r1
            if (r1 == 0) goto L_0x003c
            org.dom4j.rule.Rule r1 = r1.getMatchingRule(r5)
            if (r1 == 0) goto L_0x003c
            return r1
        L_0x0020:
            r1 = 2
            if (r0 != r1) goto L_0x003c
            java.util.Map r1 = r4.attributeNameRuleSets
            if (r1 == 0) goto L_0x003c
            java.lang.String r1 = r5.getName()
            java.util.Map r2 = r4.attributeNameRuleSets
            java.lang.Object r1 = r2.get(r1)
            org.dom4j.rule.RuleSet r1 = (org.dom4j.rule.RuleSet) r1
            if (r1 == 0) goto L_0x003c
            org.dom4j.rule.Rule r1 = r1.getMatchingRule(r5)
            if (r1 == 0) goto L_0x003c
            return r1
        L_0x003c:
            r1 = 0
            if (r0 < 0) goto L_0x0044
            org.dom4j.rule.RuleSet[] r2 = r4.ruleSets
            int r2 = r2.length
            if (r0 < r2) goto L_0x0045
        L_0x0044:
            r0 = 0
        L_0x0045:
            r2 = 0
            org.dom4j.rule.RuleSet[] r3 = r4.ruleSets
            r3 = r3[r0]
            if (r3 == 0) goto L_0x0050
            org.dom4j.rule.Rule r2 = r3.getMatchingRule(r5)
        L_0x0050:
            if (r2 != 0) goto L_0x005e
            if (r0 == 0) goto L_0x005e
            org.dom4j.rule.RuleSet[] r0 = r4.ruleSets
            r0 = r0[r1]
            if (r0 == 0) goto L_0x005e
            org.dom4j.rule.Rule r2 = r0.getMatchingRule(r5)
        L_0x005e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.rule.Mode.getMatchingRule(org.dom4j.Node):org.dom4j.rule.Rule");
    }

    /* access modifiers changed from: protected */
    public RuleSet getRuleSet(int i) {
        RuleSet ruleSet;
        RuleSet ruleSet2 = this.ruleSets[i];
        if (ruleSet2 == null) {
            ruleSet2 = new RuleSet();
            RuleSet[] ruleSetArr = this.ruleSets;
            ruleSetArr[i] = ruleSet2;
            if (!(i == 0 || (ruleSet = ruleSetArr[0]) == null)) {
                ruleSet2.addAll(ruleSet);
            }
        }
        return ruleSet2;
    }

    /* access modifiers changed from: protected */
    public Map addToNameMap(Map map, String str, Rule rule) {
        if (map == null) {
            map = new HashMap();
        }
        RuleSet ruleSet = (RuleSet) map.get(str);
        if (ruleSet == null) {
            ruleSet = new RuleSet();
            map.put(str, ruleSet);
        }
        ruleSet.addRule(rule);
        return map;
    }

    /* access modifiers changed from: protected */
    public void removeFromNameMap(Map map, String str, Rule rule) {
        RuleSet ruleSet;
        if (map != null && (ruleSet = (RuleSet) map.get(str)) != null) {
            ruleSet.removeRule(rule);
        }
    }
}
