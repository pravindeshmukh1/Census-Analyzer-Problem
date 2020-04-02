package com.analyser.services;

import com.analyser.dao.StateCodeCsv;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCodeAnalyser {

    private static final String CSV_STATE_CODE_PATH = "src/test/resources/StateCode.csv";

    public int loadStateCodeCsv(String csvStateCodePath) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
