package com.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {
    private static final String CSV_FILE_PATH = "src/test/resources/StateCensusData.csv";

    public static void main(String[] args) {
        System.out.println("Welcome Census Analyser");
    }

    public int loadCsvData(String csvFilePath) {
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
            e.printStackTrace();
        }
        return 0;
    }
}
