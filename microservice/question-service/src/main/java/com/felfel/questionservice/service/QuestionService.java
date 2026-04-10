package com.felfel.questionservice.service;

import com.felfel.questionservice.dto.AnswerDto;
import com.felfel.questionservice.dto.QuestionDto;
import com.felfel.questionservice.model.Question;
import com.felfel.questionservice.repositry.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Question service.
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;

    /**
     * Calculate score string.
     *
     * @param answerDtos the answer dtos
     * @return the string
     */
    public String calculateScore(List<AnswerDto> answerDtos) {
        int score = 0;
       List<Question> allQuestions = new ArrayList<>();
       for(AnswerDto answerDto : answerDtos){
           Question question = questionRepo.findById(answerDto.getId()).orElse(null);
              if(question != null){
                allQuestions.add(question);
              }
        }
        for (int i = 0; i < answerDtos.size(); i++) {
            AnswerDto answerDto = answerDtos.get(i);
            Question question = allQuestions.get(i);
            if (question != null && question.getAnswer().equalsIgnoreCase(answerDto.getAnswer())) {
                score++;
            }
        }
        return "Your score is: " + score + " out of " + allQuestions.size();
    }

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

    /**
     * Generate random question ids list.
     *
     * @param category the category
     * @param noQues   the no ques
     * @return the list
     */
    public List<Integer> generateRandomQuestionIds(String category, Integer noQues) {
       return  questionRepo.findRandomQuestionIdsByCategory(category, noQues);
    }

    /**
     * Gets question dtos by ids.
     *
     * @param questionIds the question ids
     * @return the question dtos by ids
     */
    public List<QuestionDto> getQuestionDtosByIds(List<Integer> questionIds) {
        List<QuestionDto> questionDtos = new ArrayList<>();
        List<Question> questions = questionRepo.findAllById(questionIds);
        for (Question q : questions) {
            if (q != null) {
                QuestionDto qd = new QuestionDto(
                        q.getId(),
                        q.getQuestionTitle(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4()
                );
                questionDtos.add(qd);
            }
        }
        return questionDtos;

    }
}
