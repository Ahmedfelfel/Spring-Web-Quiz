package com.felfel.questionservice.repositry;

import com.felfel.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Question repo.
 */
@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    /**
     * Find by category ignore case list.
     *
     * @param category the category
     * @return the list
     */
    List<Question> findByCategoryIgnoreCase(String category);

    /**
     * Find random question ids by category list.
     *
     * @param category the category
     * @param noQues   the no ques
     * @return the list
     */
    @Query(value = "SELECT q.id FROM Question q " +
            "WHERE LOWER(q.category) = LOWER(:category) " +
            "ORDER BY RANDOM() " +
            "LIMIT :noQues",
            nativeQuery = true)
    List<Integer> findRandomQuestionIdsByCategory(String category, Integer noQues);
}
