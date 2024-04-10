package com.marianadwarka.dto;

public record CategoryRecord(
        Integer idCategory,
        String nameCategory,
        String descriptionCategory,
        boolean enabledCategory
) {
}
