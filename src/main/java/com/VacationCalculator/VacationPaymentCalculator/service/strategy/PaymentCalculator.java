package com.VacationCalculator.VacationPaymentCalculator.service.strategy;

import com.VacationCalculator.VacationPaymentCalculator.model.CalculateRequest;

import java.math.BigDecimal;

public interface PaymentCalculator {
    boolean canProcess(CalculateRequest req);
    BigDecimal CalculateVacationPayment (CalculateRequest req);
}
