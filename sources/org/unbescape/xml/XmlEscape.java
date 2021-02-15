package org.unbescape.xml;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public final class XmlEscape {
    public static String escapeXml10Minimal(String str) {
        return escapeXml(str, XmlEscapeSymbols.XML10_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static String escapeXml11Minimal(String str) {
        return escapeXml(str, XmlEscapeSymbols.XML11_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static String escapeXml10AttributeMinimal(String str) {
        return escapeXml(str, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static String escapeXml11AttributeMinimal(String str) {
        return escapeXml(str, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static String escapeXml10(String str) {
        return escapeXml(str, XmlEscapeSymbols.XML10_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static String escapeXml11(String str) {
        return escapeXml(str, XmlEscapeSymbols.XML11_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static String escapeXml10Attribute(String str) {
        return escapeXml(str, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static String escapeXml11Attribute(String str) {
        return escapeXml(str, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static String escapeXml10(String str, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) {
        return escapeXml(str, XmlEscapeSymbols.XML10_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static String escapeXml11(String str, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) {
        return escapeXml(str, XmlEscapeSymbols.XML11_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static String escapeXml10Attribute(String str, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) {
        return escapeXml(str, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static String escapeXml11Attribute(String str, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) {
        return escapeXml(str, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    private static String escapeXml(String str, XmlEscapeSymbols xmlEscapeSymbols, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) {
        if (xmlEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (xmlEscapeLevel != null) {
            return XmlEscapeUtil.escape(str, xmlEscapeSymbols, xmlEscapeType, xmlEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeXml10Minimal(String str, Writer writer) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML10_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11Minimal(String str, Writer writer) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML11_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10AttributeMinimal(String str, Writer writer) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11AttributeMinimal(String str, Writer writer) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10(String str, Writer writer) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML10_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11(String str, Writer writer) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML11_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10Attribute(String str, Writer writer) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11Attribute(String str, Writer writer) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10(String str, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML10_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml11(String str, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML11_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml10Attribute(String str, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml11Attribute(String str, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(str, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    private static void escapeXml(String str, Writer writer, XmlEscapeSymbols xmlEscapeSymbols, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (xmlEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (xmlEscapeLevel != null) {
            XmlEscapeUtil.escape(new InternalStringReader(str), writer, xmlEscapeSymbols, xmlEscapeType, xmlEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeXml10Minimal(Reader reader, Writer writer) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML10_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11Minimal(Reader reader, Writer writer) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML11_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10AttributeMinimal(Reader reader, Writer writer) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11AttributeMinimal(Reader reader, Writer writer) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10(Reader reader, Writer writer) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML10_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11(Reader reader, Writer writer) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML11_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10Attribute(Reader reader, Writer writer) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11Attribute(Reader reader, Writer writer) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10(Reader reader, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML10_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml11(Reader reader, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML11_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml10Attribute(Reader reader, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml11Attribute(Reader reader, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(reader, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    private static void escapeXml(Reader reader, Writer writer, XmlEscapeSymbols xmlEscapeSymbols, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (xmlEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (xmlEscapeLevel != null) {
            XmlEscapeUtil.escape(reader, writer, xmlEscapeSymbols, xmlEscapeType, xmlEscapeLevel);
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static void escapeXml10Minimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML10_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11Minimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML11_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10AttributeMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11AttributeMinimal(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_1_ONLY_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML10_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML11_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10Attribute(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml11Attribute(char[] cArr, int i, int i2, Writer writer) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, XmlEscapeType.CHARACTER_ENTITY_REFERENCES_DEFAULT_TO_HEXA, XmlEscapeLevel.LEVEL_2_ALL_NON_ASCII_PLUS_MARKUP_SIGNIFICANT);
    }

    public static void escapeXml10(char[] cArr, int i, int i2, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML10_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml11(char[] cArr, int i, int i2, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML11_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml10Attribute(char[] cArr, int i, int i2, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML10_ATTRIBUTE_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    public static void escapeXml11Attribute(char[] cArr, int i, int i2, Writer writer, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        escapeXml(cArr, i, i2, writer, XmlEscapeSymbols.XML11_ATTRIBUTE_SYMBOLS, xmlEscapeType, xmlEscapeLevel);
    }

    private static void escapeXml(char[] cArr, int i, int i2, Writer writer, XmlEscapeSymbols xmlEscapeSymbols, XmlEscapeType xmlEscapeType, XmlEscapeLevel xmlEscapeLevel) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (xmlEscapeType == null) {
            throw new IllegalArgumentException("The 'type' argument cannot be null");
        } else if (xmlEscapeLevel != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                XmlEscapeUtil.escape(cArr, i, i2, writer, xmlEscapeSymbols, xmlEscapeType, xmlEscapeLevel);
            }
        } else {
            throw new IllegalArgumentException("The 'level' argument cannot be null");
        }
    }

    public static String unescapeXml(String str) {
        if (str == null) {
            return null;
        }
        if (str.indexOf(38) < 0) {
            return str;
        }
        return XmlEscapeUtil.unescape(str, XmlEscapeSymbols.XML11_SYMBOLS);
    }

    public static void unescapeXml(String str, Writer writer) throws IOException {
        if (writer == null) {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        } else if (str != null) {
            if (str.indexOf(38) < 0) {
                writer.write(str);
            } else {
                XmlEscapeUtil.unescape(new InternalStringReader(str), writer, XmlEscapeSymbols.XML11_SYMBOLS);
            }
        }
    }

    public static void unescapeXml(Reader reader, Writer writer) throws IOException {
        if (writer != null) {
            XmlEscapeUtil.unescape(reader, writer, XmlEscapeSymbols.XML11_SYMBOLS);
            return;
        }
        throw new IllegalArgumentException("Argument 'writer' cannot be null");
    }

    public static void unescapeXml(char[] cArr, int i, int i2, Writer writer) throws IOException {
        if (writer != null) {
            int length = cArr == null ? 0 : cArr.length;
            if (i < 0 || i > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else if (i2 < 0 || i + i2 > length) {
                throw new IllegalArgumentException("Invalid (offset, len). offset=" + i + ", len=" + i2 + ", text.length=" + length);
            } else {
                XmlEscapeUtil.unescape(cArr, i, i2, writer, XmlEscapeSymbols.XML11_SYMBOLS);
            }
        } else {
            throw new IllegalArgumentException("Argument 'writer' cannot be null");
        }
    }

    private XmlEscape() {
    }

    private static final class InternalStringReader extends Reader {
        private int length;
        private int next = 0;
        private String str;

        public InternalStringReader(String str2) {
            this.str = str2;
            this.length = str2.length();
        }

        public int read() throws IOException {
            int i = this.next;
            if (i >= this.length) {
                return -1;
            }
            String str2 = this.str;
            this.next = i + 1;
            return str2.charAt(i);
        }

        public int read(char[] cArr, int i, int i2) throws IOException {
            int i3;
            if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
                throw new IndexOutOfBoundsException();
            } else if (i2 == 0) {
                return 0;
            } else {
                int i4 = this.next;
                int i5 = this.length;
                if (i4 >= i5) {
                    return -1;
                }
                int min = Math.min(i5 - i4, i2);
                String str2 = this.str;
                int i6 = this.next;
                str2.getChars(i6, i6 + min, cArr, i);
                this.next += min;
                return min;
            }
        }

        public void close() throws IOException {
            this.str = null;
        }
    }
}
