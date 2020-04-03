package com.analyser.services;

import com.analyser.factory.ICSVBuilder;
import com.exception.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class OpenCSVBuilder<E> implements ICSVBuilder {
    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return this.getCsvToBean(reader, csvClass).iterator();
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return this.getCsvToBean(reader, csvClass).parse();
    }

    public <E> CsvToBean<E> getCsvToBean(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean;
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.FILE_INCORRECT_EXCEPTION, e.getMessage());
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION, e.getMessage());
        }
    }
}