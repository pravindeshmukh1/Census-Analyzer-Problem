package com.analyser.adapter;

import com.analyser.dao.CensusDao;
import com.analyser.dto.USStateCensusCsv;
import com.analyser.exception.CensusAnalyserException;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        return super.loadCensusData(USStateCensusCsv.class, csvFilePath[0]);
    }
}
