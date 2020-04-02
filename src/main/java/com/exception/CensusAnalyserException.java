package com.exception;

public class CensusAnalyserException extends Throwable {
    public ExceptionType exceptionType;

    public CensusAnalyserException(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {
        FILE_INCORRECT_EXCEPTION,
        DELIMITER_INCORRECT_EXCEPTION;
    }
}
