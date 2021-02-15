package org.simpleframework.xml.stream;

public interface OutputNode extends Node {
    void commit() throws Exception;

    NodeMap<OutputNode> getAttributes();

    OutputNode getChild(String str) throws Exception;

    String getComment();

    Mode getMode();

    NamespaceMap getNamespaces();

    OutputNode getParent();

    String getPrefix();

    String getPrefix(boolean z);

    String getReference();

    boolean isCommitted();

    boolean isRoot();

    void remove() throws Exception;

    OutputNode setAttribute(String str, String str2);

    void setComment(String str);

    void setData(boolean z);

    void setMode(Mode mode);

    void setName(String str);

    void setReference(String str);

    void setValue(String str);
}
