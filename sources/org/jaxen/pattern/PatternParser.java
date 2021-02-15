package org.jaxen.pattern;

import java.util.List;
import java.util.ListIterator;
import org.jaxen.JaxenException;
import org.jaxen.JaxenHandler;
import org.jaxen.expr.DefaultAllNodeStep;
import org.jaxen.expr.DefaultCommentNodeStep;
import org.jaxen.expr.DefaultFilterExpr;
import org.jaxen.expr.DefaultNameStep;
import org.jaxen.expr.DefaultProcessingInstructionNodeStep;
import org.jaxen.expr.DefaultStep;
import org.jaxen.expr.DefaultTextNodeStep;
import org.jaxen.expr.DefaultXPathFactory;
import org.jaxen.expr.Expr;
import org.jaxen.expr.FilterExpr;
import org.jaxen.expr.LocationPath;
import org.jaxen.expr.Predicate;
import org.jaxen.expr.PredicateSet;
import org.jaxen.expr.Step;
import org.jaxen.expr.UnionExpr;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.saxpath.XPathReader;
import org.jaxen.saxpath.helpers.XPathReaderFactory;

public class PatternParser {
    private static final boolean TRACE = false;
    private static final boolean USE_HANDLER = false;
    static /* synthetic */ Class class$org$jaxen$expr$DefaultStep;

    public static Pattern parse(String str) throws JaxenException, SAXPathException {
        XPathReader createReader = XPathReaderFactory.createReader();
        JaxenHandler jaxenHandler = new JaxenHandler();
        jaxenHandler.setXPathFactory(new DefaultXPathFactory());
        createReader.setXPathHandler(jaxenHandler);
        createReader.parse(str);
        return convertExpr(jaxenHandler.getXPathExpr().getRootExpr()).simplify();
    }

    protected static Pattern convertExpr(Expr expr) throws JaxenException {
        if (expr instanceof LocationPath) {
            return convertExpr((LocationPath) expr);
        }
        if (expr instanceof FilterExpr) {
            LocationPathPattern locationPathPattern = new LocationPathPattern();
            locationPathPattern.addFilter((FilterExpr) expr);
            return locationPathPattern;
        } else if (expr instanceof UnionExpr) {
            UnionExpr unionExpr = (UnionExpr) expr;
            return new UnionPattern(convertExpr(unionExpr.getLHS()), convertExpr(unionExpr.getRHS()));
        } else {
            LocationPathPattern locationPathPattern2 = new LocationPathPattern();
            locationPathPattern2.addFilter(new DefaultFilterExpr(expr, new PredicateSet()));
            return locationPathPattern2;
        }
    }

    protected static LocationPathPattern convertExpr(LocationPath locationPath) throws JaxenException {
        LocationPathPattern locationPathPattern = new LocationPathPattern();
        List steps = locationPath.getSteps();
        ListIterator listIterator = steps.listIterator(steps.size());
        boolean z = true;
        LocationPathPattern locationPathPattern2 = locationPathPattern;
        while (listIterator.hasPrevious()) {
            Step step = (Step) listIterator.previous();
            if (z) {
                z = false;
                locationPathPattern2 = convertStep(locationPathPattern2, step);
            } else {
                if (navigationStep(step)) {
                    LocationPathPattern locationPathPattern3 = new LocationPathPattern();
                    int axis = step.getAxis();
                    if (axis == 2 || axis == 12) {
                        locationPathPattern2.setAncestorPattern(locationPathPattern3);
                    } else {
                        locationPathPattern2.setParentPattern(locationPathPattern3);
                    }
                    locationPathPattern2 = locationPathPattern3;
                }
                locationPathPattern2 = convertStep(locationPathPattern2, step);
            }
        }
        if (locationPath.isAbsolute()) {
            locationPathPattern2.setParentPattern(new LocationPathPattern(NodeTypeTest.DOCUMENT_TEST));
        }
        return locationPathPattern;
    }

    protected static LocationPathPattern convertStep(LocationPathPattern locationPathPattern, Step step) throws JaxenException {
        if (!(step instanceof DefaultAllNodeStep)) {
            boolean z = step instanceof DefaultCommentNodeStep;
            if (z) {
                locationPathPattern.setNodeTest(NodeTypeTest.COMMENT_TEST);
            } else if (step instanceof DefaultProcessingInstructionNodeStep) {
                locationPathPattern.setNodeTest(NodeTypeTest.PROCESSING_INSTRUCTION_TEST);
            } else if (step instanceof DefaultTextNodeStep) {
                locationPathPattern.setNodeTest(TextNodeTest.SINGLETON);
            } else if (z) {
                locationPathPattern.setNodeTest(NodeTypeTest.COMMENT_TEST);
            } else if (step instanceof DefaultNameStep) {
                DefaultNameStep defaultNameStep = (DefaultNameStep) step;
                String localName = defaultNameStep.getLocalName();
                String prefix = defaultNameStep.getPrefix();
                int axis = defaultNameStep.getAxis();
                short s = 1;
                if (axis == 9) {
                    s = 2;
                }
                if (!defaultNameStep.isMatchesAnyName()) {
                    locationPathPattern.setNodeTest(new NameTest(localName, s));
                } else if (prefix.length() != 0 && !prefix.equals("*")) {
                    locationPathPattern.setNodeTest(new NamespaceTest(prefix, s));
                } else if (axis == 9) {
                    locationPathPattern.setNodeTest(NodeTypeTest.ATTRIBUTE_TEST);
                } else {
                    locationPathPattern.setNodeTest(NodeTypeTest.ELEMENT_TEST);
                }
                return convertDefaultStep(locationPathPattern, defaultNameStep);
            } else if (step instanceof DefaultStep) {
                return convertDefaultStep(locationPathPattern, (DefaultStep) step);
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Cannot convert: ");
                stringBuffer.append(step);
                stringBuffer.append(" to a Pattern");
                throw new JaxenException(stringBuffer.toString());
            }
        } else if (step.getAxis() == 9) {
            locationPathPattern.setNodeTest(NodeTypeTest.ATTRIBUTE_TEST);
        } else {
            locationPathPattern.setNodeTest(NodeTypeTest.ELEMENT_TEST);
        }
        return locationPathPattern;
    }

    protected static LocationPathPattern convertDefaultStep(LocationPathPattern locationPathPattern, DefaultStep defaultStep) throws JaxenException {
        List<Predicate> predicates = defaultStep.getPredicates();
        if (!predicates.isEmpty()) {
            DefaultFilterExpr defaultFilterExpr = new DefaultFilterExpr(new PredicateSet());
            for (Predicate addPredicate : predicates) {
                defaultFilterExpr.addPredicate(addPredicate);
            }
            locationPathPattern.addFilter(defaultFilterExpr);
        }
        return locationPathPattern;
    }

    protected static boolean navigationStep(Step step) {
        if (step instanceof DefaultNameStep) {
            return true;
        }
        Class<?> cls = step.getClass();
        Class cls2 = class$org$jaxen$expr$DefaultStep;
        if (cls2 == null) {
            cls2 = class$("org.jaxen.expr.DefaultStep");
            class$org$jaxen$expr$DefaultStep = cls2;
        }
        if (cls.equals(cls2)) {
            return !step.getPredicates().isEmpty();
        }
        return true;
    }

    static /* synthetic */ Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }
}
