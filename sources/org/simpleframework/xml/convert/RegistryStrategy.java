package org.simpleframework.xml.convert;

import java.util.Map;
import org.simpleframework.xml.strategy.Strategy;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.strategy.Value;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;

public class RegistryStrategy implements Strategy {
    private final Registry registry;
    private final Strategy strategy;

    public RegistryStrategy(Registry registry2) {
        this(registry2, new TreeStrategy());
    }

    public RegistryStrategy(Registry registry2, Strategy strategy2) {
        this.registry = registry2;
        this.strategy = strategy2;
    }

    public Value read(Type type, NodeMap<InputNode> nodeMap, Map map) throws Exception {
        Value read = this.strategy.read(type, nodeMap, map);
        if (isReference(read)) {
            return read;
        }
        return read(type, nodeMap, read);
    }

    private Value read(Type type, NodeMap<InputNode> nodeMap, Value value) throws Exception {
        Converter lookup = lookup(type, value);
        InputNode node = nodeMap.getNode();
        if (lookup == null) {
            return value;
        }
        Object read = lookup.read(node);
        Class type2 = type.getType();
        if (value != null) {
            value.setValue(read);
        }
        return new Reference(value, read, type2);
    }

    public boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap, Map map) throws Exception {
        boolean write = this.strategy.write(type, obj, nodeMap, map);
        return !write ? write(type, obj, nodeMap) : write;
    }

    private boolean write(Type type, Object obj, NodeMap<OutputNode> nodeMap) throws Exception {
        Converter lookup = lookup(type, obj);
        OutputNode node = nodeMap.getNode();
        if (lookup == null) {
            return false;
        }
        lookup.write(node, obj);
        return true;
    }

    private Converter lookup(Type type, Value value) throws Exception {
        Class type2 = type.getType();
        if (value != null) {
            type2 = value.getType();
        }
        return this.registry.lookup(type2);
    }

    private Converter lookup(Type type, Object obj) throws Exception {
        Class<?> type2 = type.getType();
        if (obj != null) {
            type2 = obj.getClass();
        }
        return this.registry.lookup(type2);
    }

    private boolean isReference(Value value) {
        return value != null && value.isReference();
    }
}
