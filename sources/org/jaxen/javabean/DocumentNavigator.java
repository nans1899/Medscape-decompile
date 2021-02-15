package org.jaxen.javabean;

import java.util.Iterator;
import org.jaxen.DefaultNavigator;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenConstants;
import org.jaxen.NamedAccessNavigator;
import org.jaxen.Navigator;
import org.jaxen.XPath;
import org.jaxen.saxpath.SAXPathException;
import org.jaxen.util.SingleObjectIterator;

public class DocumentNavigator extends DefaultNavigator implements NamedAccessNavigator {
    private static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static final DocumentNavigator instance = new DocumentNavigator();
    private static final long serialVersionUID = -1768605107626726499L;

    public String getAttributeName(Object obj) {
        return "";
    }

    public String getAttributeNamespaceUri(Object obj) {
        return "";
    }

    public String getAttributeQName(Object obj) {
        return "";
    }

    public String getCommentStringValue(Object obj) {
        return null;
    }

    public Object getDocument(String str) throws FunctionCallException {
        return null;
    }

    public Object getDocumentNode(Object obj) {
        return null;
    }

    public String getElementNamespaceUri(Object obj) {
        return "";
    }

    public String getElementQName(Object obj) {
        return "";
    }

    public String getNamespacePrefix(Object obj) {
        return null;
    }

    public short getNodeType(Object obj) {
        return 0;
    }

    public String getProcessingInstructionData(Object obj) {
        return null;
    }

    public String getProcessingInstructionTarget(Object obj) {
        return null;
    }

    public boolean isAttribute(Object obj) {
        return false;
    }

    public boolean isComment(Object obj) {
        return false;
    }

    public boolean isDocument(Object obj) {
        return false;
    }

    public boolean isNamespace(Object obj) {
        return false;
    }

    public boolean isProcessingInstruction(Object obj) {
        return false;
    }

    public String translateNamespacePrefixToUri(String str, Object obj) {
        return null;
    }

    public static Navigator getInstance() {
        return instance;
    }

    public boolean isElement(Object obj) {
        return obj instanceof Element;
    }

    public boolean isText(Object obj) {
        return obj instanceof String;
    }

    public String getElementName(Object obj) {
        return ((Element) obj).getName();
    }

    public Iterator getChildAxisIterator(Object obj) {
        return JaxenConstants.EMPTY_ITERATOR;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:3|4) */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r1 = new java.lang.StringBuffer();
        r1.append("get");
        r1.append(r0);
        r1.append(com.appboy.Constants.APPBOY_PUSH_SUMMARY_TEXT_KEY);
        r6 = r7.getMethod(r1.toString(), EMPTY_CLASS_ARRAY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
        r6 = r7.getMethod(r5, EMPTY_CLASS_ARRAY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0049, code lost:
        r6 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0027 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0042 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Iterator getChildAxisIterator(java.lang.Object r4, java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            r3 = this;
            java.lang.String r6 = "get"
            r7 = r4
            org.jaxen.javabean.Element r7 = (org.jaxen.javabean.Element) r7
            java.lang.Object r7 = r7.getObject()
            java.lang.Class r7 = r7.getClass()
            java.lang.String r0 = r3.javacase(r5)
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ NoSuchMethodException -> 0x0027 }
            r1.<init>()     // Catch:{ NoSuchMethodException -> 0x0027 }
            r1.append(r6)     // Catch:{ NoSuchMethodException -> 0x0027 }
            r1.append(r0)     // Catch:{ NoSuchMethodException -> 0x0027 }
            java.lang.String r1 = r1.toString()     // Catch:{ NoSuchMethodException -> 0x0027 }
            java.lang.Class[] r2 = EMPTY_CLASS_ARRAY     // Catch:{ NoSuchMethodException -> 0x0027 }
            java.lang.reflect.Method r6 = r7.getMethod(r1, r2)     // Catch:{ NoSuchMethodException -> 0x0027 }
            goto L_0x004a
        L_0x0027:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ NoSuchMethodException -> 0x0042 }
            r1.<init>()     // Catch:{ NoSuchMethodException -> 0x0042 }
            r1.append(r6)     // Catch:{ NoSuchMethodException -> 0x0042 }
            r1.append(r0)     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.String r6 = "s"
            r1.append(r6)     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.String r6 = r1.toString()     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.Class[] r0 = EMPTY_CLASS_ARRAY     // Catch:{ NoSuchMethodException -> 0x0042 }
            java.lang.reflect.Method r6 = r7.getMethod(r6, r0)     // Catch:{ NoSuchMethodException -> 0x0042 }
            goto L_0x004a
        L_0x0042:
            java.lang.Class[] r6 = EMPTY_CLASS_ARRAY     // Catch:{ NoSuchMethodException -> 0x0049 }
            java.lang.reflect.Method r6 = r7.getMethod(r5, r6)     // Catch:{ NoSuchMethodException -> 0x0049 }
            goto L_0x004a
        L_0x0049:
            r6 = 0
        L_0x004a:
            if (r6 != 0) goto L_0x004f
            java.util.Iterator r4 = org.jaxen.JaxenConstants.EMPTY_ITERATOR
            return r4
        L_0x004f:
            r7 = r4
            org.jaxen.javabean.Element r7 = (org.jaxen.javabean.Element) r7     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            java.lang.Object r7 = r7.getObject()     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            java.lang.Object[] r0 = EMPTY_OBJECT_ARRAY     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            java.lang.Object r6 = r6.invoke(r7, r0)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            if (r6 != 0) goto L_0x0061
            java.util.Iterator r4 = org.jaxen.JaxenConstants.EMPTY_ITERATOR     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            return r4
        L_0x0061:
            boolean r7 = r6 instanceof java.util.Collection     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            if (r7 == 0) goto L_0x0073
            org.jaxen.javabean.ElementIterator r7 = new org.jaxen.javabean.ElementIterator     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            org.jaxen.javabean.Element r4 = (org.jaxen.javabean.Element) r4     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            java.util.Collection r6 = (java.util.Collection) r6     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            r7.<init>(r4, r5, r6)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            return r7
        L_0x0073:
            java.lang.Class r7 = r6.getClass()     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            boolean r7 = r7.isArray()     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            if (r7 == 0) goto L_0x0080
            java.util.Iterator r4 = org.jaxen.JaxenConstants.EMPTY_ITERATOR     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            return r4
        L_0x0080:
            org.jaxen.util.SingleObjectIterator r7 = new org.jaxen.util.SingleObjectIterator     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            org.jaxen.javabean.Element r0 = new org.jaxen.javabean.Element     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            org.jaxen.javabean.Element r4 = (org.jaxen.javabean.Element) r4     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            r0.<init>(r4, r5, r6)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            r7.<init>(r0)     // Catch:{ IllegalAccessException | InvocationTargetException -> 0x008d }
            return r7
        L_0x008d:
            java.util.Iterator r4 = org.jaxen.JaxenConstants.EMPTY_ITERATOR
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaxen.javabean.DocumentNavigator.getChildAxisIterator(java.lang.Object, java.lang.String, java.lang.String, java.lang.String):java.util.Iterator");
    }

    public Iterator getParentAxisIterator(Object obj) {
        if (obj instanceof Element) {
            return new SingleObjectIterator(((Element) obj).getParent());
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getAttributeAxisIterator(Object obj) {
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getAttributeAxisIterator(Object obj, String str, String str2, String str3) {
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Iterator getNamespaceAxisIterator(Object obj) {
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public Object getParentNode(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getParent();
        }
        return JaxenConstants.EMPTY_ITERATOR;
    }

    public String getTextStringValue(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getObject().toString();
        }
        return obj.toString();
    }

    public String getElementStringValue(Object obj) {
        if (obj instanceof Element) {
            return ((Element) obj).getObject().toString();
        }
        return obj.toString();
    }

    public String getAttributeStringValue(Object obj) {
        return obj.toString();
    }

    public String getNamespaceStringValue(Object obj) {
        return obj.toString();
    }

    public XPath parseXPath(String str) throws SAXPathException {
        return new JavaBeanXPath(str);
    }

    /* access modifiers changed from: protected */
    public String javacase(String str) {
        if (str.length() == 0) {
            return str;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str.substring(0, 1).toUpperCase());
        stringBuffer.append(str.substring(1));
        return stringBuffer.toString();
    }
}
