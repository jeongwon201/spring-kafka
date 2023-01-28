package com.spring.kafka;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class MyTest {

    @Test
    void futureTest() {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("future: ok");
        });

        System.out.println("ok");
    }

    @Test
    public void test_supplyAsync_whenComplete() throws InterruptedException {

        CompletableFuture future = CompletableFuture.supplyAsync(() -> {

            System.out.println("on supplyAsync: " + Thread.currentThread().getName());
            return "ret";
        }).whenComplete((s, throwable) -> {
            System.out.println("on whenComplete: " + Thread.currentThread().getName());
            System.out.println(s);
        });

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1L);
        }
        System.out.println("outside: " + Thread.currentThread().getName());
    }
}
