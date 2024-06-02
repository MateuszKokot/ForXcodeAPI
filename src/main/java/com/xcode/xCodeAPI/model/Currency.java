package com.xcode.xCodeAPI.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Currency {

    //
    List<Currency> currencies;
    private String currency;
    private String code;
    private BigDecimal mid;
    ObjectMapper objectMapper;

    //
    public Currency() {
        this.currencies = new ArrayList<>();
        this.objectMapper = new ObjectMapper();
    }

    //
    public void parseJsonResponse(String jsonResponse) {
        try {
            CurrencyResponse[] currencyResponses = objectMapper.readValue(jsonResponse, CurrencyResponse[].class);
            for (CurrencyResponse currencyResponse : currencyResponses) {
                currencies.addAll(Arrays.asList(currencyResponse.getRates()));
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    //
    public List<Currency> getCurrencies() {
        return currencies;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getMid() {
        return mid;
    }

    public void setMid(BigDecimal mid) {
        this.mid = mid;
    }

    //
    public static class CurrencyResponse {
        String table;
        String no;
        String effectiveDate;
        Currency[] rates;

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getEffectiveDate() {
            return effectiveDate;
        }

        public void setEffectiveDate(String effectiveDate) {
            this.effectiveDate = effectiveDate;
        }

        public Currency[] getRates() {
            return rates;
        }

        public void setRates(Currency[] rates) {
            this.rates = rates;
        }
    }
}
