package org.dom4j.dtd;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

public class ExternalEntityDecl {
    private String name;
    private String publicID;
    private String systemID;

    public ExternalEntityDecl() {
    }

    public ExternalEntityDecl(String str, String str2, String str3) {
        this.name = str;
        this.publicID = str2;
        this.systemID = str3;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPublicID() {
        return this.publicID;
    }

    public void setPublicID(String str) {
        this.publicID = str;
    }

    public String getSystemID() {
        return this.systemID;
    }

    public void setSystemID(String str) {
        this.systemID = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("<!ENTITY ");
        if (this.name.startsWith("%")) {
            stringBuffer.append("% ");
            stringBuffer.append(this.name.substring(1));
        } else {
            stringBuffer.append(this.name);
        }
        if (this.publicID != null) {
            stringBuffer.append(" PUBLIC \"");
            stringBuffer.append(this.publicID);
            stringBuffer.append("\" ");
            if (this.systemID != null) {
                stringBuffer.append("\"");
                stringBuffer.append(this.systemID);
                stringBuffer.append("\" ");
            }
        } else if (this.systemID != null) {
            stringBuffer.append(" SYSTEM \"");
            stringBuffer.append(this.systemID);
            stringBuffer.append("\" ");
        }
        stringBuffer.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        return stringBuffer.toString();
    }
}
