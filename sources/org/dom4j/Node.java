package org.dom4j;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface Node extends Cloneable {
    public static final short ANY_NODE = 0;
    public static final short ATTRIBUTE_NODE = 2;
    public static final short CDATA_SECTION_NODE = 4;
    public static final short COMMENT_NODE = 8;
    public static final short DOCUMENT_NODE = 9;
    public static final short DOCUMENT_TYPE_NODE = 10;
    public static final short ELEMENT_NODE = 1;
    public static final short ENTITY_REFERENCE_NODE = 5;
    public static final short MAX_NODE_TYPE = 14;
    public static final short NAMESPACE_NODE = 13;
    public static final short PROCESSING_INSTRUCTION_NODE = 7;
    public static final short TEXT_NODE = 3;
    public static final short UNKNOWN_NODE = 14;

    void accept(Visitor visitor);

    String asXML();

    Node asXPathResult(Element element);

    Object clone();

    XPath createXPath(String str) throws InvalidXPathException;

    Node detach();

    Document getDocument();

    String getName();

    short getNodeType();

    String getNodeTypeName();

    Element getParent();

    String getPath();

    String getPath(Element element);

    String getStringValue();

    String getText();

    String getUniquePath();

    String getUniquePath(Element element);

    boolean hasContent();

    boolean isReadOnly();

    boolean matches(String str);

    Number numberValueOf(String str);

    List selectNodes(String str);

    List selectNodes(String str, String str2);

    List selectNodes(String str, String str2, boolean z);

    Object selectObject(String str);

    Node selectSingleNode(String str);

    void setDocument(Document document);

    void setName(String str);

    void setParent(Element element);

    void setText(String str);

    boolean supportsParent();

    String valueOf(String str);

    void write(Writer writer) throws IOException;
}
