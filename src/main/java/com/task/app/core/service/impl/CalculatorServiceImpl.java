package com.task.app.core.service.impl;

import com.task.app.core.entity.Calculator;
import com.task.app.core.repositories.CalculatorRepository;
import com.task.app.core.service.CalculatorService;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    private CalculatorRepository repository;

    public CalculatorServiceImpl(CalculatorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Calculator calculate(String expression) {
        final var calculator = new Calculator()
                .setValue(expression);
        return repository.save(calculator);
    }

    @Override
    public Calculator getExpression(final Long expressionId) {
        return repository.findById(expressionId).orElseThrow();
    }
}