package org.mockito.internal.verification;

import org.mockito.verification.VerificationMode;

public class VerificationModeFactory {
    public static VerificationMode atLeastOnce() {
        return atLeast(1);
    }

    public static VerificationMode atLeast(int i) {
        return new AtLeast(i);
    }

    public static VerificationMode only() {
        return new Only();
    }

    public static Times times(int i) {
        return new Times(i);
    }

    public static Calls calls(int i) {
        return new Calls(i);
    }

    public static NoMoreInteractions noMoreInteractions() {
        return new NoMoreInteractions();
    }

    public static VerificationMode atMost(int i) {
        return new AtMost(i);
    }

    public static VerificationMode description(VerificationMode verificationMode, String str) {
        return new Description(verificationMode, str);
    }
}
