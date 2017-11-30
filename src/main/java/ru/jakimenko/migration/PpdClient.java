package ru.jakimenko.migration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import ppd.wsdl.GetCountryRequest;
import ppd.wsdl.GetCountryResponse;

@Slf4j
public class PpdClient extends WebServiceGatewaySupport {

    public GetCountryResponse getCountry(final String name) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        log.info("Requesting country for " + name);
        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(
                        request
                        , (WebServiceMessage message) -> {
                            ((SoapMessage) message).
                                    setSoapAction("http://localhost:8080/ppd2/MyService/getCountry");
                        });
        return response;
    }
}
