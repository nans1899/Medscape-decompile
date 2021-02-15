package com.webmd.webmdrx.models.testing;

public class FakeClass {
    String name;

    public boolean doSomething() {
        return false;
    }

    public int returnFive() {
        return 5;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
