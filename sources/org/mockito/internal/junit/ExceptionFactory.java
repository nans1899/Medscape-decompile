package org.mockito.internal.junit;

import com.facebook.share.internal.ShareConstants;
import org.mockito.exceptions.verification.ArgumentsAreDifferent;

public class ExceptionFactory {
    private static final boolean hasJUnit = canLoadJunitClass();

    private ExceptionFactory() {
    }

    public static AssertionError createArgumentsAreDifferentException(String str, String str2, String str3) {
        if (hasJUnit) {
            return createJUnitArgumentsAreDifferent(str, str2, str3);
        }
        return new ArgumentsAreDifferent(str);
    }

    private static AssertionError createJUnitArgumentsAreDifferent(String str, String str2, String str3) {
        return JUnitArgsAreDifferent.create(str, str2, str3);
    }

    private static boolean canLoadJunitClass() {
        try {
            JUnitArgsAreDifferent.create(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, "wanted", "actual");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static class JUnitArgsAreDifferent {
        private JUnitArgsAreDifferent() {
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [org.mockito.exceptions.verification.junit.ArgumentsAreDifferent, java.lang.AssertionError] */
        static AssertionError create(String str, String str2, String str3) {
            return new org.mockito.exceptions.verification.junit.ArgumentsAreDifferent(str, str2, str3);
        }
    }
}
