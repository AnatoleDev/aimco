package com.task.app.core.repositories;

import com.task.app.core.entity.Calculator;

import java.time.LocalDate;
import java.util.List;

public interface CalculatorRepository {

    Calculator save(Calculator calculator);

    Calculator findById(Long expressionId);

    List<Calculator> findAll();

    List<String> findAllByDate(LocalDate date);

    List<String> findAllByExpressionLike(String expression);

    int countAllByDate(LocalDate date);

    int countAllByExpression(String expression);

}