package com.analyser.dao;

import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.IndiaStateCodeCsv;

public class CensusDAO {
    public String state;
    public int population;
    public int areaInSqKm;
    public int densityPerSqKm;
    public String stateCode;

    public CensusDAO(IndiaStateCensusCsv indiaStateCensusCsv) {
        this.state = indiaStateCensusCsv.state;
        this.population = indiaStateCensusCsv.population;
        this.areaInSqKm = indiaStateCensusCsv.areaInSqKm;
        this.densityPerSqKm = indiaStateCensusCsv.densityPerSqKm;
    }
    public CensusDAO(IndiaStateCodeCsv indiaStateCodeCsv) {
        this.stateCode=indiaStateCodeCsv.stateCode;
    }
}
