package com.kartikshah.optionsanalyzer.dto;

import java.time.LocalDate;

/**
 * Created by kartik on 7/9/16.
 */
public class TickerDataDto
{
    private LocalDate date;
    private double open;
    private double close;
    private double high;
    private double low;
    private double adjustedClose;
    private double volume;

    public TickerDataDto()
    {
    }

    public TickerDataDto(LocalDate date, double open, double close, double high, double low, double adjustedClose, double volume) {
        this.date = date;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.adjustedClose = adjustedClose;
        this.volume = volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getAdjustedClose() {
        return adjustedClose;
    }

    public void setAdjustedClose(double adjustedClose) {
        this.adjustedClose = adjustedClose;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

}
