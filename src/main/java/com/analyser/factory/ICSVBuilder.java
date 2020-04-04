package com.analyser.factory;

import com.exception.CSVBuilderException;
import com.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException, CensusAnalyserException;

    <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException, CensusAnalyserException;
}
