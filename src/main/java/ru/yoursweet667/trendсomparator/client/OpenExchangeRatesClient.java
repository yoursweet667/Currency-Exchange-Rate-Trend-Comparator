package ru.yoursweet667.trendсomparator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yoursweet667.trendсomparator.client.model.RatesResponse;

import java.util.Map;

@FeignClient(url="${rates.service.url}", name = "ExchangeRatesClient")
public interface OpenExchangeRatesClient {

    @GetMapping(value = "/api/latest.json?app_id=1c7d2da6c50d45f581e57d49856e5a2e&{params}")
    RatesResponse getTodayTrends(@SpringQueryMap Map<String, String> params);

    @GetMapping(value = "/api/historical/{date}.json?app_id=1c7d2da6c50d45f581e57d49856e5a2e&{params}")
    RatesResponse getYesterdayTrends(@SpringQueryMap Map<String, String> params, @PathVariable("date") String date);
}