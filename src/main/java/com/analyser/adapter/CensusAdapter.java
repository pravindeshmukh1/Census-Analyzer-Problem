package com.analyser.adapter;

import com.analyser.dao.CensusDao;
import com.analyser.dto.IndiaStateCensusCsv;
import com.analyser.dto.USStateCensusCsv;
import com.analyser.exception.CensusAnalyserException;
import com.analyser.factory.CSVBuilderFactory;
import com.analyser.factory.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public abstract class CensusAdapter {
    Map<String, CensusDao> censusDaoMap;
    List<CensusDao> censusDaoList;

    public abstract Map<String, CensusDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException;

    public <E> Map<String, CensusDao> loadCensusData(Class<E> censusCsvClass, String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            censusDaoMap = new HashMap<>();
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, censusCsvClass);
            Iterable<E> csvIterable = () -> csvFileIterator;
            if (censusCsvClass.getName().contains("IndiaStateCensusCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IndiaStateCensusCsv.class::cast)
                        .forEach(censusCsv -> censusDaoMap.put(censusCsv.state, new CensusDao(censusCsv)));
            } else if (censusCsvClass.getName().contains("USStateCensusCsv")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(USStateCensusCsv.class::cast)
                        .forEach(censusCsv -> censusDaoMap.put(censusCsv.state, new CensusDao(censusCsv)));
                censusDaoList = censusDaoMap.values().stream().collect(Collectors.toList());
            }
            return censusDaoMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        }
    }
}