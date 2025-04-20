package com.VacationCalculator.VacationPaymentCalculator.service.strategy;

import com.VacationCalculator.VacationPaymentCalculator.model.CalculateRequest;
import com.VacationCalculator.VacationPaymentCalculator.service.VacationPaymentCalculatorService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Order(2)
public class CalculatorByDays implements PaymentCalculator {
    private final VacationPaymentCalculatorService service;

    public CalculatorByDays(VacationPaymentCalculatorService service) {
        this.service = service;
    }

    @Override
    public boolean canProcess(CalculateRequest req) {
        return req.getVacationDays() != null;
    }

    @Override
    public BigDecimal CalculateVacationPayment(CalculateRequest req) {
        return service.calculateVacationSalaryByDays(req.getVacationDays(), req.getMeanSalary()
        );
    }
}
