package com.rq.example.mytest.basic;

import java.util.concurrent.CountDownLatch;

/**
 * 功能描述:
 *
 * @author: $
 * @date: $ $
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        MyCountDownLatch myCountDownLatch = new MyCountDownLatch(countDownLatch);


        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(myCountDownLatch).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }
}

class MyCountDownLatch implements Runnable {
    private CountDownLatch countDownLatch;

    public MyCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0)
                        System.out.println(i);
                }
            } finally {
                countDownLatch.countDown();
            }

        }
    }


}
