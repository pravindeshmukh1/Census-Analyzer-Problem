package com.analyser.factory;

import com.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
    <E> Iterator<E> readCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}
