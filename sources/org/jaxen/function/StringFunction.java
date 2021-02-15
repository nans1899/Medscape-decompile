package org.jaxen.function;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.jaxen.Context;
import org.jaxen.Function;
import org.jaxen.FunctionCallException;
import org.jaxen.JaxenRuntimeException;
import org.jaxen.Navigator;
import org.jaxen.UnsupportedAxisException;

public class StringFunction implements Function {
    private static DecimalFormat format = ((DecimalFormat) NumberFormat.getInstance(Locale.ENGLISH));

    private static String stringValue(boolean z) {
        return z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false";
    }

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        decimalFormatSymbols.setNaN("NaN");
        decimalFormatSymbols.setInfinity("Infinity");
        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(32);
        format.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    public Object call(Context context, List list) throws FunctionCallException {
        int size = list.size();
        if (size == 0) {
            return evaluate(context.getNodeSet(), context.getNavigator());
        }
        if (size == 1) {
            return evaluate(list.get(0), context.getNavigator());
        }
        throw new FunctionCallException("string() takes at most argument.");
    }

    public static String evaluate(Object obj, Navigator navigator) {
        if (navigator != null) {
            try {
                if (navigator.isText(obj)) {
                    return navigator.getTextStringValue(obj);
                }
            } catch (UnsupportedAxisException e) {
                throw new JaxenRuntimeException((Throwable) e);
            }
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.isEmpty()) {
                return "";
            }
            obj = list.get(0);
        }
        if (navigator != null) {
            if (navigator.isElement(obj)) {
                return navigator.getElementStringValue(obj);
            }
            if (navigator.isAttribute(obj)) {
                return navigator.getAttributeStringValue(obj);
            }
            if (navigator.isDocument(obj)) {
                Iterator childAxisIterator = navigator.getChildAxisIterator(obj);
                while (childAxisIterator.hasNext()) {
                    Object next = childAxisIterator.next();
                    if (navigator.isElement(next)) {
                        return navigator.getElementStringValue(next);
                    }
                }
            } else if (navigator.isProcessingInstruction(obj)) {
                return navigator.getProcessingInstructionData(obj);
            } else {
                if (navigator.isComment(obj)) {
                    return navigator.getCommentStringValue(obj);
                }
                if (navigator.isText(obj)) {
                    return navigator.getTextStringValue(obj);
                }
                if (navigator.isNamespace(obj)) {
                    return navigator.getNamespaceStringValue(obj);
                }
            }
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof Boolean) {
            return stringValue(((Boolean) obj).booleanValue());
        }
        if (obj instanceof Number) {
            return stringValue(((Number) obj).doubleValue());
        }
        return "";
    }

    private static String stringValue(double d) {
        String format2;
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        synchronized (format) {
            format2 = format.format(d);
        }
        return format2;
    }
}
