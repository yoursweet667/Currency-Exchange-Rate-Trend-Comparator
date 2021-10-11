package ru.yoursweet667.trendсomparator.client.exchangerates;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.yoursweet667.trendсomparator.client.giphy.GiphyPositiveConditionMocks;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class ExchangeRatesYesterdayTrendNegativeCondition {

    public static void setupMockBooksResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/historical/2020-01-01.json?app_id=1c7d2da6c50d45f581e57d49856e5a2e&%7Bparams%7D&base=qwe"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(
                                copyToString(
                                        GiphyPositiveConditionMocks.class.getClassLoader().getResourceAsStream("rates-null-example-response.json"),
                                        defaultCharset()))));
    }
}
