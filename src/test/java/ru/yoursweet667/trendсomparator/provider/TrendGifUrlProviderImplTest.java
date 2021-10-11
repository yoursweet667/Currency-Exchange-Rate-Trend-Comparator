package ru.yoursweet667.trendсomparator.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.trendсomparator.client.GiphyClient;
import ru.yoursweet667.trendсomparator.client.model.Gif;
import ru.yoursweet667.trendсomparator.client.model.GiphyResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TrendGifUrlProviderImplTest {

    private final static String POSITIVE_URL = "POSITIVE_URL";
    private final static String NEGATIVE_URL = "NEGATIVE_URL";

    @Mock
    GiphyClient giphyClient;

    @InjectMocks
    TrendGifUrlProviderImpl gifUrlProvider;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPositiveTrendGifUrl_returnPositiveUrl(){
        //given
        Gif gif = new Gif(POSITIVE_URL);
        List<Gif> gifs = new ArrayList<>();
        gifs.add(gif);
        GiphyResponse giphyResponse = new GiphyResponse(gifs);
        Mockito.when(giphyClient.getGifTrendGrow()).thenReturn(giphyResponse);

        //when
        String returnedGifUrl = gifUrlProvider.getPositiveTrendGifUrl();

        //then
        assertThat(returnedGifUrl).isEqualTo(POSITIVE_URL);

    }

    @Test
    void getNegativeTrendGifUrl_returnNegativeUrl(){
        //given
        Gif gif = new Gif(NEGATIVE_URL);
        List<Gif> gifs = new ArrayList<>();
        gifs.add(gif);
        GiphyResponse giphyResponse = new GiphyResponse(gifs);
        Mockito.when(giphyClient.getGifTrendDecrease()).thenReturn(giphyResponse);

        //when
        String returnedGifUrl = gifUrlProvider.getNegativeTrendGifUrl();

        //then
        assertThat(returnedGifUrl).isEqualTo(NEGATIVE_URL);
    }

}
