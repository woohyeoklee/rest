package com.example.rest.category.web.command;

import com.example.rest.category.web.CategoryDTO;
import lombok.Data;

@Data
public class CategoryCommand {

    private String name;
    private CategoryDTO.CategoryType type;
    private int searchCount;
    private int pagingStartOffset;
}
