package com.corso.garage.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

// RestClientConfig configura due client HTTP uno per il servizio bikes e l'altro per il servizio automobili


@Configuration // la classe contiene configurazioni e bean da creare automaticamente
public class RestClientConfig {
    // @Value prende un valore da application.properties o da application.yml
    // Esempio: services.bikes.url=http://localhost:8081
    @Value("${services.bikes.url}")
    private String bikesUrl;

    // Esempio: services.automobili.url=http://localhost:8082
    @Value("${services.automobili.url}")
    private String automobiliUrl;


    // @Bean: crea un oggetto e lo salva nel container Spring
    @Bean(name = "bikesClient")
    public RestClient bikesClient() {
        // baseUrl() importa l'Url base del servizio
        // Esempio: http://localhost:8081 e dopo si può fare client.get().uri("/bikes/1")
        // http://localhost:8081/bikes/1
        // build() crea il client finale
        return RestClient.builder().baseUrl(bikesUrl).build();
    }

    @Bean(name = "automobiliClient")
    public RestClient automobiliClient() {
        return RestClient.builder().baseUrl(automobiliUrl).build();
    }



}
