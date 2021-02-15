package at.rags.morpheus;

import java.util.HashMap;
import java.util.List;

public class JsonApiObject {
    private List<Error> errors;
    private List<Resource> included;
    private Links links;
    private HashMap<String, Object> meta;
    private Resource resource;
    private List<Resource> resources;

    public Resource getResource() {
        return this.resource;
    }

    public void setResource(Resource resource2) {
        this.resource = resource2;
    }

    public List<Resource> getResources() {
        return this.resources;
    }

    public void setResources(List<Resource> list) {
        this.resources = list;
    }

    public List<Resource> getIncluded() {
        return this.included;
    }

    public void setIncluded(List<Resource> list) {
        this.included = list;
    }

    public HashMap<String, Object> getMeta() {
        return this.meta;
    }

    public void setMeta(HashMap<String, Object> hashMap) {
        this.meta = hashMap;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    public void setErrors(List<Error> list) {
        this.errors = list;
    }

    public Links getLinks() {
        return this.links;
    }

    public void setLinks(Links links2) {
        this.links = links2;
    }
}
