package com.kartikshah.optionsanalyzer.manager;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

public class PercentCalculator {

    private int numberOfDays;

    public PercentCalculator(int numberOfDays){
        this.numberOfDays = numberOfDays;
    }

    Map<LocalDate, Long> calculateBackPercentage(Map<LocalDate, Double> map) {
            return map.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey(), e -> Math.round(calculatePercent(map, e.getKey(), e.getValue()))));
    }

    public Map<LocalDate, Long> calculateForwardPercentage(Map<LocalDate, Double> map) {
        return map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> Math.round(calculateNextPercent(map, e.getKey(), e.getValue()))));
    }

    Double calculatePercent(Map<LocalDate, Double> valueMap, LocalDate currentDate, Double currentValue) {
        int daysToSubtract = 1;
        while (valueMap.get(currentDate.minusDays(daysToSubtract + numberOfDays)) == null) {
            daysToSubtract++;
            if (daysToSubtract > 4)
                return new Double(0);
        }
        return (currentValue - valueMap.get(currentDate.minusDays(daysToSubtract + numberOfDays))) / currentValue * 100;
    }

    Double calculateNextPercent(Map<LocalDate, Double> valueMap, LocalDate currentDate, Double currentValue) {
        int daysToAdd = 1;
        while (valueMap.get(currentDate.plusDays(daysToAdd + numberOfDays)) == null) {
            daysToAdd++;
            if (daysToAdd > 4)
                return new Double(0);//Need something better than to denote NaN
        }
        return (valueMap.get(currentDate.plusDays(daysToAdd + numberOfDays)) - currentValue) / currentValue * 100;
    }
}
