package com.task.app.core.repositories;

import com.task.app.core.entity.Calculator;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CalculatorRepository extends CrudRepository<Calculator, Long>, JpaSpecificationExecutor<Calculator> {

}
