package com.will.shun;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private String workerCode;

    private CountDownLatch startLatch;
    private CountDownLatch latch;

    Worker(CountDownLatch startLatch, CountDownLatch latch, String workerCode) {
        this.startLatch = startLatch;
        this.latch = latch;
        this.workerCode = workerCode;
    }

    public void run() {
        try {
            startLatch.await();
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        System.out.println("Worker " + workerCode + " is loading goods...");
    }
}
