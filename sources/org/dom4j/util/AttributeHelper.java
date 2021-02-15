package org.dom4j.util;

import com.facebook.internal.ServerProtocol;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.QName;

public class AttributeHelper {
    protected AttributeHelper() {
    }

    public static boolean booleanValue(Element element, String str) {
        return booleanValue(element.attribute(str));
    }

    public static boolean booleanValue(Element element, QName qName) {
        return booleanValue(element.attribute(qName));
    }

    protected static boolean booleanValue(Attribute attribute) {
        Object data;
        if (attribute == null || (data = attribute.getData()) == null) {
            return false;
        }
        if (data instanceof Boolean) {
            return ((Boolean) data).booleanValue();
        }
        return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase(data.toString());
    }
}
