package org.simpleframework.xml.stream;

public interface Node {
    String getName();

    Node getParent();

    String getValue() throws Exception;
}
