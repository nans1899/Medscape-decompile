package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public interface Strategy {
    Value read(Type type, NodeMap<InputNode> nodeMap, Map map) throws Exception;

    boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap, Map map) throws Exception;
}
