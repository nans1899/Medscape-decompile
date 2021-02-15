package com.dd.plist;

import com.facebook.appevents.AppEventsConstants;
import java.io.IOException;

public class UID extends NSObject {
    private byte[] bytes;
    private String name;

    public UID(String str, byte[] bArr) {
        this.name = str;
        this.bytes = bArr;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public void toXML(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("<string>");
        int i2 = 0;
        while (true) {
            byte[] bArr = this.bytes;
            if (i2 < bArr.length) {
                byte b = bArr[i2];
                if (b < 16) {
                    sb.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                sb.append(Integer.toHexString(b));
                i2++;
            } else {
                sb.append("</string>");
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException {
        binaryPropertyListWriter.write((this.bytes.length + 128) - 1);
        binaryPropertyListWriter.write(this.bytes);
    }

    /* access modifiers changed from: protected */
    public void toASCII(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("\"");
        int i2 = 0;
        while (true) {
            byte[] bArr = this.bytes;
            if (i2 < bArr.length) {
                byte b = bArr[i2];
                if (b < 16) {
                    sb.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                sb.append(Integer.toHexString(b));
                i2++;
            } else {
                sb.append("\"");
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void toASCIIGnuStep(StringBuilder sb, int i) {
        toASCII(sb, i);
    }
}
