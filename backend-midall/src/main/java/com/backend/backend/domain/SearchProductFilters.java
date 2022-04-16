package com.backend.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchProductFilters {
    private String productName;
    private String categoryName;
    private Integer categoryId;
}
