package com.task.app.core.data.utils;

import com.task.app.core.data.Expression;

public class Minus implements Expression {

    private Expression left;
    private Expression right;

    public Minus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public float interpret() {
        return right.interpret() - left.interpret();
    }
}