package com.kartikshah.optionsanalyzer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kartik on 7/24/15.
 */
@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class TickerDto
{
    private Long tickerKey;
    private String symbol;
    private String name;
    private LocalDateTime lastUpdated;
    private List<TickerDataDto> data;

    public TickerDto()
    {
        this.data = new ArrayList<TickerDataDto>();
    }

    public TickerDto(String symbol, String name)
    {
        this();
        this.symbol = symbol;
        this.name = name;
    }

    public TickerDto(String symbol, String name, LocalDateTime lastUpdated)
    {
        this(symbol, name);
        this.lastUpdated = lastUpdated;
    }

    public TickerDto(Long tickerKey, String symbol, String name, LocalDateTime lastUpdated)
    {
        this(symbol,name, lastUpdated);
        this.tickerKey = tickerKey;
    }


    public String getSymbol()
    {
        return symbol;
    }

    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDateTime getLastUpdated()
    {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated)
    {
        this.lastUpdated = lastUpdated;
    }

    public List<TickerDataDto> getData()
    {
        return data;
    }

    public void setData(List<TickerDataDto> data)
    {
        this.data = data;
    }

    public void addData(TickerDataDto dataDto) {this.data.add(dataDto);}

    public Long getTickerKey()
    {
        return tickerKey;
    }

    public void setTickerKey(Long tickerKey)
    {
        this.tickerKey = tickerKey;
    }

    public String toDataJsonString(){
        String dataString = data.stream().map(d -> d.toJsonString()).collect(Collectors.joining(", "));
        return "[ " + dataString + " ]";
    }
}
