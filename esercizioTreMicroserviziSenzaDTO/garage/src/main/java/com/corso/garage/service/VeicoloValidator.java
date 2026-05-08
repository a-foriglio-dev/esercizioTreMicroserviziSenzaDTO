package com.corso.garage.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class VeicoloValidator {
    private final RestClient bikesClient;
    private final RestClient automobiliClient;

    // @Qualifier: Spring capisce quali bean deve iniettare. I bean sono stati definiti in RestClientConfig
    public VeicoloValidator(@Qualifier("bikesClient") RestClient bikesClient,
                            @Qualifier("automobiliClient") RestClient automobiliClient) {
        this.bikesClient = bikesClient;
        this.automobiliClient = automobiliClient;
    }

    // exists controlla se un veicolo esiste nei servizi esterni
    public boolean exists(String type, Long idEsterno){
        RestClient client = switch (type.toUpperCase()) {
             case "BIKE" -> bikesClient;
             case "AUTOMOBILE"  -> automobiliClient;
             default -> throw new IllegalArgumentException("Unknown type: " + type);
       };
         // Creazione del path e dell'endpoint REST
         String path = type.equalsIgnoreCase("BIKE") ? "/bikes/" : "/automobili/";
         try {
             // retrieve() esegue la chiamata HTTP
             // .toBodiesEntity() non considera il body della risposta
             client.get().uri(path + idEsterno).retrieve().toBodilessEntity();
             return true; // HTTP 200 ok
         } catch (HttpClientErrorException.NotFound e) {
             return false; // 404 NOT FOUND
        }
    }

}
