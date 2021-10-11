package ru.yoursweet667.trendсomparator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yoursweet667.trendсomparator.client.model.GiphyResponse;

@FeignClient(url="${giphy.service.url}", name = "GiphyClient")
public interface GiphyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/gifs/search?api_key=TXDdUM0TAHW1EwSk6M8Ml8m2NsbphXgc&q=rich&limit=50")
    GiphyResponse getGifTrendGrow();

    @RequestMapping(method = RequestMethod.GET, value = "/v1/gifs/search?api_key=TXDdUM0TAHW1EwSk6M8Ml8m2NsbphXgc&q=broke&limit=50")
    GiphyResponse getGifTrendDecrease();
}