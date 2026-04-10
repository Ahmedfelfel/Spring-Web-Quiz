package com.felfel.quizsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Answer dto.
 */
@Data
@AllArgsConstructor
public class AnswerDto {
    private Integer id;
    private String answer;
}
