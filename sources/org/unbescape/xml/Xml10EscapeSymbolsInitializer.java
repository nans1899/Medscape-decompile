package org.unbescape.xml;

import com.google.common.base.Ascii;
import java.util.Arrays;
import org.unbescape.xml.XmlEscapeSymbols;

final class Xml10EscapeSymbolsInitializer {
    static XmlEscapeSymbols initializeXml10(boolean z) {
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
        for (char c5 = Ascii.MAX; c5 <= 132; c5 = (char) (c5 + 1)) {
            bArr[c5] = 1;
        }
        for (char c6 = 134; c6 <= 159; c6 = (char) (c6 + 1)) {
            bArr[c6] = 1;
        }
        return new XmlEscapeSymbols(references, bArr, new Xml10CodepointValidator());
    }

    private Xml10EscapeSymbolsInitializer() {
    }

    static final class Xml10CodepointValidator implements XmlCodepointValidator {
        public boolean isValid(int i) {
            if (i < 32) {
                return i == 9 || i == 10 || i == 13;
            }
            if (i <= 55295) {
                return true;
            }
            if (i < 57344) {
                return false;
            }
            return i <= 65533 || i >= 65536;
        }

        Xml10CodepointValidator() {
        }
    }
}
