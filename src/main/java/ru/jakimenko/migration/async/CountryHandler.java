package ru.jakimenko.migration.async;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import lombok.extern.slf4j.Slf4j;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class CountryHandler implements AsyncHandler<GetCountryResponse> {
    final private BlockingQueue<Country> queue;

    public CountryHandler(BlockingQueue<Country> queue) {
        this.queue = queue;
    }

    @Override
    public void handleResponse(Response<GetCountryResponse> res) {
        try {
            queue.add(res.get().getCountry());
        } catch (Exception ex) {
            log.error("Error: ", ex);
        }
    }
}
