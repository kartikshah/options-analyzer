package com.kartikshah.optionsanalyzer.quandl;

import com.kartikshah.optionsanalyzer.dto.TickerDto;

/**
 * Created by kartik on 10/22/15.
 */
public interface QuandlStockParser
{
    TickerDto parse(String jsonString);
}
