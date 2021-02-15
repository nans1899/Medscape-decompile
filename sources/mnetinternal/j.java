package mnetinternal;

import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.internal.ServerProtocol;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import org.apache.commons.io.IOUtils;

public class j implements Closeable, Flushable {
    private static final String[] a = new String[128];
    private static final String[] b;
    private final Writer c;
    private int[] d = new int[32];
    private int e = 0;
    private String f;
    private String g;
    private boolean h;
    private boolean i;
    private String j;
    private boolean k;

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            a[i2] = String.format("\\u%04x", new Object[]{Integer.valueOf(i2)});
        }
        String[] strArr = a;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        b = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public j(Writer writer) {
        a(6);
        this.g = ":";
        this.k = true;
        if (writer != null) {
            this.c = writer;
            return;
        }
        throw new NullPointerException("out == null");
    }

    public final void c(String str) {
        if (str.length() == 0) {
            this.f = null;
            this.g = ":";
            return;
        }
        this.f = str;
        this.g = ": ";
    }

    public final void b(boolean z) {
        this.h = z;
    }

    public boolean g() {
        return this.h;
    }

    public final void c(boolean z) {
        this.i = z;
    }

    public final boolean h() {
        return this.i;
    }

    public final void d(boolean z) {
        this.k = z;
    }

    public final boolean i() {
        return this.k;
    }

    public j b() {
        j();
        return a(1, "[");
    }

    public j c() {
        return a(1, 2, "]");
    }

    public j d() {
        j();
        return a(3, "{");
    }

    public j e() {
        return a(3, 5, "}");
    }

    private j a(int i2, String str) {
        m();
        a(i2);
        this.c.write(str);
        return this;
    }

    private j a(int i2, int i3, String str) {
        int a2 = a();
        if (a2 != i3 && a2 != i2) {
            throw new IllegalStateException("Nesting problem.");
        } else if (this.j == null) {
            this.e--;
            if (a2 == i3) {
                k();
            }
            this.c.write(str);
            return this;
        } else {
            throw new IllegalStateException("Dangling name: " + this.j);
        }
    }

    private void a(int i2) {
        int i3 = this.e;
        int[] iArr = this.d;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[(i3 * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            this.d = iArr2;
        }
        int[] iArr3 = this.d;
        int i4 = this.e;
        this.e = i4 + 1;
        iArr3[i4] = i2;
    }

    private int a() {
        int i2 = this.e;
        if (i2 != 0) {
            return this.d[i2 - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    private void b(int i2) {
        this.d[this.e - 1] = i2;
    }

    public j a(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.j != null) {
            throw new IllegalStateException();
        } else if (this.e != 0) {
            this.j = str;
            return this;
        } else {
            throw new IllegalStateException("JsonWriter is closed.");
        }
    }

    private void j() {
        if (this.j != null) {
            l();
            d(this.j);
            this.j = null;
        }
    }

    public j b(String str) {
        if (str == null) {
            return f();
        }
        j();
        m();
        d(str);
        return this;
    }

    public j f() {
        if (this.j != null) {
            if (this.k) {
                j();
            } else {
                this.j = null;
                return this;
            }
        }
        m();
        this.c.write("null");
        return this;
    }

    public j a(boolean z) {
        j();
        m();
        this.c.write(z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }

    public j a(Boolean bool) {
        if (bool == null) {
            return f();
        }
        j();
        m();
        this.c.write(bool.booleanValue() ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false");
        return this;
    }

    public j a(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d2);
        }
        j();
        m();
        this.c.append(Double.toString(d2));
        return this;
    }

    public j a(long j2) {
        j();
        m();
        this.c.write(Long.toString(j2));
        return this;
    }

    public j a(Number number) {
        if (number == null) {
            return f();
        }
        j();
        String obj = number.toString();
        if (this.h || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            m();
            this.c.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public void flush() {
        if (this.e != 0) {
            this.c.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public void close() {
        this.c.close();
        int i2 = this.e;
        if (i2 > 1 || (i2 == 1 && this.d[i2 - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.e = 0;
    }

    private void d(String str) {
        String str2;
        String[] strArr = this.i ? b : a;
        this.c.write("\"");
        int length = str.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (charAt < 128) {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
            } else if (charAt == 8232) {
                str2 = "\\u2028";
            } else if (charAt == 8233) {
                str2 = "\\u2029";
            }
            if (i2 < i3) {
                this.c.write(str, i2, i3 - i2);
            }
            this.c.write(str2);
            i2 = i3 + 1;
        }
        if (i2 < length) {
            this.c.write(str, i2, length - i2);
        }
        this.c.write("\"");
    }

    private void k() {
        if (this.f != null) {
            this.c.write(IOUtils.LINE_SEPARATOR_UNIX);
            int i2 = this.e;
            for (int i3 = 1; i3 < i2; i3++) {
                this.c.write(this.f);
            }
        }
    }

    private void l() {
        int a2 = a();
        if (a2 == 5) {
            this.c.write(44);
        } else if (a2 != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        k();
        b(4);
    }

    private void m() {
        int a2 = a();
        if (a2 == 1) {
            b(2);
            k();
        } else if (a2 == 2) {
            this.c.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            k();
        } else if (a2 != 4) {
            if (a2 != 6) {
                if (a2 != 7) {
                    throw new IllegalStateException("Nesting problem.");
                } else if (!this.h) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            b(7);
        } else {
            this.c.append(this.g);
            b(5);
        }
    }
}
