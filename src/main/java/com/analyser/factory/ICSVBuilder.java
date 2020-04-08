package com.analyser.factory;

import com.analyser.exception.CSVBuilderException;
import com.analyser.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException, CensusAnalyserException;

    <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException, CensusAnalyserException;
}
