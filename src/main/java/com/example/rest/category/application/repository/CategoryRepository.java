package com.example.rest.category.application.repository;

import com.example.rest.category.domain.Category;
import com.example.rest.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryRepository {
    static final String TABLE = "Category";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void save(Category category) {
        if (category.getId() == null) {
            insert(category);
            return;
        }
        update(category);
    }

    private void insert(Category category) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(category);
        var id = jdbcInsert.executeAndReturnKey(params).longValue();

        Category.builder()
                .categoryName(category.getCategoryName())
                .type(category.getType())
                .searchCount(category.getSearchCount())
                .pagingStartOffset(category.getPagingStartOffset())
                .build();
    }

    private void update(Category category) {
        var sql = String.format("UPDATE %s set categoryName = :categoryName, type = :type, searchCount = :searchCount, pagingStartOffset = :pagingStartOffset WHERE id = :id", TABLE);
        SqlParameterSource params = new BeanPropertySqlParameterSource(category);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public void delete(Category category) {
        var sql = String.format("DELETE FROM %s WHERE id = :id", TABLE);
        SqlParameterSource params = new BeanPropertySqlParameterSource(category);
        namedParameterJdbcTemplate.update(sql, params);
    }
}