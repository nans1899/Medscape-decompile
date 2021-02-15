package at.rags.morpheus;

import java.util.HashMap;

public class Error {
    private String code;
    private String detail;
    private String id;
    private ErrorLinks links;
    private HashMap<String, Object> meta;
    private Source source;
    private String status;
    private String title;

    public HashMap<String, Object> getMeta() {
        return this.meta;
    }

    public void setMeta(HashMap<String, Object> hashMap) {
        this.meta = hashMap;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public ErrorLinks getLinks() {
        return this.links;
    }

    public void setLinks(ErrorLinks errorLinks) {
        this.links = errorLinks;
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
}
