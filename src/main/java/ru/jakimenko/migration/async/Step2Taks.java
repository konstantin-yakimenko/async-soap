package ru.jakimenko.migration.async;

import io.spring.guides.gs_producing_web_service.Country;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Step2Taks implements Runnable {
    private final BlockingQueue<Country> queue2;
    private final BlockingQueue<Country> queue3;
    private final Client client;

    public Step2Taks(BlockingQueue<Country> queue2, BlockingQueue<Country> queue3, Client client) {
        this.queue2 = queue2;
        this.queue3 = queue3;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.info("task 2");
                Country country = queue2.poll(1000, TimeUnit.MINUTES);
                for (int i = 0; i < 10_000; i++) {
                    int a = 10;
                    int b = 10+a+a*a;
                }
                client.send(country.getName(), queue3);
            } catch (InterruptedException e) {
                log.error("Error: ", e);
            }
        }
    }
}
