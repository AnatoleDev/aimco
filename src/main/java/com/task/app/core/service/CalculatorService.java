package com.task.app.core.service;

import com.task.app.core.entity.Calculator;

import java.util.List;

public interface CalculatorService {

    Calculator calculate(Calculator calculator);

    Calculator getExpression(final Long expression);

    List<Calculator> getAll();
}
