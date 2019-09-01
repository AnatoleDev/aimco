package com.task.app.core.repositories.impl;

import com.task.app.core.entity.Calculator;
import com.task.app.core.repositories.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class CalculatorRepositoryImpl implements CalculatorRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CalculatorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Calculator save(final Calculator calculator) {
        int update = jdbcTemplate.update(
                "INSERT INTO calculator (id, date, result, expression) VALUES (?, ?, ?, ?)",
                calculator.getId(), calculator.getDate(), calculator.getResult(), calculator.getExpression()
        );
        return update == 0
                ? null
                : calculator;
    }

    @Override
    @Transactional
    public Calculator findById(final Long expressionId) {
        List<Calculator> query = jdbcTemplate.query("SELECT id, date, result, expression FROM calculator WHERE id = " + expressionId,
                (row, rowNum) -> createRow(row));
        return query.isEmpty()
                ? null
                : query.get(0);
    }

    @Override
    @Transactional
    public List<Calculator> findAll() {
        return jdbcTemplate.query("SELECT id, date, result, expression FROM calculator",
                (row, rowNum) -> createRow(row));
    }

    @Override
    @Transactional
    public List<String> findAllByDate(LocalDate date) {
        return jdbcTemplate.query("SELECT expression FROM calculator WHERE date = " + "'" + date + "'",
                (row, rowNum) -> row.getString("expression"));
    }

    @Override
    @Transactional
    public List<String> findAllByExpressionLike(String expression) {
        return jdbcTemplate.query("SELECT expression FROM calculator WHERE expression LIKE '%" + expression + "%'",
                (row, rowNum) -> row.getString("expression"));
    }

    @Override
    @Transactional
    public int countAllByDate(LocalDate date) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM calculator WHERE date = " + "'" + date + "'", Integer.class);
    }

    @Override
    @Transactional
    public int countAllByExpression(String expression) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM calculator WHERE expression LIKE '%" + expression + "%'", Integer.class);
    }

    private Calculator createRow(ResultSet row) throws SQLException {
        return new Calculator()
                .setId(row.getLong("id"))
                .setDate(row.getDate("date").toLocalDate())
                .setResult(row.getFloat("result"))
                .setExpression(row.getString("expression"));
    }
}
