package com.VacationCalculator.VacationPaymentCalculator.service;

import com.VacationCalculator.VacationPaymentCalculator.exceptions.NegativeSalaryException;
import com.VacationCalculator.VacationPaymentCalculator.exceptions.WrongDateOrderException;
import com.VacationCalculator.VacationPaymentCalculator.util.HolidaysManager;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
public class VacationPaymentCalculatorService {
    private static final BigDecimal averageDaysInMonth = BigDecimal.valueOf(29.3);
    public BigDecimal calculateVacationSalaryByDates(LocalDate startDate, LocalDate endDate, BigDecimal meanSalary) {
        if (startDate.isAfter(endDate)) {
            throw new WrongDateOrderException("wrong date order in VacationPaymentCalculator service ");
        }
        if (meanSalary.compareTo(BigDecimal.ZERO) <= 0) {
            throw new NegativeSalaryException("negative salary in VacationPaymentCalculator service ");
        }
        long holidayDays = HolidaysManager.getHolidays().stream()
                .filter(holiday -> holiday.equals(startDate) || holiday.equals(endDate)||
                        holiday.isAfter(startDate) && holiday.isBefore(endDate))
                .count();
        BigDecimal vacationDays = BigDecimal.valueOf(ChronoUnit.DAYS.between(startDate, endDate) + 1 - holidayDays);
        return vacationDays.multiply(meanSalary.divide(averageDaysInMonth, RoundingMode.HALF_UP));
    }

    public BigDecimal calculateVacationSalaryByDays(Integer vacationDays, BigDecimal meanSalary) {
        return meanSalary.divide(averageDaysInMonth, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(vacationDays));
    }
}
