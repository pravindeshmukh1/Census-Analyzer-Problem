package com.analyser.dto;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCensusCsv {

    @CsvBindByName(column = "State", required = true)
    public String state;
    @CsvBindByName(column = "Population", required = true)
    public long population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    public long areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    public int densityPerSqKm;

    public IndiaStateCensusCsv(String state, long population, long areaInSqKm, int densityPerSqKm) {
        this.state = state;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
    }

    @Override
    public String toString() {
        return "IndiaStateCensusCsv{" +
                "state='" + state + '\'' +
                ", population=" + population +
                ", areaInSqKm=" + areaInSqKm +
                ", densityPerSqKm=" + densityPerSqKm +
                '}';
    }
}

