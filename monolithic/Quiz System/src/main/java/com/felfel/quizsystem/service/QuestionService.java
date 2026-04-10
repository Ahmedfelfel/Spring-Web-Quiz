package com.felfel.quizsystem.service;

import com.felfel.quizsystem.model.Question;
import com.felfel.quizsystem.repositry.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * The type Question service.
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    /**
     * Gets all questions.
     *
     * @return the all questions
     */
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    /**
     * Gets questions by category.
     *
     * @param category the category
     * @return the questions by category
     */
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findByCategoryIgnoreCase(category);
    }

    /**
     * Gets question by id.
     *
     * @param id the id
     * @return the question by id
     */
    public Question getQuestionById(int id) {
        return questionRepo.findById(id).orElse(null);
    }

    /**
     * Add or update question question.
     *
     * @param question the question
     * @return the question
     */
    public Question addOrUpdateQuestion(Question question) {
        return questionRepo.save(question);
    }

    /**
     * Delete question.
     *
     * @param id the id
     */
    public void deleteQuestion(int id) {
        questionRepo.deleteById(id);
    }

}
