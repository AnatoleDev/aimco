package com.task.app.core.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Calculator Test")
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator()
                .setDate(LocalDate.now());
    }

    @Test
    @DisplayName("Сложение")
    void evaluateWhereExpressionsPlus() {
        calculator.setExpression("54+6");
        calculator.evaluate();
        assertThat(calculator.getResult()).isEqualTo("60.0");
    }

    @Test
    @DisplayName("Вычитание")
    void evaluateWhereExpressionsMinus() {
        calculator.setExpression("54-6");
        calculator.evaluate();
        assertThat(calculator.getResult()).isEqualTo("48.0");
    }

    @Test
    @DisplayName("Сложение с простыми скобками")
    void evaluateWhereExpressionsParenthesesPlus() {
        calculator.setExpression("54+(10+6)");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 + +");
        assertThat(calculator.getResult()).isEqualTo("70.0");
    }

    @Test
    @DisplayName("Вычитание с простыми скобками")
    void evaluateWhereExpressionsParenthesesMinus() {
        calculator.setExpression("54-(10-6)");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 - -");
        assertThat(calculator.getResult()).isEqualTo("50.0");
    }

    @Test
    @DisplayName("Сложение со сложными скобками")
    void evaluateWhereExpressionsHardParenthesesPlus() {
        calculator.setExpression("(54+(10+6))+5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 + + 5 +");
        assertThat(calculator.getResult()).isEqualTo("75.0");
    }

    @Test
    @DisplayName("Вычитание со сложными скобками")
    void evaluateWhereExpressionsHardParenthesesMinus() {
        calculator.setExpression("(54-(10-6))-5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 - - 5 -");
        assertThat(calculator.getResult()).isEqualTo("45.0");
    }

    @Test
    @DisplayName("Сложное вычитание и сложение")
    void evaluateWherePlusMinus() {
        calculator.setExpression("(54-(10+6))-5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 + - 5 -");
        assertThat(calculator.getResult()).isEqualTo("33.0");
    }

}