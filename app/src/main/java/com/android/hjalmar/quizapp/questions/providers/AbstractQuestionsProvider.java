package com.android.hjalmar.quizapp.questions.providers;

import com.android.hjalmar.quizapp.questions.AbstractQuestion;
import com.android.hjalmar.quizapp.utils.RandomPermutationUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hjalmar
 * On 04/12/2017.
 */

public abstract class AbstractQuestionsProvider<E extends AbstractQuestion> {

    protected List<E> questions = new ArrayList<>();

    /**
     * Stores questions in the provider private list.
     *
     * @param otherQuestions Questions to be added
     */
    public void addQuestions(List<E> otherQuestions) {
        questions.addAll(otherQuestions);
    }

    /**
     * Choose random questions from the provider private list.
     *
     * @param k The number of questions selected
     * @return The list of random questions.
     */
    public List<E> getRandomQuestions(int k) {
        return RandomPermutationUtils.getRandomDisposition(questions, k);
    }

}
