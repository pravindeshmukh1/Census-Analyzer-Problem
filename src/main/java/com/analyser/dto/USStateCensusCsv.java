package com.analyser.dto;

import com.opencsv.bean.CsvBindByName;

public class USStateCensusCsv {

    @CsvBindByName(column = "State Id", required = true)
    public String stateId;
    @CsvBindByName(column = "State", required = true)
    public String state;
    @CsvBindByName(column = "Population", required = true)
    public int population;
    @CsvBindByName(column = "Housing units", required = true)
    public int housingUnit;
    @CsvBindByName(column = "Total area", required = true)
    public double totalArea;
    @CsvBindByName(column = "Water area", required = true)
    public double waterArea;
    @CsvBindByName(column = "Land area", required = true)
    public double landArea;
    @CsvBindByName(column = "Population Density", required = true)
    public double populationDensity;
    @CsvBindByName(column = "Housing Density", required = true)
    public double housingDensity;

    public USStateCensusCsv(String stateId, String state, int population, int housingUnit, double totalArea, double waterArea, double landArea, double populationDensity, double housingDensity) {
        this.stateId = stateId;
        this.state = state;
        this.population = population;
        this.housingUnit = housingUnit;
        this.totalArea = totalArea;
        this.waterArea = waterArea;
        this.landArea = landArea;
        this.populationDensity = populationDensity;
        this.housingDensity = housingDensity;
    }

    @Override
    public String toString() {
        return "USStateCensusCsv{" +
                "stateId='" + stateId + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", housingUnit=" + housingUnit +
                ", totalArea=" + totalArea +
                ", waterArea=" + waterArea +
                ", landArea=" + landArea +
                ", populationDensity=" + populationDensity +
                ", housingDensity=" + housingDensity +
                '}';
    }
}

