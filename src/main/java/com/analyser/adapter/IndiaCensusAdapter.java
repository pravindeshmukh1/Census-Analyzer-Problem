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
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDao> loadCensusData(String... csvFilePath) throws CensusAnalyserException {
        Map<String, CensusDao> censusDaoMap = super.loadCensusData(IndiaStateCensusCsv.class, csvFilePath[0]);
        if (csvFilePath.length == 1)
            return censusDaoMap;
        return this.loadStateCodeCSVData(/*censusDaoMap,*/ csvFilePath[1]);
    }

    private Map<String, CensusDao> loadStateCodeCSVData(/*Map<String, CensusDao> censusDaoMap, */String csvFilePath) throws CensusAnalyserException {
        String fileExtension = csvFilePath.substring(csvFilePath.lastIndexOf(".") + 1);
        if (!fileExtension.equals("csv")) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, "File Not Found");
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaStateCodeCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, IndiaStateCodeCsv.class);
            Iterable<IndiaStateCodeCsv> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .filter(csvState -> censusDaoMap.get(csvState.stateName) != null)
                    .forEach(csvState -> censusDaoMap.get(csvState.stateName).stateCode = csvState.stateCode);
            return censusDaoMap;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return censusDaoMap;
    }
}