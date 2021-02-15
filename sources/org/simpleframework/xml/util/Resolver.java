package org.simpleframework.xml.util;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.util.Match;

public class Resolver<M extends Match> extends AbstractSet<M> {
    protected final Resolver<M>.Cache cache = new Cache();
    protected final Resolver<M>.Stack stack = new Stack();

    public M resolve(String str) {
        List list = (List) this.cache.get(str);
        if (list == null) {
            list = resolveAll(str);
        }
        if (list.isEmpty()) {
            return null;
        }
        return (Match) list.get(0);
    }

    public List<M> resolveAll(String str) {
        List<M> list = (List) this.cache.get(str);
        if (list != null) {
            return list;
        }
        char[] charArray = str.toCharArray();
        if (charArray == null) {
            return null;
        }
        return resolveAll(str, charArray);
    }

    private List<M> resolveAll(String str, char[] cArr) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.stack.iterator();
        while (it.hasNext()) {
            Match match = (Match) it.next();
            if (match(cArr, match.getPattern().toCharArray())) {
                this.cache.put(str, arrayList);
                arrayList.add(match);
            }
        }
        return arrayList;
    }

    public boolean add(M m) {
        this.stack.push(m);
        return true;
    }

    public Iterator<M> iterator() {
        return this.stack.sequence();
    }

    public boolean remove(M m) {
        this.cache.clear();
        return this.stack.remove(m);
    }

    public int size() {
        return this.stack.size();
    }

    public void clear() {
        this.cache.clear();
        this.stack.clear();
    }

    private boolean match(char[] cArr, char[] cArr2) {
        return match(cArr, 0, cArr2, 0);
    }

    private boolean match(char[] cArr, int i, char[] cArr2, int i2) {
        while (i2 < cArr2.length && i < cArr.length) {
            if (cArr2[i2] == '*') {
                while (cArr2[i2] == '*') {
                    i2++;
                    if (i2 >= cArr2.length) {
                        return true;
                    }
                }
                if (cArr2[i2] == '?' && (i2 = i2 + 1) >= cArr2.length) {
                    return true;
                }
                while (i < cArr.length) {
                    if (cArr[i] == cArr2[i2] || cArr2[i2] == '?') {
                        if (cArr2[i2 - 1] == '?') {
                            break;
                        } else if (match(cArr, i, cArr2, i2)) {
                            return true;
                        }
                    }
                    i++;
                }
                if (cArr.length == i) {
                    return false;
                }
            }
            int i3 = i + 1;
            int i4 = i2 + 1;
            if (cArr[i] != cArr2[i2] && cArr2[i4 - 1] != '?') {
                return false;
            }
            i = i3;
            i2 = i4;
        }
        if (cArr2.length != i2) {
            while (cArr2[i2] == '*') {
                i2++;
                if (i2 >= cArr2.length) {
                    return true;
                }
            }
            return false;
        } else if (cArr.length == i) {
            return true;
        } else {
            return false;
        }
    }

    private class Cache extends LimitedCache<List<M>> {
        public Cache() {
            super(1024);
        }
    }

    private class Stack extends LinkedList<M> {
        private Stack() {
        }

        public void push(M m) {
            Resolver.this.cache.clear();
            addFirst(m);
        }

        public void purge(int i) {
            Resolver.this.cache.clear();
            remove(i);
        }

        public Iterator<M> sequence() {
            return new Sequence();
        }

        private class Sequence implements Iterator<M> {
            private int cursor;

            public Sequence() {
                this.cursor = Stack.this.size();
            }

            public M next() {
                if (!hasNext()) {
                    return null;
                }
                Stack stack = Stack.this;
                int i = this.cursor - 1;
                this.cursor = i;
                return (Match) stack.get(i);
            }

            public boolean hasNext() {
                return this.cursor > 0;
            }

            public void remove() {
                Stack.this.purge(this.cursor);
            }
        }
    }
}
