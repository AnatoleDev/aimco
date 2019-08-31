package com.task.app.core.service;

import com.task.app.core.entity.Calculator;

public interface CalculatorService {

    Calculator calculate(final String expression);

    Calculator getExpression(final Long expression);
}
