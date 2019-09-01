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
    public Calculator calculate(final String expression) {
        final var calculator = new Calculator()
                .setDate(LocalDate.now())
                .setExpression(expression);
        calculator.evaluate();
        System.out.println(calculator.toString());
        return repository.save(calculator);
    }

    @Override
    public Calculator getExpression(final Long expressionId) {
        return repository.findById(expressionId).orElseThrow();
    }

    @Override
    public List<Calculator> getAll() {
        return repository.findAll();
    }
}