package com.google.zxing.datamatrix.encoder;

import com.dd.plist.ASCIIPropertyListParser;
import net.bytebuddy.pool.TypePool;

final class TextEncoder extends C40Encoder {
    public int getEncodingMode() {
        return 2;
    }

    TextEncoder() {
    }

    /* access modifiers changed from: package-private */
    public int encodeChar(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append(3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'a' && c <= 'z') {
            sb.append((char) ((c - 'a') + 14));
            return 1;
        } else if (c >= 0 && c <= 31) {
            sb.append(0);
            sb.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            sb.append(1);
            sb.append((char) (c - '!'));
            return 2;
        } else if (c >= ':' && c <= '@') {
            sb.append(1);
            sb.append((char) ((c - ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER) + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            sb.append(1);
            sb.append((char) ((c - TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH) + 22));
            return 2;
        } else if (c == '`') {
            sb.append(2);
            sb.append((char) (c - '`'));
            return 2;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append(2);
            sb.append((char) ((c - 'A') + 1));
            return 2;
        } else if (c >= '{' && c <= 127) {
            sb.append(2);
            sb.append((char) ((c - ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN) + 27));
            return 2;
        } else if (c >= 128) {
            sb.append("\u0001\u001e");
            return encodeChar((char) (c - 128), sb) + 2;
        } else {
            HighLevelEncoder.illegalCharacter(c);
            return -1;
        }
    }
}