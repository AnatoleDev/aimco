package com.task.app.core.data.utils;

import com.task.app.core.data.Expression;

public class Multiple implements Expression {

    private Expression left;
    private Expression right;

    public Multiple(Expression left, Expression right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public float interpret()
    {
        return right.interpret() / left.interpret();
    }

}
