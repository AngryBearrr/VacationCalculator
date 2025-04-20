package com.VacationCalculator.VacationPaymentCalculator.controller;
import com.VacationCalculator.VacationPaymentCalculator.model.CalculateRequest;
import com.VacationCalculator.VacationPaymentCalculator.service.StrategyContext;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationCalculatorController {

    private final StrategyContext context;

    public VacationCalculatorController(StrategyContext context) {
        this.context = context;
    }

    @GetMapping
    public BigDecimal calculate(@Valid @RequestParam (required = false) LocalDate startDate,
                                @RequestParam (required = false) LocalDate endDate,
                                @RequestParam BigDecimal meanSalary,
                                @RequestParam (required = false) Integer vacationDays ) {
        CalculateRequest request = new CalculateRequest();
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setMeanSalary(meanSalary);
        request.setVacationDays(vacationDays);
        return context.execute(request);
    }
}