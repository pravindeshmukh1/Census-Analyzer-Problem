package com.analyser.factory;

public class CSVBuilderFactory {

    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}