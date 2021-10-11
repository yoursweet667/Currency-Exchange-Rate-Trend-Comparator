package ru.yoursweet667.trendсomparator.provider;

import feign.FeignException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.trendсomparator.client.OpenExchangeRatesClient;
import ru.yoursweet667.trendсomparator.exception.RequestedCurrencyNotSupportedException;
import ru.yoursweet667.trendсomparator.client.model.RatesResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ExchangeRateProviderImplTest {

    private static final String VALID_CURRENCY = "USD";
    private static final String EXTRA_VALID_CURRENCY = "RUB";
    private static final String INVALID_CURRENCY = "INVALID_CURRENCY";
    private static final double RATE = 10;

    @Mock
    OpenExchangeRatesClient exchangeRatesClient;

    @InjectMocks
    ExchangeRateProviderImpl rateProvider;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getTodayRate_sendValidCurrency_returnTodayTrend(){
        Map<String, Double> ratesResponseMap = new LinkedHashMap<>();
        ratesResponseMap.put(EXTRA_VALID_CURRENCY, RATE);
        RatesResponse ratesResponse = new RatesResponse(ratesResponseMap);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("base", VALID_CURRENCY);
        Mockito.when(exchangeRatesClient.getTodayTrends(params)).thenReturn(ratesResponse);

        //when
        double returnedRate = rateProvider.getTodayRate(VALID_CURRENCY);

        //then
        assertThat(returnedRate).isEqualTo(RATE);
    }

    @Test
    void getTodayRate_sendInvalidCurrency_exception(){
        //given + when
        Map<String, String> params = new LinkedHashMap<>();
        params.put("base", INVALID_CURRENCY);
        Mockito.when(exchangeRatesClient.getTodayTrends(params)).thenThrow(FeignException.class);

        //then
        Assertions.assertThatThrownBy(() -> rateProvider.getTodayRate(INVALID_CURRENCY))
                .isInstanceOf(RequestedCurrencyNotSupportedException.class);
    }

    @Test
    void getYesterdayRate_sendValidCurrency_returnYesterdayTrend(){
        Map<String, Double> ratesResponseMap = new LinkedHashMap<>();
        ratesResponseMap.put(EXTRA_VALID_CURRENCY, RATE);
        RatesResponse ratesResponse = new RatesResponse(ratesResponseMap);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("base", VALID_CURRENCY);

        LocalDate date = LocalDate.now().minusDays(1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Mockito.when(exchangeRatesClient.getYesterdayTrends(params, dateTimeFormatter.format(date))).thenReturn(ratesResponse);

        //when
        double returnedRate = rateProvider.getYesterdayRate(VALID_CURRENCY);

        //then
        assertThat(returnedRate).isEqualTo(RATE);
    }

    @Test
    void getYesterdayRate_sendInvalidCurrency_exception(){
        //given + when
        Map<String, String> params = new LinkedHashMap<>();
        params.put("base", INVALID_CURRENCY);
        LocalDate date = LocalDate.now().minusDays(1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Mockito.when(exchangeRatesClient.getYesterdayTrends(params, dateTimeFormatter.format(date))).thenThrow(FeignException.class);

        //then
        Assertions.assertThatThrownBy(() -> rateProvider.getYesterdayRate(INVALID_CURRENCY))
                .isInstanceOf(RequestedCurrencyNotSupportedException.class);
    }
}
