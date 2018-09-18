package com.kartikshah.optionsanalyzer.manager;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DayToFutureMapper {

    public Map<Long, Map<Long, Long>> mapDayChangeToFutureChange(Map<LocalDate, Long> endOfDayPercentMap, Map<LocalDate, Long> nextDayPercentMap) {

        Map<Long, List<Long>> endOfDayPercentToNextDayPercentMapping = calculateDayToFuturePercentMapping(endOfDayPercentMap, nextDayPercentMap);

        Map<Long, Map<Long, Long>> eodPercentToNextDayProbabilities = new HashMap<Long, Map<Long, Long>>();

        endOfDayPercentToNextDayPercentMapping.entrySet().forEach(entry -> {
            Long key = entry.getKey();
            List<Long> valueList = entry.getValue();
            int size = valueList.size();
            Map<Long, Long> counters = valueList.stream()
                    .collect(Collectors.groupingBy(p -> p, Collectors.counting()));
            Map<Long, Long> percent = counters.entrySet().stream()
                    .collect(Collectors.toMap(
                            k -> k.getKey(),
                            k -> Math.round(k.getValue() / new Double(size) * 100)
                    ));
            eodPercentToNextDayProbabilities.put(key, new TreeMap<Long, Long>(percent));
        });

        return eodPercentToNextDayProbabilities;
    }

    Map<Long, List<Long>> calculateDayToFuturePercentMapping(Map<LocalDate, Long> endOfDayPercentMap, Map<LocalDate, Long> nextDayPercentMap) {
        Map<Long, List<Long>> endOfDayPercentToNextDayPercentMapping = new HashMap<Long, List<Long>>();

        endOfDayPercentMap.entrySet().forEach(entry -> {
            List<Long> nextCloseList = endOfDayPercentToNextDayPercentMapping.get(entry.getValue());
            if (nextCloseList == null) nextCloseList = new ArrayList<Long>();
            nextCloseList.add(nextDayPercentMap.get(entry.getKey()));
            endOfDayPercentToNextDayPercentMapping.put(entry.getValue(), nextCloseList);

        });
        return endOfDayPercentToNextDayPercentMapping;
    }






}