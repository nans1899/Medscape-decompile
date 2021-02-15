package com.github.jasminb.jsonapi;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

@JsonSerialize(using = LinkSerializer.class)
public class Link implements Serializable {
    private static final long serialVersionUID = -6509249812347545112L;
    private String href;
    private Map<String, ?> meta;

    public Link() {
    }

    public Link(String str) {
        this.href = str;
    }

    public Link(String str, Map<String, ?> map) {
        this.href = str;
        this.meta = map;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String str) {
        this.href = str;
    }

    public Map<String, ?> getMeta() {
        return this.meta;
    }

    public void setMeta(Map<String, ?> map) {
        this.meta = map;
    }

    public String toString() {
        return String.valueOf(getHref());
    }

    protected static class LinkSerializer extends StdSerializer<Link> {
        public LinkSerializer() {
            super(Link.class);
        }

        public void serialize(Link link, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (link.getMeta() != null) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("href", link.getHref());
                jsonGenerator.writeObjectField(JSONAPISpecConstants.META, link.getMeta());
                jsonGenerator.writeEndObject();
                return;
            }
            jsonGenerator.writeString(link.getHref());
        }
    }
}
