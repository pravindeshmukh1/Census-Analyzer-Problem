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
            return censusCsvMap.size();
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
            return censusCsvMap.size();
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
        this.sort(censusCsvComparator);
        String sortedCensusJson = new Gson().toJson(stateCensusCsvList);
        return sortedCensusJson;
    }

    private void sort(Comparator<StateCensusCsv> censusCsvComparator) {
        for (int i = 0; i < stateCensusCsvList.size() - 1; i++) {
            for (int j = 0; j < stateCensusCsvList.size() - i - 1; j++) {
                StateCensusCsv censusCsv1 = stateCensusCsvList.get(j);
                StateCensusCsv censusCsv2 = stateCensusCsvList.get(j + 1);
                if (censusCsvComparator.compare(censusCsv1, censusCsv2) > 0) {
                    stateCensusCsvList.set(j, censusCsv2);
                    stateCensusCsvList.set(j + 1, censusCsv1);
                }
            }
        }
    }

    public String getStateCodeWiseSortedData() throws CensusAnalyserException {
        if (stateCodeCsvList.size() == 0)
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No State Code Data Found");
        Comparator<StateCodeCsv> stateCodeCsvComparator = Comparator.comparing(census -> census.stateCode);
        this.sorting(stateCodeCsvComparator);
        String sortedStateCodeJson = new Gson().toJson(stateCodeCsvList);
        return sortedStateCodeJson;
    }

    private void sorting(Comparator<StateCodeCsv> stateCodeCsvComparator) {
        for (int i = 0; i < stateCodeCsvList.size() - 1; i++) {
            for (int j = 0; j < stateCodeCsvList.size() - i - 1; j++) {
                StateCodeCsv codeCsv1 = stateCodeCsvList.get(j);
                StateCodeCsv codeCsv2 = stateCodeCsvList.get(j + 1);
                if (stateCodeCsvComparator.compare(codeCsv1, codeCsv2) > 0) {
                    stateCodeCsvList.set(j, codeCsv2);
                    stateCodeCsvList.set(j + 1, codeCsv1);
                }
            }
        }
    }
}