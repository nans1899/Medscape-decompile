package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MappingIterator<T> implements Iterator<T>, Closeable {
    protected static final MappingIterator<?> EMPTY_ITERATOR = new MappingIterator((JavaType) null, (JsonParser) null, (DeserializationContext) null, (JsonDeserializer<?>) null, false, (Object) null);
    protected static final int STATE_CLOSED = 0;
    protected static final int STATE_HAS_VALUE = 3;
    protected static final int STATE_MAY_HAVE_VALUE = 2;
    protected static final int STATE_NEED_RESYNC = 1;
    protected final boolean _closeParser;
    protected final DeserializationContext _context;
    protected final JsonDeserializer<T> _deserializer;
    protected final JsonParser _parser;
    protected final JsonStreamContext _seqContext;
    protected int _state;
    protected final JavaType _type;
    protected final T _updatedValue;

    protected MappingIterator(JavaType javaType, JsonParser jsonParser, DeserializationContext deserializationContext, JsonDeserializer<?> jsonDeserializer, boolean z, Object obj) {
        this._type = javaType;
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._deserializer = jsonDeserializer;
        this._closeParser = z;
        if (obj == null) {
            this._updatedValue = null;
        } else {
            this._updatedValue = obj;
        }
        if (jsonParser == null) {
            this._seqContext = null;
            this._state = 0;
            return;
        }
        JsonStreamContext parsingContext = jsonParser.getParsingContext();
        if (!z || !jsonParser.isExpectedStartArrayToken()) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.START_OBJECT || currentToken == JsonToken.START_ARRAY) {
                parsingContext = parsingContext.getParent();
            }
        } else {
            jsonParser.clearCurrentToken();
        }
        this._seqContext = parsingContext;
        this._state = 2;
    }

    protected static <T> MappingIterator<T> emptyIterator() {
        return EMPTY_ITERATOR;
    }

    public boolean hasNext() {
        try {
            return hasNextValue();
        } catch (JsonMappingException e) {
            return ((Boolean) _handleMappingException(e)).booleanValue();
        } catch (IOException e2) {
            return ((Boolean) _handleIOException(e2)).booleanValue();
        }
    }

    public T next() {
        try {
            return nextValue();
        } catch (JsonMappingException e) {
            throw new RuntimeJsonMappingException(e.getMessage(), e);
        } catch (IOException e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void close() throws IOException {
        if (this._state != 0) {
            this._state = 0;
            JsonParser jsonParser = this._parser;
            if (jsonParser != null) {
                jsonParser.close();
            }
        }
    }

    public boolean hasNextValue() throws IOException {
        JsonToken nextToken;
        JsonParser jsonParser;
        int i = this._state;
        if (i == 0) {
            return false;
        }
        if (i == 1) {
            _resync();
        } else if (i != 2) {
            return true;
        }
        if (this._parser.getCurrentToken() == null && ((nextToken = this._parser.nextToken()) == null || nextToken == JsonToken.END_ARRAY)) {
            this._state = 0;
            if (this._closeParser && (jsonParser = this._parser) != null) {
                jsonParser.close();
            }
            return false;
        }
        this._state = 3;
        return true;
    }

    public T nextValue() throws IOException {
        T t;
        int i = this._state;
        if (i == 0) {
            return _throwNoSuchElement();
        }
        int i2 = 2;
        i2 = 1;
        if ((i == i2 || i == i2) && !hasNextValue()) {
            return _throwNoSuchElement();
        }
        try {
            if (this._updatedValue == null) {
                t = this._deserializer.deserialize(this._parser, this._context);
            } else {
                this._deserializer.deserialize(this._parser, this._context, this._updatedValue);
                t = this._updatedValue;
            }
            return t;
        } finally {
            this._state = i2;
            this._parser.clearCurrentToken();
        }
    }

    public List<T> readAll() throws IOException {
        return readAll(new ArrayList());
    }

    public <L extends List<? super T>> L readAll(L l) throws IOException {
        while (hasNextValue()) {
            l.add(nextValue());
        }
        return l;
    }

    public <C extends Collection<? super T>> C readAll(C c) throws IOException {
        while (hasNextValue()) {
            c.add(nextValue());
        }
        return c;
    }

    public JsonParser getParser() {
        return this._parser;
    }

    public FormatSchema getParserSchema() {
        return this._parser.getSchema();
    }

    public JsonLocation getCurrentLocation() {
        return this._parser.getCurrentLocation();
    }

    /* access modifiers changed from: protected */
    public void _resync() throws IOException {
        JsonParser jsonParser = this._parser;
        if (jsonParser.getParsingContext() != this._seqContext) {
            while (true) {
                JsonToken nextToken = jsonParser.nextToken();
                if (nextToken == JsonToken.END_ARRAY || nextToken == JsonToken.END_OBJECT) {
                    if (jsonParser.getParsingContext() == this._seqContext) {
                        jsonParser.clearCurrentToken();
                        return;
                    }
                } else if (nextToken == JsonToken.START_ARRAY || nextToken == JsonToken.START_OBJECT) {
                    jsonParser.skipChildren();
                } else if (nextToken == null) {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public <R> R _throwNoSuchElement() {
        throw new NoSuchElementException();
    }

    /* access modifiers changed from: protected */
    public <R> R _handleMappingException(JsonMappingException jsonMappingException) {
        throw new RuntimeJsonMappingException(jsonMappingException.getMessage(), jsonMappingException);
    }

    /* access modifiers changed from: protected */
    public <R> R _handleIOException(IOException iOException) {
        throw new RuntimeException(iOException.getMessage(), iOException);
    }
}
