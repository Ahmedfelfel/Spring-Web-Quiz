package com.felfel.quizservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Quiz dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

   private String title;
   private String category;
   private Integer noQues;
}
