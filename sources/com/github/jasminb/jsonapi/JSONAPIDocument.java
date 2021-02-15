package com.github.jasminb.jsonapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.models.errors.Error;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JSONAPIDocument<T> {
    private T data;
    private ObjectMapper deserializer;
    private Iterable<? extends Error> errors;
    private Links links;
    private Map<String, Object> meta;

    public JSONAPIDocument(T t) {
        this.data = t;
    }

    public JSONAPIDocument(T t, ObjectMapper objectMapper) {
        this(t);
        this.deserializer = objectMapper;
    }

    public JSONAPIDocument(T t, Links links2, Map<String, Object> map) {
        this(t);
        this.links = links2;
        this.meta = map;
    }

    public JSONAPIDocument(T t, Links links2, Map<String, Object> map, ObjectMapper objectMapper) {
        this(t, links2, map);
        this.deserializer = objectMapper;
    }

    public JSONAPIDocument() {
    }

    public JSONAPIDocument(Iterable<? extends Error> iterable) {
        this.errors = iterable;
    }

    public JSONAPIDocument(Error error) {
        this.errors = Arrays.asList(new Error[]{error});
    }

    public static JSONAPIDocument<?> createErrorDocument(Iterable<? extends Error> iterable) {
        JSONAPIDocument<?> jSONAPIDocument = new JSONAPIDocument<>();
        jSONAPIDocument.errors = iterable;
        return jSONAPIDocument;
    }

    public T get() {
        return this.data;
    }

    public Map<String, ?> getMeta() {
        return this.meta;
    }

    public void setMeta(Map<String, ?> map) {
        this.meta = new HashMap(map);
    }

    public void addMeta(String str, Object obj) {
        if (this.meta == null) {
            this.meta = new HashMap();
        }
        this.meta.put(str, obj);
    }

    public Links getLinks() {
        return this.links;
    }

    public void addLink(String str, Link link) {
        if (this.links == null) {
            this.links = new Links(new HashMap());
        }
        this.links.addLink(str, link);
    }

    public void setLinks(Links links2) {
        this.links = links2;
    }

    public <T> T getMeta(Class<?> cls) {
        ObjectMapper objectMapper;
        Map<String, Object> map = this.meta;
        if (map == null || (objectMapper = this.deserializer) == null) {
            return null;
        }
        return objectMapper.convertValue((Object) map, cls);
    }

    public Iterable<? extends Error> getErrors() {
        return this.errors;
    }
}
