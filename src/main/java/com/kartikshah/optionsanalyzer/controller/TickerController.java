package com.kartikshah.optionsanalyzer.controller;

import com.kartikshah.optionsanalyzer.dto.DailyAnalysisResponseDto;
import com.kartikshah.optionsanalyzer.manager.StatisticalCalcManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by kartik on 7/10/16.
 */
@Controller
@RequestMapping("/ticker")
public class TickerController
{

    @Inject
    StatisticalCalcManager manager;

    @RequestMapping("/{symbol}")
    public ModelAndView ticker(@PathVariable("symbol") String symbol)
    {
        ModelAndView modelAndView = new ModelAndView("analysis");
        DailyAnalysisResponseDto responseDto = manager.processDailyAnalysis(symbol);
        modelAndView.addObject("symbol", responseDto.getSymbol());

        Long lastChange = responseDto.getLastChange();
        modelAndView.addObject("lastChange", lastChange);

        Map<Long, Long> nextDayStatistics = responseDto.getNextDayStatistics().get(lastChange);
        modelAndView.addObject("nextDayStatistics", convertToCanvasJS(nextDayStatistics));

        Map<Long, Long> nextMonthStatistics = responseDto.getNextMonthStatistics().get(lastChange);
        modelAndView.addObject("nextMonthStatistics", convertToCanvasJS(nextMonthStatistics));

        Long lastMonthChange = responseDto.getLastMonthChange();
        modelAndView.addObject("lastMonthChange", lastMonthChange);

        System.out.println(lastMonthChange);

        Map<Long, Long> monthPlusOneStatistics = responseDto.getMonthPlusOneStatistics().get(lastMonthChange);
        System.out.println(monthPlusOneStatistics);
        modelAndView.addObject("monthPlusOneStatistics", convertToCanvasJS(monthPlusOneStatistics));

        Map<Long, Long> monthToMonthStatistics = responseDto.getMonthToMonthStatistics().get(lastMonthChange);
        modelAndView.addObject("monthToMonthStatistics", convertToCanvasJS(monthToMonthStatistics));

        return modelAndView;
    }

    private List<CanvasJSBarChartData> convertToCanvasJS(Map<Long, Long> statistics){
            return statistics.entrySet().stream()
                    .map(entry -> new CanvasJSBarChartData(entry.getValue(), entry.getKey()))
                    .collect(Collectors.toList());
    }

    class CanvasJSBarChartData {
        Long y;
        String label;


        public CanvasJSBarChartData(Long y, Long label) {
            this.y = y;
            this.label = Long.toString(label);
        }

        @Override
        public String toString() {
            return "{ y:" + y + ", label:\"" + label + "\"}";
        }
    }
}
