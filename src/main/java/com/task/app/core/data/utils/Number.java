package com.task.app.core.data.utils;

import com.task.app.core.data.Expression;

public class Number implements Expression {
    private float number;

    public Number(float number) {
        this.number = number;
    }
    public float interpret() {
        return number;
    }
}
