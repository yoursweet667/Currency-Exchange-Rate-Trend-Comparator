package ru.yoursweet667.trendсomparator.service;

import org.springframework.stereotype.Service;
import ru.yoursweet667.trendсomparator.exception.RequestedCurrencyNotSupportedException;

@Service
public interface TrendService {

    String getTrendGifUrl(String baseAsset) throws RequestedCurrencyNotSupportedException;

}
