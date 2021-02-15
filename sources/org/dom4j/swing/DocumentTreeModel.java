package org.dom4j.swing;

import javax.swing.tree.DefaultTreeModel;
import org.dom4j.Document;

public class DocumentTreeModel extends DefaultTreeModel {
    protected Document document;

    public DocumentTreeModel(Document document2) {
        super(new BranchTreeNode(document2));
        this.document = document2;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document2) {
        this.document = document2;
        setRoot(new BranchTreeNode(document2));
    }
}
