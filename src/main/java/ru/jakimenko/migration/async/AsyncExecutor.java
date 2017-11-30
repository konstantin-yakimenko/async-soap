package ru.jakimenko.migration.async;

import io.spring.guides.gs_producing_web_service.Country;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncExecutor {

    private final Client client;

    public AsyncExecutor() {
        this.client = new Client();
    }

    private final LinkedBlockingQueue<Country> queue = new LinkedBlockingQueue<>(5_000);
    private final LinkedBlockingQueue<Country> queue2 = new LinkedBlockingQueue<>(5_000);
    private final LinkedBlockingQueue<Country> queue3 = new LinkedBlockingQueue<>(10_000);

    public void execute() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final AtomicInteger counter = new AtomicInteger(0);

        ExecutorService executorService = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Step1Taks(queue, queue2));
            executorService.submit(new Step2Taks(queue2, queue3, client));
            executorService.submit(new Step3Taks(queue3, counter));
        }

        for (int i = 0; i < 10_000; i++) {
            client.send(getCountryName(), queue);
        }


        while (true) {
            System.out.println("---> " + queue.size() + " | " + queue2.size() + " | " + queue3.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static String getCountryName() {
        int n = new Random().nextInt(3);
        switch (n) {
            case 0:
                return "Spain";
            case 1:
                return "Poland";
            case 2:
                return "United Kingdom";
            default:
                return "Spain";
        }
    }
}
