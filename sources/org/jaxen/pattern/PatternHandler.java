package org.jaxen.pattern;

import java.io.PrintStream;
import java.util.LinkedList;
import org.jaxen.JaxenException;
import org.jaxen.JaxenHandler;
import org.jaxen.expr.Expr;
import org.jaxen.expr.FilterExpr;

public class PatternHandler extends JaxenHandler {
    private Pattern pattern;

    public void startUnionExpr() {
    }

    public Pattern getPattern() {
        return getPattern(true);
    }

    public Pattern getPattern(boolean z) {
        if (z && !this.simplified) {
            this.pattern.simplify();
            this.simplified = true;
        }
        return this.pattern;
    }

    public void endXPath() {
        this.pattern = (Pattern) pop();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("stack is: ");
        stringBuffer.append(this.stack);
        printStream.println(stringBuffer.toString());
        popFrame();
    }

    public void endPathExpr() {
        LinkedList popFrame = popFrame();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("endPathExpr(): ");
        stringBuffer.append(popFrame);
        printStream.println(stringBuffer.toString());
        push(popFrame.removeFirst());
    }

    public void startAbsoluteLocationPath() {
        pushFrame();
        push(createAbsoluteLocationPath());
    }

    public void endAbsoluteLocationPath() throws JaxenException {
        endLocationPath();
    }

    public void startRelativeLocationPath() {
        pushFrame();
        push(createRelativeLocationPath());
    }

    public void endRelativeLocationPath() throws JaxenException {
        endLocationPath();
    }

    /* access modifiers changed from: protected */
    public void endLocationPath() throws JaxenException {
        LinkedList popFrame = popFrame();
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("endLocationPath: ");
        stringBuffer.append(popFrame);
        printStream.println(stringBuffer.toString());
        LocationPathPattern locationPathPattern = (LocationPathPattern) popFrame.removeFirst();
        push(locationPathPattern);
        while (!popFrame.isEmpty()) {
            Object removeFirst = popFrame.removeFirst();
            if (removeFirst instanceof NodeTest) {
                locationPathPattern.setNodeTest((NodeTest) removeFirst);
            } else if (removeFirst instanceof FilterExpr) {
                locationPathPattern.addFilter((FilterExpr) removeFirst);
            } else if (removeFirst instanceof LocationPathPattern) {
                LocationPathPattern locationPathPattern2 = (LocationPathPattern) removeFirst;
                locationPathPattern.setParentPattern(locationPathPattern2);
                locationPathPattern = locationPathPattern2;
            }
        }
    }

    public void startNameStep(int i, String str, String str2) {
        pushFrame();
        short s = i != 9 ? i != 10 ? (short) 1 : 13 : 2;
        if (str != null && str.length() > 0 && !str.equals("*")) {
            push(new NamespaceTest(str, s));
        }
        if (str2 != null && str2.length() > 0 && !str2.equals("*")) {
            push(new NameTest(str2, s));
        }
    }

    public void startTextNodeStep(int i) {
        pushFrame();
        push(new NodeTypeTest(3));
    }

    public void startCommentNodeStep(int i) {
        pushFrame();
        push(new NodeTypeTest(8));
    }

    public void startAllNodeStep(int i) {
        pushFrame();
        push(AnyNodeTest.getInstance());
    }

    public void startProcessingInstructionNodeStep(int i, String str) {
        pushFrame();
        push(new NodeTypeTest(7));
    }

    /* access modifiers changed from: protected */
    public void endStep() {
        LinkedList popFrame = popFrame();
        if (!popFrame.isEmpty()) {
            push(popFrame.removeFirst());
            if (!popFrame.isEmpty()) {
                PrintStream printStream = System.out;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("List should now be empty!");
                stringBuffer.append(popFrame);
                printStream.println(stringBuffer.toString());
            }
        }
    }

    public void endUnionExpr(boolean z) throws JaxenException {
        if (z) {
            Expr expr = (Expr) pop();
            push(getXPathFactory().createUnionExpr(expr, (Expr) pop()));
        }
    }

    /* access modifiers changed from: protected */
    public Pattern createAbsoluteLocationPath() {
        return new LocationPathPattern(NodeTypeTest.DOCUMENT_TEST);
    }

    /* access modifiers changed from: protected */
    public Pattern createRelativeLocationPath() {
        return new LocationPathPattern();
    }
}
