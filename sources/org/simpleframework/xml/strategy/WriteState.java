package org.simpleframework.xml.strategy;

import org.simpleframework.xml.util.WeakCache;

class WriteState extends WeakCache<WriteGraph> {
    private Contract contract;

    public WriteState(Contract contract2) {
        this.contract = contract2;
    }

    public WriteGraph find(Object obj) {
        WriteGraph writeGraph = (WriteGraph) fetch(obj);
        if (writeGraph != null) {
            return writeGraph;
        }
        WriteGraph writeGraph2 = new WriteGraph(this.contract);
        cache(obj, writeGraph2);
        return writeGraph2;
    }
}
