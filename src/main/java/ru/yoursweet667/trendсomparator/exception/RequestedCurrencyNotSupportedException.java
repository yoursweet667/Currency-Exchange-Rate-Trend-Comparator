package ru.yoursweet667.trendсomparator.exception;

public class RequestedCurrencyNotSupportedException extends RuntimeException{

    public RequestedCurrencyNotSupportedException(String message){
        super(message);
    }
}