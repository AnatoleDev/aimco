package com.task.app.core.service.impl;

import com.task.app.core.entity.Calculator;
import com.task.app.core.repositories.CalculatorRepository;
import com.task.app.core.service.CalculatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private CalculatorRepository repository;

    public CalculatorServiceImpl(CalculatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Calculator calculate(Calculator calculator) {
        calculator.evaluate();
        return repository.save(calculator);
    }

    @Override
    public Calculator getExpression(final Long expressionId) {
        return repository.findById(expressionId);
    }

    @Override
    public List<Calculator> getAll() {
        return repository.findAll();
    }

    @Override
    public int countForData(final LocalDate date) {
        return repository.countAllByDate(date);
    }

    @Override
    public int operation(final String expression) {
        return repository.countAllByExpression(expression);
    }

    @Override
    public List<String> onDate(LocalDate date) {
        return repository.findAllByDate(date);
    }

    @Override
    public List<String> onOperation(String expression) {
        return repository.findAllByExpressionLike(expression);
    }
}