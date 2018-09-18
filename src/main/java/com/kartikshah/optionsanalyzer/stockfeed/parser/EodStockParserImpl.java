package com.kartikshah.optionsanalyzer.stockfeed.parser;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.kartikshah.optionsanalyzer.dto.TickerDataDto;
import com.kartikshah.optionsanalyzer.dto.TickerDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by kartik on 10/22/15.
 */
@Component
@Qualifier("eodStockParser")
public class EodStockParserImpl implements StockParser
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

                if (!JsonToken.START_ARRAY.equals(token)) break;

                while (true)
                {
                    TickerDataDto dataDto = new TickerDataDto();

                    token = jsonParser.nextToken(); //START_OBJECT
                    if (!JsonToken.START_OBJECT.equals(token)) break;

                    LocalDate date = LocalDate.now();
                    token = jsonParser.nextToken();
                    if(JsonToken.FIELD_NAME.equals(token) && "date".equals(jsonParser.getCurrentName())){
                        token = jsonParser.nextToken();
                        date = LocalDate.parse(jsonParser.getText());
                        dataDto.setDate(date);
                    }

                    token = jsonParser.nextToken(); //FIELD_NAME
                    if(JsonToken.FIELD_NAME.equals(token) && "open".equals(jsonParser.getCurrentName())){
                        token = jsonParser.nextToken();
                        dataDto.setOpen(Double.parseDouble(jsonParser.getText()));
                    }

                    token = jsonParser.nextToken(); //FIELD_NAME
                    if(JsonToken.FIELD_NAME.equals(token) && "high".equals(jsonParser.getCurrentName())){
                        token = jsonParser.nextToken();
                        dataDto.setHigh(Double.parseDouble(jsonParser.getText()));
                    }

                    token = jsonParser.nextToken(); //FIELD_NAME
                    if(JsonToken.FIELD_NAME.equals(token) && "low".equals(jsonParser.getCurrentName())) {
                        token = jsonParser.nextToken();
                        dataDto.setLow(Double.parseDouble(jsonParser.getText()));
                    }

                    token = jsonParser.nextToken();//FIELD_NAME
                    if(JsonToken.FIELD_NAME.equals(token) && "close".equals(jsonParser.getCurrentName())) {

                        token = jsonParser.nextToken();
                        dataDto.setClose(Double.parseDouble(jsonParser.getText()));
                    }

                    token = jsonParser.nextToken();//FIELD_NAME
                    if(JsonToken.FIELD_NAME.equals(token) && "adjusted_close".equals(jsonParser.getCurrentName())) {

                        token = jsonParser.nextToken();
                        dataDto.setAdjustedClose(Double.parseDouble(jsonParser.getText()));
                    }

                    token = jsonParser.nextToken();//FIELD_NAME
                    if(JsonToken.FIELD_NAME.equals(token) && "volume".equals(jsonParser.getCurrentName())) {

                        token = jsonParser.nextToken();
                        dataDto.setVolume(Double.parseDouble(jsonParser.getText()));
                    }
                    token = jsonParser.nextToken(); //END_OBJECT

                    tickerDto.addData(date, dataDto);

                    }
                }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return tickerDto;
    }
}
