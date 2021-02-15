package org.mockito;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.mockito.internal.matchers.ArrayEquals;
import org.mockito.internal.matchers.CompareEqual;
import org.mockito.internal.matchers.EqualsWithDelta;
import org.mockito.internal.matchers.Find;
import org.mockito.internal.matchers.GreaterOrEqual;
import org.mockito.internal.matchers.GreaterThan;
import org.mockito.internal.matchers.LessOrEqual;
import org.mockito.internal.matchers.LessThan;
import org.mockito.internal.progress.ThreadSafeMockingProgress;

public class AdditionalMatchers {
    public static <T extends Comparable<T>> T geq(T t) {
        reportMatcher(new GreaterOrEqual(t));
        return null;
    }

    public static byte geq(byte b) {
        reportMatcher(new GreaterOrEqual(Byte.valueOf(b)));
        return 0;
    }

    public static double geq(double d) {
        reportMatcher(new GreaterOrEqual(Double.valueOf(d)));
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static float geq(float f) {
        reportMatcher(new GreaterOrEqual(Float.valueOf(f)));
        return 0.0f;
    }

    public static int geq(int i) {
        reportMatcher(new GreaterOrEqual(Integer.valueOf(i)));
        return 0;
    }

    public static long geq(long j) {
        reportMatcher(new GreaterOrEqual(Long.valueOf(j)));
        return 0;
    }

    public static short geq(short s) {
        reportMatcher(new GreaterOrEqual(Short.valueOf(s)));
        return 0;
    }

    public static <T extends Comparable<T>> T leq(T t) {
        reportMatcher(new LessOrEqual(t));
        return null;
    }

    public static byte leq(byte b) {
        reportMatcher(new LessOrEqual(Byte.valueOf(b)));
        return 0;
    }

    public static double leq(double d) {
        reportMatcher(new LessOrEqual(Double.valueOf(d)));
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static float leq(float f) {
        reportMatcher(new LessOrEqual(Float.valueOf(f)));
        return 0.0f;
    }

    public static int leq(int i) {
        reportMatcher(new LessOrEqual(Integer.valueOf(i)));
        return 0;
    }

    public static long leq(long j) {
        reportMatcher(new LessOrEqual(Long.valueOf(j)));
        return 0;
    }

    public static short leq(short s) {
        reportMatcher(new LessOrEqual(Short.valueOf(s)));
        return 0;
    }

    public static <T extends Comparable<T>> T gt(T t) {
        reportMatcher(new GreaterThan(t));
        return null;
    }

    public static byte gt(byte b) {
        reportMatcher(new GreaterThan(Byte.valueOf(b)));
        return 0;
    }

    public static double gt(double d) {
        reportMatcher(new GreaterThan(Double.valueOf(d)));
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static float gt(float f) {
        reportMatcher(new GreaterThan(Float.valueOf(f)));
        return 0.0f;
    }

    public static int gt(int i) {
        reportMatcher(new GreaterThan(Integer.valueOf(i)));
        return 0;
    }

    public static long gt(long j) {
        reportMatcher(new GreaterThan(Long.valueOf(j)));
        return 0;
    }

    public static short gt(short s) {
        reportMatcher(new GreaterThan(Short.valueOf(s)));
        return 0;
    }

    public static <T extends Comparable<T>> T lt(T t) {
        reportMatcher(new LessThan(t));
        return null;
    }

    public static byte lt(byte b) {
        reportMatcher(new LessThan(Byte.valueOf(b)));
        return 0;
    }

    public static double lt(double d) {
        reportMatcher(new LessThan(Double.valueOf(d)));
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static float lt(float f) {
        reportMatcher(new LessThan(Float.valueOf(f)));
        return 0.0f;
    }

    public static int lt(int i) {
        reportMatcher(new LessThan(Integer.valueOf(i)));
        return 0;
    }

    public static long lt(long j) {
        reportMatcher(new LessThan(Long.valueOf(j)));
        return 0;
    }

    public static short lt(short s) {
        reportMatcher(new LessThan(Short.valueOf(s)));
        return 0;
    }

    public static <T extends Comparable<T>> T cmpEq(T t) {
        reportMatcher(new CompareEqual(t));
        return null;
    }

    public static String find(String str) {
        reportMatcher(new Find(str));
        return null;
    }

    public static <T> T[] aryEq(T[] tArr) {
        reportMatcher(new ArrayEquals(tArr));
        return null;
    }

    public static short[] aryEq(short[] sArr) {
        reportMatcher(new ArrayEquals(sArr));
        return null;
    }

    public static long[] aryEq(long[] jArr) {
        reportMatcher(new ArrayEquals(jArr));
        return null;
    }

    public static int[] aryEq(int[] iArr) {
        reportMatcher(new ArrayEquals(iArr));
        return null;
    }

    public static float[] aryEq(float[] fArr) {
        reportMatcher(new ArrayEquals(fArr));
        return null;
    }

    public static double[] aryEq(double[] dArr) {
        reportMatcher(new ArrayEquals(dArr));
        return null;
    }

    public static char[] aryEq(char[] cArr) {
        reportMatcher(new ArrayEquals(cArr));
        return null;
    }

    public static byte[] aryEq(byte[] bArr) {
        reportMatcher(new ArrayEquals(bArr));
        return null;
    }

    public static boolean[] aryEq(boolean[] zArr) {
        reportMatcher(new ArrayEquals(zArr));
        return null;
    }

    public static boolean and(boolean z, boolean z2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return false;
    }

    public static byte and(byte b, byte b2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return 0;
    }

    public static char and(char c, char c2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return 0;
    }

    public static double and(double d, double d2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static float and(float f, float f2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return 0.0f;
    }

    public static int and(int i, int i2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return 0;
    }

    public static long and(long j, long j2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return 0;
    }

    public static short and(short s, short s2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return 0;
    }

    public static <T> T and(T t, T t2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportAnd();
        return null;
    }

    public static boolean or(boolean z, boolean z2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return false;
    }

    public static <T> T or(T t, T t2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return null;
    }

    public static short or(short s, short s2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return 0;
    }

    public static long or(long j, long j2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return 0;
    }

    public static int or(int i, int i2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return 0;
    }

    public static float or(float f, float f2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return 0.0f;
    }

    public static double or(double d, double d2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static char or(char c, char c2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return 0;
    }

    public static byte or(byte b, byte b2) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportOr();
        return 0;
    }

    public static <T> T not(T t) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return null;
    }

    public static short not(short s) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return 0;
    }

    public static int not(int i) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return 0;
    }

    public static long not(long j) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return 0;
    }

    public static float not(float f) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return 0.0f;
    }

    public static double not(double d) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static char not(char c) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return 0;
    }

    public static boolean not(boolean z) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return false;
    }

    public static byte not(byte b) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportNot();
        return 0;
    }

    public static double eq(double d, double d2) {
        reportMatcher(new EqualsWithDelta(Double.valueOf(d), Double.valueOf(d2)));
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static float eq(float f, float f2) {
        reportMatcher(new EqualsWithDelta(Float.valueOf(f), Float.valueOf(f2)));
        return 0.0f;
    }

    private static void reportMatcher(ArgumentMatcher<?> argumentMatcher) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportMatcher(argumentMatcher);
    }
}
