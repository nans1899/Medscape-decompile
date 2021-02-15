package org.mockito;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.mockito.internal.matchers.Any;
import org.mockito.internal.matchers.Contains;
import org.mockito.internal.matchers.EndsWith;
import org.mockito.internal.matchers.Equals;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.internal.matchers.Matches;
import org.mockito.internal.matchers.NotNull;
import org.mockito.internal.matchers.Null;
import org.mockito.internal.matchers.Same;
import org.mockito.internal.matchers.StartsWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.util.Primitives;

public class ArgumentMatchers {
    public static <T> T any() {
        return anyObject();
    }

    @Deprecated
    public static <T> T anyObject() {
        reportMatcher(Any.ANY);
        return null;
    }

    public static <T> T any(Class<T> cls) {
        reportMatcher(new InstanceOf.VarArgAware(cls, "<any " + cls.getCanonicalName() + HtmlObject.HtmlMarkUp.CLOSE_BRACKER));
        return Primitives.defaultValue(cls);
    }

    public static <T> T isA(Class<T> cls) {
        reportMatcher(new InstanceOf(cls));
        return Primitives.defaultValue(cls);
    }

    @Deprecated
    public static <T> T anyVararg() {
        any();
        return null;
    }

    public static boolean anyBoolean() {
        reportMatcher(new InstanceOf(Boolean.class, "<any boolean>"));
        return false;
    }

    public static byte anyByte() {
        reportMatcher(new InstanceOf(Byte.class, "<any byte>"));
        return 0;
    }

    public static char anyChar() {
        reportMatcher(new InstanceOf(Character.class, "<any char>"));
        return 0;
    }

    public static int anyInt() {
        reportMatcher(new InstanceOf(Integer.class, "<any integer>"));
        return 0;
    }

    public static long anyLong() {
        reportMatcher(new InstanceOf(Long.class, "<any long>"));
        return 0;
    }

    public static float anyFloat() {
        reportMatcher(new InstanceOf(Float.class, "<any float>"));
        return 0.0f;
    }

    public static double anyDouble() {
        reportMatcher(new InstanceOf(Double.class, "<any double>"));
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static short anyShort() {
        reportMatcher(new InstanceOf(Short.class, "<any short>"));
        return 0;
    }

    public static String anyString() {
        reportMatcher(new InstanceOf(String.class, "<any string>"));
        return "";
    }

    public static <T> List<T> anyList() {
        reportMatcher(new InstanceOf(List.class, "<any List>"));
        return new ArrayList(0);
    }

    @Deprecated
    public static <T> List<T> anyListOf(Class<T> cls) {
        return anyList();
    }

    public static <T> Set<T> anySet() {
        reportMatcher(new InstanceOf(Set.class, "<any set>"));
        return new HashSet(0);
    }

    @Deprecated
    public static <T> Set<T> anySetOf(Class<T> cls) {
        return anySet();
    }

    public static <K, V> Map<K, V> anyMap() {
        reportMatcher(new InstanceOf(Map.class, "<any map>"));
        return new HashMap(0);
    }

    @Deprecated
    public static <K, V> Map<K, V> anyMapOf(Class<K> cls, Class<V> cls2) {
        return anyMap();
    }

    public static <T> Collection<T> anyCollection() {
        reportMatcher(new InstanceOf(Collection.class, "<any collection>"));
        return new ArrayList(0);
    }

    @Deprecated
    public static <T> Collection<T> anyCollectionOf(Class<T> cls) {
        return anyCollection();
    }

    public static <T> Iterable<T> anyIterable() {
        reportMatcher(new InstanceOf(Iterable.class, "<any iterable>"));
        return new ArrayList(0);
    }

    @Deprecated
    public static <T> Iterable<T> anyIterableOf(Class<T> cls) {
        return anyIterable();
    }

    public static boolean eq(boolean z) {
        reportMatcher(new Equals(Boolean.valueOf(z)));
        return false;
    }

    public static byte eq(byte b) {
        reportMatcher(new Equals(Byte.valueOf(b)));
        return 0;
    }

    public static char eq(char c) {
        reportMatcher(new Equals(Character.valueOf(c)));
        return 0;
    }

    public static double eq(double d) {
        reportMatcher(new Equals(Double.valueOf(d)));
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public static float eq(float f) {
        reportMatcher(new Equals(Float.valueOf(f)));
        return 0.0f;
    }

    public static int eq(int i) {
        reportMatcher(new Equals(Integer.valueOf(i)));
        return 0;
    }

    public static long eq(long j) {
        reportMatcher(new Equals(Long.valueOf(j)));
        return 0;
    }

    public static short eq(short s) {
        reportMatcher(new Equals(Short.valueOf(s)));
        return 0;
    }

    public static <T> T eq(T t) {
        reportMatcher(new Equals(t));
        if (t == null) {
            return null;
        }
        return Primitives.defaultValue(t.getClass());
    }

    public static <T> T refEq(T t, String... strArr) {
        reportMatcher(new ReflectionEquals(t, strArr));
        return null;
    }

    public static <T> T same(T t) {
        reportMatcher(new Same(t));
        if (t == null) {
            return null;
        }
        return Primitives.defaultValue(t.getClass());
    }

    public static <T> T isNull() {
        reportMatcher(Null.NULL);
        return null;
    }

    @Deprecated
    public static <T> T isNull(Class<T> cls) {
        return isNull();
    }

    public static <T> T notNull() {
        reportMatcher(NotNull.NOT_NULL);
        return null;
    }

    @Deprecated
    public static <T> T notNull(Class<T> cls) {
        return notNull();
    }

    public static <T> T isNotNull() {
        return notNull();
    }

    @Deprecated
    public static <T> T isNotNull(Class<T> cls) {
        return notNull(cls);
    }

    public static <T> T nullable(Class<T> cls) {
        AdditionalMatchers.or(isNull(), isA(cls));
        return Primitives.defaultValue(cls);
    }

    public static String contains(String str) {
        reportMatcher(new Contains(str));
        return "";
    }

    public static String matches(String str) {
        reportMatcher(new Matches(str));
        return "";
    }

    public static String matches(Pattern pattern) {
        reportMatcher(new Matches(pattern));
        return "";
    }

    public static String endsWith(String str) {
        reportMatcher(new EndsWith(str));
        return "";
    }

    public static String startsWith(String str) {
        reportMatcher(new StartsWith(str));
        return "";
    }

    public static <T> T argThat(ArgumentMatcher<T> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return null;
    }

    public static char charThat(ArgumentMatcher<Character> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return 0;
    }

    public static boolean booleanThat(ArgumentMatcher<Boolean> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return false;
    }

    public static byte byteThat(ArgumentMatcher<Byte> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return 0;
    }

    public static short shortThat(ArgumentMatcher<Short> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return 0;
    }

    public static int intThat(ArgumentMatcher<Integer> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return 0;
    }

    public static long longThat(ArgumentMatcher<Long> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return 0;
    }

    public static float floatThat(ArgumentMatcher<Float> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return 0.0f;
    }

    public static double doubleThat(ArgumentMatcher<Double> argumentMatcher) {
        reportMatcher(argumentMatcher);
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private static void reportMatcher(ArgumentMatcher<?> argumentMatcher) {
        ThreadSafeMockingProgress.mockingProgress().getArgumentMatcherStorage().reportMatcher(argumentMatcher);
    }
}
