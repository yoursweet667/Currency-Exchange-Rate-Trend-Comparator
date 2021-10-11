package ru.yoursweet667.trendсomparator.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RatesResponse {

    private final Map<String, Double> rates;

    public RatesResponse(@JsonProperty("rates") Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RatesResponse that = (RatesResponse) o;
        return Objects.equals(rates, that.rates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rates);
    }

    @Override
    public String toString() {
        return "TrendResponse{" +
                "quoteAssets=" + rates +
                '}';
    }
}
