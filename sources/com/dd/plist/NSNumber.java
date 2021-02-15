package com.dd.plist;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import okhttp3.internal.ws.WebSocketProtocol;

public class NSNumber extends NSObject implements Comparable<Object> {
    public static final int BOOLEAN = 2;
    public static final int INTEGER = 0;
    public static final int REAL = 1;
    private boolean boolValue;
    private double doubleValue;
    private long longValue;
    private int type;

    public NSNumber(byte[] bArr, int i) {
        if (i == 0) {
            long parseLong = BinaryPropertyListParser.parseLong(bArr);
            this.longValue = parseLong;
            this.doubleValue = (double) parseLong;
        } else if (i == 1) {
            double parseDouble = BinaryPropertyListParser.parseDouble(bArr);
            this.doubleValue = parseDouble;
            this.longValue = Math.round(parseDouble);
        } else {
            throw new IllegalArgumentException("Type argument is not valid.");
        }
        this.type = i;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(11:6|5|7|8|9|10|14|15|17|19|(0)(0)) */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0061, code lost:
        throw new java.lang.Exception("not a boolean");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
        r4.type = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
        if (r4.boolValue != false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0069, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006c, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006e, code lost:
        r4.longValue = r0;
        r4.doubleValue = (double) r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0022 */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0059 A[Catch:{ Exception -> 0x0012, Exception -> 0x0074 }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005a A[Catch:{ Exception -> 0x0012, Exception -> 0x0074 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NSNumber(java.lang.String r5) {
        /*
            r4 = this;
            r4.<init>()
            if (r5 == 0) goto L_0x007c
            r0 = 0
            long r1 = java.lang.Long.parseLong(r5)     // Catch:{ Exception -> 0x0012 }
            r4.longValue = r1     // Catch:{ Exception -> 0x0012 }
            double r1 = (double) r1     // Catch:{ Exception -> 0x0012 }
            r4.doubleValue = r1     // Catch:{ Exception -> 0x0012 }
            r4.type = r0     // Catch:{ Exception -> 0x0012 }
            goto L_0x0073
        L_0x0012:
            r1 = 1
            double r2 = java.lang.Double.parseDouble(r5)     // Catch:{ Exception -> 0x0022 }
            r4.doubleValue = r2     // Catch:{ Exception -> 0x0022 }
            long r2 = java.lang.Math.round(r2)     // Catch:{ Exception -> 0x0022 }
            r4.longValue = r2     // Catch:{ Exception -> 0x0022 }
            r4.type = r1     // Catch:{ Exception -> 0x0022 }
            goto L_0x0073
        L_0x0022:
            java.lang.String r2 = r5.toLowerCase()     // Catch:{ Exception -> 0x0074 }
            java.lang.String r3 = "true"
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x0074 }
            if (r2 != 0) goto L_0x003c
            java.lang.String r2 = r5.toLowerCase()     // Catch:{ Exception -> 0x0074 }
            java.lang.String r3 = "yes"
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x0074 }
            if (r2 == 0) goto L_0x003d
        L_0x003c:
            r0 = 1
        L_0x003d:
            r4.boolValue = r0     // Catch:{ Exception -> 0x0074 }
            if (r0 != 0) goto L_0x0062
            java.lang.String r0 = r5.toLowerCase()     // Catch:{ Exception -> 0x0074 }
            java.lang.String r1 = "false"
            boolean r0 = r0.equals(r1)     // Catch:{ Exception -> 0x0074 }
            if (r0 != 0) goto L_0x0062
            java.lang.String r5 = r5.toLowerCase()     // Catch:{ Exception -> 0x0074 }
            java.lang.String r0 = "no"
            boolean r5 = r5.equals(r0)     // Catch:{ Exception -> 0x0074 }
            if (r5 == 0) goto L_0x005a
            goto L_0x0062
        L_0x005a:
            java.lang.Exception r5 = new java.lang.Exception     // Catch:{ Exception -> 0x0074 }
            java.lang.String r0 = "not a boolean"
            r5.<init>(r0)     // Catch:{ Exception -> 0x0074 }
            throw r5     // Catch:{ Exception -> 0x0074 }
        L_0x0062:
            r5 = 2
            r4.type = r5     // Catch:{ Exception -> 0x0074 }
            boolean r5 = r4.boolValue     // Catch:{ Exception -> 0x0074 }
            if (r5 == 0) goto L_0x006c
            r0 = 1
            goto L_0x006e
        L_0x006c:
            r0 = 0
        L_0x006e:
            r4.longValue = r0     // Catch:{ Exception -> 0x0074 }
            double r0 = (double) r0     // Catch:{ Exception -> 0x0074 }
            r4.doubleValue = r0     // Catch:{ Exception -> 0x0074 }
        L_0x0073:
            return
        L_0x0074:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "The given string neither represents a double, an int nor a boolean value."
            r5.<init>(r0)
            throw r5
        L_0x007c:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "The given string is null and cannot be parsed as number."
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dd.plist.NSNumber.<init>(java.lang.String):void");
    }

    public NSNumber(int i) {
        long j = (long) i;
        this.longValue = j;
        this.doubleValue = (double) j;
        this.type = 0;
    }

    public NSNumber(long j) {
        this.longValue = j;
        this.doubleValue = (double) j;
        this.type = 0;
    }

    public NSNumber(double d) {
        this.doubleValue = d;
        this.longValue = (long) d;
        this.type = 1;
    }

    public NSNumber(boolean z) {
        this.boolValue = z;
        long j = z ? 1 : 0;
        this.longValue = j;
        this.doubleValue = (double) j;
        this.type = 2;
    }

    public int type() {
        return this.type;
    }

    public boolean isBoolean() {
        return this.type == 2;
    }

    public boolean isInteger() {
        return this.type == 0;
    }

    public boolean isReal() {
        return this.type == 1;
    }

    public boolean boolValue() {
        if (this.type == 2) {
            return this.boolValue;
        }
        return this.longValue != 0;
    }

    public long longValue() {
        return this.longValue;
    }

    public int intValue() {
        return (int) this.longValue;
    }

    public double doubleValue() {
        return this.doubleValue;
    }

    public float floatValue() {
        return (float) this.doubleValue;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NSNumber)) {
            return false;
        }
        NSNumber nSNumber = (NSNumber) obj;
        if (this.type == nSNumber.type && this.longValue == nSNumber.longValue && this.doubleValue == nSNumber.doubleValue && this.boolValue == nSNumber.boolValue) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.longValue;
        return (((((this.type * 37) + ((int) (j ^ (j >>> 32)))) * 37) + ((int) (Double.doubleToLongBits(this.doubleValue) ^ (Double.doubleToLongBits(this.doubleValue) >>> 32)))) * 37) + (boolValue() ? 1 : 0);
    }

    public String toString() {
        int i = this.type;
        if (i == 0) {
            return String.valueOf(longValue());
        }
        if (i == 1) {
            return String.valueOf(doubleValue());
        }
        if (i != 2) {
            return super.toString();
        }
        return String.valueOf(boolValue());
    }

    /* access modifiers changed from: package-private */
    public void toXML(StringBuilder sb, int i) {
        indent(sb, i);
        int i2 = this.type;
        if (i2 == 0) {
            sb.append("<integer>");
            sb.append(longValue());
            sb.append("</integer>");
        } else if (i2 == 1) {
            sb.append("<real>");
            sb.append(doubleValue());
            sb.append("</real>");
        } else if (i2 == 2) {
            if (boolValue()) {
                sb.append("<true/>");
            } else {
                sb.append("<false/>");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException {
        int type2 = type();
        int i = 8;
        if (type2 != 0) {
            if (type2 == 1) {
                binaryPropertyListWriter.write(35);
                binaryPropertyListWriter.writeDouble(doubleValue());
            } else if (type2 == 2) {
                if (boolValue()) {
                    i = 9;
                }
                binaryPropertyListWriter.write(i);
            }
        } else if (longValue() < 0) {
            binaryPropertyListWriter.write(19);
            binaryPropertyListWriter.writeBytes(longValue(), 8);
        } else if (longValue() <= 255) {
            binaryPropertyListWriter.write(16);
            binaryPropertyListWriter.writeBytes(longValue(), 1);
        } else if (longValue() <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
            binaryPropertyListWriter.write(17);
            binaryPropertyListWriter.writeBytes(longValue(), 2);
        } else if (longValue() <= 4294967295L) {
            binaryPropertyListWriter.write(18);
            binaryPropertyListWriter.writeBytes(longValue(), 4);
        } else {
            binaryPropertyListWriter.write(19);
            binaryPropertyListWriter.writeBytes(longValue(), 8);
        }
    }

    /* access modifiers changed from: protected */
    public void toASCII(StringBuilder sb, int i) {
        indent(sb, i);
        if (this.type == 2) {
            sb.append(this.boolValue ? "YES" : "NO");
        } else {
            sb.append(toString());
        }
    }

    /* access modifiers changed from: protected */
    public void toASCIIGnuStep(StringBuilder sb, int i) {
        indent(sb, i);
        int i2 = this.type;
        if (i2 == 0) {
            sb.append("<*I");
            sb.append(toString());
            sb.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        } else if (i2 == 1) {
            sb.append("<*R");
            sb.append(toString());
            sb.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        } else if (i2 == 2) {
            if (this.boolValue) {
                sb.append("<*BY>");
            } else {
                sb.append("<*BN>");
            }
        }
    }

    public int compareTo(Object obj) {
        double doubleValue2 = doubleValue();
        if (obj instanceof NSNumber) {
            double doubleValue3 = ((NSNumber) obj).doubleValue();
            if (doubleValue2 < doubleValue3) {
                return -1;
            }
            if (doubleValue2 == doubleValue3) {
                return 0;
            }
            return 1;
        } else if (!(obj instanceof Number)) {
            return -1;
        } else {
            double doubleValue4 = ((Number) obj).doubleValue();
            if (doubleValue2 < doubleValue4) {
                return -1;
            }
            if (doubleValue2 == doubleValue4) {
                return 0;
            }
            return 1;
        }
    }
}
