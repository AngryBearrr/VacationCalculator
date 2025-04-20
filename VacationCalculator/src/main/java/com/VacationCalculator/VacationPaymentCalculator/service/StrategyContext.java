package com.VacationCalculator.VacationPaymentCalculator.service;

import com.VacationCalculator.VacationPaymentCalculator.model.CalculateRequest;
import com.VacationCalculator.VacationPaymentCalculator.service.strategy.PaymentCalculator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Component
public class StrategyContext {
    private final List<PaymentCalculator> strats;

    public StrategyContext(List<PaymentCalculator> strategies) {
        this.strats = strategies;
    }

    public BigDecimal execute(CalculateRequest req) {
        return strats.stream()
                .filter(s -> s.canProcess(req))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"wrong parameters list"))
                .CalculateVacationPayment(req);
    }
}
