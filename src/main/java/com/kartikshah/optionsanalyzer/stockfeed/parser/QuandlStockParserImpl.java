package com.kartikshah.optionsanalyzer.stockfeed.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.kartikshah.optionsanalyzer.dto.TickerDataDto;
import com.kartikshah.optionsanalyzer.dto.TickerDto;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by kartik on 10/22/15.
 */
@Component
public class QuandlStockParserImpl implements StockParser
{

    @Override
    public TickerDto parse(String jsonString)
    {
        TickerDto tickerDto = new TickerDto();
        try
        {
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jsonParser = jsonFactory.createParser(jsonString);
            while (!jsonParser.isClosed())
            {
                JsonToken token = jsonParser.nextToken();

                if (token == null) break;

                if (JsonToken.FIELD_NAME.equals(token) && "data".equals(jsonParser.getCurrentName()))
                {
                    token = jsonParser.nextToken();
                    if (!JsonToken.START_ARRAY.equals(token)) break;

                    while (true)
                    {
                        TickerDataDto dataDto = new TickerDataDto();
                        token = jsonParser.nextToken(); //START_ARRAY
                        if (!JsonToken.START_ARRAY.equals(token)) break;

                        token = jsonParser.nextToken();
                        LocalDate date = LocalDate.parse(jsonParser.getText());
                        dataDto.setDate(date);

                        token = jsonParser.nextToken();
                        dataDto.setOpen(Double.parseDouble(jsonParser.getText()));

                        token = jsonParser.nextToken();
                        dataDto.setHigh(Double.parseDouble(jsonParser.getText()));

                        token = jsonParser.nextToken();
                        dataDto.setLow(Double.parseDouble(jsonParser.getText()));

                        token = jsonParser.nextToken();
                        dataDto.setClose(Double.parseDouble(jsonParser.getText()));

                        token = jsonParser.nextToken(); //END_ARRAY
                        tickerDto.addData(date, dataDto);

                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return tickerDto;
    }
}
