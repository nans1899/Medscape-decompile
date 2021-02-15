package org.jaxen.saxpath;

import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import org.jaxen.JaxenRuntimeException;

public class Axis {
    public static final int ANCESTOR = 4;
    public static final int ANCESTOR_OR_SELF = 13;
    public static final int ATTRIBUTE = 9;
    public static final int CHILD = 1;
    public static final int DESCENDANT = 2;
    public static final int DESCENDANT_OR_SELF = 12;
    public static final int FOLLOWING = 7;
    public static final int FOLLOWING_SIBLING = 5;
    public static final int INVALID_AXIS = 0;
    public static final int NAMESPACE = 10;
    public static final int PARENT = 3;
    public static final int PRECEDING = 8;
    public static final int PRECEDING_SIBLING = 6;
    public static final int SELF = 11;

    private Axis() {
    }

    public static String lookup(int i) {
        switch (i) {
            case 1:
                return "child";
            case 2:
                return "descendant";
            case 3:
                return "parent";
            case 4:
                return "ancestor";
            case 5:
                return "following-sibling";
            case 6:
                return "preceding-sibling";
            case 7:
                return "following";
            case 8:
                return "preceding";
            case 9:
                return "attribute";
            case 10:
                return "namespace";
            case 11:
                return JSONAPISpecConstants.SELF;
            case 12:
                return "descendant-or-self";
            case 13:
                return "ancestor-or-self";
            default:
                throw new JaxenRuntimeException("Illegal Axis Number");
        }
    }

    public static int lookup(String str) {
        if ("child".equals(str)) {
            return 1;
        }
        if ("descendant".equals(str)) {
            return 2;
        }
        if ("parent".equals(str)) {
            return 3;
        }
        if ("ancestor".equals(str)) {
            return 4;
        }
        if ("following-sibling".equals(str)) {
            return 5;
        }
        if ("preceding-sibling".equals(str)) {
            return 6;
        }
        if ("following".equals(str)) {
            return 7;
        }
        if ("preceding".equals(str)) {
            return 8;
        }
        if ("attribute".equals(str)) {
            return 9;
        }
        if ("namespace".equals(str)) {
            return 10;
        }
        if (JSONAPISpecConstants.SELF.equals(str)) {
            return 11;
        }
        if ("descendant-or-self".equals(str)) {
            return 12;
        }
        return "ancestor-or-self".equals(str) ? 13 : 0;
    }
}
