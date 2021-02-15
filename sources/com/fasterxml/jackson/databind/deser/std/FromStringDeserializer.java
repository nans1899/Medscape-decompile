package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    /* access modifiers changed from: protected */
    public abstract T _deserialize(String str, DeserializationContext deserializationContext) throws IOException;

    /* access modifiers changed from: protected */
    public T _deserializeFromEmptyString() throws IOException {
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Class<?>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Class<?>[] types() {
        /*
            r0 = 12
            java.lang.Class[] r0 = new java.lang.Class[r0]
            r1 = 0
            java.lang.Class<java.io.File> r2 = java.io.File.class
            r0[r1] = r2
            r1 = 1
            java.lang.Class<java.net.URL> r2 = java.net.URL.class
            r0[r1] = r2
            r1 = 2
            java.lang.Class<java.net.URI> r2 = java.net.URI.class
            r0[r1] = r2
            r1 = 3
            java.lang.Class<java.lang.Class> r2 = java.lang.Class.class
            r0[r1] = r2
            r1 = 4
            java.lang.Class<com.fasterxml.jackson.databind.JavaType> r2 = com.fasterxml.jackson.databind.JavaType.class
            r0[r1] = r2
            r1 = 5
            java.lang.Class<java.util.Currency> r2 = java.util.Currency.class
            r0[r1] = r2
            r1 = 6
            java.lang.Class<java.util.regex.Pattern> r2 = java.util.regex.Pattern.class
            r0[r1] = r2
            r1 = 7
            java.lang.Class<java.util.Locale> r2 = java.util.Locale.class
            r0[r1] = r2
            r1 = 8
            java.lang.Class<java.nio.charset.Charset> r2 = java.nio.charset.Charset.class
            r0[r1] = r2
            r1 = 9
            java.lang.Class<java.util.TimeZone> r2 = java.util.TimeZone.class
            r0[r1] = r2
            r1 = 10
            java.lang.Class<java.net.InetAddress> r2 = java.net.InetAddress.class
            r0[r1] = r2
            r1 = 11
            java.lang.Class<java.net.InetSocketAddress> r2 = java.net.InetSocketAddress.class
            r0[r1] = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.FromStringDeserializer.types():java.lang.Class[]");
    }

    protected FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public static Std findDeserializer(Class<?> cls) {
        int i;
        if (cls == File.class) {
            i = 1;
        } else if (cls == URL.class) {
            i = 2;
        } else if (cls == URI.class) {
            i = 3;
        } else if (cls == Class.class) {
            i = 4;
        } else if (cls == JavaType.class) {
            i = 5;
        } else if (cls == Currency.class) {
            i = 6;
        } else if (cls == Pattern.class) {
            i = 7;
        } else if (cls == Locale.class) {
            i = 8;
        } else if (cls == Charset.class) {
            i = 9;
        } else if (cls == TimeZone.class) {
            i = 10;
        } else if (cls == InetAddress.class) {
            i = 11;
        } else if (cls != InetSocketAddress.class) {
            return null;
        } else {
            i = 12;
        }
        return new Std(cls, i);
    }

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String message;
        if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY || !deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            String valueAsString = jsonParser.getValueAsString();
            IllegalArgumentException e = null;
            if (valueAsString != null) {
                if (valueAsString.length() != 0) {
                    String trim = valueAsString.trim();
                    if (trim.length() != 0) {
                        try {
                            T _deserialize = _deserialize(trim, deserializationContext);
                            if (_deserialize != null) {
                                return _deserialize;
                            }
                        } catch (IllegalArgumentException e2) {
                            e = e2;
                        }
                        String str = "not a valid textual representation";
                        if (!(e == null || (message = e.getMessage()) == null)) {
                            str = str + ", problem: " + message;
                        }
                        JsonMappingException weirdStringException = deserializationContext.weirdStringException(trim, this._valueClass, str);
                        if (e != null) {
                            weirdStringException.initCause(e);
                        }
                        throw weirdStringException;
                    }
                }
                return _deserializeFromEmptyString();
            } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
                T embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return null;
                }
                if (this._valueClass.isAssignableFrom(embeddedObject.getClass())) {
                    return embeddedObject;
                }
                return _deserializeEmbedded(embeddedObject, deserializationContext);
            } else {
                throw deserializationContext.mappingException((Class<?>) this._valueClass);
            }
        } else {
            jsonParser.nextToken();
            T deserialize = deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_ARRAY) {
                return deserialize;
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '" + this._valueClass.getName() + "' value but there was more than a single value in the array");
        }
    }

    /* access modifiers changed from: protected */
    public T _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException {
        throw deserializationContext.mappingException("Don't know how to convert embedded Object of type %s into %s", obj.getClass().getName(), this._valueClass.getName());
    }

    public static class Std extends FromStringDeserializer<Object> {
        public static final int STD_CHARSET = 9;
        public static final int STD_CLASS = 4;
        public static final int STD_CURRENCY = 6;
        public static final int STD_FILE = 1;
        public static final int STD_INET_ADDRESS = 11;
        public static final int STD_INET_SOCKET_ADDRESS = 12;
        public static final int STD_JAVA_TYPE = 5;
        public static final int STD_LOCALE = 8;
        public static final int STD_PATTERN = 7;
        public static final int STD_TIME_ZONE = 10;
        public static final int STD_URI = 3;
        public static final int STD_URL = 2;
        private static final long serialVersionUID = 1;
        protected final int _kind;

        protected Std(Class<?> cls, int i) {
            super(cls);
            this._kind = i;
        }

        /* access modifiers changed from: protected */
        public Object _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            switch (this._kind) {
                case 1:
                    return new File(str);
                case 2:
                    return new URL(str);
                case 3:
                    return URI.create(str);
                case 4:
                    try {
                        return deserializationContext.findClass(str);
                    } catch (Exception e) {
                        throw deserializationContext.instantiationException((Class<?>) this._valueClass, ClassUtil.getRootCause(e));
                    }
                case 5:
                    return deserializationContext.getTypeFactory().constructFromCanonical(str);
                case 6:
                    return Currency.getInstance(str);
                case 7:
                    return Pattern.compile(str);
                case 8:
                    int indexOf = str.indexOf(95);
                    if (indexOf < 0) {
                        return new Locale(str);
                    }
                    String substring = str.substring(0, indexOf);
                    String substring2 = str.substring(indexOf + 1);
                    int indexOf2 = substring2.indexOf(95);
                    if (indexOf2 < 0) {
                        return new Locale(substring, substring2);
                    }
                    return new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
                case 9:
                    return Charset.forName(str);
                case 10:
                    return TimeZone.getTimeZone(str);
                case 11:
                    return InetAddress.getByName(str);
                case 12:
                    if (str.startsWith("[")) {
                        int lastIndexOf = str.lastIndexOf(93);
                        if (lastIndexOf != -1) {
                            int indexOf3 = str.indexOf(58, lastIndexOf);
                            return new InetSocketAddress(str.substring(0, lastIndexOf + 1), indexOf3 > -1 ? Integer.parseInt(str.substring(indexOf3 + 1)) : 0);
                        }
                        throw new InvalidFormatException(deserializationContext.getParser(), "Bracketed IPv6 address must contain closing bracket", (Object) str, (Class<?>) InetSocketAddress.class);
                    }
                    int indexOf4 = str.indexOf(58);
                    if (indexOf4 >= 0) {
                        int i = indexOf4 + 1;
                        if (str.indexOf(58, i) < 0) {
                            return new InetSocketAddress(str.substring(0, indexOf4), Integer.parseInt(str.substring(i)));
                        }
                    }
                    return new InetSocketAddress(str, 0);
                default:
                    throw new IllegalArgumentException();
            }
        }

        /* access modifiers changed from: protected */
        public Object _deserializeFromEmptyString() throws IOException {
            if (this._kind == 3) {
                return URI.create("");
            }
            return FromStringDeserializer.super._deserializeFromEmptyString();
        }
    }
}
