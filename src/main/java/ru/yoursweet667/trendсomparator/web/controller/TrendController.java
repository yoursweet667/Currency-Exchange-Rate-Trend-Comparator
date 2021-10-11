package ru.yoursweet667.trendсomparator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yoursweet667.trendсomparator.service.TrendService;

@RestController
public class TrendController {

    private final TrendService trendService;

    @Autowired
    public TrendController(TrendService trendService) {
        this.trendService = trendService;
    }

    @RequestMapping("/trend")
    public Object compareTrend(@RequestParam String currency) {

        String gifUrl = trendService.getTrendGifUrl(currency);
        if (null == gifUrl) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return gifUrl;
    }
}