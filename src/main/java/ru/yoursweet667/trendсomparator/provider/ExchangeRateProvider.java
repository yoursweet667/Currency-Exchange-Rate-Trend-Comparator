package ru.yoursweet667.trendсomparator.provider;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

public interface ExchangeRateProvider {

    double getTodayRate(String currency);

    double getYesterdayRate(String currency);
}
