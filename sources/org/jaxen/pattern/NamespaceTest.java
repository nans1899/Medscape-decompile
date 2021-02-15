package org.jaxen.pattern;

import org.jaxen.Context;
import org.jaxen.Navigator;

public class NamespaceTest extends NodeTest {
    private short nodeType;
    private String prefix;

    public double getPriority() {
        return -0.25d;
    }

    public NamespaceTest(String str, short s) {
        this.prefix = str == null ? "" : str;
        this.nodeType = s;
    }

    public boolean matches(Object obj, Context context) {
        Navigator navigator = context.getNavigator();
        String uri = getURI(obj, context);
        short s = this.nodeType;
        if (s == 1) {
            if (!navigator.isElement(obj) || !uri.equals(navigator.getElementNamespaceUri(obj))) {
                return false;
            }
            return true;
        } else if (s != 2 || !navigator.isAttribute(obj) || !uri.equals(navigator.getAttributeNamespaceUri(obj))) {
            return false;
        } else {
            return true;
        }
    }

    public short getMatchType() {
        return this.nodeType;
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.prefix);
        stringBuffer.append(":");
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append("[ prefix: ");
        stringBuffer.append(this.prefix);
        stringBuffer.append(" type: ");
        stringBuffer.append(this.nodeType);
        stringBuffer.append(" ]");
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public String getURI(Object obj, Context context) {
        String translateNamespacePrefixToUri = context.getNavigator().translateNamespacePrefixToUri(this.prefix, obj);
        if (translateNamespacePrefixToUri == null) {
            translateNamespacePrefixToUri = context.getContextSupport().translateNamespacePrefixToUri(this.prefix);
        }
        return translateNamespacePrefixToUri == null ? "" : translateNamespacePrefixToUri;
    }
}
