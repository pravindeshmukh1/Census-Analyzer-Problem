package com.analyser.services;

import com.analyser.dao.StateCensusCsv;
import com.analyser.dao.StateCodeCsv;
import com.analyser.factory.CSVBuilderFactory;
import com.analyser.factory.ICSVBuilder;
import com.exception.CSVBuilderException;
import com.exception.CensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CensusAnalyser {
    private static final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String CSV_STATE_CODE_PATH = "src/test/resources/StateCode.csv";

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<StateCensusCsv> stateCensusCsvList = icsvBuilder.getCSVFileList(reader, StateCensusCsv.class);
            return stateCensusCsvList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.type.name(), e.getMessage());
        }
    }

    public int loadStateCodeCsv(String csvStateCodePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_STATE_CODE_PATH));) {
            ICSVBuilder icsvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<StateCodeCsv> stateCodeCsvList = icsvBuilder.getCSVFileList(reader, StateCodeCsv.class);
            return stateCodeCsvList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }
}