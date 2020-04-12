package com.analyser.dao;

import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.IndiaStateCodeCsv;
import com.analyser.dto.USStateCensusCsv;
import com.analyser.services.CensusAnalyser;

import java.util.Comparator;

public class CensusDao {
    public String state;
    public String stateCode;
    public int population;
    public double totalArea;
    public double populationDensity;

    public CensusDao(IndiaStateCensusCsv indiaCensusCSV) {
        state = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        populationDensity = indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDao(USStateCensusCsv usCensusCSV) {
        state = usCensusCSV.getState();
        totalArea = usCensusCSV.getTotalArea();
        populationDensity = usCensusCSV.getPopulationDensity();
        population = usCensusCSV.getPopulation();
    }

    public CensusDao(IndiaStateCodeCsv csvState) {
        stateCode = csvState.stateCode;
    }

    public static Comparator<CensusDao> getSortComparator(CensusAnalyser.SortingMode mode) {
        if (mode.equals(CensusAnalyser.SortingMode.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(CensusAnalyser.SortingMode.POPULATION))
            return Comparator.comparing(CensusDao::getPopulation).reversed();
        if (mode.equals(CensusAnalyser.SortingMode.DENSITY))
            return Comparator.comparing(CensusDao::getPopulationDensity).reversed();
        if (mode.equals(CensusAnalyser.SortingMode.AREA))
            return Comparator.comparing(CensusDao::getTotalArea).reversed();
        if (mode.equals(CensusAnalyser.SortingMode.STATECODE))
            return Comparator.comparing(census -> census.stateCode);
        return null;
    }

    public int getPopulation() {
        return population;
    }

    private double getTotalArea() {
        return totalArea;
    }

    public double getPopulationDensity() {
        return populationDensity;
    }

    public Object getCensusDTO(CensusAnalyser.Country country, CensusAnalyser.SortingMode mode) {
        if (mode.equals(CensusAnalyser.SortingMode.STATECODE) && country.equals(CensusAnalyser.Country.INDIA))
            return new IndiaStateCodeCsv(stateCode);
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new IndiaStateCensusCsv(state, stateCode, population, totalArea, populationDensity);
        if (country.equals(CensusAnalyser.Country.US))
            return new USStateCensusCsv(state, stateCode, population, totalArea, populationDensity);
        return null;
    }

    public Object getCensusDTO(CensusAnalyser.Country country) {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new IndiaStateCensusCsv(state, stateCode, population, totalArea, populationDensity);
        if (country.equals(CensusAnalyser.Country.US))
            return new USStateCensusCsv(state, stateCode, population, totalArea, populationDensity);
        return null;
    }
}