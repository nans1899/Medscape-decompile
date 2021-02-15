package org.jaxen.expr;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.JaxenException;
import org.jaxen.Navigator;
import org.jaxen.UnresolvableException;
import org.jaxen.expr.iter.IterableAxis;

public class DefaultNameStep extends DefaultStep implements NameStep {
    private static final long serialVersionUID = 428414912247718390L;
    private boolean hasPrefix;
    private String localName;
    private boolean matchesAnyName;
    private String prefix;

    public DefaultNameStep(IterableAxis iterableAxis, String str, String str2, PredicateSet predicateSet) {
        super(iterableAxis, predicateSet);
        this.prefix = str;
        this.localName = str2;
        this.matchesAnyName = "*".equals(str2);
        String str3 = this.prefix;
        this.hasPrefix = str3 != null && str3.length() > 0;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getLocalName() {
        return this.localName;
    }

    public boolean isMatchesAnyName() {
        return this.matchesAnyName;
    }

    public String getText() {
        StringBuffer stringBuffer = new StringBuffer(64);
        stringBuffer.append(getAxisName());
        stringBuffer.append("::");
        if (getPrefix() != null && getPrefix().length() > 0) {
            stringBuffer.append(getPrefix());
            stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        }
        stringBuffer.append(getLocalName());
        stringBuffer.append(super.getText());
        return stringBuffer.toString();
    }

    public List evaluate(Context context) throws JaxenException {
        String str;
        List nodeSet = context.getNodeSet();
        int size = nodeSet.size();
        if (size == 0) {
            return Collections.EMPTY_LIST;
        }
        ContextSupport contextSupport = context.getContextSupport();
        IterableAxis iterableAxis = getIterableAxis();
        boolean z = !this.matchesAnyName && iterableAxis.supportsNamedAccess(contextSupport);
        String str2 = null;
        if (size == 1) {
            Object obj = nodeSet.get(0);
            if (z) {
                if (this.hasPrefix) {
                    String translateNamespacePrefixToUri = contextSupport.translateNamespacePrefixToUri(this.prefix);
                    if (translateNamespacePrefixToUri != null) {
                        str = translateNamespacePrefixToUri;
                    } else {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("XPath expression uses unbound namespace prefix ");
                        stringBuffer.append(this.prefix);
                        throw new UnresolvableException(stringBuffer.toString());
                    }
                } else {
                    str = null;
                }
                Iterator namedAccessIterator = iterableAxis.namedAccessIterator(obj, contextSupport, this.localName, this.prefix, str);
                if (namedAccessIterator == null || !namedAccessIterator.hasNext()) {
                    return Collections.EMPTY_LIST;
                }
                ArrayList arrayList = new ArrayList();
                while (namedAccessIterator.hasNext()) {
                    arrayList.add(namedAccessIterator.next());
                }
                return getPredicateSet().evaluatePredicates(arrayList, contextSupport);
            }
            Iterator it = iterableAxis.iterator(obj, contextSupport);
            if (it == null || !it.hasNext()) {
                return Collections.EMPTY_LIST;
            }
            ArrayList arrayList2 = new ArrayList(size);
            while (it.hasNext()) {
                Object next = it.next();
                if (matches(next, contextSupport)) {
                    arrayList2.add(next);
                }
            }
            return getPredicateSet().evaluatePredicates(arrayList2, contextSupport);
        }
        IdentitySet identitySet = new IdentitySet();
        ArrayList arrayList3 = new ArrayList(size);
        ArrayList arrayList4 = new ArrayList(size);
        if (!z) {
            for (int i = 0; i < size; i++) {
                Iterator axisIterator = axisIterator(nodeSet.get(i), contextSupport);
                if (axisIterator != null && axisIterator.hasNext()) {
                    while (axisIterator.hasNext()) {
                        Object next2 = axisIterator.next();
                        if (matches(next2, contextSupport)) {
                            arrayList3.add(next2);
                        }
                    }
                    for (Object next3 : getPredicateSet().evaluatePredicates(arrayList3, contextSupport)) {
                        if (!identitySet.contains(next3)) {
                            identitySet.add(next3);
                            arrayList4.add(next3);
                        }
                    }
                    arrayList3.clear();
                }
            }
        } else if (!this.hasPrefix || (str2 = contextSupport.translateNamespacePrefixToUri(this.prefix)) != null) {
            String str3 = str2;
            for (int i2 = 0; i2 < size; i2++) {
                Iterator namedAccessIterator2 = iterableAxis.namedAccessIterator(nodeSet.get(i2), contextSupport, this.localName, this.prefix, str3);
                if (namedAccessIterator2 != null && namedAccessIterator2.hasNext()) {
                    while (namedAccessIterator2.hasNext()) {
                        arrayList3.add(namedAccessIterator2.next());
                    }
                    for (Object next4 : getPredicateSet().evaluatePredicates(arrayList3, contextSupport)) {
                        if (!identitySet.contains(next4)) {
                            identitySet.add(next4);
                            arrayList4.add(next4);
                        }
                    }
                    arrayList3.clear();
                }
            }
        } else {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("XPath expression uses unbound namespace prefix ");
            stringBuffer2.append(this.prefix);
            throw new UnresolvableException(stringBuffer2.toString());
        }
        return arrayList4;
    }

    public boolean matches(Object obj, ContextSupport contextSupport) throws JaxenException {
        String str;
        String str2;
        Navigator navigator = contextSupport.getNavigator();
        String str3 = null;
        if (navigator.isElement(obj)) {
            str2 = navigator.getElementName(obj);
            str = navigator.getElementNamespaceUri(obj);
        } else if (navigator.isText(obj)) {
            return false;
        } else {
            if (navigator.isAttribute(obj)) {
                if (getAxis() != 9) {
                    return false;
                }
                str2 = navigator.getAttributeName(obj);
                str = navigator.getAttributeNamespaceUri(obj);
            } else if (navigator.isDocument(obj) || !navigator.isNamespace(obj) || getAxis() != 10) {
                return false;
            } else {
                str2 = navigator.getNamespacePrefix(obj);
                str = null;
            }
        }
        if (this.hasPrefix) {
            str3 = contextSupport.translateNamespacePrefixToUri(this.prefix);
            if (str3 == null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Cannot resolve namespace prefix '");
                stringBuffer.append(this.prefix);
                stringBuffer.append("'");
                throw new UnresolvableException(stringBuffer.toString());
            }
        } else if (this.matchesAnyName) {
            return true;
        }
        if (hasNamespace(str3) != hasNamespace(str)) {
            return false;
        }
        if (this.matchesAnyName || str2.equals(getLocalName())) {
            return matchesNamespaceURIs(str3, str);
        }
        return false;
    }

    private boolean hasNamespace(String str) {
        return str != null && str.length() > 0;
    }

    /* access modifiers changed from: protected */
    public boolean matchesNamespaceURIs(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null) {
            return str2.length() == 0;
        }
        if (str2 != null) {
            return str.equals(str2);
        }
        if (str.length() == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
        if ("".equals(getPrefix())) {
            str = getLocalName();
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getPrefix());
            stringBuffer.append(":");
            stringBuffer.append(getLocalName());
            str = stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("[(DefaultNameStep): ");
        stringBuffer2.append(str);
        stringBuffer2.append("]");
        return stringBuffer2.toString();
    }
}
