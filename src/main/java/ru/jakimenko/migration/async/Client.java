package ru.jakimenko.migration.async;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.MyService;
import io.spring.guides.gs_producing_web_service.MyService_Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

public class Client {

    final MyService port;

    public Client() {
        MyService_Service service = new MyService_Service();
        service.setExecutor(Executors.newFixedThreadPool(150));
        this.port = service.getMyServicePort();
    }

    public void send(final String name, BlockingQueue<Country> queue) {
        CountryHandler handler = new CountryHandler(queue);
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        port.getCountryAsync(request, handler);
    }
}
