package ru.yoursweet667.trendсomparator.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.yoursweet667.trendсomparator.exception.RequestedCurrencyNotSupportedException;
import ru.yoursweet667.trendсomparator.service.TrendService;
import ru.yoursweet667.trendсomparator.web.controller.TrendController;

import java.util.Optional;

@WebMvcTest(TrendController.class)
public class TrendControllerTest {

    private static final String VALID_CURRENCY = "USD";
    private static final String INVALID_CURRENCY = "INVALID_CURRENCY";
    private static final String GIF_URL = "testGifUrl";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TrendService trendService;

    @Test
    void compareTrend_sendInvalidTrend_exception() throws Exception {
        //given
        when(trendService.getTrendGifUrl(VALID_CURRENCY)).thenThrow(RequestedCurrencyNotSupportedException.class);

        //when + then
        this.mockMvc.perform(get("/trend?currency=" + INVALID_CURRENCY))
                .andExpect(status().isNoContent());
    }

    @Test
    void compareTrend_sendValidTrend_returnGifUrl() throws Exception {
        //given
        when(trendService.getTrendGifUrl(VALID_CURRENCY)).thenReturn(GIF_URL);

        //when + then
        this.mockMvc.perform(get("/trend?currency=" + VALID_CURRENCY))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(GIF_URL)));
    }
}