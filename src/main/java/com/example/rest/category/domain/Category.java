package com.example.rest.category.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class Category {

    public enum CategoryType {
        CLOTHES, FOOD, BOOK, ETC
    }

    private Long id;
    private String categoryName;
    private CategoryType type;
    private int searchCount;
    private int pagingStartOffset;

    @Builder
    public Category(String categoryName, CategoryType type, int searchCount, int pagingStartOffset) {
        this.categoryName = categoryName;
        this.type = type;
        this.searchCount = searchCount;
        this.pagingStartOffset = pagingStartOffset;
    }
}
