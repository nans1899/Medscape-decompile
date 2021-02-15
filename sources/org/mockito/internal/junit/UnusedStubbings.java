package org.mockito.internal.junit;

import java.util.Collection;
import java.util.LinkedList;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.plugins.MockitoLogger;
import org.mockito.stubbing.Stubbing;

public class UnusedStubbings {
    private final Collection<? extends Stubbing> unused;

    UnusedStubbings(Collection<? extends Stubbing> collection) {
        this.unused = collection;
    }

    /* access modifiers changed from: package-private */
    public void format(String str, MockitoLogger mockitoLogger) {
        if (!this.unused.isEmpty()) {
            StubbingHint stubbingHint = new StubbingHint(str);
            int i = 1;
            for (Stubbing stubbing : this.unused) {
                if (!stubbing.wasUsed()) {
                    stubbingHint.appendLine(Integer.valueOf(i), ". Unused ", stubbing.getInvocation().getLocation());
                    i++;
                }
            }
            mockitoLogger.log(stubbingHint.toString());
        }
    }

    public int size() {
        return this.unused.size();
    }

    public String toString() {
        return this.unused.toString();
    }

    /* access modifiers changed from: package-private */
    public void reportUnused() {
        if (!this.unused.isEmpty()) {
            LinkedList linkedList = new LinkedList();
            for (Stubbing invocation : this.unused) {
                linkedList.add(invocation.getInvocation());
            }
            if (!linkedList.isEmpty()) {
                Reporter.unncessaryStubbingException(linkedList);
            }
        }
    }
}
