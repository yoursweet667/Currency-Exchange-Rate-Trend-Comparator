package ru.yoursweet667.trendсomparator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yoursweet667.trendсomparator.exception.RequestedCurrencyNotSupportedException;
import ru.yoursweet667.trendсomparator.provider.ExchangeRateProvider;
import ru.yoursweet667.trendсomparator.provider.TrendGifUrlProvider;

@Service
public class TrendServiceImpl implements TrendService {

    private final ExchangeRateProvider exchangeRateProvider;
    private final TrendGifUrlProvider gifUrlProvider;

    @Autowired
    public TrendServiceImpl(ExchangeRateProvider exchangeRateProvider, TrendGifUrlProvider gifUrlProvider) {
        this.exchangeRateProvider = exchangeRateProvider;
        this.gifUrlProvider = gifUrlProvider;
    }

    @Override
    public String getTrendGifUrl(String currency) throws RequestedCurrencyNotSupportedException {

        double todayRate = exchangeRateProvider.getTodayRate(currency);

        double yesterdayRate = exchangeRateProvider.getYesterdayRate(currency);

        String gifUrl = null;
        if (todayRate > yesterdayRate) {
            gifUrl = gifUrlProvider.getPositiveTrendGifUrl();

        } else if (todayRate < yesterdayRate) {
            gifUrl = gifUrlProvider.getNegativeTrendGifUrl();
        }
        return gifUrl;
    }
}