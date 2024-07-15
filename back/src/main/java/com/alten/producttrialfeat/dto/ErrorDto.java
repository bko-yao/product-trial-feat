package com.alten.producttrialfeat.dto;

import lombok.*;

/**
 * DTO d'erreur
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode;
    private int code;
    private String message;
    private int status;
}
