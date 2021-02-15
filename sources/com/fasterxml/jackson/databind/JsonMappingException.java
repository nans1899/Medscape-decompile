package com.fasterxml.jackson.databind;

import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import net.bytebuddy.pool.TypePool;

public class JsonMappingException extends JsonProcessingException {
    static final int MAX_REFS_TO_LIST = 1000;
    private static final long serialVersionUID = 1;
    protected LinkedList<Reference> _path;
    protected Closeable _processor;

    public static class Reference implements Serializable {
        private static final long serialVersionUID = 1;
        protected String _fieldName;
        protected Object _from;
        protected int _index = -1;

        protected Reference() {
        }

        public Reference(Object obj) {
            this._from = obj;
        }

        public Reference(Object obj, String str) {
            this._from = obj;
            if (str != null) {
                this._fieldName = str;
                return;
            }
            throw new NullPointerException("Can not pass null fieldName");
        }

        public Reference(Object obj, int i) {
            this._from = obj;
            this._index = i;
        }

        public void setFrom(Object obj) {
            this._from = obj;
        }

        public void setFieldName(String str) {
            this._fieldName = str;
        }

        public void setIndex(int i) {
            this._index = i;
        }

        public Object getFrom() {
            return this._from;
        }

        public String getFieldName() {
            return this._fieldName;
        }

        public int getIndex() {
            return this._index;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Object obj = this._from;
            Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
            String packageName = ClassUtil.getPackageName(cls);
            if (packageName != null) {
                sb.append(packageName);
                sb.append('.');
            }
            sb.append(cls.getSimpleName());
            sb.append(TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH);
            if (this._fieldName != null) {
                sb.append('\"');
                sb.append(this._fieldName);
                sb.append('\"');
            } else {
                int i = this._index;
                if (i >= 0) {
                    sb.append(i);
                } else {
                    sb.append('?');
                }
            }
            sb.append(']');
            return sb.toString();
        }
    }

    @Deprecated
    public JsonMappingException(String str) {
        super(str);
    }

    @Deprecated
    public JsonMappingException(String str, Throwable th) {
        super(str, th);
    }

    @Deprecated
    public JsonMappingException(String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
    }

    @Deprecated
    public JsonMappingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str, jsonLocation, th);
    }

    public JsonMappingException(Closeable closeable, String str) {
        super(str);
        this._processor = closeable;
        if (closeable instanceof JsonParser) {
            this._location = ((JsonParser) closeable).getTokenLocation();
        }
    }

    public JsonMappingException(Closeable closeable, String str, Throwable th) {
        super(str, th);
        this._processor = closeable;
        if (closeable instanceof JsonParser) {
            this._location = ((JsonParser) closeable).getTokenLocation();
        }
    }

    public JsonMappingException(Closeable closeable, String str, JsonLocation jsonLocation) {
        super(str, jsonLocation);
        this._processor = closeable;
    }

    public static JsonMappingException from(JsonParser jsonParser, String str) {
        return new JsonMappingException((Closeable) jsonParser, str);
    }

    public static JsonMappingException from(JsonParser jsonParser, String str, Throwable th) {
        return new JsonMappingException((Closeable) jsonParser, str, th);
    }

    public static JsonMappingException from(JsonGenerator jsonGenerator, String str) {
        return new JsonMappingException((Closeable) jsonGenerator, str, (Throwable) null);
    }

    public static JsonMappingException from(JsonGenerator jsonGenerator, String str, Throwable th) {
        return new JsonMappingException((Closeable) jsonGenerator, str, th);
    }

    public static JsonMappingException from(DeserializationContext deserializationContext, String str) {
        return new JsonMappingException((Closeable) deserializationContext.getParser(), str);
    }

    public static JsonMappingException from(DeserializationContext deserializationContext, String str, Throwable th) {
        return new JsonMappingException((Closeable) deserializationContext.getParser(), str, th);
    }

    public static JsonMappingException from(SerializerProvider serializerProvider, String str) {
        return new JsonMappingException((Closeable) null, str);
    }

    public static JsonMappingException from(SerializerProvider serializerProvider, String str, Throwable th) {
        return new JsonMappingException((Closeable) null, str, th);
    }

    public static JsonMappingException fromUnexpectedIOE(IOException iOException) {
        return new JsonMappingException((Closeable) null, String.format("Unexpected IOException (of type %s): %s", new Object[]{iOException.getClass().getName(), iOException.getMessage()}));
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, String str) {
        return wrapWithPath(th, new Reference(obj, str));
    }

    public static JsonMappingException wrapWithPath(Throwable th, Object obj, int i) {
        return wrapWithPath(th, new Reference(obj, i));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.io.Closeable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.fasterxml.jackson.databind.JsonMappingException wrapWithPath(java.lang.Throwable r4, com.fasterxml.jackson.databind.JsonMappingException.Reference r5) {
        /*
            boolean r0 = r4 instanceof com.fasterxml.jackson.databind.JsonMappingException
            if (r0 == 0) goto L_0x0007
            com.fasterxml.jackson.databind.JsonMappingException r4 = (com.fasterxml.jackson.databind.JsonMappingException) r4
            goto L_0x004a
        L_0x0007:
            java.lang.String r0 = r4.getMessage()
            if (r0 == 0) goto L_0x0013
            int r1 = r0.length()
            if (r1 != 0) goto L_0x0031
        L_0x0013:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "(was "
            r0.append(r1)
            java.lang.Class r1 = r4.getClass()
            java.lang.String r1 = r1.getName()
            r0.append(r1)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L_0x0031:
            r1 = 0
            boolean r2 = r4 instanceof com.fasterxml.jackson.core.JsonProcessingException
            if (r2 == 0) goto L_0x0044
            r2 = r4
            com.fasterxml.jackson.core.JsonProcessingException r2 = (com.fasterxml.jackson.core.JsonProcessingException) r2
            java.lang.Object r2 = r2.getProcessor()
            boolean r3 = r2 instanceof java.io.Closeable
            if (r3 == 0) goto L_0x0044
            r1 = r2
            java.io.Closeable r1 = (java.io.Closeable) r1
        L_0x0044:
            com.fasterxml.jackson.databind.JsonMappingException r2 = new com.fasterxml.jackson.databind.JsonMappingException
            r2.<init>((java.io.Closeable) r1, (java.lang.String) r0, (java.lang.Throwable) r4)
            r4 = r2
        L_0x004a:
            r4.prependPath(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.JsonMappingException.wrapWithPath(java.lang.Throwable, com.fasterxml.jackson.databind.JsonMappingException$Reference):com.fasterxml.jackson.databind.JsonMappingException");
    }

    public List<Reference> getPath() {
        LinkedList<Reference> linkedList = this._path;
        if (linkedList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(linkedList);
    }

    public String getPathReference() {
        return getPathReference(new StringBuilder()).toString();
    }

    public StringBuilder getPathReference(StringBuilder sb) {
        _appendPathDesc(sb);
        return sb;
    }

    public void prependPath(Object obj, String str) {
        prependPath(new Reference(obj, str));
    }

    public void prependPath(Object obj, int i) {
        prependPath(new Reference(obj, i));
    }

    public void prependPath(Reference reference) {
        if (this._path == null) {
            this._path = new LinkedList<>();
        }
        if (this._path.size() < 1000) {
            this._path.addFirst(reference);
        }
    }

    public String getLocalizedMessage() {
        return _buildMessage();
    }

    public String getMessage() {
        return _buildMessage();
    }

    /* access modifiers changed from: protected */
    public String _buildMessage() {
        String message = super.getMessage();
        if (this._path == null) {
            return message;
        }
        StringBuilder sb = message == null ? new StringBuilder() : new StringBuilder(message);
        sb.append(" (through reference chain: ");
        StringBuilder pathReference = getPathReference(sb);
        pathReference.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        return pathReference.toString();
    }

    public String toString() {
        return getClass().getName() + ": " + getMessage();
    }

    /* access modifiers changed from: protected */
    public void _appendPathDesc(StringBuilder sb) {
        LinkedList<Reference> linkedList = this._path;
        if (linkedList != null) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                sb.append(((Reference) it.next()).toString());
                if (it.hasNext()) {
                    sb.append("->");
                }
            }
        }
    }
}
