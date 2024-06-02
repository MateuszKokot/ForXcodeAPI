package com.xcode.xCodeAPI.service;

import com.xcode.xCodeAPI.Properties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NBPAPIService implements NBPAPIInterface {

    // FIELDS
    RestTemplate restTemplate;
    Properties properties;

    //CONSTRUCTORS
    public NBPAPIService(RestTemplate restTemplate, Properties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    //METHODS
    @Override
    public String getCurrentCurrenciesValues() {
        return restTemplate.getForObject(properties.getNbpCurrenciesTableAURI(), String.class);
    }
}
