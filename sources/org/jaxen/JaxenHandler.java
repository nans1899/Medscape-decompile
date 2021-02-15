package org.jaxen;

import java.util.Iterator;
import java.util.LinkedList;
import org.jaxen.expr.DefaultXPathFactory;
import org.jaxen.expr.Expr;
import org.jaxen.expr.FilterExpr;
import org.jaxen.expr.FunctionCallExpr;
import org.jaxen.expr.LocationPath;
import org.jaxen.expr.Predicate;
import org.jaxen.expr.Predicated;
import org.jaxen.expr.Step;
import org.jaxen.expr.XPathExpr;
import org.jaxen.expr.XPathFactory;
import org.jaxen.saxpath.XPathHandler;

public class JaxenHandler implements XPathHandler {
    protected boolean simplified;
    protected LinkedList stack = new LinkedList();
    private XPathExpr xpath;
    private XPathFactory xpathFactory = new DefaultXPathFactory();

    public void startAdditiveExpr() {
    }

    public void startAndExpr() {
    }

    public void startEqualityExpr() {
    }

    public void startMultiplicativeExpr() {
    }

    public void startOrExpr() {
    }

    public void startRelationalExpr() {
    }

    public void startUnaryExpr() {
    }

    public void startUnionExpr() {
    }

    public void setXPathFactory(XPathFactory xPathFactory) {
        this.xpathFactory = xPathFactory;
    }

    public XPathFactory getXPathFactory() {
        return this.xpathFactory;
    }

    public XPathExpr getXPathExpr() {
        return getXPathExpr(true);
    }

    public XPathExpr getXPathExpr(boolean z) {
        if (z && !this.simplified) {
            this.xpath.simplify();
            this.simplified = true;
        }
        return this.xpath;
    }

    public void startXPath() {
        this.simplified = false;
        pushFrame();
    }

    public void endXPath() throws JaxenException {
        this.xpath = getXPathFactory().createXPath((Expr) pop());
        popFrame();
    }

    public void startPathExpr() {
        pushFrame();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: org.jaxen.expr.LocationPath} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: org.jaxen.expr.LocationPath} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: org.jaxen.expr.FilterExpr} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: org.jaxen.expr.LocationPath} */
    /* JADX WARNING: type inference failed for: r1v5, types: [org.jaxen.expr.LocationPath] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void endPathExpr() throws org.jaxen.JaxenException {
        /*
            r4 = this;
            int r0 = r4.stackSize()
            r1 = 0
            r2 = 2
            if (r0 != r2) goto L_0x0019
            java.lang.Object r0 = r4.pop()
            r1 = r0
            org.jaxen.expr.LocationPath r1 = (org.jaxen.expr.LocationPath) r1
            java.lang.Object r0 = r4.pop()
            org.jaxen.expr.FilterExpr r0 = (org.jaxen.expr.FilterExpr) r0
        L_0x0015:
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0027
        L_0x0019:
            java.lang.Object r0 = r4.pop()
            boolean r2 = r0 instanceof org.jaxen.expr.LocationPath
            if (r2 == 0) goto L_0x0024
            org.jaxen.expr.LocationPath r0 = (org.jaxen.expr.LocationPath) r0
            goto L_0x0027
        L_0x0024:
            org.jaxen.expr.FilterExpr r0 = (org.jaxen.expr.FilterExpr) r0
            goto L_0x0015
        L_0x0027:
            r4.popFrame()
            org.jaxen.expr.XPathFactory r2 = r4.getXPathFactory()
            org.jaxen.expr.PathExpr r0 = r2.createPathExpr(r1, r0)
            r4.push(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaxen.JaxenHandler.endPathExpr():void");
    }

    public void startAbsoluteLocationPath() throws JaxenException {
        pushFrame();
        push(getXPathFactory().createAbsoluteLocationPath());
    }

    public void endAbsoluteLocationPath() throws JaxenException {
        endLocationPath();
    }

    public void startRelativeLocationPath() throws JaxenException {
        pushFrame();
        push(getXPathFactory().createRelativeLocationPath());
    }

    public void endRelativeLocationPath() throws JaxenException {
        endLocationPath();
    }

    /* access modifiers changed from: protected */
    public void endLocationPath() throws JaxenException {
        LocationPath locationPath = (LocationPath) peekFrame().removeFirst();
        addSteps(locationPath, popFrame().iterator());
        push(locationPath);
    }

    /* access modifiers changed from: protected */
    public void addSteps(LocationPath locationPath, Iterator it) {
        while (it.hasNext()) {
            locationPath.addStep((Step) it.next());
        }
    }

    public void startNameStep(int i, String str, String str2) throws JaxenException {
        pushFrame();
        push(getXPathFactory().createNameStep(i, str, str2));
    }

    public void endNameStep() {
        endStep();
    }

    public void startTextNodeStep(int i) throws JaxenException {
        pushFrame();
        push(getXPathFactory().createTextNodeStep(i));
    }

    public void endTextNodeStep() {
        endStep();
    }

    public void startCommentNodeStep(int i) throws JaxenException {
        pushFrame();
        push(getXPathFactory().createCommentNodeStep(i));
    }

    public void endCommentNodeStep() {
        endStep();
    }

    public void startAllNodeStep(int i) throws JaxenException {
        pushFrame();
        push(getXPathFactory().createAllNodeStep(i));
    }

    public void endAllNodeStep() {
        endStep();
    }

    public void startProcessingInstructionNodeStep(int i, String str) throws JaxenException {
        pushFrame();
        push(getXPathFactory().createProcessingInstructionNodeStep(i, str));
    }

    public void endProcessingInstructionNodeStep() {
        endStep();
    }

    /* access modifiers changed from: protected */
    public void endStep() {
        Step step = (Step) peekFrame().removeFirst();
        addPredicates(step, popFrame().iterator());
        push(step);
    }

    public void startPredicate() {
        pushFrame();
    }

    public void endPredicate() throws JaxenException {
        Predicate createPredicate = getXPathFactory().createPredicate((Expr) pop());
        popFrame();
        push(createPredicate);
    }

    public void startFilterExpr() {
        pushFrame();
    }

    public void endFilterExpr() throws JaxenException {
        FilterExpr createFilterExpr = getXPathFactory().createFilterExpr((Expr) peekFrame().removeFirst());
        addPredicates(createFilterExpr, popFrame().iterator());
        push(createFilterExpr);
    }

    /* access modifiers changed from: protected */
    public void addPredicates(Predicated predicated, Iterator it) {
        while (it.hasNext()) {
            predicated.addPredicate((Predicate) it.next());
        }
    }

    /* access modifiers changed from: protected */
    public void returnExpr() {
        popFrame();
        push((Expr) pop());
    }

    public void endOrExpr(boolean z) throws JaxenException {
        if (z) {
            Expr expr = (Expr) pop();
            push(getXPathFactory().createOrExpr(expr, (Expr) pop()));
        }
    }

    public void endAndExpr(boolean z) throws JaxenException {
        if (z) {
            Expr expr = (Expr) pop();
            push(getXPathFactory().createAndExpr(expr, (Expr) pop()));
        }
    }

    public void endEqualityExpr(int i) throws JaxenException {
        if (i != 0) {
            Expr expr = (Expr) pop();
            push(getXPathFactory().createEqualityExpr(expr, (Expr) pop(), i));
        }
    }

    public void endRelationalExpr(int i) throws JaxenException {
        if (i != 0) {
            Expr expr = (Expr) pop();
            push(getXPathFactory().createRelationalExpr(expr, (Expr) pop(), i));
        }
    }

    public void endAdditiveExpr(int i) throws JaxenException {
        if (i != 0) {
            Expr expr = (Expr) pop();
            push(getXPathFactory().createAdditiveExpr(expr, (Expr) pop(), i));
        }
    }

    public void endMultiplicativeExpr(int i) throws JaxenException {
        if (i != 0) {
            Expr expr = (Expr) pop();
            push(getXPathFactory().createMultiplicativeExpr(expr, (Expr) pop(), i));
        }
    }

    public void endUnaryExpr(int i) throws JaxenException {
        if (i != 0) {
            push(getXPathFactory().createUnaryExpr((Expr) pop(), i));
        }
    }

    public void endUnionExpr(boolean z) throws JaxenException {
        if (z) {
            Expr expr = (Expr) pop();
            push(getXPathFactory().createUnionExpr(expr, (Expr) pop()));
        }
    }

    public void number(int i) throws JaxenException {
        push(getXPathFactory().createNumberExpr(i));
    }

    public void number(double d) throws JaxenException {
        push(getXPathFactory().createNumberExpr(d));
    }

    public void literal(String str) throws JaxenException {
        push(getXPathFactory().createLiteralExpr(str));
    }

    public void variableReference(String str, String str2) throws JaxenException {
        push(getXPathFactory().createVariableReferenceExpr(str, str2));
    }

    public void startFunction(String str, String str2) throws JaxenException {
        pushFrame();
        push(getXPathFactory().createFunctionCallExpr(str, str2));
    }

    public void endFunction() {
        FunctionCallExpr functionCallExpr = (FunctionCallExpr) peekFrame().removeFirst();
        addParameters(functionCallExpr, popFrame().iterator());
        push(functionCallExpr);
    }

    /* access modifiers changed from: protected */
    public void addParameters(FunctionCallExpr functionCallExpr, Iterator it) {
        while (it.hasNext()) {
            functionCallExpr.addParameter((Expr) it.next());
        }
    }

    /* access modifiers changed from: protected */
    public int stackSize() {
        return peekFrame().size();
    }

    /* access modifiers changed from: protected */
    public void push(Object obj) {
        peekFrame().addLast(obj);
    }

    /* access modifiers changed from: protected */
    public Object pop() {
        return peekFrame().removeLast();
    }

    /* access modifiers changed from: protected */
    public boolean canPop() {
        return peekFrame().size() > 0;
    }

    /* access modifiers changed from: protected */
    public void pushFrame() {
        this.stack.addLast(new LinkedList());
    }

    /* access modifiers changed from: protected */
    public LinkedList popFrame() {
        return (LinkedList) this.stack.removeLast();
    }

    /* access modifiers changed from: protected */
    public LinkedList peekFrame() {
        return (LinkedList) this.stack.getLast();
    }
}
