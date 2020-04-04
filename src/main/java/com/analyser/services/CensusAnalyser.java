package com.analyser.services;

import com.analyser.dto.StateCensusCsv;
import com.analyser.dto.StateCodeCsv;
import com.analyser.factory.CSVBuilderFactory;
import com.analyser.factory.ICSVBuilder;
import com.exception.CensusAnalyserException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser {
    private static final String CSV_CENSUS_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String CSV_STATE_CODE_FILE_PATH = "src/test/resources/StateCode.csv";
    private static String CSV_FILE_PATH;

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    List<StateCensusCsv> stateCensusCsvList = null;
    List<StateCodeCsv> stateCodeCsvList = null;
    Map<String, StateCensusCsv> censusCsvMap;
    Map<String, StateCodeCsv> codeCsvMap;

    public CensusAnalyser() {
        this.censusCsvMap = new HashMap<>();
        this.codeCsvMap = new HashMap<>();
    }

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<StateCensusCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, StateCensusCsv.class);
            while (csvFileIterator.hasNext()) {
                StateCensusCsv censusCsv = csvFileIterator.next();
                censusCsvMap.put(censusCsv.state, censusCsv);
                stateCensusCsvList = censusCsvMap.values().stream().collect(Collectors.toList());
            }
            return stateCensusCsvList.size();
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int loadStateCodeCsv(String csvStateCodePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvStateCodePath));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<StateCodeCsv> csvFileIterator = icsvBuilder.getCSVFileIterator(reader, StateCodeCsv.class);
            while (csvFileIterator.hasNext()) {
                StateCodeCsv codeCsv = csvFileIterator.next();
                codeCsvMap.put(codeCsv.stateCode, codeCsv);
                stateCodeCsvList = codeCsvMap.values().stream().collect(Collectors.toList());
            }
            return stateCodeCsvList.size();
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (stateCensusCsvList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data Found");
        Comparator<StateCensusCsv> censusCsvComparator = Comparator.comparing(census -> census.state);
        this.sorting(censusCsvComparator, stateCensusCsvList);
        String sortedCensusJson = new Gson().toJson(stateCensusCsvList);
        return sortedCensusJson;
    }

    public String getStateCodeWiseSortedData() throws CensusAnalyserException {
        if (stateCodeCsvList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Code Data Found");
        Comparator<StateCodeCsv> stateCodeCsvComparator = Comparator.comparing(census -> census.stateCode);
        this.sorting(stateCodeCsvComparator, stateCodeCsvList);
        String sortedStateCodeJson = new Gson().toJson(stateCodeCsvList);
        return sortedStateCodeJson;
    }

    private <E> void sorting(Comparator<E> eComparator, List<E> eList) {
        for (int i = 0; i < eList.size() - 1; i++) {
            for (int j = 0; j < eList.size() - i - 1; j++) {
                E csv1 = eList.get(j);
                E csv2 = eList.get(j + 1);
                if (eComparator.compare(csv1, csv2) > 0) {
                    eList.set(j, csv2);
                    eList.set(j + 1, csv1);
                }
            }
        }
    }
}