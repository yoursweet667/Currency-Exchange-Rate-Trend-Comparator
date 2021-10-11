package ru.yoursweet667.trendсomparator.client;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.yoursweet667.trendсomparator.client.giphy.GiphyNegativeConditionMocks;
import ru.yoursweet667.trendсomparator.client.giphy.GiphyPositiveConditionMocks;
import ru.yoursweet667.trendсomparator.client.model.Gif;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.util.StreamUtils.copyToString;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WireMockConfig.class})
class GiphyClientIntegrationTest {

    @Autowired
    private WireMockServer mockGiphyService;

    @Autowired
    private GiphyClient giphyClient;

    @BeforeEach
    void setUp() throws IOException {
        GiphyPositiveConditionMocks.setupMockBooksResponse(mockGiphyService);
        GiphyNegativeConditionMocks.setupMockBooksResponse(mockGiphyService);
    }

    @Test
    void getGifTrendGrow_returnGif() throws IOException {

        //when + then
        assertEquals(giphyClient.getGifTrendGrow().getData().get(0), new Gif("test-gif-url"));
    }

    @Test
    void getGifTrendDecrease_returnGif() throws IOException {

        //when + then
        assertEquals(giphyClient.getGifTrendDecrease().getData().get(0), new Gif("test-gif-url"));
    }
}