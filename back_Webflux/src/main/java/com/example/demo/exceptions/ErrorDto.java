package com.example.demo.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class ErrorDto {
    private String message;
}
