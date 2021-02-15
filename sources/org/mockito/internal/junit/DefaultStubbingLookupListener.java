package org.mockito.internal.junit;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.stubbing.StrictnessSelector;
import org.mockito.internal.stubbing.UnusedStubbingReporting;
import org.mockito.invocation.Invocation;
import org.mockito.listeners.StubbingLookupEvent;
import org.mockito.listeners.StubbingLookupListener;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Stubbing;

class DefaultStubbingLookupListener implements StubbingLookupListener, Serializable {
    private static final long serialVersionUID = -6789800638070123629L;
    private Strictness currentStrictness;
    private boolean mismatchesReported;

    DefaultStubbingLookupListener(Strictness strictness) {
        this.currentStrictness = strictness;
    }

    public void onStubbingLookup(StubbingLookupEvent stubbingLookupEvent) {
        if (StrictnessSelector.determineStrictness(stubbingLookupEvent.getStubbingFound(), stubbingLookupEvent.getMockSettings(), this.currentStrictness) == Strictness.STRICT_STUBS) {
            if (stubbingLookupEvent.getStubbingFound() == null) {
                List<Invocation> potentialArgMismatches = potentialArgMismatches(stubbingLookupEvent.getInvocation(), stubbingLookupEvent.getAllStubbings());
                if (!potentialArgMismatches.isEmpty()) {
                    this.mismatchesReported = true;
                    Reporter.potentialStubbingProblem(stubbingLookupEvent.getInvocation(), potentialArgMismatches);
                    return;
                }
                return;
            }
            stubbingLookupEvent.getInvocation().markVerified();
        }
    }

    private static List<Invocation> potentialArgMismatches(Invocation invocation, Collection<Stubbing> collection) {
        LinkedList linkedList = new LinkedList();
        for (Stubbing next : collection) {
            if (UnusedStubbingReporting.shouldBeReported(next) && next.getInvocation().getMethod().getName().equals(invocation.getMethod().getName()) && !next.getInvocation().getLocation().getSourceFile().equals(invocation.getLocation().getSourceFile())) {
                linkedList.add(next.getInvocation());
            }
        }
        return linkedList;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentStrictness(Strictness strictness) {
        this.currentStrictness = strictness;
    }

    /* access modifiers changed from: package-private */
    public boolean isMismatchesReported() {
        return this.mismatchesReported;
    }
}
