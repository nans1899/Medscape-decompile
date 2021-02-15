package com.mnet.gson.internal;

import com.mnet.gson.k;
import java.io.Writer;
import mnetinternal.j;

public final class i {
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        throw new com.mnet.gson.l((java.lang.Throwable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        throw new com.mnet.gson.t((java.lang.Throwable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002a, code lost:
        return com.mnet.gson.m.a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0030, code lost:
        throw new com.mnet.gson.t((java.lang.Throwable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        r2 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        throw new com.mnet.gson.t((java.lang.Throwable) r2);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0016 A[ExcHandler: IOException (r2v5 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001d A[ExcHandler: k (r2v4 'e' mnetinternal.k A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000f A[ExcHandler: NumberFormatException (r2v6 'e' java.lang.NumberFormatException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mnet.gson.k a(mnetinternal.h r2) {
        /*
            r2.f()     // Catch:{ EOFException -> 0x0024, k -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
            r0 = 0
            com.mnet.gson.v<com.mnet.gson.k> r1 = com.mnet.gson.internal.bind.i.X     // Catch:{ EOFException -> 0x000d, k -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
            java.lang.Object r2 = r1.read(r2)     // Catch:{ EOFException -> 0x000d, k -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
            com.mnet.gson.k r2 = (com.mnet.gson.k) r2     // Catch:{ EOFException -> 0x000d, k -> 0x001d, IOException -> 0x0016, NumberFormatException -> 0x000f }
            return r2
        L_0x000d:
            r2 = move-exception
            goto L_0x0026
        L_0x000f:
            r2 = move-exception
            com.mnet.gson.t r0 = new com.mnet.gson.t
            r0.<init>((java.lang.Throwable) r2)
            throw r0
        L_0x0016:
            r2 = move-exception
            com.mnet.gson.l r0 = new com.mnet.gson.l
            r0.<init>((java.lang.Throwable) r2)
            throw r0
        L_0x001d:
            r2 = move-exception
            com.mnet.gson.t r0 = new com.mnet.gson.t
            r0.<init>((java.lang.Throwable) r2)
            throw r0
        L_0x0024:
            r2 = move-exception
            r0 = 1
        L_0x0026:
            if (r0 == 0) goto L_0x002b
            com.mnet.gson.m r2 = com.mnet.gson.m.a
            return r2
        L_0x002b:
            com.mnet.gson.t r0 = new com.mnet.gson.t
            r0.<init>((java.lang.Throwable) r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mnet.gson.internal.i.a(mnetinternal.h):com.mnet.gson.k");
    }

    public static void a(k kVar, j jVar) {
        com.mnet.gson.internal.bind.i.X.write(jVar, kVar);
    }

    public static Writer a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new a(appendable);
    }

    private static final class a extends Writer {
        private final Appendable a;
        private final C0042a b = new C0042a();

        public void close() {
        }

        public void flush() {
        }

        a(Appendable appendable) {
            this.a = appendable;
        }

        public void write(char[] cArr, int i, int i2) {
            this.b.a = cArr;
            this.a.append(this.b, i, i2 + i);
        }

        public void write(int i) {
            this.a.append((char) i);
        }

        /* renamed from: com.mnet.gson.internal.i$a$a  reason: collision with other inner class name */
        static class C0042a implements CharSequence {
            char[] a;

            C0042a() {
            }

            public int length() {
                return this.a.length;
            }

            public char charAt(int i) {
                return this.a[i];
            }

            public CharSequence subSequence(int i, int i2) {
                return new String(this.a, i, i2 - i);
            }
        }
    }
}
