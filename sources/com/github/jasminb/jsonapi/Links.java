package com.github.jasminb.jsonapi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Links implements Serializable {
    private static final long serialVersionUID = 1305238708279094084L;
    private Map<String, Link> links;

    public Links() {
        this.links = new LinkedHashMap();
    }

    public Links(Map<String, Link> map) {
        this.links = new LinkedHashMap(map);
    }

    public Link getLink(String str) {
        return this.links.get(str);
    }

    @JsonIgnore
    public Link getPrevious() {
        return getLink(JSONAPISpecConstants.PREV);
    }

    @JsonIgnore
    public Link getFirst() {
        return getLink(JSONAPISpecConstants.FIRST);
    }

    @JsonIgnore
    public Link getNext() {
        return getLink(JSONAPISpecConstants.NEXT);
    }

    @JsonIgnore
    public Link getLast() {
        return getLink(JSONAPISpecConstants.LAST);
    }

    @JsonIgnore
    public Link getSelf() {
        return getLink(JSONAPISpecConstants.SELF);
    }

    @JsonIgnore
    public Link getRelated() {
        return getLink(JSONAPISpecConstants.RELATED);
    }

    public Map<String, Link> getLinks() {
        return new LinkedHashMap(this.links);
    }

    public void addLink(String str, Link link) {
        this.links.put(str, link);
    }
}
