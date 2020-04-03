package com.analyser.factory;

import com.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder {
    <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;

    <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException;
}
