package ru.yoursweet667.trendсomparator.provider;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yoursweet667.trendсomparator.client.OpenExchangeRatesClient;
import ru.yoursweet667.trendсomparator.exception.RequestedCurrencyNotSupportedException;
import ru.yoursweet667.trendсomparator.client.model.RatesResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ExchangeRateProviderImpl implements ExchangeRateProvider {

    private final OpenExchangeRatesClient exchangeRatesClient;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public ExchangeRateProviderImpl(OpenExchangeRatesClient exchangeRatesClient) {
        this.exchangeRatesClient = exchangeRatesClient;
    }

    private Map<String, String> exchangeRatesParams = new LinkedHashMap<>();

    @Override
    public double getTodayRate(String currency) {

        exchangeRatesParams.put("base", currency);
        RatesResponse todayTrend;
        try {
            todayTrend = exchangeRatesClient.getTodayTrends(exchangeRatesParams);
        } catch (FeignException e) {
            throw new RequestedCurrencyNotSupportedException("Something went wrong");
        }

        return todayTrend.getRates().get("RUB");
    }

    @Override
    public double getYesterdayRate(String currency) {

        exchangeRatesParams.put("base", currency);

        LocalDate date = LocalDate.now().minusDays(1);
        RatesResponse yesterdayTrend;
        try {
            yesterdayTrend = exchangeRatesClient.getYesterdayTrends(exchangeRatesParams, dateTimeFormatter.format(date));
        } catch (FeignException e) {
            throw new RequestedCurrencyNotSupportedException("Something went wrong");
        }

        return yesterdayTrend.getRates().get("RUB");
    }
}
