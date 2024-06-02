package com.xcode.xCodeAPI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class NBPAPIServiceTest {


    RestTemplate restTemplate;
    @Mock
    Properties properties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCurrentCurrenciesValues_ReturnsResponse() {
        // Given
        restTemplate = new RestTemplate();

        when(properties.getNbpCurrenciesTableAURI()).thenReturn("https://api.nbp.pl/api/exchangerates/tables/A?format=json");

        // When
        String actualResponse = restTemplate.getForObject(properties.getNbpCurrenciesTableAURI(), String.class);

        // Then
        assertNotNull(actualResponse);
    }

}
