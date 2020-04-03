package com.analyser.services;

import com.analyser.dao.StateCensusCsv;
import com.analyser.dao.StateCodeCsv;
import com.exception.CensusAnalyserException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    private static final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String CSV_STATE_CODE_PATH = "src/test/resources/StateCode.csv";

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));) {
            Iterator<StateCensusCsv> stateCensusCsvIterator = new OpenCSVBuilder().readCSVFileIterator(reader, StateCensusCsv.class);
            Iterable<StateCensusCsv> stateCensusCsvIterable = () -> stateCensusCsvIterator;
            int noOfCount = 0;
            noOfCount = (int) StreamSupport.stream(stateCensusCsvIterable.spliterator(), false).count();
            return noOfCount;
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        }
    }

    public int loadStateCodeCsv(String csvStateCodePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_STATE_CODE_PATH));) {
            Iterator<StateCodeCsv> stateCodeCsvIterator = new OpenCSVBuilder().readCSVFileIterator(reader, StateCodeCsv.class);
            Iterable<StateCodeCsv> stateCodeCsvIterable = () -> stateCodeCsvIterator;
            int noOfCount = 0;
            noOfCount = (int) StreamSupport.stream(stateCodeCsvIterable.spliterator(), false).count();
            return noOfCount;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        }
    }
}