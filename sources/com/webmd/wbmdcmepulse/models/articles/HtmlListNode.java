package com.webmd.wbmdcmepulse.models.articles;

public class HtmlListNode<T> {
    public static final String ITEM_TYPE_LIST_ITEM = "li";
    public static final String ITEM_TYPE_ORDERED_LIST = "ol";
    public static final String ITEM_TYPE_UNORDERED_LIST = "ul";
    public String type;
    public T value;
}
