package com.analyser.services;

import com.analyser.dao.StateCensusCsv;
import com.analyser.dao.StateCodeCsv;
import com.exception.CensusAnalyserException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {
    private static final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";
    private static final String CSV_STATE_CODE_PATH = "src/test/resources/StateCode.csv";

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    public int loadCsvData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));) {
            CsvToBeanBuilder<StateCensusCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(StateCensusCsv.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<StateCensusCsv> csvToBean = csvToBeanBuilder.build();
            Iterator<StateCensusCsv> stateCensusCsvIterator = csvToBean.iterator();
            int noOfCount = 0;
            while (stateCensusCsvIterator.hasNext()) {
                noOfCount++;
                StateCensusCsv stateCensusCsv = stateCensusCsvIterator.next();
            }
            return noOfCount;
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        }
    }

    public int loadStateCodeCsv(String csvStateCodePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_STATE_CODE_PATH));) {
            CsvToBeanBuilder<StateCodeCsv> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(StateCodeCsv.class).withIgnoreLeadingWhiteSpace(true);
            CsvToBean<StateCodeCsv> csvToBean = csvToBeanBuilder.build();
            Iterator<StateCodeCsv> stateCodeCsvIterator = csvToBean.iterator();
            int noOfCount = 0;
            while (stateCodeCsvIterator.hasNext()) {
                noOfCount++;
                StateCodeCsv stateCodeCsv = stateCodeCsvIterator.next();
            }
            return noOfCount;
        } catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (IOException e) {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        }
    }
}