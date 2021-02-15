package org.mockito.hamcrest;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.hamcrest.Matcher;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.mockito.internal.hamcrest.MatcherGenericTypeExtractor;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.util.Primitives;

public class MockitoHamcrest {
    public static <T> T argThat(Matcher<T> matcher) {
        reportMatcher(matcher);
        return Primitives.defaultValue(MatcherGenericTypeExtractor.genericTypeOfMatcher(matcher.getClass()));
    }

    public static char charThat(Matcher<Character> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    public static boolean booleanThat(Matcher<Boolean> matcher) {
        reportMatcher(matcher);
        return false;
    }

    public static byte byteThat(Matcher<Byte> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    public static short shortThat(Matcher<Short> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    public static int intThat(Matcher<Integer> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    public static long longThat(Matcher<Long> matcher) {
        reportMatcher(matcher);
        return 0;
    }

    public static float floatThat(Matcher<Float> matcher) {
        reportMatcher(matcher);
        return 0.0f;
    }

    public static double doubleThat(Matcher<Double> matcher) {
        reportMatcher(matcher);
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private static <T> void reportMatcher(Matcher<T> matcher) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportMatcher(new HamcrestArgumentMatcher(matcher));
    }
}
