package com.will.shun;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for(int i=0; i<10; i++) {
            executor.execute(new Worker(startLatch, latch, "worker" + i));
        }

        System.out.println("Driver is here.");

        startLatch.countDown();

        System.out.println("Workers get to work.");

        latch.await();

        System.out.println("Driver is ready to go.");

        executor.shutdown();
    }
}
