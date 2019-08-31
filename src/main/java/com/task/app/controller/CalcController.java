package com.task.app.controller;


import com.task.app.entity.Calculator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalcController {

    @GetMapping(path = "/{expression}")
    public Calculator getName(@PathVariable(name = "expression") String expression) {
        return new Calculator()
                .setValue(expression);
    }
}