package com.webmd.webmdrx.models.testing;

import android.content.Context;

public class Calculator {
    private Context context;
    FakeClass fakeClass = new FakeClass();

    public int add(int i, int i2) {
        return i + i2;
    }

    public int mult(int i, int i2) {
        return i * i2;
    }

    public int sub(int i, int i2) {
        return i - i2;
    }

    public Calculator(Context context2) {
        this.context = context2;
    }

    public int div(int i, int i2) {
        return i / i2;
    }

    public int getFive() {
        return this.fakeClass.returnFive();
    }
}
