package com.webmd.wbmdcmepulse.models.articles;

import java.util.HashMap;
import java.util.Map;

public class HtmlList {
    public static final String ITEM_TYPE_ORDERED_LIST = "ol";
    public static final String ITEM_TYPE_UNORDERED_LIST = "ul";
    private Map<Integer, HtmlListNode> listItems = new HashMap();
    public String listType;

    public void addListItem(HtmlListNode htmlListNode) {
        Map<Integer, HtmlListNode> map = this.listItems;
        map.put(Integer.valueOf(map.size()), htmlListNode);
    }

    public Map<Integer, HtmlListNode> getList() {
        return this.listItems;
    }

    public void setListType(String str) {
        this.listType = str;
    }

    public String getListType() {
        return this.listType;
    }
}
