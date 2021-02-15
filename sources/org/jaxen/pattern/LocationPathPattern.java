package org.jaxen.pattern;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.expr.FilterExpr;
import org.jaxen.util.SingletonList;

public class LocationPathPattern extends Pattern {
    private boolean absolute;
    private Pattern ancestorPattern;
    private List filters;
    private NodeTest nodeTest = AnyNodeTest.getInstance();
    private Pattern parentPattern;

    public LocationPathPattern() {
    }

    public LocationPathPattern(NodeTest nodeTest2) {
        this.nodeTest = nodeTest2;
    }

    public Pattern simplify() {
        Pattern pattern = this.parentPattern;
        if (pattern != null) {
            this.parentPattern = pattern.simplify();
        }
        Pattern pattern2 = this.ancestorPattern;
        if (pattern2 != null) {
            this.ancestorPattern = pattern2.simplify();
        }
        if (this.filters == null) {
            if (this.parentPattern == null && this.ancestorPattern == null) {
                return this.nodeTest;
            }
            Pattern pattern3 = this.parentPattern;
            return (pattern3 == null || this.ancestorPattern != null || !(this.nodeTest instanceof AnyNodeTest)) ? this : pattern3;
        }
    }

    public void addFilter(FilterExpr filterExpr) {
        if (this.filters == null) {
            this.filters = new ArrayList();
        }
        this.filters.add(filterExpr);
    }

    public void setParentPattern(Pattern pattern) {
        this.parentPattern = pattern;
    }

    public void setAncestorPattern(Pattern pattern) {
        this.ancestorPattern = pattern;
    }

    public void setNodeTest(NodeTest nodeTest2) throws JaxenException {
        if (this.nodeTest instanceof AnyNodeTest) {
            this.nodeTest = nodeTest2;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Attempt to overwrite nodeTest: ");
        stringBuffer.append(this.nodeTest);
        stringBuffer.append(" with: ");
        stringBuffer.append(nodeTest2);
        throw new JaxenException(stringBuffer.toString());
    }

    public boolean matches(Object obj, Context context) throws JaxenException {
        Object parentNode;
        Navigator navigator = context.getNavigator();
        boolean z = false;
        if (!this.nodeTest.matches(obj, context)) {
            return false;
        }
        if (this.parentPattern != null && ((parentNode = navigator.getParentNode(obj)) == null || !this.parentPattern.matches(parentNode, context))) {
            return false;
        }
        if (this.ancestorPattern != null) {
            for (Object parentNode2 = navigator.getParentNode(obj); !this.ancestorPattern.matches(parentNode2, context); parentNode2 = navigator.getParentNode(parentNode2)) {
                if (parentNode2 == null || navigator.isDocument(parentNode2)) {
                    return false;
                }
            }
        }
        if (this.filters == null) {
            return true;
        }
        SingletonList singletonList = new SingletonList(obj);
        context.setNodeSet(singletonList);
        Iterator it = this.filters.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!((FilterExpr) it.next()).asBoolean(context)) {
                    break;
                }
            } else {
                z = true;
                break;
            }
        }
        context.setNodeSet(singletonList);
        return z;
    }

    public double getPriority() {
        if (this.filters != null) {
            return 0.5d;
        }
        return this.nodeTest.getPriority();
    }

    public short getMatchType() {
        return this.nodeTest.getMatchType();
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.absolute) {
            stringBuffer.append("/");
        }
        Pattern pattern = this.ancestorPattern;
        if (pattern != null) {
            String text = pattern.getText();
            if (text.length() > 0) {
                stringBuffer.append(text);
                stringBuffer.append("//");
            }
        }
        Pattern pattern2 = this.parentPattern;
        if (pattern2 != null) {
            String text2 = pattern2.getText();
            if (text2.length() > 0) {
                stringBuffer.append(text2);
                stringBuffer.append("/");
            }
        }
        stringBuffer.append(this.nodeTest.getText());
        if (this.filters != null) {
            stringBuffer.append("[");
            for (FilterExpr text3 : this.filters) {
                stringBuffer.append(text3.getText());
            }
            stringBuffer.append("]");
        }
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append("[ absolute: ");
        stringBuffer.append(this.absolute);
        stringBuffer.append(" parent: ");
        stringBuffer.append(this.parentPattern);
        stringBuffer.append(" ancestor: ");
        stringBuffer.append(this.ancestorPattern);
        stringBuffer.append(" filters: ");
        stringBuffer.append(this.filters);
        stringBuffer.append(" nodeTest: ");
        stringBuffer.append(this.nodeTest);
        stringBuffer.append(" ]");
        return stringBuffer.toString();
    }

    public boolean isAbsolute() {
        return this.absolute;
    }

    public void setAbsolute(boolean z) {
        this.absolute = z;
    }

    public boolean hasAnyNodeTest() {
        return this.nodeTest instanceof AnyNodeTest;
    }
}
