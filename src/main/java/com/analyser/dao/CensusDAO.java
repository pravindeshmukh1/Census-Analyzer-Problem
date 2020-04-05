package com.analyser.dao;

import com.analyser.dto.StateCensusCsv;

public class CensusDAO {
    public String state;
    public int population;
    public double areaInSqKm;
    public double densityPerSqKm;
    public String stateCode;

    public CensusDAO(StateCensusCsv stateCensusCsv) {
        this.state = stateCensusCsv.state;
        this.population = stateCensusCsv.population;
        this.areaInSqKm = stateCensusCsv.areaInSqKm;
        this.densityPerSqKm = stateCensusCsv.densityPerSqKm;
    }

    public CensusDAO(String stateCode) {
        this.stateCode = stateCode;
    }
}
