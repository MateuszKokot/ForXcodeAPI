package com.xcode.xCodeAPI;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("properties")
public class Properties {
    private String nbpCurrenciesTableAURI;

    public String getNbpCurrenciesTableAURI() {
        return nbpCurrenciesTableAURI;
    }

    public void setNbpCurrenciesTableAURI(String nbpCurrenciesTableAURI) {
        this.nbpCurrenciesTableAURI = nbpCurrenciesTableAURI;
    }
}
