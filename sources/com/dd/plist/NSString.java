package com.dd.plist;

import com.facebook.appevents.AppEventsConstants;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class NSString extends NSObject implements Comparable<Object> {
    private static CharsetEncoder asciiEncoder;
    private static CharsetEncoder utf16beEncoder;
    private static CharsetEncoder utf8Encoder;
    private String content;

    public NSString(byte[] bArr, String str) throws UnsupportedEncodingException {
        this.content = new String(bArr, str);
    }

    public NSString(String str) {
        this.content = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void append(NSString nSString) {
        append(nSString.getContent());
    }

    public void append(String str) {
        this.content += str;
    }

    public void prepend(String str) {
        this.content = str + this.content;
    }

    public void prepend(NSString nSString) {
        prepend(nSString.getContent());
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NSString)) {
            return false;
        }
        return this.content.equals(((NSString) obj).content);
    }

    public int hashCode() {
        return this.content.hashCode();
    }

    public String toString() {
        return this.content;
    }

    /* access modifiers changed from: package-private */
    public void toXML(StringBuilder sb, int i) {
        String str;
        indent(sb, i);
        sb.append("<string>");
        synchronized (NSString.class) {
            if (utf8Encoder == null) {
                utf8Encoder = Charset.forName("UTF-8").newEncoder();
            } else {
                utf8Encoder.reset();
            }
            try {
                ByteBuffer encode = utf8Encoder.encode(CharBuffer.wrap(this.content));
                byte[] bArr = new byte[encode.remaining()];
                encode.get(bArr);
                str = new String(bArr, "UTF-8");
                this.content = str;
            } catch (Exception e) {
                throw new RuntimeException("Could not encode the NSString into UTF-8: " + String.valueOf(e.getMessage()));
            }
        }
        if (str.contains("&") || this.content.contains(HtmlObject.HtmlMarkUp.OPEN_BRACKER) || this.content.contains(HtmlObject.HtmlMarkUp.CLOSE_BRACKER)) {
            sb.append("<![CDATA[");
            sb.append(this.content.replaceAll("]]>", "]]]]><![CDATA[>"));
            sb.append("]]>");
        } else {
            sb.append(this.content);
        }
        sb.append("</string>");
    }

    public void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException {
        int i;
        ByteBuffer byteBuffer;
        CharBuffer wrap = CharBuffer.wrap(this.content);
        synchronized (NSString.class) {
            if (asciiEncoder == null) {
                asciiEncoder = Charset.forName("ASCII").newEncoder();
            } else {
                asciiEncoder.reset();
            }
            if (asciiEncoder.canEncode(wrap)) {
                i = 5;
                byteBuffer = asciiEncoder.encode(wrap);
            } else {
                if (utf16beEncoder == null) {
                    utf16beEncoder = Charset.forName("UTF-16BE").newEncoder();
                } else {
                    utf16beEncoder.reset();
                }
                i = 6;
                byteBuffer = utf16beEncoder.encode(wrap);
            }
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        binaryPropertyListWriter.writeIntHeader(i, this.content.length());
        binaryPropertyListWriter.write(bArr);
    }

    /* access modifiers changed from: protected */
    public void toASCII(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("\"");
        sb.append(escapeStringForASCII(this.content));
        sb.append("\"");
    }

    /* access modifiers changed from: protected */
    public void toASCIIGnuStep(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("\"");
        sb.append(escapeStringForASCII(this.content));
        sb.append("\"");
    }

    static String escapeStringForASCII(String str) {
        char[] charArray = str.toCharArray();
        String str2 = "";
        for (char c : charArray) {
            if (c > 127) {
                String str3 = str2 + "\\U";
                String hexString = Integer.toHexString(c);
                while (hexString.length() < 4) {
                    hexString = AppEventsConstants.EVENT_PARAM_VALUE_NO + hexString;
                }
                str2 = str3 + hexString;
            } else if (c == '\\') {
                str2 = str2 + "\\\\";
            } else if (c == '\"') {
                str2 = str2 + "\\\"";
            } else if (c == 8) {
                str2 = str2 + "\\b";
            } else if (c == 10) {
                str2 = str2 + "\\n";
            } else if (c == 13) {
                str2 = str2 + "\\r";
            } else if (c == 9) {
                str2 = str2 + "\\t";
            } else {
                str2 = str2 + c;
            }
        }
        return str2;
    }

    public int compareTo(Object obj) {
        if (obj instanceof NSString) {
            return getContent().compareTo(((NSString) obj).getContent());
        }
        if (obj instanceof String) {
            return getContent().compareTo((String) obj);
        }
        return -1;
    }
}
