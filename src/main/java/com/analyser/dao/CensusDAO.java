package com.analyser.dao;

import com.analyser.dto.StateCensusCsv;
import com.analyser.dto.StateCodeCsv;

public class CensusDAO {
    public String state;
    public long population;
    public long areaInSqKm;
    public long densityPerSqKm;
    public String stateCode;

    public CensusDAO(StateCensusCsv stateCensusCsv) {
        this.state = stateCensusCsv.state;
        this.population = stateCensusCsv.population;
        this.areaInSqKm = stateCensusCsv.areaInSqKm;
        this.densityPerSqKm = stateCensusCsv.densityPerSqKm;
    }
}
