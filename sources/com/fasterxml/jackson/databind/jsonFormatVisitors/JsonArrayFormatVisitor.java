package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;

public interface JsonArrayFormatVisitor extends JsonFormatVisitorWithSerializerProvider {
    void itemsFormat(JsonFormatTypes jsonFormatTypes) throws JsonMappingException;

    void itemsFormat(JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException;

    public static class Base implements JsonArrayFormatVisitor {
        protected SerializerProvider _provider;

        public void itemsFormat(JsonFormatTypes jsonFormatTypes) throws JsonMappingException {
        }

        public void itemsFormat(JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException {
        }

        public Base() {
        }

        public Base(SerializerProvider serializerProvider) {
            this._provider = serializerProvider;
        }

        public SerializerProvider getProvider() {
            return this._provider;
        }

        public void setProvider(SerializerProvider serializerProvider) {
            this._provider = serializerProvider;
        }
    }
}