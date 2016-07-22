package com.kartikshah.optionsanalyzer.controller;

import com.kartikshah.optionsanalyzer.quandl.QuandlServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 * Created by kartik on 7/10/16.
 */
@Controller
public class ChartController
{
    @Inject
    QuandlServiceClient client;

    @RequestMapping("/")
    public String chartForDays(Model model)
    {
        LocalDate date = LocalDate.now().minusWeeks(4);
        String dataString = client.getTickerInfoByDate(date).toDataJsonString();
        model.addAttribute("dataString", dataString);
        return "chart";
    }

    @RequestMapping("/all")
    public String main(Model model)
    {
        String dataString = client.getTickerInfo().toDataJsonString();
        model.addAttribute("dataString", dataString);
        return "chart";
    }

}
