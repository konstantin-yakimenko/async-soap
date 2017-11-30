package ru.jakimenko.migration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class PpdConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("ppd.wsdl");
        return marshaller;
    }

    @Bean
    public PpdClient ppdClient(Jaxb2Marshaller marshaller) {
            PpdClient client = new PpdClient();
            client.setDefaultUri("http://localhost:8080/ppd2/MyService");
            client.setMarshaller(marshaller);
            client.setUnmarshaller(marshaller);
            return client;
    }
}
