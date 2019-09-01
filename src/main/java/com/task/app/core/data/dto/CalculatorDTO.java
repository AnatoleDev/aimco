package com.task.app.core.data.dto;

import com.task.app.core.entity.Calculator;

import java.time.LocalDate;

import static java.util.Objects.isNull;

public class CalculatorDTO {
    private String expression;
    private String result;

    public String getExpression() {
        return expression;
    }

    public CalculatorDTO setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public String getResult() {
        return result;
    }

    public CalculatorDTO setResult(String result) {
        this.result = result;
        return this;
    }

    public static CalculatorDTO toDTO(Calculator calculate) {
        if (isNull(calculate)) {
            return null;
        }
        return new CalculatorDTO()
                .setExpression(calculate.getExpression())
                .setResult(String.valueOf(calculate.getResult()));
    }

    public Calculator toEntity() {
        return new Calculator()
                .setDate(LocalDate.now())
                .setExpression(expression);
    }
}
