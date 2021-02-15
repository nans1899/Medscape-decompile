package org.xmlpull.v1.builder.impl;

import org.xmlpull.v1.builder.XmlComment;
import org.xmlpull.v1.builder.XmlContainer;

public class XmlCommentImpl implements XmlComment {
    private String content_;
    private XmlContainer owner_;

    XmlCommentImpl(XmlContainer xmlContainer, String str) {
        this.owner_ = xmlContainer;
        this.content_ = str;
        if (str == null) {
            throw new IllegalArgumentException("comment content can not be null");
        }
    }

    public String getContent() {
        return this.content_;
    }

    public XmlContainer getParent() {
        return this.owner_;
    }
}
