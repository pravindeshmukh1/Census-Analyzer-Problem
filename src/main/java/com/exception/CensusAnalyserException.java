package com.exception;

public class CensusAnalyserException extends Throwable {
    public ExceptionType exceptionType;

    public CensusAnalyserException(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {
        FILE_INCORRECT_EXCEPTION,
        FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION,
        NO_CENSUS_DATA,
        INVALID_COUNTRY
    }
}

