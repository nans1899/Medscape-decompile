package org.mockito.internal.debugging;

import java.io.Serializable;
import org.mockito.internal.exceptions.stacktrace.StackTraceFilter;
import org.mockito.invocation.Location;

public class LocationImpl implements Location, Serializable {
    private static final StackTraceFilter defaultStackTraceFilter = new StackTraceFilter();
    private static final long serialVersionUID = -9054861157390980624L;
    private final String sourceFile;
    private final StackTraceFilter stackTraceFilter;
    private final Throwable stackTraceHolder;

    public LocationImpl() {
        this(defaultStackTraceFilter);
    }

    public LocationImpl(StackTraceFilter stackTraceFilter2) {
        this(stackTraceFilter2, new Throwable());
    }

    public LocationImpl(Throwable th) {
        this(defaultStackTraceFilter, th);
    }

    private LocationImpl(StackTraceFilter stackTraceFilter2, Throwable th) {
        this.stackTraceFilter = stackTraceFilter2;
        this.stackTraceHolder = th;
        if (th.getStackTrace() == null || th.getStackTrace().length == 0) {
            this.sourceFile = "<unknown source file>";
        } else {
            this.sourceFile = stackTraceFilter2.findSourceFile(th.getStackTrace(), "<unknown source file>");
        }
    }

    public String toString() {
        StackTraceElement[] filter = this.stackTraceFilter.filter(this.stackTraceHolder.getStackTrace(), false);
        if (filter.length == 0) {
            return "-> at <<unknown line>>";
        }
        return "-> at " + filter[0].toString();
    }

    public String getSourceFile() {
        return this.sourceFile;
    }
}
