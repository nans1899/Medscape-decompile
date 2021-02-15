package org.mockito.internal.debugging;

import java.io.PrintStream;
import org.mockito.invocation.DescribedInvocation;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.MethodInvocationReport;

public class VerboseMockInvocationLogger implements InvocationListener {
    private int mockInvocationsCounter;
    final PrintStream printStream;

    public VerboseMockInvocationLogger() {
        this(System.out);
    }

    public VerboseMockInvocationLogger(PrintStream printStream2) {
        this.mockInvocationsCounter = 0;
        this.printStream = printStream2;
    }

    public void reportInvocation(MethodInvocationReport methodInvocationReport) {
        printHeader();
        printStubInfo(methodInvocationReport);
        printInvocation(methodInvocationReport.getInvocation());
        printReturnedValueOrThrowable(methodInvocationReport);
        printFooter();
    }

    private void printReturnedValueOrThrowable(MethodInvocationReport methodInvocationReport) {
        String str = "";
        if (methodInvocationReport.threwException()) {
            if (methodInvocationReport.getThrowable().getMessage() != null) {
                str = " with message " + methodInvocationReport.getThrowable().getMessage();
            }
            printlnIndented("has thrown: " + methodInvocationReport.getThrowable().getClass() + str);
            return;
        }
        if (methodInvocationReport.getReturnedValue() != null) {
            str = " (" + methodInvocationReport.getReturnedValue().getClass().getName() + ")";
        }
        printlnIndented("has returned: \"" + methodInvocationReport.getReturnedValue() + "\"" + str);
    }

    private void printStubInfo(MethodInvocationReport methodInvocationReport) {
        if (methodInvocationReport.getLocationOfStubbing() != null) {
            printlnIndented("stubbed: " + methodInvocationReport.getLocationOfStubbing());
        }
    }

    private void printHeader() {
        this.mockInvocationsCounter++;
        this.printStream.println("############ Logging method invocation #" + this.mockInvocationsCounter + " on mock/spy ########");
    }

    private void printInvocation(DescribedInvocation describedInvocation) {
        this.printStream.println(describedInvocation.toString());
        printlnIndented("invoked: " + describedInvocation.getLocation().toString());
    }

    private void printFooter() {
        this.printStream.println("");
    }

    private void printlnIndented(String str) {
        PrintStream printStream2 = this.printStream;
        printStream2.println("   " + str);
    }
}
