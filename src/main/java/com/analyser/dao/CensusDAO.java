package com.analyser.dao;

import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.IndiaStateCodeCsv;
import com.analyser.dto.USStateCensusCsv;

public class CensusDAO {
    public String stateCode;
    public int population;
    public double densityPerSqKm;
    public double areaInSqKm;
   // public String stateName;
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
        this.state = nextCensusCsv.state;
    }

   /* public String stateID;
    public String stateName;
    public String stateCode;
    public long population;
    public long areaInSqKm;
    public int tin;
    public int srNo;
    public float housingDensity;
    public int densityPerSqKm;

    public CensusDAO() {
        this.stateID = stateID;
        this.stateName = stateName;
        this.stateCode = stateCode;
        this.population = population;
        this.areaInSqKm = areaInSqKm;
        this.densityPerSqKm = densityPerSqKm;
        this.tin = tin;
        this.srNo = srNo;
        this.housingDensity = housingDensity;
    }

    public CensusDAO(IndiaStateCensusCsv indiaStateCensusCsv) {
        this.stateName = indiaStateCensusCsv.state;
        this.population = indiaStateCensusCsv.population;
        this.areaInSqKm = indiaStateCensusCsv.areaInSqKm;
        this.densityPerSqKm = indiaStateCensusCsv.densityPerSqKm;
    }

    public CensusDAO(IndiaStateCodeCsv indiaStateCodeCsv) {
        this.stateName = indiaStateCodeCsv.stateName;
        this.stateCode = indiaStateCodeCsv.stateCode;
        this.tin = indiaStateCodeCsv.tin;
        this.srNo = indiaStateCodeCsv.srNo;
    }

    public CensusDAO(USStateCensusCsv nextCensusCsv) {
        this.stateCode = nextCensusCsv.stateId;
        this.stateName = nextCensusCsv.state;
        this.population = nextCensusCsv.population;
        this.housingDensity = (float) nextCensusCsv.housingDensity;
        this.areaInSqKm = (long) nextCensusCsv.totalArea;
        this.densityPerSqKm = (int) nextCensusCsv.populationDensity;
    }*/
}

