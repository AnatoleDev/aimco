package com.task.app.core.entity;

import com.task.app.core.data.Expression;
import com.task.app.core.data.utils.Number;
import com.task.app.core.data.utils.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Stack;

import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isBlank;

@Entity
public class Calculator {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String expression;
    @Transient
    private String expressionRPN;
    @Column
    private LocalDate date;
    @Column
    private float result;

    public String getExpression() {
        return expression;
    }

    public Calculator setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    public float getResult() {
        return result;
    }

    public Calculator setResult(float result) {
        this.result = result;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Calculator setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getExpressionRPN() {
        return expressionRPN;
    }

    public Calculator setExpressionRPN(String expressionRPN) {
        this.expressionRPN = expressionRPN;
        return this;
    }

    @Override
    public String toString() {
        return "Expression: " + expression
                + "\nExpressionRPN: " + expressionRPN
                + "\nResult: " + result;
    }

    public void evaluate() {
        if (isBlank(expression)) {
            return;
        }
        resultPostFix();
        Stack<Expression> stack = new Stack<>();
        float resultRPN = 0;
        for (String token : expressionRPN.split(" ")) {
            if (isOperator(token)) {
                Expression exp = null;
                switch (token) {
                    case "+": exp = stack.push(new Plus(stack.pop(), stack.pop())); break;
                    case "-": exp = stack.push(new Minus(stack.pop(), stack.pop())); break;
                    case "*": exp = stack.push(new Multiple(stack.pop(), stack.pop())); break;
                    case "/": exp = stack.push(new Divide(stack.pop(), stack.pop())); break;
                    case "^": exp = stack.push(new Pow(stack.pop(), stack.pop())); break;
                    default:break;
                }
                if (nonNull(exp)) {
                    resultRPN = exp.interpret();
                    stack.pop();
                    stack.push(new Number(resultRPN));
                }
            }
            if (isNumber(token)) {
                stack.push(new Number(Float.parseFloat(token)));
            }
        }
        result = resultRPN;
    }

    private void resultPostFix() {
        char[] in = expression.toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length; i++) {
            char c = in[i];
            switch (c) {
                case '+':
                case '-':
                    while (!stack.empty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        out.append(' ');
                        out.append(stack.pop());
                    }
                    if (i == 0 || in[i - 1] == '(' || isOperator(String.valueOf(in[i - 1]))) {
                        out.append(c);
                    } else {
                        out.append(' ');
                        stack.push(c);
                    }
                    break;
                case '*':
                case '/':
                case '^':
                    out.append(' ');
                    stack.push(c);
                    break;
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while (!stack.empty() && stack.peek() != '(') {
                        out.append(' ');
                        out.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    out.append(c);
                    break;
            }
        }
        while (!stack.isEmpty()) {
            out.append(' ').append(stack.pop());
        }
        expressionRPN = out.toString();
    }

    private boolean isNumber(final String token) {
        try {
            Float.parseFloat(token);
            return true;
        } catch (NumberFormatException nan) {
            return false;
        }
    }

    private boolean isOperator(final String token) {
        return "+".equals(token) || "-".equals(token)
                || "*".equals(token) || "/".equals(token) || "^".equals(token);
    }
}