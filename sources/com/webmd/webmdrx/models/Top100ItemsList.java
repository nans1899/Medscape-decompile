package com.webmd.webmdrx.models;

import java.util.ArrayList;

public class Top100ItemsList<E> extends ArrayList<E> {
    int position = 0;

    public boolean add(E e) {
        if (size() != 100) {
            return super.add(e);
        }
        super.add(this.position, e);
        this.position = (this.position + 1) % 100;
        return true;
    }
}
