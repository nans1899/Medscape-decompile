package org.dom4j.tree;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Visitor;

public abstract class AbstractDocumentType extends AbstractNode implements DocumentType {
    public short getNodeType() {
        return 10;
    }

    public String getPath(Element element) {
        return "";
    }

    public String getUniquePath(Element element) {
        return "";
    }

    public String getName() {
        return getElementName();
    }

    public void setName(String str) {
        setElementName(str);
    }

    public String getText() {
        List internalDeclarations = getInternalDeclarations();
        if (internalDeclarations == null || internalDeclarations.size() <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = internalDeclarations.iterator();
        if (it.hasNext()) {
            stringBuffer.append(it.next().toString());
            while (it.hasNext()) {
                Object next = it.next();
                stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
                stringBuffer.append(next.toString());
            }
        }
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(String.valueOf(super.toString()));
        stringBuffer.append(" [DocumentType: ");
        stringBuffer.append(asXML());
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public String asXML() {
        boolean z;
        StringBuffer stringBuffer = new StringBuffer("<!DOCTYPE ");
        stringBuffer.append(getElementName());
        String publicID = getPublicID();
        if (publicID == null || publicID.length() <= 0) {
            z = false;
        } else {
            stringBuffer.append(" PUBLIC \"");
            stringBuffer.append(publicID);
            stringBuffer.append("\"");
            z = true;
        }
        String systemID = getSystemID();
        if (systemID != null && systemID.length() > 0) {
            if (!z) {
                stringBuffer.append(" SYSTEM");
            }
            stringBuffer.append(" \"");
            stringBuffer.append(systemID);
            stringBuffer.append("\"");
        }
        stringBuffer.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        return stringBuffer.toString();
    }

    public void write(Writer writer) throws IOException {
        boolean z;
        writer.write("<!DOCTYPE ");
        writer.write(getElementName());
        String publicID = getPublicID();
        if (publicID == null || publicID.length() <= 0) {
            z = false;
        } else {
            writer.write(" PUBLIC \"");
            writer.write(publicID);
            writer.write("\"");
            z = true;
        }
        String systemID = getSystemID();
        if (systemID != null && systemID.length() > 0) {
            if (!z) {
                writer.write(" SYSTEM");
            }
            writer.write(" \"");
            writer.write(systemID);
            writer.write("\"");
        }
        List internalDeclarations = getInternalDeclarations();
        if (internalDeclarations != null && internalDeclarations.size() > 0) {
            writer.write(" [");
            for (Object next : internalDeclarations) {
                writer.write("\n  ");
                writer.write(next.toString());
            }
            writer.write("\n]");
        }
        writer.write(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
    }

    public void accept(Visitor visitor) {
        visitor.visit((DocumentType) this);
    }
}
