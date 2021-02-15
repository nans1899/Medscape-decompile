package org.mozilla.javascript.v8dtoa;

import java.util.Arrays;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;

public class FastDtoaBuilder {
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    final char[] chars = new char[25];
    int end = 0;
    boolean formatted = false;
    int point;

    /* access modifiers changed from: package-private */
    public void append(char c) {
        char[] cArr = this.chars;
        int i = this.end;
        this.end = i + 1;
        cArr[i] = c;
    }

    /* access modifiers changed from: package-private */
    public void decreaseLast() {
        char[] cArr = this.chars;
        int i = this.end - 1;
        cArr[i] = (char) (cArr[i] - 1);
    }

    public void reset() {
        this.end = 0;
        this.formatted = false;
    }

    public String toString() {
        return "[chars:" + new String(this.chars, 0, this.end) + ", point:" + this.point + "]";
    }

    public String format() {
        if (!this.formatted) {
            int i = this.chars[0] == '-' ? 1 : 0;
            int i2 = this.point - i;
            if (i2 < -5 || i2 > 21) {
                toExponentialFormat(i, i2);
            } else {
                toFixedFormat(i, i2);
            }
            this.formatted = true;
        }
        return new String(this.chars, 0, this.end);
    }

    private void toFixedFormat(int i, int i2) {
        int i3 = this.point;
        int i4 = this.end;
        if (i3 < i4) {
            if (i2 > 0) {
                char[] cArr = this.chars;
                System.arraycopy(cArr, i3, cArr, i3 + 1, i4 - i3);
                this.chars[this.point] = '.';
                this.end++;
                return;
            }
            int i5 = i + 2;
            int i6 = i5 - i2;
            char[] cArr2 = this.chars;
            System.arraycopy(cArr2, i, cArr2, i6, i4 - i);
            char[] cArr3 = this.chars;
            cArr3[i] = '0';
            cArr3[i + 1] = '.';
            if (i2 < 0) {
                Arrays.fill(cArr3, i5, i6, '0');
            }
            this.end += 2 - i2;
        } else if (i3 > i4) {
            Arrays.fill(this.chars, i4, i3, '0');
            int i7 = this.end;
            this.end = i7 + (this.point - i7);
        }
    }

    private void toExponentialFormat(int i, int i2) {
        int i3 = this.end;
        if (i3 - i > 1) {
            int i4 = i + 1;
            char[] cArr = this.chars;
            System.arraycopy(cArr, i4, cArr, i4 + 1, i3 - i4);
            this.chars[i4] = '.';
            this.end++;
        }
        char[] cArr2 = this.chars;
        int i5 = this.end;
        this.end = i5 + 1;
        cArr2[i5] = 'e';
        char c = SignatureVisitor.EXTENDS;
        int i6 = i2 - 1;
        if (i6 < 0) {
            c = '-';
            i6 = -i6;
        }
        char[] cArr3 = this.chars;
        int i7 = this.end;
        int i8 = i7 + 1;
        this.end = i8;
        cArr3[i7] = c;
        if (i6 > 99) {
            i8 += 2;
        } else if (i6 > 9) {
            i8++;
        }
        this.end = i8 + 1;
        while (true) {
            int i9 = i8 - 1;
            this.chars[i8] = digits[i6 % 10];
            i6 /= 10;
            if (i6 != 0) {
                i8 = i9;
            } else {
                return;
            }
        }
    }
}
