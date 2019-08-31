package com.task.app.entity;

public class Calculator {

    private String value = "Hello";

    public String getValue() {
        return value;
    }

    public Calculator setValue(String value) {
        this.value = "Ответ" + Integer.parseInt(value);
        return this;
    }
}
