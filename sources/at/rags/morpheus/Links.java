package at.rags.morpheus;

public class Links {
    private String about;
    private String first;
    private String last;
    private String next;
    private String prev;
    private String related;
    private String selfLink;

    public String getSelfLink() {
        return this.selfLink;
    }

    public void setSelfLink(String str) {
        this.selfLink = str;
    }

    public String getRelated() {
        return this.related;
    }

    public void setRelated(String str) {
        this.related = str;
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String str) {
        this.first = str;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(String str) {
        this.last = str;
    }

    public String getPrev() {
        return this.prev;
    }

    public void setPrev(String str) {
        this.prev = str;
    }

    public String getNext() {
        return this.next;
    }

    public void setNext(String str) {
        this.next = str;
    }

    public String getAbout() {
        return this.about;
    }

    public void setAbout(String str) {
        this.about = str;
    }
}
