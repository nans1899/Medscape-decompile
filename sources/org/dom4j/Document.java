package org.dom4j;

import java.util.Map;
import org.xml.sax.EntityResolver;

public interface Document extends Branch {
    Document addComment(String str);

    Document addDocType(String str, String str2, String str3);

    Document addProcessingInstruction(String str, String str2);

    Document addProcessingInstruction(String str, Map map);

    DocumentType getDocType();

    EntityResolver getEntityResolver();

    Element getRootElement();

    String getXMLEncoding();

    void setDocType(DocumentType documentType);

    void setEntityResolver(EntityResolver entityResolver);

    void setRootElement(Element element);

    void setXMLEncoding(String str);
}
