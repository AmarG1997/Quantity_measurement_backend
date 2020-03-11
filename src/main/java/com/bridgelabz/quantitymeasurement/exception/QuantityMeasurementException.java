package com.bridgelabz.quantitymeasurement.exception;

public class QuantityMeasurementException extends Exception {


    public enum ExceptionType {
        UNIT_TYPE_DIFFERENT,UNIT_TYPE_NOT_AVAILABLE
    }

    private ExceptionType type;

    public QuantityMeasurementException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }

    public QuantityMeasurementException(String message) {
        super(message);
    }
}
