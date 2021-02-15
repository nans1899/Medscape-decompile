package com.dd.plist;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class NSDate extends NSObject {
    private static final long EPOCH = 978307200000L;
    private static final SimpleDateFormat sdfDefault = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private static final SimpleDateFormat sdfGnuStep = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private Date date;

    static {
        sdfDefault.setTimeZone(TimeZone.getTimeZone("GMT"));
        sdfGnuStep.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r2 = sdfGnuStep.parse(r2);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.util.Date parseDateString(java.lang.String r2) throws java.text.ParseException {
        /*
            java.lang.Class<com.dd.plist.NSDate> r0 = com.dd.plist.NSDate.class
            monitor-enter(r0)
            java.text.SimpleDateFormat r1 = sdfDefault     // Catch:{ ParseException -> 0x000d }
            java.util.Date r2 = r1.parse(r2)     // Catch:{ ParseException -> 0x000d }
            monitor-exit(r0)
            return r2
        L_0x000b:
            r2 = move-exception
            goto L_0x0015
        L_0x000d:
            java.text.SimpleDateFormat r1 = sdfGnuStep     // Catch:{ all -> 0x000b }
            java.util.Date r2 = r1.parse(r2)     // Catch:{ all -> 0x000b }
            monitor-exit(r0)
            return r2
        L_0x0015:
            monitor-exit(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dd.plist.NSDate.parseDateString(java.lang.String):java.util.Date");
    }

    private static synchronized String makeDateString(Date date2) {
        String format;
        synchronized (NSDate.class) {
            format = sdfDefault.format(date2);
        }
        return format;
    }

    private static synchronized String makeDateStringGnuStep(Date date2) {
        String format;
        synchronized (NSDate.class) {
            format = sdfGnuStep.format(date2);
        }
        return format;
    }

    public NSDate(byte[] bArr) {
        this.date = new Date(((long) (BinaryPropertyListParser.parseDouble(bArr) * 1000.0d)) + EPOCH);
    }

    public NSDate(String str) throws ParseException {
        this.date = parseDateString(str);
    }

    public NSDate(Date date2) {
        if (date2 != null) {
            this.date = date2;
            return;
        }
        throw new IllegalArgumentException("Date cannot be null");
    }

    public Date getDate() {
        return this.date;
    }

    public boolean equals(Object obj) {
        return obj.getClass().equals(getClass()) && this.date.equals(((NSDate) obj).getDate());
    }

    public int hashCode() {
        return this.date.hashCode();
    }

    /* access modifiers changed from: package-private */
    public void toXML(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("<date>");
        sb.append(makeDateString(this.date));
        sb.append("</date>");
    }

    public void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException {
        binaryPropertyListWriter.write(51);
        binaryPropertyListWriter.writeDouble(((double) (this.date.getTime() - EPOCH)) / 1000.0d);
    }

    public String toString() {
        return this.date.toString();
    }

    /* access modifiers changed from: protected */
    public void toASCII(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("\"");
        sb.append(makeDateString(this.date));
        sb.append("\"");
    }

    /* access modifiers changed from: protected */
    public void toASCIIGnuStep(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("<*D");
        sb.append(makeDateStringGnuStep(this.date));
        sb.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
    }
}
