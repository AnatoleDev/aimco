package com.task.app.core.data.utils;

import com.task.app.core.data.Expression;

public class Plus implements Expression {
    private Expression left;
    private Expression right;

    public Plus(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public float interpret() {
        return left.interpret() + right.interpret();
    }
}
