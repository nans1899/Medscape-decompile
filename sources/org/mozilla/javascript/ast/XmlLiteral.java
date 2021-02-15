package org.mozilla.javascript.ast;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.ArrayList;
import java.util.List;

public class XmlLiteral extends AstNode {
    private List<XmlFragment> fragments = new ArrayList();

    public XmlLiteral() {
        this.type = 145;
    }

    public XmlLiteral(int i) {
        super(i);
        this.type = 145;
    }

    public XmlLiteral(int i, int i2) {
        super(i, i2);
        this.type = 145;
    }

    public List<XmlFragment> getFragments() {
        return this.fragments;
    }

    public void setFragments(List<XmlFragment> list) {
        assertNotNull(list);
        this.fragments.clear();
        for (XmlFragment addFragment : list) {
            addFragment(addFragment);
        }
    }

    public void addFragment(XmlFragment xmlFragment) {
        assertNotNull(xmlFragment);
        this.fragments.add(xmlFragment);
        xmlFragment.setParent(this);
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        for (XmlFragment source : this.fragments) {
            sb.append(source.toSource(0));
        }
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        if (nodeVisitor.visit(this)) {
            for (XmlFragment visit : this.fragments) {
                visit.visit(nodeVisitor);
            }
        }
    }
}
