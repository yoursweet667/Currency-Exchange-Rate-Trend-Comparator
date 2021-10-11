package ru.yoursweet667.trendсomparator.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yoursweet667.trendсomparator.client.GiphyClient;
import ru.yoursweet667.trendсomparator.client.model.Gif;

import java.util.List;
import java.util.Random;

@Service
public class TrendGifUrlProviderImpl implements TrendGifUrlProvider {

    private final GiphyClient giphyClient;
    private final Random random = new Random();

    @Autowired
    public TrendGifUrlProviderImpl(GiphyClient giphyClient) {
        this.giphyClient = giphyClient;
    }

    @Override
    public String getPositiveTrendGifUrl() {

        List<Gif> gifs = giphyClient.getGifTrendGrow().getData();
        int randomNumber = random.nextInt(gifs.size());
        return gifs.get(randomNumber).getUrl();
    }

    @Override
    public String getNegativeTrendGifUrl() {

        List<Gif> gifs = giphyClient.getGifTrendDecrease().getData();
        int randomNumber = random.nextInt(gifs.size());
        return gifs.get(randomNumber).getUrl();
    }
}