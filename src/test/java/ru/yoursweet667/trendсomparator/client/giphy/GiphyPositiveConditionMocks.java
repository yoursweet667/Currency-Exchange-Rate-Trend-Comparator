package ru.yoursweet667.trendсomparator.client.giphy;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class GiphyPositiveConditionMocks {

    public static void setupMockBooksResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/v1/gifs/search?api_key=TXDdUM0TAHW1EwSk6M8Ml8m2NsbphXgc&q=rich&limit=50"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        GiphyPositiveConditionMocks.class.getClassLoader().getResourceAsStream("giphy-example-response.json"),
                                        defaultCharset()))));
    }
}