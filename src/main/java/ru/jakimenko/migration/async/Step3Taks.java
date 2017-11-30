package ru.jakimenko.migration.async;

import io.spring.guides.gs_producing_web_service.Country;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Step3Taks implements Runnable {
    private final BlockingQueue<Country> queue3;
    private final AtomicInteger counter;
    public Step3Taks(BlockingQueue<Country> queue3, AtomicInteger counter) {
        this.queue3 = queue3;
        this.counter = counter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.info("task 3");
                Country country = queue3.poll(1000, TimeUnit.MINUTES);
                int i = counter.incrementAndGet();
                if (i>=5_000) {
                    System.exit(0);
                }
                log.info("======================");
                log.info("res currenc = {}", country.getCurrency());
                log.info("res capital = {}", country.getCapital());
                log.info("res populat = {}", country.getPopulation());
            } catch (InterruptedException e) {
                log.error("Error: ", e);
            }
        }
    }
}
