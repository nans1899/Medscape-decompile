package org.jaxen.pattern;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.jaxen.Context;
import org.jaxen.Navigator;

public class NameTest extends NodeTest {
    private String name;
    private short nodeType;

    public double getPriority() {
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public NameTest(String str, short s) {
        this.name = str;
        this.nodeType = s;
    }

    public boolean matches(Object obj, Context context) {
        Navigator navigator = context.getNavigator();
        short s = this.nodeType;
        if (s == 1) {
            if (!navigator.isElement(obj) || !this.name.equals(navigator.getElementName(obj))) {
                return false;
            }
            return true;
        } else if (s == 2) {
            if (!navigator.isAttribute(obj) || !this.name.equals(navigator.getAttributeName(obj))) {
                return false;
            }
            return true;
        } else if (navigator.isElement(obj)) {
            return this.name.equals(navigator.getElementName(obj));
        } else {
            if (navigator.isAttribute(obj)) {
                return this.name.equals(navigator.getAttributeName(obj));
            }
            return false;
        }
    }

    public short getMatchType() {
        return this.nodeType;
    }

    public String getText() {
        if (this.nodeType != 2) {
            return this.name;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("@");
        stringBuffer.append(this.name);
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString());
        stringBuffer.append("[ name: ");
        stringBuffer.append(this.name);
        stringBuffer.append(" type: ");
        stringBuffer.append(this.nodeType);
        stringBuffer.append(" ]");
        return stringBuffer.toString();
    }
}
