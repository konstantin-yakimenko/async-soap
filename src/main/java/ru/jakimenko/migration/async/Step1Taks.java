package ru.jakimenko.migration.async;

import io.spring.guides.gs_producing_web_service.Country;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Step1Taks implements Runnable {
    private final BlockingQueue<Country> queue;
    private final BlockingQueue<Country> queue2;
    public Step1Taks(BlockingQueue<Country> queue, BlockingQueue<Country> queue2) {
        this.queue = queue;
        this.queue2 = queue2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Country country = queue.poll(10000, TimeUnit.MINUTES);
                log.info("======================");
                log.info("res currenc = {}", country.getCurrency());
                log.info("res capital = {}", country.getCapital());
                log.info("res populat = {}", country.getPopulation());
                queue2.add(country);
            } catch (InterruptedException e) {
                log.error("Error: ", e);
            }
        }
    }
}
