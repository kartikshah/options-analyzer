package com.kartikshah.optionsanalyzer.stockfeed.parser;

import com.kartikshah.optionsanalyzer.config.TestAppConfig;
import com.kartikshah.optionsanalyzer.dto.TickerDto;
import com.kartikshah.optionsanalyzer.stockfeed.parser.StockParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;

/**
 * Created by kartik on 10/28/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestAppConfig.class)
public class EodStockParserTest
{

    @Autowired
    @Qualifier("eodStockParser")
    private StockParser eodStockParser;

    public void setEodStockParser(StockParser eodStockParser) {
        this.eodStockParser = eodStockParser;
    }

    @Test
     public void testWiring(){
         assertNotNull(eodStockParser);
     }

    @Test
    public void testParseJsonString(){
        String jsonString = "[{\"date\":\"2017-01-05\",\"open\":\"115.9200\",\"high\":\"116.8642\",\"low\":\"115.8100\",\"close\":\"116.6100\",\"adjusted_close\":\"114.2956\",\"volume\":\"22193587.00\"},{\"date\":\"2017-01-06\",\"open\":\"116.7800\",\"high\":\"118.1600\",\"low\":\"116.4700\",\"close\":\"117.9100\",\"adjusted_close\":\"115.5698\",\"volume\":\"31751900.00\"},{\"date\":\"2017-01-09\",\"open\":\"117.9500\",\"high\":\"119.4300\",\"low\":\"117.9400\",\"close\":\"118.9900\",\"adjusted_close\":\"116.6283\",\"volume\":\"33561948.00\"},{\"date\":\"2017-01-10\",\"open\":\"118.7700\",\"high\":\"119.3800\",\"low\":\"118.3000\",\"close\":\"119.1100\",\"adjusted_close\":\"116.7460\",\"volume\":\"24462051.00\"}]";
        TickerDto dto = eodStockParser.parse(jsonString);
        assertNotNull(dto);
    }

    @Test
    public void testHistogram(){
        Map<Long, List<Long>> eodPercentToNextdayPercentMap = new HashMap<>();
        eodPercentToNextdayPercentMap.put(-5L, Arrays.asList(1L, 2L, -5L, 1L, 3L , 5L, -5L, 1L));
        eodPercentToNextdayPercentMap.put(0L, Arrays.asList(0L, 2L, -5L, 0L, 2L , 5L, -5L, 2L));
        eodPercentToNextdayPercentMap.put(3L, Arrays.asList(10L, 0L, -5L, 10L, 0L , 5L, -5L, 1L));

        Map<Long, Map<Long, Long>> eodPercentToNextDayProbabilities = new HashMap<>();

        eodPercentToNextdayPercentMap.entrySet().forEach(entry -> {
            Long key = entry.getKey();
            List<Long> valueList = entry.getValue();
            int size = valueList.size();
            Map<Long, Long> counters = valueList.stream()
                    .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
            Map<Long, Long> percent = counters.entrySet().stream()
                    .collect(Collectors.toMap(
                            k -> k.getKey(),
                            k -> Math.round(k.getValue()/new Double(size)*100)
                    ));
            eodPercentToNextDayProbabilities.put(key, percent);
        });

        System.out.println(eodPercentToNextDayProbabilities);
  }

}
