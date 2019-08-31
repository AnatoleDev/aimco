package com.task.app.service.impl;

import com.task.app.entity.Calculator;
import com.task.app.service.CalculatorService;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public Calculator calculate(String expression) {
        return new Calculator()
                .setValue(expression);
    }
}