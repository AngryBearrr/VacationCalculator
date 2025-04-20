package com.VacationCalculator.VacationPaymentCalculator.util;

import com.VacationCalculator.VacationPaymentCalculator.exceptions.NegativeSalaryException;
import com.VacationCalculator.VacationPaymentCalculator.exceptions.WrongDateOrderException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {
    @ExceptionHandler(WrongDateOrderException.class)
    public ResponseEntity<ErrorResponse> handleWrongDateOrder(WrongDateOrderException ex) {
        log.error("Wrong dates order exception : {} ", ex.getMessage());
        ErrorResponse body = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(NegativeSalaryException.class)
    public ResponseEntity<ErrorResponse> handleNegativeSalary(NegativeSalaryException ex) {
        log.error("Negative salary exception : {} ", ex.getMessage());
        ErrorResponse body = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleNegativeSalary(ResponseStatusException ex){
        log.error("Response status exception : {} ", ex.getMessage());
        ErrorResponse body = new ErrorResponse(ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
}

@Data
@AllArgsConstructor
class ErrorResponse {
    private final String message;
}
