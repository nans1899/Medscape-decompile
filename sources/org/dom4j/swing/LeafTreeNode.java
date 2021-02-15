package org.dom4j.swing;

import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import org.dom4j.Node;

public class LeafTreeNode implements TreeNode {
    protected static final Enumeration EMPTY_ENUMERATION = new Enumeration() {
        public boolean hasMoreElements() {
            return false;
        }

        public Object nextElement() {
            return null;
        }
    };
    private TreeNode parent;
    protected Node xmlNode;

    public boolean getAllowsChildren() {
        return false;
    }

    public TreeNode getChildAt(int i) {
        return null;
    }

    public int getChildCount() {
        return 0;
    }

    public int getIndex(TreeNode treeNode) {
        return -1;
    }

    public boolean isLeaf() {
        return true;
    }

    public LeafTreeNode() {
    }

    public LeafTreeNode(Node node) {
        this.xmlNode = node;
    }

    public LeafTreeNode(TreeNode treeNode, Node node) {
        this.parent = treeNode;
        this.xmlNode = node;
    }

    public Enumeration children() {
        return EMPTY_ENUMERATION;
    }

    public TreeNode getParent() {
        return this.parent;
    }

    public String toString() {
        String text = this.xmlNode.getText();
        return text != null ? text.trim() : "";
    }

    public void setParent(LeafTreeNode leafTreeNode) {
        this.parent = leafTreeNode;
    }

    public Node getXmlNode() {
        return this.xmlNode;
    }
}
