package com.kartikshah.optionsanalyzer.manager;

import com.kartikshah.optionsanalyzer.dto.DailyAnalysisResponseDto;
import com.kartikshah.optionsanalyzer.dto.TickerDataDto;
import com.kartikshah.optionsanalyzer.dto.TickerDto;
import com.kartikshah.optionsanalyzer.stockfeed.client.StockFeedServiceClient;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class StatisticsCalculationManager implements StatisticalCalcManager{
    public static int HISTORY_LOOKUP_TIME_IN_WEEKS = 520;

    @Inject
    StockFeedServiceClient client;

    @Inject
    DayToFutureMapper dayToFutureMapper;

    @Inject
    PercentCalculator oneDayPercentCalculator;

    @Inject
    PercentCalculator oneMonthPercentCalculator;

    public DailyAnalysisResponseDto processDailyAnalysis(String symbol){
        Map<LocalDate, Double> map = retrieveStockDataBySymbolAndDate(symbol);

        Map<LocalDate, Long> endOfDayPercentMap = oneDayPercentCalculator.calculateBackPercentage(map);
        Long lastChange = calculateLastChange(endOfDayPercentMap, 0);
        Map<LocalDate, Long> nextDayPercentMap = oneDayPercentCalculator.calculateForwardPercentage(map);
        Map<Long, Map<Long, Long>> nextDaystatistics = dayToFutureMapper.mapDayChangeToFutureChange(endOfDayPercentMap, nextDayPercentMap);
        Map<LocalDate, Long> nextMonthPercentMap = oneMonthPercentCalculator.calculateForwardPercentage(map);
        Map<Long, Map<Long, Long>> nextMonthstatistics = dayToFutureMapper.mapDayChangeToFutureChange(endOfDayPercentMap, nextMonthPercentMap);

        Map<LocalDate, Long> endOfMonthPercentMap = oneMonthPercentCalculator.calculateBackPercentage(map);
        Long lastMonthChange = calculateLastChange(endOfMonthPercentMap, 30);
        Map<Long, Map<Long, Long>> monthPlusOneStatistics = dayToFutureMapper.mapDayChangeToFutureChange(endOfMonthPercentMap, nextDayPercentMap);
        Map<Long, Map<Long, Long>> monthToMonthStatistics = dayToFutureMapper.mapDayChangeToFutureChange(endOfMonthPercentMap, nextMonthPercentMap);

        return new DailyAnalysisResponseDto(symbol, lastChange, nextDaystatistics, nextMonthstatistics, lastMonthChange, monthPlusOneStatistics, monthToMonthStatistics);
    }

    private Map<LocalDate, Double> retrieveStockDataBySymbolAndDate(String symbol) {
        LocalDate date = LocalDate.now().minusWeeks(HISTORY_LOOKUP_TIME_IN_WEEKS);
        TickerDto tickerDto = client.getTickerInfoByDate(symbol, date);
        Map<LocalDate, TickerDataDto> map = tickerDto.getData();
        Map<LocalDate, Double> closeValueMap = map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().getClose()));
        return closeValueMap;
    }

    public Long calculateLastChange(Map<LocalDate, Long> endOfDayPercentMap, int days){
        LocalDate now = LocalDate.now();
        int counter = 1;
        while (endOfDayPercentMap.get(now) == null) {
            if(counter > 4 || endOfDayPercentMap.get(now.minusDays(counter)) != null) break;
            counter++;
        }
        return endOfDayPercentMap.get(now.minusDays(counter));
    }
}