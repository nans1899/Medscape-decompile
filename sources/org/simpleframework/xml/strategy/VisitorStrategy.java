package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class VisitorStrategy implements Strategy {
    private final Strategy strategy;
    private final Visitor visitor;

    public VisitorStrategy(Visitor visitor2) {
        this(visitor2, new TreeStrategy());
    }

    public VisitorStrategy(Visitor visitor2, Strategy strategy2) {
        this.strategy = strategy2;
        this.visitor = visitor2;
    }

    public Value read(Type type, NodeMap<InputNode> nodeMap, Map map) throws Exception {
        Visitor visitor2 = this.visitor;
        if (visitor2 != null) {
            visitor2.read(type, nodeMap);
        }
        return this.strategy.read(type, nodeMap, map);
    }

    public boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap, Map map) throws Exception {
        boolean write = this.strategy.write(type, obj, nodeMap, map);
        Visitor visitor2 = this.visitor;
        if (visitor2 != null) {
            visitor2.write(type, nodeMap);
        }
        return write;
    }
}
