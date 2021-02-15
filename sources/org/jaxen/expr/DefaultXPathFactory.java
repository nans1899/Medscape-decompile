package org.jaxen.expr;

import org.jaxen.JaxenException;
import org.jaxen.expr.iter.IterableAncestorAxis;
import org.jaxen.expr.iter.IterableAncestorOrSelfAxis;
import org.jaxen.expr.iter.IterableAttributeAxis;
import org.jaxen.expr.iter.IterableAxis;
import org.jaxen.expr.iter.IterableChildAxis;
import org.jaxen.expr.iter.IterableDescendantAxis;
import org.jaxen.expr.iter.IterableDescendantOrSelfAxis;
import org.jaxen.expr.iter.IterableFollowingAxis;
import org.jaxen.expr.iter.IterableFollowingSiblingAxis;
import org.jaxen.expr.iter.IterableNamespaceAxis;
import org.jaxen.expr.iter.IterableParentAxis;
import org.jaxen.expr.iter.IterablePrecedingAxis;
import org.jaxen.expr.iter.IterablePrecedingSiblingAxis;
import org.jaxen.expr.iter.IterableSelfAxis;

public class DefaultXPathFactory implements XPathFactory {
    public XPathExpr createXPath(Expr expr) throws JaxenException {
        return new DefaultXPathExpr(expr);
    }

    public PathExpr createPathExpr(FilterExpr filterExpr, LocationPath locationPath) throws JaxenException {
        return new DefaultPathExpr(filterExpr, locationPath);
    }

    public LocationPath createRelativeLocationPath() throws JaxenException {
        return new DefaultRelativeLocationPath();
    }

    public LocationPath createAbsoluteLocationPath() throws JaxenException {
        return new DefaultAbsoluteLocationPath();
    }

    public BinaryExpr createOrExpr(Expr expr, Expr expr2) throws JaxenException {
        return new DefaultOrExpr(expr, expr2);
    }

    public BinaryExpr createAndExpr(Expr expr, Expr expr2) throws JaxenException {
        return new DefaultAndExpr(expr, expr2);
    }

    public BinaryExpr createEqualityExpr(Expr expr, Expr expr2, int i) throws JaxenException {
        if (i == 1) {
            return new DefaultEqualsExpr(expr, expr2);
        }
        if (i == 2) {
            return new DefaultNotEqualsExpr(expr, expr2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unhandled operator in createEqualityExpr(): ");
        stringBuffer.append(i);
        throw new JaxenException(stringBuffer.toString());
    }

    public BinaryExpr createRelationalExpr(Expr expr, Expr expr2, int i) throws JaxenException {
        if (i == 3) {
            return new DefaultLessThanExpr(expr, expr2);
        }
        if (i == 4) {
            return new DefaultLessThanEqualExpr(expr, expr2);
        }
        if (i == 5) {
            return new DefaultGreaterThanExpr(expr, expr2);
        }
        if (i == 6) {
            return new DefaultGreaterThanEqualExpr(expr, expr2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unhandled operator in createRelationalExpr(): ");
        stringBuffer.append(i);
        throw new JaxenException(stringBuffer.toString());
    }

    public BinaryExpr createAdditiveExpr(Expr expr, Expr expr2, int i) throws JaxenException {
        if (i == 7) {
            return new DefaultPlusExpr(expr, expr2);
        }
        if (i == 8) {
            return new DefaultMinusExpr(expr, expr2);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unhandled operator in createAdditiveExpr(): ");
        stringBuffer.append(i);
        throw new JaxenException(stringBuffer.toString());
    }

    public BinaryExpr createMultiplicativeExpr(Expr expr, Expr expr2, int i) throws JaxenException {
        switch (i) {
            case 9:
                return new DefaultMultiplyExpr(expr, expr2);
            case 10:
                return new DefaultModExpr(expr, expr2);
            case 11:
                return new DefaultDivExpr(expr, expr2);
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unhandled operator in createMultiplicativeExpr(): ");
                stringBuffer.append(i);
                throw new JaxenException(stringBuffer.toString());
        }
    }

    public Expr createUnaryExpr(Expr expr, int i) throws JaxenException {
        return i != 12 ? expr : new DefaultUnaryExpr(expr);
    }

    public UnionExpr createUnionExpr(Expr expr, Expr expr2) throws JaxenException {
        return new DefaultUnionExpr(expr, expr2);
    }

    public FilterExpr createFilterExpr(Expr expr) throws JaxenException {
        return new DefaultFilterExpr(expr, createPredicateSet());
    }

    public FunctionCallExpr createFunctionCallExpr(String str, String str2) throws JaxenException {
        return new DefaultFunctionCallExpr(str, str2);
    }

    public NumberExpr createNumberExpr(int i) throws JaxenException {
        return new DefaultNumberExpr(new Double((double) i));
    }

    public NumberExpr createNumberExpr(double d) throws JaxenException {
        return new DefaultNumberExpr(new Double(d));
    }

    public LiteralExpr createLiteralExpr(String str) throws JaxenException {
        return new DefaultLiteralExpr(str);
    }

    public VariableReferenceExpr createVariableReferenceExpr(String str, String str2) throws JaxenException {
        return new DefaultVariableReferenceExpr(str, str2);
    }

    public Step createNameStep(int i, String str, String str2) throws JaxenException {
        return new DefaultNameStep(getIterableAxis(i), str, str2, createPredicateSet());
    }

    public Step createTextNodeStep(int i) throws JaxenException {
        return new DefaultTextNodeStep(getIterableAxis(i), createPredicateSet());
    }

    public Step createCommentNodeStep(int i) throws JaxenException {
        return new DefaultCommentNodeStep(getIterableAxis(i), createPredicateSet());
    }

    public Step createAllNodeStep(int i) throws JaxenException {
        return new DefaultAllNodeStep(getIterableAxis(i), createPredicateSet());
    }

    public Step createProcessingInstructionNodeStep(int i, String str) throws JaxenException {
        return new DefaultProcessingInstructionNodeStep(getIterableAxis(i), str, createPredicateSet());
    }

    public Predicate createPredicate(Expr expr) throws JaxenException {
        return new DefaultPredicate(expr);
    }

    /* access modifiers changed from: protected */
    public IterableAxis getIterableAxis(int i) throws JaxenException {
        switch (i) {
            case 1:
                return new IterableChildAxis(i);
            case 2:
                return new IterableDescendantAxis(i);
            case 3:
                return new IterableParentAxis(i);
            case 4:
                return new IterableAncestorAxis(i);
            case 5:
                return new IterableFollowingSiblingAxis(i);
            case 6:
                return new IterablePrecedingSiblingAxis(i);
            case 7:
                return new IterableFollowingAxis(i);
            case 8:
                return new IterablePrecedingAxis(i);
            case 9:
                return new IterableAttributeAxis(i);
            case 10:
                return new IterableNamespaceAxis(i);
            case 11:
                return new IterableSelfAxis(i);
            case 12:
                return new IterableDescendantOrSelfAxis(i);
            case 13:
                return new IterableAncestorOrSelfAxis(i);
            default:
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Unrecognized axis code: ");
                stringBuffer.append(i);
                throw new JaxenException(stringBuffer.toString());
        }
    }

    public PredicateSet createPredicateSet() throws JaxenException {
        return new PredicateSet();
    }
}
