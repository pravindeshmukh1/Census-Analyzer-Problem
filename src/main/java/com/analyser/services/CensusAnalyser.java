package com.analyser.services;

import com.analyser.dao.StateCensusCsv;
import com.analyser.dao.StateCodeCsv;
import com.analyser.factory.CSVBuilderFactory;
import com.analyser.factory.ICSVBuilder;
import com.exception.CensusAnalyserException;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class CensusAnalyser {
    private static final String CSV_CENSUS_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String CSV_STATE_CODE_FILE_PATH = "src/test/resources/StateCode.csv";

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    List<StateCensusCsv> stateCensusCsvList = null;
    List<StateCodeCsv> stateCodeCsvList = null;

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_CENSUS_FILE_PATH));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCensusCsvList = icsvBuilder.getCSVFileList(reader, StateCensusCsv.class);
            return stateCensusCsvList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        }
    }

    public int loadStateCodeCsv(String csvStateCodePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_STATE_CODE_FILE_PATH));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            stateCodeCsvList = icsvBuilder.getCSVFileList(reader, StateCodeCsv.class);
            return stateCodeCsvList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        }
    }

    public String getStateWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
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
}