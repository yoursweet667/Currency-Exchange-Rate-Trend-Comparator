package ru.yoursweet667.trendсomparator.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyResponse {

    private final List<Gif> data;

    @JsonCreator
    public GiphyResponse(@JsonProperty("data") List<Gif> data) {
        this.data = data;
    }

    public List<Gif> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiphyResponse that = (GiphyResponse) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    @Override
    public String toString() {
        return "GiphyResponse{" +
                "gifs=" + data +
                '}';
    }
}
