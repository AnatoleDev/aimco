package com.task.app.core.data.utils;

import com.task.app.core.data.Expression;

public class Pow implements Expression {
    private Expression left;
    private Expression right;

    public Pow(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public float interpret() {
        return (float) Math.pow(right.interpret(), left.interpret());
    }
}
