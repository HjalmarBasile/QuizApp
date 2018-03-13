package com.android.hjalmar.quizapp.questions.abstraction;

import java.util.List;

/**
 * Created by hjalmar
 * On 02/12/2017.
 */

public interface IQuestion {

    boolean isMultipleChoice();

    int getScore();

    String getText();

    /**
     * Evaluates user's score given his answers.
     *
     * @param userAnswers The answers given by the user
     * @return The score the user will get
     */
    int evaluateAnswer(List<String> userAnswers);

}
