package com.github.torczuk.infrastructure.facade;

import com.github.torczuk.domain.service.DictionaryService;
import org.glassfish.jersey.client.ClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.stream.Collectors;

/**
 * Stateful service using conditional GET method -'if-non-match'
 * According to the documentation Free API is updated hourly.
 */
@Service
public class ApiLayerCurrencyConverterFacade implements CurrencyConverterFacade {
    private final DictionaryService dictionaryService;
    private JsonUsdCurrencyRates latestRates;
    private String etagHeaderValue;
    private String supportedCurrencies;
    private String apiAccessKey;

    @Autowired
    public ApiLayerCurrencyConverterFacade(
            @Value("api.access.key") String apiAccessKey,
            DictionaryService dictionaryService) {
        Assert.notNull(apiAccessKey, "API access key cannot be null");
        this.apiAccessKey = apiAccessKey;
        this.dictionaryService = dictionaryService;
    }

    @PostConstruct protected void initializeSupportedCurrencies() {
        supportedCurrencies = dictionaryService
                .currencies()
                .stream()
                .map(dictionary -> dictionary.getCode())
                .collect(Collectors.joining(","));
    }

    @Override public JsonUsdCurrencyRates latestUsdCurrencyRates() {
        Response response = requestBuilder().get();

        if(HttpStatus.OK.value() == response.getStatus()) {
            update(response);
        }
        return latestRates;
    }

    private Invocation.Builder requestBuilder() {
        Client client = ClientBuilder.newClient(new ClientConfig());
        Invocation.Builder builder = client
                .target("http://apilayer.net/")
                .path("api/live")
                .queryParam("access_key", apiAccessKey)
                .queryParam("currencies", supportedCurrencies)
                .request(MediaType.APPLICATION_JSON_TYPE);

        if(etagHeaderValue != null) {
            builder.header(HttpHeaders.IF_NONE_MATCH, etagHeaderValue);
        }
        return builder;
    }

    private void update(Response response) {
        latestRates = new JsonUsdCurrencyRates(response.getEntity().toString());
        etagHeaderValue = response.getHeaders().get(HttpHeaders.ETAG).toString();
    }

    protected String getSupportedCurrencies() {
        return this.supportedCurrencies;
    }
}