package org.unbescape.xml;

import com.google.common.base.Ascii;
import java.util.Arrays;
import org.unbescape.xml.XmlEscapeSymbols;

final class Xml11EscapeSymbolsInitializer {
    static XmlEscapeSymbols initializeXml11(boolean z) {
        XmlEscapeSymbols.References references = new XmlEscapeSymbols.References();
        references.addReference(34, "&quot;");
        references.addReference(38, "&amp;");
        references.addReference(39, "&apos;");
        references.addReference(60, "&lt;");
        references.addReference(62, "&gt;");
        byte[] bArr = new byte[161];
        Arrays.fill(bArr, (byte) 3);
        for (char c = 128; c < 161; c = (char) (c + 1)) {
            bArr[c] = 2;
        }
        for (char c2 = 'A'; c2 <= 'Z'; c2 = (char) (c2 + 1)) {
            bArr[c2] = 4;
        }
        for (char c3 = 'a'; c3 <= 'z'; c3 = (char) (c3 + 1)) {
            bArr[c3] = 4;
        }
        for (char c4 = '0'; c4 <= '9'; c4 = (char) (c4 + 1)) {
            bArr[c4] = 4;
        }
        bArr[39] = 1;
        bArr[34] = 1;
        bArr[60] = 1;
        bArr[62] = 1;
        bArr[38] = 1;
        if (z) {
            bArr[9] = 1;
            bArr[10] = 1;
            bArr[13] = 1;
        }
        for (char c5 = 1; c5 <= 8; c5 = (char) (c5 + 1)) {
            bArr[c5] = 1;
        }
        bArr[11] = 1;
        bArr[12] = 1;
        for (char c6 = 14; c6 <= 31; c6 = (char) (c6 + 1)) {
            bArr[c6] = 1;
        }
        for (char c7 = Ascii.MAX; c7 <= 132; c7 = (char) (c7 + 1)) {
            bArr[c7] = 1;
        }
        for (char c8 = 134; c8 <= 159; c8 = (char) (c8 + 1)) {
            bArr[c8] = 1;
        }
        return new XmlEscapeSymbols(references, bArr, new Xml11CodepointValidator());
    }

    private Xml11EscapeSymbolsInitializer() {
    }

    static final class Xml11CodepointValidator implements XmlCodepointValidator {
        public boolean isValid(int i) {
            if (i == 0) {
                return false;
            }
            if (i <= 55295) {
                return true;
            }
            if (i < 57344) {
                return false;
            }
            return i <= 65533 || i >= 65536;
        }

        Xml11CodepointValidator() {
        }
    }
}
