package org.mockito.exceptions.verification.junit;

import junit.framework.ComparisonFailure;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;
import org.mockito.internal.util.StringUtil;

public class ArgumentsAreDifferent extends ComparisonFailure {
    private static final long serialVersionUID = 1;
    private final String message;
    private final StackTraceElement[] unfilteredStackTrace = getStackTrace();

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Throwable, org.mockito.exceptions.verification.junit.ArgumentsAreDifferent] */
    public ArgumentsAreDifferent(String str, String str2, String str3) {
        super(str, str2, str3);
        this.message = str;
        new ConditionalStackTraceFilter().filter(this);
    }

    public String getMessage() {
        return this.message;
    }

    public StackTraceElement[] getUnfilteredStackTrace() {
        return this.unfilteredStackTrace;
    }

    public String toString() {
        return StringUtil.removeFirstLine(ArgumentsAreDifferent.super.toString());
    }
}
