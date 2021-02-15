package org.unbescape.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class HtmlEscapeSymbols {
    static final HtmlEscapeSymbols HTML4_SYMBOLS = Html4EscapeSymbolsInitializer.initializeHtml4();
    static final HtmlEscapeSymbols HTML5_SYMBOLS = Html5EscapeSymbolsInitializer.initializeHtml5();
    static final char MAX_ASCII_CHAR = '';
    static final int NCRS_BY_CODEPOINT_LEN = 12287;
    static final short NO_NCR = 0;
    final int[][] DOUBLE_CODEPOINTS;
    final byte[] ESCAPE_LEVELS;
    final short[] NCRS_BY_CODEPOINT = new short[NCRS_BY_CODEPOINT_LEN];
    final Map<Integer, Short> NCRS_BY_CODEPOINT_OVERFLOW;
    final int[] SORTED_CODEPOINTS;
    final char[][] SORTED_NCRS;

    HtmlEscapeSymbols(References references, byte[] bArr) {
        byte[] bArr2 = new byte[129];
        this.ESCAPE_LEVELS = bArr2;
        int i = 0;
        System.arraycopy(bArr, 0, bArr2, 0, 129);
        ArrayList arrayList = new ArrayList(references.references.size() + 5);
        ArrayList arrayList2 = new ArrayList(references.references.size() + 5);
        ArrayList arrayList3 = new ArrayList(100);
        HashMap hashMap = new HashMap(20);
        for (Reference reference : references.references) {
            char[] access$100 = reference.ncr;
            int[] access$200 = reference.codepoints;
            arrayList.add(access$100);
            if (access$200.length == 1) {
                arrayList2.add(Integer.valueOf(access$200[0]));
            } else if (access$200.length == 2) {
                arrayList3.add(access$200);
                arrayList2.add(Integer.valueOf(arrayList3.size() * -1));
            } else {
                throw new RuntimeException("Unsupported codepoints #: " + access$200.length + " for " + new String(access$100));
            }
        }
        Arrays.fill(this.NCRS_BY_CODEPOINT, 0);
        this.SORTED_NCRS = new char[arrayList.size()][];
        this.SORTED_CODEPOINTS = new int[arrayList2.size()];
        ArrayList arrayList4 = new ArrayList(arrayList);
        Collections.sort(arrayList4, new Comparator<char[]>() {
            public int compare(char[] cArr, char[] cArr2) {
                return HtmlEscapeSymbols.compare(cArr, cArr2, 0, cArr2.length);
            }
        });
        for (short s = 0; s < this.SORTED_NCRS.length; s = (short) (s + 1)) {
            char[] cArr = (char[]) arrayList4.get(s);
            this.SORTED_NCRS[s] = cArr;
            short s2 = 0;
            while (true) {
                if (s2 >= this.SORTED_NCRS.length) {
                    break;
                } else if (Arrays.equals(cArr, (char[]) arrayList.get(s2))) {
                    int intValue = ((Integer) arrayList2.get(s2)).intValue();
                    this.SORTED_CODEPOINTS[s] = intValue;
                    if (intValue > 0) {
                        if (intValue < NCRS_BY_CODEPOINT_LEN) {
                            short[] sArr = this.NCRS_BY_CODEPOINT;
                            if (sArr[intValue] == 0) {
                                sArr[intValue] = s;
                            } else {
                                if (positionInList(arrayList, cArr) < positionInList(arrayList, this.SORTED_NCRS[sArr[intValue]])) {
                                    this.NCRS_BY_CODEPOINT[intValue] = s;
                                }
                            }
                        } else {
                            hashMap.put(Integer.valueOf(intValue), Short.valueOf(s));
                        }
                    }
                } else {
                    s2 = (short) (s2 + 1);
                }
            }
        }
        if (hashMap.size() > 0) {
            this.NCRS_BY_CODEPOINT_OVERFLOW = hashMap;
        } else {
            this.NCRS_BY_CODEPOINT_OVERFLOW = null;
        }
        if (arrayList3.size() > 0) {
            this.DOUBLE_CODEPOINTS = new int[arrayList3.size()][];
            while (true) {
                int[][] iArr = this.DOUBLE_CODEPOINTS;
                if (i < iArr.length) {
                    iArr[i] = (int[]) arrayList3.get(i);
                    i++;
                } else {
                    return;
                }
            }
        } else {
            this.DOUBLE_CODEPOINTS = null;
        }
    }

    private static int positionInList(List<char[]> list, char[] cArr) {
        int i = 0;
        for (char[] equals : list) {
            if (Arrays.equals(equals, cArr)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static int compare(char[] cArr, String str, int i, int i2) {
        int i3 = i2 - i;
        int min = Math.min(cArr.length, i3);
        int i4 = 1;
        while (i4 < min) {
            char charAt = str.charAt(i + i4);
            if (cArr[i4] < charAt) {
                return charAt == ';' ? 1 : -1;
            }
            if (cArr[i4] > charAt) {
                return cArr[i4] == ';' ? -1 : 1;
            }
            i4++;
        }
        if (cArr.length > i4) {
            return cArr[i4] == ';' ? -1 : 1;
        }
        if (i3 <= i4) {
            return 0;
        }
        if (str.charAt(i + i4) == ';') {
            return 1;
        }
        return -((i3 - i4) + 10);
    }

    /* access modifiers changed from: private */
    public static int compare(char[] cArr, char[] cArr2, int i, int i2) {
        int i3 = i2 - i;
        int min = Math.min(cArr.length, i3);
        int i4 = 1;
        while (i4 < min) {
            char c = cArr2[i + i4];
            if (cArr[i4] < c) {
                return c == ';' ? 1 : -1;
            }
            if (cArr[i4] > c) {
                return cArr[i4] == ';' ? -1 : 1;
            }
            i4++;
        }
        if (cArr.length > i4) {
            return cArr[i4] == ';' ? -1 : 1;
        }
        if (i3 <= i4) {
            return 0;
        }
        if (cArr2[i + i4] == ';') {
            return 1;
        }
        return -((i3 - i4) + 10);
    }

    static int binarySearch(char[][] cArr, String str, int i, int i2) {
        int length = cArr.length - 1;
        int i3 = 0;
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MIN_VALUE;
        while (i3 <= length) {
            int i6 = (i3 + length) >>> 1;
            int compare = compare(cArr[i6], str, i, i2);
            if (compare == -1) {
                i3 = i6 + 1;
            } else if (compare == 1) {
                length = i6 - 1;
            } else if (compare >= -10) {
                return i6;
            } else {
                i3 = i6 + 1;
                if (i4 == Integer.MIN_VALUE || i5 < compare) {
                    i4 = i6;
                    i5 = compare;
                }
            }
        }
        if (i4 != Integer.MIN_VALUE) {
            return (i4 + 10) * -1;
        }
        return Integer.MIN_VALUE;
    }

    static int binarySearch(char[][] cArr, char[] cArr2, int i, int i2) {
        int length = cArr.length - 1;
        int i3 = 0;
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MIN_VALUE;
        while (i3 <= length) {
            int i6 = (i3 + length) >>> 1;
            int compare = compare(cArr[i6], cArr2, i, i2);
            if (compare == -1) {
                i3 = i6 + 1;
            } else if (compare == 1) {
                length = i6 - 1;
            } else if (compare >= -10) {
                return i6;
            } else {
                i3 = i6 + 1;
                if (i4 == Integer.MIN_VALUE || i5 < compare) {
                    i4 = i6;
                    i5 = compare;
                }
            }
        }
        if (i4 != Integer.MIN_VALUE) {
            return (i4 + 10) * -1;
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
            this.references.add(new Reference(str, new int[]{i}));
        }

        /* access modifiers changed from: package-private */
        public void addReference(int i, int i2, String str) {
            this.references.add(new Reference(str, new int[]{i, i2}));
        }
    }

    private static final class Reference {
        /* access modifiers changed from: private */
        public final int[] codepoints;
        /* access modifiers changed from: private */
        public final char[] ncr;

        private Reference(String str, int[] iArr) {
            this.ncr = str.toCharArray();
            this.codepoints = iArr;
        }
    }
}
