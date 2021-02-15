package org.mozilla.javascript.json;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.ArrayList;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

public class JsonParser {
    private Context cx;
    private int length;
    private int pos;
    private Scriptable scope;
    private String src;

    public JsonParser(Context context, Scriptable scriptable) {
        this.cx = context;
        this.scope = scriptable;
    }

    public synchronized Object parseValue(String str) throws ParseException {
        Object readValue;
        if (str != null) {
            try {
                this.pos = 0;
                this.length = str.length();
                this.src = str;
                readValue = readValue();
                consumeWhitespace();
                if (this.pos < this.length) {
                    throw new ParseException("Expected end of stream at char " + this.pos);
                }
            } catch (Throwable th) {
                throw th;
            }
        } else {
            throw new ParseException("Input string may not be null");
        }
        return readValue;
    }

    private Object readValue() throws ParseException {
        consumeWhitespace();
        int i = this.pos;
        if (i < this.length) {
            String str = this.src;
            this.pos = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                return readString();
            }
            if (charAt != '-') {
                if (charAt == '[') {
                    return readArray();
                }
                if (charAt == 'f') {
                    return readFalse();
                }
                if (charAt == 'n') {
                    return readNull();
                }
                if (charAt == 't') {
                    return readTrue();
                }
                if (charAt == '{') {
                    return readObject();
                }
                switch (charAt) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        break;
                    default:
                        throw new ParseException("Unexpected token: " + charAt);
                }
            }
            return readNumber(charAt);
        }
        throw new ParseException("Empty JSON string");
    }

    private Object readObject() throws ParseException {
        Scriptable newObject = this.cx.newObject(this.scope);
        consumeWhitespace();
        boolean z = false;
        while (true) {
            int i = this.pos;
            if (i < this.length) {
                String str = this.src;
                this.pos = i + 1;
                char charAt = str.charAt(i);
                if (charAt != '\"') {
                    if (charAt != ',') {
                        if (charAt == '}') {
                            return newObject;
                        }
                        throw new ParseException("Unexpected token in object literal");
                    } else if (z) {
                        z = false;
                    } else {
                        throw new ParseException("Unexpected comma in object literal");
                    }
                } else if (!z) {
                    String readString = readString();
                    consume(ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER);
                    Object readValue = readValue();
                    long indexFromString = ScriptRuntime.indexFromString(readString);
                    if (indexFromString < 0) {
                        newObject.put(readString, newObject, readValue);
                    } else {
                        newObject.put((int) indexFromString, newObject, readValue);
                    }
                    z = true;
                } else {
                    throw new ParseException("Missing comma in object literal");
                }
                consumeWhitespace();
            } else {
                throw new ParseException("Unterminated object literal");
            }
        }
    }

    private Object readArray() throws ParseException {
        ArrayList arrayList = new ArrayList();
        consumeWhitespace();
        boolean z = false;
        while (true) {
            int i = this.pos;
            if (i < this.length) {
                char charAt = this.src.charAt(i);
                if (charAt != ',') {
                    if (charAt == ']') {
                        this.pos++;
                        return this.cx.newArray(this.scope, arrayList.toArray());
                    } else if (!z) {
                        arrayList.add(readValue());
                        z = true;
                    } else {
                        throw new ParseException("Missing comma in array literal");
                    }
                } else if (z) {
                    this.pos++;
                    z = false;
                } else {
                    throw new ParseException("Unexpected comma in array literal");
                }
                consumeWhitespace();
            } else {
                throw new ParseException("Unterminated array literal");
            }
        }
    }

    private String readString() throws ParseException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = this.pos;
            if (i < this.length) {
                String str = this.src;
                this.pos = i + 1;
                char charAt = str.charAt(i);
                if (charAt <= 31) {
                    throw new ParseException("String contains control character");
                } else if (charAt == '\"') {
                    return sb.toString();
                } else {
                    if (charAt != '\\') {
                        sb.append(charAt);
                    } else {
                        int i2 = this.pos;
                        if (i2 < this.length) {
                            String str2 = this.src;
                            this.pos = i2 + 1;
                            char charAt2 = str2.charAt(i2);
                            if (charAt2 == '\"') {
                                sb.append('\"');
                            } else if (charAt2 == '/') {
                                sb.append('/');
                            } else if (charAt2 == '\\') {
                                sb.append('\\');
                            } else if (charAt2 == 'b') {
                                sb.append(8);
                            } else if (charAt2 == 'f') {
                                sb.append(12);
                            } else if (charAt2 == 'n') {
                                sb.append(10);
                            } else if (charAt2 == 'r') {
                                sb.append(ASCIIPropertyListParser.WHITESPACE_CARRIAGE_RETURN);
                            } else if (charAt2 == 't') {
                                sb.append(9);
                            } else if (charAt2 == 'u') {
                                int i3 = this.length;
                                int i4 = this.pos;
                                if (i3 - i4 >= 5) {
                                    try {
                                        sb.append((char) Integer.parseInt(this.src.substring(i4, i4 + 4), 16));
                                        this.pos += 4;
                                    } catch (NumberFormatException unused) {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append("Invalid character code: ");
                                        String str3 = this.src;
                                        int i5 = this.pos;
                                        sb2.append(str3.substring(i5, i5 + 4));
                                        throw new ParseException(sb2.toString());
                                    }
                                } else {
                                    throw new ParseException("Invalid character code: \\u" + this.src.substring(this.pos));
                                }
                            } else {
                                throw new ParseException("Unexcpected character in string: '\\" + charAt2 + "'");
                            }
                        } else {
                            throw new ParseException("Unterminated string");
                        }
                    }
                }
            } else {
                throw new ParseException("Unterminated string literal");
            }
        }
    }

    private Number readNumber(char c) throws ParseException {
        int i;
        StringBuilder sb = new StringBuilder();
        sb.append(c);
        while (true) {
            int i2 = this.pos;
            if (i2 >= this.length) {
                break;
            }
            char charAt = this.src.charAt(i2);
            if (!Character.isDigit(charAt) && charAt != '-' && charAt != '+' && charAt != '.' && charAt != 'e' && charAt != 'E') {
                break;
            }
            this.pos++;
            sb.append(charAt);
        }
        String sb2 = sb.toString();
        int length2 = sb2.length();
        int i3 = 0;
        while (true) {
            if (i3 >= length2) {
                break;
            }
            try {
                char charAt2 = sb2.charAt(i3);
                if (!Character.isDigit(charAt2)) {
                    i3++;
                } else if (charAt2 == '0' && length2 > (i = i3 + 1)) {
                    if (Character.isDigit(sb2.charAt(i))) {
                        throw new ParseException("Unsupported number format: " + sb2);
                    }
                }
            } catch (NumberFormatException unused) {
                throw new ParseException("Unsupported number format: " + sb2);
            }
        }
        double parseDouble = Double.parseDouble(sb2);
        int i4 = (int) parseDouble;
        if (((double) i4) == parseDouble) {
            return Integer.valueOf(i4);
        }
        return Double.valueOf(parseDouble);
    }

    private Boolean readTrue() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 >= 3 && this.src.charAt(i2) == 'r' && this.src.charAt(this.pos + 1) == 'u' && this.src.charAt(this.pos + 2) == 'e') {
            this.pos += 3;
            return Boolean.TRUE;
        }
        throw new ParseException("Unexpected token: t");
    }

    private Boolean readFalse() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 >= 4 && this.src.charAt(i2) == 'a' && this.src.charAt(this.pos + 1) == 'l' && this.src.charAt(this.pos + 2) == 's' && this.src.charAt(this.pos + 3) == 'e') {
            this.pos += 4;
            return Boolean.FALSE;
        }
        throw new ParseException("Unexpected token: f");
    }

    private Object readNull() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 >= 3 && this.src.charAt(i2) == 'u' && this.src.charAt(this.pos + 1) == 'l' && this.src.charAt(this.pos + 2) == 'l') {
            this.pos += 3;
            return null;
        }
        throw new ParseException("Unexpected token: n");
    }

    private void consumeWhitespace() {
        while (true) {
            int i = this.pos;
            if (i < this.length) {
                char charAt = this.src.charAt(i);
                if (charAt == 9 || charAt == 10 || charAt == 13 || charAt == ' ') {
                    this.pos++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private void consume(char c) throws ParseException {
        consumeWhitespace();
        int i = this.pos;
        if (i < this.length) {
            String str = this.src;
            this.pos = i + 1;
            char charAt = str.charAt(i);
            if (charAt != c) {
                throw new ParseException("Expected " + c + " found " + charAt);
            }
            return;
        }
        throw new ParseException("Expected " + c + " but reached end of stream");
    }

    public static class ParseException extends Exception {
        static final long serialVersionUID = 4804542791749920772L;

        ParseException(String str) {
            super(str);
        }

        ParseException(Exception exc) {
            super(exc);
        }
    }
}
