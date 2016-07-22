package com.kartikshah.optionsanalyzer.quandl;

import com.kartikshah.optionsanalyzer.dto.TickerDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by kartik on 7/24/15.
 */
@Component
public class QuandlServiceClient
{

    private String serviceUrl = "https://www.quandl.com/api/v3/datasets/CBOE/VIX";
    private static final String FORMAT = ".json";
    private static final String QUERY_CLAUSE_START_DATE = "start_date=";
    private static final String QUERY_CLAUSE_END_DATE = "end_date";
    private static final String QUE_MARK = "?";

    @Inject
    private RestTemplate restTemplate;

    @Inject
    private QuandlStockParser quandlStockParser;

    public void setQuandlStockParser(QuandlStockParser quandlStockParser)
    {
        this.quandlStockParser = quandlStockParser;
    }

    @Inject
    public QuandlServiceClient(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    public TickerDto getTickerInfo(){
        String jsonString = restTemplate.getForObject(serviceUrl + FORMAT, String.class);
        TickerDto dto = quandlStockParser.parse(jsonString);
        return dto;
    }

    public TickerDto getTickerInfoByDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        String callUrl = serviceUrl + FORMAT + QUE_MARK + QUERY_CLAUSE_START_DATE + localDate.format(formatter);
        String jsonString = restTemplate.getForObject(callUrl, String.class);
        TickerDto dto = quandlStockParser.parse(jsonString);
        return dto;
    }
}
