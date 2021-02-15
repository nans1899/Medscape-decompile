package com.dd.plist;

import com.google.common.base.Ascii;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.text.ParseException;
import java.text.StringCharacterIterator;
import java.util.LinkedList;

public class ASCIIPropertyListParser {
    public static final char ARRAY_BEGIN_TOKEN = '(';
    public static final char ARRAY_END_TOKEN = ')';
    public static final char ARRAY_ITEM_DELIMITER_TOKEN = ',';
    public static final char COMMENT_BEGIN_TOKEN = '/';
    public static final char DATA_BEGIN_TOKEN = '<';
    public static final char DATA_END_TOKEN = '>';
    public static final char DATA_GSBOOL_BEGIN_TOKEN = 'B';
    public static final char DATA_GSBOOL_FALSE_TOKEN = 'N';
    public static final char DATA_GSBOOL_TRUE_TOKEN = 'Y';
    public static final char DATA_GSDATE_BEGIN_TOKEN = 'D';
    public static final char DATA_GSINT_BEGIN_TOKEN = 'I';
    public static final char DATA_GSOBJECT_BEGIN_TOKEN = '*';
    public static final char DATA_GSREAL_BEGIN_TOKEN = 'R';
    public static final char DATE_APPLE_DATE_TIME_DELIMITER = 'T';
    public static final char DATE_APPLE_END_TOKEN = 'Z';
    public static final char DATE_DATE_FIELD_DELIMITER = '-';
    public static final char DATE_GS_DATE_TIME_DELIMITER = ' ';
    public static final char DATE_TIME_FIELD_DELIMITER = ':';
    public static final char DICTIONARY_ASSIGN_TOKEN = '=';
    public static final char DICTIONARY_BEGIN_TOKEN = '{';
    public static final char DICTIONARY_END_TOKEN = '}';
    public static final char DICTIONARY_ITEM_DELIMITER_TOKEN = ';';
    public static final char MULTILINE_COMMENT_END_TOKEN = '/';
    public static final char MULTILINE_COMMENT_SECOND_TOKEN = '*';
    public static final char QUOTEDSTRING_BEGIN_TOKEN = '\"';
    public static final char QUOTEDSTRING_END_TOKEN = '\"';
    public static final char QUOTEDSTRING_ESCAPE_TOKEN = '\\';
    public static final char SINGLELINE_COMMENT_SECOND_TOKEN = '/';
    public static final char WHITESPACE_CARRIAGE_RETURN = '\r';
    public static final char WHITESPACE_NEWLINE = '\n';
    public static final char WHITESPACE_SPACE = ' ';
    public static final char WHITESPACE_TAB = '\t';
    private static CharsetEncoder asciiEncoder;
    private byte[] data;
    private int index;

    public static NSObject parse(File file) throws Exception {
        return parse((InputStream) new FileInputStream(file));
    }

    public static NSObject parse(InputStream inputStream) throws Exception {
        byte[] readAll = PropertyListParser.readAll(inputStream, Integer.MAX_VALUE);
        inputStream.close();
        return parse(readAll);
    }

    public static NSObject parse(byte[] bArr) throws Exception {
        return new ASCIIPropertyListParser(bArr).parse();
    }

    protected ASCIIPropertyListParser() {
    }

    private ASCIIPropertyListParser(byte[] bArr) {
        this.data = bArr;
    }

    private boolean acceptSequence(char... cArr) {
        for (int i = 0; i < cArr.length; i++) {
            if (this.data[this.index + i] != cArr[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean accept(char... cArr) {
        boolean z = false;
        for (char c : cArr) {
            if (this.data[this.index] == c) {
                z = true;
            }
        }
        return z;
    }

    private boolean accept(char c) {
        return this.data[this.index] == c;
    }

    private void expect(char... cArr) throws ParseException {
        if (!accept(cArr)) {
            String str = "Expected '" + cArr[0] + "'";
            for (int i = 1; i < cArr.length; i++) {
                str = str + " or '" + cArr[i] + "'";
            }
            throw new ParseException(str + " but found '" + ((char) this.data[this.index]) + "'", this.index);
        }
    }

    private void expect(char c) throws ParseException {
        if (!accept(c)) {
            throw new ParseException("Expected '" + c + "' but found '" + ((char) this.data[this.index]) + "'", this.index);
        }
    }

    private void read(char c) throws ParseException {
        expect(c);
        this.index++;
    }

    private void skip() {
        this.index++;
    }

    private void skip(int i) {
        this.index += i;
    }

    private void skipWhitespacesAndComments() {
        boolean z;
        do {
            z = false;
            while (accept(WHITESPACE_CARRIAGE_RETURN, 10, ' ', 9)) {
                skip();
            }
            if (acceptSequence('/', '/')) {
                skip(2);
                readInputUntil(WHITESPACE_CARRIAGE_RETURN, 10);
            } else if (acceptSequence('/', '*')) {
                skip(2);
                while (!acceptSequence('*', '/')) {
                    skip();
                }
                skip(2);
            } else {
                continue;
            }
            z = true;
            continue;
        } while (z);
    }

    private String readInputUntil(char... cArr) {
        String str = "";
        while (!accept(cArr)) {
            str = str + ((char) this.data[this.index]);
            skip();
        }
        return str;
    }

    private String readInputUntil(char c) {
        String str = "";
        while (!accept(c)) {
            str = str + ((char) this.data[this.index]);
            skip();
        }
        return str;
    }

    public NSObject parse() throws ParseException {
        this.index = 0;
        skipWhitespacesAndComments();
        expect(DICTIONARY_BEGIN_TOKEN, ARRAY_BEGIN_TOKEN, '/');
        try {
            return parseObject();
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ParseException("Reached end of input unexpectedly.", this.index);
        }
    }

    private NSObject parseObject() throws ParseException {
        byte[] bArr = this.data;
        int i = this.index;
        byte b = bArr[i];
        if (b == 34) {
            String parseQuotedString = parseQuotedString();
            if (parseQuotedString.length() != 20 || parseQuotedString.charAt(4) != '-') {
                return new NSString(parseQuotedString);
            }
            try {
                return new NSDate(parseQuotedString);
            } catch (Exception unused) {
                return new NSString(parseQuotedString);
            }
        } else if (b == 40) {
            return parseArray();
        } else {
            if (b == 60) {
                return parseData();
            }
            if (b == 123) {
                return parseDictionary();
            }
            if (bArr[i] > 47 && bArr[i] < 58) {
                return parseNumerical();
            }
            String parseString = parseString();
            if (parseString.equals("YES")) {
                return new NSNumber(true);
            }
            if (parseString.equals("NO")) {
                return new NSNumber(false);
            }
            return new NSString(parseString);
        }
    }

    private NSArray parseArray() throws ParseException {
        skip();
        skipWhitespacesAndComments();
        LinkedList linkedList = new LinkedList();
        while (!accept((char) ARRAY_END_TOKEN)) {
            linkedList.add(parseObject());
            skipWhitespacesAndComments();
            if (!accept((char) ARRAY_ITEM_DELIMITER_TOKEN)) {
                break;
            }
            skip();
            skipWhitespacesAndComments();
        }
        read(ARRAY_END_TOKEN);
        return new NSArray((NSObject[]) linkedList.toArray(new NSObject[linkedList.size()]));
    }

    private NSDictionary parseDictionary() throws ParseException {
        String str;
        skip();
        skipWhitespacesAndComments();
        NSDictionary nSDictionary = new NSDictionary();
        while (!accept((char) DICTIONARY_END_TOKEN)) {
            if (accept('\"')) {
                str = parseQuotedString();
            } else {
                str = parseString();
            }
            skipWhitespacesAndComments();
            read('=');
            skipWhitespacesAndComments();
            nSDictionary.put(str, parseObject());
            skipWhitespacesAndComments();
            read(';');
            skipWhitespacesAndComments();
        }
        skip();
        return nSDictionary;
    }

    private NSObject parseData() throws ParseException {
        NSObject nSObject;
        NSObject nSNumber;
        skip();
        if (accept('*')) {
            skip();
            expect(DATA_GSBOOL_BEGIN_TOKEN, DATA_GSDATE_BEGIN_TOKEN, DATA_GSINT_BEGIN_TOKEN, DATA_GSREAL_BEGIN_TOKEN);
            if (accept((char) DATA_GSBOOL_BEGIN_TOKEN)) {
                skip();
                expect(DATA_GSBOOL_TRUE_TOKEN, DATA_GSBOOL_FALSE_TOKEN);
                if (accept((char) DATA_GSBOOL_TRUE_TOKEN)) {
                    nSObject = new NSNumber(true);
                } else {
                    nSObject = new NSNumber(false);
                }
                skip();
            } else {
                if (accept((char) DATA_GSDATE_BEGIN_TOKEN)) {
                    skip();
                    nSNumber = new NSDate(readInputUntil('>'));
                } else if (accept(DATA_GSINT_BEGIN_TOKEN, DATA_GSREAL_BEGIN_TOKEN)) {
                    skip();
                    nSNumber = new NSNumber(readInputUntil('>'));
                } else {
                    nSObject = null;
                }
                nSObject = nSNumber;
            }
            read('>');
            return nSObject;
        }
        String replaceAll = readInputUntil('>').replaceAll("\\s+", "");
        int length = replaceAll.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(replaceAll.substring(i2, i2 + 2), 16);
        }
        NSData nSData = new NSData(bArr);
        skip();
        return nSData;
    }

    private NSObject parseNumerical() {
        String parseString = parseString();
        try {
            if (parseString.length() <= 4 || parseString.charAt(4) != '-') {
                return new NSNumber(parseString);
            }
            return new NSDate(parseString);
        } catch (Exception unused) {
            return new NSString(parseString);
        }
    }

    private String parseString() {
        return readInputUntil(' ', 9, 10, WHITESPACE_CARRIAGE_RETURN, ARRAY_ITEM_DELIMITER_TOKEN, ';', '=', ARRAY_END_TOKEN);
    }

    private String parseQuotedString() throws ParseException {
        skip();
        String str = "";
        boolean z = true;
        while (true) {
            byte[] bArr = this.data;
            int i = this.index;
            if (bArr[i] != 34 || (bArr[i - 1] == 92 && z)) {
                str = str + ((char) this.data[this.index]);
                if (accept('\\')) {
                    z = this.data[this.index - 1] != 92 || !z;
                }
                skip();
            } else {
                try {
                    String parseQuotedString = parseQuotedString(str);
                    skip();
                    return parseQuotedString;
                } catch (Exception unused) {
                    throw new ParseException("The quoted string could not be parsed.", this.index);
                }
            }
        }
    }

    public static synchronized String parseQuotedString(String str) throws Exception {
        int i;
        synchronized (ASCIIPropertyListParser.class) {
            LinkedList<Byte> linkedList = new LinkedList<>();
            StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(str);
            char current = stringCharacterIterator.current();
            while (true) {
                i = 0;
                if (stringCharacterIterator.getIndex() >= stringCharacterIterator.getEndIndex()) {
                    break;
                }
                if (current != '\\') {
                    linkedList.add((byte) 0);
                    linkedList.add(Byte.valueOf((byte) current));
                } else {
                    byte[] bytes = parseEscapedSequence(stringCharacterIterator).getBytes("UTF-8");
                    int length = bytes.length;
                    while (i < length) {
                        linkedList.add(Byte.valueOf(bytes[i]));
                        i++;
                    }
                }
                current = stringCharacterIterator.next();
            }
            byte[] bArr = new byte[linkedList.size()];
            for (Byte byteValue : linkedList) {
                bArr[i] = byteValue.byteValue();
                i++;
            }
            String str2 = new String(bArr, "UTF-8");
            CharBuffer wrap = CharBuffer.wrap(str2);
            if (asciiEncoder == null) {
                asciiEncoder = Charset.forName("ASCII").newEncoder();
            }
            if (!asciiEncoder.canEncode(wrap)) {
                return str2;
            }
            String charBuffer = asciiEncoder.encode(wrap).asCharBuffer().toString();
            return charBuffer;
        }
    }

    private static String parseEscapedSequence(StringCharacterIterator stringCharacterIterator) throws UnsupportedEncodingException {
        char next = stringCharacterIterator.next();
        if (next == '\\') {
            return new String("\u0000\\".getBytes(), "UTF-8");
        }
        if (next == '\"') {
            return new String("\u0000\"".getBytes(), "UTF-8");
        }
        if (next == 'b') {
            return new String(new byte[]{0, 8}, "UTF-8");
        }
        if (next == 'n') {
            return new String(new byte[]{0, 10}, "UTF-8");
        }
        if (next == 'r') {
            return new String(new byte[]{0, Ascii.CR}, "UTF-8");
        }
        if (next == 't') {
            return new String(new byte[]{0, 9}, "UTF-8");
        }
        if (next == 'U' || next == 'u') {
            return new String(new byte[]{(byte) Integer.parseInt(("" + stringCharacterIterator.next()) + stringCharacterIterator.next(), 16), (byte) Integer.parseInt(("" + stringCharacterIterator.next()) + stringCharacterIterator.next(), 16)}, "UTF-8");
        }
        return new String(new byte[]{0, (byte) Integer.parseInt((("" + next) + stringCharacterIterator.next()) + stringCharacterIterator.next(), 8)}, "UTF-8");
    }
}
