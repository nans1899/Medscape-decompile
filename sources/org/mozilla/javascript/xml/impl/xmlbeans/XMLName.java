package org.mozilla.javascript.xml.impl.xmlbeans;

import com.dd.plist.ASCIIPropertyListParser;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Ref;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Undefined;

class XMLName extends Ref {
    static final long serialVersionUID = 3832176310755686977L;
    private boolean isAttributeName;
    private boolean isDescendants;
    private String localName;
    private String uri;
    private XMLObjectImpl xmlObject;

    private XMLName(String str, String str2) {
        this.uri = str;
        this.localName = str2;
    }

    static XMLName formStar() {
        return new XMLName((String) null, "*");
    }

    static XMLName formProperty(String str, String str2) {
        return new XMLName(str, str2);
    }

    /* access modifiers changed from: package-private */
    public void initXMLObject(XMLObjectImpl xMLObjectImpl) {
        if (xMLObjectImpl == null) {
            throw new IllegalArgumentException();
        } else if (this.xmlObject == null) {
            this.xmlObject = xMLObjectImpl;
        } else {
            throw new IllegalStateException();
        }
    }

    /* access modifiers changed from: package-private */
    public String uri() {
        return this.uri;
    }

    /* access modifiers changed from: package-private */
    public String localName() {
        return this.localName;
    }

    /* access modifiers changed from: package-private */
    public boolean isAttributeName() {
        return this.isAttributeName;
    }

    /* access modifiers changed from: package-private */
    public void setAttributeName() {
        if (!this.isAttributeName) {
            this.isAttributeName = true;
            return;
        }
        throw new IllegalStateException();
    }

    /* access modifiers changed from: package-private */
    public boolean isDescendants() {
        return this.isDescendants;
    }

    /* access modifiers changed from: package-private */
    public void setIsDescendants() {
        if (!this.isDescendants) {
            this.isDescendants = true;
            return;
        }
        throw new IllegalStateException();
    }

    public boolean has(Context context) {
        XMLObjectImpl xMLObjectImpl = this.xmlObject;
        if (xMLObjectImpl == null) {
            return false;
        }
        return xMLObjectImpl.hasXMLProperty(this);
    }

    public Object get(Context context) {
        XMLObjectImpl xMLObjectImpl = this.xmlObject;
        if (xMLObjectImpl != null) {
            return xMLObjectImpl.getXMLProperty(this);
        }
        throw ScriptRuntime.undefReadError(Undefined.instance, toString());
    }

    public Object set(Context context, Object obj) {
        XMLObjectImpl xMLObjectImpl = this.xmlObject;
        if (xMLObjectImpl == null) {
            throw ScriptRuntime.undefWriteError(Undefined.instance, toString(), obj);
        } else if (!this.isDescendants) {
            xMLObjectImpl.putXMLProperty(this, obj);
            return obj;
        } else {
            throw Kit.codeBug();
        }
    }

    public boolean delete(Context context) {
        XMLObjectImpl xMLObjectImpl = this.xmlObject;
        if (xMLObjectImpl == null) {
            return true;
        }
        xMLObjectImpl.deleteXMLProperty(this);
        return !this.xmlObject.hasXMLProperty(this);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.isDescendants) {
            stringBuffer.append("..");
        }
        if (this.isAttributeName) {
            stringBuffer.append('@');
        }
        if (this.uri == null) {
            stringBuffer.append('*');
            if (localName().equals("*")) {
                return stringBuffer.toString();
            }
        } else {
            stringBuffer.append('\"');
            stringBuffer.append(uri());
            stringBuffer.append('\"');
        }
        stringBuffer.append(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
        stringBuffer.append(localName());
        return stringBuffer.toString();
    }
}
