package com.felfel.quizservice.controller;

import com.felfel.quizservice.dto.AnswerDto;
import com.felfel.quizservice.dto.QuestionDto;
import com.felfel.quizservice.dto.QuizDto;
import com.felfel.quizservice.model.Quiz;
import com.felfel.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Quiz controller.
 */
@RestController
@RequestMapping("quiz")
public class QuizController {

    /**
     * The Quiz service.
     */
    @Autowired
    QuizService quizService;

    /**
     * Create quiz response entity.
     *
     * @param quizDto the quiz dto
     * @return the response entity
     */
    @PostMapping("quiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDto quizDto)
    {
           Quiz createdQuiz = quizService.createQuiz(quizDto);
           if(createdQuiz!=null){
            return new ResponseEntity<>(createdQuiz,HttpStatus.CREATED);
           }
           else
           {
               return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
           }
    }

    /**
     * Gets quiz.
     *
     * @param quizId the quiz id
     * @return the quiz
     */
    @GetMapping("quiz/{quizId}")
    public ResponseEntity<List<QuestionDto>> getQuiz(@PathVariable Integer quizId)
    {
        List<QuestionDto> userQuiz = quizService.getQuizById(quizId);
        if(userQuiz !=null){
            return new ResponseEntity<>(userQuiz,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Submit quiz response entity.
     *
     * @param quizId      the quiz id
     * @param userAnswers the user answers
     * @return the response entity
     */
    @PostMapping("submit/{quizId}")
    public ResponseEntity<String> submitQuiz(@PathVariable Integer quizId, @RequestBody List<AnswerDto> userAnswers)
    {
        String result = quizService.submitQuiz(quizId, userAnswers);
        if(result !=null){
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
