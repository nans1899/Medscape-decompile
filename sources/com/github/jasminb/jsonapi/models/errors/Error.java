package com.github.jasminb.jsonapi.models.errors;

import com.dd.plist.ASCIIPropertyListParser;
import com.fasterxml.jackson.databind.JsonNode;

public class Error {
    private String code;
    private String detail;
    private String id;
    private Links links;
    private JsonNode meta;
    private Source source;
    private String status;
    private String title;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public Links getLinks() {
        return this.links;
    }

    public void setLinks(Links links2) {
        this.links = links2;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public Source getSource() {
        return this.source;
    }

    public void setSource(Source source2) {
        this.source = source2;
    }

    public JsonNode getMeta() {
        return this.meta;
    }

    public void setMeta(JsonNode jsonNode) {
        this.meta = jsonNode;
    }

    public String toString() {
        return "Error{id='" + this.id + '\'' + ", links=" + this.links + ", status='" + this.status + '\'' + ", code='" + this.code + '\'' + ", title='" + this.title + '\'' + ", detail='" + this.detail + '\'' + ", source=" + this.source + ", meta=" + this.meta + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
