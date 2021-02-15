package at.rags.morpheus;

import java.util.ArrayList;
import java.util.HashMap;

public class Resource {
    private String Id;
    private Links links;
    private HashMap<String, Object> meta;
    private ArrayList<String> nullableRelationships = new ArrayList<>();

    public HashMap<String, Object> getMeta() {
        return this.meta;
    }

    public void setMeta(HashMap<String, Object> hashMap) {
        this.meta = hashMap;
    }

    public Links getLinks() {
        return this.links;
    }

    public void setLinks(Links links2) {
        this.links = links2;
    }

    public String getId() {
        return this.Id;
    }

    public void setId(String str) {
        this.Id = str;
    }

    public ArrayList<String> getNullableRelationships() {
        return this.nullableRelationships;
    }

    public void resetNullableRelationships() {
        this.nullableRelationships.clear();
    }

    public void addRelationshipToNull(String str) {
        if (str != null) {
            this.nullableRelationships.add(str);
        }
    }
}
