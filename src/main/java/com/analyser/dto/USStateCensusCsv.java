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

    public USStateCensusCsv(String state, String stateCode, int population, double totalArea, double populationDensity) {
        this.state = state;
        this.stateId = stateCode;
        this.population = population;
        this.populationDensity = populationDensity;
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

    public USStateCensusCsv() {
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getHousingUnit() {
        return housingUnit;
    }

    public void setHousingUnit(int housingUnit) {
        this.housingUnit = housingUnit;
    }

    public double getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(double totalArea) {
        this.totalArea = totalArea;
    }

    public double getWaterArea() {
        return waterArea;
    }

    public void setWaterArea(double waterArea) {
        this.waterArea = waterArea;
    }

    public double getLandArea() {
        return landArea;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(double populationDensity) {
        this.populationDensity = populationDensity;
    }

    public double getHousingDensity() {
        return housingDensity;
    }

    public void setHousingDensity(double housingDensity) {
        this.housingDensity = housingDensity;
    }
}