package com.example.rest.category.application.service;

import com.example.rest.category.application.repository.CategoryRepository;
import com.example.rest.category.domain.Category;
import com.example.rest.category.web.command.CategoryCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class WriteCategoryService {

    private final CategoryRepository repository;

    public void register(String accountId, CategoryCommand command) {

    }


    public void update() {
        log.info("update");
    }

    public void delete() {
        log.info("delete");
    }
}
