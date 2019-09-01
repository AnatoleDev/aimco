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

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 6 +");
        assertThat(calculator.getResult()).isEqualTo(60.0f);
    }

    @Test
    @DisplayName("Вычитание")
    void evaluateWhereExpressionsMinus() {
        calculator.setExpression("54-6");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 6 -");
        assertThat(calculator.getResult()).isEqualTo(48.0f);
    }

    @Test
    @DisplayName("Сложение с простыми скобками")
    void evaluateWhereExpressionsParenthesesPlus() {
        calculator.setExpression("54+(10+6)");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 + +");
        assertThat(calculator.getResult()).isEqualTo(70.0f);
    }

    @Test
    @DisplayName("Вычитание с простыми скобками")
    void evaluateWhereExpressionsParenthesesMinus() {
        calculator.setExpression("54-(10-6)");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 - -");
        assertThat(calculator.getResult()).isEqualTo(50.0f);
    }

    @Test
    @DisplayName("Сложение со сложными скобками")
    void evaluateWhereExpressionsHardParenthesesPlus() {
        calculator.setExpression("(54+(10+6))+5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 + + 5 +");
        assertThat(calculator.getResult()).isEqualTo(75.0f);
    }

    @Test
    @DisplayName("Вычитание со сложными скобками")
    void evaluateWhereExpressionsHardParenthesesMinus() {
        calculator.setExpression("(54-(10-6))-5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 - - 5 -");
        assertThat(calculator.getResult()).isEqualTo(45.0f);
    }

    @Test
    @DisplayName("Сложное вычитание и сложение")
    void evaluateWherePlusMinus() {
        calculator.setExpression("(54-(10+6))-5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("54 10 6 + - 5 -");
        assertThat(calculator.getResult()).isEqualTo(33.0f);
    }

    @Test
    @DisplayName("Умножение")
    void evaluateWhereMultiple() {
        calculator.setExpression("10*5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("10 5 *");
        assertThat(calculator.getResult()).isEqualTo(50.0f);
    }

    @Test
    @DisplayName("Деление")
    void evaluateWhereDivide() {
        calculator.setExpression("10/5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("10 5 /");
        assertThat(calculator.getResult()).isEqualTo(2.0f);
    }

    @Test
    @DisplayName("Умножение с простыми скобками")
    void evaluateWhereParenthesesMultiple() {
        calculator.setExpression("2*(10*5)");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("2 10 5 * *");
        assertThat(calculator.getResult()).isEqualTo(100.0f);
    }

    @Test
    @DisplayName("Деление с простыми скобками")
    void evaluateWhereParenthesesDivide() {
        calculator.setExpression("100/(10/5)");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("100 10 5 / /");
        assertThat(calculator.getResult()).isEqualTo(50.0f);
    }

 @Test
    @DisplayName("Умножение со сложными скобками")
    void evaluateWhereHardParenthesesMultiple() {
        calculator.setExpression("(2*10)*(10*5)");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("2 10 * 10 5 * *");
        assertThat(calculator.getResult()).isEqualTo(1000.0f);
    }

    @Test
    @DisplayName("Деление со сложными скобками")
    void evaluateWhereHardParenthesesDivide() {
        calculator.setExpression("(100/50)/(10/5)");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("100 50 / 10 5 / /");
        assertThat(calculator.getResult()).isEqualTo(1.0f);
    }

    @Test
    @DisplayName("Вычисление без степени")
    void evaluateWhereMultipleDivide() {
        calculator.setExpression("(-7*8+9-(9/4.5))");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("-7 8 * 9 9 4.5 / - +");
        assertThat(calculator.getResult()).isEqualTo(-49.0f);
    }

    @Test
    @DisplayName("Вычисление степени")
    void evaluateWherePow() {
        calculator.setExpression("2^2");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("2 2 ^");
        assertThat(calculator.getResult()).isEqualTo(4.0f);
    }

    @Test
    @DisplayName("Тестовое простое вычисление")
    void evaluateWhereEaseFinal() {
        calculator.setExpression("-49^2");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("-49 2 ^");
        assertThat(calculator.getResult()).isEqualTo(2401.0f);
    }

    @Test
    @DisplayName("Тестовое вычисление (-7*8+9-(9/4.5))^2 = 2401.0")
    void evaluateWhereFinalFirst() {
        calculator.setExpression("(-7*8+9-(9/4.5))^2");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("-7 8 * 9 9 4.5 / - + 2 ^");
        assertThat(calculator.getResult()).isEqualTo(2401.0f);
    }

    @Test
    @DisplayName("Тестовое вычисление 9*1+4.5 = 13.5")
    void evaluateWhereFinalSecond() {
        calculator.setExpression("9*1+4.5");
        calculator.evaluate();

        assertThat(calculator.getExpressionRPN()).isEqualTo("9 1 * 4.5 +");
        assertThat(calculator.getResult()).isEqualTo(13.5f);
    }
}