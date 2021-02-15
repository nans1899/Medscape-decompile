package bo.app;

import bo.app.bb;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

final class bc {
    final String a;
    final long[] b;
    boolean c;
    bb.a d;
    long e;
    private int f;
    private File g;

    bc(String str, int i, File file) {
        this.a = str;
        this.f = i;
        this.g = file;
        this.b = new long[i];
    }

    public String a() {
        StringBuilder sb = new StringBuilder();
        for (long append : this.b) {
            sb.append(' ');
            sb.append(append);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void a(String[] strArr) {
        if (strArr.length == this.f) {
            int i = 0;
            while (i < strArr.length) {
                try {
                    this.b[i] = Long.parseLong(strArr[i]);
                    i++;
                } catch (NumberFormatException unused) {
                    throw b(strArr);
                }
            }
            return;
        }
        throw b(strArr);
    }

    /* access modifiers changed from: package-private */
    public IOException b(String[] strArr) {
        throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
    }

    public File a(int i) {
        File file = this.g;
        return new File(file, this.a + "." + i);
    }

    public File b(int i) {
        File file = this.g;
        return new File(file, this.a + "." + i + ".tmp");
    }
}
