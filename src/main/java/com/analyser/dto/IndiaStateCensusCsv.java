package com.analyser.dto;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCensusCsv {

    @CsvBindByName(column = "State", required = true)
    public String state;
    @CsvBindByName(column = "Population", required = true)
    public int population;
    @CsvBindByName(column = "AreaInSqKm", required = true)
    public double areaInSqKm;
    @CsvBindByName(column = "DensityPerSqKm")
    public double densityPerSqKm;

    public IndiaStateCensusCsv(String state, String stateCode, int population, double totalArea, double populationDensity) {
        this.state = stateCode;
        this.state = state;
        this.areaInSqKm = totalArea;
        this.population = population;
        this.densityPerSqKm = populationDensity;
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

    public IndiaStateCensusCsv() {
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getAreaInSqKm() {
        return areaInSqKm;
    }

    public void setAreaInSqKm(int areaInSqKm) {
        this.areaInSqKm = areaInSqKm;
    }

    public double getDensityPerSqKm() {
        return densityPerSqKm;
    }

    public void setDensityPerSqKm(int densityPerSqKm) {
        this.densityPerSqKm = densityPerSqKm;
    }
}

