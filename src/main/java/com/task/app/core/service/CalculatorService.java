package com.task.app.core.service;

import com.task.app.core.entity.Calculator;

import java.time.LocalDate;
import java.util.List;

public interface CalculatorService {

    Calculator calculate(Calculator calculator);

    Calculator getExpression(final Long expression);

    List<Calculator> getAll();

    int countForData(final LocalDate date);

    int operation(final String expression);

    List<String> onDate(final LocalDate date);

    List<String> onOperation(final String expression);
}
