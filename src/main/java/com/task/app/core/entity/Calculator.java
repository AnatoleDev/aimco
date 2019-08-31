package com.task.app.core.entity;

import javax.persistence.*;

@Entity
public class Calculator {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    public String getValue() {
        return value;
    }

    public Calculator setValue(String value) {
        this.value = value;
        return this;
    }
}
