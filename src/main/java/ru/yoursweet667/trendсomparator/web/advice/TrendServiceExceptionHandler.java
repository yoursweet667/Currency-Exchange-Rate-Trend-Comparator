package ru.yoursweet667.trendсomparator.web.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.yoursweet667.trendсomparator.web.model.ErrorResponse;
import ru.yoursweet667.trendсomparator.exception.RequestedCurrencyNotSupportedException;

@RestControllerAdvice
public class TrendServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RequestedCurrencyNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handle(RequestedCurrencyNotSupportedException exception, WebRequest webRequest) {

        ErrorResponse error = new ErrorResponse(exception.getMessage());
        return handleExceptionInternal(exception, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}