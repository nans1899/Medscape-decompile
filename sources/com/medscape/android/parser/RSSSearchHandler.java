package com.medscape.android.parser;

import com.medscape.android.parser.model.Article;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSSearchHandler extends DefaultHandler {
    static final String CATEGORY = "category";
    static final String DESCRIPTION = "description";
    static final String ITEM = "item";
    static final String LINK = "link";
    static final String PUB_DATE = "pubDate";
    private static final String TAG = "RSSSearchHandler";
    static final String TITLE = "title";
    private List<Article> articles;
    private StringBuilder builder;
    private Article currentArticle;

    public List<Article> getArticles() {
        return this.articles;
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        super.characters(cArr, i, i2);
        this.builder.append(cArr, i, i2);
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        if (this.currentArticle != null) {
            if (str2.equalsIgnoreCase("title")) {
                this.currentArticle.mTitle = this.builder.toString();
            } else if (str2.equalsIgnoreCase("link")) {
                this.currentArticle.mLink = this.builder.toString().trim();
            } else if (str2.equalsIgnoreCase("description")) {
                this.currentArticle.mDescription = this.builder.toString();
            } else if (str2.equalsIgnoreCase(PUB_DATE)) {
                this.currentArticle.setDate(this.builder.toString());
            } else if (str2.equalsIgnoreCase(CATEGORY)) {
                this.currentArticle.mCategory = this.builder.toString();
            } else if (str2.equalsIgnoreCase("title")) {
                this.articles.add(this.currentArticle);
                this.currentArticle = null;
            }
        }
        this.builder.setLength(0);
    }

    public void startDocument() throws SAXException {
        super.startDocument();
        this.articles = new ArrayList();
        this.builder = new StringBuilder();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        super.startElement(str, str2, str3, attributes);
        if (str2.equalsIgnoreCase("title")) {
            this.currentArticle = new Article();
        }
    }
}
