package org.dom4j.xpath;

import java.util.ArrayList;
import org.dom4j.InvalidXPathException;
import org.dom4j.Node;
import org.dom4j.XPathException;
import org.dom4j.rule.Pattern;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.SimpleVariableContext;
import org.jaxen.VariableContext;
import org.jaxen.XPathFunctionContext;
import org.jaxen.dom4j.DocumentNavigator;
import org.jaxen.pattern.PatternParser;
import org.jaxen.saxpath.SAXPathException;

public class XPathPattern implements Pattern {
    private Context context = new Context(getContextSupport());
    private org.jaxen.pattern.Pattern pattern;
    private String text;

    public XPathPattern(org.jaxen.pattern.Pattern pattern2) {
        this.pattern = pattern2;
        this.text = pattern2.getText();
    }

    public XPathPattern(String str) {
        this.text = str;
        try {
            this.pattern = PatternParser.parse(str);
        } catch (SAXPathException e) {
            throw new InvalidXPathException(str, e.getMessage());
        } catch (Throwable th) {
            throw new InvalidXPathException(str, th);
        }
    }

    public boolean matches(Node node) {
        try {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(node);
            this.context.setNodeSet(arrayList);
            return this.pattern.matches(node, this.context);
        } catch (JaxenException e) {
            handleJaxenException(e);
            return false;
        }
    }

    public String getText() {
        return this.text;
    }

    public double getPriority() {
        return this.pattern.getPriority();
    }

    public Pattern[] getUnionPatterns() {
        org.jaxen.pattern.Pattern[] unionPatterns = this.pattern.getUnionPatterns();
        if (unionPatterns == null) {
            return null;
        }
        int length = unionPatterns.length;
        XPathPattern[] xPathPatternArr = new XPathPattern[length];
        for (int i = 0; i < length; i++) {
            xPathPatternArr[i] = new XPathPattern(unionPatterns[i]);
        }
        return xPathPatternArr;
    }

    public short getMatchType() {
        return this.pattern.getMatchType();
    }

    public String getMatchesNodeName() {
        return this.pattern.getMatchesNodeName();
    }

    public void setVariableContext(VariableContext variableContext) {
        this.context.getContextSupport().setVariableContext(variableContext);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[XPathPattern: text: ");
        stringBuffer.append(this.text);
        stringBuffer.append(" Pattern: ");
        stringBuffer.append(this.pattern);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public ContextSupport getContextSupport() {
        return new ContextSupport(new SimpleNamespaceContext(), XPathFunctionContext.getInstance(), new SimpleVariableContext(), DocumentNavigator.getInstance());
    }

    /* access modifiers changed from: protected */
    public void handleJaxenException(JaxenException jaxenException) throws XPathException {
        throw new XPathException(this.text, (Exception) jaxenException);
    }
}
