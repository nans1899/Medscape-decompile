package org.dom4j.tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;

public class BackedList extends ArrayList {
    private AbstractBranch branch;
    private List branchContent;

    public BackedList(AbstractBranch abstractBranch, List list) {
        this(abstractBranch, list, list.size());
    }

    public BackedList(AbstractBranch abstractBranch, List list, int i) {
        super(i);
        this.branch = abstractBranch;
        this.branchContent = list;
    }

    public BackedList(AbstractBranch abstractBranch, List list, List list2) {
        super(list2);
        this.branch = abstractBranch;
        this.branchContent = list;
    }

    public boolean add(Object obj) {
        this.branch.addNode(asNode(obj));
        return super.add(obj);
    }

    public void add(int i, Object obj) {
        int i2;
        int size = size();
        if (i < 0) {
            StringBuffer stringBuffer = new StringBuffer("Index value: ");
            stringBuffer.append(i);
            stringBuffer.append(" is less than zero");
            throw new IndexOutOfBoundsException(stringBuffer.toString());
        } else if (i <= size) {
            if (size == 0) {
                i2 = this.branchContent.size();
            } else if (i < size) {
                i2 = this.branchContent.indexOf(get(i));
            } else {
                i2 = this.branchContent.indexOf(get(size - 1)) + 1;
            }
            this.branch.addNode(i2, asNode(obj));
            super.add(i, obj);
        } else {
            StringBuffer stringBuffer2 = new StringBuffer("Index value: ");
            stringBuffer2.append(i);
            stringBuffer2.append(" cannot be greater than ");
            stringBuffer2.append("the size: ");
            stringBuffer2.append(size);
            throw new IndexOutOfBoundsException(stringBuffer2.toString());
        }
    }

    public Object set(int i, Object obj) {
        int indexOf = this.branchContent.indexOf(get(i));
        if (indexOf < 0) {
            indexOf = i == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (indexOf < this.branchContent.size()) {
            this.branch.removeNode(asNode(get(i)));
            this.branch.addNode(indexOf, asNode(obj));
        } else {
            this.branch.removeNode(asNode(get(i)));
            this.branch.addNode(asNode(obj));
        }
        this.branch.childAdded(asNode(obj));
        return super.set(i, obj);
    }

    public boolean remove(Object obj) {
        this.branch.removeNode(asNode(obj));
        return super.remove(obj);
    }

    public Object remove(int i) {
        Object remove = super.remove(i);
        if (remove != null) {
            this.branch.removeNode(asNode(remove));
        }
        return remove;
    }

    public boolean addAll(Collection collection) {
        ensureCapacity(size() + collection.size());
        int size = size();
        for (Object add : collection) {
            add(add);
            size--;
        }
        return size != 0;
    }

    public boolean addAll(int i, Collection collection) {
        ensureCapacity(size() + collection.size());
        int size = size();
        for (Object add : collection) {
            add(i, add);
            size--;
            i++;
        }
        return size != 0;
    }

    public void clear() {
        Iterator it = iterator();
        while (it.hasNext()) {
            Object next = it.next();
            this.branchContent.remove(next);
            this.branch.childRemoved(asNode(next));
        }
        super.clear();
    }

    public void addLocal(Object obj) {
        super.add(obj);
    }

    /* access modifiers changed from: protected */
    public Node asNode(Object obj) {
        if (obj instanceof Node) {
            return (Node) obj;
        }
        StringBuffer stringBuffer = new StringBuffer("This list must contain instances of Node. Invalid type: ");
        stringBuffer.append(obj);
        throw new IllegalAddException(stringBuffer.toString());
    }
}
