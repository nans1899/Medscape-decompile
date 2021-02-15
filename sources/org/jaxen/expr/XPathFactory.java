package org.jaxen.expr;

import org.jaxen.JaxenException;

public interface XPathFactory {
    LocationPath createAbsoluteLocationPath() throws JaxenException;

    BinaryExpr createAdditiveExpr(Expr expr, Expr expr2, int i) throws JaxenException;

    Step createAllNodeStep(int i) throws JaxenException;

    BinaryExpr createAndExpr(Expr expr, Expr expr2) throws JaxenException;

    Step createCommentNodeStep(int i) throws JaxenException;

    BinaryExpr createEqualityExpr(Expr expr, Expr expr2, int i) throws JaxenException;

    FilterExpr createFilterExpr(Expr expr) throws JaxenException;

    FunctionCallExpr createFunctionCallExpr(String str, String str2) throws JaxenException;

    LiteralExpr createLiteralExpr(String str) throws JaxenException;

    BinaryExpr createMultiplicativeExpr(Expr expr, Expr expr2, int i) throws JaxenException;

    Step createNameStep(int i, String str, String str2) throws JaxenException;

    NumberExpr createNumberExpr(double d) throws JaxenException;

    NumberExpr createNumberExpr(int i) throws JaxenException;

    BinaryExpr createOrExpr(Expr expr, Expr expr2) throws JaxenException;

    PathExpr createPathExpr(FilterExpr filterExpr, LocationPath locationPath) throws JaxenException;

    Predicate createPredicate(Expr expr) throws JaxenException;

    PredicateSet createPredicateSet() throws JaxenException;

    Step createProcessingInstructionNodeStep(int i, String str) throws JaxenException;

    BinaryExpr createRelationalExpr(Expr expr, Expr expr2, int i) throws JaxenException;

    LocationPath createRelativeLocationPath() throws JaxenException;

    Step createTextNodeStep(int i) throws JaxenException;

    Expr createUnaryExpr(Expr expr, int i) throws JaxenException;

    UnionExpr createUnionExpr(Expr expr, Expr expr2) throws JaxenException;

    VariableReferenceExpr createVariableReferenceExpr(String str, String str2) throws JaxenException;

    XPathExpr createXPath(Expr expr) throws JaxenException;
}
