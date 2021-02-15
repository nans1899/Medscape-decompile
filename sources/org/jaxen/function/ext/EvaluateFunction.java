package org.jaxen.function.ext;

import java.util.Collections;
import java.util.List;
import org.jaxen.Context;
import org.jaxen.ContextSupport;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.Navigator;
import org.jaxen.XPath;
import org.jaxen.function.StringFunction;
import org.jaxen.saxpath.SAXPathException;

public class EvaluateFunction implements Function {
    public Object call(Context context, List list) throws FunctionCallException {
        if (list.size() == 1) {
            return evaluate(context, list.get(0));
        }
        throw new FunctionCallException("evaluate() requires one argument");
    }

    public static List evaluate(Context context, Object obj) throws FunctionCallException {
        String str;
        if (context.getNodeSet().size() == 0) {
            return Collections.EMPTY_LIST;
        }
        Navigator navigator = context.getNavigator();
        if (obj instanceof String) {
            str = (String) obj;
        } else {
            str = StringFunction.evaluate(obj, navigator);
        }
        try {
            XPath parseXPath = navigator.parseXPath(str);
            ContextSupport contextSupport = context.getContextSupport();
            parseXPath.setVariableContext(contextSupport.getVariableContext());
            parseXPath.setFunctionContext(contextSupport.getFunctionContext());
            parseXPath.setNamespaceContext(contextSupport.getNamespaceContext());
            return parseXPath.selectNodes(context.duplicate());
        } catch (SAXPathException e) {
            throw new FunctionCallException(e.toString());
        }
    }
}
