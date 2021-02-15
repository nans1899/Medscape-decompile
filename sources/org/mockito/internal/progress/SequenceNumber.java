package org.mockito.internal.progress;

public class SequenceNumber {
    private static int sequenceNumber = 1;

    public static synchronized int next() {
        int i;
        synchronized (SequenceNumber.class) {
            i = sequenceNumber;
            sequenceNumber = i + 1;
        }
        return i;
    }
}
