package org.apache.commons.io.input;

import java.io.Reader;
import java.io.Serializable;
import java.util.Objects;

public class CharSequenceReader extends Reader implements Serializable {
    private static final long serialVersionUID = 3724187752191401220L;
    private final CharSequence charSequence;
    private final Integer end;
    private int idx;
    private int mark;
    private final int start;

    public boolean markSupported() {
        return true;
    }

    public CharSequenceReader(CharSequence charSequence2) {
        this(charSequence2, 0);
    }

    public CharSequenceReader(CharSequence charSequence2, int i) {
        this(charSequence2, i, Integer.MAX_VALUE);
    }

    public CharSequenceReader(String str, int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Start index is less than zero: " + i);
        } else if (i2 >= i) {
            this.charSequence = str == null ? "" : str;
            this.start = i;
            this.end = Integer.valueOf(i2);
            this.idx = i;
            this.mark = i;
        } else {
            throw new IllegalArgumentException("End index is less than start " + i + ": " + i2);
        }
    }

    private int start() {
        return Math.min(this.charSequence.length(), this.start);
    }

    private int end() {
        int length = this.charSequence.length();
        Integer num = this.end;
        return Math.min(length, num == null ? Integer.MAX_VALUE : num.intValue());
    }

    public void close() {
        int i = this.start;
        this.idx = i;
        this.mark = i;
    }

    public boolean ready() {
        return this.idx < end();
    }

    public void mark(int i) {
        this.mark = this.idx;
    }

    public int read() {
        if (this.idx >= end()) {
            return -1;
        }
        CharSequence charSequence2 = this.charSequence;
        int i = this.idx;
        this.idx = i + 1;
        return charSequence2.charAt(i);
    }

    public int read(char[] cArr, int i, int i2) {
        if (this.idx >= end()) {
            return -1;
        }
        Objects.requireNonNull(cArr, "array");
        if (i2 < 0 || i < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException("Array Size=" + cArr.length + ", offset=" + i + ", length=" + i2);
        }
        CharSequence charSequence2 = this.charSequence;
        if (charSequence2 instanceof String) {
            int min = Math.min(i2, end() - this.idx);
            int i3 = this.idx;
            ((String) this.charSequence).getChars(i3, i3 + min, cArr, i);
            this.idx += min;
            return min;
        } else if (charSequence2 instanceof StringBuilder) {
            int min2 = Math.min(i2, end() - this.idx);
            int i4 = this.idx;
            ((StringBuilder) this.charSequence).getChars(i4, i4 + min2, cArr, i);
            this.idx += min2;
            return min2;
        } else if (charSequence2 instanceof StringBuffer) {
            int min3 = Math.min(i2, end() - this.idx);
            int i5 = this.idx;
            ((StringBuffer) this.charSequence).getChars(i5, i5 + min3, cArr, i);
            this.idx += min3;
            return min3;
        } else {
            int i6 = 0;
            for (int i7 = 0; i7 < i2; i7++) {
                int read = read();
                if (read == -1) {
                    return i6;
                }
                cArr[i + i7] = (char) read;
                i6++;
            }
            return i6;
        }
    }

    public void reset() {
        this.idx = this.mark;
    }

    public long skip(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Number of characters to skip is less than zero: " + j);
        } else if (this.idx >= end()) {
            return 0;
        } else {
            int min = (int) Math.min((long) end(), ((long) this.idx) + j);
            this.idx = min;
            return (long) (min - this.idx);
        }
    }

    public String toString() {
        return this.charSequence.subSequence(start(), end()).toString();
    }
}
