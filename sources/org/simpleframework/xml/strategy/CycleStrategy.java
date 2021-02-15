package org.simpleframework.xml.strategy;

import java.util.Map;
import org.simpleframework.xml.stream.NodeMap;

public class CycleStrategy implements Strategy {
    private final Contract contract;
    private final ReadState read;
    private final WriteState write;

    public CycleStrategy() {
        this("id", "reference");
    }

    public CycleStrategy(String str, String str2) {
        this(str, str2, Name.LABEL);
    }

    public CycleStrategy(String str, String str2, String str3) {
        this(str, str2, str3, Name.LENGTH);
    }

    public CycleStrategy(String str, String str2, String str3, String str4) {
        Contract contract2 = new Contract(str, str2, str3, str4);
        this.contract = contract2;
        this.write = new WriteState(contract2);
        this.read = new ReadState(this.contract);
    }

    public Value read(Type type, NodeMap nodeMap, Map map) throws Exception {
        ReadGraph find = this.read.find(map);
        if (find != null) {
            return find.read(type, nodeMap);
        }
        return null;
    }

    public boolean write(Type type, Object obj, NodeMap nodeMap, Map map) {
        WriteGraph find = this.write.find(map);
        if (find != null) {
            return find.write(type, obj, nodeMap);
        }
        return false;
    }
}
