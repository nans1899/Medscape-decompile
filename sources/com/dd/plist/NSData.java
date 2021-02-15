package com.dd.plist;

import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;

public class NSData extends NSObject {
    private byte[] bytes;

    public NSData(byte[] bArr) {
        this.bytes = bArr;
    }

    public NSData(String str) throws IOException {
        this.bytes = Base64.decode(str.replaceAll("\\s+", ""));
    }

    public NSData(File file) throws FileNotFoundException, IOException {
        this.bytes = new byte[((int) file.length())];
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        randomAccessFile.read(this.bytes);
        randomAccessFile.close();
    }

    public byte[] bytes() {
        return this.bytes;
    }

    public int length() {
        return this.bytes.length;
    }

    public void getBytes(ByteBuffer byteBuffer, int i) {
        byte[] bArr = this.bytes;
        byteBuffer.put(bArr, 0, Math.min(bArr.length, i));
    }

    public void getBytes(ByteBuffer byteBuffer, int i, int i2) {
        byte[] bArr = this.bytes;
        byteBuffer.put(bArr, i, Math.min(bArr.length, i2));
    }

    public String getBase64EncodedData() {
        return Base64.encodeBytes(this.bytes);
    }

    public boolean equals(Object obj) {
        return obj.getClass().equals(getClass()) && Arrays.equals(((NSData) obj).bytes, this.bytes);
    }

    public int hashCode() {
        return 335 + Arrays.hashCode(this.bytes);
    }

    /* access modifiers changed from: package-private */
    public void toXML(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("<data>");
        sb.append(NSObject.NEWLINE);
        for (String append : getBase64EncodedData().split(IOUtils.LINE_SEPARATOR_UNIX)) {
            indent(sb, i + 1);
            sb.append(append);
            sb.append(NSObject.NEWLINE);
        }
        indent(sb, i);
        sb.append("</data>");
    }

    /* access modifiers changed from: package-private */
    public void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException {
        binaryPropertyListWriter.writeIntHeader(4, this.bytes.length);
        binaryPropertyListWriter.write(this.bytes);
    }

    /* access modifiers changed from: protected */
    public void toASCII(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append('<');
        int lastIndexOf = sb.lastIndexOf(NEWLINE);
        int i2 = 0;
        while (true) {
            byte[] bArr = this.bytes;
            if (i2 < bArr.length) {
                byte b = bArr[i2] & 255;
                if (b < 16) {
                    sb.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                sb.append(Integer.toHexString(b));
                if (sb.length() - lastIndexOf > 80) {
                    sb.append(NEWLINE);
                    lastIndexOf = sb.length();
                } else if ((i2 + 1) % 2 == 0 && i2 != this.bytes.length - 1) {
                    sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
                i2++;
            } else {
                sb.append('>');
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void toASCIIGnuStep(StringBuilder sb, int i) {
        toASCII(sb, i);
    }
}
