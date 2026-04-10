package com.felfel.quizsystem.controller;

import com.felfel.quizsystem.model.Question;
import com.felfel.quizsystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The type Question controller.
 */
@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * Gets all questions.
     *
     * @return the all questions
     */
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        if (questions != null && !questions.isEmpty()) {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets questions by category.
     *
     * @param category the category
     * @return the questions by category
     */
    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable  String category) {
        List<Question> questions = questionService.getQuestionsByCategory(category);
        if (questions != null && !questions.isEmpty()) {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Gets question by id.
     *
     * @param id the id
     * @return the question by id
     */
    @GetMapping("question/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
        Question question = questionService.getQuestionById(id);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add question response entity.
     *
     * @param question the question
     * @return the response entity
     */
    @PostMapping("question")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question createQuestion = questionService.addOrUpdateQuestion(question);
        if (createQuestion != null) {
            return new ResponseEntity<>(createQuestion, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update question response entity.
     *
     * @param question the question
     * @return the response entity
     */
    @PutMapping("question")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        Question updatedQuestion = questionService.addOrUpdateQuestion(question);
        if (updatedQuestion != null) {
            return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete question response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("question/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable int id) {
        try {
            questionService.deleteQuestion(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
