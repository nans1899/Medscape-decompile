package mnetinternal;

import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.internal.ServerProtocol;
import com.mnet.gson.internal.bind.c;
import com.mnet.gson.internal.d;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import kotlin.text.Typography;
import net.bytebuddy.pool.TypePool;
import okio.internal.BufferKt;

public class h implements Closeable {
    private static final char[] b = ")]}'\n".toCharArray();
    int a = 0;
    private final Reader c;
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private long j;
    private int k;
    private String l;
    private int[] m;
    private int n;
    private String[] o;
    private int[] p;

    static {
        d.a = new d() {
            public void a(h hVar) {
                if (hVar instanceof c) {
                    ((c) hVar).o();
                    return;
                }
                int i = hVar.a;
                if (i == 0) {
                    i = hVar.r();
                }
                if (i == 13) {
                    hVar.a = 9;
                } else if (i == 12) {
                    hVar.a = 8;
                } else if (i == 14) {
                    hVar.a = 10;
                } else {
                    throw new IllegalStateException("Expected a name but was " + hVar.f() + hVar.x());
                }
            }
        };
    }

    public h(Reader reader) {
        int[] iArr = new int[32];
        this.m = iArr;
        this.n = 0;
        this.n = 0 + 1;
        iArr[0] = 6;
        this.o = new String[32];
        this.p = new int[32];
        if (reader != null) {
            this.c = reader;
            return;
        }
        throw new NullPointerException("in == null");
    }

    public final void a(boolean z) {
        this.d = z;
    }

    public final boolean q() {
        return this.d;
    }

    public void a() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 3) {
            a(1);
            this.p[this.n - 1] = 0;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + f() + x());
    }

    public void b() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 4) {
            int i3 = this.n - 1;
            this.n = i3;
            int[] iArr = this.p;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + f() + x());
    }

    public void c() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 1) {
            a(3);
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + f() + x());
    }

    public void d() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 2) {
            int i3 = this.n - 1;
            this.n = i3;
            this.o[i3] = null;
            int[] iArr = this.p;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
            this.a = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + f() + x());
    }

    public boolean e() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        return (i2 == 2 || i2 == 4) ? false : true;
    }

    public i f() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        switch (i2) {
            case 1:
                return i.BEGIN_OBJECT;
            case 2:
                return i.END_OBJECT;
            case 3:
                return i.BEGIN_ARRAY;
            case 4:
                return i.END_ARRAY;
            case 5:
            case 6:
                return i.BOOLEAN;
            case 7:
                return i.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return i.STRING;
            case 12:
            case 13:
            case 14:
                return i.NAME;
            case 15:
            case 16:
                return i.NUMBER;
            case 17:
                return i.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public int r() {
        int b2;
        int[] iArr = this.m;
        int i2 = this.n;
        int i3 = iArr[i2 - 1];
        if (i3 == 1) {
            iArr[i2 - 1] = 2;
        } else if (i3 == 2) {
            int b3 = b(true);
            if (b3 != 44) {
                if (b3 == 59) {
                    v();
                } else if (b3 == 93) {
                    this.a = 4;
                    return 4;
                } else {
                    throw b("Unterminated array");
                }
            }
        } else if (i3 == 3 || i3 == 5) {
            this.m[this.n - 1] = 4;
            if (i3 == 5 && (b2 = b(true)) != 44) {
                if (b2 == 59) {
                    v();
                } else if (b2 == 125) {
                    this.a = 2;
                    return 2;
                } else {
                    throw b("Unterminated object");
                }
            }
            int b4 = b(true);
            if (b4 == 34) {
                this.a = 13;
                return 13;
            } else if (b4 == 39) {
                v();
                this.a = 12;
                return 12;
            } else if (b4 != 125) {
                v();
                this.f--;
                if (a((char) b4)) {
                    this.a = 14;
                    return 14;
                }
                throw b("Expected name");
            } else if (i3 != 5) {
                this.a = 2;
                return 2;
            } else {
                throw b("Expected name");
            }
        } else if (i3 == 4) {
            iArr[i2 - 1] = 5;
            int b5 = b(true);
            if (b5 != 58) {
                if (b5 == 61) {
                    v();
                    if (this.f < this.g || b(1)) {
                        char[] cArr = this.e;
                        int i4 = this.f;
                        if (cArr[i4] == '>') {
                            this.f = i4 + 1;
                        }
                    }
                } else {
                    throw b("Expected ':'");
                }
            }
        } else if (i3 == 6) {
            if (this.d) {
                z();
            }
            this.m[this.n - 1] = 7;
        } else if (i3 == 7) {
            if (b(false) == -1) {
                this.a = 17;
                return 17;
            }
            v();
            this.f--;
        } else if (i3 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int b6 = b(true);
        if (b6 == 34) {
            this.a = 9;
            return 9;
        } else if (b6 != 39) {
            if (!(b6 == 44 || b6 == 59)) {
                if (b6 == 91) {
                    this.a = 3;
                    return 3;
                } else if (b6 != 93) {
                    if (b6 != 123) {
                        this.f--;
                        int o2 = o();
                        if (o2 != 0) {
                            return o2;
                        }
                        int s = s();
                        if (s != 0) {
                            return s;
                        }
                        if (a(this.e[this.f])) {
                            v();
                            this.a = 10;
                            return 10;
                        }
                        throw b("Expected value");
                    }
                    this.a = 1;
                    return 1;
                } else if (i3 == 1) {
                    this.a = 4;
                    return 4;
                }
            }
            if (i3 == 1 || i3 == 2) {
                v();
                this.f--;
                this.a = 7;
                return 7;
            }
            throw b("Unexpected value");
        } else {
            v();
            this.a = 8;
            return 8;
        }
    }

    private int o() {
        String str;
        String str2;
        int i2;
        char c2 = this.e[this.f];
        if (c2 == 't' || c2 == 'T') {
            i2 = 5;
            str2 = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
            str = "TRUE";
        } else if (c2 == 'f' || c2 == 'F') {
            i2 = 6;
            str2 = "false";
            str = "FALSE";
        } else if (c2 != 'n' && c2 != 'N') {
            return 0;
        } else {
            i2 = 7;
            str2 = "null";
            str = "NULL";
        }
        int length = str2.length();
        for (int i3 = 1; i3 < length; i3++) {
            if (this.f + i3 >= this.g && !b(i3 + 1)) {
                return 0;
            }
            char c3 = this.e[this.f + i3];
            if (c3 != str2.charAt(i3) && c3 != str.charAt(i3)) {
                return 0;
            }
        }
        if ((this.f + length < this.g || b(length + 1)) && a(this.e[this.f + length])) {
            return 0;
        }
        this.f += length;
        this.a = i2;
        return i2;
    }

    private int s() {
        char c2;
        char[] cArr = this.e;
        int i2 = this.f;
        int i3 = this.g;
        int i4 = 0;
        int i5 = 0;
        char c3 = 0;
        boolean z = true;
        long j2 = 0;
        boolean z2 = false;
        while (true) {
            if (i2 + i5 == i3) {
                if (i5 == cArr.length) {
                    return i4;
                }
                if (!b(i5 + 1)) {
                    break;
                }
                i2 = this.f;
                i3 = this.g;
            }
            c2 = cArr[i2 + i5];
            if (c2 == '+') {
                i4 = 0;
                if (c3 != 5) {
                    return 0;
                }
            } else if (c2 == 'E' || c2 == 'e') {
                i4 = 0;
                if (c3 != 2 && c3 != 4) {
                    return 0;
                }
                c3 = 5;
                i5++;
            } else {
                if (c2 == '-') {
                    i4 = 0;
                    if (c3 == 0) {
                        c3 = 1;
                        z2 = true;
                    } else if (c3 != 5) {
                        return 0;
                    }
                } else if (c2 == '.') {
                    i4 = 0;
                    if (c3 != 2) {
                        return 0;
                    }
                    c3 = 3;
                } else if (c2 >= '0' && c2 <= '9') {
                    if (c3 == 1 || c3 == 0) {
                        j2 = (long) (-(c2 - '0'));
                        i4 = 0;
                        c3 = 2;
                    } else {
                        if (c3 == 2) {
                            if (j2 == 0) {
                                return 0;
                            }
                            long j3 = (10 * j2) - ((long) (c2 - '0'));
                            int i6 = (j2 > BufferKt.OVERFLOW_ZONE ? 1 : (j2 == BufferKt.OVERFLOW_ZONE ? 0 : -1));
                            z &= i6 > 0 || (i6 == 0 && j3 < j2);
                            j2 = j3;
                        } else if (c3 == 3) {
                            i4 = 0;
                            c3 = 4;
                        } else if (c3 == 5 || c3 == 6) {
                            i4 = 0;
                            c3 = 7;
                        }
                        i4 = 0;
                    }
                }
                i5++;
            }
            c3 = 6;
            i5++;
        }
        if (a(c2)) {
            return 0;
        }
        if (c3 == 2 && z && (j2 != Long.MIN_VALUE || z2)) {
            if (!z2) {
                j2 = -j2;
            }
            this.j = j2;
            this.f += i5;
            this.a = 15;
            return 15;
        } else if (c3 != 2 && c3 != 4 && c3 != 7) {
            return 0;
        } else {
            this.k = i5;
            this.a = 16;
            return 16;
        }
    }

    private boolean a(char c2) {
        if (c2 == 9 || c2 == 10 || c2 == 12 || c2 == 13 || c2 == ' ') {
            return false;
        }
        if (c2 != '#') {
            if (c2 == ',') {
                return false;
            }
            if (!(c2 == '/' || c2 == '=')) {
                if (c2 == '{' || c2 == '}' || c2 == ':') {
                    return false;
                }
                if (c2 != ';') {
                    switch (c2) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        v();
        return false;
    }

    public String g() {
        String str;
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 14) {
            str = t();
        } else if (i2 == 12) {
            str = b('\'');
        } else if (i2 == 13) {
            str = b('\"');
        } else {
            throw new IllegalStateException("Expected a name but was " + f() + x());
        }
        this.a = 0;
        this.o[this.n - 1] = str;
        return str;
    }

    public String h() {
        String str;
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 10) {
            str = t();
        } else if (i2 == 8) {
            str = b('\'');
        } else if (i2 == 9) {
            str = b('\"');
        } else if (i2 == 11) {
            str = this.l;
            this.l = null;
        } else if (i2 == 15) {
            str = Long.toString(this.j);
        } else if (i2 == 16) {
            str = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else {
            throw new IllegalStateException("Expected a string but was " + f() + x());
        }
        this.a = 0;
        int[] iArr = this.p;
        int i3 = this.n - 1;
        iArr[i3] = iArr[i3] + 1;
        return str;
    }

    public boolean i() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 5) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (i2 == 6) {
            this.a = 0;
            int[] iArr2 = this.p;
            int i4 = this.n - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + f() + x());
        }
    }

    public void j() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 7) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + f() + x());
    }

    public double k() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return (double) this.j;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9) {
            this.l = b(i2 == 8 ? '\'' : '\"');
        } else if (i2 == 10) {
            this.l = t();
        } else if (i2 != 11) {
            throw new IllegalStateException("Expected a double but was " + f() + x());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        if (this.d || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
            this.l = null;
            this.a = 0;
            int[] iArr2 = this.p;
            int i4 = this.n - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return parseDouble;
        }
        throw new k("JSON forbids NaN and infinities: " + parseDouble + x());
    }

    public long l() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            this.a = 0;
            int[] iArr = this.p;
            int i3 = this.n - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.j;
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.l = t();
            } else {
                this.l = b(i2 == 8 ? '\'' : '\"');
            }
            try {
                long parseLong = Long.parseLong(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i4 = this.n - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected a long but was " + f() + x());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        long j2 = (long) parseDouble;
        if (((double) j2) == parseDouble) {
            this.l = null;
            this.a = 0;
            int[] iArr3 = this.p;
            int i5 = this.n - 1;
            iArr3[i5] = iArr3[i5] + 1;
            return j2;
        }
        throw new NumberFormatException("Expected a long but was " + this.l + x());
    }

    private String b(char c2) {
        char[] cArr = this.e;
        StringBuilder sb = new StringBuilder();
        do {
            int i2 = this.f;
            int i3 = this.g;
            while (true) {
                int i4 = i3;
                int i5 = i2;
                while (i2 < i4) {
                    int i6 = i2 + 1;
                    char c3 = cArr[i2];
                    if (c3 == c2) {
                        this.f = i6;
                        sb.append(cArr, i5, (i6 - i5) - 1);
                        return sb.toString();
                    } else if (c3 == '\\') {
                        this.f = i6;
                        sb.append(cArr, i5, (i6 - i5) - 1);
                        sb.append(y());
                        i2 = this.f;
                        i3 = this.g;
                    } else {
                        if (c3 == 10) {
                            this.h++;
                            this.i = i6;
                        }
                        i2 = i6;
                    }
                }
                sb.append(cArr, i5, i2 - i5);
                this.f = i2;
            }
        } while (b(1));
        throw b("Unterminated string");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004a, code lost:
        v();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String t() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            r2 = 0
        L_0x0003:
            int r3 = r6.f
            int r4 = r3 + r2
            int r5 = r6.g
            if (r4 >= r5) goto L_0x004e
            char[] r4 = r6.e
            int r3 = r3 + r2
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L_0x005c
            r4 = 10
            if (r3 == r4) goto L_0x005c
            r4 = 12
            if (r3 == r4) goto L_0x005c
            r4 = 13
            if (r3 == r4) goto L_0x005c
            r4 = 32
            if (r3 == r4) goto L_0x005c
            r4 = 35
            if (r3 == r4) goto L_0x004a
            r4 = 44
            if (r3 == r4) goto L_0x005c
            r4 = 47
            if (r3 == r4) goto L_0x004a
            r4 = 61
            if (r3 == r4) goto L_0x004a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L_0x005c
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L_0x005c
            r4 = 58
            if (r3 == r4) goto L_0x005c
            r4 = 59
            if (r3 == r4) goto L_0x004a
            switch(r3) {
                case 91: goto L_0x005c;
                case 92: goto L_0x004a;
                case 93: goto L_0x005c;
                default: goto L_0x0047;
            }
        L_0x0047:
            int r2 = r2 + 1
            goto L_0x0003
        L_0x004a:
            r6.v()
            goto L_0x005c
        L_0x004e:
            char[] r3 = r6.e
            int r3 = r3.length
            if (r2 >= r3) goto L_0x005e
            int r3 = r2 + 1
            boolean r3 = r6.b((int) r3)
            if (r3 == 0) goto L_0x005c
            goto L_0x0003
        L_0x005c:
            r0 = r2
            goto L_0x0078
        L_0x005e:
            if (r1 != 0) goto L_0x0065
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L_0x0065:
            char[] r3 = r6.e
            int r4 = r6.f
            r1.append(r3, r4, r2)
            int r3 = r6.f
            int r3 = r3 + r2
            r6.f = r3
            r2 = 1
            boolean r2 = r6.b((int) r2)
            if (r2 != 0) goto L_0x0002
        L_0x0078:
            if (r1 != 0) goto L_0x0084
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.e
            int r3 = r6.f
            r1.<init>(r2, r3, r0)
            goto L_0x008f
        L_0x0084:
            char[] r2 = r6.e
            int r3 = r6.f
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L_0x008f:
            int r2 = r6.f
            int r2 = r2 + r0
            r6.f = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: mnetinternal.h.t():java.lang.String");
    }

    private void c(char c2) {
        char[] cArr = this.e;
        do {
            int i2 = this.f;
            int i3 = this.g;
            while (i2 < i3) {
                int i4 = i2 + 1;
                char c3 = cArr[i2];
                if (c3 == c2) {
                    this.f = i4;
                    return;
                } else if (c3 == '\\') {
                    this.f = i4;
                    y();
                    i2 = this.f;
                    i3 = this.g;
                } else {
                    if (c3 == 10) {
                        this.h++;
                        this.i = i4;
                    }
                    i2 = i4;
                }
            }
            this.f = i2;
        } while (b(1));
        throw b("Unterminated string");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0048, code lost:
        v();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void u() {
        /*
            r4 = this;
        L_0x0000:
            r0 = 0
        L_0x0001:
            int r1 = r4.f
            int r2 = r1 + r0
            int r3 = r4.g
            if (r2 >= r3) goto L_0x0051
            char[] r2 = r4.e
            int r1 = r1 + r0
            char r1 = r2[r1]
            r2 = 9
            if (r1 == r2) goto L_0x004b
            r2 = 10
            if (r1 == r2) goto L_0x004b
            r2 = 12
            if (r1 == r2) goto L_0x004b
            r2 = 13
            if (r1 == r2) goto L_0x004b
            r2 = 32
            if (r1 == r2) goto L_0x004b
            r2 = 35
            if (r1 == r2) goto L_0x0048
            r2 = 44
            if (r1 == r2) goto L_0x004b
            r2 = 47
            if (r1 == r2) goto L_0x0048
            r2 = 61
            if (r1 == r2) goto L_0x0048
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L_0x004b
            r2 = 125(0x7d, float:1.75E-43)
            if (r1 == r2) goto L_0x004b
            r2 = 58
            if (r1 == r2) goto L_0x004b
            r2 = 59
            if (r1 == r2) goto L_0x0048
            switch(r1) {
                case 91: goto L_0x004b;
                case 92: goto L_0x0048;
                case 93: goto L_0x004b;
                default: goto L_0x0045;
            }
        L_0x0045:
            int r0 = r0 + 1
            goto L_0x0001
        L_0x0048:
            r4.v()
        L_0x004b:
            int r1 = r4.f
            int r1 = r1 + r0
            r4.f = r1
            return
        L_0x0051:
            int r1 = r1 + r0
            r4.f = r1
            r0 = 1
            boolean r0 = r4.b((int) r0)
            if (r0 != 0) goto L_0x0000
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mnetinternal.h.u():void");
    }

    public int m() {
        int i2 = this.a;
        if (i2 == 0) {
            i2 = r();
        }
        if (i2 == 15) {
            long j2 = this.j;
            int i3 = (int) j2;
            if (j2 == ((long) i3)) {
                this.a = 0;
                int[] iArr = this.p;
                int i4 = this.n - 1;
                iArr[i4] = iArr[i4] + 1;
                return i3;
            }
            throw new NumberFormatException("Expected an int but was " + this.j + x());
        }
        if (i2 == 16) {
            this.l = new String(this.e, this.f, this.k);
            this.f += this.k;
        } else if (i2 == 8 || i2 == 9 || i2 == 10) {
            if (i2 == 10) {
                this.l = t();
            } else {
                this.l = b(i2 == 8 ? '\'' : '\"');
            }
            try {
                int parseInt = Integer.parseInt(this.l);
                this.a = 0;
                int[] iArr2 = this.p;
                int i5 = this.n - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else {
            throw new IllegalStateException("Expected an int but was " + f() + x());
        }
        this.a = 11;
        double parseDouble = Double.parseDouble(this.l);
        int i6 = (int) parseDouble;
        if (((double) i6) == parseDouble) {
            this.l = null;
            this.a = 0;
            int[] iArr3 = this.p;
            int i7 = this.n - 1;
            iArr3[i7] = iArr3[i7] + 1;
            return i6;
        }
        throw new NumberFormatException("Expected an int but was " + this.l + x());
    }

    public void close() {
        this.a = 0;
        this.m[0] = 8;
        this.n = 1;
        this.c.close();
    }

    public void n() {
        int i2 = 0;
        do {
            int i3 = this.a;
            if (i3 == 0) {
                i3 = r();
            }
            if (i3 == 3) {
                a(1);
            } else if (i3 == 1) {
                a(3);
            } else {
                if (i3 == 4) {
                    this.n--;
                } else if (i3 == 2) {
                    this.n--;
                } else if (i3 == 14 || i3 == 10) {
                    u();
                    this.a = 0;
                } else if (i3 == 8 || i3 == 12) {
                    c('\'');
                    this.a = 0;
                } else if (i3 == 9 || i3 == 13) {
                    c('\"');
                    this.a = 0;
                } else {
                    if (i3 == 16) {
                        this.f += this.k;
                    }
                    this.a = 0;
                }
                i2--;
                this.a = 0;
            }
            i2++;
            this.a = 0;
        } while (i2 != 0);
        int[] iArr = this.p;
        int i4 = this.n;
        int i5 = i4 - 1;
        iArr[i5] = iArr[i5] + 1;
        this.o[i4 - 1] = "null";
    }

    private void a(int i2) {
        int i3 = this.n;
        int[] iArr = this.m;
        if (i3 == iArr.length) {
            int[] iArr2 = new int[(i3 * 2)];
            int[] iArr3 = new int[(i3 * 2)];
            String[] strArr = new String[(i3 * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, i3);
            System.arraycopy(this.p, 0, iArr3, 0, this.n);
            System.arraycopy(this.o, 0, strArr, 0, this.n);
            this.m = iArr2;
            this.p = iArr3;
            this.o = strArr;
        }
        int[] iArr4 = this.m;
        int i4 = this.n;
        this.n = i4 + 1;
        iArr4[i4] = i2;
    }

    private boolean b(int i2) {
        int i3;
        char[] cArr = this.e;
        int i4 = this.i;
        int i5 = this.f;
        this.i = i4 - i5;
        int i6 = this.g;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.g = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            Reader reader = this.c;
            int i8 = this.g;
            int read = reader.read(cArr, i8, cArr.length - i8);
            if (read == -1) {
                return false;
            }
            int i9 = this.g + read;
            this.g = i9;
            if (this.h == 0 && (i3 = this.i) == 0 && i9 > 0 && cArr[0] == 65279) {
                this.f++;
                this.i = i3 + 1;
                i2++;
            }
        } while (this.g < i2);
        return true;
    }

    private int b(boolean z) {
        char[] cArr = this.e;
        int i2 = this.f;
        int i3 = this.g;
        while (true) {
            if (i2 == i3) {
                this.f = i2;
                if (b(1)) {
                    i2 = this.f;
                    i3 = this.g;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input" + x());
                }
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == 10) {
                this.h++;
                this.i = i4;
            } else if (!(c2 == ' ' || c2 == 13 || c2 == 9)) {
                if (c2 == '/') {
                    this.f = i4;
                    if (i4 == i3) {
                        this.f = i4 - 1;
                        boolean b2 = b(2);
                        this.f++;
                        if (!b2) {
                            return c2;
                        }
                    }
                    v();
                    int i5 = this.f;
                    char c3 = cArr[i5];
                    if (c3 == '*') {
                        this.f = i5 + 1;
                        if (a("*/")) {
                            i2 = this.f + 2;
                            i3 = this.g;
                        } else {
                            throw b("Unterminated comment");
                        }
                    } else if (c3 != '/') {
                        return c2;
                    } else {
                        this.f = i5 + 1;
                        w();
                        i2 = this.f;
                        i3 = this.g;
                    }
                } else if (c2 == '#') {
                    this.f = i4;
                    v();
                    w();
                    i2 = this.f;
                    i3 = this.g;
                } else {
                    this.f = i4;
                    return c2;
                }
            }
            i2 = i4;
        }
    }

    private void v() {
        if (!this.d) {
            throw b("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void w() {
        char c2;
        do {
            if (this.f < this.g || b(1)) {
                char[] cArr = this.e;
                int i2 = this.f;
                int i3 = i2 + 1;
                this.f = i3;
                c2 = cArr[i2];
                if (c2 == 10) {
                    this.h++;
                    this.i = i3;
                    return;
                }
            } else {
                return;
            }
        } while (c2 != 13);
    }

    private boolean a(String str) {
        while (true) {
            int i2 = 0;
            if (this.f + str.length() > this.g && !b(str.length())) {
                return false;
            }
            char[] cArr = this.e;
            int i3 = this.f;
            if (cArr[i3] == 10) {
                this.h++;
                this.i = i3 + 1;
            } else {
                while (i2 < str.length()) {
                    if (this.e[this.f + i2] == str.charAt(i2)) {
                        i2++;
                    }
                }
                return true;
            }
            this.f++;
        }
    }

    public String toString() {
        return getClass().getSimpleName() + x();
    }

    /* access modifiers changed from: private */
    public String x() {
        return " at line " + (this.h + 1) + " column " + ((this.f - this.i) + 1) + " path " + p();
    }

    public String p() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        int i2 = this.n;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = this.m[i3];
            if (i4 == 1 || i4 == 2) {
                sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
                sb.append(this.p[i3]);
                sb.append(']');
            } else if (i4 == 3 || i4 == 4 || i4 == 5) {
                sb.append('.');
                String[] strArr = this.o;
                if (strArr[i3] != null) {
                    sb.append(strArr[i3]);
                }
            }
        }
        return sb.toString();
    }

    private char y() {
        int i2;
        int i3;
        if (this.f != this.g || b(1)) {
            char[] cArr = this.e;
            int i4 = this.f;
            int i5 = i4 + 1;
            this.f = i5;
            char c2 = cArr[i4];
            if (c2 == 10) {
                this.h++;
                this.i = i5;
            } else if (!(c2 == '\"' || c2 == '\'' || c2 == '/' || c2 == '\\')) {
                if (c2 == 'b') {
                    return 8;
                }
                if (c2 == 'f') {
                    return 12;
                }
                if (c2 == 'n') {
                    return 10;
                }
                if (c2 == 'r') {
                    return ASCIIPropertyListParser.WHITESPACE_CARRIAGE_RETURN;
                }
                if (c2 == 't') {
                    return 9;
                }
                if (c2 != 'u') {
                    throw b("Invalid escape sequence");
                } else if (i5 + 4 <= this.g || b(4)) {
                    char c3 = 0;
                    int i6 = this.f;
                    int i7 = i6 + 4;
                    while (i6 < i7) {
                        char c4 = this.e[i6];
                        char c5 = (char) (c3 << 4);
                        if (c4 < '0' || c4 > '9') {
                            if (c4 >= 'a' && c4 <= 'f') {
                                i2 = c4 - 'a';
                            } else if (c4 < 'A' || c4 > 'F') {
                                throw new NumberFormatException("\\u" + new String(this.e, this.f, 4));
                            } else {
                                i2 = c4 - 'A';
                            }
                            i3 = i2 + 10;
                        } else {
                            i3 = c4 - '0';
                        }
                        c3 = (char) (c5 + i3);
                        i6++;
                    }
                    this.f += 4;
                    return c3;
                } else {
                    throw b("Unterminated escape sequence");
                }
            }
            return c2;
        }
        throw b("Unterminated escape sequence");
    }

    private IOException b(String str) {
        throw new k(str + x());
    }

    private void z() {
        b(true);
        int i2 = this.f - 1;
        this.f = i2;
        char[] cArr = b;
        if (i2 + cArr.length <= this.g || b(cArr.length)) {
            int i3 = 0;
            while (true) {
                char[] cArr2 = b;
                if (i3 >= cArr2.length) {
                    this.f += cArr2.length;
                    return;
                } else if (this.e[this.f + i3] == cArr2[i3]) {
                    i3++;
                } else {
                    return;
                }
            }
        }
    }
}
