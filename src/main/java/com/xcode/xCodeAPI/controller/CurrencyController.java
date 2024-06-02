package com.xcode.xCodeAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xcode.xCodeAPI.model.Currency;
import com.xcode.xCodeAPI.model.Request;
import com.xcode.xCodeAPI.model.RequestRepository;
import com.xcode.xCodeAPI.service.NBPAPIInterface;
import com.xcode.xCodeAPI.service.NBPAPIService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/currencies")
public class CurrencyController {

    //
    RequestRepository requestRepository;
    NBPAPIInterface nbpapiInterface;
    Currency currency;
    ObjectMapper objectMapper;

    //
    public CurrencyController(RequestRepository requestRepository,
                              NBPAPIService nbpapiService, Currency currency) {
        this.requestRepository = requestRepository;
        this.nbpapiInterface = nbpapiService;
        this.currency = currency;
        this.objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    //
    @PostMapping(value = "/get-current-currency-value-command", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> getCurrencies(@RequestBody RqstBody rqstBody) {

        currency.parseJsonResponse(nbpapiInterface.getCurrentCurrenciesValues());

        Optional<Currency> optional = currency.getCurrencies()
                .stream()
                .filter(curr -> curr.getCode().equals(rqstBody.getCurrency()))
                .findFirst();
        // Założyłem, że do BD zapisujemy tylko poprawne requesty.
        if (optional.isPresent()) {
            Currency currency = optional.get();
            Request request = new Request(currency.getCode(), currency.getMid(),
                    rqstBody.getName(), LocalDateTime.now()/*Można byłoby też zrobić CURRENT_TIMESTAMP na BD zamiast przekazywać z aplikacji LocalDateTime.now()*/);
            requestRepository.save(request);
            return ResponseEntity.ok("{\"value\": \"" + currency.getMid() + "\"}");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //
    @GetMapping(value = "/requests", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> getRequests() {
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(requestRepository.findAll());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(jsonResponse);
    }

    //
    public static class RqstBody {
        private String currency;
        private String name;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
