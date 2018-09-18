package com.kartikshah.optionsanalyzer.stockfeed.client;

import com.kartikshah.optionsanalyzer.dto.TickerDto;
import com.kartikshah.optionsanalyzer.stockfeed.parser.StockParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by kartik on 7/24/15.
 */
@Component
public class StockFeedServiceClient
{

    private static String serviceUrl = "https://eodhistoricaldata.com/api/eod/";
    private static final String apiToken = "api_token=OeAFFmMliFG5orCUuwAKQ8l4WWFQ67YX";
    private static final String serviceUrl2 = "&period=d&fmt=json&";
    private static final String FORMAT = ".json";
    private static final String QUERY_CLAUSE_START_DATE = "from=";
    private static final String QUE_MARK = "?";
    private static final String REGION = ".US";

    @Inject
    private RestTemplate restTemplate;

    @Inject
    @Qualifier("eodStockParser")
    private StockParser eodStockParser;

    public void setEodStockParser(StockParser eodStockParser)
    {
        this.eodStockParser = eodStockParser;
    }

    @Inject
    public StockFeedServiceClient(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public TickerDto getTickerInfoByDate(String symbol, LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String callUrl = serviceUrl + symbol + REGION + QUE_MARK + apiToken + serviceUrl2 + QUERY_CLAUSE_START_DATE + localDate.format(formatter);
        System.out.println("Calling URL: " + callUrl);
        String jsonString = restTemplate.getForObject(callUrl, String.class);
        TickerDto dto = eodStockParser.parse(jsonString);
        return dto;
    }
}
