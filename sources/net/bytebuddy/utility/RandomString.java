package net.bytebuddy.utility;

import java.util.Random;

public class RandomString {
    public static final int DEFAULT_LENGTH = 8;
    private static final int KEY_BITS;
    private static final char[] SYMBOL;
    private final int length;
    private final Random random;

    static {
        StringBuilder sb = new StringBuilder();
        for (char c = '0'; c <= '9'; c = (char) (c + 1)) {
            sb.append(c);
        }
        for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
            sb.append(c2);
        }
        for (char c3 = 'A'; c3 <= 'Z'; c3 = (char) (c3 + 1)) {
            sb.append(c3);
        }
        char[] charArray = sb.toString().toCharArray();
        SYMBOL = charArray;
        int numberOfLeadingZeros = 32 - Integer.numberOfLeadingZeros(charArray.length);
        KEY_BITS = numberOfLeadingZeros - (Integer.bitCount(SYMBOL.length) == numberOfLeadingZeros ? 0 : 1);
    }

    public RandomString() {
        this(8);
    }

    public RandomString(int i) {
        if (i > 0) {
            this.length = i;
            this.random = new Random();
            return;
        }
        throw new IllegalArgumentException("A random string's length cannot be zero or negative");
    }

    public static String make() {
        return make(8);
    }

    public static String make(int i) {
        return new RandomString(i).nextString();
    }

    public static String hashOf(int i) {
        int i2 = KEY_BITS;
        int i3 = (32 / i2) + (32 % i2 == 0 ? 0 : 1);
        char[] cArr = new char[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            char[] cArr2 = SYMBOL;
            int i5 = KEY_BITS;
            cArr[i4] = cArr2[(-1 >>> (32 - i5)) & (i >>> (i4 * i5))];
        }
        return new String(cArr);
    }

    public String nextString() {
        char[] cArr = new char[this.length];
        for (int i = 0; i < this.length; i++) {
            char[] cArr2 = SYMBOL;
            cArr[i] = cArr2[this.random.nextInt(cArr2.length)];
        }
        return new String(cArr);
    }
}
