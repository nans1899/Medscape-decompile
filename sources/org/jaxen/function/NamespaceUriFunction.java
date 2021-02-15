package org.jaxen.function;

import java.util.List;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;

public class NamespaceUriFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 0) {
            return evaluate(context.getNodeSet(), context.getNavigator());
        }
        if (list.size() == 1) {
            return evaluate(list, context.getNavigator());
        }
        throw new FunctionCallException("namespace-uri() requires zero or one argument.");
    }

    public static String evaluate(List list, Navigator navigator) throws FunctionCallException {
        if (list.isEmpty()) {
            return "";
        }
        Object obj = list.get(0);
        if (obj instanceof List) {
            return evaluate((List) obj, navigator);
        }
        if (navigator.isElement(obj)) {
            return navigator.getElementNamespaceUri(obj);
        }
        if (navigator.isAttribute(obj)) {
            String attributeNamespaceUri = navigator.getAttributeNamespaceUri(obj);
            if (attributeNamespaceUri == null) {
                return "";
            }
            return attributeNamespaceUri;
        } else if (navigator.isProcessingInstruction(obj) || navigator.isNamespace(obj) || navigator.isDocument(obj) || navigator.isComment(obj) || navigator.isText(obj)) {
            return "";
        } else {
            throw new FunctionCallException("The argument to the namespace-uri function must be a node-set");
        }
    }
}
