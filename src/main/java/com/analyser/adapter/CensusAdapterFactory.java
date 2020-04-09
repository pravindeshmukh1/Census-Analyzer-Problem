package com.analyser.adapter;

import com.analyser.dao.CensusDao;
import com.analyser.exception.CensusAnalyserException;
import com.analyser.services.CensusAnalyser;

import java.util.Map;

public class CensusAdapterFactory {

    public static Map<String, CensusDao> getCensusData(CensusAnalyser.Country country, String[] csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA))
            return new IndiaCensusAdapter().loadCensusData(csvFilePath);
        else if (country.equals(CensusAnalyser.Country.US))
            return new USCensusAdapter().loadCensusData(csvFilePath);
        else
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INVALID_COUNTRY, " Invalid Country");
    }
}