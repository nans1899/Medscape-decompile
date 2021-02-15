package org.simpleframework.xml.filter;

import java.util.Stack;

public class StackFilter implements Filter {
    private Stack<Filter> stack = new Stack<>();

    public void push(Filter filter) {
        this.stack.push(filter);
    }

    public String replace(String str) {
        String replace;
        int size = this.stack.size();
        do {
            size--;
            if (size < 0) {
                return null;
            }
            replace = ((Filter) this.stack.get(size)).replace(str);
        } while (replace == null);
        return replace;
    }
}
