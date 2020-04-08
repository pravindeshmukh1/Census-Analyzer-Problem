package com.analyser.dao;

import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.IndiaStateCodeCsv;
import com.analyser.dto.USStateCensusCsv;

public class CensusDAO {
    public String stateCode;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
    public String state;

    public CensusDAO(IndiaStateCensusCsv indiaStateCensusCsv) {
        this.state = indiaStateCensusCsv.state;
        this.population = (int) indiaStateCensusCsv.population;
        this.areaInSqKm = indiaStateCensusCsv.areaInSqKm;
        this.densityPerSqKm = indiaStateCensusCsv.densityPerSqKm;
    }

    public CensusDAO(IndiaStateCodeCsv indiaStateCodeCsv) {
        this.stateCode = indiaStateCodeCsv.stateCode;
    }

    public CensusDAO(USStateCensusCsv nextCensusCsv) {
    }
}