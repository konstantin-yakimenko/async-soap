package ru.jakimenko.migration.sync;

import com.google.common.collect.Lists;
import ru.jakimenko.migration.PpdClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class SyncExecutor {

    public void execute(final PpdClient ppdClient) {
        long start = System.currentTimeMillis();
        List<String> params = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            params.add(getCountryName());
        }
        List<List<String>> parts = Lists.partition(params, 10);
        List<Future<Integer>> res = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(60);
        for (List<String> part : parts) {
            res.add(executorService.submit(new Task(ppdClient, part)));
        }
        Integer sum = 0;
        for (int i = 0; i < res.size(); i++) {
            try {
                sum += res.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("sum = " + sum);
        long finish = System.currentTimeMillis();
        long time = finish - start;
        String timeres = durationTimeToString(time);
        System.out.println("timeres = " + timeres);
    }

    public static String durationTimeToString(final long millis) {
        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public static String getCountryName() {
        int n = new Random().nextInt(3);
        switch (n) {
            case 0: return "Spain";
            case 1: return "Poland";
            case 2: return "United Kingdom";
            default: return "Spain";
        }
    }

//        MyService_Service service = new MyService_Service();
//        MyService port = service.getMyServicePort();
//        GetCountryRequest request = new GetCountryRequest();
//        request.setName("Spain");
//        GetCountryResponse response = port.getCountry(request);
//        System.out.println("population ---> " + response.getCountry().getPopulation());

}
