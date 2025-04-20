package com.VacationCalculator.VacationPaymentCalculator.util;

import com.VacationCalculator.VacationPaymentCalculator.service.VacationPaymentCalculatorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HolidaysManager {

    protected static final List<LocalDate> holidays;

    protected static final InputStream inputStream = VacationPaymentCalculatorService.class.getClassLoader().getResourceAsStream("holidays");

    public static List<LocalDate> getHolidays(){
        return Collections.unmodifiableList(holidays);
    }
    //заполняем список праздников
    static {
        try {
            holidays = parseDatesFromFile();
        } catch (IOException e) {
            System.out.println("Error reading holidays file");
            throw new RuntimeException(e);
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing holidays date");
            throw new RuntimeException(e);
        }
    }
    protected static List<LocalDate> parseDatesFromFile() throws IOException {
        if (HolidaysManager.inputStream == null) {
            throw new RuntimeException("File not found");
        }
        List<LocalDate> holidayDates = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(HolidaysManager.inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    // Предполагаем, что каждая строка имеет формат "yyyy-MM-dd"
                    LocalDate date = LocalDate.parse(line);
                    holidayDates.add(date);
                } catch (DateTimeParseException e) {
                    System.err.println("parsing error on line " + line + " (" + e.getMessage() + ")");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("file reading error", e);
        }

        return holidayDates;
    }
}
