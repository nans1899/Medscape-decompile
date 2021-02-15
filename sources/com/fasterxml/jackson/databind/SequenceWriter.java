package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.cfg.PackageVersion;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.TypeWrappedSerializer;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.Collection;

public class SequenceWriter implements Versioned, Closeable, Flushable {
    protected final boolean _cfgCloseCloseable = this._config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE);
    protected final boolean _cfgFlush;
    protected final boolean _closeGenerator;
    protected boolean _closed;
    protected final SerializationConfig _config;
    protected PropertySerializerMap _dynamicSerializers = PropertySerializerMap.emptyForRootValues();
    protected final JsonGenerator _generator;
    protected boolean _openArray;
    protected final DefaultSerializerProvider _provider;
    protected final JsonSerializer<Object> _rootSerializer;
    protected final TypeSerializer _typeSerializer;

    public SequenceWriter(DefaultSerializerProvider defaultSerializerProvider, JsonGenerator jsonGenerator, boolean z, ObjectWriter.Prefetch prefetch) throws IOException {
        this._provider = defaultSerializerProvider;
        this._generator = jsonGenerator;
        this._closeGenerator = z;
        this._rootSerializer = prefetch.getValueSerializer();
        this._typeSerializer = prefetch.getTypeSerializer();
        SerializationConfig config = defaultSerializerProvider.getConfig();
        this._config = config;
        this._cfgFlush = config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE);
    }

    public SequenceWriter init(boolean z) throws IOException {
        if (z) {
            this._generator.writeStartArray();
            this._openArray = true;
        }
        return this;
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public SequenceWriter write(Object obj) throws IOException {
        if (obj == null) {
            this._provider.serializeValue(this._generator, (Object) null);
            return this;
        } else if (this._cfgCloseCloseable && (obj instanceof Closeable)) {
            return _writeCloseableValue(obj);
        } else {
            JsonSerializer<Object> jsonSerializer = this._rootSerializer;
            if (jsonSerializer == null) {
                Class<?> cls = obj.getClass();
                JsonSerializer<Object> serializerFor = this._dynamicSerializers.serializerFor(cls);
                jsonSerializer = serializerFor == null ? _findAndAddDynamic(cls) : serializerFor;
            }
            this._provider.serializeValue(this._generator, obj, (JavaType) null, jsonSerializer);
            if (this._cfgFlush) {
                this._generator.flush();
            }
            return this;
        }
    }

    public SequenceWriter write(Object obj, JavaType javaType) throws IOException {
        if (obj == null) {
            this._provider.serializeValue(this._generator, (Object) null);
            return this;
        } else if (this._cfgCloseCloseable && (obj instanceof Closeable)) {
            return _writeCloseableValue(obj, javaType);
        } else {
            JsonSerializer<Object> serializerFor = this._dynamicSerializers.serializerFor(javaType.getRawClass());
            if (serializerFor == null) {
                serializerFor = _findAndAddDynamic(javaType);
            }
            this._provider.serializeValue(this._generator, obj, javaType, serializerFor);
            if (this._cfgFlush) {
                this._generator.flush();
            }
            return this;
        }
    }

    public SequenceWriter writeAll(Object[] objArr) throws IOException {
        for (Object write : objArr) {
            write(write);
        }
        return this;
    }

    public <C extends Collection<?>> SequenceWriter writeAll(C c) throws IOException {
        for (Object write : c) {
            write(write);
        }
        return this;
    }

    public SequenceWriter writeAll(Iterable<?> iterable) throws IOException {
        for (Object write : iterable) {
            write(write);
        }
        return this;
    }

    public void flush() throws IOException {
        if (!this._closed) {
            this._generator.flush();
        }
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            if (this._openArray) {
                this._openArray = false;
                this._generator.writeEndArray();
            }
            if (this._closeGenerator) {
                this._generator.close();
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0034 A[SYNTHETIC, Splitter:B:18:0x0034] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.SequenceWriter _writeCloseableValue(java.lang.Object r6) throws java.io.IOException {
        /*
            r5 = this;
            r0 = r6
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r2 = r5._rootSerializer     // Catch:{ all -> 0x0031 }
            if (r2 != 0) goto L_0x001a
            java.lang.Class r2 = r6.getClass()     // Catch:{ all -> 0x0031 }
            com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap r3 = r5._dynamicSerializers     // Catch:{ all -> 0x0031 }
            com.fasterxml.jackson.databind.JsonSerializer r3 = r3.serializerFor(r2)     // Catch:{ all -> 0x0031 }
            if (r3 != 0) goto L_0x0019
            com.fasterxml.jackson.databind.JsonSerializer r2 = r5._findAndAddDynamic((java.lang.Class<?>) r2)     // Catch:{ all -> 0x0031 }
            goto L_0x001a
        L_0x0019:
            r2 = r3
        L_0x001a:
            com.fasterxml.jackson.databind.ser.DefaultSerializerProvider r3 = r5._provider     // Catch:{ all -> 0x0031 }
            com.fasterxml.jackson.core.JsonGenerator r4 = r5._generator     // Catch:{ all -> 0x0031 }
            r3.serializeValue(r4, r6, r1, r2)     // Catch:{ all -> 0x0031 }
            boolean r6 = r5._cfgFlush     // Catch:{ all -> 0x0031 }
            if (r6 == 0) goto L_0x002a
            com.fasterxml.jackson.core.JsonGenerator r6 = r5._generator     // Catch:{ all -> 0x0031 }
            r6.flush()     // Catch:{ all -> 0x0031 }
        L_0x002a:
            r0.close()     // Catch:{ all -> 0x002e }
            return r5
        L_0x002e:
            r6 = move-exception
            r0 = r1
            goto L_0x0032
        L_0x0031:
            r6 = move-exception
        L_0x0032:
            if (r0 == 0) goto L_0x0037
            r0.close()     // Catch:{ IOException -> 0x0037 }
        L_0x0037:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.SequenceWriter._writeCloseableValue(java.lang.Object):com.fasterxml.jackson.databind.SequenceWriter");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f A[SYNTHETIC, Splitter:B:16:0x002f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.SequenceWriter _writeCloseableValue(java.lang.Object r5, com.fasterxml.jackson.databind.JavaType r6) throws java.io.IOException {
        /*
            r4 = this;
            r0 = r5
            java.io.Closeable r0 = (java.io.Closeable) r0
            com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap r1 = r4._dynamicSerializers     // Catch:{ all -> 0x002c }
            java.lang.Class r2 = r6.getRawClass()     // Catch:{ all -> 0x002c }
            com.fasterxml.jackson.databind.JsonSerializer r1 = r1.serializerFor(r2)     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x0013
            com.fasterxml.jackson.databind.JsonSerializer r1 = r4._findAndAddDynamic((com.fasterxml.jackson.databind.JavaType) r6)     // Catch:{ all -> 0x002c }
        L_0x0013:
            com.fasterxml.jackson.databind.ser.DefaultSerializerProvider r2 = r4._provider     // Catch:{ all -> 0x002c }
            com.fasterxml.jackson.core.JsonGenerator r3 = r4._generator     // Catch:{ all -> 0x002c }
            r2.serializeValue(r3, r5, r6, r1)     // Catch:{ all -> 0x002c }
            boolean r5 = r4._cfgFlush     // Catch:{ all -> 0x002c }
            if (r5 == 0) goto L_0x0023
            com.fasterxml.jackson.core.JsonGenerator r5 = r4._generator     // Catch:{ all -> 0x002c }
            r5.flush()     // Catch:{ all -> 0x002c }
        L_0x0023:
            r5 = 0
            r0.close()     // Catch:{ all -> 0x0028 }
            return r4
        L_0x0028:
            r6 = move-exception
            r0 = r5
            r5 = r6
            goto L_0x002d
        L_0x002c:
            r5 = move-exception
        L_0x002d:
            if (r0 == 0) goto L_0x0032
            r0.close()     // Catch:{ IOException -> 0x0032 }
        L_0x0032:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.SequenceWriter._writeCloseableValue(java.lang.Object, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.SequenceWriter");
    }

    private final JsonSerializer<Object> _findAndAddDynamic(Class<?> cls) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult serializerAndMapResult;
        TypeSerializer typeSerializer = this._typeSerializer;
        if (typeSerializer == null) {
            serializerAndMapResult = this._dynamicSerializers.findAndAddRootValueSerializer(cls, (SerializerProvider) this._provider);
        } else {
            serializerAndMapResult = this._dynamicSerializers.addSerializer(cls, (JsonSerializer<Object>) new TypeWrappedSerializer(typeSerializer, this._provider.findValueSerializer(cls, (BeanProperty) null)));
        }
        this._dynamicSerializers = serializerAndMapResult.map;
        return serializerAndMapResult.serializer;
    }

    private final JsonSerializer<Object> _findAndAddDynamic(JavaType javaType) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult serializerAndMapResult;
        TypeSerializer typeSerializer = this._typeSerializer;
        if (typeSerializer == null) {
            serializerAndMapResult = this._dynamicSerializers.findAndAddRootValueSerializer(javaType, (SerializerProvider) this._provider);
        } else {
            serializerAndMapResult = this._dynamicSerializers.addSerializer(javaType, (JsonSerializer<Object>) new TypeWrappedSerializer(typeSerializer, this._provider.findValueSerializer(javaType, (BeanProperty) null)));
        }
        this._dynamicSerializers = serializerAndMapResult.map;
        return serializerAndMapResult.serializer;
    }
}
