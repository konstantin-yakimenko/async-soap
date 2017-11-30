package ru.jakimenko.migration.async;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

public class TestAsyncHandler implements AsyncHandler<GetCountryResponse> {
    private GetCountryResponse response;
    @Override
    public void handleResponse(Response<GetCountryResponse> res) {
        try {
            response = res.get();
            System.out.println("---> Res availible yet!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Country getResponse() {
        return response.getCountry();
    }
}
