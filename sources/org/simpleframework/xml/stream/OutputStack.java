package org.simpleframework.xml.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

class OutputStack extends ArrayList<OutputNode> {
    private final Set active;

    public OutputStack(Set set) {
        this.active = set;
    }

    public OutputNode pop() {
        int size = size();
        if (size <= 0) {
            return null;
        }
        return purge(size - 1);
    }

    public OutputNode top() {
        int size = size();
        if (size <= 0) {
            return null;
        }
        return (OutputNode) get(size - 1);
    }

    public OutputNode bottom() {
        if (size() <= 0) {
            return null;
        }
        return (OutputNode) get(0);
    }

    public OutputNode push(OutputNode outputNode) {
        this.active.add(outputNode);
        add(outputNode);
        return outputNode;
    }

    public OutputNode purge(int i) {
        OutputNode outputNode = (OutputNode) remove(i);
        if (outputNode != null) {
            this.active.remove(outputNode);
        }
        return outputNode;
    }

    public Iterator<OutputNode> iterator() {
        return new Sequence();
    }

    private class Sequence implements Iterator<OutputNode> {
        private int cursor;

        public Sequence() {
            this.cursor = OutputStack.this.size();
        }

        public OutputNode next() {
            if (!hasNext()) {
                return null;
            }
            OutputStack outputStack = OutputStack.this;
            int i = this.cursor - 1;
            this.cursor = i;
            return (OutputNode) outputStack.get(i);
        }

        public boolean hasNext() {
            return this.cursor > 0;
        }

        public void remove() {
            OutputStack.this.purge(this.cursor);
        }
    }
}
