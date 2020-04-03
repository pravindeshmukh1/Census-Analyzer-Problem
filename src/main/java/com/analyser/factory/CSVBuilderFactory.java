package com.analyser.factory;

import com.analyser.services.OpenCSVBuilder;

public class CSVBuilderFactory extends Throwable {

    public static ICSVBuilder createCSVBuilder() {
        return new OpenCSVBuilder();
    }
}
