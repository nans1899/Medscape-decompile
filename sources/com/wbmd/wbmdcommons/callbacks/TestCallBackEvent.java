package com.wbmd.wbmdcommons.callbacks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class TestCallBackEvent<T, E> implements ICallbackEvent<T, E> {
    private CountDownLatch latch;
    public List<T> onCompleted = new ArrayList();
    public List<E> onErrors = new ArrayList();

    public TestCallBackEvent(CountDownLatch countDownLatch) {
        this.latch = countDownLatch;
    }

    public void onError(E e) {
        this.onErrors.add(e);
        CountDownLatch countDownLatch = this.latch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }

    public void onCompleted(T t) {
        this.onCompleted.add(t);
        CountDownLatch countDownLatch = this.latch;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
}
