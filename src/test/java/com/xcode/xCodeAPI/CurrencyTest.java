package com.xcode.xCodeAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.xcode.xCodeAPI.model.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {

    private Currency currency;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        currency = new Currency();
        objectMapper = new ObjectMapper();
    }

    @Test
    void parseJsonResponse_ParsesValidJson() throws Exception {
        // Given
        String jsonResponse = "[{\"table\":\"A\",\"no\":\"105/A/NBP/2024\",\"effectiveDate\":\"2024-05-31\",\"rates\":[{\"currency\":\"bat (Tajlandia)\",\"code\":\"THB\",\"mid\":0.1070},{\"currency\":\"dolar amerykański\",\"code\":\"USD\",\"mid\":3.9389},{\"currency\":\"dolar australijski\",\"code\":\"AUD\",\"mid\":2.6148},{\"currency\":\"dolar Hongkongu\",\"code\":\"HKD\",\"mid\":0.5039},{\"currency\":\"dolar kanadyjski\",\"code\":\"CAD\",\"mid\":2.8839},{\"currency\":\"dolar nowozelandzki\",\"code\":\"NZD\",\"mid\":2.4137},{\"currency\":\"dolar singapurski\",\"code\":\"SGD\",\"mid\":2.9128},{\"currency\":\"euro\",\"code\":\"EUR\",\"mid\":4.2678},{\"currency\":\"forint (Węgry)\",\"code\":\"HUF\",\"mid\":0.010962},{\"currency\":\"frank szwajcarski\",\"code\":\"CHF\",\"mid\":4.3471},{\"currency\":\"funt szterling\",\"code\":\"GBP\",\"mid\":5.0056},{\"currency\":\"hrywna (Ukraina)\",\"code\":\"UAH\",\"mid\":0.0972},{\"currency\":\"jen (Japonia)\",\"code\":\"JPY\",\"mid\":0.025043},{\"currency\":\"korona czeska\",\"code\":\"CZK\",\"mid\":0.1725},{\"currency\":\"korona duńska\",\"code\":\"DKK\",\"mid\":0.5722},{\"currency\":\"korona islandzka\",\"code\":\"ISK\",\"mid\":0.028701},{\"currency\":\"korona norweska\",\"code\":\"NOK\",\"mid\":0.3741},{\"currency\":\"korona szwedzka\",\"code\":\"SEK\",\"mid\":0.3728},{\"currency\":\"lej rumuński\",\"code\":\"RON\",\"mid\":0.8576},{\"currency\":\"lew (Bułgaria)\",\"code\":\"BGN\",\"mid\":2.1821},{\"currency\":\"lira turecka\",\"code\":\"TRY\",\"mid\":0.1226},{\"currency\":\"nowy izraelski szekel\",\"code\":\"ILS\",\"mid\":1.0601},{\"currency\":\"peso chilijskie\",\"code\":\"CLP\",\"mid\":0.004289},{\"currency\":\"peso filipińskie\",\"code\":\"PHP\",\"mid\":0.0673},{\"currency\":\"peso meksykańskie\",\"code\":\"MXN\",\"mid\":0.2313},{\"currency\":\"rand (Republika Południowej Afryki)\",\"code\":\"ZAR\",\"mid\":0.2093},{\"currency\":\"real (Brazylia)\",\"code\":\"BRL\",\"mid\":0.7569},{\"currency\":\"ringgit (Malezja)\",\"code\":\"MYR\",\"mid\":0.8367},{\"currency\":\"rupia indonezyjska\",\"code\":\"IDR\",\"mid\":0.00024237},{\"currency\":\"rupia indyjska\",\"code\":\"INR\",\"mid\":0.047224},{\"currency\":\"won południowokoreański\",\"code\":\"KRW\",\"mid\":0.002847},{\"currency\":\"yuan renminbi (Chiny)\",\"code\":\"CNY\",\"mid\":0.5439},{\"currency\":\"SDR (MFW)\",\"code\":\"XDR\",\"mid\":5.2192}]}]";
        Currency testCurrency = new Currency();
        testCurrency.setCode("EUR");
        testCurrency.setCurrency("euro");
        testCurrency.setMid(new BigDecimal("4.2678"));
        Currency chosenCurrency = new Currency();

        // When
        currency.parseJsonResponse(jsonResponse);
        Optional<Currency> optionalCurrency
                = currency.getCurrencies()
                .stream()
                .filter(curr -> curr.getCode().equals("EUR"))
                .findFirst();

        if (optionalCurrency.isPresent()) {
            chosenCurrency = optionalCurrency.get();
        }

        // Then
        assertNotNull(currency.getCurrencies());
        assertEquals(testCurrency.getCode(), chosenCurrency.getCode());
        assertEquals(testCurrency.getCurrency(), chosenCurrency.getCurrency());
        assertEquals(testCurrency.getMid(), chosenCurrency.getMid());

    }

    @Test
    void parseJsonResponse_HandlesEmptyJson() throws Exception {
        // Given
        String jsonResponse = "[]";

        // When
        currency.parseJsonResponse(jsonResponse);

        // Then
        List<Currency> currencies = currency.getCurrencies();
        assertNotNull(currencies);
        assertEquals(0, currencies.size());
    }

}




