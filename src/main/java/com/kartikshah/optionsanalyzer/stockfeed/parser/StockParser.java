package com.kartikshah.optionsanalyzer.stockfeed.parser;

import com.kartikshah.optionsanalyzer.dto.TickerDto;

/**
 * Created by kartik on 10/22/15.
 */
public interface StockParser
{
    TickerDto parse(String jsonString);
}
