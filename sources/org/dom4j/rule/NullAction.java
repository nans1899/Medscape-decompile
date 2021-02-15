package org.dom4j.rule;

import org.dom4j.Node;

public class NullAction implements Action {
    public static final NullAction SINGLETON = new NullAction();

    public void run(Node node) throws Exception {
    }
}
