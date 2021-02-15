package org.jsoup.helper;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.UncheckedIOException;
import org.jsoup.internal.ConstrainableInputStream;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.parser.Parser;

public final class DataUtil {
    static final int boundaryLength = 32;
    static final int bufferSize = 32768;
    private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*(?:[\"'])?([^\\s,;\"']*)");
    static final String defaultCharset = "UTF-8";
    private static final int firstReadBufferSize = 5120;
    private static final char[] mimeBoundaryChars = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private DataUtil() {
    }

    public static Document load(File file, String str, String str2) throws IOException {
        return parseInputStream(new FileInputStream(file), str, str2, Parser.htmlParser());
    }

    public static Document load(InputStream inputStream, String str, String str2) throws IOException {
        return parseInputStream(inputStream, str, str2, Parser.htmlParser());
    }

    public static Document load(InputStream inputStream, String str, String str2, Parser parser) throws IOException {
        return parseInputStream(inputStream, str, str2, parser);
    }

    static void crossStreams(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[32768];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    static Document parseInputStream(InputStream inputStream, String str, String str2, Parser parser) throws IOException {
        Document document;
        XmlDeclaration xmlDeclaration;
        InputStream inputStream2 = inputStream;
        String str3 = str2;
        Parser parser2 = parser;
        if (inputStream2 == null) {
            return new Document(str3);
        }
        boolean z = false;
        ConstrainableInputStream wrap = ConstrainableInputStream.wrap(inputStream2, 32768, 0);
        wrap.mark(32768);
        ByteBuffer readToByteBuffer = readToByteBuffer(wrap, 5119);
        boolean z2 = wrap.read() == -1;
        wrap.reset();
        BomCharset detectCharsetFromBom = detectCharsetFromBom(readToByteBuffer);
        String access$000 = detectCharsetFromBom != null ? detectCharsetFromBom.charset : str;
        Document document2 = null;
        if (access$000 == null) {
            try {
                CharBuffer decode = Charset.forName("UTF-8").decode(readToByteBuffer);
                if (decode.hasArray()) {
                    document = parser2.parseInput((Reader) new CharArrayReader(decode.array()), str3);
                } else {
                    document = parser2.parseInput(decode.toString(), str3);
                }
                Iterator it = document.select("meta[http-equiv=content-type], meta[charset]").iterator();
                String str4 = null;
                while (it.hasNext()) {
                    Element element = (Element) it.next();
                    if (element.hasAttr("http-equiv")) {
                        str4 = getCharsetFromContentType(element.attr("content"));
                    }
                    if (str4 == null && element.hasAttr("charset")) {
                        str4 = element.attr("charset");
                        continue;
                    }
                    if (str4 != null) {
                        break;
                    }
                }
                if (str4 == null && document.childNodeSize() > 0) {
                    Node childNode = document.childNode(0);
                    if (childNode instanceof XmlDeclaration) {
                        xmlDeclaration = (XmlDeclaration) childNode;
                    } else {
                        if (childNode instanceof Comment) {
                            Comment comment = (Comment) childNode;
                            if (comment.isXmlDeclaration()) {
                                xmlDeclaration = comment.asXmlDeclaration();
                            }
                        }
                        xmlDeclaration = null;
                    }
                    if (xmlDeclaration != null && xmlDeclaration.name().equalsIgnoreCase("xml")) {
                        str4 = xmlDeclaration.attr("encoding");
                    }
                }
                String validateCharset = validateCharset(str4);
                if (validateCharset != null && !validateCharset.equalsIgnoreCase("UTF-8")) {
                    access$000 = validateCharset.trim().replaceAll("[\"']", "");
                } else if (z2) {
                    document2 = document;
                }
            } catch (UncheckedIOException e) {
                throw e.ioException();
            }
        } else {
            Validate.notEmpty(access$000, "Must set charset arg to character set of file to parse. Set to null to attempt to detect from HTML");
        }
        if (document2 == null) {
            if (access$000 == null) {
                access$000 = "UTF-8";
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(wrap, access$000), 32768);
            if (detectCharsetFromBom != null && detectCharsetFromBom.offset) {
                if (bufferedReader.skip(1) == 1) {
                    z = true;
                }
                Validate.isTrue(z);
            }
            try {
                document2 = parser2.parseInput((Reader) bufferedReader, str3);
                Charset forName = Charset.forName(access$000);
                document2.outputSettings().charset(forName);
                if (!forName.canEncode()) {
                    document2.charset(Charset.forName("UTF-8"));
                }
            } catch (UncheckedIOException e2) {
                throw e2.ioException();
            }
        }
        wrap.close();
        return document2;
    }

    public static ByteBuffer readToByteBuffer(InputStream inputStream, int i) throws IOException {
        Validate.isTrue(i >= 0, "maxSize must be 0 (unlimited) or larger");
        return ConstrainableInputStream.wrap(inputStream, 32768, i).readToByteBuffer(i);
    }

    static ByteBuffer emptyByteBuffer() {
        return ByteBuffer.allocate(0);
    }

    static String getCharsetFromContentType(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = charsetPattern.matcher(str);
        if (matcher.find()) {
            return validateCharset(matcher.group(1).trim().replace("charset=", ""));
        }
        return null;
    }

    private static String validateCharset(String str) {
        if (!(str == null || str.length() == 0)) {
            String replaceAll = str.trim().replaceAll("[\"']", "");
            try {
                if (Charset.isSupported(replaceAll)) {
                    return replaceAll;
                }
                String upperCase = replaceAll.toUpperCase(Locale.ENGLISH);
                if (Charset.isSupported(upperCase)) {
                    return upperCase;
                }
            } catch (IllegalCharsetNameException unused) {
            }
        }
        return null;
    }

    static String mimeBoundary() {
        StringBuilder borrowBuilder = StringUtil.borrowBuilder();
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            char[] cArr = mimeBoundaryChars;
            borrowBuilder.append(cArr[random.nextInt(cArr.length)]);
        }
        return StringUtil.releaseBuilder(borrowBuilder);
    }

    private static BomCharset detectCharsetFromBom(ByteBuffer byteBuffer) {
        byteBuffer.mark();
        byte[] bArr = new byte[4];
        if (byteBuffer.remaining() >= 4) {
            byteBuffer.get(bArr);
            byteBuffer.rewind();
        }
        if ((bArr[0] == 0 && bArr[1] == 0 && bArr[2] == -2 && bArr[3] == -1) || (bArr[0] == -1 && bArr[1] == -2 && bArr[2] == 0 && bArr[3] == 0)) {
            return new BomCharset("UTF-32", false);
        }
        if ((bArr[0] == -2 && bArr[1] == -1) || (bArr[0] == -1 && bArr[1] == -2)) {
            return new BomCharset("UTF-16", false);
        }
        if (bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) {
            return new BomCharset("UTF-8", true);
        }
        return null;
    }

    private static class BomCharset {
        /* access modifiers changed from: private */
        public final String charset;
        /* access modifiers changed from: private */
        public final boolean offset;

        public BomCharset(String str, boolean z) {
            this.charset = str;
            this.offset = z;
        }
    }
}
