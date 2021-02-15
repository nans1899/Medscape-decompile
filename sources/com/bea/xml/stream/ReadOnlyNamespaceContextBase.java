package com.bea.xml.stream;

import com.appboy.Constants;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class ReadOnlyNamespaceContextBase implements NamespaceContext {
    private String[] prefixes;
    private String[] uris;

    private String checkNull(String str) {
        return str == null ? "" : str;
    }

    public ReadOnlyNamespaceContextBase(String[] strArr, String[] strArr2, int i) {
        String[] strArr3 = new String[i];
        this.prefixes = strArr3;
        this.uris = new String[i];
        System.arraycopy(strArr, 0, strArr3, 0, strArr3.length);
        String[] strArr4 = this.uris;
        System.arraycopy(strArr2, 0, strArr4, 0, strArr4.length);
    }

    public String getNamespaceURI(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Prefix may not be null.");
        } else if (str.length() > 0) {
            for (int length = this.uris.length - 1; length >= 0; length--) {
                if (str.equals(this.prefixes[length])) {
                    return this.uris[length];
                }
            }
            if ("xml".equals(str)) {
                return XMLConstants.XML_NS_URI;
            }
            if (XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
                return XMLConstants.XMLNS_ATTRIBUTE_NS_URI;
            }
            return null;
        } else {
            for (int length2 = this.uris.length - 1; length2 >= 0; length2--) {
                if (this.prefixes[length2] == null) {
                    return this.uris[length2];
                }
            }
            return null;
        }
    }

    public String getPrefix(String str) {
        if (str == null) {
            throw new IllegalArgumentException("uri may not be null");
        } else if (str.length() != 0) {
            for (int length = this.uris.length - 1; length >= 0; length--) {
                if (str.equals(this.uris[length])) {
                    String str2 = this.prefixes[length];
                    if (str2 == null) {
                        int length2 = this.uris.length - 1;
                        while (length2 > length) {
                            if (this.prefixes[length2] != null) {
                                length2--;
                            }
                        }
                        return "";
                    }
                    int length3 = this.uris.length - 1;
                    while (length3 > length) {
                        if (!str2.equals(this.prefixes[length3])) {
                            length3--;
                        }
                    }
                    return str2;
                }
            }
            if (XMLConstants.XML_NS_URI.equals(str)) {
                return "xml";
            }
            if (XMLConstants.XMLNS_ATTRIBUTE_NS_URI.equals(str)) {
                return XMLConstants.XMLNS_ATTRIBUTE;
            }
            return null;
        } else {
            throw new IllegalArgumentException("uri may not be empty string");
        }
    }

    public String getDefaultNameSpace() {
        for (int length = this.uris.length - 1; length >= 0; length--) {
            if (this.prefixes[length] == null) {
                return this.uris[length];
            }
        }
        return null;
    }

    public Iterator getPrefixes(String str) {
        if (str == null) {
            throw new IllegalArgumentException("uri may not be null");
        } else if (!"".equals(str)) {
            HashSet hashSet = new HashSet();
            for (int length = this.uris.length - 1; length >= 0; length--) {
                String checkNull = checkNull(this.prefixes[length]);
                if (str.equals(this.uris[length]) && !hashSet.contains(checkNull)) {
                    if (checkNull.length() != 0) {
                        int length2 = this.uris.length - 1;
                        while (true) {
                            if (length2 <= length) {
                                break;
                            } else if (checkNull.equals(this.prefixes[length2])) {
                                break;
                            } else {
                                length2--;
                            }
                        }
                    } else {
                        int length3 = this.uris.length - 1;
                        while (true) {
                            if (length3 <= length) {
                                break;
                            } else if (this.prefixes[length3] == null) {
                                break;
                            } else {
                                length3--;
                            }
                        }
                    }
                    hashSet.add(checkNull);
                }
            }
            return hashSet.iterator();
        } else {
            throw new IllegalArgumentException("uri may not be empty string");
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.uris.length; i++) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("[");
            stringBuffer2.append(checkNull(this.prefixes[i]));
            stringBuffer2.append("<->");
            stringBuffer2.append(this.uris[i]);
            stringBuffer2.append("]");
            stringBuffer.append(stringBuffer2.toString());
        }
        return stringBuffer.toString();
    }

    public static void main(String[] strArr) throws Exception {
        MXParser mXParser = new MXParser();
        mXParser.setInput((Reader) new FileReader(strArr[0]));
        while (mXParser.hasNext()) {
            if (mXParser.isStartElement()) {
                PrintStream printStream = System.out;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("context[");
                stringBuffer.append(mXParser.getNamespaceContext());
                stringBuffer.append("]");
                printStream.println(stringBuffer.toString());
                Iterator prefixes2 = mXParser.getNamespaceContext().getPrefixes(Constants.APPBOY_PUSH_CONTENT_KEY);
                while (prefixes2.hasNext()) {
                    PrintStream printStream2 = System.out;
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append("Found prefix:");
                    stringBuffer2.append(prefixes2.next());
                    printStream2.println(stringBuffer2.toString());
                }
            }
            mXParser.next();
        }
    }
}
