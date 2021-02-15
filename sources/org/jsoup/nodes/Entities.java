package org.jsoup.nodes;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import kotlin.text.Typography;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Parser;

public class Entities {
    private static final Document.OutputSettings DefaultOutput = new Document.OutputSettings();
    private static final char[] codeDelims = {ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN, ';'};
    static final int codepointRadix = 36;
    private static final int empty = -1;
    private static final String emptyName = "";
    private static final HashMap<String, String> multipoints = new HashMap<>();

    public enum EscapeMode {
        xhtml(EntitiesData.xmlPoints, 4),
        base(EntitiesData.basePoints, 106),
        extended(EntitiesData.fullPoints, 2125);
        
        /* access modifiers changed from: private */
        public int[] codeKeys;
        /* access modifiers changed from: private */
        public int[] codeVals;
        /* access modifiers changed from: private */
        public String[] nameKeys;
        /* access modifiers changed from: private */
        public String[] nameVals;

        private EscapeMode(String str, int i) {
            Entities.load(this, str, i);
        }

        /* access modifiers changed from: package-private */
        public int codepointForName(String str) {
            int binarySearch = Arrays.binarySearch(this.nameKeys, str);
            if (binarySearch >= 0) {
                return this.codeVals[binarySearch];
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public String nameForCodepoint(int i) {
            int binarySearch = Arrays.binarySearch(this.codeKeys, i);
            if (binarySearch < 0) {
                return "";
            }
            String[] strArr = this.nameVals;
            if (binarySearch < strArr.length - 1) {
                int i2 = binarySearch + 1;
                if (this.codeKeys[i2] == i) {
                    return strArr[i2];
                }
            }
            return this.nameVals[binarySearch];
        }

        private int size() {
            return this.nameKeys.length;
        }
    }

    private Entities() {
    }

    public static boolean isNamedEntity(String str) {
        return EscapeMode.extended.codepointForName(str) != -1;
    }

    public static boolean isBaseNamedEntity(String str) {
        return EscapeMode.base.codepointForName(str) != -1;
    }

    public static String getByName(String str) {
        String str2 = multipoints.get(str);
        if (str2 != null) {
            return str2;
        }
        int codepointForName = EscapeMode.extended.codepointForName(str);
        if (codepointForName == -1) {
            return "";
        }
        return new String(new int[]{codepointForName}, 0, 1);
    }

    public static int codepointsForName(String str, int[] iArr) {
        String str2 = multipoints.get(str);
        if (str2 != null) {
            iArr[0] = str2.codePointAt(0);
            iArr[1] = str2.codePointAt(1);
            return 2;
        }
        int codepointForName = EscapeMode.extended.codepointForName(str);
        if (codepointForName == -1) {
            return 0;
        }
        iArr[0] = codepointForName;
        return 1;
    }

    public static String escape(String str, Document.OutputSettings outputSettings) {
        if (str == null) {
            return "";
        }
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        try {
            escape(borrowBuilder, str, outputSettings, false, false, false);
            return StringUtil.releaseBuilder(borrowBuilder);
        } catch (IOException e) {
            throw new SerializationException((Throwable) e);
        }
    }

    public static String escape(String str) {
        return escape(str, DefaultOutput);
    }

    static void escape(Appendable appendable, String str, Document.OutputSettings outputSettings, boolean z, boolean z2, boolean z3) throws IOException {
        EscapeMode escapeMode = outputSettings.escapeMode();
        CharsetEncoder encoder = outputSettings.encoder();
        CoreCharset coreCharset = outputSettings.coreCharset;
        int length = str.length();
        int i = 0;
        boolean z4 = false;
        boolean z5 = false;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (z2) {
                if (StringUtil.isWhitespace(codePointAt)) {
                    if ((!z3 || z4) && !z5) {
                        appendable.append(' ');
                        z5 = true;
                    }
                    i += Character.charCount(codePointAt);
                } else {
                    z4 = true;
                    z5 = false;
                }
            }
            if (codePointAt < 65536) {
                char c = (char) codePointAt;
                if (c != '\"') {
                    if (c == '&') {
                        appendable.append("&amp;");
                    } else if (c != '<') {
                        if (c != '>') {
                            if (c != 160) {
                                if (canEncode(coreCharset, c, encoder)) {
                                    appendable.append(c);
                                } else {
                                    appendEncoded(appendable, escapeMode, codePointAt);
                                }
                            } else if (escapeMode != EscapeMode.xhtml) {
                                appendable.append("&nbsp;");
                            } else {
                                appendable.append("&#xa0;");
                            }
                        } else if (!z) {
                            appendable.append("&gt;");
                        } else {
                            appendable.append(c);
                        }
                    } else if (!z || escapeMode == EscapeMode.xhtml) {
                        appendable.append("&lt;");
                    } else {
                        appendable.append(c);
                    }
                } else if (z) {
                    appendable.append("&quot;");
                } else {
                    appendable.append(c);
                }
            } else {
                String str2 = new String(Character.toChars(codePointAt));
                if (encoder.canEncode(str2)) {
                    appendable.append(str2);
                } else {
                    appendEncoded(appendable, escapeMode, codePointAt);
                }
            }
            i += Character.charCount(codePointAt);
        }
    }

    private static void appendEncoded(Appendable appendable, EscapeMode escapeMode, int i) throws IOException {
        String nameForCodepoint = escapeMode.nameForCodepoint(i);
        if (!"".equals(nameForCodepoint)) {
            appendable.append(Typography.amp).append(nameForCodepoint).append(';');
        } else {
            appendable.append("&#x").append(Integer.toHexString(i)).append(';');
        }
    }

    public static String unescape(String str) {
        return unescape(str, false);
    }

    static String unescape(String str, boolean z) {
        return Parser.unescapeEntities(str, z);
    }

    /* renamed from: org.jsoup.nodes.Entities$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$nodes$Entities$CoreCharset;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.jsoup.nodes.Entities$CoreCharset[] r0 = org.jsoup.nodes.Entities.CoreCharset.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jsoup$nodes$Entities$CoreCharset = r0
                org.jsoup.nodes.Entities$CoreCharset r1 = org.jsoup.nodes.Entities.CoreCharset.ascii     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jsoup$nodes$Entities$CoreCharset     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jsoup.nodes.Entities$CoreCharset r1 = org.jsoup.nodes.Entities.CoreCharset.utf     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.nodes.Entities.AnonymousClass1.<clinit>():void");
        }
    }

    private static boolean canEncode(CoreCharset coreCharset, char c, CharsetEncoder charsetEncoder) {
        int i = AnonymousClass1.$SwitchMap$org$jsoup$nodes$Entities$CoreCharset[coreCharset.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return charsetEncoder.canEncode(c);
            }
            return true;
        } else if (c < 128) {
            return true;
        } else {
            return false;
        }
    }

    enum CoreCharset {
        ascii,
        utf,
        fallback;

        static CoreCharset byName(String str) {
            if (str.equals("US-ASCII")) {
                return ascii;
            }
            if (str.startsWith("UTF-")) {
                return utf;
            }
            return fallback;
        }
    }

    /* access modifiers changed from: private */
    public static void load(EscapeMode escapeMode, String str, int i) {
        int i2;
        String[] unused = escapeMode.nameKeys = new String[i];
        int[] unused2 = escapeMode.codeVals = new int[i];
        int[] unused3 = escapeMode.codeKeys = new int[i];
        String[] unused4 = escapeMode.nameVals = new String[i];
        CharacterReader characterReader = new CharacterReader(str);
        boolean z = false;
        int i3 = 0;
        while (!characterReader.isEmpty()) {
            String consumeTo = characterReader.consumeTo('=');
            characterReader.advance();
            int parseInt = Integer.parseInt(characterReader.consumeToAny(codeDelims), 36);
            char current = characterReader.current();
            characterReader.advance();
            if (current == ',') {
                i2 = Integer.parseInt(characterReader.consumeTo(';'), 36);
                characterReader.advance();
            } else {
                i2 = -1;
            }
            int parseInt2 = Integer.parseInt(characterReader.consumeTo((char) Typography.amp), 36);
            characterReader.advance();
            escapeMode.nameKeys[i3] = consumeTo;
            escapeMode.codeVals[i3] = parseInt;
            escapeMode.codeKeys[parseInt2] = parseInt;
            escapeMode.nameVals[parseInt2] = consumeTo;
            if (i2 != -1) {
                multipoints.put(consumeTo, new String(new int[]{parseInt, i2}, 0, 2));
            }
            i3++;
        }
        if (i3 == i) {
            z = true;
        }
        Validate.isTrue(z, "Unexpected count of entities loaded");
    }
}
