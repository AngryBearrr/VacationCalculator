package com.VacationCalculator.VacationPaymentCalculator.service.strategy;

import com.VacationCalculator.VacationPaymentCalculator.model.CalculateRequest;
import com.VacationCalculator.VacationPaymentCalculator.service.VacationPaymentCalculatorService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(1)
public class CalculatorByDates implements PaymentCalculator{
    private final VacationPaymentCalculatorService service;

    public CalculatorByDates(VacationPaymentCalculatorService service) {
        this.service = service;
    }

    @Override
    public boolean canProcess(CalculateRequest req) {
        return req.getStartDate() != null && req.getEndDate() != null;
    }

    @Override
    public BigDecimal CalculateVacationPayment(CalculateRequest req) {
        return service.calculateVacationSalaryByDates(req.getStartDate(), req.getEndDate(), req.getMeanSalary());
    }
}
