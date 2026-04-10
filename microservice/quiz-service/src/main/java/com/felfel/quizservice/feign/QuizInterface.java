package com.felfel.quizservice.feign;

import com.felfel.quizservice.dto.AnswerDto;
import com.felfel.quizservice.dto.QuestionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

/**
 * The interface Quiz interface.
 */
@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    /**
     * Generate random question ids response entity.
     *
     * @param category the category
     * @param noQues   the no ques
     * @return the response entity
     */
    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> generateRandomQuestionIds(@RequestParam String category, @RequestParam Integer noQues);

    /**
     * Gets questions by ids.
     *
     * @param questionIds the question ids
     * @return the questions by ids
     */
    @PostMapping("question/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsByIds(@RequestBody List<Integer> questionIds);

    /**
     * Calculate score response entity.
     *
     * @param answerDtos the answer dtos
     * @return the response entity
     */
    @PostMapping("question/score")
    public ResponseEntity<String> calculateScore(@RequestBody List<AnswerDto> answerDtos);

}
