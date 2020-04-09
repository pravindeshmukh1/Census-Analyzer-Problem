package com.analyser.dao;

import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.IndiaStateCodeCsv;
import com.analyser.dto.USStateCensusCsv;

public class CensusDao {
    private String stateId;
    private int tin;
    private int srNo;
    public String stateCode;
    public int population;
    public int densityPerSqKm;
    public int areaInSqKm;
    public String state;

    public CensusDao(IndiaStateCensusCsv indiaStateCensusCsv) {
        this.state = indiaStateCensusCsv.state;
        this.population = (int) indiaStateCensusCsv.population;
        this.areaInSqKm = indiaStateCensusCsv.areaInSqKm;
        this.densityPerSqKm = indiaStateCensusCsv.densityPerSqKm;
    }

    public CensusDao(IndiaStateCodeCsv indiaStateCodeCsv) {
        this.stateCode = indiaStateCodeCsv.stateCode;
        this.srNo = indiaStateCodeCsv.srNo;
        this.state = indiaStateCodeCsv.stateName;
        this.tin = indiaStateCodeCsv.tin;
    }

    public CensusDao(USStateCensusCsv nextCensusCsv) {

        state = nextCensusCsv.state;
        stateId = nextCensusCsv.stateId;
        population = nextCensusCsv.population;

    }
}