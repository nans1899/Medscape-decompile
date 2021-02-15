package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.OutputNode;

interface Decorator {
    void decorate(OutputNode outputNode);

    void decorate(OutputNode outputNode, Decorator decorator);
}
