package org.jaxen.function;

import java.util.Iterator;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class LangFunction implements Function {
    private static final String LANG_LOCALNAME = "lang";
    private static final String XMLNS_URI = "http://www.w3.org/XML/1998/namespace";

    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 1) {
            try {
                return evaluate(context.getNodeSet(), list.get(0), context.getNavigator());
            } catch (UnsupportedAxisException e) {
                throw new FunctionCallException("Can't evaluate lang()", e);
            }
        } else {
            throw new FunctionCallException("lang() requires exactly one argument.");
        }
    }

    private static Boolean evaluate(List list, Object obj, Navigator navigator) throws UnsupportedAxisException {
        return evaluate(list.get(0), StringFunction.evaluate(obj, navigator), navigator) ? Boolean.TRUE : Boolean.FALSE;
    }

    private static boolean evaluate(Object obj, String str, Navigator navigator) throws UnsupportedAxisException {
        if (!navigator.isElement(obj)) {
            obj = navigator.getParentNode(obj);
        }
        while (obj != null && navigator.isElement(obj)) {
            Iterator attributeAxisIterator = navigator.getAttributeAxisIterator(obj);
            while (attributeAxisIterator.hasNext()) {
                Object next = attributeAxisIterator.next();
                if (LANG_LOCALNAME.equals(navigator.getAttributeName(next)) && "http://www.w3.org/XML/1998/namespace".equals(navigator.getAttributeNamespaceUri(next))) {
                    return isSublang(navigator.getAttributeStringValue(next), str);
                }
            }
            obj = navigator.getParentNode(obj);
        }
        return false;
    }

    private static boolean isSublang(String str, String str2) {
        if (str.equalsIgnoreCase(str2)) {
            return true;
        }
        int length = str2.length();
        if (str.length() <= length || str.charAt(length) != '-' || !str.substring(0, length).equalsIgnoreCase(str2)) {
            return false;
        }
        return true;
    }
}
