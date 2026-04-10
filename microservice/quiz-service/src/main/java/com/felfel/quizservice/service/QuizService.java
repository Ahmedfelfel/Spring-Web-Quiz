package com.felfel.quizservice.service;

import com.felfel.quizservice.dto.AnswerDto;
import com.felfel.quizservice.dto.QuestionDto;
import com.felfel.quizservice.dto.QuizDto;
import com.felfel.quizservice.feign.QuizInterface;
import com.felfel.quizservice.model.Quiz;
import com.felfel.quizservice.repositry.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * The type Quiz service.
 */
@Service
public class QuizService {


    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;

    /**
     * Create quiz quiz.
     *
     * @param quizDto the quiz dto
     * @return the quiz
     */
    public Quiz createQuiz(QuizDto quizDto) {
        String title = quizDto.getTitle();
        String category = quizDto.getCategory();
        Integer noQues = quizDto.getNoQues();
        List<Integer> questions = quizInterface.generateRandomQuestionIds(category,noQues).getBody();
        Quiz quiz = new Quiz();
        quiz.setQuizTitle(title);
        quiz.setQuestionsNo(questions);
        return quizRepo.save(quiz);
    }

    /**
     * Gets quiz by id.
     *
     * @param quizId the quiz id
     * @return the quiz by id
     */
    public List<QuestionDto> getQuizById(Integer quizId) {
        Quiz quiz = quizRepo.findById(quizId).orElse(null);
        if(quiz != null){
            List<Integer> questionIds = quiz.getQuestionsNo();
            return quizInterface.getQuestionsByIds(questionIds).getBody();
        }
        return null;
    }

    /**
     * Submit quiz string.
     *
     * @param quizId      the quiz id
     * @param userAnswers the user answers
     * @return the string
     */
    public String submitQuiz(Integer quizId, List<AnswerDto> userAnswers) {
           return quizInterface.calculateScore(userAnswers).getBody();
    }
}
