package org.mockito.internal.matchers.text;

import com.appboy.Constants;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;

public class ValuePrinter {
    private ValuePrinter() {
    }

    public static String print(final Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return '\"' + obj.toString() + '\"';
        } else if (obj instanceof Character) {
            return printChar(((Character) obj).charValue());
        } else {
            if (obj instanceof Long) {
                return obj + "L";
            } else if (obj instanceof Double) {
                return obj + Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE;
            } else if (obj instanceof Float) {
                return obj + "f";
            } else if (obj instanceof Short) {
                return "(short) " + obj;
            } else if (obj instanceof Byte) {
                return String.format("(byte) 0x%02X", new Object[]{(Byte) obj});
            } else if (obj instanceof Map) {
                return printMap((Map) obj);
            } else {
                if (obj.getClass().isArray()) {
                    return printValues("[", ", ", "]", new Iterator<Object>() {
                        private int currentIndex = 0;

                        public boolean hasNext() {
                            return this.currentIndex < Array.getLength(obj);
                        }

                        public Object next() {
                            Object obj = obj;
                            int i = this.currentIndex;
                            this.currentIndex = i + 1;
                            return Array.get(obj, i);
                        }

                        public void remove() {
                            throw new UnsupportedOperationException("cannot remove items from an array");
                        }
                    });
                }
                if (obj instanceof FormattedText) {
                    return ((FormattedText) obj).getText();
                }
                return descriptionOf(obj);
            }
        }
    }

    private static String printMap(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            sb.append(print(next.getKey()));
            sb.append(" = ");
            sb.append(print(next.getValue()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return "{" + sb.toString() + "}";
    }

    public static String printValues(String str, String str2, String str3, Iterator<?> it) {
        if (str == null) {
            str = "(";
        }
        if (str2 == null) {
            str2 = ",";
        }
        if (str3 == null) {
            str3 = ")";
        }
        StringBuilder sb = new StringBuilder(str);
        while (it.hasNext()) {
            sb.append(print(it.next()));
            if (it.hasNext()) {
                sb.append(str2);
            }
        }
        sb.append(str3);
        return sb.toString();
    }

    private static String printChar(char c) {
        StringBuilder sb = new StringBuilder();
        sb.append('\'');
        if (c == 9) {
            sb.append("\\t");
        } else if (c == 10) {
            sb.append("\\n");
        } else if (c == 13) {
            sb.append("\\r");
        } else if (c != '\"') {
            sb.append(c);
        } else {
            sb.append("\\\"");
        }
        sb.append('\'');
        return sb.toString();
    }

    private static String descriptionOf(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception unused) {
            return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
        }
    }
}
