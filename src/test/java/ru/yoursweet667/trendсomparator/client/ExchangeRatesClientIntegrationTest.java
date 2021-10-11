package ru.yoursweet667.trendсomparator.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yoursweet667.trendсomparator.client.exchangerates.ExchangeRatesTodayTrendNegativeCondition;
import ru.yoursweet667.trendсomparator.client.exchangerates.ExchangeRatesTodayTrendPositiveCondition;
import ru.yoursweet667.trendсomparator.client.exchangerates.ExchangeRatesYesterdayTrendNegativeCondition;
import ru.yoursweet667.trendсomparator.client.exchangerates.ExchangeRatesYesterdayTrendPositiveCondition;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
class ExchangeRatesClientIntegrationTest {

    @Autowired
    private WireMockServer mockBooksService;

    @Autowired
    private OpenExchangeRatesClient ratesClient;

    @BeforeEach
    void setUp() throws IOException {
        ExchangeRatesTodayTrendNegativeCondition.setupMockBooksResponse(mockBooksService);
        ExchangeRatesTodayTrendPositiveCondition.setupMockBooksResponse(mockBooksService);
        ExchangeRatesYesterdayTrendNegativeCondition.setupMockBooksResponse(mockBooksService);
        ExchangeRatesYesterdayTrendPositiveCondition.setupMockBooksResponse(mockBooksService);
    }

    @Test
    void getTodayTrends_sendValidCurrency_returnRatesResponse() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put("base", "USD");


        //when + then
        assertEquals(ratesClient.getTodayTrends(params).getRates().get("USD"), Double.valueOf("5.5"));
    }

    @Test
    void getTodayTrends_sendInvalidCurrency_exception() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put("base", "qwe");

        //when + then
        assertNull(ratesClient.getTodayTrends(params));
    }

    @Test
    void getYesterdayTrends_sendValidCurrency_returnRatesResponse() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put("base", "USD");
        String date = "2020-01-01";

        //when + then
        assertEquals(ratesClient.getYesterdayTrends(params, date).getRates().get("USD"), Double.valueOf("5.5"));
    }

    @Test
    void getYesterdayTrends_sendInvalidCurrency_exception() {
        //given
        Map<String, String> params = new LinkedHashMap<>();
        params.put("base", "qwe");
        String date = "2020-01-01";

        //when + then
        assertNull(ratesClient.getYesterdayTrends(params, date));
    }
}