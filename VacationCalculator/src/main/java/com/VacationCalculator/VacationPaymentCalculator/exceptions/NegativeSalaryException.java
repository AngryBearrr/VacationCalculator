package com.VacationCalculator.VacationPaymentCalculator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeSalaryException extends RuntimeException {
    public NegativeSalaryException(String message) {
        super(message + "negativeSalaryException");
    }
}
