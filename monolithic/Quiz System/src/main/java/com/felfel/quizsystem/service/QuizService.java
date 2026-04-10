package com.felfel.quizsystem.service;

import com.felfel.quizsystem.dto.AnswerDto;
import com.felfel.quizsystem.dto.QuestionDto;
import com.felfel.quizsystem.model.Question;
import com.felfel.quizsystem.model.Quiz;
import com.felfel.quizsystem.repositry.QuestionRepo;
import com.felfel.quizsystem.repositry.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Quiz service.
 */
@Service
public class QuizService {

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private QuizRepo quizRepo;

    /**
     * Create quiz quiz.
     *
     * @param title    the title
     * @param category the category
     * @param noQues   the no ques
     * @return the quiz
     */
    public Quiz createQuiz(String title, String category, Integer noQues) {
        List<Question> questions = questionRepo.makeQuiz(category,noQues);
        Quiz quiz = new Quiz();
        quiz.setQuizTitle(title);
        quiz.setQuestions(questions);

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
        List<QuestionDto> questionDtos = new ArrayList<>();
        if (quiz != null) {
            List<Question> questions = quiz.getQuestions();
            for (Question q : questions) {
                QuestionDto qd = new QuestionDto(q.getId(),
                        q.getQuestionTitle(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4());
                questionDtos.add(qd);
            }
        }
        return questionDtos;
    }

    /**
     * Submit quiz string.
     *
     * @param quizId      the quiz id
     * @param userAnswers the user answers
     * @return the string
     */
    public String submitQuiz(Integer quizId, List<AnswerDto> userAnswers) {
        Quiz quiz = quizRepo.findById(quizId).orElse(null);
        if (quiz == null) {
            return null;
        }

        List<Question> questions = quiz.getQuestions();
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            AnswerDto userAnswer = userAnswers.get(i);

            if (question.getAnswer().equalsIgnoreCase(userAnswer.getAnswer())) {
                score++;
            }
        }

        return "Your score is: " + score + " out of " + questions.size();
    }
}
