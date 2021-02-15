package org.simpleframework.xml.stream;

public interface InputNode extends Node {
    InputNode getAttribute(String str);

    NodeMap<InputNode> getAttributes();

    InputNode getNext() throws Exception;

    InputNode getNext(String str) throws Exception;

    InputNode getParent();

    Position getPosition();

    String getPrefix();

    String getReference();

    Object getSource();

    boolean isElement();

    boolean isEmpty() throws Exception;

    boolean isRoot();

    void skip() throws Exception;
}
