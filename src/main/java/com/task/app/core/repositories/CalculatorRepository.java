package com.task.app.core.repositories;

import com.task.app.core.entity.Calculator;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CalculatorRepository extends CrudRepository<Calculator, Long>, JpaSpecificationExecutor<Calculator> {

    Optional<Calculator> findById(Long expressionId);

    List<Calculator> findAll();

    List<Calculator> findAllByDate(LocalDate date);

    List<Calculator> findAllByExpressionLike(String expression);

    int countAllByDate(LocalDate date);

    int countAllByExpression(String expression);

}