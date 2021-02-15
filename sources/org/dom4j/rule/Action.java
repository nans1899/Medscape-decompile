package org.dom4j.rule;

import org.dom4j.Node;

public interface Action {
    void run(Node node) throws Exception;
}
