package com.marianadwarka.dto;

import java.util.List;

public record GenericResponseRecord<T>(
        int status,
        String message,
        List<T> data
) {
}
