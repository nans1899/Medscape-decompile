package org.mockito.internal.junit;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.mockito.invocation.Invocation;
import org.mockito.plugins.MockitoLogger;

class StubbingArgMismatches {
    final Map<Invocation, Set<Invocation>> mismatches = new LinkedHashMap();

    StubbingArgMismatches() {
    }

    public void add(Invocation invocation, Invocation invocation2) {
        Set set = this.mismatches.get(invocation2);
        if (set == null) {
            set = new LinkedHashSet();
            this.mismatches.put(invocation2, set);
        }
        set.add(invocation);
    }

    public void format(String str, MockitoLogger mockitoLogger) {
        if (!this.mismatches.isEmpty()) {
            StubbingHint stubbingHint = new StubbingHint(str);
            int i = 1;
            for (Map.Entry next : this.mismatches.entrySet()) {
                int i2 = i + 1;
                stubbingHint.appendLine(Integer.valueOf(i), ". Unused... ", ((Invocation) next.getKey()).getLocation());
                for (Invocation location : (Set) next.getValue()) {
                    stubbingHint.appendLine(" ...args ok? ", location.getLocation());
                }
                i = i2;
            }
            mockitoLogger.log(stubbingHint.toString());
        }
    }

    public int size() {
        return this.mismatches.size();
    }

    public String toString() {
        return "" + this.mismatches;
    }
}
