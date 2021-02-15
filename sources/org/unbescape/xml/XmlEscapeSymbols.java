package org.unbescape.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

final class XmlEscapeSymbols {
    static final char LEVELS_LEN = '¡';
    static final XmlEscapeSymbols XML10_ATTRIBUTE_SYMBOLS = Xml10EscapeSymbolsInitializer.initializeXml10(true);
    static final XmlEscapeSymbols XML10_SYMBOLS = Xml10EscapeSymbolsInitializer.initializeXml10(false);
    static final XmlEscapeSymbols XML11_ATTRIBUTE_SYMBOLS = Xml11EscapeSymbolsInitializer.initializeXml11(true);
    static final XmlEscapeSymbols XML11_SYMBOLS = Xml11EscapeSymbolsInitializer.initializeXml11(false);
    final XmlCodepointValidator CODEPOINT_VALIDATOR;
    final byte[] ESCAPE_LEVELS;
    final char[][] SORTED_CERS;
    final char[][] SORTED_CERS_BY_CODEPOINT;
    final int[] SORTED_CODEPOINTS;
    final int[] SORTED_CODEPOINTS_BY_CER;

    XmlEscapeSymbols(References references, byte[] bArr, XmlCodepointValidator xmlCodepointValidator) {
        byte[] bArr2 = new byte[161];
        this.ESCAPE_LEVELS = bArr2;
        this.CODEPOINT_VALIDATOR = xmlCodepointValidator;
        System.arraycopy(bArr, 0, bArr2, 0, 161);
        int size = references.references.size();
        int i = size + 5;
        ArrayList arrayList = new ArrayList(i);
        ArrayList arrayList2 = new ArrayList(i);
        for (Reference reference : references.references) {
            arrayList.add(reference.cer);
            arrayList2.add(Integer.valueOf(reference.codepoint));
        }
        this.SORTED_CODEPOINTS = new int[size];
        this.SORTED_CERS_BY_CODEPOINT = new char[size][];
        this.SORTED_CERS = new char[size][];
        this.SORTED_CODEPOINTS_BY_CER = new int[size];
        ArrayList arrayList3 = new ArrayList(arrayList);
        Collections.sort(arrayList3, new Comparator<char[]>() {
            public int compare(char[] cArr, char[] cArr2) {
                return new String(cArr).compareTo(new String(cArr2));
            }
        });
        ArrayList arrayList4 = new ArrayList(arrayList2);
        Collections.sort(arrayList4);
        for (short s = 0; s < size; s = (short) (s + 1)) {
            int intValue = ((Integer) arrayList4.get(s)).intValue();
            this.SORTED_CODEPOINTS[s] = intValue;
            short s2 = 0;
            while (true) {
                if (s2 >= size) {
                    break;
                } else if (intValue == ((Integer) arrayList2.get(s2)).intValue()) {
                    this.SORTED_CERS_BY_CODEPOINT[s] = (char[]) arrayList.get(s2);
                    break;
                } else {
                    s2 = (short) (s2 + 1);
                }
            }
        }
        for (short s3 = 0; s3 < size; s3 = (short) (s3 + 1)) {
            char[] cArr = (char[]) arrayList3.get(s3);
            this.SORTED_CERS[s3] = cArr;
            short s4 = 0;
            while (true) {
                if (s4 >= size) {
                    break;
                } else if (Arrays.equals(cArr, (char[]) arrayList.get(s4))) {
                    this.SORTED_CODEPOINTS_BY_CER[s3] = ((Integer) arrayList2.get(s4)).intValue();
                    break;
                } else {
                    s4 = (short) (s4 + 1);
                }
            }
        }
    }

    private static int compare(char[] cArr, String str, int i, int i2) {
        int i3 = i2 - i;
        int min = Math.min(cArr.length, i3);
        int i4 = 1;
        while (i4 < min) {
            char charAt = str.charAt(i + i4);
            if (cArr[i4] < charAt) {
                return -1;
            }
            if (cArr[i4] > charAt) {
                return 1;
            }
            i4++;
        }
        if (cArr.length > i4) {
            return 1;
        }
        if (i3 > i4) {
            return -1;
        }
        return 0;
    }

    private static int compare(char[] cArr, char[] cArr2, int i, int i2) {
        int i3 = i2 - i;
        int min = Math.min(cArr.length, i3);
        int i4 = 1;
        while (i4 < min) {
            char c = cArr2[i + i4];
            if (cArr[i4] < c) {
                return -1;
            }
            if (cArr[i4] > c) {
                return 1;
            }
            i4++;
        }
        if (cArr.length > i4) {
            return 1;
        }
        if (i3 > i4) {
            return -1;
        }
        return 0;
    }

    static int binarySearch(char[][] cArr, String str, int i, int i2) {
        int length = cArr.length - 1;
        int i3 = 0;
        while (i3 <= length) {
            int i4 = (i3 + length) >>> 1;
            int compare = compare(cArr[i4], str, i, i2);
            if (compare == -1) {
                i3 = i4 + 1;
            } else if (compare != 1) {
                return i4;
            } else {
                length = i4 - 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    static int binarySearch(char[][] cArr, char[] cArr2, int i, int i2) {
        int length = cArr.length - 1;
        int i3 = 0;
        while (i3 <= length) {
            int i4 = (i3 + length) >>> 1;
            int compare = compare(cArr[i4], cArr2, i, i2);
            if (compare == -1) {
                i3 = i4 + 1;
            } else if (compare != 1) {
                return i4;
            } else {
                length = i4 - 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    static final class References {
        /* access modifiers changed from: private */
        public final List<Reference> references = new ArrayList(200);

        References() {
        }

        /* access modifiers changed from: package-private */
        public void addReference(int i, String str) {
            this.references.add(new Reference(str, i));
        }
    }

    private static final class Reference {
        /* access modifiers changed from: private */
        public final char[] cer;
        /* access modifiers changed from: private */
        public final int codepoint;

        private Reference(String str, int i) {
            this.cer = str.toCharArray();
            this.codepoint = i;
        }
    }
}
