package com.analyser.exception;

public class CSVBuilderException extends RuntimeException {
    public ExceptionType type;

    public CSVBuilderException(ExceptionType type, String message) {
    }

    public enum ExceptionType {
        FILE_INCORRECT_EXCEPTION,
        FILE_HEADER_AND_DELIMITER_INCORRECT_EXCEPTION,
    }
}
