package com.wutka.dtd;

import java.io.IOException;

public class DTDParseException extends IOException {
    public int column;
    public int lineNumber;
    public String uriID;

    public DTDParseException() {
        this.uriID = "";
        this.lineNumber = -1;
        this.column = -1;
    }

    public DTDParseException(String str) {
        super(str);
        this.uriID = "";
        this.lineNumber = -1;
        this.column = -1;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DTDParseException(java.lang.String r3, int r4, int r5) {
        /*
            r2 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "At line "
            r0.append(r1)
            r0.append(r4)
            java.lang.String r1 = ", column "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r1 = ": "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            java.lang.String r3 = ""
            r2.uriID = r3
            r2.lineNumber = r4
            r2.column = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wutka.dtd.DTDParseException.<init>(java.lang.String, int, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DTDParseException(java.lang.String r4, java.lang.String r5, int r6, int r7) {
        /*
            r3 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            if (r4 == 0) goto L_0x0024
            int r1 = r4.length()
            if (r1 <= 0) goto L_0x0024
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "URI "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r2 = " at "
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            goto L_0x0026
        L_0x0024:
            java.lang.String r1 = "At "
        L_0x0026:
            r0.append(r1)
            java.lang.String r1 = "line "
            r0.append(r1)
            r0.append(r6)
            java.lang.String r1 = ", column "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r1 = ": "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r3.<init>(r5)
            java.lang.String r5 = ""
            r3.uriID = r5
            if (r4 == 0) goto L_0x0050
            r3.uriID = r4
        L_0x0050:
            r3.lineNumber = r6
            r3.column = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wutka.dtd.DTDParseException.<init>(java.lang.String, java.lang.String, int, int):void");
    }

    public String getId() {
        return this.uriID;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public int getColumn() {
        return this.column;
    }
}
