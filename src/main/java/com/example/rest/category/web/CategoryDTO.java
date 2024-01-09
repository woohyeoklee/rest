package com.example.rest.category.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CategoryDTO {

    public enum CategoryType {
        CLOTHES, FOOD, BOOK, ETC
    }

    private String categoryName;
    private CategoryType type;
    private int searchCount;
    private int pagingStartOffset;

    public CategoryDTO(String categoryName, CategoryType type, int searchCount, int pagingStartOffset) {
        this.categoryName = categoryName;
        this.type = type;
        this.searchCount = searchCount;
        this.pagingStartOffset = pagingStartOffset;
    }
}
