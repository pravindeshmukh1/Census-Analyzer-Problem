package com.analyser.factory;

import com.exception.CensusAnalyserException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    <E> Iterator<E> readCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException;
}
