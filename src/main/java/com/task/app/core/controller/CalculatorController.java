package com.task.app.core.controller;


import com.task.app.core.data.dto.CalculatorDTO;
import com.task.app.core.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private CalculatorService service;

    @Autowired
    public CalculatorController(CalculatorService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public List<CalculatorDTO> getAll() {
        return service.getAll().stream()
                .map(CalculatorDTO::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping()
    public CalculatorDTO setExpression(@RequestBody CalculatorDTO dto) {
        return CalculatorDTO.toDTO(service.calculate(dto.toEntity()));
    }

    @GetMapping()
    public CalculatorDTO getExpression(@RequestParam(name = "expressionId") final Long expressionId) {
        return CalculatorDTO.toDTO(service.getExpression(expressionId));
    }

    //количество вычислений на дату
    @GetMapping("/count")
    public int countForData(@RequestParam(name = "date")
                            @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return service.countForData(date);
    }

    //количество заданий содержащих заданную операцию
    @GetMapping("/operation")
    public int operation(@RequestParam(name = "expression") final String expression) {
        return service.operation(expression);
    }

    //список всех заданий на дату
    @GetMapping("/onDate")
    public List<String> onDate(@RequestParam(name = "date")
                                  @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return service.onDate(date);
    }

    //список всех заданий с операцией
    @GetMapping("/onOperation")
    public List<String> onOperation(@RequestParam(name = "expression") final String expression) {
        return service.onOperation(expression);
    }

    //самое используемое число
    @GetMapping("/popular")
    public int operation() {
        return 0;
    }
}