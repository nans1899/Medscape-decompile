package com.wutka.dtd;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class DTDEntity implements DTDOutput {
    public Object defaultLocation;
    public DTDExternalID externalID;
    public boolean isParsed;
    public String name;
    public String ndata;
    public String value;

    public DTDEntity() {
    }

    public DTDEntity(String str) {
        this.name = str;
    }

    public DTDEntity(String str, Object obj) {
        this.name = str;
        this.defaultLocation = obj;
    }

    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("<!ENTITY ");
        if (this.isParsed) {
            printWriter.print(" % ");
        }
        printWriter.print(this.name);
        String str = this.value;
        if (str != null) {
            char c = '\"';
            if (str.indexOf(34) >= 0) {
                c = '\'';
            }
            printWriter.print(c);
            printWriter.print(this.value);
            printWriter.print(c);
        } else {
            this.externalID.write(printWriter);
            if (this.ndata != null) {
                printWriter.print(" NDATA ");
                printWriter.print(this.ndata);
            }
        }
        printWriter.println(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
    }

    public String getExternalId() {
        return this.externalID.system;
    }

    public Reader getReader() throws IOException {
        DTDExternalID dTDExternalID = this.externalID;
        if (dTDExternalID == null) {
            return null;
        }
        return getReader(dTDExternalID.system);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:13|14|15) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005a, code lost:
        return new java.io.BufferedReader(new java.io.InputStreamReader(new java.net.URL(r5).openStream()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005b, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0047 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.io.Reader getReader(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.defaultLocation     // Catch:{ Exception -> 0x0047 }
            if (r0 == 0) goto L_0x003c
            java.lang.Object r0 = r4.defaultLocation     // Catch:{ Exception -> 0x0047 }
            boolean r0 = r0 instanceof java.io.File     // Catch:{ Exception -> 0x0047 }
            if (r0 == 0) goto L_0x001e
            java.lang.Object r0 = r4.defaultLocation     // Catch:{ Exception -> 0x0047 }
            java.io.File r0 = (java.io.File) r0     // Catch:{ Exception -> 0x0047 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0047 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ Exception -> 0x0047 }
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x0047 }
            r3.<init>(r0, r5)     // Catch:{ Exception -> 0x0047 }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0047 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0047 }
            return r1
        L_0x001e:
            java.lang.Object r0 = r4.defaultLocation     // Catch:{ Exception -> 0x0047 }
            boolean r0 = r0 instanceof java.net.URL     // Catch:{ Exception -> 0x0047 }
            if (r0 == 0) goto L_0x003c
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x0047 }
            java.lang.Object r1 = r4.defaultLocation     // Catch:{ Exception -> 0x0047 }
            java.net.URL r1 = (java.net.URL) r1     // Catch:{ Exception -> 0x0047 }
            r0.<init>(r1, r5)     // Catch:{ Exception -> 0x0047 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0047 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0047 }
            java.io.InputStream r0 = r0.openStream()     // Catch:{ Exception -> 0x0047 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0047 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0047 }
            return r1
        L_0x003c:
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0047 }
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Exception -> 0x0047 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0047 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x0047 }
            return r0
        L_0x0047:
            java.net.URL r0 = new java.net.URL     // Catch:{ Exception -> 0x005b }
            r0.<init>(r5)     // Catch:{ Exception -> 0x005b }
            java.io.InputStream r5 = r0.openStream()     // Catch:{ Exception -> 0x005b }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ Exception -> 0x005b }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x005b }
            r1.<init>(r5)     // Catch:{ Exception -> 0x005b }
            r0.<init>(r1)     // Catch:{ Exception -> 0x005b }
            return r0
        L_0x005b:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wutka.dtd.DTDEntity.getReader(java.lang.String):java.io.Reader");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DTDEntity)) {
            return false;
        }
        DTDEntity dTDEntity = (DTDEntity) obj;
        String str = this.name;
        if (str == null) {
            if (dTDEntity.name != null) {
                return false;
            }
        } else if (!str.equals(dTDEntity.name)) {
            return false;
        }
        if (this.isParsed != dTDEntity.isParsed) {
            return false;
        }
        String str2 = this.value;
        if (str2 == null) {
            if (dTDEntity.value != null) {
                return false;
            }
        } else if (!str2.equals(dTDEntity.value)) {
            return false;
        }
        DTDExternalID dTDExternalID = this.externalID;
        if (dTDExternalID == null) {
            if (dTDEntity.externalID != null) {
                return false;
            }
        } else if (!dTDExternalID.equals(dTDEntity.externalID)) {
            return false;
        }
        String str3 = this.ndata;
        if (str3 == null) {
            if (dTDEntity.ndata != null) {
                return false;
            }
        } else if (!str3.equals(dTDEntity.ndata)) {
            return false;
        }
        return true;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setIsParsed(boolean z) {
        this.isParsed = z;
    }

    public boolean isParsed() {
        return this.isParsed;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setExternalID(DTDExternalID dTDExternalID) {
        this.externalID = dTDExternalID;
    }

    public DTDExternalID getExternalID() {
        return this.externalID;
    }

    public void setNdata(String str) {
        this.ndata = str;
    }

    public String getNdata() {
        return this.ndata;
    }
}
