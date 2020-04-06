package com.analyser.dto;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCensusCsv {

    @CsvBindByName(column = "State", required = true)
    public String state;
    @CsvBindByName(column = "Population", required = true)
    public int population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    public int areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    public int densityPerSqKm;

    @Override
    public String toString() {
        return "IndiaStateCensusCsv{" +
                "stateName='" + state + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                '}';
    }
}

