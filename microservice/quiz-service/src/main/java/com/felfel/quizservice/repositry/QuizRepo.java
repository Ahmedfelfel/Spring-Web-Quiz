package com.felfel.quizservice.repositry;

import com.felfel.quizservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Quiz repo.
 */
@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {
}
