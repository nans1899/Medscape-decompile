package org.dom4j;

public interface ElementPath {
    void addHandler(String str, ElementHandler elementHandler);

    Element getCurrent();

    Element getElement(int i);

    String getPath();

    void removeHandler(String str);

    int size();
}
