package org.dom4j.dtd;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;

public class ElementDecl {
    private String model;
    private String name;

    public ElementDecl() {
    }

    public ElementDecl(String str, String str2) {
        this.name = str;
        this.model = str2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("<!ELEMENT ");
        stringBuffer.append(this.name);
        stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(this.model);
        stringBuffer.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        return stringBuffer.toString();
    }
}
