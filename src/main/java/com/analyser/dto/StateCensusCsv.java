package com.analyser.dto;

import com.opencsv.bean.CsvBindByName;

public class StateCensusCsv {

    @CsvBindByName(column = "State", required = true)
    public static String state;
    @CsvBindByName(column = "Population", required = true)
    public int population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    public double areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    public double densityPerSqKm;

    @Override
    public String toString() {
        return "StateCensusCsv{" +
                "stateName='" + state + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                '}';
    }
}

