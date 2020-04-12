package com.analyser.adapter;

import com.analyser.dao.CensusDao;
import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.IndiaStateCodeCsv;
import com.analyser.exception.CensusAnalyserException;
import com.analyser.factory.CSVBuilderFactory;
import com.analyser.factory.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        censusDaoMap = super.loadCensusData(IndiaStateCensusCsv.class, csvFilePath[0]);
        if (csvFilePath.length == 2){
            this.loadStateCodeCSVData(csvFilePath[1]);
        }
        return censusDaoMap;
    }
    private int loadStateCodeCSVData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            censusDaoMap = new HashMap<>();
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, IndiaStateCodeCsv.class);
            Iterable<IndiaStateCodeCsv> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IndiaStateCodeCsv.class::cast)
                    .forEach(csvState -> censusDaoMap.put(csvState.stateName, new CensusDao(csvState)));
            return censusDaoMap.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        }
    }
}