package com.wbmd.qxcalculator.util.legacy;

import java.util.ArrayList;
import java.util.List;

public class ContentNode {
    private List<ContentNode> children;
    private ContentNode parent;

    public ContentNode getParent() {
        return this.parent;
    }

    public List<ContentNode> getChildren() {
        return this.children;
    }

    public void addChild(ContentNode contentNode) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(contentNode);
        contentNode.parent = this;
    }

    public void removeChild(ContentNode contentNode) {
        List<ContentNode> list = this.children;
        if (list != null) {
            list.remove(contentNode);
            contentNode.parent = null;
        }
    }

    public int depth() {
        ContentNode contentNode = this.parent;
        if (contentNode == null) {
            return 0;
        }
        return contentNode.depth() + 1;
    }
}
