package org.dom4j;

public interface ElementHandler {
    void onEnd(ElementPath elementPath);

    void onStart(ElementPath elementPath);
}
