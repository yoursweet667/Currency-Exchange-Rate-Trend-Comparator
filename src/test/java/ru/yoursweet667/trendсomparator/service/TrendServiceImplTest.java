package ru.yoursweet667.trendсomparator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.yoursweet667.trendсomparator.exception.RequestedCurrencyNotSupportedException;
import ru.yoursweet667.trendсomparator.provider.ExchangeRateProvider;
import ru.yoursweet667.trendсomparator.provider.TrendGifUrlProvider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TrendServiceImplTest {

    private static final String VALID_CURRENCY = "USD";
    private static final String INVALID_CURRENCY = "INVALID_CURRENCY";
    private static final String GIF_URL = "testGifUrl";

    @Mock
    TrendGifUrlProvider gifUrlProvider;

    @Mock
    ExchangeRateProvider exchangeRateProvider;

    @InjectMocks
    TrendServiceImpl trendService;

    @BeforeEach
    private void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void compareGrowTrends_sendValidCurrency_getGifUrl() {
        //given
        Mockito.when(exchangeRateProvider.getTodayRate(VALID_CURRENCY)).thenReturn(10.0);
        Mockito.when(exchangeRateProvider.getYesterdayRate(VALID_CURRENCY)).thenReturn(5.0);
        Mockito.when(gifUrlProvider.getPositiveTrendGifUrl()).thenReturn(GIF_URL);

        //when
        String returnedGifUrl = trendService.getTrendGifUrl(VALID_CURRENCY);

        //then
        assertThat(GIF_URL).isEqualTo(returnedGifUrl);

    }

    @Test
    void compareDecreaseTrends_sendValidCurrency_getGifUrl() {
        //given
        Mockito.when(exchangeRateProvider.getTodayRate(VALID_CURRENCY)).thenReturn(5.0);
        Mockito.when(exchangeRateProvider.getYesterdayRate(VALID_CURRENCY)).thenReturn(10.0);
        Mockito.when(gifUrlProvider.getNegativeTrendGifUrl()).thenReturn(GIF_URL);

        //when
        String returnedGifUrl = trendService.getTrendGifUrl(VALID_CURRENCY);

        //then
        assertThat(GIF_URL).isEqualTo(returnedGifUrl);
    }

    @Test
    void compareTrends_sendInvalidCurrency_exception() {
        //given + when
        Mockito.when(exchangeRateProvider.getTodayRate(INVALID_CURRENCY)).thenThrow(RequestedCurrencyNotSupportedException.class);

        //then
        assertThatThrownBy(() -> trendService.getTrendGifUrl(INVALID_CURRENCY))
                .isInstanceOf(RequestedCurrencyNotSupportedException.class);
    }
}
