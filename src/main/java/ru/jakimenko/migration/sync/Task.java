package ru.jakimenko.migration.sync;

import lombok.extern.slf4j.Slf4j;
import ppd.wsdl.GetCountryResponse;
import ru.jakimenko.migration.PpdClient;

import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
public class Task implements Callable<Integer> {
    private final PpdClient ppdClient;
    private final List<String> input;
    public Task(PpdClient ppdClient, List<String> input) {
        this.ppdClient = ppdClient;
        this.input = input;
    }
    @Override
    public Integer call() throws Exception {
        for (String s : input) {
            GetCountryResponse response = ppdClient.getCountry(s);
            log.info("======================");
            log.info("res currenc = {}", response.getCountry().getCurrency());
            log.info("res capital = {}", response.getCountry().getCapital());
            log.info("res populat = {}", response.getCountry().getPopulation());
        }
        for (int i = 0; i < 10_000; i++) {
            int a = 10;
            int b = 10+a+a*a;
        }
        for (String s : input) {
            GetCountryResponse response = ppdClient.getCountry(s);
            log.info("======================");
            log.info("res currenc = {}", response.getCountry().getCurrency());
            log.info("res capital = {}", response.getCountry().getCapital());
            log.info("res populat = {}", response.getCountry().getPopulation());
        }
        return 0;
    }
}
