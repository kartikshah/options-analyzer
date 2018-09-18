package com.kartikshah.optionsanalyzer.dto;

import java.util.Map;

public class DailyAnalysisResponseDto {
    private String symbol;
    private Long lastChange;
    private Map<Long, Map<Long, Long>> nextDayStatistics;
    private Map<Long, Map<Long, Long>> nextMonthStatistics;
    private Long lastMonthChange;
    private Map<Long, Map<Long, Long>> monthPlusOneStatistics;
    private Map<Long, Map<Long, Long>> monthToMonthStatistics;


    public DailyAnalysisResponseDto(String symbol,
                                    Long lastChange,
                                    Map<Long, Map<Long, Long>> nextDayStatistics,
                                    Map<Long, Map<Long, Long>> nextMonthStatistics,
                                    Long lastMonthChange,
                                    Map<Long, Map<Long, Long>> monthPlusOneStatistics,
                                    Map<Long, Map<Long, Long>> monthToMonthStatistics) {
        this.symbol = symbol;
        this.lastChange = lastChange;
        this.nextDayStatistics = nextDayStatistics;
        this.nextMonthStatistics = nextMonthStatistics;
        this.lastMonthChange = lastMonthChange;
        this.monthPlusOneStatistics = monthPlusOneStatistics;
        this.monthToMonthStatistics = monthToMonthStatistics;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Long getLastChange() {
        return lastChange;
    }

    public void setLastChange(Long lastChange) {
        this.lastChange = lastChange;
    }

    public Map<Long, Map<Long, Long>> getNextDayStatistics() {
        return nextDayStatistics;
    }

    public void setNextDayStatistics(Map<Long, Map<Long, Long>> nextDayStatistics) {
        this.nextDayStatistics = nextDayStatistics;
    }

    public Map<Long, Map<Long, Long>> getNextMonthStatistics() {
        return nextMonthStatistics;
    }

    public void setNextMonthStatistics(Map<Long, Map<Long, Long>> nextMonthStatistics) {
        this.nextMonthStatistics = nextMonthStatistics;
    }

    public Map<Long, Map<Long, Long>> getMonthPlusOneStatistics() {
        return monthPlusOneStatistics;
    }

    public void setMonthPlusOneStatistics(Map<Long, Map<Long, Long>> monthPlusOneStatistics) {
        this.monthPlusOneStatistics = monthPlusOneStatistics;
    }

    public Map<Long, Map<Long, Long>> getMonthToMonthStatistics() {
        return monthToMonthStatistics;
    }

    public void setMonthToMonthStatistics(Map<Long, Map<Long, Long>> monthToMonthStatistics) {
        this.monthToMonthStatistics = monthToMonthStatistics;
    }

    public Long getLastMonthChange() {
        return lastMonthChange;
    }

    public void setLastMonthChange(Long lastMonthChange) {
        this.lastMonthChange = lastMonthChange;
    }
}
