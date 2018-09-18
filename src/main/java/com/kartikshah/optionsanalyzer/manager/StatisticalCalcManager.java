package com.kartikshah.optionsanalyzer.manager;

import com.kartikshah.optionsanalyzer.dto.DailyAnalysisResponseDto;
import com.kartikshah.optionsanalyzer.dto.TickerDataDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface StatisticalCalcManager {
    DailyAnalysisResponseDto processDailyAnalysis(String symbol);
}
